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

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 10.
 * (Leben - Kapitallebens-/Risikovers. = Wagnisart 1 u. 3 - Zukünftige
 * Summenänderungen" (Satzart 0221)).
 *
 * @author ralfklemmer
 * @since 17.01.2013
 */
public enum Feld221Wagnis6ZukSummenaenderungen {

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
     * 1 = Kapitallebensversicherung 3 = Risikoversicherung
     */
    @FeldInfo(teildatensatz = 1, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
    WAGNISART,

    /**
     * Lfd Nummer zur Wagnisart.
     */
    @FeldInfo(teildatensatz = 1, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART,

    /**
     * Laufende Nummer der Satzart.
     * Lfd. Nummer der Satzart 0220.010.2/6 innerhalb der gleichen Folgenummer
     * (z. B. n-fache hintereinanderfolgende Lieferung der Satzart 0220.010.2/6, wenn mehrere Bezugsrechte vorhanden)
     */
    @FeldInfo(teildatensatz = 1, nr = 11, type = AlphaNumFeld.class, anzahlBytes = 2, byteAdresse = 62)
    LFD_NUMMER_SATZART,

    /**
     * Anfängliche Todesfall VS in Währungseinheiten.
     * Anfängliche bzw. erste Todesfallleistung
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 12, type = Betrag.class, anzahlBytes = 14, byteAdresse = 64)
    ANFAENGLICHE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Absolute Todesfalländerungssumme in Währungseinheiten.
     * Absolute Summe der Steigerung bzw. Reduzierung der Todesfalleistung
     * (12,0 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 13, type = Betrag.class, anzahlBytes = 14, byteAdresse = 78)
    ABSOLUTE_TODESFALLAENDERUNGSSUMME_VS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Nächste Todesfall VS in Währungseinheiten.
     * Todesfalleistung ab dem nächsten Änderungstermin
     * (12,0 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 14, type = Betrag.class, anzahlBytes = 14, byteAdresse = 92)
    NAECHSTE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Anfängliche Erlebensfall VS in Währungseinheiten.
     * Anfängliche bzw. erste Erlebensfall VS
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 15, type = Betrag.class, anzahlBytes = 14, byteAdresse = 106)
    ANFAENGLICHE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Absolute Erlebensfall VS in Währungseinheiten.
     * Absolute Summe der Steigerung bzw. Reduzierung der Erlebensfall VS
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 16, type = Betrag.class, anzahlBytes = 14, byteAdresse = 120)
    ABSOLUTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Nächste Erlebensfall VS in Währungseinheiten.
     * Erlebensfall VS ab dem nächsten Änderungstermin
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 17, type = Betrag.class, anzahlBytes = 14, byteAdresse = 134)
    NAECHSTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Leerstellen.
     */
    @FeldInfo(teildatensatz = 1, nr = 18, type = AlphaNumFeld.class, anzahlBytes = 108, byteAdresse = 148)
    LEERSTELLEN
}
