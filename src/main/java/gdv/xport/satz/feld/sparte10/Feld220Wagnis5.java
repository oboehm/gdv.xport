/*
 * Copyright (c) 2011, 2012 by Oli B.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 23.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.feld.sparte10;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 10 <br/>
 * "Leben - Risikozusatz = Wagnisart 5" (Satzart 0220).
 * 
 * @author ralfklemmer
 * @since 19.01.2013
 */
public enum Feld220Wagnis5 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(teildatensatz = 1, type = Feld1bis7.class)
    INTRO1,

    /**
     * Lfd. Nummer der versicherten Person (VP).<br/>
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 1, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE,

    /**
     * Wagnisart.<br/>
     * 5 = Risikozusatzversicherung
     */
    @FeldInfo(teildatensatz = 1, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
    WAGNISART,

    /**
     * Lfd Nummer zur Wagnisart.<br/>
     */
    @FeldInfo(teildatensatz = 1, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART,

    /**
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden <br>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 11, type = Datum.class, anzahlBytes = 8, byteAdresse = 62)
    BEGINN,

    /**
     * Vertragsablaufdatum (inkl. Abrufphase) Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt
     * werden.<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 12, type = Datum.class, anzahlBytes = 8, byteAdresse = 70)
    ABLAUF,

    /**
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 13, type = Datum.class, anzahlBytes = 8, byteAdresse = 78)
    AENDERUNG,

    /**
     * JJJ Jahre (lebenslänglich ist mit Wert 999 zu schlüsseln)
     */
    @FeldInfo(teildatensatz = 1, nr = 14, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 86)
    VERTRAGSLAUFZEIT,

    /**
     * 1 = Grundvertrag<br/>
     * 2 = Dynamik<br/>
     * 3 = Grundvertrag incl. vorletzter Dynamik<br/>
     * 4 = Grundvertrag incl. letzter Dynamik.
     */
    @FeldInfo(teildatensatz = 1, nr = 15, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 89)
    VERTRAGSART,

    /**
     * Beitrag<br/>
     * Beitrag gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 16, type = Betrag.class, anzahlBytes = 12, byteAdresse = 90)
    BEITRAG,

    /**
     * Versicherungssumme in Währungseinheiten<br/>
     * erreichte Summe / tarifliche Vers.summe (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 17, type = NumFeld.class, anzahlBytes = 9, byteAdresse = 102)
    VERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * fallende Summe.<br/>
     * 0 = linear, 1 = progressiv
     */
    @FeldInfo(teildatensatz = 1, nr = 18, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 111)
    FALLENDE_SUMME,

    /**
     * Nettobeitrag in Währungseinheiten<br/>
     * Beitrag gem. Zahlungsweise nach Überschussanrechnung (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 19, type = Betrag.class, anzahlBytes = 12, byteAdresse = 112)
    NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Status.<br/>
     * siehe Anlage 24
     */
    @FeldInfo(teildatensatz = 1, nr = 20, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 124)
    STATUS,

    /**
     * Gewinnverwendungsart.<br/>
     * siehe Anlage 71
     */
    @FeldInfo(teildatensatz = 1, nr = 21, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 125)
    GEWINNVERWENDUNGSART,

    /**
     * Risikoeinschränkung.<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 22, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 126)
    RISIKOEINSCHRAENKUNG,

    /**
     * Risikozuschlaege.<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 23, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 127)
    RISIKOZUSCHLAEGE,

    /**
     * Dynamik %-Satz<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 24, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 128)
    DYNAMIK_PROZENT_SATZ,

    /**
     * Erhöhungsbasis Dynamik<br/>
     * 1 = Beitrag, 2 = Versicherungssumme
     */
    @FeldInfo(teildatensatz = 1, nr = 25, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 133)
    ERHOEHUNGSBASIS_DYNAMIK,

    /**
     * Erhöhungsart Dynamik<br/>
     * siehe Anlage 72
     */
    @FeldInfo(teildatensatz = 1, nr = 26, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 134)
    ERHOEHUNGSART_DYNAMIK,

    /**
     * Dynamikstop<br/>
     * Datum, ab dem keine Dynamikerhöhung mehr möglich ist.<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 27, type = Datum.class, anzahlBytes = 8, byteAdresse = 135)
    DYNAMIKSTOP,

    /**
     * Datum der letzten positiven Dynamik<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 28, type = Datum.class, anzahlBytes = 8, byteAdresse = 143)
    DATUM_DER_LETZTEN_POSITIVEN_DYNAMIK,

    /**
     * Jahresrente in Wöhrungseinheiten<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 29, type = Betrag.class, anzahlBytes = 12, byteAdresse = 151)
    JAHRESRENTE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Endalter<br/>
     * JJJ Alter Lebenslänglich ist mit 999 zu schlüsseln
     */
    @FeldInfo(teildatensatz = 1, nr = 30, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 163)
    ENDALTER,

    /**
     * Eintrittsalter<br/>
     * JJ Alter
     */
    @FeldInfo(teildatensatz = 1, nr = 31, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 166)
    EINTRITTSALTER,

    /**
     * Rente inkl. Überschussanrechnung in Währungseinheiten<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 32, type = Betrag.class, anzahlBytes = 12, byteAdresse = 168)
    RENTE_INKL_UEBERSCHUSSANRECHNUNG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Umtauschrecht<br/>
     * 0 = ja, 1 = nein
     */
    @FeldInfo(teildatensatz = 1, nr = 33, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 180)
    UMTAUSCHRECHT,

    /**
     * Abweichende Vertragslaufzeit<br/>
     * Abgekürzte oder verlängerte Vertragslaufzeit Anzahl Jahre (JJJ).<br/>
     * Lebenslänglich ist mit "999" zu schlüsseln
     */
    @FeldInfo(teildatensatz = 1, nr = 34, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 181)
    ABWEICHENDE_VERTRAGSLAUFZEIT,

    /**
     * Abweichender Ablauf<br/>
     * Ablauf abgekürzt oder verlängert Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt
     * werden Tag/Monat/Jahr<br/>
     * (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 35, type = Datum.class, anzahlBytes = 8, byteAdresse = 184)
    ABWEICHENDER_ABLAUF,

    /**
     * Risikozuschlag in Währungseinheiten<br/>
     * Risikozuschlag gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 36, type = Betrag.class, anzahlBytes = 12, byteAdresse = 192)
    RISIKOZUSCHLAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Leerstellen.<br/>
     */
    @FeldInfo(teildatensatz = 1, nr = 37, type = AlphaNumFeld.class, anzahlBytes = 52, byteAdresse = 204)
    LEERSTELLEN,

    // /// Teildatensatz 2 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(teildatensatz = 2, type = Feld1bis7.class)
    INTRO2,

    /**
     * Lfd. Nummer der versicherten Person (VP).<br/>
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 2, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE2,

    /**
     * Wagnisart.<br/>
     * 5 = Risikozusatzversicherung
     */
    @FeldInfo(teildatensatz = 2, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
    WAGNISART2,

    /**
     * Lfd Nummer zur Wagnisart.<br/>
     * siehe Anlage 15
     */
    @FeldInfo(teildatensatz = 2, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART2,

    /**
     * Tarifbezeichnung<br/>
     * Klartextbezeichnung des Tarifs
     */
    @FeldInfo(teildatensatz = 2, nr = 11, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 62)
    TARIFBEZEICHNUNG,
    /**
     * Tarifbezeichnung des Folgetarifs<br/>
     * Klartextbezeichnung des Folgetarifs (wenn der Tarif noch nicht umgestellt wurde)
     */
    @FeldInfo(teildatensatz = 2, nr = 12, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 92)
    TARIFBEZEICHNUNG_DES_FOLGETARIFS,

    /**
     * Umstellungsdatum des Folgetarifs<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 13, type = Datum.class, anzahlBytes = 8, byteAdresse = 122)
    UMSTELLUNGSDATUM_DES_FOLGETARIFS,

    /**
     * Zukünftiger Beitrag in Währungseinheiten<br/>
     * gem. Zahlungsweise wenn der Beitrag noch nicht umgestellt wurde<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 14, type = Betrag.class, anzahlBytes = 12, byteAdresse = 130)
    ZUKUENFTIGER_BEITRAG,

    /**
     * Vertragsbedingung<br/>
     * Individuelle Vertragsbedingung (frei definierbar)
     */
    @FeldInfo(teildatensatz = 2, nr = 15, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 142)
    VERTRAGSBEDINGUNG,

    /**
     * Dynamikbeginn<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden.<br/>
     * Datum der ersten Dynamikerhöhung<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 16, type = Datum.class, anzahlBytes = 8, byteAdresse = 172)
    DYNAMIKBEGINN,

    /**
     * Abweichendes Dynamikendalter<br/>
     * Wenn abweichend von normalem Endalter<br/>
     * (JJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 17, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 180)
    ABWEICHENDES_DYNAMIKENDALTER,

    /**
     * Absoluter Dynamikerhöhungsbetrag in Währungseinheiten<br/>
     * (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 18, type = Betrag.class, anzahlBytes = 9, byteAdresse = 183)
    ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAERHUNGSEINHEITEN,

    /**
     * Anteiliger Dynamikprozentsatz<br/>
     * Prozentualer Anteil am Dynamikerhöhungsbetrag Sozialversicherung prozentual oder absolut bzw. Gehaltsanpassung;<br/>
     * z. B.: 100,00 = volle absolute BfA-Dynamik, 50,00 = halbe absolute BfA-Dynamik<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 19, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 192)
    ANTEILIGER_DYNAMIKPROZENTSATZ,

    /**
     * Vereinbarter Dynamikmindestanpassungsprozentsatz<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 20, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 197)
    VEREINBARTER_DYNAMIKMINDESTANPASSUNGSPROZENTSATZ,

    /**
     * Vereinbarter Dynamikmaximalanpassungsprozentsatz<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 21, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 202)
    VEREINBARTER_DYNAMIKMAXIMALANPASSUNGSPROZENTSATZ,

    /**
     * Anzahl verbleibende Dynamikwidersprüche<br/>
     * Anzahl der verbleibenden Dynamikwidersprüche, ohne das Recht auf Dynamikerhöhung zu verlieren
     */
    @FeldInfo(teildatensatz = 2, nr = 22, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 207)
    ANZAHL_VERBLEIBENDE_DYNAMIKWIDERSPRUECHE,

    /**
     * Leerstellen.<br/>
     */
    @FeldInfo(teildatensatz = 2, nr = 23, type = AlphaNumFeld.class, anzahlBytes = 48, byteAdresse = 208)
    LEERSTELLEN2
}
