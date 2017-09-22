/*
 * $Id: XmlHelper.java,v 1.4 2007/11/05 15:33:50 boehm Exp $
 *
 * Copyright (c) 2007 agentes AG
 * Raepplenstr. 17, 70191 Stuttgart, Germany
 * All rights reserved.
 *
 * (c)reated 12.10.2007 by ob@aosd.de
 */

package gdv.xport.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.Properties;

/**
 * Kleine Helper-Klasse fuer den Umgang mit XML.
 *
 * @author ob@aosd.de
 * @since 0.3
 */
public final class XmlHelper {

    private static final Logger LOG = LogManager.getLogger(XmlHelper.class);

    /**
     * Privater Konstruktor, damit diese Klasse nicht instantiiert werden kann
     * (sie hat nur statische Methoden).
     */
    private XmlHelper() {
    }

    /**
     * Wird hauptsaechlich zum Testen verwendet, um einen bestehenden
     * XML-String gegen eine XSD validieren zu koennen.
     *
     * @since 0.3
     * @param xmlString der XML-String
     * @param xsdResource z.B. "/gdv/datenpaket.xsd"
     * @throws SAXException bei einem XML-Fehler
     * @throws IOException bei einem Lese-Fehler
     */
    public static void validate(final String xmlString, final String xsdResource)
            throws SAXException, IOException {
        Reader reader = new StringReader(xmlString);
        Source source = new StreamSource(reader);
        LOG.debug("validating " + xmlString + "...");
        validate(source, xsdResource);
    }

    /**
     * Wird hauptsaechlich zum Testen verwendet, um einen bestehende Source
     * gegen eine XSD validieren zu koennen.
     *
     * @since 0.3
     * @param source die Source mit dem XML-String
     * @param xsdResource z.B. "/gdv/datenpaket.xsd"
     * @throws SAXException bei einem XML-Fehler
     * @throws IOException bei einem Lese-Fehler
     */
    public static void validate(final Source source, final String xsdResource) throws SAXException,
            IOException {
        Schema schema = getSchema(xsdResource);
        Validator validator = schema.newValidator();
        validator.validate(source);
    }

    /**
     * Um das Schema zur uebergebenen Resource zu bekommen.
     *
     * @since 0.3
     * @param resource z.B. "/gdv/datenpaket.xsd"
     * @return das entsprechende Schema
     * @throws SAXException bei einem XML-Fehler
     */
    public static Schema getSchema(final String resource) throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        URL xsdURL = XmlHelper.class.getResource(resource);
        return factory.newSchema(xsdURL);
    }



    /////   XML-Streams Utilities   ///////////////////////////////////////////

    /**
     * Wandelt einfache XML-Elemente in Properties um. Einfache Elemente sind
     * z.B.:
     *
     * <pre>
     * &lt;feldreferenz referenz="BN-2003.02.11.22.49.47.214"&gt;
     *     &lt;name&gt;Satzart&lt;/name&gt;
     *     &lt;technischerName&gt;Satzart&lt;/technischerName&gt;
     *     &lt;auspraegung&gt;0100&lt;/auspraegung&gt;
     * &lt;/feldreferenz&gt;
     * </pre>
     *
     * @param name the name
     * @param reader the reader
     * @return the properties
     * @throws XMLStreamException the XML stream exception
     */
    public static Properties parseSimpleElements(final QName name, final XMLEventReader reader)
            throws XMLStreamException {
        LOG.trace("Parsing simple elements of <{}>...<{}/>.", name, name);
        Properties props = new Properties();
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                addAsProperty(event.asStartElement(), reader, props);
            } else if (isEndElement(event, name)) {
                LOG.debug("{} elements of <{}>...{} were returned as properties.", props.size(), name, event);
                return props;
            }
        }
        throw new XMLStreamException("end element of <" + name + "> not read");
    }

    private static void addAsProperty(final StartElement element, final XMLEventReader reader, final Properties props)
            throws XMLStreamException {
        QName name = element.getName();
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.CHARACTERS:
                    String key = name.getLocalPart();
                    String value = props.getProperty(key, "") + "\n" + event.asCharacters().getData().trim();
                    props.setProperty(key, value.trim());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    return;
                case XMLStreamConstants.START_ELEMENT:
                    ignore(event, reader);
                    break;
                default:
                    LOG.trace("Event {} is ignored.", event);
                    break;
            }
        }
        throw new XMLStreamException("end element of <" + name + "> not read");
    }

    /**
     * Ueberprueft, ob es ein Start-Event mit dem uebergebenen Namen ist.
     *
     * @param event der XML-Event
     * @param name Name des Start-Elements
     * @return true, falls es ein Start-Element ist
     * @since 1.0
     */
    public static boolean isStartElement(final XMLEvent event, final String name) {
        if (event.isStartElement()) {
            return event.asStartElement().getName().getLocalPart().equals(name);
        } else {
            return false;
        }
    }

    /**
     * Ueberprueft, ob es ein End-Event mit dem uebergebenen Namen ist.
     *
     * @param event der XML-Event
     * @param name Name des Start-Elements
     * @return true, falls es ein End-Element ist
     */
    public static boolean isEndElement(final XMLEvent event, final QName name) {
        return event.isEndElement() && event.asEndElement().getName().equals(name);
    }

    /**
     * Holt sich das naechste Start-Element aus dem uebergebenen XML-Stream.
     *
     * @param name gesuchtes Start-Element
     * @param reader the reader
     * @return the next start element
     * @throws XMLStreamException the XML stream exception
     */
    public static StartElement getNextStartElement(final String name, final XMLEventReader reader)
            throws XMLStreamException {
        while (true) {
            StartElement startElement = getNextStartElement(reader);
            if (name.equals(startElement.getName().getLocalPart())) {
                return startElement;
            }
        }
    }

    /**
     * Holt sich das naechste Start-Element aus dem uebergebenen XML-Stream.
     *
     * @param reader the reader
     * @return the next start element
     * @throws XMLStreamException the XML stream exception
     */
    public static StartElement getNextStartElement(final XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                return event.asStartElement();
            }
            LOG.trace("Event {} is ignored.", event);
        }
        throw new XMLStreamException("no start element found");
    }

    /**
     * Ignoriert die komplette Hierarchie unterhalb von "&lt;name&gt;".
     *
     * @param startEvent the start event
     * @param reader der XML-Stream
     * @throws XMLStreamException the XML stream exception
     */
    public static void ignore(final XMLEvent startEvent, final XMLEventReader reader) throws XMLStreamException {
        if (startEvent.isEndElement()) {
            LOG.debug("Element <{}/> is ignored.", startEvent);
            return;
        }
        ignore(startEvent.asStartElement().getName(), reader);
    }

    /**
     * Ignoriert die komplette Hierarchie unterhalb von "&lt;name&gt;".
     *
     * @param name der Tag-Name (z.B. "feld")
     * @param reader der XML-Stream
     * @throws XMLStreamException the XML stream exception
     */
    public static void ignore(final QName name, final XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (isEndElement(event, name)) {
                LOG.debug("<{}>...{} was ignored.", name, event);
                return;
            }
        }
        throw new XMLStreamException("end of <" + name + "> not found");
    }

}
