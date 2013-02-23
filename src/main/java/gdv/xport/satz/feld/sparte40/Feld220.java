package gdv.xport.satz.feld.sparte40;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 40
 * "Haftpflicht - Wagnisdaten /-zusatz" (Satzart 0220).
 *
 * @author rklemmer
 * @since 20.02.2013
 */
public enum Feld220 {

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
     * Satznummer.<br/>
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
     * Berechnungseinheit.<br/>
     * siehe Anlage 9
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 52)
    BERECHNUNGSEINHEIT,

    /**
     * Mengenschlüssel.<br/>
     * Beispiel 1: 1 Öltank, Wagnis Nr. = 0505, Berechnungseinheit = 016,
     * Mengenschlüssel = 1, Wagnismenge = 10<br/>
     * => (10.000 Liter), (Öltank), (CBM Inhalt), (Originalmenge), (10 CBM);<br/>
     * Beispiel 2: 3 Pferde, Wagnis Nr. = 9007, Berechnungseinheit = 027,
     * Mengenschlüssel = 1, Wagnismenge = 3<br/>
     * => (Pferde), (Pferde), (Originalmenge), (3 Pferde);<br/>
     * Beispiel 3: Anschlußgleise Bahnanlage 2.000 mtr Länge, Wagnis Nr. =
     * 0101, Berechnungseinheit = 088, Mengenschlüssel = 4, Wagnismenge = 2 =>
     * (Gleise), (mtr. Bahnlänge), (in 1000), (2000)<br/>
     * siehe Anlage 10
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 55)
    MENGENSCHLUESSEL,

    /**
     * Wagnismenge.<br/>
     * (12,0 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 56)
    WAGNISMENGE,

    /**
     * Währungsschlüssel.<br/>
     * ISO-Code, siehe Anlage 3
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 68)
    WAEHRUNGSSCHLUESSEL,

    /**
     * Beitrag je Berechnungseinheit und Mengenschlüssel.<br/>
     * (10,3 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Betrag.class,
            anzahlBytes = 13,
            byteAdresse = 71)
    BEITRAG_JE_BERECHNUNGSEINHEIT_UND_MENGENSCHLUESSEL,

    /**
     * Mindestbeitrag je Wagnis in Währungseinheiten.<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 84)
    MINDESTBEITRAG_JE_WAGNIS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Zuschlagsbetrag in Währungseinheiten.<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 96)
    ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlagbetrag in Währungseinheiten.<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 108)
    ABSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Gesamtbeitrag in Währungseinheiten.<br/>
     * Gültiger Beitrag unter Berücksichtigung aller Zu- und Abschläge ohne
     * Gebühren und Steuer gemäß Zahlungsweise <br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 120)
    GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN,

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
            nr = 20,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 132)
    SUMMENART_1,

    /**
     * Deckungssumme 1 in Tausend Währungseinheiten.<br/>
     * abweichende Deckungssumme zum Vertrag<br/>
     * es gilt der Währungsschlüssel aus dem zugehörigen 0210er Satz (Feld
     * 13)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 133)
    DECKUNGSSUMME_1_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Summenart 2<br/>
     * siehe Erläuterung Summenart 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 142)
    SUMMENART_2,

    /**
     * Deckungssumme 2 in Tausend Währungseinheiten.<br/>
     * siehe Erläuterung Deckungssumme 1<br/>
     * es gilt der Währungsschlüssel aus dem zugehörigen 0210er Satz (Feld
     * 16)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 143)
    DECKUNGSSUMME_2_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Summenart 3<br/>
     * Details siehe Summenart 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 152)
    SUMMENART_3,

    /**
     * Deckungssumme 3 in Tausend Währungseinheiten.<br/>
     * siehe Erläuterung Deckungssumme 1<br/>
     * es gilt der Währungsschlüssel aus dem zugehörigen 0210er Satz (Feld
     * 19)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 153)
    DECKUNGSSUMME_3_IN_TAUSEND_WAEHRUNGSEINHEITEN,

    /**
     * Summenart 4.<br/>
     * Details siehe Summenart 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 162)
    SUMMENART_4,

    /**
     * Deckungssumme 4 in Tausend Währungseinheiten.<br/>
     * siehe Erläuterung Deckungssumme 1<br/>
     * es gilt der Währungsschlüssel aus dem zugehörigen 0210er Satz (Feld
     * 22)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Betrag.class,
            anzahlBytes = 9,
            byteAdresse = 163)
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
            nr = 28,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 172)
    KENNZEICHEN_FUER_JAHRES_MAXIMIERUNG,

    /**
     * Kennung Erhöhungssatz.<br/>
     * 1 = aufgelaufen, 2 = real
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 173)
    KENNUNG_ERHOEHUNGSSATZ,

    /**
     * Erhöhungssatz § 8, III AHB.<br/>
     * %-Satz der Erhöhung.<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = NumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 174)
    ERHOEHUNGSSATZ_8_III_AHB,

    /**
     * Erhöhungssatz § 8, III AHB.<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt
     * werden <br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 179)
    LETZTE_ERHOEHUNG_8_III_AHB,

    /**
     * Beginn.<br/>
     * Beginn der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein, muss
     * "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 187)
    BEGINN,

    /**
     * Ausschluss.<br/>
     * Ausschluss der Sparte. Sollten Tag und/oder Monat nicht vorhanden sein,
     * muss "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 195)
    AUSSCHLUSS,

    /**
     * Änderungsdatum.<br/>
     * Termin, zu dem der Vertragszustand wirksam wird/wurde (Gültig-ab- /
     * Wirksam-ab-Datum).<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 203)
    AENDERUNGSDATUM,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = AlphaNumFeld.class,
            anzahlBytes = 46,
            byteAdresse = 211)
    LEERSTELLEN,

    // /// Teildatensatz 2 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 2,
            type = Feld1bis7.class)
    INTRO2,

    /**
     * Wagnisart<br/>
     * siehe Anlage 16
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 43)
    WAGNISART2,

    /**
     * Ordnungs-Nummer für Wagniszuatz.<br/>
     * Lfd. Nr. bei mehreren Wagnissen gleicher Wagnisart
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 49)
    ORDNUNGS_NUMMER_FUER_WAGNISZUSATZ2,

    /**
     * Satznummer<br/>
     * konstant 2
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 51)
    SATZNUMMER2,

    /**
     * Wagnistext.<br/>
     * z. B.: Privathaftpflicht
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 50,
            byteAdresse = 52)
    WAGNISTEXT,

    /**
     * Wagnisbeschreibung.<br/>
     * (2 x 40 Stellen) z. B. Risikoanschrift
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 80,
            byteAdresse = 102)
    WAGNISBESCHREIBUNG,

    /**
     * Postleitzahl der Risikoanschrift.<br/>
     * Sollte die Risikoanschrift nicht differenziert geliefert werden können,
     * so kann diese in Datenfeld Wagnisbeschreibung eingestellt werden
     * (linksbündig)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 182)
    POSTLEITZAHL_DER_RISIKOANSCHRIFT,

    /**
     * Risikoort.<br/>
     * siehe Postleitzahl der Risikoanschrift
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 25,
            byteAdresse = 188)
    RISIKOORT,

    /**
     * Risikostraße.<br/>
     * siehe Postleitzahl der Risikoanschrift
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 213)
    RISIKOSTRASSE,

    /**
     * Referenznummer.<br/>
     *
     * Wenn in einem gebündelten Vertrag (Bündelungskennzeichen = 1) mehrere
     * gleiche Sparten unter der selben Versicherungsscheinnummer gebündelt
     * werden, müssen diese im Feld Referenznummer unterschieden werden.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 243)
    REFERENZNUMMER,


    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 250)
    LEERSTELLEN2

}
