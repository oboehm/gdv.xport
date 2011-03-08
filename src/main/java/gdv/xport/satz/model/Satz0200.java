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
 * (c)reated 06.03.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz.model;

import java.lang.reflect.*;

import org.apache.commons.logging.*;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.feld.*;
import gdv.xport.satz.*;
import gdv.xport.satz.sop.FeldAllgemeinerVertragsteil;

/**
 * Diese Klasse repraesentiert die Satzart 200.
 * Es handelt es sich dabei um eine alternative Implementierung der
 * {@link AllgemeinerVertragsteil}-Klasse, die nach dem Soplet-Ansazt
 * (s. <a href="http://www.soplets.org/">soplets.org</a>) implementiert
 * wurde.
 *
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.6 (06.03.2011)
 */
public class Satz0200 extends Datensatz {
    
    private static final Log log = LogFactory.getLog(Satz0200.class);
    
    /**
     * Default-Konstruktor.
     */
    public Satz0200() {
        super("0200", 2);
        this.setUpDatenfelder();
    }

    /**
     * Sets the up datenfelder.
     */
    private void setUpDatenfelder() {
        FeldAllgemeinerVertragsteil[] felder = FeldAllgemeinerVertragsteil.values();
        for (int i = 0; i < felder.length; i++) {
            add(felder[i]);
        }
        if (log.isTraceEnabled()) {
            log.trace(felder.length + " Datenfelder set up");
        }
    }

    /**
     * Adds the.
     *
     * @param feldElement the feld element
     */
    private void add(final FeldAllgemeinerVertragsteil feldElement) {
        FeldInfo info = feldElement.getFeldInfo();
        String name = feldElement.getAsBezeichner();
        Feld feld = createFeld(name, info);
        add(feld, info.teildatensatz());
    }
    
    /**
     * Creates the feld.
     *
     * @param name the name
     * @param info the info
     * @return the feld
     */
    private static Feld createFeld(final String name, final FeldInfo info) {
        try {
            Constructor<? extends Feld> ctor = info.type().getConstructor(String.class, int.class, int.class);
            Feld feld = ctor.newInstance(name, info.anzahlBytes(), info.byteAdresse());
            return feld;
        } catch (NoSuchMethodException e) {
            throw new InternalError("no constructor " + info.type().getSimpleName() + "(String, int, int) found");
        } catch (InstantiationException e) {
            throw new InternalError("can't instantiate " + info.type());
        } catch (IllegalAccessException e) {
            throw new InternalError("can't access ctor for " + info.type());
        } catch (InvocationTargetException e) {
            throw new InternalError("error invoking ctor for " + info.type() + " (" + e.getTargetException() + ")");
        }
    }

    /**
     * Liefert den Inhalt des gewuenschten Feldes.
     *
     * @param feld das gewuenschte Feld-Element
     * @return Inhalt des gefundenden Felds
     */
    public String get(final FeldAllgemeinerVertragsteil feld) {
        String name = feld.getAsBezeichner();
        return super.get(name);
    }

}

