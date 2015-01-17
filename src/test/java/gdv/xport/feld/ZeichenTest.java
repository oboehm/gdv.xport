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
import gdv.xport.satz.feld.Feld100;

import org.junit.Test;

/**
 * JUnit-Test fuer Zeichen-Klasse.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (10.05.2011)
 */
public class ZeichenTest {

    /**
     * Test method for {@link gdv.xport.feld.Zeichen#Zeichen(java.lang.Enum)}.
     */
    @Test
    public void testZeichenEnum() {
        Zeichen zeichen = new Zeichen(Feld100.ANREDESCHLUESSEL);
        assertEquals(43, zeichen.getByteAdresse());
    }

    /**
     * Test method for {@link gdv.xport.feld.Zeichen#Zeichen(java.lang.Enum)}.
     */
    @Test
    public void testZeichenName() {
        Zeichen zeichen = new Zeichen(Bezeichner.NAME_ANREDESCHLUESSEL, FeldTest.createFeldInfo());
        assertEquals(1, zeichen.getAnzahlBytes());
    }

}
