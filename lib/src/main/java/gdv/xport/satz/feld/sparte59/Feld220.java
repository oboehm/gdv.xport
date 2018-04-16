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
 * (c)reated 07.04.11 by oliver
 */

package gdv.xport.satz.feld.sparte59;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 59.
 *
 * @author Ken Schosinsky
 * @since 16.04.2018
 */
public enum Feld220 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /**
     * Gepäck-Beginn.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 43
    )
    GEPAECK_BEGINN,

    /**
     * Gepäck-Ausschluss.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 51
    )
    GEPAECK_AUSSCHLUSS,

    /**
     * Gepäck-Änderungsdatum.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 59
    )
    GEPAECK_AENDERUNGSDAT,

    /**
     * Gepäckversicherungssumme in Tausend Währungseinheiten.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 67
    )
    GEPAECKVERSICHERUNGSSUMME_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Gepäck-Beitrag in Währungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 8,
            byteAdresse = 71
    )
    GEPAECK_BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Gueltige AKB.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Datum.class,
            anzahlBytes = 4,
            byteAdresse = 79
    )
    GUELTIGE_AKB,

    /**
     * Provision (2,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 83
    )
    PROVISION,

    /**
     * Kennzeichen fuer abweichende Provision.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 87
    )
    KENNZEICHEN_FUER_ABWEICHENDE_PROVISION,

    /**
     * Tarifbeitrag 100 % für Kraftfahrt-Gepäckvers. in Währungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 8,
            byteAdresse = 88
    )
    TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_GEPAECKVERS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 96
    )
    REFERENZNUMMER,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 103
    )
    LFD_NUMMER,

    /**
     * Tarif.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 107
    )
    TARIF,

    /**
     * Personennummer / lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 117
    )
    PERSONENNUMMER_LFD_NUMMER,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 122,
            byteAdresse = 134
    )
    LEERSTELLEN1,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER1,

    /////   Teildatensatz 2   /////////////////////////////////////////////////

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
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 63
    )
    VERSICHERTE_GEFAHREN,

    /**
     * Geltungsbereich.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 93
    )
    GELTUNGSBEREICH,

    /**
     * Geltungsbereicheinschraenkung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 123
    )
    GELTUNGSBEREICHEINSCHRAENKUNG,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 103,
            byteAdresse = 153
    )
    LEERSTELLEN2,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER2
   

}
