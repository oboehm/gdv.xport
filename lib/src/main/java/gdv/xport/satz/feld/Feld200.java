/*
 * Copyright (c) 2011, 2012 by aosd.de
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
 * (c)reated 06.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.feld;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 200
 * (Allgemeiner Vertragsteil).
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (06.03.2011)
 */
public enum Feld200 {

    /////   Allgemeiner Teil   ////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /////   Satzspezifischer Teil (Satz 1)  ///////////////////////////////////

    /**
     * Inkassoart.
     * <pre>
     * 1 = Vermittlerinkasso
     * 2 = Zentralinkasso, Direktes Inkasso
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 43
    )
    INKASSOART,

    /**
     * Beginn des Vertrages.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00"
     * geschluesselt werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 44
    )
    VERTRAGSBEGINN,

    /**
     * Der aktuelle Ablauf des Vertrages zum Zeitpunkt der Lieferung.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00"
     * geschluesselt werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 52
    )
    VERTRAGSABLAUF,

    /**
     * Die jeweils folgende Hauptfaelligkeit des Vertrages zum Zeitpunkt der
     * Lieferung.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00"
     * geschluesselt werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 60
    )
    HAUPTFAELLIGKEIT,

    /**
     * Zahlungsweise.
     * blank = Rechtschutz / Verkehrsservice / Kredit (In den Sparten
     * Rechtschutz und Verkehrsservice kann das Datenfeld "Zahlungsweise"
     * in der Satzart 0200 blank sein, die Zahlungsweise wird dann auf
     * Risikoebene in der Satzart 0210, spaetestens in Satzart 0220 angegeben.
     * Bei Einzel- und / oder Umsatzanmeldungen muss das Datenfeld
     * "Zahlungsweise" in der Satzart 0400 blank sein.)
     * siehe Anlage 14
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Zeichen.class,
            byteAdresse = 68
    )
    ZAHLUNGSWEISE,

    /**
     * Vertragsstatus.
     * siehe Anlage 24
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Zeichen.class,
            byteAdresse = 69
    )
    VERTRAGSSTATUS,

    /**
     * Abgang des Vertrages, Stornogrund.
     * siehe Anlage 5
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 70
    )
    ABGANGSGRUND,

    /**
     * Stornodatum.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00"
     * geschluesselt werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 72
    )
    ABGANGSDAT,

    /**
     * Aenderungsgrund.
     * 5 x 2 Stellen, siehe Anlage 6
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 80
    )
    AENDERUNGSGRUND,

    /**
     * Termin, zu dem der Vertragszustand wirksam wird/wurde
     * (Gueltig-ab- / Wirksam-ab-Datum).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 90
    )
    AENDERUNGSDAT,

    /**
     * Hinweis ob Vertrag 1 = Alleinbesitzend, 2 = Fuehrungsgeschaeft,
     * 3 = Beteiligung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = Zeichen.class,
            byteAdresse = 98
    )
    AFB,

    /**
     * Anteil in %, Fuehrungs- und Beteiligungsgeschaeft.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = NumFeld.class,
            nachkommaStellen = 3,
            anzahlBytes = 5,
            byteAdresse = 99
    )
    ANTEIL_IN_PROZENT,

    /**
     * Auftrags-Nummer des Vermittlers, vorlauefige Vertrags-Nummer des
     * Vermittlers, die er dem VU aufgibt, rechtsbuendig, mit Leerstellen
     * linksbuendig auffuellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 104
    )
    AUFTRAGSNR_VERMITTLER,

    /**
     * Waehrung, in der die Betragsfelder in diesem GDV-Satz geliefert werden.
     * ISO-CODE, siehe Anlage 3
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 113
    )
    WAEHRUNGSSCHLUESSEL,

    /**
     * Gesamtbeitrag unter Beruecksichtigung aller Zu- und Abschlaege ohne
     * Gebuehren und Versicherungs-Steuer gemaess Zahlungsweise.
     * Im Fuehrungs-/Beteiligungsgeschaeft 100%. In KV nicht gemaess der
     * Zahlungsweise, sondern Monats-Soll-Beitrag oder sonstiger
     * tarifspezifischer Beitrag (z. B. AS). In LV einschl. verrechnete
     * Ueberschussbeteiligung. Bei Kreditvers. nur: Kautionsversicherung-S,
     * Warenkreditversicherung-S, Vertrauensschadenversicherung
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = NumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 116
    )
    GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN,

    /**
     * Mehrzweckfeld. Z. B. Personalnummer, Kostenstelle, Fahrer des
     * Fahrzeuges, Kundennummer, Kredit = "Versicherungsschein-Unternummer"
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 128
    )
    MEHRZWECKFELD,

    /**
     * 0 = nein, 1 = ja.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = Zeichen.class,
            byteAdresse = 158
    )
    KENNZEICHEN_VERS_STEUER_FREI,

    /**
     * Personen-/Kundennummer des Versicherungsnehmers beim Versicherer,
     * rechtsbuendig, mit Leerstellen linksbuendig auffuellen, ohne
     * Sonderzeichen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 159
    )
    KUNDENNR_VERSICHERER,

    /**
     * Personen-/Kundennummer des Versicherungsnehmers beim Vermittler,
     * rechtsbuendig, mit Leerstellen linksbuendig auffuellen, ohne
     * Sonderzeichen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 176
    )
    KUNDENNR_VERMITTLER,

    /**
     * Nach EG-Richtlinien 01-07-1990 0 = nein, 1 = ja.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Zeichen.class,
            byteAdresse = 193
    )
    AUFSICHTSFREIER_VERTRAG,

    /**
     * Nach EG-Richtlinien 01-07-1990 0 = nein, 1 = ja.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = Zeichen.class,
            byteAdresse = 194
    )
    AUFTEILUNG_VERSICHERUNGSSTEUER,

    /**
     * Gemaess Ablauf des Vertrages Grundlage fuer die Berechnung von
     * Provision, sofern nicht in Satzart 0210 geschluesselt (JJ).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 195
    )
    RESTLAUFZEIT_DES_VERTRAGES,

    /**
     * Gemaess Laufzeitrabatt VAG-Novelle 01-07-1991.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 197
    )
    LAUFZEITRABATT_IN_PROZENT,

    /**
     * Datum der Antragsaufnahme. Sollten Tag und/oder Monat nicht vorhanden
     * sein, muss "00" geschluesselt werden Tag/Monat/Jahr
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 201
    )
    ANTRAGSDAT,

    /**
     * Referenz-Versicherungsscheinummer.
     * Z. B.: bei Wechsel der Vers.scheinnummer;
     * eigenständige Vers.scheinnr. des Vermittlers
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 209
    )
    REFERENZ_VERSICHERUNGSSCHEINNUMMER,

    /**
     * Bei Wechsel der Versicherungsscheinnummer:
     * 1 = alte Versicherungsscheinnummer, 2 = neue Versicherungsscheinnummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = Zeichen.class,
            byteAdresse = 226
    )
    SPEZIFIKATION_REFERENZ_VERSICHERUNGSSCHEINNUMMER,

    /**
     * Zusaetzlicher Ordnungsbegriff des VU.
     * Z. B. Rahmenvertragsnummer, Bearbeitende Stelle.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 227
    )
    ORDNUNGSBEGRIFF,

    /**
     * Waehrung, in der die Dokumente fuer den Kunden ausgefertigt werden.
     * ISO-CODE, siehe Anlage 3
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 244
    )
    WAEHRUNG_DOKUMENTE_FUER_VN,

    /**
     * Erweiterungssatz fuer Euro-/Waehrungsfaehigkeit (Satzarten 0211 bzw. 0221) vorhanden.
     * 0 = nein, 1 = ja.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 36,
            type = Zeichen.class,
            byteAdresse = 247
    )
    ERWEITERUNGSSATZ_VORHANDEN,

    /**
     * Spezifiziert, ob das Feld 22 "Gesamtbetrag" einen Einzahlungs- oder
     * Ausschuettungsbetrag beinhaltet (nur fuer Investment - Sparte 0550).
     * A = Auszahlung, E = Einzahlung
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 37,
            type = Zeichen.class,
            byteAdresse = 248
    )
    EINZAHLUNG_AUSSCHUETTUNG,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 38,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 249
    )
    LEERSTELLEN1,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 39,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER1,

    /////   Satzspezifischer Teil (Satz 2)  ///////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 2,
            type = Feld1bis7.class
    )
    INTRO2,

    /**
     * Abhaengig von der Sparte bei kurzfristigen Vertraegen sowie bei
     * Vertraegen, die nicht automatisch verlaengert werden, immer "Nein".
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = Zeichen.class,
            byteAdresse = 43
    )
    KUENDIGUNGSKLAUSEL,

    /**
     * Versicherungsschein-Nr. des Vermittlers, rechtsbuendig, mit Leerstellen
     * linksbuendig auffuellen ohne Sonderzeichen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 44
    )
    VERSICHERUNGSSCHEINNUMMER_VM,

    /**
     * Unternehmensindividuelle Produktkuerzel/-schluessel.
     * Abweichend von der Sparte bzw. "Markenbegriff".
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 61
    )
    PRODUKTFORM,

    /**
     * Versionsdatum der Produktform, Monat / Jahr (MMJJJJ).
     * Wenn kein Versionsdatum vorhanden, muss das Datum der ProdukteinFuehrung
     * geliefert werden. Abweichend von der Sparte bzw. "Markenbegriff".
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 66
    )
    PRODUKTFORM_GUELTIG_AB,

    /**
     * Gesamtbeitrag unter Beruecksichtigung aller Zu- und Abschlaege inklusiv
     * Versicherungs-Steuer gemaess Zahlungsweise.
     * Im Fuehrungs-/Beteiligungsgeschaeft 100%. In KV nicht gemaess der
     * Zahlungsweise, sondern Monats-Soll-Beitrag oder sonstiger
     * tarifspezifischer Beitrag (z. B. AS). In LV einschl. verrechnete
     * Ueberschussbeteiligung. Bei Kreditvers. nur: Kautionsversicherung-S,
     * Warenkreditversicherung-S, Vertrauensschadenversicherung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = AlphaNumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 72
    )
    GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN,

    /**
     * Druckaufbereitete Versicherungsscheinnummer wie auf dem Dokument (zum
     * Beispiel Police) angegeben, inklusive aller Alpha und Sonderzeichen.
     * Diese Feld dient der Zuordnung der druckaufbereiteten
     * Versicherungsscheinnummer der Dokumente (z. B. Police) zur
     * mitgelieferten Versicherungsscheinnummer in Feld 5 beim Datenimport und
     * fuer den Ausdruck von Dokumenten durch den Vermittler.
     * Dieses Feld sollte immer gefuellt werden, wenn die systemtechnische VSNR
     * von der druckaufbereiteten VSNR abweicht.
     * Beispiel: Versicherungsscheinnummer: RS12345456
     * Druckaufbereitete Versicherungsscheinnummer: RS12345/456.123
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 84
    )
    DRUCKAUFBEREITETE_VERSICHERUNGSSCHEINNUMMER,

    /**
     * Produktname.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 114
    )
    PRODUKTNAME,

    /**
     * Der fuer diesen Vertragszustand gueltige Ratenzahlungszuschlag gemaess
     * Zahlungsweise in Prozent (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 134
    )
    RATENZAHLUNGSZUSCHLAG_IN_PROZENT,

    /**
     * Datum des Antragseingangs beim VU Tag/Monat/Jahr (TTMMJJJJ).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 139
    )
    ANTRAGSEINGANGSDAT,

    /**
     * Datum der Policierung Tag/Monat/Jahr (TTMMJJJJ).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 147
    )
    POLICIERUNGSDAT,

    /**
     * Der aktuelle Vermittler zu diesem Vertrag.
     * Die Registierungsnummer gemaess dem Versicherungsvermittlerregister,
     * 15 Byte incl. 3 Sonderzeichen. Z. B. D-K0CR-GGQV6-53
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 15,
            byteAdresse = 155
    )
    REGISTRIERUNGSNUMMER_VERMITTLER,

    /**
     * Internes Ordnungsmerkmal des VM.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 50,
            byteAdresse = 170
    )
    INTERNES_ORDNUNGSMERKMAL_DES_VM,

    /**
     * Vertragsverbindungsnummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 35,
            byteAdresse = 220
    )
    VERTRAGSVERBINDUNGSNUMMER,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 255
    )
    LEERSTELLEN2,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 22,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER2;

}
