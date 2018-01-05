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
 * "Leben - Risikozusatz = Wagnisart 5 - Zukünftige Summenänderung" (Satzart 0221)
 *
 * @author ralfklemmer
 * @since 19.01.2013
 */
public enum Feld221Wagnis5ZukSummenaenderungen {

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
     * Lfd. Nummer der Satzart.
     * Lfd. Nummer der Satzart 0221.010.5/8 innerhalb der gleichen Folgenummer
     * (z. B. n-fache hintereinanderfolgende Lieferung der Satzart 0221.010.5/8, wenn mehrere zukünftige
     * Summenänderungen feststehen)
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
    ABSOLUTE_TODESFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Nächste Todesfall VS in Währungseinheiten.
     * Todesfalleistung ab dem nächsten Änderungstermin
     * (12,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 14, type = Betrag.class, anzahlBytes = 14, byteAdresse = 92)
    NAECHSTE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN,

    /**
     * Leerstellen.
     */
    @FeldInfo(teildatensatz = 1, nr = 15, type = AlphaNumFeld.class, anzahlBytes = 150, byteAdresse = 106)
    LEERSTELLEN

}
