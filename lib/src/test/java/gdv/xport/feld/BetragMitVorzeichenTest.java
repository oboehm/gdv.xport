/*
 * Copyright (c) 2009 - 2021 by Oli B.
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

import gdv.xport.config.Config;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The Class BetragMitVorzeichenTest.
 *
 * @author oliver
 * @since 11.10.2009
 */
public final class BetragMitVorzeichenTest extends AbstractFeldTest {

    private final BetragMitVorzeichen betrag = new BetragMitVorzeichen(Bezeichner.of("Test"), 5, 1);

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
    public void testToDoubleNegativ() {
        betrag.setInhalt(-1.2);
        assertEquals("0120-", betrag.getInhalt());
        assertEquals(-1.2, betrag.toDouble(), 0.001);
    }

    @Test
    public void testToBigDecimalPositiv() {
        betrag.setInhalt(new BigDecimal("1.20"));
        assertEquals("0120+", betrag.getInhalt());
        assertEquals(new BigDecimal("1.20"), betrag.toBigDecimal());
    }

    @Test
    public void testToBigDecimalNegativ() {
        betrag.setInhalt(new BigDecimal("-1.20"));
        assertEquals("0120-", betrag.getInhalt());
        assertEquals(new BigDecimal("-1.20"), betrag.toBigDecimal());
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

    @Test
    public void testOf() {
        NumFeld betrag = new NumFeld(Bezeichner.GESAMTBEITRAG_BRUTTO, 40, "00000000004711", 2);
        AlphaNumFeld vorzeichen = new Zeichen(Bezeichner.VORZEICHEN, 54, '+');
        BetragMitVorzeichen bmz = BetragMitVorzeichen.of(betrag, vorzeichen);
        assertEquals(54, bmz.getEndAdresse());
        assertEquals("00000000004711+", bmz.getInhalt());
        assertEquals(betrag, bmz.getBetrag());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOfWithException() {
        NumFeld betrag = new NumFeld(Bezeichner.GESAMTBEITRAG_BRUTTO, 40, "00000000004711", 2);
        AlphaNumFeld vorzeichen = new Zeichen(Bezeichner.VORZEICHEN, 55, '+');
        BetragMitVorzeichen.of(betrag, vorzeichen);
    }

    @Test
    public void testSetInhalt() {
        betrag.setInhalt("123+");
        assertEquals("0123+", betrag.getInhalt());
    }

    @Test
    public void testSetInhaltOhneVorzeichen() {
        betrag.setInhalt("123");
        assertEquals("0123+", betrag.getInhalt());
    }

    @Test
    public void testCtor() {
        BetragMitVorzeichen betragMitVZ= new BetragMitVorzeichen(Bezeichner.of("betragTestVZ"), 10, 2);
        assertEquals('+', betragMitVZ.getVorzeichen());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInhaltNull() {
        BetragMitVorzeichen betragMitVz = new BetragMitVorzeichen(Bezeichner.of("betragTestVZ"), 10, 2).mitConfig(Config.STRICT);
        String wert = null;
        betragMitVz.setInhalt(wert);
    }

}
