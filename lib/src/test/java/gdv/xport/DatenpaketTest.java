/*
 * Copyright (c) 2009 - 2012 by Oli B.
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
 * (c)reated 23.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport;

import gdv.xport.config.Config;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Datum;
import gdv.xport.feld.Feld;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Nachsatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Vorsatz;
import gdv.xport.satz.model.Satz100;
import gdv.xport.satz.model.Satz220;
import net.sf.oval.ConstraintViolation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import patterntesting.runtime.annotation.IntegrationTest;
import patterntesting.runtime.annotation.SkipTestOn;
import patterntesting.runtime.junit.FileTester;
import patterntesting.runtime.junit.SmokeRunner;

import java.io.*;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit- und Integrations-Tests fuer {@link Datenpaket}-Klasse. Die
 * Integrationstests sind durch "@IntegrationTest" gekennzeichnet und werden
 * ueber
 * <ul>
 *  <li>-Dpatterntesting.integrationTest</li>
 * </ul>
 * aktiviert. Sie sind standardmaessig ausgeblendet, weil sie etwas laenger
 * dauern.
 * <p>
 * Um die Import- oder Export-Tests auszublenden, koennen beim Aufruf die
 * Optionen
 * </p>
 * <ul>
 *  <li>-DSKIP_IMPORT_TEST</li>
 *  <li>-DSKIP_EXPORT_TEST</li>
 * </ul>
 * <p>
 * angegeben werden. Entsprechend beide Properties sind zu setzen, wenn
 * beides, Import- und Export-Tests, ausgeblendet werden sollen.
 * </p>
 *
 * @author oliver
 * @since 23.10.2009
 */
@RunWith(SmokeRunner.class)
public final class DatenpaketTest {

    private static final Logger LOG = LogManager.getLogger(Datenpaket.class);
    /** Fuer jeden Test gibt es ein frisches Datenpaket. */
    private final Datenpaket datenpaket = new Datenpaket();

    /**
     * Test-Methode fuer {@link gdv.xport.Datenpaket#export(java.io.Writer)}.
     * Damit die Assert's der Satzlaenge stimmen, muessen wir das
     * End-of-Datensatz abschalten.
     *
     * @throws IOException falls z.B. die Platte voll ist
     */
    @Test
    @SkipTestOn(property = "SKIP_EXPORT_TEST")
    public void testEmptyExport() throws IOException {
        Datenpaket empty = new Datenpaket();
        StringWriter swriter = new StringWriter(1024);
        empty.export(swriter);
        String data = swriter.toString();
        swriter.close();
        int expectedLength = 1024 + 4 * Config.getEOD().length();
        assertEquals(expectedLength, data.length());
        Vorsatz vorsatz = datenpaket.getVorsatz();
        assertEquals("2.1", vorsatz.getVersion(Bezeichner.VERSION_SATZART_0001));
        assertEquals("1.1", vorsatz.getVersion(Bezeichner.VERSION_SATZART_9999));
        Nachsatz nachsatz = datenpaket.getNachsatz();
        assertEquals(0, nachsatz.getAnzahlSaetze());
        assertEquals(0.0, nachsatz.getGesamtBeitrag().toDouble(), 0.001);
        assertEquals(0.0, nachsatz.getGesamtBeitragBrutto().toDouble(), 0.001);
        assertEquals(0.0, nachsatz.getVersicherungsLeistungen().toDouble(), 0.001);
        assertEquals(0.0, nachsatz.getSchadenbearbeitungsKosten().toDouble(), 0.001);
    }

    /**
     * Tested den Export. Damit die Assert's der Satzlaenge stimmen, muessen wir
     * das End-of-Datensatz abschalten.
     *
     * @throws IOException
     *             falls Temp-Datei nicht angelegt werden kann.
     */
    @Test
    @SkipTestOn(property = "SKIP_EXPORT_TEST")
    public void testExportFile() throws IOException {
        datenpaket.setVuNummer("Hello");
        datenpaket.setAbsender("World");
        datenpaket.setAdressat("Test-Adressat");
        datenpaket.setVermittler("845/666666");
        Datum datum = new Datum();
        datum.setInhalt("13022014");
        datenpaket.setErstellungsDatumVon(datum);
        datenpaket.setErstellungsDatumBis(datum);
        File file = File.createTempFile("datenpaket", ".txt");
        Config.setEOD("\n");
        datenpaket.export(file);
        LOG.info(datenpaket + " was exported to " + file);
        assertTrue(file + " was not created", file.exists());
        FileTester.assertContentEquals(new File("src/test/resources/gdv/xport/test-export.txt"), file);
    }

    /**
     * Testet das Hinzuefuegen eines Datensatzes.
     */
    @Test
    public void testAdd() {
        datenpaket.add(new Satz220());
        Vorsatz vorsatz = datenpaket.getVorsatz();
        assertEquals("2.1", vorsatz.getVersion(Bezeichner.VERSION_SATZART_0001));
        assertEquals("2.1", vorsatz.getVersion(100));
        assertEquals("1.1", vorsatz.getVersion(Bezeichner.VERSION_SATZART_9999));
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

    /**
     * Test-Methode fuer {@link gdv.xport.Datenpaket#getAbsender()}.
     */
    @Test
    public void testGetAbsender() {
        String absender = "Dagobert Duck";
        datenpaket.setAbsender(absender);
        assertEquals(absender, datenpaket.getAbsender());
    }

    /**
     * Test-Methode fuer {@link gdv.xport.Datenpaket#getVermittler()}.
     */
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

    /**
     * Tested den Import.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @IntegrationTest
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testImportFromReader() throws IOException {
        InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt");
        try {
            checkImport(datenpaket, istream);
        } finally {
            istream.close();
        }
    }

    /**
     * Tested einen Import von 2 Datenpaketen.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @IntegrationTest
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testImport2Datenpakete() throws IOException {
        InputStream istream = this.getClass().getResourceAsStream("/zwei_datenpakete.txt");
        try {
            checkImport(datenpaket, istream);
            Datenpaket zwei = new Datenpaket();
            checkImport(zwei, istream);
            LOG.info(datenpaket + " / " + zwei + " imported.");
            assertFalse(datenpaket.equals(zwei));
        } finally {
            istream.close();
        }
    }

    private static void checkImport(final Datenpaket paket, final InputStream istream) throws IOException {
        paket.importFrom(istream);
        assertTrue(paket.isValid());
    }

    /**
     * Tested einen Import von 2 Datenpaketen.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @IntegrationTest
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testImport2DatenpaketeWithReader() throws IOException {
        Reader reader = new FileReader(new File("src/test/resources/zwei_datenpakete.txt"));
        try {
            checkImport(datenpaket, reader);
            Datenpaket zwei = new Datenpaket();
            checkImport(zwei, reader);
            LOG.info(datenpaket + " / " + zwei + " imported.");
            assertFalse(datenpaket.equals(zwei));
        } finally {
            reader.close();
        }
    }

    private static void checkImport(final Datenpaket paket, final Reader reader) throws IOException {
        paket.importFrom(reader);
        assertTrue(paket.isValid());
    }

    /**
     * Tested den Import von einer URL.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @IntegrationTest
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testImportFromURL() throws IOException {
        URL url = this.getClass().getResource("/musterdatei_041222.txt");
        datenpaket.importFrom(url);
        assertTrue(datenpaket.isValid());
    }

    /**
     * Der Test wurde als IntegrationTest markiert, da dazu eine Online-Verbindung
     * noetig ist (die nicht immer vorausgesetzt werden kann).
     *
     * @throws IOException falls man z.B. offline ist
     * @since 0.3
     */
    @IntegrationTest
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testImportFromHTTP() throws IOException {
        URL url = new URL(
                "http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt");
        try {
            datenpaket.importFrom(url);
            assertTrue(datenpaket.isValid());
        } catch (UnknownHostException mayhappen) {
            LOG.warn("Offline? Import von " + url + " abgebrochen!", mayhappen);
        }
    }

    /**
     * Falls Leerzeichen am Zeilenende weggelassen wurden, sollen die einzelnen
     * Saetze trotzdem importiert werden koennen.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @since 0.9.3
     */
    @IntegrationTest
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testImportTrimmed() throws IOException {
        StringBuilder buffer = new StringBuilder();
        InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(istream));
            for (String line = reader.readLine(); StringUtils.isNotEmpty(line); line = reader.readLine()) {
                buffer.append(line.trim() + '\n');
            }
            datenpaket.importFrom(buffer.toString());
            assertTrue(datenpaket.isValid());
        } finally {
            istream.close();
        }
    }


	/**
	 * Pruefe, ob der Wechsel zum naechsten Datensatz korrekt funktioniert,
     * auch wenn der folgende Satz die gleiche Satzart+Sparte besitzt...
     *
     * @throws IOException Signals that an I/O exception has occurred.
	 */
	@IntegrationTest
	@Test
	@SkipTestOn(property = "SKIP_IMPORT_TEST")
	public void testTeildatensatzWechsel() throws IOException {
		InputStream istream = this.getClass().getResourceAsStream("/teildatensatz_wechsel.gdv");
		datenpaket.importFrom(istream);
		assertTrue(datenpaket.isValid());
		assertEquals(2, datenpaket.getDatensaetze().size());
		assertEquals("123456789001", datenpaket.getDatensaetze().get(0).getVersicherungsscheinNummer());
		assertEquals("123456789002", datenpaket.getDatensaetze().get(1).getVersicherungsscheinNummer());
	}


	/**
     * Der Export eines zuvor importierten Datenpakets sollte identisch mit der
     * Ausgangsdatei sein.
     *
     * @since 0.5.0
     * @throws IOException falls die Platte kaputt ist
     */
    @IntegrationTest
    @Test
    @SkipTestOn(property = { "SKIP_IMPORT_TEST", "SKIP_EXPORT_TEST" })
    public void testImportExport() throws IOException {
        Config.setEOD("\n");
        String muster = getResourceAsString("/musterdatei_041222.txt");
        datenpaket.importFrom(muster);
        Satz vertragsteil = datenpaket.getDatensaetze().get(2);
        Feld vertragsstatus = vertragsteil.getFeld(Bezeichner.VERTRAGSSTATUS);
        assertEquals("1", vertragsstatus.getInhalt());
        checkExportWith(muster);
    }

    /**
     * Hier wird die Import-Datei getestet, die mir Igor geschickt hat und
     * mit dem es anfangs Probleme gab.
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testImportIgor() throws IOException {
        importResource("/igor_110120.txt");
    }

    /**
     * Hier wird die Import-Datei getestet, die mir Igor geschickt hat und
     * mit dem es anfangs Probleme gab. Dieses Mal wird aber die Datei als
     * Stream eingelesen.
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testImportIgorAsStream() throws IOException {
        InputStream istream = DatenpaketTest.class.getResourceAsStream("/igor_110120.txt");
        try {
            datenpaket.importFrom(istream);
            assertTrue(datenpaket.validate().toString(), datenpaket.isValid());
        } finally {
            istream.close();
        }
    }

    /**
     * Test-Import von "Bender_Leben.GDV".
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testBenderLeben() throws IOException {
        importResource("/Bender_Leben.GDV");
    }

    /**
     * Test-Import von "Leben_Wagnis2_Riester.GDV".
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testLebenWagnis2Riester() throws IOException {
        importResource("/Leben_Wagnis2_Riester.GDV");
    }

    /**
     * Test-Import von "Leben_Wagnis3_Risikoversicherung.GDV".
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testLebenWagnis3Riskikoversicherung() throws IOException {
        importResource("/Leben_Wagnis3_Risikoversicherung.GDV");
    }

    /**
     * Test-Import von "Leben_Wagnis4_BU.GDV".
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST")
    public void testLebenWagnis4BU() throws IOException {
        importResource("/Leben_Wagnis4_BU.GDV");
    }

    private void importResource(final String name) throws IOException {
        Config.setEOD("\n");
        String content = getResourceAsString(name);
        datenpaket.importFrom(content);
        checkExportWith(content);
    }

    private static String getResourceAsString(final String resource) throws IOException {
        InputStream istream = DatenpaketTest.class.getResourceAsStream(resource);
        try {
            assertNotNull(resource + " not found", istream);
            return IOUtils.toString(istream);
        } finally {
            istream.close();
        }
    }

    private void checkExportWith(final String content) throws IOException {
        StringWriter swriter = new StringWriter(content.length());
        datenpaket.export(swriter);
        swriter.close();
        assertLines(content, swriter.toString());
    }

    private static void assertLines(final String expected, final String paket) throws IOException {
        BufferedReader expectedReader = new BufferedReader(new StringReader(expected));
        BufferedReader paketReader = new BufferedReader(new StringReader(paket));
        for (int line = 1;; line++) {
            String expectedLine = readNextLine(expectedReader);
            String paketLine = readNextLine(paketReader);
            if (expectedLine == null) {
                LOG.info(line + " lines compared (no difference)");
                break;
            }
            assertEquals("difference in line " + line, expectedLine, paketLine);
        }
        expectedReader.close();
    }

    private static String readNextLine(final BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            LOG.debug("EOF reached");
            return null;
        }
        if (line.isEmpty()) {
            LOG.debug("skipping empty line");
            return readNextLine(reader);
        }
        return line;
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
        if (LOG.isTraceEnabled()) {
            List<ConstraintViolation> violations = defect.validate();
            assertTrue("at least 1 violation is expected", (violations.size() > 0));
            for (ConstraintViolation cv : violations) {
                LOG.trace("Violation: " + cv);
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
        Datensatz datensatz = new Satz100();
        datensatz.setVersicherungsscheinNummer("4711");
        datensatz.setFolgenummer(nr);
        return datensatz;
    }

}
