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

import static org.junit.Assert.assertEquals;
import gdv.xport.satz.feld.Feld0001;

import org.junit.Test;

/**
 * Unit-Tests fuer {@link Version}-Klasse.
 *
 * @author oliver
 * @since 1.0 (03.02.2015)
 */
public class VersionTest {

    /**
     * Test method for {@link Version#Version(Enum)}.
     */
    @Test
    public void testVersion() {
        Version v = new Version(Feld0001.VERSION_SATZART_0001);
        assertEquals(96, v.getByteAdresse());
        assertEquals((Bezeichner.VERSION_SATZART_0001), v.getBezeichner());
    }

}
