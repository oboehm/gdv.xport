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

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 230, Sparte 30.
 * "Unfall Leistungsarten" (Satzart 0230)
 *
 * @author Ralf
 * @since 20.02.2013
 */
public enum Feld230 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 1,
            type = Feld1bis7.class)
    INTRO2,

    /**
     * Lfd. Nummer der versicherten Person (VP) / Personengruppe.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE,

    /**
     * Art der Leistung<br/>
     * siehe Anlage 80
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 49)
    ART_DER_LEISTUNG,

    /**
     * Bezeichnung der Leistung<br/>
     * Text zur Art der Leistung
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 52)
    BEZEICHNUNG_DER_LEISTUNG,

    /**
     * Laufende Nummer zur Art der Leistung<br/>
     * Laufende Nummer zur Art der Leistung,<br/>
     * beginnend mit 001 innerhalb der lfd. Nummer der Person / Personengruppen
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 82)
    LFD_NUMMER_ZUR_ART_DER_LEISTUNG,

    /**
     * Art der Auszahlung<br/>
     * 01 = Kapital<br/>
     * 02 = Rente<br/>
     * 03 = Sachleistung<br/>
     * 04 = Prämienfreistellung<br/>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 85)
    ART_DER_AUSZAHLUNG,

    /**
     * Leistungszahlungsweise<br/>
     * Es handelt sich hier um die Zahlweise bei Eintritt einer Leistung! 1 =
     * jährlich<br/>
     * 2 = halbjährlich<br/>
     * 3 = täglich<br/>
     * 4 = vierteljährlich<br/>
     * 5 = Sonstiges<br/>
     * 6 = Einmalzahlung<br/>
     * 7 = noch zu bestimmen<br/>
     * 8 = monatlich
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 87)
    LEISTUNGSZAHLUNGSWEISE,

    /**
     * Beginn der Zahlung ab Tag.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 88)
    BEGINN_DER_ZAHLUNG_AB_TAG,

    /**
     * Leistung in WE<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 91)
    LEISTUNG,

    /**
     * Beitragssatz<br/>
     * (3,4 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = NumFeld.class,
            nachkommaStellen = 4,
            anzahlBytes = 7,
            byteAdresse = 105)
    BEITRAGSSATZ,

    /**
     * Art des Beitragssatzes<br/>
     * 01 = von Tausend (Promille)<br/>
     * 02 = von Hundert (Prozent)<br/>
     * 03 = Faktor<br/>
     * 04 = Fester Beitrag
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 112)
    ART_DES_BEITRAGSSATZES,


    /**
     * Beitrag in WE<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 114)
    BEITRAG,

    /**
     * Prozentsatz progressive Invalidität / Mehrleistung bei Invalidität<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 126)
    PROZENTSATZ_PROGRESSIVE_INVALIDITAET_MEHRLEISTUNG_BEI_INVALIDITAET,

    /**
     * Leistung ab Invaliditätsgrad in Prozent<br/>
     * (3,4 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = NumFeld.class,
            nachkommaStellen = 4,
            anzahlBytes = 7,
            byteAdresse = 132)
    LEISTUNG_AB_INVALIDITAETSGRAD_IN_PROZENT,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 117,
            byteAdresse = 139)
    LEERSTELLEN,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 256)
    SATZNUMMER1,

}
