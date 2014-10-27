/*
 * Copyright (c) 2009 - 2012 by Oli B.
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
 * (c)reated 28.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;
import gdv.xport.feld.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Klasse fuer die Satzart 0210.
 * <p>
 * Bitte diese Klasse nicht mehr benutzen. Sie wird mit Version 1.0
 * entsorgt werden.
 * </p>
 *
 * @author oliver (ob@aosd.de)
 * @since 0.1.0 (28.10.2009)
 * @deprecated seit 0.6 durch {@link gdv.xport.satz.model.Satz210} ersetzt
 */
@Deprecated
public class VertragsspezifischerTeil extends Spartensatz {

    private static final Logger LOG = LogManager.getLogger(VertragsspezifischerTeil.class);
    private static final int SATZART = 210;

    /**
     * Default-Constructor.
     */
    public VertragsspezifischerTeil() {
        super(SATZART);
    }

    /**
     * @param sparte
     *            z.B. 70 (Rechtsschutz)
     */
    public VertragsspezifischerTeil(final int sparte) {
        this(sparte, getNumberOfTeildatensaetzeFor(sparte));
    }

    /**
     * @param sparte
     *            z.B. 70 (Rechtsschutz)
     * @param n
     *            Anzahl Teildatensaetze
     */
    public VertragsspezifischerTeil(final int sparte, final int n) {
        super(SATZART, sparte, n);
        this.setUpDatenfelder(sparte);
    }

    static int getNumberOfTeildatensaetzeFor(final int sparte) {
        switch (sparte) {
            case 30:
            case 40:
            case 70:
            case 80:
            case 110:
            case 140:
            case 190:
            case 510:
            case 550:
                return 1;
            case 0:
            case 10:
            case 20:
            case 50:
            case 130:
            case 170:
            case 580:
                return 2;
            default:
                LOG.warn("unknown Sparte " + sparte + " -> mapped to 1 Teildatensatz");
                return 1;
        }
    }

    /**
     * Legt die entsprechende Anzahl von Teildatensaetze fuer die angegebene Sparte an.
     *
     * @param x
     *            Sparte (z.B. 30)
     */
    @Override
    protected void createTeildatensaetzeFor(final int x) {
        this.createTeildatensaetze(getNumberOfTeildatensaetzeFor(x));
    }

    /**
     * Initialisiert die Teildatensaetze fuer die angegebene Sparte.
     *
     * @param sparte
     *            Sparte (z.B. 30)
     */
    @Override
    protected void setUpDatenfelder(final int sparte) {
        switch (sparte) {
            case 10:
                this.setUpDatenfelder10();
                break;
            case 30:
                this.setUpDatenfelder30();
                break;
            case 50:
                this.setUpDatenfelder50();
                break;
            case 70:
                this.setUpDatenfelder70();
                break;
            default:
                LOG.warn("Sparte " + sparte + " not yet fully supported");
                this.setUpTeildatensaetze();
                this.addFiller();
                break;
        }
    }

    private void setUpDatenfelder10() {
        this.setUpTeildatensatz10(1, this.getTeildatensatz(1));
        this.setUpTeildatensatz10(2, this.getTeildatensatz(2));
    }

    private void setUpTeildatensatz10(final int n, final Teildatensatz tds) {
        this.setUpTeildatensatz(tds);
        switch (n) {
            case 1: // Teildatensatz 1
                tds.add(new Zeichen(KENNZEICHEN_VERTRAGSENTSTEHUNG, 43));
                tds.add(new AlphaNumFeld(WAEHRUNGSSCHLUESSEL, 3, 44));
                tds.add(new Zeichen(BEITRAGSDEPOT, 47));
                tds.add(new Zeichen(RISIKOVERLAUF, 48));
                tds.add(new Betrag(VORLAUFSSUMME_IN_WAEHRUNGSEINHEITEN, 12, 49));
                tds.add(new NumFeld(VERKUERZTE_BEITRAGSZAHLUNGSDAUER, 2, 61));
                tds.add(new Zeichen(BESONDERER_VERWENDUNGSZWECK, 63));
                tds.add(new NumFeld(VERTRAGSFORM, 1, 64));
                tds.add(new AlphaNumFeld(GRUPPENART, 40, 65));
                tds.add(new AlphaNumFeld(NAME_MITVERSICHERTES_KIND, 20, 105));
                tds.add(new NumFeld(ABSCHLUSSPROVISION, 5, 125));
                tds.add(new Zeichen(KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION, 130));
                tds.add(new NumFeld(BESTANDSPFLEGEPROVISION, 5, 131));
                tds.add(new Zeichen(KENNZEICHEN_ABWEICHENDE_BESTANDSPFLEGEPROVISION, 136));
                tds.add(new AlphaNumFeld(ART_DES_DRITTRECHTS, 2, 137));
                tds.add(new Betrag(AKTUELLE_BEITRAGSDEPOTSUMME_IN_WAEHRUNGSEINHEITEN, 12, 139).mitNachkommastellen(2));
                tds.add(new NumFeld(ZAHLUNGSANFANG, 8, 151));
                tds.add(new Zeichen(ZUZAHLUNGSRECHT, 159));
                tds.add(new Betrag(ZUZAHLUNGSBETRAG_IN_WAEHRUNGSEINHEITEN, 12, 160).mitNachkommastellen(2));
                tds.add(new Datum(ZUZAHLUNGSDATUM, 8, 172));
                tds.add(new Betrag(ZUKUENFTIGER_GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN, 12, 160).mitNachkommastellen(2));
                tds.add(new Betrag(ZAHLUNGSWEISE_KUENFTIGER_GESAMTBETRAG, 12, 180).mitNachkommastellen(2));
                tds.add(new Datum(BEITRAGSUMSTELLUNGSDATUM, 8, 193));
                tds.add(new AlphaNumFeld(BEITRAGSUMSTELLUNGSGRUND, 2, 201));
                tds.add(new AlphaNumFeld(REFERENZ_VERSICHERUNGSSCHEINNUMMER_1, 17, 203));
                tds.add(new AlphaNumFeld(REFERENZ_VERSICHERUNGSSCHEINNUMMER_2, 17, 220));
                tds.add(new AlphaNumFeld(REFERENZ_VERSICHERUNGSSCHEINNUMMER_3, 17, 237));
                tds.add(new Zeichen(WEITERE_REFERENZNUMMERN, 254));
                tds.add(new AlphaNumFeld(LEERSTELLEN, 1, 255));
                break;
            case 2: // Teildatensatz 2
                tds.add(new Zeichen(BETRIEBLICHE_ALTERSVORSORGE, 43));
                tds.add(new Zeichen(UNVERFALLBARKEIT, 44));
                tds.add(new Datum(DIENSTEINTRITTSDATUM, 8, 45));
                tds.add(new NumFeld(BILANZMONAT_ARBEITGEBER, 2, 53));
                tds.add(new AlphaNumFeld(PRODUKTBESCHREIBUNG, 30, 55));
                tds.add(new Zeichen(KAPITALERTRAGSTEUERPFLICHT, 85));
                tds.add(new AlphaNumFeld(PRODUKTFORM, 5, 86));
                tds.add(new Datum(PRODUKTFORM_GUELTIG_AB, 6, 91));
                tds.add(new AlphaNumFeld(PRODUKTNAME, 20, 97));
                // Betriebliche und private Altersversorgung
                tds.add(new Zeichen(ART_DER_STEUERLICHEN_FOERDERUNG, 117));
                tds.add(new Zeichen(FINANZIERUNGSART, 118));
                tds.add(new Zeichen(DURCHFUEHRUNGSWEG, 119));
                tds.add(new Zeichen(FINANZIERUNG_ZUSAGE, 120));
                tds.add(new AlphaNumFeld(UNTERSTUETZUNGSKASSE_SCHLUESSEL, 4, 121));
                tds.add(new AlphaNumFeld(UNTERSTUETZUNGSKASSE_NAME, 40, 125));
                tds.add(new AlphaNumFeld(TRAEGERUNTERNEHMEN_SCHLUESSEL, 4, 165));
                tds.add(new AlphaNumFeld(TRAEGERUNTERNEHMEN_NAME, 40, 169));
                tds.add(new AlphaNumFeld(KOLLEKTIV_NR, 15, 209));
                tds.add(new AlphaNumFeld(LEERSTELLEN, 32, 224));
                break;
            default:
                throw new IllegalArgumentException("unbekannte Teildatensatz-Nr.: " + n);
        }
    }

    private void setUpDatenfelder30() {
        this.setUpTeildatensatz(1);
        // Teildatensatz 1
        add(new Zeichen(VERTRAGSSTATUS, 43));
        add(new Datum(BEGINN, 44));
        add(new NumFeld(AUSSCHLUSS, 8, 52));
        add(new Datum(AENDERUNGSDATUM, 60));
        add(new Datum(ALLGEMEINE_VERSICHERUNGSBEDINGUNGEN, 4, 68));
        add(new Zeichen(SONDERBEDINGUNGEN, 72));
        add(new Zeichen(BEITRAGSRUECKGEWAEHR, 73));
        add(new Zeichen(DYNAMIK, 74));
        add(new NumFeld(DYNAMIK_IN_PROZENT, 5, 75).mitNachkommastellen(3));
        add(new Datum(LETZTE_ERHOEHUNG, 6, 80));
        add(new Datum(NAECHSTE_ERHOEHUNG, 6, 86));
        add(new Zeichen(BEITRAGSREGULIERUNG, 92));
        add(new AlphaNumFeld(WAEHRUNGSSCHLUESSEL, 3, 93));
        add(new Betrag(ZUSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN, 12, 96));
        add(new Betrag(ABSCHLAGSBETRAG_IN_WAEHRUNGSEINHEITEN, 12, 108));
        add(new Betrag(GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN, 12, 120));
        add(new NumFeld(ABSCHLUSSPROVISION, 5, 132).mitNachkommastellen(2));
        add(new NumFeld(FOLGEPROVISION, 5, 137).mitNachkommastellen(2));
        add(new Zeichen(KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION, 142));
        add(new Zeichen(KENNZEICHEN_ABWEICHENDE_FOLGEPROVISION, 143));
        add(new NumFeld(RESTLAUFZEIT_VERTRAG, 2, 144));
        add(new NumFeld(LAUFZEITRABATT_IN_PROZENT, 4, 146).mitNachkommastellen(2));
        add(new AlphaNumFeld(TARIFBEZEICHNUNG, 30, 150));
        add(new Zeichen(ERHOEHUNGSART_DYNAMIK, 180));
        add(new AlphaNumFeld(REFERENZ_VERSICHERUNGSSCHEINNUMMER, 17, 181));
        add(new Zeichen(WEITERE_REFERENZNUMMERN, 198));
        add(new AlphaNumFeld(PRODUKTFORM, 5, 199));
        add(new Datum(PRODUKTFORM_GUELTIG_AB, 6, 204));
        add(new AlphaNumFeld(PRODUKTNAME, 20, 210));
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 230));
    }

    /**
     * Sparte 50 (KFZ - Fahrzeugdaten) wurde freundlicherweise von Igor Narodetskyi zur Verfuegung gestellt.
     *
     * @since 0.5.1
     */
    private void setUpDatenfelder50() {
        // Teildatensatz 1
        add(new NumFeld(WAGNISKENNZIFFER, 3, 43));
        add(new NumFeld(STAERKE, 4, 46).mitNachkommastellen(1));
        add(new AlphaNumFeld(HERSTELLERNAME, 15, 50));
        add(new AlphaNumFeld(MODELLNAME, 25, 65));
        add(new AlphaNumFeld(HERSTELLER_SCHLUESSEL_NR, 4, 90));
        add(new AlphaNumFeld(TYPSCHLUESSEL_NR, 3, 94));
        add(new AlphaNumFeld(FAHRZEUGIDENTIFIZIERUNGSNUMMER, 17, 97));
        add(new AlphaNumFeld(AMTL_KENNZEICHEN, 12, 114));
        add(new Datum(ERSTZULASSUNG, 8, 126));
        add(new NumFeld(NEUPREIS_IN_WAEHRUNGSEINHEITEN, 7, 134));
        add(new NumFeld(MEHRWERT_IN_WAEHRUNGSEINHEITEN, 6, 141));
        add(new AlphaNumFeld(KENNUNG_FUER_ABS_RABATT, 1, 147));
        add(new AlphaNumFeld(FLOTTENKENNZEICHEN, 1, 148));
        add(new AlphaNumFeld(WAEHRUNGSSCHLUESSEL, 3, 149));
        add(new Betrag(GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN, 12, 152));
        add(new AlphaNumFeld(SICHERUNGSSCHEIN, 1, 164));
        add(new AlphaNumFeld(SONDERBEDINGUNGEN_KLAUSELN, 1, 165));
        add(new AlphaNumFeld(SICHERUNGSEINRICHTUNG, 1, 166));
        add(new AlphaNumFeld(KLARTEXT_SICHERUNGSEINRICHTUNG, 30, 167));
        add(new AlphaNumFeld(SCHLUESSEL_SICHERUNGSEINRICHTUNG, 3, 197));
        add(new Betrag(BAUSTEIN_GESAMTBEITRAG_1_IN_WAEHRUNGSEINHEITEN, 12, 200));
        add(new Betrag(BAUSTEIN_GESAMTBEITRAG_2_IN_WAEHRUNGSEINHEITEN, 12, 212));
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 224));
        add(new AlphaNumFeld(LFD_NUMMER, 4, 231));
        add(new AlphaNumFeld(PERSONEN_KUNDENNUMMER_DES_VERSICHERERS, 17, 235));
        add(new AlphaNumFeld(SAISONKENNZEICHEN, 4, 252));
        // Teildatensatz 2
        add(new AlphaNumFeld(PRODUKTFORM, 5, 43), 2);
        add(new NumFeld(PRODUKTFORM_GUELTIG_AB, 6, 48), 2);
        add(new AlphaNumFeld(FAHRZEUGART, 30, 54), 2);
        add(new AlphaNumFeld(ART_DES_AMTLICHEN_KENNZEICHENS, 2, 84), 2);
        add(new AlphaNumFeld(LAND_DES_AMTL_KENNZEICHENS, 3, 86), 2);
        add(new NumFeld(BAUJAHR, 8, 89), 2);
        add(new Datum(ERSTE_ZULASSUNG_AUF_DEN_VN, 8, 97), 2);
        add(new AlphaNumFeld(ART_DER_ZULASSUNG_BEIM_VORBESITZER, 2, 105), 2);
        add(new NumFeld(ANZAHL_DER_VORBESITZER, 2, 107), 2);
        add(new Betrag(KAUFPREIS, 12, 109), 2);
        add(new AlphaNumFeld(MEHRWERTGRUND, 30, 121), 2);
        add(new NumFeld(LFD_PERSONENNUMMER_DES_SICHERUNGSGLAEUBIGERS, 6, 151), 2);
        add(new NumFeld(JAEHRLICHE_FAHRLEISTUNG, 6, 157), 2);
        add(new AlphaNumFeld(GARAGE, 2, 163), 2);
        add(new AlphaNumFeld(NUTZUNGSART, 2, 165), 2);
        add(new AlphaNumFeld(EIGENTUMSVERHAELTNIS_FAHRZEUG, 1, 167), 2);
        add(new AlphaNumFeld(WOHNEIGENTUM, 1, 168), 2);
        add(new AlphaNumFeld(PRODUKTNAME, 20, 169), 2);
        add(new NumFeld(KREISGEMEINDESCHLUESSEL, 5, 189), 2);
        add(new NumFeld(KREISGEMEINDESCHLUESSEL_ZUSATZINFORMATION, 1, 194), 2);
        add(new Datum(BEGINN_VERSICHERUNGSSCHUTZ, 8, 195), 2);
        add(new Datum(ENDEDATUM_DES_VERSICHERUNGSSCHUTZES_BEI_ROTEN_KENNZEICHEN, 8, 203), 2);
        add(new NumFeld(GUELTIGKEITSDAUER_IN_TAGEN_BEI_KURZZEITKENNZEICHEN, 2, 211), 2);
        add(new AlphaNumFeld(EVB_NUMMER, 7, 213), 2);
        add(new NumFeld(AUFBAUART, 2, 220), 2);
        add(new NumFeld(GEFAHRGUT, 1, 222), 2);
        add(new NumFeld(GESAMTMASSE, 6, 223), 2);
        add(new NumFeld(STAERKEEINHEIT, 1, 229), 2);
    }

    private void setUpDatenfelder70() {
        this.setUpTeildatensatz(1);
        // Teildatensatz 1
        add(new Zeichen(VERTRAGSSTATUS, 43));
        add(new Datum(BEGINN, 44));
        add(new NumFeld(AUSSCHLUSS, 8, 52));
        add(new Datum(AENDERUNGSDATUM, 60));
        add(new Zeichen(ZAHLUNGSWEISE, 68));
        add(new Datum(HAUPTFAELLIGKEIT, 69));
        add(new AlphaNumFeld(WAEHRUNGSSCHLUESSEL, 3, 77));
        add(new Betrag(BEITRAG_IN_WAEHRUNGSEINHEITEN, 12, 80));
        add(new Betrag(ABSCHLUSSPROVISION, 5, 92).mitNachkommastellen(2));
        add(new Zeichen(KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION, 97));
        add(new Betrag(FOLGEPROVISION, 5, 98).mitNachkommastellen(2));
        add(new Zeichen(KENNZEICHEN_ABWEICHENDE_FOLGEPROVISION, 103));
        add(new AlphaNumFeld(ABWEICHENDE_VU_NR, 5, 104));
        add(new Zeichen(KENNZEICHEN_ABWEICHENDE_VU_NR, 109));
        add(new Datum(RESTLAUFZEIT_VERTRAG, 2, 110));
        add(new Betrag(LAUFZEITRABATT_IN_PROZENT, 4, 112).mitNachkommastellen(2));
        add(new AlphaNumFeld(PRODUKTFORM, 5, 116));
        add(new Datum(PRODUKTFORM_GUELTIG_AB, 6, 121));
        add(new AlphaNumFeld(PRODUKTNAME, 20, 127));
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 147));
    }

}
