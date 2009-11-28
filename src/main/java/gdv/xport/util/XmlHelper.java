/*
 * $Id: XmlHelper.java,v 1.4 2007/11/05 15:33:50 boehm Exp $
 *
 * Copyright (c) 2007 agentes AG
 * Raepplenstr. 17, 70191 Stuttgart, Germany
 * All rights reserved.
 *
 * (c)reated 12.10.2007 by oliver.boehm@agentes.de
 */

package gdv.xport.util;

import java.io.*;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.apache.commons.logging.*;
import org.xml.sax.SAXException;

/**
 * Helper class for XML
 * 
 * @author oliver.boehm@agentes.de
 * @since 0.3
 */
public class XmlHelper {
	
	private static final Log log = LogFactory.getLog(XmlHelper.class);

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

}
