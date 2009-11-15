/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 13.11.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.util;

import gdv.xport.config.ConfigException;
import gdv.xport.feld.Feld;
import gdv.xport.satz.*;

import java.io.*;
import java.util.*;

import javax.xml.stream.*;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.*;

/**
 * Diese Klasse dient dazu, um die verschiedenen Saetze und Felder in einer
 * XML-Struktur ausgeben zu koennen.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.2 (13.11.2009)
 */
public class XmlFormatter {

    private static final Log log = LogFactory.getLog(XmlFormatter.class);
    private static final XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
    private final XMLStreamWriter xmlStreamWriter;

    /**
     * Der einzige Konstruktor.
     * 
     * @param writer the writer
     */
    public XmlFormatter(Writer writer) {
        try {
            this.xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(writer);
        } catch (XMLStreamException e) {
            throw new RuntimeException("you should never see this", e);
        } catch (FactoryConfigurationError e) {
            throw new ConfigException("XML problems", e);
        }
    }
    
    /**
     * Instantiates a new xml formatter.
     * 
     * @param xmlStreamWriter the xml stream writer
     */
    public XmlFormatter(XMLStreamWriter xmlStreamWriter) {
        this.xmlStreamWriter = xmlStreamWriter;
    }

    /**
     * Ausgabe eines Feldes als XML.
     * 
     * @param feld the feld
     * 
     * @throws XMLStreamException Signals that an I/O exception has occurred.
     */
    public void write(Feld feld) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("feld");
        String s = new Formatter().format("%3d-%3d", feld.getByteAdresse(), feld.getEndAdresse())
                .toString();
        xmlStreamWriter.writeAttribute("bytes", s);
        s = new Formatter().format("%-30.30s", feld.getBezeichnung()).toString();
        xmlStreamWriter.writeAttribute("bezeichnung", s);
        xmlStreamWriter.writeCharacters(feld.getInhalt());
        xmlStreamWriter.writeEndElement();
    }
    
    /**
     * Ausgabe eines Teildatensatzes als XML.
     * 
     * @param teildatensatz the teildatensatz
     * 
     * @throws XMLStreamException the XML stream exception
     */
    public void write(Teildatensatz teildatensatz) throws XMLStreamException {
        write(teildatensatz, 0);
    }
    
    private void write(Teildatensatz teildatensatz, int level) throws XMLStreamException {
        writeIndent(level);
        xmlStreamWriter.writeStartElement("teildatensatz");
        xmlStreamWriter.writeAttribute("nr", teildatensatz.getNummer().getInhalt());
        xmlStreamWriter.writeCharacters("\n");
        for (Iterator<Feld> iterator = teildatensatz.getFelder().iterator(); iterator.hasNext();) {
            writeIndent(level+1);
            Feld feld = iterator.next();
            write(feld);
            xmlStreamWriter.writeCharacters("\n");
        }
        writeIndent(level);
        xmlStreamWriter.writeEndElement();        
    }
    
    public void write(Satz satz) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("satz");
        xmlStreamWriter.writeAttribute("satzart", satz.getSatzart().getInhalt());
        if (satz instanceof Datensatz) {
            Datensatz datensatz = (Datensatz) satz;
            xmlStreamWriter.writeAttribute("sparte", datensatz.getSparte().getInhalt());
        }
        xmlStreamWriter.writeCharacters("\n");
        Teildatensatz[] teildatensaetze = satz.getTeildatensaetze();
        for (int i = 0; i < teildatensaetze.length; i++) {
            write(teildatensaetze[i], 1);
        }
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeEndElement();
    }
    
    /**
     * Wandelt das uebergebenen Feld in einen XML-String um.
     * 
     * @param feld ein Feld
     * 
     * @return das Feld als XML-String
     */
    public static String toString(final Feld feld) {
        StringWriter swriter = new StringWriter();
        XmlFormatter formatter = new XmlFormatter(swriter);
        try {
            formatter.write(feld);
        } catch (XMLStreamException shouldnothappen) {
            throw new RuntimeException("can't convert " + feld + " to String", shouldnothappen);
        }
        IOUtils.closeQuietly(swriter);
        return swriter.toString();
    }
    
    /**
     * Wandelt dens uebergebenen Teildatensatz in einen XML-String um.
     * 
     * @param Teildatensatz ein Teildatensatz
     * 
     * @return Teildatensatz als XML-String
     */
    public static String toString(final Teildatensatz teildatensatz) {
        StringWriter swriter = new StringWriter();
        XmlFormatter formatter = new XmlFormatter(swriter);
        try {
            formatter.write(teildatensatz);
        } catch (XMLStreamException shouldnothappen) {
            throw new RuntimeException("can't convert " + teildatensatz + " to String",
                    shouldnothappen);
        }
        IOUtils.closeQuietly(swriter);
        return swriter.toString();
    }
    
    /**
     * Wandelt den uebergebenen Satz in einen XML-String um.
     * 
     * @param satz ein Satz
     * 
     * @return Satz als XML-String
     */
    public static String toString(final Satz satz) {
        StringWriter swriter = new StringWriter();
        XmlFormatter formatter = new XmlFormatter(swriter);
        try {
            formatter.write(satz);
        } catch (XMLStreamException shouldnothappen) {
            throw new RuntimeException("can't convert " + satz + " to String",
                    shouldnothappen);
        }
        IOUtils.closeQuietly(swriter);
        return swriter.toString();
    }
    
    private void writeIndent(int level) {
        try {
            for (int i = 0; i < level; i++) {
                xmlStreamWriter.writeCharacters("  ");
            }
        } catch (XMLStreamException e) {
            log.warn("can't indent " + this, e);
        }
    }

}

