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
 * (c)reated 06.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import gdv.xport.satz.feld.Feld200;

/**
 * Diese Klasse repraesentiert die Satzart 200. Es handelt es sich dabei um eine
 * alternative Implementierung der ehemalinge AllgemeinerVertragsteil-Klasse,
 * die nach dem Soplet- Ansatz (s. <a
 * href="http://www.soplets.org/">soplets.org</a>) implementiert wurde.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (06.03.2011)
 */
public class Satz200 extends SatzX {

    /**
     * Default-Konstruktor.
     */
    public Satz200() {
        super(200, Feld200.values());
    }

    /**
     * Legt ein neues Satz200-Objekt fuer die uebergebene Sparte an.
     *
     * @param sparte Sparte (z.B. 10)
     */
    public Satz200(final int sparte) {
        super(200, sparte, Feld200.values());
    }

}
