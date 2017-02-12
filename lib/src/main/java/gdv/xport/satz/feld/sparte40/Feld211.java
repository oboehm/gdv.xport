package gdv.xport.satz.feld.sparte40;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 211, Sparte 40 (
 * "Haftpflicht - Vertragsspezifischer Teil - Erweiterungssatz für Satzart: Euro-/Währungsfähigkeit"
 * ).
 *
 * @author rklemmer
 * @since 20.02.2013
 */
public enum Feld211 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 40,
            teildatensatz = 1,
            type = Feld1bis7.class)
    INTRO1,

    /**
     * Deckungssumme 1 in Währungseinheiten.
     * Deckungssummen die dem Vertrag zugrunde liegen.
     * Sollten unterschiedliche Deckungssummen vereinbart sein, so sind diese in
     * den Wagnisteilen (Satzart 0220) zu schlüsseln
     * (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 43)
    DECKUNGSSUMME_1_IN_WAEHRUNGSEINHEITEN,

    /**
     * Deckungssumme 2 in Währungseinheiten.
     * s. Erläuterung Deckungssumme 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 57)
    DECKUNGSSUMME_2_IN_WAEHRUNGSEINHEITEN,

    /**
     * Deckungssumme 3 in Währungseinheiten.
     * s. Erläuterung Deckungssumme 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 71)
    DECKUNGSSUMME_3_IN_WAEHRUNGSEINHEITEN,

    /**
     * Deckungssumme 4 in Währungseinheiten.
     * s. Erläuterung Deckungssumme 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 85)
    DECKUNGSSUMME_4_IN_WAEHRUNGSEINHEITEN,


    /**
     * Referenznummer.
     *
     * Wenn in einem gebündelten Vertrag (Bündelungskennzeichen = 1) mehrere
     * gleiche Sparten unter der selben Versicherungsscheinnummer gebündelt
     * werden, müssen diese im Feld Referenznummer unterschieden werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 99)
    REFERENZNUMMER,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 150,
            byteAdresse = 106)
    LEERSTELLEN,


    /**
     * Satznummer.
     */
    @FeldInfo(
          teildatensatz = 1,
          nr = 14,
          type = Zeichen.class,
          anzahlBytes = 1,
          byteAdresse = 256
    )
    SATZNUMMER

}
