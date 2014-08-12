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

import gdv.xport.feld.Feld;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.util.XmlHelper;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public final class SatzXml extends Datensatz {

    private static final Logger LOG = LoggerFactory.getLogger(SatzXml.class);

    /**
     * Instantiiert einen neuen Satz.
     *
     * @param parser XML-Event-Parser
     * @throws XMLStreamException the XML stream exception
     */
    public SatzXml(final XMLEventReader parser) throws XMLStreamException {
        this(parser, XmlHelper.getNextStartElement(parser));
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

    /**
     * Parses the element.
     *
     * @param element the element
     * @param reader the reader
     * @throws XMLStreamException the XML stream exception
     */
    private void parseElement(final StartElement element, final XMLEventReader reader) throws XMLStreamException {
        LOG.trace("Parsing element {}.", element);
        QName name = element.getName();
        if ("satzanfang".equals(name.getLocalPart())) {
            parseSatzanfang(element, reader);
        } else if ("felder".equals(name.getLocalPart())) {
            parseFelder(element, reader);
        } else if ("feldreferenz".equals(name.getLocalPart())) {
            parseFeldreferenz(element, reader);
        }
    }

    /**
     * Parses the satzanfang.
     *
     * @param element the element
     * @param reader the reader
     * @throws XMLStreamException the XML stream exception
     */
    private void parseSatzanfang(final StartElement element, final XMLEventReader reader) throws XMLStreamException {
        Attribute teilsatz = element.getAttributeByName(new QName("teilsatz"));
        int nr = Integer.parseInt(teilsatz.getValue());
        TeildatensatzXml tds = new TeildatensatzXml(this.getSatzart(), nr);
        this.add(tds);
        LOG.debug("Teildatensatz {} added to {}.", nr, this);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (XmlHelper.isStartElement(event, "feldreferenz")) {
                tds.add(new FeldReferenz(reader, event.asStartElement()));
            } else if (XmlHelper.isEndElement(event, element.getName())) {
                LOG.trace("End of <{}> is reached.", element);
                return;
            }
        }
        throw new XMLStreamException("end of " + element + " not found");
    }

    /**
     * Parses the felder.
     *
     * @param element the element
     * @param reader the reader
     * @throws XMLStreamException the XML stream exception
     */
    private void parseFelder(final StartElement element, final XMLEventReader reader) throws XMLStreamException {
        LOG.trace("Element {} will be parsed.", element);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                this.add(new FeldXml(reader, event.asStartElement()));
            } else if (XmlHelper.isEndElement(event, element.getName())) {
                LOG.debug("Felder between {}...{} successful parsed.", element, event);
                return;
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
            this.getSatzartFeld().setInhalt(referenz.getAuspraegung());
        }
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#add(gdv.xport.feld.Feld)
     */
    @Override
    public void add(final Feld feld) {
        if (feld instanceof FeldXml) {
            this.add((FeldXml) feld);
        } else {
            super.add(feld);
        }
    }

    /**
     * Fuegt das uebergebene Feld zur Liste der Datenfelder hinzu.
     *
     * @param feld das Feld
     */
    public void add(final FeldXml feld) {
        this.getTeildatensaetze();
        for (Teildatensatz tds : this.getTeildatensaetze()) {
            TeildatensatzXml tdsXml = (TeildatensatzXml) tds;
            tdsXml.add(feld);
        }
    }

}
