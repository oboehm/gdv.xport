/*
 * Copyright (c) 2011-2013 by aosd.de
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
 * (c)reated 08.04.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gdv.xport.io.ImportException;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.feld.MetaFeldInfo;

/**
 * Diese Klasse repraesentiert die Satzart 220. Es handelt es sich dabei um eine
 * alternative Implementierung der SpartenspezifischerTeil-Klasse, die nach dem
 * Soplet- Ansatz (s. <a href="http://www.soplets.org/">soplets.org</a>)
 * implementiert wurde.
 * <p>
 * <b>HINWEIS</b>: Bitte nicht {@link gdv.xport.satz.feld.sparte30.Feld220}
 * ueber {@link gdv.xport.util.SatzFactory#registerEnum(Class, int, int)} fuer
 * Satz 220, Sparte 30 registrieren, sondern diese Klasse hier. Sie behandelt
 * den Import fuer Teildatensatz 9 richtig, der vom allgemeinen Schema etwas
 * abweicht.
 * </p>
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (08.04.2011)
 */
public class Satz220 extends SpartensatzX {

    /** Mapping table for sparte to Feldxxx enumeration. */
    private static final Map<Integer, Enum<?>[]> MAPPING = new HashMap<Integer, Enum<?>[]>();

    static {
        MAPPING.put(30, gdv.xport.satz.feld.sparte30.Feld220.values());
        MAPPING.put(40, gdv.xport.satz.feld.sparte40.Feld220.values());
        MAPPING.put(51, gdv.xport.satz.feld.sparte51.Feld220.values());
        MAPPING.put(52, gdv.xport.satz.feld.sparte52.Feld220.values());
        MAPPING.put(53, gdv.xport.satz.feld.sparte53.Feld220.values());
        MAPPING.put(55, gdv.xport.satz.feld.sparte55.Feld220.values());
        MAPPING.put(59, gdv.xport.satz.feld.sparte59.Feld220.values());
        MAPPING.put(70, gdv.xport.satz.feld.sparte70.Feld220.values());
        MAPPING.put(110, gdv.xport.satz.feld.sparte110.Feld220.values());
        MAPPING.put(140, gdv.xport.satz.feld.sparte140.Feld220.values());
        MAPPING.put(510, gdv.xport.satz.feld.sparte510.Feld220.values());
    }

    /**
     * Default-Konstruktor.
     */
    public Satz220() {
        this(UNKNOWN_SPARTE);
    }

    /**
     * Legt ein neues Satz220-Objekt fuer die uebergebene Sparte an.
     *
     * @param sparte
     *            Sparte (z.B. 10)
     */
    public Satz220(final int sparte) {
        super(220, sparte);
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
