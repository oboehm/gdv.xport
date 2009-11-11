/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 30.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static org.junit.Assert.*;
import gdv.xport.feld.NumFeld;

import org.junit.Test;

/**
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.1.0 (30.10.2009)
 */
public class SatzFactoryTest extends AbstractSatzTest {

    @Test
    public void testGetSatz() {
        Satz vorsatz = new Vorsatz();
        String content = vorsatz.toLongString();
        Satz satz = SatzFactory.getSatz(vorsatz.toLongString());
        assertEquals(content, satz.toLongString());
        assertEquals(Vorsatz.class, satz.getClass());
    }

    @Test
    public void testGetUnsupportedSatz() {
        Datensatz unsupported = new Datensatz("0123");
        unsupported.setVuNummer("56789");
        unsupported.setSparte(88);
        unsupported.add(new NumFeld("zweiundvierzig", 4, 200, 42));
        String content = unsupported.toLongString();
        Satz imported = SatzFactory.getSatz(content);
        assertEquals(content, imported.toLongString());
        assertTrue(imported + " should be valid", imported.isValid());
    }
    
    @Test
    public void testRegister() {
        SatzFactory.register(Datensatz.class, 42);
        Satz satz = SatzFactory.getSatz(42);
        assertEquals(Datensatz.class, satz.getClass());
    }

}

