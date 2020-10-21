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
        SatzTyp one = new SatzTyp(1, 1);
        SatzTyp other = new SatzTyp(1, 1, 1);
        assertNotEquals(other, one.equals(other));
    }

    /**
     * Zwei Satznummern ohne Wagnisart sind gleich, egal wie die Satznummer
     * instantiiert wird.
     */
    @Test
    public void testEqualsSparte() {
        SatzTyp satz210 = new SatzTyp(210, 30);
        SatzTyp sparte30 = new SatzTyp(210, 30, WagnisartLeben.NULL.getCode(), -1);
        ObjectTester.assertEquals(satz210, sparte30);
    }

    /**
     * Test to string.
     */
    @Test
    public void testToString() {
        assertEquals("0001", new SatzTyp(1).toString());
        assertEquals("0210.050", new SatzTyp(210, 50).toString());
        assertEquals("0220.010.0", new SatzTyp(220, 10, 0).toString());
        assertEquals("0220.010.6.1", new SatzTyp(220, 10, 6, 1).toString());
    }

    @Test
    public void testOf() {
        assertEquals(SatzTyp.of("0001"), new SatzTyp(1));
        assertEquals(SatzTyp.of("0210.050"), new SatzTyp(210, 50));
        assertEquals(SatzTyp.of("0220.010.0"), new SatzTyp(220, 10, 0));
        assertEquals(SatzTyp.of("0220.010.6.1"), new SatzTyp(220, 10, 6, 1));
    }

    @Test
    public void testOfWagnisart1u3() {
        assertEquals(SatzTyp.of("0220.010.13.7"), new SatzTyp(220, 10, 1, 7));
        assertEquals(SatzTyp.of("0220.010.13.7"), new SatzTyp(220, 10, 3, 7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOfInvalid() {
        SatzTyp.of("0001.a");
    }

    @Test
    public void testGetArt() {
        assertEquals("1", SatzTyp.of("0220.020.1").getArt());
        assertEquals("2", SatzTyp.of("0220.020.2").getArt());
        assertEquals("3", SatzTyp.of("0220.020.3").getArt());
        assertEquals("01", SatzTyp.of("0220.580.01").getArt());
        assertEquals("2", SatzTyp.of("0220.580.2").getArt());
    }

    @Test
    public void testGetArt4Wagnisart() {
        assertEquals("0", SatzTyp.of("0220.010.0").getArt());
        assertEquals("13", SatzTyp.of("0220.010.13").getArt());
        assertEquals("13", SatzTyp.of("0221.010.13").getArt());
        assertEquals("48", SatzTyp.of("0220.010.48").getArt());
        assertEquals("48", SatzTyp.of("0221.010.48").getArt());
        assertEquals("6", SatzTyp.of("0220.010.6").getArt());
        assertEquals("6", SatzTyp.of("0221.010.6").getArt());
        assertEquals("5", SatzTyp.of("0220.010.5").getArt());
        assertEquals("5", SatzTyp.of("0221.010.5").getArt());
        assertEquals("2", SatzTyp.of("0220.010.2").getArt());
        assertEquals("2", SatzTyp.of("0221.010.2").getArt());
        assertEquals("7", SatzTyp.of("0220.010.7").getArt());
        assertEquals("7", SatzTyp.of("0221.010.7").getArt());
        assertEquals("9", SatzTyp.of("0220.010.9").getArt());
    }

    @Test
    public void testGetArtWagnisart13() {
        assertEquals("13", new SatzTyp(220, 10, 1).getArt());
        assertEquals("13", new SatzTyp(220, 10, 3).getArt());
    }

    @Test
    public void testGetArtWagnisart48() {
        assertEquals("48", new SatzTyp(220, 10, 4).getArt());
        assertEquals("48", new SatzTyp(220, 10, 8).getArt());
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
        assertEquals("01", SatzTyp.of("0220.580.01").getBausparenArt());
        assertEquals("2", SatzTyp.of("0220.580.2").getBausparenArt());
        assertEquals("", SatzTyp.of("0220.570").getBausparenArt());
    }

}
