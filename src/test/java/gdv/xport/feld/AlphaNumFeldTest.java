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
import gdv.xport.annotation.FeldInfo;
import gdv.xport.satz.feld.Feld100;

import org.junit.Test;

/**
 * JUnit-Test fuer AlphaNum-Klasse.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (10.05.2011)
 */
public final class AlphaNumFeldTest extends AbstractFeldTest {

    private enum Alphabet { ALPHA, BETA, GAMMA, DYNAMIK; }

    /* (non-Javadoc)
     * @see gdv.xport.feld.AbstractFeldTest#getTestFeld()
     */
    @Override
    protected Feld getTestFeld() {
        return new AlphaNumFeld(Feld100.NAME1);
    }

    /**
     * Test method for {@link AlphaNumFeld#AlphaNumFeld(java.lang.Enum, gdv.xport.annotation.FeldInfo)}.
     */
    @Test
    public void testAlphaNumFeldEnumFeldInfo() {
        Alphabet alpha = Alphabet.DYNAMIK;
        FeldInfo info = FeldTest.createFeldInfo();
        AlphaNumFeld feld = new AlphaNumFeld(alpha, info);
        assertEquals("Dynamik", feld.getBezeichnung());
    }

}

