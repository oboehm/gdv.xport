/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 05.11.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;

import java.io.IOException;

import gdv.xport.feld.*;
import gdv.xport.io.ImportException;

import org.apache.commons.logging.*;

/**
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.1.0 (05.11.2009)
 */
public class SpartenspezifischerTeil extends Spartensatz {

    private static final Log log = LogFactory.getLog(SpartenspezifischerTeil.class);

    /**
     * Dieser Konstruktor wird fuer die SatzFactory benoetigt.
     *
     * @since 0.2
     */
    public SpartenspezifischerTeil() {
        super(220);
    }

    /**
     * Konstruktor fuer die gewuenschte Sparte.
     * 
     * @param sparte z.B. 70 (Rechtsschutz)
     */
    public SpartenspezifischerTeil(final int sparte) {
        this(sparte, getNumberOfTeildatensaetzeFor(sparte));
    }

    /**
     * Konstruktor fuer die gewuenschte Sparte.
     * 
     * @param sparte z.B. 70 (Rechtsschutz)
     * @param n Anzahl Teildatensaetze
     */
    public SpartenspezifischerTeil(final int sparte, final int n) {
        super(220, sparte, n);
        this.setUpDatenfelder(sparte);
    }

    private static int getNumberOfTeildatensaetzeFor(final int sparte) {
        switch (sparte) {
            case 0:
            case 20:
            case 110:
            case 130:
            case 510:
            case 550:
                return 1;
            case 40:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 59:
            case 70:
            case 80:
            case 140:
            case 190:
            case 580:
                return 2;
            case 170:
                return 3;
            case 30:
                return 4;
            case 10:
                return 9;
            default:
                log.warn("unknown Sparte " + sparte + " -> mapped to 1 Teildatensatz");
                return 1;
        }
    }
    
    /**
     * Legt die entsprechende Anzahl von Teildatensaetze fuer die angegebene
     * Sparte an.
     *
     * @param x Sparte (z.B. 30)
     */
    protected void createTeildatensaetzeFor(final int x) {
        this.createTeildatensaetze(getNumberOfTeildatensaetzeFor(x));
    }

    /**
     * Initialisiert die Teildatensaetze fuer die angegebene Sparte.
     *
     * @param sparte Sparte (z.B. 30)
     */
    protected void setUpDatenfelder(final int sparte) {
        switch (sparte) {
            case 30:
                this.setUpDatenfelder30();
                break;
            case 51:
                this.setUpDatenfelder51();
                break;
            case 52:
                this.setUpDatenfelder52();
                break;
            case 53:
                this.setUpDatenfelder53();
                break;
            case 70:
                this.setUpDatenfelder70();
                break;
            default:
                log.warn("Sparte " + sparte + " not yet fully supported");
                this.addFiller();
                break;
        }
    }

    private void setUpDatenfelder30() {
        this.setUpTeildatensatz30(1, this.getTeildatensatz(1));
        this.setUpTeildatensatz30(2, this.getTeildatensatz(2));
        this.setUpTeildatensatz30(3, this.getTeildatensatz(3));
        this.setUpTeildatensatz30(9, this.getTeildatensatz(4));
    }

    private void setUpTeildatensatz30(final int n, final Teildatensatz tds) {
        this.setUpTeildatensatz(tds);
        switch(n) {
            case 1:     // Teildatensatz 1
                tds.add(new NumFeld(LFD_NUMMER, 6, 43));
                tds.add(new NumFeld(SATZNUMMER, 1, 49, 1));
                tds.add(new AlphaNumFeld(BEZEICHNUNG_PERSONENGRUPPE, 30, 50));
                tds.add(new NumFeld(ANZAHL_VP_PRO_PERSONENGRUPPE, 6, 80));
                tds.add(new AlphaNumFeld(NAME_VP, 30, 86));
                tds.add(new AlphaNumFeld(VORNAME_VP, 30, 116));
                tds.add(new Datum(GEBURTSDATUM_VP, 146));
                tds.add(new Zeichen(GESCHLECHT_VP, 154));
                tds.add(new NumFeld(BERUFSSCHLUESSEL, 3, 155));
                tds.add(new AlphaNumFeld(BERUF_TEXT, 30, 158));
                tds.add(new Zeichen(GEFAHRENGRUPPE, 188));
                tds.add(new Zeichen(TARIF, 189));
                tds.add(new Zeichen(DECKUNGSUMFANG, 190));
                tds.add(new AlphaNumFeld(WAEHRUNGSSCHLUESSEL, 3, 191));
                tds.add(new NumFeld(ZUSCHLAG_IN_PROZENT, 5, 194).mitNachkommastellen(2));
                tds.add(new NumFeld(ABSCHLAG_IN_PROZENT, 5, 199).mitNachkommastellen(2));
                tds.add(new Betrag(BEITRAG_PRO_VP_IN_WAEHRUNGSEINHEITEN, 12, 204));
                tds.add(new Datum(EINSCHLUSSDATUM_VP, 216));
                tds.add(new Datum(AUSSCHLUSSDATUM_VP, 224));
                tds.add(new Datum(AENDERUNGSDATUM, 232));
                tds.add(new Zeichen(BESONDERE_VEREINBARUNG_ZUM_FLUGGASTRISIKO, 240));
                tds.add(new Zeichen(PASSIVES_KRIEGSRISIKO, 241));
                tds.add(new Zeichen(KUENDIGUNGSKLAUSEL_VP, 242));
                tds.add(new AlphaNumFeld(LEERSTELLEN, 7, 243));
                tds.add(new Zeichen(SATZNUMMERWIEDERHOLUNG, 250));
                break;
            case 2:
                tds.add(new NumFeld(LFD_NUMMER, 6, 43));
                tds.add(new Zeichen(SATZNUMMER, 49, '2'));
                tds.add(new NumFeld(TOD, 9, 50));
                tds.add(new NumFeld(TOD_BEITRAGSSATZ, 7, 59).mitNachkommastellen(5));
                tds.add(new NumFeld(INVALIDITAET, 9, 66));
                tds.add(new Zeichen(KENNUNG_GLIEDERTAXE, 75));
                tds.add(new Zeichen(KENNUNG_PROGRESSIVE_INVALIDITAET, 76));
                tds.add(new NumFeld(INVALIDITAET_BEITRAGSSATZ, 7, 77).mitNachkommastellen(5));
                tds.add(new NumFeld(TAGEGELD1, 7, 84).mitNachkommastellen(1));
                tds.add(new NumFeld(BEGINN_TAGEGELD1_AB_TAG, 3, 91));
                tds.add(new NumFeld(TAGEGELD1_BEITRAGSSATZ, 7, 94).mitNachkommastellen(5));
                tds.add(new NumFeld(TAGEGELD2, 7, 101).mitNachkommastellen(1));
                tds.add(new NumFeld(BEGINN_TAGEGELD2_AB_TAG, 3, 108));
                tds.add(new NumFeld(TAGEGELD2_BEITRAGSSATZ, 7, 111).mitNachkommastellen(5));
                tds.add(new NumFeld(KRANKENHAUSTAGEGELD, 7, 118).mitNachkommastellen(1));
                tds.add(new NumFeld(KRANKENHAUSTAGEGELD_BEITRAGSSATZ, 7, 125).mitNachkommastellen(5));
                tds.add(new NumFeld(GENESUNGSGELD, 7, 132).mitNachkommastellen(1));
                tds.add(new NumFeld(GENESUNGSGELD_BEITRAGSSATZ, 7, 139).mitNachkommastellen(5));
                tds.add(new NumFeld(UEBERGANGSENTSCHAEDIGUNG, 9, 146));
                tds.add(new Zeichen(KENNUNG_UEBERGANGSENTSCHAEDIGUNG, 155));
                tds.add(new NumFeld(UEBERGANGSENTSCHAEDIGUNG_BEITRAGSSATZ, 7, 156).mitNachkommastellen(5));
                tds.add(new NumFeld(HEILKOSTEN, 9, 163));
                tds.add(new Zeichen(ART_DER_HEILKOSTEN, 172));
                tds.add(new NumFeld(HEILKOSTEN_BEITRAGSSATZ, 7, 173).mitNachkommastellen(5));
                tds.add(new NumFeld(FESTE_RENTE, 9, 180));
                tds.add(new NumFeld(FESTE_RENTE_BEITRAGSSATZ, 7, 189).mitNachkommastellen(5));
                tds.add(new NumFeld(KOSMETISCHE_OPERATIONEN, 9, 196));
                tds.add(new NumFeld(KOSMETISCHE_OPERATIONEN_BEITRAGSSATZ, 7, 205).mitNachkommastellen(5));
                tds.add(new NumFeld(KURKOSTEN, 9, 212));
                tds.add(new NumFeld(KURKOSTEN_BEITRAGSSATZ, 7, 221).mitNachkommastellen(5));
                tds.add(new NumFeld(BERGUNGSKOSTEN, 9, 228));
                tds.add(new NumFeld(BERGUNGSKOSTEN_BEITRAGSSATZ, 7, 237).mitNachkommastellen(5));
                tds.add(new Zeichen(UEBERFUEHRUNGSKOSTEN, 244));
                tds.add(new Zeichen(RUECKFUEHRUNGSKOSTEN, 245));
                tds.add(new NumFeld(PROZENTSATZ_PROGRESSIVE_INVALIDITAET, 6, 246).mitNachkommastellen(2));
                tds.add(new Zeichen(MEHRLEISTUNGSKLAUSEL, 252));
                tds.add(new AlphaNumFeld(LEERSTELLEN, 3, 253));
                tds.add(new Zeichen(ZUSAETZLICHE_SATZKENNUNG, 256, 'X'));
                break;
            case 3:     // Teildatensatz 3
                tds.add(new Zeichen(SATZNUMMER, 43, '3'));
                tds.add(new AlphaNumFeld(VP_PERSONENNUMMER_VERSICHERER, 17, 44));
                tds.add(new AlphaNumFeld(VP_PERSONENNUMMER_VERMITTLER, 17, 61));
                tds.add(new NumFeld(SERVICELEISTUNGEN, 9, 78));
                tds.add(new NumFeld(SERVICELEISTUNGEN_BEITRAGSSATZ, 7, 87).mitNachkommastellen(5));
                tds.add(new Betrag(BEITRAG_SERVICELEISTUNGEN_IN_WAEHRUNGSEINHEITEN, 12, 94));
                tds.add(new Betrag(BEITRAG_TOD_IN_WAEHRUNGSEINHEITEN, 12, 106));
                tds.add(new Betrag(BEITRAG_INVALIDITAET_IN_WAEHRUNGSEINHEITEN, 12, 118));
                tds.add(new Betrag(BEITRAG_TAGEGELD1_IN_WAEHRUNGSEINHEITEN, 12, 130));
                tds.add(new Betrag(BEITRAG_TAGEGELD2_IN_WAEHRUNGSEINHEITEN, 12, 142));
                tds.add(new Betrag(BEITRAG_KRANKENHAUSTAGEGELD_IN_WAEHRUNGSEINHEITEN, 12, 154));
                tds.add(new Betrag(BEITRAG_GENESUNGSGELD_IN_WAEHRUNGSEINHEITEN, 12, 166));
                tds.add(new Betrag(BEITRAG_UEBERGANGSENTSCHAEDIGUNG_IN_WAEHRUNGSEINHEITEN, 12, 178));
                tds.add(new Betrag(BEITRAG_HEILKOSTEN_IN_WAEHRUNGSEINHEITEN, 12, 190));
                tds.add(new Betrag(BEITRAG_FESTE_RENTE_IN_WAEHRUNGSEINHEITEN, 12, 202));
                tds.add(new Betrag(BEITRAG_KOSMETISCHE_OPERATION_IN_WAEHRUNGSEINHEITEN, 12, 214));
                tds.add(new Betrag(BEITRAG_KURKOSTEN_IN_WAEHRUNGSEINHEITEN, 12, 226));
                tds.add(new Betrag(BEITRAG_BERGUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN, 12, 238));
                tds.add(new Zeichen(SATZNUMMERWIEDERHOLUNG, 250));
                tds.add(new NumFeld(LFD_NUMMER_VP_PERSONENGRUPPE, 6, 251));
                break;
            case 9:     // Teildatensatz 9
                tds.add(new AlphaNumFeld(LFD_NUMMER_VP, 17, 43));
                tds.add(new Zeichen(SATZNUMMER, 60, '9'));
                tds.add(new AlphaNumFeld(LFD_NUMMER_SATZART, 2, 61));
                tds.add(new Zeichen(BEZUGSBERECHTIGT_IM_LEISTUNGSFALL, 63));
                tds.add(new AlphaNumFeld(SONSTIGER_BEZUGSBERECHTIGTER_IM_LEISTUNGSFALL, 30, 64));
                tds.add(new NumFeld(BEZUGSRECHTANTEIL_IM_LEISTUNGSFALL, 5, 94).mitNachkommastellen(2));
                tds.add(new Zeichen(UNWIDERRUFLICHES_BEZUGSRECHT_IM_LEISTUNGSFALL, 99));
                tds.add(new AlphaNumFeld(REFERENZNUMMER, 7, 100));
                tds.add(new AlphaNumFeld(LEERSTELLEN, 143, 107));
                tds.add(new Zeichen(SATZNUMMERWIEDERHOLUNG, 250));
                tds.add(new NumFeld(LFD_NUMMER_VP_PERSONENGRUPPE, 6, 251));
                break;
            default:
                throw new IllegalArgumentException("unbekannte Teildatensatz-Nr.: " + n);
        }
    }
    
    /**
     * Sparte 51 (KFZ - Fahrzeughaftpflicht) wurde freundlicherweise von
     * Igor Narodetskyi zur Verfuegung gestellt.
     * 
     * @since 0.5.1
     */
    private void setUpDatenfelder51() {
        // Teildatensatz 1
        add(new NumFeld(KH_BEGINN, 8, 43));
        add(new NumFeld(KH_AUSSCHLUSS, 8, 51));
        add(new NumFeld(KH_AENDERUNGSDATUM, 8, 59));
        add(new AlphaNumFeld(KH_TARIFGRUPPE, 4, 67));
        add(new NumFeld(KH_DECKUNGSART, 2, 71));
        add(new NumFeld(KH_DECKUNGSSUMMEN, 27, 73));
        add(new AlphaNumFeld(KH_RGJ, 2, 100));
        add(new AlphaNumFeld(KH_SF_S_KLASSE, 5, 102));
        add(new NumFeld(KH_BEITRAGSSAETZE, 3, 107));
        add(new NumFeld(KH_BEITRAG_IN_WAEHRUNGSEINHEITEN, 8, 110));
        add(new NumFeld(KH_ZUSCHLAEGE_IN_PROZENT, 5, 118));
        add(new NumFeld(KH_ABSCHLAEGE_IN_PROZENT, 5, 123));
        add(new NumFeld(KH_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 6, 128));
        add(new NumFeld(KH_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 6, 134));
        add(new NumFeld(FLOTTENRABATT_IN_PROZENT, 3, 140));
        add(new NumFeld(GUELTIGE_AKB, 4, 143));
        add(new NumFeld(PROVISION, 4, 147));
        add(new NumFeld(KH_SCHAEDEN_AUS_RUECKSTUFUNG, 2, 151));
        add(new NumFeld(TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_HAFTPFLICHT_IN_WAEHRUNGSEINHEITEN, 8, 154));
        add(new AlphaNumFeld(SCHUTZBRIEF_VERKEHRSSERVICE, 1, 162));
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 163));
        add(new NumFeld(FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_KH, 10, 170));
        add(new AlphaNumFeld(TYKLASSE_KH, 3, 180));
        add(new AlphaNumFeld(TARIF, 10, 183));
        add(new AlphaNumFeld(LFD_NUMMER, 4, 193));
        add(new AlphaNumFeld(PERSONENNUMMER_LFD_NUMMER, 17, 197));
        // Teildatensatz 2
        add(new AlphaNumFeld(PRODUKTKENNUNG, 20, 43), 2);
        add(new AlphaNumFeld(VERSICHERTE_GEFAHREN, 30, 63), 2);
        add(new NumFeld(SELBSTBETEILIGUNG_IN_PROZENT, 5, 93), 2);
        add(new NumFeld(SELBSTBETEILIGUNG_IN_WE_MIND, 12, 98), 2);
        add(new NumFeld(SELBSTBETEILIGUNG_IN_WE_MAX, 12, 110), 2);
        add(new AlphaNumFeld(GELTUNGSBEREICH, 30, 122), 2);
        add(new AlphaNumFeld(GELTUNGSBEREICHEINSCHRAENKUNG, 30, 152), 2);
    }
    
    /**
     * Sparte 52 (KFZ - Fahrzeugvollkasko) wurde freundlicherweise von
     * Igor Narodetskyi zur Verfuegung gestellt.
     * 
     * @since 0.5.1
     */
    private void setUpDatenfelder52() {
        // Teildatensatz 1
        add(new NumFeld(KFV_BEGINN, 8, 43));
        add(new NumFeld(KFV_AUSSCHLUSS, 8, 51));
        add(new NumFeld(KFV_AENDERUNGSDATUM, 8, 59));
        add(new AlphaNumFeld(KFV_TARIFGRUPPE, 4, 67));
        add(new NumFeld(KFV_DECKUNGSART, 2, 71));
        add(new AlphaNumFeld(KFV_RGJ, 2, 73));
        add(new AlphaNumFeld(KFV_SFS_KLASSE, 5, 75));
        add(new NumFeld(KFV_BEITRAGSSATZ, 3, 80));
        add(new NumFeld(KFV_BEITRAG_IN_WAEHRUNGSEINHEITEN, 8, 83));
        add(new NumFeld(KFV_ZUSCHLAEGE_IN_PROZENT, 5, 91));
        add(new NumFeld(KFV_ABSCHLAEGE_IN_PROZENT, 5, 96));
        add(new NumFeld(KFV_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 6, 101));
        add(new NumFeld(KFV_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 6, 107));
        add(new NumFeld(FLOTTENRABATT_IN_PROZENT, 3, 113));
        add(new NumFeld(GUELTIGE_AKB, 4, 116));
        add(new NumFeld(PROVISION, 4, 120));
        add(new NumFeld(KFV_SCHAEDEN_AUS_RUECKSTUFUNG, 2, 124));
        add(new AlphaNumFeld(TYPKLASSE_FUER_KFV, 2, 126));
        add(new AlphaNumFeld(FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_VOLLKASKO, 10, 128));
        add(new AlphaNumFeld(KENNZEICHEN_FUER_ABWEICHENDE_PROVISION, 1, 138));
        add(new NumFeld(TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGVOLL_IN_WAEHRUNGSEINHEITEN, 8, 139));
        add(new NumFeld(KASKO_BEGINNJAHR, 4, 147));
        add(new NumFeld(
                FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO_IM_RAHMEN_DER_VOLLKASKO, 10,
                151));
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 161));
        add(new AlphaNumFeld(TARIF, 10, 168));
        add(new AlphaNumFeld(LFD_NUMMER, 4, 178));
        add(new AlphaNumFeld(PERSONENNUMMER_LFD_NUMMER, 17, 182));
        // Teildatensatz 2
        add(new AlphaNumFeld(PRODUKTKENNUNG, 20, 43), 2);
        add(new AlphaNumFeld(VERSICHERTE_GEFAHREN, 30, 63), 2);
        add(new NumFeld(SELBSTBETEILIGUNG_IN_PROZENT, 5, 93), 2);
        add(new NumFeld(SELBSTBETEILIGUNG_IN_WE_MIND, 12, 98), 2);
        add(new NumFeld(SELBSTBETEILIGUNG_IN_WE_MAX, 12, 110), 2);
        add(new AlphaNumFeld(GELTUNGSBEREICH, 30, 122), 2);
        add(new AlphaNumFeld(GELTUNGSBEREICHEINSCHRAENKUNG, 30, 152), 2);
    }
    
    /**
     * Sparte 53 (KFZ - Fahrzeugteil) wurde freundlicherweise von
     * Igor Narodetskyi zur Verfuegung gestellt.
     * 
     * @since 0.5.1
     */
    private void setUpDatenfelder53() {
        // Teildatensatz 1
        add(new NumFeld(KFT_BEGINN, 8, 43));
        add(new NumFeld(KFT_AUSSCHLUSS, 8, 51));
        add(new NumFeld(KFT_AENDERUNGSDATUM, 8, 59));
        add(new AlphaNumFeld(KFT_TARIFGRUPPE, 4, 67));
        add(new NumFeld(KFT_DECKUNGSART, 2, 71));
        add(new NumFeld(KFT_BEITRAG_IN_WAEHRUNGSEINHEITEN, 8, 73));
        add(new NumFeld(KFT_ZUSCHLAEGE_IN_PROZENT, 5, 81));
        add(new NumFeld(KFT_ABSCHLAEGE_IN_PROZENT, 5, 86));
        add(new NumFeld(KFT_ZUSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 6, 91));
        add(new NumFeld(KFT_ABSCHLAEGE_IN_WAEHRUNGSEINHEITEN, 6, 97));
        add(new NumFeld(FLOTTENRABATT_IN_PROZENT, 3, 103));
        add(new NumFeld(GUELTIGE_AKB, 4, 106));
        add(new NumFeld(PROVISION, 4, 110));
        add(new AlphaNumFeld(TYPKLASSE_FUER_KFT, 2, 114));
        add(new NumFeld(FREI_VEREINBARTE_SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN_FUER_TEILKASKO, 10, 116));
        add(new AlphaNumFeld(KENNZEICHEN_FUER_ABWEICHENDE_PROVISION, 1, 126));
        add(new NumFeld(TARIFBEITRAG_100_PROZENT_FUER_KRAFTFAHRT_FAHRZEUGTEIL_IN_WAEHRUNGSEINHEITEN, 8, 127));
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 135));
        add(new AlphaNumFeld(TARIF, 10, 142));
        add(new AlphaNumFeld(LFD_NUMMER, 4, 152));
        add(new AlphaNumFeld(PERSONENNUMMER_LFD_NUMMER, 17, 156));
        add(new AlphaNumFeld(SCHUTZBRIEF_VERKEHRSSERVICE, 1, 173));
        add(new AlphaNumFeld(GAP_DECKUNG, 1, 174));
        // Teildatensatz 2
        add(new AlphaNumFeld(PRODUKTKENNUNG, 20, 43), 2);
        add(new AlphaNumFeld(VERSICHERTE_GEFAHREN, 30, 63), 2);
        add(new NumFeld(SELBSTBETEILIGUNG_IN_PROZENT, 5, 93), 2);
        add(new NumFeld(SELBSTBETEILIGUNG_IN_WE_MIND, 12, 98), 2);
        add(new NumFeld(SELBSTBETEILIGUNG_IN_WE_MAX, 12, 110), 2);
        add(new AlphaNumFeld(GELTUNGSBEREICH, 30, 122), 2);
        add(new AlphaNumFeld(GELTUNGSBEREICHEINSCHRAENKUNG, 30, 152), 2);
    }

    private void setUpDatenfelder70() {
        Feld risikoGruppe = new AlphaNumFeld(RISIKOGRUPPE_RISIKOART, 5, 43);
        Feld lfdNummer = new NumFeld(LFD_NUMMER, 5, 48);
        // Teildatensatz 1
        add(risikoGruppe);
        add(lfdNummer);
        add(new NumFeld(SATZNUMMER, 1, 53, 1));
        add(new Zeichen(VERTRAGSSTATUS, 54));
        add(new Datum(BEGINN, 55));
        add(new Datum(AUSSCHLUSS, 63));
        add(new Datum(AENDERUNGSDATUM, 71));
        add(new Zeichen(ZAHLUNGSWEISE, 79));
        add(new Datum(HAUPTFAELLIGKEIT, 80));
        add(new AlphaNumFeld(WAEHRUNGSSCHLUESSEL, 3, 88));
        add(new Betrag(BEITRAG_IN_WAEHRUNGSEINHEITEN, 12, 91));
        add(new AlphaNumFeld(RISIKOTEXT, 30, 103));
        add(new AlphaNumFeld(TARIFIERUNGSMERKMAL_LAUFZEIT, 2, 133));
        add(new Datum(VORAUSSICHTLICHES_ENDE, 135));
        add(new Zeichen(SELBSTBETEILIGUNG, 143));
        add(new NumFeld(SELBSTBETEILIGUNG_IN_PROZENT, 5, 144).mitNachkommastellen(2));
        add(new Betrag(SELBSTBETEILIGUNG_IN_WAEHRUNGSEINHEITEN, 12, 149));
        add(new Zeichen(BEITRAGSANGLEICHUNGSKLAUSEL, 161));
        add(new Datum(DATUM_LETZTE_BEITRAGSANGLEICHUNG, 162));
        add(new Zeichen(BEITRAGSKLASSE, 170));
        add(new AlphaNumFeld(BERUFSGRUPPENEINTEILUNG, 4, 171));
        add(new Zeichen(BEITRAGSREGULIERUNG, 175));
        add(new NumFeld(DECKUNGSSUMME_IN_TSD_WAEHRUNGSEINHEITEN, 9, 176));
        add(new NumFeld(RISKIOEINHEIT1, 7, 185));
        add(new NumFeld(RISKIOEINHEIT2, 7, 192));
        add(new NumFeld(ARB, 4, 199));
        add(new Zeichen(SONDERVEREINBARUNGEN, 203));
        add(new NumFeld(TARIFJAHR, 4, 204));
        add(new AlphaNumFeld(AMTL_KENNZEICHEN, 12, 208));
        add(new Zeichen(WARTEZEIT, 220));
        // Teildatensatz 2
        add(risikoGruppe, 2);
        add(lfdNummer, 2);
        add(new Zeichen(SATZNUMMER, 53, '2'), 2);
        add(new NumFeld(ZUSCHLAG1_IN_PROZENT, 5, 54).mitNachkommastellen(2), 2);
        add(new Betrag(ZUSCHLAG1_IN_WAEHRUNGSEINHEITEN, 6, 59), 2);
        add(new NumFeld(ZUSCHLAG2_IN_PROZENT, 5, 65).mitNachkommastellen(2), 2);
        add(new Betrag(ZUSCHLAG2_IN_WAEHRUNGSEINHEITEN, 6, 70), 2);
        add(new NumFeld(ABSCHLAG1_IN_PROZENT, 5, 76).mitNachkommastellen(2), 2);
        add(new Betrag(ABSCHLAG1_IN_WAEHRUNGSEINHEITEN, 6, 81), 2);
        add(new NumFeld(ABSCHLAG2_IN_PROZENT, 5, 87).mitNachkommastellen(2), 2);
        add(new Betrag(ABSCHLAG2_IN_WAEHRUNGSEINHEITEN, 6, 92), 2);
        add(new NumFeld(ABSCHLAG3_IN_PROZENT, 5, 98).mitNachkommastellen(2), 2);
        add(new Betrag(ABSCHLAG3_IN_WAEHRUNGSEINHEITEN, 6, 103), 2);
        add(new AlphaNumFeld(VERSICHERTES_OBJEKT, 80, 109), 2);
        add(new AlphaNumFeld(MITVERSICHERTE_PERSON_FAMILIENNAME, 30, 189), 2);
        add(new AlphaNumFeld(MITVERSICHERTE_PERSON_VORNAME, 30, 219), 2);
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 249), 2);
    }

//    /**
//     * Abhaengig von der Sparte muessen wir hier noch die verschiedenen
//     * Teildatensaetze aufsetzen.
//     *
//     * @param x z.B. 30
//     * @see gdv.xport.satz.Datensatz#setSparte(int)
//     */
//    @Override
//    public void setSparte(final int x) {
//        if (this.getSatzart() == x) {
//            log.debug("nothing to do here - old Sparte = new Sparte (" + x + ")");
//        }
//        super.setSparte(x);
//        this.createTeildatensaetze(getNumberOfTeildatensaetzeFor(x));
//        super.setUpTeildatensaetze();
//        this.setUpDatenfelder(x);
//    }

    /**
     * Sparte 30 hat optionale Teildatensaetze (Teildatensatz 9). Den
     * muessen wir gesondert behandeln.
     *
     * @see gdv.xport.satz.Satz#importFrom(java.lang.String)
     * @param input Inupt
     * @throws IOException falls der String zu kurz ist
     */
    @Override
    public void importFrom(final String input) throws IOException {
        switch (this.getSparte()) { // NOPMD by oliver on 20.11.10 18:54
            case 30:
                importSparte30(input);
                break;
            default:
                super.importFrom(input);
                break;
        }
    }

    private void importSparte30(final String s) throws IOException {
        this.removeAllTeildatensaetze();
        int satzlength = getSatzlength(s);
        for (int i = 0; i < s.length(); i += satzlength) {
            String input = s.substring(i);
            if (input.trim().isEmpty()) {
                break;
            }
            char satznummer = input.charAt(48);
            switch (satznummer) {
                case '1':
                    addTeildatensatz30(1, input);
                    break;
                case '2':
                    addTeildatensatz30(2, input);
                    break;
                default:
                    if (input.charAt(42) == '3') {
                        addTeildatensatz30(3, input);
                    } else if (input.charAt(59) == '9') {
                        addTeildatensatz30(9, input);
                    } else {
                        throw new ImportException("Satz 0220.030: unbekannter Teildatensatz \""
                                + input.substring(0, 60) + "...\"");
                    }
                    break;
            }
        }
    }

    private void addTeildatensatz30(final int n, final String input) throws IOException {
        Teildatensatz tds = new Teildatensatz(this.getSatzartFeld());
        this.setUpTeildatensatz30(n, tds);
        tds.importFrom(input);
        this.add(tds);
    }

}

