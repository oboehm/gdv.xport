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
 * (c)reated 07.04.11 by oliver
 */

package gdv.xport.satz.feld.sparte55;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 55.
 *
 * @author Ken Schosinsky
 * @since 16.04.2018
 */
public enum Feld220 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,
    
    /**
     * Bausteinschlüssel
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 43
    )
    BAUSTEINSCHLUESSEL,
    
    /**
     * Bausteinbezeichnung
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 63
    )
    BAUSTEINBEZEICHNUNG,

    /**
     * Baustein-Beginn.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 93
    )
    BAUSTEIN_BEGINN,

    /**
     * Baustein-Ausschluss.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 101
    )
    BAUSTEIN_AUSSCHLUSS,

    /**
     * Baustein-Änderungsdatum.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 109
    )
    BAUSTEIN_AENDERUNGSDAT,

    /**
     * Tarifierungsmerkmal.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 117
    )
    TARIFIERUNGSMERKMAL,
    
    /**
     * Selbstbehalt in Währungseinheiten.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = NumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 127
    )
    SELBSTBEHALT_IN_WAEHRUNGSEINHEITEN,

    /**
     * Waehrungsschluessel.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 137
    )
    WAEHRUNGSSCHLUESSEL,
    
    /**
     * Selbstbehalt in Prozent.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 140
    )
    SELBSTBEHALT_IN_PROZENT,
    
    /**
     * Rabattgrundjahr.
     * (JJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = NumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 145
    )
    RABATTGRUNDJAHR,
    
    /**
     * SF/S-Klasse.
     */
    @FeldInfo(
            bezeichnung = "SF/S-Klasse",
            teildatensatz = 1,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 149
    )
    SFS_KLASSE,

    /**
     * Beitragssatz.
     * (3,4 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 154)
    BEITRAGSSATZ,

    /**
     * Beitrag in Waehrungseinheiten (7,1 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = Betrag.class,
            nachkommaStellen = 1,
            anzahlBytes = 8,
            byteAdresse = 157
    )
    BEITRAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Waehrungsschluessel.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 165
    )
    WAEHRUNGSSCHLUESSEL2,

    /**
     * Baustein-Zuschläge in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 168
    )
    BAUSTEIN_ZUSCHLAEGE_IN_PROZENT,

    /**
     * Baustein-Zuschläge in % (3,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 173
    )
    BAUSTEIN_ABSCHLAEGE_IN_PROZENT,

    /**
     * Baustein-Zuschläge in Währungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 178
    )
    BAUSTEIN_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Baustein-Abschläge in Währungseinheiten (4,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 184
    )
    BAUSTEIN_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Gueltige AKB.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Datum.class,
            anzahlBytes = 4,
            byteAdresse = 190
    )
    GUELTIGE_AKB,

    /**
     * Provision (2,2 Stellen).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 4,
            byteAdresse = 194
    )
    PROVISION,

    /**
     * Kennzeichen fuer abweichende Provision.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 198
    )
    KENNZEICHEN_FUER_ABWEICHENDE_PROVISION,

    /**
     * KFV-Schaeden aus Rueckstufung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 199
    )
    SCHAEDEN_AUS_RUECKSTUFUNG,

    /**
     * Typklasse fuer KFV.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 201
    )
    TYPKLASSE,

    /**
     * Produktkennung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 204
    )
    PRODUKTKENNUNG,

    /**
     * Referenznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 224
    )
    REFERENZNUMMER,

    /**
     * Lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = AlphaNumFeld.class,
            anzahlBytes = 4,
            byteAdresse = 231
    )
    LFD_NUMMER,

    /**
     * Tarif.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = AlphaNumFeld.class,
            anzahlBytes = 10,
            byteAdresse = 235
    )
    TARIF,
    
    /**
     * Bezug zur Teilsparte.
     * 051=KH, 052=KFV, 053=KFT, 054=KU, 059=KG
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 245
    )
    BEZUG_ZUR_TEILSPARTE1,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 36,
            type = AlphaNumFeld.class,
            anzahlBytes = 8,
            byteAdresse = 248
    )
    LEERSTELLEN1,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 37,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER1,

    /////   Teildatensatz 2   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 2,
            type = Feld1bis7.class
    )
    INTRO2,

    /**
     * Personennummer / lfd. Nummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 43
    )
    PERSONENNUMMER_LFD_NUMMER,
    
    /**
     * Bezug zur Teilsparte.
     * 051=KH, 052=KFV, 053=KFT, 054=KU, 059=KG
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 60
    )
    BEZUG_ZUR_TEILSPARTE2,

    /**
     * Versicherte Gefahren.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 63
    )
    VERSICHERTE_GEFAHREN,

    /**
     * Geltungsbereich.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 93
    )
    GELTUNGSBEREICH,

    /**
     * Geltungsbereicheinschraenkung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 123
    )
    GELTUNGSBEREICHEINSCHRAENKUNG,
    
    /**
     * Deckungs-/Versicherungssumme.
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 153
    )
    DECKUNGS_VERSICHERUNGSSUMME,

    /**
     * Selbstbeteiligung in % (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2, 
            nr = 14, 
            type = NumFeld.class, 
            anzahlBytes = 5, 
            nachkommaStellen = 2, 
            byteAdresse = 165)
    SELBSTBETEILIGUNG_IN_PROZENT,

    /**
     * Selbstbeteiligung in Waehrungseinheiten. (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2, 
            nr = 15, type = 
            NumFeld.class, 
            anzahlBytes = 12, 
            nachkommaStellen = 2, 
            byteAdresse = 170)
    SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_MIND,

    /**
     * Selbstbeteiligung in Waehrungseinheiten. (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2, 
            nr = 16, 
            type = NumFeld.class, 
            anzahlBytes = 12, 
            nachkommaStellen = 2, 
            byteAdresse = 182)
    SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_MAX,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 62,
            byteAdresse = 194
    )
    LEERSTELLEN2,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER2

}
