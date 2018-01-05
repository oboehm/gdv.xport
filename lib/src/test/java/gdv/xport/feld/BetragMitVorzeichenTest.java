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
 * (c)reated 11.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

/**
 * The Class BetragMitVorzeichenTest.
 *
 * @author oliver
 * @since 11.10.2009
 * @version $Revision$
 */
public final class BetragMitVorzeichenTest extends AbstractFeldTest {

    private final BetragMitVorzeichen betrag = new BetragMitVorzeichen(new Bezeichner("Test"), 5, 1);

    /* (non-Javadoc)
     * @see gdv.xport.feld.AbstractFeldTest#getTestFeld()
     */
    @Override
    protected Feld getTestFeld() {
        return this.betrag;
    }

    /**
     * Tested den Konstruktor.
     */
    @Test
    public void testBetragMitVorzeichen() {
        assertEquals("0000+", betrag.getInhalt());
    }

    /**
     * Test to double positive.
     */
    @Test
    public void testToDoublePositive() {
        betrag.setInhalt(1.2);
        assertEquals("0120+", betrag.getInhalt());
        assertEquals(1.2, betrag.toDouble(), 0.001);
    }

    /**
     * Test to double negative.
     */
    @Test
    public void testToDoubleNegative() {
        betrag.setInhalt(-1.2);
        assertEquals("0120-", betrag.getInhalt());
        assertEquals(-1.2, betrag.toDouble(), 0.001);
    }

    /**
     * Test is valid.
     */
    @Test
    public void testIsValid() {
        betrag.setInhalt(99.99);
        assertTrue(betrag + " should be a valid number", betrag.isValid());
    }

    /**
     * Test-Methode fuer {@link BetragMitVorzeichen#format()}.
     */
    @Test
    public void testFormat() {
        betrag.setInhalt(-1.2);
        if ("DE".equals(Locale.getDefault().getCountry())) {
            assertEquals("-1,20", betrag.format());
        }
    }

}

