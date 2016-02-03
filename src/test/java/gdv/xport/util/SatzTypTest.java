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
        assertFalse("expected: " + one + " != " + other, one.equals(other));
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

}
