/*
 * Copyright (c) 2011 by agentes
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

package gdv.xport.satz.feld.sparte30;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.feld.*;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 210, Sparte 30.
 * 
 * @author oliver
 * @since 0.6
 */
public enum Feld210 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /**
     * Vertragsstatus.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 43
    )
    VERTRAGSSTATUS,

    /**
     * Beginn.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 44
    )
    BEGINN,

    /**
     * Ausschluss.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = NumFeld.class,
            anzahlBytes = 8,
            byteAdresse = 52
    )
    AUSSCHLUSS,

    /**
     * Aenderungsdatum.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 60
    )
    AENDERUNGSDATUM,

    /**
     * Allgemeine Versicherungsbedingungen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Datum.class,
            anzahlBytes = 4,
            byteAdresse = 68
    )
    ALLGEMEINE_VERSICHERUNGSBEDINGUNGEN,

    /**
     * Sonderbedingungen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 72
    )
    SONDERBEDINGUNGEN,

    /**
     * Beitragsrueckgewaehr.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 73
    )
    BEITRAGSRUECKGEWAEHR,

    /**
     * Dynamik.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 74
    )
    DYNAMIK,

    /**
     * Dynamik in % (2,3 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = NumFeld.class,
            nachkommaStellen = 3,
            anzahlBytes = 5,
            byteAdresse = 75
    )
    DYNAMIK_IN_PROZENT,

    /**
     * letzte Erhoehung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 80
    )
    LETZTE_ERHOEHUNG,

    /**
     * naechste Erhoehung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 86
    )
    NAECHSTE_ERHOEHUNG,

    /**
     * Beitragsregulierung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 92
    )
    BEITRAGSREGULIERUNG,

    /**
     * Waehrungsschluessel.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 93
    )
    WAEHRUNGSSCHLUESSEL,

    /**
     * Zuschlagsbetrag in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 96
    )
    ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlagsbetrag in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 108
    )
    ABSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Gesamtbeitrag in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 120
    )
    GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlussprovision (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 132
    )
    ABSCHLUSSPROVISION,

    /**
     * Folgeprovision (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 137
    )
    FOLGEPROVISION,

    /**
     * Kennzeichen fuer abweichende Abschlussprovision.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 142
    )
    KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION,

    /**
     * Kennzeichen fuer abweichende Folgeprovision.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 143
    )
    KENNZEICHEN_ABWEICHENDE_FOLGEPROVISION,

    /**
     * Restlaufzeit des Vertrages.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 144
    )
    RESTLAUFZEIT_VERTRAG,

    /**
     * Laufzeitrabatt in % (2,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 146
    )
    LAUFZEITRABATT_IN_PROZENT,

    /**
     * Tarifbezeichnung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 150
    )
    TARIFBEZEICHNUNG,

    /**
     * Erhoehungsart Dynamik.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 180
    )
    ERHOEHUNGSART_DYNAMIK,

    /**
     * Referenz-Versicherungsscheinnummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 181
    )
    REFERENZ_VERSICHERUNGSSCHEINNUMMER,

    /**
     * Weitere Referenznummern.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 198
    )
    WEITERE_REFERENZNUMMERN,

    /**
     * Produktform.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 199
    )
    PRODUKTFORM,

    /**
     * Produktform gueltig ab.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 204
    )
    PRODUKTFORM_GUELTIG_AB,

    /**
     * Produktname.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 36,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 210
    )
    PRODUKTNAME,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 37,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 230
    )
    REFERENZNUMMER,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 38,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER;

}
