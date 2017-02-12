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

import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.Teildatensatz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.apache.logging.log4j.*;

/**
 * Dieser {@link Teildatensatz} wurde um Belange fuer die XML-Verarbeitung
 * erweitert.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (08.08.2014)
 */
public final class TeildatensatzXml extends Teildatensatz {

    private static final Logger LOG = LogManager.getLogger(TeildatensatzXml.class);
    private static final Map<String, FeldXml> MISSING_FELDER = new HashMap<String, FeldXml>();
    private final List<FeldReferenz> feldReferenzen = new ArrayList<FeldReferenz>();
    private Satzende satzende;

    static {
        try {
            MISSING_FELDER.putAll(XmlService.getInstance("fehlendeFelder.xml").getFelder());
        } catch (XMLStreamException ex) {
            throw new IllegalArgumentException("cannot get missing felder from resource 'fehlendeFelder.xml'", ex);
        }
    }

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
     * Setzt das Satzende
     *
     * @param satzende das Satzende
     */
    public void setSatzende(final Satzende satzende) {
        this.satzende = satzende;
    }

    /**
     * Legt mithilfe der uebergebenen Felder die entsprechenden {@link Feld}-
     * Objekte an. Aber nur, wenn ein Feld noch nicht existiert.
     *
     * @param felder Felder aus der XML-Beschreibung
     */
    public void updateWith(Map<String, FeldXml> felder) {
        int byteAddress = 1;
        for (FeldReferenz referenz : this.feldReferenzen) {
            FeldXml feldXml = getFeld(felder, referenz.getId());
            this.addFeld(feldXml, byteAddress, referenz.getBezeichner());
            byteAddress += feldXml.getAnzahlBytes();
        }
        updateSatzendeWith(byteAddress, felder);
        LOG.trace("{} felder set.", this.feldReferenzen.size());
    }

    private FeldXml getFeld(Map<String, FeldXml> felder, String id) {
        FeldXml feldXml = felder.get(id);
        if (feldXml == null) {
            LOG.info("Will try fallback for reference '{}'.", id);
            feldXml = MISSING_FELDER.get(id);
        }
        if (feldXml == null) {
            throw new IllegalArgumentException("reference '" + id + "' not found in " + felder);
        }
        return feldXml;
    }

    private void updateSatzendeWith(final int startAddress, final Map<String, FeldXml> felder) {
        List<FeldReferenz> referenzen = this.satzende.getFeldReferenzen();
        int endAddress = 256;
        for (int i = referenzen.size() - 1; i >= 0; i--) {
            FeldReferenz referenz = referenzen.get(i);
            FeldXml feldXml = felder.get(referenz.getId());
            endAddress -= feldXml.getAnzahlBytes();
            this.addFeld(feldXml, endAddress+1, referenz.getBezeichner());
        }
        int length = endAddress + 1 - startAddress;
        if (length > 0) {
            Feld leerstelle = new AlphaNumFeld((Bezeichner.LEERSTELLEN), endAddress + 1 - startAddress, startAddress);
            this.add(leerstelle);
        }
    }

    private void addFeld(final FeldXml feldXml, final int byteAddress, final Bezeichner bezeichner) {
        Feld feld = feldXml.toFeld(byteAddress, bezeichner);
        if (!this.hasFeld(feld)) {
            super.add(feld);
        } else {
            LOG.trace("{} not added again.", feld);
        }
    }

    /**
     * Liefert die entsprechende {@link FeldReferenz}.
     *
     * @param bezeichner der gesuchte Bezeichner
     * @return die gefundene {@link FeldReferenz}
     */
    public FeldReferenz getFeldRefenz(final Bezeichner bezeichner) {
        for (FeldReferenz ref : this.feldReferenzen) {
            if (bezeichner.equals(ref.getBezeichner())) {
                return ref;
            }
        }
        throw new IllegalArgumentException(bezeichner + " not part of " + this);
    }

}
