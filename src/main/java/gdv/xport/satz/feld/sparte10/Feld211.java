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
 * (c)reated 06.04.11 by oliver
 */

package gdv.xport.satz.feld.sparte10;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 211, Sparte 10.
 *
 * @author oliver
 * @since 06.04.11
 */
public enum Feld211 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /**
     * Vorlaufsumme in Waehrungseinheiten (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 43
    )
    VORLAUFSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Zukuenftiger Gesamtbeitrag in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 57
    )
    ZUKUENFTIGER_GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 187,
            byteAdresse = 69
    )
    LEERSTELLEN,

    /**
     * Satznummer.
     */
    @FeldInfo(
          teildatensatz = 1,
          nr = 39,
          type = Zeichen.class,
          anzahlBytes = 1,
          byteAdresse = 256
    )
    SATZNUMMER1;

}
