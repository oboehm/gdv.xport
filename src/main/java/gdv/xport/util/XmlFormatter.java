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
import gdv.xport.feld.Feld;
import gdv.xport.satz.*;

import java.io.*;
import java.util.*;

import javax.xml.stream.*;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.*;

/**
 * Diese Klasse dient dazu, um die verschiedenen Saetze und Felder in einer XML-Struktur ausgeben zu koennen.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.2 (13.11.2009)
 */
public final class XmlFormatter extends AbstractFormatter {

    private static final Log log = LogFactory.getLog(XmlFormatter.class);
    private static final XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
    /** Writer fuer die XML-Ausgabe. */
    private XMLStreamWriter xmlStreamWriter;

    /**
     * Default-Konstruktor.
     * 
     * @since 0.5.0
     */
    public XmlFormatter() {
        super();
    }

    /**
     * Der Konstruktor fuer die normale Arbeit.
     * 
     * @param writer
     *            the writer
     */
    public XmlFormatter(final Writer writer) {
        super(writer);
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
     * @param xmlStreamWriter
     *            the xml stream writer
     */
    public XmlFormatter(final XMLStreamWriter xmlStreamWriter) {
        this.xmlStreamWriter = xmlStreamWriter;
    }

    /**
     * @param file
     *            Ausgabe-Datein
     * @throws IOException
     *             falls die uebergebene Date nicht existiert
     */
    public XmlFormatter(final File file) throws IOException {
        this(new FileOutputStream(file));
    }

    /**
     * @since 0.3
     * @param ostream
     *            z.B. System.out
     */
    public XmlFormatter(final OutputStream ostream) {
        super(new OutputStreamWriter(ostream, Config.DEFAULT_ENCODING));
        try {
            this.xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(ostream, Config.DEFAULT_ENCODING.name());
        } catch (XMLStreamException e) {
            throw new RuntimeException("you should never see this", e);
        } catch (FactoryConfigurationError e) {
            throw new ConfigException("XML problems", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see gdv.xport.util.AbstractFormatter#setWriter(java.io.Writer)
     */
    @Override
    public void setWriter(final Writer writer) {
        super.setWriter(writer);
        try {
            this.xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(writer);
        } catch (XMLStreamException e) {
            throw new IllegalArgumentException("can't create XmlStreamWriter with " + writer);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see gdv.xport.util.AbstractFormatter#setWriter(java.io.OutputStream)
     */
    @Override
    public void setWriter(final OutputStream ostream) {
        super.setWriter(ostream);
        try {
            this.xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(ostream, Config.DEFAULT_ENCODING.name());
        } catch (XMLStreamException e) {
            throw new IllegalArgumentException("can't create XmlStreamWriter with " + ostream);
        }
    }

    /**
     * Ausgabe eines Feldes als XML.
     * 
     * @param feld
     *            the feld
     * 
     * @throws XMLStreamException
     *             Signals that an I/O exception has occurred.
     */
    public void write(final Feld feld) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("feld");
        String s = new Formatter().format("%3d-%3d", feld.getByteAdresse(), feld.getEndAdresse()).toString();
        xmlStreamWriter.writeAttribute("bytes", s);
        s = new Formatter().format("%-30.30s", feld.getBezeichnung()).toString();
        xmlStreamWriter.writeAttribute("bezeichnung", s);
        xmlStreamWriter.writeCharacters(feld.getInhalt());
        xmlStreamWriter.writeEndElement();
    }

    /**
     * Ausgabe eines Teildatensatzes als XML.
     * 
     * @param teildatensatz
     *            the teildatensatz
     * 
     * @throws XMLStreamException
     *             the XML stream exception
     */
    public void write(final Teildatensatz teildatensatz) throws XMLStreamException {
        write(teildatensatz, 0);
    }

    private void write(final Teildatensatz teildatensatz, final int level) throws XMLStreamException {
        writeIndent(level);
        xmlStreamWriter.writeStartElement("teildatensatz");
        xmlStreamWriter.writeAttribute("nr", teildatensatz.getNummer().getInhalt());
        xmlStreamWriter.writeCharacters("\n");
        for (Iterator<Feld> iterator = teildatensatz.getFelder().iterator(); iterator.hasNext();) {
            writeIndent(level + 1);
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
     * @param satz
     *            der auszugebende (Daten-)Satz
     * 
     * @throws XMLStreamException
     *             the XML stream exception
     */
    public void write(final Satz satz) throws XMLStreamException {
        write(satz, 0);
    }

    private void write(final Satz satz, final int level) throws XMLStreamException {
        writeIndent(level);
        xmlStreamWriter.writeStartElement("satz");
        xmlStreamWriter.writeAttribute("satzart", satz.getSatzartFeld().getInhalt());
        if (satz instanceof Datensatz) {
            Datensatz datensatz = (Datensatz) satz;
            xmlStreamWriter.writeAttribute("sparte", datensatz.getSparteFeld().getInhalt());
        }
        xmlStreamWriter.writeCharacters("\n");
        for (Iterator<Teildatensatz> iterator = satz.getTeildatensaetze().iterator(); iterator.hasNext();) {
            Teildatensatz teildatensatz = iterator.next();
            write(teildatensatz, level + 1);
            xmlStreamWriter.writeCharacters("\n");
        }
        writeIndent(level);
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.flush();
    }

    /**
     * Ausgabe eines kompletten Datenpakets als XML.
     * 
     * @param datenpaket
     *            Datenpaket, das als XML ausgegeben werden soll
     * @throws IOException
     *             bei Problemen mit der XML-Generierung
     */
    @Override
    public void write(final Datenpaket datenpaket) throws IOException {
        long t0 = System.currentTimeMillis();
        try {
            xmlStreamWriter.writeStartDocument(Config.DEFAULT_ENCODING.name(), "1.0");
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeStartElement("datenpaket");
            xmlStreamWriter.writeDefaultNamespace("http://labs.agentes.de");
            xmlStreamWriter.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            xmlStreamWriter.writeNamespace("schemaLocation", "http://labs.agentes.de /xsd/datenpaket.xsd");
            xmlStreamWriter.writeCharacters("\n");
            write(datenpaket.getVorsatz(), 1);
            xmlStreamWriter.writeCharacters("\n");
            for (Iterator<Datensatz> iterator = datenpaket.getDatensaetze().iterator(); iterator.hasNext();) {
                Satz datensatz = iterator.next();
                write(datensatz, 1);
                xmlStreamWriter.writeCharacters("\n");
            }
            write(datenpaket.getNachsatz(), 1);
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeComment(" (c)reated by gdv-xport in " + (System.currentTimeMillis() - t0) + " ms ");
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.flush();
        } catch (XMLStreamException e) {
            throw new IOException("XML-Fehler", e);
        }
    }

    /**
     * Falls man diese Klasse mit dem File-Konstruktor geoeffnet hat, sollte man den Stream hierueber wieder schliessen.
     * 
     * @since 0.3
     * @throws IOException
     *             sollte eigentlich nicht vorkommen
     */
    public void close() throws IOException {
        try {
            this.xmlStreamWriter.close();
            this.getWriter().close();
        } catch (XMLStreamException e) {
            throw new IOException("can't close " + this.xmlStreamWriter, e);
        }
    }

    /**
     * Wandelt das uebergebenen Feld in einen XML-String um.
     * 
     * @param feld
     *            ein Feld
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
            throw new RuntimeException("can't convert " + teildatensatz + " to String", shouldnothappen);
        }
        IOUtils.closeQuietly(swriter);
        return swriter.toString();
    }

    /**
     * Wandelt den uebergebenen Satz in einen XML-String um.
     * 
     * @param satz
     *            ein Satz
     * 
     * @return Satz als XML-String
     */
    public static String toString(final Satz satz) {
        StringWriter swriter = new StringWriter();
        XmlFormatter formatter = new XmlFormatter(swriter);
        try {
            formatter.write(satz);
        } catch (XMLStreamException shouldnothappen) {
            throw new RuntimeException("can't convert " + satz + " to String", shouldnothappen);
        }
        IOUtils.closeQuietly(swriter);
        return swriter.toString();
    }

    /**
     * Wandelt das uebergebene Datenpaket in einen XML-String um.
     * 
     * @param datenpaket
     *            das Datenpaket
     * @return Datenpaket als XML-String
     */
    public static String toString(final Datenpaket datenpaket) {
        StringWriter swriter = new StringWriter();
        XmlFormatter formatter = new XmlFormatter(swriter);
        try {
            formatter.write(datenpaket);
        } catch (IOException shouldnothappen) {
            throw new RuntimeException("can't convert " + datenpaket + " to String", shouldnothappen);
        }
        IOUtils.closeQuietly(swriter);
        return swriter.toString();
    }

    /**
     * Diese Methode ist fuer die Einrueckung verantwortlich.
     * 
     * @param level
     *            Einrueckungstiefe
     */
    protected void writeIndent(final int level) {
        try {
            for (int i = 0; i < level; i++) {
                xmlStreamWriter.writeCharacters("  ");
            }
        } catch (XMLStreamException e) {
            log.warn("can't indent " + this, e);
        }
    }

}
