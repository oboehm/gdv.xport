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

package gdv.xport.satz.feld.sparte10;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 10 
 * (Leben - Angaben zur versicherten Person: Person = Wagnisart 0).
 *
 * @author ralfklemmer
 * @since 12.11.2012
 */
public enum Feld220Wagnis0 {

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
     * 0 = VP
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 60,
            value = "0"
    )
    WAGNISART,

    /**
     * Lfd Nummer zur Wagnisart.
     */
    @FeldInfo(teildatensatz = 1, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART,

    /**
     * Name der VP.
     */
    @FeldInfo(teildatensatz = 1, nr = 11, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 62)
    NAME_VP,

    /**
     * Vorname der VP.
     */
    @FeldInfo(teildatensatz = 1, nr = 12, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 92)
    VORNAME_VP,

    /**
     * Geburtsdatum der VP.
     */
    @FeldInfo(teildatensatz = 1, nr = 13, type = Datum.class, anzahlBytes = 8, byteAdresse = 122)
    GEBURTSDAT_VP,

    /**
     * Geschlecht der VP.
     * 0 = juristische Person, 1 = männlich, 2 = weiblich
     */
    @FeldInfo(teildatensatz = 1, nr = 14, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 130)
    GESCHLECHT_VP,

    /**
     * Berufsschlüssel.
     */
    @FeldInfo(teildatensatz = 1, nr = 15, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 131)
    BERUFSSCHLUESSEL,

    /**
     * Beruf - Text.
     */
    @FeldInfo(teildatensatz = 1, nr = 16, type = AlphaNumFeld.class, anzahlBytes = 30, byteAdresse = 134)
    BERUF_TEXT,

    /**
     * Anzahl der versicherten Personen.
     */
    @FeldInfo(teildatensatz = 1, nr = 17, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 164)
    ANZAHL_DER_VERSICHERTEN_PERSONEN,

    /**
     * Geburtsdatum der 2. VP.
     */
    @FeldInfo(teildatensatz = 1, nr = 18, type = Datum.class, anzahlBytes = 8, byteAdresse = 165)
    GEBURTSDAT_VP2,

    /**
     * Personen-/Kundennummer des Versicherers.
     */
    @FeldInfo(teildatensatz = 1, nr = 19, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 173)
    PERSONEN_KUNDENNUMMER_DES_VERSICHERERS,

    /**
     * Personen-/Kundennummer des Vermittlers.
     */
    @FeldInfo(teildatensatz = 1, nr = 20, type = AlphaNumFeld.class, anzahlBytes = 17, byteAdresse = 190)
    PERSONEN_KUNDENNUMMER_DES_VERMITTLERS,

    /**
     * Diensteintritt.
     */
    @FeldInfo(teildatensatz = 1, nr = 21, type = Datum.class, anzahlBytes = 8, byteAdresse = 207)
    DIENSTEINTRITT,

    /**
     * Mitarbeiter-Status.
     */
    @FeldInfo(teildatensatz = 1, nr = 22, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 215)
    MITARBEITER_STATUS,

    /**
     * Status seit.
     */
    @FeldInfo(teildatensatz = 1, nr = 23, type = Datum.class, anzahlBytes = 8, byteAdresse = 217)
    STATUS_SEIT,

    /**
     * Sozialversicherung Nummer.
     */
    @FeldInfo(teildatensatz = 1, nr = 24, type = AlphaNumFeld.class, anzahlBytes = 12, byteAdresse = 225)
    SOZIALVERSICHERUNG_NUMMER,

    /**
     * Staatsangehörigkeit.
     * KFZ-Länderkennzeichen, z. B. Länderkennzeichen für D = Deutschland, B = Belgien, DK = Dänemark, F =
     * Frankreich, CDN = Kanada siehe Anlage 63
     */
    @FeldInfo(teildatensatz = 1, nr = 25, type = AlphaNumFeld.class, anzahlBytes = 3, byteAdresse = 237)
    STAATSANGEHOERIGKEIT,

    /**
     * Unverfallbarkeit.
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 240)
    UNVERFALLBARKEIT,

    /**
     * Datum Unverfallbarkeit.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 241)
    DAT_UNVERFALLBARKEIT,

    /**
     * Art des Berufschluesselverzeichnisses.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = Zeichen.class,
            byteAdresse = 249)
    ART_DES_BERUFSSCHLUESSELVERZEICHNISSES,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = AlphaNumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 250)
    LEERSTELLEN,

    /**
     * Satznummer.
     */
    @FeldInfo(
          teildatensatz = 1,
          nr = 30,
          type = Zeichen.class,
          anzahlBytes = 1,
          byteAdresse = 256
    )
    SATZNUMMER;

}
