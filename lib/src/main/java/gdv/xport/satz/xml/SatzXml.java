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
 * (c)reated 30-Jul-2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import gdv.xport.feld.Bezeichner;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.util.SatzTyp;
import gdv.xport.util.XmlHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Im Gegensatz zum SOP-Ansatz und zur SatzX-Klasse wird hier eine XML-
 * Beschreibung verwendet, um die einzelnen Teildatensaetze mit ihren Feldern
 * zu bestimmen. Da die XML-Datei mit der Komplett-Beschreibung relativ gross
 * ist (ca. 7 MB), verwenden wir hier nicht einen DOM-Parser. Und auch keinen
 * XPath-Ansatz.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (30.07.2014)
 */
public class SatzXml extends Datensatz {

    private static final Logger LOG = LogManager.getLogger(SatzXml.class);

    /**
     * Instantiiert einen neuen Satz.
     *
     * @param parser XML-Event-Parser
     * @throws XMLStreamException the XML stream exception
     */
    public SatzXml(final XMLEventReader parser) throws XMLStreamException {
        this(parser, XmlHelper.getNextStartElement("satzart", parser));
    }

    /**
     * Instantiiert einen neuen Satz.
     *
     * @param parser XML-Event-Parser
     * @param element the element
     * @throws XMLStreamException the XML stream exception
     */
    public SatzXml(final XMLEventReader parser, final StartElement element) throws XMLStreamException {
        super(0);
        parse(element, parser);
    }

    /**
     * Dies ist der Copy-Constructor, mit dem man einen bestehenden Satz
     * kopieren kann.
     *
     * @param orig der originale Satz
     */
    public SatzXml(final Datensatz orig) {
        super(orig);
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Datensatz#setUpTeildatensaetze()
     */
    @Override
    protected void setUpTeildatensaetze() {
        this.removeAllTeildatensaetze();
    }

    private void parse(final StartElement element, final XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                parseElement(event.asStartElement(), reader);
            } else if (XmlHelper.isEndElement(event, element.getName())) {
                LOG.debug("{}...{} successful parsed.", element, event);
                return;
            }
            LOG.trace("Event {} is ignored.", event);
        }
        throw new XMLStreamException("end of " + element + " not found");
    }

    private void parseElement(final StartElement element, final XMLEventReader reader) throws XMLStreamException {
        LOG.trace("Parsing element {}.", element);
        QName name = element.getName();
        if ("satzanfang".equals(name.getLocalPart())) {
            parseTeildatensatz(element, reader);
        } else if ("feldreferenz".equals(name.getLocalPart())) {
            parseFeldreferenz(element, reader);
        }
    }

    private void parseTeildatensatz(final StartElement element, final XMLEventReader reader) throws XMLStreamException {
        TeildatensatzXml tds = parseSatzanfang(element, reader);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (XmlHelper.isStartElement(event, "feldreferenz")) {
                tds.add(new FeldReferenz(reader, event.asStartElement()));
            } else if (XmlHelper.isStartElement(event, "satzende")) {
                LOG.trace("<{}> is reached.", element);
                tds.setSatzende(new Satzende(reader, event.asStartElement()));
                this.add(tds);
                return;
            }
        }
        throw new XMLStreamException("<satzende> for " + element + " not found");
    }

    private TeildatensatzXml parseSatzanfang(final StartElement element, final XMLEventReader reader) throws XMLStreamException {
        Attribute teilsatz = element.getAttributeByName(new QName("teilsatz"));
        int nr = Integer.parseInt(teilsatz.getValue());
        TeildatensatzXml tds = new TeildatensatzXml(this.getSatzart(), nr);
        LOG.debug("Teildatensatz {} added to {}.", nr, this);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (XmlHelper.isStartElement(event, "feldreferenz")) {
                tds.add(new FeldReferenz(reader, event.asStartElement()));
            } else if (XmlHelper.isEndElement(event, element.getName())) {
                LOG.trace("End of <{}> is reached.", element);
                return tds;
            }
        }
        throw new XMLStreamException("end of " + element + " not found");
    }

    /**
     * Parses the feldreferenz.
     *
     * @param element the element
     * @param reader the reader
     * @throws XMLStreamException the XML stream exception
     */
    private void parseFeldreferenz(StartElement element, final XMLEventReader reader) throws XMLStreamException {
        FeldReferenz referenz = new FeldReferenz(reader, element);
        if (referenz.hasAuspraegung()) {
            if ("Satzart".equals(referenz.getName())) {
                this.getSatzartFeld().setInhalt(referenz.getAuspraegung());
            } else if ("Sparte".equals(referenz.getName())) {
                this.setSparte(referenz.getAuspraegung());
            }
        }
    }

    /**
     * Verwendet die uebergebene Map, um die Teildatensaetze um fehlende
     * Informationen zu ergaenzen.
     *
     * @param felder the felder
     */
    public void setFelder(Map<String, FeldXml> felder) {
        LOG.trace("Setting missing felder infos to {}.", this);
        for (Teildatensatz tds : this.getTeildatensaetze()) {
            TeildatensatzXml tdsXml = (TeildatensatzXml) tds;
            tdsXml.updateWith(felder);
        }
    }

    /**
     * Liefert eine Liste der unterstuetzten Satz-Typen. Dies ist vor allem
     * fuer Satz 220 Sparte 10 von Bedeutung, die verschienden Wagnisarten
     * unterstuetzen koennen.
     *
     * @return the supported satz typen
     */
    public List<SatzTyp> getSupportedSatzTypen() {
        List<SatzTyp> satzTypen = new ArrayList<SatzTyp>();
        if (this.hasWagnisart()) {
            TeildatensatzXml tdsXml = (TeildatensatzXml) this.getTeildatensatz(1);
            FeldReferenz feldReferenz = tdsXml.getFeldRefenz((Bezeichner.WAGNISART));
            for (String value : feldReferenz.getDefaultWerte()) {
                satzTypen.add(new SatzTyp(this.getSatzart(), this.getSparte(), Integer.parseInt(value)));
            }
        } else if (this.hasKrankenFolgeNr()) {
            TeildatensatzXml tdsXml = (TeildatensatzXml) this.getTeildatensatz(1);
            FeldReferenz feldReferenz = this.hasFeld(Bezeichner.FOLGE_NR_ZUR_LAUFENDEN_PERSONEN_NR_UNTER_NR_LAUFENDE_NR_TARIF)
                    ? tdsXml.getFeldRefenz(Bezeichner.FOLGE_NR_ZUR_LAUFENDEN_PERSONEN_NR_UNTER_NR_LAUFENDE_NR_TARIF)
                    : tdsXml.getFeldRefenz(Bezeichner.FOLGE_NR_ZUR_LAUFENDEN_PERSONEN_NR_UNTER_NR_BZW_LAUFENDEN_NR_TARIF);
            if (feldReferenz.getBemerkung().contains("mit \"1\" belegt")) {
                satzTypen.add(new SatzTyp(this.getSatzart(), this.getSparte(), -1, 1, -1));
            } else if (feldReferenz.getBemerkung().contains("mit \"2\" belegt")) {
                satzTypen.add(new SatzTyp(this.getSatzart(), this.getSparte(), -1, 2, -1));
            } else if (feldReferenz.getBemerkung().contains("mit \"3\" belegt")) {
                satzTypen.add(new SatzTyp(this.getSatzart(), this.getSparte(), -1, 3, -1));
            } else {
                LOG.error("Expected Folge-Nr. Bemerkung containing 'mit \"X\"X belegt' for Satzart 220, Sparte 20, Feld 10 with X being 1, 2 or 3, but got "
                        + feldReferenz.getBemerkung());
                satzTypen.add(this.getSatzTyp());
            }
        } else {
            satzTypen.add(this.getSatzTyp());
        }
        return satzTypen;
    }
    
}
