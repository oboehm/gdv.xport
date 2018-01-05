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
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;

/**
 * Jeder Datensatz beginnt mit denselben 7 Feldern. Dies sind die Felder, die in diesem Enum zusammengefasst sind.
 *
 * @author oliver
 * @since 0.7.1 (28.08.2012)
 */
public enum Feld1bis7 {

    /**
     * Satzart.
     */
    @FeldInfo(nr = 1, type = NumFeld.class, anzahlBytes = 4, byteAdresse = 1)
    SATZART,

    /**
     * Gemaess VU-Verzeichnis der BaFin, linksbuendig. Das VU-Nr.-Verzeichnis
     * kann bei der Bundesanstalt fuer Finanzdienstleistungsaufsicht in Bonn
     * angefordert werden (Graurheindorfer Str. 108, 53117 Bonn, www.bafin.de).
     * Der neue vierstellige VU.-Nr.-Schluessel gilt ab 01.01.1993
     */
    @FeldInfo(
            nr = 2,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 5,
            bezeichnung = "VU-Nummer")
    VU_NUMMER,

    /**
     * Kennzeichen für gebuendelte Vertraege. 1 = gebuendelt.
     */
    @FeldInfo(nr = 3, type = Zeichen.class, byteAdresse = 10)
    BUENDELUNGSKENNZEICHEN,

    /**
     * Versicherungszweig gemaess Verordnung ueber die Berichterstattung von Versicherungsunternehmen gegenueber der
     * BaFin (BerVersV) Abschnitt C.
     */
    @FeldInfo(nr = 4, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 11)
    SPARTE,

    /**
     * Versicherungsschein-Nummer, rechtsbuendig, mit Leerstellen linksbuendig
     * auffuellen ohne Sonderzeichen.
     */
    @FeldInfo(
            nr = 5,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 14,
            bezeichnung = "Versicherungsschein-Nummer")
    VERSICHERUNGSSCHEINNUMMER,

    /**
     * Laufende Nummer innerhalb einer Versicherungsschein-Nummer ueber alle Satzarten fuer die gleiche Datensendung.
     */
    @FeldInfo(nr = 6, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 31)
    FOLGENUMMER,

    /**
     * Die geschaeftsfuehrende Geschaeftsstelle und der Vermittler ohne Sonderzeichen.
     */
    @FeldInfo(nr = 7, type = AlphaNumFeld.class, anzahlBytes = 10, byteAdresse = 33)
    VERMITTLER;

}
