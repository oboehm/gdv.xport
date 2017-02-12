/*
 * Copyright (c) 2011, 2012 by aosd.de
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
 * (c)reated 14.04.11 by oliver
 */

package gdv.xport.satz.feld.sparte30;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 222, Sparte 30.
 * "Unfallspezifische Antragsdaten" (Satzart 0222)
 *
 * @author Ralf
 * @since 20.02.2013
 */
public enum Feld222 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 30,
            teildatensatz = 1,
            type = Feld1bis7.class)
    INTRO2,

    /**
     * Laufende Nummer der versicherten Person (VP) / Personengruppe.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE1,

    /**
     * Erkrankungen.
     * Liegen oder lagen Erkrankungen im Zeitraum und Umfang gemäß Antrag vor?
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 49)
    ERKRANKUNGEN,

    /**
     * Unfälle.
     * Lagen Unfälle im Zeitraum und Umfang gemäß Antrag vor?
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 50)
    UNFAELLE,

    /**
     * Dauerschäden / Körperliche Beeinträchtigungen.
     * Bestehen Dauerschäden oder körperliche Beeinträchtigungen ?
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 51)
    DAUERSCHAEDEN_KOERPERLICHE_BEEINTRAECHTIGUNGEN,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 204,
            byteAdresse = 52)
    LEERSTELLEN,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 256)
    SATZNUMMER1,

}
