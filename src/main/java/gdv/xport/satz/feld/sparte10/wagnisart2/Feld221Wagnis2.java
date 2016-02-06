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

package gdv.xport.satz.feld.sparte10.wagnisart2;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 221, Sparte 10.
 * "Leben - Rentenversicherung = Wagnisart 2 " (Satzart 0221)
 *
 * @author ralfklemmer
 * @since 19.01.2013
 */
public enum Feld221Wagnis2 {

	// /// Teildatensatz 1 /////////////////////////////////////////////////

	/** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 1,
            type = Feld1bis7.class)
	INTRO1,

	/**
	 * Laufende Nummer der versicherten Person (VP).
	 * lfd. Nr., die im VU geführt wird
	 */
	@FeldInfo(teildatensatz = 1, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17,
	        byteAdresse = 43)
	LFD_NUMMER_VP_PERSONENGRUPPE,

	/**
	 * Wagnisart.
	 * 2 = Rentenversicherung
	 */
	@FeldInfo(teildatensatz = 1, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
	WAGNISART,

	/**
	 * Lfd Nummer zur Wagnisart.
	 * siehe Anlage 15
	 */
	@FeldInfo(teildatensatz = 1, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
	LFD_NUMMER_ZUR_WAGNISART,

	/**
	 * Rückkaufswert in Währungseinheiten.
	 * incl. aller Dynamiken
	 * (12,2 Stellen)
	 */
	@FeldInfo(teildatensatz = 1, nr = 11, type = Betrag.class, anzahlBytes = 14, byteAdresse = 62)
	RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN_MIT_NACHKOMMA,

	/**
	 * Guthaben Divid. Ansammlungen in Währungseinheiten.
	 * (12,2 Stellen)
	 */
	@FeldInfo(teildatensatz = 1, nr = 12, type = Betrag.class, anzahlBytes = 14, byteAdresse = 76)
	GUTHABEN_DIVID_ANSAMMLUNG_IN_WAEHRUNGSEINHEIT,

	/**
	 * Kapitalzahlungs-Summe in Währungseinheiten.
	 * Vertraglich vereinbarte Kapitalzahlungssumme zum Ablauf (inkl.
	 * Abrufphase)
	 * (12,2 Stellen)
	 */
	@FeldInfo(teildatensatz = 1, nr = 13, type = Betrag.class, anzahlBytes = 14, byteAdresse = 90)
	KAPITALZAHLUNGS_SUMME_IN_WAEHRUNGSEINHEIT,

	/**
	 * Leerstellen.
	 */
	@FeldInfo(teildatensatz = 1, nr = 14, type = AlphaNumFeld.class, anzahlBytes = 152,
	        byteAdresse = 104)
	LEERSTELLEN,

	// /// Teildatensatz 2 /////////////////////////////////////////////////

	/** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 10,
            teildatensatz = 2,
            type = Feld1bis7.class)
	INTRO2,

	/**
	 * Laufende Nummer der versicherten Person (VP).
	 * lfd. Nr., die im VU geführt wird
	 */
	@FeldInfo(teildatensatz = 2, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17,
	        byteAdresse = 43)
	LFD_NUMMER_VP_PERSONENGRUPPE2,

	/**
	 * Wagnisart.
	 * 2 = Rentenversicherung
	 */
	@FeldInfo(teildatensatz = 2, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
	WAGNISART2,

	/**
	 * Lfd Nummer zur Wagnisart.
	 * siehe Anlage 15
	 */
	@FeldInfo(teildatensatz = 2, nr = 10, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 61)
	LFD_NUMMER_ZUR_WAGNISART2,

	/**
	 * Absoluter Dynamikerhöhungsbetrag in Währungseinheiten.
	 * (10,2 Stellen)
	 */
	@FeldInfo(teildatensatz = 2, nr = 11, type = Betrag.class, anzahlBytes = 12, byteAdresse = 62)
	ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Leerstellen.
	 */
	@FeldInfo(teildatensatz = 2, nr = 12, type = AlphaNumFeld.class, anzahlBytes = 182,
	        byteAdresse = 74)
	LEERSTELLEN2
}
