package gdv.xport.satz.feld.sparte140;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 140.
 * "Gebauede" (Satzart 0220.1/1)
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
     * Wagnis, siehe Anlage 21.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 43)
    Wagnis,

    /**
     * Objekt-Nummer, siehe Anlage 21.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 47)
    OBJEKTNUMMER,

    /**
     * Beginn.<br/>
     * Beginn der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein, muss
     * "00" geschluesselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 51)
    SATZNUMMER,

    /**
     * Beginn.<br/>
     * Beginn der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschluesselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 52)
    BEGINN,

    /**
     * Ausschluss der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschluesselt werden.<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 60)
    AUSSCHLUSS,

    /**
     * Aenderungsdatum.<br/>
     * Aenderungsdatum der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschluesselt werden.<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 68)
    AENDERUNGSDATUM,

    /**
     * KFZ-Laenderkennzeichen, zum Beispiel Laenderkennzeichen fuer
     * D = Deutschland, B = Belgien, DK = Daenemark, F = Frankreich,
     * CDN = Kanada.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 76
    )
    LAENDERKENNZEICHEN,


    /**
     * Postleitzahl der Risikoanschrift.<br/>
     * linksbuendig
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 79)
    POSTLEITZAHL_DER_RISIKOANSCHRIFT,

    /**
     * Risikoort.<br/>
     * Wenn Risikoanschrift abweichend vom Wohnort
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 25,
            byteAdresse = 85)
    RISIKOORT,

    /**
     * Risikostrasse.<br/>
     * siehe Erlaeuterung Risikoort
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 110)
    RISIKOSTRASSE,

    /**
     * Sturmzone.<br/>
     * Verbandsschluessel siehe Anlage 22.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 140)
    STURMZONE,

  /**
     * Bauartklasse.<br/>
     * siehe Anlage 12
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 141)
    BAUARTKLASSE,

   /**
     * Baujahr.<br/>
     * JJJJ (Jahr)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 142
    )
    BAUJAHR,


   /**
     * Umbauter Raum.<br/>
     * in CBM, bei VGB 88 in qm^2
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 146
    )
    UMBAUTER_RAUM,


   /**
     * Stockwerke.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 151
    )
    STOCKWERKE,


   /**
     * Anzahl Wohneinheiten.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 153
    )
    ANZAHL_WOHNEINHEITEN,

   /**
     * Fremdnutzung.<br/>
     * 0 = nein
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 156
    )
    FREMDNUTZUNG,


   /**
     * Fremder Grund und Boden.<br/>
     * 0 = nein
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 157
    )
    FREMDER_GRUND_UND_BODEN,


    /**
     * Datum der Bezugsfertigkeit.<br/>
     * Datum der Bezugsfertigkeit. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschluesselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 158)
    DATUM_DER_BEZUGSFERTIGKEIT,


  /**
     * Rohbau-Einmalbetrag.<br/>
     * Rohbau-Einmalbetrag in Waehrungseinheiten (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = NumFeld.class,
            anzahlBytes = 12,
            nachkommaStellen = 2,
            byteAdresse = 166)
    ROHBAU_EINMALBETRAG,

  /**
     * Sicherungsglaeubiger.<br/>
     * 0 = nein
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 178
    )
    SICHERUNGSGLAEUBIGER,

  /**
     * Vorsteuerabzugsberechtigt.<br/>
     * 0 = nein
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 179
    )
    VORSTEUERABZUGSBERECHTIGT,

    /**
     * Leerstellen.<br/>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 77,
            byteAdresse = 180)
    LEERSTELLEN
}
