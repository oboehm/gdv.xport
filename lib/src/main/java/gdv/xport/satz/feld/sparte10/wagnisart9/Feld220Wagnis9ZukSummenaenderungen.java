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

package gdv.xport.satz.feld.sparte10.wagnisart9;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 10.
 * "Leben - Fondsgebundene Rentenversicherung = Wagnisart 9 - Zukünftige Summenänderung" (Satzart 0220)
 *
 * @author ralfklemmer
 * @since 19.01.2013
 */
public enum Feld220Wagnis9ZukSummenaenderungen {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(teildatensatz = 1, type = Feld1bis7.class)
    INTRO1,

    /**
     * Lfd. Nummer der versicherten Person (VP).
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 1, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE,

    /**
     * Wagnisart.
     * 9 = Fondsgebundene Rentenversicherung
     */
    @FeldInfo(teildatensatz = 1, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
    WAGNISART,

    /**
     * Lfd Nummer zur Wagnisart.
     */
    @FeldInfo(teildatensatz = 1, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART,

    /**
     * Lfd. Nummer der Satzart
     * Lfd. Nummer der Satzart 0220.010.2/6 innerhalb der gleichen Folgenummer
     * (z. B. n-fache hintereinanderfolgende Lieferung der Satzart 0220.010.2/6, wenn mehrere Bezugsrechte vorhanden)
     */
    @FeldInfo(teildatensatz = 1, nr = 11, type = AlphaNumFeld.class, anzahlBytes = 2, byteAdresse = 62)
    LFD_NUMMER_SATZART,

    /**
     * Anfängliche Todesfall VS in Währungseinheiten
     * Anfängliche bzw. erste Todesfalleistung
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 12, type = Betrag.class, anzahlBytes = 12, byteAdresse = 64)
    ANFAENGLICHE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Änderung der Todesfallleistung
     * 0 = keine Änderung bzw. nur eine Todesfallleistung
     * 1 = Erhöhung der Todesfallleistung
     * 2 = Reduzierung der Todesfallleistung
     */
    @FeldInfo(teildatensatz = 1, nr = 13, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 76)
    AENDERUNG_DER_TODESFALLLEISTUNG,

    /**
     * Abstand der Todesfalländerungstermine.
     * in Monaten bei periodischen Änderungsterminen 
     * in Monaten bei periodischen Auszahlungen
     * 000 = keine Änderungen/Auszahlungen
     * 999 = unregelmäßige Änderungen/Auszahlungen
     */
    @FeldInfo(teildatensatz = 1, nr = 14, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 77)
    ABSTAND_DER_TODESFALLAENDERUNGSTERMINE,

    /**
     * Todesfalländerungs-Prozentsatz.
     * Konstanter Prozentsatz der Steigerung bzw. Reduzierung der Todesfalleistung
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 15, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 80)
    TODESFALLAENDERUNGS_PROZENTSATZ,

    /**
     * Absolute Todesfalländerungssumme in Währungseinheiten.
     * Absolute Summe der Steigerung bzw. Reduzierung der Todesfalleistung
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 16, type = Betrag.class, anzahlBytes = 12, byteAdresse = 85)
    ABSOLUTE_TODESFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Nächste Todesfall VS in Währungseinheiten.
     * Todesfalleistung ab dem nächsten Änderungstermin
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 17, type = Betrag.class, anzahlBytes = 12, byteAdresse = 97)
    NAECHSTE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beginndatum der nächsten Todesfall VS.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden Datum der nächsten
     * Todesfalländerung
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 18, type = Datum.class, anzahlBytes = 8, byteAdresse = 109)
    BEGINNDAT_DER_NAECHSTEN_TODESFALL_VS,

    /**
     * Anfängliche Jahresrente in Währungseinheiten.
     * Anfängliche bzw. erste Jahresrente
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 19, type = Betrag.class, anzahlBytes = 12, byteAdresse = 117)
    ANFAENGLICHE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Änderung der Jahresrente.
     * 0 = keine Änderung bzw. nur eine Jahresrente VS
     * 1 = Erhöhung der Jahresrente
     * 2 = Reduzierung der Jahresrente
     */
    @FeldInfo(teildatensatz = 1, nr = 20, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 129)
    AENDERUNG_DER_JAHRESRENTE,

    /**
     * Abstand der Jahresrentennänderungstermine.
     * in Monaten bei periodischen Änderungsterminen 
     * in Monaten bei periodischen Auszahlungen
     * 000 = keine Änderungen/Auszahlungen
     * 999 = unregelmäßige Änderungen/Auszahlungen
     */
    @FeldInfo(teildatensatz = 1, nr = 21, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 130)
    ABSTAND_DER_JAHRESRENTENAENDERUNGSTERMINE,

    /**
     * Jahresrentenänderungs-Prozentsatz.
     * Konstanter Prozentsatz der Steigerung bzw. Reduzierung der Jahresrente
     * (3,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 22, type = NumFeld.class, anzahlBytes = 5, byteAdresse = 133)
    JAHRESRENTENAENDERUNGS_PROZENTSATZ,

    /**
     * Absolute Jahresrentenänderungssumme in Währungseinheiten.
     * Absolute Summe der Steigerung bzw. Reduzierung der Jahresrente
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 23, type = Betrag.class, anzahlBytes = 12, byteAdresse = 138)
    ABSOLUTE_JAHRESRENTENAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Nächste Jahresrente in Währungseinheiten.
     * Jahresrente ab dem nächsten Änderungstermin
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 24, type = Betrag.class, anzahlBytes = 12, byteAdresse = 150)
    NAECHSTE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beginndatum der nächsten Jahresrentesumme.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt
     * werden Datum der nächsten Jahresrente
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 25, type = Datum.class, anzahlBytes = 8, byteAdresse = 162)
    BEGINNDAT_DER_NAECHSTEN_JAHRESRENTESUMME,

    /**
     * Leerstellen.
     */
    @FeldInfo(teildatensatz = 1, nr = 26, type = AlphaNumFeld.class, anzahlBytes = 86, byteAdresse = 170)
    LEERSTELLEN

}
