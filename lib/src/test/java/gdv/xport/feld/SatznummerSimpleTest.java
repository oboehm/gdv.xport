/*
 * Copyright (c) 2021 by Oliver Boehm
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
 * (c)reated 28.10.21 by oliver (ob@oasd.de)
 */
package gdv.xport.feld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import patterntesting.runtime.junit.ObjectTester;

import static org.junit.Assert.assertTrue;

/**
 * Einfache Unit-Tests fuer {@link Satznummer}.
 */
public final class SatznummerSimpleTest {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    public void testIsEmpty() {
        Satznummer empty = new Satznummer();
        assertTrue(empty.isEmpty());
    }

    @Test
    public void testZero() {
        Satznummer zero = new Satznummer();
        zero.setInhalt('0');
        assertTrue(zero.isInvalid());
    }

    @Test
    public void testLetter() {
        Satznummer x = new Satznummer();
        x.setInhalt('a');
        assertTrue(x.isInvalid());
    }

    @Test
    public void testEquals() {
        Satznummer nummer = new Satznummer();
        Satznummer nr = new Satznummer(new Zeichen(Bezeichner.SATZ_NR_1, 256));
        ObjectTester.assertEquals(nummer, nr);
    }

}
