package gdv.xport.satz.feld.sparte130;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 210, Sparte 130.
 * "Verbundene Hausrat" (Satzart 0210)
 *
 * @author David
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
     * versicherte Gefahren.<br/>
     * 10 x 1 Stelle<br/>
     * B = Beraubung<br/>
     * E = ED<br/>
     * F = Feuer<br/>
     * G = Glas<br/>
     * H = Hagel<br/>
     * L = Leitungswasser<br/>
     * M = Elementarschäden<br/>
     * N = Nutzfeuer<br/>
     * S = Sturm<br/>
     * U = Überspannungschäde<br/>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 68)
    VERSICHERTE_GEFAHREN,

    /**
     * Länderkennzeichen der Risikoanschrift.<br/>
     * KFZ-Länderkennzeichen z. B. Länderkennzeichen für D = Deutschland, B =
     * Belgien, DK = Dänemark, F = Frankreich, CDN = Kanada<br/>
     * siehe Anlage 63
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 78)
    LAENDERKENNZEICHEN_DER_RISIKOANSCHRIFT,

    /**
     * Postleitzahl der Risikoanschrift.<br/>
     * linksbündig
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 81)
    POSTLEITZAHL_DER_RISIKOANSCHRIFT,

    /**
     * Risikoort.<br/>
     * Wenn Risikoanschrift abweichend vom Wohnort
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 25,
            byteAdresse = 87)
    RISIKOORT,

    /**
     * Risikostraße.<br/>
     * siehe Erläuterung Risikoort
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 112)
    RISIKOSTRASSE,

    /**
     * Währungsschlüssel.<br/>
     * ISO-Code,<br/>
     * siehe Anlage 3
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 142)
    WAEHRUNGSSCHUESSEL,

    /**
     * Zuschlagsbetrag in Währungseinheiten.<br/>
     * Kumulierter Zuschlagsbetrag auf Vertragsebene<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 145)
    ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlagsbetrag in Währungseinheiten.<br/>
     * Kumulierter Abschlagsbetrag auf Vertragsebene<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 157)
    ABSCHLAGSBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Gesamtversicherungssummer in Währungseinheiten.<br/>
     * Gesamtversicherungssumme in Währungseinheiten<br/>
     * (12,0 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = NumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 169)
    GESAMTVERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Gesamtbeitrag in Währungseinheiten.<br/>
     * Gesamtbeitrag unter Berücksichtigung aller Zu- und Abschläge ohne Gebühr
     * und Vers.Steuer gemäß Zahlungsweise in Währungseinheiten<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 181)
    GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Bedingungen.<br/>
     * z.B.: VHB 74, VHB 84, VHB 92<br/>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 193)
    BEDINGUNGEN,

    /**
     * erweiterte Neuwertversicherung.<br/>
     * 0 = nein<br/>
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 198)
    ERWEITERTE_NEUWERTVERSICHERUNG,

    /**
     * Sicherungsrichtlinien.<br/>
     * 1 = normal<br/>
     * 2 = überdurchschnittlich gem. Sicherungsrichtlinien GDV
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 199)
    SICHERUNGSRICHTLINIEN,

    /**
     * Einbruch Meldeanlage.<br/>
     * 0 = nein<br/>
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 200)
    EINBRUCH_MELDEANLAGE,

    /**
     * Risikokennziffer.<br/>
     * siehe Anlage 11
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 201)
    RISIKOKENNZIFFER,

    /**
     * Anzahl Monate unbewohnt.<br/>
     * Ganzahlige Angabe
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 206)
    ANZAHL_MONATE_UNBEWOHNT,

    /**
     * Wohnfläche qm.<br/>
     * Ganzzahlige Angabe
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 208)
    WOHNFLAECHE_QM,

    /**
     * Tarifzone.<br/>
     * 1, 2 oder 3. Zuordnung gemäß PLZ
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 213)
    TARIFZONE,

    /**
     * Bauartklasse.<br/>
     * siehe Anlage 12
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 214)
    BAUARTKLASSE,

    /**
     * Gefahrenerhöhung.<br/>
     * 0 = nein <br/>
     * 1 = ja <br/>
     * 2 = ja (temporär)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 215)
    GEFAHRENERHOEHUNG,

    /**
     * Einschluss Vandalismus.<br/>
     * 0 = nein <br/>
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 216)
    EINSCHLUSS_VANDALISMUS,

    /**
     * Untervers.-Verzicht.<br/>
     * 0 = nein <br/>
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 217)
    UNTERVERS_VERZICHT,

    /**
     * Abschlussprovision.<br/>
     * Für den betreffenden Vertrag vereinbarter Provisionssatz (3,2 Stellen) <br/>
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 218)
    ABSCHLUSSPROVISION,

    /**
     * Kennzeichen für abweichende Abschlussprovision.<br/>
     * Kennzeichen, dass für den betreffenden Vertrag der
     * Provisionssatz von den allgemeinen Provisionsvereinbarungen
     * abweicht <br/>
     * 0 = nein <br/>
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 223)
    KENNZEICHEN_FUER_ABWEICHENDE_ABSCHLUSSPROVISION,

    /**
     * Folgeprovision.<br/>
     * s. Erläuterung für Abschlussprovision <br/>
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 36,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 224)
    FOLGEPROVISION,

    /**
     * Kennzeichen für abweichende Folgeprovision.<br/>
     * s. Erläuterung: Kennzeichen für abweichende Abschlussprovision <br/>
     * 0 = nein <br/>
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 37,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 229)
    KENNZEICHEN_FUER_ABWEICHENDE_FOLGEPROVISION,

    /**
     * Restlaufzeit des Vertrages.<br/>
     * gemäß Ablauf des Vertrages. Grundlage für die
     * Berechnung von Provision, sofern nicht in
     * Satzart 210 geschlüsselt (JJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 38,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 230)
    RESTLAUFZEIT_DES_VERTRAGES,

    /**
     * Laufzeitrabatt in %.<br/>
     * gemäß Laufzeitrabatt VAG-Novelle 01.07.1991 <br/>
     * (2,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 39,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 232)
    LAUFZEITRABATT_IN_PROZENT,

    /**
     * Produktform.<br/>
     * Unternehmensindividuelle Produktkürzel/-schlüssel
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 40,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 236)
    PRODUKTFORM,

    /**
     * Produktform gültig ab.<br/>
     * Versionsdatum der Produktform. Monat / Jahr (MMJJJJ)
     * Wenn kein Versionsdatum vorhanden, muss das Datum der
     * Produkteinführung geliefert werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 41,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 241)
    PRODUKTFORM_GUELTIG_AB,

    /**
     * Leerstellen.<br/>
     * Leerstellen <br/>
     * Freie Stellen für weitere Belegung
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 42,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 247)
    LEERSTELLEN2,

    /**
     * Satznummer.<br/>
     * konstant 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 43,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 251)
    SATZNUMMER2,

    /**
     * Objektnummer.<br/>
     * siehe Anlage 67
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 44,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 252)
    OBJEKTNUMMER2,


    // /// Teildatensatz 2 /////////////////////////////////////////////////

    /**
     * Satzart.<br/>
     * konstant 0210
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 1,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 1)
    SATZART,

    /**
     * VU-Nummer.<br/>
     * Gemäß VU-Verzeichnis der BaFin, linksbündig<br/>
     * Das VU-Nr.-Verzeichnis kann bei der Bundesanstalt für
     * Finanzdienstleistungsaufsicht in Bonn angefordert werden
     * (Graurheindorfer Str. 108, 53117 Bonn, www.bafin.de).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 2,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 5)
    VU_NUMMER,

    /**
     * Bündelungskennzeichen.<br/>
     * Kennzeichen für gebündelte Verträge<br/>
     * <br/>
     * 1 = gebündelt
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 3,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 10)
    BUENDELUNSKENNZEICHEN,

    /**
     * Sparte.<br/>
     * Versicherungszweig gemäß Verordnung über die Berichterstattung
     * von Versicherungsunternehmen gegenüber der BaFin (BerVersV)
     * Abschnitt C<br/>
     * siehe Anlage 1
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 4,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 11)
    SPARTE,

    /**
     * Versicherungsschein-Nummer.<br/>
     * Versicherungsschein-Nummer, rechtsbündig, mit Leerstellen linksbündig
     * auffüllen ohne Sonderzeichen
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 5,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 14)
    VERSICHERUNGSSCHEIN_NUMMER,

    /**
     * Folgenummer.<br/>
     * Lfd. Nummer innerhalb einer Versicherungsschein-Nr. über alle
     * Satzarten für die gleiche Datensendung<br/>
     * siehe Anlage 2
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 6,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 31)
    FOLGENUMMER,

    /**
     * Geschäftsstelle / Vermittler.<br/>
     * Die geschäftsführende Geschäftsstelleund der Vermittler ohne
     * Sonderzeichen
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 7,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 33)
    GESCHAEFTSSTELLE_VERMITTLER,

    /**
     * Produktname.<br/>
     * Produktname
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 43)
    PRODUKTNAME,

    /**
     * Referenznummer.<br/>
     *
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 63)
    REFERENZNUMMER,

    /**
     * Ständig bewohnt.<br/>
     * 0 = nein<br/>
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 70)
    STAENDIG_BEWOHNT,

    /**
     * Leerstellen.<br/>
     * Leerstellen<br/>
     * Freie Stellen für weitere Belegung
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 180,
            byteAdresse = 71)
    LEERSTELLEN,

    /**
     * Satznummer.<br/>
     * konstant 2
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 251)
    SATZNUMMER,

    /**
     * Objektnummer.<br/>
     * siehe Anlage 67
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 252)
    OBJEKTNUMMER
}
