/*
 * Copyright (c) 2011, 2012 by Oli B.
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
 * (c)reated 05.01.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;
import gdv.xport.feld.*;

import org.apache.commons.logging.*;

/**
 * @author oliver (ob@aosd.de)
 * @since 0.5.0 (05.01.2011)
 *
 */
public class Erweiterungssatz211 extends Spartensatz {

    /** 211 Erweiterungssatz. */
    public static final int SATZART = 211;

    private static final Log log = LogFactory.getLog(Erweiterungssatz211.class);

    /**
     * Dieser Konstruktor wird fuer die SatzFactory benoetigt.
     */
    public Erweiterungssatz211() {
        super(SATZART);
    }

    /**
     * Konstruktor fuer die gewuenschte Sparte.
     * 
     * @param sparte z.B. 70 (Rechtsschutz)
     */
    public Erweiterungssatz211(final int sparte) {
        this(sparte, getNumberOfTeildatensaetzeFor(sparte));
    }

    /**
     * Konstruktor fuer die gewuenschte Sparte.
     * 
     * @param sparte z.B. 70 (Rechtsschutz)
     * @param n Anzahl Teildatensaetze
     */
    public Erweiterungssatz211(final int sparte, final int n) {
        super(SATZART, sparte, n);
        this.setUpDatenfelder(sparte);
    }
    
    private static int getNumberOfTeildatensaetzeFor(final int sparte) {
        switch (sparte) {
            case 0:
            case 10:
            case 40:
            case 50:
            case 80:
            case 110:
            case 130:
            case 140:
            case 170:
            case 190:
                return 1;
            default:
                log.warn("Satz 211: unknown Sparte " + sparte + " -> mapped to 1 Teildatensatz");
                return 1;
        }
    }
    
    /**
     * Legt die entsprechende Anzahl von Teildatensaetze fuer die angegebene
     * Sparte an.
     *
     * @param x Sparte (z.B. 10)
     */
    protected void createTeildatensaetzeFor(final int x) {
        this.createTeildatensaetze(getNumberOfTeildatensaetzeFor(x));
    }

    /**
     * Initialisiert die Teildatensaetze fuer die angegebene Sparte.
     *
     * @param sparte Sparte (z.B. 10)
     */
    protected void setUpDatenfelder(final int sparte) {
        switch (sparte) {
            case 10:
                this.setUpDatenfelder10();
                break;
            case 50:
                this.setUpDatenfelder50();
                break;
            default:
                log.warn("Sparte " + sparte + " not yet fully supported");
                this.setUpTeildatensatz(1);
                this.addFiller();
                break;
        }
    }
    
    private void setUpDatenfelder10() {
        this.setUpTeildatensatz(1);
        this.add(new NumFeld(VORLAUFSSUMME_IN_WAEHRUNGSEINHEITEN, 14, 43).mitNachkommastellen(2));
        this.add(new NumFeld(ZUKUENFTIGER_GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN, 12, 57).mitNachkommastellen(2));
        this.add(new AlphaNumFeld(LEERSTELLEN, 187, 69));
    }
    
    /**
     * Sparte 50 (KFZ - Fahrzeugdaten) wurde freundlicherweise von
     * Igor Narodetskyi zur Verfuegung gestellt.
     * 
     * @since 0.5.1
     */
    private void setUpDatenfelder50() {
        // Teildatensatz 1
        add(new Betrag(NEUPREIS_IN_WAEHRUNGSEINHEITEN, 12, 43));
        add(new Betrag(MEHRWERT_IN_WAEHRUNGSEINHEITEN, 12, 55));
        add(new AlphaNumFeld(REFERENZNUMMER, 7, 67));
        add(new AlphaNumFeld(LFD_NUMMER, 4, 74));
        add(new AlphaNumFeld(PERSONEN_KUNDENNUMMER_DES_VERSICHERERS, 17, 78));
        add(new AlphaNumFeld(Bezeichner.LEERSTELLEN, 161, 95));
    }

}

