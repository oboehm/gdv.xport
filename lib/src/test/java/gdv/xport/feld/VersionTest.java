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
 * (c)reated 03.02.2015 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import gdv.xport.util.SatzTyp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit-Tests fuer {@link Version}-Klasse.
 *
 * @author oliver
 * @since 1.0 (03.02.2015)
 */
public class VersionTest {

    @Test
    public void testVersion() {
        Version v = new Version(Bezeichner.VERSION_SATZART_0001, 96);
        assertEquals(96, v.getByteAdresse());
        assertEquals((Bezeichner.VERSION_SATZART_0001), v.getBezeichner());
    }

    @Test
    public void testGetSatzTyp1() {
        Version v = new Version(Bezeichner.VERSION_SATZART_0001, 96);
        assertEquals(SatzTyp.of("0001"), v.getSatzTyp());
    }

    @Test
    public void testGetSatzTyp210() {
        Version v = new Version(Bezeichner.VERSION_SATZART_0210_050, 105);
        assertEquals(SatzTyp.of("0210.050"), v.getSatzTyp());
    }

    @Test
    public void testGetSatzTyp0210080() {
        Version v = new Version(new Bezeichner("Satzart 0210", "Satzart0210080"), 171);
        assertEquals(SatzTyp.of("0210.080"), v.getSatzTyp());
    }

    @Test
    public void testGetSatzTyp02102() {
        Version v = new Version(new Bezeichner("Satzart 0210", "Satzart02102"), 183);
        assertEquals(SatzTyp.of("0210.170"), v.getSatzTyp());
    }

    @Test
    public void testGetSatzTyp0300() {
        Version v = new Version(new Bezeichner("Beteiligungs-Informationssatz Satzart 0300"), 210);
        assertEquals(SatzTyp.of("0300"), v.getSatzTyp());
    }

    @Test
    public void testGetSatzTyp0251() {
        Version v = new Version(new Bezeichner("Satzart 0251 Einzelanmeldung"), 174);
        assertEquals(SatzTyp.of("0251"), v.getSatzTyp());
    }

    @Test
    public void testGetSatzTyp9999() {
        Version v = new Version(new Bezeichner("Nachsatz Satzart 9999", "Nachsatzsatzart9999"), 225);
        assertEquals(SatzTyp.of("9999"), v.getSatzTyp());
    }

}
