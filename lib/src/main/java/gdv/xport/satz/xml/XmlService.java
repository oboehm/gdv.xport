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

import gdv.xport.util.*;
import org.apache.logging.log4j.*;
import patterntesting.runtime.log.*;

import javax.xml.namespace.*;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.util.*;

/**
 * Hier wird jetzt eine XML-Beschreibung verwendet, um die Saetze fuer die
 * einzelnen Satzarten aufzusetzen. Als Basis fuer die XML-Beschreibung wurde
 * urspruenglich die Datei "VUVM2013_010713.xml" genommen, die ueber <a
 * href="http://www.gdv-online.de/vuvm/bestand/best_2013.htm"
 * >www.gdv-online.de</a> heruntergeladen werden kann. Inzwischen wird die
 * aktueller "VUVM2015.xml" verwendet.
 *
 * @author oliver
 * @since 1.0 (15.08.2014)
 */
public class XmlService {

    private static final Logger LOG = LogManager.getLogger(XmlService.class);
    private static final Map<String, XmlService> INSTANCES = new WeakHashMap<>();
    private final List<SatzXml> saetze = new ArrayList<>();
    private final Map<SatzTyp, SatzXml> satzarten = new HashMap<>();
    private final Map<String, FeldXml> felder = new HashMap<>();

    /**
     * Liefert einen Service anhand des Standard-XML-Handbuchs von 2013.
     *
     * @return der frisch instantiierte XmlService
     */
    public static XmlService getInstance() {
        try {
            return getInstance("VUVM2015.xml");
        } catch (XMLStreamException ex) {
            LOG.error("Cannot parse XML from resource 'VUVM2015.xml':", ex);
            return new XmlService();
        }
    }

    /**
     * Liefert Service-Instanz anhand der uebergebenen Resource. Da der Aufruf
     * des {@link XmlService}-Konstruktors teuer ist und einige Sekunden braucht
     * (2-3 Sekunden auf einem MacBook-Air von 2011), wird ein interner Cache
     * verwendet, um nicht jedesmal die Resource parsen zu muessen.
     *
     * @param resource Resource-Name (z.B. "VUVM2013.xml")
     * @return der frisch instantiierte XmlService
     * @throws XMLStreamException falls die angegebene Resource nicht existiert
     *             oder nicht interpretiert werden kann
     */
    public static XmlService getInstance(final String resource) throws XMLStreamException {
        XmlService service = INSTANCES.get(resource);
        if (service == null) {
            XMLEventReader parser = createXMLEventReader(resource);
            try {
                service = new XmlService(parser);
                INSTANCES.put(resource, service);
            } finally {
                parser.close();
            }
        }
        return service;
    }

    private  static XMLEventReader createXMLEventReader(final String resourceName) throws XMLStreamException {
        InputStream istream = XmlService.class.getResourceAsStream(resourceName);
        if (istream == null) {
            throw new XMLStreamException("resource '" + resourceName + "' not found");
        }
        return XMLInputFactory.newInstance().createXMLEventReader(istream);
    }

    /** Only for internal fallback. */
    private XmlService() {
        LOG.debug("Default XmlService created.");
    }

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
        LogWatch watch = new LogWatch();
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                parseElement(event.asStartElement(), reader);
            } else if (XmlHelper.isEndElement(event, element.getName())) {
                LOG.info("{} Satzarten successful parsed from {}...{} in {}.", this.saetze.size(), element, event,
                        watch);
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
            this.felder.putAll(parseFelder(element, reader));
            this.setFelder(felder);
        } else {
            XmlHelper.ignore(name, reader);
        }
    }

    private void parseSatzarten(final StartElement element, final XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (XmlHelper.isStartElement(event, "satzart")) {
                SatzXml satz = new SatzXml(reader, event.asStartElement());
                this.saetze.add(satz);
                LOG.debug("Satz {} added .", satz);
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
        Map<String, FeldXml> felder = new HashMap<>();
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
     * Liefert die Felder mit den Referenzen.
     *
     * @return the felder
     * @since 1.1
     */
    public Map<String, FeldXml> getFelder() {
        return this.felder;
    }

    private void setFelder(Map<String, FeldXml> felder) {
        LOG.debug("Missing felder for {} saetze will be set.", this.satzarten.size());
        for (SatzXml satz : this.saetze) {
            satz.setFelder(felder);
            for (SatzTyp type : satz.getSupportedSatzTypen()) {
                this.satzarten.put(type, satz);
                LOG.trace("Satz {} registered as {}.", satz, type);
            }
        }
    }

    /**
     * Liefert den Satz zur gewuenschten Satzart. Falls es mehr wie einen Satz
     * zur gesuchten Satzart gibt (d.h. wenn es mehrere Sparten gibt), wird
     * eine {@link NotUniqueException} geworfen.
     * <p>
     * Um Nebeneffekte zu vermeiden wird jedesmal ein neuer Satz erzeugt und
     * zurueckgeliefert.
     * </p>
     *
     * @param satzart z.B. 100 fuer Satz100 (Adressteil)
     * @return die entsprechende Satzart
     */
    public SatzXml getSatzart(final int satzart) {
        SatzXml satz = this.satzarten.get(new SatzTyp(satzart));
        if (satz != null) {
            return new SatzXml(satz);
        }
        List<SatzTyp> satzTypen = new ArrayList<>();
        for (SatzTyp satzNr : this.satzarten.keySet()) {
            if (satzNr.getSatzart() == satzart) {
                satzTypen.add(satzNr);
            }
        }
        if (satzTypen.isEmpty()) {
            throw new NotRegisteredException(satzart);
        }
        if (satzTypen.size() > 1) {
            checkSatzarten(satzTypen);
        }
        return new SatzXml(this.satzarten.get(satzTypen.get(0)));
    }

    private static void checkSatzarten(List<SatzTyp> satzTypen) {
        SatzTyp first = satzTypen.get(0);
        for (int i = 1; i < satzTypen.size(); i++) {
            if (first.getSparte() != satzTypen.get(i).getSparte()) {
                throw new NotUniqueException("Sparte for Satzart " + first.getSatzart() + " is missing: " + satzTypen);
            }
        }
    }

    /**
     * Liefert den Satz zur gewuenschten Satzart.
     *
     * @param satzNr z.B. 'new Satznummer(100)' fuer Satz100 (Adressteil)
     * @return die entsprechende Satzart
     */
    public SatzXml getSatzart(final SatzTyp satzNr) {
        SatzXml satz = this.satzarten.get(satzNr);
        if (satz == null) {
            satz = getSatzart(satzNr.getSatzart());
        }
        if (satz == null) {
            throw new NotRegisteredException(satzNr);
        }
        return satz;
    }

    /**
     * Liefert die registrierten Satzarten.
     *
     * @return Satzarten als Hashmap
     * @since 2.1.4
     */
    public Map<SatzTyp, SatzXml> getSatzarten() {
        return this.satzarten;
    }

}
