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
 * (c)reated 09.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static org.junit.Assert.*;

import java.io.*;

import gdv.xport.config.Config;
import gdv.xport.feld.VUNummer;

import org.apache.commons.logging.*;
import org.junit.BeforeClass;

/**
 * Hier setzen wir eine Standard-Konfiguration auf, die wir in den
 * verschiedenen JUnit-Tests verwenden.
 *
 * @author oliver
 * @since 09.10.2009
 * @version $Revision$
 */
public class AbstractSatzTest {

    private static final Log log = LogFactory.getLog(AbstractSatzTest.class);
    /** zum Testen nehmen wir hier die VU-Nr. der Oerag */
    protected static final VUNummer VU_NUMMER = new VUNummer("5183");

    /**
     * Test aufsetzen.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Config.setVUNummer(VU_NUMMER);
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
        log.info("data: " + data.substring(0, 9) + "..." + toBeChecked + "...");
        assertEquals(expected, toBeChecked);
    }

    private static String export(final Satz satz) throws IOException {
        String eod = Config.getEOD();
        Config.setEOD("");
        StringWriter swriter = new StringWriter(768);
        satz.export(swriter);
        String data = swriter.toString();
        Config.setEOD(eod);
        return data;
    }
    
    protected static void checkDatensatz(final Datensatz datensatz, final String expected) throws IOException {
        StringWriter swriter = new StringWriter(expected.length());
        datensatz.export(swriter);
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
        assertEquals(input, exported);
    }

}

