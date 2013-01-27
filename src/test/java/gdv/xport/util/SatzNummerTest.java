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

import static org.junit.Assert.*;

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
        SatzNummer one = new SatzNummer(1,1);
        SatzNummer other = new SatzNummer(1,1,0);
        assertFalse("expected: " + one + " != " + other, one.equals(other));
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
