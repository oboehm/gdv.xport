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

package gdv.xport.satz.feld.sparte10.wagnisart2;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 10.
 * "Leben - Rentenversicherung = Wagnisart 2" (Satzart 0220)
 *
 * @author ralfklemmer
 * @since 17.01.2013
 */
public enum Feld220Wagnis2 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 1,
            type = Feld1bis7.class)
    INTRO1,

    /**
     * Lfd. Nummer der versicherten Person (VP).
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 1, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE,

    /**
     * Wagnisart.
     * 2 = Rentenversicherung
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
     * Vertragsart.
     * 1 = Grundvertrag
     * 2 = Dynamik
     * 3 = Grundvertrag incl. vorletzter Dynamik
     * 4 = Grundvertrag incl. letzter Dynamik.
     */
    @FeldInfo(teildatensatz = 1, nr = 15, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 89)
    VERTRAGSART,

    /**
     * Beitrag gem. Zahlungsweise (10,2 Stelle)
     */
    @FeldInfo(teildatensatz = 1, nr = 16, type = Betrag.class, anzahlBytes = 12, byteAdresse = 90)
    BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Tarifbezeichnung.
     * Kurzbezeichnung des Tarifes
     */
    @FeldInfo(teildatensatz = 1, nr = 17, type = AlphaNumFeld.class, anzahlBytes = 20, byteAdresse = 102)
    TARIFBEZEICHNUNG,

    /**
     * Status.
     * siehe Anlage 24
     */
    @FeldInfo(teildatensatz = 1, nr = 18, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 122)
    STATUS,

    /**
     * Gewinnverwendungsart.
     * siehe Anlage 71
     */
    @FeldInfo(teildatensatz = 1, nr = 19, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 123)
    GEWINNVERWENDUNGSART,

    /**
     * Überschuss gültig ab.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Bezugsjahr zum Bonus/Ansammlungsguthaben
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 20, type = Datum.class, anzahlBytes = 8, byteAdresse = 124)
    UEBERSCHUSS_GUELTIG_AB,

    /**
     * Risikoeinschränkung.
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 21, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 132)
    RISIKOEINSCHRAENKUNG,

    /**
     * Risikozuschläge.
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 22, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 133)
    RISIKOZUSCHLAEGE,

    /**
     * Dynamik %-Satz.
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 23, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 134)
    DYNAMIK_PROZENT_SATZ,

    /**
     * Erhöhungsbasis Dynamik.
     * 1 = Beitrag, 2 = Versicherungssumme
     */
    @FeldInfo(teildatensatz = 1, nr = 24, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 139)
    ERHOEHUNGSBASIS_DYNAMIK,

    /**
     * Erhöhungsart Dynamik.
     * siehe Anlage 72
     */
    @FeldInfo(teildatensatz = 1, nr = 25, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 140)
    ERHOEHUNGSART_DYNAMIK,

    /**
     * Dynamikstop
     * Datum, ab dem keine Dynamikerhöhung mehr möglich ist.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 26, type = Datum.class, anzahlBytes = 8, byteAdresse = 141)
    DYNAMIKSTOP,

    /**
     * Datum der letzten positiven Dynamik.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 27, type = Datum.class, anzahlBytes = 8, byteAdresse = 149)
    DAT_LETZTEN_POSITIVEN_DYNAMIK,

    /**
     * Rückkaufswert in Währungseinheiten
     * kumuliert, incl. aller Dynamiken
     * (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 28, type = Betrag.class, anzahlBytes = 9, byteAdresse = 157)
    RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN,

    /**
     * Rückkaufswert gültig ab.
     * Monat / Jahr (MMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 29, type = Datum.class, anzahlBytes = 6, byteAdresse = 166)
    RUECKKAUFSWERT_GUELTIG_AB,

    /**
     * Guthaben Divid. Ansammlungen in Währungseinheiten
     * (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 30, type = Betrag.class, anzahlBytes = 9, byteAdresse = 172)
    GUTHABEN_DIVID_ANSAMMLUNGEN_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beginn der Rentenzahlung.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 31, type = Datum.class, anzahlBytes = 8, byteAdresse = 181)
    BEGINN_DER_RENTENZAHLUNG,

    /**
     * Mindestlaufzeit.
     * Mindestlaufzeit der Rentenzahlung / Garantierte Rentenzahlungsdauer (JJ) Jahre
     */
    @FeldInfo(teildatensatz = 1, nr = 32, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 189)
    MINDESTLAUFZEIT,

    /**
     * Rückgewähr bei Tod.
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 33, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 191)
    RUECKGEWAEHR_BEI_TOD,

    /**
     * Jahresrente in Währungseinheiten.
     * Vertraglich vereinbarte Jahresrente zum Ablauf (inkl. Abrufphase)
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 34, type = Betrag.class, anzahlBytes = 12, byteAdresse = 192)
    JAHRESRENTE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Kapitalzahlungssumme in Währungseinheiten.
     * Vertraglich vereinbarte Kapitalzahlungssumme zum Ablauf (inkl. Abrufphase)
     * (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 35, type = Betrag.class, anzahlBytes = 9, byteAdresse = 204)
    KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Witwen-/Witwerrente %.
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 36, type = Betrag.class, anzahlBytes = 5, byteAdresse = 213)
    WITWEN_WITWERRENTE_IN_PROZENT,

    /**
     * Technische Witwe/Witwer % (im Rahmen einer Gesellschafter/Gesschäftsführer-Vers.).
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 37, type = Betrag.class, anzahlBytes = 5, byteAdresse = 218)
    TECHNISCHE_WITWEN_WITTWERRENTE_IN_PROZENT,

    /**
     * Waisenrente in %.
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 38, type = Betrag.class, anzahlBytes = 5, byteAdresse = 223)
    WAISENRENTE_IN_PROZENT,

    /**
     * technische Waise % (im Rahmen einer Gesellschafter/Gesschäftsführer-Vers.).
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 39, type = Betrag.class, anzahlBytes = 5, byteAdresse = 228)
    TECHNISCHE_WAISE_IN_PROZENT,

    /**
     * Schlussalter des Waisen
     * JJ. Bis zu welchem Alter des Waisen wird Rente bezahlt
     */
    @FeldInfo(teildatensatz = 1, nr = 40, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 233)
    SCHLUSSALTER_DES_WAISEN,

    /**
     * Auslösung der Leistung.
     * 1 = Tod
     * 2 = Endalter
     * 3 = Beitragsrückgewähr
     * 4 = Rückkauf
     */
    @FeldInfo(teildatensatz = 1, nr = 41, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 235)
    AUSLOESUNG_DER_LEISTUNG,

    /**
     * Zahlung der Witwen-/Witwerrente bis.
     * 1 = lebenslänglich, 2 = Wiederheirat
     */
    @FeldInfo(teildatensatz = 1, nr = 42, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 236)
    ZAHLUNG_DER_WITWEN_WITWERRENTE_BIS,

    /**
     * Endalter.
     * Eintrittsalter + Vertragslaufzeit (Aufschubzeit) (JJJ), Alter
     * Lebenslänglich ist mit Wert 999 zu schlüsseln
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 43,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 237)
    ENDALTER,

    /**
     * Eintrittsalter.
     * JJ Alter (versicherungstechnisch)
     */
    @FeldInfo(teildatensatz = 1, nr = 44, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 240)
    EINTRITTSALTER,

    /**
     * Umtauschrecht.
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 45, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 242)
    UMTAUSCHRECHT,

    /**
     * Stundung.
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 46, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 243)
    STUNDUNG,

    /**
     * Beginn Abrufphase.
     * Termin an dem die Abrufphase beginnt.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden.
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 47, type = Datum.class, anzahlBytes = 8, byteAdresse = 244)
    BEGINN_ABRUFPHASE,

    /**
     * Leerstellen.
     */
    @FeldInfo(teildatensatz = 1, nr = 48, type = AlphaNumFeld.class, anzahlBytes = 4, byteAdresse = 252)
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
     * 2 = Rentenversicherung
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
     * Abweichende Vertragslaufzeit.
     * Abgekürzte oder verlängerte Vertragslaufzeit Anzahl Jahre (JJJ)
     * Lebenslänglich ist mit "999" zu schlüsseln
     */
    @FeldInfo(teildatensatz = 2, nr = 11, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 62)
    ABWEICHENDE_VERTRAGSLAUFZEIT,

    /**
     * Abweichender Ablauf.
     * Ablauf abgekürzt oder verlängert Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt 
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 12, type = Datum.class, anzahlBytes = 8, byteAdresse = 65)
    ABWEICHENDER_ABLAUF,

    /**
     * Nettobeitrag in Währungseinheiten
     * Beitrag gem. Zahlungsweise nach Überschussanrechnung
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 13, type = Betrag.class, anzahlBytes = 12, byteAdresse = 73)
    NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Risikozuschlag in Währungseinheiten.
     * Risikozuschlag gem. Zahlungsweise
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 14, type = Betrag.class, anzahlBytes = 12, byteAdresse = 85)
    RISIKOZUSCHLAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Rentenzahlweise.
     * blank = Rechtschutz / Verkehrsservice / Kredit
     * (In den Sparten Rechtschutz und Verkehrsservice kann das Datenfeld "Zahlungsweise" in der Satzart 0200 blank
     * sein, die Zahlungsweise wird dann auf Risikoebene in der Satzart 0210, spätestens in Satzart 0220 angegeben. Bei
     * Einzel- und / oder Umsatzanmeldungen muss das Datenfeld "Zahlungsweise" in der Satzart 0400 blank sein.) siehe
     * Anlage 14
     */
    @FeldInfo(teildatensatz = 2, nr = 15, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 97)
    RENTENZAHLWEISE,

    /**
     * Beitragsrückgewähr.
     * 1 = vor Rentenbeginn
     * 2 = nach Rentenbeginn
     * 3 = vor und nach Rentenbeginn
     */
    @FeldInfo(teildatensatz = 2, nr = 16, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 98)
    BEITRAGSRUECKGEWAEHR,

    /**
     * Mitzuversichernde Person.
     * Name, Vorname (z. B. bei Witwen-/Witwerrente oder Waisenrente)
     */
    @FeldInfo(teildatensatz = 2, nr = 17, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 99)
    MITZUVERSICHERNDE_PERSON,

    /**
     * Geburtsdatum der mitzuversichernden Person.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 18, type = Datum.class, anzahlBytes = 8, byteAdresse = 129)
    GEBURTSDATUM_DER_MITZUVERSICHERNDEN_PERSON,

    /**
     * Geschlecht der mitzuversichernden Person.
     * 0 = juristische Person
     * 1 = männlich
     * 2 = weiblich
     */
    @FeldInfo(teildatensatz = 2, nr = 19, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 137)
    GESCHLECHT_DER_MITZUVERSICHERNDEN_PERSON,

    /**
     * Tarifbezeichnung des Folgetarifs.
     * Klartextbezeichnung des Folgetarifs (wenn der Tarif noch nicht umgestellt wurde)
     */
    @FeldInfo(teildatensatz = 2, nr = 20, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 138)
    TARIFBEZEICHNUNG_DES_FOLGETARIFS,

    /**
     * Umstellungsdatum des Folgetarifs.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 21, type = Datum.class, anzahlBytes = 8, byteAdresse = 168)
    UMSTELLUNGSDAT_FOLGETARIFS,

    /**
     * Zukünftiger Beitrag in Währungseinheiten.
     * gem. Zahlungsweise wenn der Beitrag noch nicht umgestellt wurde
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 22, type = Betrag.class, anzahlBytes = 12, byteAdresse = 176)
    ZUKUENFTIGER_BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Vertragsbedingung.
     * Individuelle Vertragsbedingung (frei definierbar)
     */
    @FeldInfo(teildatensatz = 2, nr = 23, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 188)
    VERTRAGSBEDINGUNG,

    /**
     * Dynamikbeginn.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden.
     * Datum der ersten Dynamikerhöhung
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 24, type = Datum.class, anzahlBytes = 8, byteAdresse = 218)
    DYNAMIKBEGINN,

    /**
     * Abweichendes Dynamikendalter.
     * Wenn abweichend von normalem Endalter
     * (JJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 25, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 226)
    ABWEICHENDES_DYNAMIKENDALTER,

    /**
     * Absoluter Dynamikerhöhungsbetrag in Währungseinheiten.
     * (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 26, type = Betrag.class, anzahlBytes = 9, byteAdresse = 229)
    ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Anteiliger Dynamikprozentsatz.
     * Prozentualer Anteil am Dynamikerhöhungsbetrag Sozialversicherung prozentual oder absolut
     * bzw. Gehaltsanpassung (3,2 Stellen)
     * z. B.: 100,00 = volle absolute BfA-Dynamik
     * 50,00 = halbe absolute BfA-Dynamik (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 27, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 238)
    ANTEILIGER_DYNAMIKPROZENTSATZ,

    /**
     * Vereinbarter Dynamikmindestanpassungsprozentsatz.
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 28, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 243)
    VEREINBARTER_DYNAMIKMINDESTANPASSUNGSPROZENTSATZ,

    /**
     * Vereinbarter Dynamikmaximalanpassungsprozentsatz.
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 29, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 248)
    VEREINBARTER_DYNAMIKMAXIMALANPASSUNGSPROZENTSATZ,

    /**
     * Anzahl verbleibende Dynamikwidersprüche.
     * Anzahl der verbleibenden Dynamikwidersprüche, ohne das Recht auf Dynamikerhöhung zu verlieren
     */
    @FeldInfo(teildatensatz = 2, nr = 30, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 253)
    ANZAHL_VERBLEIBENDE_DYNAMIKWIDERSPRUECHE,

    /**
     * Leerstellen.
     */
    @FeldInfo(teildatensatz = 2, nr = 31, type = AlphaNumFeld.class, anzahlBytes = 2, byteAdresse = 254)
    LEERSTELLEN2,

    // /// Teildatensatz 3 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 3,
            type = Feld1bis7.class)
    INTRO3,

    /**
     * Laufende Nummer der versicherten Person (VP).
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 3, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE3,

    /**
     * Wagnisart.
     * 1 = Kapitallebensversicherung 3 = Risikoversicherung
     */
    @FeldInfo(teildatensatz = 3, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
    WAGNISART3,

    /**
     * Laufende Nummer zur Wagnisart.
     */
    @FeldInfo(teildatensatz = 3, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART3,

    /**
     * Leistung bei schwerer Erkrankung.
     * "Dread Disease"
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 3, nr = 11, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 62)
    LEISTUNG_BEI_SCHWERER_ERKRANKUNG,

    /**
     * Versicherte Erkrankungen.
     * Versicherte Erkrankungen bei "Dread Disease" (15 x 2 Stellen)
     * 01 = Herzinfarkt
     * 02 = Schlaganfall
     * 03 = Multiple Sklerose
     * 04 = Krebs
     * 05 = Bypass-Operation
     * 06 = Dauerdialyse
     * 07 = Nierentransplantation
     */
    @FeldInfo(teildatensatz = 3, nr = 12, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 63)
    VERSICHERTE_ERKRANKUNGEN,

    /**
     * Leistungsbeginn ab.
     * Leistungsbeginn / Karenzzeit bei Dread Disease"
     * (xxxT) oder (xxxW).
     * Die letzte Stelle muss mit "T" oder "W" belegt werden wenn Eintrag vorhanden,
     * z. B. 004T = 4 Tage 007W = 7 Wochen
     */
    @FeldInfo(teildatensatz = 3, nr = 13, type = AlphaNumFeld.class, anzahlBytes = 4, byteAdresse = 93)
    LEISTUNGSBEGINN_AB,

    /**
     * Jahresrente in Währungseinheiten.
     * Vertraglich vereinbarte Jahresrente zum Ablauf (inkl. Abrufphase)
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 3, nr = 14, type = Betrag.class, anzahlBytes = 14, byteAdresse = 97)
    JAHRESRENTE_IN_WAEHRUNGSEINHEITEN2,

    /**
     * Kapitalzahlungssumme in Währungseinheiten.
     * Vertraglich vereinbarte Kapitalzahlungssumme zum Ablauf (inkl. Abrufphase)
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 3, nr = 15, type = Betrag.class, anzahlBytes = 14, byteAdresse = 111)
    KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN2,

    /**
     * Teilkapitalisierung.
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 3, nr = 16, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 125)
    TEILKAPITALISIERUNG,

    /**
     * Todesfallleistung in Währungseinheiten.
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 3, nr = 17, type = Betrag.class, anzahlBytes = 14, byteAdresse = 126)
    TODESFALLLEISTUNG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Eingerechnete Zulage.
     * Vertraglich vereinbarte Versicherungssumme im Todesfall (aktuelle VS) zum Beginn der Abrufphase
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 3, nr = 18, type = Betrag.class, anzahlBytes = 14, byteAdresse = 140)
    EINGERECHNETE_ZULAGE,
    /**
     * Einrechnungsjahr.
     * Jahr der letzten eingerechneten Zulage
     */
    @FeldInfo(teildatensatz = 3, nr = 19, type = Betrag.class, anzahlBytes = 4, byteAdresse = 154)
    EINRECHNUNGSJAHR,

    /**
     * Leerstellen.
     */
    @FeldInfo(teildatensatz = 3, nr = 20, type = AlphaNumFeld.class, anzahlBytes = 98, byteAdresse = 158)
    LEERSTELLEN3
}
