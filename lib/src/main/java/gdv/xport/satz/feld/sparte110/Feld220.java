package gdv.xport.satz.feld.sparte110;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 110.
 * "Glas" (Satzart 0220.1/1)
 *
 * @author Frank Berger
 * @since 17.01.2014
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
     * Beginn.
     * Beginn der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschluesselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 43)
    BEGINN,

    /**
     * AUSSCHLUSS.
     * AUSSCHLUSS der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschluesselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 51)
    AUSSCHLUSS,

    /**
     * Aenderungsdatum.
     * Aenderungsdatum der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschluesselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 59)
    AENDERUNGSDAT,

    /**
     * KFZ-Laenderkennzeichen, zum Beispiel Laenderkennzeichen fuer
     * D = Deutschland, B = Belgien, DK = Daenemark, F = Frankreich,
     * CDN = Kanada.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 67
    )
    LAENDERKENNZEICHEN,


    /**
     * Postleitzahl der Risikoanschrift.
     * linksbuendig
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 70)
    POSTLEITZAHL_DER_RISIKOANSCHRIFT,

    /**
     * Risikoort.
     * Wenn Risikoanschrift abweichend vom Wohnort
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 25,
            byteAdresse = 76)
    RISIKOORT,

    /**
     * Risikostrasse.
     * siehe Erlaeuterung Risikoort
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 101)
    RISIKOSTRASSE,

    /**
     * Form.
     * 1 = gewerblich
     * 2 = privat
     * 3 = Werbeanlagen
     * 9 = Sonstige
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 131)
    FORM,


    /**
     * Formart.
     * 1 = Gebauudeverglasung
     * 2 = Mobiliarverglasung
     * 3 = Gebauude- und Mobiliarverglasung
     * 9 = sonstige
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 132)
    FORMART,


    /**
     * AUSSCHLUSS2.
     * 01 = Mehrscheibenisolierverglasung
     * 02 = kuenstlerisch bearbeitete Glaeser
     * 03 = Scheiben ueber 4 qm
     * 99 = Sonstige
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 133)
    AUSSCHLUSS2,


    /**
     * Risiko.
     * siehe Anlage 19
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 135)
    RISIKO,


    /**
     * Risikokennziffer.
     * siehe Anlage 11
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 137)
    RISIKOKENNZIFFER,


    /**
     * Berechnungsart
     * siehe Anlage 20
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 142)
    BERECHNUNGSART,


    /**
     * Versicherungssummer in Waehrungseinheiten.
     * Versicherungssumme in Waehrungseinheiten
     * (12,0 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = NumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 144)
    VERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN,


   /**
     * qm.
     * gemaess Berechnungsart
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 156 )
    QM,


   /**
     * Beitragssatz fuer Berechnungsart.
     * Promille-Satz fuer Versicherungssumme
     * (3,3 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = NumFeld.class,
            anzahlBytes = 6,
            nachkommaStellen = 3,
            byteAdresse = 161 )
    BEITRAGSSATZ_FUER_BERECHNUNGSART,


   /**
     * Beitrag in Waehrungseinheiten.
     * Beitrag unter Beruecksichtigung aller Zu- und Abschlaege ohne Gebuehr und
     * Vers.-Steuer gemaess Zahlungsweise in Waehrungseinheiten
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = NumFeld.class,
            anzahlBytes = 12,
            nachkommaStellen = 2,
            byteAdresse = 167
    )
    BEITRAG,


   /**
     * Selbstbeteiligung %-Satz
     * (2,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = NumFeld.class,
            anzahlBytes = 4,
            nachkommaStellen = 2,
            byteAdresse = 179 )
    SELBSTBETEILIGUNG_IN_PROZENT,


   /**
     * Selbstbeteiligung in Waehrungseinheiten.
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = NumFeld.class,
            anzahlBytes = 12,
            nachkommaStellen = 2,
            byteAdresse = 183 )
    SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN,


    /**
     * Glaspreisangleichung.
     * 0 = nein
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 195
    )
    GLASPREISANGLEICHUNG,


   /**
     * %-Satz Erhoehung der letzten Glaspreisangleichung.
     * (2,3 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = NumFeld.class,
            anzahlBytes = 5,
            nachkommaStellen = 3,
            byteAdresse = 196 )
    ERHOEHUNGSSATZ_LETZTER_GLASPREISANGLEICHUNG,


   /**
     * Datum der letzten Glaspreisangleichung.
     * Datum der letzten Glaspreisangleichung. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschluesselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 201)
    DAT_LETZTER_GLASPREISANGLEICHUNG,


   /**
     * Pauschale Glasversicherung ohne Angabe der Versicherungssumme.
     * 0 = nein
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 209
    )
    PAUSCHALE_GLASVERSICHERUNG_OHNE_VERSICHERUNGSSUMME,

    /**
     * Risiko laufende Nummer.
     * Risiko lfd. Nummer. Zur eindeutigen Identifizierung der versicherten Risiken innerhalb der Satzart 220
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 210)
    LFD_NUMMER_RISIKO,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 41,
            byteAdresse = 216)
    LEERSTELLEN
}
