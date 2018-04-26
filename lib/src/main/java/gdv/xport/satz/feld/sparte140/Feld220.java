package gdv.xport.satz.feld.sparte140;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
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
    WAGNIS,

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
     * Satznummer.
     * Satznummer. Hier konstant "1"
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
     * Beginn.
     * Beginn der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschluesselt werden
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
     * muss "00" geschluesselt werden.
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
     * Aenderungsdatum.
     * Aenderungsdatum der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschluesselt werden.
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 68)
    AENDERUNGSDAT,

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
     * Postleitzahl der Risikoanschrift.
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
     * Risikoort.
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
     * Risikostrasse.
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
     * Sturmzone.
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
     * Bauartklasse.
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
     * Baujahr.
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
     * Umbauter Raum.
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
     * Fremdnutzung.
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
     * Fremder Grund und Boden.
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
     * Datum der Bezugsfertigkeit.
     * Datum der Bezugsfertigkeit. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschluesselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 158)
    DAT_BEZUGSFERTIGKEIT,


  /**
     * Rohbau-Einmalbetrag.
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
     * Sicherungsglaeubiger.
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
     * Vorsteuerabzugsberechtigt.
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
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 77,
            byteAdresse = 180)
    LEERSTELLEN,

    // /// Teildatensatz 2 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 30,
            teildatensatz = 2,
            type = Feld1bis7.class)
    INTRO2,
    
    /**
     * Wagnis, siehe Anlage 21.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 43)
    WAGNIS2,

    /**
     * Objekt-Nummer, siehe Anlage 21.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 47)
    OBJEKTNUMMER2,

    /**
     * Satznummer.
     * Satznummer. Hier konstant "1"
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 51)
    SATZNUMMER2,
    
    /**
     * Risikokennziffer
     * siehe Anlage 11
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 52)
    RISIKOKENNZIFFER,
    
    /**
     * Objektbeschreibung
     * Wenn abweichend oder ergänzend zur Risikokennziffer
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 57)
    OBJEKTBESCHREIBUNG,
    
    /**
     * Gefahrenschlüssel
     * 4 x 1 Stelle; F = Feuer, W = Leitungswasser, S = Sturm, H = Hagel, siehe Feld 30
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 87)
    GEFAHRENSCHLUESSEL,
    
    /**
     * Art der Summe
     * siehe Anlage 28
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 91)
    ART_DER_SUMME,
    
    /**
     * Basisjahr
     * z. B. 1914 (JJJJ)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 94)
    BASISJAHR,
    
    /**
     * Basisversicherungssumme in Währungseinheiten
     * (12,0 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = NumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 98)
    BASISVERSICHERUNGSSUMME_IN_WE,
    
    /**
     * Baupreisindex / Wertzuschlag
     * (5,1 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 6,
            byteAdresse = 110)
    BAUPREISINDEX_WERTZUSCHLAG,
    
    /**
     * Versicherungssumme aktuell in Währungseinheiten
     * (12,0 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = NumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 116)
    VERSICHERUNGSSUMME_AKTUELL_IN_WE,
    
    /**
     *  Beitragssatz
     * (3,3 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 19,
            type = NumFeld.class,
            nachkommaStellen = 3,
            anzahlBytes = 6,
            byteAdresse = 128)
    BEITRAGSSATZ,
    
    /**
     *  Basisbeitrag in Währungseinheiten
     * z. B. 1914 (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 20,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 134)
    BASISBEITRAG_IN_WE,
    
    /**
     * Prämienrichtzahl
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 21,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 146)
    PRAEMIENRICHTZAHL,
    
    /**
     *  Prämienfaktor
     * (2,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 22,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 150)
    PRÄMIENFAKTOR,
    
    /**
     * Beitrag aktuell in Währungseinheiten
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 23,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 154)
    BEITRAG_AKTUELL_IN_WE,
    
    /**
     * Datum der letzten Summenanpassung
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 24,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 166)
    DATUM_DER_LETZTEN_SUMMENANPASSUNG,
    
    /**
     * Selbstbeteiligung %
     * (2,2 Stellen) Der Selbstbehalt gilt für die Grunddeckung. 
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 25,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 174)
    SELBSTBETEILIGUNG_PROZENT,
    
    /**
     *  Selbstbeteiligung in Währungseinheiten
     * Der Selbstbehalt gilt für die Grunddeckung. (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 26,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 178)
    SELBSTBETEILIGUNG_IN_WE,
    
    /**
     * Besonderheiten
     * 12 x 2 Stellen
     * siehe Anlage 23
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 27,
            type = AlphaNumFeld.class,
            anzahlBytes = 24,
            byteAdresse = 190)
    BESONDERHEITEN,
    
    /**
     * Unterversicherungsverzicht
     * 0 = nein
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 28,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 214)
    UNTERVERSICHERUNGSVERZICHT,
    
    /**
     * Neuwertfaktor
     * (3,3 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 29,
            type = NumFeld.class,
            nachkommaStellen = 3,
            anzahlBytes = 6,
            byteAdresse = 215)
    NEUWERTFAKTOR,
    
    /**
     * Elementarschäden
     * 0 = nein
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 30,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 221)
    ELEMENTARSCHAEDEN,
    
    /**
     * Gebäudetyp
     * siehe Anlage 73
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 222)
    GEBAEUDETYP,
    
    /**
     * Art der Schätzung
     * siehe Anlage 74
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 32,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 224)
    ART_DER_SCHAETZUNG,
    
    /**
     * Wertermittlungs-/Schätzjahr
     * (JJJJ)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 33,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 225)
    WERTERMITTLUNGS_SCHAETZJAHR,
    
    /**
     * Wert 1914 pro qm Wohnfläche in Mark
     * (4,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 34,
            type = Betrag.class,
            anzahlBytes = 6,
            byteAdresse = 229)
    WERT_1914_PRO_QM_WOHNFLAECHE_IN_MARK,
    
    /**
     * Referenznummer
     * Wenn in einem gebündelten Vertrag (Bündelungskennzeichen = 1) mehrere gleiche Sparten unter der selben Versicherungsscheinnummer gebündelt werden, müssen diese im Feld Referenznummer unterschieden werden. 
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 35,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 235)
    REFERENZNUMMER,
    
    /**
     * Denkmalschutz
     * 0 = nein
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 36,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 242)
    DENKMALSCHUTZ,
    
    /**
     * Leerstellen
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 37,
            type = AlphaNumFeld.class,
            anzahlBytes = 13,
            byteAdresse = 243)
    LEERSTELLEN2,
    
    /**
     * Satznummer38
     * Dieses Feld entspricht dem Feld 10 des Teildatensatzes 2. Es ist konstant der Wert 2 einzugeben.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 38,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256)
    SATZNUMMER38
}
