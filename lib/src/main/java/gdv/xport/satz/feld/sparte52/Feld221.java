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

package gdv.xport.satz.feld.sparte52;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 221, Sparte 52.
 *
 * @author oliver
 * @since 14.04.11
 */
public enum Feld221 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /**
     * KFV-Beitrag in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 43
    )
    KFV_BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * KFV-Zuschlaege in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 55
    )
    KFV_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * KFV-Abschlaege in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 67
    )
    KFV_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Frei vereinbarte Selbstbeteiligung in Waehrungseinheiten fuer Vollkasko (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 79
    )
    FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_VOLLKASKO,

    /**
     * Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugvoll in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 91
    )
    TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGVOLL_IN_WAEHRUNGSEINHEITEN,

    /**
     * Frei vereinbarte Selbstbeteiligung in Waehrungseinheiten fuer Teilkasko im Rahmen der Vollkasko (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 103
    )
    FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 115
    )
    REFERENZNUMMER,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 122
    )
    LFD_NUMMER,

    /**
     * Personennummer / lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 126
    )
    PERSONENNUMMER_LFD_NUMMER,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER;

}
