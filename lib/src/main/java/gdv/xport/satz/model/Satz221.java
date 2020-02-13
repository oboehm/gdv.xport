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

import gdv.xport.feld.Bezeichner;
import gdv.xport.satz.feld.sparte10.wagnisart13.Feld221Wagnis13;
import gdv.xport.satz.feld.sparte10.wagnisart13.Feld221Wagnis13Auszahlungen;
import gdv.xport.satz.feld.sparte10.wagnisart13.Feld221Wagnis13ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld221Wagnis2;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld221Wagnis2Auszahlungen;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld221Wagnis2ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart48.Feld221Wagnis48;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld221Wagnis5;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld221Wagnis5ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld221Wagnis6;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld221Wagnis6ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld221Wagnis7;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld221Wagnis7ZukSummenaenderungen;

import java.util.*;

/**
 * Diese Klasse repraesentiert die Satzart 221. Es handelt es sich dabei um eine
 * alternative Implementierung der ehemaligen Erweiterungssatz221-Klasse, die
 * nach dem Soplet-Ansatz (s. <a href="http://www.soplets.org/">soplets.org</a>)
 * implementiert wurde.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (14.04.2011)
 */
public class Satz221 extends SpartensatzX {

    /** Mapping table for sparte to Feldxxx enumeration. */
    private static final Map<Integer, Enum[]> MAPPING = new HashMap<Integer, Enum[]>();
    private static final List<Enum[]>[] MAPPING_SPARTE10 = new List[10];

    static {
        initMappingSparte10();
        initMapping();
    }

    private static void initMappingSparte10() {
        MAPPING_SPARTE10[0] = new ArrayList<>();
        MAPPING_SPARTE10[1] = Arrays.asList(Feld221Wagnis13.values(), Feld221Wagnis13Auszahlungen.values(),
                Feld221Wagnis13ZukSummenaenderungen.values());
        MAPPING_SPARTE10[2] = Arrays.asList(Feld221Wagnis2.values(), Feld221Wagnis2Auszahlungen.values(),
                Feld221Wagnis2ZukSummenaenderungen.values());
        MAPPING_SPARTE10[3] = MAPPING_SPARTE10[1];
        MAPPING_SPARTE10[4] = Collections.singletonList(Feld221Wagnis48.values());
        MAPPING_SPARTE10[5] = Arrays.asList(Feld221Wagnis5.values(), Feld221Wagnis5ZukSummenaenderungen.values());
        MAPPING_SPARTE10[6] = Arrays.asList(Feld221Wagnis6.values(), Feld221Wagnis6ZukSummenaenderungen.values());
        MAPPING_SPARTE10[7] = Arrays.asList(Feld221Wagnis7.values(), Feld221Wagnis7ZukSummenaenderungen.values());
        MAPPING_SPARTE10[8] = MAPPING_SPARTE10[4];
        MAPPING_SPARTE10[9] = new ArrayList<>();
    }

    private static void initMapping() {
        MAPPING.put(30, gdv.xport.satz.feld.sparte30.Feld221.values());
        MAPPING.put(40, gdv.xport.satz.feld.sparte40.Feld221.values());
        MAPPING.put(51, gdv.xport.satz.feld.sparte51.Feld221.values());
        MAPPING.put(52, gdv.xport.satz.feld.sparte52.Feld221.values());
        MAPPING.put(53, gdv.xport.satz.feld.sparte53.Feld221.values());
        MAPPING.put(55, gdv.xport.satz.feld.sparte55.Feld221.values());
        MAPPING.put(59, gdv.xport.satz.feld.sparte59.Feld221.values());
        MAPPING.put(70, gdv.xport.satz.feld.sparte70.Feld221.values());
    }

    /**
     * Default-Konstruktor.
     */
    public Satz221() {
        this(UNKNOWN_SPARTE);
    }

    /**
     * Legt ein neues Satz221-Objekt fuer die uebergebene Sparte an.
     *
     * @param sparte
     *            Sparte (z.B. 10)
     */
    public Satz221(final int sparte) {
        super(221, sparte);
    }

    @Override
    public void set(Bezeichner name, String value) {
        if ((this.getSparte() == 10) && !hasFeld(name)) {
            Satz220.setUpTeildatensatzeOf(this, name, MAPPING_SPARTE10);
        }
        super.set(name, value);
    }

    /**
     * Liefert die Mapping-Tabelle zu Sparte - Feldxxx zurueck.
     *
     * @return Mapping-Tabelle
     * @see gdv.xport.satz.model.SpartensatzX#getMapping()
     */
    @Override
    protected Map<Integer, Enum[]> getMapping() {
        return MAPPING;
    }

}
