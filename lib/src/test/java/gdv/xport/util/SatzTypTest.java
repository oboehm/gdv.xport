/*
 * Copyright (c) 2013 by Oli B.
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
 * (c)reated 26.01.2013 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.satz.feld.common.WagnisartLeben;
import org.junit.Test;
import patterntesting.runtime.junit.ObjectTester;

import static org.junit.Assert.*;

/**
 * JUnit-Tests fuer {@link SatzTyp}.
 *
 * @author oliver
 * @since 0.9 (26.01.2013)
 */
public class SatzTypTest {

    /**
     * Test method for {@link SatzTyp#equals(Object)}.
     */
    @Test
    public void testEqualsObject() {
        SatzTyp one = new SatzTyp(1);
        SatzTyp anotherOne = new SatzTyp(1);
        ObjectTester.assertEquals(one, anotherOne);
    }

    /**
     * Test method for {@link SatzTyp#equals(Object)}.
     */
    @Test
    public void testNotEquals() {
        SatzTyp one = SatzTyp.of(1, 1);
        SatzTyp other = SatzTyp.of(1, 1, 1);
        assertNotEquals(other, one.equals(other));
    }

    /**
     * Zwei Satznummern ohne Wagnisart sind gleich, egal wie die Satznummer
     * instantiiert wird.
     */
    @Test
    public void testEqualsSparte() {
        SatzTyp satz210 = new SatzTyp(210, 30);
        SatzTyp sparte30 = SatzTyp.of(210, 30, WagnisartLeben.NULL.getCode());
        ObjectTester.assertEquals(satz210, sparte30);
    }

    /**
     * Test to string.
     */
    @Test
    public void testToString() {
        assertEquals("0001", SatzTyp.of(1).toString());
        assertEquals("0210.050", SatzTyp.of(210, 50).toString());
        assertEquals("0220.010.0", SatzTyp.of(220, 10, 0).toString());
        assertEquals("0220.010.6.1", SatzTyp.of(220, 10, 6, 1).toString());
    }

    @Test
    public void testOf() {
        assertEquals(SatzTyp.of("0001"), SatzTyp.of(1));
        assertEquals(SatzTyp.of("0210.050"), SatzTyp.of(210, 50));
        assertEquals(SatzTyp.of("0220.010.0"), SatzTyp.of(220, 10, 0));
        assertEquals(SatzTyp.of("0220.010.6.1"), SatzTyp.of(220, 10, 6, 1));
    }

    @Test
    public void testGetSatzart() {
        assertEquals(210, SatzTyp.of("0210.050").getSatzart());
    }

    @Test
    public void testGetSparte() {
        assertEquals(50, SatzTyp.of("0210.050").getSparte());
    }

    @Test
    public void testOfWagnisart1u3() {
        assertEquals(SatzTyp.of("0220.010.13.7"), SatzTyp.of(220, 10, 1, 7));
        assertEquals(SatzTyp.of("0220.010.13.7"), SatzTyp.of(220, 10, 3, 7));
    }

    /**
     * SatzTyp "0220.010.13" gibt es eigentlich nicht. Oft ist damit aber
     * eigentlich SatzTyp "0220.010.13.1" gemeint.
     */
    @Test
    public void tesetOfWagnisartLeben() {
        assertEquals(SatzTyp.of("0220.010.13.1"), SatzTyp.of("0220.010.13"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOfInvalid() {
        SatzTyp.of("0001.a");
    }

    @Test
    public void testGetArt() {
        assertEquals("1", SatzTyp.of("0220.020.1").getArtAsString());
        assertEquals("2", SatzTyp.of("0220.020.2").getArtAsString());
        assertEquals("3", SatzTyp.of("0220.020.3").getArtAsString());
        assertEquals("01", SatzTyp.of("0220.580.01").getArtAsString());
        assertEquals("2", SatzTyp.of("0220.580.2").getArtAsString());
    }

    @Test
    public void testGetArt4Wagnisart() {
        assertEquals("0", SatzTyp.of("0220.010.0").getArtAsString());
        assertEquals("13", SatzTyp.of("0220.010.13").getArtAsString());
        assertEquals("13", SatzTyp.of("0221.010.13").getArtAsString());
        assertEquals("48", SatzTyp.of("0220.010.48").getArtAsString());
        assertEquals("48", SatzTyp.of("0221.010.48").getArtAsString());
        assertEquals("6", SatzTyp.of("0220.010.6").getArtAsString());
        assertEquals("6", SatzTyp.of("0221.010.6").getArtAsString());
        assertEquals("5", SatzTyp.of("0220.010.5").getArtAsString());
        assertEquals("5", SatzTyp.of("0221.010.5").getArtAsString());
        assertEquals("2", SatzTyp.of("0220.010.2").getArtAsString());
        assertEquals("2", SatzTyp.of("0221.010.2").getArtAsString());
        assertEquals("7", SatzTyp.of("0220.010.7").getArtAsString());
        assertEquals("7", SatzTyp.of("0221.010.7").getArtAsString());
        assertEquals("9", SatzTyp.of("0220.010.9").getArtAsString());
    }

    @Test
    public void testGetWagnisartAsString() {
        assertEquals("0", SatzTyp.of("0220.010.0").getWagnisartAsString());
        assertEquals("13", SatzTyp.of("0220.010.13").getWagnisartAsString());
        assertEquals("13", SatzTyp.of("0221.010.13").getWagnisartAsString());
        assertEquals("48", SatzTyp.of("0220.010.48").getWagnisartAsString());
        assertEquals("48", SatzTyp.of("0221.010.48").getWagnisartAsString());
        assertEquals("6", SatzTyp.of("0220.010.6").getWagnisartAsString());
        assertEquals("6", SatzTyp.of("0221.010.6").getWagnisartAsString());
        assertEquals("5", SatzTyp.of("0220.010.5").getWagnisartAsString());
        assertEquals("5", SatzTyp.of("0221.010.5").getWagnisartAsString());
        assertEquals("2", SatzTyp.of("0220.010.2").getWagnisartAsString());
        assertEquals("2", SatzTyp.of("0221.010.2").getWagnisartAsString());
        assertEquals("7", SatzTyp.of("0220.010.7").getWagnisartAsString());
        assertEquals("7", SatzTyp.of("0221.010.7").getWagnisartAsString());
        assertEquals("9", SatzTyp.of("0220.010.9").getWagnisartAsString());
    }

    @Test
    public void testOfWagnisart() {
        assertEquals(SatzTyp.of("0220.010.0"), SatzTyp.of(220, 10, 0));
        assertEquals(SatzTyp.of("0220.010.13"), SatzTyp.of(220, 10, 13));
        assertEquals(SatzTyp.of("0221.010.13"), SatzTyp.of(221, 10, 13));
        assertEquals(SatzTyp.of("0220.010.48"), SatzTyp.of(220, 10, 48));
        assertEquals(SatzTyp.of("0221.010.48"), SatzTyp.of(221, 10, 48));
        assertEquals(SatzTyp.of("0220.010.6"), SatzTyp.of(220, 10, 6));
        assertEquals(SatzTyp.of("0221.010.6"), SatzTyp.of(221, 10, 6));
        assertEquals(SatzTyp.of("0220.010.9"), SatzTyp.of(220, 10, 9));
    }

    @Test
    public void testGetArtWagnisart13() {
        assertEquals("13", SatzTyp.of("220.10.1").getArtAsString());
        assertEquals("13", SatzTyp.of("220.10.3").getArtAsString());
        assertEquals("13", SatzTyp.of("0220.010.13").getWagnisartAsString());
    }

    @Test
    public void testGetArtWagnisart48() {
        assertEquals("48", SatzTyp.of("220.10.4").getArtAsString());
        assertEquals("48", SatzTyp.of("220.10.8").getArtAsString());
        assertEquals("48", SatzTyp.of("0220.010.48").getWagnisartAsString());
    }

    @Test
    public void testHasArt() {
        assertTrue(SatzTyp.of("0220.020.1").hasArt());
        assertFalse(SatzTyp.of("0220.000").hasArt());
    }

    @Test
    public void testToStringBausparen() {
        assertEquals("0220.580.01", SatzTyp.of("0220.580.01").toString());
        assertEquals("0220.580.2", SatzTyp.of("0220.580.2").toString());
    }

    @Test
    public void testGetBausparenArt() {
        assertEquals("01", SatzTyp.of("0220.580.01").getBausparenArtAsString());
        assertEquals("2", SatzTyp.of("0220.580.2").getBausparenArtAsString());
        assertEquals("", SatzTyp.of("0220.570").getBausparenArtAsString());
    }

    @Test
    public void testOfBausparenArt() {
        assertEquals(SatzTyp.of("0220.580.01"), SatzTyp.of(220, 580, 1));
        assertEquals(SatzTyp.of("0220.580.2"), SatzTyp.of(220, 580, 2));
        assertEquals(SatzTyp.of("0220.570"), SatzTyp.of(220, 570));
    }

    @Test
    public void testGetKrankenFolgeNr() {
        assertEquals(1, SatzTyp.of("0220.020.1").getKrankenFolgeNr());
        assertEquals(2, SatzTyp.of("0220.020.2").getKrankenFolgeNr());
        assertEquals(3, SatzTyp.of("0220.020.3").getKrankenFolgeNr());
    }

    @Test
    public void testHasKrankenFolgeNr() {
        assertTrue(SatzTyp.of("0220.020.1").hasKrankenFolgeNr());
        assertTrue(SatzTyp.of("0220.020.2").hasKrankenFolgeNr());
        assertTrue(SatzTyp.of("0220.020.3").hasKrankenFolgeNr());
        assertFalse(SatzTyp.of("0220.000").hasKrankenFolgeNr());
        assertFalse(SatzTyp.of("0220.580.01").hasKrankenFolgeNr());
        assertFalse(SatzTyp.of("0220.580.2").hasKrankenFolgeNr());
    }

    @Test
    public void testToStringKrankenFolgeNr() {
        assertEquals("0220.020.1", SatzTyp.of("0220.020.1").toString());
        assertEquals("0220.020.2", SatzTyp.of("0220.020.2").toString());
        assertEquals("0220.020.3", SatzTyp.of("0220.020.3").toString());
    }

    @Test
    public void testOfKrankenFolgeNr() {
        assertEquals(SatzTyp.of(220, 20, 1), SatzTyp.of("0220.020.1"));
        assertEquals(SatzTyp.of(220, 20, 2), SatzTyp.of("0220.020.2"));
        assertEquals(SatzTyp.of(220, 20, 3), SatzTyp.of("0220.020.3"));
    }

    /**
     * Fuer Satzart 210, 211 und 220 gibt es einen "Allgemeinen Satz", die
     * "000" als Sparte hat. Daher sollte "0210" auf "0210.000" abgebildet
     * werden (entsprechendes gilt fuer Satzart 211 und 220).
     */
    @Test
    public void testAllgemeinerSatz() {
        assertEquals(SatzTyp.of("0210.000"), SatzTyp.of("0210"));
        assertEquals(SatzTyp.of("0211.000"), SatzTyp.of("0211"));
        assertEquals(SatzTyp.of("0220.000"), SatzTyp.of("0220"));
    }

    /**
     * SatzTyp "0220.010" gibt es eigentlich nicht. In diesem Fall sollte die
     * Wagnisart 0 genommen werden.
     */
    @Test
    public void testLebenWagnisart0() {
        assertEquals(SatzTyp.of("0220.010.0"), SatzTyp.of("0220.010"));
        assertEquals("0220.010.0", SatzTyp.of("0220.010.0").toString());
    }

    @Test
    public void testGetParent() {
        assertEquals(SatzTyp.of("0220.020"), SatzTyp.of("0220.020.1").getParent());
        assertEquals(SatzTyp.of("0220"), SatzTyp.of("0220.020").getParent());

    }

    @Test
    public void testHasParent() {
        assertFalse(SatzTyp.of("0100").hasParent());
        assertTrue(SatzTyp.of("0220.020").hasParent());
        assertFalse(SatzTyp.of("0220").hasParent());
    }

    @Test
    public void testSatzTyp100() {
        SatzTyp a = SatzTyp.of("0100");
        SatzTyp b = SatzTyp.of("0100.000");
        assertEquals(a.toString(), b.toString());
        ObjectTester.assertEquals(a, b);
    }

}
