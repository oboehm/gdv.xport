/*
 * Copyright (c) 2014 by Oli B.
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
 * (c)reated 08.08.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import gdv.xport.feld.Align;
import gdv.xport.feld.Feld;
import gdv.xport.satz.Teildatensatz;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Dieser {@link Teildatensatz} wurde um Belange fuer die XML-Verarbeitung
 * erweitert.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (08.08.2014)
 */
public final class TeildatensatzXml extends Teildatensatz {

    private final Collection<FeldReferenz> feldReferenzen = new ArrayList<FeldReferenz>();

    /**
     * Instantiiert einen neuen Teildatensatz mit der angegebenen Satzart
     * und Nummer.
     *
     * @param satzart z.B. 100
     * @param nr Nummer des Teildatensatzes (zwischen 1 und 9)
     */
    public TeildatensatzXml(final int satzart, final int nr) {
        super(satzart, nr);
    }

    /**
     * Verarbeitet die uebergebene {@link FeldReferenz}. Oder auch nicht.
     *
     * @param referenz the feld referenz
     */
    public void add(final FeldReferenz referenz) {
        feldReferenzen.add(referenz);
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Teildatensatz#getFeld(java.lang.String)
     */
    @Override
    public Feld getFeld(final String name) {
        for (FeldReferenz referenz : this.feldReferenzen) {
            if (name.equals(referenz.getName())) {
                return new Feld(name, referenz.getAuspraegung(), Align.UNKNOWN);
            }
        }
        throw new IllegalArgumentException("no feld '" + name + "' found");
    }

}
