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
 * "Leben - Fondsdatensatz" (Satzart 0230)
 *
 * @author ralfklemmer
 * @since 19.01.2013
 */
public enum Feld230 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(teildatensatz = 1, type = Feld1bis7.class)
    INTRO1,

    /**
     * Laufende Nummer der versicherten Person (VP).
     * lfd. Nr., die im VU geführt wird
     */
    @FeldInfo(teildatensatz = 1, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE,

    /**
     * Wagnisart.
     * 7 = Fonsgebundene LV
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
     * Lfd Nummer des Fonds.
     */
    @FeldInfo(teildatensatz = 1, nr = 11, type = NumFeld.class, anzahlBytes = 4, byteAdresse = 62)
    LFD_NUMMER_DES_FONDS,

    /**
     * Wertpapierkennnummer.
     */
    @FeldInfo(teildatensatz = 1, nr = 12, type = AlphaNumFeld.class, anzahlBytes = 6, byteAdresse = 66)
    WERTPAPIERKENNNUMMER,

    /**
     * Fondskennung.
     * Fonds-Schlüssel zur eindeutigen Darstellung im System
     */
    @FeldInfo(teildatensatz = 1, nr = 13, type = AlphaNumFeld.class, anzahlBytes = 10, byteAdresse = 72)
    FONDSKENNUNG,

    /**
     * ISIN-Nummer.
     * internationale Wertpapierkennnummer gemäß ISO-Norm 6166
     */
    @FeldInfo(teildatensatz = 1, nr = 14, type = AlphaNumFeld.class, anzahlBytes = 12, byteAdresse = 82)
    ISIN_NUMMER,

    /**
     * Fondsname.
     */
    @FeldInfo(teildatensatz = 1, nr = 15, type = AlphaNumFeld.class, anzahlBytes = 50, byteAdresse = 94)
    FONDSNAME,

    /**
     * Thesaur.
     * Kennzeichen, ob Fond thesaurierend
     * 0 = nein, 1 = ja
     */
    @FeldInfo(teildatensatz = 1, nr = 16, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 144)
    THESAUR,

    /**
     * Anteile.
     * Anteile in Stück
     * (8,6 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 17, type = Betrag.class, anzahlBytes = 14, byteAdresse = 145)
    ANTEILE,

    /**
     * Bezugsdatum.
     * Gültigkeitstermin des Anteilsstandes
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 18, type = Datum.class, anzahlBytes = 8, byteAdresse = 159)
    BEZUGSDAT,

    /**
     * Prozentsatz.
     * Prozentualer Anteil des Beitrags mit dem dieser Fonds aktuell bespart wird
     * (3,4 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 19, type = NumFeld.class, anzahlBytes = 7, byteAdresse = 167)
    PROZENTSATZ,

    /**
     * Leerstellen.
     */
    @FeldInfo(teildatensatz = 1, nr = 20, type = AlphaNumFeld.class, anzahlBytes = 82, byteAdresse = 174)
    LEERSTELLEN

}
