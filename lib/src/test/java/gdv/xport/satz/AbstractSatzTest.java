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
 * (c)reated 09.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import com.fasterxml.jackson.databind.ObjectMapper;
import gdv.xport.feld.Feld;
import gdv.xport.feld.VUNummer;
import gdv.xport.satz.feld.common.Kopffelder1bis7;
import gdv.xport.util.SatzTyp;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.junit.BeforeClass;
import org.junit.Test;
import patterntesting.runtime.junit.ObjectTester;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * Hier setzen wir eine Standard-Konfiguration auf, die wir in den verschiedenen JUnit-Tests verwenden.
 *
 * @author oliver
 * @since 09.10.2009
 * @version $Revision$
 */
abstract public class AbstractSatzTest {

    private static final Logger LOG = LogManager.getLogger(AbstractSatzTest.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final File JSON_DIR = new File("target", "json");
    /** zum Testen nehmen wir hier die VU-Nr. der Oerag */
    protected static final VUNummer VU_NUMMER = new VUNummer("5183");

    /**
     * Hier sollte in Satz der zu testenden Satzart zurueckgeliefert werden.
     *
     * @return zu testender Satz
     */
    abstract protected Satz getSatz();

    /**
     * Test aufsetzen.
     */
    @BeforeClass
    public static void setUpTargetDirs() {
        File exportDir = new File("target", "export");
        if (!exportDir.exists() && exportDir.mkdir()) {
            LOG.info("Verzeichnis '{}' wurde angelegt.", exportDir);
        }
        if (!JSON_DIR.exists() && JSON_DIR.mkdirs()) {
            LOG.info("Verzeichnis '{}' wurde angelegt.", JSON_DIR);
        }
    }

    /**
     * Die Satzart ist im ersten Feld (Byte 1 - 4) enthalten und ist in jedem Satz vorhanden (auch Vorsatz und Nachsatz).
     */
    @Test
    public void testSatzart() {
        Satz satz = this.getSatz();
        Feld satzart = satz.getFeld(Kopffelder1bis7.SATZART.getBezeichner());
        assertTrue("expected: is valid", satzart.isValid());
        assertFalse("expected: not empty", satzart.isEmpty());
        assertEquals(satz.getSatzart(), Integer.parseInt(satzart.getInhalt()));
    }

    /**
     * Test-Methode fuer {@link Satz#equals(Object)}.
     */
    @Test
    public void testEquals() {
        Satz satz = this.getSatz();
        Satz sameSatz = this.getSatz();
        ObjectTester.assertEquals(satz, sameSatz);
    }

    @Test
    public void testToJSON() throws IOException {
        Satz satz = this.getSatz();
        checkJSON(satz);
    }

    protected static String checkJSON(Satz satz) throws IOException {
        String json = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(satz);
        SatzTyp satzTyp = satz.getSatzTyp();
        File exportFile = new File(JSON_DIR, String.format("satz%s.json", satzTyp));
        FileUtils.writeStringToFile(exportFile, json, StandardCharsets.UTF_8);
        LOG.info("{} wurde zur manuellen Pruefung in '{}' abgelegt", satz, exportFile);
        MatcherAssert.assertThat(json, containsString(satzTyp.toString()));
        return json;
    }

    /**
     * @param satz Satz
     * @param startByte beginnend bei 1
     * @param endByte beginnend bei 1
     * @param expected erwarteter Export-String
     * @param expectedLength erwartete Laenge
     * @throws IOException sollte bei StringWriter eigentlich nicht vorkommen
     */
    protected final void checkExport(final Satz satz, final int startByte, final int endByte,
            final String expected, final int expectedLength) throws IOException {
        String data = export(satz);
        assertEquals(expectedLength, data.length());
        String toBeChecked = data.substring(startByte - 1, endByte);
        LOG.info("data: " + data.substring(0, 9) + "..." + toBeChecked + "...");
        assertEquals(expected, toBeChecked);
    }

    private static String export(final Satz satz) throws IOException {
        StringWriter swriter = new StringWriter(768);
        satz.export(swriter, "");
        return swriter.toString();
    }

    protected static void checkDatensatz(final Satz datensatz, final String expected) throws IOException {
        StringWriter swriter = new StringWriter(expected.length());
        datensatz.export(swriter, "\n");
        swriter.close();
        assertEquals(expected, swriter.toString());
        assertTrue(datensatz.toShortString() + " is not valid", datensatz.isValid());
    }

    /**
     * Import und Export sollten den gleichen Inhalt ergeben.
     *
     * @param satz Datensatz fuer den Import
     * @param input Inhalt des Datensatzes
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected static void checkImportExport(final Satz satz, final String input) throws IOException {
        satz.importFrom(input);
        String exported = export(satz);
        assertEquals(input.trim(), exported.trim());
    }

    /**
     * Setzt fuer den uebergebenen Satz die normalen Felder mit einem Wert,
     * damit einfache Test-Daten fuer die einzelnen Tests vorhanden sind.
     *
     * @param satz the new up
     */
    public static void setUp(final Satz satz) {
        for (Teildatensatz tds : satz.getTeildatensaetze()) {
            setUp(tds);
        }
    }

    private static void setUp(final Teildatensatz tds) {
        for (Feld feld : tds.getFelder()) {
            String technischerName = feld.getBezeichner().getTechnischerName();
            if (feld.getBezeichnung().equalsIgnoreCase("Versicherungsschein-Nummer")) {
                feld.setInhalt("__123456789__");
            } else if (technischerName.startsWith("GeschaeftsstelleVermittler")) {
                feld.setInhalt("__77777___");
            } else if ((feld.getByteAdresse() > 42) && (feld.getByteAdresse() < 256)
                    && !technischerName.startsWith("Satz") && !technischerName.toLowerCase().contains("art")
                    && !technischerName.startsWith("FolgeNrZurLaufendenPersonenNr")
                    && !technischerName.startsWith("Vorzeichen")
                    && !technischerName.startsWith("Leerstelle")) {
                feld.setInhalt('1');
            }
        }
    }

}
