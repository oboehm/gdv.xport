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

import com.fasterxml.jackson.annotation.JsonIgnore;
import gdv.xport.config.Config;
import gdv.xport.satz.Datensatz;
import gdv.xport.util.SatzTyp;
import gdv.xport.util.XmlHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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
        this(parser, XmlHelper.getNextStartElement("satzart", parser), Config.getInstance());
    }

    /**
     * Instantiiert einen neuen Satz.
     *
     * @param parser XML-Event-Parser
     * @param element the element
     * @param config Konfiguration
     * @throws XMLStreamException the XML stream exception
     */
    public SatzXml(final XMLEventReader parser, final StartElement element, final Config config) throws XMLStreamException {
        super(SatzTyp.of(0), 0, config);
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

  private void parseElement(final StartElement element, final XMLEventReader reader) throws XMLStreamException  {
    LOG.trace("Parsing element {}.", element);
    QName name = element.getName();

    if ("satzanfang".equals(name.getLocalPart()))  {
      parseTeildatensatz(element, reader);
    } else if ("feldreferenz".equals(name.getLocalPart()))  {
      parseFeldreferenz(element, reader);
    }
    else if ("version".equals(name.getLocalPart()))  {
      parseSatzversion(reader);
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
        TeildatensatzXml tds = new TeildatensatzXml(this, nr);
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

  private void parseFeldreferenz(StartElement element, final XMLEventReader reader) throws XMLStreamException  {
    FeldReferenz referenz = new FeldReferenz(reader, element);
    if (referenz.hasAuspraegung()) {
      if ("Satzart".equals(referenz.getName())) {
        this.getSatzartFeld().setInhalt(referenz.getAuspraegung());

        this.setGdvSatzartName(referenz.getAuspraegung());
      } else if ("Sparte".equals(referenz.getName())) {
        this.setSparte(referenz.getAuspraegung());

        this.setGdvSatzartName(referenz.getAuspraegung());
        LOG.debug("Sparte: " + referenz.getAuspraegung());
      }
      else if ("Satznummer".equals(referenz.getName()))
      {
        this.setGdvSatzartNummer(referenz.getAuspraegung());

        this.setGdvSatzartName(referenz.getAuspraegung());
        LOG.debug("Satznummer: " + referenz.getAuspraegung());
      }
    }
  }

    private void parseSatzversion(XMLEventReader reader) throws XMLStreamException {
        if (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isCharacters()) {
                this.getSatzversion().setInhalt(event.asCharacters().getData());
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
        for (int n = 1; n <= this.getNumberOfTeildatensaetze(); n++) {
            TeildatensatzXml tdsXml = (TeildatensatzXml) this.getTeildatensatz(n);
            tdsXml.updateWith(felder);
            updateSparte(tdsXml);
        }
    }

    private void updateSparte(TeildatensatzXml tdsXml) {
        if (tdsXml.hasSparte() && tdsXml.getSparte() == 0) {
            tdsXml.setSparte(getSatzTyp().getSparte());
        }
    }

    /**
     * Liefert eine Liste der unterstuetzten Satz-Typen. Dies ist vor allem fuer
     * Satz 220 Sparte 10 von Bedeutung, die verschienden Wagnisarten
     * unterstuetzen koennen.
     *
     * @return the supported satz typen
     */
    @JsonIgnore
    public List<SatzTyp> getSupportedSatzTypen() {
        List<SatzTyp> satzTypen = new ArrayList<>();
        satzTypen.add(SatzTyp.of(this.getGdvSatzartName()));
        return satzTypen;
    }

    /**
     * Hier kann man mithilfe einer XML-Beschreibung einen Satz generieren.
     * Diese Methode dient dazu, um die Notwendigkeit der Enum-Beschreibung
     * weiter zu reduzieren.
     *
     * @param file Datei mit XML-Beschreibung
     * @return einen Satz gemaess der XML-Beschreibung
     * @throws IOException        the io exception
     * @throws XMLStreamException the xml stream exception
     * @since 5.0
     */
    public static SatzXml of(File file) throws IOException, XMLStreamException {
        try (InputStream istream = new FileInputStream(file)) {
            return createSatzXml(istream);
        }
    }

    /**
     * Hier kann man mithile einer XML-Beschreibung einen Satz generieren.
     * Diese Methode dient dazu, um die Notwendigkeit der Enum-Beschreibung
     * weiter zu reduzieren.
     *
     * @param resource Classpath-Resource mit XML-Beschreibung
     * @return einen Satz gemaess der XML-Beschreibung
     * @throws IOException        the io exception
     * @throws XMLStreamException the xml stream exception
     * @since 5.0
     */
    public static SatzXml of(String resource) throws IOException, XMLStreamException {
        try (InputStream istream = SatzXml.class.getResourceAsStream(resource)) {
            if (istream == null) {
                throw new IllegalArgumentException("not a resource: " + resource);
            }
            return createSatzXml(istream);
        }
    }

    /**
     * Im Gegensatz zu {@link SatzXml#of(String)} und {@link SatzXml#of(File)}
     * kann hier eine beliebige URI als Parameter uebergeben werden. Allerdings
     * werden momentan nur "classpath:" und "file:" als Protokoll unterstuetzt.
     *
     * @param resource URI der Resource (z.B. "classpath:/mein/satz.xml")
     * @return einen Satz gemaess der XML-Beschreibung
     * @throws IOException        the io exception
     * @throws XMLStreamException the xml stream exception
     * @since 6.1
     */
    public static SatzXml of(URI resource) throws IOException, XMLStreamException {
        String scheme = resource.getScheme();
        switch (scheme.toLowerCase()) {
            case "classpath":
                return of(resource.getPath());
            case "file":
                return of(new File(resource));
            default:
                throw new UnsupportedOperationException("Protokoll '" + scheme + "' wird (noch) nicht unterstuetzt");
        }
    }

    private static SatzXml createSatzXml(InputStream istream) throws XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader parser = xmlInputFactory.createXMLEventReader(istream);
        try {
            SatzXml satz = new SatzXml(parser);
            Map<String, FeldXml> xmlFelder = XmlService.parseFelder(parser);
            satz.setFelder(xmlFelder);
            return satz;
        } finally {
            parser.close();
        }
    }

}
