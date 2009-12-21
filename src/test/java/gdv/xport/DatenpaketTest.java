/*
 * Copyright (c) 2009 by agentes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 23.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport;

import static gdv.xport.feld.Bezeichner.*;
import static org.junit.Assert.*;
import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.satz.*;

import java.io.*;
import java.net.URL;
import java.util.*;

import net.sf.oval.ConstraintViolation;

import org.apache.commons.logging.*;
import org.junit.*;

import patterntesting.annotation.concurrent.RunTestsParallel;

/**
 * @author oliver
 * @since 23.10.2009
 * @version $Revision$
 *
 */
@RunTestsParallel
public final class DatenpaketTest {

    private static final Log log = LogFactory.getLog(Datenpaket.class);
    /** fuer jeden Test gibt es ein frisches Datenpaket */
    private Datenpaket datenpaket = new Datenpaket();

    /**
     * Damit die Assert's der Satzlaenge stimmen, muessen wir das
     * End-of-Datensatz abschalten.
     *
     * @since 0.3
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Config.setEOD("");
    }

    /**
     * Test method for {@link gdv.xport.Datenpaket#export(java.io.Writer)}.
     * @throws IOException falls z.B. die Platte voll ist
     */
    @Test
    public void testEmptyExport() throws IOException {
        Datenpaket empty = new Datenpaket();
        StringWriter swriter = new StringWriter(1024);
        empty.export(swriter);
        String data = swriter.toString();
        assertEquals(1024, data.length());
        Vorsatz vorsatz = datenpaket.getVorsatz();
        assertEquals("2.1", vorsatz.getVersion(VERSION_VORSATZ));
        assertEquals("1.1", vorsatz.getVersion(VERSION_NACHSATZ));
        Nachsatz nachsatz = datenpaket.getNachsatz();
        assertEquals(0, nachsatz.getAnzahlSaetze());
        assertEquals(0.0, nachsatz.getGesamtBeitrag().toDouble(), 0.001);
        assertEquals(0.0, nachsatz.getGesamtBeitragBrutto().toDouble(), 0.001);
        assertEquals(0.0, nachsatz.getVersicherungsLeistungen().toDouble(), 0.001);
        assertEquals(0.0, nachsatz.getSchadenbearbeitungsKosten().toDouble(), 0.001);
    }

    @Test
    public void testExportFile() throws IOException {
        datenpaket.setAdressat("Test-Adressat");
        datenpaket.setVermittler("845/666666");
        File file = File.createTempFile("datenpaket", ".txt");
        datenpaket.export(file);
        log.info(datenpaket + " was exported to " + file);
        assertTrue(file + " was not created", file.exists());
        assertEquals(1024, file.length());
    }

    @Test
    public void testAdd() {
        Datensatz datensatz = new Adressteil();
        datenpaket.add(datensatz);
        Vorsatz vorsatz = datenpaket.getVorsatz();
        assertEquals("2.1", vorsatz.getVersion(VERSION_VORSATZ));
        assertEquals("2.1", vorsatz.getVersion(100));
        assertEquals("1.1", vorsatz.getVersion(VERSION_NACHSATZ));
        Nachsatz nachsatz = datenpaket.getNachsatz();
        assertEquals(1, nachsatz.getAnzahlSaetze());
    }

    /**
     * Falls kein Datum gesetzt wird, sollte als Default das heutige DAtum
     * zurueckgegeben werden.
     */
    @Test
    public void testGetErstellungsDatum() {
        Datum von = datenpaket.getErstellungsDatumVon();
        Datum bis = datenpaket.getErstellungsDatumBis();
        Datum heute = Datum.heute();
        Date today = heute.toDate();
        assertEquals(today, von.toDate());
        assertEquals(today, bis.toDate());
    }

    @Test
    public void testGetAbsender() {
        String absender = "Dagobert Duck";
        datenpaket.setAbsender(absender);
        assertEquals(absender, datenpaket.getAbsender());
    }

    @Test
    public void testSetVermittler() {
        String vermittler = "08/15";
        datenpaket.setVermittler(vermittler);
        assertEquals(vermittler, datenpaket.getVermittler());
        Vorsatz vorsatz = datenpaket.getVorsatz();
        assertEquals(vermittler, vorsatz.getVermittler());
        Nachsatz nachsatz = datenpaket.getNachsatz();
        assertEquals(vermittler, nachsatz.getVermittler());
    }

    @Test
    public void testImportFromReader() throws IOException {
        InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt");
        try {
            datenpaket.importFrom(istream);
            assertTrue(datenpaket.isValid());
        } finally {
            istream.close();
        }
    }

    @Test
    public void testImportFromURL() throws IOException {
        URL url = this.getClass().getResource("/musterdatei_041222.txt");
        datenpaket.importFrom(url);
        assertTrue(datenpaket.isValid());
    }

    /**
     * Der Test wurde wieder deaktiviert, da dazu eine Online-Verbindung noetig
     * ist (die nicht immer vorausgesetzt werden kann)
     *
     * @since 0.3
     * @throws IOException falls man z.B. offline ist
     */
    //@Test
    public void testImportFromHTTP() throws IOException {
        URL url = new URL(
                "http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt");
        datenpaket.importFrom(url);
        assertTrue(datenpaket.isValid());
    }

    /**
     * Wenn keine VU-Nummer gesetzt wurde, sollte es mind. ein Validierungs-
     * Fehler geben.
     */
    @Test
    public void testValidate() {
        Datenpaket dummy = new Datenpaket(Config.DUMMY_VU_NUMMER);
        checkViolations(dummy);
    }

    /**
     * Da die Validierung ziemlich lange dauert, wurde sie einfach durch
     * eine isValid()-Afrage ersetzt. Falls man die Validierung doch noch
     * will, setzt man einfach den Log-Level auf TRACE.
     *
     * @param defect das defekte Datenpaket
     */
    private void checkViolations(final Datenpaket defect) {
        if (log.isTraceEnabled()) {
            List<ConstraintViolation> violations = defect.validate();
            assertTrue("at least 1 violation is expected", (violations.size() > 0));
            for (ConstraintViolation cv : violations) {
                log.trace("Violation: " + cv);
            }
        }
        assertFalse("at least 1 violation is expected", defect.isValid());
    }

    /**
     * Fuer eine Versicherungsscheinnummer muss die Folgenummer immer mit 1
     * anfangen. Taucht dieser Versicherungsscheinnummer fuer den gleichen Satz
     * ein zweites Mal auf, muss die Folgenummer entsprechend erhoeht werden.
     *
     * @since 0.3
     */
    @Test
    public void testValidateFolgenummer() {
        Datenpaket defect = new Datenpaket("08/15");
        defect.add(createDatensatzWithFolgenummer(1));
        defect.add(createDatensatzWithFolgenummer(3));
        defect.add(createDatensatzWithFolgenummer(2));
        checkViolations(defect);
    }

    private static Datensatz createDatensatzWithFolgenummer(final int nr) {
        Datensatz datensatz = new Adressteil();
        datensatz.setVersicherungsscheinNummer("4711");
        datensatz.setFolgenummer(nr);
        return datensatz;
    }


}
