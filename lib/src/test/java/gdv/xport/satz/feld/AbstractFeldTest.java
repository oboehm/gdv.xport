/*
 * Copyright (c) 2018 by Oliver Boehm
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
 * (c)reated 07.04.18 by oliver (ob@oasd.de)
 */
package gdv.xport.satz.feld;

import gdv.xport.satz.Satz;

/**
 * Class AbstractFeldTest.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
public abstract class AbstractFeldTest {

    /**
     * Dieser Test ueberprueft die Konstanten einer Enum, um ueber sie
     * tatsaechlich die Werte eines Satzes ausgelesen werden koennen.
     * Diese Pruefung wurde mit Issue #10 eingefuehrt
     * (https://github.com/oboehm/gdv.xport/issues/10).
     *
     * @param satz Satz, zu dem das Enum gehoert
     * @param values z.B. Feld100.values()
     */
    protected static void checkEntries(Satz satz, Enum<?>[] values) {
        for (Enum<?> entry : values) {
            if (entry.name().startsWith("INTRO") || entry.name().startsWith("TEILDATENSATZ")) {
                continue;
            }
            satz.getFeld(entry);
        }
    }

}
