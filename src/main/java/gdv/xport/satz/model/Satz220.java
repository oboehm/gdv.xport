/*
 * Copyright (c) 2011 by agentes
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
 * (c)reated 08.04.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz.model;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.io.ImportException;
import gdv.xport.satz.Teildatensatz;

import java.io.IOException;
import java.util.*;

/**
 * Diese Klasse repraesentiert die Satzart 220.
 * Es handelt es sich dabei um eine alternative Implementierung der
 * SpartenspezifischerTeil-Klasse, die nach dem Soplet-
 * Ansatz (s. <a href="http://www.soplets.org/">soplets.org</a>) implementiert
 * wurde.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.6 (08.04.2011)
 */
public class Satz220 extends SpartensatzX {

    /** Mapping table for sparte to Feldxxx enumeration. */
    private static final Map<Integer, Enum<?>[]> mapping = new HashMap<Integer, Enum<?>[]>();
    
    static {
        mapping.put(30, gdv.xport.satz.feld.sparte30.Feld220.values());
        mapping.put(51, gdv.xport.satz.feld.sparte51.Feld220.values());
        mapping.put(52, gdv.xport.satz.feld.sparte52.Feld220.values());
        mapping.put(53, gdv.xport.satz.feld.sparte53.Feld220.values());
        mapping.put(70, gdv.xport.satz.feld.sparte70.Feld220.values());
    }
    
    /**
     * Default-Konstruktor.
     */
    public Satz220() {
        this(UNKNOWN_SPARTE);
    }
    
    /**
     * Legt ein neues Satz220-Objekt fuer die uebergebene Sparte an.
     *
     * @param sparte Sparte (z.B. 10)
     */
    public Satz220(final int sparte) {
        super(220, sparte);
    }
    
    /**
     * Liefert die Mapping-Tabelle zu Sparte - Feldxxx zurueck.
     *
     * @return Mapping-Tabelle
     * @see gdv.xport.satz.model.SpartensatzX#getMapping()
     */
    protected Map<Integer, Enum<?>[]> getMapping() {
        return mapping;
    }

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
        this.setUpTeildatensatz(tds);
        this.setUpTeildatensatz30(n, tds);
        tds.importFrom(input);
        this.add(tds);
    }

    private void setUpTeildatensatz30(final int n, final Teildatensatz tds) {
        Enum<?>[] felder = mapping.get(30);
        for (int i = 0; i < felder.length; i++) {
            FeldInfo info = getFeldInfo(felder[i]);
            if (info.teildatensatz() == n) {
                add(felder[i], tds);
            }
        }
    }

}

