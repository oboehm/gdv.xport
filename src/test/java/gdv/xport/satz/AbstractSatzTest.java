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

import static org.junit.Assert.assertEquals;

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

    @BeforeClass
    public static void setUpBeforeClass() {
        Config.setVUNummer(VU_NUMMER);
    }

    /**
     * @param satz
     * @param startByte beginnend bei 1
     * @param endByte   beginnend bei 1
     * @param expected
     * @throws IOException
     */
    protected void checkExport(Satz satz, int startByte, int endByte,
            String expected, int expectedLength) throws IOException {
        StringWriter swriter = new StringWriter(768);
        satz.export(swriter);
        String data = swriter.toString();
        assertEquals(expectedLength, data.length());
        String toBeChecked = data.substring(startByte - 1, endByte);
        log.info("data: " + data.substring(0, 9) + "..." + toBeChecked + "...");
        assertEquals(expected, toBeChecked);
    }

}

