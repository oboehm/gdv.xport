/*
 * Copyright (c) 2012 by Oli B.
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
 * (c)reated 17.07.2012 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.feld.common;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;

/**
 * Der Vertragsstatus und folgende Feld-Informationen sind fuer Satz210 und
 * Sparte 30 und 70 identisch und wurden in diese Klasse herausgezogen.
 * 
 * @author oliver
 * @since 0.7.1 (17.07.2012)
 */
public enum VertragsStatus {

    /**
     * Vertragsstatus.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 43
    )
    VERTRAGSSTATUS,

    /**
     * Beginn.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 44
    )
    BEGINN,

    /**
     * Ausschluss.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = NumFeld.class,
            anzahlBytes = 8,
            byteAdresse = 52
    )
    AUSSCHLUSS,

    /**
     * Aenderungsdatum.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 60
    )
    AENDERUNGSDAT,

}
