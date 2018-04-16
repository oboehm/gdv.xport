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

package gdv.xport.satz.feld.sparte55;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 221, Sparte 55.
 *
 * @author Ken Schosinsky
 * @since 16.04.2018
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
     * Selbstbehalt in Währungseinheiten.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 43
    )
    SELBSTBEHALT_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag in Waehrungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 55
    )
    BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Baustein-Zuschläge in Währungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 67
    )
    BAUSTEIN_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Baustein-Abschläge in Währungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 79
    )
    BAUSTEIN_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN,
    
    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 91
    )
    REFERENZNUMMER,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 98
    )
    LFD_NUMMER,

    /**
     * Personennummer / lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 102
    )
    PERSONENNUMMER_LFD_NUMMER,
    
    /**
     * Bezug zur Teilsparte.
     * 051=KH, 052=KFV, 053=KFT, 054=KU, 059=KG
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 119
    )
    BEZUG_ZUR_TEILSPARTE,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 134,
            byteAdresse = 122
    )
    LEERSTELLEN1,

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
