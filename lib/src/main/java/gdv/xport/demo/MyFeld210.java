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
 * (c)reated 18.04.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.demo;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Datum;

/**
 * Dies ist ein Beispiel, wie die Daten fuer einen Datensatz beschrieben
 * werden. Diese Enum-Klasse hier repraesentiert den Datensatz
 * 0210.030 (Unfall, Vertragsspezifischer Teil), definiert aber nur
 * einige wenige ausgewaehlte Felder.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (18.04.2011)
 */
public enum MyFeld210 {

    /**
     * Beispiel fuer ein Datumsfeld von Byte 44 - 51.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 44
    )
    VERTRAGSBEGINN,

    /**
     * Beispiel fuer ein alhpanumerisches Feld von Byte 93 - 95.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 93
    )
    BAUJAHR;

}

