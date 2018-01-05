/*
 * Copyright (c) 2009 - 2012 by Oli B.
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
 * (c)reated 13.11.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.*;
import gdv.xport.config.*;
import gdv.xport.feld.*;
import gdv.xport.satz.*;
import org.apache.commons.io.*;
import org.apache.commons.io.output.*;
import org.apache.logging.log4j.*;

import javax.xml.stream.*;
import java.io.*;
import java.util.*;

/**
 * Diese Klasse dient dazu, um die verschiedenen Saetze und Felder in einer
 * XML-Struktur ausgeben zu koennen.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.2 (13.11.2009)
 */
public final class XmlFormatter extends AbstractFormatter {

    private static final Logger LOG = LogManager.getLogger(XmlFormatter.class);
    private static final XMLOutputFactory XML_OUTPUT_FACTORY = XMLOutputFactory.newInstance();
    private XMLStreamWriter xmlStreamWriter;

    /**
     * Default-Konstruktor.
     *
     * @since 0.5.0
     */
    public XmlFormatter() {
        this(System.out);
    }

    /**
     * Der Konstruktor fuer die normale Arbeit.
     *
     * @param writer the writer
     */
    public XmlFormatter(final Writer writer) {
        super(writer);
        try {
            this.xmlStreamWriter = XML_OUTPUT_FACTORY.createXMLStreamWriter(writer);
        } catch (XMLStreamException ex) {
            throw new ShitHappenedException("you should never see this", ex);
        } catch (FactoryConfigurationError ex) {
            throw new ConfigException("XML problems", ex);
        }
    }

    /**
     * Instantiiert einen neuen XML-Formatter.
     *
     * @param xmlStreamWriter
     *            the xml stream writer
     */
    public XmlFormatter(final XMLStreamWriter xmlStreamWriter) {
        this.xmlStreamWriter = xmlStreamWriter;
    }

    /**
     * @since 0.3
     * @param ostream
     *            z.B. System.out
     */
    public XmlFormatter(final OutputStream ostream) {
        super(new OutputStreamWriter(ostream, Config.DEFAULT_ENCODING));
        try {
            this.xmlStreamWriter = XML_OUTPUT_FACTORY.createXMLStreamWriter(ostream, Config.DEFAULT_ENCODING.name());
        } catch (XMLStreamException ex) {
            throw new ShitHappenedException("you should never see this", ex);
        } catch (FactoryConfigurationError ex) {
            throw new ConfigException("XML problems", ex);
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
            this.xmlStreamWriter = XML_OUTPUT_FACTORY
                    .createXMLStreamWriter(new WriterOutputStream(writer, Config.DEFAULT_ENCODING),
                            Config.DEFAULT_ENCODING.name());
        } catch (XMLStreamException ex) {
            throw new IllegalArgumentException("can't create XmlStreamWriter with " + writer, ex);
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
            this.xmlStreamWriter = XML_OUTPUT_FACTORY.createXMLStreamWriter(ostream, Config.DEFAULT_ENCODING.name());
        } catch (XMLStreamException ex) {
            throw new IllegalArgumentException("can't create XmlStreamWriter with " + ostream, ex);
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
        write("bytes", "%3d-%3d", feld.getByteAdresse(), feld.getEndAdresse());
        write("bezeichnung", "%-30.30s", feld.getBezeichnung());
        xmlStreamWriter.writeCharacters(feld.getInhalt());
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.flush();
    }

    private void write(final String attribute, final String format, final Object... args) throws XMLStreamException {
        try (Formatter formatter = new Formatter()) {
            String s = formatter.format(format, args).toString();
            xmlStreamWriter.writeAttribute(attribute, s);
        }
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
        for (Feld feld1 : teildatensatz.getFelder()) {
            writeIndent(level + 1);
            Feld feld = feld1;
            write(feld);
            xmlStreamWriter.writeCharacters("\n");
        }
        writeIndent(level);
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.flush();
    }

    /**
     * Ausgabe eines Datensatzes als XML.
     *
     * @param satz der auszugebende (Daten-)Satz
     */
    @Override
    public void write(final Satz satz) throws IOException {
        try {
            if (satz.getSatzart() == 1) {
                this.writeHead();
            }
            write(satz, 1);
            if (satz.getSatzart() == 9999) {
                this.writeTail();
            }
        } catch (XMLStreamException ex) {
            throw new IOException("cannot format " + satz, ex);
        }
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
        for (Teildatensatz teildatensatz : satz.getTeildatensaetze()) {
            write(teildatensatz, level + 1);
            xmlStreamWriter.writeCharacters("\n");
        }
        writeIndent(level);
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.flush();
    }

    private void writeHead() throws XMLStreamException {
        xmlStreamWriter.writeStartDocument(Config.DEFAULT_ENCODING.name(), "1.0");
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeStartElement("datenpaket");
        xmlStreamWriter.writeDefaultNamespace("http://labs.agentes.de");
        xmlStreamWriter.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        xmlStreamWriter.writeNamespace("schemaLocation", "http://labs.agentes.de /xsd/datenpaket.xsd");
        xmlStreamWriter.writeCharacters("\n");
    }

    private void writeTail() throws XMLStreamException {
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeComment(" (c)reated by gdv-xport at " + new Date() + " ");
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.flush();
    }

    /**
     * Wandelt das uebergebenen Feld in einen XML-String um.
     *
     * @param feld ein Feld
     * @return das Feld als XML-String
     */
    public static String toString(final Feld feld) {
        StringWriter swriter = new StringWriter();
        XmlFormatter formatter = new XmlFormatter(swriter);
        try {
            formatter.write(feld);
        } catch (XMLStreamException shouldnothappen) {
            throw new ShitHappenedException("can't convert " + feld + " to String", shouldnothappen);
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
            throw new ShitHappenedException("can't convert " + teildatensatz + " to String", shouldnothappen);
        }
        swriter.flush();
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
            formatter.write(satz, 0);
        } catch (XMLStreamException ex) {
            LOG.warn("cannot format " + satz, ex);
            swriter.write("<!-- " + satz + " -->");
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
            throw new ShitHappenedException("can't convert " + datenpaket + " to String", shouldnothappen);
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
            LOG.warn("can't indent " + this, e);
        }
    }

}
