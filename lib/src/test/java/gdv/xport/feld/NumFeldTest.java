/*
 * $Id$
 *
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
 * (c)reated 05.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import gdv.xport.satz.feld.common.Feld1bis7;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Test-Klasse fuer NumFeld.
 *
 * @author oliver
 * @since 05.10.2009
 */
public final class NumFeldTest extends AbstractFeldTest {

    private static final Logger LOG = LogManager.getLogger(NumFeldTest.class);

    /* (non-Javadoc)
     * @see gdv.xport.feld.AbstractFeldTest#getTestFeld()
     */
    @Override
    protected Feld getTestFeld() {
        return new NumFeld(Feld1bis7.SPARTE);
    }

    /**
     * Einfacher Test, ob das Anlegen erfolgreich war.
     */
    @Test
    public void testNumFeld() {
        NumFeld nummer = new NumFeld(new Bezeichner("Feld X"), 4, 1);
        assertEquals("0000", nummer.getInhalt());
    }

    /**
     * Hier testen wir, ob eine negative Zahl richtig umgewandelt wird.
     */
    @Test
    public void testNumFeldNegativ() {
        NumFeld einsminus = new NumFeld("einsminus", "-001");
        assertEquals(-1, einsminus.toInt());
    }

    /**
     * Wir sollten keine {@link NumberFormatException} bekommen, wenn wir
     * den Konstruktor mit Enum verwenden.
     */
    @Test
    public void testNumFeldWithEnum() {
        NumFeld sparte = new NumFeld(Feld1bis7.SPARTE);
        assertEquals(0, sparte.toInt());
    }

    /**
     * Test method for {@link gdv.xport.feld.NumFeld#setInhalt(int)}.
     */
    @Test
    public void testSetInhaltInt() {
        NumFeld nummer = new NumFeld("Feld X", "0001");
        nummer.setInhalt(2);
        assertEquals("0002", nummer.getInhalt());
    }

    /**
     * Hier pruefen wir mit einem ungueltigen Inhalt.
     */
    @Test
    public void testIsInvalid() {
        NumFeld x = new NumFeld("x", "xxxx");
        assertFalse(x + " is invalid", x.isValid());
    }

    /**
     * Und hier pruefen wir mit einem gueltigen Inhalt.
     */
    @Test
    public void testIsValid() {
        NumFeld three = new NumFeld("testIsValid", "3     ");
        assertTrue("should be valid", three.isValid());
    }

    /**
     * Auch wenn es nicht vorgesehen ist, sollten (ungefragte) Vorzeichen
     * keine Probleme bereiten.
     * @since 0.4
     */
    @Test
    public void testIsValidMitVorzeichen() {
        NumFeld einsplus = new NumFeld("einsplus", "+001");
        assertTrue("auch Vorzeichen koennen vorkommen", einsplus.isValid());
        assertEquals(1, einsplus.toInt());
    }

    /**
     * Hier pruefen wir mit einem ungueltigen Inhalt.
     */
    @Test
    public void testInvalidate() {
        NumFeld x = new NumFeld("x", "xxxx");
        List<ConstraintViolation> violations = x.validate();
        for (ConstraintViolation violation : violations) {
            LOG.info(violation.getValidatedObject() + ": " + violation.getMessage());
        }
        assertEquals(1, violations.size());
    }

    /**
     * Und hier pruefen wir mit einem gueltigen Inhalt.
     */
    @Test
    public void testValidate() {
        NumFeld three = new NumFeld("testValidate", "3     ");
        assertEquals(0, three.validate().size());
    }

    /**
     * Eine Zahl mit Nachkommastellen sollte auch als Double ausgegeben werden
     * koennen. Hier probieren wir es noch mit 0 Nachkommastellen.
     *
     * @since 0.4
     */
    @Test
    public void testIntAsDouble() {
        NumFeld x = new NumFeld("x", "1");
        assertEquals(1.0, x.toDouble(), 0.01);
    }

    /**
     * Und hier testen wir eine Zahl mit 2 Nachkommastellen, ob sie korrekt
     * umgewandelt wird.
     *
     * @since 0.4
     */
    @Test
    public void testToDouble() {
        NumFeld pi = new NumFeld("pi", "314", 2);
        assertEquals(3.14, pi.toDouble(), 0.001);
    }

    /**
     * Waehrend des Testen gab es 'NumFeld KH-Deckungssummen in
     * Waehrungseinheiten Teil 1(43-56): "00005000000000" is invalid:
     * not a number (java.lang.NumberFormatException: For input string:
     * "00005000000000")' als Fehlermeldung. Dieser Testfall versucht
     * dies zu rekonstruieren.
     */
    @Test
    public void testBigNumber() {
        NumFeld big = new NumFeld(new Bezeichner("big"), 14, 1).mitNachkommastellen(2);
        big.setInhalt("00005000000000");
        assertTrue("should be valid", big.isValid());
        List<ConstraintViolation> violations = big.validate();
        assertTrue(violations + " should be empty", violations.isEmpty());
    }

    /**
     * Ein Betrag sollte als entsprechender Text formattiert werden.
     * @since 0.5.1
     */
    @Test
    public void testFormatInt() {
        NumFeld betrag = new NumFeld(new Bezeichner("betrag"), 5, 1);
        betrag.setInhalt("120");
        assertEquals("120", betrag.format());
    }

    /**
     * Ein Betrag sollte als entsprechender Text formattiert werden.
     * @since 0.5.1
     */
    @Test
    public void testFormatDouble() {
        NumFeld betrag = new NumFeld(new Bezeichner("betrag"), 5, 1).mitNachkommastellen(2);
        betrag.setInhalt("120");
        if ("DE".equals(Locale.getDefault().getCountry())) {
            assertEquals("1,20", betrag.format());
        }
    }

    @Test
    public void testToDoubleTooLong() {
        NumFeld feld = new NumFeld("name", "123456789012");
        double value = feld.toDouble();
        assertEquals(123456789012.0, value, 0.0);
    }

}
