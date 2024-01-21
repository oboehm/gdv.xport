/*
 * Copyright (c) 2009 - 2021 by Oli B.
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import gdv.xport.feld.internal.UmlautMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Diese Klasse enthaelt die Namen der einzelnen Felder. Die Konstanten sind
 * (mehr oder weniger) alphabetisch geordnet.
 * <p>
 * Daneben dient diese Klasse auch als String-Erweiterung, um verschiedene
 * Bezeichner besser vergleichen zu koennen. So enthalten manche
 * Bezeichner-Konstanten Leerzeichen, die bei einem Vergleich nicht
 * beruecksichtigt werden sollen.
 * </p>
 *
 * @author oliver
 * @since 15.10.2009
 */
public final class Bezeichner implements Serializable {

    private static final Logger LOG = LogManager.getLogger(Bezeichner.class);
    private static final Map<String, String> MAPPING = new HashMap<>();
    private static final List<Bezeichner> CONSTANTS = new ArrayList<>();
    private static final Map<String, Bezeichner> CACHED = new ConcurrentHashMap<>();

    /////////// Bezeichner-Konstanten (alphabetisch geordnet) /////////////////

    public static final Bezeichner ABGANGSDAT = new Bezeichner("Abgangsdatum");
    public static final Bezeichner ABGANGSGRUND = new Bezeichner("Abgangsgrund");
    public static final Bezeichner ABLAUF = new Bezeichner("Ablauf");
    public static final Bezeichner ABLAUFTERMIN = new Bezeichner("Ablauftermin");
    public static final Bezeichner ABLAUFLEISTUNG_INKL_UEBERSCHUSSANTEILE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Ablaufleistung incl. Ueberschussanteile in W\u00e4hrungseinheiten");
    public static final Bezeichner ABSCHLAG1_IN_PROZENT = new Bezeichner("Abschlag-1 in %");
    public static final Bezeichner ABSCHLAG1_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Abschlag-1 in W\u00e4hrungseinheiten");
    public static final Bezeichner ABSCHLAG2_IN_PROZENT = new Bezeichner("Abschlag-2 in %");
    public static final Bezeichner ABSCHLAG2_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Abschlag-2 in W\u00e4hrungseinheiten");
    public static final Bezeichner ABSCHLAG3_IN_PROZENT = new Bezeichner("Abschlag-3 in %");
    public static final Bezeichner ABSCHLAG3_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Abschlag-3 in W\u00e4hrungseinheiten");
    public static final Bezeichner ABSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Abschlagsbetrag in W\u00e4hrungseinheiten");
    public static final Bezeichner ABSCHLAG_IN_PROZENT = new Bezeichner("Abschlag in %");
    public static final Bezeichner ABSCHLAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Abschlag in Währungseinheiten");
    public static final Bezeichner ABSCHLUSSPROVISION = new Bezeichner("Abschlussprovision");
    public static final Bezeichner ABSENDER = new Bezeichner("Absender");
    public static final Bezeichner ABSOLUTE_BEITRAGSSUMMENAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absolute Beitragssummenaenderungssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner ABSOLUTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absolute Erlebensfall VS in W\u00e4hrungseinheiten");
    public static final Bezeichner ABSOLUTE_TODESFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absolute Todesfallaenderungssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner ABSOLUTE_TODESFALLAENDERUNGSSUMME_VS_IN_WAEHRUNGSEINHEITEN = ABSOLUTE_TODESFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN;
    public static final Bezeichner ABSOLUTE_UNFALLAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absolute Unfallaenderungssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner ABWEICHENDES_DYNAMIKENDALTER = new Bezeichner("Abweichendes Dynamikendalter");
    public static final Bezeichner ABSOLUTE_JAHRESRENTENAENDERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absolute Jahresrentenaenderungssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner ABSOLUTER_DYNAMIKERHOEHUNGSBETRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Absoluter Dynamikerhoehungsbetrag in W\u00e4hrungseinheiten");
    public static final Bezeichner ABSTAND_DER_ERLEBENSFALL_VS_AENDERUNGSTERMINE = new Bezeichner("Abstand der Erlebensfall VS-Aenderungstermine");
    public static final Bezeichner ABSTAND_DER_BEITRAGSSUMMENAENDERUNGSTERMINE = new Bezeichner("Abstand der Beitragssummenaenderungstermine");
    public static final Bezeichner ABSTAND_DER_JAHRESRENTENAENDERUNGSTERMINE = new Bezeichner("Abstand der Jahresrentenaenderungstermine");
    public static final Bezeichner ABSTAND_DER_TODESFALLAENDERUNGSTERMINE = new Bezeichner("Abstand der Todesfallaenderungstermine");
    public static final Bezeichner ABSTAND_DER_UNFALLAENDERUNGSTERMINE = new Bezeichner("Abstand der Unfallaenderungstermine");
    public static final Bezeichner ABWEICHENDE_LEISTUNGSDAUER = new Bezeichner("Abweichende Leistungsdauer");
    public static final Bezeichner ABWEICHENDE_VERTRAGSLAUFZEIT = new Bezeichner("Abweichende Vertragslaufzeit");
    public static final Bezeichner ABWEICHENDER_ABLAUF = new Bezeichner("Abweichender Ablauf");
    public static final Bezeichner ABWEICHENDER_KONTOINHABER1 = new Bezeichner("Abweichender Kontoinhaber 1");
    public static final Bezeichner ABWEICHENDER_KONTOINHABER2 = new Bezeichner("Abweichender Kontoinhaber 2");
    public static final Bezeichner ABWEICHENDE_VU_NR = new Bezeichner("Abweichende VU-Nr.");
    public static final Bezeichner AENDERUNG_DER_BEITRAGSSUMME = new Bezeichner("Aenderung der Beitragssumme");
    public static final Bezeichner AENDERUNG_DER_ERLEBENSFALL_VS = new Bezeichner("Aenderung der Erlebensfall VS");
    public static final Bezeichner AENDERUNG_DER_TODESFALLLEISTUNG = new Bezeichner("Aenderung der Todesfallleistung", "AenderungTodesfallleistung", "AenderungTodesfalleistung");
    public static final Bezeichner AENDERUNG_DER_JAHRESRENTE = new Bezeichner("Aenderung der Jahresrente");
    public static final Bezeichner AENDERUNG_DER_UNFALLLEISTUNG = new Bezeichner("Aenderung der Unfallleistung");
    public static final Bezeichner AENDERUNGSDAT = new Bezeichner("Aenderungsdatum");
    public static final Bezeichner AENDERUNGSDATUM = new Bezeichner("Aenderungsdatum");
    public static final Bezeichner ANFAENGLICHE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Anfaengliche Beitragssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner ANFAENGLICHE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Anfaengliche Erlebensfall VS in W\u00e4hrungseinheiten");
    public static final Bezeichner ANFAENGLICHE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Anfaengliche Todesfall VS in W\u00e4hrungseinheiten");
    public static final Bezeichner ANFAENGLICHE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Anfaengliche Jahresrente in W\u00e4hrungseinheiten");
    public static final Bezeichner ANFAENGLICHE_UNFALLSUMME = new Bezeichner("Anfaengliche Unfallsumme");
    public static final Bezeichner ANLAGESTRATEGIE = new Bezeichner("Anlagestrategie");
    public static final Bezeichner ANTEILIGER_DYNAMIKPROZENTSATZ = new Bezeichner("Anteiliger Dynamikprozentsatz");
    public static final Bezeichner ANTRAGSDAT = new Bezeichner("Antragsdatum");
    public static final Bezeichner ANTRAGSEINGANGSDAT = new Bezeichner("Antragseingangsdatum");
    public static final Bezeichner ANZAHL_DER_AUSZAHLUNGEN = new Bezeichner("Anzahl der Auszahlungen");
    public static final Bezeichner ANZAHL_DER_KINDER = new Bezeichner("Anzahl der Kinder");
    public static final Bezeichner ANZAHL_DER_VERSICHERTEN_FAHRZEUGE = new Bezeichner("Anzahl der versicherten Fahrzeuge");
    public static final Bezeichner ANZAHL_DER_VERSICHERTEN_PERSONEN = new Bezeichner("Anzahl der versicherten Personen");
    public static final Bezeichner ANZAHL_DER_VERSICHERTEN_TIERE_OHNE_NAMENSANGABE_TIERGRUPPE = new Bezeichner("Anzahl der versicherten Tiere ohne Namensangabe (Tiergruppe)");
    public static final Bezeichner ANZAHL_DER_PLAETZE_RISIKO1 = new Bezeichner("Anzahl der Plaetze (Risiko 1)");
    public static final Bezeichner ANZAHL_DER_PLAETZE_RISIKO2 = new Bezeichner("Anzahl der Plaetze (Risiko 2)");
    public static final Bezeichner ART1 = new Bezeichner("Art1", "Art1", "Art");
    /** Fuer 0220.580 */
    public static final Bezeichner ART_580 = new Bezeichner("Art");
    public static final Bezeichner ART_DER_HALTUNG = new Bezeichner("Art der Haltung");
    public static final Bezeichner ART_DER_TIERKENNZEICHNUNG = new Bezeichner("Art der Tierkennzeichnung");
    public static final Bezeichner ART_DER_SCHAETZUNG = new Bezeichner("Art der Schaetzung");
    public static final Bezeichner ART_DER_SUMME = new Bezeichner("Art der Summe");
    /** Fuer 0220.170 */
    /** @deprecated bitte ART_DER_SUMME verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner ART_DER_SUMME_2AN = ART_DER_SUMME;
    /**  Fuer 022x.130 */
    /** @deprecated bitte ART_DER_SUMME verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner ART_DER_SUMME_3AN = ART_DER_SUMME;
    /** @deprecated bitte ART_DER_SUMME verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner ART_DER_SUMME_2N = ART_DER_SUMME;
    /**  Fuer 0220.140 */
    /** @deprecated bitte ART_DER_SUMME verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner ART_DER_SUMME_3N = ART_DER_SUMME;

    public static final Bezeichner ART_DER_SUMMENANPASSUNG = new Bezeichner("Art der Summenanpassung");

    public static final Bezeichner ART_DES_SELBSTBEHALTS = new Bezeichner("Art des Selbstbehalts");
    /** @deprecated bitte ART_DES_SELBSTBEHALTS verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner ART_DES_SELBSTBEHALT = ART_DES_SELBSTBEHALTS;

  public static final Bezeichner ART_DES_BERUFSSCHLUESSELVERZEICHNISSES = new Bezeichner("Art des Berufsschluesselverzeichnisses");
    public static final Bezeichner ANWARTSCHAFTSGRUND = new Bezeichner("Anwartschaftsgrund");
    public static final Bezeichner AUSLANDSDECKUNG = new Bezeichner("Auslandsdeckung");
  public static final Bezeichner AUSSCHLUSSDATUM = new Bezeichner("Ausschlussdatum");
    public static final Bezeichner AUSSCHLUSSDAT_VP_PERSONENGRUPPE = new Bezeichner("Ausschlussdatum VP / Personengruppe");
    public static final Bezeichner AUSZAHLUNGSWEISE = new Bezeichner("Auszahlungsweise");
    public static final Bezeichner ADRESSAT = new Bezeichner("Adressat");
  public static final Bezeichner ADRESSKENNZEICHEN = new Bezeichner("Adresskennzeichen");
    public static final Bezeichner AENDERUNG = new Bezeichner("Aenderung");
    public static final Bezeichner AENDERUNGSGRUND = new Bezeichner("Aenderungsgrund");
    public static final Bezeichner AFB = new Bezeichner("A,F,B");
    public static final Bezeichner AKTENZEICHEN_SICHERUNGSGLAEUBIGER = new Bezeichner("Aktenzeichen des Sicherungsglaeubigers");
    public static final Bezeichner AKTUELLE_BEITRAGSDEPOTSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("aktuelle Beitragsdepotsumme in W\u00e4hrungseinheiten");
  public static final Bezeichner AKTUELLE_FONDSJAHRESRENTE_ZUM_ABLAUF_INKL_ABRUFPHASE = new Bezeichner("Aktuelle Fondsjahresrente zum Ablauf (inkl. Abrufphase)");
  public static final Bezeichner AKTUELLE_FONDSJAHRESRENTE_ZUM_BEGINN_DER_ABRUFPHASE = new Bezeichner("Aktuelle Fondsjahresrente zum Beginn der Abrufphase");
  public static final Bezeichner ALLGEMEINE_BEDINGUNGEN_FUER_DIE_VERKEHRSSERVICE_VERSICHERUNG = new Bezeichner("Allgemeine Bedingungen fuer die Verkehrsservice-Versicherung");
  public static final Bezeichner ALLGEMEINE_VERSICHERUNGSBEDINGUNGEN = new Bezeichner("Allgemeine Versicherungsbedingungen");
    public static final Bezeichner ALTERSDYNAMIK = new Bezeichner("Altersdynamik");
    public static final Bezeichner ALTERSGRUPPE = new Bezeichner("Altersgruppe");
    public static final Bezeichner ALTERSVORSORGEVERMOEGEN = new Bezeichner("Altersvorsorgevermögen");
    public static final Bezeichner AMTL_KENNZEICHEN = new Bezeichner("Amtl. Kennzeichen");
    public static final Bezeichner ANRECHNUNGSBETRAG_UMSTELLUNGSBETRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Anrechnungsbetrag / Umstellungsbetrag in Währungseinheiten");
    public static final Bezeichner ANREDESCHLUESSEL = new Bezeichner("Anredeschluessel");
    public static final Bezeichner ANTEIL_IN_PROZENT = new Bezeichner("Anteil in %");
    public static final Bezeichner ANTEILE = new Bezeichner("Anteile");
    public static final Bezeichner ANZAHL_DER_VORBESITZER = new Bezeichner("Anzahl der Vorbesitzer");
    public static final Bezeichner ANZAHL_MONATE_UNBEWOHNT = new Bezeichner("Anzahl Monate unbewohnt");
    public static final Bezeichner ANZAHL_SAETZE = new Bezeichner("Anzahl der Saetze");
    public static final Bezeichner ANZAHL_VERBLEIBENDE_DYNAMIKWIDERSPRUECHE = new Bezeichner("Anzahl verbleibende Dynamikwidersprueche");
    public static final Bezeichner ANZAHL_VP_PRO_PERSONENGRUPPE = new Bezeichner("Anzahl der VP pro Personengruppe");
    public static final Bezeichner ANZAHL_WOHNEINHEITEN = new Bezeichner("Anzahl Wohneinheiten");
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
   public static final Bezeichner AUFTEILUNG_VERSICHERUNGSSTEUER = new Bezeichner("Aufteilung Versicherungsteuer gemaess EG-Richtlinien");
    public static final Bezeichner AUFTRAGSNR_VERMITTLER = new Bezeichner("Auftrags-Nr. des Vermittlers");
    public static final Bezeichner AUSFALLZIFFER_IN_PROZENT = new Bezeichner("Ausfallziffer in %");
    public static final Bezeichner AUSLOESUNG_DER_LEISTUNG = new Bezeichner("Ausloesung der Leistung");
    /** @deprecated bitte Konstante AUSSCHLUSS verwenden (TODO: wird mit v8 entfernt). */
    public static final Bezeichner AUSCHLUSS = new Bezeichner("Auschluss");
    public static final Bezeichner AUSSCHLUSS = new Bezeichner("Ausschluss", "Ausschluss", "Auschluss");
    public static final Bezeichner AUSSCHLUSS2 = new Bezeichner("Ausschluss2", "Ausschluss2");
    public static final Bezeichner AUSSCHLUSS_DER_BEITRAGSANPASSUNG = new Bezeichner("Ausschluss der Beitragsanpassung", "AusschlussBeitragsanpassung", "AusschlussDerBeitragsanpassung");
    public static final Bezeichner AUSSCHLUSSDATUM_VP = new Bezeichner("Ausschlussdatum VP / Personengruppe");

    public static final Bezeichner BASISBEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Basisbeitrag in Währungseinheiten");
    public static final Bezeichner BASISJAHR = new Bezeichner("Basisjahr");
    public static final Bezeichner BASISVERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Basisversicherungssumme in W\u00e4hrungseinheiten", "BasisVersssummeInWE", "BasisVsInWE", "BasiVsInWE");
    public static final Bezeichner BAUARTKLASSE = new Bezeichner("Bauartklasse");
    public static final Bezeichner BAUPREISINDEX_WERTZUSCHLAG = new Bezeichner("Baupreisindex / Wertzuschlag");
    public static final Bezeichner BAUJAHR = new Bezeichner("Baujahr");
    public static final Bezeichner BAUSTEINSCHLUESSEL = new Bezeichner("Bausteinschluessel");
    public static final Bezeichner BAUSTEINBEZEICHNUNG = new Bezeichner("Bausteinbezeichnung");
    public static final Bezeichner BAUSTEIN_ABSCHLAEGE_IN_PROZENT =  new Bezeichner("Baustein-Abschlaege in %");
    /** @deprecated bitte BAUSTEIN_ABSCHLAEGE_IN_PROZENT verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner BAUSTEIN_ABSCHLAEGE_PROZENT = BAUSTEIN_ABSCHLAEGE_IN_PROZENT;
    public static final Bezeichner BAUSTEIN_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Baustein-Abschlaege in W\u00e4hrungseinheiten");
    public static final Bezeichner BAUSTEIN_BEGINN = new Bezeichner("Baustein-Beginn");
    public static final Bezeichner BAUSTEIN_AUSSCHLUSS = new Bezeichner("Baustein-Ausschluss");
    public static final Bezeichner BAUSTEIN_AENDERUNGSDAT = new Bezeichner("Baustein-Aenderungsdatum");
  public static final Bezeichner BAUSTEIN_GESAMTBEITRAG_1_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Baustein-Gesamtbeitrag 1 in W\u00e4hrungseinheiten");
  public static final Bezeichner BAUSTEIN_GESAMTBEITRAG_2_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Baustein-Gesamtbeitrag 2 in W\u00e4hrungseinheiten");
  public static final Bezeichner BAUSTEIN_ZUSCHLAEGE_IN_PROZENT = new Bezeichner("Baustein-Zuschl\u00e4ge in Prozent", "BausteinZuschlaegeInProz");
    public static final Bezeichner BAUSTEIN_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Baustein-Zuschlaege in W\u00e4hrungseinheiten");
    public static final Bezeichner BEDINGUNGEN = new Bezeichner("Bedingungen");
    public static final Bezeichner BEGINN = new Bezeichner("Beginn");
    public static final Bezeichner BEGINN_ABRUFPHASE = new Bezeichner("Beginn Abrufphase");
    public static final Bezeichner BEGINN_DER_ANWARTSCHAFT = new Bezeichner("Beginn der Anwartschaft");
  public static final Bezeichner BEGINN_DER_RENTENZAHLUNG = new Bezeichner("Beginn der Rentenzahlung");
    public static final Bezeichner BEGINN_DER_ZAHLUNG_AB_TAG = new Bezeichner("Beginn der Zahlung ab Tag");
    public static final Bezeichner BEGINN_TAGEGELD1_AB_TAG = new Bezeichner("Beginn Tagegeld 1 ab Tag");
    public static final Bezeichner BEGINN_TAGEGELD2_AB_TAG = new Bezeichner("Beginn Tagegeld 2 ab Tag");
    public static final Bezeichner BEGINN_VERSICHERUNGSSCHUTZ = new Bezeichner("Beginn Versicherungsschutz");
  public static final Bezeichner BEGINNDAT_DER_NAECHSTEN_BEITRAGSSUMME =  new Bezeichner("Beginndatum der naechsten Beitragssumme");
  public static final Bezeichner BEGINNDAT_DER_NAECHSTEN_JAHRESRENTE = new Bezeichner("Beginndatum der naechsten Jahresrente");
  public static final Bezeichner BEGINNDAT_DER_NAECHSTEN_UNFALLSUMME = new Bezeichner("Beginndatum der naechsten Unfallsumme");
    public static final Bezeichner BEGINNDAT_NAECHSTEN_BEITRAGSSUMME = new Bezeichner("Beginndatum der naechsten Beitragssumme");
    public static final Bezeichner BEGINNDAT_NAECHSTEN_ERLEBENSFALL_VS = new Bezeichner("Beginndatum der naechsten Erlebensfall VS");
  public static final Bezeichner BEGINNDAT_NAECHSTEN_JAHRESRENTE = new Bezeichner("Beginndatum der naechsten Jahresrente");
    public static final Bezeichner BEGINNDAT_NAECHSTEN_TODESFALL_VS = new Bezeichner("Beginndatum der naechsten Todesfall VS");
    public static final Bezeichner BEGINNDAT_DER_NAECHSTEN_TODESFALL_VS = BEGINNDAT_NAECHSTEN_TODESFALL_VS; 
  /** @deprecated bitte BEGINNDAT_DER_NAECHSTEN_JAHRESRENTE verwenden (TODO: wird mit v8 entfernt). */
  @Deprecated
  public static final Bezeichner BEGINNDAT_NAECHSTEN_JAHRESRENTESUMME = BEGINNDAT_DER_NAECHSTEN_JAHRESRENTE;
  /** @deprecated bitte BEGINNDAT_DER_NAECHSTEN_UNFALLSUMME verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner BEGINNDAT_NAECHSTEN_UNFALLSUMME = BEGINNDAT_DER_NAECHSTEN_UNFALLSUMME;
    public static final Bezeichner BEIHILFESTATUS = new Bezeichner("Beihilfestatus");
    public static final Bezeichner BEIHILFETRAEGER = new Bezeichner("Beihilfetraeger");
    public static final Bezeichner BEITRAGSANGLEICHUNGSKLAUSEL = new Bezeichner("Beitragsangleichungsklausel");
    public static final Bezeichner BEITRAGSDEPOT = new Bezeichner("Beitragsdepot");
    public static final Bezeichner BEITRAGSFREIE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitragsfreie Beitragssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAGSFREIE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitragsfreie Erlebensfall VS in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAGSFREIE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitragsfreie Todesfall VS in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAGSKLASSE = new Bezeichner("Beitragsklasse");
    public static final Bezeichner BEITRAGSGARANTIE_IN_PROZENT = new Bezeichner("Beitragsgarantie in Prozent");
    public static final Bezeichner BEITRAGSREGULIERUNG = new Bezeichner("Beitragsregulierung");
    public static final Bezeichner BEITRAGSRUECKERSTATTUNGSBETRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitragsrueckerstattungsbetrag in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAGSRUECKGEWAEHR = new Bezeichner("Beitragsrueckgewaehr");
    public static final Bezeichner BEITRAGSUMSTELLUNGSGRUND = new Bezeichner("Beitragsumstellungsgrund");
    public static final Bezeichner BEITRAG = new Bezeichner("Beitrag");
    public static final Bezeichner BEITRAG_AKTUELL_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag aktuell in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_BERGUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Bergungskosten in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_BU_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag BU in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_FESTE_RENTE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Feste Rente in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_GENESUNGSGELD_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Genesungsgeld in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_HEILKOSTEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Heilkosten in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_INVALIDITAET_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Invaliditaet in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_JE_BERECHNUNGSEINHEIT_UND_MENGENSCHLUESSEL = new Bezeichner("Beitrag je Berechnungseinheit und Mengenschluessel");
    public static final Bezeichner BEITRAG_KOSMETISCHE_OPERATION_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Kosmetische Operation in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_KRANKENHAUSTAGEGELD_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Krankenhaustagegeld in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_KURKOSTEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Kurkosten in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_PRO_VP_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag pro VP oder pro Personengruppe in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_PROMILLE = new Bezeichner("Beitrag Promille");
    public static final Bezeichner BEITRAG_SERVICELEISTUNGEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Serviceleistungen in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_TAGEGELD1_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Tagegeld 1 in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_TAGEGELD2_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Tagegeld 2 in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_TOD_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Tod in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAG_UEBERGANGSENTSCHAEDIGUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitrag Uebergangsentschaedigung in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAGSSATZ = new Bezeichner("Beitragssatz");
    public static final Bezeichner BEITRAGSSATZ_FUER_BERECHNUNGSART = new Bezeichner("Beitragssatz für Berechnungsart");
    public static final Bezeichner BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Beitragssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner BEITRAGSSUMMENAENDERUNGS_PROZENTSATZ = new Bezeichner("Beitragssummenaenderungs-Prozentsatz");
    public static final Bezeichner BEITRAGSUMSTELLUNGSDAT = new Bezeichner("Beitragsumstellungsdatum");
    public static final Bezeichner BEITRAGSZAHLUNG_BIS = new Bezeichner("Beitragszahlung bis");
    public static final Bezeichner BERECHNUNGSART = new Bezeichner("Berechnungsart");
    public static final Bezeichner BERECHNUNGSEINHEIT = new Bezeichner("Berechnungseinheit");
    public static final Bezeichner BERECHNUNGSSTICHTAG_ZUM_RUECKKAUFSWERT = new Bezeichner("Berechnungsstichtag zum Rueckkaufswert");
    public static final Bezeichner BERGUNGSKOSTEN = new Bezeichner("Bergungskosten");
    public static final Bezeichner BERGUNGSKOSTEN_BEITRAGSSATZ = new Bezeichner("Bergungskosten Beitragssatz");
    public static final Bezeichner BERUFSGRUPPENEINTEILUNG = new Bezeichner("Berufsgruppeneinteilung im Industrie-Straf-RS");
    public static final Bezeichner BERUFSSCHLUESSEL = new Bezeichner("Berufsschluessel");
    public static final Bezeichner BERUFSSCHLUESSEL_VN = new Bezeichner("Berufsschlüssel VN");
    public static final Bezeichner BERUFSSCHLUESSEL_DER_VP = new Bezeichner("Berufsschluessel der VP");
    public static final Bezeichner BERUF_TEXT = new Bezeichner("Beruf - Text (Berufliche Stellung)");
    public static final Bezeichner BERUF_TEXT_BERUFLICHE_STELLUNG = new Bezeichner("Beruf - Text (Berufliche Stellung)");
    public static final Bezeichner BESONDERER_VERWENDUNGSZWECK = new Bezeichner("besonderer Verwendungszweck");
    public static final Bezeichner BESONDERE_VEREINBARUNGEN_ZUM_FLUGGASTRISIKO = new Bezeichner("Besondere Vereinbarungen zum Fluggastrisiko");
    public static final Bezeichner BESONDERE_VEREINBARUNG_ZUM_FLUGGASTRISIKO = BESONDERE_VEREINBARUNGEN_ZUM_FLUGGASTRISIKO;
    public static final Bezeichner BESONDERE_VEREINBARUNGEN = new Bezeichner("Besondere Vereinbarungen");
    public static final Bezeichner BESONDERHEITEN = new Bezeichner("Besonderheiten");
    public static final Bezeichner BESCHREIBUNG_ZU_LFD_NUMMER_OBJEKT_KOMPLEX = new Bezeichner("Beschreibung zu lfd. Nr. Objekt / Komplex");
    public static final Bezeichner BESTANDSFUEHRENDE_GESCHAEFTSSTELLE = new Bezeichner("Bestandsfuehrende Geschaeftsstelle");
    public static final Bezeichner BESTANDSPFLEGEPROVISION = new Bezeichner("Bestandspflegeprovision");
    public static final Bezeichner BETRAG_IN_WAEHRUNGSEINHEITEN_GEMAESS_ZAHLUNGSART = new Bezeichner("Betrag in Waehrungseinheiten gemaess Zahlungsart");
    public static final Bezeichner BETRAG_VORSORGEZUSCHLAG = new Bezeichner("Betrag Vorsorgezuschlag");
    public static final Bezeichner BETRIEBSART = new Bezeichner("Betriebsart");
    public static final Bezeichner BETRIEBLICHE_ALTERSVERSORGUNG = new Bezeichner("Betriebliche Altersversorgung");
    /** @deprecated bitte BETRIEBLICHE_ALTERSVERSORGUNG verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner BETRIEBLICHE_ALTERSVORSORGUNG = BETRIEBLICHE_ALTERSVERSORGUNG;
    /** @deprecated bitte BETRIEBLICHE_ALTERSVERSORGUNG verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner BETRIEBLICHE_ALTERSVORSORGE = BETRIEBLICHE_ALTERSVERSORGUNG;
    public static final Bezeichner BEWEGUNGSRISIKO = new Bezeichner("Bewegungsrisiko");
    public static final Bezeichner BEZEICHNUNG_DER_LEISTUNG = new Bezeichner("Bezeichnung der Leistung");
    public static final Bezeichner BEZEICHNUNG_PERSONENGRUPPE = new Bezeichner("Bezeichnung Personengruppe");
    public static final Bezeichner BEZUGSBERECHTIGT_IM_ERLEBENSFALL = new Bezeichner("Bezugsberechtigt im Erlebensfall");
    public static final Bezeichner BEZUGSBERECHTIGT_IM_LEISTUNGSFALL = new Bezeichner("Bezugsberechtigt im Leistungsfall");
    public static final Bezeichner BEZUGSBERECHTIGT_IM_TODESFALL = new Bezeichner("Bezugsberechtigt im Todesfall");
    public static final Bezeichner BEZUGSDAT = new Bezeichner("Bezugsdatum");
    public static final Bezeichner BEZUGSRECHTANTEIL_IM_ERLEBENSFALL = new Bezeichner("Bezugsrechtanteil im Erlebensfall");
    public static final Bezeichner BEZUGSRECHTANTEIL_IM_LEISTUNGSFALL = new Bezeichner("Bezugsrechtanteil im Leistungsfall");
    public static final Bezeichner BEZUGSRECHTANTEIL_IM_TODESFALL = new Bezeichner("Bezugsrechtanteil im Todesfall");
    public static final Bezeichner BEZUG_ZUR_TEILSPARTE = new Bezeichner("Bezug zur Teilsparte");
    /** @deprecated bitte BEZUG_ZUR_TEILSPARTE verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner BEZUG_ZUR_TEILSPARTE1 = BEZUG_ZUR_TEILSPARTE;
    /** @deprecated bitte BEZUG_ZUR_TEILSPARTE verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner BEZUG_ZUR_TEILSPARTE2 = BEZUG_ZUR_TEILSPARTE;
    public static final Bezeichner BIC1 = new Bezeichner("BIC 1");
    public static final Bezeichner BIC2 = new Bezeichner("BIC 2");
    public static final Bezeichner BILANZMONAT_ARBEITGEBER = new Bezeichner("Bilanzmonat Arbeitgeber");
    public static final Bezeichner BLZ1 = new Bezeichner("Bankleitzahl 1");
    public static final Bezeichner BLZ2 = new Bezeichner("Bankleitzahl 2");
    public static final Bezeichner BRE_JAHR = new Bezeichner("BRE-Jahr");
    public static final Bezeichner BRE_STAFFEL = new Bezeichner("BRE-Staffel");
    public static final Bezeichner BUENDELUNGSKENNZEICHEN = new Bezeichner("Buendelungskennzeichen");
    public static final Bezeichner BUCHUNGSKENNZEICHEN = new Bezeichner("Buchungskennzeichen");
    public static final Bezeichner BUCHUNGSKENNZEICHEN1 = new Bezeichner("Buchungskennzeichen1", "Buchungskennzeichen");
    public static final Bezeichner BUCHUNGSKENNZEICHEN2 = new Bezeichner("Buchungskennzeichen2", "Buchungskennzeichen1");
    public static final Bezeichner BUZ_LEISTUNG_VON = new Bezeichner("Leistung von");
    public static final Bezeichner BUZ_LEISTUNG_BIS = new Bezeichner("Leistung bis");
    public static final Bezeichner BUZ_PROZENT_SATZ = new Bezeichner("Prozent-Satz");
    public static final Bezeichner VERWENDUNGSART = new Bezeichner("Verwendungsart");
    /** @deprecated bitte VERWENDUNGSART verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner BUZ_VERWENDUNGSART = VERWENDUNGSART;

    public static final Bezeichner DAT_BEZUGSFERTIGKEIT = new Bezeichner("Datum der Bezugsfertigkeit");
    public static final Bezeichner DAT_LETZTEN_BEITRAGSANGLEICHUNG = new Bezeichner("Datum der letzten Beitragsangleichung");
    public static final Bezeichner DAT_LETZTER_GLASPREISANGLEICHUNG = new Bezeichner("Datum der letzten Glaspreisangleichung");
    public static final Bezeichner DAT_LETZTEN_POSITIVEN_DYNAMIK = new Bezeichner("Datum der letzten positiven Dynamik");
    public static final Bezeichner DATUM_DER_LETZTEN_SUMMENANPASSUNG = new Bezeichner("Datum der letzten Summenanpassung");
    public static final Bezeichner DATUM_SEPA = new Bezeichner("SEPA-Ablaufdatum oder erloschen am");
    public static final Bezeichner DAT_UNVERFALLBARKEIT = new Bezeichner("Datum Unverfallbarkeit");
    public static final Bezeichner DAUER_DES_RENTENBEZUGS_IN_MONATEN = new Bezeichner("Dauer des Rentenbezugs in Monaten");
    public static final Bezeichner DAUERSCHAEDEN_KOERPERLICHE_BEEINTRAECHTIGUNGEN = new Bezeichner("Dauerschaeden / koerperliche Beeintraechtigungen");
    public static final Bezeichner DAUERZULAGEANTRAG = new Bezeichner("Dauerzulagenantrag");
    public static final Bezeichner DECKUNGSSUMME_1_IN_TAUSEND_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 1 in Tausend W\u00e4hrungseinheiten");
    public static final Bezeichner DECKUNGSSUMME_2_IN_TAUSEND_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 2 in Tausend W\u00e4hrungseinheiten");
    public static final Bezeichner DECKUNGSSUMME_3_IN_TAUSEND_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 3 in Tausend W\u00e4hrungseinheiten");
    public static final Bezeichner DECKUNGSSUMME_4_IN_TAUSEND_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 4 in Tausend W\u00e4hrungseinheiten");
    public static final Bezeichner DECKUNGSSUMME_1_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 1 in W\u00e4hrungseinheiten");
    public static final Bezeichner DECKUNGSSUMME_2_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 2 in W\u00e4hrungseinheiten");
    public static final Bezeichner DECKUNGSSUMME_3_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 3 in W\u00e4hrungseinheiten");
    public static final Bezeichner DECKUNGSSUMME_4_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme 4 in W\u00e4hrungseinheiten");
    public static final Bezeichner DECKUNGSSUMME_IN_TSD_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme in Tausend W\u00e4hrungseinheiten");
    public static final Bezeichner DECKUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Deckungssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner DECKUNGSRUECKSTELLUNG_VORSORGEZUSCHLAG = new Bezeichner("Deckungsrueckstellung Vorsorgezuschlag");
    public static final Bezeichner DECKUNGSUMFANG = new Bezeichner("Deckungsumfang");
    public static final Bezeichner DECKUNGS_VERSICHERUNGSSUMME = new Bezeichner("Deckungs-/Versicherungssumme");
    public static final Bezeichner DENKMALSCHUTZ = new Bezeichner("Denkmalschutz");
    public static final Bezeichner DIENSTEINTRITT = new Bezeichner("Diensteintritt");
    public static final Bezeichner DIENSTEINTRITTSDAT = new Bezeichner("Diensteintrittsdatum");
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
    public static final Bezeichner EINSCHLUSS = new Bezeichner("Einschluss");
    public static final Bezeichner EINSCHLUSSDATUM = new Bezeichner("Einschlussdatum");
    public static final Bezeichner EINSCHLUSS_PROZENT_SATZ = new Bezeichner("Einschluss %-Satz");
    public static final Bezeichner EINSCHLUSS_VANDALISMUS = new Bezeichner("Einschluss Vandalismus");
    public static final Bezeichner EINSCHLUSSDAT_VP_PERSONENGRUPPE = new Bezeichner("Einschlussdatum VP / Personengruppe");
    public static final Bezeichner EINZAHLUNG_AUSSCHUETTUNG = new Bezeichner("Einzahlung / Ausschuettung");
    public static final Bezeichner ENDE_DER_ANWARTSCHAFT = new Bezeichner("Ende der Anwartschaft");
    public static final Bezeichner ENDE_DER_RENTENZAHLUNG = new Bezeichner("Ende der Rentenzahlung");
    public static final Bezeichner ENDEDATUM_DES_VERSICHERUNGSSCHUTZES_BEI_ROTEN_KENNZEICHEN = new Bezeichner("Endedatum des Versicherungsschutzes bei roten Kennzeichen");
    public static final Bezeichner ENTSCHAEDIGUNGSGRENZE_PRO_LEISTUNGSFALL = new Bezeichner("Entschädigungsgrenze pro Leistungsfall");
    public static final Bezeichner ENTSCHAEDIGUNGSGRENZE_PRO_VERSICHERUNGSJAHR = new Bezeichner("Entschädigungsgrenze pro Versicherungsjahr");
    public static final Bezeichner ERHOEHUNGSART_DYNAMIK = new Bezeichner("Erhoehungsart Dynamik");
    public static final Bezeichner ERHOEHUNGSBASIS_DYNAMIK = new Bezeichner("Erhoehungsbasis Dynamik");
    public static final Bezeichner ERHOEHUNGSSATZ_LETZTER_GLASPREISANGLEICHUNG = new Bezeichner("%-Satz Erhöhung der letzten Glaspreisangleichung");
    public static final Bezeichner ERSTATTUNG_OP = new Bezeichner("Erstattung OP");
    public static final Bezeichner ERSTE_ZULASSUNG_AUF_DEN_VN = new Bezeichner("Erste Zulassung auf den VN", "ErsteZulassungAufVN", "ErsteZulassungAufDenVn");
    public static final Bezeichner ERSTZULASSUNG = new Bezeichner("Erstzulassung");
    public static final Bezeichner ERWEITERTE_NEUWERTVERSICHERUNG = new Bezeichner("erweiterte Neuwertversicherung");
    public static final Bezeichner ERWEITERUNG_DES_GELTUNGSBEREICHES = new Bezeichner("Erweiterung des Geltungsbereiches");
    public static final Bezeichner ERWEITERUNGSSATZ_VORHANDEN = new Bezeichner("Erweiterungssatz vorhanden");
    public static final Bezeichner EVB_NUMMER = new Bezeichner("eVB-Nummer");
    public static final Bezeichner ENDALTER = new Bezeichner("Endalter");
    public static final Bezeichner EINTRITTSALTER = new Bezeichner("Eintrittsalter");
    public static final Bezeichner EINTRITTSALTER_DER_VP = new Bezeichner("Eintrittsalter der VP");
    public static final Bezeichner ELEMENTARSCHAEDEN = new Bezeichner("Elementarschaeden");
    public static final Bezeichner ENDEDATUM_BEI_ROTEN_KENNZEICHEN = new Bezeichner("Endedatum des Versicherungsschutzes bei roten Kennzeichen");
    public static final Bezeichner ERHOEHUNGSSATZ_8_III_AHB = new Bezeichner("Erhoehungssatz \u00a78, III AHB");
    public static final Bezeichner ERKRANKUNGEN = new Bezeichner("Erkrankungen");
    public static final Bezeichner ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT = new Bezeichner("Erlebensfall VS in W\u00e4hrungseinheiten");
    public static final Bezeichner ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = new Bezeichner("Erlebensfall VS in W\u00e4hrungseinheiten zum Ablauf");
    public static final Bezeichner ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = new Bezeichner("Erlebensfall VS II in W\u00e4hrungseinheiten zum Ablauf");
    public static final Bezeichner ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Erlebensfall VS II in W\u00e4hrungseinheiten");
    public static final Bezeichner ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Erlebensfall VS in W\u00e4hrungseinheiten");
    public static final Bezeichner ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = new Bezeichner("Erlebensfall VS in W\u00e4hrungseinheiten zum Beginn der Abrufphase");
    public static final Bezeichner ERLEBENSFALL_VS_II_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = new Bezeichner("Erlebensfall VS II in W\u00e4hrungseinheiten zum Beginn der Abrufphase");
    public static final Bezeichner ERLEBENSFALL_VS_AENDERUNGS_PROZENTSATZ = new Bezeichner("Erlebensfall VS-Aenderungs-Prozentsatz");
    public static final Bezeichner ERSTELLUNGSDAT_ZEITRAUM_BIS = new Bezeichner("Erstellungs-Datum-Zeitraum bis");
    public static final Bezeichner ERSTELLUNGSDAT_ZEITRAUM_VOM = new Bezeichner("Erstellungs-Datum-Zeitraum vom");
    public static final Bezeichner ERSTELLUNGS_DAT_ZEITRAUM_VOM_ZEITRAUM_BIS = new Bezeichner("Erstellungs-Datum, Zeitraum von, Zeitraum bis", "ErstellungsDatumZeitraumVomZeitraumBis", "ErstellungsDatZeitraumVomZeitraumBis");
    public static final Bezeichner ERWEITERTER_BERVERSV_SCHLUESSEL = new Bezeichner("Erweiterter BerVersV-Schluessel");

    public static final Bezeichner FABRIK_NR = new Bezeichner("Fabrik-Nr.");
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
    /**
     * Fuer Satzart 220, Sparte 20, Folge-Nr. 1 (Personendaten)
     */
    public static final Bezeichner FOLGE_NR_ZUR_LAUFENDEN_PERSONEN_NR_UNTER_NR_LAUFENDE_NR_TARIF = new Bezeichner("Folge-Nr. zur laufenden Personen-Nummer/Unternummer/laufende Nummer Tarif");
    /**
     * Fuer Satzart 220, Sparte 20, Folge-Nr. 2 und 3 (Allgemeine Tarifdaten und Spezielle Tarifdaten)
     */
    public static final Bezeichner FOLGE_NR_ZUR_LAUFENDEN_PERSONEN_NR_UNTER_NR_BZW_LAUFENDEN_NR_TARIF = new Bezeichner("Folgenummer zur laufenden Personen-Nr. / Unternummer bzw. laufenden Nummer Tarif");
    public static final Bezeichner FONDSKENNUNG = new Bezeichner("Fondskennung");
    public static final Bezeichner FONDSNAME = new Bezeichner("Fondsname");
    public static final Bezeichner FORM = new Bezeichner("Form");
    public static final Bezeichner FORMART = new Bezeichner("Formart");
    public static final Bezeichner FREMDER_GRUND_UND_BODEN = new Bezeichner("Fremder Grund und Boden");
    public static final Bezeichner FREMDNUTZUNG = new Bezeichner("Fremdnutzung");
    public static final Bezeichner FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_KH = new Bezeichner("Frei vereinbarte Selbstbeteiligung in W\u00e4hrungseinheiten für KH");
    public static final Bezeichner FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO = new Bezeichner("Frei vereinbarte Selbstbeteiligung in W\u00e4hrungseinheiten fuer Teilkasko im Rahmen der Vollkasko");
    public static final Bezeichner FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_VOLLKASKO = new Bezeichner("Frei vereinbarte Selbstbeteiligung in W\u00e4hrungseinheiten fuer Vollkasko");

    public static final Bezeichner GAP_DECKUNG = new Bezeichner("GAP-Deckung");
    public static final Bezeichner GARAGE = new Bezeichner("Garage");
    public static final Bezeichner GARANTIERTE_FONDSJAHRESRENTE_ZUM_ABLAUF_INKL_ABRUFPHASE = new Bezeichner("Garantierte Fondsjahresrente zum Ablauf (inkl. Abrufphase)");
    public static final Bezeichner GARANTIERTE_FONDSJAHRESRENTE_ZUM_BEGINN_DER_ABRUFPHASE = new Bezeichner("Garantierte Fondsjahresrente zum Beginn der Abrufphase");
    public static final Bezeichner GARANTIERTER_STEIGERUNGSSATZ_BEI_BU = new Bezeichner("Garantierter Steigerungssatz bei BU");
    public static final Bezeichner GEBAEUDETYP = new Bezeichner("Gebaeudetyp");
    public static final Bezeichner GEBURTSDAT = new Bezeichner("Geburtsdatum");
    public static final Bezeichner GEBURTSDATUM_DES_TIERES = new Bezeichner("Geburtsdatum des Tieres");
    public static final Bezeichner GEBURTSDAT_VP = new Bezeichner("Geburtsdatum der VP");
    public static final Bezeichner GEBURTSDAT_VP2 = new Bezeichner("Geburtsdatum der 2. VP");
    public static final Bezeichner GEBURTSDATUM_DER_BEZUGSBERECHTIGTEN_PERSON = new Bezeichner("Geburtsdatum der bezugsberechtigten Person");
    public static final Bezeichner GESCHLECHT_VP = new Bezeichner("Geschlecht der VP");
    public static final Bezeichner GEBURTSLAND = new Bezeichner("Geburtsland");
    public static final Bezeichner GEBURTSNAME = new Bezeichner("Geburtsname");
    public static final Bezeichner GEBURTSORT = new Bezeichner("Geburtsort");
    public static final Bezeichner GEFAHRENERHOEHUNG = new Bezeichner("Gefahrenerhoehung");
    public static final Bezeichner GEFAHRENGRUPPE = new Bezeichner("Gefahrengruppe");
    public static final Bezeichner GEFAHRENSCHLUESSEL = new Bezeichner("Gefahrenschluessel");
    public static final Bezeichner GEFAHRGUT = new Bezeichner("Gefahrgut");
    public static final Bezeichner GELTUNGSBEREICH = new Bezeichner("Geltungsbereich");
    public static final Bezeichner GELTUNGSBEREICHEINSCHRAENKUNG = new Bezeichner("Geltungsbereicheinschraenkung");
    public static final Bezeichner GENESUNGSGELD = new Bezeichner("Genesungsgeld");
    public static final Bezeichner GENESUNGSGELD_BEITRAGSSATZ = new Bezeichner("Genesungsgeld Beitragssatz");
    public static final Bezeichner GEPAECK_BEGINN = new Bezeichner("Gepäck-Beginn");
    public static final Bezeichner GEPAECK_AUSSCHLUSS = new Bezeichner("Gepäck-Ausschluss");
    public static final Bezeichner GEPAECK_AENDERUNGSDAT = new Bezeichner("Gepäck-Änderungsdatum");
    public static final Bezeichner GEPAECKVERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gepaeckversicherungssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner GEPAECKVERSICHERUNGSSUMME_IN_TAUSEND_WAEHRUNGSEINHEITEN = new Bezeichner("Gepaeckversicherungssumme in Tausend Währungseinheiten");
    public static final Bezeichner GEPAECK_BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gepaeck-Beitrag in W\u00e4hrungseinheiten");
    public static final Bezeichner GESAMTBEITRAG = new Bezeichner("Gesamtbeitrag");
    public static final Bezeichner GESAMTBEITRAG_BRUTTO = new Bezeichner("Gesamtbeitrag-Brutto(Inkasso)");
    public static final Bezeichner GESAMTBEITRAG_BRUTTO_INKASSO = GESAMTBEITRAG_BRUTTO;
    public static final Bezeichner GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtbeitrag (Brutto) in W\u00e4hrungseinheiten");
    public static final Bezeichner GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtbeitrag (Netto) in W\u00e4hrungseinheiten");
    public static final Bezeichner GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN2 = new Bezeichner("Gesamtbeitrag in W\u00e4hrungseinheiten");
    public static final Bezeichner GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtbeitrag (Netto) in W\u00e4hrungseinheiten");
    public static final Bezeichner GESAMTBEITRAG_TARIF_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtbeitrag Tarif in Währungseinheiten");
    public static final Bezeichner GESAMTBEITRAG_TARIF_IN_WAEHRUNGSEINHEITEN_GEMAESS_ZAHLWEISE = new Bezeichner("Gesamtbeitrag Tarif in Währungseinheiten gemäß Zahlweise");
    public static final Bezeichner GESAMTBEITRAG_VP_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtbeitrag VP in W\u00e4hrungseinheiten");
    public static final Bezeichner GESAMTMASSE = new Bezeichner("Gesamtmasse");
    public static final Bezeichner GESAMTPROVISIONSBETRAG = new Bezeichner("Gesamtprovisions-Betrag");
    public static final Bezeichner GESAMT_PROVISIONSBETRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamt-Provisionsbetrag in W\u00e4hrungseinheiten");
    /** @deprecated bitte GESAMT_PROVISIONSBETRAG_IN_WAEHRUNGSEINHEITEN verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner GESAMTPROVISIONSBETRAG_IN_WAEHRUNGSEINHEITEN = GESAMT_PROVISIONSBETRAG_IN_WAEHRUNGSEINHEITEN;
    public static final Bezeichner GESAMTVERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Gesamtversicherungssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner GESCHAEFTSSTELLE_VERMITTLER = new Bezeichner("Geschaeftsstelle / Vermittler");
    public static final Bezeichner GESCHLECHT = new Bezeichner("Geschlecht");
    public static final Bezeichner GESCHLECHTSKENNZEICHEN = new Bezeichner("Geschlechtskennzeichen");
    public static final Bezeichner GEBURTSDATUM_DER_MITZUVERSICHERNDEN_PERSON = new Bezeichner("Geburtsdatum der mitzuversichernden Person");
    public static final Bezeichner GESCHLECHT_DER_MITZUVERSICHERNDEN_PERSON = new Bezeichner("Geschlecht der mitzuversichernden Person");
    public static final Bezeichner GESTUNDET_AUSGESETZT_BIS = new Bezeichner("Gestundet ausgesetzt bis");
    public static final Bezeichner GEWINNVERWENDUNGSART = new Bezeichner("Gewinnverwendungsart");
    public static final Bezeichner GLASPREISANGLEICHUNG = new Bezeichner("Glaspreisangleichung");
    public static final Bezeichner GROSSRISIKEN = new Bezeichner("Aufsichtsfreier Versicherungsnehmer (Grossrisiken)");
    public static final Bezeichner GRUPPENART = new Bezeichner("Gruppenart");
    public static final Bezeichner GUELTIG_AB = new Bezeichner("gueltig ab");
    public static final Bezeichner GUELTIGE_AKB = new Bezeichner("Gueltige AKB");
    public static final Bezeichner GUELTIGE_AVB = new Bezeichner("Gueltige AVB");
    public static final Bezeichner GUELTIGKEITSDAUER_IN_TAGEN_BEI_KURZZEITKENNZEICHEN = new Bezeichner("Gueltigkeitsdauer in Tagen bei Kurzzeitkennzeichen");
    public static final Bezeichner GUTHABEN_DIVID_ANSAMMLUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Guthaben Divid. Ansammlung in W\u00e4hrungseinheiten");
    public static final Bezeichner GUTHABEN_DIVID_ANSAMMLUNGEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Guthaben Divid. Ansammlungen in W\u00e4hrungseinheiten");

    public static final Bezeichner HAFTUNG_AB = new Bezeichner("Haftung ab");
    public static final Bezeichner HAFTUNG_AB2 = new Bezeichner("Haftung ab 2");
    public static final Bezeichner HAFTUNG_BIS = new Bezeichner("Haftung bis");
    public static final Bezeichner HAFTUNG_BIS2 = new Bezeichner("Haftung bis 2");
    public static final Bezeichner HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Haftungswertungssumme in W\u00e4hrungseinheiten", "HaftungswertungssummeInWE", "HaftungswertungssummeInWE1");
    public static final Bezeichner HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN1 = new Bezeichner("Haftungswertungssumme in W\u00e4hrungseinheiten 1", "HaftungswertungssummeInWE1", "HaftungswertungssummeInWE");
    public static final Bezeichner HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = new Bezeichner("Haftungswertungssumme in W\u00e4hrungseinheiten 2");
    public static final Bezeichner HAUPTFAELLIGKEIT = new Bezeichner("Hauptfaelligkeit");
    public static final Bezeichner HEILKOSTEN = new Bezeichner("Heilkosten");
    public static final Bezeichner HEILKOSTEN_BEITRAGSSATZ = new Bezeichner("Heilkosten Beitragssatz");
    public static final Bezeichner HEILKOSTEN_RISIKO1 = new Bezeichner("Heilkosten (Risiko 1)");
    public static final Bezeichner HEILKOSTEN_RISIKO2 = new Bezeichner("Heilkosten (Risiko 2)");
    public static final Bezeichner HERSTELLER = new Bezeichner("Hersteller");
    public static final Bezeichner HERSTELLERNAME = new Bezeichner("Herstellername");
    public static final Bezeichner HERSTELLER_SCHLUESSEL_NR = new Bezeichner("Hersteller-Schluessel-Nr.");

    public static final Bezeichner IBAN1 = new Bezeichner("IBAN 1");
    public static final Bezeichner IBAN2 = new Bezeichner("IBAN 2");
    public static final Bezeichner INKASSOART = new Bezeichner("Inkassoart");
    public static final Bezeichner INTERNES_ORDNUNGSMERKMAL_DES_VM = new Bezeichner("Internes Ordnungsmerkmal des VM");
    public static final Bezeichner INTRO = new Bezeichner("Intro");
    public static final Bezeichner INVALIDITAET = new Bezeichner("Invaliditaet");
    public static final Bezeichner INVALIDITAET_BEITRAGSSATZ = new Bezeichner("Invaliditaet Beitragssatz");
    public static final Bezeichner INVALIDITAET_RISIKO1 = new Bezeichner("Invalidität (Risiko 1)");
    public static final Bezeichner INVALIDITAET_RISIKO2 = new Bezeichner("Invalidität (Risiko 2)");
    public static final Bezeichner ISIN_NUMMER = new Bezeichner("ISIN-Nummer");

    public static final Bezeichner JAEHRLICHE_FAHRLEISTUNG = new Bezeichner("Jaehrliche Fahrleistung");
    public static final Bezeichner JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Jahresrente in W\u00e4hrungseinheiten");
    /** @deprecated bitte JAHRESRENTE_IN_WAEHRUNGSEINHEITEN verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner JAHRESRENTE_IN_WAEHRUNGSEINHEITEN2 = JAHRESRENTE_IN_WAEHRUNGSEINHEITEN;

    public static final Bezeichner JAHRESRENTE_INKL_GEWINNBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Jahresrente inkl. Gewinnbeteiligung in WE");
    public static final Bezeichner JAHRESRENTENAENDERUNGS_PROZENTSATZ = new Bezeichner("Jahresrenten\u00e4nderungs-Prozentsatz");

    public static final Bezeichner KAPITALERTRAGSSTEUER_BEI_ABLAUF = new Bezeichner("Kapitalertragssteuer bei Ablauf");
    public static final Bezeichner KAPITALERTRAGSSTEUER_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG = new Bezeichner("Kapitalertragssteuer bei Rueckkauf zum Berechnungsstichtag");
    public static final Bezeichner KAPITALERTRAGSTEUERPFLICHT = new Bezeichner("Kapitalertragsteuerpflicht");
    public static final Bezeichner KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Kapitalzahlungssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = KAPITALZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN;
    public static final Bezeichner KAPITALZAHLUNGSSUMME_INKL_GEWINNBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Kapitalzahlungssumme inkl. Gewinnbeteiligung in WE");
    public static final Bezeichner KARENZZEIT = new Bezeichner("Karenzzeit");
    public static final Bezeichner KASKO_BEGINNJAHR = new Bezeichner("Kasko-Beginnjahr");
    public static final Bezeichner KAUFPREIS = new Bezeichner("Kaufpreis");
    public static final Bezeichner KENNUNG_ERHOEHUNGSSATZ = new Bezeichner("Kennung Erhoehungssatz");
    public static final Bezeichner KENNZEICHEN_ANPASSUNGSVERZICHT = new Bezeichner("Kennzeichen Anpassungsverzicht");
    public static final Bezeichner KENNZEICHEN_BERUFSUNFALLRISIKO_EINGESCHLOSSEN = new Bezeichner("Kennzeichen Berufsunfallrisiko eingeschlossen");
    public static final Bezeichner KENNZEICHEN_FUER_JAHRES_MAXIMIERUNG = new Bezeichner("Kennzeichen fuer Jahres-Maximierung");
    public static final Bezeichner KENNZEICHEN_KLINIK_CARD = new Bezeichner("Kennzeichen Klinik Card");
    public static final Bezeichner KENNZEICHEN_WARTEZEITERLASS = new Bezeichner("Kennzeichen Wartezeiterlass");
    public static final Bezeichner KENNZEICHEN_OB_DAS_KRANKENHAUSTAGEGELD_MIT_ODER_OHNE_EINSCHLUSS_GENESUNGSGELD_IST_RISIKO1 = new Bezeichner("Kennzeichen, ob das Krankenhaustagegeld mit oder ohne Einschluss Genesungsgeld ist (Risiko 1).");
    public static final Bezeichner KENNZEICHEN_OB_DAS_KRANKENHAUSTAGEGELD_MIT_ODER_OHNE_EINSCHLUSS_GENESUNGSGELD_IST_RISIKO2 = new Bezeichner("Kennzeichen, ob das Krankenhaustagegeld mit oder ohne Einschluss Genesungsgeld ist (Risiko 2).");
    public static final Bezeichner KENNUNG_FUER_ABS_RABATT = new Bezeichner("Kennung fuer ABS-Rabatt");
    public static final Bezeichner KENNUNG_GLIEDERTAXE = new Bezeichner("Kennung Gliedertaxe");
    public static final Bezeichner KENNUNG_PROGRESSIVE_INVALIDITAET = new Bezeichner("Kennung progressive Invaliditaet");
    public static final Bezeichner KENNUNG_UEBERGANGSENTSCHAEDIGUNG = new Bezeichner("Kennung Uebergangsentschaedigung");
    public static final Bezeichner KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION = new Bezeichner("Kennzeichen fuer abweichende Abschlussprovision");
    public static final Bezeichner KENNZEICHEN_ABWEICHENDE_BESTANDSPFLEGEPROVISION = new Bezeichner("Kennzeichen fuer abweichende Bestandspflegeprovision");
    public static final Bezeichner KENNZEICHEN_ABWEICHENDE_FOLGEPROVISION = new Bezeichner("Kennzeichen fuer abweichende Folgeprovision");
    public static final Bezeichner KENNZEICHEN_ABWEICHENDE_PROVISION = new Bezeichner("Kennzeichen fuer abweichende Provision");
    public static final Bezeichner KENNZEICHEN_ABWEICHENDE_VU_NR = new Bezeichner("Kennzeichen zur Erlaeuterung der abweichenden VU-Nr.");
    public static final Bezeichner KENNZEICHEN_FUER_ABWEICHENDE_ABSCHLUSSPROVISION = new Bezeichner("Kennzeichen fuer abweichende Abschlussprovision");
    public static final Bezeichner KENNZEICHEN_FUER_ABWEICHENDE_FOLGEPROVISION = new Bezeichner("Kennzeichen fuer abweichende Folgeprovision");
    public static final Bezeichner KENNZEICHEN_FUER_ABWEICHENDE_PROVISION = new Bezeichner("Kennzeichen fuer abweichende Provision");
    public static final Bezeichner KENNZEICHEN_VERS_STEUER_FREI = new Bezeichner("Kennzeichen Vers.-Steuer frei");
    public static final Bezeichner KENNZEICHEN_VERTRAGSENTSTEHUNG = new Bezeichner("Kennzeichen Vertragsentstehung");
    public static final Bezeichner KFT_ABSCHLAEGE_IN_PROZENT = new Bezeichner("KFT-Abschlaege in %");
    public static final Bezeichner KFT_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFT-Abschlaege in W\u00e4hrungseinheiten");
    public static final Bezeichner KFT_AENDERUNGSDAT = new Bezeichner("KFT-aenderungsdatum");
    public static final Bezeichner KFT_AUSSCHLUSS = new Bezeichner("KFT-Ausschluss");
    public static final Bezeichner KFT_BEGINN = new Bezeichner("KFT-Beginn");
    public static final Bezeichner KFT_BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFT-Beitrag in W\u00e4hrungseinheiten");
    public static final Bezeichner KFT_DECKUNGSART = new Bezeichner("KFT-Deckungsart");
    public static final Bezeichner KFT_TARIFGRUPPE = new Bezeichner("KFT-Tarifgruppe");
    public static final Bezeichner KFT_ZUSCHLAEGE_IN_PROZENT = new Bezeichner("KFT-Zuschlaege in %");
    public static final Bezeichner KFT_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFT-Zuschlaege in W\u00e4hrungseinheiten");
    public static final Bezeichner KFV_ABSCHLAEGE_IN_PROZENT = new Bezeichner("KFV-Abschlaege in %");
    public static final Bezeichner KFV_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFV-Abschlaege in W\u00e4hrungseinheiten");
    public static final Bezeichner KFV_AENDERUNGSDAT = new Bezeichner("KFV-aenderungsdatum");
    public static final Bezeichner KFV_AUSSCHLUSS = new Bezeichner("KFV-Ausschluss");
    public static final Bezeichner KFV_BEGINN = new Bezeichner("KFV-Beginn");
    public static final Bezeichner KFV_BEITRAGSSATZ = new Bezeichner("KFV-Beitragssatz");
    public static final Bezeichner KFV_BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFV-Beitrag in W\u00e4hrungseinheiten");
    public static final Bezeichner KFV_DECKUNGSART = new Bezeichner("KFV-Deckungsart");
    public static final Bezeichner KFV_RGJ = new Bezeichner("KFV-RGJ");
    public static final Bezeichner KFV_SCHAEDEN_AUS_RUECKSTUFUNG = new Bezeichner("KFV-Schaeden aus Rueckstufung");
    public static final Bezeichner KFV_SFS_KLASSE = new Bezeichner("KFV-SF/S-Klasse");
    public static final Bezeichner KFV_TARIFGRUPPE = new Bezeichner("KFV-Tarifgruppe");
    public static final Bezeichner KFV_ZUSCHLAEGE_IN_PROZENT = new Bezeichner("KFV-Zuschlaege in %");
    public static final Bezeichner KFV_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KFV-Zuschlaege in W\u00e4hrungseinheiten");
    public static final Bezeichner KH_ABSCHLAEGE_IN_PROZENT = new Bezeichner("KH-Abschlaege in %");
    public static final Bezeichner KH_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KH-Abschlaege in W\u00e4hrungseinheiten");
    public static final Bezeichner KH_AENDERUNGSDAT = new Bezeichner("KH-aenderungsdatum");
    public static final Bezeichner KH_AUSSCHLUSS = new Bezeichner("KH-Ausschluss");
    public static final Bezeichner KH_BEGINN = new Bezeichner("KH-Beginn");
    public static final Bezeichner KH_BEITRAGSSAETZE = new Bezeichner("KH-Beitragssaetze");
    public static final Bezeichner KH_BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KH-Beitrag in W\u00e4hrungseinheiten");
    public static final Bezeichner KH_DECKUNGSART = new Bezeichner("KH Deckungsart");
    public static final Bezeichner KH_DECKUNGSSUMMEN = new Bezeichner("KH-Deckungssummen");
    public static final Bezeichner KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KH-Deckungssummen in W\u00e4hrungseinheiten");
    public static final Bezeichner KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL1 = new Bezeichner("KH-Deckungssummen in W\u00e4hrungseinheiten Teil 1");
    public static final Bezeichner KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL2 = new Bezeichner("KH-Deckungssummen in W\u00e4hrungseinheiten Teil 2");
    public static final Bezeichner KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL3 = new Bezeichner("KH-Deckungssummen in W\u00e4hrungseinheiten Teil 3");
    public static final Bezeichner KH_DECKUNGSSUMMEN_PERSONENSCHAEDEN = KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL1;
    public static final Bezeichner KH_DECKUNGSSUMMEN_SACHSCHAEDEN = KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL2;
    public static final Bezeichner KH_DECKUNGSSUMMEN_VERMOEGENSCHAEDEN = KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL3;
    public static final Bezeichner KH_RGJ = new Bezeichner("KH-RGJ");
    public static final Bezeichner KH_SCHAEDEN_AUS_RUECKSTUFUNG = new Bezeichner("KH-Schaeden aus Rueckstufung");
    public static final Bezeichner KH_SF_S_KLASSE = new Bezeichner("KH-SF/S-Klasse");
    public static final Bezeichner KH_TARIFGRUPPE = new Bezeichner("KH-Tarifgruppe");
    public static final Bezeichner KH_ZUSCHLAEGE_IN_PROZENT = new Bezeichner("KH-Zuschlaege in %");
    public static final Bezeichner KH_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KH-Zuschlaege in W\u00e4hrungseinheiten");
    public static final Bezeichner KLARTEXT_SICHERUNGSEINRICHTUNG = new Bezeichner("Klartext Sicherungseinrichtung");
    public static final Bezeichner KOLLEKTIV_NR = new Bezeichner("Kollektiv-Nr.");
    public static final Bezeichner KOMBINATIONSSCHLUESSEL_EC = new Bezeichner("Kombinationsschlüssel (EC)");
    public static final Bezeichner KOMBINATIONSSCHLUESSEL_EC2 = new Bezeichner("Kombinationsschlüssel (EC) 2");
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
    public static final Bezeichner KRANKENHAUSTAGEGELD_RISIKO1 = new Bezeichner("Krankenhaustagegeld (Risiko 1)");
    public static final Bezeichner KRANKENHAUSTAGEGELD_RISIKO2 = new Bezeichner("Krankenhaustagegeld (Risiko 2)");
    public static final Bezeichner KREISGEMEINDESCHLUESSEL = new Bezeichner("Kreisgemeindeschl\u00fcssel");
    public static final Bezeichner KREISGEMEINDESCHLUESSEL_ZUSATZINFORMATION = new Bezeichner("Kreisgemeindeschl\u00fcssel Zusatzinformation");
    public static final Bezeichner KUENDIGUNGSKLAUSEL = new Bezeichner("Kuendigungsklausel");
    public static final Bezeichner KUENDIGUNGSKLAUSEL_VP = new Bezeichner("Kuendigungsklausel VP / Personengruppe gestrichen");
    public static final Bezeichner KUNDENGRUPPE = new Bezeichner("Kundengruppe");
    public static final Bezeichner KUNDENNR_VERMITTLER = new Bezeichner("Personen-/Kundennummer des Vermittlers");
    public static final Bezeichner KUNDENNR_VERSICHERER = new Bezeichner("Personen-/Kundennummer des Versicherers");
    public static final Bezeichner KURKOSTEN = new Bezeichner("Kurkosten");
    public static final Bezeichner KURKOSTEN_BEITRAGSSATZ = new Bezeichner("Kurkosten Beitragssatz");
    public static final Bezeichner KU_ABSCHLAEGE_IN_PROZENT = new Bezeichner("KU-Abschläge in %");
    public static final Bezeichner KU_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KU-Abschläge in Währungseinheiten");
    public static final Bezeichner KU_AENDERUNGSDATUM = new Bezeichner("KU-Änderungsdatum");
    public static final Bezeichner KU_AUSSCHLUSS = new Bezeichner("KU-Ausschluss");
    public static final Bezeichner KU_BEGINN = new Bezeichner("KU-Beginn");
    public static final Bezeichner KU_BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KU-Beitrag in Währungseinheiten");
    public static final Bezeichner KU_DECKUNG_RISIKO1 = new Bezeichner("KU-Deckung (Risiko 1)");
    public static final Bezeichner KU_DECKUNG_RISIKO2 = new Bezeichner("KU-Deckung (Risiko 2)");
    public static final Bezeichner KU_ZUSCHLAEGE_IN_PROZENT = new Bezeichner("KU-Zuschläge in %");
    public static final Bezeichner KU_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("KU-Zuschläge in Währungseinheiten");

    public static final Bezeichner LAENDERKENNZEICHEN = new Bezeichner("Laenderkennzeichen");
    public static final Bezeichner LAENDERKENNZEICHEN_DER_RISIKOANSCHRIFT = new Bezeichner("Laenderkennzeichen der Risikoanschrift");
    public static final Bezeichner LAND_DES_AMTL_KENNZEICHENS = new Bezeichner("Land des amtl. Kennzeichens");
    public static final Bezeichner LAUFZEITRABATT_IN_PROZENT = new Bezeichner("Laufzeitrabatt in %");
    public static final Bezeichner LAUFZEITVERKUERZUNG = new Bezeichner("Laufzeitverkuerzung");
    public static final Bezeichner LEBENSLANGE_BEITRAGSZAHLUNG = new Bezeichner("Lebenslange Beitragszahlung");
    public static final Bezeichner LEERSTELLEN = new Bezeichner("Leerstellen");
    public static final Bezeichner LEERSTELLEN1 = new Bezeichner("Leerstellen", "Leerstellen1");
    public static final Bezeichner LEERSTELLEN2 = LEERSTELLEN;
    public static final Bezeichner LEERSTELLEN3 = LEERSTELLEN;
    public static final Bezeichner LEERSTELLEN4 = new Bezeichner("Leerstellen", "Leerstellen4");
    public static final Bezeichner LEERSTELLEN5 = LEERSTELLEN;
    public static final Bezeichner LEERSTELLEN9 = LEERSTELLEN;
    public static final Bezeichner LEISTUNG_AB_INVALIDITAETSGRAD_IN_PROZENT = new Bezeichner("Leistung ab Invaliditaetsgrad in Prozent");
    public static final Bezeichner LEISTUNG_BEI_SCHWERER_ERKRANKUNG = new Bezeichner("Leistung bei schwerer Erkrankung");
    public static final Bezeichner LEISTUNG_IN_WE = new Bezeichner("Leistung in WE");
    public static final Bezeichner LEISTUNGSAUSSCHLUSS1_AB = new Bezeichner("Leistungsausschluss 1 ab");
    public static final Bezeichner LEISTUNGSAUSSCHLUSS1_BIS = new Bezeichner("Leistungsausschluss 1 bis");
    public static final Bezeichner LEISTUNGSAUSSCHLUSS2_AB = new Bezeichner("Leistungsausschluss 2 ab");
    public static final Bezeichner LEISTUNGSAUSSCHLUSS2_BIS = new Bezeichner("Leistungsausschluss 2 bis");
    public static final Bezeichner LEISTUNGSAUSSCHLUSS3_AB = new Bezeichner("Leistungsausschluss 3 ab");
    public static final Bezeichner LEISTUNGSAUSSCHLUSS3_BIS = new Bezeichner("Leistungsausschluss 3 bis");
    public static final Bezeichner LEISTUNGSBEGINN_AB = new Bezeichner("Leistungsbeginn ab");
    public static final Bezeichner LEISTUNGSDAUER = new Bezeichner("Leistungsdauer");
    public static final Bezeichner LEISTUNGSDAUER_BIS = new Bezeichner("Leistungsdauer bis");
    public static final Bezeichner LEISTUNGSZAHLUNGSWEISE = new Bezeichner("Leistungszahlungsweise");
    public static final Bezeichner LETZTE_ERHOEHUNG = new Bezeichner("letzte Erhoehung");
    public static final Bezeichner LETZTE_ERHOEHUNG_8_III_AHB = new Bezeichner("Letzte Erhoehung \u00a78, III AHB");
    // Teildatensatz 1 Satzart 0210.684 Adresse 105-110
    public static final Bezeichner LETZE_ERHOEHUNG_DURCH_DYNAMIK = new Bezeichner("Letze Erhöhung durch Dynamik");
    public static final Bezeichner LFD_NUMMER = new Bezeichner("Lfd. Nummer");
    /** @deprecated bitte LFD_NUMMER verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER1 = LFD_NUMMER;
    /** @deprecated bitte LFD_NUMMER verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER2 = LFD_NUMMER;
    public static final Bezeichner LFD_NR_DER_DEKLARATION = new Bezeichner("Lfd. Nr. der Deklaration");
    /** @deprecated bitte LFD_NR_DER_DEKLARATION verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_DEKLARATION = LFD_NR_DER_DEKLARATION;
    /** @deprecated bitte LFD_NR_DER_DEKLARATION verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NR_DEKLARATION_1 = LFD_NR_DER_DEKLARATION;
    /** @deprecated bitte LFD_NR_DER_DEKLARATION verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NR_DEKLARATION_2 = LFD_NR_DER_DEKLARATION;
    public static final Bezeichner LFD_NUMMER_DES_FONDS = new Bezeichner("Lfd. Nummer des Fonds");
    public static final Bezeichner LFD_NUMMER_DES_VERSICHERTEN_FAHRZEUGES = new Bezeichner("lfd. Nummer des versicherten Fahrzeuges");
    public static final Bezeichner LFD_NUMMER_DES_VERSICHERTEN_TIERES = new Bezeichner("Laufende Nummer des versicherten Tieres");
    /** @deprecated bitte LFD_NUMMER_DES_VERSICHERTEN_TIERES verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_DES_VERSICHERTEN_TIERES_2 = LFD_NUMMER_DES_VERSICHERTEN_TIERES;
    public static final Bezeichner LFD_NUMMER_OBJEKT_KOMPLEX = new Bezeichner("lfd. Nr. Objekt / Komplex");
    public static final Bezeichner LFD_NUMMER_POSITION_DES_MASCHINENVERZEICHNISSES = new Bezeichner("lfd. Nr. Position des Maschinenverzeichnisses");
    public static final Bezeichner LFD_NUMMER_SATZART = new Bezeichner("Lfd. Nummer der Satzart");
    public static final Bezeichner LFD_NUMMER_VERSICHERUNGSGRUNDSTUECK = new Bezeichner("lfd. Nr. Versicherungsgrundst\u00fcck");
    /** @deprecated bitte LFD_NUMMER_VERSICHERUNGSGRUNDSTUECK verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_VERSICHERUNGSGRUNDSTUECK_2 = LFD_NUMMER_VERSICHERUNGSGRUNDSTUECK;
    /**
     * Fuer Satzart 0220.020.1 (Personendaten), 0350, 0352, 0362, 0382, 0390
     */
    public static final Bezeichner LFD_NUMMER_TARIF = new Bezeichner("Lfd. Nummer Tarif");
    /**
     * Fuer Satzart 0220.020.2 und 0220.020.3 (Allgemeine Tarifdaten und Spezielle Tarifdaten)
     */
    public static final Bezeichner LAUFENDE_NUMMER_TARIF = new Bezeichner("Laufende Nummer Tarif");
    public static final Bezeichner LFD_NUMMER_VP = new Bezeichner("Lfd. Nummer der versicherten Person (VP)", "LfdNrVersichertenPersonVp", "LfdNrVersichertenPersonVp9");
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE = new Bezeichner("Lfd. Nummer der versicherten Person (VP) / Personengruppe");
    /** @deprecated bitte LFD_NUMMER_VP_PERSONENGRUPPE verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE1 = LFD_NUMMER_VP_PERSONENGRUPPE;
    /** @deprecated bitte LFD_NUMMER_VP_PERSONENGRUPPE verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE2 = LFD_NUMMER_VP_PERSONENGRUPPE;
    /** @deprecated bitte LFD_NUMMER_VP_PERSONENGRUPPE verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE3 = LFD_NUMMER_VP_PERSONENGRUPPE;
    /** @deprecated bitte LFD_NUMMER_VP_PERSONENGRUPPE verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE4 = LFD_NUMMER_VP_PERSONENGRUPPE;
    /** @deprecated bitte LFD_NUMMER_VP_PERSONENGRUPPE verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE9 = new Bezeichner("Lfd. Nummer der versicherten Person (VP) / PersonengruppeAN", "LfdNrVersichertenPersonVp9");
    /** @deprecated bitte LFD_NUMMER_VP_PERSONENGRUPPE verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_VP_PERSONENGRUPPE10 = LFD_NUMMER_VP_PERSONENGRUPPE;

    public static final Bezeichner LFD_NUMMER_ZUR_ART_DER_LEISTUNG = new Bezeichner("Laufende Nummer zur Art der Leistung");
    public static final Bezeichner LFD_NUMMER_ZUR_WAGNISART = new Bezeichner("Lfd. Nummer zur Wagnisart");
    /** @deprecated bitte LFD_NUMMER_ZUR_WAGNISART verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_ZUR_WAGNISART1 = LFD_NUMMER_ZUR_WAGNISART;
    /** @deprecated bitte LFD_NUMMER_ZUR_WAGNISART verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_ZUR_WAGNISART2 = LFD_NUMMER_ZUR_WAGNISART;
    /** @deprecated bitte LFD_NUMMER_ZUR_WAGNISART verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_ZUR_WAGNISART3 = LFD_NUMMER_ZUR_WAGNISART;
    /** @deprecated bitte LFD_NUMMER_ZUR_WAGNISART verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner LFD_NUMMER_ZUR_WAGNISART4 = LFD_NUMMER_ZUR_WAGNISART;
    public static final Bezeichner LFD_PERSONENNR_GEVO = new Bezeichner("Lfd. Personennummer", "LfdPersonenNrimGeVo");
    public static final Bezeichner LFD_PERSONENNUMMER_DES_SICHERUNGSGLAEUBIGERS = new Bezeichner("Lfd. Personennummer des Sicherungsglaeubigers");
    public static final Bezeichner LFD_PERSONEN_NR_UNTERNUMMER = new Bezeichner("Lfd. Personen-Nr. / Unternummer");

    public static final Bezeichner MEHRLEISTUNGSKLAUSEL = new Bezeichner("Mehrleistungsklausel");
    public static final Bezeichner MEHRWERTGRUND = new Bezeichner("Mehrwertgrund");
    public static final Bezeichner MEHRWERT_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Mehrwert in W\u00e4hrungseinheiten");
    public static final Bezeichner MEHRZWECKFELD = new Bezeichner("Mehrzweckfeld");
    public static final Bezeichner MENGENSCHLUESSEL = new Bezeichner("Mengenschl\u00fcssel");
    public static final Bezeichner MINDESTBEITRAG_JE_WAGNIS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Mindestbeitrag je Wagnis in W\u00e4hrungseinheiten");
    public static final Bezeichner MINDESTLAUFZEIT = new Bezeichner("Mindestlaufzeit");
    public static final Bezeichner MITARBEITER_STATUS = new Bezeichner("Mitarbeiter Status");
    public static final Bezeichner MITVERSICHERTE_PERSON_FAMILIENNAME = new Bezeichner("Mitversicherte Person Familienname");
    public static final Bezeichner MITVERSICHERTE_PERSON_VORNAME = new Bezeichner("Mitversicherte Person Vorname");
    public static final Bezeichner MITZUVERSICHERNDE_PERSON = new Bezeichner("Mitzuversichernde Person");
    public static final Bezeichner MODELLNAME = new Bezeichner("Modellname");

    public static final Bezeichner NAECHSTE_AUSZAHLUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Auszahlungssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner NAECHSTER_AUSZAHLUNGSTERMIN = new Bezeichner("Naechster Auszahlungstermin");
    public static final Bezeichner NAECHSTE_UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Unfallsumme in W\u00e4hrungseinheiten");
    public static final Bezeichner NAECHSTE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Beitragssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner NAECHSTE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Erlebensfall VS in W\u00e4hrungseinheiten");
    public static final Bezeichner NAECHSTE_ERHOEHUNG = new Bezeichner("naechste Erhoehung");
    public static final Bezeichner NAECHSTE_ERHOEHUNG_DURCH_DYNAMIK = new Bezeichner("Nächste Erhöhung durch Dynamik");
    public static final Bezeichner NAECHSTE_JAHRESRENTE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Jahresrente in W\u00e4hrungseinheiten");
    public static final Bezeichner NAECHSTE_TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Naechste Todesfall VS in W\u00e4hrungseinheiten");
    public static final Bezeichner NAME1 = new Bezeichner("Name1");
    public static final Bezeichner NAME2 = new Bezeichner("Name2");
    public static final Bezeichner NAME3 = new Bezeichner("Name3");
    public static final Bezeichner NAME_DER_VERSICHERTEN_PERSON_VP = new Bezeichner("Name der versicherten Person (VP)");public static final Bezeichner NAME_DES_TIERES = new Bezeichner("Name des Tieres");
    public static final Bezeichner NAME_KREDITINSTITUT1 = new Bezeichner("Name des Kreditinstituts 1");
    public static final Bezeichner NAME_KREDITINSTITUT2 = new Bezeichner("Name des Kreditinstituts 2");
    public static final Bezeichner NAME_MITVERSICHERTES_KIND = new Bezeichner("Name des mitversicherten Kindes");
    public static final Bezeichner NAME_VP = new Bezeichner("Name der VP");
    public static final Bezeichner NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Nettobeitrag in W\u00e4hrungseinheiten");
    public static final Bezeichner NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN2 = NETTOBEITRAG_IN_WAEHRUNGSEINHEITEN;
    public static final Bezeichner NEUPREIS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Neupreis in W\u00e4hrungseinheiten");
    public static final Bezeichner NEUWERT_LISTENPREIS_IM_ANSCHAFFUNGSJAHR_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Neuwert / Listenpreis im Anschaffungsjahr in W\u00e4hrungseinheiten");
    public static final Bezeichner NEUWERTFAKTOR = new Bezeichner("Neuwertfaktor");
    public static final Bezeichner NUTZUNGSART = new Bezeichner("Nutzungsart");

    public static final Bezeichner OBJEKTBESCHREIBUNG = new Bezeichner("Objektbeschreibung");
    public static final Bezeichner OBJEKTKENNZIFFER = new Bezeichner("Objektkennziffer");
    public static final Bezeichner OBJEKTNUMMER = new Bezeichner("Objektnummer");
    /** @deprecated bitte OBJEKTNUMMER verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner OBJEKTNUMMER1 = OBJEKTNUMMER;
    /** @deprecated bitte OBJEKTNUMMER verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner OBJEKTNUMMER2 = OBJEKTNUMMER;
    public static final Bezeichner ORDNUNGS_NUMMER_FUER_WAGNISZUATZ = new Bezeichner("Ordnungs-Nummer fuer Wagniszuatz");
    public static final Bezeichner ORDNUNGS_NUMMER_FUER_WAGNISZUSATZ2 = ORDNUNGS_NUMMER_FUER_WAGNISZUATZ;
    public static final Bezeichner ORDNUNGSBEGRIFF = new Bezeichner("Ordnungsbegriff");
    public static final Bezeichner ORT = new Bezeichner("Ort");

    public static final Bezeichner PASSIVES_KRIEGSRISIKO = new Bezeichner("passives Kriegsrisiko");
    public static final Bezeichner PAUSCHALE_GLASVERSICHERUNG_OHNE_VERSICHERUNGSSUMME = new Bezeichner("Pauschale Glasversicherung ohne Angabe der Versicherungssumme");
    public static final Bezeichner PERSONENSTATUS = new Bezeichner("Personenstatus");
    public static final Bezeichner PERSONEN_KUNDENNUMMER_DES_VERMITTLERS = KUNDENNR_VERMITTLER;
    public static final Bezeichner PERSONEN_KUNDENNUMMER_DES_VERSICHERERS = KUNDENNR_VERSICHERER;
    public static final Bezeichner PERSONENNUMMER_LFD_NUMMER = new Bezeichner("Personennummer / lfd. Nummer");
    public static final Bezeichner POSITION_DER_DEKLARATION = new Bezeichner("Position der Deklaration");
    public static final Bezeichner POLICIERUNGSDAT = new Bezeichner("Policierungsdatum");
    public static final Bezeichner POSTALISCHES_KENNZEICHEN = new Bezeichner("postalisches Kennzeichen");
    public static final Bezeichner POSTFACH = new Bezeichner("Postfach");
    public static final Bezeichner POSTLEITZAHL = new Bezeichner("Postleitzahl");
    public static final Bezeichner POSTLEITZAHL_DER_RISIKOANSCHRIFT = new Bezeichner("Postleitzahl der Risikoanschrift");
    public static final Bezeichner PRAEMIENFAKTOR = new Bezeichner("Praemienfaktor");
    public static final Bezeichner PRAEMIENRICHTZAHL = new Bezeichner("Praemienrichtzahl");    
    public static final Bezeichner PRAEMIENSATZ_IN_PROMILLE = new Bezeichner("Prämiensatz in Promille");
    public static final Bezeichner PRODUKTBESCHREIBUNG = new Bezeichner("Produktbeschreibung");
    public static final Bezeichner PRODUKTFORM = new Bezeichner("Produktform");
    public static final Bezeichner PRODUKTFORM_GUELTIG_AB = new Bezeichner("Produktform gueltig ab");
    public static final Bezeichner PRODUKTKENNUNG = new Bezeichner("Produktkennung");
    public static final Bezeichner PRODUKTNAME = new Bezeichner("Produktname");
    public static final Bezeichner PROVISION = new Bezeichner("Provision");
    public static final Bezeichner PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Provisionspflichtige Beitragssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner PROVISIONSPFLICHTIGE_BEITRAGSSUMME_IN_WAEHRUNGSEINHEITEN2 = new Bezeichner("Provisionspflichtige Beitragssumme in W\u00e4hrungseinheiten 2");
    public static final Bezeichner PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Provisionspflichtige Wertungssumme in W\u00e4hrungseinheiten");
    public static final Bezeichner PROVISIONSPFLICHTIGE_WERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2 = new Bezeichner("Provisionspflichtige Wertungssumme in W\u00e4hrungseinheiten 2");
    public static final Bezeichner PROZENTSATZ = new Bezeichner("Prozentsatz");
    public static final Bezeichner PROZENTSATZ_PROGRESSIVE_INVALIDITAET = new Bezeichner("Prozentsatz progressive Invaliditaet");
    public static final Bezeichner PROZENTSATZ_PROGRESSIVE_INVALIDITAET_MEHRLEISTUNG_BEI_INVALIDITAET = new Bezeichner("Prozentsatz progressive Invaliditaet / Mehrleistung bei Invaliditaet");
    public static final Bezeichner PROZENTSATZ_VORSORGEZUSCHLAG = new Bezeichner("Prozentsatz Vorsorgezuschlag");

    public static final Bezeichner QM = new Bezeichner("qm");

    public static final Bezeichner RABATTGRUNDJAHR = new Bezeichner("Rabattgrundjahr");
    public static final Bezeichner RASSE = new Bezeichner("Rasse");
    public static final Bezeichner RATENZAHLUNGSZUSCHLAG_IN_PROZENT = new Bezeichner("Ratenzahlungszuschlag in %");
    public static final Bezeichner RECHTSFORM = new Bezeichner("Rechtsform");
    public static final Bezeichner REFERENZNUMMER = new Bezeichner("Referenznummer");
    public static final Bezeichner REFERENZ_NR = new Bezeichner("Referenz-Nr");
    public static final Bezeichner REFERENZ_VERSICHERUNGSSCHEINNUMMER = new Bezeichner("Referenz-Versicherungsscheinnummer");
    public static final Bezeichner REFERENZ_VERSICHERUNGSSCHEINNUMMER_1 = new Bezeichner("1. Referenz-Versicherungsscheinnummer", "ReferenzVsNr1");
    public static final Bezeichner REFERENZ_VERSICHERUNGSSCHEINNUMMER_2 = new Bezeichner("2. Referenz-Versicherungsscheinnummer", "ReferenzVsNr2");
    public static final Bezeichner REFERENZ_VERSICHERUNGSSCHEINNUMMER_3 = new Bezeichner("3. Referenz-Versicherungsscheinnummer", "ReferenzVsNr3");
    public static final Bezeichner REFERENZ_VERSICHERUNGSSCHEIN_NUMMER_BEI_VERTRAGSTRENNUNG_ZUSAMMENLEGUNG = new Bezeichner("Referenz-Versicherungsschein-Nummer bei Vertragstrennung/-zusammenlegung");
    public static final Bezeichner REGISTRIERUNGSNUMMER_VERMITTLER = new Bezeichner("Registrierungsnummer Vermittler");
    public static final Bezeichner RENTE_INCL_UEBERSCHUSSBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Rente incl. Ueberschussbeteiligung in W\u00e4hrungseinheiten");
    public static final Bezeichner RENTE_INCL_UEBERSCHUSSANRECHNUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Rente incl. Ueberschussanrechnung in W\u00e4hrungseinheiten");
    public static final Bezeichner RENTENZAHLWEISE = new Bezeichner("Rentenzahlweise");
    public static final Bezeichner RESTLAUFZEIT_DES_VERTRAGES = new Bezeichner("Restlaufzeit des Vertrages");
    public static final Bezeichner RISIKO = new Bezeichner("Risiko");
    public static final Bezeichner RISIKOGRUPPE_RISIKOART1 = new Bezeichner("Risikogruppe / Risikoart 1");
    public static final Bezeichner RISIKOGRUPPE_RISIKOART2 = new Bezeichner("Risikogruppe / Risikoart 2");
    public static final Bezeichner RISIKOKENNZIFFER = new Bezeichner("Risikokennziffer");
    public static final Bezeichner RISIKOORT = new Bezeichner("Risikoort");
    public static final Bezeichner RISIKOSTRASSE = new Bezeichner("Risiko Strasse");
    public static final Bezeichner RISIKOTEXT = new Bezeichner("Risikotext");
    public static final Bezeichner RISKIOEINHEIT1 = new Bezeichner("Risikoeinheit-1");
    public static final Bezeichner RISKIOEINHEIT2 = new Bezeichner("Risikoeinheit-2");
    public static final Bezeichner RISIKOEINSCHRAENKUNG = new Bezeichner("Risikoeinschraenkung");
    public static final Bezeichner RISIKOLAENDERKENNZEICHEN = new Bezeichner("Risiko Länderkennzeichen");
    public static final Bezeichner RISIKOPOSTLEITZAHL = new Bezeichner("Risiko Postleitzahl");
    public static final Bezeichner RISIKOVORLAUF = new Bezeichner("Risikovorlauf");
    public static final Bezeichner RISIKOZUSCHLAEGE = new Bezeichner("Risikozuschlaege");
    public static final Bezeichner RISIKOZUSCHLAG = new Bezeichner("Risikozuschlag");
    public static final Bezeichner RISIKOZUSCHLAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Risikozuschlag in W\u00e4hrungseinheiten");
    public static final Bezeichner RISIKOZUSCHLAG1_IN_PROZENT = new Bezeichner("Risikozuschlag 1 in Prozent");
    public static final Bezeichner RISIKOZUSCHLAG1_AB = new Bezeichner("Risikozuschlag 1 ab");
    public static final Bezeichner RISIKOZUSCHLAG1_BIS = new Bezeichner("Risikozuschlag 1 bis");
    public static final Bezeichner RISIKOZUSCHLAG2_IN_PROZENT = new Bezeichner("Risikozuschlag 2 in Prozent");
    public static final Bezeichner RISIKOZUSCHLAG2_AB = new Bezeichner("Risikozuschlag 2 ab");
    public static final Bezeichner RISIKOZUSCHLAG2_BIS = new Bezeichner("Risikozuschlag 2 bis");
    public static final Bezeichner RISIKOZUSCHLAG3_IN_PROZENT = new Bezeichner("Risikozuschlag 3 in Prozent");
    public static final Bezeichner RISIKOZUSCHLAG3_AB = new Bezeichner("Risikozuschlag 3 ab");
    public static final Bezeichner RISIKOZUSCHLAG3_BIS = new Bezeichner("Risikozuschlag 3 bis");
    public static final Bezeichner RISIKOZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Risikozuschlagsbetrag in Währungseinheiten");
    public static final Bezeichner RISIKO_LFD_NUMMER = new Bezeichner("Risiko lfd. Nummer");
    public static final Bezeichner ROLLE_W_AKZ = new Bezeichner("Rolle W-AKZ");
    public static final Bezeichner ROHBAU_EINMALBETRAG = new Bezeichner("Rohbau-Einmalbetrag in W\u00e4hrungseinheiten");
    public static final Bezeichner RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Rueckkaufswert in W\u00e4hrungseinheiten");
    public static final Bezeichner RUECKGEWAEHRSUMME_ZUM_ABLAUF_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Rueckgewaehrsumme zum Ablauf in W\u00e4hrungseinheiten");
    public static final Bezeichner RUECKKAUFSWERT_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Rueckkaufswert zum Berechnungsstichtag in W\u00e4hrungseinheiten", "RueckkaufswertZumBerechnugsstichtagInWE", "RueckkaufswertZumBerechnungsstichtagInWE");
    public static final Bezeichner RUECKKAUFSWERT_IN_WAEHRUNGSEINHEITEN_MIT_NACHKOMMA = new Bezeichner("Rueckkaufswert in W\u00e4hrungseinheiten");
    public static final Bezeichner RUECKKAUFSWERT_GUELTIG_AM = new Bezeichner("Rueckkaufswert gueltig am");
    /** seit Version 2018 durch RUECKKAUFSWERT_GUELTIG_AM ersetzt. */
    public static final Bezeichner RUECKKAUFSWERT_GUELTIG_AB = new Bezeichner("Rueckkaufswert gueltig ab");
    public static final Bezeichner RUECKGEWAEHR_BEI_TOD = new Bezeichner("Rueckgewaehr bei Tod");
    public static final Bezeichner RUECKFUEHRUNGSKOSTEN = new Bezeichner("Rueckfuehrungskosten");
    public static final Bezeichner RUECKGEWAEHRDAT = new Bezeichner("Rueckgewaehrdatum");

    public static final Bezeichner SAISONKENNZEICHEN = new Bezeichner("Saisonkennzeichen");
    public static final Bezeichner SATZART = new Bezeichner("Satzart");
    public static final Bezeichner SATZART_9999 = new Bezeichner("Satzart 9999");
    public static final Bezeichner SATZART_0001 = new Bezeichner("Satzart 0001");
    public static final Bezeichner SATZART_0100 = new Bezeichner("Satzart 0100");
    public static final Bezeichner SATZART_0200 = new Bezeichner("Satzart 0200");
    public static final Bezeichner SATZART_0202_ALLG_ANTRAGSDATEN = new Bezeichner("Allgemeine Antragsdaten 0202", "Satzart0202", "AllgemeineAntragsdaten0202");
    public static final Bezeichner SATZART_0210_050 = new Bezeichner("Satzart 0210.050");
    public static final Bezeichner SATZART_0220_051 = new Bezeichner("Satzart 0220.051");
    public static final Bezeichner SATZART_0220_052 = new Bezeichner("Satzart 0220.052");
    public static final Bezeichner SATZART_0220_053 = new Bezeichner("Satzart 0220.053");
    public static final Bezeichner SATZART_0220_054 = new Bezeichner("Satzart 0220.054");
    public static final Bezeichner SATZART_0220_059 = new Bezeichner("Satzart 0220.059");
    public static final Bezeichner SATZART_0210_040 = new Bezeichner("Satzart 0210.040");
    public static final Bezeichner SATZART_0220_040 = new Bezeichner("Satzart 0220.040");
    public static final Bezeichner SATZART_0210_030 = new Bezeichner("Satzart 0210.030");
    public static final Bezeichner SATZART_0220_030 = new Bezeichner("Satzart 0220.030");
    public static final Bezeichner SATZART_0210_010 = new Bezeichner("Satzart 0210.010");
    public static final Bezeichner SATZART_0220_010 = new Bezeichner("Satzart 0220.010");
    public static final Bezeichner SATZART_0210_130 = new Bezeichner("Satzart 0210.130");
    public static final Bezeichner SATZART_0220_130 = new Bezeichner("Satzart 0220.130");
    public static final Bezeichner SATZART_0210_110 = new Bezeichner("Satzart 0210.110");
    public static final Bezeichner SATZART_0220_110 = new Bezeichner("Satzart 0220.110");
    public static final Bezeichner SATZART_0210_140 = new Bezeichner("Satzart 0210.140");
    public static final Bezeichner SATZART_0220_140 = new Bezeichner("Satzart 0220.140");
    public static final Bezeichner SATZART_0210_020 = new Bezeichner("Satzart 0210.020");
    public static final Bezeichner SATZART_0220_020 = new Bezeichner("Satzart 0220.020");
    public static final Bezeichner SATZART_0210_070 = new Bezeichner("Satzart 0210.070");
    public static final Bezeichner SATZART_0220_070 = new Bezeichner("Satzart 0220.070");
    public static final Bezeichner SATZART_0210_FEUER = new Bezeichner("Satzart 0210.080", "Satzart0210", "Satzart0210080");
    public static final Bezeichner SATZART_0220_FEUER = new Bezeichner("Satzart 0220.080", "Satzart0220", "Satzart0220080");
    public static final Bezeichner SATZART_0210_510 = new Bezeichner("Satzart 0210.510");
    public static final Bezeichner SATZART_0220_510 = new Bezeichner("Satzart 0220.510");
    public static final Bezeichner SATZART_0210_TRANSPORT = new Bezeichner("Satzart 0210.190", "Satzart02103", "Satzart0210190");
    public static final Bezeichner SATZART_0220_TRANSPORT = new Bezeichner("Satzart 0220.190", "Satzart02203", "Satzart0220190");
    public static final Bezeichner SATZART_0250_TRANSPORT = new Bezeichner("Satzart 0250.190", "Satzart0250Einzelanmeldung", "Satzart0250190");
    public static final Bezeichner SATZART_0260_TRANSPORT = new Bezeichner("Satzart 0260.190", "Satzart0260Umsatzanmeldung", "Satzart0260190");
    public static final Bezeichner SATZART_0210_NICHT_DEF_SPARTEN = new Bezeichner("Satzart 0210.000", "Satzart02104", "Satzart0210000");
    public static final Bezeichner SATZART_0210_TECH_VERS = new Bezeichner("Satzart 0210.170", "Satzart02102", "Satzart0210170");
    public static final Bezeichner SATZART_0220_NICHT_DEF_SPARTEN = new Bezeichner("Satzart 0220.000", "Satzart02204", "Satzart0220000");
    public static final Bezeichner SATZART_0220_TECH_VERS = new Bezeichner("Satzart 0220.170", "Satzart02202", "Satzart0220170");
    public static final Bezeichner SATZART_0220_055 = new Bezeichner("Satzart 0220.055", "Satzart0220055", "KfzBaustein");
    public static final Bezeichner SATZART_0300_BETEILIGUNGSINFORMATION = new Bezeichner("Satzart 0300", "Satzart0300", "BeteiligungsInformationssatzSatzart0300");
    public static final Bezeichner SATZART_0342_BEGLEITDOK = new Bezeichner("Satzart 0342", "Satzart0342", "BegleitdokumenteundSignaturen0342");
    public static final Bezeichner SATZART_0372_PRODUKT_SPEZIFISCHE_ANTRAGSDATEN = new Bezeichner("Satzart 0372");
    public static final Bezeichner SATZART_0400_INKASSO = new Bezeichner("Satzart 0400", "Satzart0400", "AllgemeineInkassoDatenSatzart0400");
    public static final Bezeichner SATZART_0410_INKASSO = new Bezeichner("Satzart 0410", "Satzart0410", "InkassoTeilsparteSatzart0410");
    public static final Bezeichner SATZART_0430_INKASSO = new Bezeichner("Satzart 0430", "Satzart0430", "AllgemeineInkassoDatenSatzart0430");
    public static final Bezeichner SATZART_0500_SCHADENINFORMATION = new Bezeichner("Satzart 0500", "Satzart0500", "Schadeninformationssatzsatzart0500");
    public static final Bezeichner SATZART_0420_VERSICHERUNGSTEUERABRECHNUNG = new Bezeichner("Satzart 0420", "Satzart0420", "VerssteuerabrechnungssatzGemaessEgRichtliniesatzart0420");
    public static final Bezeichner SATZART_0450_INKASSOABRECHNUNG = new Bezeichner("Satzart 0450", "Satzart0450", "InkassoAbrechnungssatzTransportSatzart0450");
    public static final Bezeichner SATZART_0550_SCHADENABRECHNUNG = new Bezeichner("Satzart 0550", "Satzart0550", "Schadenabrechnungssatzsatzart0550");
    public static final Bezeichner SATZART_0350_KLAUSELN = new Bezeichner("Satzart 0350", "Satzart0350", "KlauselDatensatzSatzart0350");
    public static final Bezeichner SATZART_0211_050 = new Bezeichner("Satzart 0211.050");
    public static final Bezeichner SATZART_0221_051 = new Bezeichner("Satzart 0221.051");
    public static final Bezeichner SATZART_0221_052 = new Bezeichner("Satzart 0221.052");
    public static final Bezeichner SATZART_0221_053 = new Bezeichner("Satzart 0221.053");
    public static final Bezeichner SATZART_0221_054 = new Bezeichner("Satzart 0221.054");
    public static final Bezeichner SATZART_0221_055 = new Bezeichner("Satzart 0221.055");
    public static final Bezeichner SATZART_0221_059 = new Bezeichner("Satzart 0221.059");
    public static final Bezeichner SATZART_0211_040 = new Bezeichner("Satzart 0211.040");
    public static final Bezeichner SATZART_0221_040 = new Bezeichner("Satzart 0221.040");
    public static final Bezeichner SATZART_0221_030 = new Bezeichner("Satzart 0221.030");
    public static final Bezeichner SATZART_0211_010 = new Bezeichner("Satzart 0211.010");
    public static final Bezeichner SATZART_0221_010 = new Bezeichner("Satzart 0221.010");
    public static final Bezeichner SATZART_0211_130 = new Bezeichner("Satzart 0211.130");
    public static final Bezeichner SATZART_0221_130 = new Bezeichner("Satzart 0221.130");
    public static final Bezeichner SATZART_0211_110 = new Bezeichner("Satzart 0211.110");
    public static final Bezeichner SATZART_0221_110 = new Bezeichner("Satzart 0221.110");
    public static final Bezeichner SATZART_0211_140 = new Bezeichner("Satzart 0211.140");
    public static final Bezeichner SATZART_0221_140 = new Bezeichner("Satzart 0221.140");
    public static final Bezeichner SATZART_0221_070 = new Bezeichner("Satzart 0221.070");
    public static final Bezeichner SATZART_0211_FEUER = new Bezeichner("Satzart 0211.080", "Satzart0211", "Satzart0211080");
    public static final Bezeichner SATZART_0221_FEUER = new Bezeichner("Satzart 0221.080", "Satzart0221", "Satzart0221080");
    public static final Bezeichner SATZART_0221_510 = new Bezeichner("Satzart 0221.510");
    public static final Bezeichner SATZART_0211_TRANSPORT = new Bezeichner("Satzart 0211.190", "Satzart02113", "Satzart0211190");
    public static final Bezeichner SATZART_0221_TRANSPORT = new Bezeichner("Satzart 0221.190", "Satzart02213", "Satzart0221190");
    public static final Bezeichner SATZART_0222 = new Bezeichner("Satzart 0222.030", "Satzart0222030", "UnfallspezifischeAntragsdaten0222");
    public static final Bezeichner SATZART_0225_LEBEN = new Bezeichner("Satzart 0225.010", "Satzart0225010", "LebenRenteLeistungsarten");
    public static final Bezeichner SATZART_0230_LEBEN = new Bezeichner("Satzart 0230.010", "Satzart0230010", "FondsdatensatzLeben0230");
    public static final Bezeichner SATZART_0230_UNFALL = new Bezeichner("Satzart 0230.030", "Satzart0230030", "UnfallLeistungsarten0230");
    public static final Bezeichner SATZART_0230_KFZ_WECHSEL_AKZ = new Bezeichner("Satzart 0230.050", "Satzart0230050", "KFZWechselkennzeichenWAKZ");
    public static final Bezeichner SATZART_0230_GEBAEUDE = new Bezeichner("Satzart 0230.140", "Satzart0230140", "VerbundeneGebaeudeVersicherteSachenUndKosten");
    public static final Bezeichner SATZART_0251_TRANSPORT = new Bezeichner("Satzart 0251.190", "Satzart0251190", "Satzart0251Einzelanmeldung");
    public static final Bezeichner SATZART_0211_NICHT_DEF_SPARTEN = new Bezeichner("Satzart 0211.000", "Satzart02114", "Satzart0211000");
    public static final Bezeichner SATZART_0211_TECH_VERS = new Bezeichner("Satzart 0211.170", "Satzart02112", "Satzart0211170");
    public static final Bezeichner SATZART_0221_NICHT_DEF_SPARTEN = new Bezeichner("Satzart 0221.000", "Satzart02214", "Satzart0221000");
    public static final Bezeichner SATZART_0221_TECH_VERS = new Bezeichner("Satzart 0221.170", "Satzart02212", "Satzart0221170");
    public static final Bezeichner SATZART_0210_550 = new Bezeichner("Satzart 0210.550");
    public static final Bezeichner SATZART_0210_560 = new Bezeichner("Satzart 0210.560");
    public static final Bezeichner SATZART_0210_570 = new Bezeichner("Satzart 0210.570");
    public static final Bezeichner SATZART_0220_550 = new Bezeichner("Satzart 0220.550");
    public static final Bezeichner SATZART_0220_560 = new Bezeichner("Satzart 0220.560");
    public static final Bezeichner SATZART_0220_570 = new Bezeichner("Satzart 0220.570");
    public static final Bezeichner SATZART_0270_550 = new Bezeichner("Satzart 0270.550");
    public static final Bezeichner SATZART_0280_550 = new Bezeichner("Satzart 0280.550");
    public static final Bezeichner SATZART_0291_550 = new Bezeichner("Satzart 0291.550");
    public static final Bezeichner SATZART_0292_550 = new Bezeichner("Satzart 0292.550");
    public static final Bezeichner SATZART_0293_550 = new Bezeichner("Satzart 0293.550");
    public static final Bezeichner SATZART_0294_550 = new Bezeichner("Satzart 0294.550");
    public static final Bezeichner SATZART_0295_550 = new Bezeichner("Satzart 0295.550");
    public static final Bezeichner SATZART_0052 = new Bezeichner("Satzart 0052");
    public static final Bezeichner SATZART_0102 = new Bezeichner("Satzart 0102");
    public static final Bezeichner SATZART_0212 = new Bezeichner("Satzart 0212", "Satzart0212", "Satzart0212050");
    public static final Bezeichner SATZART_0352 = new Bezeichner("Satzart 0352");
    public static final Bezeichner SATZART_0362 = new Bezeichner("Satzart 0362");
    public static final Bezeichner SATZART_0382 = new Bezeichner("Satzart 0382");
    public static final Bezeichner SATZART_0390_RABATTE = new Bezeichner("Satzart 0390", "Satzart0390", "RabatteundZuschlaege0390");
    public static final Bezeichner SATZART_0392_EVB = new Bezeichner("Satzart 0392", "Satzart0392", "eVBNummer0392");
    public static final Bezeichner SATZART_9950 = new Bezeichner("Satzart 9950");
    public static final Bezeichner SATZART_9951_MIME = new Bezeichner("Satzart 9951", "Satzart9951", "MIMEDatei9951");
    public static final Bezeichner SATZART_9952 = new Bezeichner("Satzart 9952");
    public static final Bezeichner SATZART_0210_580 = new Bezeichner("Satzart 0210.580");
    public static final Bezeichner SATZART_0210_684 = new Bezeichner("Satzart 0210.684");
    public static final Bezeichner SATZART_0220_580 = new Bezeichner("Satzart 0220.580");
    public static final Bezeichner SATZART_0220_684 = new Bezeichner("Satzart 0220.684");
    public static final Bezeichner SATZART_0372 = new Bezeichner("Satzart 0372", "Satzart0372", "ProduktspezifischeAntragsdaten0372");
    public static final Bezeichner SATZART_0600 = new Bezeichner("Satzart 0600", "Satzart0600", "ProduktspezifischeStammdaten");
    public static final Bezeichner SATZ_NR_1 = new Bezeichner("Satznummer1", "SatzNr1", "SatzNr", "Satznummer");
    public static final Bezeichner SATZ_NR_2 = new Bezeichner("Satznummer2", "SatzNr2");
    public static final Bezeichner SATZ_NR_3 = new Bezeichner("Satznummer3", "SatzNr3");
    public static final Bezeichner SATZ_NR_4 = new Bezeichner("Satznummer4", "SatzNr4");
    public static final Bezeichner SATZ_NR_9 = new Bezeichner("Satznummer9", "SatzNr9");
    public static final Bezeichner SATZNUMMER = new Bezeichner("Satznummer", "SatzNr", "Satznummer",
            "SatzNr1", "SatzNr2", "SatzNr3", "SatzNr4", "SatzNr9");
    /** @deprecated bitte SATZNUMMER oder SATZ_NR_1 verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner SATZNUMMER1 = SATZNUMMER;
    /** @deprecated bitte SATZNUMMER oder SATZ_NR_2 verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner SATZNUMMER2 = SATZNUMMER;
    /** @deprecated bitte SATZNUMMER oder SATZ_NR_3 verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner SATZNUMMER3 = SATZNUMMER;
    /** @deprecated bitte SATZNUMMER oder SATZ_NR_4 verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner SATZNUMMER4 = SATZNUMMER;
    /** @deprecated bitte SATZNUMMER verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner SATZNUMMER5 = SATZNUMMER;
    /** @deprecated bitte SATZNUMMER oder SATZ_NR_9 verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner SATZNUMMER9 = SATZNUMMER;
    public static final Bezeichner SATZNUMMER38 = new Bezeichner("Satznummer38", "SatzNr");
    public static final Bezeichner SATZNUMMERNWIEDERHOLUNG = new Bezeichner("Satznummernwiederholung", "SatzNrnwiederholung", "SatzNrnwiederholung1", "SatzNrnwiederholung2", "SatzNrnwiederholung3");
    public static final Bezeichner SATZNUMMERNWIEDERHOLUNG1 = new Bezeichner("Satznummernwiederholung1", "SatzNrnwiederholung1");
    public static final Bezeichner SATZNUMMERNWIEDERHOLUNG2 = new Bezeichner("Satznummernwiederholung2", "SatzNrnwiederholung2");
    public static final Bezeichner SATZNUMMERNWIEDERHOLUNG3 = new Bezeichner("Satznummernwiederholung3", "SatzNrnwiederholung3");
    public static final Bezeichner SATZNUMMERNWIEDERHOLUNG4 = new Bezeichner("Satznummernwiederholung4", "SatzNrnwiederholung4");
    public static final Bezeichner SATZNUMMERNWIEDERHOLUNG9 = new Bezeichner("Satznummernwiederholung9", "SatzNrnwiederholung9");
    public static final Bezeichner SCHADENBEARBEITUNGSKOSTEN = new Bezeichner("Schadenbearbeitungskosten");
    public static final Bezeichner SCHADENBEARBEITUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Schadenbearbeitungskosten in W\u00e4hrungseinheiten");
    public static final Bezeichner SCHADENDATUM = new Bezeichner("Schadendatum");
    public static final Bezeichner SCHADENMELDEDATUM = new Bezeichner("Schadenmeldedatum");
    public static final Bezeichner SCHAEDEN_AUS_RUECKSTUFUNG = new Bezeichner("Schäden aus Rückstufung");
    public static final Bezeichner SCHLUESSEL_SICHERUNGSEINRICHTUNG = new Bezeichner("Schl\u00fcssel Sicherungseinrichtung");
    public static final Bezeichner SCHLUSSALTER_DES_WAISEN = new Bezeichner("Schlussalter des Waisen");
    public static final Bezeichner SCHUTZBRIEF_VERKEHRSSERVICE = new Bezeichner("Schutzbrief /Verkehrsservice");
    public static final Bezeichner SELBSTBEHALT = new Bezeichner("Selbstbehalt");
    public static final Bezeichner SELBSTBEHALT_IN_PROZ = new Bezeichner("Selbstbehalt in Prozent");
    public static final Bezeichner SELBSTBEHALT_IN_PROZENT = new Bezeichner("Selbstbehalt in Prozent");
    public static final Bezeichner SELBSTBEHALT_IN_TAGEN = new Bezeichner("Selbstbehalt in Tagen");
    public static final Bezeichner SELBSTBEHALT_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Selbstbehalt in W\u00e4hrungseinheiten");
    public static final Bezeichner SELBSTBETEILIGUNG = new Bezeichner("Selbstbeteiligung");
    public static final Bezeichner SELBSTBETEILIGUNG_IN_PROZENT = new Bezeichner("Selbstbeteiligung in %");
    /**
     * Fuer Satzart 220, Sparte 20, Folge-Nr. 3
     */
    public static final Bezeichner SELBSTBETEILIGUNG_IN_PROZENT2 = new Bezeichner("Selbstbeteiligung in Prozent");
    public static final Bezeichner SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Selbstbeteiligung in W\u00e4hrungseinheiten");
    public static final Bezeichner SELBSTBETEILIGUNG_IN_WE_MAX = new Bezeichner("Selbstbeteiligung in WE (max.)");
    public static final Bezeichner SELBSTBETEILIGUNG_IN_WE_MIND = new Bezeichner("Selbstbeteiligung in WE (mind.)");
    /** @deprecated bitte {@link #SELBSTBETEILIGUNG_IN_WE_MAX} verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_MAX = SELBSTBETEILIGUNG_IN_WE_MAX;
    /** @deprecated bitte {@link #SELBSTBETEILIGUNG_IN_WE_MIND} verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_MIND = SELBSTBETEILIGUNG_IN_WE_MIND;
    public static final Bezeichner SEPA_GLAEUBIGERIDENTIFIKATIONSNUMMER = new Bezeichner("SEPA-Glaeubigeridentifikationsnummer");
    public static final Bezeichner SEPA_MANDAT_AUSSTELLUNGSDATUM = new Bezeichner("SEPA-Mandat Ausstellungsdatum");
    public static final Bezeichner SEPA_MANDAT_BASIS_FIRMENLASTSCHRIFT = new Bezeichner("SEPA-Mandat Basis-/Firmenlastschrift");
    public static final Bezeichner SEPA_MANDAT_EINMALIG_WIEDERKEHREND = new Bezeichner("SEPA-Mandat einmalig/wiederkehrend");
    public static final Bezeichner SEPA_MANDAT_KONTOREFERENZ = new Bezeichner("SEPA-Mandat Kontoreferenz");
    public static final Bezeichner SEPA_MANDAT_REFERENZNUMMER = new Bezeichner("SEPA-Mandat Referenznummer");
    public static final Bezeichner SERVICELEISTUNGEN = new Bezeichner("Serviceleistungen");
    public static final Bezeichner SERVICELEISTUNGEN_BEITRAGSSATZ = new Bezeichner("Serviceleistungen Beitragssatz");
    public static final Bezeichner SFS_KLASSE = new Bezeichner("SF/S-Klasse");
    public static final Bezeichner SICHERUNGSEINRICHTUNG = new Bezeichner("Sicherungseinrichtung");
    public static final Bezeichner SICHERUNGSGLAEUBIGER = new Bezeichner("Sicherungsglaeubiger");
    public static final Bezeichner SICHERUNGSRICHTLINIEN = new Bezeichner("Sicherungsrichtlinien");
    public static final Bezeichner SICHERUNGSSCHEIN = new Bezeichner("Sicherungsschein");
    public static final Bezeichner SOLIDARITAETSZUSCHLAG_BEI_ABLAUF = new Bezeichner("Solidarit\u00e4tszuschlag bei Ablauf");
    public static final Bezeichner SOLIDARITAETSZUSCHLAG_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG = new Bezeichner("Solidarit\u00e4tszuschlag bei Rueckkauf zum Berechnungsstichtag");
    public static final Bezeichner SONDERBEDINGUNGEN = new Bezeichner("Sonderbedingungen");
    public static final Bezeichner SONDERBEDINGUNGEN_KLAUSELN = new Bezeichner("Sonderbedingungen / Klauseln");
    public static final Bezeichner SONDERVEREINBARUNG1 = new Bezeichner("Sondervereinbarung 1");
    public static final Bezeichner SONDERVEREINBARUNG2 = new Bezeichner("Sondervereinbarung 2");
    public static final Bezeichner SONDERVEREINBARUNG3 = new Bezeichner("Sondervereinbarung 3");
    public static final Bezeichner SONDERVEREINBARUNGEN = new Bezeichner("Sondervereinbarungen");
    public static final Bezeichner SONSTIGE_ZUSCHLAGSBETRAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Sonstige Zuschlagsbetr\u00e4ge in W\u00e4hrungseinheiten");
    public static final Bezeichner SONSTIGE_ABSCHLAGSBETRAEGE_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Sonstige Abschlagsbetr\u00e4ge in W\u00e4hrungseinheiten");
    public static final Bezeichner SONSTIGER_BEZUGSBERECHTIGTER_IM_ERLEBENSFALL = new Bezeichner("Sonstiger Bezugsberechtigter im Erlebensfall");
    public static final Bezeichner SONSTIGER_BEZUGSBERECHTIGTER_IM_LEISTUNGSFALL = new Bezeichner("Sonstiger Bezugsberechtigter im Leistungsfall");
    public static final Bezeichner SONSTIGER_BEZUGSBERECHTIGTER_IM_TODESFALL = new Bezeichner("Sonstiger Bezugsberechtigter im Todesfall");
    public static final Bezeichner SOZIALVERSICHERUNG_NUMMER = new Bezeichner("Sozialversicherung Nummer");
    public static final Bezeichner SPARTE = new Bezeichner("Sparte");
    public static final Bezeichner SPARTE2 = new Bezeichner("Sparte2", "Sparte2");
    public static final Bezeichner SPARTE_SA0100_TD5_FELD15 = SPARTE2;
    public static final Bezeichner SPARVORGANG = new Bezeichner("Sparvorgang");
    public static final Bezeichner SPEZIFIKATION_REFERENZ_VERSICHERUNGSSCHEINNUMMER = new Bezeichner("Spezifikation der Referenz-Versicherungsscheinnummer");
    public static final Bezeichner STAATSANGEHOERIGKEIT = new Bezeichner("Staatsangehoerigkeit");
    public static final Bezeichner STAENDIG_BEWOHNT = new Bezeichner("Staendig bewohnt");
    public static final Bezeichner STAERKE = new Bezeichner("Staerke");
    public static final Bezeichner STAERKEEINHEIT = new Bezeichner("Staerkeeinheit");
    public static final Bezeichner STATUS = new Bezeichner("Status");
    public static final Bezeichner STATUS_SEIT = new Bezeichner("Status seit");
    public static final Bezeichner STATUS_TARIF = new Bezeichner("Status Tarif");
  public static final Bezeichner STEIGERUNGSSATZ_FUER_BU_RENTE = new Bezeichner("Steigerungssatz für BU-Rente");
    public static final Bezeichner STERILISATION_KASTRATION = new Bezeichner("Sterilisation / Kastration");
    public static final Bezeichner STEUERNR_JURISTISCHE_PERSON = new Bezeichner("Steuernummer bei juristischen Personen");
    public static final Bezeichner STOCKWERKE = new Bezeichner("Stockwerke");
    public static final Bezeichner STRASSE = new Bezeichner("Straße");
    public static final Bezeichner SUMME_VORSORGEZUSCHLAG = new Bezeichner("Summe Vorsorgezuschlag");
    public static final Bezeichner SUMMENANPASSUNG_PROZENTSATZ = new Bezeichner("Summenanpassung - %-Satz");
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
    public static final Bezeichner TAGEGELD_RISIKO1 = new Bezeichner("Tagegeld (Risiko 1)");
    public static final Bezeichner TAGEGELD_RISIKO2 = new Bezeichner("Tagegeld (Risiko 2)");
    public static final Bezeichner TARIF = new Bezeichner("Tarif");
    public static final Bezeichner TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGTEIL_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugteil in W\u00e4hrungseinheiten");
    public static final Bezeichner TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGVOLL_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Tarifbeitrag 100 % fuer Kraftfahrt-Fahrzeugvoll in W\u00e4hrungseinheiten");
    public static final Bezeichner TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_GEPAECKVERS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Tarifbeitrag 100 % fuer Kraftfahrt-Gepäckvers. in W\u00e4hrungseinheiten");
    public static final Bezeichner TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_HAFTPFLICHT_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Tarifbeitrag 100 % fuer Kraftfahrt-Haftpflicht in W\u00e4hrungseinheiten");
    public static final Bezeichner TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_UNFALL_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Tarifbeitrag 100 % für Kraftfahrt-Unfall in W\u00e4hrungseinheiten");
    public static final Bezeichner TARIFBEZEICHNUNG = new Bezeichner("Tarifbezeichnung");
    public static final Bezeichner TARIFBEZEICHNUNG_DES_FOLGETARIFS = new Bezeichner("Tarifbezeichnung des Folgetarifs");
    public static final Bezeichner TARIFIERUNGSMERKMAL = new Bezeichner("Tarifierungsmerkmal");
    public static final Bezeichner TARIFIERUNGSMERKMAL_LAUFZEIT = new Bezeichner("Tarifierungsmerkmal Laufzeit");
    public static final Bezeichner TARIFJAHR = new Bezeichner("Tarifjahr");
    public static final Bezeichner TARIFLEISTUNG = new Bezeichner("Tarifleistung");
    public static final Bezeichner TARIFWAHL_VERSICHERUNGSUMFANG = new Bezeichner("Tarifwahl / Versicherungsumfang");
    public static final Bezeichner TARIFZONE = new Bezeichner("Tarifzone");
    public static final Bezeichner TECHNISCHE_WAISE_PROZ_IM_RAHMEN_GESELLSCHAFTER_GESSCHAEFTSFUEHRER_VERS = new Bezeichner("technische Waise % (im Rahmen einer Gesellschafter/Gesschaeftsfuehrer-Vers.");
    public static final Bezeichner TECHNISCHE_WITWE_WITWER_PROZ_IM_RAHMEN_GESELLSCHAFTER_GESSCHAEFTSFUEHRERVERS = new Bezeichner("technische Witwe/Witwer % (im Rahmen einer Gesellschafter/Gesschaeftsfuehrer-Vers.)");
    public static final Bezeichner TECHNISCHER_VERS_BEGINN_DES_TARIFES = new Bezeichner("Technischer Vers.-Beginn des Tarifes");
    public static final Bezeichner TEILDATENSATZNUMMER = new Bezeichner("Teildatensatznummer");
    public static final Bezeichner TEILKAPITALISIERUNG = new Bezeichner("Teilkapitalisierung");
    public static final Bezeichner TERMIN_DER_BERECHNUNG = new Bezeichner("Termin der Berechnung");
    public static final Bezeichner THESAUR = new Bezeichner("Thesaur");
    public static final Bezeichner TIERART = new Bezeichner("Tierart");
    public static final Bezeichner TIERART_SONSTIGE = new Bezeichner("Tierart (Sonstige)");
    public static final Bezeichner TIERKENNZEICHNUNGSDETAILS = new Bezeichner("Tierkennzeichnungsdetails");
    public static final Bezeichner TITEL = new Bezeichner("Titel");
    public static final Bezeichner TITEL_DER_VP = new Bezeichner("Titel der VP");
    public static final Bezeichner TOD = new Bezeichner("Tod");
    public static final Bezeichner TOD_BEITRAGSSATZ = new Bezeichner("Tod-Beitragssatz");
    public static final Bezeichner TOD_RISIKO1 = new Bezeichner("Tod (Risiko 1)");
    public static final Bezeichner TOD_RISIKO2 = new Bezeichner("Tod (Risiko 2)");
    public static final Bezeichner TODESFALL_VS_IN_WAEHRUNGSEINHEIT = new Bezeichner("Todesfall VS in W\u00e4hrungseinheiten");
    public static final Bezeichner TODESFALL_VS_IN_WAEHRUNGSEINHEIT_ZUM_ABLAUF = new Bezeichner("Todesfall VS zum Ablauf");
    public static final Bezeichner TODESFALL_VS_IN_WAEHRUNGSEINHEITEN_ZUM_BEGINN_DER_ABRUFPHASE = new Bezeichner("Todesfall VS in W\u00e4hrungseinheiten zum Beginn der Abrufphase");
    public static final Bezeichner TODESFALL_VS_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Todesfall VS in W\u00e4hrungseinheiten");
    public static final Bezeichner TODESFALLAENDERUNGS_PROZENTSATZ = new Bezeichner("Todesfallaenderungs-Prozentsatz");
    public static final Bezeichner TODESFALLLEISTUNG_IN_PROZENT = new Bezeichner("Todesfall-Leistung in %");
    public static final Bezeichner TODESFALLLEISTUNG_IN_WAEHRUNGSEINHEITEN =
            new Bezeichner("Todesfallleistung in W\u00e4hrungseinheiten");
    public static final Bezeichner TRAEGERUNTERNEHMEN_NAME = new Bezeichner("Traegerunternehmen Name");
    public static final Bezeichner TRAEGERUNTERNEHMEN_SCHLUESSEL = new Bezeichner("Traegerunternehmen Schl\u00fcssel");
    public static final Bezeichner TYPKLASSE = new Bezeichner("Typklasse");
    public static final Bezeichner TYKLASSE_KH = new Bezeichner("Tyklasse KH");
    public static final Bezeichner TYPKLASSE_FUER_KFT = new Bezeichner("Typklasse fuer KFT");
    public static final Bezeichner TYPKLASSE_FUER_KFV = new Bezeichner("Typklasse fuer KFV");
    public static final Bezeichner TYPSCHLUESSEL_NR = new Bezeichner("Typschl\u00fcssel-Nr.");
    public static final Bezeichner TYP = new Bezeichner("Typ");
    public static final Bezeichner TYP_BANKVERBINDUNG1 = new Bezeichner("Typ der Bankverbindung 1");
    public static final Bezeichner TYP_BANKVERBINDUNG2 = new Bezeichner("Typ der Bankverbindung 2");

    public static final Bezeichner UEBERFUEHRUNGSKOSTEN = new Bezeichner("Ueberfuehrungskosten");
    public static final Bezeichner UEBERGANGSENTSCHAEDIGUNG = new Bezeichner("Uebergangsentschaedigung");
    public static final Bezeichner UEBERGANGSENTSCHAEDIGUNG_BEITRAGSSATZ = new Bezeichner("Uebergangsentschaedigung Beitragssatz");
    public static final Bezeichner UEBERSCHUSS_GUELTIG_AB = new Bezeichner("Ueberschuss gueltig ab");
    public static final Bezeichner UEBERSCHUSSANTEILE_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Ueberschussanteile zum Berechnungsstichtag in W\u00e4hrungseinheiten");
    public static final Bezeichner UMBAUTER_RAUM = new Bezeichner("umbauter Raum");
    public static final Bezeichner UMSATZSTEUER_ID = new Bezeichner("Umsatzsteuer-Identifikationsnummer");
    public static final Bezeichner UMSTELLUNGSDAT_FOLGETARIFS = new Bezeichner("Umstellungsdatum des Folgetarifs");
    public static final Bezeichner UMSTELLUNGSDATUM_DES_FOLGETARIFS = UMSTELLUNGSDAT_FOLGETARIFS;
    public static final Bezeichner UMTAUSCHRECHT = new Bezeichner("Umtauschrecht");
    public static final Bezeichner UNBEKANNT = new Bezeichner("unbekannt");
    public static final Bezeichner UNFAELLE = new Bezeichner("Unfaelle");
    public static final Bezeichner UNFALLAENDERUNGS_PROZENTSATZ = new Bezeichner("Unfallaenderungs-Prozentsatz");
    public static final Bezeichner UNFALLSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Unfallsumme in W\u00e4hrungseinheiten");
    public static final Bezeichner UNTERSTUETZUNGSKASSE_NAME = new Bezeichner("Unterstuetzungskasse Name");
    public static final Bezeichner UNTERSTUETZUNGSKASSE_SCHLUESSEL = new Bezeichner("Unterstuetzungskasse Schl\u00fcssel");
    public static final Bezeichner UNTERVERSICHERUNGSVERZICHT = new Bezeichner("Unterversicherungsverzicht");
    /** @deprecated bitte #UNTERVERSICHERUNGSVERZICHT verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner UNTERVERS_VERZICHT = UNTERVERSICHERUNGSVERZICHT;
    public static final Bezeichner UNVERFALLBARKEIT = new Bezeichner("Unverfallbarkeit");
    public static final Bezeichner UNWIDERRUFLICHES_BEZUGSRECHT_IM_ERLEBENSFALL = new Bezeichner("Unwiderrufliches Bezugsrecht im Erlebensfall");
    public static final Bezeichner UNWIDERRUFLICHES_BEZUGSRECHT_IM_LEISTUNGSFALL = new Bezeichner("Unwiderrufliches Bezugsrecht im Leistungsfall");
    public static final Bezeichner UNWIDERRUFLICHES_BEZUGSRECHT_IM_TODESFALL = new Bezeichner("Unwiderrufliches Bezugsrecht im Todesfall");
    public static final Bezeichner URSPRUENGLICHES_HAFTUNGSBEGINNDAT = new Bezeichner("Urspruengliches Haftungsbeginndatum");

    public static final Bezeichner VEREINBARTER_DYNAMIKMAXIMALANPASSUNGSPROZENTSATZ = new Bezeichner("Vereinbarter Dynamikmaximalanpassungsprozentsatz");
    public static final Bezeichner VEREINBARTER_DYNAMIKMINDESTANPASSUNGSPROZENTSATZ = new Bezeichner("Vereinbarter Dynamikmindestanpassungsprozentsatz");
    public static final Bezeichner VERKUERZTE_BEITRAGSZAHLUNGSDAUER = new Bezeichner("verkuerzte Beitragszahlungsdauer");
    public static final Bezeichner VERMITTLER = new Bezeichner("Geschaeftsstelle/Vermittler");
    public static final Bezeichner VERSICHERTE_ERKRANKUNGEN = new Bezeichner("Versicherte Erkrankungen");
    public static final Bezeichner VERSICHERTES_OBJEKT = new Bezeichner("Versichertes Objekt");
    public static final Bezeichner VERSICHERTE_GEFAHREN = new Bezeichner("Versicherte Gefahren");
    public static final Bezeichner VERSICHERTE_SACHE = new Bezeichner("Versicherte Sache");
    public static final Bezeichner VERSICHERUNGSBEGINN_DES_TARIFES = new Bezeichner("Versicherungsbeginn des Tarifes");
    public static final Bezeichner VERSICHERUNGSBEGINN_URSPRUNGSBEGINN_DER_TARIFART = new Bezeichner("Versicherungsbeginn / Ursprungsbeginn der Tarifart");
    public static final Bezeichner VERSICHERUNGSBEGINN_VP_URSPRUNGSBEGINN = new Bezeichner("Versicherungsbeginn der VP / Ursprungsbeginn");
    public static final Bezeichner VERSICHERUNGSLEISTUNGEN = new Bezeichner("Versicherungsleistungen");
    public static final Bezeichner VERSICHERUNGSSCHEINNUMMER = new Bezeichner("Versicherungsschein-Nummer");
    public static final Bezeichner VERSICHERUNGSSCHEINNUMMER_DER_VORVERSICHERUNG = new Bezeichner("Versicherungsscheinnummer der Vorversicherung");
    public static final Bezeichner VERSICHERUNGSSCHEINNUMMER_VM = new Bezeichner("Versicherungsscheinnummer VM");
    public static final Bezeichner VERSICHERUNGSSCHUTZ = new Bezeichner("Versicherungsschutz");
    public static final Bezeichner VERSICHERUNGSSUMME_AKTUELL_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Versicherungssumme aktuell in W\u00e4hrungseinheiten");
    public static final Bezeichner VERSICHERUNGSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Versicherungssumme in W\u00e4hrungseinheiten");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_9999 = new Bezeichner("Version Satzart 9999 Nachsatz", "Satzart9999", "Nachsatzsatzart9999");
    public static final Bezeichner VERTRAG_MIT_ZUWACHSGARANTIE = new Bezeichner("Vertrag mit Zuwachsgarantie");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0001 = new Bezeichner("Version Satzart 0001");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0100 = new Bezeichner("Version Satzart 0100");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0200 = new Bezeichner("Version Satzart 0200");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0202_ALLG_ANTRAGSDATEN = SATZART_0202_ALLG_ANTRAGSDATEN;
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_050 = new Bezeichner("Version Satzart 0210.050");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_051 = new Bezeichner("Version Satzart 0220.051");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_052 = new Bezeichner("Version Satzart 0220.052");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_053 = new Bezeichner("Version Satzart 0220.053");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_054 = new Bezeichner("Version Satzart 0220.054");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_059 = new Bezeichner("Version Satzart 0220.059");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_040 = new Bezeichner("Version Satzart 0210.040");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_040 = new Bezeichner("Version Satzart 0220.040");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_030 = new Bezeichner("Version Satzart 0210.030");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_030 = new Bezeichner("Version Satzart 0220.030");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_010 = new Bezeichner("Version Satzart 0210.010");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_010 = new Bezeichner("Version Satzart 0220.010");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_130 = new Bezeichner("Version Satzart 0210.130");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_130 = new Bezeichner("Version Satzart 0220.130");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_110 = new Bezeichner("Version Satzart 0210.110");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_110 = new Bezeichner("Version Satzart 0220.110");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_140 = new Bezeichner("Version Satzart 0210.140");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_140 = new Bezeichner("Version Satzart 0220.140");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_020 = new Bezeichner("Version Satzart 0210.020");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_020 = new Bezeichner("Version Satzart 0220.020");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_070 = new Bezeichner("Version Satzart 0210.070");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_070 = new Bezeichner("Version Satzart 0220.070");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_FEUER = new Bezeichner("Version Satzart 0210 Feuer-Industrie/Gewerbliche Sachversicherung");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_FEUER = new Bezeichner("Version Satzart 0220 Feuer-Industrie/Gewerbliche Sachversicherung");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_510 = new Bezeichner("Version Satzart 0210.510");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_510 = new Bezeichner("Version Satzart 0220.510");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_TRANSPORT = new Bezeichner("Version Satzart 0210 Transport");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_TRANSPORT = new Bezeichner("Version Satzart 0220 Transport");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0250_TRANSPORT = new Bezeichner("Version Satzart 0250 Transport Einzelanmeldung");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0260_TRANSPORT = new Bezeichner("Version Satzart 0260 Transport Umsatzanmeldung");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_NICHT_DEF_SPARTEN = new Bezeichner("Version Satzart 0210 Nicht einzeln definierte Sparten");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_TECH_VERS = new Bezeichner("Version Satzart 0210 Technische Versicherungen");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_NICHT_DEF_SPARTEN = new Bezeichner("Version Satzart 0220 Nicht einzeln definierte Sparten");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_TECH_VERS = new Bezeichner("Version Satzart 0220 Technische Versicherungen");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_KFZ_BAUSTEIN = new Bezeichner("Version KFZ-Baustein");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0300_BETEILIGUNGSINFORMATION = new Bezeichner("Version Satzart 0300 Beteiligungsinformation");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0342_BEGLEITDOK = new Bezeichner("Version Satzart 0342 Begleitdokumente und Signaturen");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0400_INKASSO = new Bezeichner("Version Satzart 0400 Inkasso");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0410_INKASSO = new Bezeichner("Version Satzart 0410 Inkasso");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0430_INKASSO = new Bezeichner("Version Satzart 0430 Inkasso");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0500_SCHADENINFORMATION = new Bezeichner("Version Satzart 0500 Schadeninformation");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0420_VERSICHERUNGSTEUERABRECHNUNG = new Bezeichner("Version Satzart 0420 Versicherungsteuerabrechnung");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0450_INKASSOABRECHNUNG = new Bezeichner("Version Satzart 0450 Inkassoabrechnung");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0550_SCHADENABRECHNUNG = new Bezeichner("Version Satzart 0550 Schadenabrechnung");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0350_KLAUSELN = new Bezeichner("Version Satzart 0350 Klausel");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0211_050 = new Bezeichner("Version Satzart 0211.050");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_051 = new Bezeichner("Version Satzart 0221.051");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_052 = new Bezeichner("Version Satzart 0221.052");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_053 = new Bezeichner("Version Satzart 0221.053");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_054 = new Bezeichner("Version Satzart 0221.054");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_055 = new Bezeichner("Version Satzart 0221.055");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_059 = new Bezeichner("Version Satzart 0221.059");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0211_040 = new Bezeichner("Version Satzart 0211.040");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_040 = new Bezeichner("Version Satzart 0221.040");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_030 = new Bezeichner("Version Satzart 0221.030");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0211_010 = new Bezeichner("Version Satzart 0211.010");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_010 = new Bezeichner("Version Satzart 0221.010");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0211_130 = new Bezeichner("Version Satzart 0211.130");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_130 = new Bezeichner("Version Satzart 0221.130");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0211_110 = new Bezeichner("Version Satzart 0211.110");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_110 = new Bezeichner("Version Satzart 0221.110");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0211_140 = new Bezeichner("Version Satzart 0211.140");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_140 = new Bezeichner("Version Satzart 0221.140");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_070 = new Bezeichner("Version Satzart 0221.070");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0211_FEUER = new Bezeichner("Version Satzart 0211 Feuer-Industrie/Gewerbliche Sachversicherung");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_FEUER = new Bezeichner("Version Satzart 0221 Feuer-Industrie/Gewerbliche Sachversicherung");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_510 = new Bezeichner("Version Satzart 0221.510");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0211_TRANSPORT = new Bezeichner("Version Satzart 0211 Transport");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_TRANSPORT = new Bezeichner("Version Satzart 0221 Transport");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0222 = new Bezeichner("Version Satzart 0222");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0230_LEBEN = new Bezeichner("Version Satzart 0230 Fondsdatensatz - Leben");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0230_UNFALL = new Bezeichner("Version Satzart 0230 Unfall Leistungsarten");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0251_TRANSPORT = new Bezeichner("Version Satzart 0251 Transport Einzelanmeldung");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0211_NICHT_DEF_SPARTEN = new Bezeichner("Version Satzart 0211 Nicht einzeln definierte Sparten");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0211_TECH_VERS = new Bezeichner("Version Satzart 0211 Technische Versicherungen");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_NICHT_DEF_SPARTEN = new Bezeichner("Version Satzart 0221 Nicht einzeln definierte Sparten");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0221_TECH_VERS = new Bezeichner("Version Satzart 0221 Technische Versicherungen");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_550 = new Bezeichner("Version Satzart 0210.550");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_560 = new Bezeichner("Version Satzart 0210.560");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_570 = new Bezeichner("Version Satzart 0210.570");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_550 = new Bezeichner("Version Satzart 0220.550");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_560 = new Bezeichner("Version Satzart 0220.560");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_570 = new Bezeichner("Version Satzart 0220.570");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0270_550 = new Bezeichner("Version Satzart 0270.550");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0280_550 = new Bezeichner("Version Satzart 0280.550");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0291_550 = new Bezeichner("Version Satzart 0291.550");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0292_550 = new Bezeichner("Version Satzart 0292.550");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0293_550 = new Bezeichner("Version Satzart 0293.550");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0294_550 = new Bezeichner("Version Satzart 0294.550");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0295_550 = new Bezeichner("Version Satzart 0295.550");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0052 = new Bezeichner("Version Satzart 0052");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0102 = new Bezeichner("Version Satzart 0102");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0212 = new Bezeichner("Version Satzart 0212");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0352 = new Bezeichner("Version Satzart 0352");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0362 = new Bezeichner("Version Satzart 0362");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0382 = new Bezeichner("Version Satzart 0382");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0390_RABATTE = new Bezeichner("Version Satzart 0390 Rabatte und Zuschlaege");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0392_EVB = new Bezeichner("Version Satzart 0392 eVB-Nummer");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_9950 = new Bezeichner("Version Satzart 9950");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_9951_MIME = new Bezeichner("Version Satzart 9951 MIME-Datei");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_9952 = new Bezeichner("Version Satzart 9952");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_580 = new Bezeichner("Version Satzart 0210.580");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0210_684 = new Bezeichner("Version Satzart 0210.684");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_580 = new Bezeichner("Version Satzart 0220.580");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0220_684 = new Bezeichner("Version Satzart 0220.684");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0372 = new Bezeichner("Version Satzart 0372");
    /** @deprecated bitte ohne Prefix "VERSION_" verwenden (TODO: wird mit v8 entsorgt) */
    public static final Bezeichner VERSION_SATZART_0600 = new Bezeichner("Version Satzart 0600");
    public static final Bezeichner VERTRAGSABLAUF = new Bezeichner("Vertragsablauf");
    public static final Bezeichner VERTRAGSART = new Bezeichner("Vertragsart");
    public static final Bezeichner VERTRAGSBEGINN = new Bezeichner("Vertragsbeginn");
    public static final Bezeichner VERTRAGSDAUER = new Bezeichner("Vertragsdauer");
    public static final Bezeichner VERTRAGSBEDINGUNG = new Bezeichner("Vertragsbedingung");
    public static final Bezeichner VERTRAGSFORM = new Bezeichner("Vertragsform");
    public static final Bezeichner VERTRAGSLAUFZEIT = new Bezeichner("Vertragslaufzeit");
    public static final Bezeichner VERTRAGSSTATUS = new Bezeichner("Vertragsstatus");
    public static final Bezeichner VERTRAGSVERBINDUNGSNUMMER = new Bezeichner("Vertragsverbindungsnummer");
    public static final Bezeichner VERWENDUNGSZWECK = new Bezeichner("Verwendungszweck");
    public static final Bezeichner VORVERSICHERUNG_BEI_VU = new Bezeichner("Vorversicherung bei VU");
    public static final Bezeichner VORAUSSICHTLICHES_ENDE = new Bezeichner("voraussichtliches Ende");
    public static final Bezeichner VORLAUFSSUMME_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Vorlaufsumme in W\u00e4hrungseinheiten");
    public static final Bezeichner VORNAME_DER_VERSICHERTEN_PERSON_VP = new Bezeichner("Vorname der versicherten Person (VP)");
    public static final Bezeichner VORNAME_VP = new Bezeichner("Vorname der VP");
    public static final Bezeichner VORSTEUERABZUGSBERECHTIGT = new Bezeichner("Vorsteuerabzugsberechtigt");
    public static final Bezeichner VORZEICHEN = new Bezeichner("Vorzeichen", "Vorzeichen", "Vorzeichen1");
    public static final Bezeichner VORZEICHEN2 = new Bezeichner("Vorzeichen2");
    public static final Bezeichner VORZEICHEN3 = new Bezeichner("Vorzeichen3");
    public static final Bezeichner VORZEICHEN4 = new Bezeichner("Vorzeichen4");
    public static final Bezeichner VORZEICHEN5 = new Bezeichner("Vorzeichen5");
    public static final Bezeichner VORZUGSSEUERBERECHTIGUNG_PROZENT = new Bezeichner("Vorsteuerabszugsberechtigung in Prozent");
    public static final Bezeichner VORZUGSSTEUERBERECHTIGUNG = new Bezeichner("Vorsteuerabszugsberechtigung Ja/Nein");
    public static final Bezeichner VP_PERSONENNUMMER_VERMITTLER = new Bezeichner("VP-Personennummer des Vermittlers");
    public static final Bezeichner VP_PERSONENNUMMER_VERSICHERER = new Bezeichner("VP-Personennummer des Versicherers");
    public static final Bezeichner VS_NR = VERSICHERUNGSSCHEINNUMMER;
    public static final Bezeichner VU_ABRECHNUNGSSTELLE = new Bezeichner("VU-Abrechnungsstelle");
    public static final Bezeichner VU_NR = new Bezeichner("VU-Nummer");
    public static final Bezeichner VU_NUMMER = VU_NR;

    public static final Bezeichner WAEHRUNG_DOKUMENTE_FUER_VN = new Bezeichner("Waehrung der Dokumente fuer VN");
    public static final Bezeichner WAEHRUNGSSCHLUESSEL1 = new Bezeichner("Waehrungsschl\u00fcssel 1");
    public static final Bezeichner WAEHRUNGSSCHLUESSEL2 = new Bezeichner("Waehrungsschl\u00fcssel 2");
    public static final Bezeichner WAEHRUNGSSCHLUESSEL3 = new Bezeichner("Waehrungsschl\u00fcssel 3");
    public static final Bezeichner WAEHRUNGSSCHLUESSEL4 = new Bezeichner("Waehrungsschl\u00fcssel 4");
    /** @deprecated bitte Konstanten ohne '_' benutzen (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner WAEHRUNGSSCHLUESSEL_1 = WAEHRUNGSSCHLUESSEL1;
    /** @deprecated bitte Konstanten ohne '_' benutzen (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner WAEHRUNGSSCHLUESSEL_2 = WAEHRUNGSSCHLUESSEL2;
    /** @deprecated bitte Konstanten ohne '_' benutzen (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner WAEHRUNGSSCHLUESSEL_3 = WAEHRUNGSSCHLUESSEL3;
    /** @deprecated bitte Konstanten ohne '_' benutzen (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner WAEHRUNGSSCHLUESSEL_4 = WAEHRUNGSSCHLUESSEL4;
    public static final Bezeichner WAGNIS = new Bezeichner("Wagnis");
    /** @deprecated bitte {@link #WAGNIS} benutzen (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner WAGNIS2 = WAGNIS;
    public static final Bezeichner WAGNISART = new Bezeichner("Wagnisart");
    /** @deprecated bitte {@link #WAGNISART} benutzen (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner WAGNISART2 = WAGNISART;
    /** @deprecated bitte {@link #WAGNISART} benutzen (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner WAGNISART3 = WAGNISART;
    /** @deprecated bitte {@link #WAGNISART} benutzen (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner WAGNISART4 = WAGNISART;
    public static final Bezeichner WAGNISBESCHREIBUNG = new Bezeichner("Wagnisbeschreibung");
    public static final Bezeichner WAGNISKENNZIFFER = new Bezeichner("Wagniskennziffer");
    public static final Bezeichner WAGNISMENGE = new Bezeichner("Wagnismenge");
    public static final Bezeichner WAGNISTEXT = new Bezeichner("Wagnistext");
    public static final Bezeichner WAISENRENTE_PROZENT = new Bezeichner("Waisenrente %");
    /** @deprecated bitte WAISENRENTE_PROZENT verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner WAISENRENTE_IN_PROZENT = WAISENRENTE_PROZENT;

    public static final Bezeichner WARTEZEIT = new Bezeichner("Wartezeit");
    public static final Bezeichner WARTUNGSVERTRAG = new Bezeichner("Wartungsvertrag");
    public static final Bezeichner WECHSELKENNZEICHEN_W_AKZ = new Bezeichner("Wechselkennzeichen W-AKZ");
    public static final Bezeichner WEITERE_REFERENZNUMMERN = new Bezeichner("Weitere Referenznummern");
    public static final Bezeichner WERT_1914_PRO_QM_WOHNFLAECHE_IN_MARK = new Bezeichner("Wert 1914 pro qm Wohnflaeche in Mark");
    public static final Bezeichner WERTERMITTLUNGS_SCHAETZJAHR = new Bezeichner("Wertermittlungs-/Schaetzjahr");
    public static final Bezeichner WERT_ZUSCHREIBUNG_VORSORGEZUSCHLAG = new Bezeichner("Wert Zuschreibung Vorsorgezuschlag");
    public static final Bezeichner WERTPAPIERKENNNUMMER = new Bezeichner("Wertpapierkennnummer");
    public static final Bezeichner WERTUNGSBASIS = new Bezeichner("Wertungsbasis");
    public static final Bezeichner WERTUNGSBASIS2 = new Bezeichner("Wertungsbasis2");
    public static final Bezeichner WERTUNGSMODELL = new Bezeichner("Wertungsmodell");
    public static final Bezeichner WERTUNGSMODELL2 = new Bezeichner("Wertungsmodell2");
    public static final Bezeichner WIDERSPRUCH_VORSORGEZUSCHLAG = new Bezeichner("Widerspruch Vorsorgezuschlag");
    public static final Bezeichner WITWEN_WITWERRENTE_PROZENT = new Bezeichner("Witwen- / Witwerrente %");
    /** @deprecated bitte WITWEN_WITWERRENTE_PROZENT verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner WITWEN_WITWERRENTE_IN_PROZENT = WITWEN_WITWERRENTE_PROZENT;

    public static final Bezeichner WOHNEIGENTUM = new Bezeichner("Wohneigentum");
    public static final Bezeichner WOHNFLAECHE_QM = new Bezeichner("Wohnflaeche qm");

    public static final Bezeichner ZAHLUNG_DER_WITWEN_WITWERRENTE_BIS = new Bezeichner("Zahlung der Witwen- / Witwerrente bis");
    public static final Bezeichner ZAHLUNGSANFANG = new Bezeichner("Zahlungsanfang");
    public static final Bezeichner ZAHLUNGSART = new Bezeichner("Zahlungsart /-weg");
    public static final Bezeichner ZAHLUNGSWEISE = new Bezeichner("Zahlungsweise");
    public static final Bezeichner ZAHLUNGSWEISE_KUENFTIGER_GESAMTBEITRAG = new Bezeichner("Zahlungsweise des zukuenftigen Gesamtbeitrags");
    /** @deprecated bitte ZAHLUNGSWEISE_KUENFTIGER_GESAMTBEITRAG verwenden (TODO: wird mit v8 entfernt). */
    @Deprecated
    public static final Bezeichner ZAHLUNGSWEISE_KUENFTIGER_GESAMTBETRAG = ZAHLUNGSWEISE_KUENFTIGER_GESAMTBEITRAG;
    public static final Bezeichner ZIELGRUPPENSCHLUESSEL = new Bezeichner("Zielgruppenschl\u00fcssel");
    public static final Bezeichner ZUKUENFTIGER_BEITRAG = new Bezeichner("Zukuenftiger Beitrag");
    public static final Bezeichner ZUKUENFTIGER_BEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Zukuenftiger Beitrag in W\u00e4hrungseinheiten");
    public static final Bezeichner ZUKUENFTIGER_GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Zukuenftiger Gesamtbeitrag in W\u00e4hrungseinheiten");
    public static final Bezeichner ZUSAETZLICHE_SATZKENNUNG = new Bezeichner("zusaetzliche Satzkennung");
    public static final Bezeichner ZUSAETZLICHE_TEXTE = new Bezeichner("zusaetzliche Texte");
    public static final Bezeichner ZUSATZVERSICHERUNG_ZUR_TIERKRANKEN = new Bezeichner("Zusatzversicherung zur Tierkranken");
    public static final Bezeichner ZUSCHLAG1_IN_PROZENT = new Bezeichner("Zuschlag-1 in %");
    public static final Bezeichner ZUSCHLAG1_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Zuschlag-1 in W\u00e4hrungseinheiten");
    public static final Bezeichner ZUSCHLAG2_IN_PROZENT = new Bezeichner("Zuschlag-2 in %");
    public static final Bezeichner ZUSCHLAG2_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Zuschlag-2 in W\u00e4hrungseinheiten");
    public static final Bezeichner ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Zuschlagsbetrag in W\u00e4hrungseinheiten");
    public static final Bezeichner ZUSCHLAG_IN_PROZENT = new Bezeichner("Zuschlag in %");
    public static final Bezeichner ZUSCHLAG_IN_WAEHRUNGSEINHEITEN = new Bezeichner("Zuschlag in W\u00e4hrungseinheiten");
    public static final Bezeichner ZUZAHLUNGSBETRAG_IN_WE = new Bezeichner("Zuzahlungsbetrag in W\u00e4hrungseinheiten");
    public static final Bezeichner ZUZAHLUNGSDAT = new Bezeichner("Zuzahlungsdatum");
    public static final Bezeichner ZUZAHLUNGSRECHT = new Bezeichner("Zuzahlungsrecht");
    public static final Bezeichner ZWANG_ZUR_BUZ = new Bezeichner("Zwang zur BUZ");

    private final String name;
    private final String technischerName;
    private final int hash;
    private final Set<Bezeichner> variants = new HashSet<>();

    // Mapping fuer manche Bezeichner (Name <--> technischer Name)
    static {
        MAPPING.put(AUSSCHLUSSDAT_VP_PERSONENGRUPPE.getName(), AUSSCHLUSSDAT_VP_PERSONENGRUPPE.getTechnischerName());
        MAPPING.put(BEGINNDAT_NAECHSTEN_ERLEBENSFALL_VS.getName(), BEGINNDAT_NAECHSTEN_ERLEBENSFALL_VS.getTechnischerName());
        MAPPING.put(BEGINNDAT_NAECHSTEN_JAHRESRENTE.getName(), BEGINNDAT_NAECHSTEN_JAHRESRENTE.getTechnischerName());
        MAPPING.put(BEGINNDAT_DER_NAECHSTEN_UNFALLSUMME.getName(), BEGINNDAT_DER_NAECHSTEN_UNFALLSUMME.getTechnischerName());
        MAPPING.put(DAT_BEZUGSFERTIGKEIT.getName(), DAT_BEZUGSFERTIGKEIT.getTechnischerName());
        MAPPING.put(DAT_LETZTEN_BEITRAGSANGLEICHUNG.getName(), DAT_LETZTEN_BEITRAGSANGLEICHUNG.getTechnischerName());
        MAPPING.put(DAT_LETZTEN_POSITIVEN_DYNAMIK.getName(), DAT_LETZTEN_POSITIVEN_DYNAMIK.getTechnischerName());
        MAPPING.put(ENDEDATUM_BEI_ROTEN_KENNZEICHEN.getName(), ENDEDATUM_BEI_ROTEN_KENNZEICHEN.getTechnischerName());
        MAPPING.put(ERSTELLUNGS_DAT_ZEITRAUM_VOM_ZEITRAUM_BIS.getName(),
                ERSTELLUNGS_DAT_ZEITRAUM_VOM_ZEITRAUM_BIS.getTechnischerName());
        MAPPING.put(LFD_PERSONENNR_GEVO.getName(), LFD_PERSONENNR_GEVO.getTechnischerName());
        MAPPING.put(URSPRUENGLICHES_HAFTUNGSBEGINNDAT.getName(), URSPRUENGLICHES_HAFTUNGSBEGINNDAT.getTechnischerName());
        MAPPING.put(VS_NR.getName(), VS_NR.getTechnischerName());
        MAPPING.put(VU_NR.getName(), VU_NR.getTechnischerName());
        findConstants();
    }

    private static void findConstants() {
        Field[] fields = Bezeichner.class.getFields();
        for (Field field : fields) {
            try {
                Object value = field.get(null);
                if (value instanceof Bezeichner) {
                    Bezeichner bez = (Bezeichner) value;
                    CONSTANTS.add(bez);
                }
            } catch (IllegalAccessException e) {
                LOG.debug("Will ignore field {}:", field, e);
            }
        }
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

    /**
     * Legt einen neuen Bezeichner mit dem gewuenschten Name an.
     *
     * @param name            der gewuenschte Name
     * @param technischerName der entsprechende technische Name
     * @param varianten       moegliche Varianten des technischen Namens
     * @since 5.0
     */
    public Bezeichner(final String name, final String technischerName, final String... varianten) {
        this(name, technischerName);
        for (String s : varianten) {
            this.variants.add(new Bezeichner(name, s));
        }
    }

    private static String toTechnischerName(final String input) {
        String techName = MAPPING.get(input);
        if (techName != null) {
            return techName;
        }
        StringBuilder buf = new StringBuilder();
        String[] words = input.split("[\\s\\.,;=?/\\-\u00a7]");
        for (String word : words) {
            buf.append(toShortcut(word));
        }
        return buf.toString();
    }

    private static String toShortcut(final String input) {
        StringBuilder converted = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char ch : chars) {
            appendLetterOrDigitOrProzent(converted, ch);
        }
        String word = converted.toString();
        switch (word) {
            case "einer":
            case "fuer":
            case "und":
                return "";
            case "Anzahl":
                return "Anz";
            case "AHB":
                return "Ahb";
            case "AKB":
                return "Akb";
            case "ARB":
                return "Arb";
            case "BerVersV":
                return "Berversv";
            case "BRE":
                return "Bre";
            case "BUZ":
                return "Buz";
            case "EG":
                return "Eg";
            case "Einschlussdatum":
                return "Einschlussdat";
            case "eVB":
                return word;
            case "Heilkosten":
                return "Heilkost";
            case "III":
                return "Iii";
            case "ISIN":
                return "Isin";
            case "KAG":
                return "Kag";
            case "Kurkosten":
                return "Kurkost";
            case "LEV":
                return "Lev";
            case "Laenderkennzeichen":
                return "LKZ";
            case "Postleitzahl":
                return "PLZ";
            case "Rueckfuehrungskosten":
                return "Rueckfuehrungskost";
            case "RGJ":
                return "Rgj";
            case "Schadenbearbeitungskosten":
                return "Schadenbearbeitungskost";
            case "SF":
                return "Sf";
            case "Sonstige":
                return "Sonst";
            case "Unternehmens":
                return "U";
            case "Versicherungsscheinnummer":
                return "VsNr";
            case "VP":
                return "Vp";
            case "VS":
                return "Vs";
            case "VU":
                return "Vu";
            case "Wagniskennziffer":
                return "WKZ";
            case "Waehrungseinheiten":
                return "WE";
            case "Ueberfuehrungskosten":
                return "Ueberfuehrungskost";
            case "KFT":
                return "Kft";
            case "KFV":
                return "Kfv";
            case "KH":
                return "Kh";
            default:
                if ((word.length() == 3) && (word.toLowerCase().charAt(0) == 'd')) {
                    return "";
                }
                if (word.length() > 5) {
                    word = StringUtils.replaceIgnoreCase(word, "versicherungsschein", "Vs");
                    word = StringUtils.replaceIgnoreCase(word, "versicherungssumme", "Versssumme");
                    word = StringUtils.replaceIgnoreCase(word, "versicherung", "Vers");
                    word = StringUtils.replaceIgnoreCase(word, "nummer", "Nr");
                    word = StringUtils.replaceIgnoreCase(word, "gesamt", "Ges");
                    word = StringUtils.replaceIgnoreCase(word, "Strasse", "Str");
                }
                return WordUtils.capitalize(word);
        }
    }

    private static void appendLetterOrDigitOrProzent(StringBuilder converted, char aChar) {
        if (Character.isLetterOrDigit(aChar)) {
            String s = UmlautMapper.replaceUmlaut(aChar);
            converted.append(s);
        } else if (aChar == '%') {
            converted.append("Proz");
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
     * Zum Vergleich zweier {@link Bezeichner} wird der technische Name
     * herangezogen.
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
     * Mit dieser Methode lasesst sich feststellen, ob der uebergebene
     * Bezeichner einer Variante ist.
     *
     * @param other
     * @return true fuer SATZ_NR_2.isVariantOf(SATZNUMMER)
     * @since 6.6
     */
    public boolean isVariantOf(final Bezeichner other) {
        for (Bezeichner v : other.variants) {
            if (equals(v)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Manche Bezeichner wie "HaftungswertungssummeInWE" koennen eventuell
     * auch als Variante wie "HaftungswertungssummeInWE1" (also mit
     * angehaengter "1") auftreten. Mit dieser Methode kann man sich die
     * verschiedenen Varianten eines Bezeichners geben lassen.
     *
     * @return Liste von Varianten
     * @since 5.0
     */
    @JsonIgnore
    public Set<Bezeichner> getVariants() {
        Set<Bezeichner> vars = new HashSet<>(variants);
        vars.add(this);
        if (getTechnischerName().startsWith("VersionSatzart")) {
            vars.add(Bezeichner.of(getTechnischerName().substring(7).trim()));
        } else if (getName().startsWith("Satzart")) {
            vars.add(Bezeichner.of("Version " + name));
        }
//        if (getName().startsWith("Satzart")) {
//            vars.add(Bezeichner.of("Version " + name));
//        } else if (getName().startsWith("Version")) {
//            vars.add(Bezeichner.of(getName().substring(7).trim()));
//        }
        return vars;
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
        return this.getTechnischerName();
    }
    
    /**
     * Liefert zum angegebenen Namen den entsprechenden Bezeichner, falls es
     * ihn als Konstante gibt. Falls nicht, wird er ganz normal ueber den
     * Konstruktor erzeugt.
     *
     * @param name Bezeichner-Name
     * @return Bezeichner-Konstante oder neuen Bezeichner
     * @since 3.1
     */
    public static Bezeichner of(String name) {
        Bezeichner b = CACHED.get(name);
        if (b == null) {
            b = getBezeichner(name);
            CACHED.put(name, b);
        }
        return b;
    }

    private static Bezeichner getBezeichner(String name) {
        for (Bezeichner bez : CONSTANTS) {
            if (name.equalsIgnoreCase(bez.getTechnischerName())) {
                return bez;
            }
        }
        for (Bezeichner bez : CONSTANTS) {
            if (name.equalsIgnoreCase(bez.getName())) {
                return bez;
            }
        }
        if (name.endsWith("000")) {
            LOG.debug("Will look for '{}' without trailing '000'.", name);
            return of(name.substring(0, name.length() - 3));
        }
        LOG.debug("Will generate new Bezeichner '{}'.", name);
        return new Bezeichner(name);
    }

}
