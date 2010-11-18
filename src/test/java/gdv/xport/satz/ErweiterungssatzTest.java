/*
 * Copyright (c) 2010 by agentes
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
 * (c)reated 18.11.2010 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static org.junit.Assert.*;

import java.io.IOException;

import gdv.xport.config.Config;

import org.apache.commons.logging.*;
import org.junit.*;

/**
 * JUnit-Test fuer Erweiterungssatz.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.4.3 (18.11.2010)
 */
public class ErweiterungssatzTest extends AbstractSatzTest {
    
    private static final Log log = LogFactory.getLog(ErweiterungssatzTest.class);

    /**
     * Der Lesbarkeit halber aktivieren wir das Zeilenende fuer jeden
     * exportierten Satz.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Config.setEOD("\n");
    }

    /**
     * Test method for {@link gdv.xport.satz.Erweiterungssatz#Erweiterungssatz(int)}.
     */
    @Test
    public void testSpartenspezifischerTeil70() {
        Erweiterungssatz rechtschutz = new Erweiterungssatz(70);
        log.info(rechtschutz + " created.");
        assertEquals(70, rechtschutz.getSparte());
    }

    /**
     * Hier wird der Teildatensatz 2 der Sparte 30 (Rechtschutz) getestet.
     * Der Test-Input dazu stammt von der musterdatei_041222.txt von gdv-online.
     *
     * @throws IOException
     *             sollte eigentlich nicht vorkommen, da wir von einem String
     *             importieren
     */
    @Test
    public void testSparte30Teildatensatz2() throws IOException {
        String input = "02219999  030      599999999990199990099990000012000000050000000"
            + "0000025000000000000000000000000000000000000000000000000000000000"
            + "0000000000000000000000000000000000000000000000000000010000000000"
            + "000000000                                                      X"
            + "\n";
        assertEquals(257, input.length());
        Erweiterungssatz wagnisdaten = new Erweiterungssatz(30);
        wagnisdaten.importFrom(input);
        checkDatensatz(wagnisdaten, input);
    }

}

