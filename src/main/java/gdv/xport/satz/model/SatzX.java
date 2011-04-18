/*
 * Copyright (c) 2010 by agentes
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
 * (c)reated 09.03.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz.model;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.feld.*;
import gdv.xport.satz.*;
import gdv.xport.satz.feld.FeldX;

import java.util.*;

import org.apache.commons.logging.*;

/**
 * Dies ist die gemeinsame Oberklasse aller Saetze in diesem Package, die
 * nach dem SOP-Muster aufgebaut sind.
 * Eventuell wird diese Klasse mit der Oberklasse vereinigt.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.6 (09.03.2011)
 */
public class SatzX extends Datensatz {
    
    private static Log log = LogFactory.getLog(SatzX.class);

    /**
     * Instantiiert einen neuen Datensatz.
     * 
     * @param satzart z.B. 100
     * @param felder mit allen Elementen des Datensatzes
     */
    public SatzX(final int satzart, final Enum<?>[] felder) {
        super(satzart, getTeildatensaetzeFor(satzart, felder));
    }
    
    /**
     * Instantiiert einen neuen Datensatz.
     *
     * @param satzart z.B. 100
     * @param sparte Sparte
     * @param felder mit allen Elementen des Datensatzes
     */
    public SatzX(final int satzart, final int sparte, final Enum<?>[] felder) {
        super(satzart, sparte, getTeildatensaetzeFor(satzart, felder));
    }

    /**
     * Instantiiert einen allgemeinen Datensatz fuer die angegebene Satzart und
     * Sparte. Dieser Konstruktor ist hauptsaechlich als Fallback fuer
     * Satzarten/Sparten gedacht, die noch nicht unterstuetzt werden.
     *
     * @param satzart z.B. 100
     * @param sparte Sparte
     */
    public SatzX(final int satzart, final int sparte) {
        this(satzart, sparte, FeldX.values());
    }

    /**
     * Leitet aus dem uebergebenen Feldelement die Parameter ab, um daraus
     * ein Feld anzulegen und im jeweiligen Teildatensatz einzuhaengen.
     * Zusaetzlich wird das Feld "Satznummer" vorbelegt, falls es in den
     * uebergebenen Feldern vorhanden ist.
     *
     * @param feldX das Feld-Element
     * @param tds der entsprechende Teildatensatz
     */
    protected static void add(final Enum<?> feldX, final Teildatensatz tds) {
        FeldInfo info = getFeldInfo(feldX);
        String name = getAsBezeichner(feldX);
        Feld feld = Feld.createFeld(name, info);
        if (info.nr() < 8) {
            log.info("using default settings for " + feld);
        } else {
            tds.add(feld);
            if (isSatznummer(feldX)) {
                feld.setInhalt(info.teildatensatz());
            }
        }
    }
    
    private static boolean isSatznummer(final Enum<?> feldX) {
        return (feldX.name().length() <= 11) && feldX.name().startsWith("SATZNUMMER");
    }
    
    /**
     * Liefert das angehaengte FeldInfo zu einem Feld zurueck.
     *
     * @param feldX the feld x
     * @return the feld info
     */
    protected static FeldInfo getFeldInfo(final Enum<?> feldX) {
        String name = feldX.name();
        try {
            FeldInfo info = feldX.getClass().getField(name).getAnnotation(FeldInfo.class);
            if (info == null) {
                throw new UnsupportedOperationException("@FeldInfo missing for " + name);
            }
            return info;
        } catch (NoSuchFieldException nsfe) {
            throw new InternalError("no field " + name + " (" + nsfe + ")");
        }
    }
    
    private static List<Teildatensatz> getTeildatensaetzeFor(final int satzart, final Enum<?>[] felder) {
        SortedMap<Integer, Teildatensatz> tdsMap = new TreeMap<Integer, Teildatensatz>();
        for (int i = 0; i < felder.length; i++) {
            FeldInfo info = getFeldInfo(felder[i]);
            int n = info.teildatensatz();
            Teildatensatz tds = tdsMap.get(n);
            if (tds == null) {
                tds = new Teildatensatz(satzart, n);
                tdsMap.put(n, tds);
            }
            add(felder[i], tds);
        }
        return new ArrayList<Teildatensatz>(tdsMap.values());
    }

    /**
     * Setzt die Teildatensaetze mit den angegebenen Feldern auf.
     *
     * @param felder Felder fuer die Teildatensaetze.
     */
    protected void setUpTeildatensaetze(final Enum<?>[] felder) {
        super.createTeildatensaetze(getTeildatensaetzeFor(this.getSatzart(), felder));
        super.completeTeildatensaetze();
    }

}

