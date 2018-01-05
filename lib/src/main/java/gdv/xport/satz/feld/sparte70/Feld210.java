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
 * (c)reated 05.04.11 by oliver
 */

package gdv.xport.satz.feld.sparte70;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.*;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 210, Sparte 70.
 *
 * @author oliver
 * @since 05.04.11
 */
public enum Feld210 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 70,
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /**
     * Vertragsstatus und weitere Felder.
     */
    @FelderInfo(type = VertragsStatus.class)
    VERTRAGSSTATUS,

    /**
     * Zahlungsweise.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 68
    )
    ZAHLUNGSWEISE,

    /**
     * Hauptfaelligkeit.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 69
    )
    HAUPTFAELLIGKEIT,

    /**
     * Waehrungsschluessel.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 77
    )
    WAEHRUNGSSCHLUESSEL,

    /**
     * Beitrag in Waehrungseinheiten (10,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 80
    )
    BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Abschlussprovision (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 92
    )
    ABSCHLUSSPROVISION,

    /**
     * Kennzeichen fuer abweichende Abschlussprovision.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 97
    )
    KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION,

    /**
     * Folgeprovision (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 98
    )
    FOLGEPROVISION,

    /**
     * Kennzeichen fuer abweichende Folgeprovision.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 103
    )
    KENNZEICHEN_ABWEICHENDE_FOLGEPROVISION,

    /**
     * Abweichende VU-Nr.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 104
    )
    ABWEICHENDE_VU_NR,

    /**
     * Kennzeichen zur Erlaeuterung der abweichenden VU-Nr.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 109
    )
    KENNZEICHEN_ABWEICHENDE_VU_NR,

    /**
     * Restlaufzeit des Vertrages.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = Datum.class,
            anzahlBytes = 2,
            byteAdresse = 110
    )
    RESTLAUFZEIT_DES_VERTRAGES,

    /**
     * Laufzeitrabatt in % (2,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 112
    )
    LAUFZEITRABATT_IN_PROZENT,

    /**
     * Produktform.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 116
    )
    PRODUKTFORM,

    /**
     * Produktform gueltig ab.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 121
    )
    PRODUKTFORM_GUELTIG_AB,

    /**
     * Produktname.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 127
    )
    PRODUKTNAME,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 147
    )
    REFERENZNUMMER,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 103,
            byteAdresse = 154
    )
    LEERSTELLEN;

}
