/*
 * Copyright (c) 2012 by Oli B.
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
 * (c)reated 29.11.2012 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.feld;

import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.model.SatzX;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import patterntesting.runtime.junit.SmokeRunner;

import static org.junit.Assert.*;

/**
 * Hier testen wir (indirekt) die {@link Feld0001} Annotatation, ob die
 * Werte der einzelnen Felder ok sind.
 *
 * @author oliver
 * @since 0.9.0 (29.11.2012)
 */
@RunWith(SmokeRunner.class)
public class Feld0001Test extends AbstractFeldTest {

    private static SatzX vorsatz;

    /**
     * Hier setzen wir eine Satz mit {@link Feld0001} auf.
     */
    @BeforeClass
    public static void setUpSatz() {
        vorsatz = new SatzX(1, Feld0001.values());
    }

    /**
     * Hier testen wir nur, ob der Vorsatz richtig mit der "Version Satzart
     * 1" aufgesetzt ist.
     */
    @Test
    public void testVersionSatzart0001() {
        Feld x = vorsatz.getTeildatensatz(1).getFeld(Bezeichner.VERSION_SATZART_0001);
        assertNotNull(x);
        assertEquals(Bezeichner.VERSION_SATZART_0001, x.getBezeichner());
        assertEquals(96, x.getByteAdresse());
    }

    /**
     * Wurde das Feld fuer "Art des Absenders" richtig gesetzt? Let's test!
     */
    @Test
    public void testArtDesAbsenders() {
        Teildatensatz tds = vorsatz.getTeildatensatz(1);
        assertTrue("expected: ART_DES_ABSENDERS in " + tds, tds.hasFeld(Feld0001.ART_DES_ABSENDERS));
    }

    /**
     * Weiterer Testfall fuer Issue 10
     * (https://github.com/oboehm/gdv.xport/issues/10).
     */
    @Test
    public void testIssue10() {
        checkEntries(vorsatz, Feld0001.values());
    }

}
