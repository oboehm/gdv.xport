/*
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
//FIXME: XXX_DATUM durch XXX_DAT ersetzen, NAME_XXX_DATUM entfernen (14-Nov-2014, Oli B.)
public final class Bezeichner {

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABGANGSDATUM = "Abgangsdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABGANGSGRUND = "Abgangsgrund";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSCHLAG1_IN_PROZENT = "Abschlag-1 in %";
    /** @deprecated bitte {@link Bezeichner#ABSCHLAG1_IN_WE} verwenden */
    @Deprecated
    public static final String ABSCHLAG1_IN_WAEHRUNGSEINHEITEN = "Abschlag-1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSCHLAG2_IN_PROZENT = "Abschlag-2 in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSCHLAG2_IN_WAEHRUNGSEINHEITEN = "Abschlag-2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSCHLAG3_IN_PROZENT = "Abschlag-3 in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSCHLAG3_IN_WAEHRUNGSEINHEITEN = "Abschlag-3";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN = "Abschlagsbetrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSCHLAG_IN_PROZENT = "Abschlag in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSCHLUSSPROVISION = "Abschlussprovision";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSENDER = "Absender";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABWEICHENDER_KONTOINHABER1 = "Abweichender Kontoinhaber 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABWEICHENDER_KONTOINHABER2 = "Abweichender Kontoinhaber 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABWEICHENDE_VU_NR = "Abweichende VU-Nr.";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ADRESSAT = "Adressat";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ADRESSKENNZEICHEN = "Adresskennzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AENDERUNGSDATUM = "Aenderungsdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AENDERUNGSGRUND = "Aenderungsgrund";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AFB = "A,F,B";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AKTENZEICHEN_SICHERUNGSGLAEUBIGER = "Aktenzeichen des Sicherungsglaeubigers";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AKTUELLE_BEITRAGSDEPOTSUMME_IN_WAEHRUNGSEINHEITEN = "aktuelle Beitragsdepotsumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ALLGEMEINE_VERSICHERUNGSBEDINGUNGEN = "Allgemeine Versicherungsbedingungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AMTL_KENNZEICHEN = "Amtl. Kennzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANREDESCHLUESSEL = "Anredeschluessel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANTEIL_IN_PROZENT = "Anteil in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANTRAGSDATUM = "Antragsdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANTRAGSEINGANGSDATUM = "Antragseingangsdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANZAHL_DER_VORBESITZER = "Anzahl der Vorbesitzer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANZAHL_SAETZE = "Anzahl der Saetze";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANZAHL_VP_PRO_PERSONENGRUPPE = "Anzahl der VP pro Personengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANZAHL_WOHNEINHEITEN = "Anzahl Wohnheiten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ARB = "ARB (Allgemeine Bedingungen fuer die Rechtschutzvers.)";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ART_DER_HEILKOSTEN = "Art der Heilkosten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ART_DER_STEUERLICHEN_FOERDERUNG = "Art der steuerlichen Foerderung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ART_DER_ZULASSUNG_BEIM_VORBESITZER = "Art der Zulassung beim Vorbesitzer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ART_DES_ABSENDERS = "Art des Absenders";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ART_DES_ADRESSATEN = "Art des Adressaten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ART_DES_AMTLICHEN_KENNZEICHENS = "Art des amtlichen Kennzeichens";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ART_DES_DRITTRECHTS = "Art des Drittrechts";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AUFBAUART = "Aufbauart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AUFSICHTSFREIER_VERTRAG = "Aufsichtsfreier Vertrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AUFTEILUNG_VERSICHERUNGSSTEUER = "Aufteilung Versicherungsteuer gemaess EU-Richtlinien";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AUFTRAGSNR_VERMITTLER = "Auftrags-Nr. des Vermittlers";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AUSSCHLUSS = "Ausschluss";
    /** @deprecated bitte {@link Bezeichner#AUSSCHLUSSDAT_VP_PERSONENGRUPPE} verwenden */
    @Deprecated
    public static final String AUSSCHLUSSDATUM_VP = "Ausschlussdatum VP / Personengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BAUJAHR = "Baujahr";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BAUSTEIN_GESAMTBEITRAG_1_IN_WAEHRUNGSEINHEITEN = "Baustein-Gesamtbeitrag 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BAUSTEIN_GESAMTBEITRAG_2_IN_WAEHRUNGSEINHEITEN = "Baustein-Gesamtbeitrag 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINN = "Beginn";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINN_TAGEGELD1_AB_TAG = "Beginn Tagegeld 1 ab Tag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINN_TAGEGELD2_AB_TAG = "Beginn Tagegeld 2 ab Tag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINN_VERSICHERUNGSSCHUTZ = "Beginn Versicherungsschutz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSANGLEICHUNGSKLAUSEL = "Beitragsangleichungsklausel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSDEPOT = "Beitragsdepot";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSKLASSE = "Beitragsklasse";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSREGULIERUNG = "Beitragsregulierung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSRUECKGEWAEHR = "Beitragsrueckgewaehr";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSUMSTELLUNGSDATUM = "Beitragsumstellungsdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSUMSTELLUNGSGRUND = "Beitragsumstellungsgrund";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_BERGUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Bergungskosten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_FESTE_RENTE_IN_WAEHRUNGSEINHEITEN = "Beitrag Feste Rente";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_GENESUNGSGELD_IN_WAEHRUNGSEINHEITEN = "Beitrag Genesungsgeld";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_HEILKOSTEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Heilkosten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_INVALIDITAET_IN_WAEHRUNGSEINHEITEN = "Beitrag Invaliditaet";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_IN_WAEHRUNGSEINHEITEN = "Beitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_KOSMETISCHE_OPERATION_IN_WAEHRUNGSEINHEITEN = "Beitrag Kosmetische Operation";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_KRANKENHAUSTAGEGELD_IN_WAEHRUNGSEINHEITEN = "Beitrag Krankenhaustagegeld";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_KURKOSTEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Kurkosten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_PRO_VP_IN_WAEHRUNGSEINHEITEN = "Beitrag pro VP oder pro Personengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_SERVICELEISTUNGEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Serviceleistungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_TAGEGELD1_IN_WAEHRUNGSEINHEITEN = "Beitrag Tagegeld 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_TAGEGELD2_IN_WAEHRUNGSEINHEITEN = "Beitrag Tagegeld 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_TOD_IN_WAEHRUNGSEINHEITEN = "Beitrag Tod";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_UEBERGANGSENTSCHAEDIGUNG_IN_WAEHRUNGSEINHEITEN = "Beitrag Uebergangsentschaedigung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BERGUNGSKOSTEN = "Bergungskosten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BERGUNGSKOSTEN_BEITRAGSSATZ = "Bergungskosten Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BERUFSGRUPPENEINTEILUNG = "Berufsgruppeneinteilung im Industrie-Straf-RS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BERUFSSCHLUESSEL = "Berufsschluessel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BERUF_TEXT = "Beruf-Text";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BESONDERER_VERWENDUNGSZWECK = "besonderer Verwendungszweck";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BESONDERE_VEREINBARUNG_ZUM_FLUGGASTRISIKO = "Besondere Vereinbarung zum Fluggastrisiko";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BESTANDSFUEHRENDE_GESCHAEFTSSTELLE = "Bestandsfuehrende Geschaeftsstelle";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BESTANDSPFLEGEPROVISION = "Bestandspflegeprovision";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BETRIEBLICHE_ALTERSVORSORGE = "Betriebliche Altersvorsorge";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEZEICHNUNG_PERSONENGRUPPE = "Bezeichnung Personengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEZUGSBERECHTIGT_IM_LEISTUNGSFALL = "Bezugsberechtigt im Leistungsfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEZUGSRECHTANTEIL_IM_LEISTUNGSFALL = "Bezugsrechtanteil im Leistungsfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BIC1 = "BIC 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BIC2 = "BIC 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BILANZMONAT_ARBEITGEBER = "Bilanzmonat Arbeitgeber";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BLZ1 = "Bankleitzahl 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BLZ2 = "Bankleitzahl 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BUENDELUNGSKENNZEICHEN = "Buendelungskennzeichen";
    /** @deprecated bitte {@link Bezeichner#DAT_BEZUGSFERTIGKEIT} verwenden */
    @Deprecated
    public static final String DATUM_DER_BEZUGSFERTIGKEIT = "Datum der Bezugsfertigkeit";
    /** @deprecated bitte {@link Bezeichner#DAT_LETZTEN_BEITRAGSANGLEICHUNG} verwenden */
    @Deprecated
    public static final String DATUM_LETZTE_BEITRAGSANGLEICHUNG = "Datum der letzten Beitragsangleichung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DECKUNGSSUMME_IN_TSD_WAEHRUNGSEINHEITEN = "Deckungssumme in Tausend Waehrungseinheiten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DECKUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Deckungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DECKUNGSUMFANG = "Deckungsumfang";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DIENSTEINTRITTSDATUM = "Diensteintrittsdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DRUCKAUFBEREITETE_VERSICHERUNGSSCHEINNUMMER = "Druckaufbereitete Versicherungsscheinnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DURCHFUEHRUNGSWEG = "Durchfuehrungsweg";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DYNAMIK = "Dynamik";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DYNAMIK_IN_PROZENT = "Dynamik in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String EIGENTUMSVERHAELTNIS_FAHRZEUG = "Eigentumsverhaeltnis (Fahrzeug)";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String EINSCHLUSSDATUM_VP = "Einschlussdatum VP / Personengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String EINZAHLUNG_AUSSCHUETTUNG = "Einzahlung / Ausschuettung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ENDEDATUM_DES_VERSICHERUNGSSCHUTZES_BEI_ROTEN_KENNZEICHEN = "Endedatum des Versicherungsschutzes bei roten Kennzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERHOEHUNGSART_DYNAMIK = "Erhoehungsart Dynamik";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERSTELLUNGSDATUM_ZEITRAUM_BIS = "Erstellungs-Datum-Zeitraum bis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERSTELLUNGSDATUM_ZEITRAUM_VOM = "Erstellungs-Datum-Zeitraum vom";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERSTE_ZULASSUNG_AUF_DEN_VN = "Erste Zulassung auf den VN";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERSTZULASSUNG = "Erstzulassung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERWEITERUNGSSATZ_VORHANDEN = "Erweiterungssatz vorhanden";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String EVB_NUMMER = "eVB-Nummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FAHRZEUGART = "Fahrzeugart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FAHRZEUGIDENTIFIZIERUNGSNUMMER = "Fahrzeugidentifizierungsnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FAMILIENSTAND = "Familienstand";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FESTE_RENTE = "Feste Rente";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FESTE_RENTE_BEITRAGSSATZ = "Feste Rente Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FINANZIERUNGSART = "Finanzierungsart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FINANZIERUNG_ZUSAGE = "Finanzierung der Zusage";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FLOTTENKENNZEICHEN = "Flottenkennzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FLOTTENRABATT_IN_PROZENT = "Flottenrabatt in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FOLGENUMMER = "Folgenummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FOLGEPROVISION = "Folgeprovision";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FREMDER_GRUND_UND_BODEN = "fremder Grund und Boden";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FREMDNUTZUNG = "Fremdnutzung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_KH = "Frei vereinbarte Selbstbeteiligung fuer KH";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO = "Frei vereinbarte Selbstbeteiligung fuer Teilkasko";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO_IM_RAHMEN_DER_VOLLKASKO = "Frei vereinbarte Selbstbeteiligung fuer Teilkasko im Rahmen der Vollkasko";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_VOLLKASKO = "Frei vereinbarte Selbstbeteiligung fuer Vollkasko";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GAP_DECKUNG = "GAP-Deckung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GARAGE = "Garage";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GEBURTSDATUM = "Geburtsdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GEBURTSLAND = "Geburtsland";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GEBURTSNAME = "Geburtsname";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GEBURTSORT = "Geburtsort";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GEFAHRENGRUPPE = "Gefahrengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GEFAHRGUT = "Gefahrgut";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GELTUNGSBEREICH = "Geltungsbereich";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GELTUNGSBEREICHEINSCHRAENKUNG = "Geltungsbereicheinschraenkung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GENESUNGSGELD = "Genesungsgeld";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GENESUNGSGELD_BEITRAGSSATZ = "Genesungsgeld Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESAMTBEITRAG = "Gesamtbeitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESAMTBEITRAG_BRUTTO = "Gesamtbeitrag-Brutto(Inkasso)";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN = "Gesamtbeitrag (Brutto)";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN = "Gesamtbeitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN = "Gesamtbeitrag (Netto)";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESAMTMASSE = "Gesamtmasse";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESAMTPROVISIONSBETRAG = "Gesamtprovisions-Betrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESCHLECHT = "Geschlecht";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GROSSRISIKEN = "Aufsichtsfreier Versicherungsnehmer (Grossrisiken)";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GRUPPENART = "Gruppenart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GUELTIGE_AKB = "Gueltige AKB";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GUELTIGKEITSDAUER_IN_TAGEN_BEI_KURZZEITKENNZEICHEN = "Gueltigkeitsdauer in Tagen bei Kurzzeitkennzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String HAUPTFAELLIGKEIT = "Hauptfaelligkeit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String HEILKOSTEN = "Heilkosten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String HEILKOSTEN_BEITRAGSSATZ = "Heilkosten Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String HERSTELLERNAME = "Herstellername";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String HERSTELLER_SCHLUESSEL_NR = "Hersteller-Schluessel-Nr.";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String IBAN1 = "IBAN 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String IBAN2 = "IBAN 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String INKASSOART = "Inkassoart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String INVALIDITAET = "Invaliditaet";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String INVALIDITAET_BEITRAGSSATZ = "Invaliditaet Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String JAEHRLICHE_FAHRLEISTUNG = "Jaehrliche Fahrleistung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KAPITALERTRAGSTEUERPFLICHT = "Kapitalertragsteuerpflicht";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KASKO_BEGINNJAHR = "Kasko-Beginnjahr";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KAUFPREIS = "Kaufpreis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNUNG_FUER_ABS_RABATT = "Kennung fuer ABS-Rabatt";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNUNG_GLIEDERTAXE = "Kennung Gliedertaxe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNUNG_PROGRESSIVE_INVALIDITAET = "Kennung progressive Invaliditaet";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNUNG_UEBERGANGSENTSCHAEDIGUNG = "Kennung Uebergangsentschaedigung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION = "Kennzeichen fuer abweichende Abschlussprovision";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNZEICHEN_ABWEICHENDE_BESTANDSPFLEGEPROVISION = "Kennzeichen fuer abweichende Bestandspflegeprovision";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNZEICHEN_ABWEICHENDE_FOLGEPROVISION = "Kennzeichen fuer abweichende Folgeprovision";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNZEICHEN_ABWEICHENDE_VU_NR = "Kennzeichen zur Erlaeuterung der abweichenden VU-Nr.";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNZEICHEN_FUER_ABWEICHENDE_PROVISION = "Kennzeichen fuer abweichende Provision";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNZEICHEN_VERS_STEUER_FREI = "Kennzeichen Vers.-Steuer frei";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNZEICHEN_VERTRAGSENTSTEHUNG = "Kennzeichen Vertragsentstehung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFT_ABSCHLAEGE_IN_PROZENT = "KFT-Abschlaege in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFT_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFT-Abschlaege";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFT_AENDERUNGSDATUM = "KFT-aenderungsdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFT_AUSSCHLUSS = "KFT-Ausschluss";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFT_BEGINN = "KFT-Beginn";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFT_BEITRAG_IN_WAEHRUNGSEINHEITEN = "KFT-Beitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFT_DECKUNGSART = "KFT-Deckungsart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFT_TARIFGRUPPE = "KFT-Tarifgruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFT_ZUSCHLAEGE_IN_PROZENT = "KFT-Zuschlaege in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFT_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFT-Zuschlaege";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_ABSCHLAEGE_IN_PROZENT = "KFV-Abschlaege in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFV-Abschlaege";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_AENDERUNGSDATUM = "KFV-aenderungsdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_AUSSCHLUSS = "KFV-Ausschluss";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_BEGINN = "KFV-Beginn";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_BEITRAGSSATZ = "KFV-Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_BEITRAG_IN_WAEHRUNGSEINHEITEN = "KFV-Beitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_DECKUNGSART = "KFV-Deckungsart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_RGJ = "KFV-RGJ";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_SCHAEDEN_AUS_RUECKSTUFUNG = "KFV-Schaeden aus Rueckstufung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_SFS_KLASSE = "KFV-SF/S-Klasse";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_TARIFGRUPPE = "KFV-Tarifgruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_ZUSCHLAEGE_IN_PROZENT = "KFV-Zuschlaege in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KFV_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFV-Zuschlaege";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_ABSCHLAEGE_IN_PROZENT = "KH-Abschlaege in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KH-Abschlaege";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_AENDERUNGSDATUM = "KH-aenderungsdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_AUSSCHLUSS = "KH-Ausschluss";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_BEGINN = "KH-Beginn";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_BEITRAGSSAETZE = "KH-Beitragssaetze";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_BEITRAG_IN_WAEHRUNGSEINHEITEN = "KH-Beitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_DECKUNGSART = "KH Deckungsart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_DECKUNGSSUMMEN = "KH-Deckungssummen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN = "KH-Deckungssummen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL1 = "KH-Deckungssummen Teil 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL2 = "KH-Deckungssummen Teil 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL3 = "KH-Deckungssummen Teil 3";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_DECKUNGSSUMMEN_PERSONENSCHAEDEN = "KH-Deckungssummen Personenschaeden";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_DECKUNGSSUMMEN_SACHSCHAEDEN = "KH-Deckungssummen Sachschaeden";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_DECKUNGSSUMMEN_VERMOEGENSCHAEDEN = "KH-Deckungssummen Vermoegensschaeden";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_RGJ = "KH-RGJ";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_SCHAEDEN_AUS_RUECKSTUFUNG = "KH-Schaeden aus Rueckstufung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_SF_S_KLASSE = "KH-SF/S-Klasse";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_TARIFGRUPPE = "KH-Tarifgruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_ZUSCHLAEGE_IN_PROZENT = "KH-Zuschlaege in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KH_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KH-Zuschlaege";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KLARTEXT_SICHERUNGSEINRICHTUNG = "Klartext Sicherungseinrichtung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOLLEKTIV_NR = "Kollektiv-Nr.";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSNR1 = "Kommunikationsnummer 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSNR2 = "Kommunikationsnummer 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSNR3 = "Kommunikationsnummer 3";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSNR4 = "Kommunikationsnummer 4";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSNR5 = "Kommunikationsnummer 5";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSNR6 = "Kommunikationsnummer 6";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSNR7 = "Kommunikationsnummer 7";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSTYP1 = "Kommunikationstyp 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSTYP2 = "Kommunikationstyp 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSTYP3 = "Kommunikationstyp 3";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSTYP4 = "Kommunikationstyp 4";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSTYP5 = "Kommunikationstyp 5";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSTYP6 = "Kommunikationstyp 6";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOMMUNIKATIONSTYP7 = "Kommunikationstyp 7";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KONTONR1 = "Kontonummer 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KONTONR2 = "Kontonummer 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOSMETISCHE_OPERATIONEN = "Kosmetische Operationen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KOSMETISCHE_OPERATIONEN_BEITRAGSSATZ = "Kosmetische Operationen Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KRANKENHAUSTAGEGELD = "Krankenhaustagegeld";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KRANKENHAUSTAGEGELD_BEITRAGSSATZ = "Krankenhaustagegeld Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KREISGEMEINDESCHLUESSEL = "Kreisgemeindeschluessel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KREISGEMEINDESCHLUESSEL_ZUSATZINFORMATION = "Kreisgemeindeschluessel Zusatzinformation";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KUENDIGUNGSKLAUSEL = "Kuendigungsklausel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KUENDIGUNGSKLAUSEL_VP = "Kuendigungsklausel VP / Personengruppe gestrichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KUNDENGRUPPE = "Kundengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KUNDENNR_VERMITTLER = "Personen-/Kundennummer des Vermittlers";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KUNDENNR_VERSICHERER = "Personen-/Kundennummer des Versicherers";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KURKOSTEN = "Kurkosten";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KURKOSTEN_BEITRAGSSATZ = "Kurkosten Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LAENDERKENNZEICHEN = "Laenderkennzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LAND_DES_AMTL_KENNZEICHENS = "Land des amtl. Kennzeichens";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LAUFZEITRABATT_IN_PROZENT = "Laufzeitrabatt in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEERSTELLEN = "Leerstellen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEERSTELLEN1 = "Leerstellen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEERSTELLEN2 = "Leerstellen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEERSTELLEN3 = "Leerstellen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEERSTELLEN4 = "Leerstellen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEERSTELLEN5 = "Leerstellen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LETZTE_ERHOEHUNG = "letzte Erhoehung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER = "Lfd. Nummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER1 = "Lfd. Nummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER2 = "Lfd. Nummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_SATZART = "Lfd. Nummer der Satzart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_VP = "Lfd. Nummer der versicherten Person (VP)";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_VP_PERSONENGRUPPE = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_VP_PERSONENGRUPPE1 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_VP_PERSONENGRUPPE2 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_VP_PERSONENGRUPPE3 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_VP_PERSONENGRUPPE4 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_VP_PERSONENGRUPPE9 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated bitte {@link Bezeichner#LFD_PERSONEN_NR_IM_GEVO} verwenden */
    @Deprecated
    public static final String LFD_PERSONENNR_GEVO = "Lfd. Personennummer im GeVo";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_PERSONENNUMMER_DES_SICHERUNGSGLAEUBIGERS = "Lfd. Personennummer des Sicherungsglaeubigers";
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
    public static final String POLICIERUNGSDATUM = "Policierungsdatum";
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
    public static final String VERSION_SATZART_0210_TECH_VERS = "Version Satzart 0210 Technische Versicherungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSION_SATZART_0220_TECH_VERS = "Version Satzart 0220 Technische Versicherungen";
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
    public static final String ZUZAHLUNGSDATUM = "Zuzahlungsdatum";
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
    public static final String INTERNES_ORDNUNGSMERKMAL_DES_VM = "Internes Ordnungsmerkmal des VM";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FAELLIGKEIT_DER_LETZTEN_BEITRAGSZAHLUNG = "Faelligkeit der letzten Beitragszahlung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEBENSLANGE_BEITRAGSZAHLUNG = "Lebenslange Beitragszahlung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BESONDERE_VEREINBARUNGEN = "Besondere Vereinbarungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DIREKTANSPRUCH = "Direktanspruch";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String EINTRITTSALTER_DER_VP = "Eintrittsalter der VP";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ALTERSGRUPPE = "Altersgruppe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSZAHLUNG_BIS = "Beitragszahlung bis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RUECKGEWAEHRDATUM = "Rueckgewaehrdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RUECKGEWAEHRSUMME_ZUM_ABLAUF_IN_WAEHRUNGSEINHEITEN = "Rueckgewaehrsumme zum Ablauf";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABLAUFLEISTUNG_INKL_UEBERSCHUSSANTEILE_IN_WAEHRUNGSEINHEITEN = "Ablaufleistung incl. Ueberschussanteile";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KAPITALERTRAGSSTEUER_BEI_ABLAUF = "Kapitalertragssteuer bei Ablauf";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SOLIDARITAETSZUSCHLAG_BEI_ABLAUF = "Solidarit\u00e4tszuschlag bei Ablauf";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RUECKKAUFSWERT_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = "Rueckkaufswert zum Berechnungsstichtag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BERECHNUNGSSTICHTAG_ZUM_RUECKKAUFSWERT = "Berechnungsstichtag zum Rueckkaufswert";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KAPITALERTRAGSSTEUER_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG = "Kapitalertragssteuer bei Rueckkauf zum Berechnungsstichtag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SOLIDARITAETSZUSCHLAG_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG = "Solidarit\u00e4tszuschlag bei Rueckkauf zum Berechnungsstichtag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UEBERSCHUSSANTEILE_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = "Ueberschussanteile zum Berechnungsstichtag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GEBURTSDATUM_DER_BEZUGSBERECHTIGTEN_PERSON = "Geburtsdatum der bezugsberechtigten Person";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANZAHL_DER_VERSICHERTEN_PERSONEN = "Anzahl der versicherten Personen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DATUM_UNVERFALLBARKEIT = "Datum Unverfallbarkeit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DIENSTEINTRITT = "Diensteintritt";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GEBURTSDATUM_VP = "Geburtsdatum der VP";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GEBURTSDATUM_VP2 = "Geburtsdatum der VP2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESCHLECHT_VP = "Geschlecht der VP";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_ZUR_WAGNISART = "Lfd Nummer zur Wagnisart";
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
    public static final String ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = "Erlebensfall VS zum Ablauf";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TODESFALL_VS_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = "Todesfall VS zum Ablauf";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = "Erlebensfall VS II zum Ablauf";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSFREIE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Beitragsfreie Erlebensfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG = "Absoluter Dynamikerhoehungsbetrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = "Erlebensfall VS zum Beginn der Abrufphase";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TODESFALL_VS_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = "Todesfall VS zum Beginn der Abrufphase";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = "Erlebensfall VS II zum Beginn der Abrufphase";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANFAENGLICHE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Anfaengliche Erlebensfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AENDERUNG_DER_ERLEBENSFALL_VS = "Aenderung der Erlebensfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSTAND_DER_ERLEBENSFAL_VS_AENDERUNGSTERMINE = "Abstand der Erlebensfall VS-Aenderungstermine";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERLEBENSFALL_VS_AENDERUNGS_PROZENTSATZ = "Erlebensfall VS-Aenderungs-Prozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSOLUTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Absolute Erlebensfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Naechste Erlebensfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINNDATUM_DER_NAECHSTEN_ERLEBENSFALL_VS = "Beginndatum der naechsten Erlebensfall VS";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_BU_IN_WAEHRUNGSEINHEITEN = "Beitrag BU";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String EINSCHLUSS_PROZENT_SATZ = "Einschluss %-Satz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZWANG_ZUR_BUZ = "Zwang zur BUZ";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEISTUNGSDAUER = "Leistungsdauer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BUZ_VERWENDUNGSART = "BUZ Verwendungsart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RENTE_INCL_UEBERSCHUSSBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = "Rente inkl. Ueberschussbeteiligung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BUZ_LEISTUNG_VON = "BUZ Leistung von";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BUZ_LEISTUNG_BIS = "BUZ Leistung bis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BUZ_PROZENT_SATZ = "BUZ %-Satz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KARENZZEIT = "Karenzzeit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABWEICHENDE_LEISTUNGSDAUER = "Abweichende Leistungsdauer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZUKUENFTIGER_BEITRAG = "Zukuenftiger Beitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAERHUNGSEINHEITEN = "Absoluter Dynamikerhoehungsbetrag";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT = "Erlebensfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TODESFALL_VS_IN_WAEHRUNGSEINHEIT = "Todesfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERLEBENSFALL_VS2_IN_WAEHRUNGSEINHEIT = "Erlebensfall VS II";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSFREIE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT = "Beitragsfreie Erlebensfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSFREIE_TODESFALL_VS_IN_WAEHRUNGSEINHEIT = "Beitragsfreie Todesfall VS";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Versicherungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FALLENDE_SUMME = "fallende Summe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RENTE_INKL_UEBERSCHUSSANRECHNUNG_IN_WAEHRUNGSEINHEITEN = "Rente inkl. Ueberschussanrechnung";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSOLUTE_UNFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Unfallaenderungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTE_UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = "Naechste Unfallsumme";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANFAENGLICHE_UNFALLSUMME = "Anfaengliche Unfallsumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AENDERUNG_DER_UNFALLLEISTUNG = "Aenderung der Unfallleistung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSTAND_DER_UNFALLAENDERUNGSTERMINE = "Abstand der Unfallaenderungstermine";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNFALLAENDERUNGS_PROZENTSATZ = "Unfallaenderungs-Prozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINNDATUM_DER_NAECHSTEN_UNFALLSUMME = "Beginndatum der naechsten Unfallsumme";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG_PROMILLE = "Beitrag Promille";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = "Unfallsumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOZUSCHLAG = "Risikozuschlag";

    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_DES_FONDS = "Lfd. Nummer des Fonds";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WERTPAPIERKENNNUMMER = "Wertpapierkennnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FONDSKENNUNG = "Fondskennung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ISIN_NUMMER = "ISIN-Nummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FONDSNAME = "Fondname";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String THESAUR = "Thesaur";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANTEILE = "Anteile";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEZUGSDATUM = "Bezugsdatum";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PROZENTSATZ = "Prozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINNDATUM_DER_NAECHSTEN_JAHRESRENTESUMME = "Beginndatum der naechsten Jahresrentensumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GUTHABEN_DIVID_ANSAMMLUNG_IN_WAEHRUNGSEINHEITEN = "Guthaben Divid. Ansammlung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN2 = "Nettobeitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GARANTIERTE_FONDSJAHRESRENTE_ZUM_ABLAUF_INKL_ABRUFPHASE = "Garantierte Fondsjahresrente zum Ablauf (inkl. Abrufphase)";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GARANTIERTE_FONDSJAHRESRENTE_ZUM_BEGINN_DER_ABRUFPHASE = "Garantierte Fondsjahresrente zum Beginn der Abrufphase";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AKTUELLE_FONDSJAHRESRENTE_ZUM_ABLAUF_INKL_ABRUFPHASE = "Aktuelle Fondsjahresrente zum Ablauf (inkl. Abrufphase)";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AKTUELLE_FONDSJAHRESRENTE_ZUM_BEGINN_DER_ABRUFPHASE = "Aktuelle Fondsjahresrente zum Beginn der Abrufphase";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNISART4 = "Wagnisart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_ZUR_WAGNISART4 = "Lfd. Nummer zur Wagnisart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSOLUTE_TODESFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Todesfallaenderungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AENDERUNG_DER_BEITRAGSSUMME = "Aenderung der Beitragssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSTAND_DER_BEITRAGSSUMMENAENDERUNGSTERMINE = "Abstand der Beitragssummenaenderungstermine";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSSUMMENAENDERUNGS_PROZENTSATZ = "Beitragssummenaenderungs-Prozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSOLUTE_BEITRAGSSUMMENAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Beitragssummenaenderungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Naechste Beitragssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINNDATUM_DER_NAECHSTEN_BEITRAGSSUMME = "Beginndatum der naechsten Beitragssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Beitragssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Todesfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String FALLENDE_VS = "fallende VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEITEN = "Erlebensfall VS II";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSFREIE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Beitragsfreie Beitragssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSFREIE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Beitragsfreie Todesfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LAUFZEITVERKUERZUNG = "Laufzeitverkuerzung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERTRAG_MIT_ZUWACHSGARANTIE = "Vertrag mit Zuwachsgarantie";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TODESFALLLEISTUNG_IN_PROZENT = "Todesfallleistung in %";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SPARVORGANG = "Sparvorgang";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESTUNDET_AUSGESETZT_BIS = "Gestundet ausgesetzt bis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANLAGESTRATEGIE = "Anlagestrategie";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Erlebensfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GUTHABEN_DIVID_ANSAMMLUNG_IN_WAEHRUNGSEINHEIT = "Guthaben Divid. Ansammlung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KAPITALZAHLUNGS_SUMME_IN_WAEHRUNGSEINHEIT = "Kapitalzahlungs-Summe";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANFAENGLICHE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Anfaengliche Todesfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AENDERUNG_DER_TODESFALLLEISTUNG = "Aenderung der Todesfallleistung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSTAND_DER_TODESFALLAENDERUNGSTERMINE = "Abstand der Todesfallaenderungstermine";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TODESFALLAENDERUNGS_PROZENTSATZ = "Todesfallaenderungs-Prozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSOLUTE_TODESFALLAENDERUNGSSUMME_VS_IN_WAEHRUNGSEINHEITEN = "Absolute Todesfallaenderungssumme VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Naechste Todesfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINNDATUM_DER_NAECHSTEN_TODESFALL_VS = "Beginndatum der naechsten Todesfall VS";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANFAENGLICHE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = "Anfaengliche Jahresrente";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AENDERUNG_DER_JAHRESRENTE = "Aenderung der Jahresrente";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSTAND_DER_JAHRESRENTENAENDERUNGSTERMINE = "Abstand der Jahresrentenaenderungstermine";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String JAHRESRENTENAENDERUNGS_PROZENTSATZ = "Jahresrentenaenderungs-Prozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSOLUTE_JAHRESRENTENAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Jahresrentenaenderungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String NAECHSTE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = "Naechste Jahresrente";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINNDATUM_DER_NAECHSTEN_JAHRESRENTE = "Beginndatum der naechsten Jahresrente";
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
    public static final String BUCHUNGSKENNZEICHEN = "Buchungskennzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Haftungswertungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String HAFTUNG_AB = "Haftung ab";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String HAFTUNG_BIS = "Haftung bis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String URSPRUENGLICHES_HAFTUNGSBEGINNDATUM = "Urspruengliches Haftungsbeginndatum";
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
    public static final String BUCHUNGSKENNZEICHEN2 = "Buchungskennzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Haftungswertungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String HAFTUNG_AB2 = "Haftung ab";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String HAFTUNG_BIS2 = "Haftung bis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEZUGSBERECHTIGT_IM_ERLEBENSFALL = "Bezugsberechtigt im Erlebensfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SONSTIGER_BEZUGSBERECHTIGTER_IM_ERLEBENSFALL = "Sonstiger Bezeugsberechtigter im Erlebensfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEZUGSRECHTANTEIL_IM_ERLEBENSFALL = "Bezugsrechtanteil im Erlebensfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNWIDERRUFLICHES_BEZUGSRECHT_IM_ERLEBENSFALL = "Unwiderrufliches Bezugsrecht im Erlebensfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEZUGSBERECHTIGT_IM_TODESFALL = "Bezugsberechtigt im Todesfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SONSTIGER_BEZUGSBERECHTIGTER_IM_TODESFALL = "Sonstiger Bezugsberechtigter im Todesfall";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEZUGSRECHTANTEIL_IM_TODESFALL = "Bezugsrechtanteil im Todesfall";
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
    public static final String AUSZAHLUNGSWEISE = "Auszahlungsweise";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANZAHL_DER_AUSZAHLUNGEN = "Anzahl der Auszahlungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABLAUF = "Ablauf";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String AENDERUNG = "Aenderung";
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
    public static final String GEWINNVERWENDUNGSART = "Gewinnverwendungsart";
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
    public static final String DYNAMIK_PROZENT_SATZ = "Dynamik %-Satz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERHOEHUNGSBASIS_DYNAMIK = "Erhoehungsbasis Dynamik";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DYNAMIKSTOP = "Dynamikstop";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DATUM_DER_LETZTEN_POSITIVEN_DYNAMIK = "Datum der letzten positiven Dynamik";
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
    public static final String GUTHABEN_DIVID_ANSAMMLUNGEN_IN_WAEHRUNGSEINHEITEN = "Guthaben Divid. Ansammlungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINN_DER_RENTENZAHLUNG = "Beginn der Rentenzahlung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String MINDESTLAUFZEIT = "Mindestlaufzeit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RUECKGEWAEHR_BEI_TOD = "Rueckgewaehr bei Tod";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = "Jahresrente";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Kapitalzahlungssumme";
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
    public static final String AUSLOESUNG_DER_LEISTUNG = "Ausloesung der Leistung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ZAHLUNG_DER_WITWEN_WITWERRENTE_BIS = "Zahlung der Witwen- / Witwerrente bis";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ENDALTER = "Endalter";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String EINTRITTSALTER = "Eintrittsalter";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UMTAUSCHRECHT = "Umtauschrecht";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STUNDUNG = "Stundung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINN_ABRUFPHASE = "Beginn Abrufphase";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNISART2 = "Wagnisart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_ZUR_WAGNISART2 = "Lfd. Nummer Wagnisart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABWEICHENDE_VERTRAGSLAUFZEIT = "Abweichende Vertragslaufzeit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABWEICHENDER_ABLAUF = "Abweichender Ablauf";
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
    public static final String GEBURTSDATUM_DER_MITZUVERSICHERNDEN_PERSON = "Geburtsdatum der mitzuversichernden Person";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESCHLECHT_DER_MITZUVERSICHERNDEN_PERSON = "Geschlecht der mitzuverschernden Person";
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
    public static final String DYNAMIKBEGINN = "Dynamikbeginn";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABWEICHENDES_DYNAMIKENDALTER = "Abweichendes Dynamikalter";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAEHRUNGSEINHEITEN = "Absoluter Dynamikerhoehungsbetrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANTEILIGER_DYNAMIKPROZENTSATZ = "Anteiliger Dynamikprozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VEREINBARTER_DYNAMIKMINDESTANPASSUNGSPROZENTSATZ = "Vereinbarter Dynamikmindestanpassungsprozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VEREINBARTER_DYNAMIKMAXIMALANPASSUNGSPROZENTSATZ = "Vereinbarter Dynamikmaximalanpassungsprozentsatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANZAHL_VERBLEIBENDE_DYNAMIKWIDERSPRUECHE = "Anzahl verbleibende Dynamikwidersprueche";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAGNISART3 = "Wagnisart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_ZUR_WAGNISART3 = "Lfd. Numemr Wagnisart";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEISTUNG_BEI_SCHWERER_ERKRANKUNG = "Leistung bei schwerer Erkrankung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String VERSICHERTE_ERKRANKUNGEN = "Versicherte Erkrankungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEISTUNGSBEGINN_AB = "Leistungsbeginn ab";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String JAHRESRENTE_IN_WAEHRUNGSEINHEITEN2 = "Jahresrente";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Kapitalzahlungssumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TEILKAPITALISIERUNG = "Teilkapitalisierung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TODESFALLLEISTUNG_IN_WAEHRUNGSEINHEITEN = "Todesfallleistung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String EINGERECHNETE_ZULAGE = "Eingerechnete Zulage";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String EINRECHNUNGSJAHR = "Einrechnungsjahr";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TEILDATENSATZNUMMER = "Teildatensatznummer";

    // Haftpflicht
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNZEICHEN_FUER_ABWEICHENDE_ABSCHLUSSPROVISION = "Kennzeichen fuer abweichende Abschlussprovision";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNZEICHEN_FUER_ABWEICHENDE_FOLGEPROVISION = "Kennzeichen fuer abweichende Folgeprovision";
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
    public static final String BEITRAG_JE_BERECHNUNGSEINHEIT_UND_MENGENSCHLUESSEL = "Beitrag je Berechnungseinheit und Mengenschluessel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BERECHNUNGSEINHEIT = "Berechnungseinheit";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DECKUNGSSUMME_1_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 1 in Tausend";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DECKUNGSSUMME_2_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 2 in Tausend";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DECKUNGSSUMME_3_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 3 in Tausend";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DECKUNGSSUMME_4_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 4 in Tausend";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERHOEHUNGSSATZ_8_III_AHB = "Erhoehungssatz \u00a78, III AHB";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNUNG_ERHOEHUNGSSATZ = "Kennung Erhoehungssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String KENNZEICHEN_FUER_JAHRES_MAXIMIERUNG = "Kennzeichen fuer Jahres-Maximierung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LETZTE_ERHOEHUNG_8_III_AHB = "Letzte Erhoehung \u00a78, III AHB";
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
    public static final String DECKUNGSSUMME_1_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 1";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DECKUNGSSUMME_2_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 2";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DECKUNGSSUMME_3_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 3";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DECKUNGSSUMME_4_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 4";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ORDNUNGS_NUMMER_FUER_WAGNISZUATZ = "Ordungs-Nummer fuer Wagniszusatz";

    // Unfall
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String REFERENZ_VERSICHERUNGSSCHEINNUMME = "Referenz-Versicherungsscheinnumme";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String DAUERSCHAEDEN_KOERPERLICHE_BEEINTRAECHTIGUNGEN = "Dauerschaeden / koerperliche Beeintraechtigungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERKRANKUNGEN = "Erkrankungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNFAELLE = "Unfaelle";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ART_DER_AUSZAHLUNG = "Art der Auszahlung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ART_DER_LEISTUNG = "Art der Leistung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ART_DES_BEITRAGSSATZES = "Art des Beitragssatzes";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEGINN_DER_ZAHLUNG_AB_TAG = "Beginn der Zahlung ab Tag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAG = "Beitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEITRAGSSATZ = "Beitragssatz";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEZEICHNUNG_DER_LEISTUNG = "Bezeichnung der Leistung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEISTUNG = "Leistung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEISTUNG_AB_INVALIDITAETSGRAD_IN_PROZENT = "Leistung ab Invaliditaetsgrad in Prozent";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LEISTUNGSZAHLUNGSWEISE = "Leistungszahlungsweise";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LFD_NUMMER_ZUR_ART_DER_LEISTUNG = "Laufende Nummer zur Art der Leistung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String PROZENTSATZ_PROGRESSIVE_INVALIDITAET_MEHRLEISTUNG_BEI_INVALIDITAET = "Prozentsatz progressive Invaliditaet / Mehrleistung bei Invaliditaet";

    // Verbundene Hausrat
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String INTRO1 = "Intro";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String LAENDERKENNZEICHEN_DER_RISIKOANSCHRIFT = "Laenderkennzeichen der Risikoanschrift";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WAEHRUNGSSCHUESSEL = "Waehrungsschluessel";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ABSCHLAGSBEITRAG_IN_WAEHRUNGSEINHEITEN = "Abschlagsbeitrag";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESAMTVERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Gesamtversicherungssummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BEDINGUNGEN = "Bedingungen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ERWEITERTE_NEUWERTVERSICHERUNG = "erweiterte Neuwertversicherung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String SICHERUNGSRICHTLINIEN = "Sicherungsrichtlinien";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String EINBRUCH_MELDEANLAGE = "Einbruch Meldeanlage";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String RISIKOKENNZIFFER = "Risikokennziffer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String ANZAHL_MONATE_UNBEWOHNT = "Anzahl Monate unbewohnt";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String WOHNFLAECHE_QM = "Wohnflaeche qm";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String TARIFZONE = "Tarifzone";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BAUARTKLASSE = "Bauartklasse";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GEFAHRENERHOEHUNG = "Gefahrenerhoehung";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String EINSCHLUSS_VANDALISMUS = "Einschluss Vandalismus";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String UNTERVERS_VERZICHT = "Untervers.-Verzicht";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String OBJEKTNUMMER2 = "Objektnummer";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String BUENDELUNSKENNZEICHEN = "Buendelungskennzeichen";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String GESCHAEFTSSTELLE_VERMITTLER = "Geschaeftsstelle / Vermittler";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String STAENDIG_BEWOHNT = "Staendig bewohnt";
    /** @deprecated bitte Konstante mit NAME_-Prefix verwenden */
    @Deprecated
    public static final String OBJEKTNUMMER = "Objektnummer";

    /////////// Bezeichner-Konstanten /////////////////////////////////////////

    /** Die Konstante AUSSCHLUSSDAT_VP_PERSONENGRUPPE. */
    public static final Bezeichner AUSSCHLUSSDAT_VP_PERSONENGRUPPE = new Bezeichner(
            "Ausschlussdatum VP / Personengruppe", "AusschlussdatVpPersonengruppe");

    /** Die Konstante ABSCHLAG1_IN_WE. */
    public static final Bezeichner ABSCHLAG1_IN_WE = new Bezeichner("Abschlag-1 in Waehrungseinheiten", "Abschlag1InWE");

    /** Die Konstante DAT_BEZUGSFERTIGKEIT. */
    public static final Bezeichner DAT_BEZUGSFERTIGKEIT = new Bezeichner("Datum der Bezugsfertigkeit", "DatBezugsfertigkeit");

    /** Die Konstante DAT_BEZUGSFERTIGKEIT. */
    public static final Bezeichner DAT_LETZTEN_BEITRAGSANGLEICHUNG = new Bezeichner("Datum der letzten Beitragsangleichung", "DatLetztenBeitragsangleichung");

    /** Die Konstante LFD_PERSONEN_NR_IM_GEVO. */
    public static final Bezeichner LFD_PERSONEN_NR_IM_GEVO = new Bezeichner ("Lfd. Personennummer im GeVo", "LfdPersonenNrImGevo");

    /** Die Konstante VS_NR. */
    public static final Bezeichner VS_NR = new Bezeichner("Versicherungsschein-Nummer", "VsNr");

    /** Die Konstante VU_NR. */
    public static final Bezeichner VU_NR = new Bezeichner("VU-Nummer", "VuNr");

    /** Die Konstante ZUZAHLUNGSBETRAG_IN_WE. */
    public static final Bezeichner ZUZAHLUNGSBETRAG_IN_WE = new Bezeichner("Zuzahlungsbetrag in Waehrungseinheiten",
            "ZuzahlungsbetragInWE");

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
    public static final String NAME_VERKUERZTE_BEITRAGSZAHLUNGSDAUER = "verkuerzte Beitragszahlungsdauer";
    public static final String NAME_VERMITTLER = "Geschaeftsstelle/Vermittler";
    public static final String NAME_VERSICHERTES_OBJEKT = "Versichertes Objekt";
    public static final String NAME_VERSICHERTE_GEFAHREN = "Versicherte Gefahren";
    public static final String NAME_VERSICHERUNGSLEISTUNGEN = "Versicherungsleistungen";
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
    public static final String NAME_INTERNES_ORDNUNGSMERKMAL_DES_VM = "Internes Ordnungsmerkmal des VM";
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
    public static final String NAME_GEBURTSDATUM_VP = "Geburtsdatum der VP";
    public static final String NAME_GEBURTSDATUM_VP2 = "Geburtsdatum der VP2";
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

    private static final Logger LOG = LogManager.getLogger(Bezeichner.class);

    private static final Map<String, String> MAPPING = new HashMap<String, String>();

    private final String name;
    private final String technischerName;
    private final int hash;

    // Mapping fuer manche Bezeichner (Name <--> technischer Name)
    static {
        MAPPING.put(AUSSCHLUSSDAT_VP_PERSONENGRUPPE.name, AUSSCHLUSSDAT_VP_PERSONENGRUPPE.technischerName);
        MAPPING.put(DAT_BEZUGSFERTIGKEIT.name, DAT_BEZUGSFERTIGKEIT.technischerName);
        MAPPING.put(DAT_LETZTEN_BEITRAGSANGLEICHUNG.name, DAT_LETZTEN_BEITRAGSANGLEICHUNG.technischerName);
        MAPPING.put(LFD_PERSONEN_NR_IM_GEVO.name, LFD_PERSONEN_NR_IM_GEVO.technischerName);
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
        StringBuilder converted = new StringBuilder();
        char[] chars = WordUtils.capitalize(input).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case '\u00c4':
                    converted.append("Ae");
                    break;
                case '\u00d6':
                    converted.append("Oe");
                    break;
                case '\u00dc':
                    converted.append("Ue");
                    break;
                case '\u00e4':
                    converted.append("ae");
                    break;
                case '\u00f6':
                    converted.append("oe");
                    break;
                case '\u00fc':
                    converted.append("ue");
                    break;
                case '\u00df':
                    converted.append("ss");
                    break;
                default:
                    if (Character.isLetterOrDigit(chars[i])) {
                        converted.append(chars[i]);
                    }
                    break;
            }
        }
        techName = converted.toString();
        if (techName.endsWith("datum")) {
            return techName.substring(0, techName.length() - 2);
        }
        if (techName.endsWith("Waehrungseinheiten")) {
            return techName.substring(0, techName.length() - 18) + "WE";
        }
        return techName;
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
                if ((value != null) && bezeichnung.equalsIgnoreCase(value.toString())) {
                    return fields[i];
                }
            } catch (IllegalAccessException e) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Will ignore field " + fields[i] + ": ", e);
                }
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
            LOG.info("Merge of {} and {} is ignored.", this, bezeichner);
            return this;
        } else {
            return bezeichner;
        }
    }

}
