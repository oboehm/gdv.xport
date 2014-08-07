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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

/**
 * Kleine Helper-Klasse fuer den Umgang mit XML.
 *
 * @author ob@aosd.de
 * @since 0.3
 */
public final class XmlHelper {

    private static final Log log = LogFactory.getLog(XmlHelper.class);

    /**
     * Privater Konstruktor, damit diese Klasse nicht instantiiert werden kann
     * (sie hat nur statische Methoden).
     */
    private XmlHelper() {}

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
        log.debug("validating " + xmlString + "...");
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

}
