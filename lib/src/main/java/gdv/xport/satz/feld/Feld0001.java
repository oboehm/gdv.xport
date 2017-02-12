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
 * (c)reated 08.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.feld;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.VorsatzFeld1bis6;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 1
 * (Vorsatz).
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (08.03.2011)
 */
public enum Feld0001 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 6 sind fuer jeden Vorsatz identisch. */
    @FelderInfo(
            teildatensatz = 1,
            type = VorsatzFeld1bis6.class
    )
    INTRO1,

    /**
     * Satzart 0001 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 7,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 96
            )
    VERSION_SATZART_0001,

    /**
     * Satzart 0100 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 99
            )
    VERSION_SATZART_0100,

    /**
     * Satzart 0200 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 102
            )
    VERSION_SATZART_0200,

    /**
     * Satzart 0210 050 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 105
            )
    VERSION_SATZART_0210_050,

    /**
     * Satzart 0220 051 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 108
            )
    VERSION_SATZART_0220_051,

    /**
     * Satzart 0220 052 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 111
            )
    VERSION_SATZART_0220_052,

    /**
     * Satzart 0220 053 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 114
            )
    VERSION_SATZART_0220_053,

    /**
     * Satzart 0220 054 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 117
            )
    VERSION_SATZART_0220_054,

    /**
     * Satzart 0220 059 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 120
            )
    VERSION_SATZART_0220_059,

    /**
     * Satzart 0210 040 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 123
            )
    VERSION_SATZART_0210_040,

    /**
     * Satzart 0220 040 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 126
            )
    VERSION_SATZART_0220_040,

    /**
     * Satzart 0210 030 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 129
            )
    VERSION_SATZART_0210_030,

    /**
     * Satzart 0220 030 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 132
            )
    VERSION_SATZART_0220_030,

    /**
     * Satzart 0210 010 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 135
            )
    VERSION_SATZART_0210_010,

    /**
     * Satzart 0220 010 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 138
            )
    VERSION_SATZART_0220_010,

    /**
     * Satzart 0210 130 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 141
            )
    VERSION_SATZART_0210_130,

    /**
     * Satzart 0220 130 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 144
            )
    VERSION_SATZART_0220_130,

    /**
     * Satzart 0210 110 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 147
            )
    VERSION_SATZART_0210_110,

    /**
     * Satzart 0220 110 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 150
            )
    VERSION_SATZART_0220_110,

    /**
     * Satzart 0210 140 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 153
            )
    VERSION_SATZART_0210_140,

    /**
     * Satzart 0220 140 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 156
            )
    VERSION_SATZART_0220_140,

    /**
     * Satzart 0210 020 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 159
            )
    VERSION_SATZART_0210_020,

    /**
     * Satzart 0220 020 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 162
            )
    VERSION_SATZART_0220_020,

    /**
     * Satzart 0210 070 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 165
            )
    VERSION_SATZART_0210_070,

    /**
     * Satzart 0220 070 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 168
            )
    VERSION_SATZART_0220_070,

    /**
     * Satzart 0210 Version Feuer-Industrie/Gewerbliche Sachversicherung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 171
            )
    VERSION_SATZART_0210_FEUER,

    /**
     * Satzart 0220 Version Feuer-Industrie/Gewerbliche Sachversicherung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 174
            )
    VERSION_SATZART_0220_FEUER,

    /**
     * Satzart 0210 510 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 177
            )
    VERSION_SATZART_0210_510,

    /**
     * Satzart 0220 510 Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 180
            )
    VERSION_SATZART_0220_510,

    /**
     * Satzart 0210 Version Technische Versicherungen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 36,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 183
            )
    VERSION_SATZART_0210_TECH_VERS,

    /**
     * Satzart 0220 Version Technische Versicherungen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 37,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 186
            )
    VERSION_SATZART_0220_TECH_VERS,

    /**
     * Satzart 0210 Version Transport.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 38,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 189
            )
    VERSION_SATZART_0210_TRANSPORT,

    /**
     * Satzart 0220 Version Transport.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 39,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 192
            )
    VERSION_SATZART_0220_TRANSPORT,

    /**
     * Satzart 0250 Version Transport Einzelanmeldung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 40,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 195
            )
    VERSION_SATZART_0250_TRANSPORT,

    /**
     * Satzart 0260 Version Transport Umsatzanmeldung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 41,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 198
            )
    VERSION_SATZART_0260_TRANSPORT,

    /**
     * Satzart 0210 Version Nicht einzeln definierte Sparten.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 42,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 201
            )
    VERSION_SATZART_0210_NICHT_DEF_SPARTEN,

    /**
     * Satzart 0220 Version Nicht einzeln definierte Sparten.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 43,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 204
            )
    VERSION_SATZART_0220_NICHT_DEF_SPARTEN,

    /**
     * KFZ-Baustein Version.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 44,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 207
            )
    VERSION_KFZ_BAUSTEIN,

    /**
     * Satzart 0300 Version Beteiligungsinformation.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 45,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 210
            )
    VERSION_SATZART_0300_BETEILIGUNGSINFORMATION,

    /**
     * Satzart 0400 Version Inkasso.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 46,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 213
            )
    VERSION_SATZART_0400_INKASSO,

    /**
     * Satzart 0410 Version Inkasso.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 47,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 216
            )
    VERSION_SATZART_0410_INKASSO,

    /**
     * Satzart 0430 Version Inkasso.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 48,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 219
            )
    VERSION_SATZART_0430_INKASSO,

    /**
     * Satzart 0500 Version Schadeninformation.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 49,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 222
            )
    VERSION_SATZART_0500_SCHADENINFORMATION,

    /**
     * Satzart 9999 Version Nachsatz.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 50,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 225
            )
    VERSION_SATZART_9999,

    /**
     * Satzart 0420 Version Versicherungsteuerabrechnung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 51,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 228
            )
    VERSION_SATZART_0420_VERSICHERUNGSTEUERABRECHNUNG,

    /**
     * Satzart 0450 Version Inkassoabrechnung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 52,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 231
            )
    VERSION_SATZART_0450_INKASSOABRECHNUNG,

    /**
     * Satzart 0550 Version Schadenabrechnung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 53,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 234
            )
    VERSION_SATZART_0550_SCHADENABRECHNUNG,

    /**
     * Art des Absenders.
     * 1 = VU
     * 2 = Vermittler
     * 3 = VU (Nicht GDV-Schlüssel)
     * 9 = Sonstige (z. B. Clearingstelle, Firmenvermittler)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 54,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 237,
            bezeichnung = "Art des Absenders"
            )
    ART_DES_ABSENDERS,

    /**
     * Art des Adressaten.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 55,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 238
            )
    ART_DES_ADRESSATEN,

    /**
     * VU-Abrechnungsstelle.
     * z. B. 05 = München (Bei der Allianz)
     * Sollte keine Abrechnungstelle vorhanden sein,
     * muss "00" geschlüsselt werden.
     * Bei Lieferung von Mitversicherungsdaten muss "00" geschlüsselt werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 56,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 239
            )
    VU_ABRECHNUNGSSTELLE,

    /**
     * Bestandsführende Geschäftsstelle.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 57,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 241
            )
    BESTANDSFUEHRENDE_GESCHAEFTSSTELLE,

    /**
     * Satzart 0350 Version Klausel-Datensatz.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 58,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 243
            )
    VERSION_SATZART_0350_KLAUSELN,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 59,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 246
            )
    LEERSTELLEN,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 60,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 256
            )
    SATZNUMMER1,

    /////   Teildatensatz 2   /////////////////////////////////////////////////

    /** Feld 1 - 6 sind fuer jeden Vorsatz identisch. */
    @FelderInfo(
            teildatensatz = 2,
            type = VorsatzFeld1bis6.class
    )
    INTRO2,

    /**
     * Satzart 0211 050 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 7,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 96
            )
    VERSION_SATZART_0211_050,

    /**
     * Satzart 0221 051 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 99
            )
    VERSION_SATZART_0221_051,

    /**
     * Satzart 0221 052 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 102
            )
    VERSION_SATZART_0221_052,

    /**
     * Satzart 0221 053 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 105
            )
    VERSION_SATZART_0221_053,

    /**
     * Satzart 0221 054 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 108
            )
    VERSION_SATZART_0221_054,

    /**
     * Satzart 0221 059 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 111
            )
    VERSION_SATZART_0221_059,

    /**
     * Satzart 0221 055 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 114
            )
    VERSION_SATZART_0221_055,

    /**
     * Satzart 0211 040 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 117
            )
    VERSION_SATZART_0211_040,

    /**
     * Satzart 0221 040 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 120
            )
    VERSION_SATZART_0221_040,

    /**
     * Satzart 0221 030 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 123
            )
    VERSION_SATZART_0221_030,

    /**
     * Satzart 0211 010 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 126
            )
    VERSION_SATZART_0211_010,

    /**
     * Satzart 0221 010 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 129
            )
    VERSION_SATZART_0221_010,

    /**
     * Satzart 0211 130 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 132
            )
    VERSION_SATZART_0211_130,

    /**
     * Satzart 0221 130 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 135
            )
    VERSION_SATZART_0221_130,

    /**
     * Satzart 0211 110 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 138
            )
    VERSION_SATZART_0211_110,

    /**
     * Satzart 0221 110 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 141
            )
    VERSION_SATZART_0221_110,

    /**
     * Satzart 0211 140 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 23,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 144
            )
    VERSION_SATZART_0211_140,

    /**
     * Satzart 0221 140 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 24,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 147
            )
    VERSION_SATZART_0221_140,

    /**
     * Satzart 0221 070 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 150
            )
    VERSION_SATZART_0221_070,

    /**
     * Satzart 0211 Version Feuer-Industrie/Gewerbliche Sachversicherung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 153
            )
    VERSION_SATZART_0211_FEUER,

    /**
     * Satzart 0221 Version Feuer-Industrie/Gewerbliche Sachversicherung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 27,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 156
            )
    VERSION_SATZART_0221_FEUER,

    /**
     * Satzart 0221 510 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 28,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 159
            )
    VERSION_SATZART_0221_510,

    /**
     * Satzart 0211 Version Technische Versicherungen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 29,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 162
            )
    VERSION_SATZART_0211_TECH_VERS,

    /**
     * Satzart 0221 Version Technische Versicherungen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 165
            )
    VERSION_SATZART_0221_TECH_VERS,

    /**
     * Satzart 0211 Version Transport.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 168
            )
    VERSION_SATZART_0211_TRANSPORT,

    /**
     * Satzart 0221 Version Transport.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 171
            )
    VERSION_SATZART_0221_TRANSPORT,

    /**
     * Satzart 0251 Version Transport Einzelanmeldung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 33,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 174
            )
    VERSION_SATZART_0251_TRANSPORT,


    /**
     * Satzart 0211 Version Nicht einzeln definierte Sparten.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 34,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 177
            )
    VERSION_SATZART_0211_NICHT_DEF_SPARTEN,

    /**
     * Satzart 0221 Version Nicht einzeln definierte Sparten.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 35,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 180
            )
    VERSION_SATZART_0221_NICHT_DEF_SPARTEN,

    /**
     * Satzart 0210 550 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 36,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 183
            )
    VERSION_SATZART_0210_550,

    /**
     * Satzart 0220 550 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 37,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 186
            )
    VERSION_SATZART_0220_550,

    /**
     * Satzart 0270 550 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 38,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 189
            )
    VERSION_SATZART_0270_550,

    /**
     * Satzart 0280 550 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 39,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 192
            )
    VERSION_SATZART_0280_550,

    /**
     * Satzart 0291 550 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 40,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 195
            )
    VERSION_SATZART_0291_550,

    /**
     * Satzart 0292 550 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 41,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 198
            )
    VERSION_SATZART_0292_550,

    /**
     * Satzart 0293 550 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 42,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 201
            )
    VERSION_SATZART_0293_550,

    /**
     * Satzart 0294 550 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 43,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 204
            )
    VERSION_SATZART_0294_550,

    /**
     * Satzart 0295 550 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 44,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 207
            )
    VERSION_SATZART_0295_550,

    /**
     * Satzart 0052 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 45,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 210
            )
    VERSION_SATZART_0052,

    /**
     * Satzart 0102 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 46,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 213
            )
    VERSION_SATZART_0102,

    /**
     * Satzart 0212 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 47,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 216
            )
    VERSION_SATZART_0212,

    /**
     * Satzart 0352 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 48,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 219
            )
    VERSION_SATZART_0352,

    /**
     * Satzart 0362 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 49,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 222
            )
    VERSION_SATZART_0362,

    /**
     * Satzart 0382 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 50,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 225
            )
    VERSION_SATZART_0382,

    /**
     * Satzart 9950 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 51,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 228
            )
    VERSION_SATZART_9950,

    /**
     * Satzart 9952 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 52,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 231
            )
    VERSION_SATZART_9952,

    /**
     * Satzart 0210 580 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 53,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 234
            )
    VERSION_SATZART_0210_580,

    /**
     * Satzart 0220 580 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 54,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 237
            )
    VERSION_SATZART_0220_580,

    /**
     * Satzart 0372 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 55,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 240
            )
    VERSION_SATZART_0372,

    /**
     * Satzart 0600 Version.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 56,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 243
            )
    VERSION_SATZART_0600,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 57,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 246
            )
    LEERSTELLEN2,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 58,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 256
            )
    SATZNUMMER2,

    /////   Teildatensatz 3   /////////////////////////////////////////////////

    /** Feld 1 - 6 sind fuer jeden Vorsatz identisch. */
    @FelderInfo(
            teildatensatz = 3,
            type = VorsatzFeld1bis6.class
    )
    INTRO,

    /**
     * Satzart 0392 Version eVB-Nummer.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 7,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 96
            )
    VERSION_SATZART_0392_EVB,

    /**
     * Satzart 0230 Version Fondsdatensatz - Leben.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 99
            )
    VERSION_SATZART_0230_LEBEN,

    /**
     * Satzart 0202 Version Allgemeine Antragsdaten.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 102
            )
    VERSION_SATZART_0202_ALLG_ANTRAGSDATEN,

    /**
     * Satzart 0222 Version Unfallspezifische Antragsdaten.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 105
            )
    VERSION_SATZART_0222,

    /**
     * Satzart 0230 Version Unfall Leistungsarten.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 108
            )
    VERSION_SATZART_0230_UNFALL,

    /**
     * Satzart 0390 Version Rabatte und Zuschläge.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 111
            )
    VERSION_SATZART_0390_RABATTE,

    /**
     * Satzart 0342 Version Begleitdokumente und Signaturen.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 114
            )
    VERSION_SATZART_0342_BEGLEITDOK,

    /**
     * Satzart 9951 Version MIME-Datei.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 117
            )
    VERSION_SATZART_9951_MIME,

    /**
     * Satzart 0210 560 Version Kapitalanlage.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 120
            )
    VERSION_SATZART_0210_560,

    /**
     * Satzart 0220 560 Version Kapitalanlage.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 123
            )
    VERSION_SATZART_0220_560,

    /**
     * Satzart 0210 570 Version Baufinanzierung.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 126
            )
    VERSION_SATZART_0210_570,

    /**
     * Satzart 0220 570 Version Baufinanzierung.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 129
            )
    VERSION_SATZART_0220_570,

    /**
     * Satzart 0210 684 Version Tierkranken.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 132
            )
    VERSION_SATZART_0210_684,

    /**
     * Satzart 0220 684 Version Tierkranken.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 135
            )
    VERSION_SATZART_0220_684,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 118,
            byteAdresse = 138
            )
    LEERSTELLEN3,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 22,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 256
            )
    SATZNUMMER3;

}
