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

package gdv.xport.satz.feld.sparte51;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.*;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 51.
 *
 * @author oliver
 * @since 07.04.11
 */
public enum Feld220 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 51,
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /**
     * KH-Beginn.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 43
    )
    KH_BEGINN,

    /**
     * KH-Ausschluss.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 51
    )
    KH_AUSSCHLUSS,

    /**
     * KH-aenderungsdatum.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 59
    )
    KH_AENDERUNGSDAT,

    /**
     * KH-Tarifgruppe.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 67
    )
    KH_TARIFGRUPPE,

    /**
     * KH Deckungsart.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 71
    )
    KH_DECKUNGSART,

    /**
     * KH-Deckungssummen Personenschaeden.
     */
    @FeldInfo(
            bezeichnung = "KH-Deckungssummen in Waehrungseinheiten Teil 1",
            teildatensatz = 1,
            nr = 13,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 73
    )
    KH_DECKUNGSSUMMEN_PERSONENSCHAEDEN,

    /**
     * KH-Deckungssummen Sachschaeden.
     */
    @FeldInfo(
            bezeichnung = "KH-Deckungssummen in Waehrungseinheiten Teil 2",
            teildatensatz = 1,
            nr = 14,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 82
    )
    KH_DECKUNGSSUMMEN_SACHSCHAEDEN,

    /**
     * KH-Deckungssummen Vermoegensschaeden.
     */
    @FeldInfo(
            bezeichnung = "KH-Deckungssummen in Waehrungseinheiten Teil 3",
            teildatensatz = 1,
            nr = 15,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 91
    )
    KH_DECKUNGSSUMMEN_VERMOEGENSCHAEDEN,

    /**
     * KH-RGJ.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 100
    )
    KH_RGJ,

    /**
     * KH-SF/S-Klasse.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 102
    )
    KH_SF_S_KLASSE,

    /**
     * KH-Beitragssaetze.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 107
    )
    KH_BEITRAGSSAETZE,

    /**
     * KH-Beitrag in Waehrungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 8,
            byteAdresse = 110
    )
    KH_BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * KH-Zuschlaege in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 118
    )
    KH_ZUSCHLAEGE_IN_PROZENT,

    /**
     * KH-Abschlaege in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 123
    )
    KH_ABSCHLAEGE_IN_PROZENT,

    /**
     * KH-Zuschlaege in Waehrungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 128
    )
    KH_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * KH-Abschlaege in Waehrungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 134
    )
    KH_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Flottenrabatt in % (2,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 3,
            byteAdresse = 140
    )
    FLOTTENRABATT_IN_PROZENT,

    /**
     * Gueltige AKB.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = Datum.class,
            anzahlBytes = 4,
            byteAdresse = 143
    )
    GUELTIGE_AKB,

    /**
     * Provision (2,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 147
    )
    PROVISION,

    /**
     * KH-Schaeden aus Rueckstufung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 151
    )
    KH_SCHAEDEN_AUS_RUECKSTUFUNG,

    /**
     * Kennzeichen fuer abweichende Provision.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 153
    )
    KENNZEICHEN_FUER_ABWEICHENDE_PROVISION,

    /**
     * Tarifbeitrag 100 % fuer Kraftfahrt-Haftpflicht in Waehrungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 8,
            byteAdresse = 154
    )
    TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_HAFTPFLICHT_IN_WAEHRUNGSEINHEITEN,

    /**
     * Schutzbrief /Verkehrsservice.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 162
    )
    SCHUTZBRIEF_VERKEHRSSERVICE,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 163
    )
    REFERENZNUMMER,

    /**
     * Frei vereinbarte Selbstbeteiligung in Waehrungseinheiten fuer KH.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = NumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 170
    )
    FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_KH,

    /**
     * Tyklasse KH.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 180
    )
    TYKLASSE_KH,

    /**
     * Tarif.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 183
    )
    TARIF,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 193
    )
    LFD_NUMMER,

    /**
     * Personennummer / lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 36,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 197
    )
    PERSONENNUMMER_LFD_NUMMER,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 37,
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
