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
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 510.
 * "Verkehrsservice" (Satzart 0220.1/1)
 *
 * @author Ken Schosinsky
 * @since 16.04.2018
 */
public enum Feld220 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 30,
            teildatensatz = 1,
            type = Feld1bis7.class)
    INTRO1,
    
    /**
     * lfd.-Nummer des versicherten Fahrzeuges.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 43)
    LFD_NUMMER_DES_VERSICHERTEN_FAHRZEUGES,

    /**
     * Wagniskennziffer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 44
    )
    WAGNISKENNZIFFER,
    
    /**
     * Wagniskennziffer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 47
            )
    VERTRAGSDAUER,

    /**
     * Geltungsbereich.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 49
    )
    GELTUNGSBEREICH,

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
            nr = 12,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 50)
    VERTRAGSSTATUS,

    /**
     * Beginn.
     * Beginn der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein, muss
     * "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 51)
    BEGINN,

    /**
     * Ausschluss.
     * Ausschluss der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 59)
    AUSSCHLUSS,

    /**
     * Änderungsdatum.
     * Termin, zu dem der Vertragszustand wirksam wird/wurde (Gültig-ab- /
     * Wirksam-ab-Datum).
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 67)
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
            nr = 16,
            type = Zeichen.class,
            byteAdresse = 75
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
            nr = 17,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 76
    )
    HAUPTFAELLIGKEIT,

    /**
     * Waehrungsschluessel.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 84
    )
    WAEHRUNGSSCHLUESSEL,

    /**
     * Beitrag in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 87
    )
    BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Risikotext.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 99
    )
    RISIKOTEXT,

    /**
     * voraussichtliches Ende.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 129
    )
    VORAUSSICHTLICHES_ENDE,

    /**
     * Allgemeine Bedingungen für die Verkehrsservice-Versicherung.
     * Inkraftsetzung beim Versicherungsunternehmen Monat / Jahr (MMJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = Datum.class,
            anzahlBytes = 4,
            byteAdresse = 137)
    ALLGEMEINE_BEDINGUNGEN_FUER_DIE_VERKEHRSSERVICE_VERSICHERUNG,

    /**
     * Sondervereinbarungen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 141
    )
    SONDERVEREINBARUNGEN,

    /**
     * Tarifjahr.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 142
    )
    TARIFJAHR,

    /**
     * Amtliches Kennzeichen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 146
    )
    AMTL_KENNZEICHEN,

    /**
     * Anzahl der versicherten Fahrzeuge.
     * Rechtsbuendig.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 158
    )
    ANZAHL_DER_VERSICHERTEN_FAHRZEUGE,

    /**
     * Zuschlag in %.
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 161)
    ZUSCHLAG_IN_PROZENT,

    /**
     * Zuschlag in Währungseinheiten.
     * (4,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = Betrag.class,
            anzahlBytes = 6,
            byteAdresse = 166)
    ZUSCHLAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlag in %.
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 172)
    ABSCHLAG_IN_PROZENT,

    /**
     * Abschlag in Währungseinheiten.
     * (4,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = Betrag.class,
            anzahlBytes = 6,
            byteAdresse = 177)
    ABSCHLAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Zusätzliche Texte.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 60,
            byteAdresse = 183)
    ZUSAETZLICHE_TEXTE,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 243
    )
    REFERENZNUMMER,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 250)
    LEERSTELLEN

}
