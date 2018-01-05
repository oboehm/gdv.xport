/*
 * Copyright (c) 2012 by Oli B.
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
 * (c)reated 17.07.2012 by Oli B. (boehm@javatux.de)
 */

package gdv.xport.satz.feld.common;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;

/**
 * Teil 2 des Datensatzes 220 fuer die Sparten 51, 52 und 53 ist identisch
 * aufgebaut. Dieser Teil wurde in diese Klasse herausgezogen.
 *
 * @author oliver
 * @since 0.7.1 (17.07.2012)
 */
public enum Satz220Teil2 {

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 2,
            type = Feld1bis7.class
    )
    INTRO2,

    /**
     * Produktkennung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 43
    )
    PRODUKTKENNUNG,

    /**
     * Versicherte Gefahren.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 63
    )
    VERSICHERTE_GEFAHREN,

    /**
     * Selbstbeteiligung in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 93
    )
    SELBSTBETEILIGUNG_IN_PROZENT,

    /**
     * Selbstbeteiligung in WE (mind.) (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 98
    )
    SELBSTBETEILIGUNG_IN_WE_MIND,

    /**
     * Selbstbeteiligung in WE (max.) (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 110
    )
    SELBSTBETEILIGUNG_IN_WE_MAX,

    /**
     * Geltungsbereich.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 122
    )
    GELTUNGSBEREICH,

    /**
     * Geltungsbereicheinschraenkung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 152
    )
    GELTUNGSBEREICHEINSCHRAENKUNG,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER2;

}
