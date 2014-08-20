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
 * (c)reated 15.08.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import gdv.xport.util.XmlHelper;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hier wird jetzt eine XML-Beschreibung verwendet, um die Saetze fuer die
 * einzelnen Satzarten aufzusetzen. Als Basis fuer die XML-Beschreibung wurde
 * die Datei "VUVM2013_010713.xml" genommen, die ueber <a
 * href="http://www.gdv-online.de/vuvm/bestand/best_2009.htm"
 * >www.gdv-online.de</a> heruntergeladen werden kann.
 *
 * @author oliver
 * @since 1.0 (15.08.2014)
 */
public class XmlService {

    private static final Logger LOG = LoggerFactory.getLogger(XmlService.class);
    private final Map<Integer, SatzXml> satzarten = new HashMap<Integer, SatzXml>();

    /**
     * Instantiiert einen XML-Service.
     *
     * @param parser the parser
     * @throws XMLStreamException the XML stream exception
     */
    public XmlService(final XMLEventReader parser) throws XMLStreamException {
        this(parser, XmlHelper.getNextStartElement(parser));
    }

    /**
     * Instantiiert einen XML-Service.
     *
     * @param parser the parser
     * @param startElement the start element
     * @throws XMLStreamException the XML stream exception
     */
    public XmlService(final XMLEventReader parser, final StartElement startElement)throws XMLStreamException  {
        parse(startElement, parser);
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
        if ("satzarten".equals(name.getLocalPart())) {
            parseSatzarten(element, reader);
        } else if ("felder".equals(name.getLocalPart())) {
            parseFelder(element, reader);
        } else {
            XmlHelper.ignore(name, reader);
        }
    }

    private void parseSatzarten(final StartElement element, final XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (XmlHelper.isStartElement(event, "satzart")) {
                SatzXml satz = new SatzXml(reader, event.asStartElement());
                this.satzarten.put(satz.getSatzart(), satz);
            } else if (XmlHelper.isEndElement(event, element.getName())) {
                LOG.debug("{} satzarten successful parsed.", this.satzarten.size());
                return;
            }
        }
        throw new XMLStreamException("end of " + element + " not found");
    }

    /**
     * Liest die &lt;felder&gt;-Elemente ein und liefert sie als Map zurueck.
     *
     * @param reader the reader
     * @return Map mit den Feldern
     * @throws XMLStreamException the XML stream exception
     */
    public static Map<String, FeldXml> parseFelder(final XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (XmlHelper.isStartElement(event, "felder")) {
                return parseFelder(event.asStartElement(), reader);
            }
            LOG.trace("Event {} is ignored.", event);
        }
        throw new XMLStreamException("<felder>...</felder> not found");
    }

    private static Map<String, FeldXml> parseFelder(final StartElement element, final XMLEventReader reader) throws XMLStreamException {
        LOG.trace("Element {} will be parsed.", element);
        Map<String, FeldXml> felder = new HashMap<String, FeldXml>();
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                FeldXml feld = new FeldXml(reader, event.asStartElement());
                felder.put(feld.getId(), feld);
            } else if (XmlHelper.isEndElement(event, element.getName())) {
                LOG.debug("{} felder between {}...{} successful parsed.", felder.size(), element, event);
                return felder;
            }
        }
        throw new XMLStreamException("end of " + element + " not found");
    }

    /**
     * Liefert den Satz zur gewuenschten Satzart
     *
     * @param satzart z.B. 100 fuer Satz100 (Adressteil)
     * @return die entsprechende Satzart
     */
    public SatzXml getSatzart(final int satzart) {
        return this.satzarten.get(satzart);
    }

}
