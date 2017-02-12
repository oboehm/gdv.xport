/*
 * Copyright (c) 2011, 2012 by aosd.de
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
 * (c)reated 26.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import static org.junit.Assert.assertEquals;
import gdv.xport.satz.Satz;
import gdv.xport.satz.feld.Feld200;
import gdv.xport.satz.feld.sparte10.Feld220Wagnis0;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * JUnit test for SatzX.
 *
 * @author oliver (ob@aosd.de)
 * @since 26.03.2011
 */
public final class SatzXTest {

    private static Log log = LogFactory.getLog(SatzXTest.class);

    /**
     * Test method for {@link SatzX#SatzX(int, java.lang.Enum[])}.
     */
    @Test
    public void testSatzX() {
        Satz satz200 = new SatzX(200, Feld200.values());
        assertEquals(2, satz200.getTeildatensaetze().size());
        log.info("satz200 = \"" + satz200 + "\"");
    }

    /**
     * {@link SatzX} mit der {@link Feld220Wagnis0} sollte mit Wagnisart "0"
     * vorbelegt sein.
     */
    @Test
    public void testWagnisart() {
        SatzX satz220 = new SatzX(220, Feld220Wagnis0.class);
        assertEquals("0", satz220.getWagnisart());
    }

}
