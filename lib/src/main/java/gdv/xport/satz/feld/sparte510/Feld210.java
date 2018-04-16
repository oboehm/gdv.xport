package gdv.xport.satz.feld.sparte510;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 210, Sparte 510.
 * "Verkehrsservice" (Satzart 0210.1/1)
 *
 * @author Ken Schosinsky
 * @since 16.04.2018
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
     * Die jeweils folgende Hauptfaelligkeit des Vertrages zum Zeitpunkt der
     * Lieferung.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00"
     * geschluesselt werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 69
    )
    HAUPTFAELLIGKEIT,

    /**
     * Waehrungsschluessel.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 77
    )
    WAEHRUNGSSCHLUESSEL,

    /**
     * Beitrag in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 80
    )
    BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlussprovision (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 92
    )
    ABSCHLUSSPROVISION,

    /**
     * Kennzeichen fuer abweichende Abschlussprovision.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 97
    )
    KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION,

    /**
     * Folgeprovision (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 98
    )
    FOLGEPROVISION,

    /**
     * Kennzeichen fuer abweichende Folgeprovision.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 103
    )
    KENNZEICHEN_ABWEICHENDE_FOLGEPROVISION,

    /**
     * Restlaufzeit des Vertrages.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = Datum.class,
            anzahlBytes = 2,
            byteAdresse = 104
    )
    RESTLAUFZEIT_DES_VERTRAGES,

    /**
     * Laufzeitrabatt in % (2,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 106
    )
    LAUFZEITRABATT_IN_PROZENT,

    /**
     * Produktform.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 110
    )
    PRODUKTFORM,

    /**
     * Produktform gueltig ab.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 115
    )
    PRODUKTFORM_GUELTIG_AB,

    /**
     * Produktname.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 121
    )
    PRODUKTNAME,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 141
    )
    REFERENZNUMMER,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 109,
            byteAdresse = 148)
    LEERSTELLEN

}
