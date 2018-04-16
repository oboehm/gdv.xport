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

package gdv.xport.satz.feld.sparte59;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 221, Sparte 59.
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
     * Gepäckversicherungssumme in Tausend Währungseinheiten.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 43
    )
    GEPAECKVERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Gepäck-Beitrag in Währungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 57
    )
    GEPAECK_BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Tarifbeitrag 100 % für Kraftfahrt-Gepäckvers. in Währungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 69
    )
    TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_GEPAECKVERS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 81
    )
    REFERENZNUMMER,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 88
    )
    LFD_NUMMER,

    /**
     * Personennummer / lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 92
    )
    PERSONENNUMMER_LFD_NUMMER,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 147,
            byteAdresse = 109
    )
    LEERSTELLEN1,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER;

}
