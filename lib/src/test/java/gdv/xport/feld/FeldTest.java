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

import gdv.xport.annotation.FeldInfo;
import gdv.xport.satz.feld.common.Feld1bis7;
import gdv.xport.satz.feld.sparte50.Feld210;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import patterntesting.runtime.junit.CloneableTester;

import java.lang.annotation.Annotation;
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
    private enum Greeting { HELLO_WORLD, ADRESSAT; }

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

    /**
     * Testet, ob ein Feld nur mit einem Enum angelegt werden kann. Als
     * Enum wird dabei die Satzart aus {@link Feld1bis7} verwendet.
     */
    @Test
    public void testFeldWithEnum() {
        Feld satzart = new Feld(Feld1bis7.SATZART);
        assertTrue("expected: " + satzart + " is valid", satzart.isValid());
        assertEquals(1, satzart.getByteAdresse());
        assertEquals(4, satzart.getAnzahlBytes());
    }

    /**
     * Test overlaps with.
     */
    @Test
    public void testOverlapsWith() {
        Feld a = new Feld(new Bezeichner("a"), 2, 1, Align.LEFT);    // Byte 1-2
        Feld b = new Feld(new Bezeichner("b"), 2, 3, Align.LEFT);    // Byte 3-4
        Feld c = new Feld(new Bezeichner("c"), 2, 2, Align.LEFT);    // Byte 2-3
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
        Feld a = new Feld(new Bezeichner("a"), 257, -1, Align.UNKNOWN);
        assertFalse(a + " is not valid - to long, wrong start byte, unknow alignment", a.isValid());
        Feld b = new Feld(new Bezeichner("b"), 2, 256, Align.LEFT);
        assertFalse(b + " geht ueber Satz-Grenze hinweg", b.isValid());
        Feld c = new Feld("c", 1, 'c');
        assertTrue(c + " should be valid", c.isValid());
    }

    /**
     * Test validate.
     */
    @Test
    public void testValidate() {
        Feld a = new Feld(new Bezeichner("a"), 10, -1, Align.UNKNOWN);
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
        Feld a = new Feld(new Bezeichner("x"), 2, 1, Align.LEFT);
        Feld b = new Feld(new Bezeichner("x"), 2, 1, Align.LEFT);
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
        Feld x = new Feld(new Bezeichner("Version Satzart 0100"), 99, 3, Align.LEFT);
        assertEquals(new Bezeichner("VERSION_SATZART_0100"), x.getBezeichner());
    }

    /**
     * Test-Methode fuer {@link Feld#getAsBezeichner(Enum)}.
     */
    @Test
    public void testGetAsBezeichner() {
        Bezeichner adressat = Feld.getAsBezeichner(Greeting.ADRESSAT);
        assertEquals(Bezeichner.ADRESSAT, adressat);
    }

    /**
     * Wenn eine Enum-Konstante bereits als Bezeichner zur Verfuegung steht,
     * soll dieses zurueckgegeben.
     */
    @Test
    public void testGetAsBezeichnerWechselkennzeichen() {
        Bezeichner b = Feld.getAsBezeichner(Feld210.WECHSELKENNZEICHEN_W_AKZ);
        assertEquals(Bezeichner.WECHSELKENNZEICHEN_W_AKZ, b);
    }

    /**
     * Hier wollen wir testen, ob aus dem Bezeichner "HELLO_WORLD" als
     * Bezeichnung tatsaechlich "Hello World" rauskommt.
     */
    @Test
    public void testCreateFeld() {
        FeldInfo feldInfo = createFeldInfo();
        Greeting hello = Greeting.HELLO_WORLD;
        Feld feld = Feld.createFeld(hello, feldInfo);
        assertEquals(new Bezeichner("HELLO_WORLD"), feld.getBezeichner());
        assertEquals("Hello World", feld.getBezeichnung());
    }

    /**
     * Hier wird eine vorbelegte FeldInfo-Annotation zum Testen erzeugt.
     *
     * @return FeldInfo mit einigen Default-Werten
     */
    static FeldInfo createFeldInfo() {
        return new FeldInfo() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }
            @Override
            public Class<? extends Feld> type() {
                return Zeichen.class;
            }
            @Override
            public int teildatensatz() {
                return 1;
            }
            @Override
            public int nr() {
                return 11;
            }
            @Override
            public int nachkommaStellen() {
                return 0;
            }
            @Override
            public String erlaeuterung() {
                return "only for testing";
            }
            @Override
            public int byteAdresse() {
                return 100;
            }
            @Override
            public int anzahlBytes() {
                return 1;
            }
            @Override
            public Align align() {
                return Align.UNKNOWN;
            }
            @Override
            public String bezeichnung() {
                return "";
            }
            @Override
            public String value() {
                return "";
            }
        };
    }

    /**
     * Test-Methode fuer {@link Feld#getInhalt()} in Zusammenhang mit dem
     * Encoding.
     */
    @Test
    public void testEncoding() {
        Feld feld = new AlphaNumFeld("Gruesse", "Gr\u00fc\u00dfe");
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

}
