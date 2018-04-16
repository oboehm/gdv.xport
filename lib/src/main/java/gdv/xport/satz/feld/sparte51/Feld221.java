/*
 * Copyright (c) 2011, 2012 by aosd.de
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
 * (c)reated 14.04.11 by oliver
 */

package gdv.xport.satz.feld.sparte51;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 221, Sparte 51.
 *
 * @author oliver
 * @since 14.04.11
 */
public enum Feld221 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 51,
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /**
     * KH-Deckungssummen in Waehrungseinheiten Teil 1 (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 43
    )
    KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL1,

    /**
     * KH-Deckungssummen in Waehrungseinheiten Teil 2 (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 57
    )
    KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL2,

    /**
     * KH-Deckungssummen in Waehrungseinheiten Teil 3 (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 71
    )
    KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL3,

    /**
     * KH-Beitrag in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 85
    )
    KH_BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * KH-Zuschlaege in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 97
    )
    KH_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * KH-Abschlaege in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 109
    )
    KH_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Tarifbeitrag 100 % fuer Kraftfahrt-Haftpflicht in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 121
    )
    TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_HAFTPFLICHT_IN_WAEHRUNGSEINHEITEN,

    /**
     * Frei vereinbarte Selbstbeteiligung in Waehrungseinheiten fuer KH (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 133
    )
    FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_KH,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 145
    )
    REFERENZNUMMER,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 152
    )
    LFD_NUMMER,

    /**
     * Personennummer / lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 156
    )
    PERSONENNUMMER_LFD_NUMMER,

    /**
     * Leerstellen .
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 83,
            byteAdresse = 173
    )
    LEERSTELLEN,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER;

}
