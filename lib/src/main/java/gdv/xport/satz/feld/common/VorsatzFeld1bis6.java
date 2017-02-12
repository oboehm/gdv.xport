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
 * (c)reated 28.08.2012 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.feld.common;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.feld.*;

/**
 * Jeder Vorsatz beginnt mit denselben 6 Feldern. Dies sind die Felder, die in diesem Enum zusammengefasst sind.
 *
 * @author oliver
 * @since 0.9.0 (22.11.2012)
 */
public enum VorsatzFeld1bis6 {

    /**
     * Satzart.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 1,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 1
            )
    SATZART,

    /**
     * VU-Nummer
     *
     * Gemäß VU-Verzeichnis der BaFin, linksbündig,
     * Das VU-Nr.-Verzeichnis kann bei der Bundesanstalt für Finanzdienstleistungsaufsicht
     * in Bonn angefordert werden (Graurheindorfer Str. 108, 53117 Bonn, www.bafin.de).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 2,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 5
    )
    VU_NUMMER,

    /**
     * Absender
     *
     * Bei Übertragung via DFÜ kann hier statt
     * Klartext eine Empfänger- oder
     * Absender-Codierung eingestellt werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 3,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 10
    )
    ABSENDER,

    /**
     * Adressat.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 4,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 40
            )
    ADRESSAT,

    /**
     * Erstellungs-Datum- Zeitraum vom- Zeitraum bis.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 5,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 70
            )
    ERSTELLUNGSDAT_ZEITRAUM_VOM,

    /**
     * Erstellungs-Datum- Zeitraum vom- Zeitraum bis.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 5,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 78
            )
    ERSTELLUNGSDAT_ZEITRAUM_BIS,

    /**
     * Geschäftsstelle und Vermittler.
     * Die geschäftsführende Geschäftsstelle und der Vermittler
     * ohne Sonderzeichen
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 6,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 86
            )
    VERMITTLER;

}
