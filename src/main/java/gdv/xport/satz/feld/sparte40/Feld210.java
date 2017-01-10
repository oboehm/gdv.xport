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
            sparte = 40,
            teildatensatz = 1,
            type = Feld1bis7.class)
    INTRO1,

    /**
     * Vertragsstatus.
     * <pre>
     * 1 = lebend
     * 2 = lebend / kurzfristig / unterjährig
     * 3 = ruhend / Anwartschaft
     * 4 = storniert
     * 5 = ruhend / beitragsfrei
     * 6 = beitragsfrei
     * 7 = beitragsfrei durch Leistung
     * 8 = Beitragsstundung
     * 9 = Sonstiges
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 43)
    VERTRAGSSTATUS,

    /**
     * Beginn der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein, muss
     * "00" geschlüsselt werden, ansonsten Tag/Monat/Jahr (TTMMJJJJ).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 44)
    BEGINN,

    /**
     * Ausschluss der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschlüsselt werden, ansonsten Tag/Monat/Jahr (TTMMJJJJ).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 52)
    AUSSCHLUSS,

    /**
     * Termin, zu dem der Vertragszustand wirksam wird/wurde (Gültig-ab- /
     * Wirksam-ab-Datum).
     * <p>
     * Format: Tag/Monat/Jahr (TTMMJJJJ)
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 60)
    AENDERUNGSDAT,

    /**
     * Summenart 1.
     * <pre>
     * 1 = Personenschäden
     * 2 = Sachschäden
     * 3 = Personen- und Sachschäden
     * 4 = Vermögensschäden
     * 5 = Personen- und Vermögensschäden
     * 6 = Sach- und Vermögensschäden
     * 7 = Personen-,Vermögens- und Sachschäden
     * 8 = Mietsachschäden
     * 9 = EP (Einheitspauschale)
     * A = Personen- und Sachschäden (pauschal)
     * B = sonstige Sach- und Vermögensschäden (pauschal)
     * C = Personenschäden und sonstige Schäden (pauschal)
     * D = unbegrenzt (nur für Gabelstapler nach AKB)
     * Z = Sonstiges
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 68)
    SUMMENART_1,

    /**
     * Währungsschlüssel 1.
     * ISO-Code, siehe Anlage 3.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 69)
    WAEHRUNGSSCHLUESSEL_1,

    /**
     * Deckungssumme 1 in Tausend Währungseinheiten.
     * <p>
     * Deckungssummen die dem Vertrag zugrunde liegen.
     * Sollten unterschiedliche Deckungssummen vereinbart sein, so sind diese in
     * den Wagnisteilen (Satzart 0220) zu schlüsseln
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 72)
    DECKUNGSSUMME_1_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Summenart 2.
     * Details siehe Deckungsart 1.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 81)
    SUMMENART_2,

    /**
     * Währungsschlüssel 2.
     * ISO-Code, siehe Anlage 3.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 82)
    WAEHRUNGSSCHLUESSEL_2,

    /**
     * Deckungssumme 2 in Tausend Währungseinheiten.
     * <p>
     * Deckungssummen die dem Vertrag zugrunde liegen.
     * Sollten unterschiedliche Deckungssummen vereinbart sein, so sind diese in
     * den Wagnisteilen (Satzart 0220) zu schlüsseln
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 85)
    DECKUNGSSUMME_2_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Summenart 3.
     * Details siehe Deckungsart 1.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 94)
    SUMMENART_3,

    /**
     * Währungsschlüssel 3 nach
     * ISO-Code, siehe Anlage 3.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 95)
    WAEHRUNGSSCHLUESSEL_3,

    /**
     * Deckungssumme 3 in Tausend Währungseinheiten.
     * <p>
     * Deckungssummen die dem Vertrag zugrunde liegen. Sollten unterschiedliche
     * Deckungssummen vereinbart sein, so sind diese in den Wagnisteilen
     * (Satzart 0220) zu schlüsseln.
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 98)
    DECKUNGSSUMME_3_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Summenart 4.
     * Details siehe Deckungsart 1.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 107)
    SUMMENART_4,

    /**
     * Währungsschlüssel 4.
     * ISO-Code, siehe Anlage 3.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 108)
    WAEHRUNGSSCHLUESSEL_4,

    /**
     * Deckungssumme 4 in Tausend Währungseinheiten.
     * <p>
     * Deckungssummen die dem Vertrag zugrunde liegen. Sollten unterschiedliche
     * Deckungssummen vereinbart sein, so sind diese in den Wagnisteilen
     * (Satzart 0220) zu schlüsseln.
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 111)
    DECKUNGSSUMME_4_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Kennzeichen für Jahres-Maximierung.
     * <pre>
     * 0 = keine
     * 1 = 1-fach
     * 2 = 2-fach
     * 3 = 3-fach
     * 4 = 4-fach
     * 5 = 5-fach
     * 6 = 6-fach
     * 7 = 7-fach
     * 8 = 8-fach
     * 9 = sonstige Vereinbarung
     * A = 1,25-fach
     * B = 1,5-fach
     * C = 1,75-fach
     * D = 2,25-fach
     * E = 2,5-fach
     * F = 2,75-fach
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 120)
    KENNZEICHEN_FUER_JAHRES_MAXIMIERUNG,

    /**
     * Selbstbehalt.
     * 0 = nein, 1 = ja.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 121)
    SELBSTBEHALT,

    /**
     * Allgemeine Versicherungsbedingungen.
     * Inkraftsetzung bei VU.
     * <p>
     * Format: Monat / Jahr (MMJJ)
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Datum.class,
            anzahlBytes = 4,
            byteAdresse = 122)
    ALLGEMEINE_VERSICHERUNGSBEDINGUNGEN,

    /**
     * Sonderbedingungen.
     * <p>
     * Vertragsbezogene oder unternehmensindividuelle Bedingungen:
     * 0 = nein, 1 = ja.
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 126)
    SONDERBEDINGUNGEN,

    /**
     * Währungsschlüssel.
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
     * Zuschlagsbetrag in Währungseinheiten.
     * <p>
     * Kumulierter Zuschlagsbetrag auf Vertragsebene
     * (10,2 Stellen).
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 130)
    ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlagsbetrag in Währungseinheiten.
     * <p>
     * kumulierter Abschlagsbetrag auf Vertragsebene
     * (10,2 Stellen).
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 142)
    ABSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Gesamtbeitrag in Währungseinheiten.
     * <p>
     * Summe aus den Satzarten 0220.
     * Gesamt-Jahresnetto-Beitrag unter Berücksichtigung aller Zu- und
     * Abschläge gemäß Zahlungsweise ohne Vers.-Steuer und Gebühr
     * (10,2 Stellen).
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 154)
    GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlussprovision.
     * <p>
     * Für den betreffenden Vertrag vereinbarter Provisionssatz
     * (3,2 Stellen).
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 166)
    ABSCHLUSSPROVISION,

    /**
     * Folgeprovision.
     * <p>
     * s. Erläuterung für Abschlussprovision
     * (3,2 Stellen).
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 171)
    FOLGEPROVISION,

    /**
     * Kennzeichen für abweichende Abschlussprovision.
     * <p>
     * Kennzeichen, dass für den betreffenden Vertrag der Provisionssatz von
     * den allgemeinen Provisionsvereinbarungen abweicht:
     * 0 = nein, 1 = ja.
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 176)
    KENNZEICHEN_FUER_ABWEICHENDE_ABSCHLUSSPROVISION,

    /**
     * Kennzeichen für abweichende Folgeprovision.
     * <p>
     * s. Erläuterung: Kennzeichen für abweichende Abschlussprovision:
     * 0 = nein, 1 = ja.
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 177)
    KENNZEICHEN_FUER_ABWEICHENDE_FOLGEPROVISION,

    /**
     * Restlaufzeit des Vertrages.
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
     * Laufzeitrabatt in %.
     * gemäß Laufzeitrabatt VAG-Novelle 01.07.1991
     * (2,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 37,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 180)
    LAUFZEITRABATT_IN_PROZENT,

    /**
     * Produktform.
     * Unternehmensindividuelle Produktkürzel/-schlüssel.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 38,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 184)
    PRODUKTFORM,

    /**
     * Produktform gültig ab.
     * <p>
     * Versionsdatum der Produktform. Monat / Jahr (MMJJJJ).
     * Wenn kein Versionsdatum vorhanden, muss das Datum der Produkteinführung
     * geliefert werden.
     * </p>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 39,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 189)
    PRODUKTFORM_GUELTIG_AB,

    /**
     * Produktname.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 40,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 195)
    PRODUKTNAME,

    /**
     * Referenznummer.
     * <p>
     * Wenn in einem gebündelten Vertrag (Bündelungskennzeichen = 1) mehrere
     * gleiche Sparten unter der selben Versicherungsscheinnummer gebündelt
     * werden, müssen diese im Feld Referenznummer unterschieden werden.
     * </p>
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
