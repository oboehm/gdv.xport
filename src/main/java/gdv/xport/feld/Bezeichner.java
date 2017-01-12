/*
 * Copyright (c) 2009 - 2013 by Oli B.
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
 * (c)reated 15.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gdv.xport.feld.internal.UmlautMapper;

/**
 * Diese Klasse enthaelt die Namen der einzelnen Felder. Die Konstanten sind
 * (mehr oder weniger) alphabetisch geordnet.
 * <p>
 * Daneben dient diese Klasse auch als String-Erweiterung, um verschiedene
 * Bezeichner besser vergleichen zu koennen. So enthalten manche
 * Bezeichner-Konstanten Leerzeichen, die bei einem Vergleich nicht
 * beruecksichtigt werden sollen.
 * </p>
 * <p>
 * Die String-Konstanten werden ab 1.2 durch entsprechnende Bezeichner-
 * Konstanten ersetzt werden. Fuer diese Uebergangszeit wurden diese Konstanten
 * durch entsprechende Konstanten mit dem Prefix "NAME_" und die alten
 * String-Konstanten als "deprecated" gekennzeichnet.
 * </p>
 * <p>
 * Bitte Konstanten alphabetisch ordnen bzw. eintragen! (oboehm, 26-Jan-2013)
 * </p>
 * <p>
 * TODO: Ersetzung String-Konstanten durch Bezeichner-Konstenten (ab 1.2)
 * </p>
 *
 * @author oliver
 * @since 15.10.2009
 */
public final class Bezeichner {

    private static final Logger LOG = LogManager.getLogger(Bezeichner.class);
    private static final Map<String, String> MAPPING = new HashMap<>();

    // Konstanten A-K... gibt es nur noch als Bezeichner-Konstanten
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MEHRLEISTUNGSKLAUSEL = "Mehrleistungsklausel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MEHRWERTGRUND = "Mehrwertgrund";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MEHRWERT_IN_WAEHRUNGSEINHEITEN = "Mehrwert";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MEHRZWECKFELD = "Mehrzweckfeld";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MITVERSICHERTE_PERSON_FAMILIENNAME = "Mitversicherte Person Familienname";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MITVERSICHERTE_PERSON_VORNAME = "Mitversicherte Person Vorname";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MODELLNAME = "Modellname";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTE_ERHOEHUNG = "naechste Erhoehung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAME1 = "Name1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAME2 = "Name2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAME3 = "Name3";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAME_KREDITINSTITUT1 = "Name des Kreditinstituts 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAME_KREDITINSTITUT2 = "Name des Kreditinstituts 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAME_MITVERSICHERTES_KIND = "Name des mitversicherten Kinds";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAME_VP = "Name der VP";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NEUPREIS_IN_WAEHRUNGSEINHEITEN = "Neupreis in Waehrungseinheiten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NUTZUNGSART = "Nutzungsart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ORDNUNGSBEGRIFF = "Ordnungsbegriff";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ORT = "Ort";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PASSIVES_KRIEGSRISIKO = "passives Kriegsrisiko";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PERSONENNUMMER_LFD_NUMMER = "Personennummer / lfd. Nummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PERSONEN_KUNDENNUMMER_DES_VERSICHERERS = "Personen-/Kundennummer des Versicherers";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String POSTALISCHES_KENNZEICHEN = "postalisches Kennzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String POSTFACH = "postfach";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String POSTLEITZAHL = "Postleitzahl";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PRODUKTBESCHREIBUNG = "Produktbeschreibung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PRODUKTFORM = "Produktform";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PRODUKTFORM_GUELTIG_AB = "Produktform gueltig ab";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PRODUKTKENNUNG = "Produktkennung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PRODUKTNAME = "Produktname";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PRODUKTSPEZIFISCHE_ANTRAGSDATEN = "Produktspezifische Antragsdaten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PRODUKTSPEZIFISCHE_STAMMDATEN = "Produktspezifische Stammdaten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PROVISION = "Provision";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PROZENTSATZ_PROGRESSIVE_INVALIDITAET = "Prozentsatz progressive Invaliditaet";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RATENZAHLUNGSZUSCHLAG_IN_PROZENT = "Ratenzahlungszuschlag in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RECHTSFORM = "Rechtsform";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String REFERENZNUMMER = "Referenznummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String REFERENZ_VERSICHERUNGSSCHEINNUMMER = "Referenz-Versicherungsscheinnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String REFERENZ_VERSICHERUNGSSCHEINNUMMER_1 = "1. Referenz-Versicherungsscheinnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String REFERENZ_VERSICHERUNGSSCHEINNUMMER_2 = "2. Referenz-Versicherungsscheinnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String REFERENZ_VERSICHERUNGSSCHEINNUMMER_3 = "3. Referenz-Versicherungsscheinnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String REGISTRIERUNGSNUMMER_VERMITTLER = "Registrierungsnummer Vermittler";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RESTLAUFZEIT_VERTRAG = "Restlaufzeit des Vertrages";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOGRUPPE_RISIKOART = "Risikogruppe / Risikoart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOGRUPPE_RISIKOART1 = "Risikogruppe / Risikoart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOGRUPPE_RISIKOART2 = "Risikogruppe / Risikoart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOTEXT = "Risikotext";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOVERLAUF = "Risikoverlauf";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISKIOEINHEIT1 = "Risikoeinheit-1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISKIOEINHEIT2 = "Risikoeinheit-2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ROHBAU_EINMALBETRAG = "Rohbau-Einmalbetrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RUECKFUEHRUNGSKOSTEN = "Rueckfuehrungskosten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SAISONKENNZEICHEN = "Saisonkennzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZART = "Satzart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMER = "Satznummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMER1 = "Satznummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMER2 = "Satznummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMER3 = "Satznummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMER4 = "Satznummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMER5 = "Satznummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMER9 = "Satznummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMERWIEDERHOLUNG = "Satznummerwiederholung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMERWIEDERHOLUNG1 = "Satznummerwiederholung1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMERWIEDERHOLUNG2 = "Satznummerwiederholung2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMERWIEDERHOLUNG3 = "Satznummerwiederholung3";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMERWIEDERHOLUNG4 = "Satznummerwiederholung4";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SATZNUMMERWIEDERHOLUNG9 = "Satznummerwiederholung9";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SCHADENBEARBEITUNGSKOSTEN = "Schadenbearbeitungskosten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SCHLUESSEL_SICHERUNGSEINRICHTUNG = "Schluessel Sicherungseinrichtung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SCHUTZBRIEF_VERKEHRSSERVICE = "Schutzbrief /Verkehrsservice";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SELBSTBETEILIGUNG = "Selbstbeteiligung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SELBSTBETEILIGUNG_IN_PROZENT = "Selbstbeteiligung in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = "Selbstbeteiligung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SELBSTBETEILIGUNG_IN_WE_MAX = "Selbstbeteiligung in WE (max.)";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SELBSTBETEILIGUNG_IN_WE_MIND = "Selbstbeteiligung in WE (mind.)";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SERVICELEISTUNGEN = "Serviceleistungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SERVICELEISTUNGEN_BEITRAGSSATZ = "Serviceleistungen Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SICHERUNGSEINRICHTUNG = "Sicherungseinrichtung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SICHERUNGSGLAEUBIGER = "Sicherungsglaeubiger";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SICHERUNGSSCHEIN = "Sicherungsschein";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SONDERBEDINGUNGEN = "Sonderbedingungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SONDERBEDINGUNGEN_KLAUSELN = "Sonderbedingungen / Klauseln";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SONDERVEREINBARUNGEN = "Sondervereinbarungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SONSTIGER_BEZUGSBERECHTIGTER_IM_LEISTUNGSFALL = "Sonstigter Bezugsberechtigter im Leistungsfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SPARTE = "Sparte";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SPEZIFIKATION_REFERENZ_VERSICHERUNGSSCHEINNUMMER = "Spezifikation der Referenz-Versicherungsscheinnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STAATSANGEHOERIGKEIT = "Staatsangehoerigkeit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STAERKE = "Staerke";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STAERKEEINHEIT = "Staerkeeinheit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STEUERNR_JURISTISCHE_PERSON = "Steuernummer bei juristischen Personen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STOCKWERKE = "Stockwerke";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STRASSE = "Strasse";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STURMZONE = "Sturmzone";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TAGEGELD1 = "Tagegeld 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TAGEGELD1_BEITRAGSSATZ = "Tagegeld 1 Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TAGEGELD2 = "Tagegeld 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TAGEGELD2_BEITRAGSSATZ = "Tagegeld 2 Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TARIF = "Tarif";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGTEIL_IN_WAEHRUNGSEINHEITEN = "Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugteil";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGVOLL_IN_WAEHRUNGSEINHEITEN = "Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugvoll";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_HAFTPFLICHT_IN_WAEHRUNGSEINHEITEN = "Tarifbeitrag 100 % fuer Kraftfahrt-Haftpflicht";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TARIFBEZEICHNUNG = "Tarifbezeichnung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TARIFIERUNGSMERKMAL_LAUFZEIT = "Tarifierungsmerkmal Laufzeit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TARIFJAHR = "Tarifjahr";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TITEL = "Titel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TOD = "Tod";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TOD_BEITRAGSSATZ = "Tod-Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TRAEGERUNTERNEHMEN_NAME = "Traegerunternehmen Name";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TRAEGERUNTERNEHMEN_SCHLUESSEL = "Traegerunternehmen Schluessel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TYKLASSE_KH = "Tyklasse KH";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TYPKLASSE_FUER_KFT = "Typklasse fuer KFT";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TYPKLASSE_FUER_KFV = "Typklasse fuer KFV";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TYPSCHLUESSEL_NR = "Typschluessel-Nr.";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TYP_BANKVERBINDUNG1 = "Typ der Bankverbindung 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TYP_BANKVERBINDUNG2 = "Typ der Bankverbindung 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UEBERFUEHRUNGSKOSTEN = "Ueberfuehrungskosten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UEBERGANGSENTSCHAEDIGUNG = "Uebergangsentschaedigung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UEBERGANGSENTSCHAEDIGUNG_BEITRAGSSATZ = "Uebergangsentschaedigung Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UMBAUTER_RAUM = "umbauter Raum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UMSATZSTEUER_ID = "Umsatzsteuer-Identifikationsnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNBEKANNT = "unbekannt";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNTERSTUETZUNGSKASSE_NAME = "Unterstuetzungskasse Name";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNTERSTUETZUNGSKASSE_SCHLUESSEL = "Unterstuetzungskasse Schluessel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNVERFALLBARKEIT = "Unverfallbarkeit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNWIDERRUFLICHES_BEZUGSRECHT_IM_LEISTUNGSFALL = "Unwiderrufliches Bezugsrecht im Leistungsfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERKUERZTE_BEITRAGSZAHLUNGSDAUER = "verkuerzte Beitragszahlungsdauer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERMITTLER = "Geschaeftsstelle/Vermittler";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSICHERTES_OBJEKT = "Versichertes Objekt";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSICHERTE_GEFAHREN = "Versicherte Gefahren";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSICHERUNGSLEISTUNGEN = "Versicherungsleistungen";
    /** @deprecated bitte {@link Bezeichner#VS_NR} verwenden */
    @Deprecated
    public static final String VERSICHERUNGSSCHEINNUMMER = "Versicherungsschein-Nummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSICHERUNGSSCHEINNUMMER_VM = "Versicherungsscheinnummer VM";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_9999 = "Version Satzart 9999 Nachsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0001 = "Version Satzart 0001";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0100 = "Version Satzart 0100";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0200 = "Version Satzart 0200";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_050 = "Version Satzart 0210 050";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_051 = "Version Satzart 0220 051";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_052 = "Version Satzart 0220 052";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_053 = "Version Satzart 0220 053";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_054 = "Version Satzart 0220 054";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_059 = "Version Satzart 0220 059";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_040 = "Version Satzart 0210 040";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_040 = "Version Satzart 0220 040";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_030 = "Version Satzart 0210 030";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_030 = "Version Satzart 0220 030";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_010 = "Version Satzart 0210 010";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_010 = "Version Satzart 0220 010";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_130 = "Version Satzart 0210 130";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_130 = "Version Satzart 0220 130";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_110 = "Version Satzart 0210 110";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_110 = "Version Satzart 0220 110";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_140 = "Version Satzart 0210 140";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_140 = "Version Satzart 0220 140";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_020 = "Version Satzart 0210 020";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_020 = "Version Satzart 0220 020";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_070 = "Version Satzart 0210 070";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_070 = "Version Satzart 0220 070";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_FEUER = "Version Satzart 0210 Feuer-Industrie/Gewerbliche Sachversicherung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_FEUER = "Version Satzart 0220 Feuer-Industrie/Gewerbliche Sachversicherung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_510 = "Version Satzart 0210 510";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_510 = "Version Satzart 0220 510";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_TRANSPORT = "Version Satzart 0210 Transport";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_TRANSPORT = "Version Satzart 0220 Transport";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0250_TRANSPORT = "Version Satzart 0250 Transport Einzelanmeldung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0260_TRANSPORT = "Version Satzart 0260 Transport Umsatzanmeldung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_NICHT_DEF_SPARTEN = "Version Satzart 0210 Nicht einzeln definierte Sparten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_NICHT_DEF_SPARTEN = "Version Satzart 0220 Nicht einzeln definierte Sparten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_KFZ_BAUSTEIN = "Version KFZ-Baustein";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0300_BETEILIGUNGSINFORMATION = "Version Satzart 0300 Beteiligungsinformation";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0400_INKASSO = "Version Satzart 0400 Inkasso";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0410_INKASSO = "Version Satzart 0410 Inkasso";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0430_INKASSO = "Version Satzart 0430 Inkasso";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0500_SCHADENINFORMATION = "Version Satzart 0500 Schadeninformation";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0420_VERSICHERUNGSTEUERABRECHNUNG = "Version Satzart 0420 Versicherungsteuerabrechnung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0450_INKASSOABRECHNUNG = "Version Satzart 0450 Inkassoabrechnung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0550_SCHADENABRECHNUNG = "Version Satzart 0550 Schadenabrechnung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0350_KLAUSELN = "Version Satzart 0350 Klausel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0211_050 = "Version Satzart 0211 050";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_051 = "Version Satzart 0221 051";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_052 = "Version Satzart 0221 052";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_053 = "Version Satzart 0221 053";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_054 = "Version Satzart 0221 054";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_055 = "Version Satzart 0221 055";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_059 = "Version Satzart 0221 059";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0211_040 = "Version Satzart 0211 040";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_040 = "Version Satzart 0221 040";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_030 = "Version Satzart 0221 030";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0211_010 = "Version Satzart 0211 010";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_010 = "Version Satzart 0221 010";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0211_130 = "Version Satzart 0211 130";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_130 = "Version Satzart 0221 130";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0211_110 = "Version Satzart 0211 110";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_110 = "Version Satzart 0221 110";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0211_140 = "Version Satzart 0211 140";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_140 = "Version Satzart 0221 140";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_070 = "Version Satzart 0221 070";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0211_FEUER = "Version Satzart 0211 Feuer-Industrie/Gewerbliche Sachversicherung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_FEUER = "Version Satzart 0221 Feuer-Industrie/Gewerbliche Sachversicherung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_510 = "Version Satzart 0221 510";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0211_TRANSPORT = "Version Satzart 0211 Transport";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_TRANSPORT = "Version Satzart 0221 Transport";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0251_TRANSPORT = "Version Satzart 0251 Transport Einzelanmeldung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0211_NICHT_DEF_SPARTEN = "Version Satzart 0211 Nicht einzeln definierte Sparten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0221_NICHT_DEF_SPARTEN = "Version Satzart 0221 Nicht einzeln definierte Sparten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_550 = "Version Satzart 0210 550";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_550 = "Version Satzart 0220 550";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0270_550 = "Version Satzart 0270 550";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0280_550 = "Version Satzart 0280 550";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0291_550 = "Version Satzart 0291 550";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0292_550 = "Version Satzart 0292 550";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0293_550 = "Version Satzart 0293 550";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0294_550 = "Version Satzart 0294 550";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0295_550 = "Version Satzart 0295 550";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0052 = "Version Satzart 0051";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0102 = "Version Satzart 0102";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0212 = "Version Satzart 0212";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0352 = "Version Satzart 0352";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0362 = "Version Satzart 0362";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0382 = "Version Satzart 0382";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_9950 = "Version Satzart 9950";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_9952 = "Version Satzart 9952";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_580 = "Version Satzart 0210 580";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_580 = "Version Satzart 0220 580";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0372 = "Version Satzart 0372";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0600 = "Version Satzart 0600";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERTRAGSABLAUF = "Vertragsablauf";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERTRAGSBEGINN = "Vertragsbeginn";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERTRAGSFORM = "Vertragsform";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERTRAGSSTATUS = "Vertragsstatus";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VORAUSSICHTLICHES_ENDE = "voraussichtliches Ende";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VORLAUFSSUMME_IN_WAEHRUNGSEINHEITEN = "Vorlaufsumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VORNAME_VP = "Vorname der VP";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VORSTEUERABZUGSBERECHTIGT = "Vorsteuerabzugsberechtigt";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VORZUGSSEUERBERECHTIGUNG_PROZENT = "Vorsteuerabszugsberechtigung in Prozent";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VORZUGSSTEUERBERECHTIGUNG = "Vorsteuerabszugsberechtigung Ja/Nein";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VP_PERSONENNUMMER_VERMITTLER = "VP-Personnenummer des Vermittlers";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VP_PERSONENNUMMER_VERSICHERER = "VP-Personnenummer des Versicherers";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VU_ABRECHNUNGSSTELLE = "VU-Abrechnungsstelle";
    /** @deprecated bitte {@link Bezeichner#VU_NR} verwenden */
    @Deprecated
    public static final String VU_NUMMER = "VU-Nummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAEHRUNGSSCHLUESSEL = "Waehrungsschluessel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAEHRUNG_DOKUMENTE_FUER_VN = "Waehrung der Dokumente fuer VN";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNIS = "Wagnis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNISKENNZIFFER = "Wagniskennziffer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WARTEZEIT = "Wartezeit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WEITERE_REFERENZNUMMERN = "Weitere Referenznummern";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WOHNEIGENTUM = "Wohneigentum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZAHLUNGSANFANG = "Zahlungsanfang";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZAHLUNGSART = "Zahlungsart /-weg";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZAHLUNGSWEISE = "Zahlungsweise";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZAHLUNGSWEISE_KUENFTIGER_GESAMTBETRAG = "Zahlungsweise des kuenftigen Gesamtbetrags";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZIELGRUPPENSCHLUESSEL = "Zielgruppenschluessel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUKUENFTIGER_GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN = "Zukuenftiger Gesamtbeitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUSAETZLICHE_SATZKENNUNG = "zusaetzliche Satzkennung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUSCHLAG1_IN_PROZENT = "Zuschlag-1 in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUSCHLAG1_IN_WAEHRUNGSEINHEITEN = "Zuschlag-1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUSCHLAG2_IN_PROZENT = "Zuschlag-2 in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUSCHLAG2_IN_WAEHRUNGSEINHEITEN = "Zuschlag-2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN = "Zuschlagsbetrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUSCHLAG_IN_PROZENT = "Zuschlag in %";
    /** @deprecated bitte {@link Bezeichner#ZUZAHLUNGSBETRAG_IN_WE} verwenden */
    @Deprecated
    public static final String ZUZAHLUNGSBETRAG_IN_WAEHRUNGSEINHEITEN = "Zuzahlungsbetrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUZAHLUNGSRECHT = "Zuzahlungsrecht";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0392_EVB = "Version Satzart 0392 eVB-Nummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0230_LEBEN = "Version Satzart 0230 Fondsdatensatz - Leben";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0202_ALLG_ANTRAGSDATEN = "Version Satzart 0202 Allgemeine Antragsdaten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0230_UNFALL = "Version Satzart 0230 Unfall Leistungsarten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0390_RABATTE = "Version Satzart 0390 Rabatte und Zuschlaege";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0342_BEGLEITDOK = "Version Satzart 0342 Begleitdokumente und Signaturen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_9951_MIME = "Version Satzart 9951 MIME-Datei";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_560 = "Version Satzart 0210 560";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_560 = "Version Satzart 0220 560";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_570 = "Version Satzart 0210 570";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_570 = "Version Satzart 0220 570";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0210_684 = "Version Satzart 0210 684";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_684 = "Version Satzart 0220 684";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RUECKGEWAEHRSUMME_ZUM_ABLAUF_IN_WAEHRUNGSEINHEITEN = "Rueckgewaehrsumme zum Ablauf";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SOLIDARITAETSZUSCHLAG_BEI_ABLAUF = "Solidarit\u00e4tszuschlag bei Ablauf";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RUECKKAUFSWERT_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = "Rueckkaufswert zum Berechnungsstichtag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SOLIDARITAETSZUSCHLAG_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG = "Solidarit\u00e4tszuschlag bei Rueckkauf zum Berechnungsstichtag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UEBERSCHUSSANTEILE_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = "Ueberschussanteile zum Berechnungsstichtag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MITARBEITER_STATUS = "Mitarbeiter Status";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PERSONEN_KUNDENNUMMER_DES_VERMITTLERS = "Personen Kundennummer des Vermittlers";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SOZIALVERSICHERUNG_NUMMER = "Sozialversicherung Nummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STATUS_SEIT = "Status seit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNISART = "Wagnisart";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VORZEICHEN = "Vorzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VORZEICHEN2 = "Vorzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VORZEICHEN3 = "Vorzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VORZEICHEN4 = "Vorzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VORZEICHEN5 = "Vorzeichen";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZWANG_ZUR_BUZ = "Zwang zur BUZ";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RENTE_INCL_UEBERSCHUSSBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = "Rente inkl. Ueberschussbeteiligung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUKUENFTIGER_BEITRAG = "Zukuenftiger Beitrag";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Versicherungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RENTE_INKL_UEBERSCHUSSANRECHNUNG_IN_WAEHRUNGSEINHEITEN = "Rente inkl. Ueberschussanrechnung";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTE_UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = "Naechste Unfallsumme";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNFALLAENDERUNGS_PROZENTSATZ = "Unfallaenderungs-Prozentsatz";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = "Unfallsumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOZUSCHLAG = "Risikozuschlag";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WERTPAPIERKENNNUMMER = "Wertpapierkennnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String THESAUR = "Thesaur";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PROZENTSATZ = "Prozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN2 = "Nettobeitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNISART4 = "Wagnisart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Naechste Beitragssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Todesfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TODESFALLAENDERUNGS_PROZENTSATZ = "Todesfallaenderungs-Prozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Naechste Todesfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = "Naechste Jahresrente";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Provisionspflichtige Beitragssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Provisionspflichtige Wertungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WERTUNGSBASIS = "Wertungsbasis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WERTUNGSMODELL = "Wertungsmodell";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Provisionspflichtige Beitragssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Provisionspflichtige Wertungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WERTUNGSBASIS2 = "Wertungsbasis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WERTUNGSMODELL2 = "Wertungsmodell";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SONSTIGER_BEZUGSBERECHTIGTER_IM_ERLEBENSFALL = "Sonstiger Bezeugsberechtigter im Erlebensfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNWIDERRUFLICHES_BEZUGSRECHT_IM_ERLEBENSFALL = "Unwiderrufliches Bezugsrecht im Erlebensfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SONSTIGER_BEZUGSBERECHTIGTER_IM_TODESFALL = "Sonstiger Bezugsberechtigter im Todesfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNWIDERRUFLICHES_BEZUGSRECHT_IM_TODESFALL = "Unwiderrufliches Bezugsrecht im Todesfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTE_AUSZAHLUNGSSUMMER_IN_WAEHRUNGSEINHEITEN = "Naechste Auszahlungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTER_AUSZAHLUNGSTERMIN = "Naechster Auszahlungstermin";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERTRAGSLAUFZEIT = "Vertragslaufzeit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERTRAGSART = "Vertragsart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STATUS = "Status";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UEBERSCHUSS_GUELTIG_AB = "Ueberschuss gueltig ab";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOEINSCHRAENKUNG = "Risikoeinschraenkung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOZUSCHLAEGE = "Risikozuschlaege";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN = "Rueckkaufswert";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN_MIT_NACHKOMMA = "Rueckkaufswert";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RUECKKAUFSWERT_GUELTIG_AB = "Rueckkaufswert gueltig ab";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MINDESTLAUFZEIT = "Mindestlaufzeit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RUECKGEWAEHR_BEI_TOD = "Rueckgewaehr bei Tod";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WITWEN_WITWERRENTE_IN_PROZENT = "Witwen- / Witwerrente in Prozent";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TECHNISCHE_WITWEN_WITTWERRENTE_IN_PROZENT = "Technische Witwen- / Witwerrente in Prozent";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAISENRENTE_IN_PROZENT = "Waisenrente in Prozent";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TECHNISCHE_WAISE_IN_PROZENT = "Technische Waisenrente in Prozent";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SCHLUSSALTER_DES_WAISEN = "Schlussalter des Waisen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZAHLUNG_DER_WITWEN_WITWERRENTE_BIS = "Zahlung der Witwen- / Witwerrente bis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UMTAUSCHRECHT = "Umtauschrecht";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STUNDUNG = "Stundung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNISART2 = "Wagnisart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN = "Nettobeitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOZUSCHLAG_IN_WAEHRUNGSEINHEITEN = "Risikozuschlag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RENTENZAHLWEISE = "Rentenzahlweise";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MITZUVERSICHERNDE_PERSON = "Mitzuversichernde Person";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TARIFBEZEICHNUNG_DES_FOLGETARIFS = "Tarifbezeichnung des Folgetarifs";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UMSTELLUNGSDATUM_DES_FOLGETARIFS = "Umstellungsdatum des Folgetarifs";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUKUENFTIGER_BEITRAG_IN_WAEHRUNGSEINHEITEN = "Zukuenftiger Beitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERTRAGSBEDINGUNG = "Vertragsbedingung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VEREINBARTER_DYNAMIKMINDESTANPASSUNGSPROZENTSATZ = "Vereinbarter Dynamikmindestanpassungsprozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VEREINBARTER_DYNAMIKMAXIMALANPASSUNGSPROZENTSATZ = "Vereinbarter Dynamikmaximalanpassungsprozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNISART3 = "Wagnisart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEISTUNG_BEI_SCHWERER_ERKRANKUNG = "Leistung bei schwerer Erkrankung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSICHERTE_ERKRANKUNGEN = "Versicherte Erkrankungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TEILKAPITALISIERUNG = "Teilkapitalisierung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TODESFALLLEISTUNG_IN_WAEHRUNGSEINHEITEN = "Todesfallleistung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TEILDATENSATZNUMMER = "Teildatensatznummer";

    // Haftpflicht
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RESTLAUFZEIT_DES_VERTRAGES = "Restlaufzeit des Vertrages";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SELBSTBEHALT = "Selbstbehalt";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAEHRUNGSSCHLUESSEL_1 = "Waehrungsschluessel 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAEHRUNGSSCHLUESSEL_2 = "Waehrungsschluessel 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAEHRUNGSSCHLUESSEL_3 = "Waehrungsschluessel 3";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAEHRUNGSSCHLUESSEL_4 = "Waehrungsschluessel 4";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MENGENSCHLUESSEL = "Mengenschluessel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MINDESTBEITRAG_JE_WAGNIS_IN_WAEHRUNGSEINHEITEN = "Mindestbeitrag je Wagnis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ORDNUNGS_NUMMER_FUER_WAGNISZUSATZ2 = "Ordungs-Nummer fuer Wagniszusatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String POSTLEITZAHL_DER_RISIKOANSCHRIFT = "Postleitzahl der Risikoanschrift";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOORT = "Risikoort";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOSTRASSE = "Risikostrasse";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SUMMENART_1 = "Summenart 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SUMMENART_2 = "Summenart 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SUMMENART_3 = "Summenart 3";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SUMMENART_4 = "Summenart 4";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNISBESCHREIBUNG = "Wagnisbeschreibung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNISMENGE = "Wagnismenge";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNISTEXT = "Wagnistext";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ORDNUNGS_NUMMER_FUER_WAGNISZUATZ = "Ordungs-Nummer fuer Wagniszusatz";

    // Unfall
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String REFERENZ_VERSICHERUNGSSCHEINNUMME = "Referenz-Versicherungsscheinnumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNFAELLE = "Unfaelle";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PROZENTSATZ_PROGRESSIVE_INVALIDITAET_MEHRLEISTUNG_BEI_INVALIDITAET = "Prozentsatz progressive Invaliditaet / Mehrleistung bei Invaliditaet";

    // Verbundene Hausrat
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAEHRUNGSSCHUESSEL = "Waehrungsschluessel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SICHERUNGSRICHTLINIEN = "Sicherungsrichtlinien";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOKENNZIFFER = "Risikokennziffer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WOHNFLAECHE_QM = "Wohnflaeche qm";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TARIFZONE = "Tarifzone";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNTERVERS_VERZICHT = "Untervers.-Verzicht";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String OBJEKTNUMMER2 = "Objektnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STAENDIG_BEWOHNT = "Staendig bewohnt";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String OBJEKTNUMMER = "Objektnummer";

    /////////// Bezeichner-Konstanten /////////////////////////////////////////

    public static final Bezeichner ABGANGSDAT = new Bezeichner("Abgangsdatum", "Abgangsdat");
    public static final Bezeichner ABGANGSGRUND = new Bezeichner("Abgangsgrund");
    public static final Bezeichner ABLAUF = new Bezeichner("Ablauf");
    public static final Bezeichner ABLAUFLEISTUNG_INKL_UEBERSCHUSSANTEILE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Ablaufleistung incl. Ueberschussanteile");
    public static final Bezeichner ABSCHLAG1_IN_PROZENT = new Bezeichner("Abschlag-1 in %");
    public static final Bezeichner ABSCHLAG1_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Abschlag-1 in Waehrungseinheiten", "Abschlag1InWE");
    public static final Bezeichner ABSCHLAG2_IN_PROZENT = new Bezeichner("Abschlag-2 in %");
    public static final Bezeichner ABSCHLAG2_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Abschlag-2 in Waehrungseinheiten", "Abschlag1InWE");
    public static final Bezeichner ABSCHLAG3_IN_PROZENT = new Bezeichner("Abschlag-3 in %");
    public static final Bezeichner ABSCHLAG3_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Abschlag-3 in Waehrungseinheiten", "Abschlag1InWE");
    public static final Bezeichner ABSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Abschlagsbetrag");
    public static final Bezeichner ABSCHLAG_IN_PROZENT = new Bezeichner("Abschlag in %");
    public static final Bezeichner ABSCHLUSSPROVISION = new Bezeichner("Abschlussprovision");
    public static final Bezeichner ABSENDER = new Bezeichner("Absender");
    public static final Bezeichner ABSOLUTE_BEITRAGSSUMMENAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absolute Beitragssummenaenderungssumme");
    public static final Bezeichner ABSOLUTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absolute Erlebensfall VS");
    public static final Bezeichner ABSOLUTE_JAHRESRENTENAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absolute Jahresrentenaenderungssumme");
    public static final Bezeichner ABSOLUTE_TODESFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absolute Todesfallaenderungssumme");
    public static final Bezeichner ABSOLUTE_TODESFALLAENDERUNGSSUMME_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absolute Todesfallaenderungssumme VS");
    public static final Bezeichner ABSOLUTE_UNFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absolute Unfallaenderungssumme");
    public static final Bezeichner ABWEICHENDES_DYNAMIKENDALTER = new Bezeichner("Abweichendes Dynamikalter");
    public static final Bezeichner ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absoluter Dynamikerhoehungsbetrag");
    public static final Bezeichner AENDERUNG_DER_BEITRAGSSUMME = new Bezeichner("Aenderung der Beitragssumme");
    public static final Bezeichner ABSTAND_DER_BEITRAGSSUMMENAENDERUNGSTERMINE = new Bezeichner("Abstand der Beitragssummenaenderungstermine");
    public static final Bezeichner ABSTAND_DER_ERLEBENSFAL_VS_AENDERUNGSTERMINE = new Bezeichner("Abstand der Erlebensfall VS-Aenderungstermine");
    public static final Bezeichner ABSTAND_DER_JAHRESRENTENAENDERUNGSTERMINE = new Bezeichner("Abstand der Jahresrentenaenderungstermine");
    public static final Bezeichner ABSTAND_DER_TODESFALLAENDERUNGSTERMINE = new Bezeichner("Abstand der Todesfallaenderungstermine");
    public static final Bezeichner ABSTAND_DER_UNFALLAENDERUNGSTERMINE = new Bezeichner("Abstand der Unfallaenderungstermine");
    public static final Bezeichner ABWEICHENDE_LEISTUNGSDAUER = new Bezeichner("Abweichende Leistungsdauer");
    public static final Bezeichner ABWEICHENDE_VERTRAGSLAUFZEIT = new Bezeichner("Abweichende Vertragslaufzeit");
    public static final Bezeichner ABWEICHENDER_ABLAUF = new Bezeichner("Abweichender Ablauf");
    public static final Bezeichner ABWEICHENDER_KONTOINHABER1 = new Bezeichner("Abweichender Kontoinhaber 1");
    public static final Bezeichner ABWEICHENDER_KONTOINHABER2 = new Bezeichner("Abweichender Kontoinhaber 2");
    public static final Bezeichner ABWEICHENDE_VU_NR = new Bezeichner("Abweichende VU-Nr.");
    public static final Bezeichner AENDERUNG_DER_ERLEBENSFALL_VS = new Bezeichner("Aenderung der Erlebensfall VS");
    public static final Bezeichner AENDERUNG_DER_TODESFALLLEISTUNG = new Bezeichner("Aenderung der Todesfallleistung");
    public static final Bezeichner AENDERUNG_DER_JAHRESRENTE = new Bezeichner("Aenderung der Jahresrente");
    public static final Bezeichner AENDERUNG_DER_UNFALLLEISTUNG = new Bezeichner("Aenderung der Unfallleistung");
    public static final Bezeichner AENDERUNGSDAT = new Bezeichner("Aenderungsdatum", "Aenderungsdat");
    public static final Bezeichner ANFAENGLICHE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Anfaengliche Erlebensfall VS");
    public static final Bezeichner ANFAENGLICHE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Anfaengliche Todesfall VS");
    public static final Bezeichner ANFAENGLICHE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Anfaengliche Jahresrente");
    public static final Bezeichner ANFAENGLICHE_UNFALLSUMME = new Bezeichner("Anfaengliche Unfallsumme");
    public static final Bezeichner ANLAGESTRATEGIE = new Bezeichner("Anlagestrategie");
    public static final Bezeichner ANTEILIGER_DYNAMIKPROZENTSATZ = new Bezeichner("Anteiliger Dynamikprozentsatz");
    public static final Bezeichner ANTRAGSDAT = new Bezeichner("Antragsdatum", "Antragsdat");
    public static final Bezeichner ANTRAGSEINGANGSDAT = new Bezeichner("Antragseingangsdatum", "Antragseingangsdat");
    public static final Bezeichner AUSZAHLUNGSWEISE = new Bezeichner("Auszahlungsweise");
    public static final Bezeichner ANZAHL_DER_AUSZAHLUNGEN = new Bezeichner("Anzahl der Auszahlungen");
    public static final Bezeichner ANZAHL_DER_VERSICHERTEN_PERSONEN = new Bezeichner("Anzahl der versicherten Personen");
    public static final Bezeichner ART_DES_BERUFSSCHLUESSELVERZEICHNISSES = new Bezeichner("Art des Berufsschluesselverzeichnisses", "ArtDesBerufsschluesselverzeichnisses");
    public static final Bezeichner AUSSCHLUSSDAT_VP_PERSONENGRUPPE = new Bezeichner("Ausschlussdatum VP / Personengruppe", "AusschlussdatVpPersonengruppe");
    public static final Bezeichner ADRESSAT = new Bezeichner("Adressat");
    public static final Bezeichner ADRESSKENNZEICHEN = new Bezeichner("Adresskennzeichen");
    public static final Bezeichner AENDERUNG = new Bezeichner("Aenderung");
    public static final Bezeichner AENDERUNGSGRUND = new Bezeichner("Aenderungsgrund");
    public static final Bezeichner AFB = new Bezeichner("A,F,B");
    public static final Bezeichner AKTENZEICHEN_SICHERUNGSGLAEUBIGER = new Bezeichner("Aktenzeichen des Sicherungsglaeubigers");
    public static final Bezeichner AKTUELLE_BEITRAGSDEPOTSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("aktuelle Beitragsdepotsumme");
    public static final Bezeichner AKTUELLE_FONDSJAHRESRENTE_ZUM_ABLAUF_INKL_ABRUFPHASE = new Bezeichner("Aktuelle Fondsjahresrente zum Ablauf (inkl. Abrufphase)");
    public static final Bezeichner AKTUELLE_FONDSJAHRESRENTE_ZUM_BEGINN_DER_ABRUFPHASE = new Bezeichner("Aktuelle Fondsjahresrente zum Beginn der Abrufphase");
    public static final Bezeichner ALLGEMEINE_VERSICHERUNGSBEDINGUNGEN = new Bezeichner("Allgemeine Versicherungsbedingungen");
    public static final Bezeichner ALTERSGRUPPE = new Bezeichner("Altersgruppe");
    public static final Bezeichner AMTL_KENNZEICHEN = new Bezeichner("Amtl. Kennzeichen");
    public static final Bezeichner ANREDESCHLUESSEL = new Bezeichner("Anredeschluessel");
    public static final Bezeichner ANTEIL_IN_PROZENT = new Bezeichner("Anteil in %");
    public static final Bezeichner ANTEILE = new Bezeichner("Anteile");
    public static final Bezeichner ANZAHL_DER_VORBESITZER = new Bezeichner("Anzahl der Vorbesitzer");
    public static final Bezeichner ANZAHL_MONATE_UNBEWOHNT = new Bezeichner("Anzahl Monate unbewohnt");
    public static final Bezeichner ANZAHL_SAETZE = new Bezeichner("Anzahl der Saetze");
    public static final Bezeichner ANZAHL_VERBLEIBENDE_DYNAMIKWIDERSPRUECHE = new Bezeichner("Anzahl verbleibende Dynamikwidersprueche");
    public static final Bezeichner ANZAHL_VP_PRO_PERSONENGRUPPE = new Bezeichner("Anzahl der VP pro Personengruppe");
    public static final Bezeichner ANZAHL_WOHNEINHEITEN = new Bezeichner("Anzahl Wohnheiten");
    public static final Bezeichner ARB = new Bezeichner("ARB (Allgemeine Bedingungen fuer die Rechtschutzvers.)");
    public static final Bezeichner ART_DER_AUSZAHLUNG = new Bezeichner("Art der Auszahlung");
    public static final Bezeichner ART_DES_BEITRAGSSATZES = new Bezeichner("Art des Beitragssatzes");
    public static final Bezeichner ART_DER_LEISTUNG = new Bezeichner("Art der Leistung");
    public static final Bezeichner ART_DER_HEILKOSTEN = new Bezeichner("Art der Heilkosten");
    public static final Bezeichner ART_DER_STEUERLICHEN_FOERDERUNG = new Bezeichner("Art der steuerlichen Foerderung");
    public static final Bezeichner ART_DER_ZULASSUNG_BEIM_VORBESITZER = new Bezeichner("Art der Zulassung beim Vorbesitzer");
    public static final Bezeichner ART_DES_ABSENDERS = new Bezeichner("Art des Absenders");
    public static final Bezeichner ART_DES_ADRESSATEN = new Bezeichner("Art des Adressaten");
    public static final Bezeichner ART_DES_AMTLICHEN_KENNZEICHENS = new Bezeichner("Art des amtlichen Kennzeichens");
    public static final Bezeichner ART_DES_DRITTRECHTS = new Bezeichner("Art des Drittrechts");
    public static final Bezeichner AUFBAUART = new Bezeichner("Aufbauart");
    public static final Bezeichner AUFSICHTSFREIER_VERTRAG = new Bezeichner("Aufsichtsfreier Vertrag");
    public static final Bezeichner AUFTEILUNG_VERSICHERUNGSSTEUER = new Bezeichner("Aufteilung Versicherungsteuer gemaess EU-Richtlinien");
    public static final Bezeichner AUFTRAGSNR_VERMITTLER = new Bezeichner("Auftrags-Nr. des Vermittlers");
    public static final Bezeichner AUSLOESUNG_DER_LEISTUNG = new Bezeichner("Ausloesung der Leistung");
    public static final Bezeichner AUSSCHLUSS = new Bezeichner("Ausschluss");
    public static final Bezeichner AUSSCHLUSSDATUM_VP = new Bezeichner("Ausschlussdatum VP / Personengruppe");

    public static final Bezeichner BAUARTKLASSE = new Bezeichner("Bauartklasse");
    public static final Bezeichner BAUJAHR = new Bezeichner("Baujahr");
    public static final Bezeichner BAUSTEIN_GESAMTBEITRAG_1_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Baustein-Gesamtbeitrag 1");
    public static final Bezeichner BAUSTEIN_GESAMTBEITRAG_2_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Baustein-Gesamtbeitrag 2");
    public static final Bezeichner BEDINGUNGEN = new Bezeichner("Bedingungen");
    public static final Bezeichner BEGINN = new Bezeichner("Beginn");
    public static final Bezeichner BEGINN_ABRUFPHASE = new Bezeichner("Beginn Abrufphase");
    public static final Bezeichner BEGINN_DER_RENTENZAHLUNG = new Bezeichner("Beginn der Rentenzahlung");
    public static final Bezeichner BEGINN_DER_ZAHLUNG_AB_TAG = new Bezeichner("Beginn der Zahlung ab Tag");
    public static final Bezeichner BEGINN_TAGEGELD1_AB_TAG = new Bezeichner("Beginn Tagegeld 1 ab Tag");
    public static final Bezeichner BEGINN_TAGEGELD2_AB_TAG = new Bezeichner("Beginn Tagegeld 2 ab Tag");
    public static final Bezeichner BEGINN_VERSICHERUNGSSCHUTZ = new Bezeichner("Beginn Versicherungsschutz");
    public static final Bezeichner BEGINNDAT_NAECHSTEN_BEITRAGSSUMME = new Bezeichner("Beginndatum der naechsten Beitragssumme");
    public static final Bezeichner BEGINNDAT_NAECHSTEN_ERLEBENSFALL_VS = new Bezeichner("Beginndatum der naechsten Erlebensfall VS", "BeginndatNaechstenErlebensfallVs");
    public static final Bezeichner BEGINNDAT_NAECHSTEN_JAHRESRENTE = new Bezeichner("Beginndatum der naechsten Jahresrente", "BeginndatNaechstenJahresrente");
    public static final Bezeichner BEGINNDAT_NAECHSTEN_TODESFALL_VS = new Bezeichner("Beginndatum der naechsten Todesfall VS");
    public static final Bezeichner BEGINNDAT_NAECHSTEN_JAHRESRENTESUMME = new Bezeichner("Beginndatum der naechsten Jahresrentensumme");
    public static final Bezeichner BEGINNDAT_NAECHSTEN_UNFALLSUMME = new Bezeichner("Beginndatum der naechsten Unfallsumme", "BeginndatNaechstenUnfallsumme");
    public static final Bezeichner BEITRAGSANGLEICHUNGSKLAUSEL = new Bezeichner("Beitragsangleichungsklausel");
    public static final Bezeichner BEITRAGSDEPOT = new Bezeichner("Beitragsdepot");
    public static final Bezeichner BEITRAGSFREIE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitragsfreie Beitragssumme");
    public static final Bezeichner BEITRAGSFREIE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitragsfreie Erlebensfall VS");
    public static final Bezeichner BEITRAGSFREIE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitragsfreie Todesfall VS");
    public static final Bezeichner BEITRAGSKLASSE = new Bezeichner("Beitragsklasse");
    public static final Bezeichner BEITRAGSREGULIERUNG = new Bezeichner("Beitragsregulierung");
    public static final Bezeichner BEITRAGSRUECKGEWAEHR = new Bezeichner("Beitragsrueckgewaehr");
    public static final Bezeichner BEITRAGSUMSTELLUNGSGRUND = new Bezeichner("Beitragsumstellungsgrund");
    public static final Bezeichner BEITRAG = new Bezeichner("Beitrag");
    public static final Bezeichner BEITRAG_BERGUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Bergungskosten");
    public static final Bezeichner BEITRAG_BU_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag BU");
    public static final Bezeichner BEITRAG_FESTE_RENTE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Feste Rente");
    public static final Bezeichner BEITRAG_GENESUNGSGELD_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Genesungsgeld");
    public static final Bezeichner BEITRAG_HEILKOSTEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Heilkosten");
    public static final Bezeichner BEITRAG_INVALIDITAET_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Invaliditaet");
    public static final Bezeichner BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag");
    public static final Bezeichner BEITRAG_JE_BERECHNUNGSEINHEIT_UND_MENGENSCHLUESSEL = new Bezeichner("Beitrag je Berechnungseinheit und Mengenschluessel");
    public static final Bezeichner BEITRAG_KOSMETISCHE_OPERATION_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Kosmetische Operation");
    public static final Bezeichner BEITRAG_KRANKENHAUSTAGEGELD_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Krankenhaustagegeld");
    public static final Bezeichner BEITRAG_KURKOSTEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Kurkosten");
    public static final Bezeichner BEITRAG_PRO_VP_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag pro VP oder pro Personengruppe");
    public static final Bezeichner BEITRAG_PROMILLE = new Bezeichner("Beitrag Promille");
    public static final Bezeichner BEITRAG_SERVICELEISTUNGEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Serviceleistungen");
    public static final Bezeichner BEITRAG_TAGEGELD1_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Tagegeld 1");
    public static final Bezeichner BEITRAG_TAGEGELD2_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Tagegeld 2");
    public static final Bezeichner BEITRAG_TOD_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Tod");
    public static final Bezeichner BEITRAG_UEBERGANGSENTSCHAEDIGUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Uebergangsentschaedigung");
    public static final Bezeichner BEITRAGSSATZ = new Bezeichner("Beitragssatz");
    public static final Bezeichner BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitragssumme");
    public static final Bezeichner BEITRAGSSUMMENAENDERUNGS_PROZENTSATZ = new Bezeichner("Beitragssummenaenderungs-Prozentsatz");
    public static final Bezeichner BEITRAGSUMSTELLUNGSDAT = new Bezeichner("Beitragsumstellungsdatum", "Beitragsumstellungsdat");
    public static final Bezeichner BEITRAGSZAHLUNG_BIS = new Bezeichner("Beitragszahlung bis");
    public static final Bezeichner BERECHNUNGSEINHEIT = new Bezeichner("Berechnungseinheit");
    public static final Bezeichner BERECHNUNGSSTICHTAG_ZUM_RUECKKAUFSWERT = new Bezeichner("Berechnungsstichtag zum Rueckkaufswert");
    public static final Bezeichner BERGUNGSKOSTEN = new Bezeichner("Bergungskosten");
    public static final Bezeichner BERGUNGSKOSTEN_BEITRAGSSATZ = new Bezeichner("Bergungskosten Beitragssatz");
    public static final Bezeichner BERUFSGRUPPENEINTEILUNG = new Bezeichner("Berufsgruppeneinteilung im Industrie-Straf-RS");
    public static final Bezeichner BERUFSSCHLUESSEL = new Bezeichner("Berufsschluessel");
    public static final Bezeichner BERUF_TEXT = new Bezeichner("Beruf-Text");
    public static final Bezeichner BESONDERER_VERWENDUNGSZWECK = new Bezeichner("besonderer Verwendungszweck");
    public static final Bezeichner BESONDERE_VEREINBARUNG_ZUM_FLUGGASTRISIKO = new Bezeichner("Besondere Vereinbarung zum Fluggastrisiko");
    public static final Bezeichner BESONDERE_VEREINBARUNGEN = new Bezeichner("Besondere Vereinbarungen");
    public static final Bezeichner BESTANDSFUEHRENDE_GESCHAEFTSSTELLE = new Bezeichner("Bestandsfuehrende Geschaeftsstelle");
    public static final Bezeichner BESTANDSPFLEGEPROVISION = new Bezeichner("Bestandspflegeprovision");
    public static final Bezeichner BETRIEBLICHE_ALTERSVORSORGE = new Bezeichner("Betriebliche Altersvorsorge");
    public static final Bezeichner BEZEICHNUNG_DER_LEISTUNG = new Bezeichner("Bezeichnung der Leistung");
    public static final Bezeichner BEZEICHNUNG_PERSONENGRUPPE = new Bezeichner("Bezeichnung Personengruppe");
    public static final Bezeichner BEZUGSBERECHTIGT_IM_ERLEBENSFALL = new Bezeichner("Bezugsberechtigt im Erlebensfall");
    public static final Bezeichner BEZUGSBERECHTIGT_IM_LEISTUNGSFALL = new Bezeichner("Bezugsberechtigt im Leistungsfall");
    public static final Bezeichner BEZUGSBERECHTIGT_IM_TODESFALL = new Bezeichner("Bezugsberechtigt im Todesfall");
    public static final Bezeichner BEZUGSDAT = new Bezeichner("Bezugsdatum", "Bezugsdat");
    public static final Bezeichner BEZUGSRECHTANTEIL_IM_ERLEBENSFALL = new Bezeichner("Bezugsrechtanteil im Erlebensfall");
    public static final Bezeichner BEZUGSRECHTANTEIL_IM_LEISTUNGSFALL = new Bezeichner("Bezugsrechtanteil im Leistungsfall");
    public static final Bezeichner BEZUGSRECHTANTEIL_IM_TODESFALL = new Bezeichner("Bezugsrechtanteil im Todesfall");
    public static final Bezeichner BIC1 = new Bezeichner("BIC 1");
    public static final Bezeichner BIC2 = new Bezeichner("BIC 2");
    public static final Bezeichner BILANZMONAT_ARBEITGEBER = new Bezeichner("Bilanzmonat Arbeitgeber");
    public static final Bezeichner BLZ1 = new Bezeichner("Bankleitzahl 1");
    public static final Bezeichner BLZ2 = new Bezeichner("Bankleitzahl 2");
    public static final Bezeichner BUENDELUNGSKENNZEICHEN = new Bezeichner("Buendelungskennzeichen");
    public static final Bezeichner BUCHUNGSKENNZEICHEN = new Bezeichner("Buchungskennzeichen");
    public static final Bezeichner BUCHUNGSKENNZEICHEN2 = BUCHUNGSKENNZEICHEN;
    public static final Bezeichner BUZ_LEISTUNG_VON = new Bezeichner("BUZ Leistung von");
    public static final Bezeichner BUZ_LEISTUNG_BIS = new Bezeichner("BUZ Leistung bis");
    public static final Bezeichner BUZ_PROZENT_SATZ = new Bezeichner("BUZ %-Satz");
    public static final Bezeichner BUZ_VERWENDUNGSART = new Bezeichner("BUZ Verwendungsart");

    public static final Bezeichner DAT_BEZUGSFERTIGKEIT = new Bezeichner("Datum der Bezugsfertigkeit", "DatBezugsfertigkeit");
    public static final Bezeichner DAT_LETZTEN_BEITRAGSANGLEICHUNG = new Bezeichner("Datum der letzten Beitragsangleichung", "DatLetztenBeitragsangleichung");
    public static final Bezeichner DAT_LETZTEN_POSITIVEN_DYNAMIK = new Bezeichner("Datum der letzten positiven Dynamik", "DatLetztenPositivenDynamik");
    public static final Bezeichner DAT_UNVERFALLBARKEIT = new Bezeichner("Datum Unverfallbarkeit");
    public static final Bezeichner DAUERSCHAEDEN_KOERPERLICHE_BEEINTRAECHTIGUNGEN = new Bezeichner("Dauerschaeden / koerperliche Beeintraechtigungen");
    public static final Bezeichner DECKUNGSSUMME_1_IN_TAUSEND_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 1 in Tausend");
    public static final Bezeichner DECKUNGSSUMME_2_IN_TAUSEND_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 2 in Tausend");
    public static final Bezeichner DECKUNGSSUMME_3_IN_TAUSEND_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 3 in Tausend");
    public static final Bezeichner DECKUNGSSUMME_4_IN_TAUSEND_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 4 in Tausend");
    public static final Bezeichner DECKUNGSSUMME_1_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 1");
    public static final Bezeichner DECKUNGSSUMME_2_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 2");
    public static final Bezeichner DECKUNGSSUMME_3_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 3");
    public static final Bezeichner DECKUNGSSUMME_4_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 4");
    public static final Bezeichner DECKUNGSSUMME_IN_TSD_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme in Tausend Waehrungseinheiten");
    public static final Bezeichner DECKUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme");
    public static final Bezeichner DECKUNGSUMFANG = new Bezeichner("Deckungsumfang");
    public static final Bezeichner DIENSTEINTRITT = new Bezeichner("Diensteintritt");
    public static final Bezeichner DIENSTEINTRITTSDAT = new Bezeichner("Diensteintrittsdatum", "Diensteintrittsdat");
    public static final Bezeichner DIREKTANSPRUCH = new Bezeichner("Direktanspruch");
    public static final Bezeichner DRUCKAUFBEREITETE_VERSICHERUNGSSCHEINNUMMER = new Bezeichner("Druckaufbereitete Versicherungsscheinnummer");
    public static final Bezeichner DURCHFUEHRUNGSWEG = new Bezeichner("Durchfuehrungsweg");
    public static final Bezeichner DYNAMIK = new Bezeichner("Dynamik");
    public static final Bezeichner DYNAMIK_IN_PROZENT = new Bezeichner("Dynamik in %");
    public static final Bezeichner DYNAMIK_PROZENT_SATZ = new Bezeichner("Dynamik %-Satz");
    public static final Bezeichner DYNAMIKBEGINN = new Bezeichner("Dynamikbeginn");
    public static final Bezeichner DYNAMIKSTOP = new Bezeichner("Dynamikstop");

    public static final Bezeichner EIGENTUMSVERHAELTNIS_FAHRZEUG = new Bezeichner("Eigentumsverhaeltnis (Fahrzeug)");
    public static final Bezeichner EINBRUCH_MELDEANLAGE = new Bezeichner("Einbruch Meldeanlage");
    public static final Bezeichner EINGERECHNETE_ZULAGE = new Bezeichner("Eingerechnete Zulage");
    public static final Bezeichner EINRECHNUNGSJAHR = new Bezeichner("Einrechnungsjahr");
    public static final Bezeichner EINSCHLUSS_PROZENT_SATZ = new Bezeichner("Einschluss %-Satz");
    public static final Bezeichner ERWEITERTE_NEUWERTVERSICHERUNG = new Bezeichner("erweiterte Neuwertversicherung");
    public static final Bezeichner EINSCHLUSSDATUM_VP = new Bezeichner("Einschlussdatum VP / Personengruppe");
    public static final Bezeichner EINZAHLUNG_AUSSCHUETTUNG = new Bezeichner("Einzahlung / Ausschuettung");
    public static final Bezeichner ENDEDATUM_DES_VERSICHERUNGSSCHUTZES_BEI_ROTEN_KENNZEICHEN = new Bezeichner("Endedatum des Versicherungsschutzes bei roten Kennzeichen");
    public static final Bezeichner ERHOEHUNGSART_DYNAMIK = new Bezeichner("Erhoehungsart Dynamik");
    public static final Bezeichner ERHOEHUNGSBASIS_DYNAMIK = new Bezeichner("Erhoehungsbasis Dynamik");
    public static final Bezeichner ERSTE_ZULASSUNG_AUF_DEN_VN = new Bezeichner("Erste Zulassung auf den VN");
    public static final Bezeichner ERSTZULASSUNG = new Bezeichner("Erstzulassung");
    public static final Bezeichner ERWEITERUNGSSATZ_VORHANDEN = new Bezeichner("Erweiterungssatz vorhanden");
    public static final Bezeichner EVB_NUMMER = new Bezeichner("eVB-Nummer");
    public static final Bezeichner ERSTELLUNGS_DAT_ZEITRAUM_VOM_ZEITRAUM_BIS = new Bezeichner("Erstellungs-Datum, Zeitraum von, Zeitraum bis", "ErstellungsDatZeitraumVomZeitraumBis");
    public static final Bezeichner EINSCHLUSS_VANDALISMUS = new Bezeichner("Einschluss Vandalismus");
    public static final Bezeichner EINSCHLUSSDAT_VP_PERSONENGRUPPE = new Bezeichner("Einschlussdatum VP / Personengruppe", "EinschlussdatVpPersonengruppe");
    public static final Bezeichner ENDALTER = new Bezeichner("Endalter");
    public static final Bezeichner EINTRITTSALTER = new Bezeichner("Eintrittsalter");
    public static final Bezeichner EINTRITTSALTER_DER_VP = new Bezeichner("Eintrittsalter der VP");
    public static final Bezeichner ENDEDATUM_BEI_ROTEN_KENNZEICHEN = new Bezeichner("Endedatum des Versicherungsschutzes bei roten Kennzeichen", "EndedatumBeiRotenKennzeichen");
    public static final Bezeichner ERHOEHUNGSSATZ_8_III_AHB = new Bezeichner("Erhoehungssatz \u00a78, III AHB");
    public static final Bezeichner ERKRANKUNGEN = new Bezeichner("Erkrankungen");
    public static final Bezeichner ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT = new Bezeichner("Erlebensfall VS");
    public static final Bezeichner ERLEBENSFALL_VS2_IN_WAEHRUNGSEINHEIT = new Bezeichner("Erlebensfall VS II");
    public static final Bezeichner ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = new Bezeichner("Erlebensfall VS zum Ablauf");
    public static final Bezeichner ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = new Bezeichner("Erlebensfall VS II zum Ablauf");
    public static final Bezeichner ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Erlebensfall VS II");
    public static final Bezeichner ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Erlebensfall VS");
    public static final Bezeichner ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = new Bezeichner("Erlebensfall VS zum Beginn der Abrufphase");
    public static final Bezeichner ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = new Bezeichner("Erlebensfall VS II zum Beginn der Abrufphase");
    public static final Bezeichner ERLEBENSFALL_VS_AENDERUNGS_PROZENTSATZ = new Bezeichner("Erlebensfall VS-Aenderungs-Prozentsatz");
    public static final Bezeichner ERSTELLUNGSDAT_ZEITRAUM_VOM = new Bezeichner("Erstellungsdatm Zeitraum vom");
    public static final Bezeichner ERSTELLUNGSDAT_ZEITRAUM_BIS = new Bezeichner("Erstellungs-Datum-Zeitraum bis");

    public static final Bezeichner FAELLIGKEIT_DER_LETZTEN_BEITRAGSZAHLUNG = new Bezeichner("Faelligkeit der letzten Beitragszahlung");
    public static final Bezeichner FAHRZEUGART = new Bezeichner("Fahrzeugart");
    public static final Bezeichner FAHRZEUGIDENTIFIZIERUNGSNUMMER = new Bezeichner("Fahrzeugidentifizierungsnummer");
    public static final Bezeichner FALLENDE_SUMME = new Bezeichner("fallende Summe");
    public static final Bezeichner FALLENDE_VS = new Bezeichner("fallende VS");
    public static final Bezeichner FAMILIENSTAND = new Bezeichner("Familienstand");
    public static final Bezeichner FESTE_RENTE = new Bezeichner("Feste Rente");
    public static final Bezeichner FESTE_RENTE_BEITRAGSSATZ = new Bezeichner("Feste Rente Beitragssatz");
    public static final Bezeichner FINANZIERUNGSART = new Bezeichner("Finanzierungsart");
    public static final Bezeichner FINANZIERUNG_ZUSAGE = new Bezeichner("Finanzierung der Zusage");
    public static final Bezeichner FLOTTENKENNZEICHEN = new Bezeichner("Flottenkennzeichen");
    public static final Bezeichner FLOTTENRABATT_IN_PROZENT = new Bezeichner("Flottenrabatt in %");
    public static final Bezeichner FOLGENUMMER = new Bezeichner("Folgenummer");
    public static final Bezeichner FOLGEPROVISION = new Bezeichner("Folgeprovision");
    public static final Bezeichner FONDSKENNUNG = new Bezeichner("Fondskennung");
    public static final Bezeichner FONDSNAME = new Bezeichner("Fondname");
    public static final Bezeichner FREMDER_GRUND_UND_BODEN = new Bezeichner("fremder Grund und Boden");
    public static final Bezeichner FREMDNUTZUNG = new Bezeichner("Fremdnutzung");
    public static final Bezeichner FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_KH = new Bezeichner("Frei vereinbarte Selbstbeteiligung fuer KH");
    public static final Bezeichner FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO = new Bezeichner("Frei vereinbarte Selbstbeteiligung fuer Teilkasko");
    public static final Bezeichner FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO_IM_RAHMEN_DER_VOLLKASKO = new Bezeichner("Frei vereinbarte Selbstbeteiligung fuer Teilkasko im Rahmen der Vollkasko");
    public static final Bezeichner FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_VOLLKASKO = new Bezeichner("Frei vereinbarte Selbstbeteiligung fuer Vollkasko");

    public static final Bezeichner GAP_DECKUNG = new Bezeichner("GAP-Deckung");
    public static final Bezeichner GARAGE = new Bezeichner("Garage");
    public static final Bezeichner GARANTIERTE_FONDSJAHRESRENTE_ZUM_ABLAUF_INKL_ABRUFPHASE = new Bezeichner("Garantierte Fondsjahresrente zum Ablauf (inkl. Abrufphase)");
    public static final Bezeichner GARANTIERTE_FONDSJAHRESRENTE_ZUM_BEGINN_DER_ABRUFPHASE = new Bezeichner("Garantierte Fondsjahresrente zum Beginn der Abrufphase");
    public static final Bezeichner GEBURTSDAT = new Bezeichner("Geburtsdatum", "Geburtsdat");
    public static final Bezeichner GEBURTSDAT_VP = new Bezeichner("Geburtsdatum der VP", "GeburtsdatVp");
    public static final Bezeichner GEBURTSDAT_VP2 = new Bezeichner("Geburtsdatum der 2. VP", "GeburtsdatVp2");
    public static final Bezeichner GEBURTSDATUM_DER_BEZUGSBERECHTIGTEN_PERSON = new Bezeichner("Geburtsdatum der bezugsberechtigten Person");
    public static final Bezeichner GEBURTSDATUM_VP = new Bezeichner("Geburtsdatum der VP");
    public static final Bezeichner GEBURTSDATUM_VP2 = new Bezeichner("Geburtsdatum der VP2");
    public static final Bezeichner GESCHLECHT_VP = new Bezeichner("Geschlecht der VP");
    public static final Bezeichner GEBURTSLAND = new Bezeichner("Geburtsland");
    public static final Bezeichner GEBURTSNAME = new Bezeichner("Geburtsname");
    public static final Bezeichner GEBURTSORT = new Bezeichner("Geburtsort");
    public static final Bezeichner GEFAHRENERHOEHUNG = new Bezeichner("Gefahrenerhoehung");
    public static final Bezeichner GEFAHRENGRUPPE = new Bezeichner("Gefahrengruppe");
    public static final Bezeichner GEFAHRGUT = new Bezeichner("Gefahrgut");
    public static final Bezeichner GELTUNGSBEREICH = new Bezeichner("Geltungsbereich");
    public static final Bezeichner GELTUNGSBEREICHEINSCHRAENKUNG = new Bezeichner("Geltungsbereicheinschraenkung");
    public static final Bezeichner GENESUNGSGELD = new Bezeichner("Genesungsgeld");
    public static final Bezeichner GENESUNGSGELD_BEITRAGSSATZ = new Bezeichner("Genesungsgeld Beitragssatz");
    public static final Bezeichner GESAMTBEITRAG = new Bezeichner("Gesamtbeitrag");
    public static final Bezeichner GESAMTBEITRAG_BRUTTO = new Bezeichner("Gesamtbeitrag-Brutto(Inkasso)");
    public static final Bezeichner GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtbeitrag (Brutto)");
    public static final Bezeichner GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtbeitrag");
    public static final Bezeichner GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtbeitrag (Netto)");
    public static final Bezeichner GESAMTMASSE = new Bezeichner("Gesamtmasse");
    public static final Bezeichner GESAMTPROVISIONSBETRAG = new Bezeichner("Gesamtprovisions-Betrag");
    public static final Bezeichner GESAMTVERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtversicherungssummer");
    public static final Bezeichner GESCHAEFTSSTELLE_VERMITTLER = new Bezeichner("Geschaeftsstelle / Vermittler");
    public static final Bezeichner GESCHLECHT = new Bezeichner("Geschlecht");
    public static final Bezeichner GEBURTSDATUM_DER_MITZUVERSICHERNDEN_PERSON = new Bezeichner("Geburtsdatum der mitzuversichernden Person");
    public static final Bezeichner GESCHLECHT_DER_MITZUVERSICHERNDEN_PERSON = new Bezeichner("Geschlecht der mitzuverschernden Person");
    public static final Bezeichner GESTUNDET_AUSGESETZT_BIS = new Bezeichner("Gestundet ausgesetzt bis");
    public static final Bezeichner GEWINNVERWENDUNGSART = new Bezeichner("Gewinnverwendungsart");
    public static final Bezeichner GROSSRISIKEN = new Bezeichner("Aufsichtsfreier Versicherungsnehmer (Grossrisiken)");
    public static final Bezeichner GRUPPENART = new Bezeichner("Gruppenart");
    public static final Bezeichner GUELTIGE_AKB = new Bezeichner("Gueltige AKB");
    public static final Bezeichner GUELTIGKEITSDAUER_IN_TAGEN_BEI_KURZZEITKENNZEICHEN = new Bezeichner("Gueltigkeitsdauer in Tagen bei Kurzzeitkennzeichen");
    public static final Bezeichner GUTHABEN_DIVID_ANSAMMLUNG_IN_WAEHRUNGSEINHEIT = new Bezeichner("Guthaben Divid. Ansammlung");
    public static final Bezeichner GUTHABEN_DIVID_ANSAMMLUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Guthaben Divid. Ansammlung");
    public static final Bezeichner GUTHABEN_DIVID_ANSAMMLUNGEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Guthaben Divid. Ansammlungen");

    public static final Bezeichner HAFTUNG_AB = new Bezeichner("Haftung ab");
    public static final Bezeichner HAFTUNG_AB2 = new Bezeichner("Haftung ab");
    public static final Bezeichner HAFTUNG_BIS = new Bezeichner("Haftung bis");
    public static final Bezeichner HAFTUNG_BIS2 = new Bezeichner("Haftung bis");
    public static final Bezeichner HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = new Bezeichner("Haftungswertungssumme");
    public static final Bezeichner HAUPTFAELLIGKEIT = new Bezeichner("Hauptfaelligkeit");
    public static final Bezeichner HEILKOSTEN = new Bezeichner("Heilkosten");
    public static final Bezeichner HEILKOSTEN_BEITRAGSSATZ = new Bezeichner("Heilkosten Beitragssatz");
    public static final Bezeichner HERSTELLERNAME = new Bezeichner("Herstellername");
    public static final Bezeichner HERSTELLER_SCHLUESSEL_NR = new Bezeichner("Hersteller-Schluessel-Nr.");

    public static final Bezeichner IBAN1 = new Bezeichner("IBAN 1");
    public static final Bezeichner IBAN2 = new Bezeichner("IBAN 2");
    public static final Bezeichner INKASSOART = new Bezeichner("Inkassoart");
    public static final Bezeichner INTERNES_ORDNUNGSMERKMAL_DES_VM = new Bezeichner("Internes Ordnungsmerkmal des VM");
    public static final Bezeichner INTRO1 = new Bezeichner("Intro");
    public static final Bezeichner INVALIDITAET = new Bezeichner("Invaliditaet");
    public static final Bezeichner INVALIDITAET_BEITRAGSSATZ = new Bezeichner("Invaliditaet Beitragssatz");
    public static final Bezeichner ISIN_NUMMER = new Bezeichner("ISIN-Nummer");

    public static final Bezeichner JAEHRLICHE_FAHRLEISTUNG = new Bezeichner("Jaehrliche Fahrleistung");
    public static final Bezeichner JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Jahresrente");
    public static final Bezeichner JAHRESRENTE_IN_WAEHRUNGSEINHEITEN2 = new Bezeichner("Jahresrente");
    public static final Bezeichner JAHRESRENTENAENDERUNGS_PROZENTSATZ = new Bezeichner("Jahresrentenaenderungs-Prozentsatz");

    public static final Bezeichner KAPITALERTRAGSSTEUER_BEI_ABLAUF = new Bezeichner("Kapitalertragssteuer bei Ablauf");
    public static final Bezeichner KAPITALERTRAGSSTEUER_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG = new Bezeichner("Kapitalertragssteuer bei Rueckkauf zum Berechnungsstichtag");
    public static final Bezeichner KAPITALERTRAGSTEUERPFLICHT = new Bezeichner("Kapitalertragsteuerpflicht");
    public static final Bezeichner KAPITALZAHLUNGS_SUMME_IN_WAEHRUNGSEINHEIT = new Bezeichner("Kapitalzahlungs-Summe");
    public static final Bezeichner KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Kapitalzahlungssumme");
    public static final Bezeichner KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = new Bezeichner("Kapitalzahlungssumme");
    public static final Bezeichner KARENZZEIT = new Bezeichner("Karenzzeit");
    public static final Bezeichner KASKO_BEGINNJAHR = new Bezeichner("Kasko-Beginnjahr");
    public static final Bezeichner KAUFPREIS = new Bezeichner("Kaufpreis");
    public static final Bezeichner KENNUNG_ERHOEHUNGSSATZ = new Bezeichner("Kennung Erhoehungssatz");
    public static final Bezeichner KENNZEICHEN_FUER_JAHRES_MAXIMIERUNG = new Bezeichner("Kennzeichen fuer Jahres-Maximierung");
    public static final Bezeichner KENNUNG_FUER_ABS_RABATT = new Bezeichner("Kennung fuer ABS-Rabatt");
    public static final Bezeichner KENNUNG_GLIEDERTAXE = new Bezeichner("Kennung Gliedertaxe");
    public static final Bezeichner KENNUNG_PROGRESSIVE_INVALIDITAET = new Bezeichner("Kennung progressive Invaliditaet");
    public static final Bezeichner KENNUNG_UEBERGANGSENTSCHAEDIGUNG = new Bezeichner("Kennung Uebergangsentschaedigung");
    public static final Bezeichner KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION = new Bezeichner("Kennzeichen fuer abweichende Abschlussprovision");
    public static final Bezeichner KENNZEICHEN_ABWEICHENDE_BESTANDSPFLEGEPROVISION = new Bezeichner("Kennzeichen fuer abweichende Bestandspflegeprovision");
    public static final Bezeichner KENNZEICHEN_ABWEICHENDE_FOLGEPROVISION = new Bezeichner("Kennzeichen fuer abweichende Folgeprovision");
    public static final Bezeichner KENNZEICHEN_ABWEICHENDE_VU_NR = new Bezeichner("Kennzeichen zur Erlaeuterung der abweichenden VU-Nr.");
    public static final Bezeichner KENNZEICHEN_FUER_ABWEICHENDE_ABSCHLUSSPROVISION = new Bezeichner("Kennzeichen fuer abweichende Abschlussprovision");
    public static final Bezeichner KENNZEICHEN_FUER_ABWEICHENDE_FOLGEPROVISION = new Bezeichner("Kennzeichen fuer abweichende Folgeprovision");
    public static final Bezeichner KENNZEICHEN_FUER_ABWEICHENDE_PROVISION = new Bezeichner("Kennzeichen fuer abweichende Provision");
    public static final Bezeichner KENNZEICHEN_VERS_STEUER_FREI = new Bezeichner("Kennzeichen Vers.-Steuer frei");
    public static final Bezeichner KENNZEICHEN_VERTRAGSENTSTEHUNG = new Bezeichner("Kennzeichen Vertragsentstehung");
    public static final Bezeichner KFT_ABSCHLAEGE_IN_PROZENT = new Bezeichner("KFT-Abschlaege in %");
    public static final Bezeichner KFT_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFT-Abschlaege");
    public static final Bezeichner KFT_AENDERUNGSDAT = new Bezeichner("KFT-aenderungsdatum", "KftAenderungsdat");
    public static final Bezeichner KFT_AUSSCHLUSS = new Bezeichner("KFT-Ausschluss");
    public static final Bezeichner KFT_BEGINN = new Bezeichner("KFT-Beginn");
    public static final Bezeichner KFT_BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFT-Beitrag");
    public static final Bezeichner KFT_DECKUNGSART = new Bezeichner("KFT-Deckungsart");
    public static final Bezeichner KFT_TARIFGRUPPE = new Bezeichner("KFT-Tarifgruppe");
    public static final Bezeichner KFT_ZUSCHLAEGE_IN_PROZENT = new Bezeichner("KFT-Zuschlaege in %");
    public static final Bezeichner KFT_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFT-Zuschlaege");
    public static final Bezeichner KFV_ABSCHLAEGE_IN_PROZENT = new Bezeichner("KFV-Abschlaege in %");
    public static final Bezeichner KFV_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFV-Abschlaege");
    public static final Bezeichner KFV_AENDERUNGSDAT = new Bezeichner("KFV-aenderungsdatum", "KfvAenderungsdat");
    public static final Bezeichner KFV_AUSSCHLUSS = new Bezeichner("KFV-Ausschluss");
    public static final Bezeichner KFV_BEGINN = new Bezeichner("KFV-Beginn");
    public static final Bezeichner KFV_BEITRAGSSATZ = new Bezeichner("KFV-Beitragssatz");
    public static final Bezeichner KFV_BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFV-Beitrag");
    public static final Bezeichner KFV_DECKUNGSART = new Bezeichner("KFV-Deckungsart");
    public static final Bezeichner KFV_RGJ = new Bezeichner("KFV-RGJ");
    public static final Bezeichner KFV_SCHAEDEN_AUS_RUECKSTUFUNG = new Bezeichner("KFV-Schaeden aus Rueckstufung");
    public static final Bezeichner KFV_SFS_KLASSE = new Bezeichner("KFV-SF/S-Klasse");
    public static final Bezeichner KFV_TARIFGRUPPE = new Bezeichner("KFV-Tarifgruppe");
    public static final Bezeichner KFV_ZUSCHLAEGE_IN_PROZENT = new Bezeichner("KFV-Zuschlaege in %");
    public static final Bezeichner KFV_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFV-Zuschlaege");
    public static final Bezeichner KH_ABSCHLAEGE_IN_PROZENT = new Bezeichner("KH-Abschlaege in %");
    public static final Bezeichner KH_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KH-Abschlaege");
    public static final Bezeichner KH_AENDERUNGSDAT = new Bezeichner("KH-aenderungsdatum", "KhAenderungsdat");
    public static final Bezeichner KH_AUSSCHLUSS = new Bezeichner("KH-Ausschluss");
    public static final Bezeichner KH_BEGINN = new Bezeichner("KH-Beginn");
    public static final Bezeichner KH_BEITRAGSSAETZE = new Bezeichner("KH-Beitragssaetze");
    public static final Bezeichner KH_BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KH-Beitrag");
    public static final Bezeichner KH_DECKUNGSART = new Bezeichner("KH Deckungsart");
    public static final Bezeichner KH_DECKUNGSSUMMEN = new Bezeichner("KH-Deckungssummen");
    public static final Bezeichner KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KH-Deckungssummen");
    public static final Bezeichner KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL1 = new Bezeichner("KH-Deckungssummen Teil 1");
    public static final Bezeichner KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL2 = new Bezeichner("KH-Deckungssummen Teil 2");
    public static final Bezeichner KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL3 = new Bezeichner("KH-Deckungssummen Teil 3");
    public static final Bezeichner KH_DECKUNGSSUMMEN_PERSONENSCHAEDEN = new Bezeichner("KH-Deckungssummen Personenschaeden");
    public static final Bezeichner KH_DECKUNGSSUMMEN_SACHSCHAEDEN = new Bezeichner("KH-Deckungssummen Sachschaeden");
    public static final Bezeichner KH_DECKUNGSSUMMEN_VERMOEGENSCHAEDEN = new Bezeichner("KH-Deckungssummen Vermoegensschaeden");
    public static final Bezeichner KH_RGJ = new Bezeichner("KH-RGJ");
    public static final Bezeichner KH_SCHAEDEN_AUS_RUECKSTUFUNG = new Bezeichner("KH-Schaeden aus Rueckstufung");
    public static final Bezeichner KH_SF_S_KLASSE = new Bezeichner("KH-SF/S-Klasse");
    public static final Bezeichner KH_TARIFGRUPPE = new Bezeichner("KH-Tarifgruppe");
    public static final Bezeichner KH_ZUSCHLAEGE_IN_PROZENT = new Bezeichner("KH-Zuschlaege in %");
    public static final Bezeichner KH_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KH-Zuschlaege");
    public static final Bezeichner KLARTEXT_SICHERUNGSEINRICHTUNG = new Bezeichner("Klartext Sicherungseinrichtung");
    public static final Bezeichner KOLLEKTIV_NR = new Bezeichner("Kollektiv-Nr.");
    public static final Bezeichner KOMMUNIKATIONSNR1 = new Bezeichner("Kommunikationsnummer 1");
    public static final Bezeichner KOMMUNIKATIONSNR2 = new Bezeichner("Kommunikationsnummer 2");
    public static final Bezeichner KOMMUNIKATIONSNR3 = new Bezeichner("Kommunikationsnummer 3");
    public static final Bezeichner KOMMUNIKATIONSNR4 = new Bezeichner("Kommunikationsnummer 4");
    public static final Bezeichner KOMMUNIKATIONSNR5 = new Bezeichner("Kommunikationsnummer 5");
    public static final Bezeichner KOMMUNIKATIONSNR6 = new Bezeichner("Kommunikationsnummer 6");
    public static final Bezeichner KOMMUNIKATIONSNR7 = new Bezeichner("Kommunikationsnummer 7");
    public static final Bezeichner KOMMUNIKATIONSTYP1 = new Bezeichner("Kommunikationstyp 1");
    public static final Bezeichner KOMMUNIKATIONSTYP2 = new Bezeichner("Kommunikationstyp 2");
    public static final Bezeichner KOMMUNIKATIONSTYP3 = new Bezeichner("Kommunikationstyp 3");
    public static final Bezeichner KOMMUNIKATIONSTYP4 = new Bezeichner("Kommunikationstyp 4");
    public static final Bezeichner KOMMUNIKATIONSTYP5 = new Bezeichner("Kommunikationstyp 5");
    public static final Bezeichner KOMMUNIKATIONSTYP6 = new Bezeichner("Kommunikationstyp 6");
    public static final Bezeichner KOMMUNIKATIONSTYP7 = new Bezeichner("Kommunikationstyp 7");
    public static final Bezeichner KONTONR1 = new Bezeichner("Kontonummer 1");
    public static final Bezeichner KONTONR2 = new Bezeichner("Kontonummer 2");
    public static final Bezeichner KOSMETISCHE_OPERATIONEN = new Bezeichner("Kosmetische Operationen");
    public static final Bezeichner KOSMETISCHE_OPERATIONEN_BEITRAGSSATZ = new Bezeichner("Kosmetische Operationen Beitragssatz");
    public static final Bezeichner KRANKENHAUSTAGEGELD = new Bezeichner("Krankenhaustagegeld");
    public static final Bezeichner KRANKENHAUSTAGEGELD_BEITRAGSSATZ = new Bezeichner("Krankenhaustagegeld Beitragssatz");
    public static final Bezeichner KREISGEMEINDESCHLUESSEL = new Bezeichner("Kreisgemeindeschluessel");
    public static final Bezeichner KREISGEMEINDESCHLUESSEL_ZUSATZINFORMATION = new Bezeichner("Kreisgemeindeschluessel Zusatzinformation");
    public static final Bezeichner KUENDIGUNGSKLAUSEL = new Bezeichner("Kuendigungsklausel");
    public static final Bezeichner KUENDIGUNGSKLAUSEL_VP = new Bezeichner("Kuendigungsklausel VP / Personengruppe gestrichen");
    public static final Bezeichner KUNDENGRUPPE = new Bezeichner("Kundengruppe");
    public static final Bezeichner KUNDENNR_VERMITTLER = new Bezeichner("Personen-/Kundennummer des Vermittlers");
    public static final Bezeichner KUNDENNR_VERSICHERER = new Bezeichner("Personen-/Kundennummer des Versicherers");
    public static final Bezeichner KURKOSTEN = new Bezeichner("Kurkosten");
    public static final Bezeichner KURKOSTEN_BEITRAGSSATZ = new Bezeichner("Kurkosten Beitragssatz");

    public static final Bezeichner LAENDERKENNZEICHEN = new Bezeichner("Laenderkennzeichen");
    public static final Bezeichner LAENDERKENNZEICHEN_DER_RISIKOANSCHRIFT = new Bezeichner("Laenderkennzeichen der Risikoanschrift");
    public static final Bezeichner LAND_DES_AMTL_KENNZEICHENS = new Bezeichner("Land des amtl. Kennzeichens");
    public static final Bezeichner LAUFZEITRABATT_IN_PROZENT = new Bezeichner("Laufzeitrabatt in %");
    public static final Bezeichner LAUFZEITVERKUERZUNG = new Bezeichner("Laufzeitverkuerzung");
    public static final Bezeichner LEBENSLANGE_BEITRAGSZAHLUNG = new Bezeichner("Lebenslange Beitragszahlung");
    public static final Bezeichner LEERSTELLEN = new Bezeichner("Leerstellen");
    public static final Bezeichner LEERSTELLEN1 = new Bezeichner("Leerstellen");
    public static final Bezeichner LEERSTELLEN2 = new Bezeichner("Leerstellen");
    public static final Bezeichner LEERSTELLEN3 = new Bezeichner("Leerstellen");
    public static final Bezeichner LEERSTELLEN4 = new Bezeichner("Leerstellen");
    public static final Bezeichner LEERSTELLEN5 = new Bezeichner("Leerstellen");
    public static final Bezeichner LEISTUNG = new Bezeichner("Leistung");
    public static final Bezeichner LEISTUNG_AB_INVALIDITAETSGRAD_IN_PROZENT = new Bezeichner("Leistung ab Invaliditaetsgrad in Prozent");
    public static final Bezeichner LEISTUNGSBEGINN_AB = new Bezeichner("Leistungsbeginn ab");
    public static final Bezeichner LEISTUNGSDAUER = new Bezeichner("Leistungsdauer");
    public static final Bezeichner LEISTUNGSZAHLUNGSWEISE = new Bezeichner("Leistungszahlungsweise");
    public static final Bezeichner LETZTE_ERHOEHUNG = new Bezeichner("letzte Erhoehung");
    public static final Bezeichner LETZTE_ERHOEHUNG_8_III_AHB = new Bezeichner("Letzte Erhoehung \u00a78, III AHB");
    public static final Bezeichner LFD_NUMMER = new Bezeichner("Lfd. Nummer");
    public static final Bezeichner LFD_NUMMER1 = new Bezeichner("Lfd. Nummer");
    public static final Bezeichner LFD_NUMMER2 = new Bezeichner("Lfd. Nummer");
    public static final Bezeichner LFD_NUMMER_DES_FONDS = new Bezeichner("Lfd. Nummer des Fonds");
    public static final Bezeichner LFD_NUMMER_SATZART = new Bezeichner("Lfd. Nummer der Satzart");
    public static final Bezeichner LFD_NUMMER_VP = new Bezeichner("Lfd. Nummer der versicherten Person (VP)");
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE = new Bezeichner("Lfd. Nummer der versicherten Person (VP) / Personengruppe");
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE1 = new Bezeichner("Lfd. Nummer der versicherten Person (VP) / Personengruppe");
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE2 = new Bezeichner("Lfd. Nummer der versicherten Person (VP) / Personengruppe");
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE3 = new Bezeichner("Lfd. Nummer der versicherten Person (VP) / Personengruppe");
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE4 = new Bezeichner("Lfd. Nummer der versicherten Person (VP) / Personengruppe");
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE9 = new Bezeichner("Lfd. Nummer der versicherten Person (VP) / Personengruppe");
    public static final Bezeichner LFD_NUMMER_ZUR_ART_DER_LEISTUNG = new Bezeichner("Laufende Nummer zur Art der Leistung");
    public static final Bezeichner LFD_NUMMER_ZUR_WAGNISART = new Bezeichner("Lfd Nummer zur Wagnisart");
    public static final Bezeichner LFD_NUMMER_ZUR_WAGNISART2 = new Bezeichner("Lfd. Nummer Wagnisart");
    public static final Bezeichner LFD_NUMMER_ZUR_WAGNISART3 = new Bezeichner("Lfd. Numemr Wagnisart");
    public static final Bezeichner LFD_NUMMER_ZUR_WAGNISART4 = new Bezeichner("Lfd. Nummer zur Wagnisart");
    public static final Bezeichner LFD_PERSONEN_NR_IM_GEVO = new Bezeichner ("Lfd. Personennummer im GeVo", "LfdPersonenNrImGevo");
    public static final Bezeichner LFD_PERSONENNR_GEVO = new Bezeichner("Lfd. Personennummer im GeVo");
    public static final Bezeichner LFD_PERSONENNUMMER_DES_SICHERUNGSGLAEUBIGERS = new Bezeichner("Lfd. Personennummer des Sicherungsglaeubigers");

    public static final Bezeichner NAECHSTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Erlebensfall VS");

    /** Die Konstante POLICIERUNGSDAT. */
    public static final Bezeichner POLICIERUNGSDAT = new Bezeichner("Policierungsdatum", "Policierungsdat");

    /** The Konstante ROLLE_W_AKZ. */
    public static final Bezeichner ROLLE_W_AKZ = new Bezeichner("Rolle W-AKZ", "RolleWAKZ");

    /** The Konstante RUECKGEWAEHRDAT. */
    public static final Bezeichner RUECKGEWAEHRDAT = new Bezeichner("Rueckgewaehrdatum", "Rueckgewaehrdat");

    public static final Bezeichner SEPA_GLAEUBIGERIDENTIFIKATIONSNUMMER = new Bezeichner("SEPA-Glaeubigeridentifikationsnummer", "SEPAGlaeubigeridentifikationsnummer");
    public static final Bezeichner SEPA_MANDAT_AUSSTELLUNGSDATUM = new Bezeichner("SEPA-Mandat Ausstellungsdatum", "SEPAMandatAusstellungsdatum");
    public static final Bezeichner SEPA_MANDAT_KONTOREFERENZ = new Bezeichner("SEPA-Mandat Kontoreferenz", "SEPAMandatKontoreferenz");
    public static final Bezeichner SPARTE2 = new Bezeichner("Sparte2", "Sparte2");
    public static final Bezeichner SPARVORGANG = new Bezeichner("Sparvorgang");

    public static final Bezeichner TODESFALL_VS_IN_WAEHRUNGSEINHEIT = new Bezeichner("Todesfall VS");
    public static final Bezeichner TODESFALL_VS_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = new Bezeichner("Todesfall VS zum Ablauf");
    public static final Bezeichner TODESFALL_VS_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = new Bezeichner("Todesfall VS zum Beginn der Abrufphase");
    public static final Bezeichner TODESFALLLEISTUNG_IN_PROZENT = new Bezeichner("Todesfallleistung in %");

    /** The Constant UMSTELLUNGSDAT_FOLGETARIFS. */
    public static final Bezeichner UMSTELLUNGSDAT_FOLGETARIFS = new Bezeichner("Umstellungsdatum des Folgetarifs", "UmstellungsdatFolgetarifs");

    /** Die Konstante URSPRUENGLICHES_HAFTUNGSBEGINNDAT. */
    public static final Bezeichner URSPRUENGLICHES_HAFTUNGSBEGINNDAT = new Bezeichner(
            "Urspruengliches Haftungsbeginndatum", "UerspruenglichesHaftungsbeginndat");

    public static final Bezeichner VERSION_SATZART_0210_TECH_VERS = new Bezeichner("Version Satzart 0210 Technische Versicherungen");
    public static final Bezeichner VERSION_SATZART_0211_TECH_VERS = new Bezeichner("Version Satzart 0211 Technische Versicherungen");
    public static final Bezeichner VERSION_SATZART_0220_TECH_VERS = new Bezeichner("Version Satzart 0220 Technische Versicherungen");
    public static final Bezeichner VERSION_SATZART_0221_TECH_VERS = new Bezeichner("Version Satzart 0221 Technische Versicherungen");
    public static final Bezeichner VERSION_SATZART_0222 = new Bezeichner("Version Satzart 0222");
    public static final Bezeichner VERTRAG_MIT_ZUWACHSGARANTIE = new Bezeichner("Vertrag mit Zuwachsgarantie");
    public static final Bezeichner VERTRAGSVERBINDUNGSNUMMER = new Bezeichner("Vertragsverbindungs-Nummer");

    /** Die Konstante VS_NR. */
    public static final Bezeichner VS_NR = new Bezeichner("Versicherungsschein-Nummer", "VsNr");

    /** Die Konstante VU_NR. */
    public static final Bezeichner VU_NR = new Bezeichner("VU-Nummer", "VuNr");

    /** The Constant WECHSELKENNZEICHEN_W_AKZ. */
    public static final Bezeichner WECHSELKENNZEICHEN_W_AKZ = new Bezeichner("KFZ - Wechselkennzeichen W-AKZ",
            "KFZWechselkennzeichenWAKZ");

    /** Die Konstante ZUZAHLUNGSBETRAG_IN_WE. */
    public static final Bezeichner ZUZAHLUNGSBETRAG_IN_WE = new Bezeichner("Zuzahlungsbetrag in Waehrungseinheiten",
            "ZuzahlungsbetragInWE");

    /** Die Konstante ZUZAHLUNGSDAT. */
    public static final Bezeichner ZUZAHLUNGSDAT = new Bezeichner("Zuzahlungsdatum", "Zuzahlungsdat");

    /////////// Konstanten mit NAME_-Prefix ///////////////////////////////////

    public static final String NAME_ABGANGSGRUND = "Abgangsgrund";
    public static final String NAME_ABSCHLAG1_IN_PROZENT = "Abschlag-1 in %";
    public static final String NAME_ABSCHLAG2_IN_PROZENT = "Abschlag-2 in %";
    public static final String NAME_ABSCHLAG2_IN_WAEHRUNGSEINHEITEN = "Abschlag-2";
    public static final String NAME_ABSCHLAG3_IN_PROZENT = "Abschlag-3 in %";
    public static final String NAME_ABSCHLAG3_IN_WAEHRUNGSEINHEITEN = "Abschlag-3";
    public static final String NAME_ABSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN = "Abschlagsbetrag";
    public static final String NAME_ABSCHLAG_IN_PROZENT = "Abschlag in %";
    public static final String NAME_ABSCHLUSSPROVISION = "Abschlussprovision";
    public static final String NAME_ABSENDER = "Absender";
    public static final String NAME_ABWEICHENDER_KONTOINHABER1 = "Abweichender Kontoinhaber 1";
    public static final String NAME_ABWEICHENDER_KONTOINHABER2 = "Abweichender Kontoinhaber 2";
    public static final String NAME_ABWEICHENDE_VU_NR = "Abweichende VU-Nr.";
    public static final String NAME_ADRESSAT = "Adressat";
    public static final String NAME_ADRESSKENNZEICHEN = "Adresskennzeichen";
    public static final String NAME_AENDERUNGSGRUND = "Aenderungsgrund";
    public static final String NAME_AFB = "A,F,B";
    public static final String NAME_AKTENZEICHEN_SICHERUNGSGLAEUBIGER = "Aktenzeichen des Sicherungsglaeubigers";
    public static final String NAME_AKTUELLE_BEITRAGSDEPOTSUMME_IN_WAEHRUNGSEINHEITEN = "aktuelle Beitragsdepotsumme";
    public static final String NAME_ALLGEMEINE_VERSICHERUNGSBEDINGUNGEN = "Allgemeine Versicherungsbedingungen";
    public static final String NAME_AMTL_KENNZEICHEN = "Amtl. Kennzeichen";
    public static final String NAME_ANREDESCHLUESSEL = "Anredeschluessel";
    public static final String NAME_ANTEIL_IN_PROZENT = "Anteil in %";
    public static final String NAME_ANZAHL_DER_VORBESITZER = "Anzahl der Vorbesitzer";
    public static final String NAME_ANZAHL_SAETZE = "Anzahl der Saetze";
    public static final String NAME_ANZAHL_VP_PRO_PERSONENGRUPPE = "Anzahl der VP pro Personengruppe";
    public static final String NAME_ANZAHL_WOHNEINHEITEN = "Anzahl Wohnheiten";
    public static final String NAME_ARB = "ARB (Allgemeine Bedingungen fuer die Rechtschutzvers.)";
    public static final String NAME_ART_DER_HEILKOSTEN = "Art der Heilkosten";
    public static final String NAME_ART_DER_STEUERLICHEN_FOERDERUNG = "Art der steuerlichen Foerderung";
    public static final String NAME_ART_DER_ZULASSUNG_BEIM_VORBESITZER = "Art der Zulassung beim Vorbesitzer";
    public static final String NAME_ART_DES_ABSENDERS = "Art des Absenders";
    public static final String NAME_ART_DES_ADRESSATEN = "Art des Adressaten";
    public static final String NAME_ART_DES_AMTLICHEN_KENNZEICHENS = "Art des amtlichen Kennzeichens";
    public static final String NAME_ART_DES_BERUFSSCHLUESSELVERZEICHNISSES = "Art des Berufsschluesselverzeichnisses";
    public static final String NAME_ART_DES_DRITTRECHTS = "Art des Drittrechts";
    public static final String NAME_AUFBAUART = "Aufbauart";
    public static final String NAME_AUFSICHTSFREIER_VERTRAG = "Aufsichtsfreier Vertrag";
    public static final String NAME_AUFTEILUNG_VERSICHERUNGSSTEUER = "Aufteilung Versicherungsteuer gemaess EU-Richtlinien";
    public static final String NAME_AUFTRAGSNR_VERMITTLER = "Auftrags-Nr. des Vermittlers";
    public static final String NAME_AUSSCHLUSS = "Ausschluss";
    public static final String NAME_BAUJAHR = "Baujahr";
    public static final String NAME_BAUSTEIN_GESAMTBEITRAG_1_IN_WAEHRUNGSEINHEITEN = "Baustein-Gesamtbeitrag 1";
    public static final String NAME_BAUSTEIN_GESAMTBEITRAG_2_IN_WAEHRUNGSEINHEITEN = "Baustein-Gesamtbeitrag 2";
    public static final String NAME_BEGINN = "Beginn";
    public static final String NAME_BEGINN_TAGEGELD1_AB_TAG = "Beginn Tagegeld 1 ab Tag";
    public static final String NAME_BEGINN_TAGEGELD2_AB_TAG = "Beginn Tagegeld 2 ab Tag";
    public static final String NAME_BEGINN_VERSICHERUNGSSCHUTZ = "Beginn Versicherungsschutz";
    public static final String NAME_BEITRAGSANGLEICHUNGSKLAUSEL = "Beitragsangleichungsklausel";
    public static final String NAME_BEITRAGSDEPOT = "Beitragsdepot";
    public static final String NAME_BEITRAGSKLASSE = "Beitragsklasse";
    public static final String NAME_BEITRAGSREGULIERUNG = "Beitragsregulierung";
    public static final String NAME_BEITRAGSRUECKGEWAEHR = "Beitragsrueckgewaehr";
    public static final String NAME_BEITRAGSUMSTELLUNGSGRUND = "Beitragsumstellungsgrund";
    public static final String NAME_BEITRAG_BERGUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Bergungskosten";
    public static final String NAME_BEITRAG_FESTE_RENTE_IN_WAEHRUNGSEINHEITEN = "Beitrag Feste Rente";
    public static final String NAME_BEITRAG_GENESUNGSGELD_IN_WAEHRUNGSEINHEITEN = "Beitrag Genesungsgeld";
    public static final String NAME_BEITRAG_HEILKOSTEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Heilkosten";
    public static final String NAME_BEITRAG_INVALIDITAET_IN_WAEHRUNGSEINHEITEN = "Beitrag Invaliditaet";
    public static final String NAME_BEITRAG_IN_WAEHRUNGSEINHEITEN = "Beitrag";
    public static final String NAME_BEITRAG_KOSMETISCHE_OPERATION_IN_WAEHRUNGSEINHEITEN = "Beitrag Kosmetische Operation";
    public static final String NAME_BEITRAG_KRANKENHAUSTAGEGELD_IN_WAEHRUNGSEINHEITEN = "Beitrag Krankenhaustagegeld";
    public static final String NAME_BEITRAG_KURKOSTEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Kurkosten";
    public static final String NAME_BEITRAG_PRO_VP_IN_WAEHRUNGSEINHEITEN = "Beitrag pro VP oder pro Personengruppe";
    public static final String NAME_BEITRAG_SERVICELEISTUNGEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Serviceleistungen";
    public static final String NAME_BEITRAG_TAGEGELD1_IN_WAEHRUNGSEINHEITEN = "Beitrag Tagegeld 1";
    public static final String NAME_BEITRAG_TAGEGELD2_IN_WAEHRUNGSEINHEITEN = "Beitrag Tagegeld 2";
    public static final String NAME_BEITRAG_TOD_IN_WAEHRUNGSEINHEITEN = "Beitrag Tod";
    public static final String NAME_BEITRAG_UEBERGANGSENTSCHAEDIGUNG_IN_WAEHRUNGSEINHEITEN = "Beitrag Uebergangsentschaedigung";
    public static final String NAME_BERGUNGSKOSTEN = "Bergungskosten";
    public static final String NAME_BERGUNGSKOSTEN_BEITRAGSSATZ = "Bergungskosten Beitragssatz";
    public static final String NAME_BERUFSGRUPPENEINTEILUNG = "Berufsgruppeneinteilung im Industrie-Straf-RS";
    public static final String NAME_BERUFSSCHLUESSEL = "Berufsschluessel";
    public static final String NAME_BERUF_TEXT = "Beruf-Text";
    public static final String NAME_BESONDERER_VERWENDUNGSZWECK = "besonderer Verwendungszweck";
    public static final String NAME_BESONDERE_VEREINBARUNG_ZUM_FLUGGASTRISIKO = "Besondere Vereinbarung zum Fluggastrisiko";
    public static final String NAME_BESTANDSFUEHRENDE_GESCHAEFTSSTELLE = "Bestandsfuehrende Geschaeftsstelle";
    public static final String NAME_BESTANDSPFLEGEPROVISION = "Bestandspflegeprovision";
    public static final String NAME_BETRIEBLICHE_ALTERSVORSORGE = "Betriebliche Altersvorsorge";
    public static final String NAME_BEZEICHNUNG_PERSONENGRUPPE = "Bezeichnung Personengruppe";
    public static final String NAME_BEZUGSBERECHTIGT_IM_LEISTUNGSFALL = "Bezugsberechtigt im Leistungsfall";
    public static final String NAME_BEZUGSRECHTANTEIL_IM_LEISTUNGSFALL = "Bezugsrechtanteil im Leistungsfall";
    public static final String NAME_BIC1 = "BIC 1";
    public static final String NAME_BIC2 = "BIC 2";
    public static final String NAME_BILANZMONAT_ARBEITGEBER = "Bilanzmonat Arbeitgeber";
    public static final String NAME_BLZ1 = "Bankleitzahl 1";
    public static final String NAME_BLZ2 = "Bankleitzahl 2";
    public static final String NAME_BUENDELUNGSKENNZEICHEN = "Buendelungskennzeichen";
    public static final String NAME_DECKUNGSSUMME_IN_TSD_WAEHRUNGSEINHEITEN = "Deckungssumme in Tausend Waehrungseinheiten";
    public static final String NAME_DECKUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Deckungssumme";
    public static final String NAME_DECKUNGSUMFANG = "Deckungsumfang";
    public static final String NAME_DIENSTEINTRITTSDATUM = "Diensteintrittsdatum";
    public static final String NAME_DRUCKAUFBEREITETE_VERSICHERUNGSSCHEINNUMMER = "Druckaufbereitete Versicherungsscheinnummer";
    public static final String NAME_DURCHFUEHRUNGSWEG = "Durchfuehrungsweg";
    public static final String NAME_DYNAMIK = "Dynamik";
    public static final String NAME_DYNAMIK_IN_PROZENT = "Dynamik in %";
    public static final String NAME_EIGENTUMSVERHAELTNIS_FAHRZEUG = "Eigentumsverhaeltnis (Fahrzeug)";
    public static final String NAME_EINSCHLUSSDATUM_VP = "Einschlussdatum VP / Personengruppe";
    public static final String NAME_EINZAHLUNG_AUSSCHUETTUNG = "Einzahlung / Ausschuettung";
    public static final String NAME_ENDEDATUM_DES_VERSICHERUNGSSCHUTZES_BEI_ROTEN_KENNZEICHEN = "Endedatum des Versicherungsschutzes bei roten Kennzeichen";
    public static final String NAME_ERHOEHUNGSART_DYNAMIK = "Erhoehungsart Dynamik";
    public static final String NAME_ERSTELLUNGSDATUM_ZEITRAUM_BIS = "Erstellungs-Datum-Zeitraum bis";
    public static final String NAME_ERSTELLUNGSDATUM_ZEITRAUM_VOM = "Erstellungs-Datum-Zeitraum vom";
    public static final String NAME_ERSTE_ZULASSUNG_AUF_DEN_VN = "Erste Zulassung auf den VN";
    public static final String NAME_ERSTZULASSUNG = "Erstzulassung";
    public static final String NAME_ERWEITERUNGSSATZ_VORHANDEN = "Erweiterungssatz vorhanden";
    public static final String NAME_EVB_NUMMER = "eVB-Nummer";
    public static final String NAME_FAHRZEUGART = "Fahrzeugart";
    public static final String NAME_FAHRZEUGIDENTIFIZIERUNGSNUMMER = "Fahrzeugidentifizierungsnummer";
    public static final String NAME_FAMILIENSTAND = "Familienstand";
    public static final String NAME_FESTE_RENTE = "Feste Rente";
    public static final String NAME_FESTE_RENTE_BEITRAGSSATZ = "Feste Rente Beitragssatz";
    public static final String NAME_FINANZIERUNGSART = "Finanzierungsart";
    public static final String NAME_FINANZIERUNG_ZUSAGE = "Finanzierung der Zusage";
    public static final String NAME_FLOTTENKENNZEICHEN = "Flottenkennzeichen";
    public static final String NAME_FLOTTENRABATT_IN_PROZENT = "Flottenrabatt in %";
    public static final String NAME_FOLGENUMMER = "Folgenummer";
    public static final String NAME_FOLGEPROVISION = "Folgeprovision";
    public static final String NAME_FREMDER_GRUND_UND_BODEN = "fremder Grund und Boden";
    public static final String NAME_FREMDNUTZUNG = "Fremdnutzung";
    public static final String NAME_FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_KH = "Frei vereinbarte Selbstbeteiligung fuer KH";
    public static final String NAME_FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO = "Frei vereinbarte Selbstbeteiligung fuer Teilkasko";
    public static final String NAME_FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO_IM_RAHMEN_DER_VOLLKASKO = "Frei vereinbarte Selbstbeteiligung fuer Teilkasko im Rahmen der Vollkasko";
    public static final String NAME_FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_VOLLKASKO = "Frei vereinbarte Selbstbeteiligung fuer Vollkasko";
    public static final String NAME_GAP_DECKUNG = "GAP-Deckung";
    public static final String NAME_GARAGE = "Garage";
    public static final String NAME_GEBURTSDATUM = "Geburtsdatum";
    public static final String NAME_GEBURTSLAND = "Geburtsland";
    public static final String NAME_GEBURTSNAME = "Geburtsname";
    public static final String NAME_GEBURTSORT = "Geburtsort";
    public static final String NAME_GEFAHRENGRUPPE = "Gefahrengruppe";
    public static final String NAME_GEFAHRGUT = "Gefahrgut";
    public static final String NAME_GELTUNGSBEREICH = "Geltungsbereich";
    public static final String NAME_GELTUNGSBEREICHEINSCHRAENKUNG = "Geltungsbereicheinschraenkung";
    public static final String NAME_GENESUNGSGELD = "Genesungsgeld";
    public static final String NAME_GENESUNGSGELD_BEITRAGSSATZ = "Genesungsgeld Beitragssatz";
    public static final String NAME_GESAMTBEITRAG = "Gesamtbeitrag";
    public static final String NAME_GESAMTBEITRAG_BRUTTO = "Gesamtbeitrag-Brutto(Inkasso)";
    public static final String NAME_GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN = "Gesamtbeitrag (Brutto)";
    public static final String NAME_GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN = "Gesamtbeitrag";
    public static final String NAME_GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN = "Gesamtbeitrag (Netto)";
    public static final String NAME_GESAMTMASSE = "Gesamtmasse";
    public static final String NAME_GESAMTPROVISIONSBETRAG = "Gesamtprovisions-Betrag";
    public static final String NAME_GESCHLECHT = "Geschlecht";
    public static final String NAME_GROSSRISIKEN = "Aufsichtsfreier Versicherungsnehmer (Grossrisiken)";
    public static final String NAME_GRUPPENART = "Gruppenart";
    public static final String NAME_GUELTIGE_AKB = "Gueltige AKB";
    public static final String NAME_GUELTIGKEITSDAUER_IN_TAGEN_BEI_KURZZEITKENNZEICHEN = "Gueltigkeitsdauer in Tagen bei Kurzzeitkennzeichen";
    public static final String NAME_HAUPTFAELLIGKEIT = "Hauptfaelligkeit";
    public static final String NAME_HEILKOSTEN = "Heilkosten";
    public static final String NAME_HEILKOSTEN_BEITRAGSSATZ = "Heilkosten Beitragssatz";
    public static final String NAME_HERSTELLERNAME = "Herstellername";
    public static final String NAME_HERSTELLER_SCHLUESSEL_NR = "Hersteller-Schluessel-Nr.";
    public static final String NAME_IBAN1 = "IBAN 1";
    public static final String NAME_IBAN2 = "IBAN 2";
    public static final String NAME_INKASSOART = "Inkassoart";
    public static final String NAME_INVALIDITAET = "Invaliditaet";
    public static final String NAME_INVALIDITAET_BEITRAGSSATZ = "Invaliditaet Beitragssatz";
    public static final String NAME_INTERNES_ORDNUNGSMERKMAL_DES_VM = "Internes Ordnungsmerkmal des VM";
    public static final String NAME_JAEHRLICHE_FAHRLEISTUNG = "Jaehrliche Fahrleistung";
    public static final String NAME_KAPITALERTRAGSTEUERPFLICHT = "Kapitalertragsteuerpflicht";
    public static final String NAME_KASKO_BEGINNJAHR = "Kasko-Beginnjahr";
    public static final String NAME_KAUFPREIS = "Kaufpreis";
    public static final String NAME_KENNUNG_FUER_ABS_RABATT = "Kennung fuer ABS-Rabatt";
    public static final String NAME_KENNUNG_GLIEDERTAXE = "Kennung Gliedertaxe";
    public static final String NAME_KENNUNG_PROGRESSIVE_INVALIDITAET = "Kennung progressive Invaliditaet";
    public static final String NAME_KENNUNG_UEBERGANGSENTSCHAEDIGUNG = "Kennung Uebergangsentschaedigung";
    public static final String NAME_KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION = "Kennzeichen fuer abweichende Abschlussprovision";
    public static final String NAME_KENNZEICHEN_ABWEICHENDE_BESTANDSPFLEGEPROVISION = "Kennzeichen fuer abweichende Bestandspflegeprovision";
    public static final String NAME_KENNZEICHEN_ABWEICHENDE_FOLGEPROVISION = "Kennzeichen fuer abweichende Folgeprovision";
    public static final String NAME_KENNZEICHEN_ABWEICHENDE_VU_NR = "Kennzeichen zur Erlaeuterung der abweichenden VU-Nr.";
    public static final String NAME_KENNZEICHEN_FUER_ABWEICHENDE_PROVISION = "Kennzeichen fuer abweichende Provision";
    public static final String NAME_KENNZEICHEN_VERS_STEUER_FREI = "Kennzeichen Vers.-Steuer frei";
    public static final String NAME_KENNZEICHEN_VERTRAGSENTSTEHUNG = "Kennzeichen Vertragsentstehung";
    public static final String NAME_KFT_ABSCHLAEGE_IN_PROZENT = "KFT-Abschlaege in %";
    public static final String NAME_KFT_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFT-Abschlaege";
    public static final String NAME_KFT_AENDERUNGSDATUM = "KFT-aenderungsdatum";
    public static final String NAME_KFT_AUSSCHLUSS = "KFT-Ausschluss";
    public static final String NAME_KFT_BEGINN = "KFT-Beginn";
    public static final String NAME_KFT_BEITRAG_IN_WAEHRUNGSEINHEITEN = "KFT-Beitrag";
    public static final String NAME_KFT_DECKUNGSART = "KFT-Deckungsart";
    public static final String NAME_KFT_TARIFGRUPPE = "KFT-Tarifgruppe";
    public static final String NAME_KFT_ZUSCHLAEGE_IN_PROZENT = "KFT-Zuschlaege in %";
    public static final String NAME_KFT_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFT-Zuschlaege";
    public static final String NAME_KFV_ABSCHLAEGE_IN_PROZENT = "KFV-Abschlaege in %";
    public static final String NAME_KFV_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFV-Abschlaege";
    public static final String NAME_KFV_AENDERUNGSDATUM = "KFV-aenderungsdatum";
    public static final String NAME_KFV_AUSSCHLUSS = "KFV-Ausschluss";
    public static final String NAME_KFV_BEGINN = "KFV-Beginn";
    public static final String NAME_KFV_BEITRAGSSATZ = "KFV-Beitragssatz";
    public static final String NAME_KFV_BEITRAG_IN_WAEHRUNGSEINHEITEN = "KFV-Beitrag";
    public static final String NAME_KFV_DECKUNGSART = "KFV-Deckungsart";
    public static final String NAME_KFV_RGJ = "KFV-RGJ";
    public static final String NAME_KFV_SCHAEDEN_AUS_RUECKSTUFUNG = "KFV-Schaeden aus Rueckstufung";
    public static final String NAME_KFV_SFS_KLASSE = "KFV-SF/S-Klasse";
    public static final String NAME_KFV_TARIFGRUPPE = "KFV-Tarifgruppe";
    public static final String NAME_KFV_ZUSCHLAEGE_IN_PROZENT = "KFV-Zuschlaege in %";
    public static final String NAME_KFV_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFV-Zuschlaege";
    public static final String NAME_KH_ABSCHLAEGE_IN_PROZENT = "KH-Abschlaege in %";
    public static final String NAME_KH_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KH-Abschlaege";
    public static final String NAME_KH_AENDERUNGSDATUM = "KH-aenderungsdatum";
    public static final String NAME_KH_AUSSCHLUSS = "KH-Ausschluss";
    public static final String NAME_KH_BEGINN = "KH-Beginn";
    public static final String NAME_KH_BEITRAGSSAETZE = "KH-Beitragssaetze";
    public static final String NAME_KH_BEITRAG_IN_WAEHRUNGSEINHEITEN = "KH-Beitrag";
    public static final String NAME_KH_DECKUNGSART = "KH Deckungsart";
    public static final String NAME_KH_DECKUNGSSUMMEN = "KH-Deckungssummen";
    public static final String NAME_KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN = "KH-Deckungssummen";
    public static final String NAME_KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL1 = "KH-Deckungssummen Teil 1";
    public static final String NAME_KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL2 = "KH-Deckungssummen Teil 2";
    public static final String NAME_KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL3 = "KH-Deckungssummen Teil 3";
    public static final String NAME_KH_DECKUNGSSUMMEN_PERSONENSCHAEDEN = "KH-Deckungssummen Personenschaeden";
    public static final String NAME_KH_DECKUNGSSUMMEN_SACHSCHAEDEN = "KH-Deckungssummen Sachschaeden";
    public static final String NAME_KH_DECKUNGSSUMMEN_VERMOEGENSCHAEDEN = "KH-Deckungssummen Vermoegensschaeden";
    public static final String NAME_KH_RGJ = "KH-RGJ";
    public static final String NAME_KH_SCHAEDEN_AUS_RUECKSTUFUNG = "KH-Schaeden aus Rueckstufung";
    public static final String NAME_KH_SF_S_KLASSE = "KH-SF/S-Klasse";
    public static final String NAME_KH_TARIFGRUPPE = "KH-Tarifgruppe";
    public static final String NAME_KH_ZUSCHLAEGE_IN_PROZENT = "KH-Zuschlaege in %";
    public static final String NAME_KH_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KH-Zuschlaege";
    public static final String NAME_KLARTEXT_SICHERUNGSEINRICHTUNG = "Klartext Sicherungseinrichtung";
    public static final String NAME_KOLLEKTIV_NR = "Kollektiv-Nr.";
    public static final String NAME_KOMMUNIKATIONSNR1 = "Kommunikationsnummer 1";
    public static final String NAME_KOMMUNIKATIONSNR2 = "Kommunikationsnummer 2";
    public static final String NAME_KOMMUNIKATIONSNR3 = "Kommunikationsnummer 3";
    public static final String NAME_KOMMUNIKATIONSNR4 = "Kommunikationsnummer 4";
    public static final String NAME_KOMMUNIKATIONSNR5 = "Kommunikationsnummer 5";
    public static final String NAME_KOMMUNIKATIONSNR6 = "Kommunikationsnummer 6";
    public static final String NAME_KOMMUNIKATIONSNR7 = "Kommunikationsnummer 7";
    public static final String NAME_KOMMUNIKATIONSTYP1 = "Kommunikationstyp 1";
    public static final String NAME_KOMMUNIKATIONSTYP2 = "Kommunikationstyp 2";
    public static final String NAME_KOMMUNIKATIONSTYP3 = "Kommunikationstyp 3";
    public static final String NAME_KOMMUNIKATIONSTYP4 = "Kommunikationstyp 4";
    public static final String NAME_KOMMUNIKATIONSTYP5 = "Kommunikationstyp 5";
    public static final String NAME_KOMMUNIKATIONSTYP6 = "Kommunikationstyp 6";
    public static final String NAME_KOMMUNIKATIONSTYP7 = "Kommunikationstyp 7";
    public static final String NAME_KONTONR1 = "Kontonummer 1";
    public static final String NAME_KONTONR2 = "Kontonummer 2";
    public static final String NAME_KOSMETISCHE_OPERATIONEN = "Kosmetische Operationen";
    public static final String NAME_KOSMETISCHE_OPERATIONEN_BEITRAGSSATZ = "Kosmetische Operationen Beitragssatz";
    public static final String NAME_KRANKENHAUSTAGEGELD = "Krankenhaustagegeld";
    public static final String NAME_KRANKENHAUSTAGEGELD_BEITRAGSSATZ = "Krankenhaustagegeld Beitragssatz";
    public static final String NAME_KREISGEMEINDESCHLUESSEL = "Kreisgemeindeschluessel";
    public static final String NAME_KREISGEMEINDESCHLUESSEL_ZUSATZINFORMATION = "Kreisgemeindeschluessel Zusatzinformation";
    public static final String NAME_KUENDIGUNGSKLAUSEL = "Kuendigungsklausel";
    public static final String NAME_KUENDIGUNGSKLAUSEL_VP = "Kuendigungsklausel VP / Personengruppe gestrichen";
    public static final String NAME_KUNDENGRUPPE = "Kundengruppe";
    public static final String NAME_KUNDENNR_VERMITTLER = "Personen-/Kundennummer des Vermittlers";
    public static final String NAME_KUNDENNR_VERSICHERER = "Personen-/Kundennummer des Versicherers";
    public static final String NAME_KURKOSTEN = "Kurkosten";
    public static final String NAME_KURKOSTEN_BEITRAGSSATZ = "Kurkosten Beitragssatz";
    public static final String NAME_LAENDERKENNZEICHEN = "Laenderkennzeichen";
    public static final String NAME_LAND_DES_AMTL_KENNZEICHENS = "Land des amtl. Kennzeichens";
    public static final String NAME_LAUFZEITRABATT_IN_PROZENT = "Laufzeitrabatt in %";
    public static final String NAME_LEERSTELLEN = "Leerstellen";
    public static final String NAME_LEERSTELLEN1 = "Leerstellen";
    public static final String NAME_LEERSTELLEN2 = "Leerstellen";
    public static final String NAME_LEERSTELLEN3 = "Leerstellen";
    public static final String NAME_LEERSTELLEN4 = "Leerstellen";
    public static final String NAME_LEERSTELLEN5 = "Leerstellen";
    public static final String NAME_LETZTE_ERHOEHUNG = "letzte Erhoehung";
    public static final String NAME_LFD_NUMMER = "Lfd. Nummer";
    public static final String NAME_LFD_NUMMER1 = "Lfd. Nummer";
    public static final String NAME_LFD_NUMMER2 = "Lfd. Nummer";
    public static final String NAME_LFD_NUMMER_SATZART = "Lfd. Nummer der Satzart";
    public static final String NAME_LFD_NUMMER_VP = "Lfd. Nummer der versicherten Person (VP)";
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE1 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE2 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE3 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE4 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE9 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    public static final String NAME_LFD_PERSONENNUMMER_DES_SICHERUNGSGLAEUBIGERS = "Lfd. Personennummer des Sicherungsglaeubigers";
    public static final String NAME_MEHRLEISTUNGSKLAUSEL = "Mehrleistungsklausel";
    public static final String NAME_MEHRWERTGRUND = "Mehrwertgrund";
    public static final String NAME_MEHRWERT_IN_WAEHRUNGSEINHEITEN = "Mehrwert";
    public static final String NAME_MEHRZWECKFELD = "Mehrzweckfeld";
    public static final String NAME_MITVERSICHERTE_PERSON_FAMILIENNAME = "Mitversicherte Person Familienname";
    public static final String NAME_MITVERSICHERTE_PERSON_VORNAME = "Mitversicherte Person Vorname";
    public static final String NAME_MODELLNAME = "Modellname";
    public static final String NAME_NAECHSTE_ERHOEHUNG = "naechste Erhoehung";
    public static final String NAME_NAME1 = "Name1";
    public static final String NAME_NAME2 = "Name2";
    public static final String NAME_NAME3 = "Name3";
    public static final String NAME_NAME_KREDITINSTITUT1 = "Name des Kreditinstituts 1";
    public static final String NAME_NAME_KREDITINSTITUT2 = "Name des Kreditinstituts 2";
    public static final String NAME_NAME_MITVERSICHERTES_KIND = "Name des mitversicherten Kinds";
    public static final String NAME_NAME_VP = "Name der VP";
    public static final String NAME_NEUPREIS_IN_WAEHRUNGSEINHEITEN = "Neupreis in Waehrungseinheiten";
    public static final String NAME_NUTZUNGSART = "Nutzungsart";
    public static final String NAME_ORDNUNGSBEGRIFF = "Ordnungsbegriff";
    public static final String NAME_ORT = "Ort";
    public static final String NAME_PASSIVES_KRIEGSRISIKO = "passives Kriegsrisiko";
    public static final String NAME_PERSONENNUMMER_LFD_NUMMER = "Personennummer / lfd. Nummer";
    public static final String NAME_PERSONEN_KUNDENNUMMER_DES_VERSICHERERS = "Personen-/Kundennummer des Versicherers";
    public static final String NAME_POLICIERUNGSDATUM = "Policierungsdatum";
    public static final String NAME_POSTALISCHES_KENNZEICHEN = "postalisches Kennzeichen";
    public static final String NAME_POSTFACH = "postfach";
    public static final String NAME_POSTLEITZAHL = "Postleitzahl";
    public static final String NAME_PRODUKTBESCHREIBUNG = "Produktbeschreibung";
    public static final String NAME_PRODUKTFORM = "Produktform";
    public static final String NAME_PRODUKTFORM_GUELTIG_AB = "Produktform gueltig ab";
    public static final String NAME_PRODUKTKENNUNG = "Produktkennung";
    public static final String NAME_PRODUKTNAME = "Produktname";
    public static final String NAME_PRODUKTSPEZIFISCHE_ANTRAGSDATEN = "Produktspezifische Antragsdaten";
    public static final String NAME_PRODUKTSPEZIFISCHE_STAMMDATEN = "Produktspezifische Stammdaten";
    public static final String NAME_PROVISION = "Provision";
    public static final String NAME_PROZENTSATZ_PROGRESSIVE_INVALIDITAET = "Prozentsatz progressive Invaliditaet";
    public static final String NAME_RATENZAHLUNGSZUSCHLAG_IN_PROZENT = "Ratenzahlungszuschlag in %";
    public static final String NAME_RECHTSFORM = "Rechtsform";
    public static final String NAME_REFERENZNUMMER = "Referenznummer";
    public static final String NAME_REFERENZ_VERSICHERUNGSSCHEINNUMMER = "Referenz-Versicherungsscheinnummer";
    public static final String NAME_REFERENZ_VERSICHERUNGSSCHEINNUMMER_1 = "1. Referenz-Versicherungsscheinnummer";
    public static final String NAME_REFERENZ_VERSICHERUNGSSCHEINNUMMER_2 = "2. Referenz-Versicherungsscheinnummer";
    public static final String NAME_REFERENZ_VERSICHERUNGSSCHEINNUMMER_3 = "3. Referenz-Versicherungsscheinnummer";
    public static final String NAME_REGISTRIERUNGSNUMMER_VERMITTLER = "Registrierungsnummer Vermittler";
    public static final String NAME_RESTLAUFZEIT_VERTRAG = "Restlaufzeit des Vertrages";
    public static final String NAME_RISIKOGRUPPE_RISIKOART = "Risikogruppe / Risikoart";
    public static final String NAME_RISIKOGRUPPE_RISIKOART1 = "Risikogruppe / Risikoart";
    public static final String NAME_RISIKOGRUPPE_RISIKOART2 = "Risikogruppe / Risikoart";
    public static final String NAME_RISIKOTEXT = "Risikotext";
    public static final String NAME_RISIKOVERLAUF = "Risikoverlauf";
    public static final String NAME_RISKIOEINHEIT1 = "Risikoeinheit-1";
    public static final String NAME_RISKIOEINHEIT2 = "Risikoeinheit-2";
    public static final String NAME_ROHBAU_EINMALBETRAG = "Rohbau-Einmalbetrag";
    public static final String NAME_ROLLE_W_AKZ = "Rolle W-AKZ";
    public static final String NAME_RUECKFUEHRUNGSKOSTEN = "Rueckfuehrungskosten";
    public static final String NAME_SAISONKENNZEICHEN = "Saisonkennzeichen";
    public static final String NAME_SATZART = "Satzart";
    public static final String NAME_SATZNUMMER = "Satznummer";
    public static final String NAME_SATZNUMMER1 = "Satznummer";
    public static final String NAME_SATZNUMMER2 = "Satznummer";
    public static final String NAME_SATZNUMMER3 = "Satznummer";
    public static final String NAME_SATZNUMMER4 = "Satznummer";
    public static final String NAME_SATZNUMMER5 = "Satznummer";
    public static final String NAME_SATZNUMMER9 = "Satznummer";
    public static final String NAME_SATZNUMMERWIEDERHOLUNG = "Satznummerwiederholung";
    public static final String NAME_SATZNUMMERWIEDERHOLUNG1 = "Satznummerwiederholung1";
    public static final String NAME_SATZNUMMERWIEDERHOLUNG2 = "Satznummerwiederholung2";
    public static final String NAME_SATZNUMMERWIEDERHOLUNG3 = "Satznummerwiederholung3";
    public static final String NAME_SATZNUMMERWIEDERHOLUNG4 = "Satznummerwiederholung4";
    public static final String NAME_SATZNUMMERWIEDERHOLUNG9 = "Satznummerwiederholung9";
    public static final String NAME_SCHADENBEARBEITUNGSKOSTEN = "Schadenbearbeitungskosten";
    public static final String NAME_SCHLUESSEL_SICHERUNGSEINRICHTUNG = "Schluessel Sicherungseinrichtung";
    public static final String NAME_SCHUTZBRIEF_VERKEHRSSERVICE = "Schutzbrief /Verkehrsservice";
    public static final String NAME_SELBSTBETEILIGUNG = "Selbstbeteiligung";
    public static final String NAME_SELBSTBETEILIGUNG_IN_PROZENT = "Selbstbeteiligung in %";
    public static final String NAME_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = "Selbstbeteiligung";
    public static final String NAME_SELBSTBETEILIGUNG_IN_WE_MAX = "Selbstbeteiligung in WE (max.)";
    public static final String NAME_SELBSTBETEILIGUNG_IN_WE_MIND = "Selbstbeteiligung in WE (mind.)";
    public static final String NAME_SEPA_GLAEUBIGERIDENTIFIKATIONSNUMMER = "SEPA-Glaeubigeridentifikationsnummer";
    public static final String NAME_SEPA_MANDAT_AUSSTELLUNGSDATUM = "SEPA-Mandat Ausstellungsdatum";
    public static final String NAME_SEPA_MANDAT_KONTOREFERENZ = "SEPA-Mandat Kontoreferenz";
    public static final String NAME_SEPA_MANDAT_REFERENZNUMMER = "SEPA-Mandat Referenznummer";
    public static final String NAME_SERVICELEISTUNGEN = "Serviceleistungen";
    public static final String NAME_SERVICELEISTUNGEN_BEITRAGSSATZ = "Serviceleistungen Beitragssatz";
    public static final String NAME_SICHERUNGSEINRICHTUNG = "Sicherungseinrichtung";
    public static final String NAME_SICHERUNGSGLAEUBIGER = "Sicherungsglaeubiger";
    public static final String NAME_SICHERUNGSSCHEIN = "Sicherungsschein";
    public static final String NAME_SONDERBEDINGUNGEN = "Sonderbedingungen";
    public static final String NAME_SONDERBEDINGUNGEN_KLAUSELN = "Sonderbedingungen / Klauseln";
    public static final String NAME_SONDERVEREINBARUNGEN = "Sondervereinbarungen";
    public static final String NAME_SONSTIGER_BEZUGSBERECHTIGTER_IM_LEISTUNGSFALL = "Sonstigter Bezugsberechtigter im Leistungsfall";
    public static final String NAME_SPARTE = "Sparte";
    public static final String NAME_SPARTE2 = "Sparte";
    public static final String NAME_SPEZIFIKATION_REFERENZ_VERSICHERUNGSSCHEINNUMMER = "Spezifikation der Referenz-Versicherungsscheinnummer";
    public static final String NAME_STAATSANGEHOERIGKEIT = "Staatsangehoerigkeit";
    public static final String NAME_STAERKE = "Staerke";
    public static final String NAME_STAERKEEINHEIT = "Staerkeeinheit";
    public static final String NAME_STEUERNR_JURISTISCHE_PERSON = "Steuernummer bei juristischen Personen";
    public static final String NAME_STOCKWERKE = "Stockwerke";
    public static final String NAME_STRASSE = "Strasse";
    public static final String NAME_STURMZONE = "Sturmzone";
    public static final String NAME_TAGEGELD1 = "Tagegeld 1";
    public static final String NAME_TAGEGELD1_BEITRAGSSATZ = "Tagegeld 1 Beitragssatz";
    public static final String NAME_TAGEGELD2 = "Tagegeld 2";
    public static final String NAME_TAGEGELD2_BEITRAGSSATZ = "Tagegeld 2 Beitragssatz";
    public static final String NAME_TARIF = "Tarif";
    public static final String NAME_TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGTEIL_IN_WAEHRUNGSEINHEITEN = "Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugteil";
    public static final String NAME_TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGVOLL_IN_WAEHRUNGSEINHEITEN = "Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugvoll";
    public static final String NAME_TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_HAFTPFLICHT_IN_WAEHRUNGSEINHEITEN = "Tarifbeitrag 100 % fuer Kraftfahrt-Haftpflicht";
    public static final String NAME_TARIFBEZEICHNUNG = "Tarifbezeichnung";
    public static final String NAME_TARIFIERUNGSMERKMAL_LAUFZEIT = "Tarifierungsmerkmal Laufzeit";
    public static final String NAME_TARIFJAHR = "Tarifjahr";
    public static final String NAME_TITEL = "Titel";
    public static final String NAME_TOD = "Tod";
    public static final String NAME_TOD_BEITRAGSSATZ = "Tod-Beitragssatz";
    public static final String NAME_TRAEGERUNTERNEHMEN_NAME = "Traegerunternehmen Name";
    public static final String NAME_TRAEGERUNTERNEHMEN_SCHLUESSEL = "Traegerunternehmen Schluessel";
    public static final String NAME_TYKLASSE_KH = "Tyklasse KH";
    public static final String NAME_TYPKLASSE_FUER_KFT = "Typklasse fuer KFT";
    public static final String NAME_TYPKLASSE_FUER_KFV = "Typklasse fuer KFV";
    public static final String NAME_TYPSCHLUESSEL_NR = "Typschluessel-Nr.";
    public static final String NAME_TYP_BANKVERBINDUNG1 = "Typ der Bankverbindung 1";
    public static final String NAME_TYP_BANKVERBINDUNG2 = "Typ der Bankverbindung 2";
    public static final String NAME_UEBERFUEHRUNGSKOSTEN = "Ueberfuehrungskosten";
    public static final String NAME_UEBERGANGSENTSCHAEDIGUNG = "Uebergangsentschaedigung";
    public static final String NAME_UEBERGANGSENTSCHAEDIGUNG_BEITRAGSSATZ = "Uebergangsentschaedigung Beitragssatz";
    public static final String NAME_UMBAUTER_RAUM = "umbauter Raum";
    public static final String NAME_UMSATZSTEUER_ID = "Umsatzsteuer-Identifikationsnummer";
    public static final String NAME_UNBEKANNT = "unbekannt";
    public static final String NAME_UNTERSTUETZUNGSKASSE_NAME = "Unterstuetzungskasse Name";
    public static final String NAME_UNTERSTUETZUNGSKASSE_SCHLUESSEL = "Unterstuetzungskasse Schluessel";
    public static final String NAME_UNVERFALLBARKEIT = "Unverfallbarkeit";
    public static final String NAME_UNWIDERRUFLICHES_BEZUGSRECHT_IM_LEISTUNGSFALL = "Unwiderrufliches Bezugsrecht im Leistungsfall";
    public static final String NAME_VERTRAGSVERBINDUNGSNUMMER = "Vertragsverbindungsnummer";
    public static final String NAME_VERKUERZTE_BEITRAGSZAHLUNGSDAUER = "verkuerzte Beitragszahlungsdauer";
    public static final String NAME_VERMITTLER = "Geschaeftsstelle/Vermittler";
    public static final String NAME_VERSICHERTES_OBJEKT = "Versichertes Objekt";
    public static final String NAME_VERSICHERTE_GEFAHREN = "Versicherte Gefahren";
    public static final String NAME_VERSICHERUNGSLEISTUNGEN = "Versicherungsleistungen";
    public static final String NAME_VERSICHERUNGSSCHEINNUMMER = "Versicherungsschein-Nummer";
    public static final String NAME_VERSICHERUNGSSCHEINNUMMER_VM = "Versicherungsscheinnummer VM";
    public static final String NAME_VERSION_SATZART_9999 = "Version Satzart 9999 Nachsatz";
    public static final String NAME_VERSION_SATZART_0001 = "Version Satzart 0001";
    public static final String NAME_VERSION_SATZART_0100 = "Version Satzart 0100";
    public static final String NAME_VERSION_SATZART_0200 = "Version Satzart 0200";
    public static final String NAME_VERSION_SATZART_0210_050 = "Version Satzart 0210 050";
    public static final String NAME_VERSION_SATZART_0220_051 = "Version Satzart 0220 051";
    public static final String NAME_VERSION_SATZART_0220_052 = "Version Satzart 0220 052";
    public static final String NAME_VERSION_SATZART_0220_053 = "Version Satzart 0220 053";
    public static final String NAME_VERSION_SATZART_0220_054 = "Version Satzart 0220 054";
    public static final String NAME_VERSION_SATZART_0220_059 = "Version Satzart 0220 059";
    public static final String NAME_VERSION_SATZART_0210_040 = "Version Satzart 0210 040";
    public static final String NAME_VERSION_SATZART_0220_040 = "Version Satzart 0220 040";
    public static final String NAME_VERSION_SATZART_0210_030 = "Version Satzart 0210 030";
    public static final String NAME_VERSION_SATZART_0220_030 = "Version Satzart 0220 030";
    public static final String NAME_VERSION_SATZART_0210_010 = "Version Satzart 0210 010";
    public static final String NAME_VERSION_SATZART_0220_010 = "Version Satzart 0220 010";
    public static final String NAME_VERSION_SATZART_0210_130 = "Version Satzart 0210 130";
    public static final String NAME_VERSION_SATZART_0220_130 = "Version Satzart 0220 130";
    public static final String NAME_VERSION_SATZART_0210_110 = "Version Satzart 0210 110";
    public static final String NAME_VERSION_SATZART_0220_110 = "Version Satzart 0220 110";
    public static final String NAME_VERSION_SATZART_0210_140 = "Version Satzart 0210 140";
    public static final String NAME_VERSION_SATZART_0220_140 = "Version Satzart 0220 140";
    public static final String NAME_VERSION_SATZART_0210_020 = "Version Satzart 0210 020";
    public static final String NAME_VERSION_SATZART_0220_020 = "Version Satzart 0220 020";
    public static final String NAME_VERSION_SATZART_0210_070 = "Version Satzart 0210 070";
    public static final String NAME_VERSION_SATZART_0220_070 = "Version Satzart 0220 070";
    public static final String NAME_VERSION_SATZART_0210_FEUER = "Version Satzart 0210 Feuer-Industrie/Gewerbliche Sachversicherung";
    public static final String NAME_VERSION_SATZART_0220_FEUER = "Version Satzart 0220 Feuer-Industrie/Gewerbliche Sachversicherung";
    public static final String NAME_VERSION_SATZART_0210_510 = "Version Satzart 0210 510";
    public static final String NAME_VERSION_SATZART_0220_510 = "Version Satzart 0220 510";
    public static final String NAME_VERSION_SATZART_0210_TECH_VERS = "Version Satzart 0210 Technische Versicherungen";
    public static final String NAME_VERSION_SATZART_0220_TECH_VERS = "Version Satzart 0220 Technische Versicherungen";
    public static final String NAME_VERSION_SATZART_0210_TRANSPORT = "Version Satzart 0210 Transport";
    public static final String NAME_VERSION_SATZART_0220_TRANSPORT = "Version Satzart 0220 Transport";
    public static final String NAME_VERSION_SATZART_0250_TRANSPORT = "Version Satzart 0250 Transport Einzelanmeldung";
    public static final String NAME_VERSION_SATZART_0260_TRANSPORT = "Version Satzart 0260 Transport Umsatzanmeldung";
    public static final String NAME_VERSION_SATZART_0210_NICHT_DEF_SPARTEN = "Version Satzart 0210 Nicht einzeln definierte Sparten";
    public static final String NAME_VERSION_SATZART_0220_NICHT_DEF_SPARTEN = "Version Satzart 0220 Nicht einzeln definierte Sparten";
    public static final String NAME_VERSION_KFZ_BAUSTEIN = "Version KFZ-Baustein";
    public static final String NAME_VERSION_SATZART_0300_BETEILIGUNGSINFORMATION = "Version Satzart 0300 Beteiligungsinformation";
    public static final String NAME_VERSION_SATZART_0400_INKASSO = "Version Satzart 0400 Inkasso";
    public static final String NAME_VERSION_SATZART_0410_INKASSO = "Version Satzart 0410 Inkasso";
    public static final String NAME_VERSION_SATZART_0430_INKASSO = "Version Satzart 0430 Inkasso";
    public static final String NAME_VERSION_SATZART_0500_SCHADENINFORMATION = "Version Satzart 0500 Schadeninformation";
    public static final String NAME_VERSION_SATZART_0420_VERSICHERUNGSTEUERABRECHNUNG = "Version Satzart 0420 Versicherungsteuerabrechnung";
    public static final String NAME_VERSION_SATZART_0450_INKASSOABRECHNUNG = "Version Satzart 0450 Inkassoabrechnung";
    public static final String NAME_VERSION_SATZART_0550_SCHADENABRECHNUNG = "Version Satzart 0550 Schadenabrechnung";
    public static final String NAME_VERSION_SATZART_0350_KLAUSELN = "Version Satzart 0350 Klausel";
    public static final String NAME_VERSION_SATZART_0211_050 = "Version Satzart 0211 050";
    public static final String NAME_VERSION_SATZART_0221_051 = "Version Satzart 0221 051";
    public static final String NAME_VERSION_SATZART_0221_052 = "Version Satzart 0221 052";
    public static final String NAME_VERSION_SATZART_0221_053 = "Version Satzart 0221 053";
    public static final String NAME_VERSION_SATZART_0221_054 = "Version Satzart 0221 054";
    public static final String NAME_VERSION_SATZART_0221_055 = "Version Satzart 0221 055";
    public static final String NAME_VERSION_SATZART_0221_059 = "Version Satzart 0221 059";
    public static final String NAME_VERSION_SATZART_0211_040 = "Version Satzart 0211 040";
    public static final String NAME_VERSION_SATZART_0221_040 = "Version Satzart 0221 040";
    public static final String NAME_VERSION_SATZART_0221_030 = "Version Satzart 0221 030";
    public static final String NAME_VERSION_SATZART_0211_010 = "Version Satzart 0211 010";
    public static final String NAME_VERSION_SATZART_0221_010 = "Version Satzart 0221 010";
    public static final String NAME_VERSION_SATZART_0211_130 = "Version Satzart 0211 130";
    public static final String NAME_VERSION_SATZART_0221_130 = "Version Satzart 0221 130";
    public static final String NAME_VERSION_SATZART_0211_110 = "Version Satzart 0211 110";
    public static final String NAME_VERSION_SATZART_0221_110 = "Version Satzart 0221 110";
    public static final String NAME_VERSION_SATZART_0211_140 = "Version Satzart 0211 140";
    public static final String NAME_VERSION_SATZART_0221_140 = "Version Satzart 0221 140";
    public static final String NAME_VERSION_SATZART_0221_070 = "Version Satzart 0221 070";
    public static final String NAME_VERSION_SATZART_0211_FEUER = "Version Satzart 0211 Feuer-Industrie/Gewerbliche Sachversicherung";
    public static final String NAME_VERSION_SATZART_0221_FEUER = "Version Satzart 0221 Feuer-Industrie/Gewerbliche Sachversicherung";
    public static final String NAME_VERSION_SATZART_0221_510 = "Version Satzart 0221 510";
    public static final String NAME_VERSION_SATZART_0211_TRANSPORT = "Version Satzart 0211 Transport";
    public static final String NAME_VERSION_SATZART_0221_TRANSPORT = "Version Satzart 0221 Transport";
    public static final String NAME_VERSION_SATZART_0251_TRANSPORT = "Version Satzart 0251 Transport Einzelanmeldung";
    public static final String NAME_VERSION_SATZART_0211_NICHT_DEF_SPARTEN = "Version Satzart 0211 Nicht einzeln definierte Sparten";
    public static final String NAME_VERSION_SATZART_0221_NICHT_DEF_SPARTEN = "Version Satzart 0221 Nicht einzeln definierte Sparten";
    public static final String NAME_VERSION_SATZART_0210_550 = "Version Satzart 0210 550";
    public static final String NAME_VERSION_SATZART_0220_550 = "Version Satzart 0220 550";
    public static final String NAME_VERSION_SATZART_0270_550 = "Version Satzart 0270 550";
    public static final String NAME_VERSION_SATZART_0280_550 = "Version Satzart 0280 550";
    public static final String NAME_VERSION_SATZART_0291_550 = "Version Satzart 0291 550";
    public static final String NAME_VERSION_SATZART_0292_550 = "Version Satzart 0292 550";
    public static final String NAME_VERSION_SATZART_0293_550 = "Version Satzart 0293 550";
    public static final String NAME_VERSION_SATZART_0294_550 = "Version Satzart 0294 550";
    public static final String NAME_VERSION_SATZART_0295_550 = "Version Satzart 0295 550";
    public static final String NAME_VERSION_SATZART_0052 = "Version Satzart 0051";
    public static final String NAME_VERSION_SATZART_0102 = "Version Satzart 0102";
    public static final String NAME_VERSION_SATZART_0212 = "Version Satzart 0212";
    public static final String NAME_VERSION_SATZART_0352 = "Version Satzart 0352";
    public static final String NAME_VERSION_SATZART_0362 = "Version Satzart 0362";
    public static final String NAME_VERSION_SATZART_0382 = "Version Satzart 0382";
    public static final String NAME_VERSION_SATZART_9950 = "Version Satzart 9950";
    public static final String NAME_VERSION_SATZART_9952 = "Version Satzart 9952";
    public static final String NAME_VERSION_SATZART_0210_580 = "Version Satzart 0210 580";
    public static final String NAME_VERSION_SATZART_0220_580 = "Version Satzart 0220 580";
    public static final String NAME_VERSION_SATZART_0372 = "Version Satzart 0372";
    public static final String NAME_VERSION_SATZART_0600 = "Version Satzart 0600";
    public static final String NAME_VERTRAGSABLAUF = "Vertragsablauf";
    public static final String NAME_VERTRAGSBEGINN = "Vertragsbeginn";
    public static final String NAME_VERTRAGSFORM = "Vertragsform";
    public static final String NAME_VERTRAGSSTATUS = "Vertragsstatus";
    public static final String NAME_VORAUSSICHTLICHES_ENDE = "voraussichtliches Ende";
    public static final String NAME_VORLAUFSSUMME_IN_WAEHRUNGSEINHEITEN = "Vorlaufsumme";
    public static final String NAME_VORNAME_VP = "Vorname der VP";
    public static final String NAME_VORSTEUERABZUGSBERECHTIGT = "Vorsteuerabzugsberechtigt";
    public static final String NAME_VORZUGSSEUERBERECHTIGUNG_PROZENT = "Vorsteuerabszugsberechtigung in Prozent";
    public static final String NAME_VORZUGSSTEUERBERECHTIGUNG = "Vorsteuerabszugsberechtigung Ja/Nein";
    public static final String NAME_VP_PERSONENNUMMER_VERMITTLER = "VP-Personnenummer des Vermittlers";
    public static final String NAME_VP_PERSONENNUMMER_VERSICHERER = "VP-Personnenummer des Versicherers";
    public static final String NAME_VU_ABRECHNUNGSSTELLE = "VU-Abrechnungsstelle";
    public static final String NAME_WAEHRUNGSSCHLUESSEL = "Waehrungsschluessel";
    public static final String NAME_WAEHRUNG_DOKUMENTE_FUER_VN = "Waehrung der Dokumente fuer VN";
    public static final String NAME_WAGNIS = "Wagnis";
    public static final String NAME_WAGNISKENNZIFFER = "Wagniskennziffer";
    public static final String NAME_WARTEZEIT = "Wartezeit";
    public static final String NAME_WECHSELKENNZEICHEN_W_AKZ = "Wechselkennzeichen W-AKZ";
    public static final String NAME_WEITERE_REFERENZNUMMERN = "Weitere Referenznummern";
    public static final String NAME_WOHNEIGENTUM = "Wohneigentum";
    public static final String NAME_ZAHLUNGSANFANG = "Zahlungsanfang";
    public static final String NAME_ZAHLUNGSART = "Zahlungsart /-weg";
    public static final String NAME_ZAHLUNGSWEISE = "Zahlungsweise";
    public static final String NAME_ZAHLUNGSWEISE_KUENFTIGER_GESAMTBETRAG = "Zahlungsweise des kuenftigen Gesamtbetrags";
    public static final String NAME_ZIELGRUPPENSCHLUESSEL = "Zielgruppenschluessel";
    public static final String NAME_ZUKUENFTIGER_GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN = "Zukuenftiger Gesamtbeitrag";
    public static final String NAME_ZUSAETZLICHE_SATZKENNUNG = "zusaetzliche Satzkennung";
    public static final String NAME_ZUSCHLAG1_IN_PROZENT = "Zuschlag-1 in %";
    public static final String NAME_ZUSCHLAG1_IN_WAEHRUNGSEINHEITEN = "Zuschlag-1";
    public static final String NAME_ZUSCHLAG2_IN_PROZENT = "Zuschlag-2 in %";
    public static final String NAME_ZUSCHLAG2_IN_WAEHRUNGSEINHEITEN = "Zuschlag-2";
    public static final String NAME_ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN = "Zuschlagsbetrag";
    public static final String NAME_ZUSCHLAG_IN_PROZENT = "Zuschlag in %";
    public static final String NAME_ZUZAHLUNGSDATUM = "Zuzahlungsdatum";
    public static final String NAME_ZUZAHLUNGSRECHT = "Zuzahlungsrecht";

    public static final String NAME_VERSION_SATZART_0392_EVB = "Version Satzart 0392 eVB-Nummer";
    public static final String NAME_VERSION_SATZART_0230_LEBEN = "Version Satzart 0230 Fondsdatensatz - Leben";
    public static final String NAME_VERSION_SATZART_0202_ALLG_ANTRAGSDATEN = "Version Satzart 0202 Allgemeine Antragsdaten";
    public static final String NAME_VERSION_SATZART_0230_UNFALL = "Version Satzart 0230 Unfall Leistungsarten";
    public static final String NAME_VERSION_SATZART_0390_RABATTE = "Version Satzart 0390 Rabatte und Zuschlaege";
    public static final String NAME_VERSION_SATZART_0342_BEGLEITDOK = "Version Satzart 0342 Begleitdokumente und Signaturen";
    public static final String NAME_VERSION_SATZART_9951_MIME = "Version Satzart 9951 MIME-Datei";
    public static final String NAME_VERSION_SATZART_0210_560 = "Version Satzart 0210 560";
    public static final String NAME_VERSION_SATZART_0220_560 = "Version Satzart 0220 560";
    public static final String NAME_VERSION_SATZART_0210_570 = "Version Satzart 0210 570";
    public static final String NAME_VERSION_SATZART_0220_570 = "Version Satzart 0220 570";
    public static final String NAME_VERSION_SATZART_0210_684 = "Version Satzart 0210 684";
    public static final String NAME_VERSION_SATZART_0220_684 = "Version Satzart 0220 684";
    public static final String NAME_FAELLIGKEIT_DER_LETZTEN_BEITRAGSZAHLUNG = "Faelligkeit der letzten Beitragszahlung";
    public static final String NAME_LEBENSLANGE_BEITRAGSZAHLUNG = "Lebenslange Beitragszahlung";
    public static final String NAME_BESONDERE_VEREINBARUNGEN = "Besondere Vereinbarungen";
    public static final String NAME_DIREKTANSPRUCH = "Direktanspruch";
    public static final String NAME_EINTRITTSALTER_DER_VP = "Eintrittsalter der VP";
    public static final String NAME_ALTERSGRUPPE = "Altersgruppe";
    public static final String NAME_BEITRAGSZAHLUNG_BIS = "Beitragszahlung bis";
    public static final String NAME_RUECKGEWAEHRDATUM = "Rueckgewaehrdatum";
    public static final String NAME_RUECKGEWAEHRSUMME_ZUM_ABLAUF_IN_WAEHRUNGSEINHEITEN = "Rueckgewaehrsumme zum Ablauf";
    public static final String NAME_ABLAUFLEISTUNG_INKL_UEBERSCHUSSANTEILE_IN_WAEHRUNGSEINHEITEN = "Ablaufleistung incl. Ueberschussanteile";
    public static final String NAME_KAPITALERTRAGSSTEUER_BEI_ABLAUF = "Kapitalertragssteuer bei Ablauf";
    public static final String NAME_SOLIDARITAETSZUSCHLAG_BEI_ABLAUF = "Solidarit\u00e4tszuschlag bei Ablauf";
    public static final String NAME_RUECKKAUFSWERT_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = "Rueckkaufswert zum Berechnungsstichtag";
    public static final String NAME_BERECHNUNGSSTICHTAG_ZUM_RUECKKAUFSWERT = "Berechnungsstichtag zum Rueckkaufswert";
    public static final String NAME_KAPITALERTRAGSSTEUER_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG = "Kapitalertragssteuer bei Rueckkauf zum Berechnungsstichtag";
    public static final String NAME_SOLIDARITAETSZUSCHLAG_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG = "Solidarit\u00e4tszuschlag bei Rueckkauf zum Berechnungsstichtag";
    public static final String NAME_UEBERSCHUSSANTEILE_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = "Ueberschussanteile zum Berechnungsstichtag";
    public static final String NAME_GEBURTSDATUM_DER_BEZUGSBERECHTIGTEN_PERSON = "Geburtsdatum der bezugsberechtigten Person";

    public static final String NAME_ANZAHL_DER_VERSICHERTEN_PERSONEN = "Anzahl der versicherten Personen";
    public static final String NAME_DATUM_UNVERFALLBARKEIT = "Datum Unverfallbarkeit";
    public static final String NAME_DIENSTEINTRITT = "Diensteintritt";
    public static final String NAME_GESCHLECHT_VP = "Geschlecht der VP";
    public static final String NAME_LFD_NUMMER_ZUR_WAGNISART = "Lfd Nummer zur Wagnisart";
    public static final String NAME_MITARBEITER_STATUS = "Mitarbeiter Status";
    public static final String NAME_PERSONEN_KUNDENNUMMER_DES_VERMITTLERS = "Personen Kundennummer des Vermittlers";
    public static final String NAME_SOZIALVERSICHERUNG_NUMMER = "Sozialversicherung Nummer";
    public static final String NAME_STATUS_SEIT = "Status seit";
    public static final String NAME_WAGNISART = "Wagnisart";

    public static final String NAME_VORZEICHEN = "Vorzeichen";
    public static final String NAME_VORZEICHEN2 = "Vorzeichen";
    public static final String NAME_VORZEICHEN3 = "Vorzeichen";
    public static final String NAME_VORZEICHEN4 = "Vorzeichen";
    public static final String NAME_VORZEICHEN5 = "Vorzeichen";

    public static final String NAME_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = "Erlebensfall VS zum Ablauf";
    public static final String NAME_TODESFALL_VS_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = "Todesfall VS zum Ablauf";
    public static final String NAME_ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = "Erlebensfall VS II zum Ablauf";
    public static final String NAME_BEITRAGSFREIE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Beitragsfreie Erlebensfall VS";
    public static final String NAME_ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG = "Absoluter Dynamikerhoehungsbetrag";
    public static final String NAME_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = "Erlebensfall VS zum Beginn der Abrufphase";
    public static final String NAME_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = "Todesfall VS zum Beginn der Abrufphase";
    public static final String NAME_ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = "Erlebensfall VS II zum Beginn der Abrufphase";

    public static final String NAME_ANFAENGLICHE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Anfaengliche Erlebensfall VS";
    public static final String NAME_AENDERUNG_DER_ERLEBENSFALL_VS = "Aenderung der Erlebensfall VS";
    public static final String NAME_ABSTAND_DER_ERLEBENSFAL_VS_AENDERUNGSTERMINE = "Abstand der Erlebensfall VS-Aenderungstermine";
    public static final String NAME_ERLEBENSFALL_VS_AENDERUNGS_PROZENTSATZ = "Erlebensfall VS-Aenderungs-Prozentsatz";
    public static final String NAME_ABSOLUTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Absolute Erlebensfall VS";
    public static final String NAME_NAECHSTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Naechste Erlebensfall VS";
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_ERLEBENSFALL_VS = "Beginndatum der naechsten Erlebensfall VS";

    public static final String NAME_BEITRAG_BU_IN_WAEHRUNGSEINHEITEN = "Beitrag BU";
    public static final String NAME_EINSCHLUSS_PROZENT_SATZ = "Einschluss %-Satz";
    public static final String NAME_ZWANG_ZUR_BUZ = "Zwang zur BUZ";
    public static final String NAME_LEISTUNGSDAUER = "Leistungsdauer";
    public static final String NAME_BUZ_VERWENDUNGSART = "BUZ Verwendungsart";
    public static final String NAME_RENTE_INCL_UEBERSCHUSSBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = "Rente inkl. Ueberschussbeteiligung";
    public static final String NAME_BUZ_LEISTUNG_VON = "BUZ Leistung von";
    public static final String NAME_BUZ_LEISTUNG_BIS = "BUZ Leistung bis";
    public static final String NAME_BUZ_PROZENT_SATZ = "BUZ %-Satz";
    public static final String NAME_KARENZZEIT = "Karenzzeit";
    public static final String NAME_ABWEICHENDE_LEISTUNGSDAUER = "Abweichende Leistungsdauer";
    public static final String NAME_ZUKUENFTIGER_BEITRAG = "Zukuenftiger Beitrag";
    public static final String NAME_ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAERHUNGSEINHEITEN = "Absoluter Dynamikerhoehungsbetrag";

    public static final String NAME_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT = "Erlebensfall VS";
    public static final String NAME_TODESFALL_VS_IN_WAEHRUNGSEINHEIT = "Todesfall VS";
    public static final String NAME_ERLEBENSFALL_VS2_IN_WAEHRUNGSEINHEIT = "Erlebensfall VS II";
    public static final String NAME_BEITRAGSFREIE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT = "Beitragsfreie Erlebensfall VS";
    public static final String NAME_BEITRAGSFREIE_TODESFALL_VS_IN_WAEHRUNGSEINHEIT = "Beitragsfreie Todesfall VS";

    public static final String NAME_VERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Versicherungssumme";
    public static final String NAME_FALLENDE_SUMME = "fallende Summe";
    public static final String NAME_RENTE_INKL_UEBERSCHUSSANRECHNUNG_IN_WAEHRUNGSEINHEITEN = "Rente inkl. Ueberschussanrechnung";

    public static final String NAME_ABSOLUTE_UNFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Unfallaenderungssumme";
    public static final String NAME_NAECHSTE_UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = "Naechste Unfallsumme";

    public static final String NAME_ANFAENGLICHE_UNFALLSUMME = "Anfaengliche Unfallsumme";
    public static final String NAME_AENDERUNG_DER_UNFALLLEISTUNG = "Aenderung der Unfallleistung";
    public static final String NAME_ABSTAND_DER_UNFALLAENDERUNGSTERMINE = "Abstand der Unfallaenderungstermine";
    public static final String NAME_UNFALLAENDERUNGS_PROZENTSATZ = "Unfallaenderungs-Prozentsatz";
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_UNFALLSUMME = "Beginndatum der naechsten Unfallsumme";

    public static final String NAME_BEITRAG_PROMILLE = "Beitrag Promille";
    public static final String NAME_UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = "Unfallsumme";
    public static final String NAME_RISIKOZUSCHLAG = "Risikozuschlag";

    public static final String NAME_LFD_NUMMER_DES_FONDS = "Lfd. Nummer des Fonds";
    public static final String NAME_WERTPAPIERKENNNUMMER = "Wertpapierkennnummer";
    public static final String NAME_FONDSKENNUNG = "Fondskennung";
    public static final String NAME_ISIN_NUMMER = "ISIN-Nummer";
    public static final String NAME_FONDSNAME = "Fondname";
    public static final String NAME_THESAUR = "Thesaur";
    public static final String NAME_ANTEILE = "Anteile";
    public static final String NAME_BEZUGSDATUM = "Bezugsdatum";
    public static final String NAME_PROZENTSATZ = "Prozentsatz";
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_JAHRESRENTESUMME = "Beginndatum der naechsten Jahresrentensumme";
    public static final String NAME_GUTHABEN_DIVID_ANSAMMLUNG_IN_WAEHRUNGSEINHEITEN = "Guthaben Divid. Ansammlung";
    public static final String NAME_NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN2 = "Nettobeitrag";
    public static final String NAME_GARANTIERTE_FONDSJAHRESRENTE_ZUM_ABLAUF_INKL_ABRUFPHASE = "Garantierte Fondsjahresrente zum Ablauf (inkl. Abrufphase)";
    public static final String NAME_GARANTIERTE_FONDSJAHRESRENTE_ZUM_BEGINN_DER_ABRUFPHASE = "Garantierte Fondsjahresrente zum Beginn der Abrufphase";
    public static final String NAME_AKTUELLE_FONDSJAHRESRENTE_ZUM_ABLAUF_INKL_ABRUFPHASE = "Aktuelle Fondsjahresrente zum Ablauf (inkl. Abrufphase)";
    public static final String NAME_AKTUELLE_FONDSJAHRESRENTE_ZUM_BEGINN_DER_ABRUFPHASE = "Aktuelle Fondsjahresrente zum Beginn der Abrufphase";
    public static final String NAME_WAGNISART4 = "Wagnisart";
    public static final String NAME_LFD_NUMMER_ZUR_WAGNISART4 = "Lfd. Nummer zur Wagnisart";
    public static final String NAME_ABSOLUTE_TODESFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Todesfallaenderungssumme";
    public static final String NAME_AENDERUNG_DER_BEITRAGSSUMME = "Aenderung der Beitragssumme";
    public static final String NAME_ABSTAND_DER_BEITRAGSSUMMENAENDERUNGSTERMINE = "Abstand der Beitragssummenaenderungstermine";
    public static final String NAME_BEITRAGSSUMMENAENDERUNGS_PROZENTSATZ = "Beitragssummenaenderungs-Prozentsatz";
    public static final String NAME_ABSOLUTE_BEITRAGSSUMMENAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Beitragssummenaenderungssumme";
    public static final String NAME_NAECHSTE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Naechste Beitragssumme";
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_BEITRAGSSUMME = "Beginndatum der naechsten Beitragssumme";
    public static final String NAME_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Beitragssumme";
    public static final String NAME_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Todesfall VS";
    public static final String NAME_FALLENDE_VS = "fallende VS";
    public static final String NAME_ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEITEN = "Erlebensfall VS II";
    public static final String NAME_BEITRAGSFREIE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Beitragsfreie Beitragssumme";
    public static final String NAME_BEITRAGSFREIE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Beitragsfreie Todesfall VS";
    public static final String NAME_LAUFZEITVERKUERZUNG = "Laufzeitverkuerzung";
    public static final String NAME_VERTRAG_MIT_ZUWACHSGARANTIE = "Vertrag mit Zuwachsgarantie";
    public static final String NAME_TODESFALLLEISTUNG_IN_PROZENT = "Todesfallleistung in %";
    public static final String NAME_SPARVORGANG = "Sparvorgang";
    public static final String NAME_GESTUNDET_AUSGESETZT_BIS = "Gestundet ausgesetzt bis";
    public static final String NAME_ANLAGESTRATEGIE = "Anlagestrategie";
    public static final String NAME_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Erlebensfall VS";
    public static final String NAME_GUTHABEN_DIVID_ANSAMMLUNG_IN_WAEHRUNGSEINHEIT = "Guthaben Divid. Ansammlung";
    public static final String NAME_KAPITALZAHLUNGS_SUMME_IN_WAEHRUNGSEINHEIT = "Kapitalzahlungs-Summe";
    public static final String NAME_ANFAENGLICHE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Anfaengliche Todesfall VS";
    public static final String NAME_AENDERUNG_DER_TODESFALLLEISTUNG = "Aenderung der Todesfallleistung";
    public static final String NAME_ABSTAND_DER_TODESFALLAENDERUNGSTERMINE = "Abstand der Todesfallaenderungstermine";
    public static final String NAME_TODESFALLAENDERUNGS_PROZENTSATZ = "Todesfallaenderungs-Prozentsatz";
    public static final String NAME_ABSOLUTE_TODESFALLAENDERUNGSSUMME_VS_IN_WAEHRUNGSEINHEITEN = "Absolute Todesfallaenderungssumme VS";
    public static final String NAME_NAECHSTE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Naechste Todesfall VS";
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_TODESFALL_VS = "Beginndatum der naechsten Todesfall VS";
    public static final String NAME_ANFAENGLICHE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = "Anfaengliche Jahresrente";
    public static final String NAME_AENDERUNG_DER_JAHRESRENTE = "Aenderung der Jahresrente";
    public static final String NAME_ABSTAND_DER_JAHRESRENTENAENDERUNGSTERMINE = "Abstand der Jahresrentenaenderungstermine";
    public static final String NAME_JAHRESRENTENAENDERUNGS_PROZENTSATZ = "Jahresrentenaenderungs-Prozentsatz";
    public static final String NAME_ABSOLUTE_JAHRESRENTENAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Jahresrentenaenderungssumme";
    public static final String NAME_NAECHSTE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = "Naechste Jahresrente";
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_JAHRESRENTE = "Beginndatum der naechsten Jahresrente";
    public static final String NAME_PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Provisionspflichtige Beitragssumme";
    public static final String NAME_PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Provisionspflichtige Wertungssumme";
    public static final String NAME_WERTUNGSBASIS = "Wertungsbasis";
    public static final String NAME_WERTUNGSMODELL = "Wertungsmodell";
    public static final String NAME_BUCHUNGSKENNZEICHEN = "Buchungskennzeichen";
    public static final String NAME_HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Haftungswertungssumme";
    public static final String NAME_HAFTUNG_AB = "Haftung ab";
    public static final String NAME_HAFTUNG_BIS = "Haftung bis";
    public static final String NAME_URSPRUENGLICHES_HAFTUNGSBEGINNDATUM = "Urspruengliches Haftungsbeginndatum";
    public static final String NAME_PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Provisionspflichtige Beitragssumme";
    public static final String NAME_PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Provisionspflichtige Wertungssumme";
    public static final String NAME_WERTUNGSBASIS2 = "Wertungsbasis";
    public static final String NAME_WERTUNGSMODELL2 = "Wertungsmodell";
    public static final String NAME_BUCHUNGSKENNZEICHEN2 = "Buchungskennzeichen";
    public static final String NAME_HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Haftungswertungssumme";
    public static final String NAME_HAFTUNG_AB2 = "Haftung ab";
    public static final String NAME_HAFTUNG_BIS2 = "Haftung bis";
    public static final String NAME_BEZUGSBERECHTIGT_IM_ERLEBENSFALL = "Bezugsberechtigt im Erlebensfall";
    public static final String NAME_SONSTIGER_BEZUGSBERECHTIGTER_IM_ERLEBENSFALL = "Sonstiger Bezeugsberechtigter im Erlebensfall";
    public static final String NAME_BEZUGSRECHTANTEIL_IM_ERLEBENSFALL = "Bezugsrechtanteil im Erlebensfall";
    public static final String NAME_UNWIDERRUFLICHES_BEZUGSRECHT_IM_ERLEBENSFALL = "Unwiderrufliches Bezugsrecht im Erlebensfall";
    public static final String NAME_BEZUGSBERECHTIGT_IM_TODESFALL = "Bezugsberechtigt im Todesfall";
    public static final String NAME_SONSTIGER_BEZUGSBERECHTIGTER_IM_TODESFALL = "Sonstiger Bezugsberechtigter im Todesfall";
    public static final String NAME_BEZUGSRECHTANTEIL_IM_TODESFALL = "Bezugsrechtanteil im Todesfall";
    public static final String NAME_UNWIDERRUFLICHES_BEZUGSRECHT_IM_TODESFALL = "Unwiderrufliches Bezugsrecht im Todesfall";
    public static final String NAME_NAECHSTE_AUSZAHLUNGSSUMMER_IN_WAEHRUNGSEINHEITEN = "Naechste Auszahlungssumme";
    public static final String NAME_NAECHSTER_AUSZAHLUNGSTERMIN = "Naechster Auszahlungstermin";
    public static final String NAME_AUSZAHLUNGSWEISE = "Auszahlungsweise";
    public static final String NAME_ANZAHL_DER_AUSZAHLUNGEN = "Anzahl der Auszahlungen";
    public static final String NAME_ABLAUF = "Ablauf";
    public static final String NAME_AENDERUNG = "Aenderung";
    public static final String NAME_VERTRAGSLAUFZEIT = "Vertragslaufzeit";
    public static final String NAME_VERTRAGSART = "Vertragsart";
    public static final String NAME_STATUS = "Status";
    public static final String NAME_GEWINNVERWENDUNGSART = "Gewinnverwendungsart";
    public static final String NAME_UEBERSCHUSS_GUELTIG_AB = "Ueberschuss gueltig ab";
    public static final String NAME_RISIKOEINSCHRAENKUNG = "Risikoeinschraenkung";
    public static final String NAME_RISIKOZUSCHLAEGE = "Risikozuschlaege";
    public static final String NAME_DYNAMIK_PROZENT_SATZ = "Dynamik %-Satz";
    public static final String NAME_ERHOEHUNGSBASIS_DYNAMIK = "Erhoehungsbasis Dynamik";
    public static final String NAME_DYNAMIKSTOP = "Dynamikstop";
    public static final String NAME_DATUM_DER_LETZTEN_POSITIVEN_DYNAMIK = "Datum der letzten positiven Dynamik";
    public static final String NAME_RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN = "Rueckkaufswert";
    public static final String NAME_RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN_MIT_NACHKOMMA = "Rueckkaufswert";
    public static final String NAME_RUECKKAUFSWERT_GUELTIG_AB = "Rueckkaufswert gueltig ab";
    public static final String NAME_GUTHABEN_DIVID_ANSAMMLUNGEN_IN_WAEHRUNGSEINHEITEN = "Guthaben Divid. Ansammlungen";
    public static final String NAME_BEGINN_DER_RENTENZAHLUNG = "Beginn der Rentenzahlung";
    public static final String NAME_MINDESTLAUFZEIT = "Mindestlaufzeit";
    public static final String NAME_RUECKGEWAEHR_BEI_TOD = "Rueckgewaehr bei Tod";
    public static final String NAME_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = "Jahresrente";
    public static final String NAME_KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Kapitalzahlungssumme";
    public static final String NAME_WITWEN_WITWERRENTE_IN_PROZENT = "Witwen- / Witwerrente in Prozent";
    public static final String NAME_TECHNISCHE_WITWEN_WITTWERRENTE_IN_PROZENT = "Technische Witwen- / Witwerrente in Prozent";
    public static final String NAME_WAISENRENTE_IN_PROZENT = "Waisenrente in Prozent";
    public static final String NAME_TECHNISCHE_WAISE_IN_PROZENT = "Technische Waisenrente in Prozent";
    public static final String NAME_SCHLUSSALTER_DES_WAISEN = "Schlussalter des Waisen";
    public static final String NAME_AUSLOESUNG_DER_LEISTUNG = "Ausloesung der Leistung";
    public static final String NAME_ZAHLUNG_DER_WITWEN_WITWERRENTE_BIS = "Zahlung der Witwen- / Witwerrente bis";
    public static final String NAME_ENDALTER = "Endalter";
    public static final String NAME_EINTRITTSALTER = "Eintrittsalter";
    public static final String NAME_UMTAUSCHRECHT = "Umtauschrecht";
    public static final String NAME_STUNDUNG = "Stundung";
    public static final String NAME_BEGINN_ABRUFPHASE = "Beginn Abrufphase";
    public static final String NAME_WAGNISART2 = "Wagnisart";
    public static final String NAME_LFD_NUMMER_ZUR_WAGNISART2 = "Lfd. Nummer Wagnisart";
    public static final String NAME_ABWEICHENDE_VERTRAGSLAUFZEIT = "Abweichende Vertragslaufzeit";
    public static final String NAME_ABWEICHENDER_ABLAUF = "Abweichender Ablauf";
    public static final String NAME_NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN = "Nettobeitrag";
    public static final String NAME_RISIKOZUSCHLAG_IN_WAEHRUNGSEINHEITEN = "Risikozuschlag";
    public static final String NAME_RENTENZAHLWEISE = "Rentenzahlweise";
    public static final String NAME_MITZUVERSICHERNDE_PERSON = "Mitzuversichernde Person";
    public static final String NAME_GEBURTSDATUM_DER_MITZUVERSICHERNDEN_PERSON = "Geburtsdatum der mitzuversichernden Person";
    public static final String NAME_GESCHLECHT_DER_MITZUVERSICHERNDEN_PERSON = "Geschlecht der mitzuverschernden Person";
    public static final String NAME_TARIFBEZEICHNUNG_DES_FOLGETARIFS = "Tarifbezeichnung des Folgetarifs";
    public static final String NAME_UMSTELLUNGSDATUM_DES_FOLGETARIFS = "Umstellungsdatum des Folgetarifs";
    public static final String NAME_ZUKUENFTIGER_BEITRAG_IN_WAEHRUNGSEINHEITEN = "Zukuenftiger Beitrag";
    public static final String NAME_VERTRAGSBEDINGUNG = "Vertragsbedingung";
    public static final String NAME_DYNAMIKBEGINN = "Dynamikbeginn";
    public static final String NAME_ABWEICHENDES_DYNAMIKENDALTER = "Abweichendes Dynamikalter";
    public static final String NAME_ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAEHRUNGSEINHEITEN = "Absoluter Dynamikerhoehungsbetrag";
    public static final String NAME_ANTEILIGER_DYNAMIKPROZENTSATZ = "Anteiliger Dynamikprozentsatz";
    public static final String NAME_VEREINBARTER_DYNAMIKMINDESTANPASSUNGSPROZENTSATZ = "Vereinbarter Dynamikmindestanpassungsprozentsatz";
    public static final String NAME_VEREINBARTER_DYNAMIKMAXIMALANPASSUNGSPROZENTSATZ = "Vereinbarter Dynamikmaximalanpassungsprozentsatz";
    public static final String NAME_ANZAHL_VERBLEIBENDE_DYNAMIKWIDERSPRUECHE = "Anzahl verbleibende Dynamikwidersprueche";
    public static final String NAME_WAGNISART3 = "Wagnisart";
    public static final String NAME_LFD_NUMMER_ZUR_WAGNISART3 = "Lfd. Numemr Wagnisart";
    public static final String NAME_LEISTUNG_BEI_SCHWERER_ERKRANKUNG = "Leistung bei schwerer Erkrankung";
    public static final String NAME_VERSICHERTE_ERKRANKUNGEN = "Versicherte Erkrankungen";
    public static final String NAME_LEISTUNGSBEGINN_AB = "Leistungsbeginn ab";
    public static final String NAME_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN2 = "Jahresrente";
    public static final String NAME_KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Kapitalzahlungssumme";
    public static final String NAME_TEILKAPITALISIERUNG = "Teilkapitalisierung";
    public static final String NAME_TODESFALLLEISTUNG_IN_WAEHRUNGSEINHEITEN = "Todesfallleistung";
    public static final String NAME_EINGERECHNETE_ZULAGE = "Eingerechnete Zulage";
    public static final String NAME_EINRECHNUNGSJAHR = "Einrechnungsjahr";
    public static final String NAME_TEILDATENSATZNUMMER = "Teildatensatznummer";

    // Haftpflicht
    public static final String NAME_KENNZEICHEN_FUER_ABWEICHENDE_ABSCHLUSSPROVISION = "Kennzeichen fuer abweichende Abschlussprovision";
    public static final String NAME_KENNZEICHEN_FUER_ABWEICHENDE_FOLGEPROVISION = "Kennzeichen fuer abweichende Folgeprovision";
    public static final String NAME_RESTLAUFZEIT_DES_VERTRAGES = "Restlaufzeit des Vertrages";
    public static final String NAME_SELBSTBEHALT = "Selbstbehalt";
    public static final String NAME_WAEHRUNGSSCHLUESSEL_1 = "Waehrungsschluessel 1";
    public static final String NAME_WAEHRUNGSSCHLUESSEL_2 = "Waehrungsschluessel 2";
    public static final String NAME_WAEHRUNGSSCHLUESSEL_3 = "Waehrungsschluessel 3";
    public static final String NAME_WAEHRUNGSSCHLUESSEL_4 = "Waehrungsschluessel 4";
    public static final String NAME_BEITRAG_JE_BERECHNUNGSEINHEIT_UND_MENGENSCHLUESSEL = "Beitrag je Berechnungseinheit und Mengenschluessel";
    public static final String NAME_BERECHNUNGSEINHEIT = "Berechnungseinheit";
    public static final String NAME_DECKUNGSSUMME_1_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 1 in Tausend";
    public static final String NAME_DECKUNGSSUMME_2_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 2 in Tausend";
    public static final String NAME_DECKUNGSSUMME_3_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 3 in Tausend";
    public static final String NAME_DECKUNGSSUMME_4_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 4 in Tausend";
    public static final String NAME_ERHOEHUNGSSATZ_8_III_AHB = "Erhoehungssatz \u00a78, III AHB";
    public static final String NAME_KENNUNG_ERHOEHUNGSSATZ = "Kennung Erhoehungssatz";
    public static final String NAME_KENNZEICHEN_FUER_JAHRES_MAXIMIERUNG = "Kennzeichen fuer Jahres-Maximierung";
    public static final String NAME_LETZTE_ERHOEHUNG_8_III_AHB = "Letzte Erhoehung \u00a78, III AHB";
    public static final String NAME_MENGENSCHLUESSEL = "Mengenschluessel";
    public static final String NAME_MINDESTBEITRAG_JE_WAGNIS_IN_WAEHRUNGSEINHEITEN = "Mindestbeitrag je Wagnis";
    public static final String NAME_ORDNUNGS_NUMMER_FUER_WAGNISZUSATZ2 = "Ordungs-Nummer fuer Wagniszusatz";
    public static final String NAME_POSTLEITZAHL_DER_RISIKOANSCHRIFT = "Postleitzahl der Risikoanschrift";
    public static final String NAME_RISIKOORT = "Risikoort";
    public static final String NAME_RISIKOSTRASSE = "Risikostrasse";
    public static final String NAME_SUMMENART_1 = "Summenart 1";
    public static final String NAME_SUMMENART_2 = "Summenart 2";
    public static final String NAME_SUMMENART_3 = "Summenart 3";
    public static final String NAME_SUMMENART_4 = "Summenart 4";
    public static final String NAME_WAGNISBESCHREIBUNG = "Wagnisbeschreibung";
    public static final String NAME_WAGNISMENGE = "Wagnismenge";
    public static final String NAME_WAGNISTEXT = "Wagnistext";
    public static final String NAME_DECKUNGSSUMME_1_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 1";
    public static final String NAME_DECKUNGSSUMME_2_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 2";
    public static final String NAME_DECKUNGSSUMME_3_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 3";
    public static final String NAME_DECKUNGSSUMME_4_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 4";
    public static final String NAME_ORDNUNGS_NUMMER_FUER_WAGNISZUATZ = "Ordungs-Nummer fuer Wagniszusatz";

    // Unfall
    public static final String NAME_REFERENZ_VERSICHERUNGSSCHEINNUMME = "Referenz-Versicherungsscheinnumme";
    public static final String NAME_DAUERSCHAEDEN_KOERPERLICHE_BEEINTRAECHTIGUNGEN = "Dauerschaeden / koerperliche Beeintraechtigungen";
    public static final String NAME_ERKRANKUNGEN = "Erkrankungen";
    public static final String NAME_UNFAELLE = "Unfaelle";
    public static final String NAME_ART_DER_AUSZAHLUNG = "Art der Auszahlung";
    public static final String NAME_ART_DER_LEISTUNG = "Art der Leistung";
    public static final String NAME_ART_DES_BEITRAGSSATZES = "Art des Beitragssatzes";
    public static final String NAME_BEGINN_DER_ZAHLUNG_AB_TAG = "Beginn der Zahlung ab Tag";
    public static final String NAME_BEITRAG = "Beitrag";
    public static final String NAME_BEITRAGSSATZ = "Beitragssatz";
    public static final String NAME_BEZEICHNUNG_DER_LEISTUNG = "Bezeichnung der Leistung";
    public static final String NAME_LEISTUNG = "Leistung";
    public static final String NAME_LEISTUNG_AB_INVALIDITAETSGRAD_IN_PROZENT = "Leistung ab Invaliditaetsgrad in Prozent";
    public static final String NAME_LEISTUNGSZAHLUNGSWEISE = "Leistungszahlungsweise";
    public static final String NAME_LFD_NUMMER_ZUR_ART_DER_LEISTUNG = "Laufende Nummer zur Art der Leistung";
    public static final String NAME_PROZENTSATZ_PROGRESSIVE_INVALIDITAET_MEHRLEISTUNG_BEI_INVALIDITAET = "Prozentsatz progressive Invaliditaet / Mehrleistung bei Invaliditaet";

    // Verbundene Hausrat
    public static final String NAME_INTRO1 = "Intro";
    public static final String NAME_LAENDERKENNZEICHEN_DER_RISIKOANSCHRIFT = "Laenderkennzeichen der Risikoanschrift";
    public static final String NAME_WAEHRUNGSSCHUESSEL = "Waehrungsschluessel";
    public static final String NAME_ABSCHLAGSBEITRAG_IN_WAEHRUNGSEINHEITEN = "Abschlagsbeitrag";
    public static final String NAME_GESAMTVERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Gesamtversicherungssummer";
    public static final String NAME_BEDINGUNGEN = "Bedingungen";
    public static final String NAME_ERWEITERTE_NEUWERTVERSICHERUNG = "erweiterte Neuwertversicherung";
    public static final String NAME_SICHERUNGSRICHTLINIEN = "Sicherungsrichtlinien";
    public static final String NAME_EINBRUCH_MELDEANLAGE = "Einbruch Meldeanlage";
    public static final String NAME_RISIKOKENNZIFFER = "Risikokennziffer";
    public static final String NAME_ANZAHL_MONATE_UNBEWOHNT = "Anzahl Monate unbewohnt";
    public static final String NAME_WOHNFLAECHE_QM = "Wohnflaeche qm";
    public static final String NAME_TARIFZONE = "Tarifzone";
    public static final String NAME_BAUARTKLASSE = "Bauartklasse";
    public static final String NAME_GEFAHRENERHOEHUNG = "Gefahrenerhoehung";
    public static final String NAME_EINSCHLUSS_VANDALISMUS = "Einschluss Vandalismus";
    public static final String NAME_UNTERVERS_VERZICHT = "Untervers.-Verzicht";
    public static final String NAME_OBJEKTNUMMER2 = "Objektnummer";
    public static final String NAME_BUENDELUNSKENNZEICHEN = "Buendelungskennzeichen";
    public static final String NAME_GESCHAEFTSSTELLE_VERMITTLER = "Geschaeftsstelle / Vermittler";
    public static final String NAME_STAENDIG_BEWOHNT = "Staendig bewohnt";
    public static final String NAME_OBJEKTNUMMER = "Objektnummer";

    private final String name;
    private final String technischerName;
    private final int hash;

    // Mapping fuer manche Bezeichner (Name <--> technischer Name)
    static {
        MAPPING.put(AUSSCHLUSSDAT_VP_PERSONENGRUPPE.name, AUSSCHLUSSDAT_VP_PERSONENGRUPPE.technischerName);
        MAPPING.put(BEGINNDAT_NAECHSTEN_ERLEBENSFALL_VS.name, BEGINNDAT_NAECHSTEN_ERLEBENSFALL_VS.technischerName);
        MAPPING.put(BEGINNDAT_NAECHSTEN_JAHRESRENTE.name, BEGINNDAT_NAECHSTEN_JAHRESRENTE.technischerName);
        MAPPING.put(BEGINNDAT_NAECHSTEN_UNFALLSUMME.name, BEGINNDAT_NAECHSTEN_UNFALLSUMME.technischerName);
        MAPPING.put(DAT_BEZUGSFERTIGKEIT.name, DAT_BEZUGSFERTIGKEIT.technischerName);
        MAPPING.put(DAT_LETZTEN_BEITRAGSANGLEICHUNG.name, DAT_LETZTEN_BEITRAGSANGLEICHUNG.technischerName);
        MAPPING.put(DAT_LETZTEN_POSITIVEN_DYNAMIK.name, DAT_LETZTEN_POSITIVEN_DYNAMIK.technischerName);
        MAPPING.put(ENDEDATUM_BEI_ROTEN_KENNZEICHEN.name, ENDEDATUM_BEI_ROTEN_KENNZEICHEN.technischerName);
        MAPPING.put(ERSTELLUNGS_DAT_ZEITRAUM_VOM_ZEITRAUM_BIS.name,
                ERSTELLUNGS_DAT_ZEITRAUM_VOM_ZEITRAUM_BIS.technischerName);
        MAPPING.put(LFD_PERSONEN_NR_IM_GEVO.name, LFD_PERSONEN_NR_IM_GEVO.technischerName);
        MAPPING.put(URSPRUENGLICHES_HAFTUNGSBEGINNDAT.name, URSPRUENGLICHES_HAFTUNGSBEGINNDAT.technischerName);
        MAPPING.put(VS_NR.name, VS_NR.technischerName);
        MAPPING.put(VU_NR.name, VU_NR.technischerName);
    }

    /**
     * Legt einen neuen Bezeichner mit dem gewuenschten Name an.
     *
     * @param name der gewuenschte Name
     * @since 1.0
     */
    public Bezeichner(final String name) {
        this(name, toTechnischerName(name));
    }

    /**
     * Legt einen neuen Bezeichner mit dem gewuenschten Werten an.
     * Die Werte muessen in der uebergebenen Property unter dem Key "name"
     * und "technischerName" vorliegen.
     *
     * @param props Properties mit Name und technischer Name
     */
    public Bezeichner(final Properties props) {
        this(props.getProperty("name", ""), props.getProperty("technischerName", ""));
    }

    /**
     * Legt einen neuen Bezeichner mit dem gewuenschten Name an.
     *
     * @param name der gewuenschte Name
     * @param technischerName der entsprechende technische Name
     * @since 1.0
     */
    public Bezeichner(final String name, final String technischerName) {
        this.name = name;
        this.technischerName = StringUtils.isEmpty(technischerName) ? toTechnischerName(name) : technischerName;
        this.hash = this.technischerName.toUpperCase().hashCode();
    }

    private static String toTechnischerName(final String input) {
        String techName = MAPPING.get(input);
        if (techName != null) {
            return techName;
        }
        StringBuilder buf = new StringBuilder();
        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i++) {
            buf.append(toShortcut(words[i]));
        }
        return buf.toString();
    }

    private static String toShortcut(final String input) {
        StringBuilder converted = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            appendLetterOrDigit(converted, chars[i]);
        }
        String word = converted.toString();
        if (word.endsWith("datum")) {
            return word.substring(0, word.length() - 2);
        }
        if ("Waehrungseinheiten".equals(word)) {
            return "WE";
        }
        return WordUtils.capitalize(word);
    }

    private static void appendLetterOrDigit(StringBuilder converted, char aChar) {
        if (Character.isLetterOrDigit(aChar)) {
            String s = UmlautMapper.replaceUmlaut(aChar);
            converted.append(s);
        }
    }

    /**
     * Liefert den Namen des Bezeichners.
     *
     * @return der Name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Der technische Name leitet sich aus dem normalen Namen ab. Im
     * Gegensatz zum normalen Namen enthaelt er aber keine Leerzeichen,
     * Sonderzeichen oder Umlaute.
     * <p>
     * Der technische Name wird auch dazu verwendet, um zwei {@link Bezeichner}
     * auf Gleichheit zu testen.
     * </p>
     *
     * @return der technische Name
     */
    public String getTechnischerName() {
        return this.technischerName;
    }

    /**
     * Zum Vergleich zweier {@link Bezeichner} wird zuerst der Name und dann der
     * technische Name herangezogen.
     *
     * @param obj der andere Bezeichner
     * @return true, wenn er als gleich angesehen wird
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Bezeichner)) {
            return false;
        }
        Bezeichner other = (Bezeichner) obj;
        return this.getTechnischerName().equalsIgnoreCase(other.getTechnischerName());
    }

    /**
     * Der Hash-Code wird aus dem technischen Namen abgeleitet.
     * <p>
     * Aus Performance-Gruenden wird der hash einmal beim Anlegen ermittelt,
     * da der Bezeichner intern fuer diverse HashMaps verwendet wird.
     * </p>
     *
     * @return den berechneten Hash-Code
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.hash;
    }

    /**
     * Da der Bezeichner als Ersatz fuer die String-Klasse eingesetzt werden soll,
     * liefern wir den Namen hier zurueck.
     *
     * @return den Namen
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (this.technischerName.length() > 5) {
            return this.technischerName;
        } else  {
            return this.technischerName + " (" + this.name + ")";
        }
    }

    /**
     * Hierueber liefern wird die Konstante mit dem uebergebenen Text als
     * Feld zurueck.
     *
     * @param bezeichnung Text der gesuchten Konstanten
     * @return die entsprechende Konstante
     * @since 1.0
     */
    public static Field getField(final String bezeichnung) {
        Field[] fields = Bezeichner.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                Object value = fields[i].get(null);
                if (value == null) {
                    continue;
                }
                if (bezeichnung.equalsIgnoreCase(value.toString())) {
                    return fields[i];
                }
                if (value instanceof Bezeichner) {
                    Bezeichner bez = (Bezeichner) value;
                    if (bezeichnung.equalsIgnoreCase(bez.getName())) {
                        return fields[i];
                    }
                }
            } catch (IllegalAccessException e) {
                LOG.debug("Will ignore field {}:", fields[i], e);
            }
        }
        throw new IllegalArgumentException("no constant with text \"" + bezeichnung + "\" defined");
    }

    /**
     * Verwendet den uebergebenen Bezeichner, um den technischen Namen zu
     * aktualisieren.
     *
     * @param bezeichner the bezeichner
     * @return the bezeichner
     */
    public Bezeichner mergeWith(final Bezeichner bezeichner) {
        if (this.technischerName.equals(bezeichner.getTechnischerName())) {
            LOG.trace("Merge of {} and {} is ignored.", this, bezeichner);
            return this;
        } else {
            return bezeichner;
        }
    }

}
