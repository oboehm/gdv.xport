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

package gdv.xport.satz.feld.sparte10.wagnisart13;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.Datum;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 10 
 * (Leben - Kapitallebens-/Risikovers. = Wagnisart 1 u. 3 - Wertungssummen" (Satzart 0220)).
 *
 * @author ralfklemmer
 * @since 17.01.2013
 */
public enum Feld220Wagnis13Wertungssummen {

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
     * Laufende Nummer zur Wagnisart.
     */
    @FeldInfo(teildatensatz = 1, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
    LFD_NUMMER_ZUR_WAGNISART,

    /**
     * Laufende Nummer der Satzart.
     * Lfd. Nummer der Satzart 0220.010.2/6 innerhalb der gleichen Folgenummer
     * (z. B. n-fache hintereinanderfolgende Lieferung der Satzart 0220.010.2/6,
     * wenn mehrere Bezugsrechte vorhanden)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 62)
    LFD_NUMMER_SATZART,

    /**
     * Provisionspflichtige Beitragssumme in Währungseinheiten.
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 12, type = Betrag.class, anzahlBytes = 12, byteAdresse = 64)
    PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Provisionspflichtige Wertungssumme in Währungseinheiten.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 13, type = Betrag.class, anzahlBytes = 12, byteAdresse = 76)
    PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Wertungsbasis.
     * 1 = VS, 2 = Beitrag
     */
    @FeldInfo(teildatensatz = 1, nr = 14, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 88)
    WERTUNGSBASIS,

    /**
     * Wertungsmodell.
     * Modell der Provisionierung (VU-individuell), z. B. 01 = Wertungssummenscheibenmodell
     */
    @FeldInfo(teildatensatz = 1, nr = 15, type = AlphaNumFeld.class, anzahlBytes = 2, byteAdresse = 89)
    WERTUNGSMODELL,

    /**
     * Buchungskennzeichen.
     * Modell der Provisionierung (VU-individuell), z. B. 01 = Wertungssummenscheibenmodell
     */
    @FeldInfo(teildatensatz = 1, nr = 16, type = AlphaNumFeld.class, anzahlBytes = 2, byteAdresse = 91)
    BUCHUNGSKENNZEICHEN,

    /**
     * Haftungswertungssumme in Währungseinheiten.
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 17, type = Betrag.class, anzahlBytes = 12, byteAdresse = 93)
    HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN,

    /**
     * Haftung ab.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 18, type = Datum.class, anzahlBytes = 8, byteAdresse = 105)
    HAFTUNG_AB,

    /**
     * Haftung bis.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 19, type = Datum.class, anzahlBytes = 8, byteAdresse = 113)
    HAFTUNG_BIS,

    /**
     * Ursprüngliches Haftungsbeginndatum.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 20, type = Datum.class, anzahlBytes = 8, byteAdresse = 121)
    URSPRUENGLICHES_HAFTUNGSBEGINNDAT,

    /**
     * Provisionspflichtige Beitragssumme in Währungseinheiten.
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 21, type = Betrag.class, anzahlBytes = 12, byteAdresse = 129)
    PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN2,

    /**
     * Provisionspflichtige Wertungssumme in Währungseinheiten.
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 22, type = Betrag.class, anzahlBytes = 12, byteAdresse = 141)
    PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2,

    /**
     * Wertungsbasis.
     * 1 = VS, 2 = Beitrag
     */
    @FeldInfo(teildatensatz = 1, nr = 23, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 153)
    WERTUNGSBASIS2,

    /**
     * Wertungsmodell.
     * Modell der Provisionierung (VU-individuell), z. B. 01 = Wertungssummenscheibenmodell
     */
    @FeldInfo(teildatensatz = 1, nr = 24, type = AlphaNumFeld.class, anzahlBytes = 2, byteAdresse = 154)
    WERTUNGSMODELL2,

    /**
     * Buchungskennzeichen.
     * Modell der Provisionierung (VU-individuell), z. B. 01 = Wertungssummenscheibenmodell
     */
    @FeldInfo(teildatensatz = 1, nr = 25, type = AlphaNumFeld.class, anzahlBytes = 2, byteAdresse = 156)
    BUCHUNGSKENNZEICHEN2,

    /**
     * Haftungswertungssumme in Währungseinheiten.
     * (10,2 Stellen)
     */
    @FeldInfo(teildatensatz = 1, nr = 26, type = Betrag.class, anzahlBytes = 12, byteAdresse = 158)
    HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2,

    /**
     * Haftung ab.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 27, type = Datum.class, anzahlBytes = 8, byteAdresse = 170)
    HAFTUNG_AB2,

    /**
     * Haftung bis.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(teildatensatz = 1, nr = 28, type = Datum.class, anzahlBytes = 8, byteAdresse = 178)
    HAFTUNG_BIS2,

    /**
     * Leerstellen.
     */
    @FeldInfo(teildatensatz = 1, nr = 29, type = AlphaNumFeld.class, anzahlBytes = 70, byteAdresse = 186)
    LEERSTELLEN
}
