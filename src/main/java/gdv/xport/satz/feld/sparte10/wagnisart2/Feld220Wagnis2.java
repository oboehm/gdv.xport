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

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 10 <br/>
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
     * Lfd. Nummer der versicherten Person (VP).<br/>
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 1, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE,

    /**
     * Wagnisart.<br/>
     * 2 = Rentenversicherung
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
     * Beitrag gem. Zahlungsweise (10,2 Stelle)
     */
    @FeldInfo(teildatensatz = 1, nr = 16, type = Betrag.class, anzahlBytes = 12, byteAdresse = 90)
    BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Tarifbezeichnung.<br/>
     * Kurzbezeichnung des Tarifes
     */
    @FeldInfo(teildatensatz = 1, nr = 17, type = AlphaNumFeld.class, anzahlBytes = 20, byteAdresse = 102)
    TARIFBEZEICHNUNG,

    /**
     * Status.<br/>
     * siehe Anlage 24
     */
    @FeldInfo(teildatensatz = 1, nr = 18, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 122)
    STATUS,

    /**
     * Gewinnverwendungsart.<br/>
     * siehe Anlage 71
     */
    @FeldInfo(teildatensatz = 1, nr = 19, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 123)
    GEWINNVERWENDUNGSART,

    /**
     * Überschuss gültig ab.<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden<br/>
     * Bezugsjahr zum Bonus/Ansammlungsguthaben<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 20, type = Datum.class, anzahlBytes = 8, byteAdresse = 124)
    UEBERSCHUSS_GUELTIG_AB,

    /**
     * Risikoeinschränkung<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 21, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 132)
    RISIKOEINSCHRAENKUNG,

    /**
     * Risikozuschläge<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 22, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 133)
    RISIKOZUSCHLAEGE,

    /**
     * Dynamik %-Satz<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 23, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 134)
    DYNAMIK_PROZENT_SATZ,

    /**
     * Erhöhungsbasis Dynamik<br/>
     * 1 = Beitrag, 2 = Versicherungssumme
     */
    @FeldInfo(teildatensatz = 1, nr = 24, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 139)
    ERHOEHUNGSBASIS_DYNAMIK,

    /**
     * Erhöhungsart Dynamik<br/>
     * siehe Anlage 72
     */
    @FeldInfo(teildatensatz = 1, nr = 25, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 140)
    ERHOEHUNGSART_DYNAMIK,

    /**
     * Dynamikstop<br/>
     * Datum, ab dem keine Dynamikerhöhung mehr möglich ist.<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 26, type = Datum.class, anzahlBytes = 8, byteAdresse = 141)
    DYNAMIKSTOP,

    /**
     * Datum der letzten positiven Dynamik<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 27, type = Datum.class, anzahlBytes = 8, byteAdresse = 149)
    DATUM_DER_LETZTEN_POSITIVEN_DYNAMIK,

    /**
     * Rückkaufswert in Währungseinheiten<br/>
     * kumuliert, incl. aller Dynamiken<br/>
     * (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 28, type = Betrag.class, anzahlBytes = 9, byteAdresse = 157)
    RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN,

    /**
     * Rückkaufswert gültig ab<br/>
     * Monat / Jahr (MMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 29, type = Datum.class, anzahlBytes = 6, byteAdresse = 166)
    RUECKKAUFSWERT_GUELTIG_AB,

    /**
     * Guthaben Divid. Ansammlungen in Währungseinheiten<br/>
     * (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 30, type = Betrag.class, anzahlBytes = 9, byteAdresse = 172)
    GUTHABEN_DIVID_ANSAMMLUNGEN_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beginn der Rentenzahlung<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 31, type = Datum.class, anzahlBytes = 8, byteAdresse = 181)
    BEGINN_DER_RENTENZAHLUNG,

    /**
     * Mindestlaufzeit<br/>
     * Mindestlaufzeit der Rentenzahlung / Garantierte Rentenzahlungsdauer (JJ) Jahre
     */
    @FeldInfo(teildatensatz = 1, nr = 32, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 189)
    MINDESTLAUFZEIT,

    /**
     * Rückgewähr bei Tod<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 33, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 191)
    RUECKGEWAEHR_BEI_TOD,

    /**
     * Jahresrente in Währungseinheiten<br/>
     * Vertraglich vereinbarte Jahresrente zum Ablauf (inkl. Abrufphase)<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 34, type = Betrag.class, anzahlBytes = 12, byteAdresse = 192)
    JAHRESRENTE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Kapitalzahlungssumme in Währungseinheiten<br/>
     * Vertraglich vereinbarte Kapitalzahlungssumme zum Ablauf (inkl. Abrufphase)<br/>
     * (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 35, type = Betrag.class, anzahlBytes = 9, byteAdresse = 204)
    KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Witwen-/Witwerrente %<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 36, type = Betrag.class, anzahlBytes = 5, byteAdresse = 213)
    WITWEN_WITWERRENTE_IN_PROZENT,

    /**
     * technische Witwe/Witwer % (im Rahmen einer Gesellschafter/Gesschäftsführer-Vers.)<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 37, type = Betrag.class, anzahlBytes = 5, byteAdresse = 218)
    TECHNISCHE_WITWEN_WITTWERRENTE_IN_PROZENT,

    /**
     * Waisenrente in %<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 38, type = Betrag.class, anzahlBytes = 5, byteAdresse = 223)
    WAISENRENTE_IN_PROZENT,

    /**
     * technische Waise % (im Rahmen einer Gesellschafter/Gesschäftsführer-Vers.)<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 39, type = Betrag.class, anzahlBytes = 5, byteAdresse = 228)
    TECHNISCHE_WAISE_IN_PROZENT,

    /**
     * Schlussalter des Waisen<br/>
     * JJ. Bis zu welchem Alter des Waisen wird Rente bezahlt
     */
    @FeldInfo(teildatensatz = 1, nr = 40, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 233)
    SCHLUSSALTER_DES_WAISEN,

    /**
     * Auslösung der Leistung<br/>
     * 1 = Tod<br/>
     * 2 = Endalter<br/>
     * 3 = Beitragsrückgewähr<br/>
     * 4 = Rückkauf
     */
    @FeldInfo(teildatensatz = 1, nr = 41, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 235)
    AUSLOESUNG_DER_LEISTUNG,

    /**
     * Zahlung der Witwen-/Witwerrente bis<br/>
     * 1 = lebenslänglich, 2 = Wiederheirat
     */
    @FeldInfo(teildatensatz = 1, nr = 42, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 236)
    ZAHLUNG_DER_WITWEN_WITWERRENTE_BIS,

    /**
     * Endalter<br/>
     * Eintrittsalter + Vertragslaufzeit (Aufschubzeit) (JJJ), Alter Lebenslänglich ist mit Wert 999 zu schlüsseln
     */
    @FeldInfo(teildatensatz = 1, nr = 43, type = Zeichen.class, anzahlBytes = 3, byteAdresse = 237)
    ENDALTER,

    /**
     * Eintrittsalter<br/>
     * JJ Alter (versicherungstechnisch)
     */
    @FeldInfo(teildatensatz = 1, nr = 44, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 240)
    EINTRITTSALTER,

    /**
     * Umtauschrecht<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 45, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 242)
    UMTAUSCHRECHT,

    /**
     * Stundung<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 46, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 243)
    STUNDUNG,

    /**
     * Beginn Abrufphase<br/>
     * Termin an dem die Abrufphase beginnt.<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden.<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 47, type = Datum.class, anzahlBytes = 8, byteAdresse = 244)
    BEGINN_ABRUFPHASE,

    /**
     * Leerstellen.<br/>
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
     * Lfd. Nummer der versicherten Person (VP).<br/>
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 2, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE2,

    /**
     * Wagnisart.<br/>
     * 2 = Rentenversicherung
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
     * Abweichende Vertragslaufzeit<br/>
     * Abgekürzte oder verlängerte Vertragslaufzeit Anzahl Jahre (JJJ) Lebenslänglich ist mit "999" zu schlüsseln<br/>
     */
    @FeldInfo(teildatensatz = 2, nr = 11, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 62)
    ABWEICHENDE_VERTRAGSLAUFZEIT,

    /**
     * Abweichender Ablauf<br/>
     * Ablauf abgekürzt oder verlängert Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt <br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 12, type = Datum.class, anzahlBytes = 8, byteAdresse = 65)
    ABWEICHENDER_ABLAUF,

    /**
     * Nettobeitrag in Währungseinheiten<br/>
     * Beitrag gem. Zahlungsweise nach Überschussanrechnung<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 13, type = Betrag.class, anzahlBytes = 12, byteAdresse = 73)
    NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Risikozuschlag in Währungseinheiten<br/>
     * Risikozuschlag gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 14, type = Betrag.class, anzahlBytes = 12, byteAdresse = 85)
    RISIKOZUSCHLAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Rentenzahlweise<br/>
     * blank = Rechtschutz / Verkehrsservice / Kredit<br/>
     * (In den Sparten Rechtschutz und Verkehrsservice kann das Datenfeld "Zahlungsweise" in der Satzart 0200 blank
     * sein, die Zahlungsweise wird dann auf Risikoebene in der Satzart 0210, spätestens in Satzart 0220 angegeben. Bei
     * Einzel- und / oder Umsatzanmeldungen muss das Datenfeld "Zahlungsweise" in der Satzart 0400 blank sein.) siehe
     * Anlage 14
     */
    @FeldInfo(teildatensatz = 2, nr = 15, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 97)
    RENTENZAHLWEISE,

    /**
     * Beitragsrückgewähr<br/>
     * 1 = vor Rentenbeginn<br/>
     * 2 = nach Rentenbeginn<br/>
     * 3 = vor und nach Rentenbeginn
     */
    @FeldInfo(teildatensatz = 2, nr = 16, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 98)
    BEITRAGSRUECKGEWAEHR,

    /**
     * Mitzuversichernde Person<br/>
     * Name, Vorname (z. B. bei Witwen-/Witwerrente oder Waisenrente)
     */
    @FeldInfo(teildatensatz = 2, nr = 17, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 99)
    MITZUVERSICHERNDE_PERSON,

    /**
     * Geburtsdatum der mitzuversichernden Person<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 18, type = Datum.class, anzahlBytes = 8, byteAdresse = 129)
    GEBURTSDATUM_DER_MITZUVERSICHERNDEN_PERSON,

    /**
     * Geschlecht der mitzuversichernden Person<br/>
     * 0 = juristische Person<br/>
     * 1 = männlich<br/>
     * 2 = weiblich
     */
    @FeldInfo(teildatensatz = 2, nr = 19, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 137)
    GESCHLECHT_DER_MITZUVERSICHERNDEN_PERSON,

    /**
     * Tarifbezeichnung des Folgetarifs<br/>
     * Klartextbezeichnung des Folgetarifs (wenn der Tarif noch nicht umgestellt wurde)
     */
    @FeldInfo(teildatensatz = 2, nr = 20, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 138)
    TARIFBEZEICHNUNG_DES_FOLGETARIFS,

    /**
     * Umstellungsdatum des Folgetarifs<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 21, type = Datum.class, anzahlBytes = 8, byteAdresse = 168)
    UMSTELLUNGSDATUM_DES_FOLGETARIFS,

    /**
     * Zukünftiger Beitrag in Währungseinheiten<br/>
     * gem. Zahlungsweise wenn der Beitrag noch nicht umgestellt wurde<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 22, type = Betrag.class, anzahlBytes = 12, byteAdresse = 176)
    ZUKUENFTIGER_BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Vertragsbedingung<br/>
     * Individuelle Vertragsbedingung (frei definierbar)
     */
    @FeldInfo(teildatensatz = 2, nr = 23, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 188)
    VERTRAGSBEDINGUNG,

    /**
     * Dynamikbeginn<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden.<br/>
     * Datum der ersten Dynamikerhöhung<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 24, type = Datum.class, anzahlBytes = 8, byteAdresse = 218)
    DYNAMIKBEGINN,

    /**
     * Abweichendes Dynamikendalter<br/>
     * Wenn abweichend von normalem Endalter<br/>
     * (JJJ)
     */
    @FeldInfo(teildatensatz = 2, nr = 25, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 226)
    ABWEICHENDES_DYNAMIKENDALTER,

    /**
     * Absoluter Dynamikerhöhungsbetrag in Währungseinheiten<br/>
     * (9,0 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 26, type = Betrag.class, anzahlBytes = 9, byteAdresse = 229)
    ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Anteiliger Dynamikprozentsatz<br/>
     * Prozentualer Anteil am Dynamikerhöhungsbetrag Sozialversicherung prozentual oder absolut<br/>
     * bzw. Gehaltsanpassung (3,2 Stellen)<br/>
     * z. B.: 100,00 = volle absolute BfA-Dynamik<br/>
     * 50,00 = halbe absolute BfA-Dynamik (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 27, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 238)
    ANTEILIGER_DYNAMIKPROZENTSATZ,

    /**
     * Vereinbarter Dynamikmindestanpassungsprozentsatz<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 28, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 243)
    VEREINBARTER_DYNAMIKMINDESTANPASSUNGSPROZENTSATZ,

    /**
     * Vereinbarter Dynamikmaximalanpassungsprozentsatz<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 2, nr = 29, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 248)
    VEREINBARTER_DYNAMIKMAXIMALANPASSUNGSPROZENTSATZ,

    /**
     * Anzahl verbleibende Dynamikwidersprüche<br/>
     * Anzahl der verbleibenden Dynamikwidersprüche, ohne das Recht auf Dynamikerhöhung zu verlieren
     */
    @FeldInfo(teildatensatz = 2, nr = 30, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 253)
    ANZAHL_VERBLEIBENDE_DYNAMIKWIDERSPRUECHE,

    /**
     * Leerstellen.<br/>
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
     * Lfd. Nummer der versicherten Person (VP).<br/>
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 3, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE3,

    /**
     * Wagnisart.<br/>
     * 1 = Kapitallebensversicherung 3 = Risikoversicherung
     */
    @FeldInfo(teildatensatz = 3, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
    WAGNISART3,

    /**
     * Lfd Nummer zur Wagnisart.<br/>
     */
    @FeldInfo(teildatensatz = 3, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART3,

    /**
     * Leistung bei schwerer Erkrankung<br/>
     * "Dread Disease"<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 3, nr = 11, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 62)
    LEISTUNG_BEI_SCHWERER_ERKRANKUNG,

    /**
     * Versicherte Erkrankungen<br/>
     * Versicherte Erkrankungen bei "Dread Disease" (15 x 2 Stellen)<br/>
     * 01 = Herzinfarkt<br/>
     * 02 = Schlaganfall<br/>
     * 03 = Multiple Sklerose<br/>
     * 04 = Krebs<br/>
     * 05 = Bypass-Operation<br/>
     * 06 = Dauerdialyse<br/>
     * 07 = Nierentransplantation
     */
    @FeldInfo(teildatensatz = 3, nr = 12, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 63)
    VERSICHERTE_ERKRANKUNGEN,

    /**
     * Leistungsbeginn ab<br/>
     * Leistungsbeginn / Karenzzeit bei Dread Disease"<br/>
     * (xxxT) oder (xxxW).<br/>
     * Die letzte Stelle muss mit "T" oder "W" belegt werden wenn Eintrag vorhanden,<br/>
     * z. B. 004T = 4 Tage 007W = 7 Wochen
     */
    @FeldInfo(teildatensatz = 3, nr = 13, type = AlphaNumFeld.class, anzahlBytes = 4, byteAdresse = 93)
    LEISTUNGSBEGINN_AB,

    /**
     * Jahresrente in Währungseinheiten<br/>
     * Vertraglich vereinbarte Jahresrente zum Ablauf (inkl. Abrufphase)<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 3, nr = 14, type = Betrag.class, anzahlBytes = 14, byteAdresse = 97)
    JAHRESRENTE_IN_WAEHRUNGSEINHEITEN2,

    /**
     * Kapitalzahlungssumme in Währungseinheiten<br/>
     * Vertraglich vereinbarte Kapitalzahlungssumme zum Ablauf (inkl. Abrufphase)<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 3, nr = 15, type = Betrag.class, anzahlBytes = 14, byteAdresse = 111)
    KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN2,

    /**
     * Teilkapitalisierungn<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 3, nr = 16, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 125)
    TEILKAPITALISIERUNG,

    /**
     * Todesfallleistung in Währungseinheiten<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 3, nr = 17, type = Betrag.class, anzahlBytes = 14, byteAdresse = 126)
    TODESFALLLEISTUNG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Eingerechnete Zulage<br/>
     * Vertraglich vereinbarte Versicherungssumme im Todesfall (aktuelle VS) zum Beginn der Abrufphase<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 3, nr = 18, type = Betrag.class, anzahlBytes = 14, byteAdresse = 140)
    EINGERECHNETE_ZULAGE,
    /**
     * Einrechnungsjahr<br/>
     * Jahr der letzten eingerechneten Zulage
     */
    @FeldInfo(teildatensatz = 3, nr = 19, type = Betrag.class, anzahlBytes = 4, byteAdresse = 154)
    EINRECHNUNGSJAHR,

    /**
     * Leerstellen.<br/>
     */
    @FeldInfo(teildatensatz = 3, nr = 20, type = AlphaNumFeld.class, anzahlBytes = 98, byteAdresse = 158)
    LEERSTELLEN3
}
