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

package gdv.xport.satz.feld.sparte30;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 210, Sparte 30.
 * "Unfall" (Satzart 0210)
 *
 * @author Ralf
 * @since 20.02.2013
 */
public enum Feld210 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 30,
            teildatensatz = 1,
            type = Feld1bis7.class)
    INTRO1,

    /**
     * Vertragsstatus.<br/>
     * 1 = lebend<br/>
     * 2 = lebend / kurzfristig / unterjährig<br/>
     * 3 = ruhend / Anwartschaft<br/>
     * 4 = storniert<br/>
     * 5 = ruhend / beitragsfrei<br/>
     * 6 = beitragsfrei<br/>
     * 7 = beitragsfrei durch Leistung<br/>
     * 8 = Beitragsstundung<br/>
     * 9 = Sonstiges
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 43)
    VERTRAGSSTATUS,

    /**
     * Beginn.<br/>
     * Beginn der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein, muss
     * "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 44)
    BEGINN,

    /**
     * Ausschluss.<br/>
     * Ausschluss der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 52)
    AUSSCHLUSS,

    /**
     * Änderungsdatum.<br/>
     * Termin, zu dem der Vertragszustand wirksam wird/wurde (Gültig-ab- /
     * Wirksam-ab-Datum).<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 60)
    AENDERUNGSDATUM,

    /**
     * Allgemeine Versicherungsbedingungen.<br/>
     * Inkraftsetzung bei VU. Monat / Jahr (MMJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Datum.class,
            anzahlBytes = 4,
            byteAdresse = 68)
    ALLGEMEINE_VERSICHERUNGSBEDINGUNGEN,

    /**
     * Sonderbedingungen.<br/>
     * Vertragsbezogene oder unternehmensindividuelle Bedingungen<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 72)
    SONDERBEDINGUNGEN,

    /**
     * Beitragsrückgewähr.<br/>
     * Einmalige Beitragsrückgewähr bei Ablauf des Vertrages<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 73)
    BEITRAGSRUECKGEWAEHR,

    /**
     * Dynamik.<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 74)
    DYNAMIK,

    /**
     * Dynamik in %.<br/>
     * (2,3 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 75)
    DYNAMIK_IN_PROZENT,

    /**
     * Letzte Erhöhung.<br/>
     * Monat / Jahr (MMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 80)
    LETZTE_ERHOEHUNG,

    /**
     * Nächste Erhöhung.<br/>
     * Monat / Jahr (MMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 86)
    NAECHSTE_ERHOEHUNG,

    /**
     * Beitragsregulierung.<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 92)
    BEITRAGSREGULIERUNG,

    /**
     * Währungsschlüssel.<br/>
     * ISO-Code, siehe Anlage 3
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 93)
    WAEHRUNGSSCHLUESSEL,

    /**
     * Zuschlagsbetrag in Währungseinheiten.<br/>
     * kumulierter Zuschlagsbetrag auf Vertragsebene<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 96)
    ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlagsbetrag in Währungseinheiten.<br/>
     * kumulierter Abschlagsbetrag auf Vertragsebene<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 108)
    ABSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Gesamtbeitrag in Währungseinheiten.<br/>
     * Gesamtbeitrag unter Berücksichtigung aller Zu- und Abschläge gem.
     * Zahlungsweise ohne Gebühr und Steuer.<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 120)
    GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlussprovision.<br/>
     * Für den betreffenden Vertrag vereinbarter Provisionssatz<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 132)
    ABSCHLUSSPROVISION,

    /**
     * Folgeprovision<br/>
     * s. Erläuterung für Abschlussprovision.<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 137)
    FOLGEPROVISION,

    /**
     * Kennzeichen für abweichende Abschlussprovision.<br/>
     * Kennzeichen, dass für den betreffenden Vertrag der Provisionssatz von
     * den allgemeinen Provisionsvereinbarungen abweicht<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 142)
    KENNZEICHEN_FUER_ABWEICHENDE_ABSCHLUSSPROVISION,

    /**
     * Kennzeichen für abweichende Folgeprovision.<br/>
     * s. Erläuterung: Kennzeichen für abweichende Abschlussprovision<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 143)
    KENNZEICHEN_FUER_ABWEICHENDE_FOLGEPROVISION,

    /**
     * Restlaufzeit des Vertrages.<br/>
     * gemäß Ablauf des Vertrages Grundlage für die Berechnung von Provision
     * (JJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 144)
    RESTLAUFZEIT_DES_VERTRAGES,

    /**
     * Laufzeitrabatt in %.<br/>
     * gemäß Laufzeitrabatt VAG-Novelle 01.07.1991<br/>
     * (2,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 146)
    LAUFZEITRABATT_IN_PROZENT,

    /**
     * Tarifbezeichnung.<br/>
     * Kurzbezeichnung des Tarifes
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 150)
    TARIFBEZEICHNUNG,

    /**
     * Erhöhungsart Dynamik.<br/>
     * siehe Anlage 72
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 180)
    ERHOEHUNGSART_DYNAMIK,

    /**
     * Referenz-Versicherungsscheinnumme.<br/>
     * Zusätzliche Versicherungsscheinnummer, wenn Teile des
     * Versicherungsproduktes / -vertrages sich in anderen
     * Versicherungsscheinnummern wiederfinden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 181)
    REFERENZ_VERSICHERUNGSSCHEINNUMME,

    /**
     * Weitere Referenznummern.<br/>
     * Weitere, nicht abbildbare Referenznummern vorhanden<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 198)
    WEITERE_REFERENZNUMMERN,

    /**
     * Produktform.<br/>
     * Unternehmensindividuelle Produktkürzel/-schlüssel
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 199)
    PRODUKTFORM,

    /**
     * Produktform gültig ab.<br/>
     * Versionsdatum der Produktform. Monat / Jahr (MMJJJJ).<br/>
     * Wenn kein Versionsdatum vorhanden, muss das Datum der Produkteinführung
     * geliefert werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 204)
    PRODUKTFORM_GUELTIG_AB,

    /**
     * Produktname.<br/>
     * Produktname
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 36,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 210)
    PRODUKTNAME,

    /**
     * Referenznummer.<br/>
     *
     * Wenn in einem gebündelten Vertrag (Bündelungskennzeichen = 1) mehrere
     * gleiche Sparten unter der selben Versicherungsscheinnummer gebündelt
     * werden, müssen diese im Feld Referenznummer unterschieden werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 37,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 230)
    REFERENZNUMMER,

    /**
     * Besondere Vereinbarungen.<br/>
     *
     * Besondere Vereinbarungen gemäß Antrag<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 38,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 237)
    BESONDERE_VEREINBARUNGEN,

    /**
     * Direktanspruch.<br/>
     *
     * Direkter Leistungsanspruch der versicherten Personen in der Gruppenunfallversicherung<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 39,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 238)
    DIREKTANSPRUCH,


    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 40,
            type = AlphaNumFeld.class,
            anzahlBytes = 18,
            byteAdresse = 239)
    LEERSTELLEN

}
