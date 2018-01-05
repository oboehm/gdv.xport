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
 * (c)reated 14.04.11 by oliver
 */

package gdv.xport.satz.feld.sparte70;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 221, Sparte 70.
 *
 * @author oliver
 * @since 14.04.11
 */
public enum Feld221 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 70,
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /**
     * Risikogruppe / Risikoart.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 43
    )
    RISIKOGRUPPE_RISIKOART1,

    /**
     * Laufende Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 48
    )
    LFD_NUMMER1,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 53
    )
    SATZNUMMER1,

    /**
     * Deckungssumme in Waehrungseinheiten (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 54
    )
    DECKUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 189,
            byteAdresse = 68
    )
    LEERSTELLEN1,

    /////   Teildatensatz 2   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 70,
            teildatensatz = 2,
            type = Feld1bis7.class
    )
    INTRO2,

   /**
     * Risikogruppe / Risikoart.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 43
    )
    RISIKOGRUPPE_RISIKOART2,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 48
    )
    LFD_NUMMER2,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 53
    )
    SATZNUMMER2,

    /**
     * Zuschlag-1 in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 54
    )
    ZUSCHLAG1_IN_WAEHRUNGSEINHEITEN,

    /**
     * Zuschlag-2 in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 66
    )
    ZUSCHLAG2_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlag-1 in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 78
    )
    ABSCHLAG1_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlag-2 in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 90
    )
    ABSCHLAG2_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlag-3 in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 102
    )
    ABSCHLAG3_IN_WAEHRUNGSEINHEITEN,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 114
    )
    REFERENZNUMMER,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 136,
            byteAdresse = 121
    )
    LEERSTELLEN2;

}
