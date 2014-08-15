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

import gdv.xport.feld.Feld;
import gdv.xport.satz.Teildatensatz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dieser {@link Teildatensatz} wurde um Belange fuer die XML-Verarbeitung
 * erweitert.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (08.08.2014)
 */
public final class TeildatensatzXml extends Teildatensatz {

    private static final Logger LOG = LoggerFactory.getLogger(TeildatensatzXml.class);
    private final List<FeldReferenz> feldReferenzen = new ArrayList<FeldReferenz>();

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

    /**
     * Legt mithilfe der uebergebenen Felder die entsprechenden {@link Feld}-
     * Objekte an.
     *
     * @param felder Felder aus der XML-Beschreibung
     */
    public void setFelder(Map<String, FeldXml> felder) {
        int byteAddress = 1;
        for (FeldReferenz referenz : this.feldReferenzen) {
            FeldXml feldXml = felder.get(referenz.getId());
            if (feldXml == null) {
                throw new IllegalArgumentException("referenz for " + referenz + " not found in " + felder);
            }
            Feld feld = feldXml.toFeld(byteAddress);
            this.getDatenfelder().put(feldXml.getBezeichner(), feld);
            byteAddress += feldXml.getAnzahlBytes();
        }
        LOG.trace("{} felder set.", this.feldReferenzen.size());
    }

}
