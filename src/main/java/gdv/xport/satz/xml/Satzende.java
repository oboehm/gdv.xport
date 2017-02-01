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
 * (c)reated 30.10.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.logging.log4j.*;

import gdv.xport.util.XmlHelper;

/**
 * Diese Klasse repraesentiert den Wert, der als "&lt;satzende&gt;" ueber XML
 * reinkommt.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (30.10.2014)
 */
public class Satzende {

    private static final Logger LOG = LogManager.getLogger(Satzende.class);
    private final List<FeldReferenz> feldReferenzen = new ArrayList<FeldReferenz>();

    /**
     * Instantiiert eine Objekt mit den Werten, die ueber den uebergebenen
     * Parser gelesen werden.
     *
     * @param parser the parser
     * @throws XMLStreamException the XML stream exception
     */
    public Satzende(final XMLEventReader parser) throws XMLStreamException {
        this(parser, XmlHelper.getNextStartElement(parser));
    }

    /**
     * Instantiiert eine Objekt mit den Werten, die ueber den uebergebenen
     * Parser gelesen werden.
     *
     * @param parser the parser
     * @param element das Start-Element &lt;feldreferenz referenz=... &gt;
     * @throws XMLStreamException the XML stream exception
     */
    public Satzende(final XMLEventReader parser, final StartElement element) throws XMLStreamException {
        parse(element.getName(), parser);
        LOG.debug("{} created.", this);
    }

    private void parse(final QName name, final XMLEventReader parser) throws XMLStreamException {
        while (parser.hasNext()) {
            XMLEvent event = parser.nextEvent();
            if (event.isStartElement()) {
                parseElement(event.asStartElement(), parser);
            } else if (XmlHelper.isEndElement(event, name)) {
                LOG.debug("<{}>...{} read.", name, event);
                return;
            }
        }
        throw new XMLStreamException("end element of <" + name + "> not read");
    }

    private void parseElement(final StartElement element, XMLEventReader reader) throws XMLStreamException {
        LOG.trace("Parsing element {}.", element);
        QName name = element.getName();
        if ("feldreferenz".equals(name.getLocalPart())) {
            feldReferenzen.add(new FeldReferenz(reader, element));
        } else {
            XmlHelper.ignore(name, reader);
        }
    }

    /**
     * Liefert die FeldReferenzen des Satzendes.
     *
     * @return the feld referenzen
     */
    public List<FeldReferenz> getFeldReferenzen() {
        return this.feldReferenzen;
    }

}
