package gdv.xport.satz.feld;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.feld.*;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 9999
 * (Nachsatz).
 */
public enum Feld9999 {

    /**
     * Satzart.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 1,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 1
    )
    SATZART,

    /**
     * Anzahl der Sätze.
     * ohne Vor-/Nachsatz
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 2,
            type = NumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 5
            )
    ANZAHL_SAETZE,

    /**
     * Geschäftsstelle/Vermittler.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 3,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 15
            )
    VERMITTLER,

    /**
     * Gesamtbeitrag.
     * Addition aller Beiträge ohne Berücksichtigung der Währung aus der Satzart 0200 (13, 2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 4,
            type = Betrag.class,
            anzahlBytes = 15,
            byteAdresse = 25
            )
    GESAMTBEITRAG,

    /**
     * Gesamtbeitrag-Brutto(Inkasso).
     * Addition aller Beiträge ohne Berücksichtigung der Währung aus der Satzart 0400(12, 2 Stellen)
     * Prüfsumme zur Validierung
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 5,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 40
            )
    GESAMTBEITRAG_BRUTTO,

    /**
     * Vorzeichen.
     * - = Negativ
     * + = Positiv
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 6,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 54
            )
    VORZEICHEN,

    /**
     * Gesamtprovisions-Betrag.
     * Addition aller Provisionen ohne Berücksichtigung der Währung aus der Satzart 0400
     * (12, 2 Stellen) (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 7,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 55
            )
    GESAMTPROVISIONSBETRAG,


    /**
     * Vorzeichen.
     * - = Negativ
     * + = Positiv
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 69
            )
    VORZEICHEN2,

    /**
     * Versicherungsleistungen.
     * Addition aller Versicherungsleistungen ohne Berücksichtigung der Währung aus der Satzart 0500
     * (12, 2 Stellen) Prüfsumme zur Validierung
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 70
            )
    VERSICHERUNGSLEISTUNGEN,


    /**
     * Vorzeichen.
     * - = Negativ
     * + = Positiv
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 84
            )
    VORZEICHEN3,

    /**
     * Schadenbearbeitungskosten.
     * Addition aller Schadenbearbeitungskosten ohne Berücksichtigung der Währung aus der Satzart 0500
     * (12, 2 Stellen)
     * Prüfsumme zur Validierung
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 85
            )
    SCHADENBEARBEITUNGSKOSTEN,

    /**
     * Vorzeichen.
     * - = Negativ
     * + = Positiv
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 99
            )
    VORZEICHEN4,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 157,
            byteAdresse = 100
            )
    LEERSTELLEN,

}

