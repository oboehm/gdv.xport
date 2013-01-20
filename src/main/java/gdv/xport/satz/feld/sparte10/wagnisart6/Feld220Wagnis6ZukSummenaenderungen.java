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

package gdv.xport.satz.feld.sparte10.wagnisart6;

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
 * "Leben - Unfall = Wagnisart 6 - Zukünftige Summenänderung" (Satzart 0220)
 * 
 * @author ralfklemmer
 * @since 19.01.2013
 */
public enum Feld220Wagnis6ZukSummenaenderungen {

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
     * 6 = Unfallzusatzversicherung
     */
    @FeldInfo(teildatensatz = 1, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
    WAGNISART,

    /**
     * Lfd Nummer zur Wagnisart.<br/>
     */
    @FeldInfo(teildatensatz = 1, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART,

    /**
     * Lfd. Nummer der Satzart<br/>
     * Lfd. Nummer der Satzart 0220.010 (Wagnisart 6) innerhalb der gleichen Folgenummer<br/>
     * (z. B. n-fache hintereinanderfolgende Lieferung der Satzart 0220.010 (Wagnisart 6), wenn mehrere zukünftige
     * Summenänderungen feststehen)
     */
    @FeldInfo(teildatensatz = 1, nr = 11, type = AlphaNumFeld.class, anzahlBytes = 2, byteAdresse = 62)
    LFD_NUMMER_DER_SATZART,

    /**
     * Anfängliche Unfallsumme<br/>
     * Anfängliche bzw. erste Unfallsumme<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 12, type = Betrag.class, anzahlBytes = 12, byteAdresse = 64)
    ANFAENGLICHE_UNFALLSUMME,

    /**
     * Änderung der Unfallleistung<br/>
     * 0 = keine Änderung bzw. nur eine Unfallleistung<br/>
     * 1 = Erhöhung der Unfallleistung<br/>
     * 2 = Reduzierung der Unfallleistung
     */
    @FeldInfo(teildatensatz = 1, nr = 13, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 76)
    AENDERUNG_DER_UNFALLLEISTUNG,

    /**
     * Abstand der Jahresrentenänderungstermine<br/>
     * in Monaten bei periodischen Änderungsterminen<br/>
     * in Monaten bei periodischen Auszahlungen<br/>
     * 000 = keine Änderungen/Auszahlungen<br/>
     * 999 = unregelmäßige Änderungen/Auszahlungen
     */
    @FeldInfo(teildatensatz = 1, nr = 14, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 77)
    ABSTAND_DER_UNFALLAENDERUNGSTERMINE,

    /**
     * Unfalländerungs-Prozentsatz<br/>
     * Konstanter Prozentsatz der Steigerung bzw. Reduzierung der Unfallleistung<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 15, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 80)
    UNFALLAENDERUNGS_PROZENTSATZ,

    /**
     * Absolute Unfalländerungssumme in Währungseinheiten<br/>
     * Absolute Summe der Steigerung bzw. Reduzierung der Unfallleistung<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 16, type = Betrag.class, anzahlBytes = 9, byteAdresse = 85)
    ABSOLUTE_UNFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Nächste Unfallsumme in Währungseinheiten<br/>
     * Unfallleistung ab dem nächsten Änderungstermin<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 17, type = Betrag.class, anzahlBytes = 9, byteAdresse = 94)
    NAECHSTE_UNFALLSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beginndatum der nächsten Unfallsumme<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden Datum der nächsten Jahresrente<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 18, type = Datum.class, anzahlBytes = 8, byteAdresse = 103)
    BEGINNDATUM_DER_NAECHSTEN_UNFALLSUMME,

    /**
     * Leerstellen.<br/>
     */
    @FeldInfo(teildatensatz = 1, nr = 19, type = AlphaNumFeld.class, anzahlBytes = 145, byteAdresse = 111)
    LEERSTELLEN
}
