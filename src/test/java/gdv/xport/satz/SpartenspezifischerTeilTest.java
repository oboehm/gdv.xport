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
 * (c)reated 08.11.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;
import static org.junit.Assert.*;
import gdv.xport.config.Config;
import gdv.xport.feld.*;

import java.io.*;

import org.apache.commons.logging.*;
import org.junit.Test;

/**
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.1.0 (08.11.2009)
 *
 */
public class SpartenspezifischerTeilTest extends AbstractSatzTest {

    private static final Log log = LogFactory.getLog(SpartenspezifischerTeilTest.class);

    /**
     * Test method for {@link gdv.xport.satz.SpartenspezifischerTeil#SpartenspezifischerTeil(int)}.
     */
    @Test
    public void testSpartenspezifischerTeil70() {
        SpartenspezifischerTeil rechtsschutz = new SpartenspezifischerTeil(70);
        log.info(rechtsschutz + " created.");
        assertEquals(70, rechtsschutz.getSparte());
    }
    
    /**
     * Da inzwischen auch Sparte 30 (Unfall) unterstuetzt wird, sollte ein Import
     * dafuer kein Problem mehr sein.
     * Der Test-Input dazu stammt von der musterdatei_041222.txt von gdv-online.
     * 
     * @since 0.4
     * @throws IOException
     *             sollte eigentlich nicht vorkommen, da wir von einem String
     *             importieren
     */
    @Test
    public void testSparte30() throws IOException {
        Config.setEOD("\n");
        String input = "02209999  030      599999999990199990099990000011Kitzelpfütze   "
            + "               000000Kitzelpfütze                  Martina      "
            + "                 111119791000Hausfrau                      A 1EU"
            + "R0000000000000000041141010520040000000001052004          1      "
            + "\n"
            + "02209999  030      599999999990199990099990000012000000000011305"
            + "0000000000000141950000000000000000000000000000000000000000000000"
            + "0000000000000000000000000010000000000000000 00000000000000000000"
            + "000000000000000000000000000000000000000000000000000  000000    X"
            + "\n";
        assertEquals(514, input.length());
        SpartenspezifischerTeil wagnisdaten = new SpartenspezifischerTeil(30);
        wagnisdaten.importFrom(input);
        Feld x = wagnisdaten.getFeld(ZUSAETZLICHE_SATZKENNUNG, 2);
        assertEquals("X", x.getInhalt());
        StringWriter swriter = new StringWriter(256);
        wagnisdaten.export(swriter);
        assertEquals(input, swriter.toString().substring(0, 514));
        assertTrue("Fehler in " + wagnisdaten.toShortString(), wagnisdaten.isValid());
    }

}

