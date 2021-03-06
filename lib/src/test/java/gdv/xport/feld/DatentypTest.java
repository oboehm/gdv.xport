/*
 * Copyright (c) 2014 by Oli B.
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
 * (c)reated 29.10.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit-Test fuer {@link Datentyp}.
 *
 * @author oliver
 * @since 1.0 (29.10.2014)
 */
public class DatentypTest {

    /**
     * Test-Methode fuer {@link Datentyp#asValue(String)}.
     */
    @Test
    public void testAsValue() {
        assertEquals(Datentyp.DATUM, Datentyp.asValue("Datum"));
    }

    /**
     * Test-Methode fuer {@link Datentyp#asValue(String)} mit FLIESSKOMMA
     * als Datentyp. Dieser Datentyp wird extra getestet, da er normalerweise
     * mit einem Sonderzeichen uebergeben wird.
     */
    @Test
    public void testAsValueFliesskomma() {
        assertEquals(Datentyp.FLIESSKOMMA, Datentyp.asValue("Flie\u00dfkomma"));
    }

    @Test
    public void testAsStringNumFeld() {
        NumFeld n = new NumFeld(Bezeichner.of("test"), 2, 1);
        assertEquals("Numerisch", Datentyp.asString(n));
    }

    @Test
    public void testBetrag() {
        Betrag b = new Betrag(Bezeichner.of("Test-Betrag"), 4, 1);
        assertEquals("Betrag", Datentyp.asString(b));
    }

}
