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

package gdv.xport.satz.feld.sparte52;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;
import gdv.xport.satz.feld.common.Satz220Teil2;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 52.
 *
 * @author oliver
 * @since 07.04.11
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
     * KFV-Beginn.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 43
    )
    KFV_BEGINN,

    /**
     * KFV-Ausschluss.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 51
    )
    KFV_AUSSCHLUSS,

    /**
     * KFV-aenderungsdatum.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 59
    )
    KFV_AENDERUNGSDAT,

    /**
     * KFV-Tarifgruppe.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 67
    )
    KFV_TARIFGRUPPE,

    /**
     * KFV-Deckungsart.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 71
    )
    KFV_DECKUNGSART,

    /**
     * KFV-RGJ.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 73
    )
    KFV_RGJ,

    /**
     * KFV-SF/S-Klasse.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 75
    )
    KFV_SFS_KLASSE,

    /**
     * KFV-Beitragssatz.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 80
    )
    KFV_BEITRAGSSATZ,

    /**
     * KFV-Beitrag in Waehrungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 8,
            byteAdresse = 83
    )
    KFV_BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * KFV-Zuschlaege in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 91
    )
    KFV_ZUSCHLAEGE_IN_PROZENT,

    /**
     * KFV-Abschlaege in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 96
    )
    KFV_ABSCHLAEGE_IN_PROZENT,

    /**
     * KFV-Zuschlaege in Waehrungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 101
    )
    KFV_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * KFV-Abschlaege in Waehrungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 107
    )
    KFV_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Flottenrabatt in % (2,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 3,
            byteAdresse = 113
    )
    FLOTTENRABATT_IN_PROZENT,

    /**
     * Gueltige AKB.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = Datum.class,
            anzahlBytes = 4,
            byteAdresse = 116
    )
    GUELTIGE_AKB,

    /**
     * Provision (2,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 120
    )
    PROVISION,

    /**
     * KFV-Schaeden aus Rueckstufung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 124
    )
    KFV_SCHAEDEN_AUS_RUECKSTUFUNG,

    /**
     * Typklasse fuer KFV.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 126
    )
    TYPKLASSE_FUER_KFV,

    /**
     * Frei vereinbarte Selbstbeteiligung in Waehrungseinheiten fuer Vollkasko.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 128
    )
    FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_VOLLKASKO,

    /**
     * Kennzeichen fuer abweichende Provision.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 138
    )
    KENNZEICHEN_FUER_ABWEICHENDE_PROVISION,

    /**
     * Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugvoll in Waehrungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 8,
            byteAdresse = 139
    )
    TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGVOLL_IN_WAEHRUNGSEINHEITEN,

    /**
     * Kasko-Beginnjahr.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 147
    )
    KASKO_BEGINNJAHR,

    /**
     * Frei vereinbarte Selbstbeteiligung in Waehrungseinheiten fuer Teilkasko im Rahmen der Vollkasko.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = NumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 151
    )
    FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO_IM_RAHMEN_DER_VOLLKASKO,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 161
    )
    REFERENZNUMMER,

    /**
     * Tarif.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 168
    )
    TARIF,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 178
    )
    LFD_NUMMER,

    /**
     * Personennummer / lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 182
    )
    PERSONENNUMMER_LFD_NUMMER,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER1,

    /////   Teildatensatz 2   /////////////////////////////////////////////////

    /**
     * Teildatensatz 2.
     */
    @FelderInfo(type = Satz220Teil2.class)
    TEILDATENSATZ2;

}
