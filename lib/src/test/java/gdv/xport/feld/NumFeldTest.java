/*
 * Copyright (c) 2009 - 2019 by Oli B.
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

import gdv.xport.config.Config;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test-Klasse fuer NumFeld.
 *
 * @author oliver
 * @since 05.10.2009
 */
public class NumFeldTest extends AbstractNumFeldTest {

    private static final Logger LOG = LogManager.getLogger(NumFeldTest.class);
    private final NumFeld nummer = new NumFeld(Bezeichner.LFD_NUMMER, 5, ByteAdresse.of(1));

    @Override
    protected NumFeld getTestBetrag() {
        return nummer.mitNachkommastellen(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInhaltNegative() {
        NumFeld positiv = nummer.mitConfig(Config.STRICT);
        positiv.setInhalt(-1);
    }

    /**
     * Hier pruefen wir mit einem ungueltigen Inhalt.
     */
    @Test
    public void testIsInvalid() {
        nummer.setInhalt("xxxx");
        assertFalse(nummer + " is invalid", nummer.isValid());
    }

    /**
     * Und hier pruefen wir mit einem gueltigen Inhalt.
     */
    @Test
    public void testIsValid() {
        nummer.setInhalt("3    ");
        assertTrue("should be valid", nummer.isValid());
    }

    /**
     * Auch wenn es nicht vorgesehen ist, sollten (ungefragte) Vorzeichen
     * keine Probleme bereiten.
     * @since 0.4
     */
    @Test
    public void testIsValidMitVorzeichen() {
        NumFeld einsplus = new NumFeld(Bezeichner.of("einsplus"), ByteAdresse.of(1), "+001", 0);
        assertTrue("auch Vorzeichen koennen vorkommen", einsplus.isValid());
        assertEquals(1, einsplus.toInt());
    }

    /**
     * Hier pruefen wir mit einem ungueltigen Inhalt.
     */
    @Test
    public void testInvalidate() {
        nummer.setInhalt("xxxx");
        List<ConstraintViolation> violations = nummer.validate();
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
        nummer.setInhalt("3    ");
        assertEquals(0, nummer.validate().size());
    }

    /**
     * Eine Zahl mit Nachkommastellen sollte auch als Double ausgegeben werden
     * koennen. Hier probieren wir es noch mit 0 Nachkommastellen.
     *
     * @since 0.4
     */
    @Test
    public void testIntAsDouble() {
        nummer.setInhalt("1");
        assertEquals(1.0, nummer.toDouble(), 0.01);
    }

    /**
     * Und hier testen wir eine Zahl mit 2 Nachkommastellen, ob sie korrekt
     * umgewandelt wird.
     *
     * @since 0.4
     */
    @Test
    public void testToDouble() {
        NumFeld pi = new NumFeld(Bezeichner.of("pi"), ByteAdresse.of(1), "314", 2);
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
        NumFeld big = new NumFeld(Bezeichner.of("big"), 14, ByteAdresse.of(1)).mitNachkommastellen(2);
        big.setInhalt("00005000000000");
        assertTrue("should be valid", big.isValid());
        List<ConstraintViolation> violations = big.validate();
        assertTrue(violations + " should be empty", violations.isEmpty());
    }

    @Test
    public void testBigDecimal() {
        NumFeld big = new NumFeld(Bezeichner.of("big"), 14, ByteAdresse.of(1));
        String zahl = "12345000000000";
        big.setInhalt(new BigInteger(zahl));
        assertEquals(zahl, big.getInhalt());
        assertEquals(new BigInteger(zahl), big.toBigInteger());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBigDecimalMinus() {
        NumFeld big = new NumFeld(Bezeichner.of("big"), 14, ByteAdresse.of(1)).mitConfig(Config.LAX);
        big.setInhalt(new BigInteger("-1"));
    }

    /**
     * Ein Betrag sollte als entsprechender Text formattiert werden.
     * @since 0.5.1
     */
    @Test
    public void testFormatInt() {
        nummer.setInhalt("120");
        assertEquals("120", nummer.format());
    }

    @Test
    public void testAdd() {
        NumFeld feld = new NumFeld(Bezeichner.of("fuffzig"), ByteAdresse.of(1), "5000", 2);
        BigDecimal summe = feld.add(BigDecimal.TEN);
        BigDecimal expected = new BigDecimal("60.00");
        assertEquals(expected, summe);
        assertEquals(expected, feld.toBigDecimal());
    }

    /**
     * Beim Umstieg auf die aktuelle XML-Version von 2018 fiel auf, dass beim
     * Setzen der Nachkommastellen sich der Bezeichner in manchen Situationen
     * aenderte. Mit diesem Test wurde das Verhalten nachgestellt und behoben.
     */
    @Test
    public void testMitNachkommastellen() {
        Bezeichner bezeichner = new Bezeichner("Satznummer", "SatzNr1");
        NumFeld satznr = new NumFeld(bezeichner, 1, ByteAdresse.of(43), 0);
        NumFeld satznrOhneNachkommastalle = satznr.mitNachkommastellen(0);
        assertEquals(satznr, satznrOhneNachkommastalle);
        assertEquals(bezeichner, satznrOhneNachkommastalle.getBezeichner());
    }

    /**
     * In der XML-Beschreibung zum Satzart "0221.051" sind 3 Deckungssummen
     * zu einem NumFeld der Laenge 42 zusammengefasst. Das fuehrt bei der
     * Validierung zu Problemen, die hierueber nachgestellt werden.
     */
    @Test
    public void testKhDeckungssummenInWE() {
        NumFeld summen = new NumFeld(Bezeichner.of("KhDeckungssummenInWE"), 42, ByteAdresse.of(43));
        summen.setInhalt("000050000000000000000000000000000000000000");
        assertTrue(summen.isValid());
    }

    @Test
    public void testHasValue() {
        NumFeld n = new NumFeld(Bezeichner.of("Test"), 1, ByteAdresse.of(1));
        n.setInhalt(1);
        assertTrue(n.hasValue());
    }

    @Test
    public void testHasValueEmpty() {
        NumFeld n = new NumFeld(Bezeichner.of("Test"), 8, ByteAdresse.of(1));
        n.resetInhalt();
        assertFalse(n.hasValue());
        assertEquals("00000000", n.getInhalt());
    }

    @Test
    public void testHasValueXXXXXXXX() {
        NumFeld n = new NumFeld(Bezeichner.of("Test-Datum"), 8, ByteAdresse.of(1));
        n.setInhalt("xxxxxxxx");
        assertFalse(n.hasValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInhaltR2D2() {
        NumFeld one = new NumFeld(Bezeichner.ANTEILE, 5, ByteAdresse.of(1)).mitConfig(Config.LAX);
        one.setInhalt("R2 D2");
    }

    @Test
    public void testValidator() {
        NumFeld.Validator validator = new NumFeld.Validator();
        String theAnswer = "42";
        assertEquals(theAnswer, validator.validate(theAnswer));
    }

    @Test(expected = ValidationException.class)
    public void testValidatorFails() {
        NumFeld.Validator validator = new NumFeld.Validator();
        validator.validate("no");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidatorVerifyFails() {
        NumFeld.Validator validator = new NumFeld.Validator();
        validator.verify("not valid");
    }

    @Test(expected = ValidationException.class)
    public void testValidatorNegativeNumber() {
        NumFeld.Validator validator = new NumFeld.Validator();
        validator.validate("-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNumberWithBlanksConfigStrict() {
        NumFeld numFeld = new NumFeld(Bezeichner.of("numTestFeld"), 5, ByteAdresse.of(1)).mitConfig(Config.STRICT);
        numFeld.setInhalt(" 1 ");
    }

}
