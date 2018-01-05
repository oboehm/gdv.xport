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

package gdv.xport.satz.feld.sparte70;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 70.
 *
 * @author oliver
 * @since 07.04.11
 */
public enum Feld220 {

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
    RISIKOGRUPPE_RISIKOART2,

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
     * Vertragsstatus.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 54
    )
    VERTRAGSSTATUS,

    /**
     * Beginn.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 55
    )
    BEGINN,

    /**
     * Ausschluss.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 63
    )
    AUSSCHLUSS,

    /**
     * Aenderungsdatum.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 71
    )
    AENDERUNGSDAT,

    /**
     * Zahlungsweise.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 79
    )
    ZAHLUNGSWEISE,

    /**
     * Hauptfaelligkeit.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 80
    )
    HAUPTFAELLIGKEIT,

    /**
     * Waehrungsschluessel.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 88
    )
    WAEHRUNGSSCHLUESSEL,

    /**
     * Beitrag in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 91
    )
    BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Risikotext.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 103
    )
    RISIKOTEXT,

    /**
     * Tarifierungsmerkmal Laufzeit.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 133
    )
    TARIFIERUNGSMERKMAL_LAUFZEIT,

    /**
     * voraussichtliches Ende.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 135
    )
    VORAUSSICHTLICHES_ENDE,

    /**
     * Selbstbeteiligung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 143
    )
    SELBSTBETEILIGUNG,

    /**
     * Selbstbeteiligung in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 144
    )
    SELBSTBETEILIGUNG_IN_PROZENT,

    /**
     * Selbstbeteiligung in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 149
    )
    SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitragsangleichungsklausel.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 161
    )
    BEITRAGSANGLEICHUNGSKLAUSEL,

    /**
     * Datum der letzten Beitragsangleichung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 162
    )
    DAT_LETZTEN_BEITRAGSANGLEICHUNG,

    /**
     * Beitragsklasse.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 170
    )
    BEITRAGSKLASSE,

    /**
     * Berufsgruppeneinteilung im Industrie-Straf-RS.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 171
    )
    BERUFSGRUPPENEINTEILUNG,

    /**
     * Beitragsregulierung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 175
    )
    BEITRAGSREGULIERUNG,

    /**
     * Deckungssumme in Tausend Waehrungseinheiten.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 176
    )
    DECKUNGSSUMME_IN_TSD_WAEHRUNGSEINHEITEN,

    /**
     * Risikoeinheit-1.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = NumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 185
    )
    RISKIOEINHEIT1,

    /**
     * Risikoeinheit-2.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = NumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 192
    )
    RISKIOEINHEIT2,

    /**
     * ARB (Allgemeine Bedingungen fuer die Rechtschutzvers.).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 199
    )
    ARB,

    /**
     * Sondervereinbarungen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 203
    )
    SONDERVEREINBARUNGEN,

    /**
     * Tarifjahr.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 204
    )
    TARIFJAHR,

    /**
     * Amtliches Kennzeichen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 36,
            type = AlphaNumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 208
    )
    AMTL_KENNZEICHEN,

    /**
     * Wartezeit.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 37,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 220
    )
    WARTEZEIT,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 38,
            type = AlphaNumFeld.class,
            anzahlBytes = 36,
            byteAdresse = 221
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
    RISIKOGRUPPE_RISIKOART1,

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
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 53
    )
    SATZNUMMER2,

    /**
     * Zuschlag-1 in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 54
    )
    ZUSCHLAG1_IN_PROZENT,

    /**
     * Zuschlag-1 in Waehrungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 59
    )
    ZUSCHLAG1_IN_WAEHRUNGSEINHEITEN,

    /**
     * Zuschlag-2 in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 65
    )
    ZUSCHLAG2_IN_PROZENT,

    /**
     * Zuschlag-2 in Waehrungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 70
    )
    ZUSCHLAG2_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlag-1 in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 76
    )
    ABSCHLAG1_IN_PROZENT,

    /**
     * Abschlag-1 in Waehrungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 81
    )
    ABSCHLAG1_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlag-2 in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 87
    )
    ABSCHLAG2_IN_PROZENT,

    /**
     * Abschlag-2 in Waehrungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 92
    )
    ABSCHLAG2_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlag-3 in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 19,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 98
    )
    ABSCHLAG3_IN_PROZENT,

    /**
     * Abschlag-3 in Waehrungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 20,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 103
    )
    ABSCHLAG3_IN_WAEHRUNGSEINHEITEN,

    /**
     * Versichertes Objekt.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 80,
            byteAdresse = 109
    )
    VERSICHERTES_OBJEKT,

    /**
     * Mitversicherte Person Familienname.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 189
    )
    MITVERSICHERTE_PERSON_FAMILIENNAME,

    /**
     * Mitversicherte Person Vorname.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 23,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 219
    )
    MITVERSICHERTE_PERSON_VORNAME,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 24,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 249
    )
    REFERENZNUMMER,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    LEERSTELLEN2;

}
