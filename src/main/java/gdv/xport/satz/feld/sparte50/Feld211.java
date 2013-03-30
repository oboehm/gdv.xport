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

package gdv.xport.satz.feld.sparte50;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 211, Sparte 50.
 *
 * @author oliver
 * @since 06.04.11
 */
public enum Feld211 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 50,
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /**
     * Neupreis in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 43
    )
    NEUPREIS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Mehrwert in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 55
    )
    MEHRWERT_IN_WAEHRUNGSEINHEITEN,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 67
    )
    REFERENZNUMMER,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 74
    )
    LFD_NUMMER,

    /**
     * Personen-/Kundennummer des Versicherers.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 78
    )
    KUNDENNR_VERSICHERER,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 161,
            byteAdresse = 95
    )
    LEERSTELLEN,

    /**
     * Satznummer.
     */
    @FeldInfo(
          teildatensatz = 1,
          nr = 33,
          type = Zeichen.class,
          anzahlBytes = 1,
          byteAdresse = 256
    )
    SATZNUMMER1;

}
