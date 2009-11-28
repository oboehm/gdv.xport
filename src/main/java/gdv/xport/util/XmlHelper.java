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

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * Helper class for XML
 * 
 * @author oliver.boehm@agentes.de
 * @since 0.3
 */
public class XmlHelper {
	
	private static Logger log = Logger.getLogger(XmlHelper.class);

	public static void validate(String xmlString, String xsdResource)
			throws SAXException, IOException {
		Reader reader = new StringReader(xmlString);
		Source source = new StreamSource(reader);
		log.debug("validating " + xmlString + "...");
		validate(source, xsdResource);
	}

	public static void validate(Source source, String xsdResource)
			throws SAXException, IOException {
		Schema schema = getSchema(xsdResource);
		Validator validator = schema.newValidator();
		validator.validate(source);
	}
	
	public static Schema getSchema(String resource) throws SAXException {
		SchemaFactory factory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		URL xsdURL = XmlHelper.class.getResource(resource);
		return factory.newSchema(xsdURL);
	}

}
