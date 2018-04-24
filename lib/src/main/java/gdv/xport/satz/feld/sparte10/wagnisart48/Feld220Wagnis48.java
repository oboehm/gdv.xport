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

package gdv.xport.satz.feld.sparte10.wagnisart48;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 10.
 * Leben - Berufsunfähigkeit = Wagnisart 4 u. 8" (Satzart 0220).
 *
 * @author ralfklemmer
 * @since 19.01.2013
 */
public enum Feld220Wagnis48 {

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
     * 4 = BUZ, 8 = Selbständige Berufsunfähigkeitsvers.
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
     * 1 = Grundvertrag.
     * 2 = Dynamik.
     * 3 = Grundvertrag incl. vorletzter Dynamik.
     * 4 = Grundvertrag incl. letzter Dynamik.
     */
    @FeldInfo(teildatensatz = 1, nr = 15, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 89)
    VERTRAGSART,

    /**
     * Beitrag BU in Währungseinheiten Beitrag gemaess Zahlungsweise (10,2 Stellen).
     */
    @FeldInfo(teildatensatz = 1, nr = 16, type = Betrag.class, anzahlBytes = 12, byteAdresse = 90)
    BEITRAG_BU_IN_WAEHRUNGSEINHEITEN,

    /**
     * Nettobeitrag in Währungseinheiten Beitrag gemaess Zahlungsweise nach
     * Überschussanrechnung (10,2 Stellen).
     */
    @FeldInfo(teildatensatz = 1, nr = 17, type = NumFeld.class, anzahlBytes = 12, byteAdresse = 102)
    NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Status.
     * siehe Anlage 24
     */
    @FeldInfo(teildatensatz = 1, nr = 18, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 114)
    STATUS,

    /**
     * Gewinnverwendungsart.
     * siehe Anlage 71
     */
    @FeldInfo(teildatensatz = 1, nr = 19, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 115)
    GEWINNVERWENDUNGSART,

    /**
     * Risikoeinschränkung.
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 20, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 116)
    RISIKOEINSCHRAENKUNG,

    /**
     * Risikozuschläge.
     * 1 = Berufssparte, 2 = medizinisch, 9 = Sonstige
     */
    @FeldInfo(teildatensatz = 1, nr = 21, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 117)
    RISIKOZUSCHLAEGE,

    /**
     * Dynamik %-Satz.
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 22, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 118)
    DYNAMIK_PROZENT_SATZ,

    /**
     * Erhöhungsbasis Dynamik.
     * 1 = Beitrag, 2 = Versicherungssumme
     */
    @FeldInfo(teildatensatz = 1, nr = 23, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 123)
    ERHOEHUNGSBASIS_DYNAMIK,

    /**
     * Erhöhungsart Dynamik.
     * siehe Anlage 72
     */
    @FeldInfo(teildatensatz = 1, nr = 24, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 124)
    ERHOEHUNGSART_DYNAMIK,

    /**
     * Dynamikstop.
     * Datum, ab dem keine Dynamikerhöhung mehr möglich ist.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 25, type = Datum.class, anzahlBytes = 8, byteAdresse = 125)
    DYNAMIKSTOP,

    /**
     * Datum der letzten positiven Dynamik.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 26, type = Datum.class, anzahlBytes = 8, byteAdresse = 133)
    DAT_LETZTEN_POSITIVEN_DYNAMIK,

    /**
     * Jahresrente in Währungseinheiten.
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 27, type = Betrag.class, anzahlBytes = 12, byteAdresse = 141)
    JAHRESRENTE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Endalter.
     * JJJ Alter Lebenslänglich ist mit 999 zu schlüsseln
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 153)
    ENDALTER,

    /**
     * Eintrittsalter.
     * JJ Alter, Zeitpunkt zu dem BUZ einbezogen wird
     */
    @FeldInfo(teildatensatz = 1, nr = 29, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 156)
    EINTRITTSALTER,

    /**
     * Einschluss %-Satz.
     * Anteil an der BUZ-Jahresrente an der VS
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 30, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 158)
    EINSCHLUSS_PROZENT_SATZ,

    /**
     * Zwang zur BUZ.
     * (z. B. Finanzierungen)
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 31, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 163)
    ZWANG_ZUR_BUZ,

    /**
     * Leistungsdauer.
     * Endedatum der Leistungsdauer, Monat / Jahr (MMJJ).
     * Sollte Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     */
    @FeldInfo(teildatensatz = 1, nr = 32, type = NumFeld.class, anzahlBytes = 4, byteAdresse = 164)
    LEISTUNGSDAUER,

    /**
     * BUZ Verwendungsart.
     * 0 = Beitragsfreiheit bei BU
     * 1 = Rente bei BU
     * 2 = Einmalzahlung bei BU
     */
    @FeldInfo(teildatensatz = 1, nr = 33, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 168)
    BUZ_VERWENDUNGSART,

    /**
     * Rente incl. Überschussbeteiligung in Währungseinheiten.
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 34, type = Betrag.class, anzahlBytes = 12, byteAdresse = 169)
    RENTE_INCL_UEBERSCHUSSBETEILIGUNG_IN_WAEHRUNGSEINHEITEN,

    /**
     * BUZ Leistung von.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 35, type = Datum.class, anzahlBytes = 8, byteAdresse = 181)
    BUZ_LEISTUNG_VON,

    /**
     * BUZ Leistung bis.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 36, type = Datum.class, anzahlBytes = 8, byteAdresse = 189)
    BUZ_LEISTUNG_BIS,

    /**
     * BUZ Prozent-Satz.
     * Prozent-Satz, ab dem BUZ-Leistung vereinbart ist
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 37, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 197)
    BUZ_PROZENT_SATZ,

    /**
     * Abweichende Vertragslaufzeit.
     * Abgekürzte oder verlängerte Vertragslaufzeit. Anzahl Jahre (JJJ), lebenslänglich ist mit "999" zu schlüsseln
     */
    @FeldInfo(teildatensatz = 1, nr = 38, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 202)
    ABWEICHENDE_VERTRAGSLAUFZEIT,

    /**
     * Abweichender Ablauf.
     * Ablauf abgekürzt oder verlängert Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt
     * werden Tag/Monat/Jahr
     * (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 39, type = Datum.class, anzahlBytes = 8, byteAdresse = 205)
    ABWEICHENDER_ABLAUF,

    /**
     * Risikozuschlag in Währungseinheiten.
     * Risikozuschlag gem. Zahlungsweise
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 40, type = Betrag.class, anzahlBytes = 12, byteAdresse = 213)
    RISIKOZUSCHLAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Karenzzeit.
     * Anzahl Monate (MM)
     */
    @FeldInfo(teildatensatz = 1, nr = 41, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 225)
    KARENZZEIT,

    /**
     * Abweichende Leistungsdauer.
     * Anzahl Jahre (JJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 42, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 227)
    ABWEICHENDE_LEISTUNGSDAUER,
    
    /**
     * Garantierter Steigerungssatz bei BU.
     * Garantierter Steigerungssatz bei "Beitragsübernahme" durch den VU bei BU (Beitrag/Leistung) (3,2)
     */
    @FeldInfo(teildatensatz = 1, nr = 43, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 229)
    GARANTIERTER_STEIGERUNGSSATZ_BEI_BU,
    
    /**
     * Steigerungssatz für BU-Rente
     * Garantierter Steigerungssatz bei "Beitragsübernahme" durch den VU bei BU (BUZ-Rente) (3,2)
     */
    @FeldInfo(teildatensatz = 1, nr = 44, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 234)
    STEIGERUNGSSATZ_FUER_BU_RENTE,

    /**
     * Leerstellen.
     */
    @FeldInfo(teildatensatz = 1, nr = 45, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 239)
    LEERSTELLEN,

    // /// Teildatensatz 2 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 2,
            type = Feld1bis7.class)
    INTRO2,

    /**
     * Laufende Nummer der versicherten Person (VP).
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 2, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE2,

    /**
     * Wagnisart.
     * 4 = BUZ, 8 = Selbständige Berufsunfähigkeitsvers.
     */
    @FeldInfo(teildatensatz = 2, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
    WAGNISART2,

    /**
     * Laufende Nummer zur Wagnisart.
     */
    @FeldInfo(teildatensatz = 2, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART2,

    /**
     * Tarifbezeichnung.
     * Klartextbezeichnung des Folgetarifs (wenn der Tarif noch nicht umgestellt wurde)
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
     * Dynamikbeginn.
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
     * Prozentualer Anteil am Dynamikerhöhungsbetrag Sozialversicherung prozentual oder absolut
     * bzw. Gehaltsanpassung (3,2 Stellen)
     * z. B.: 100,00 = volle absolute BfA-Dynamik
     * 50,00 = halbe absolute BfA-Dynamik (3,2 Stellen)
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
