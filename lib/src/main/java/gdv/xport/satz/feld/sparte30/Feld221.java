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
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 221, Sparte 30.
 * "Unfall - Wagnisdaten" (Satzart 0221)
 *
 * @author Ralf
 * @since 20.02.2013
 */
public enum Feld221 {

    /////   Teildatensatz 2   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 30,
            teildatensatz = 2,
            type = Feld1bis7.class
    )
    INTRO2,

    /**
     * Lfd. Nummer der versicherten Person (VP) / Personengruppe.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 43
    )
    LFD_NUMMER_VP_PERSONENGRUPPE1,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 49
    )
    SATZNUMMER1,

    /**
     * Tod (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 50
    )
    TOD,

    /**
     * Invaliditaet (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 64
    )
    INVALIDITAET,

    /**
     * Tagegeld 1 (8,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 10,
            byteAdresse = 78
    )
    TAGEGELD1,

    /**
     * Tagegeld 2 (8,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 10,
            byteAdresse = 88
    )
    TAGEGELD2,

    /**
     * Krankenhaustagegeld (8,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 10,
            byteAdresse = 98
    )
    KRANKENHAUSTAGEGELD,

    /**
     * Genesungsgeld (8,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 10,
            byteAdresse = 108
    )
    GENESUNGSGELD,

    /**
     * Uebergangsentschaedigung (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 118
    )
    UEBERGANGSENTSCHAEDIGUNG,

    /**
     * Heilkosten (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 132
    )
    HEILKOSTEN,

    /**
     * Feste Rente (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 146
    )
    FESTE_RENTE,

    /**
     * Kosmetische Operationen (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 19,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 160
    )
    KOSMETISCHE_OPERATIONEN,

    /**
     * Kurkosten (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 20,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 174
    )
    KURKOSTEN,

    /**
     * Bergungskosten (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 21,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 188
    )
    BERGUNGSKOSTEN,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 54,
            byteAdresse = 202
    )
    LEERSTELLEN1,

    /**
     * zusaetzliche Satzkennung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 23,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    ZUSAETZLICHE_SATZKENNUNG,

    /////   Teildatensatz 3   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 30,
            teildatensatz = 3,
            type = Feld1bis7.class
    )
    INTRO3,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 8,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 43
    )
    SATZNUMMER2,

    /**
     * Serviceleistungen (12,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 9,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 14,
            byteAdresse = 44
    )
    SERVICELEISTUNGEN,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 58
    )
    REFERENZNUMMER,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 185,
            byteAdresse = 65
    )
    LEERSTELLEN2,

    /**
     * Satznummerwiederholung.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 12,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 250
    )
    SATZNUMMERWIEDERHOLUNG,

    /**
     * Lfd. Nummer der versicherten Person (VP) / Personengruppe.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 13,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 251
    )
    LFD_NUMMER_VP_PERSONENGRUPPE2;

}
