package gdv.xport.satz.feld.sparte40;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 210, Sparte 40
 * (Vertragsspezifischer Teil, Haftpflicht).
 *
 * @author rklemmer
 * @since 20.02.2013
 */
public enum Feld210 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
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
     * Summenart 1.<br/>
     * 1 = Personenschäden<br/>
     * 2 = Sachschäden<br/>
     * 3 = Personen- und Sachschäden<br/>
     * 4 = Vermögensschäden<br/>
     * 5 = Personen- und Vermögensschäden<br/>
     * 6 = Sach- und Vermögensschäden<br/>
     * 7 = Personen-,Vermögens- und Sachschäden<br/>
     * 8 = Mietsachschäden<br/>
     * 9 = EP (Einheitspauschale)<br/>
     * A = Personen- und Sachschäden (pauschal)<br/>
     * B = sonstige Sach- und Vermögensschäden (pauschal)<br/>
     * C = Personenschäden und sonstige Schäden (pauschal)<br/>
     * D = unbegrenzt (nur für Gabelstapler nach AKB)<br/>
     * Z = Sonstiges
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 68)
    SUMMENART_1,

    /**
     * Währungsschlüssel 1.<br/>
     * ISO-Code, siehe Anlage 3
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 69)
    WAEHRUNGSSCHLUESSEL_1,

    /**
     * Deckungssumme 1 in Tausend Währungseinheiten.<br/>
     * Deckungssummen die dem Vertrag zugrunde liegen.<br/>
     * Sollten unterschiedliche Deckungssummen vereinbart sein, so sind diese in
     * den Wagnisteilen (Satzart 0220) zu schlüsseln
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 72)
    DECKUNGSSUMME_1_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Summenart 2.<br/>
     * Details siehe Deckungsart 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 81)
    SUMMENART_2,

    /**
     * Währungsschlüssel 2.<br/>
     * ISO-Code, siehe Anlage 3
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 82)
    WAEHRUNGSSCHLUESSEL_2,

    /**
     * Deckungssumme 2 in Tausend Währungseinheiten.<br/>
     * Deckungssummen die dem Vertrag zugrunde liegen.<br/>
     * Sollten unterschiedliche Deckungssummen vereinbart sein, so sind diese in
     * den Wagnisteilen (Satzart 0220) zu schlüsseln
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 85)
    DECKUNGSSUMME_2_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Summenart 3.<br/>
     * Details siehe Deckungsart 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 94)
    SUMMENART_3,

    /**
     * Währungsschlüssel 3.<br/>
     * ISO-Code, siehe Anlage 3
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 95)
    WAEHRUNGSSCHLUESSEL_3,

    /**
     * Deckungssumme 3 in Tausend Währungseinheiten.<br/>
     * Deckungssummen die dem Vertrag zugrunde liegen.<br/>
     * Sollten unterschiedliche Deckungssummen vereinbart sein, so sind diese in
     * den Wagnisteilen (Satzart 0220) zu schlüsseln
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 98)
    DECKUNGSSUMME_3_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Summenart 4.<br/>
     * Details siehe Deckungsart 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 107)
    SUMMENART_4,

    /**
     * Währungsschlüssel 4.<br/>
     * ISO-Code, siehe Anlage 3
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 108)
    WAEHRUNGSSCHLUESSEL_4,

    /**
     * Deckungssumme 4 in Tausend Währungseinheiten.<br/>
     * Deckungssummen die dem Vertrag zugrunde liegen.<br/>
     * Sollten unterschiedliche Deckungssummen vereinbart sein, so sind diese in
     * den Wagnisteilen (Satzart 0220) zu schlüsseln
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 111)
    DECKUNGSSUMME_4_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Kennzeichen für Jahres-Maximierung.<br/>
     * 0 = keine<br/>
     * 1 = 1-fach<br/>
     * 2 = 2-fach<br/>
     * 3 = 3-fach<br/>
     * 4 = 4-fach<br/>
     * 5 = 5-fach<br/>
     * 6 = 6-fach<br/>
     * 7 = 7-fach<br/>
     * 8 = 8-fach<br/>
     * 9 = sonstige Vereinbarung<br/>
     * A = 1,25-fach<br/>
     * B = 1,5-fach<br/>
     * C = 1,75-fach<br/>
     * D = 2,25-fach<br/>
     * E = 2,5-fach<br/>
     * F = 2,75-fach
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 120)
    KENNZEICHEN_FUER_JAHRES_MAXIMIERUNG,

    /**
     * Selbstbehalt.<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 121)
    SELBSTBEHALT,

    /**
     * Allgemeine Versicherungsbedingungen.<br/>
     * Inkraftsetzung bei VU. Monat / Jahr (MMJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Datum.class,
            anzahlBytes = 4,
            byteAdresse = 122)
    ALLGEMEINE_VERSICHERUNGSBEDINGUNGEN,

    /**
     * Sonderbedingungen.<br/>
     * Vertragsbezogene oder unternehmensindividuelle Bedingungen<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 126)
    SONDERBEDINGUNGEN,

    /**
     * Währungsschlüssel.<br/>
     * ISO-Code, siehe Anlage 3
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 127)
    WAEHRUNGSSCHLUESSEL,

    /**
     * Zuschlagsbetrag in Währungseinheiten.<br/>
     * kumulierter Zuschlagsbetrag auf Vertragsebene<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 130)
    ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlagsbetrag in Währungseinheiten.<br/>
     * kumulierter Abschlagsbetrag auf Vertragsebene<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 142)
    ABSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Gesamtbeitrag in Währungseinheiten.<br/>
     * Summe aus den Satzarten 0220.<br/>
     * Gesamt-Jahresnetto-Beitrag unter Berücksichtigung aller Zu- und
     * Abschläge gemäß Zahlungsweise ohne Vers.-Steuer und Gebühr<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 154)
    GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlussprovision.<br/>
     * Für den betreffenden Vertrag vereinbarter Provisionssatz<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 166)
    ABSCHLUSSPROVISION,

    /**
     * Folgeprovision.<br/>
     * s. Erläuterung für Abschlussprovision<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 171)
    FOLGEPROVISION,

    /**
     * Kennzeichen für abweichende Abschlussprovision.<br/>
     * Kennzeichen, dass für den betreffenden Vertrag der Provisionssatz von
     * den allgemeinen Provisionsvereinbarungen abweicht<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 176)
    KENNZEICHEN_FUER_ABWEICHENDE_ABSCHLUSSPROVISION,

    /**
     * Kennzeichen für abweichende Folgeprovision.<br/>
     * s. Erläuterung: Kennzeichen für abweichende Abschlussprovision<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 177)
    KENNZEICHEN_FUER_ABWEICHENDE_FOLGEPROVISION,

    /**
     * Restlaufzeit des Vertrages.<br/>
     * gemäß Ablauf des Vertrages Grundlage für die Berechnung von Provision
     * (JJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 36,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 178)
    RESTLAUFZEIT_DES_VERTRAGES,

    /**
     * Laufzeitrabatt in %.<br/>
     * gemäß Laufzeitrabatt VAG-Novelle 01.07.1991<br/>
     * (2,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 37,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 180)
    LAUFZEITRABATT_IN_PROZENT,

    /**
     * Produktform.<br/>
     * Unternehmensindividuelle Produktkürzel/-schlüssel
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 38,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 184)
    PRODUKTFORM,

    /**
     * Produktform gültig ab.<br/>
     * Versionsdatum der Produktform. Monat / Jahr (MMJJJJ).<br/>
     * Wenn kein Versionsdatum vorhanden, muss das Datum der Produkteinführung
     * geliefert werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 39,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 189)
    PRODUKTFORM_GUELTIG_AB,

    /**
     * Produktname.<br/>
     * Produktname
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 40,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 195)
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
            nr = 41,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 215)
    REFERENZNUMMER,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 41,
            type = AlphaNumFeld.class,
            anzahlBytes = 35,
            byteAdresse = 222)
    LEERSTELLEN

}
