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

import gdv.xport.config.Config;
import gdv.xport.util.NotRegisteredException;
import gdv.xport.util.NotUniqueException;
import gdv.xport.util.SatzTyp;
import gdv.xport.util.XmlHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import patterntesting.runtime.log.LogWatch;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hier wird jetzt eine XML-Beschreibung verwendet, um die Saetze fuer die
 * einzelnen Satzarten aufzusetzen. Als Basis fuer die XML-Beschreibung wurde
 * urspruenglich die Datei "VUVM2013_010713.xml" genommen, die ueber <a
 * href="http://www.gdv-online.de/vuvm/bestand/best_2013.htm"
 * >www.gdv-online.de</a> heruntergeladen werden kann. Inzwischen wird die
 * aktuelle "VUVM2018.xml" verwendet.
 *
 * @author oliver
 * @since 1.0 (15.08.2014)
 */
public class XmlService {

    private static final Logger LOG = LogManager.getLogger(XmlService.class);
    private static final Map<Config, XmlService> INSTANCES = new HashMap<>();
    private final Config config;
    private final List<SatzXml> saetze = new ArrayList<>();
    private final Map<SatzTyp, SatzXml> satzarten = new HashMap<>();
    private final Map<String, FeldXml> felder = new HashMap<>();
  private String gdvRelease = "";

    /**
     * Liefert einen Service anhand des Standard-XML-Handbuchs von 2018.
     *
     * @return der frisch instantiierte XmlService
     */
    public static XmlService getInstance() {
        String xmlResource = Config.getXmlResource();
        try {
            return getInstance(xmlResource);
        } catch (XMLStreamException | IOException ex) {
            LOG.error("Cannot parse XML from resource '{}':", xmlResource, ex);
            return new XmlService();
        }
    }

    /**
     * Liefert Service-Instanz anhand der uebergebenen Konfiguration. Da der Aufruf
     * des {@link XmlService}-Konstruktors teuer ist und einige Sekunden braucht
     * (2-3 Sekunden auf einem MacBook-Air von 2011), wird ein interner Cache
     * verwendet, um nicht jedesmal die Resource parsen zu muessen.
     *
     * @param cfg gewuenschte Konfiguration
     * @return der frisch instantiierte XmlService
     * @throws XMLStreamException falls die angegebene Resource nicht existiert
     *                            oder nicht interpretiert werden kann
     * @throws IOException        bei Lesefehlern
     */
    public static XmlService getInstance(final Config cfg) throws XMLStreamException, IOException {
        XmlService service = INSTANCES.get(cfg);
        if (service == null) {
            service = createXmlService(cfg);
            INSTANCES.put(cfg, service);
            LOG.info("{} wurde mit Resource {} angelegt.", service, cfg);
        }
        return service;
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
     *                            oder nicht interpretiert werden kann
     * @throws IOException        bei Lesefehlern
     */
    public static XmlService getInstance(final String resource) throws XMLStreamException, IOException {
        return getInstance(Config.getInstance().withProperty("gdv.XML-Resource", resource));
    }

    /**
     * Dies ist die allgemeinere Variante von {@link #getInstance(String)},
     * die eine URI als Parameter erwartet. Dies kann die URI zu einer
     * Classpath-Resource oder zu einer Datei sein, theoretisch aber auch
     * eine URI zu einer Web-Resource.
     *
     * @param resource z.B. eine File-URI
     * @return der frisch instantiierte XmlService
     * @throws XMLStreamException falls die angegebene Resource nicht existiert             oder nicht interpretiert werden kann
     * @throws IOException        bei Lesefehlern
     * @since 5.0
     */
    public static XmlService getInstance(URI resource) throws XMLStreamException, IOException {
        Config cfg = Config.getInstance().withProperty("gdv.XML-Resource", resource.toString());
        XmlService service = INSTANCES.get(cfg);
        if (service == null) {
            service = createXmlService(resource, cfg);
            INSTANCES.put(cfg, service);
            LOG.info("{} wurde mit Resource {} angelegt.", service, resource);
        }
        return service;
    }

    private static XmlService createXmlService(URI resource, Config cfg) throws XMLStreamException, IOException {
        try (InputStream istream = resource.toURL().openStream()) {
            return createXmlService(istream, cfg);
        }
    }

    private static XmlService createXmlService(Config cfg) throws XMLStreamException, IOException {
        String resource = cfg.getProperty("gdv.XML-Resource", "VUVM2018.xml");
        try (InputStream istream = XmlService.class.getResourceAsStream(resource)) {
            if (istream == null) {
                throw new XMLStreamException("resource '" + resource + "' not found");
            }
            return createXmlService(istream, cfg);
        }
    }

    private static XmlService createXmlService(InputStream istream, Config cfg) throws XMLStreamException {
        XMLEventReader parser = createXMLEventReader(istream);
        try {
            return new XmlService(parser, cfg);
        } finally {
            parser.close();
        }
    }

    private static XMLEventReader createXMLEventReader(InputStream istream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
        return factory.createXMLEventReader(istream);
    }

    /** Only for internal fallback. */
    private XmlService() {
        LOG.debug("Default XmlService created.");
        this.config = Config.EMPTY;
    }

    /**
     * Instantiiert einen XML-Service.
     *
     * @param parser the parser
     * @throws XMLStreamException the XML stream exception
     */
    public XmlService(final XMLEventReader parser) throws XMLStreamException {
        this(parser, Config.getInstance());
    }

    private XmlService(final XMLEventReader parser, final Config config) throws XMLStreamException {
        this(parser, XmlHelper.getNextStartElement(parser), config);
    }

    /**
     * Instantiiert einen XML-Service.
     *
     * @param parser the parser
     * @param startElement the start element
     * @throws XMLStreamException the XML stream exception
     */
    public XmlService(final XMLEventReader parser, final StartElement startElement) throws XMLStreamException  {
        this(parser, startElement, Config.getInstance());
    }

    public XmlService(final XMLEventReader parser, final StartElement startElement, final Config config) throws XMLStreamException {
        this.config = config;
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
    if ("info".equals(name.getLocalPart()))
    {
      parseInfo(element, reader);
    }
    else if ("satzarten".equals(name.getLocalPart()))
    {
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
                SatzXml satz = new SatzXml(reader, event.asStartElement(), config);
                this.saetze.add(satz);
                LOG.debug("Satz {} added .", satz);
            } else if (XmlHelper.isEndElement(event, element.getName())) {
                LOG.debug("{} satzarten successful parsed.", this.satzarten.size());
                return;
            }
        }
        throw new XMLStreamException("end of " + element + " not found");
    }

  private void parseInfo(final StartElement element,
      final XMLEventReader reader) throws XMLStreamException
  {
    while (reader.hasNext())
    {
      XMLEvent event = reader.nextEvent();
      if (XmlHelper.isStartElement(event, "stand"))
      {
        if (reader.hasNext())
        {
          event = reader.nextEvent();
          if (event.isCharacters())
          {
            this.gdvRelease = event.asCharacters()
                .getData();
            LOG.info(this.gdvRelease);
          }
        }

        LOG.debug("GdvRelease '{}' found.", this.gdvRelease);
      }
      else if (XmlHelper.isEndElement(event, element.getName()))
      {
        LOG.debug("GdvRelease {} successful parsed. ", this.gdvRelease);
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
            registerSatzart(satz);
        }
    }

    /**
     * Ueber diese Methode kann man eigene Satzarten an der aktuellen Instanz
     * registrieren. Eigene Satzarten duerfen im Bereich von 800 - 900 liegen.
     *
     * @param uri URI der Resource (z.B. "classpath:/mein/satz.xml")
     * @throws IOException        the io exception
     * @throws XMLStreamException the xml stream exception
     * @since 6.1
     */
    public void registerSatzart(URI uri) throws XMLStreamException, IOException {
        registerSatzart(SatzXml.of(uri));
    }

    private void registerSatzart(SatzXml satz) {
        List<SatzTyp> supportedSatzTypen = satz.getSupportedSatzTypen();
        if (supportedSatzTypen.isEmpty()) {
            registerSatzart(SatzTyp.of(satz.getGdvSatzartName()), satz);
        } else {
            for (SatzTyp type : supportedSatzTypen) {
                registerSatzart(type, satz);
                LOG.trace("Satz {} registered as {}.", satz, type);
            }
        }
    }

    private void registerSatzart(SatzTyp type, SatzXml satz) {
        satz.init(type);
        this.satzarten.put(type, satz);
    }

    /**
     * Liefert den Satz zur gewuenschten Satzart. Falls es mehr wie einen Satz zur
     * gesuchten Satzart gibt (d.h. wenn es mehrere Sparten gibt), wird eine
     * {@link NotUniqueException} geworfen.
     * <p>
     * Urspruenglich war diese Methoden fuer Satzarten wie 100 oder 200 vorgesehen,
     * die keine Sparte besitzen. Fuer andere Satzarten wird deswegen eine
     * {@link NotUniqueException} geworfen, da die Methode dafuer nicht verwendet
     * werden soll. Seit v0.9 ist die durch {@link SatzTyp} gekapselt.
     * </p>
     *
     * @param satzart z.B. 100 fuer Satz100 (Adressteil)
     * @return die entsprechende Satzart
     * @deprecated wird durch {@link #getSatzart(SatzTyp)} abgeloest
     */
    @Deprecated
    public SatzXml getSatzart(final int satzart)  {
        SatzXml satz = this.satzarten.get(SatzTyp.of(Integer.toString(satzart)));
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
            if (first.getSparte() != satzTypen.get(i)
                                              .getSparte()) {
                throw new NotUniqueException("Sparte for Satzart " + first.getSatzart()
                        + " is missing: " + satzTypen);
            }
        }
    }

    /**
     * Liefert den Satz zur gewuenschten Satzart.
     * Dabei koennen auch Satzarten wie "0350.030", die es so direkt nicht gibt
     * in der XML-Beschreibung, angefordert werden.
     * <p>
     * Um Nebeneffekte zu vermeiden wird jedesmal ein neuer Satz erzeugt und
     * zurueckgeliefert.
     * </p>
     * <p>
     * TODO: Aufteilen in getSatzart(SatzTyp) und getSatzart(SatzTyp, int sparte)
     *       Vermutlich gehoert diese Funktionalitaet auch eher in die SatzFactory.
     *       Problem hierbei ist, dass es Satzarten wie "0211.170" gibt, in
     *       die verschiedene Satzarten erlauben. D.h. "170" ist hier nicht die
     *       Sparte, sondern steht eher als Sammelnummer fuer verschiedene
     *       Satzarten.
     * </p>
     *
     * @param satzNr z.B. SatzTyp.of("0100") fuer Satz100 (Adressteil)
     * @return die entsprechende Satzart
     */
    public SatzXml getSatzart(final SatzTyp satzNr) {
        SatzXml satz = satzarten.get(satzNr);
        if (satz == null) {
            throw new NotRegisteredException(satzNr);
        }
        return new SatzXml(satz);
    }

    /**
     * Liefert die registrierten Satzarten.
     *
     * @return Satzarten als Hashmap
     * @since 2.1.4
     */
    public Map<SatzTyp, SatzXml> getSatzarten() {
        Map<SatzTyp, SatzXml> copy = new HashMap<>();
        for (Map.Entry<SatzTyp, SatzXml> entry : satzarten.entrySet()) {
            copy.put(entry.getKey(), new SatzXml(entry.getValue()));
        }
        return copy;
    }

    /**
     * Liefert die Satzversion eines registrierten Satztyps
     *
     * @param satzTyp die Satzart
     * @return die Version
     * @since 5.0
     */
    public String getSatzVersion(final SatzTyp satzTyp) {
        SatzXml satzXml = this.satzarten.get(satzTyp);
        if (satzXml == null) {
            throw new IllegalArgumentException("nicht registriert: " + satzTyp);
        } else {
            return satzXml.getSatzversion().getInhalt();
        }
    }

    /**
     * Liefert das Release der erzeugten Xml-Saetze
     *
     * @return das Release der erzeugten XmlSaetze
     */
    public String getGdvRelease()
    {
        return this.gdvRelease;
    }

    /**
     * Liefert die eingestellt Config zurueck.
     *
     * @return eingestellte Config
     * @since 6.1
     */
    public Config getConfig() {
        return this.config;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (Stand " + getGdvRelease() + ")";
    }

}
