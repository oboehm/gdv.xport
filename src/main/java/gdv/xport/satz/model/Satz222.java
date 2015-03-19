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
 * (c)reated 14.04.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import java.util.*;

/**
 * Diese Klasse repraesentiert die Satzart 222 fuer Antragsdaten. Diese Satzart
 * kennt nur die Sparte 30 (Unfallspezifische Antragsdaten).
 * <p>
 * Alternativ kann man auch direkt <code>new SatzX(222, Feld222.values())</code>
 * verwenden.
 * </p>
 *
 * @author oliver (ob@aosd.de)
 * @since 0.9 (29.03.2013)
 */
public class Satz222 extends SpartensatzX {

    /** Mapping table for sparte to Feldxxx enumeration. */
    private static final Map<Integer, Enum<?>[]> MAPPING = new HashMap<Integer, Enum<?>[]>();

    static {
        MAPPING.put(30, gdv.xport.satz.feld.sparte30.Feld222.values());
    }

    /**
     * Default-Konstruktor. Da die Satzart 220 nur die Sparte 3 kennt, wird
     * diese Sparte als Standard gesetzt.
     */
    public Satz222() {
        this(30);
    }

    /**
     * Legt ein neues Satz222-Objekt fuer die uebergebene Sparte an.
     *
     * @param sparte 30 (andere Sparten sind im GDV-Handbuch (noch) nicht
     *        aufgelistet)
     */
    public Satz222(final int sparte) {
        super(222, sparte);
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
