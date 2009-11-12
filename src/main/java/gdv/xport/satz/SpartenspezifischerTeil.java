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
import gdv.xport.feld.*;

import org.apache.commons.logging.*;

/**
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.1.0 (05.11.2009)
 */
public class SpartenspezifischerTeil extends Datensatz {

    private static final Log log = LogFactory.getLog(SpartenspezifischerTeil.class);

    public SpartenspezifischerTeil(int sparte) {
        this(sparte, getNumberOfTeildatensaetzeFor(sparte));
    }

    /**
     * @param sparte
     * @param n Anzahl Teildatensaetze
     */
    public SpartenspezifischerTeil(int sparte, int n) {
        super(220, sparte, n);
        this.setUpDatenfelder(sparte);
    }

    private static int getNumberOfTeildatensaetzeFor(int sparte) {
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
    
    private void setUpDatenfelder(int sparte) {
        switch (sparte) {
            case 70:
                this.setUpDatenfelder70();
                break;
            default:
                log.warn("Sparte " + sparte + " not yet fully supported");
                this.addFiller();
                break;
        }
    }

    private void setUpDatenfelder70() {
        // Teildatensatz 1
        Feld risikoGruppe = new AlphaNumFeld(RISIKOGRUPPE_RISIKOART, 5, 43);
        add(risikoGruppe);
        Feld lfdNummer = new NumFeld(LFD_NUMMER, 5, 48);
        add(lfdNummer);
        add(new NumFeld(SATZNUMMER, 1, 53, 1));
        add(new Zeichen(VERTRAGSSATUS, 54));
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
        add(new Betrag(SELBSTBETEILIGUNG_IN_PROZENT, 5, 144));
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
        add(new Betrag(ZUSCHLAG1_IN_PROZENT, 5, 54), 2);
        add(new Betrag(ZUSCHLAG1_IN_WAEHRUNGSEINHEITEN, 6, 59), 2);
        add(new Betrag(ZUSCHLAG2_IN_PROZENT, 5, 65), 2);
        add(new Betrag(ZUSCHLAG2_IN_WAEHRUNGSEINHEITEN, 6, 70), 2);
        add(new Betrag(ABSCHLAG1_IN_PROZENT, 5, 76), 2);
        add(new Betrag(ABSCHLAG1_IN_WAEHRUNGSEINHEITEN, 6, 81), 2);
        add(new Betrag(ABSCHLAG2_IN_PROZENT, 5, 87), 2);
        add(new Betrag(ABSCHLAG2_IN_WAEHRUNGSEINHEITEN, 6, 92), 2);
        add(new Betrag(ABSCHLAG3_IN_PROZENT, 5, 98), 2);
        add(new Betrag(ABSCHLAG3_IN_WAEHRUNGSEINHEITEN, 6, 103), 2);
        add(new AlphaNumFeld(VERSICHERTES_OBJEKT, 80, 109), 2);
        add(new AlphaNumFeld(MITVERSICHERTE_PERSON_FAMILIENNAME, 30, 189), 2);
        add(new AlphaNumFeld(MITVERSICHERTE_PERSON_VORNAME, 30, 219), 2);
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 249), 2);
    }

}

