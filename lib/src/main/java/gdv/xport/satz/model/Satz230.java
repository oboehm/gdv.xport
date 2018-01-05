/*
 * Copyright (c) 2013 by Oli B.
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
 * (c)reated 29.03.2013 by Oli B. (oliver.boehm@gmail.com)
 */

package gdv.xport.satz.model;

import java.util.*;

/**
 * Diese Klasse repraesentiert die Satzart 230 fuer Spartenspezifischer Teil.
 * Diese Satzart kennt Sparte 10 (Leben) und Sparte 30 (Unfall Leistungsarten).
 * <p>
 * Alternativ kann man auch direkt
 * <code>new SatzX(230, Feld230.values())</code>
 * verwenden.
 * </p>
 *
 * @author oliver (ob@aosd.de)
 * @since 0.9 (29.03.2013)
 */
public class Satz230 extends SpartensatzX {

    /** Mapping table for sparte to Feldxxx enumeration. */
    private static final Map<Integer, Enum<?>[]> MAPPING = new HashMap<Integer, Enum<?>[]>();

    static {
        MAPPING.put(10, gdv.xport.satz.feld.sparte10.wagnisart9.Feld230.values());
        MAPPING.put(30, gdv.xport.satz.feld.sparte30.Feld230.values());
    }

    /**
     * Default-Konstruktor.
     */
    public Satz230() {
        this(UNKNOWN_SPARTE);
    }

    /**
     * Legt ein neues Satz230-Objekt fuer die uebergebene Sparte an.
     *
     * @param sparte 30
     */
    public Satz230(final int sparte) {
        super(230, sparte);
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

