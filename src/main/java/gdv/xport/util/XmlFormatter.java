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

import gdv.xport.Datenpaket;
import gdv.xport.config.*;
import gdv.xport.feld.*;
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
    /** den brauchen wir fuer close() */
    private final Writer writer;

    /**
     * Der einzige Konstruktor.
     *
     * @param writer the writer
     */
    public XmlFormatter(Writer writer) {
        try {
            this.xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(writer);
            this.writer = writer;
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
        this.writer = null;
    }
    
    public XmlFormatter(File file) throws IOException {
        this(new FileWriter(file));
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

    /**
     * Ausgabe eines Datensatzes als XML.
     *
     * @param satz der auszugebende (Daten-)Satz
     *
     * @throws XMLStreamException the XML stream exception
     */
    public void write(Satz satz) throws XMLStreamException {
        write(satz, 0);
    }

    private void write(Satz satz, int level) throws XMLStreamException {
        writeIndent(level);
        xmlStreamWriter.writeStartElement("satz");
        xmlStreamWriter.writeAttribute("satzart", satz.getSatzart().getInhalt());
        if (satz instanceof Datensatz) {
            Datensatz datensatz = (Datensatz) satz;
            xmlStreamWriter.writeAttribute("sparte", datensatz.getSparte().getInhalt());
        }
        xmlStreamWriter.writeCharacters("\n");
        for (Iterator<Teildatensatz> iterator = satz.getTeildatensaetze().iterator(); iterator
                .hasNext();) {
            Teildatensatz teildatensatz = iterator.next();
            write(teildatensatz, level + 1);
            xmlStreamWriter.writeCharacters("\n");
        }
        writeIndent(level);
        xmlStreamWriter.writeEndElement();
    }

    /**
     * Ausgabe eines kompletten Datenpakets als XML.
     *
     * @param datenpaket
     * @throws XMLStreamException
     */
    public void write(Datenpaket datenpaket) throws XMLStreamException {
        long t0 = System.currentTimeMillis();
        xmlStreamWriter.writeStartDocument(Config.DEFAULT_ENCODING.name(), "1.0");
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeStartElement("datenpaket");
        xmlStreamWriter.writeCharacters("\n");
        write(datenpaket.getVorsatz(), 1);
        xmlStreamWriter.writeCharacters("\n");
        for (Iterator<Datensatz> iterator = datenpaket.getDatensaetze().iterator(); iterator.hasNext(); ) {
            Datensatz datensatz = iterator.next();
            write(datensatz, 1);
            xmlStreamWriter.writeCharacters("\n");
        }
        write(datenpaket.getNachsatz(), 1);
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeComment(" (c)reated by gdv-xport in "
                + (System.currentTimeMillis() - t0) + " ms ");
        xmlStreamWriter.writeEndDocument();
    }
    
    /**
     * Falls man diese Klasse mit dem File-Konstruktor geoeffnet hat, sollte
     * man den Stream hierueber wieder schliessen.
     * 
     * @since 0.3
     * @throws IOException
     */
    public void close() throws IOException {
        try {
            this.xmlStreamWriter.close();
            this.writer.close();
        } catch (XMLStreamException e) {
            throw new IOException("can't close " + this.xmlStreamWriter, e);
        }
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
     * @param teildatensatz
     *            ein Teildatensatz
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

    /**
     * Wandelt das uebergebene Datenpaket in einen XML-String um.
     *
     * @param datenpaket das Datenpaket
     *
     * @return Datenpaket als XML-String
     */
    public static String toString(final Datenpaket datenpaket) {
        StringWriter swriter = new StringWriter();
        XmlFormatter formatter = new XmlFormatter(swriter);
        try {
            formatter.write(datenpaket);
        } catch (XMLStreamException shouldnothappen) {
            throw new RuntimeException("can't convert " + datenpaket + " to String",
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

