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
 * (c)reated 23.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.feld.sparte10;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 210, Sparte 10
 * (Vertragsspezifischer Teil, Leben).
 *
 * @author oliver (ob@aosd.de)
 * @since 23.03.2011
 */
public enum Feld210 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /**
     * Kennzeichen Vertragsentstehung.
     * <pre>
     * 1 = Neuantrag
     * 2 = Zuwachsgarantie
     * 3 = Abtrennung Gruppenversicherung
     * 4 = Umtausch Risiko
     * 5 = uebernahme von anderer Gesellschaft
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 43
    )
    KENNZEICHEN_VERTRAGSENTSTEHUNG,

    /**
     * ISO-Code, siehe Anlage 3.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 44
    )
    WAEHRUNGSSCHLUESSEL,

    /**
     * Beitragsdepot (0 = nein, 1 = ja).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 47
    )
    BEITRAGSDEPOT,

    /**
     * Vorgeschaltete Risikoversicherung (nur waehrend der Risikovorlaufphase).
     * 0 = nein, 1 = ja.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 48
    )
    RISIKOVERLAUF,

    /**
     * Vorlaufssumme in Waehrungseinheiten, bezogen auf Risikovorlauf.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 49
    )
    VORLAUFSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Verkuerzte Beitragszahlungsdauer in Anzahl der Jahre.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 61
    )
    VERKUERZTE_BEITRAGSZAHLUNGSDAUER,

    /**
     * Besonderer Verwendungszweck.
     * <pre>
     * 1 = Hypothekentilgung
     * 2 = VL
     * 3 = Restschuldversicherung
     * 9 = sonstiges
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 63
    )
    BESONDERER_VERWENDUNGSZWECK,

    /**
     * Vertragsform.
     * <pre>
     * 0 = Einzel
     * 1 = Firmengruppen
     * 2 = Vereins-/Verbandsgruppen
     * 3 = Sammelversicherung
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 64
    )
    VERTRAGSFORM,

    /**
     * Der objektiv umschriebene Personenkreis oder bei Gruppen-Verband:
     * Vertragsnummer des Beitragszahlers/Gruppenvertragsnummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 40,
            byteAdresse = 65
    )
    GRUPPENART,

    /**
     * Name des mitversicherten Kinds.
     * Aufgrund spez. Tarifformen bestimmter VU.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 105
    )
    NAME_MITVERSICHERTES_KIND,

    /**
     * Fuer den betreffenden Vertrag vereinbarter Provisionssatz (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 125,
            nachkommaStellen = 2
    )
    ABSCHLUSSPROVISION,

    /**
     * Kennzeichen, dass fuer den betreffenden Vertrag der Provisionssatz von
     * den allgemeinen Provisionsvereinbarungen abweicht (0 = nein, 1 = ja).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 130
    )
    KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION,

    /**
     * Bestandspflegeprovision (siehe Erlaeuterung fuer Abschlussprovision).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 131
    )
    BESTANDSPFLEGEPROVISION,

    /**
     * Kennzeichen fuer abweichend Bestandspflegeprovision.
     * s. Erlaeuterung fuer abweichende Abschlussprovision (0 = nein, 1 = ja).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 136
    )
    KENNZEICHEN_ABWEICHENDE_BESTANDSPFLEGEPROVISION,

    /**
     * Art des Drittrechts.
     * <pre>
     * 01 = Abtretung
     * 02 = Verpfaendung
     * 03 = Pfaendung
     * 04 = Konkurs
     * 05 = Vergleich
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 137
    )
    ART_DES_DRITTRECHTS,

    /**
     * Derzeitige Depotsumme (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 139,
            nachkommaStellen = 2
    )
    AKTUELLE_BEITRAGSDEPOTSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschluesselt
     * werden. Datum, an dem der VN die Erstpraemie zu zahlen hat, wenn der
     * Zahlungsanfang spaeter als der Versicherungsbeginn liegt, z. B. bei
     * Sammelinkasso.
     * <pre>
     * Tag/Monat/Jahr (TTMMJJJJ)
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = NumFeld.class,
            anzahlBytes = 8,
            byteAdresse = 151
    )
    ZAHLUNGSANFANG,

    /**
     * Zuzahlungsrecht (0 = nein, 1 = ja).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 159
    )
    ZUZAHLUNGSRECHT,

    /**
     * Letzter Zuzahlungsbetrag (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 160,
            nachkommaStellen = 2
    )
    ZUZAHLUNGSBETRAG_IN_WE,

    /**
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschluesselt
     * werden. Datum der letzten Zuzahlung, Tag/Monat/Jahr (TTMMJJJJ).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 172
    )
    ZUZAHLUNGSDAT,

    /**
     * Zukuenftiger Gesamtbeitrag in Waehrungseinheiten.
     * gem. Zahlungsweise wenn der Beitrag noch nicht umgestellt wurde
     * (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 180,
            nachkommaStellen = 2
    )
    ZUKUENFTIGER_GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Zahlungsweise des zukuenftigen Gesamtbetrags.
     * blank = Rechtschutz / Verkehrsservice / Kredit (In den Sparten
     * Rechtschutz und Verkehrsservice kann das Datenfeld "Zahlungsweise" in
     * der Satzart 0200 blank sein, die Zahlungsweise wird dann auf Risikoebene
     * in der Satzart 0210, spaetestens in Satzart 0220 angegeben.
     * Bei Einzel- und / oder Umsatzanmeldungen muss das Datenfeld
     * "Zahlungsweise" in der Satzart 0400 blank sein.)
     * <p>
     * siehe Anlage 14
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 192
    )
    ZAHLUNGSWEISE_KUENFTIGER_GESAMTBETRAG,

    /**
     * Beitragsumstellungsdatum.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschluesselt
     * werden.
     * <pre>
     * Tag/Monat/Jahr (TTMMJJJJ)
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 193
    )
    BEITRAGSUMSTELLUNGSDAT,

    /**
     * Beitragsumstellungsgrund, siehe Anlage 6.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 201
    )
    BEITRAGSUMSTELLUNGSGRUND,

    /**
     * Referenz-Versicherungsscheinnummer.
     * Zusaetzliche Versicherungsscheinnummer, wenn Teile des
     * Versicherungsproduktes/-vertrages sich in anderen
     * Versicherungsscheinnummern wiederfinden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 203
    )
    REFERENZ_VERSICHERUNGSSCHEINNUMMER_1,

    /**
     * Referenz-Versicherungsscheinnummer.
     * @see #REFERENZ_VERSICHERUNGSSCHEINNUMMER_1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 220
    )
    REFERENZ_VERSICHERUNGSSCHEINNUMMER_2,

    /**
     * Referenz-Versicherungsscheinnummer.
     * @see #REFERENZ_VERSICHERUNGSSCHEINNUMMER_1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 237
    )
    REFERENZ_VERSICHERUNGSSCHEINNUMMER_3,

    /**
     * Weitere, nicht abbildbare Referenznummern vorhanden (0 = nein, 1 = ja).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 254
    )
    WEITERE_REFERENZNUMMERN,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 36,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 255
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

    /////   Teildatensatz 2   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 2,
            type = Feld1bis7.class
    )
    INTRO2,

    /**
     * Betriebliche Altersversorgung.
     * <pre>
     * 1 = Direktversicherung
     * 2 = Direktversicherung mit Gehaltsumwandlung
     * 3 = Direktversicherung mit anteiliger Gehaltsumwandlung
     * 4 = Rueckdeckungsversicherung fuer Pensionszusage
     * 5 = Ruekdeckungsversicherung fuer Unterstuetzungskassenzusage
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 43
    )
    BETRIEBLICHE_ALTERSVORSORGE,

    /**
     * Unverfallbarkeit (0 = nein, 1 = ja).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 44
    )
    UNVERFALLBARKEIT,

    /**
     * Diensteintrittsdatum.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschluesselt
     * werden. Tag/Monat/Jahr (TTMMJJJJ).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 45
    )
    DIENSTEINTRITTSDAT,

    /**
     * Bilanzmonat des Arbeitgebers bei Rueckdeckungsversicherung fuer
     * Pensionszusage (MM).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 53
    )
    BILANZMONAT_ARBEITGEBER,

    /**
     * Produktbeschreibung.
     * Klartext oder freidefinierbare Schluessel (z. B. bei vorhandener
     * Umstellungsoption, Aufbaumodell und/oder Besondere Vereinbarungen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 55
    )
    PRODUKTBESCHREIBUNG,

    /**
     * Kapitalertragsteuerpflicht (0 = nein, 1 = ja).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 85
    )
    KAPITALERTRAGSTEUERPFLICHT,

    /**
     * Unternehmensindividuelle Produktkuerzel/-schluessel.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 86
    )
    PRODUKTFORM,

    /**
     * Versionsdatum der Produktform. Monat / Jahr (MMJJJJ).
     * Wenn kein Versionsdatum vorhanden, muss das Datum der Produkteinfuehrung
     * geliefert werden.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 91
    )
    PRODUKTFORM_GUELTIG_AB,

    /**
     * Produktname.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 97
    )
    PRODUKTNAME,

    // Betriebliche und private Altersversorgung

    /**
     * Art der steuerlichen Foerderung.
     * <pre>
     * 0 = irrelevant
     * 1 = pauschal besteuerte Direktversicherung nach Paragraph 40b EStG
     * 2 = Beitraege steuerfrei nach Paragraph 3 Ziff.63 EStG
     * 3 = zulagengefoerderte Direktversicherung nach Paragraph 10a EStG
     * 4 = Beitraege steuerfrei nach Paragraph 3 Ziff.66 EstG
     * 5 = keine
     * 6 = Zulagegefoerderte Privatversicherung nach Paragraph 10 a EStG
     * 7 = Basis-/Ruerup-Rente nach Paragraph 10 Abs. 1 Nr. 2 Buchstabe b EStG
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 117
    )
    ART_DER_STEUERLICHEN_FOERDERUNG,

    /**
     * Finanzierungsart.
     * <pre>
     * 0 = irrelevant
     * 1 = Arbeitgeber finanziert
     * 2 = Arbeitnehmer finanziert Entgeltumwandlung
     * 3 = Arbeitnehmer finanziert aus versteuertem Einkommen
     * 4 = aus Vertrag nicht erkennbar
     * 5 = Arbeitgeber / Arbeitnehmer
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 118
    )
    FINANZIERUNGSART,

    /**
     * Durchfuehrungsweg.
     * <pre>
     * 0 = irrelevant
     * 1 = Direktversicherung
     * 2 = Unterstuetzungskasse
     * 3 = Pensionskasse
     * 4 = Pensionsfonds
     * 5 = Privat
     * 6 = Direktzusage
     * 7 = Private Riesterversicherung
     * 8 = Riesterdirektversicherung
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 19,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 119
    )
    DURCHFUEHRUNGSWEG,

    /**
     * Finanzierung der Zusage.
     * <pre>
     * 0 = irrelevant
     * 1 = Rueckdeckungsversicherung
     * 2 = Rueckdeckungsversicherung partiell
     * 3 = Rueckdeckungsversicherung kongruent
     * 4 = Pensionsfonds
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 20,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 120
    )
    FINANZIERUNG_ZUSAGE,

    /**
     * Individueller Schluessel der Unterstuetzungskasse beim VU.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 121
    )
    UNTERSTUETZUNGSKASSE_SCHLUESSEL,

    /**
     * Unterstuetzungskasse Name.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 40,
            byteAdresse = 125
    )
    UNTERSTUETZUNGSKASSE_NAME,

    /**
     * Individueller Schluessel des Traegerunternehmens beim VU.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 23,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 165
    )
    TRAEGERUNTERNEHMEN_SCHLUESSEL,

    /**
     * Traegerunternehmen Name.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 24,
            type = AlphaNumFeld.class,
            anzahlBytes = 40,
            byteAdresse = 169
    )
    TRAEGERUNTERNEHMEN_NAME,

    /**
     * Nummer des Kollektivvertrages.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 15,
            byteAdresse = 209
    )
    KOLLEKTIV_NR,

    /**
     * Datum der Fälligkeit der letzten Beitragszahlung vor Ablauf des Vertrags
     * Tag/Monat/Jahr (TTMMJJJJ).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 25,
            type = NumFeld.class,
            anzahlBytes = 8,
            byteAdresse = 224
            )
    FAELLIGKEIT_DER_LETZTEN_BEITRAGSZAHLUNG,

    /**
     * Lebenslange Beitragszahlung, 0 = nein 1 = ja.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 25,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 232
            )
    LEBENSLANGE_BEITRAGSZAHLUNG,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 23,
            byteAdresse = 233
    )
    LEERSTELLEN2,

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
    SATZNUMMER2;

}
