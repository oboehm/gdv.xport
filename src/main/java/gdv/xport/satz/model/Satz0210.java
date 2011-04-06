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
 * (c)reated 26.03.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz.model;

import java.util.*;

/**
 * Diese Klasse repraesentiert die Satzart 200.
 * Es handelt es sich dabei um eine alternative Implementierung der
 * {@link gdv.xport.satz.AllgemeinerVertragsteil}-Klasse, die nach dem Soplet-
 * Ansatz (s. <a href="http://www.soplets.org/">soplets.org</a>) implementiert
 * wurde.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.6 (26.03.2011)
 */
public class Satz0210 extends SatzX {
    
    /** Mapping table for sparte to Feldxxx enumeration. */
    private static Map<Integer, Enum<?>[]> map = new HashMap<Integer, Enum<?>[]>();
    
    static {
        map.put(10, gdv.xport.satz.feld.sparte10.Feld210.values());
        map.put(30, gdv.xport.satz.feld.sparte30.Feld210.values());
        map.put(50, gdv.xport.satz.feld.sparte50.Feld210.values());
        map.put(70, gdv.xport.satz.feld.sparte70.Feld210.values());
    }
    
    private static Enum<?>[] getFelderFor(final int sparte) {
        Enum<?>[] felder = map.get(sparte);
        if (felder == null) {
            throw new IllegalArgumentException("Sparte " + sparte + " not (yet) supported by " + Satz0210.class);
        }
        return felder;
    }
    
    /**
     * Legt ein neues Satz0210-Objekt fuer die uebergebene Sparte an.
     *
     * @param sparte Sparte (z.B. 10)
     */
    public Satz0210(final int sparte) {
        super(210, sparte, getFelderFor(sparte));
    }

}

