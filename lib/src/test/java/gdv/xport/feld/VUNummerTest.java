/*
 * Copyright (c) 2009 - 2012 by Oli B.
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
 * (c)reated 09.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Die VUNummer (Versicherungs-Unternehmen-Nummer) ist ein fuenfstelliges
 * alphanumerisches Feld (ab 1.1.1993 wohl nur noch vierstellig).
 *
 * @author oliver
 * @since 09.10.2009
 * @version $Revision$
 */
public class VUNummerTest extends AbstractFeldTest {

    /* (non-Javadoc)
     * @see gdv.xport.feld.AbstractFeldTest#getTestFeld()
     */
    @Override
    protected Feld getTestFeld() {
        return new VUNummer("4711");
    }

    /**
     * Test method for {@link gdv.xport.feld.VUNummer#VUNummer(java.lang.String)}.
     */
    @Test
    public void testVUNummerString() {
        VUNummer vunr = new VUNummer("1234");
        assertEquals(5, vunr.getAnzahlBytes());
        assertEquals("1234 ", vunr.getInhalt());
    }

}

