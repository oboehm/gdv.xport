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
 * (c)reated 11.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.feld;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author oliver
 * @since 11.10.2009
 * @version $Revision$
 *
 */
public class BetragMitVorzeichenTest {

    private BetragMitVorzeichen betrag = new BetragMitVorzeichen("Test", 5, 1);

    /**
     * Tested den Konstruktor
     */
    @Test
    public void testBetragMitVorzeichen() {
        assertEquals("0000+", betrag.getInhalt());
    }

    @Test
    public void testToDoublePositive() {
        betrag.setInhalt(1.2);
        assertEquals("0120+", betrag.getInhalt());
        assertEquals(1.2, betrag.toDouble(), 0.001);
    }

    @Test
    public void testToDoubleNegative() {
        betrag.setInhalt(-1.2);
        assertEquals("0120-", betrag.getInhalt());
        assertEquals(-1.2, betrag.toDouble(), 0.001);
    }

    @Test
    public void testIsValid() {
        betrag.setInhalt(99.99);
        assertTrue(betrag + " should be a valid number", betrag.isValid());
    }

}

