/*
 * $Id$
 *
 * Copyright (c) 2012 by Oliver Boehm
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
 * (c)reated 23.04.2013 by oliver (ob@oasd.de)
 */

package gdv.xport.satz.feld.sparte10.wagnisart9;

import static org.junit.Assert.assertEquals;
import gdv.xport.feld.Feld;
import gdv.xport.satz.*;
import gdv.xport.satz.model.SatzX;

import java.io.IOException;

import org.junit.Test;

/**
 * @author oboehm
 * @since 1.2.20 (23.04.2013)
 */
public class Feld220Wagnis9Test extends AbstractDatensatzTest {

    private final SatzX wagnisdaten = new SatzX(220, Feld220Wagnis9.values());

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new SatzX(220, Feld220Wagnis9.values());
    }

    /**
     * Hier importieren wir einen Satz 220, Sparte 10, Wagnisart 9. Die Daten
     * dazu stammen aus Bender_Leben.GDV, Zeile 34 - 36.
     *
     * @throws IOException sollte eigentlich nicht vorkommen, da wir von einem
     *         String importieren
     */
    @Test
    public void testWagnisart9() throws IOException {
        String input = "02201104  010       12001469310100085869  1                91010"
                + "720080107204201072012034100000000607853oG                1901072"
                + "0120000000130000000000000000000000169512092012000000000000010720"
                + "4215 0000000000000000000000000000000000000000000000  06531     1"
                + "\n"
                + "02201104  010       12001469310100085869  1                91000"
                + "00000000000000006078000000000000 1                              "
                + "00000000                               0000000000000000000000002"
                + "0090000000000000000000000000000003                             2"
                + "\n"
                + "02201104  010       12001469310100085869  1                911 1"
                + "VN                            1000009Eltern                     "
                + "   100000                                                       "
                + "                                                               6"
                + "\n";
        wagnisdaten.importFrom(input);
        Feld wagnisart = wagnisdaten.getFeld(Feld220Wagnis9.WAGNISART);
        assertEquals("9", wagnisart.getInhalt());
        checkDatensatz(wagnisdaten, input);
    }

}

