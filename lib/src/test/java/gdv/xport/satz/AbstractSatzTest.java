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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gdv.xport.config.Config;
import gdv.xport.feld.Feld;
import gdv.xport.feld.VUNummer;
import gdv.xport.satz.feld.common.Feld1bis7;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import patterntesting.runtime.junit.ObjectTester;

/**
 * Hier setzen wir eine Standard-Konfiguration auf, die wir in den
 * verschiedenen JUnit-Tests verwenden.
 *
 * @author oliver
 * @since 09.10.2009
 * @version $Revision$
 */
abstract public class AbstractSatzTest {

    private static final Logger LOG = LogManager.getLogger(AbstractSatzTest.class);
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
    public static void setUpBeforeClass() {
        Config.setVUNummer(VU_NUMMER);
    }


    /**
     * Die Satzart ist im ersten Feld (Byte 1 - 4) enthalten und ist in jedem
     * Satz vorhanden (auch Vorsatz und Nachsatz).
     */
    @Test
    public void testSatzart() {
        Satz satz = this.getSatz();
        Feld satzart = satz.getFeld(Feld1bis7.SATZART);
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

    /**
     * @param satz Satz
     * @param startByte beginnend bei 1
     * @param endByte   beginnend bei 1
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
        String data = swriter.toString();
        return data;
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
            if ((feld.getByteAdresse() > 42) && (feld.getByteAdresse() < 256)) {
                feld.setInhalt('1');
            }
        }
    }

}

