/*
 * Copyright (c) 2011-2013 by Oli B.
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

package gdv.xport.satz.feld.sparte30;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 30.
 * <p>
 * <b>HINWEIS</b>: Bitte nicht diese Enum-Klasse ueber
 * {@link gdv.xport.util.SatzFactory#registerEnum(Class, int, int)}
 * registrieren, sondern dafuer die Klasse {@link gdv.xport.satz.model.Satz220}
 * verwenden. Diese Klasse behandelt den Import fuer Teildatensatz 9 richtig,
 * der vom allgemeinen Schema etwas abweicht.
 * </p>
 * <p>
 * Intern verwendet {@link gdv.xport.satz.model.Satz220} diese Enum-Klasse
 * hier.
 * </p>
 *
 * @author oliver
 * @since 07.04.11
 */
public enum Feld220 {

	// /// Teildatensatz 1 /////////////////////////////////////////////////

	/** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
	@FelderInfo(teildatensatz = 1, type = Feld1bis7.class)
	INTRO1,

	/**
	 * Laufende Nummer der versicherten Person (VP) / Personengruppe bei
	 * Gruppenunfallversicherungen.
	 */
	@FeldInfo(teildatensatz = 1, nr = 8, type = NumFeld.class, anzahlBytes = 6, byteAdresse = 43)
	LFD_NUMMER_VP_PERSONENGRUPPE1,

	/**
	 * Satznummer.
	 */
	@FeldInfo(teildatensatz = 1, nr = 9, type = NumFeld.class, anzahlBytes = 1, byteAdresse = 49)
	SATZNUMMER1,

	/**
	 * Bezeichnung Personengruppe.
	 */
	@FeldInfo(teildatensatz = 1, nr = 10, type = AlphaNumFeld.class, anzahlBytes = 30,
	        byteAdresse = 50)
	BEZEICHNUNG_PERSONENGRUPPE,

	/**
	 * Anzahl der VP pro Personengruppe.
	 */
	@FeldInfo(teildatensatz = 1, nr = 11, type = NumFeld.class, anzahlBytes = 6, byteAdresse = 80)
	ANZAHL_VP_PRO_PERSONENGRUPPE,

	/**
	 * Name der VP.
	 */
	@FeldInfo(teildatensatz = 1, nr = 12, type = AlphaNumFeld.class, anzahlBytes = 30,
	        byteAdresse = 86)
	NAME_VP,

	/**
	 * Vorname der VP.
	 */
	@FeldInfo(teildatensatz = 1, nr = 13, type = AlphaNumFeld.class, anzahlBytes = 30,
	        byteAdresse = 116)
	VORNAME_VP,

	/**
	 * Geburtsdatum der VP.
	 */
	@FeldInfo(teildatensatz = 1, nr = 14, type = Datum.class, anzahlBytes = 8, byteAdresse = 146)
	GEBURTSDATUM_VP,

	/**
	 * Geschlecht der VP.
	 */
	@FeldInfo(teildatensatz = 1, nr = 15, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 154)
	GESCHLECHT_VP,

	/**
	 * Berufsschluessel.
	 */
	@FeldInfo(teildatensatz = 1, nr = 16, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 155)
	BERUFSSCHLUESSEL,

	/**
	 * Beruf-Text.
	 */
	@FeldInfo(teildatensatz = 1, nr = 17, type = AlphaNumFeld.class, anzahlBytes = 30,
	        byteAdresse = 158)
	BERUF_TEXT,

	/**
	 * Gefahrengruppe.
	 */
	@FeldInfo(teildatensatz = 1, nr = 18, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 188)
	GEFAHRENGRUPPE,

	/**
	 * Tarif.
	 */
	@FeldInfo(teildatensatz = 1, nr = 19, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 189)
	TARIF,

	/**
	 * Deckungsumfang. 1 = 24 Stunden-Deckung 2 = nur Berufsunfall 3 =
	 * Berufsunfall mit Wegeunfall 4 = nur Wegeunfall 5 = Freizeitunfall 6 =
	 * Ehrenamtliche Tätigkeit 9 = sonstige
	 */
	@FeldInfo(teildatensatz = 1, nr = 20, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 190)
	DECKUNGSUMFANG,

	/**
	 * Waehrungsschluessel.
	 */
	@FeldInfo(teildatensatz = 1, nr = 21, type = AlphaNumFeld.class, anzahlBytes = 3,
	        byteAdresse = 191)
	WAEHRUNGSSCHLUESSEL,

	/**
	 * Zuschlag in % (3,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 1, nr = 22, type = NumFeld.class, nachkommaStellen = 2,
	        anzahlBytes = 5, byteAdresse = 194)
	ZUSCHLAG_IN_PROZENT,

	/**
	 * Abschlag in % (3,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 1, nr = 23, type = NumFeld.class, nachkommaStellen = 2,
	        anzahlBytes = 5, byteAdresse = 199)
	ABSCHLAG_IN_PROZENT,

	/**
	 * Beitrag pro VP oder pro Personengruppe in Waehrungseinheiten (10,2
	 * Stellen).
	 */
	@FeldInfo(teildatensatz = 1, nr = 24, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 204)
	BEITRAG_PRO_VP_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Einschlussdatum VP / Personengruppe.
	 */
	@FeldInfo(teildatensatz = 1, nr = 25, type = Datum.class, anzahlBytes = 8, byteAdresse = 216)
	EINSCHLUSSDATUM_VP,

	/**
	 * Ausschlussdatum VP / Personengruppe.
	 */
	@FeldInfo(teildatensatz = 1, nr = 26, type = Datum.class, anzahlBytes = 8, byteAdresse = 224)
	AUSSCHLUSSDATUM_VP,

	/**
	 * Aenderungsdatum.
	 */
	@FeldInfo(teildatensatz = 1, nr = 27, type = Datum.class, anzahlBytes = 8, byteAdresse = 232)
	AENDERUNGSDATUM,

	/**
	 * Besondere Vereinbarung zum Fluggastrisiko.
	 */
	@FeldInfo(teildatensatz = 1, nr = 28, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 240)
	BESONDERE_VEREINBARUNG_ZUM_FLUGGASTRISIKO,

	/**
	 * passives Kriegsrisiko.
	 */
	@FeldInfo(teildatensatz = 1, nr = 29, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 241)
	PASSIVES_KRIEGSRISIKO,

	/**
	 * Kuendigungsklausel VP / Personengruppe gestrichen.
	 */
	@FeldInfo(teildatensatz = 1, nr = 30, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 242)
	KUENDIGUNGSKLAUSEL_VP,

	/**
	 * Eintrittsalter der VP in Jahren
	 */
	@FeldInfo(teildatensatz = 1, nr = 31, type = NumFeld.class, anzahlBytes = 2, byteAdresse = 243)
	EINTRITTSALTER_DER_VP,

	/**
	 * Eintrittsalter der VP in Jahren 01 = Kinder 02 = Erwachsene 03 = Senioren
	 * 99 = Sonstige
	 */
	@FeldInfo(teildatensatz = 1, nr = 32, type = AlphaNumFeld.class, anzahlBytes = 2,
	        byteAdresse = 245)
	ALTERSGRUPPE,

	/**
	 * Leerstellen.
	 */
	@FeldInfo(teildatensatz = 1, nr = 33, type = AlphaNumFeld.class, anzahlBytes = 3,
	        byteAdresse = 247)
	LEERSTELLEN,

	/**
	 * Satznummerwiederholung.
	 */
	@FeldInfo(teildatensatz = 1, nr = 34, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 250)
	SATZNUMMERWIEDERHOLUNG1,

	/**
	 * Leerstellen. Freie Stellen fuer weitere Belegung.
	 */
	@FeldInfo(teildatensatz = 1, nr = 35, type = AlphaNumFeld.class, anzahlBytes = 6,
	        byteAdresse = 251)
	LEERSTELLEN1,

	// /// Teildatensatz 2 /////////////////////////////////////////////////

	/** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
	@FelderInfo(teildatensatz = 2, type = Feld1bis7.class)
	INTRO2,

	/**
	 * Laufende Nummer der versicherten Person (VP) / Personengruppe bei
	 * Gruppenunfallversicherungen.
	 */
	@FeldInfo(teildatensatz = 2, nr = 8, type = NumFeld.class, anzahlBytes = 6, byteAdresse = 43)
	LFD_NUMMER_VP_PERSONENGRUPPE2,

	/**
	 * Satznummer.
	 */
	@FeldInfo(teildatensatz = 2, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 49)
	SATZNUMMER2,

	/**
	 * Tod.
	 */
	@FeldInfo(teildatensatz = 2, nr = 10, type = NumFeld.class, anzahlBytes = 9, byteAdresse = 50)
	TOD,

	/**
	 * Tod-Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 11, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 59)
	TOD_BEITRAGSSATZ,

	/**
	 * Invaliditaet.
	 */
	@FeldInfo(teildatensatz = 2, nr = 12, type = NumFeld.class, anzahlBytes = 9, byteAdresse = 66)
	INVALIDITAET,

	/**
	 * Kennung Gliedertaxe.
	 */
	@FeldInfo(teildatensatz = 2, nr = 13, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 75)
	KENNUNG_GLIEDERTAXE,

	/**
	 * Kennung progressive Invaliditaet.
	 */
	@FeldInfo(teildatensatz = 2, nr = 14, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 76)
	KENNUNG_PROGRESSIVE_INVALIDITAET,

	/**
	 * Invaliditaet Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 15, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 77)
	INVALIDITAET_BEITRAGSSATZ,

	/**
	 * Tagegeld 1 (6,1 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 16, type = NumFeld.class, nachkommaStellen = 1,
	        anzahlBytes = 7, byteAdresse = 84)
	TAGEGELD1,

	/**
	 * Beginn Tagegeld 1 ab Tag.
	 */
	@FeldInfo(teildatensatz = 2, nr = 17, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 91)
	BEGINN_TAGEGELD1_AB_TAG,

	/**
	 * Tagegeld 1 Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 18, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 94)
	TAGEGELD1_BEITRAGSSATZ,

	/**
	 * Tagegeld 2 (6,1 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 19, type = NumFeld.class, nachkommaStellen = 1,
	        anzahlBytes = 7, byteAdresse = 101)
	TAGEGELD2,

	/**
	 * Beginn Tagegeld 2 ab Tag.
	 */
	@FeldInfo(teildatensatz = 2, nr = 20, type = NumFeld.class, anzahlBytes = 3, byteAdresse = 108)
	BEGINN_TAGEGELD2_AB_TAG,

	/**
	 * Tagegeld 2 Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 21, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 111)
	TAGEGELD2_BEITRAGSSATZ,

	/**
	 * Krankenhaustagegeld (6,1 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 22, type = NumFeld.class, nachkommaStellen = 1,
	        anzahlBytes = 7, byteAdresse = 118)
	KRANKENHAUSTAGEGELD,

	/**
	 * Krankenhaustagegeld Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 23, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 125)
	KRANKENHAUSTAGEGELD_BEITRAGSSATZ,

	/**
	 * Genesungsgeld (6,1 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 24, type = NumFeld.class, nachkommaStellen = 1,
	        anzahlBytes = 7, byteAdresse = 132)
	GENESUNGSGELD,

	/**
	 * Genesungsgeld Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 25, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 139)
	GENESUNGSGELD_BEITRAGSSATZ,

	/**
	 * Uebergangsentschaedigung.
	 */
	@FeldInfo(teildatensatz = 2, nr = 26, type = NumFeld.class, anzahlBytes = 9, byteAdresse = 146)
	UEBERGANGSENTSCHAEDIGUNG,

	/**
	 * Kennung Uebergangsentschaedigung.
	 */
	@FeldInfo(teildatensatz = 2, nr = 27, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 155)
	KENNUNG_UEBERGANGSENTSCHAEDIGUNG,

	/**
	 * Uebergangsentschaedigung Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 28, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 156)
	UEBERGANGSENTSCHAEDIGUNG_BEITRAGSSATZ,

	/**
	 * Heilkosten.
	 */
	@FeldInfo(teildatensatz = 2, nr = 29, type = NumFeld.class, anzahlBytes = 9, byteAdresse = 163)
	HEILKOSTEN,

	/**
	 * Art der Heilkosten.
	 */
	@FeldInfo(teildatensatz = 2, nr = 30, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 172)
	ART_DER_HEILKOSTEN,

	/**
	 * Heilkosten Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 31, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 173)
	HEILKOSTEN_BEITRAGSSATZ,

	/**
	 * Feste Rente.
	 */
	@FeldInfo(teildatensatz = 2, nr = 32, type = NumFeld.class, anzahlBytes = 9, byteAdresse = 180)
	FESTE_RENTE,

	/**
	 * Feste Rente Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 33, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 189)
	FESTE_RENTE_BEITRAGSSATZ,

	/**
	 * Kosmetische Operationen.
	 */
	@FeldInfo(teildatensatz = 2, nr = 34, type = NumFeld.class, anzahlBytes = 9, byteAdresse = 196)
	KOSMETISCHE_OPERATIONEN,

	/**
	 * Kosmetische Operationen Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 35, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 205)
	KOSMETISCHE_OPERATIONEN_BEITRAGSSATZ,

	/**
	 * Kurkosten.
	 */
	@FeldInfo(teildatensatz = 2, nr = 36, type = NumFeld.class, anzahlBytes = 9, byteAdresse = 212)
	KURKOSTEN,

	/**
	 * Kurkosten Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 37, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 221)
	KURKOSTEN_BEITRAGSSATZ,

	/**
	 * Bergungskosten.
	 */
	@FeldInfo(teildatensatz = 2, nr = 38, type = NumFeld.class, anzahlBytes = 9, byteAdresse = 228)
	BERGUNGSKOSTEN,

	/**
	 * Bergungskosten Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 39, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 237)
	BERGUNGSKOSTEN_BEITRAGSSATZ,

	/**
	 * Ueberfuehrungskosten.
	 */
	@FeldInfo(teildatensatz = 2, nr = 40, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 244)
	UEBERFUEHRUNGSKOSTEN,

	/**
	 * Rueckfuehrungskosten.
	 */
	@FeldInfo(teildatensatz = 2, nr = 41, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 245)
	RUECKFUEHRUNGSKOSTEN,

	/**
	 * Prozentsatz progressive Invaliditaet (4,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 2, nr = 42, type = NumFeld.class, nachkommaStellen = 2,
	        anzahlBytes = 6, byteAdresse = 246)
	PROZENTSATZ_PROGRESSIVE_INVALIDITAET,

	/**
	 * Mehrleistungsklausel.
	 */
	@FeldInfo(teildatensatz = 2, nr = 43, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 252)
	MEHRLEISTUNGSKLAUSEL,

	/**
	 * Leerstellen.
	 */
	@FeldInfo(teildatensatz = 2, nr = 44, type = AlphaNumFeld.class, anzahlBytes = 3,
	        byteAdresse = 253)
	LEERSTELLEN2,

	/**
	 * zusaetzliche Satzkennung.
	 */
	@FeldInfo(teildatensatz = 2, nr = 45, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 256)
	ZUSAETZLICHE_SATZKENNUNG,

	// /// Teildatensatz 3 /////////////////////////////////////////////////

	/** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
	@FelderInfo(teildatensatz = 3, type = Feld1bis7.class)
	INTRO3,

	/**
	 * Satznummer.
	 */
	@FeldInfo(teildatensatz = 3, nr = 8, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 43)
	SATZNUMMER3,

	/**
	 * VP-Personnenummer des Versicherers.
	 */
	@FeldInfo(teildatensatz = 3, nr = 9, type = AlphaNumFeld.class, anzahlBytes = 17,
	        byteAdresse = 44)
	VP_PERSONENNUMMER_VERSICHERER,

	/**
	 * VP-Personnenummer des Vermittlers.
	 */
	@FeldInfo(teildatensatz = 3, nr = 10, type = AlphaNumFeld.class, anzahlBytes = 17,
	        byteAdresse = 61)
	VP_PERSONENNUMMER_VERMITTLER,

	/**
	 * Serviceleistungen.
	 */
	@FeldInfo(teildatensatz = 3, nr = 11, type = NumFeld.class, anzahlBytes = 9, byteAdresse = 78)
	SERVICELEISTUNGEN,

	/**
	 * Serviceleistungen Beitragssatz (2,5 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 12, type = NumFeld.class, nachkommaStellen = 5,
	        anzahlBytes = 7, byteAdresse = 87)
	SERVICELEISTUNGEN_BEITRAGSSATZ,

	/**
	 * Beitrag Serviceleistungen in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 13, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 94)
	BEITRAG_SERVICELEISTUNGEN_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Tod in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 14, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 106)
	BEITRAG_TOD_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Invaliditaet in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 15, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 118)
	BEITRAG_INVALIDITAET_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Tagegeld 1 in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 16, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 130)
	BEITRAG_TAGEGELD1_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Tagegeld 2 in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 17, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 142)
	BEITRAG_TAGEGELD2_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Krankenhaustagegeld in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 18, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 154)
	BEITRAG_KRANKENHAUSTAGEGELD_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Genesungsgeld in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 19, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 166)
	BEITRAG_GENESUNGSGELD_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Uebergangsentschaedigung in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 20, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 178)
	BEITRAG_UEBERGANGSENTSCHAEDIGUNG_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Heilkosten in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 21, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 190)
	BEITRAG_HEILKOSTEN_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Feste Rente in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 22, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 202)
	BEITRAG_FESTE_RENTE_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Kosmetische Operation in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 23, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 214)
	BEITRAG_KOSMETISCHE_OPERATION_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Kurkosten in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 24, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 226)
	BEITRAG_KURKOSTEN_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Beitrag Bergungskosten in Waehrungseinheiten (10,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 3, nr = 25, type = Betrag.class, nachkommaStellen = 2,
	        anzahlBytes = 12, byteAdresse = 238)
	BEITRAG_BERGUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Satznummerwiederholung.
	 */
	@FeldInfo(teildatensatz = 3, nr = 26, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 250)
	SATZNUMMERWIEDERHOLUNG2,

	/**
	 * Laufende Nummer der versicherten Person (VP) / Personengruppe bei
	 * Gruppenunfallversicherungen.
	 */
	@FeldInfo(teildatensatz = 3, nr = 27, type = NumFeld.class, anzahlBytes = 6, byteAdresse = 251)
	LFD_NUMMER_VP_PERSONENGRUPPE3,

	// /// Teildatensatz 4 /////////////////////////////////////////////////

	/** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */

	@FelderInfo(teildatensatz = 4, type = Feld1bis7.class)
	INTRO4,

	/**
	 * Laufende Nummer.
	 */
	@FeldInfo(teildatensatz = 4, nr = 8, type = NumFeld.class, anzahlBytes = 6, byteAdresse = 43)
	LFD_NUMMER_VP_PERSONENGRUPPE4,

	/**
	 * Satznummer.
	 */
	@FeldInfo(teildatensatz = 4, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 49)
	SATZNUMMER4,

	/**
	 * Beitragszahlung bis (Tag/Monat/Jahr (TTMMJJJJ))
	 */
	@FeldInfo(teildatensatz = 4, nr = 10, type = Datum.class, anzahlBytes = 8, byteAdresse = 50)
	BEITRAGSZAHLUNG_BIS,

	/**
	 * Rückgewährdatum (Tag/Monat/Jahr (TTMMJJJJ))
	 */
	@FeldInfo(teildatensatz = 4, nr = 11, type = Datum.class, anzahlBytes = 8, byteAdresse = 58)
	RUECKGEWAEHRDATUM,

	/**
	 * Rückgewährsumme zum Ablauf in Währungseinheiten Vertraglich
	 * vereinbarte Rückgewährsumme zum Ablauf des Vertrages (aufgelaufene
	 * Beiträge) (12,2 Stellen)
	 */
	@FeldInfo(teildatensatz = 4, nr = 12, type = Betrag.class, anzahlBytes = 14, byteAdresse = 66)
	RUECKGEWAEHRSUMME_ZUM_ABLAUF_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Ablaufleistung incl. Überschussanteile in Währungseinheiten nach
	 * Überschußanrechnung erreichte Ablaufleistung (12,2 Stellen)
	 */
	@FeldInfo(teildatensatz = 4, nr = 13, type = Betrag.class, anzahlBytes = 14, byteAdresse = 80)
	ABLAUFLEISTUNG_INKL_UEBERSCHUSSANTEILE_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Kapitalertragssteuer bei Ablauf (12,2 Stellen)
	 */
	@FeldInfo(teildatensatz = 4, nr = 14, type = Betrag.class, anzahlBytes = 14, byteAdresse = 94)
	KAPITALERTRAGSSTEUER_BEI_ABLAUF,

	/**
	 * Solidaritätszuschlag bei Ablauf (12,2 Stellen)
	 */
	@FeldInfo(teildatensatz = 4, nr = 15, type = Betrag.class, anzahlBytes = 14, byteAdresse = 108)
	SOLIDARITAETSZUSCHLAG_BEI_ABLAUF,

	/**
	 * Rückkaufswert zum Berechnungsstichtag in Währungseinheiten (12,2
	 * Stellen)
	 */
	@FeldInfo(teildatensatz = 4, nr = 16, type = Betrag.class, anzahlBytes = 14, byteAdresse = 122)
	RUECKKAUFSWERT_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Berechnungsstichtag zum Rückkaufswert (Tag/Monat/Jahr (TTMMJJJJ))
	 */
	@FeldInfo(teildatensatz = 4, nr = 17, type = Datum.class, anzahlBytes = 8, byteAdresse = 136)
	BERECHNUNGSSTICHTAG_ZUM_RUECKKAUFSWERT,

	/**
	 * Kapitalertragssteuer bei Rückkauf zum Berechnungsstichtag (12,2 Stellen)
	 */
	@FeldInfo(teildatensatz = 4, nr = 18, type = Betrag.class, anzahlBytes = 14, byteAdresse = 144)
	KAPITALERTRAGSSTEUER_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG,

	/**
	 * Solidaritätszuschlag bei Rückkauf zum Berechnungsstichtag (12,2
	 * Stellen)
	 */
	@FeldInfo(teildatensatz = 4, nr = 19, type = Betrag.class, anzahlBytes = 14, byteAdresse = 158)
	SOLIDARITAETSZUSCHLAG_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG,

	/**
	 * Überschussanteile zum Berechnungsstichtag in Währungseinheiten (12,2
	 * Stellen)
	 */
	@FeldInfo(teildatensatz = 4, nr = 20, type = Betrag.class, anzahlBytes = 14, byteAdresse = 172)
	UEBERSCHUSSANTEILE_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN,

	/**
	 * Überschussanteile zum Berechnungsstichtag in Währungseinheiten (12,2
	 * Stellen)
	 */
	@FeldInfo(teildatensatz = 4, nr = 21, type = AlphaNumFeld.class, anzahlBytes = 64,
	        byteAdresse = 186)
	LEERSTELLEN4,

	/**
	 * Satznummerwiederholung
	 */
	@FeldInfo(teildatensatz = 4, nr = 22, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 250)
	SATZNUMMERWIEDERHOLUNG4,

	// /// Teildatensatz 9 /////////////////////////////////////////////////

	/** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
	@FelderInfo(teildatensatz = 9, type = Feld1bis7.class)
	INTRO9,

	/**
	 * Lfd. Nummer der versicherten Person (VP). lfd. Nr., die im VU geführt
	 * wird
	 */
	@FeldInfo(teildatensatz = 9, nr = 8, type = AlphaNumFeld.class, anzahlBytes = 17,
	        byteAdresse = 43)
	LFD_NUMMER_VP_PERSONENGRUPPE9,

	/**
	 * Satznummer.
	 */
	@FeldInfo(teildatensatz = 9, nr = 9, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 60)
	SATZNUMMER9,

	/**
	 * Lfd. Nummer der Satzart.
	 */
	@FeldInfo(teildatensatz = 9, nr = 10, type = AlphaNumFeld.class, anzahlBytes = 2,
	        byteAdresse = 61)
	LFD_NUMMER_SATZART,

	/**
	 * Bezugsberechtigt im Leistungsfall.
	 */
	@FeldInfo(teildatensatz = 9, nr = 11, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 63)
	BEZUGSBERECHTIGT_IM_LEISTUNGSFALL,

	/**
	 * Sonstigter Bezugsberechtigter im Leistungsfall.
	 */
	@FeldInfo(teildatensatz = 9, nr = 12, type = AlphaNumFeld.class, anzahlBytes = 30,
	        byteAdresse = 64)
	SONSTIGER_BEZUGSBERECHTIGTER_IM_LEISTUNGSFALL,

	/**
	 * Bezugsrechtanteil im Leistungsfall (3,2 Stellen).
	 */
	@FeldInfo(teildatensatz = 9, nr = 13, type = NumFeld.class, nachkommaStellen = 2,
	        anzahlBytes = 5, byteAdresse = 94)
	BEZUGSRECHTANTEIL_IM_LEISTUNGSFALL,

	/**
	 * Unwiderrufliches Bezugsrecht im Leistungsfall.
	 */
	@FeldInfo(teildatensatz = 9, nr = 14, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 99)
	UNWIDERRUFLICHES_BEZUGSRECHT_IM_LEISTUNGSFALL,

	/**
	 * Referenznummer.
	 */
	@FeldInfo(teildatensatz = 9, nr = 15, type = AlphaNumFeld.class, anzahlBytes = 7,
	        byteAdresse = 100)
	REFERENZNUMMER,

	/**
	 * Geburtsdatum der bezugsberechtigten Person.
	 */
	@FeldInfo(teildatensatz = 9, nr = 16, type = Datum.class, anzahlBytes = 8, byteAdresse = 107)
	GEBURTSDATUM_DER_BEZUGSBERECHTIGTEN_PERSON,

	/**
	 * Leerstellen.
	 */
	@FeldInfo(teildatensatz = 9, nr = 17, type = AlphaNumFeld.class, anzahlBytes = 135,
	        byteAdresse = 115)
	LEERSTELLEN3,

	/**
	 * Satznummerwiederholung.
	 */
	@FeldInfo(teildatensatz = 9, nr = 18, type = Zeichen.class, anzahlBytes = 1, byteAdresse = 250)
	SATZNUMMERWIEDERHOLUNG9,

	/**
	 * Lfd. Nummer der versicherten Person (VP) / Personengruppe.
	 */
	@FeldInfo(teildatensatz = 9, nr = 19, type = NumFeld.class, anzahlBytes = 6, byteAdresse = 251)
	LFD_NUMMER_VP;

}
