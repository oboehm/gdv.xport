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
     * Vertragsstatus.
     * 1 = lebend
     * 2 = lebend / kurzfristig / unterjährig
     * 3 = ruhend / Anwartschaft
     * 4 = storniert
     * 5 = ruhend / beitragsfrei
     * 6 = beitragsfrei
     * 7 = beitragsfrei durch Leistung
     * 8 = Beitragsstundung
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
     * Beginn.
     * Beginn der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein, muss
     * "00" geschlüsselt werden
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
     * Ausschluss.
     * Ausschluss der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschlüsselt werden
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
     * Änderungsdatum.
     * Termin, zu dem der Vertragszustand wirksam wird/wurde (Gültig-ab- /
     * Wirksam-ab-Datum).
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 60)
    AENDERUNGSDAT,

    /**
     * Allgemeine Versicherungsbedingungen.
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
     * Sonderbedingungen.
     * Vertragsbezogene oder unternehmensindividuelle Bedingungen
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
     * Beitragsrückgewähr.
     * Einmalige Beitragsrückgewähr bei Ablauf des Vertrages
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
     * Dynamik.
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
     * Dynamik in %.
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
     * Letzte Erhöhung.
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
     * Nächste Erhöhung.
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
     * Beitragsregulierung.
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
     * Währungsschlüssel.
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
     * Zuschlagsbetrag in Währungseinheiten.
     * kumulierter Zuschlagsbetrag auf Vertragsebene
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
     * Abschlagsbetrag in Währungseinheiten.
     * kumulierter Abschlagsbetrag auf Vertragsebene
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
     * Gesamtbeitrag in Währungseinheiten.
     * Gesamtbeitrag unter Berücksichtigung aller Zu- und Abschläge gem.
     * Zahlungsweise ohne Gebühr und Steuer.
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
     * Abschlussprovision.
     * Für den betreffenden Vertrag vereinbarter Provisionssatz
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
     * Folgeprovision
     * s. Erläuterung für Abschlussprovision.
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
     * Kennzeichen für abweichende Abschlussprovision.
     * Kennzeichen, dass für den betreffenden Vertrag der Provisionssatz von
     * den allgemeinen Provisionsvereinbarungen abweicht
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
     * Kennzeichen für abweichende Folgeprovision.
     * s. Erläuterung: Kennzeichen für abweichende Abschlussprovision
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
     * Restlaufzeit des Vertrages.
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
     * Laufzeitrabatt in %.
     * gemäß Laufzeitrabatt VAG-Novelle 01.07.1991
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
     * Tarifbezeichnung.
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
     * Erhöhungsart Dynamik.
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
     * Referenz-Versicherungsscheinnumme.
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
     * Weitere Referenznummern.
     * Weitere, nicht abbildbare Referenznummern vorhanden
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
     * Produktform.
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
     * Produktform gültig ab.
     * Versionsdatum der Produktform. Monat / Jahr (MMJJJJ).
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
     * Produktname.
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
     * Referenznummer.
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
     * Besondere Vereinbarungen.
     *
     * Besondere Vereinbarungen gemäß Antrag
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
     * Direktanspruch.
     *
     * Direkter Leistungsanspruch der versicherten Personen in der Gruppenunfallversicherung
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
