/*
 * Copyright (c) 2011, 2012 by Oli B.
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
 * (c)reated 10.05.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gdv.xport.satz.feld.Feld100;

/**
 * JUnit-Test fuer Zeichen-Klasse.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (10.05.2011)
 */
public final class ZeichenTest extends AbstractFeldTest {

    /* (non-Javadoc)
     * @see gdv.xport.feld.AbstractFeldTest#getTestFeld()
     */
    @Override
    protected Feld getTestFeld() {
        return new Zeichen(Feld100.ANREDESCHLUESSEL);
    }

    /**
     * Test method for {@link Zeichen#Zeichen(Enum)}.
     */
    @Test
    public void testZeichenEnum() {
        Zeichen zeichen = new Zeichen(Feld100.ANREDESCHLUESSEL);
        assertEquals(43, zeichen.getByteAdresse());
    }

    /**
     * Test method for {@link Zeichen#Zeichen(Enum)}.
     */
    @Test
    public void testZeichenName() {
        Zeichen zeichen = new Zeichen(Bezeichner.ANREDESCHLUESSEL.getName(), FeldTest.createFeldInfo());
        assertEquals(1, zeichen.getAnzahlBytes());
    }

    /**
     * Test-Methode fuer {@link Zeichen#Zeichen(int, char)}.
     */
    @Test
    public void testZeichen() {
        Zeichen zeichen = new Zeichen(42, 'c');
        assertEquals(42, zeichen.getByteAdresse());
        assertEquals(42, zeichen.getEndAdresse());
    }

}
