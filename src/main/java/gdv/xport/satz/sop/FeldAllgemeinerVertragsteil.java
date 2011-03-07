/*
 * Copyright (c) 2010 by agentes
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
 * (c)reated 06.03.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz.sop;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.feld.*;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 200
 * (Allgemeiner Vertragsteil).
 *
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.6 (06.03.2011)
 */
public enum FeldAllgemeinerVertragsteil {
    
    /////   Allgemeiner Teil   ////////////////////////////////////////////////

//    @FeldInfo(
//            nr = 1,
//            type = NumFeld.class,
//            anzahlBytes = 4,
//            byteAdresse = 1,
//            erlaeuterung = "konstant 200"
//    )
//    SATZART,
//
//    @FeldInfo(
//            nr = 2,
//            type = VUNummer.class,
//            anzahlBytes = 5,
//            byteAdresse = 5
//    )
//    VU_NUMMER,
//
//    @FeldInfo(
//            nr = 3,
//            type = AlphaNumFeld.class,
//            anzahlBytes = 1,
//            byteAdresse = 10
//    )
//    BUENDELUNGSKENNZEICHEN,
//
//    @FeldInfo(
//            nr = 4,
//            type = NumFeld.class,
//            anzahlBytes = 3,
//            byteAdresse = 11
//    )
//    SPARTE,
//
//    @FeldInfo(
//            nr = 5,
//            type = AlphaNumFeld.class,
//            anzahlBytes = 17,
//            byteAdresse = 14
//    )
//    VERSICHERUNGSSCHEINNUMMER,
//
//    @FeldInfo(
//            nr = 6,
//            type = NumFeld.class,
//            anzahlBytes = 2,
//            byteAdresse = 31
//    )
//    FOLGENUMMER,
//
//    @FeldInfo(
//            nr = 7,
//            type = AlphaNumFeld.class,
//            anzahlBytes = 10,
//            byteAdresse = 33
//    )
//    VERMITTLER,
    
    /////   Satzspezifischer Teil (Satz 1)  ///////////////////////////////////

    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 43
    )
    INKASSOART,

    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 44
    )
    VERTRAGSBEGINN,

    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 52
    )
    VERTRAGSABLAUF,

    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 60
    )
    HAUPTFAELLIGKEIT,

    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Zeichen.class,
            byteAdresse = 68
    )
    ZAHLUNGSWEISE,

    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = Zeichen.class,
            byteAdresse = 69
    )
    VERTRAGSSTATUS,

    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 70
    )
    ABGANGSGRUND,

    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 72
    )
    ABGANGSDATUM,

    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 80
    )
    AENDERUNGSGRUND,

    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 90
    )
    AENDERUNGSDATUM,

    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = Zeichen.class,
            byteAdresse = 98
    )
    AFB,

    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = NumFeld.class,
            nachkommaStellen = 3,
            anzahlBytes = 5,
            byteAdresse = 99
    )
    ANTEIL_IN_PROZENT,

    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 104
    )
    AUFTRAGSNR_VERMITTLER,

    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 113
    )
    WAEHRUNGSSCHLUESSEL,

    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = NumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 116
    )
    GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN,

    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 128
    )
    MEHRZWECKFELD,

    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = Zeichen.class,
            byteAdresse = 158
    )
    KENNZEICHEN_VERS_STEUER_FREI,

    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 159
    )
    KUNDENNR_VERSICHERER,

    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 176
    )
    KUNDENNR_VERMITTLER,

    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Zeichen.class,
            byteAdresse = 193
    )
    AUFSICHTSFREIER_VERTRAG,

    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = Zeichen.class,
            byteAdresse = 194
    )
    AUFTEILUNG_VERSICHERUNGSSTEUER,

    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 195
    )
    RESTLAUFZEIT_VERTRAG,

    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 197
    )
    LAUFZEITRABATT_IN_PROZENT,

    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 201
    )
    ANTRAGSDATUM,

    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 209
    )
    REFERENZ_VERSICHERUNGSSCHEINNUMMER,

    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = Zeichen.class,
            byteAdresse = 226
    )
    SPEZIFIKATION_REFERENZ_VERSICHERUNGSSCHEINNUMMER,

    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 227
    )
    ORDNUNGSBEGRIFF,

    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 244
    )
    WAEHRUNG_DOKUMENTE_FUER_VN,

    @FeldInfo(
            teildatensatz = 1,
            nr = 36,
            type = Zeichen.class,
            byteAdresse = 247
    )
    ERWEITERUNGSSATZ_VORHANDEN,

    @FeldInfo(
            teildatensatz = 1,
            nr = 37,
            type = Zeichen.class,
            byteAdresse = 248
    )
    EINZAHLUNG_AUSSCHUETTUNG,
    
    /////   Satzspezifischer Teil (Satz 1)  ///////////////////////////////////

    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = Zeichen.class,
            byteAdresse = 43
    )
    KUENDIGUNGSKLAUSEL,

    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 44
    )
    VERSICHERUNGSSCHEINNUMMER_VM,

    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 61
    )
    PRODUKTFORM,

    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 6,
            byteAdresse = 66
    )
    PRODUKTFORM_GUELTIG_AB,

    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = AlphaNumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 72
    )
    GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN,

    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 84
    )
    DRUCKAUFBEREITETE_VERSICHERUNGSSCHEINNUMMER,

    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 114
    )
    PRODUKTNAME,

    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 134
    )
    RATENZAHLUNGSZUSCHLAG_IN_PROZENT,

    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 139
    )
    ANTRAGSEINGANGSDATUM,

    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 147
    )
    POLICIERUNGSDATUM,

    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 15,
            byteAdresse = 155
    )
    REGISTRIERUNGSNUMMER_VERMITTLER;
    

    
    /////   Utilities   ///////////////////////////////////////////////////////

    /**
     * Rueckt die FeldInfo-Annotation raus.
     *
     * @return the feld info
     */
    public FeldInfo getFeldInfo() {
        try {
            FeldInfo info = this.getClass().getField(this.name()).getAnnotation(FeldInfo.class);
            if (info == null) {
                throw new UnsupportedOperationException("@FeldInfo missing for " + this.name());
            }
            return info;
        } catch (NoSuchFieldException nsfe) {
            throw new InternalError("no field " + this.name() + " (" + nsfe + ")");
        }
    }

}
