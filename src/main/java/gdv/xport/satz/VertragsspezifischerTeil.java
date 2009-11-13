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
 * (c)reated 28.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;
import gdv.xport.feld.*;

import org.apache.commons.logging.*;

/**
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.1.0 (28.10.2009)
 *
 */
public class VertragsspezifischerTeil extends Datensatz {

    private static final Log log = LogFactory.getLog(VertragsspezifischerTeil.class);

    public VertragsspezifischerTeil(int sparte) {
        this(sparte, getNumberOfTeildatensaetzeFor(sparte));
    }

    /**
     * @param sparte
     * @param n Anzahl Teildatensaetze
     */
    public VertragsspezifischerTeil(int sparte, int n) {
        super(210, sparte, n);
        this.setUpDatenfelder(sparte);
    }

    private static int getNumberOfTeildatensaetzeFor(int sparte) {
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
        add(new Zeichen(VERTRAGSSATUS, 43));
        add(new Datum(BEGINN, 44));
        add(new NumFeld(AUSSCHLUSS, 8, 52));
        add(new Datum(AENDERUNGSDATUM, 60));
        add(new Zeichen(ZAHLUNGSWEISE, 68));
        add(new Datum(HAUPTFAELLIGKEIT, 69));
        add(new AlphaNumFeld(WAEHRUNGSSCHLUESSEL, 3, 77));
        add(new Betrag(BEITRAG_IN_WAEHRUNGSEINHEITEN, 12, 80));
        add(new Betrag(ABSCHLUSSPROVISION, 5, 92));
        add(new Zeichen(KENNZEICHEN_ABWEICHENDE_ABSCHLUSSPROVISION, 97));
        add(new Betrag(FOLGEPROVISION, 5, 98));
        add(new Zeichen(KENNZEICHEN_ABWEICHENDE_FOLGEPROVISION, 103));
        add(new AlphaNumFeld(ABWEICHENDE_VU_NR, 5, 104));
        add(new Zeichen(KENNZEICHEN_ABWEICHENDE_VU_NR, 109));
        add(new NumFeld(RESTLAUFZEIT_VERTRAG, 2, 110));
        add(new Betrag(LAUFZEITRABATT_IN_PROZENT, 4, 112));
        add(new AlphaNumFeld(PRODUKTFORM, 5, 116));
        add(new Datum(PRODUKTFORM_GUELTIG_AB, 6, 121));
        add(new AlphaNumFeld(PRODUKTNAME, 20, 127));
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 147));
    }
    
    /**
     * Abhaengig von der Sparte muessen wir hier noch die verschiedenen
     * Teildatensaetze aufsetzen.
     * 
     * @param x
     * @see gdv.xport.satz.Datensatz#setSparte(int)
     */
    @Override
    public void setSparte(int x) {
        if (this.getSatzart() == x) {
            log.debug("nothing to do here - old Sparte = new Sparte (" + x + ")");
        }
        super.setSparte(x);
        this.createTeildatensaetze(getNumberOfTeildatensaetzeFor(x));
        this.setUpDatenfelder(x);
    }

}

