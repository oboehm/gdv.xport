/*
 * Copyright (c) 2011-2020 by aosd.de
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

import gdv.xport.feld.Bezeichner;
import gdv.xport.satz.feld.sparte10.Feld220Wagnis0;
import gdv.xport.satz.feld.sparte10.wagnisart13.*;
import gdv.xport.satz.feld.sparte10.wagnisart2.*;
import gdv.xport.satz.feld.sparte10.wagnisart48.Feld220Wagnis48;
import gdv.xport.satz.feld.sparte10.wagnisart48.Feld220Wagnis48Bezugsrechte;
import gdv.xport.satz.feld.sparte10.wagnisart48.Feld220Wagnis48Wertungssummen;
import gdv.xport.satz.feld.sparte10.wagnisart48.Feld220Wagnis48ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld220Wagnis5;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld220Wagnis5Bezugsrechte;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld220Wagnis5Wertungssummen;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld220Wagnis5ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld220Wagnis6;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld220Wagnis6Bezugsrechte;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld220Wagnis6Wertungssummen;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld220Wagnis6ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld220Wagnis7;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld220Wagnis7Bezugsrechte;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld220Wagnis7Wertungssummen;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld220Wagnis7ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart9.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

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

    private static final Logger LOG = LogManager.getLogger(Satz220.class);
    /** Mapping table for sparte to Feldxxx enumeration. */
    private static final Map<Integer, Enum[]> MAPPING = new HashMap<>();
    private static final List<Enum[]>[] MAPPING_SPARTE10 = new List[10];

    static {
        initMappingSparte10();
        initMapping();
    }

    private static void initMappingSparte10() {
        MAPPING_SPARTE10[0] = Collections.singletonList(Feld220Wagnis0.values());
        MAPPING_SPARTE10[1] = Arrays.asList(Feld220Wagnis13.values(), Feld220Wagnis13Bezugsrechte.values(),
                Feld220Wagnis13Auszahlungen.values(), Feld220Wagnis13ZukSummenaenderungen.values(),
                Feld220Wagnis13Wertungssummen.values());
        MAPPING_SPARTE10[2] = Arrays.asList(Feld220Wagnis2.values(), Feld220Wagnis2Bezugsrechte.values(),
                Feld220Wagnis2Auszahlungen.values(), Feld220Wagnis2ZukSummenaenderungen.values(),
                Feld220Wagnis2Wertungssummen.values());
        MAPPING_SPARTE10[3] = MAPPING_SPARTE10[1];
        MAPPING_SPARTE10[4] = Arrays.asList(Feld220Wagnis48.values(), Feld220Wagnis48Bezugsrechte.values(),
                Feld220Wagnis48ZukSummenaenderungen.values(), Feld220Wagnis48Wertungssummen.values());
        MAPPING_SPARTE10[5] = Arrays.asList(Feld220Wagnis5.values(), Feld220Wagnis5Bezugsrechte.values(),
                Feld220Wagnis5ZukSummenaenderungen.values(), Feld220Wagnis5Wertungssummen.values());
        MAPPING_SPARTE10[6] = Arrays.asList(Feld220Wagnis6.values(), Feld220Wagnis6Bezugsrechte.values(),
                Feld220Wagnis6ZukSummenaenderungen.values(), Feld220Wagnis6Wertungssummen.values());
        MAPPING_SPARTE10[7] = Arrays.asList(Feld220Wagnis7.values(), Feld220Wagnis7Bezugsrechte.values(),
                Feld220Wagnis7ZukSummenaenderungen.values(), Feld220Wagnis7Wertungssummen.values());
        MAPPING_SPARTE10[8] = MAPPING_SPARTE10[4];
        MAPPING_SPARTE10[9] = Arrays.asList(Feld220Wagnis9.values(), Feld220Wagnis9Bezugsrechte.values(),
                Feld220Wagnis9Auszahlungen.values(), Feld220Wagnis9ZukSummenaenderungen.values(),
                Feld220Wagnis9Wertungssummen.values());
    }

    private static void initMapping() {
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
     * @param sparte Sparte (z.B. 10)
     */
    public Satz220(final int sparte) {
        super(220, sparte);
        if (sparte == 10) {
            LOG.warn("Wagnisart fehlt - fuer Sparte 10 bitte anderen Konstruktor verwenden.");
        }
    }

    /**
     * Dieser Konstruktor ist fuer Sparte 10 gedacht, wo es verschiedene
     * Auspraegungen durch die Wagnisart gibt.
     *
     * @param sparte sollte immer 10 sein
     * @param wagnisart Zahl von 1 bis 9
     */
    public Satz220(final int sparte, final int wagnisart) {
        this(MAPPING_SPARTE10[wagnisart].get(0));
        if (sparte != 10) {
            throw new IllegalArgumentException(
                    "falsche Sparte " + sparte + ": Wagnisart " + wagnisart + " gibt es nur bei Sparte 10");
        }
        this.getFeld(Bezeichner.WAGNISART).setInhalt(wagnisart);
    }

    /**
     * Dies ist der Konstruktor fuer Sparte 10. Hier gibt es verschiedene
     * Belegung der Wagnisart, die ueber die Felder vorbelegt werden.
     *
     * @param felder z.B. Feld220Wagnis9.values()
     */
    public Satz220(final Enum[] felder) {
        super(220, 10, felder);
        this.getFeld(Bezeichner.WAGNISART).setInhalt(getWagnisartFrom(felder));
    }

    private static int getWagnisartFrom(Enum[] felder) {
        for (int art = 0; art < MAPPING_SPARTE10.length; art++) {
            for (Enum[] enumValues : MAPPING_SPARTE10[art]) {
                if (Arrays.equals(felder, enumValues)) {
                    return art;
                }
            }
        }
        return 0;
    }

    @Override
    public void set(Bezeichner name, String value) {
        if ((this.getSparte() == 10) && !hasFeld(name)) {
            setUpTeildatensatzeOf(this, name, MAPPING_SPARTE10);
        }
        super.set(name, value);
    }

    protected static void setUpTeildatensatzeOf(SatzX satz, Bezeichner name, List<Enum[]>[] mappings) {
        for (int art = 0; art < mappings.length; art++) {
            if (hasIn(mappings[art], name)) {
                LOG.info("{} wird fuer Wagnisart {} aufgesetzt.", satz.toShortString(), art);
                setUpTeildatensatzeOf(satz, name, mappings[art]);
                break;
            }
        }
    }

    private static void setUpTeildatensatzeOf(SatzX satz, Bezeichner name, List<Enum[]> valuesList) {
        for (Enum[] sparte10Values : valuesList) {
            if (hasInSparte10(name, sparte10Values)) {
                satz.setUpTeildatensaetze(sparte10Values);
                return;
            }
        }
        LOG.warn("{} mit {} konnte nicht aufgesetzt werden.", satz.toShortString(), name);
    }

    private static boolean hasIn(List<Enum[]> mappingList, Bezeichner name) {
        for (Enum[] sparte10Values : mappingList) {
            if (hasInSparte10(name, sparte10Values)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasInSparte10(Bezeichner name, Enum[] sparte10Values) {
        for (Enum e : sparte10Values) {
            if (name.equals(Bezeichner.of(e))) {
                return true;
            }
        }
        return false;
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
