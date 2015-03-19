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
 * (c)reated 06.04.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse repraesentiert die Satzart 211. Es handelt es sich dabei um eine
 * alternative Implementierung der ehemaligen Erweiterungssatz211-Klasse, die
 * nach dem Soplet-Ansatz (s. <a
 * href="http://www.soplets.org/">soplets.org</a>) implementiert wurde.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (06.04.2011)
 */
public class Satz211 extends SpartensatzX {

    /** Mapping table for sparte to Feldxxx enumeration. */
    private static final Map<Integer, Enum<?>[]> MAPPING = new HashMap<Integer, Enum<?>[]>();

    static {
        MAPPING.put(10, gdv.xport.satz.feld.sparte10.Feld211.values());
        MAPPING.put(40, gdv.xport.satz.feld.sparte40.Feld211.values());
        MAPPING.put(50, gdv.xport.satz.feld.sparte50.Feld211.values());
    }

    /**
     * Default-Konstruktor.
     */
    public Satz211() {
        this(UNKNOWN_SPARTE);
    }

    /**
     * Legt ein neues Satz210-Objekt fuer die uebergebene Sparte an.
     *
     * @param sparte Sparte (z.B. 10)
     */
    public Satz211(final int sparte) {
        super(211, sparte);
    }

    /**
     * Liefert die Mapping-Tabelle zu Sparte - Feldxxx zurueck.
     *
     * @return Mapping-Tabelle
     * @see gdv.xport.satz.model.SpartensatzX#getMapping()
     */
    @Override
    protected Map<Integer, Enum<?>[]> getMapping() {
        return MAPPING;
    }

}
