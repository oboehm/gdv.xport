/*
 * Copyright (c) 2014 by Oli B.
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
 * (c)reated 25.09.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import patterntesting.runtime.junit.ObjectTester;

/**
 * JUnit-Tests fuer die {@link Bezeichner}-Klasse.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (25.09.2014)
 */
public class BezeichnerTest {

    /**
     * Test-Methode fuer {@link Bezeichner#toString()}.
     */
    @Test
    public void testToString() {
        String name = "Anrede";
        String s = new Bezeichner(name).toString();
        assertTrue(s, s.contains(name));
    }

    /**
     * Zwei Bezeichner mit dem identischen Namen sollten natuerlich gleich
     * sein.
     */
    @Test
    public void testEqualsExact() {
        String name = "Hello";
        ObjectTester.assertEquals(new Bezeichner(name), new Bezeichner(name));
    }

}
