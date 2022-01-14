/*
 * Copyright (c) 2022 by Oli B.
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
 * (c)reated 13.01.22 by oboehm
 */
package gdv.xport.feld;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * In dieser Klasse sind Tests zusammengefasst, die fuer NumFeld und
 * abgeleiteten Klassen (wie Betrag) gemeinsam sind und gleiche
 * Ergebnisse liefern sollen.
 *
 * @author oliver
 */
public abstract class AbstractNumFeldTest extends AbstractFeldTest {

    /* (non-Javadoc)
     * @see gdv.xport.feld.AbstractFeldTest#getTestFeld()
     */
    @Override
    protected NumFeld getTestFeld() {
        return getTestBetrag();
    }

    /**
     * Hier werden die Vorbedingungen ueberprueft, auf die die Tests basieren.
     */
    @Before
    public void testPreConditions() {
        NumFeld betrag = getTestBetrag();
        assertEquals(2, betrag.getNachkommastellen());
        assertEquals(5, betrag.getAnzahlBytes());
    }

    /**
     * Diese Methode solle ein NumFeld mit 2 Nachkommastellen zurueckliefern,
     * so wie es auch bei der Betrag-Klasse der Fall ist.
     *
     * @return ein NumFeld mit 2 Nachkommastellen
     */
    protected abstract NumFeld getTestBetrag();

    /**
     * Dieser Test wurde aus testBigDecimalVergleich extrahiert.
     */
    @Test
    public void testBigDecimalRundung() {
        NumFeld betrag = getTestBetrag();
        betrag.setInhalt(new BigDecimal("0.146"));
        assertEquals("00015", betrag.getInhalt());
        assertEquals("0.15", betrag.toBigDecimal().toString());
        assertEquals(0.15, betrag.toDouble(), 0.0);
        assertEquals(0, betrag.toInt());
        assertEquals(0, betrag.toLong());
    }

    /**
     * Dieser Test wurde aus testLongVergleich extrahiert.
     */
    @Test
    public void testSetInhaltLong() {
        NumFeld betrag = getTestBetrag();
        betrag.setInhalt(33L);
        assertEquals("03300", betrag.getInhalt());
        assertEquals("33.00", betrag.toBigDecimal().toString());
        assertEquals(33.0, betrag.toDouble(), 0.0001);
        assertEquals(33, betrag.toInt());
        assertEquals(33, betrag.toLong());
    }

    /**
     * Dieser Test wurde aus testIntVergleich extrahiert.
     */
    @Test
    public void testSetInhaltInt() {
        NumFeld numFeldTest = getTestBetrag();
        numFeldTest.setInhalt(22);
        assertEquals("02200", numFeldTest.getInhalt());
        assertEquals("22.00", numFeldTest.toBigDecimal().toString());
        assertEquals("22", numFeldTest.toBigInteger().toString());
        assertEquals(22.0, numFeldTest.toDouble(), 0.0);
        assertEquals(22, numFeldTest.toInt());
        assertEquals(22, numFeldTest.toLong());
    }

    /**
     * Dieser Test wurde aus testIntVergleich extrahiert.
     */
    @Test
    public void testSetInhaltString() {
        NumFeld numFeldTest = getTestBetrag();
        numFeldTest.setInhalt("33");
        assertEquals("00033", numFeldTest.getInhalt());
        assertEquals("0.33", numFeldTest.toBigDecimal().toString());
        assertEquals(BigInteger.ZERO, numFeldTest.toBigInteger());
        assertEquals(0.33, numFeldTest.toDouble(), 0.0);
        assertEquals(0, numFeldTest.toInt());
        assertEquals(0, numFeldTest.toLong());
    }

}
