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
import gdv.xport.satz.Datensatz;

import java.lang.reflect.Field;

import org.apache.commons.logging.*;

/**
 * Dies ist die gemeinsame Oberklasse aller Saetze in diesem Package, die
 * nach dem SOP-Muster aufgebaut sind.
 * Eventuell wird diese Klasse mit der Oberklasse vereinigt.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.6 (09.03.2011)
 */
public abstract class SatzX extends Datensatz {
    
    private static Log log = LogFactory.getLog(SatzX.class);

    /**
     * Instantiiert einen neuen Datensatz.
     * 
     * @param satzart z.B. 100
     * @param n Anzahl der Teildatensaetze
     * @param felder mit allen Elementen des Datensatzes
     */
    public SatzX(final String satzart, final int n, final Enum<?>[] felder) {
        super(satzart, n);
        this.setUpDatenfelder(felder);
    }
    
    private void setUpDatenfelder(final Enum<?>[] felder) {
        for (int i = 0; i < felder.length; i++) {
            add(felder[i]);
        }
    }
    
    /**
     * Leitet aus dem uebergebenen Feldelement die Pararmeter ab, um daraus
     * ein Feld anzulegen und im jeweiligen Teildatensatz einzuhaengen.
     * 
     * @param feldX das Feld-Element
     */
    private void add(final Enum<?> feldX) {
        FeldInfo info = this.getFeldInfo(feldX);
        String name = getAsBezeichner(feldX);
        Feld feld = Feld.createFeld(name, info);
        add(feld, info.teildatensatz());
    }
    
    private FeldInfo getFeldInfo(final Enum<?> feldX) {
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
    
    /**
     * Liefert den Namen als Bezeichner zurueck. Dazu verwendet es die
     * {@link Bezeichner}-Klasse, um festzustellen, ob es den Namen schon
     * als Bezeichner gibt. Falls nicht, wird der Name zurueckgeliefert.
     * 
     * @param feldX das Feld-Element mit dem gesuchten Bezeichner
     * @return z.B. "Inkassoart"
     */
    private String getAsBezeichner(final Enum<?> feldX) {
        try {
            Field field = Bezeichner.class.getField(feldX.name());
            return (String) field.get(null);
        } catch (NoSuchFieldException e) {
            log.info("Bezeichner." + feldX.name() + " not found");
        } catch (IllegalArgumentException e) {
            log.warn(e);
        } catch (IllegalAccessException e) {
            log.warn("can't access Bezeichner." + feldX.name());
        }
        return feldX.name();
    }

    /**
     * Liefert den Inhalt des gewuenschten Feldes.
     *
     * @param feldX das gewuenschte Feld-Element
     * @return Inhalt des gefundenden Felds
     */
    public String get(final Enum<?> feldX) {
        String name = this.getAsBezeichner(feldX);
        return super.get(name);
    }

}

