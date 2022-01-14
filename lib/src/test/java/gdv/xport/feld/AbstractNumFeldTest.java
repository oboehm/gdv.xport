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

import gdv.xport.config.Config;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * In dieser Klasse sind Tests zusammengefasst, die fuer NumFeld und
 * abgeleiteten Klassen (wie Betrag) gemeinsam sind und gleiche
 * Ergebnisse liefern sollen.
 *
 * @author oliver
 */
public abstract class AbstractNumFeldTest extends AbstractFeldTest {

    private static final Config TRUNCATE = Config.getInstance()
            .withProperty("gdv.feld.truncate", "true")
            .withProperty("gdv.feld.validate", "true");
    private NumFeld testBetrag;

    /* (non-Javadoc)
     * @see gdv.xport.feld.AbstractFeldTest#getTestFeld()
     */
    @Override
    protected NumFeld getTestFeld() {
        return getTestBetrag();
    }

    /**
     * Diese Methode solle ein NumFeld mit 2 Nachkommastellen zurueckliefern,
     * so wie es auch bei der Betrag-Klasse der Fall ist.
     *
     * @return ein NumFeld mit 2 Nachkommastellen
     */
    protected abstract NumFeld getTestBetrag();

    /**
     * Hier werden die Vorbedingungen ueberprueft, auf die die Tests basieren.
     */
    @Before
    public void testPreConditions() {
        testBetrag = getTestBetrag();
        assertEquals(2, testBetrag.getNachkommastellen());
        assertEquals(5, testBetrag.getAnzahlBytes());
    }

    /**
     * Einfacher Test, ob das Anlegen erfolgreich war.
     */
    @Test
    public void testInitialWert() {
        assertEquals("00000", testBetrag.getInhalt());
    }

    /**
     * Dieser Test wurde aus testBigDecimalVergleich extrahiert.
     */
    @Test
    public void testSetBigDecimalGerundet() {
        testBetrag.setInhalt(new BigDecimal("0.146"));
        assertEquals("00015", testBetrag.getInhalt());
        assertEquals("0.15", testBetrag.toBigDecimal().toString());
        assertEquals(0.15, testBetrag.toDouble(), 0.0001);
        assertEquals(0, testBetrag.toInt());
        assertEquals(0, testBetrag.toLong());
    }

    /**
     * Test-Methode fuer {@link NumFeld#setInhalt(double)} und davon
     * abgeleitete Klassen.
     */
    @Test
    public void testSetInhaltDouble() {
        testBetrag.setInhalt(1.23);
        assertEquals("00123", testBetrag.getInhalt());
        assertEquals(1.23, testBetrag.toDouble(), 0.001);
    }

    /**
     * Dieser Test wurde aus testLongVergleich extrahiert.
     */
    @Test
    public void testSetInhaltLong() {
        testBetrag.setInhalt(33L);
        assertEquals("03300", testBetrag.getInhalt());
        assertEquals("33.00", testBetrag.toBigDecimal().toString());
        assertEquals(33.0, testBetrag.toDouble(), 0.0001);
        assertEquals(33, testBetrag.toInt());
        assertEquals(33, testBetrag.toLong());
    }

    /**
     /**
     * Test-Methode fuer {@link NumFeld#setInhalt(int)} und davon abgeleteite
     * Klassen.
     * Dieser Test wurde aus testIntVergleich extrahiert und mit den Tests
     * aus NumFeldTest und BetragTest abgeglichen.
     */
    @Test
    public void testSetInhaltInt() {
        testBetrag.setInhalt(22);
        assertEquals("02200", testBetrag.getInhalt());
        assertEquals("22.00", testBetrag.toBigDecimal().toString());
        assertEquals("22", testBetrag.toBigInteger().toString());
        assertEquals(22.0, testBetrag.toDouble(), 0.0);
        assertEquals(22, testBetrag.toInt());
        assertEquals(22, testBetrag.toLong());
    }

    @Test
    public void testSetInhaltBigInteger() {
        testBetrag.setInhalt(BigInteger.ONE);
        assertEquals("00100", testBetrag.getInhalt());
        assertEquals(BigInteger.ONE, testBetrag.toBigInteger());
    }

    /**
     * Dieser Test wurde aus testIntVergleich extrahiert.
     */
    @Test
    public void testSetInhaltString() {
        testBetrag.setInhalt("33");
        assertEquals("00033", testBetrag.getInhalt());
        assertEquals("0.33", testBetrag.toBigDecimal().toString());
        assertEquals(BigInteger.ZERO, testBetrag.toBigInteger());
        assertEquals(0.33, testBetrag.toDouble(), 0.0);
        assertEquals(0, testBetrag.toInt());
        assertEquals(0, testBetrag.toLong());
    }

    @Test
    public void testSetInhaltChar() {
        testBetrag.setInhalt('1');
        assertEquals(1, testBetrag.toInt());
        assertEquals("00100", testBetrag.getInhalt());
    }

    @Test
    public void testToBigDecimal() {
        testBetrag.setInhalt("12345");
        assertEquals(new BigDecimal("123.45"), testBetrag.toBigDecimal());
    }

    /**
     * Ein Betrag sollte als entsprechender Text formattiert werden.
     *
     * @since 0.5.1
     */
    @Test
    public void testFormatDecimal() {
        testBetrag.setInhalt("120");
        if ("DE".equals(Locale.getDefault().getCountry())) {
            assertEquals("1,20", testBetrag.format());
        }
    }

    @Test
    public void testTruncate() {
        NumFeld betrag = testBetrag.mitConfig(TRUNCATE);
        betrag.setInhalt(12);
        assertEquals("01200", betrag.getInhalt());
        betrag.setInhalt(123);
        assertEquals("12300", betrag.getInhalt());
        betrag.setInhalt(1234);
        assertEquals("99999", betrag.getInhalt());
    }

    @Test
    public void testTruncate000321() {
        NumFeld feld = testBetrag.mitConfig(TRUNCATE);
        feld.setInhalt("000321");
        assertEquals("00321", feld.getInhalt());
    }

}
