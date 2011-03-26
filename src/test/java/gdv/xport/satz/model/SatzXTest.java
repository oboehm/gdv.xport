/*
 * Copyright (c) 2010 by agentes
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
 * (c)reated 26.03.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz.model;

import static org.junit.Assert.*;
import gdv.xport.satz.feld.Feld0200;

import org.junit.Test;

/**
 * JUnit test for SatzX.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 26.03.2011
 */
public class SatzXTest {

    /**
     * Test method for {@link SatzX#SatzX(int, java.lang.Enum[])}.
     */
    @Test
    public void testSatzX() {
        SatzX satz200 = new SatzX(200, Feld0200.values());
        assertEquals(2, satz200.getTeildatensaetze().size());
    }

}
