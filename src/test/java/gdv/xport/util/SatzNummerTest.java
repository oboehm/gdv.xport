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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import gdv.xport.satz.feld.common.WagnisartLeben;

import org.junit.Test;

import patterntesting.runtime.junit.ObjectTester;

/**
 * JUnit-Tests fuer {@link SatzNummer}.
 *
 * @author oliver
 * @since 0.9 (26.01.2013)
 */
public class SatzNummerTest {

    /**
     * Test method for {@link SatzNummer#equals(Object)}.
     */
    @Test
    public void testEqualsObject() {
        SatzNummer one = new SatzNummer(1);
        SatzNummer anotherOne = new SatzNummer(1);
        ObjectTester.assertEquals(one, anotherOne);
    }

    /**
     * Test method for {@link SatzNummer#equals(Object)}.
     */
    @Test
    public void testNotEquals() {
        SatzNummer one = new SatzNummer(1, 1);
        SatzNummer other = new SatzNummer(1, 1, 1);
        assertFalse("expected: " + one + " != " + other, one.equals(other));
    }

    /**
     * Zwei Satznummern ohne Wagnisart sind gleich, egal wie die Satznummer
     * instantiiert wird.
     */
    @Test
    public void testEqualsSparte() {
        SatzNummer satz210 = new SatzNummer(210, 30);
        SatzNummer sparte30 = new SatzNummer(210, 30, WagnisartLeben.NULL.getCode(), -1);
        ObjectTester.assertEquals(satz210, sparte30);
    }

    /**
     * Test to string.
     */
    @Test
    public void testToString() {
        assertEquals("0001", new SatzNummer(1).toString());
        assertEquals("0210.050", new SatzNummer(210, 50).toString());
        assertEquals("0220.010.0", new SatzNummer(220, 10, 0).toString());
        assertEquals("0220.010.6.1", new SatzNummer(220, 10, 6, 1).toString());
    }

}
