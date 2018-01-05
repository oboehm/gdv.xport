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

package gdv.xport.satz.feld.sparte10.wagnisart5;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 10.
 * "Leben - Risikozusatz = Wagnisart 5" (Satzart 0220).
 *
 * @author ralfklemmer
 * @since 19.01.2013
 */
public enum Feld220Wagnis5 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 1,
            type = Feld1bis7.class)
    INTRO1,

    /**
     * Laufende Nummer der versicherten Person (VP).
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 1, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE,

    /**
     * Wagnisart.
     * 5 = Risikozusatzversicherung
     */
    @FeldInfo(teildatensatz = 1, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
    WAGNISART,

    /**
     * Lfd Nummer zur Wagnisart.
     */
    @FeldInfo(teildatensatz = 1, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART,

    /**
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden.<br>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 11, type = Datum.class, anzahlBytes = 8, byteAdresse = 62)
    BEGINN,

    /**
     * Vertragsablaufdatum (inkl. Abrufphase) Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt
     * werden.
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 12, type = Datum.class, anzahlBytes = 8, byteAdresse = 70)
    ABLAUF,

    /**
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden.
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 13, type = Datum.class, anzahlBytes = 8, byteAdresse = 78)
    AENDERUNG,

    /**
     * JJJ Jahre (lebenslänglich ist mit Wert 999 zu schlüsseln).
     */
    @FeldInfo(teildatensatz = 1, nr = 14, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 86)
    VERTRAGSLAUFZEIT,

    /**
     * 1 = Grundvertrag
     * 2 = Dynamik
     * 3 = Grundvertrag incl. vorletzter Dynamik
     * 4 = Grundvertrag incl. letzter Dynamik.
     */
    @FeldInfo(teildatensatz = 1, nr = 15, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 89)
    VERTRAGSART,

    /**
     * Beitrag
     * Beitrag gem. Zahlungsweise
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 16, type = Betrag.class, anzahlBytes = 12, byteAdresse = 90)
    BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Versicherungssumme in Währungseinheiten.
     * erreichte Summe / tarifliche Vers.summe (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 17, type = NumFeld.class, anzahlBytes = 9, byteAdresse = 102)
    VERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * fallende Summe.
     * 0 = linear, 1 = progressiv
     */
    @FeldInfo(teildatensatz = 1, nr = 18, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 111)
    FALLENDE_SUMME,

    /**
     * Nettobeitrag in Währungseinheiten
     * Beitrag gem. Zahlungsweise nach Überschussanrechnung (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 19, type = Betrag.class, anzahlBytes = 12, byteAdresse = 112)
    NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Status.
     * siehe Anlage 24
     */
    @FeldInfo(teildatensatz = 1, nr = 20, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 124)
    STATUS,

    /**
     * Gewinnverwendungsart.
     * siehe Anlage 71
     */
    @FeldInfo(teildatensatz = 1, nr = 21, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 125)
    GEWINNVERWENDUNGSART,

    /**
     * Risikoeinschränkung.
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 22, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 126)
    RISIKOEINSCHRAENKUNG,

    /**
     * Risikozuschlaege.
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 23, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 127)
    RISIKOZUSCHLAEGE,

    /**
     * Dynamik %-Satz.
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 24, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 128)
    DYNAMIK_PROZENT_SATZ,

    /**
     * Erhöhungsbasis Dynamik.
     * 1 = Beitrag, 2 = Versicherungssumme
     */
    @FeldInfo(teildatensatz = 1, nr = 25, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 133)
    ERHOEHUNGSBASIS_DYNAMIK,

    /**
     * Erhöhungsart Dynamik.
     * siehe Anlage 72
     */
    @FeldInfo(teildatensatz = 1, nr = 26, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 134)
    ERHOEHUNGSART_DYNAMIK,

    /**
     * Dynamikstop.
     * Datum, ab dem keine Dynamikerhöhung mehr möglich ist.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 27, type = Datum.class, anzahlBytes = 8, byteAdresse = 135)
    DYNAMIKSTOP,

    /**
     * Datum der letzten positiven Dynamik.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 28, type = Datum.class, anzahlBytes = 8, byteAdresse = 143)
    DAT_LETZTEN_POSITIVEN_DYNAMIK,

    /**
     * Jahresrente in Wöhrungseinheiten.
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 29, type = Betrag.class, anzahlBytes = 12, byteAdresse = 151)
    JAHRESRENTE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Endalter.
     * JJJ Alter Lebenslänglich ist mit 999 zu schlüsseln
     */
    @FeldInfo(teildatensatz = 1, nr = 30, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 163)
    ENDALTER,

    /**
     * Eintrittsalter.
     * JJ Alter
     */
    @FeldInfo(teildatensatz = 1, nr = 31, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 166)
    EINTRITTSALTER,

    /**
     * Rente inklusiv Überschussanrechnung in Währungseinheiten.
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 32, type = Betrag.class, anzahlBytes = 12, byteAdresse = 168)
    RENTE_INKL_UEBERSCHUSSANRECHNUNG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Umtauschrecht.
     * 0 = ja, 1 = nein
     */
    @FeldInfo(teildatensatz = 1, nr = 33, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 180)
    UMTAUSCHRECHT,

    /**
     * Abweichende Vertragslaufzeit.
     * Abgekürzte oder verlängerte Vertragslaufzeit Anzahl Jahre (JJJ).
     * Lebenslänglich ist mit "999" zu schlüsseln
     */
    @FeldInfo(teildatensatz = 1, nr = 34, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 181)
    ABWEICHENDE_VERTRAGSLAUFZEIT,

    /**
     * Abweichender Ablauf.
     * Ablauf abgekürzt oder verlängert Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt
     * werden Tag/Monat/Jahr
     * (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 35, type = Datum.class, anzahlBytes = 8, byteAdresse = 184)
    ABWEICHENDER_ABLAUF,

    /**
     * Risikozuschlag in Währungseinheiten
     * Risikozuschlag gem. Zahlungsweise
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 36, type = Betrag.class, anzahlBytes = 12, byteAdresse = 192)
    RISIKOZUSCHLAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Leerstellen.
     */
    @FeldInfo(teildatensatz = 1, nr = 37, type = AlphaNumFeld.class, anzahlBytes = 52, byteAdresse = 204)
    LEERSTELLEN,

    // /// Teildatensatz 2 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 2,
            type = Feld1bis7.class)
    INTRO2,

    /**
     * Lfd. Nummer der versicherten Person (VP).
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 2, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE2,

    /**
     * Wagnisart.
     * 5 = Risikozusatzversicherung
     */
    @FeldInfo(teildatensatz = 2, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
    WAGNISART2,

    /**
     * Lfd Nummer zur Wagnisart.
     * siehe Anlage 15
     */
    @FeldInfo(teildatensatz = 2, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART2,

    /**
     * Tarifbezeichnung.
     * Klartextbezeichnung des Tarifs
     */
    @FeldInfo(teildatensatz = 2, nr = 11, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 62)
    TARIFBEZEICHNUNG,

    /**
     * Tarifbezeichnung des Folgetarifs.
     * Klartextbezeichnung des Folgetarifs (wenn der Tarif noch nicht umgestellt wurde)
     */
    @FeldInfo(teildatensatz = 2, nr = 12, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 92)
    TARIFBEZEICHNUNG_DES_FOLGETARIFS,

    /**
     * Umstellungsdatum des Folgetarifs.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 13, type = Datum.class, anzahlBytes = 8, byteAdresse = 122)
    UMSTELLUNGSDAT_FOLGETARIFS,

    /**
     * Zukünftiger Beitrag in Währungseinheiten.
     * gem. Zahlungsweise wenn der Beitrag noch nicht umgestellt wurde
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 14, type = Betrag.class, anzahlBytes = 12, byteAdresse = 130)
    ZUKUENFTIGER_BEITRAG,

    /**
     * Vertragsbedingung.
     * Individuelle Vertragsbedingung (frei definierbar)
     */
    @FeldInfo(teildatensatz = 2, nr = 15, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 142)
    VERTRAGSBEDINGUNG,

    /**
     * Dynamikbeginn
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden.
     * Datum der ersten Dynamikerhöhung
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 16, type = Datum.class, anzahlBytes = 8, byteAdresse = 172)
    DYNAMIKBEGINN,

    /**
     * Abweichendes Dynamikendalter.
     * Wenn abweichend von normalem Endalter
     * (JJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 17, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 180)
    ABWEICHENDES_DYNAMIKENDALTER,

    /**
     * Absoluter Dynamikerhöhungsbetrag in Währungseinheiten.
     * (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 18, type = Betrag.class, anzahlBytes = 9, byteAdresse = 183)
    ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Anteiliger Dynamikprozentsatz.
     * Prozentualer Anteil am Dynamikerhöhungsbetrag Sozialversicherung
     * prozentual oder absolut bzw. Gehaltsanpassung;
     * z. B.: 100,00 = volle absolute BfA-Dynamik, 50,00 = halbe absolute
     * BfA-Dynamik
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 19, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 192)
    ANTEILIGER_DYNAMIKPROZENTSATZ,

    /**
     * Vereinbarter Dynamikmindestanpassungsprozentsatz.
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 20, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 197)
    VEREINBARTER_DYNAMIKMINDESTANPASSUNGSPROZENTSATZ,

    /**
     * Vereinbarter Dynamikmaximalanpassungsprozentsatz.
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 21, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 202)
    VEREINBARTER_DYNAMIKMAXIMALANPASSUNGSPROZENTSATZ,

    /**
     * Anzahl verbleibende Dynamikwidersprüche.
     * Anzahl der verbleibenden Dynamikwidersprüche, ohne das Recht auf Dynamikerhöhung zu verlieren
     */
    @FeldInfo(teildatensatz = 2, nr = 22, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 207)
    ANZAHL_VERBLEIBENDE_DYNAMIKWIDERSPRUECHE,

    /**
     * Leerstellen.
     */
    @FeldInfo(teildatensatz = 2, nr = 23, type = AlphaNumFeld.class, anzahlBytes = 48, byteAdresse = 208)
    LEERSTELLEN2

}
