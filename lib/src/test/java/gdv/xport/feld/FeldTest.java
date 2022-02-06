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
import gdv.xport.satz.feld.common.Kopffelder1bis7;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import patterntesting.runtime.junit.CloneableTester;
import patterntesting.runtime.junit.SerializableTester;

import javax.validation.ValidationException;
import java.io.NotSerializableException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit-Test fuer die Feld-Klasse.
 *
 * @author oliver
 * @since 05.10.2009
 */
public final class FeldTest extends AbstractFeldTest {

    private static final Logger LOG = LogManager.getLogger(FeldTest.class);
    private enum Greeting { HELLO_WORLD, ADRESSAT }

    /* (non-Javadoc)
     * @see gdv.xport.feld.AbstractFeldTest#getTestFeld()
     */
    @Override
    protected Feld getTestFeld() {
        return new Feld();
    }

    /**
     * Test method for {@link gdv.xport.feld.Feld#resetInhalt()}.
     */
    @Test
    public void testResetInhalt() {
        Feld feld = new Feld("testfeld", "abc", Align.LEFT);
        feld.resetInhalt();
        assertEquals("   ", feld.getInhalt());
    }

    /**
     * Test set inhalt.
     */
    @Test
    public void testSetInhalt() {
        Feld linksbuendig = new Feld("f1", "hello", Align.LEFT);
        linksbuendig.setInhalt("abc");
        assertEquals("abc  ", linksbuendig.getInhalt());
        Feld rechtsbuendig = new Feld("f2", "world", Align.RIGHT);
        rechtsbuendig.setInhalt("hi");
        assertEquals("   hi", rechtsbuendig.getInhalt());
    }

    /**
     * Test feld.
     */
    @Test
    public void testFeld() {
        Feld zeichen = new Feld("Testfeld", 1, 1, 'x', Align.LEFT);
        assertEquals("x", zeichen.getInhalt());
    }

    @Test
    public void testKopffelder() {
        Feld satzart = new Feld(Kopffelder1bis7.SATZART);
        assertTrue("expected: " + satzart + " is valid", satzart.isValid());
        assertEquals(1, satzart.getByteAdresse());
        assertEquals(4, satzart.getAnzahlBytes());
    }

    /**
     * Test overlaps with.
     */
    @Test
    public void testOverlapsWith() {
        Feld a = new Feld(Bezeichner.of("a"), 2, 1, Align.LEFT);    // Byte 1-2
        Feld b = new Feld(Bezeichner.of("b"), 2, 3, Align.LEFT);    // Byte 3-4
        Feld c = new Feld(Bezeichner.of("c"), 2, 2, Align.LEFT);    // Byte 2-3
        assertFalse(a + " overlaps with " + b, a.overlapsWith(b));
        assertFalse(b + " overlaps with " + a, b.overlapsWith(a));
        assertTrue(b + " doesn't overlap with " + c, b.overlapsWith(c));
        assertTrue(c + " doesn't overlap with " + a, c.overlapsWith(a));
        assertTrue(c + " doesn't overlap with " + b, c.overlapsWith(b));
    }

    /**
     * Test is valid.
     */
    @Test
    public void testIsValid() {
        Feld b = new Feld(Bezeichner.of("b"), 2, 256, Align.LEFT);
        assertFalse(b + " geht ueber Satz-Grenze hinweg", b.isValid());
        Feld c = new Feld("c", 1, 'c');
        assertTrue(c + " should be valid", c.isValid());
    }

    /**
     * Test validate.
     */
    @Test
    public void testValidate() {
        Feld a = new Feld(Bezeichner.of("a"), 10, 250, Align.UNKNOWN);
        List<ConstraintViolation> violations = a.validate();
        for (ConstraintViolation violation : violations) {
            LOG.info("ConstraintViolation: " + violation);
        }
        assertEquals(2, violations.size());
    }

    /**
     * Test equals.
     */
    @Test
    public void testEquals() {
        Feld a = new Feld(Bezeichner.of("x"), 2, 1, Align.LEFT);
        Feld b = new Feld(Bezeichner.of("x"), 2, 1, Align.LEFT);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        b.setInhalt('b');
        assertNotEquals(a + " differs from " + b, a, b);
    }

    /**
     * Bezeichnung kann aus mehreren Woertern in Gross- und Kleinschreibung
     * bestehen, der Bezeichner enthaelt neben der Bezeichnung auch den
     * "technischen Namen", der aus einem Wort besteht.
     */
    @Test
    public void testGetBezeichner() {
        Feld x = new Feld(Bezeichner.SATZART.getName(), "Test", Align.LEFT);
        assertEquals(Bezeichner.SATZART.getName(), x.getBezeichnung());
        assertEquals(Bezeichner.SATZART, x.getBezeichner());
    }

    /**
     * Bezeichner, die nicht als Bezeichner-Konstante gefunden werden,
     * sollen nach den Regeln fuer Java-Konstanten zurueckgegeben werden.
     */
    @Test
    public void testGetBezeichnerConstructed() {
        Feld x = new Feld(Bezeichner.of("Version Satzart 0100"), 99, 3, Align.LEFT);
        assertEquals(Bezeichner.of("VERSION_SATZART_0100"), x.getBezeichner());
    }

    /**
     * Test-Methode fuer {@link Feld#getInhalt()} in Zusammenhang mit dem
     * Encoding.
     */
    @Test
    public void testEncoding() {
        Feld feld = new AlphaNumFeld(Bezeichner.of("Gruesse"), 5, 1);
        feld.setInhalt("Gr\u00fc\u00dfe");
        assertEquals("Gr\u00fc\u00dfe", feld.getInhalt());
    }

    /**
     * Test-Methode fuer {@link Feld#clone()}:
     */
    @Override
    @Test
    public void testCloneable() {
        CloneableTester.assertCloning(Feld.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverflow() {
        Feld feld = new Feld(Bezeichner.NAME1, 5, 1, Align.LEFT);
        feld.setInhalt("hello world");
    }

    @Test
    public void testTruncateLeft() {
        Feld feld = new Feld(Bezeichner.NAME1, 5, 1, Align.LEFT).mitConfig(Config.EXPERIMENTAL);
        feld.setInhalt("hello world");
        assertEquals("hello", feld.getInhalt());
    }

    @Test
    public void testTruncateRight() {
        Feld feld = new Feld(Bezeichner.NAME1, 6, 1, Align.RIGHT).mitConfig(Config.EXPERIMENTAL);
        feld.setInhalt("hello world");
        assertEquals("hello ", feld.getInhalt());
    }

    @Test
    public void testNoTruncate() {
        Feld feld = new Feld(Bezeichner.NAME1, 5, 1, Align.RIGHT).mitConfig(Config.EXPERIMENTAL);
        feld.setInhalt("hi");
        assertEquals("hi", feld.getInhalt().trim());
    }

    @Test
    public void testValidator() {
        Feld.Validator validator = new Feld.Validator();
        String ok = "Paragraph §218 ok?";
        assertEquals(ok, validator.validate(ok));
    }

    @Test
    public void testValidatorMitUmlaute() {
        Feld.Validator validator = new Feld.Validator();
        String text = "Gr\u00fc\u00dfe";
        assertEquals(text, validator.validate(text));
    }

    @Test(expected = ValidationException.class)
    public void testValidatorInvalidChars() {
        Feld.Validator validator = new Feld.Validator();
        byte[] bytes = { 'a', 'b', 1, -127 };
        String invalid = new String(bytes, StandardCharsets.US_ASCII);
        validator.validate(invalid);
    }

    @Test
    public void testBigDecimal() {
        Feld provision = new NumFeld(Bezeichner.of("GesprovisionsBetrag"), 14, 55).mitNachkommastellen(2);
        provision.setInhalt(new BigDecimal("12345678.90"));
        assertEquals("00001234567890", provision.getInhalt());
        Feld pi = new Feld(Bezeichner.of("pi"), 4, 1, Align.LEFT);
        pi.setInhalt(new BigDecimal("3.14"));
        assertEquals("3.14", pi.getInhalt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFeldNull() {
        Feld feld1 = new Feld(Bezeichner.of("test"), 5, 4, Align.LEFT).mitConfig(Config.EMPTY);
        String leer = null;
        feld1.setInhalt(leer);
    }

    @Test
    public void testSerializable() throws NotSerializableException {
        Feld feld = new Feld(Bezeichner.of("test"), 10, 1, Align.LEFT);
        SerializableTester.assertSerialization(feld);
    }

}
