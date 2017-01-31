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

import java.util.Locale;

import org.junit.Test;

import gdv.xport.satz.feld.Feld9999;

/**
 * JUnit-Test fuer die Betrag-Klasse.
 *
 * @author oliver
 * @since 11.10.2009
 * @version $Revision$
 */
public final class BetragTest extends AbstractFeldTest {

    private final Betrag betrag = new Betrag(new Bezeichner("test"), 5, 1);

    /* (non-Javadoc)
     * @see gdv.xport.feld.AbstractFeldTest#getTestFeld()
     */
    @Override
    protected Feld getTestFeld() {
        return this.betrag;
    }

    /**
     * Test-Methode fuer {@link Betrag#toDouble()}.
     */
    @Test
    public void testBetrag() {
        assertEquals("00000", betrag.getInhalt());
        assertEquals(0.0, betrag.toDouble(), 0.001);
    }

    /**
     * Test-Methode fuer {@link Betrag#Betrag(Enum)}.
     */
    @Test
    public void testBetragEnum() {
        Betrag b = new Betrag(Feld9999.GESAMTBEITRAG);
        assertEquals((Bezeichner.GESAMTBEITRAG), b.getBezeichner());
    }

    /**
     * Test-Methode fuer {@link Betrag#setInhalt(int)}.
     */
    @Test
    public void testSetInhaltInt() {
        betrag.setInhalt(50);
        assertEquals("05000", betrag.getInhalt());
        assertEquals(50, betrag.toInt());
    }

    /**
     * Test-Methode fuer {@link Betrag#setInhalt(double)}.
     */
    @Test
    public void testSetInhaltDouble() {
        betrag.setInhalt(1.23);
        assertEquals("00123", betrag.getInhalt());
        assertEquals(1.23, betrag.toDouble(), 0.001);
    }

    /**
     * Ein Betrag sollte als entsprechender Text formattiert werden.
     * @since 0.5.1
     */
    @Test
    public void testFormat() {
        betrag.setInhalt(1.23);
        if ("DE".equals(Locale.getDefault().getCountry())) {
            assertEquals("1,23", betrag.format());
        }
    }

}

