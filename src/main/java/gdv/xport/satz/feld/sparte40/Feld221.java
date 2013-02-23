package gdv.xport.satz.feld.sparte40;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 211, Sparte 40 (
 * "Haftpflicht - Wagnisdaten" (Satzart 0221) ).
 *
 * @author rklemmer
 * @since 20.02.2013
 */
public enum Feld221 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 1,
            type = Feld1bis7.class)
    INTRO1,

    /**
     * Wagnisart.<br/>
     * siehe Anlage 16
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 43)
    WAGNISART,

    /**
     * Ordnungs-Nummer für Wagniszuatz.<br/>
     * Lfd. Nr. bei mehreren Wagnissen gleicher Wagnisart
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 49)
    ORDNUNGS_NUMMER_FUER_WAGNISZUATZ,

    /**
     * Satznummer<br/>
     * konstant 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 51)
    SATZNUMMER,

    /**
     * Deckungssumme 1 in Währungseinheiten.<br/>
     * abweichende Deckungssumme zum Vertrag<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 52)
    DECKUNGSSUMME_1_IN_WAEHRUNGSEINHEITEN,

    /**
     * Deckungssumme 2 in Währungseinheiten.<br/>
     * siehe Erläuterung Deckungssumme 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 66)
    DECKUNGSSUMME_2_IN_WAEHRUNGSEINHEITEN,

    /**
     * Deckungssumme 3 in Währungseinheiten.<br/>
     * siehe Erläuterung Deckungssumme 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 80)
    DECKUNGSSUMME_3_IN_WAEHRUNGSEINHEITEN,

    /**
     * Deckungssumme 4 in Währungseinheiten.<br/>
     * siehe Erläuterung Deckungssumme 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 94)
    DECKUNGSSUMME_4_IN_WAEHRUNGSEINHEITEN,

    /**
     * Referenznummer.<br/>
     *
     * Wenn in einem gebündelten Vertrag (Bündelungskennzeichen = 1) mehrere
     * gleiche Sparten unter der selben Versicherungsscheinnummer gebündelt
     * werden, müssen diese im Feld Referenznummer unterschieden werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 108)
    REFERENZNUMMER,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 142,
            byteAdresse = 115)
    LEERSTELLEN

}
