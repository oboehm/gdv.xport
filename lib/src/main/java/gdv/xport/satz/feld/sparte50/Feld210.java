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
 * (c)reated 05.04.11 by oliver
 */

package gdv.xport.satz.feld.sparte50;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 210, Sparte 50.
 *
 * @author oliver
 * @since 05.04.11
 */
public enum Feld210 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /**
     * Wagniskennziffer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 43
    )
    WAGNISKENNZIFFER,

    /**
     * Staerke (3,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 4,
            byteAdresse = 46
    )
    STAERKE,

    /**
     * Herstellername.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 15,
            byteAdresse = 50
    )
    HERSTELLERNAME,

    /**
     * Modellname.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 25,
            byteAdresse = 65
    )
    MODELLNAME,

    /**
     * Hersteller-Schluessel-Nr.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 90
    )
    HERSTELLER_SCHLUESSEL_NR,

    /**
     * Typschluessel-Nr.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 94
    )
    TYPSCHLUESSEL_NR,

    /**
     * Fahrzeugidentifizierungsnummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 97
    )
    FAHRZEUGIDENTIFIZIERUNGSNUMMER,

    /**
     * Amtl. Kennzeichen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 114
    )
    AMTL_KENNZEICHEN,

    /**
     * Erstzulassung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 126
    )
    ERSTZULASSUNG,

    /**
     * Neupreis in Waehrungseinheiten.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = NumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 134
    )
    NEUPREIS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Mehrwert in Waehrungseinheiten.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 141
    )
    MEHRWERT_IN_WAEHRUNGSEINHEITEN,

    /**
     * Kennung fuer ABS-Rabatt.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 147
    )
    KENNUNG_FUER_ABS_RABATT,

    /**
     * Flottenkennzeichen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 148
    )
    FLOTTENKENNZEICHEN,

    /**
     * Waehrungsschluessel.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 149
    )
    WAEHRUNGSSCHLUESSEL,

    /**
     * Gesamtbeitrag in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 152
    )
    GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Sicherungsschein.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 164
    )
    SICHERUNGSSCHEIN,

    /**
     * Sonderbedingungen / Klauseln.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 165
    )
    SONDERBEDINGUNGEN_KLAUSELN,

    /**
     * Sicherungseinrichtung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 166
    )
    SICHERUNGSEINRICHTUNG,

    /**
     * Klartext Sicherungseinrichtung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 167
    )
    KLARTEXT_SICHERUNGSEINRICHTUNG,

    /**
     * Schluessel Sicherungseinrichtung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 197
    )
    SCHLUESSEL_SICHERUNGSEINRICHTUNG,

    /**
     * Baustein-Gesamtbeitrag 1 in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 200
    )
    BAUSTEIN_GESAMTBEITRAG_1_IN_WAEHRUNGSEINHEITEN,

    /**
     * Baustein-Gesamtbeitrag 2 in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 212
    )
    BAUSTEIN_GESAMTBEITRAG_2_IN_WAEHRUNGSEINHEITEN,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 224
    )
    REFERENZNUMMER,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 231
    )
    LFD_NUMMER,

    /**
     * Personen-/Kundennummer des Versicherers.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 235
    )
    KUNDENNR_VERSICHERER,

    /**
     * Saisonkennzeichen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 252
    )
    SAISONKENNZEICHEN,

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

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 2,
            type = Feld1bis7.class
    )
    INTRO2,

    /**
     * Produktform.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 43
    )
    PRODUKTFORM,

    /**
     * Produktform gueltig ab.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 48
    )
    PRODUKTFORM_GUELTIG_AB,

    /**
     * Fahrzeugart.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 54
    )
    FAHRZEUGART,

    /**
     * Art des amtlichen Kennzeichens.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 84
    )
    ART_DES_AMTLICHEN_KENNZEICHENS,

    /**
     * Land des amtl. Kennzeichens.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 86
    )
    LAND_DES_AMTL_KENNZEICHENS,

    /**
     * Baujahr.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = NumFeld.class,
            anzahlBytes = 8,
            byteAdresse = 89
    )
    BAUJAHR,

    /**
     * Erste Zulassung auf den VN.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 97
    )
    ERSTE_ZULASSUNG_AUF_DEN_VN,

    /**
     * Art der Zulassung beim Vorbesitzer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 105
    )
    ART_DER_ZULASSUNG_BEIM_VORBESITZER,

    /**
     * Anzahl der Vorbesitzer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 107
    )
    ANZAHL_DER_VORBESITZER,

    /**
     * Kaufpreis (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 109
    )
    KAUFPREIS,

    /**
     * Mehrwertgrund.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 121
    )
    MEHRWERTGRUND,

    /**
     * Lfd. Personennummer des Sicherungsglaeubigers.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 19,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 151
    )
    LFD_PERSONENNUMMER_DES_SICHERUNGSGLAEUBIGERS,

    /**
     * Jaehrliche Fahrleistung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 20,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 157
    )
    JAEHRLICHE_FAHRLEISTUNG,

    /**
     * Garage.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 163
    )
    GARAGE,

    /**
     * Nutzungsart.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 165
    )
    NUTZUNGSART,

    /**
     * Eigentumsverhaeltnis (Fahrzeug).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 23,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 167
    )
    EIGENTUMSVERHAELTNIS_FAHRZEUG,

    /**
     * Wohneigentum.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 24,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 168
    )
    WOHNEIGENTUM,

    /**
     * Produktname.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 169
    )
    PRODUKTNAME,

    /**
     * Kreisgemeindeschluessel.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 26,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 189
    )
    KREISGEMEINDESCHLUESSEL,

    /**
     * Kreisgemeindeschluessel Zusatzinformation.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 27,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 194
    )
    KREISGEMEINDESCHLUESSEL_ZUSATZINFORMATION,

    /**
     * Beginn Versicherungsschutz.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 28,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 195
    )
    BEGINN_VERSICHERUNGSSCHUTZ,

    /**
     * Endedatum des Versicherungsschutzes bei roten Kennzeichen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 29,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 203
    )
    ENDEDATUM_BEI_ROTEN_KENNZEICHEN,

    /**
     * Gueltigkeitsdauer in Tagen bei Kurzzeitkennzeichen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 30,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 211
    )
    GUELTIGKEITSDAUER_IN_TAGEN_BEI_KURZZEITKENNZEICHEN,

    /**
     * eVB-Nummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 213
    )
    EVB_NUMMER,

    /**
     * Aufbauart.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 32,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 220
    )
    AUFBAUART,

    /**
     * Gefahrgut.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 33,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 222
    )
    GEFAHRGUT,

    /**
     * Gesamtmasse.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 34,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 223
    )
    GESAMTMASSE,

    /**
     * Staerkeeinheit.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 35,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 229
    )
    STAERKEEINHEIT,

    /**
     * Wechselkennzeichen W-AKZ.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 36,
            type = Zeichen.class,
            byteAdresse = 230
    )
    WECHSELKENNZEICHEN_W_AKZ,

    /**
     * Rolle W-AKZ.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 37,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 231
    )
    ROLLE_W_AKZ,

    /**
     * Staerkeeinheit.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 38,
            type = AlphaNumFeld.class,
            anzahlBytes = 23,
            byteAdresse = 233
    )
    LEERSTELLEN,

    /**
     * Satznummer.
     */
    @FeldInfo(
          teildatensatz = 2,
          nr = 39,
          type = Zeichen.class,
          anzahlBytes = 1,
          byteAdresse = 256
    )
    SATZNUMMER2

}
