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
 * (c)reated 09.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import gdv.xport.satz.feld.Feld100;

/**
 * Diese Klasse repraesentiert die Satzart 100. Es handelt es sich dabei um eine
 * alternative Implementierung der gdv.xport.satz.Adressteil-Klasse, die
 * nach dem Soplet-Ansatz (s. <a href="http://www.soplets.org/">soplets.org</a>)
 * implementiert wurde.
 * <p>
 * Da langfristig (ab 1.0) die Adressteil-Klasse rausfliegen wird, wurde diese
 * Klasse um die fehlenden Methoden noch ergaenzt.
 * </p>
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (09.03.2011)
 */
public class Satz100 extends SatzX {

    /**
     * Default-Konstruktor.
     */
    public Satz100() {
        super(100, Feld100.values());
    }

    /**
     * Setzt den Vor- und Nachname. Der mittlere Name bleibt dabei unberuehrt.
     *
     * @param vorname z.B. "Max"
     * @param nachname z.B. "Mustermann"
     */
    public void setName(final String vorname, final String nachname) {
        this.set(Feld100.NAME1, vorname);
        this.set(Feld100.NAME3, nachname);
    }

    /**
     * Setzt den Vornamen, den mittleren Namen (zweiter Vorname) und den
     * Nachnamen.
     *
     * @param vorname erster Vorname
     * @param mittelname zweiter Name
     * @param nachname Nachnahme
     */
    public void setName(final String vorname, final String mittelname, final String nachname) {
        this.setName(vorname, nachname);
        this.set(Feld100.NAME2, mittelname);
    }

    /**
     * Liefert den entsprechenden Namen zurueck.
     *
     * @param n 1, 2 oder 3
     * @return Name1, Name2 oder Name3
     */
    public String getName(final int n) {
        switch (n) {
            case 1:
                return this.get(Feld100.NAME1).trim();
            case 2:
                return this.get(Feld100.NAME2).trim();
            case 3:
                return this.get(Feld100.NAME3).trim();
            default:
                throw new IllegalArgumentException("1 <= n <= 3 expected, not n=" + n);
        }
    }

}
