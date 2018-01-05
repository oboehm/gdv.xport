/*
 * Copyright (c) 2011, 2012 by Oli B. Licensed under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express orimplied. See the License for the specific language
 * governing permissions and limitations under the License. (c)reated 23.03.2011
 * by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.feld.sparte10.wagnisart13;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 221, Sparte 10
 * (Leben - Kapitallebens-/Risikovers. = Wagnisart 1 und 3, Leben).
 *
 * @author ralfklemmer
 * @since 12.11.2012
 */
public enum Feld221Wagnis13 {

	// /// Teildatensatz 1 /////////////////////////////////////////////////

	/** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 1,
            type = Feld1bis7.class)
	INTRO1,

	/**
	 * Lfd. Nummer der versicherten Person (VP).
	 * lfd. Nr., die im VU geführt wird
	 */
	@FeldInfo(teildatensatz = 1, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17,
	        byteAdresse = 43)
	LFD_NUMMER_VP_PERSONENGRUPPE,

	/**
	 * Wagnisart.
	 * 1 = Kapitallebensversicherung, 3 = Risikoversicherung
	 */
	@FeldInfo(teildatensatz = 1, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
	WAGNISART,

	/**
	 * Lfd Nummer zur Wagnisart.
	 */
	@FeldInfo(teildatensatz = 1, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
	LFD_NUMMER_ZUR_WAGNISART,

	/**
	 * Erlebensfall VS in Währungseinheiten.
	 */
	@FeldInfo(teildatensatz = 1, nr = 11, type = Betrag.class, anzahlBytes = 14, byteAdresse = 62)
	ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT,

	/**
	 * Todesfall VS in Währungseinheiten.
	 */
	@FeldInfo(teildatensatz = 1, nr = 12, type = Betrag.class, anzahlBytes = 14, byteAdresse = 76)
	TODESFALL_VS_IN_WAEHRUNGSEINHEIT,

	/**
	 * Erlebensfall VS II in Währungseinheiten.
	 */
	@FeldInfo(teildatensatz = 1, nr = 13, type = Betrag.class, anzahlBytes = 14, byteAdresse = 90)
	ERLEBENSFALL_VS2_IN_WAEHRUNGSEINHEIT,

	/**
	 * Beitragsfreie Erlebensfall VS in Währungseinheiten.
	 */
	@FeldInfo(teildatensatz = 1, nr = 14, type = Betrag.class, anzahlBytes = 14, byteAdresse = 104)
    BEITRAGSFREIE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitragsfreie Todesfall VS in Währungseinheiten.
	 */
	@FeldInfo(teildatensatz = 1, nr = 15, type = Betrag.class, anzahlBytes = 14, byteAdresse = 118)
    BEITRAGSFREIE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Rückkaufswert in Währungseinheiten.
	 */
	@FeldInfo(teildatensatz = 1, nr = 16, type = Betrag.class, anzahlBytes = 14, byteAdresse = 132)
	RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN_MIT_NACHKOMMA,

	/**
	 * Guthaben Divid. Ansammlungen in Währungseinheiten.
	 */
	@FeldInfo(teildatensatz = 1, nr = 17, type = Betrag.class, anzahlBytes = 14, byteAdresse = 146)
	GUTHABEN_DIVID_ANSAMMLUNG_IN_WAEHRUNGSEINHEIT,

	/**
	 * Leerstellen.
	 */
	@FeldInfo(teildatensatz = 1, nr = 18, type = AlphaNumFeld.class, anzahlBytes = 96,
	        byteAdresse = 160)
	LEERSTELLEN,

	// /// Teildatensatz 2 /////////////////////////////////////////////////

	/** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 2,
            type = Feld1bis7.class)
	INTRO2,

	/**
	 * Lfd. Nummer der versicherten Person (VP).
	 * lfd. Nr., die im VU geführt wird
	 */
	@FeldInfo(teildatensatz = 2, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17,
	        byteAdresse = 43)
	LFD_NUMMER_VP_PERSONENGRUPPE2,

	/**
	 * Wagnisart.
	 * 1 = Kapitallebensversicherung, 3 = Risikoversicherung
	 */
	@FeldInfo(teildatensatz = 2, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
	WAGNISART2,

	/**
	 * Lfd Nummer zur Wagnisart.
	 */
	@FeldInfo(teildatensatz = 2, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
	LFD_NUMMER_ZUR_WAGNISART2,

	/**
	 * Absoluter Dynamikerhöhungsbetrag in Währungseinheiten.
	 */
	@FeldInfo(teildatensatz = 2, nr = 11, type = Betrag.class, anzahlBytes = 12, byteAdresse = 62)
	ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Leerstellen.
	 */
	@FeldInfo(teildatensatz = 2, nr = 13, type = AlphaNumFeld.class, anzahlBytes = 182,
	        byteAdresse = 74)
	LEERSTELLEN2

}
