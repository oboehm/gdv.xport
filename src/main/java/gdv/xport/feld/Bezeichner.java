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
import java.util.*;

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
 * Die ehemaligen String-Konstanten werden ab 2.0 durch entsprechnende Bezeichner-
 * Konstanten ersetzt. Fuer eine Uebergangszeit sind die alten String-Konstanten
 * durch den Prefix "NAME_" gekennzeichnet, werden aber ab der Version 2.2 nicht
 * mehr zur Verfuegung stehen.
 * </p>
 *
 * @author oliver
 * @since 15.10.2009
 */
public final class Bezeichner {

    private static final Logger LOG = LogManager.getLogger(Bezeichner.class);
    private static final Map<String, String> MAPPING = new HashMap<>();

    /////////// Bezeichner-Konstanten (alphabetisch geordnet) /////////////////

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
    public static final Bezeichner ERSTELLUNGSDAT_ZEITRAUM_VOM = new Bezeichner("Erstellungs-Datum-Zeitraum vom");
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
    public static final Bezeichner GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtbeitrag (Brutto) in Waehrungseinheiten");
    public static final Bezeichner GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtbeitrag");
    public static final Bezeichner GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtbeitrag (Netto) in Waehrungseinheiten");
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
    public static final Bezeichner LEISTUNG_BEI_SCHWERER_ERKRANKUNG = new Bezeichner("Leistung bei schwerer Erkrankung");
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

    public static final Bezeichner MEHRLEISTUNGSKLAUSEL = new Bezeichner("Mehrleistungsklausel");
    public static final Bezeichner MEHRWERTGRUND = new Bezeichner("Mehrwertgrund");
    public static final Bezeichner MEHRWERT_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Mehrwert");
    public static final Bezeichner MEHRZWECKFELD = new Bezeichner("Mehrzweckfeld");
    public static final Bezeichner MENGENSCHLUESSEL = new Bezeichner("Mengenschluessel");
    public static final Bezeichner MINDESTBEITRAG_JE_WAGNIS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Mindestbeitrag je Wagnis");
    public static final Bezeichner MINDESTLAUFZEIT = new Bezeichner("Mindestlaufzeit");
    public static final Bezeichner MITARBEITER_STATUS = new Bezeichner("Mitarbeiter Status");
    public static final Bezeichner MITVERSICHERTE_PERSON_FAMILIENNAME = new Bezeichner("Mitversicherte Person Familienname");
    public static final Bezeichner MITVERSICHERTE_PERSON_VORNAME = new Bezeichner("Mitversicherte Person Vorname");
    public static final Bezeichner MITZUVERSICHERNDE_PERSON = new Bezeichner("Mitzuversichernde Person");
    public static final Bezeichner MODELLNAME = new Bezeichner("Modellname");

    public static final Bezeichner NAECHSTE_AUSZAHLUNGSSUMMER_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Auszahlungssumme");
    public static final Bezeichner NAECHSTER_AUSZAHLUNGSTERMIN = new Bezeichner("Naechster Auszahlungstermin");
    public static final Bezeichner NAECHSTE_UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Unfallsumme");
    public static final Bezeichner NAECHSTE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Beitragssumme");
    public static final Bezeichner NAECHSTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Erlebensfall VS");
    public static final Bezeichner NAECHSTE_ERHOEHUNG = new Bezeichner("naechste Erhoehung");
    public static final Bezeichner NAECHSTE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Jahresrente");
    public static final Bezeichner NAECHSTE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Todesfall VS");
    public static final Bezeichner NAME1 = new Bezeichner("Name1");
    public static final Bezeichner NAME2 = new Bezeichner("Name2");
    public static final Bezeichner NAME3 = new Bezeichner("Name3");
    public static final Bezeichner NAME_KREDITINSTITUT1 = new Bezeichner("Name des Kreditinstituts 1");
    public static final Bezeichner NAME_KREDITINSTITUT2 = new Bezeichner("Name des Kreditinstituts 2");
    public static final Bezeichner NAME_MITVERSICHERTES_KIND = new Bezeichner("Name des mitversicherten Kinds");
    public static final Bezeichner NAME_VP = new Bezeichner("Name der VP");
    public static final Bezeichner NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Nettobeitrag");
    public static final Bezeichner NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN2 = NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN;
    public static final Bezeichner NEUPREIS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Neupreis in Waehrungseinheiten");
    public static final Bezeichner NUTZUNGSART = new Bezeichner("Nutzungsart");

    public static final Bezeichner OBJEKTNUMMER = new Bezeichner("Objektnummer");
    public static final Bezeichner OBJEKTNUMMER2 = OBJEKTNUMMER;
    public static final Bezeichner ORDNUNGS_NUMMER_FUER_WAGNISZUATZ = new Bezeichner("Ordungs-Nummer fuer Wagniszusatz");
    public static final Bezeichner ORDNUNGS_NUMMER_FUER_WAGNISZUSATZ2 = ORDNUNGS_NUMMER_FUER_WAGNISZUATZ;
    public static final Bezeichner ORDNUNGSBEGRIFF = new Bezeichner("Ordnungsbegriff");
    public static final Bezeichner ORT = new Bezeichner("Ort");

    public static final Bezeichner PASSIVES_KRIEGSRISIKO = new Bezeichner("passives Kriegsrisiko");
    public static final Bezeichner PERSONEN_KUNDENNUMMER_DES_VERMITTLERS = new Bezeichner("Personen Kundennummer des Vermittlers");
    public static final Bezeichner PERSONEN_KUNDENNUMMER_DES_VERSICHERERS = new Bezeichner("Personen-/Kundennummer des Versicherers");
    public static final Bezeichner PERSONENNUMMER_LFD_NUMMER = new Bezeichner("Personennummer / lfd. Nummer");
    public static final Bezeichner POLICIERUNGSDAT = new Bezeichner("Policierungsdatum", "Policierungsdat");
    public static final Bezeichner POSTALISCHES_KENNZEICHEN = new Bezeichner("postalisches Kennzeichen");
    public static final Bezeichner POSTFACH = new Bezeichner("postfach");
    public static final Bezeichner POSTLEITZAHL = new Bezeichner("Postleitzahl");
    public static final Bezeichner POSTLEITZAHL_DER_RISIKOANSCHRIFT = new Bezeichner("Postleitzahl der Risikoanschrift");
    public static final Bezeichner PRODUKTBESCHREIBUNG = new Bezeichner("Produktbeschreibung");
    public static final Bezeichner PRODUKTFORM = new Bezeichner("Produktform");
    public static final Bezeichner PRODUKTFORM_GUELTIG_AB = new Bezeichner("Produktform gueltig ab");
    public static final Bezeichner PRODUKTKENNUNG = new Bezeichner("Produktkennung");
    public static final Bezeichner PRODUKTNAME = new Bezeichner("Produktname");
    public static final Bezeichner PRODUKTSPEZIFISCHE_ANTRAGSDATEN = new Bezeichner("Produktspezifische Antragsdaten");
    public static final Bezeichner PRODUKTSPEZIFISCHE_STAMMDATEN = new Bezeichner("Produktspezifische Stammdaten");
    public static final Bezeichner PROVISION = new Bezeichner("Provision");
    public static final Bezeichner PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Provisionspflichtige Beitragssumme");
    public static final Bezeichner PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN2 = PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN;
    public static final Bezeichner PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Provisionspflichtige Wertungssumme");
    public static final Bezeichner PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN;
    public static final Bezeichner PROZENTSATZ = new Bezeichner("Prozentsatz");
    public static final Bezeichner PROZENTSATZ_PROGRESSIVE_INVALIDITAET = new Bezeichner("Prozentsatz progressive Invaliditaet");
    public static final Bezeichner PROZENTSATZ_PROGRESSIVE_INVALIDITAET_MEHRLEISTUNG_BEI_INVALIDITAET = new Bezeichner("Prozentsatz progressive Invaliditaet / Mehrleistung bei Invaliditaet");

    public static final Bezeichner RATENZAHLUNGSZUSCHLAG_IN_PROZENT = new Bezeichner("Ratenzahlungszuschlag in %");
    public static final Bezeichner RECHTSFORM = new Bezeichner("Rechtsform");
    public static final Bezeichner REFERENZ_VERSICHERUNGSSCHEINNUMME = new Bezeichner("Referenz-Versicherungsscheinnumme");
    public static final Bezeichner REFERENZNUMMER = new Bezeichner("Referenznummer");
    public static final Bezeichner REFERENZ_VERSICHERUNGSSCHEINNUMMER = new Bezeichner("Referenz-Versicherungsscheinnummer");
    public static final Bezeichner REFERENZ_VERSICHERUNGSSCHEINNUMMER_1 = new Bezeichner("1. Referenz-Versicherungsscheinnummer");
    public static final Bezeichner REFERENZ_VERSICHERUNGSSCHEINNUMMER_2 = new Bezeichner("2. Referenz-Versicherungsscheinnummer");
    public static final Bezeichner REFERENZ_VERSICHERUNGSSCHEINNUMMER_3 = new Bezeichner("3. Referenz-Versicherungsscheinnummer");
    public static final Bezeichner REGISTRIERUNGSNUMMER_VERMITTLER = new Bezeichner("Registrierungsnummer Vermittler");
    public static final Bezeichner RENTE_INCL_UEBERSCHUSSBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Rente inkl. Ueberschussbeteiligung");
    public static final Bezeichner RENTE_INKL_UEBERSCHUSSANRECHNUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Rente inkl. Ueberschussanrechnung");
    public static final Bezeichner RENTENZAHLWEISE = new Bezeichner("Rentenzahlweise");
    public static final Bezeichner RESTLAUFZEIT_DES_VERTRAGES = new Bezeichner("Restlaufzeit des Vertrages");
    public static final Bezeichner RISIKOGRUPPE_RISIKOART = new Bezeichner("Risikogruppe / Risikoart");
    public static final Bezeichner RISIKOGRUPPE_RISIKOART1 = RISIKOGRUPPE_RISIKOART;
    public static final Bezeichner RISIKOGRUPPE_RISIKOART2 = RISIKOGRUPPE_RISIKOART;
    public static final Bezeichner RISIKOKENNZIFFER = new Bezeichner("Risikokennziffer");
    public static final Bezeichner RISIKOORT = new Bezeichner("Risikoort");
    public static final Bezeichner RISIKOSTRASSE = new Bezeichner("Risikostrasse");
    public static final Bezeichner RISIKOTEXT = new Bezeichner("Risikotext");
    public static final Bezeichner RISIKOVERLAUF = new Bezeichner("Risikoverlauf");
    public static final Bezeichner RISKIOEINHEIT1 = new Bezeichner("Risikoeinheit-1");
    public static final Bezeichner RISKIOEINHEIT2 = new Bezeichner("Risikoeinheit-2");
    public static final Bezeichner RISIKOEINSCHRAENKUNG = new Bezeichner("Risikoeinschraenkung");
    public static final Bezeichner RISIKOZUSCHLAEGE = new Bezeichner("Risikozuschlaege");
    public static final Bezeichner RISIKOZUSCHLAG = new Bezeichner("Risikozuschlag");
    public static final Bezeichner RISIKOZUSCHLAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Risikozuschlag");
    public static final Bezeichner ROLLE_W_AKZ = new Bezeichner("Rolle W-AKZ", "RolleWAKZ");
    public static final Bezeichner ROHBAU_EINMALBETRAG = new Bezeichner("Rohbau-Einmalbetrag");
    public static final Bezeichner RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Rueckkaufswert");
    public static final Bezeichner RUECKGEWAEHRSUMME_ZUM_ABLAUF_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Rueckgewaehrsumme zum Ablauf");
    public static final Bezeichner RUECKKAUFSWERT_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Rueckkaufswert zum Berechnungsstichtag");
    public static final Bezeichner RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN_MIT_NACHKOMMA = new Bezeichner("Rueckkaufswert");
    public static final Bezeichner RUECKKAUFSWERT_GUELTIG_AB = new Bezeichner("Rueckkaufswert gueltig ab");
    public static final Bezeichner RUECKGEWAEHR_BEI_TOD = new Bezeichner("Rueckgewaehr bei Tod");
    public static final Bezeichner RUECKFUEHRUNGSKOSTEN = new Bezeichner("Rueckfuehrungskosten");
    public static final Bezeichner RUECKGEWAEHRDAT = new Bezeichner("Rueckgewaehrdatum", "Rueckgewaehrdat");

    public static final Bezeichner SAISONKENNZEICHEN = new Bezeichner("Saisonkennzeichen");
    public static final Bezeichner SATZART = new Bezeichner("Satzart");
    public static final Bezeichner SATZNUMMER = new Bezeichner("Satznummer");
    public static final Bezeichner SATZNUMMER1 = SATZNUMMER;
    public static final Bezeichner SATZNUMMER2 = SATZNUMMER;
    public static final Bezeichner SATZNUMMER3 = SATZNUMMER;
    public static final Bezeichner SATZNUMMER4 = SATZNUMMER;
    public static final Bezeichner SATZNUMMER5 = SATZNUMMER;
    public static final Bezeichner SATZNUMMER9 = SATZNUMMER;
    public static final Bezeichner SATZNUMMERWIEDERHOLUNG = new Bezeichner("Satznummerwiederholung");
    public static final Bezeichner SATZNUMMERWIEDERHOLUNG1 = new Bezeichner("Satznummerwiederholung1");
    public static final Bezeichner SATZNUMMERWIEDERHOLUNG2 = new Bezeichner("Satznummerwiederholung2");
    public static final Bezeichner SATZNUMMERWIEDERHOLUNG3 = new Bezeichner("Satznummerwiederholung3");
    public static final Bezeichner SATZNUMMERWIEDERHOLUNG4 = new Bezeichner("Satznummerwiederholung4");
    public static final Bezeichner SATZNUMMERWIEDERHOLUNG9 = new Bezeichner("Satznummerwiederholung9");
    public static final Bezeichner SCHADENBEARBEITUNGSKOSTEN = new Bezeichner("Schadenbearbeitungskosten");
    public static final Bezeichner SCHLUESSEL_SICHERUNGSEINRICHTUNG = new Bezeichner("Schluessel Sicherungseinrichtung");
    public static final Bezeichner SCHLUSSALTER_DES_WAISEN = new Bezeichner("Schlussalter des Waisen");
    public static final Bezeichner SCHUTZBRIEF_VERKEHRSSERVICE = new Bezeichner("Schutzbrief /Verkehrsservice");
    public static final Bezeichner SELBSTBEHALT = new Bezeichner("Selbstbehalt");
    public static final Bezeichner SELBSTBETEILIGUNG = new Bezeichner("Selbstbeteiligung");
    public static final Bezeichner SELBSTBETEILIGUNG_IN_PROZENT = new Bezeichner("Selbstbeteiligung in %");
    public static final Bezeichner SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Selbstbeteiligung");
    public static final Bezeichner SELBSTBETEILIGUNG_IN_WE_MAX = new Bezeichner("Selbstbeteiligung in WE (max.)");
    public static final Bezeichner SELBSTBETEILIGUNG_IN_WE_MIND = new Bezeichner("Selbstbeteiligung in WE (mind.)");
    public static final Bezeichner SEPA_GLAEUBIGERIDENTIFIKATIONSNUMMER = new Bezeichner("SEPA-Glaeubigeridentifikationsnummer", "SEPAGlaeubigeridentifikationsnummer");
    public static final Bezeichner SEPA_MANDAT_AUSSTELLUNGSDATUM = new Bezeichner("SEPA-Mandat Ausstellungsdatum", "SEPAMandatAusstellungsdatum");
    public static final Bezeichner SEPA_MANDAT_KONTOREFERENZ = new Bezeichner("SEPA-Mandat Kontoreferenz", "SEPAMandatKontoreferenz");
    public static final Bezeichner SERVICELEISTUNGEN = new Bezeichner("Serviceleistungen");
    public static final Bezeichner SERVICELEISTUNGEN_BEITRAGSSATZ = new Bezeichner("Serviceleistungen Beitragssatz");
    public static final Bezeichner SICHERUNGSEINRICHTUNG = new Bezeichner("Sicherungseinrichtung");
    public static final Bezeichner SICHERUNGSGLAEUBIGER = new Bezeichner("Sicherungsglaeubiger");
    public static final Bezeichner SICHERUNGSRICHTLINIEN = new Bezeichner("Sicherungsrichtlinien");
    public static final Bezeichner SICHERUNGSSCHEIN = new Bezeichner("Sicherungsschein");
    public static final Bezeichner SOLIDARITAETSZUSCHLAG_BEI_ABLAUF = new Bezeichner("Solidarit\u00e4tszuschlag bei Ablauf");
    public static final Bezeichner SOLIDARITAETSZUSCHLAG_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG = new Bezeichner("Solidarit\u00e4tszuschlag bei Rueckkauf zum Berechnungsstichtag");
    public static final Bezeichner SONDERBEDINGUNGEN = new Bezeichner("Sonderbedingungen");
    public static final Bezeichner SONDERBEDINGUNGEN_KLAUSELN = new Bezeichner("Sonderbedingungen / Klauseln");
    public static final Bezeichner SONDERVEREINBARUNGEN = new Bezeichner("Sondervereinbarungen");
    public static final Bezeichner SONSTIGER_BEZUGSBERECHTIGTER_IM_ERLEBENSFALL = new Bezeichner("Sonstiger Bezeugsberechtigter im Erlebensfall");
    public static final Bezeichner SONSTIGER_BEZUGSBERECHTIGTER_IM_LEISTUNGSFALL = new Bezeichner("Sonstigter Bezugsberechtigter im Leistungsfall");
    public static final Bezeichner SONSTIGER_BEZUGSBERECHTIGTER_IM_TODESFALL = new Bezeichner("Sonstiger Bezugsberechtigter im Todesfall");
    public static final Bezeichner SOZIALVERSICHERUNG_NUMMER = new Bezeichner("Sozialversicherung Nummer");
    public static final Bezeichner SPARTE = new Bezeichner("Sparte");
    public static final Bezeichner SPARTE2 = new Bezeichner("Sparte2", "Sparte2");
    public static final Bezeichner SPARVORGANG = new Bezeichner("Sparvorgang");
    public static final Bezeichner SPEZIFIKATION_REFERENZ_VERSICHERUNGSSCHEINNUMMER = new Bezeichner("Spezifikation der Referenz-Versicherungsscheinnummer");
    public static final Bezeichner STAATSANGEHOERIGKEIT = new Bezeichner("Staatsangehoerigkeit");
    public static final Bezeichner STAENDIG_BEWOHNT = new Bezeichner("Staendig bewohnt");
    public static final Bezeichner STAERKE = new Bezeichner("Staerke");
    public static final Bezeichner STAERKEEINHEIT = new Bezeichner("Staerkeeinheit");
    public static final Bezeichner STATUS = new Bezeichner("Status");
    public static final Bezeichner STATUS_SEIT = new Bezeichner("Status seit");
    public static final Bezeichner STEUERNR_JURISTISCHE_PERSON = new Bezeichner("Steuernummer bei juristischen Personen");
    public static final Bezeichner STOCKWERKE = new Bezeichner("Stockwerke");
    public static final Bezeichner STRASSE = new Bezeichner("Strasse");
    public static final Bezeichner STUNDUNG = new Bezeichner("Stundung");
    public static final Bezeichner STURMZONE = new Bezeichner("Sturmzone");
    public static final Bezeichner SUMMENART_1 = new Bezeichner("Summenart 1");
    public static final Bezeichner SUMMENART_2 = new Bezeichner("Summenart 2");
    public static final Bezeichner SUMMENART_3 = new Bezeichner("Summenart 3");
    public static final Bezeichner SUMMENART_4 = new Bezeichner("Summenart 4");

    public static final Bezeichner TAGEGELD1 = new Bezeichner("Tagegeld 1");
    public static final Bezeichner TAGEGELD1_BEITRAGSSATZ = new Bezeichner("Tagegeld 1 Beitragssatz");
    public static final Bezeichner TAGEGELD2 = new Bezeichner("Tagegeld 2");
    public static final Bezeichner TAGEGELD2_BEITRAGSSATZ = new Bezeichner("Tagegeld 2 Beitragssatz");
    public static final Bezeichner TARIF = new Bezeichner("Tarif");
    public static final Bezeichner TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGTEIL_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugteil");
    public static final Bezeichner TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGVOLL_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugvoll");
    public static final Bezeichner TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_HAFTPFLICHT_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Tarifbeitrag 100 % fuer Kraftfahrt-Haftpflicht");
    public static final Bezeichner TARIFBEZEICHNUNG = new Bezeichner("Tarifbezeichnung");
    public static final Bezeichner TARIFBEZEICHNUNG_DES_FOLGETARIFS = new Bezeichner("Tarifbezeichnung des Folgetarifs");
    public static final Bezeichner TARIFIERUNGSMERKMAL_LAUFZEIT = new Bezeichner("Tarifierungsmerkmal Laufzeit");
    public static final Bezeichner TARIFJAHR = new Bezeichner("Tarifjahr");
    public static final Bezeichner TARIFZONE = new Bezeichner("Tarifzone");
    public static final Bezeichner TECHNISCHE_WAISE_IN_PROZENT = new Bezeichner("Technische Waisenrente in Prozent");
    public static final Bezeichner TECHNISCHE_WITWEN_WITTWERRENTE_IN_PROZENT = new Bezeichner("Technische Witwen- / Witwerrente in Prozent");
    public static final Bezeichner TEILDATENSATZNUMMER = new Bezeichner("Teildatensatznummer");
    public static final Bezeichner TEILKAPITALISIERUNG = new Bezeichner("Teilkapitalisierung");
    public static final Bezeichner THESAUR = new Bezeichner("Thesaur");
    public static final Bezeichner TITEL = new Bezeichner("Titel");
    public static final Bezeichner TOD = new Bezeichner("Tod");
    public static final Bezeichner TOD_BEITRAGSSATZ = new Bezeichner("Tod-Beitragssatz");
    public static final Bezeichner TODESFALL_VS_IN_WAEHRUNGSEINHEIT = new Bezeichner("Todesfall VS");
    public static final Bezeichner TODESFALL_VS_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = new Bezeichner("Todesfall VS zum Ablauf");
    public static final Bezeichner TODESFALL_VS_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = new Bezeichner("Todesfall VS zum Beginn der Abrufphase");
    public static final Bezeichner TODESFALLLEISTUNG_IN_PROZENT = new Bezeichner("Todesfallleistung in %");
    public static final Bezeichner TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Todesfall VS");
    public static final Bezeichner TODESFALLAENDERUNGS_PROZENTSATZ = new Bezeichner("Todesfallaenderungs-Prozentsatz");
    public static final Bezeichner TODESFALLLEISTUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Todesfallleistung");
    public static final Bezeichner TRAEGERUNTERNEHMEN_NAME = new Bezeichner("Traegerunternehmen Name");
    public static final Bezeichner TRAEGERUNTERNEHMEN_SCHLUESSEL = new Bezeichner("Traegerunternehmen Schluessel");
    public static final Bezeichner TYKLASSE_KH = new Bezeichner("Tyklasse KH");
    public static final Bezeichner TYPKLASSE_FUER_KFT = new Bezeichner("Typklasse fuer KFT");
    public static final Bezeichner TYPKLASSE_FUER_KFV = new Bezeichner("Typklasse fuer KFV");
    public static final Bezeichner TYPSCHLUESSEL_NR = new Bezeichner("Typschluessel-Nr.");
    public static final Bezeichner TYP_BANKVERBINDUNG1 = new Bezeichner("Typ der Bankverbindung 1");
    public static final Bezeichner TYP_BANKVERBINDUNG2 = new Bezeichner("Typ der Bankverbindung 2");

    public static final Bezeichner UEBERFUEHRUNGSKOSTEN = new Bezeichner("Ueberfuehrungskosten");
    public static final Bezeichner UEBERGANGSENTSCHAEDIGUNG = new Bezeichner("Uebergangsentschaedigung");
    public static final Bezeichner UEBERGANGSENTSCHAEDIGUNG_BEITRAGSSATZ = new Bezeichner("Uebergangsentschaedigung Beitragssatz");
    public static final Bezeichner UEBERSCHUSS_GUELTIG_AB = new Bezeichner("Ueberschuss gueltig ab");
    public static final Bezeichner UEBERSCHUSSANTEILE_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Ueberschussanteile zum Berechnungsstichtag");
    public static final Bezeichner UMBAUTER_RAUM = new Bezeichner("umbauter Raum");
    public static final Bezeichner UMSATZSTEUER_ID = new Bezeichner("Umsatzsteuer-Identifikationsnummer");
    public static final Bezeichner UMSTELLUNGSDAT_FOLGETARIFS = new Bezeichner("Umstellungsdatum des Folgetarifs", "UmstellungsdatFolgetarifs");
    public static final Bezeichner UMSTELLUNGSDATUM_DES_FOLGETARIFS = UMSTELLUNGSDAT_FOLGETARIFS;
    public static final Bezeichner UMTAUSCHRECHT = new Bezeichner("Umtauschrecht");
    public static final Bezeichner UNBEKANNT = new Bezeichner("unbekannt");
    public static final Bezeichner UNFAELLE = new Bezeichner("Unfaelle");
    public static final Bezeichner UNFALLAENDERUNGS_PROZENTSATZ = new Bezeichner("Unfallaenderungs-Prozentsatz");
    public static final Bezeichner UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Unfallsumme");
    public static final Bezeichner UNTERSTUETZUNGSKASSE_NAME = new Bezeichner("Unterstuetzungskasse Name");
    public static final Bezeichner UNTERSTUETZUNGSKASSE_SCHLUESSEL = new Bezeichner("Unterstuetzungskasse Schluessel");
    public static final Bezeichner UNTERVERS_VERZICHT = new Bezeichner("Untervers.-Verzicht");
    public static final Bezeichner UNVERFALLBARKEIT = new Bezeichner("Unverfallbarkeit");
    public static final Bezeichner UNWIDERRUFLICHES_BEZUGSRECHT_IM_ERLEBENSFALL = new Bezeichner("Unwiderrufliches Bezugsrecht im Erlebensfall");
    public static final Bezeichner UNWIDERRUFLICHES_BEZUGSRECHT_IM_LEISTUNGSFALL = new Bezeichner("Unwiderrufliches Bezugsrecht im Leistungsfall");
    public static final Bezeichner UNWIDERRUFLICHES_BEZUGSRECHT_IM_TODESFALL = new Bezeichner("Unwiderrufliches Bezugsrecht im Todesfall");
    public static final Bezeichner URSPRUENGLICHES_HAFTUNGSBEGINNDAT = new Bezeichner("Urspruengliches Haftungsbeginndatum", "UerspruenglichesHaftungsbeginndat");

    public static final Bezeichner VEREINBARTER_DYNAMIKMAXIMALANPASSUNGSPROZENTSATZ = new Bezeichner("Vereinbarter Dynamikmaximalanpassungsprozentsatz");
    public static final Bezeichner VEREINBARTER_DYNAMIKMINDESTANPASSUNGSPROZENTSATZ = new Bezeichner("Vereinbarter Dynamikmindestanpassungsprozentsatz");
    public static final Bezeichner VERKUERZTE_BEITRAGSZAHLUNGSDAUER = new Bezeichner("verkuerzte Beitragszahlungsdauer");
    public static final Bezeichner VERMITTLER = new Bezeichner("Geschaeftsstelle/Vermittler");
    public static final Bezeichner VERSICHERTE_ERKRANKUNGEN = new Bezeichner("Versicherte Erkrankungen");
    public static final Bezeichner VERSICHERTES_OBJEKT = new Bezeichner("Versichertes Objekt");
    public static final Bezeichner VERSICHERTE_GEFAHREN = new Bezeichner("Versicherte Gefahren");
    public static final Bezeichner VERSICHERUNGSLEISTUNGEN = new Bezeichner("Versicherungsleistungen");
    public static final Bezeichner VERSICHERUNGSSCHEINNUMMER = new Bezeichner("Versicherungsschein-Nummer", "VsNr");
    public static final Bezeichner VERSICHERUNGSSCHEINNUMMER_VM = new Bezeichner("Versicherungsscheinnummer VM");
    public static final Bezeichner VERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Versicherungssumme");
    public static final Bezeichner VERSION_SATZART_9999 = new Bezeichner("Version Satzart 9999 Nachsatz");
    public static final Bezeichner VERTRAG_MIT_ZUWACHSGARANTIE = new Bezeichner("Vertrag mit Zuwachsgarantie");
    public static final Bezeichner VERSION_SATZART_0001 = new Bezeichner("Version Satzart 0001");
    public static final Bezeichner VERSION_SATZART_0100 = new Bezeichner("Version Satzart 0100");
    public static final Bezeichner VERSION_SATZART_0200 = new Bezeichner("Version Satzart 0200");
    public static final Bezeichner VERSION_SATZART_0202_ALLG_ANTRAGSDATEN = new Bezeichner("Version Satzart 0202 Allgemeine Antragsdaten");
    public static final Bezeichner VERSION_SATZART_0210_050 = new Bezeichner("Version Satzart 0210 050");
    public static final Bezeichner VERSION_SATZART_0220_051 = new Bezeichner("Version Satzart 0220 051");
    public static final Bezeichner VERSION_SATZART_0220_052 = new Bezeichner("Version Satzart 0220 052");
    public static final Bezeichner VERSION_SATZART_0220_053 = new Bezeichner("Version Satzart 0220 053");
    public static final Bezeichner VERSION_SATZART_0220_054 = new Bezeichner("Version Satzart 0220 054");
    public static final Bezeichner VERSION_SATZART_0220_059 = new Bezeichner("Version Satzart 0220 059");
    public static final Bezeichner VERSION_SATZART_0210_040 = new Bezeichner("Version Satzart 0210 040");
    public static final Bezeichner VERSION_SATZART_0220_040 = new Bezeichner("Version Satzart 0220 040");
    public static final Bezeichner VERSION_SATZART_0210_030 = new Bezeichner("Version Satzart 0210 030");
    public static final Bezeichner VERSION_SATZART_0220_030 = new Bezeichner("Version Satzart 0220 030");
    public static final Bezeichner VERSION_SATZART_0210_010 = new Bezeichner("Version Satzart 0210 010");
    public static final Bezeichner VERSION_SATZART_0220_010 = new Bezeichner("Version Satzart 0220 010");
    public static final Bezeichner VERSION_SATZART_0210_130 = new Bezeichner("Version Satzart 0210 130");
    public static final Bezeichner VERSION_SATZART_0220_130 = new Bezeichner("Version Satzart 0220 130");
    public static final Bezeichner VERSION_SATZART_0210_110 = new Bezeichner("Version Satzart 0210 110");
    public static final Bezeichner VERSION_SATZART_0220_110 = new Bezeichner("Version Satzart 0220 110");
    public static final Bezeichner VERSION_SATZART_0210_140 = new Bezeichner("Version Satzart 0210 140");
    public static final Bezeichner VERSION_SATZART_0220_140 = new Bezeichner("Version Satzart 0220 140");
    public static final Bezeichner VERSION_SATZART_0210_020 = new Bezeichner("Version Satzart 0210 020");
    public static final Bezeichner VERSION_SATZART_0220_020 = new Bezeichner("Version Satzart 0220 020");
    public static final Bezeichner VERSION_SATZART_0210_070 = new Bezeichner("Version Satzart 0210 070");
    public static final Bezeichner VERSION_SATZART_0220_070 = new Bezeichner("Version Satzart 0220 070");
    public static final Bezeichner VERSION_SATZART_0210_FEUER = new Bezeichner("Version Satzart 0210 Feuer-Industrie/Gewerbliche Sachversicherung");
    public static final Bezeichner VERSION_SATZART_0220_FEUER = new Bezeichner("Version Satzart 0220 Feuer-Industrie/Gewerbliche Sachversicherung");
    public static final Bezeichner VERSION_SATZART_0210_510 = new Bezeichner("Version Satzart 0210 510");
    public static final Bezeichner VERSION_SATZART_0220_510 = new Bezeichner("Version Satzart 0220 510");
    public static final Bezeichner VERSION_SATZART_0210_TRANSPORT = new Bezeichner("Version Satzart 0210 Transport");
    public static final Bezeichner VERSION_SATZART_0220_TRANSPORT = new Bezeichner("Version Satzart 0220 Transport");
    public static final Bezeichner VERSION_SATZART_0250_TRANSPORT = new Bezeichner("Version Satzart 0250 Transport Einzelanmeldung");
    public static final Bezeichner VERSION_SATZART_0260_TRANSPORT = new Bezeichner("Version Satzart 0260 Transport Umsatzanmeldung");
    public static final Bezeichner VERSION_SATZART_0210_NICHT_DEF_SPARTEN = new Bezeichner("Version Satzart 0210 Nicht einzeln definierte Sparten");
    public static final Bezeichner VERSION_SATZART_0210_TECH_VERS = new Bezeichner("Version Satzart 0210 Technische Versicherungen");
    public static final Bezeichner VERSION_SATZART_0220_NICHT_DEF_SPARTEN = new Bezeichner("Version Satzart 0220 Nicht einzeln definierte Sparten");
    public static final Bezeichner VERSION_SATZART_0220_TECH_VERS = new Bezeichner("Version Satzart 0220 Technische Versicherungen");
    public static final Bezeichner VERSION_KFZ_BAUSTEIN = new Bezeichner("Version KFZ-Baustein");
    public static final Bezeichner VERSION_SATZART_0300_BETEILIGUNGSINFORMATION = new Bezeichner("Version Satzart 0300 Beteiligungsinformation");
    public static final Bezeichner VERSION_SATZART_0342_BEGLEITDOK = new Bezeichner("Version Satzart 0342 Begleitdokumente und Signaturen");
    public static final Bezeichner VERSION_SATZART_0400_INKASSO = new Bezeichner("Version Satzart 0400 Inkasso");
    public static final Bezeichner VERSION_SATZART_0410_INKASSO = new Bezeichner("Version Satzart 0410 Inkasso");
    public static final Bezeichner VERSION_SATZART_0430_INKASSO = new Bezeichner("Version Satzart 0430 Inkasso");
    public static final Bezeichner VERSION_SATZART_0500_SCHADENINFORMATION = new Bezeichner("Version Satzart 0500 Schadeninformation");
    public static final Bezeichner VERSION_SATZART_0420_VERSICHERUNGSTEUERABRECHNUNG = new Bezeichner("Version Satzart 0420 Versicherungsteuerabrechnung");
    public static final Bezeichner VERSION_SATZART_0450_INKASSOABRECHNUNG = new Bezeichner("Version Satzart 0450 Inkassoabrechnung");
    public static final Bezeichner VERSION_SATZART_0550_SCHADENABRECHNUNG = new Bezeichner("Version Satzart 0550 Schadenabrechnung");
    public static final Bezeichner VERSION_SATZART_0350_KLAUSELN = new Bezeichner("Version Satzart 0350 Klausel");
    public static final Bezeichner VERSION_SATZART_0211_050 = new Bezeichner("Version Satzart 0211 050");
    public static final Bezeichner VERSION_SATZART_0221_051 = new Bezeichner("Version Satzart 0221 051");
    public static final Bezeichner VERSION_SATZART_0221_052 = new Bezeichner("Version Satzart 0221 052");
    public static final Bezeichner VERSION_SATZART_0221_053 = new Bezeichner("Version Satzart 0221 053");
    public static final Bezeichner VERSION_SATZART_0221_054 = new Bezeichner("Version Satzart 0221 054");
    public static final Bezeichner VERSION_SATZART_0221_055 = new Bezeichner("Version Satzart 0221 055");
    public static final Bezeichner VERSION_SATZART_0221_059 = new Bezeichner("Version Satzart 0221 059");
    public static final Bezeichner VERSION_SATZART_0211_040 = new Bezeichner("Version Satzart 0211 040");
    public static final Bezeichner VERSION_SATZART_0221_040 = new Bezeichner("Version Satzart 0221 040");
    public static final Bezeichner VERSION_SATZART_0221_030 = new Bezeichner("Version Satzart 0221 030");
    public static final Bezeichner VERSION_SATZART_0211_010 = new Bezeichner("Version Satzart 0211 010");
    public static final Bezeichner VERSION_SATZART_0221_010 = new Bezeichner("Version Satzart 0221 010");
    public static final Bezeichner VERSION_SATZART_0211_130 = new Bezeichner("Version Satzart 0211 130");
    public static final Bezeichner VERSION_SATZART_0221_130 = new Bezeichner("Version Satzart 0221 130");
    public static final Bezeichner VERSION_SATZART_0211_110 = new Bezeichner("Version Satzart 0211 110");
    public static final Bezeichner VERSION_SATZART_0221_110 = new Bezeichner("Version Satzart 0221 110");
    public static final Bezeichner VERSION_SATZART_0211_140 = new Bezeichner("Version Satzart 0211 140");
    public static final Bezeichner VERSION_SATZART_0221_140 = new Bezeichner("Version Satzart 0221 140");
    public static final Bezeichner VERSION_SATZART_0221_070 = new Bezeichner("Version Satzart 0221 070");
    public static final Bezeichner VERSION_SATZART_0211_FEUER = new Bezeichner("Version Satzart 0211 Feuer-Industrie/Gewerbliche Sachversicherung");
    public static final Bezeichner VERSION_SATZART_0221_FEUER = new Bezeichner("Version Satzart 0221 Feuer-Industrie/Gewerbliche Sachversicherung");
    public static final Bezeichner VERSION_SATZART_0221_510 = new Bezeichner("Version Satzart 0221 510");
    public static final Bezeichner VERSION_SATZART_0211_TRANSPORT = new Bezeichner("Version Satzart 0211 Transport");
    public static final Bezeichner VERSION_SATZART_0221_TRANSPORT = new Bezeichner("Version Satzart 0221 Transport");
    public static final Bezeichner VERSION_SATZART_0222 = new Bezeichner("Version Satzart 0222");
    public static final Bezeichner VERSION_SATZART_0230_LEBEN = new Bezeichner("Version Satzart 0230 Fondsdatensatz - Leben");
    public static final Bezeichner VERSION_SATZART_0230_UNFALL = new Bezeichner("Version Satzart 0230 Unfall Leistungsarten");
    public static final Bezeichner VERSION_SATZART_0251_TRANSPORT = new Bezeichner("Version Satzart 0251 Transport Einzelanmeldung");
    public static final Bezeichner VERSION_SATZART_0211_NICHT_DEF_SPARTEN = new Bezeichner("Version Satzart 0211 Nicht einzeln definierte Sparten");
    public static final Bezeichner VERSION_SATZART_0211_TECH_VERS = new Bezeichner("Version Satzart 0211 Technische Versicherungen");
    public static final Bezeichner VERSION_SATZART_0221_NICHT_DEF_SPARTEN = new Bezeichner("Version Satzart 0221 Nicht einzeln definierte Sparten");
    public static final Bezeichner VERSION_SATZART_0221_TECH_VERS = new Bezeichner("Version Satzart 0221 Technische Versicherungen");
    public static final Bezeichner VERSION_SATZART_0210_550 = new Bezeichner("Version Satzart 0210 550");
    public static final Bezeichner VERSION_SATZART_0210_560 = new Bezeichner("Version Satzart 0210 560");
    public static final Bezeichner VERSION_SATZART_0210_570 = new Bezeichner("Version Satzart 0210 570");
    public static final Bezeichner VERSION_SATZART_0220_550 = new Bezeichner("Version Satzart 0220 550");
    public static final Bezeichner VERSION_SATZART_0220_560 = new Bezeichner("Version Satzart 0220 560");
    public static final Bezeichner VERSION_SATZART_0220_570 = new Bezeichner("Version Satzart 0220 570");
    public static final Bezeichner VERSION_SATZART_0270_550 = new Bezeichner("Version Satzart 0270 550");
    public static final Bezeichner VERSION_SATZART_0280_550 = new Bezeichner("Version Satzart 0280 550");
    public static final Bezeichner VERSION_SATZART_0291_550 = new Bezeichner("Version Satzart 0291 550");
    public static final Bezeichner VERSION_SATZART_0292_550 = new Bezeichner("Version Satzart 0292 550");
    public static final Bezeichner VERSION_SATZART_0293_550 = new Bezeichner("Version Satzart 0293 550");
    public static final Bezeichner VERSION_SATZART_0294_550 = new Bezeichner("Version Satzart 0294 550");
    public static final Bezeichner VERSION_SATZART_0295_550 = new Bezeichner("Version Satzart 0295 550");
    public static final Bezeichner VERSION_SATZART_0052 = new Bezeichner("Version Satzart 0051");
    public static final Bezeichner VERSION_SATZART_0102 = new Bezeichner("Version Satzart 0102");
    public static final Bezeichner VERSION_SATZART_0212 = new Bezeichner("Version Satzart 0212");
    public static final Bezeichner VERSION_SATZART_0352 = new Bezeichner("Version Satzart 0352");
    public static final Bezeichner VERSION_SATZART_0362 = new Bezeichner("Version Satzart 0362");
    public static final Bezeichner VERSION_SATZART_0382 = new Bezeichner("Version Satzart 0382");
    public static final Bezeichner VERSION_SATZART_0390_RABATTE = new Bezeichner("Version Satzart 0390 Rabatte und Zuschlaege");
    public static final Bezeichner VERSION_SATZART_0392_EVB = new Bezeichner("Version Satzart 0392 eVB-Nummer");
    public static final Bezeichner VERSION_SATZART_9950 = new Bezeichner("Version Satzart 9950");
    public static final Bezeichner VERSION_SATZART_9951_MIME = new Bezeichner("Version Satzart 9951 MIME-Datei");
    public static final Bezeichner VERSION_SATZART_9952 = new Bezeichner("Version Satzart 9952");
    public static final Bezeichner VERSION_SATZART_0210_580 = new Bezeichner("Version Satzart 0210 580");
    public static final Bezeichner VERSION_SATZART_0210_684 = new Bezeichner("Version Satzart 0210 684");
    public static final Bezeichner VERSION_SATZART_0220_580 = new Bezeichner("Version Satzart 0220 580");
    public static final Bezeichner VERSION_SATZART_0220_684 = new Bezeichner("Version Satzart 0220 684");
    public static final Bezeichner VERSION_SATZART_0372 = new Bezeichner("Version Satzart 0372");
    public static final Bezeichner VERSION_SATZART_0600 = new Bezeichner("Version Satzart 0600");
    public static final Bezeichner VERTRAGSABLAUF = new Bezeichner("Vertragsablauf");
    public static final Bezeichner VERTRAGSART = new Bezeichner("Vertragsart");
    public static final Bezeichner VERTRAGSBEGINN = new Bezeichner("Vertragsbeginn");
    public static final Bezeichner VERTRAGSBEDINGUNG = new Bezeichner("Vertragsbedingung");
    public static final Bezeichner VERTRAGSFORM = new Bezeichner("Vertragsform");
    public static final Bezeichner VERTRAGSLAUFZEIT = new Bezeichner("Vertragslaufzeit");
    public static final Bezeichner VERTRAGSSTATUS = new Bezeichner("Vertragsstatus");
    public static final Bezeichner VERTRAGSVERBINDUNGSNUMMER = new Bezeichner("Vertragsverbindungs-Nummer");
    public static final Bezeichner VORAUSSICHTLICHES_ENDE = new Bezeichner("voraussichtliches Ende");
    public static final Bezeichner VORLAUFSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Vorlaufsumme");
    public static final Bezeichner VORNAME_VP = new Bezeichner("Vorname der VP");
    public static final Bezeichner VORSTEUERABZUGSBERECHTIGT = new Bezeichner("Vorsteuerabzugsberechtigt");
    public static final Bezeichner VORZEICHEN = new Bezeichner("Vorzeichen");
    public static final Bezeichner VORZEICHEN2 = VORZEICHEN;
    public static final Bezeichner VORZEICHEN3 = VORZEICHEN;
    public static final Bezeichner VORZEICHEN4 = VORZEICHEN;
    public static final Bezeichner VORZEICHEN5 = VORZEICHEN;
    public static final Bezeichner VORZUGSSEUERBERECHTIGUNG_PROZENT = new Bezeichner("Vorsteuerabszugsberechtigung in Prozent");
    public static final Bezeichner VORZUGSSTEUERBERECHTIGUNG = new Bezeichner("Vorsteuerabszugsberechtigung Ja/Nein");
    public static final Bezeichner VP_PERSONENNUMMER_VERMITTLER = new Bezeichner("VP-Personnenummer des Vermittlers");
    public static final Bezeichner VP_PERSONENNUMMER_VERSICHERER = new Bezeichner("VP-Personnenummer des Versicherers");
    public static final Bezeichner VS_NR = VERSICHERUNGSSCHEINNUMMER;
    public static final Bezeichner VU_ABRECHNUNGSSTELLE = new Bezeichner("VU-Abrechnungsstelle");
    public static final Bezeichner VU_NR = new Bezeichner("VU-Nummer", "VuNr");
    public static final Bezeichner VU_NUMMER = VU_NR;

    public static final Bezeichner WAEHRUNG_DOKUMENTE_FUER_VN = new Bezeichner("Waehrung der Dokumente fuer VN");
    public static final Bezeichner WAEHRUNGSSCHLUESSEL = new Bezeichner("Waehrungsschluessel");
    public static final Bezeichner WAEHRUNGSSCHLUESSEL_1 = new Bezeichner("Waehrungsschluessel 1");
    public static final Bezeichner WAEHRUNGSSCHLUESSEL_2 = new Bezeichner("Waehrungsschluessel 2");
    public static final Bezeichner WAEHRUNGSSCHLUESSEL_3 = new Bezeichner("Waehrungsschluessel 3");
    public static final Bezeichner WAEHRUNGSSCHLUESSEL_4 = new Bezeichner("Waehrungsschluessel 4");
    public static final Bezeichner WAGNIS = new Bezeichner("Wagnis");
    public static final Bezeichner WAGNISART = new Bezeichner("Wagnisart");
    public static final Bezeichner WAGNISART2 = WAGNISART;
    public static final Bezeichner WAGNISART3 = WAGNISART;
    public static final Bezeichner WAGNISART4 = WAGNISART;
    public static final Bezeichner WAGNISBESCHREIBUNG = new Bezeichner("Wagnisbeschreibung");
    public static final Bezeichner WAGNISKENNZIFFER = new Bezeichner("Wagniskennziffer");
    public static final Bezeichner WAGNISMENGE = new Bezeichner("Wagnismenge");
    public static final Bezeichner WAGNISTEXT = new Bezeichner("Wagnistext");
    public static final Bezeichner WAISENRENTE_IN_PROZENT = new Bezeichner("Waisenrente in Prozent");
    public static final Bezeichner WARTEZEIT = new Bezeichner("Wartezeit");
    public static final Bezeichner WECHSELKENNZEICHEN_W_AKZ = new Bezeichner("KFZ - Wechselkennzeichen W-AKZ", "KFZWechselkennzeichenWAKZ");
    public static final Bezeichner WEITERE_REFERENZNUMMERN = new Bezeichner("Weitere Referenznummern");
    public static final Bezeichner WERTPAPIERKENNNUMMER = new Bezeichner("Wertpapierkennnummer");
    public static final Bezeichner WERTUNGSBASIS = new Bezeichner("Wertungsbasis");
    public static final Bezeichner WERTUNGSBASIS2 = WERTUNGSBASIS;
    public static final Bezeichner WERTUNGSMODELL = new Bezeichner("Wertungsmodell");
    public static final Bezeichner WERTUNGSMODELL2 = WERTUNGSMODELL;
    public static final Bezeichner WITWEN_WITWERRENTE_IN_PROZENT = new Bezeichner("Witwen- / Witwerrente in Prozent");
    public static final Bezeichner WOHNEIGENTUM = new Bezeichner("Wohneigentum");
    public static final Bezeichner WOHNFLAECHE_QM = new Bezeichner("Wohnflaeche qm");

    public static final Bezeichner ZAHLUNG_DER_WITWEN_WITWERRENTE_BIS = new Bezeichner("Zahlung der Witwen- / Witwerrente bis");
    public static final Bezeichner ZAHLUNGSANFANG = new Bezeichner("Zahlungsanfang");
    public static final Bezeichner ZAHLUNGSART = new Bezeichner("Zahlungsart /-weg");
    public static final Bezeichner ZAHLUNGSWEISE = new Bezeichner("Zahlungsweise");
    public static final Bezeichner ZAHLUNGSWEISE_KUENFTIGER_GESAMTBETRAG = new Bezeichner("Zahlungsweise des kuenftigen Gesamtbetrags");
    public static final Bezeichner ZIELGRUPPENSCHLUESSEL = new Bezeichner("Zielgruppenschluessel");
    public static final Bezeichner ZUKUENFTIGER_BEITRAG = new Bezeichner("Zukuenftiger Beitrag");
    public static final Bezeichner ZUKUENFTIGER_BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Zukuenftiger Beitrag");
    public static final Bezeichner ZUKUENFTIGER_GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Zukuenftiger Gesamtbeitrag");
    public static final Bezeichner ZUSAETZLICHE_SATZKENNUNG = new Bezeichner("zusaetzliche Satzkennung");
    public static final Bezeichner ZUSCHLAG1_IN_PROZENT = new Bezeichner("Zuschlag-1 in %");
    public static final Bezeichner ZUSCHLAG1_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Zuschlag-1");
    public static final Bezeichner ZUSCHLAG2_IN_PROZENT = new Bezeichner("Zuschlag-2 in %");
    public static final Bezeichner ZUSCHLAG2_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Zuschlag-2");
    public static final Bezeichner ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Zuschlagsbetrag");
    public static final Bezeichner ZUSCHLAG_IN_PROZENT = new Bezeichner("Zuschlag in %");
    public static final Bezeichner ZUZAHLUNGSBETRAG_IN_WE = new Bezeichner("Zuzahlungsbetrag in Waehrungseinheiten", "ZuzahlungsbetragInWE");
    public static final Bezeichner ZUZAHLUNGSDAT = new Bezeichner("Zuzahlungsdatum", "Zuzahlungsdat");
    public static final Bezeichner ZUZAHLUNGSRECHT = new Bezeichner("Zuzahlungsrecht");
    public static final Bezeichner ZWANG_ZUR_BUZ = new Bezeichner("Zwang zur BUZ");



    /////////// Konstanten mit NAME_-Prefix ///////////////////////////////////
    // TODO: ab v2.2 entfernen

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABGANGSGRUND = "Abgangsgrund";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSCHLAG1_IN_PROZENT = "Abschlag-1 in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSCHLAG2_IN_PROZENT = "Abschlag-2 in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSCHLAG2_IN_WAEHRUNGSEINHEITEN = "Abschlag-2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSCHLAG3_IN_PROZENT = "Abschlag-3 in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSCHLAG3_IN_WAEHRUNGSEINHEITEN = "Abschlag-3";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN = "Abschlagsbetrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSCHLAG_IN_PROZENT = "Abschlag in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSCHLUSSPROVISION = "Abschlussprovision";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSENDER = "Absender";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABWEICHENDER_KONTOINHABER1 = "Abweichender Kontoinhaber 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABWEICHENDER_KONTOINHABER2 = "Abweichender Kontoinhaber 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABWEICHENDE_VU_NR = "Abweichende VU-Nr.";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ADRESSAT = "Adressat";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ADRESSKENNZEICHEN = "Adresskennzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AENDERUNGSGRUND = "Aenderungsgrund";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AFB = "A,F,B";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AKTENZEICHEN_SICHERUNGSGLAEUBIGER = "Aktenzeichen des Sicherungsglaeubigers";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AKTUELLE_BEITRAGSDEPOTSUMME_IN_WAEHRUNGSEINHEITEN = "aktuelle Beitragsdepotsumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ALLGEMEINE_VERSICHERUNGSBEDINGUNGEN = "Allgemeine Versicherungsbedingungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AMTL_KENNZEICHEN = "Amtl. Kennzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANREDESCHLUESSEL = "Anredeschluessel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANTEIL_IN_PROZENT = "Anteil in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANZAHL_DER_VORBESITZER = "Anzahl der Vorbesitzer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANZAHL_SAETZE = "Anzahl der Saetze";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANZAHL_VP_PRO_PERSONENGRUPPE = "Anzahl der VP pro Personengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANZAHL_WOHNEINHEITEN = "Anzahl Wohnheiten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ARB = "ARB (Allgemeine Bedingungen fuer die Rechtschutzvers.)";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ART_DER_HEILKOSTEN = "Art der Heilkosten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ART_DER_STEUERLICHEN_FOERDERUNG = "Art der steuerlichen Foerderung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ART_DER_ZULASSUNG_BEIM_VORBESITZER = "Art der Zulassung beim Vorbesitzer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ART_DES_ABSENDERS = "Art des Absenders";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ART_DES_ADRESSATEN = "Art des Adressaten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ART_DES_AMTLICHEN_KENNZEICHENS = "Art des amtlichen Kennzeichens";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ART_DES_BERUFSSCHLUESSELVERZEICHNISSES = "Art des Berufsschluesselverzeichnisses";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ART_DES_DRITTRECHTS = "Art des Drittrechts";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AUFBAUART = "Aufbauart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AUFSICHTSFREIER_VERTRAG = "Aufsichtsfreier Vertrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AUFTEILUNG_VERSICHERUNGSSTEUER = "Aufteilung Versicherungsteuer gemaess EU-Richtlinien";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AUFTRAGSNR_VERMITTLER = "Auftrags-Nr. des Vermittlers";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AUSSCHLUSS = "Ausschluss";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BAUJAHR = "Baujahr";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BAUSTEIN_GESAMTBEITRAG_1_IN_WAEHRUNGSEINHEITEN = "Baustein-Gesamtbeitrag 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BAUSTEIN_GESAMTBEITRAG_2_IN_WAEHRUNGSEINHEITEN = "Baustein-Gesamtbeitrag 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINN = "Beginn";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINN_TAGEGELD1_AB_TAG = "Beginn Tagegeld 1 ab Tag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINN_TAGEGELD2_AB_TAG = "Beginn Tagegeld 2 ab Tag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINN_VERSICHERUNGSSCHUTZ = "Beginn Versicherungsschutz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSANGLEICHUNGSKLAUSEL = "Beitragsangleichungsklausel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSDEPOT = "Beitragsdepot";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSKLASSE = "Beitragsklasse";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSREGULIERUNG = "Beitragsregulierung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSRUECKGEWAEHR = "Beitragsrueckgewaehr";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSUMSTELLUNGSGRUND = "Beitragsumstellungsgrund";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_BERGUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Bergungskosten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_FESTE_RENTE_IN_WAEHRUNGSEINHEITEN = "Beitrag Feste Rente";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_GENESUNGSGELD_IN_WAEHRUNGSEINHEITEN = "Beitrag Genesungsgeld";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_HEILKOSTEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Heilkosten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_INVALIDITAET_IN_WAEHRUNGSEINHEITEN = "Beitrag Invaliditaet";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_IN_WAEHRUNGSEINHEITEN = "Beitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_KOSMETISCHE_OPERATION_IN_WAEHRUNGSEINHEITEN = "Beitrag Kosmetische Operation";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_KRANKENHAUSTAGEGELD_IN_WAEHRUNGSEINHEITEN = "Beitrag Krankenhaustagegeld";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_KURKOSTEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Kurkosten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_PRO_VP_IN_WAEHRUNGSEINHEITEN = "Beitrag pro VP oder pro Personengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_SERVICELEISTUNGEN_IN_WAEHRUNGSEINHEITEN = "Beitrag Serviceleistungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_TAGEGELD1_IN_WAEHRUNGSEINHEITEN = "Beitrag Tagegeld 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_TAGEGELD2_IN_WAEHRUNGSEINHEITEN = "Beitrag Tagegeld 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_TOD_IN_WAEHRUNGSEINHEITEN = "Beitrag Tod";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_UEBERGANGSENTSCHAEDIGUNG_IN_WAEHRUNGSEINHEITEN = "Beitrag Uebergangsentschaedigung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BERGUNGSKOSTEN = "Bergungskosten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BERGUNGSKOSTEN_BEITRAGSSATZ = "Bergungskosten Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BERUFSGRUPPENEINTEILUNG = "Berufsgruppeneinteilung im Industrie-Straf-RS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BERUFSSCHLUESSEL = "Berufsschluessel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BERUF_TEXT = "Beruf-Text";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BESONDERER_VERWENDUNGSZWECK = "besonderer Verwendungszweck";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BESONDERE_VEREINBARUNG_ZUM_FLUGGASTRISIKO = "Besondere Vereinbarung zum Fluggastrisiko";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BESTANDSFUEHRENDE_GESCHAEFTSSTELLE = "Bestandsfuehrende Geschaeftsstelle";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BESTANDSPFLEGEPROVISION = "Bestandspflegeprovision";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BETRIEBLICHE_ALTERSVORSORGE = "Betriebliche Altersvorsorge";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEZEICHNUNG_PERSONENGRUPPE = "Bezeichnung Personengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEZUGSBERECHTIGT_IM_LEISTUNGSFALL = "Bezugsberechtigt im Leistungsfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEZUGSRECHTANTEIL_IM_LEISTUNGSFALL = "Bezugsrechtanteil im Leistungsfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BIC1 = "BIC 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BIC2 = "BIC 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BILANZMONAT_ARBEITGEBER = "Bilanzmonat Arbeitgeber";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BLZ1 = "Bankleitzahl 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BLZ2 = "Bankleitzahl 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BUENDELUNGSKENNZEICHEN = "Buendelungskennzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DECKUNGSSUMME_IN_TSD_WAEHRUNGSEINHEITEN = "Deckungssumme in Tausend Waehrungseinheiten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DECKUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Deckungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DECKUNGSUMFANG = "Deckungsumfang";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DIENSTEINTRITTSDATUM = "Diensteintrittsdatum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DRUCKAUFBEREITETE_VERSICHERUNGSSCHEINNUMMER = "Druckaufbereitete Versicherungsscheinnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DURCHFUEHRUNGSWEG = "Durchfuehrungsweg";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DYNAMIK = "Dynamik";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DYNAMIK_IN_PROZENT = "Dynamik in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_EIGENTUMSVERHAELTNIS_FAHRZEUG = "Eigentumsverhaeltnis (Fahrzeug)";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_EINSCHLUSSDATUM_VP = "Einschlussdatum VP / Personengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_EINZAHLUNG_AUSSCHUETTUNG = "Einzahlung / Ausschuettung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ENDEDATUM_DES_VERSICHERUNGSSCHUTZES_BEI_ROTEN_KENNZEICHEN = "Endedatum des Versicherungsschutzes bei roten Kennzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERHOEHUNGSART_DYNAMIK = "Erhoehungsart Dynamik";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERSTELLUNGSDATUM_ZEITRAUM_BIS = "Erstellungs-Datum-Zeitraum bis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERSTELLUNGSDATUM_ZEITRAUM_VOM = "Erstellungs-Datum-Zeitraum vom";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERSTE_ZULASSUNG_AUF_DEN_VN = "Erste Zulassung auf den VN";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERSTZULASSUNG = "Erstzulassung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERWEITERUNGSSATZ_VORHANDEN = "Erweiterungssatz vorhanden";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_EVB_NUMMER = "eVB-Nummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FAHRZEUGART = "Fahrzeugart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FAHRZEUGIDENTIFIZIERUNGSNUMMER = "Fahrzeugidentifizierungsnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FAMILIENSTAND = "Familienstand";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FESTE_RENTE = "Feste Rente";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FESTE_RENTE_BEITRAGSSATZ = "Feste Rente Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FINANZIERUNGSART = "Finanzierungsart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FINANZIERUNG_ZUSAGE = "Finanzierung der Zusage";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FLOTTENKENNZEICHEN = "Flottenkennzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FLOTTENRABATT_IN_PROZENT = "Flottenrabatt in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FOLGENUMMER = "Folgenummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FOLGEPROVISION = "Folgeprovision";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FREMDER_GRUND_UND_BODEN = "fremder Grund und Boden";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FREMDNUTZUNG = "Fremdnutzung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_KH = "Frei vereinbarte Selbstbeteiligung fuer KH";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO = "Frei vereinbarte Selbstbeteiligung fuer Teilkasko";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO_IM_RAHMEN_DER_VOLLKASKO = "Frei vereinbarte Selbstbeteiligung fuer Teilkasko im Rahmen der Vollkasko";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_VOLLKASKO = "Frei vereinbarte Selbstbeteiligung fuer Vollkasko";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GAP_DECKUNG = "GAP-Deckung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GARAGE = "Garage";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GEBURTSDATUM = "Geburtsdatum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GEBURTSLAND = "Geburtsland";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GEBURTSNAME = "Geburtsname";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GEBURTSORT = "Geburtsort";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GEFAHRENGRUPPE = "Gefahrengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GEFAHRGUT = "Gefahrgut";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GELTUNGSBEREICH = "Geltungsbereich";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GELTUNGSBEREICHEINSCHRAENKUNG = "Geltungsbereicheinschraenkung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GENESUNGSGELD = "Genesungsgeld";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GENESUNGSGELD_BEITRAGSSATZ = "Genesungsgeld Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESAMTBEITRAG = "Gesamtbeitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESAMTBEITRAG_BRUTTO = "Gesamtbeitrag-Brutto(Inkasso)";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN = "Gesamtbeitrag (Brutto)";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN = "Gesamtbeitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN = "Gesamtbeitrag (Netto)";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESAMTMASSE = "Gesamtmasse";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESAMTPROVISIONSBETRAG = "Gesamtprovisions-Betrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESCHLECHT = "Geschlecht";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GROSSRISIKEN = "Aufsichtsfreier Versicherungsnehmer (Grossrisiken)";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GRUPPENART = "Gruppenart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GUELTIGE_AKB = "Gueltige AKB";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GUELTIGKEITSDAUER_IN_TAGEN_BEI_KURZZEITKENNZEICHEN = "Gueltigkeitsdauer in Tagen bei Kurzzeitkennzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_HAUPTFAELLIGKEIT = "Hauptfaelligkeit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_HEILKOSTEN = "Heilkosten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_HEILKOSTEN_BEITRAGSSATZ = "Heilkosten Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_HERSTELLERNAME = "Herstellername";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_HERSTELLER_SCHLUESSEL_NR = "Hersteller-Schluessel-Nr.";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_IBAN1 = "IBAN 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_IBAN2 = "IBAN 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_INKASSOART = "Inkassoart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_INVALIDITAET = "Invaliditaet";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_INVALIDITAET_BEITRAGSSATZ = "Invaliditaet Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_INTERNES_ORDNUNGSMERKMAL_DES_VM = "Internes Ordnungsmerkmal des VM";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_JAEHRLICHE_FAHRLEISTUNG = "Jaehrliche Fahrleistung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KAPITALERTRAGSTEUERPFLICHT = "Kapitalertragsteuerpflicht";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KASKO_BEGINNJAHR = "Kasko-Beginnjahr";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KAUFPREIS = "Kaufpreis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNUNG_FUER_ABS_RABATT = "Kennung fuer ABS-Rabatt";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNUNG_GLIEDERTAXE = "Kennung Gliedertaxe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNUNG_PROGRESSIVE_INVALIDITAET = "Kennung progressive Invaliditaet";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNUNG_UEBERGANGSENTSCHAEDIGUNG = "Kennung Uebergangsentschaedigung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION = "Kennzeichen fuer abweichende Abschlussprovision";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNZEICHEN_ABWEICHENDE_BESTANDSPFLEGEPROVISION = "Kennzeichen fuer abweichende Bestandspflegeprovision";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNZEICHEN_ABWEICHENDE_FOLGEPROVISION = "Kennzeichen fuer abweichende Folgeprovision";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNZEICHEN_ABWEICHENDE_VU_NR = "Kennzeichen zur Erlaeuterung der abweichenden VU-Nr.";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNZEICHEN_FUER_ABWEICHENDE_PROVISION = "Kennzeichen fuer abweichende Provision";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNZEICHEN_VERS_STEUER_FREI = "Kennzeichen Vers.-Steuer frei";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNZEICHEN_VERTRAGSENTSTEHUNG = "Kennzeichen Vertragsentstehung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFT_ABSCHLAEGE_IN_PROZENT = "KFT-Abschlaege in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFT_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFT-Abschlaege";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFT_AENDERUNGSDATUM = "KFT-aenderungsdatum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFT_AUSSCHLUSS = "KFT-Ausschluss";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFT_BEGINN = "KFT-Beginn";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFT_BEITRAG_IN_WAEHRUNGSEINHEITEN = "KFT-Beitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFT_DECKUNGSART = "KFT-Deckungsart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFT_TARIFGRUPPE = "KFT-Tarifgruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFT_ZUSCHLAEGE_IN_PROZENT = "KFT-Zuschlaege in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFT_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFT-Zuschlaege";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_ABSCHLAEGE_IN_PROZENT = "KFV-Abschlaege in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFV-Abschlaege";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_AENDERUNGSDATUM = "KFV-aenderungsdatum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_AUSSCHLUSS = "KFV-Ausschluss";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_BEGINN = "KFV-Beginn";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_BEITRAGSSATZ = "KFV-Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_BEITRAG_IN_WAEHRUNGSEINHEITEN = "KFV-Beitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_DECKUNGSART = "KFV-Deckungsart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_RGJ = "KFV-RGJ";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_SCHAEDEN_AUS_RUECKSTUFUNG = "KFV-Schaeden aus Rueckstufung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_SFS_KLASSE = "KFV-SF/S-Klasse";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_TARIFGRUPPE = "KFV-Tarifgruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_ZUSCHLAEGE_IN_PROZENT = "KFV-Zuschlaege in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KFV_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KFV-Zuschlaege";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_ABSCHLAEGE_IN_PROZENT = "KH-Abschlaege in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KH-Abschlaege";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_AENDERUNGSDATUM = "KH-aenderungsdatum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_AUSSCHLUSS = "KH-Ausschluss";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_BEGINN = "KH-Beginn";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_BEITRAGSSAETZE = "KH-Beitragssaetze";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_BEITRAG_IN_WAEHRUNGSEINHEITEN = "KH-Beitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_DECKUNGSART = "KH Deckungsart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_DECKUNGSSUMMEN = "KH-Deckungssummen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN = "KH-Deckungssummen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL1 = "KH-Deckungssummen Teil 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL2 = "KH-Deckungssummen Teil 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL3 = "KH-Deckungssummen Teil 3";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_DECKUNGSSUMMEN_PERSONENSCHAEDEN = "KH-Deckungssummen Personenschaeden";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_DECKUNGSSUMMEN_SACHSCHAEDEN = "KH-Deckungssummen Sachschaeden";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_DECKUNGSSUMMEN_VERMOEGENSCHAEDEN = "KH-Deckungssummen Vermoegensschaeden";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_RGJ = "KH-RGJ";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_SCHAEDEN_AUS_RUECKSTUFUNG = "KH-Schaeden aus Rueckstufung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_SF_S_KLASSE = "KH-SF/S-Klasse";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_TARIFGRUPPE = "KH-Tarifgruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_ZUSCHLAEGE_IN_PROZENT = "KH-Zuschlaege in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KH_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = "KH-Zuschlaege";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KLARTEXT_SICHERUNGSEINRICHTUNG = "Klartext Sicherungseinrichtung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOLLEKTIV_NR = "Kollektiv-Nr.";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSNR1 = "Kommunikationsnummer 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSNR2 = "Kommunikationsnummer 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSNR3 = "Kommunikationsnummer 3";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSNR4 = "Kommunikationsnummer 4";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSNR5 = "Kommunikationsnummer 5";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSNR6 = "Kommunikationsnummer 6";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSNR7 = "Kommunikationsnummer 7";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSTYP1 = "Kommunikationstyp 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSTYP2 = "Kommunikationstyp 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSTYP3 = "Kommunikationstyp 3";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSTYP4 = "Kommunikationstyp 4";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSTYP5 = "Kommunikationstyp 5";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSTYP6 = "Kommunikationstyp 6";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOMMUNIKATIONSTYP7 = "Kommunikationstyp 7";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KONTONR1 = "Kontonummer 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KONTONR2 = "Kontonummer 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOSMETISCHE_OPERATIONEN = "Kosmetische Operationen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KOSMETISCHE_OPERATIONEN_BEITRAGSSATZ = "Kosmetische Operationen Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KRANKENHAUSTAGEGELD = "Krankenhaustagegeld";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KRANKENHAUSTAGEGELD_BEITRAGSSATZ = "Krankenhaustagegeld Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KREISGEMEINDESCHLUESSEL = "Kreisgemeindeschluessel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KREISGEMEINDESCHLUESSEL_ZUSATZINFORMATION = "Kreisgemeindeschluessel Zusatzinformation";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KUENDIGUNGSKLAUSEL = "Kuendigungsklausel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KUENDIGUNGSKLAUSEL_VP = "Kuendigungsklausel VP / Personengruppe gestrichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KUNDENGRUPPE = "Kundengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KUNDENNR_VERMITTLER = "Personen-/Kundennummer des Vermittlers";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KUNDENNR_VERSICHERER = "Personen-/Kundennummer des Versicherers";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KURKOSTEN = "Kurkosten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KURKOSTEN_BEITRAGSSATZ = "Kurkosten Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LAENDERKENNZEICHEN = "Laenderkennzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LAND_DES_AMTL_KENNZEICHENS = "Land des amtl. Kennzeichens";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LAUFZEITRABATT_IN_PROZENT = "Laufzeitrabatt in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEERSTELLEN = "Leerstellen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEERSTELLEN1 = "Leerstellen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEERSTELLEN2 = "Leerstellen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEERSTELLEN3 = "Leerstellen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEERSTELLEN4 = "Leerstellen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEERSTELLEN5 = "Leerstellen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LETZTE_ERHOEHUNG = "letzte Erhoehung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER = "Lfd. Nummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER1 = "Lfd. Nummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER2 = "Lfd. Nummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_SATZART = "Lfd. Nummer der Satzart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_VP = "Lfd. Nummer der versicherten Person (VP)";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE1 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE2 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE3 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE4 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_VP_PERSONENGRUPPE9 = "Lfd. Nummer der versicherten Person (VP) / Personengruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_PERSONENNUMMER_DES_SICHERUNGSGLAEUBIGERS = "Lfd. Personennummer des Sicherungsglaeubigers";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MEHRLEISTUNGSKLAUSEL = "Mehrleistungsklausel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MEHRWERTGRUND = "Mehrwertgrund";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MEHRWERT_IN_WAEHRUNGSEINHEITEN = "Mehrwert";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MEHRZWECKFELD = "Mehrzweckfeld";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MITVERSICHERTE_PERSON_FAMILIENNAME = "Mitversicherte Person Familienname";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MITVERSICHERTE_PERSON_VORNAME = "Mitversicherte Person Vorname";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MODELLNAME = "Modellname";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAECHSTE_ERHOEHUNG = "naechste Erhoehung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAME1 = "Name1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAME2 = "Name2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAME3 = "Name3";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAME_KREDITINSTITUT1 = "Name des Kreditinstituts 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAME_KREDITINSTITUT2 = "Name des Kreditinstituts 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAME_MITVERSICHERTES_KIND = "Name des mitversicherten Kinds";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAME_VP = "Name der VP";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NEUPREIS_IN_WAEHRUNGSEINHEITEN = "Neupreis in Waehrungseinheiten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NUTZUNGSART = "Nutzungsart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ORDNUNGSBEGRIFF = "Ordnungsbegriff";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ORT = "Ort";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PASSIVES_KRIEGSRISIKO = "passives Kriegsrisiko";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PERSONENNUMMER_LFD_NUMMER = "Personennummer / lfd. Nummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PERSONEN_KUNDENNUMMER_DES_VERSICHERERS = "Personen-/Kundennummer des Versicherers";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_POLICIERUNGSDATUM = "Policierungsdatum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_POSTALISCHES_KENNZEICHEN = "postalisches Kennzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_POSTFACH = "postfach";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_POSTLEITZAHL = "Postleitzahl";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PRODUKTBESCHREIBUNG = "Produktbeschreibung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PRODUKTFORM = "Produktform";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PRODUKTFORM_GUELTIG_AB = "Produktform gueltig ab";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PRODUKTKENNUNG = "Produktkennung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PRODUKTNAME = "Produktname";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PRODUKTSPEZIFISCHE_ANTRAGSDATEN = "Produktspezifische Antragsdaten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PRODUKTSPEZIFISCHE_STAMMDATEN = "Produktspezifische Stammdaten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PROVISION = "Provision";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PROZENTSATZ_PROGRESSIVE_INVALIDITAET = "Prozentsatz progressive Invaliditaet";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RATENZAHLUNGSZUSCHLAG_IN_PROZENT = "Ratenzahlungszuschlag in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RECHTSFORM = "Rechtsform";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_REFERENZNUMMER = "Referenznummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_REFERENZ_VERSICHERUNGSSCHEINNUMMER = "Referenz-Versicherungsscheinnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_REFERENZ_VERSICHERUNGSSCHEINNUMMER_1 = "1. Referenz-Versicherungsscheinnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_REFERENZ_VERSICHERUNGSSCHEINNUMMER_2 = "2. Referenz-Versicherungsscheinnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_REFERENZ_VERSICHERUNGSSCHEINNUMMER_3 = "3. Referenz-Versicherungsscheinnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_REGISTRIERUNGSNUMMER_VERMITTLER = "Registrierungsnummer Vermittler";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RESTLAUFZEIT_VERTRAG = "Restlaufzeit des Vertrages";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOGRUPPE_RISIKOART = "Risikogruppe / Risikoart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOGRUPPE_RISIKOART1 = "Risikogruppe / Risikoart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOGRUPPE_RISIKOART2 = "Risikogruppe / Risikoart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOTEXT = "Risikotext";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOVERLAUF = "Risikoverlauf";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISKIOEINHEIT1 = "Risikoeinheit-1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISKIOEINHEIT2 = "Risikoeinheit-2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ROHBAU_EINMALBETRAG = "Rohbau-Einmalbetrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ROLLE_W_AKZ = "Rolle W-AKZ";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RUECKFUEHRUNGSKOSTEN = "Rueckfuehrungskosten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SAISONKENNZEICHEN = "Saisonkennzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZART = "Satzart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMER = "Satznummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMER1 = "Satznummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMER2 = "Satznummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMER3 = "Satznummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMER4 = "Satznummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMER5 = "Satznummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMER9 = "Satznummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMERWIEDERHOLUNG = "Satznummerwiederholung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMERWIEDERHOLUNG1 = "Satznummerwiederholung1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMERWIEDERHOLUNG2 = "Satznummerwiederholung2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMERWIEDERHOLUNG3 = "Satznummerwiederholung3";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMERWIEDERHOLUNG4 = "Satznummerwiederholung4";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SATZNUMMERWIEDERHOLUNG9 = "Satznummerwiederholung9";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SCHADENBEARBEITUNGSKOSTEN = "Schadenbearbeitungskosten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SCHLUESSEL_SICHERUNGSEINRICHTUNG = "Schluessel Sicherungseinrichtung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SCHUTZBRIEF_VERKEHRSSERVICE = "Schutzbrief /Verkehrsservice";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SELBSTBETEILIGUNG = "Selbstbeteiligung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SELBSTBETEILIGUNG_IN_PROZENT = "Selbstbeteiligung in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = "Selbstbeteiligung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SELBSTBETEILIGUNG_IN_WE_MAX = "Selbstbeteiligung in WE (max.)";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SELBSTBETEILIGUNG_IN_WE_MIND = "Selbstbeteiligung in WE (mind.)";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SEPA_GLAEUBIGERIDENTIFIKATIONSNUMMER = "SEPA-Glaeubigeridentifikationsnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SEPA_MANDAT_AUSSTELLUNGSDATUM = "SEPA-Mandat Ausstellungsdatum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SEPA_MANDAT_KONTOREFERENZ = "SEPA-Mandat Kontoreferenz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SEPA_MANDAT_REFERENZNUMMER = "SEPA-Mandat Referenznummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SERVICELEISTUNGEN = "Serviceleistungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SERVICELEISTUNGEN_BEITRAGSSATZ = "Serviceleistungen Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SICHERUNGSEINRICHTUNG = "Sicherungseinrichtung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SICHERUNGSGLAEUBIGER = "Sicherungsglaeubiger";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SICHERUNGSSCHEIN = "Sicherungsschein";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SONDERBEDINGUNGEN = "Sonderbedingungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SONDERBEDINGUNGEN_KLAUSELN = "Sonderbedingungen / Klauseln";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SONDERVEREINBARUNGEN = "Sondervereinbarungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SONSTIGER_BEZUGSBERECHTIGTER_IM_LEISTUNGSFALL = "Sonstigter Bezugsberechtigter im Leistungsfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SPARTE = "Sparte";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SPARTE2 = "Sparte";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SPEZIFIKATION_REFERENZ_VERSICHERUNGSSCHEINNUMMER = "Spezifikation der Referenz-Versicherungsscheinnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_STAATSANGEHOERIGKEIT = "Staatsangehoerigkeit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_STAERKE = "Staerke";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_STAERKEEINHEIT = "Staerkeeinheit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_STEUERNR_JURISTISCHE_PERSON = "Steuernummer bei juristischen Personen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_STOCKWERKE = "Stockwerke";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_STRASSE = "Strasse";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_STURMZONE = "Sturmzone";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TAGEGELD1 = "Tagegeld 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TAGEGELD1_BEITRAGSSATZ = "Tagegeld 1 Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TAGEGELD2 = "Tagegeld 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TAGEGELD2_BEITRAGSSATZ = "Tagegeld 2 Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TARIF = "Tarif";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGTEIL_IN_WAEHRUNGSEINHEITEN = "Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugteil";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGVOLL_IN_WAEHRUNGSEINHEITEN = "Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugvoll";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_HAFTPFLICHT_IN_WAEHRUNGSEINHEITEN = "Tarifbeitrag 100 % fuer Kraftfahrt-Haftpflicht";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TARIFBEZEICHNUNG = "Tarifbezeichnung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TARIFIERUNGSMERKMAL_LAUFZEIT = "Tarifierungsmerkmal Laufzeit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TARIFJAHR = "Tarifjahr";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TITEL = "Titel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TOD = "Tod";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TOD_BEITRAGSSATZ = "Tod-Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TRAEGERUNTERNEHMEN_NAME = "Traegerunternehmen Name";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TRAEGERUNTERNEHMEN_SCHLUESSEL = "Traegerunternehmen Schluessel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TYKLASSE_KH = "Tyklasse KH";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TYPKLASSE_FUER_KFT = "Typklasse fuer KFT";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TYPKLASSE_FUER_KFV = "Typklasse fuer KFV";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TYPSCHLUESSEL_NR = "Typschluessel-Nr.";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TYP_BANKVERBINDUNG1 = "Typ der Bankverbindung 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TYP_BANKVERBINDUNG2 = "Typ der Bankverbindung 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UEBERFUEHRUNGSKOSTEN = "Ueberfuehrungskosten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UEBERGANGSENTSCHAEDIGUNG = "Uebergangsentschaedigung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UEBERGANGSENTSCHAEDIGUNG_BEITRAGSSATZ = "Uebergangsentschaedigung Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UMBAUTER_RAUM = "umbauter Raum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UMSATZSTEUER_ID = "Umsatzsteuer-Identifikationsnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UNBEKANNT = "unbekannt";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UNTERSTUETZUNGSKASSE_NAME = "Unterstuetzungskasse Name";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UNTERSTUETZUNGSKASSE_SCHLUESSEL = "Unterstuetzungskasse Schluessel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UNVERFALLBARKEIT = "Unverfallbarkeit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UNWIDERRUFLICHES_BEZUGSRECHT_IM_LEISTUNGSFALL = "Unwiderrufliches Bezugsrecht im Leistungsfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERTRAGSVERBINDUNGSNUMMER = "Vertragsverbindungsnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERKUERZTE_BEITRAGSZAHLUNGSDAUER = "verkuerzte Beitragszahlungsdauer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERMITTLER = "Geschaeftsstelle/Vermittler";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSICHERTES_OBJEKT = "Versichertes Objekt";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSICHERTE_GEFAHREN = "Versicherte Gefahren";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSICHERUNGSLEISTUNGEN = "Versicherungsleistungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSICHERUNGSSCHEINNUMMER = "Versicherungsschein-Nummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSICHERUNGSSCHEINNUMMER_VM = "Versicherungsscheinnummer VM";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_9999 = "Version Satzart 9999 Nachsatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0001 = "Version Satzart 0001";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0100 = "Version Satzart 0100";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0200 = "Version Satzart 0200";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_050 = "Version Satzart 0210 050";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_051 = "Version Satzart 0220 051";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_052 = "Version Satzart 0220 052";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_053 = "Version Satzart 0220 053";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_054 = "Version Satzart 0220 054";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_059 = "Version Satzart 0220 059";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_040 = "Version Satzart 0210 040";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_040 = "Version Satzart 0220 040";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_030 = "Version Satzart 0210 030";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_030 = "Version Satzart 0220 030";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_010 = "Version Satzart 0210 010";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_010 = "Version Satzart 0220 010";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_130 = "Version Satzart 0210 130";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_130 = "Version Satzart 0220 130";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_110 = "Version Satzart 0210 110";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_110 = "Version Satzart 0220 110";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_140 = "Version Satzart 0210 140";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_140 = "Version Satzart 0220 140";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_020 = "Version Satzart 0210 020";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_020 = "Version Satzart 0220 020";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_070 = "Version Satzart 0210 070";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_070 = "Version Satzart 0220 070";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_FEUER = "Version Satzart 0210 Feuer-Industrie/Gewerbliche Sachversicherung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_FEUER = "Version Satzart 0220 Feuer-Industrie/Gewerbliche Sachversicherung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_510 = "Version Satzart 0210 510";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_510 = "Version Satzart 0220 510";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_TECH_VERS = "Version Satzart 0210 Technische Versicherungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_TECH_VERS = "Version Satzart 0220 Technische Versicherungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_TRANSPORT = "Version Satzart 0210 Transport";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_TRANSPORT = "Version Satzart 0220 Transport";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0250_TRANSPORT = "Version Satzart 0250 Transport Einzelanmeldung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0260_TRANSPORT = "Version Satzart 0260 Transport Umsatzanmeldung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_NICHT_DEF_SPARTEN = "Version Satzart 0210 Nicht einzeln definierte Sparten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_NICHT_DEF_SPARTEN = "Version Satzart 0220 Nicht einzeln definierte Sparten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_KFZ_BAUSTEIN = "Version KFZ-Baustein";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0300_BETEILIGUNGSINFORMATION = "Version Satzart 0300 Beteiligungsinformation";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0400_INKASSO = "Version Satzart 0400 Inkasso";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0410_INKASSO = "Version Satzart 0410 Inkasso";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0430_INKASSO = "Version Satzart 0430 Inkasso";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0500_SCHADENINFORMATION = "Version Satzart 0500 Schadeninformation";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0420_VERSICHERUNGSTEUERABRECHNUNG = "Version Satzart 0420 Versicherungsteuerabrechnung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0450_INKASSOABRECHNUNG = "Version Satzart 0450 Inkassoabrechnung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0550_SCHADENABRECHNUNG = "Version Satzart 0550 Schadenabrechnung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0350_KLAUSELN = "Version Satzart 0350 Klausel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0211_050 = "Version Satzart 0211 050";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_051 = "Version Satzart 0221 051";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_052 = "Version Satzart 0221 052";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_053 = "Version Satzart 0221 053";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_054 = "Version Satzart 0221 054";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_055 = "Version Satzart 0221 055";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_059 = "Version Satzart 0221 059";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0211_040 = "Version Satzart 0211 040";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_040 = "Version Satzart 0221 040";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_030 = "Version Satzart 0221 030";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0211_010 = "Version Satzart 0211 010";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_010 = "Version Satzart 0221 010";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0211_130 = "Version Satzart 0211 130";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_130 = "Version Satzart 0221 130";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0211_110 = "Version Satzart 0211 110";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_110 = "Version Satzart 0221 110";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0211_140 = "Version Satzart 0211 140";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_140 = "Version Satzart 0221 140";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_070 = "Version Satzart 0221 070";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0211_FEUER = "Version Satzart 0211 Feuer-Industrie/Gewerbliche Sachversicherung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_FEUER = "Version Satzart 0221 Feuer-Industrie/Gewerbliche Sachversicherung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_510 = "Version Satzart 0221 510";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0211_TRANSPORT = "Version Satzart 0211 Transport";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_TRANSPORT = "Version Satzart 0221 Transport";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0251_TRANSPORT = "Version Satzart 0251 Transport Einzelanmeldung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0211_NICHT_DEF_SPARTEN = "Version Satzart 0211 Nicht einzeln definierte Sparten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0221_NICHT_DEF_SPARTEN = "Version Satzart 0221 Nicht einzeln definierte Sparten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_550 = "Version Satzart 0210 550";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_550 = "Version Satzart 0220 550";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0270_550 = "Version Satzart 0270 550";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0280_550 = "Version Satzart 0280 550";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0291_550 = "Version Satzart 0291 550";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0292_550 = "Version Satzart 0292 550";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0293_550 = "Version Satzart 0293 550";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0294_550 = "Version Satzart 0294 550";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0295_550 = "Version Satzart 0295 550";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0052 = "Version Satzart 0051";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0102 = "Version Satzart 0102";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0212 = "Version Satzart 0212";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0352 = "Version Satzart 0352";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0362 = "Version Satzart 0362";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0382 = "Version Satzart 0382";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_9950 = "Version Satzart 9950";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_9952 = "Version Satzart 9952";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_580 = "Version Satzart 0210 580";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_580 = "Version Satzart 0220 580";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0372 = "Version Satzart 0372";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0600 = "Version Satzart 0600";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERTRAGSABLAUF = "Vertragsablauf";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERTRAGSBEGINN = "Vertragsbeginn";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERTRAGSFORM = "Vertragsform";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERTRAGSSTATUS = "Vertragsstatus";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VORAUSSICHTLICHES_ENDE = "voraussichtliches Ende";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VORLAUFSSUMME_IN_WAEHRUNGSEINHEITEN = "Vorlaufsumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VORNAME_VP = "Vorname der VP";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VORSTEUERABZUGSBERECHTIGT = "Vorsteuerabzugsberechtigt";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VORZUGSSEUERBERECHTIGUNG_PROZENT = "Vorsteuerabszugsberechtigung in Prozent";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VORZUGSSTEUERBERECHTIGUNG = "Vorsteuerabszugsberechtigung Ja/Nein";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VP_PERSONENNUMMER_VERMITTLER = "VP-Personnenummer des Vermittlers";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VP_PERSONENNUMMER_VERSICHERER = "VP-Personnenummer des Versicherers";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VU_ABRECHNUNGSSTELLE = "VU-Abrechnungsstelle";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAEHRUNGSSCHLUESSEL = "Waehrungsschluessel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAEHRUNG_DOKUMENTE_FUER_VN = "Waehrung der Dokumente fuer VN";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAGNIS = "Wagnis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAGNISKENNZIFFER = "Wagniskennziffer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WARTEZEIT = "Wartezeit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WECHSELKENNZEICHEN_W_AKZ = "Wechselkennzeichen W-AKZ";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WEITERE_REFERENZNUMMERN = "Weitere Referenznummern";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WOHNEIGENTUM = "Wohneigentum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZAHLUNGSANFANG = "Zahlungsanfang";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZAHLUNGSART = "Zahlungsart /-weg";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZAHLUNGSWEISE = "Zahlungsweise";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZAHLUNGSWEISE_KUENFTIGER_GESAMTBETRAG = "Zahlungsweise des kuenftigen Gesamtbetrags";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZIELGRUPPENSCHLUESSEL = "Zielgruppenschluessel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUKUENFTIGER_GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN = "Zukuenftiger Gesamtbeitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUSAETZLICHE_SATZKENNUNG = "zusaetzliche Satzkennung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUSCHLAG1_IN_PROZENT = "Zuschlag-1 in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUSCHLAG1_IN_WAEHRUNGSEINHEITEN = "Zuschlag-1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUSCHLAG2_IN_PROZENT = "Zuschlag-2 in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUSCHLAG2_IN_WAEHRUNGSEINHEITEN = "Zuschlag-2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN = "Zuschlagsbetrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUSCHLAG_IN_PROZENT = "Zuschlag in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUZAHLUNGSDATUM = "Zuzahlungsdatum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUZAHLUNGSRECHT = "Zuzahlungsrecht";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0392_EVB = "Version Satzart 0392 eVB-Nummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0230_LEBEN = "Version Satzart 0230 Fondsdatensatz - Leben";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0202_ALLG_ANTRAGSDATEN = "Version Satzart 0202 Allgemeine Antragsdaten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0230_UNFALL = "Version Satzart 0230 Unfall Leistungsarten";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0390_RABATTE = "Version Satzart 0390 Rabatte und Zuschlaege";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0342_BEGLEITDOK = "Version Satzart 0342 Begleitdokumente und Signaturen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_9951_MIME = "Version Satzart 9951 MIME-Datei";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_560 = "Version Satzart 0210 560";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_560 = "Version Satzart 0220 560";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_570 = "Version Satzart 0210 570";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_570 = "Version Satzart 0220 570";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0210_684 = "Version Satzart 0210 684";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSION_SATZART_0220_684 = "Version Satzart 0220 684";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FAELLIGKEIT_DER_LETZTEN_BEITRAGSZAHLUNG = "Faelligkeit der letzten Beitragszahlung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEBENSLANGE_BEITRAGSZAHLUNG = "Lebenslange Beitragszahlung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BESONDERE_VEREINBARUNGEN = "Besondere Vereinbarungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DIREKTANSPRUCH = "Direktanspruch";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_EINTRITTSALTER_DER_VP = "Eintrittsalter der VP";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ALTERSGRUPPE = "Altersgruppe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSZAHLUNG_BIS = "Beitragszahlung bis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RUECKGEWAEHRDATUM = "Rueckgewaehrdatum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RUECKGEWAEHRSUMME_ZUM_ABLAUF_IN_WAEHRUNGSEINHEITEN = "Rueckgewaehrsumme zum Ablauf";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABLAUFLEISTUNG_INKL_UEBERSCHUSSANTEILE_IN_WAEHRUNGSEINHEITEN = "Ablaufleistung incl. Ueberschussanteile";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KAPITALERTRAGSSTEUER_BEI_ABLAUF = "Kapitalertragssteuer bei Ablauf";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SOLIDARITAETSZUSCHLAG_BEI_ABLAUF = "Solidarit\u00e4tszuschlag bei Ablauf";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RUECKKAUFSWERT_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = "Rueckkaufswert zum Berechnungsstichtag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BERECHNUNGSSTICHTAG_ZUM_RUECKKAUFSWERT = "Berechnungsstichtag zum Rueckkaufswert";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KAPITALERTRAGSSTEUER_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG = "Kapitalertragssteuer bei Rueckkauf zum Berechnungsstichtag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SOLIDARITAETSZUSCHLAG_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG = "Solidarit\u00e4tszuschlag bei Rueckkauf zum Berechnungsstichtag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UEBERSCHUSSANTEILE_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = "Ueberschussanteile zum Berechnungsstichtag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GEBURTSDATUM_DER_BEZUGSBERECHTIGTEN_PERSON = "Geburtsdatum der bezugsberechtigten Person";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANZAHL_DER_VERSICHERTEN_PERSONEN = "Anzahl der versicherten Personen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DATUM_UNVERFALLBARKEIT = "Datum Unverfallbarkeit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DIENSTEINTRITT = "Diensteintritt";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESCHLECHT_VP = "Geschlecht der VP";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_ZUR_WAGNISART = "Lfd Nummer zur Wagnisart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MITARBEITER_STATUS = "Mitarbeiter Status";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PERSONEN_KUNDENNUMMER_DES_VERMITTLERS = "Personen Kundennummer des Vermittlers";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SOZIALVERSICHERUNG_NUMMER = "Sozialversicherung Nummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_STATUS_SEIT = "Status seit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAGNISART = "Wagnisart";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VORZEICHEN = "Vorzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VORZEICHEN2 = "Vorzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VORZEICHEN3 = "Vorzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VORZEICHEN4 = "Vorzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VORZEICHEN5 = "Vorzeichen";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = "Erlebensfall VS zum Ablauf";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TODESFALL_VS_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = "Todesfall VS zum Ablauf";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = "Erlebensfall VS II zum Ablauf";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSFREIE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Beitragsfreie Erlebensfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG = "Absoluter Dynamikerhoehungsbetrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = "Erlebensfall VS zum Beginn der Abrufphase";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = "Todesfall VS zum Beginn der Abrufphase";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = "Erlebensfall VS II zum Beginn der Abrufphase";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANFAENGLICHE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Anfaengliche Erlebensfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AENDERUNG_DER_ERLEBENSFALL_VS = "Aenderung der Erlebensfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSTAND_DER_ERLEBENSFAL_VS_AENDERUNGSTERMINE = "Abstand der Erlebensfall VS-Aenderungstermine";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERLEBENSFALL_VS_AENDERUNGS_PROZENTSATZ = "Erlebensfall VS-Aenderungs-Prozentsatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSOLUTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Absolute Erlebensfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAECHSTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Naechste Erlebensfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_ERLEBENSFALL_VS = "Beginndatum der naechsten Erlebensfall VS";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_BU_IN_WAEHRUNGSEINHEITEN = "Beitrag BU";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_EINSCHLUSS_PROZENT_SATZ = "Einschluss %-Satz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZWANG_ZUR_BUZ = "Zwang zur BUZ";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEISTUNGSDAUER = "Leistungsdauer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BUZ_VERWENDUNGSART = "BUZ Verwendungsart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RENTE_INCL_UEBERSCHUSSBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = "Rente inkl. Ueberschussbeteiligung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BUZ_LEISTUNG_VON = "BUZ Leistung von";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BUZ_LEISTUNG_BIS = "BUZ Leistung bis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BUZ_PROZENT_SATZ = "BUZ %-Satz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KARENZZEIT = "Karenzzeit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABWEICHENDE_LEISTUNGSDAUER = "Abweichende Leistungsdauer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUKUENFTIGER_BEITRAG = "Zukuenftiger Beitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAERHUNGSEINHEITEN = "Absoluter Dynamikerhoehungsbetrag";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT = "Erlebensfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TODESFALL_VS_IN_WAEHRUNGSEINHEIT = "Todesfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERLEBENSFALL_VS2_IN_WAEHRUNGSEINHEIT = "Erlebensfall VS II";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSFREIE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT = "Beitragsfreie Erlebensfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSFREIE_TODESFALL_VS_IN_WAEHRUNGSEINHEIT = "Beitragsfreie Todesfall VS";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Versicherungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FALLENDE_SUMME = "fallende Summe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RENTE_INKL_UEBERSCHUSSANRECHNUNG_IN_WAEHRUNGSEINHEITEN = "Rente inkl. Ueberschussanrechnung";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSOLUTE_UNFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Unfallaenderungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAECHSTE_UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = "Naechste Unfallsumme";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANFAENGLICHE_UNFALLSUMME = "Anfaengliche Unfallsumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AENDERUNG_DER_UNFALLLEISTUNG = "Aenderung der Unfallleistung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSTAND_DER_UNFALLAENDERUNGSTERMINE = "Abstand der Unfallaenderungstermine";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UNFALLAENDERUNGS_PROZENTSATZ = "Unfallaenderungs-Prozentsatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_UNFALLSUMME = "Beginndatum der naechsten Unfallsumme";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_PROMILLE = "Beitrag Promille";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = "Unfallsumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOZUSCHLAG = "Risikozuschlag";

    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_DES_FONDS = "Lfd. Nummer des Fonds";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WERTPAPIERKENNNUMMER = "Wertpapierkennnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FONDSKENNUNG = "Fondskennung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ISIN_NUMMER = "ISIN-Nummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FONDSNAME = "Fondname";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_THESAUR = "Thesaur";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANTEILE = "Anteile";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEZUGSDATUM = "Bezugsdatum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PROZENTSATZ = "Prozentsatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_JAHRESRENTESUMME = "Beginndatum der naechsten Jahresrentensumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GUTHABEN_DIVID_ANSAMMLUNG_IN_WAEHRUNGSEINHEITEN = "Guthaben Divid. Ansammlung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN2 = "Nettobeitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GARANTIERTE_FONDSJAHRESRENTE_ZUM_ABLAUF_INKL_ABRUFPHASE = "Garantierte Fondsjahresrente zum Ablauf (inkl. Abrufphase)";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GARANTIERTE_FONDSJAHRESRENTE_ZUM_BEGINN_DER_ABRUFPHASE = "Garantierte Fondsjahresrente zum Beginn der Abrufphase";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AKTUELLE_FONDSJAHRESRENTE_ZUM_ABLAUF_INKL_ABRUFPHASE = "Aktuelle Fondsjahresrente zum Ablauf (inkl. Abrufphase)";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AKTUELLE_FONDSJAHRESRENTE_ZUM_BEGINN_DER_ABRUFPHASE = "Aktuelle Fondsjahresrente zum Beginn der Abrufphase";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAGNISART4 = "Wagnisart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_ZUR_WAGNISART4 = "Lfd. Nummer zur Wagnisart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSOLUTE_TODESFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Todesfallaenderungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AENDERUNG_DER_BEITRAGSSUMME = "Aenderung der Beitragssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSTAND_DER_BEITRAGSSUMMENAENDERUNGSTERMINE = "Abstand der Beitragssummenaenderungstermine";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSSUMMENAENDERUNGS_PROZENTSATZ = "Beitragssummenaenderungs-Prozentsatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSOLUTE_BEITRAGSSUMMENAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Beitragssummenaenderungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAECHSTE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Naechste Beitragssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_BEITRAGSSUMME = "Beginndatum der naechsten Beitragssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Beitragssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Todesfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_FALLENDE_VS = "fallende VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEITEN = "Erlebensfall VS II";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSFREIE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Beitragsfreie Beitragssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSFREIE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Beitragsfreie Todesfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LAUFZEITVERKUERZUNG = "Laufzeitverkuerzung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERTRAG_MIT_ZUWACHSGARANTIE = "Vertrag mit Zuwachsgarantie";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TODESFALLLEISTUNG_IN_PROZENT = "Todesfallleistung in %";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SPARVORGANG = "Sparvorgang";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESTUNDET_AUSGESETZT_BIS = "Gestundet ausgesetzt bis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANLAGESTRATEGIE = "Anlagestrategie";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = "Erlebensfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GUTHABEN_DIVID_ANSAMMLUNG_IN_WAEHRUNGSEINHEIT = "Guthaben Divid. Ansammlung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KAPITALZAHLUNGS_SUMME_IN_WAEHRUNGSEINHEIT = "Kapitalzahlungs-Summe";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANFAENGLICHE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Anfaengliche Todesfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AENDERUNG_DER_TODESFALLLEISTUNG = "Aenderung der Todesfallleistung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSTAND_DER_TODESFALLAENDERUNGSTERMINE = "Abstand der Todesfallaenderungstermine";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TODESFALLAENDERUNGS_PROZENTSATZ = "Todesfallaenderungs-Prozentsatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSOLUTE_TODESFALLAENDERUNGSSUMME_VS_IN_WAEHRUNGSEINHEITEN = "Absolute Todesfallaenderungssumme VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAECHSTE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = "Naechste Todesfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_TODESFALL_VS = "Beginndatum der naechsten Todesfall VS";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANFAENGLICHE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = "Anfaengliche Jahresrente";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AENDERUNG_DER_JAHRESRENTE = "Aenderung der Jahresrente";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSTAND_DER_JAHRESRENTENAENDERUNGSTERMINE = "Abstand der Jahresrentenaenderungstermine";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_JAHRESRENTENAENDERUNGS_PROZENTSATZ = "Jahresrentenaenderungs-Prozentsatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSOLUTE_JAHRESRENTENAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Absolute Jahresrentenaenderungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAECHSTE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = "Naechste Jahresrente";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINNDATUM_DER_NAECHSTEN_JAHRESRENTE = "Beginndatum der naechsten Jahresrente";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = "Provisionspflichtige Beitragssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Provisionspflichtige Wertungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WERTUNGSBASIS = "Wertungsbasis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WERTUNGSMODELL = "Wertungsmodell";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BUCHUNGSKENNZEICHEN = "Buchungskennzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Haftungswertungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_HAFTUNG_AB = "Haftung ab";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_HAFTUNG_BIS = "Haftung bis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_URSPRUENGLICHES_HAFTUNGSBEGINNDATUM = "Urspruengliches Haftungsbeginndatum";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Provisionspflichtige Beitragssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Provisionspflichtige Wertungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WERTUNGSBASIS2 = "Wertungsbasis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WERTUNGSMODELL2 = "Wertungsmodell";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BUCHUNGSKENNZEICHEN2 = "Buchungskennzeichen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Haftungswertungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_HAFTUNG_AB2 = "Haftung ab";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_HAFTUNG_BIS2 = "Haftung bis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEZUGSBERECHTIGT_IM_ERLEBENSFALL = "Bezugsberechtigt im Erlebensfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SONSTIGER_BEZUGSBERECHTIGTER_IM_ERLEBENSFALL = "Sonstiger Bezeugsberechtigter im Erlebensfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEZUGSRECHTANTEIL_IM_ERLEBENSFALL = "Bezugsrechtanteil im Erlebensfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UNWIDERRUFLICHES_BEZUGSRECHT_IM_ERLEBENSFALL = "Unwiderrufliches Bezugsrecht im Erlebensfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEZUGSBERECHTIGT_IM_TODESFALL = "Bezugsberechtigt im Todesfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SONSTIGER_BEZUGSBERECHTIGTER_IM_TODESFALL = "Sonstiger Bezugsberechtigter im Todesfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEZUGSRECHTANTEIL_IM_TODESFALL = "Bezugsrechtanteil im Todesfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UNWIDERRUFLICHES_BEZUGSRECHT_IM_TODESFALL = "Unwiderrufliches Bezugsrecht im Todesfall";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAECHSTE_AUSZAHLUNGSSUMMER_IN_WAEHRUNGSEINHEITEN = "Naechste Auszahlungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NAECHSTER_AUSZAHLUNGSTERMIN = "Naechster Auszahlungstermin";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AUSZAHLUNGSWEISE = "Auszahlungsweise";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANZAHL_DER_AUSZAHLUNGEN = "Anzahl der Auszahlungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABLAUF = "Ablauf";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AENDERUNG = "Aenderung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERTRAGSLAUFZEIT = "Vertragslaufzeit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERTRAGSART = "Vertragsart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_STATUS = "Status";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GEWINNVERWENDUNGSART = "Gewinnverwendungsart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UEBERSCHUSS_GUELTIG_AB = "Ueberschuss gueltig ab";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOEINSCHRAENKUNG = "Risikoeinschraenkung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOZUSCHLAEGE = "Risikozuschlaege";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DYNAMIK_PROZENT_SATZ = "Dynamik %-Satz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERHOEHUNGSBASIS_DYNAMIK = "Erhoehungsbasis Dynamik";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DYNAMIKSTOP = "Dynamikstop";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DATUM_DER_LETZTEN_POSITIVEN_DYNAMIK = "Datum der letzten positiven Dynamik";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN = "Rueckkaufswert";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN_MIT_NACHKOMMA = "Rueckkaufswert";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RUECKKAUFSWERT_GUELTIG_AB = "Rueckkaufswert gueltig ab";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GUTHABEN_DIVID_ANSAMMLUNGEN_IN_WAEHRUNGSEINHEITEN = "Guthaben Divid. Ansammlungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINN_DER_RENTENZAHLUNG = "Beginn der Rentenzahlung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MINDESTLAUFZEIT = "Mindestlaufzeit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RUECKGEWAEHR_BEI_TOD = "Rueckgewaehr bei Tod";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = "Jahresrente";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Kapitalzahlungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WITWEN_WITWERRENTE_IN_PROZENT = "Witwen- / Witwerrente in Prozent";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TECHNISCHE_WITWEN_WITTWERRENTE_IN_PROZENT = "Technische Witwen- / Witwerrente in Prozent";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAISENRENTE_IN_PROZENT = "Waisenrente in Prozent";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TECHNISCHE_WAISE_IN_PROZENT = "Technische Waisenrente in Prozent";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SCHLUSSALTER_DES_WAISEN = "Schlussalter des Waisen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_AUSLOESUNG_DER_LEISTUNG = "Ausloesung der Leistung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZAHLUNG_DER_WITWEN_WITWERRENTE_BIS = "Zahlung der Witwen- / Witwerrente bis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ENDALTER = "Endalter";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_EINTRITTSALTER = "Eintrittsalter";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UMTAUSCHRECHT = "Umtauschrecht";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_STUNDUNG = "Stundung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINN_ABRUFPHASE = "Beginn Abrufphase";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAGNISART2 = "Wagnisart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_ZUR_WAGNISART2 = "Lfd. Nummer Wagnisart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABWEICHENDE_VERTRAGSLAUFZEIT = "Abweichende Vertragslaufzeit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABWEICHENDER_ABLAUF = "Abweichender Ablauf";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN = "Nettobeitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOZUSCHLAG_IN_WAEHRUNGSEINHEITEN = "Risikozuschlag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RENTENZAHLWEISE = "Rentenzahlweise";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MITZUVERSICHERNDE_PERSON = "Mitzuversichernde Person";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GEBURTSDATUM_DER_MITZUVERSICHERNDEN_PERSON = "Geburtsdatum der mitzuversichernden Person";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESCHLECHT_DER_MITZUVERSICHERNDEN_PERSON = "Geschlecht der mitzuverschernden Person";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TARIFBEZEICHNUNG_DES_FOLGETARIFS = "Tarifbezeichnung des Folgetarifs";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UMSTELLUNGSDATUM_DES_FOLGETARIFS = "Umstellungsdatum des Folgetarifs";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ZUKUENFTIGER_BEITRAG_IN_WAEHRUNGSEINHEITEN = "Zukuenftiger Beitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERTRAGSBEDINGUNG = "Vertragsbedingung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DYNAMIKBEGINN = "Dynamikbeginn";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABWEICHENDES_DYNAMIKENDALTER = "Abweichendes Dynamikalter";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAEHRUNGSEINHEITEN = "Absoluter Dynamikerhoehungsbetrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANTEILIGER_DYNAMIKPROZENTSATZ = "Anteiliger Dynamikprozentsatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VEREINBARTER_DYNAMIKMINDESTANPASSUNGSPROZENTSATZ = "Vereinbarter Dynamikmindestanpassungsprozentsatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VEREINBARTER_DYNAMIKMAXIMALANPASSUNGSPROZENTSATZ = "Vereinbarter Dynamikmaximalanpassungsprozentsatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANZAHL_VERBLEIBENDE_DYNAMIKWIDERSPRUECHE = "Anzahl verbleibende Dynamikwidersprueche";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAGNISART3 = "Wagnisart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_ZUR_WAGNISART3 = "Lfd. Numemr Wagnisart";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEISTUNG_BEI_SCHWERER_ERKRANKUNG = "Leistung bei schwerer Erkrankung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_VERSICHERTE_ERKRANKUNGEN = "Versicherte Erkrankungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEISTUNGSBEGINN_AB = "Leistungsbeginn ab";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN2 = "Jahresrente";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = "Kapitalzahlungssumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TEILKAPITALISIERUNG = "Teilkapitalisierung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TODESFALLLEISTUNG_IN_WAEHRUNGSEINHEITEN = "Todesfallleistung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_EINGERECHNETE_ZULAGE = "Eingerechnete Zulage";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_EINRECHNUNGSJAHR = "Einrechnungsjahr";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TEILDATENSATZNUMMER = "Teildatensatznummer";

    // Haftpflicht
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNZEICHEN_FUER_ABWEICHENDE_ABSCHLUSSPROVISION = "Kennzeichen fuer abweichende Abschlussprovision";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNZEICHEN_FUER_ABWEICHENDE_FOLGEPROVISION = "Kennzeichen fuer abweichende Folgeprovision";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RESTLAUFZEIT_DES_VERTRAGES = "Restlaufzeit des Vertrages";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SELBSTBEHALT = "Selbstbehalt";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAEHRUNGSSCHLUESSEL_1 = "Waehrungsschluessel 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAEHRUNGSSCHLUESSEL_2 = "Waehrungsschluessel 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAEHRUNGSSCHLUESSEL_3 = "Waehrungsschluessel 3";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAEHRUNGSSCHLUESSEL_4 = "Waehrungsschluessel 4";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG_JE_BERECHNUNGSEINHEIT_UND_MENGENSCHLUESSEL = "Beitrag je Berechnungseinheit und Mengenschluessel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BERECHNUNGSEINHEIT = "Berechnungseinheit";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DECKUNGSSUMME_1_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 1 in Tausend";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DECKUNGSSUMME_2_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 2 in Tausend";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DECKUNGSSUMME_3_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 3 in Tausend";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DECKUNGSSUMME_4_IN_TAUSEND_WAEHRUNGSEINHEITEN = "Deckungssumme 4 in Tausend";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERHOEHUNGSSATZ_8_III_AHB = "Erhoehungssatz \u00a78, III AHB";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNUNG_ERHOEHUNGSSATZ = "Kennung Erhoehungssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_KENNZEICHEN_FUER_JAHRES_MAXIMIERUNG = "Kennzeichen fuer Jahres-Maximierung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LETZTE_ERHOEHUNG_8_III_AHB = "Letzte Erhoehung \u00a78, III AHB";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MENGENSCHLUESSEL = "Mengenschluessel";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_MINDESTBEITRAG_JE_WAGNIS_IN_WAEHRUNGSEINHEITEN = "Mindestbeitrag je Wagnis";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ORDNUNGS_NUMMER_FUER_WAGNISZUSATZ2 = "Ordungs-Nummer fuer Wagniszusatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_POSTLEITZAHL_DER_RISIKOANSCHRIFT = "Postleitzahl der Risikoanschrift";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOORT = "Risikoort";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOSTRASSE = "Risikostrasse";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SUMMENART_1 = "Summenart 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SUMMENART_2 = "Summenart 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SUMMENART_3 = "Summenart 3";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SUMMENART_4 = "Summenart 4";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAGNISBESCHREIBUNG = "Wagnisbeschreibung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAGNISMENGE = "Wagnismenge";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WAGNISTEXT = "Wagnistext";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DECKUNGSSUMME_1_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 1";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DECKUNGSSUMME_2_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 2";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DECKUNGSSUMME_3_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 3";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DECKUNGSSUMME_4_IN_WAEHRUNGSEINHEITEN = "Deckungssumme 4";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ORDNUNGS_NUMMER_FUER_WAGNISZUATZ = "Ordungs-Nummer fuer Wagniszusatz";

    // Unfall
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_REFERENZ_VERSICHERUNGSSCHEINNUMME = "Referenz-Versicherungsscheinnumme";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_DAUERSCHAEDEN_KOERPERLICHE_BEEINTRAECHTIGUNGEN = "Dauerschaeden / koerperliche Beeintraechtigungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERKRANKUNGEN = "Erkrankungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UNFAELLE = "Unfaelle";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ART_DER_AUSZAHLUNG = "Art der Auszahlung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ART_DER_LEISTUNG = "Art der Leistung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ART_DES_BEITRAGSSATZES = "Art des Beitragssatzes";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEGINN_DER_ZAHLUNG_AB_TAG = "Beginn der Zahlung ab Tag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAG = "Beitrag";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEITRAGSSATZ = "Beitragssatz";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEZEICHNUNG_DER_LEISTUNG = "Bezeichnung der Leistung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEISTUNG = "Leistung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEISTUNG_AB_INVALIDITAETSGRAD_IN_PROZENT = "Leistung ab Invaliditaetsgrad in Prozent";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LEISTUNGSZAHLUNGSWEISE = "Leistungszahlungsweise";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LFD_NUMMER_ZUR_ART_DER_LEISTUNG = "Laufende Nummer zur Art der Leistung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_PROZENTSATZ_PROGRESSIVE_INVALIDITAET_MEHRLEISTUNG_BEI_INVALIDITAET = "Prozentsatz progressive Invaliditaet / Mehrleistung bei Invaliditaet";

    // Verbundene Hausrat
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_INTRO1 = "Intro";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_LAENDERKENNZEICHEN_DER_RISIKOANSCHRIFT = "Laenderkennzeichen der Risikoanschrift";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESAMTVERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = "Gesamtversicherungssummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BEDINGUNGEN = "Bedingungen";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ERWEITERTE_NEUWERTVERSICHERUNG = "erweiterte Neuwertversicherung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_SICHERUNGSRICHTLINIEN = "Sicherungsrichtlinien";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_EINBRUCH_MELDEANLAGE = "Einbruch Meldeanlage";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_RISIKOKENNZIFFER = "Risikokennziffer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_ANZAHL_MONATE_UNBEWOHNT = "Anzahl Monate unbewohnt";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_WOHNFLAECHE_QM = "Wohnflaeche qm";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_TARIFZONE = "Tarifzone";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_BAUARTKLASSE = "Bauartklasse";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GEFAHRENERHOEHUNG = "Gefahrenerhoehung";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_EINSCHLUSS_VANDALISMUS = "Einschluss Vandalismus";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_UNTERVERS_VERZICHT = "Untervers.-Verzicht";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_OBJEKTNUMMER2 = "Objektnummer";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_GESCHAEFTSSTELLE_VERMITTLER = "Geschaeftsstelle / Vermittler";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
    public static final String NAME_STAENDIG_BEWOHNT = "Staendig bewohnt";
    /** @deprecated Bitte entsprechende Bezeichner-Konstante verwenden. */
    @Deprecated
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
        return this.technischerName;
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
