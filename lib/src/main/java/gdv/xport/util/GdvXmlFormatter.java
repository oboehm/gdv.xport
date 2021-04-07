/*
 * Copyright (c) 2021 by Oli B.
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
 * (c)reated 27.03.2021 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.config.Config;
import gdv.xport.config.ConfigException;
import gdv.xport.feld.*;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import javanet.staxutils.IndentingXMLStreamWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse orientiert an sich an der GDV-XML-Beschreibung fuer das
 * Ausgabeformat. Es kann u.a. dazu benutzt werden, um aus einem Datensatz
 * mit Enum-Beschreibung die entspechende XML-Beschreibung zu bekommen.
 * <p>
 * Mit v6 soll die Beschreibung eigener Datensaetze mittels Enums durch
 * XML-basierte Beschreibungen abgeloest werden. Fuer dieses Ziel ist
 * diese Klasse ein Baustein dazu.
 * </p>
 *
 * @author oliver (ob@aosd.de)
 * @since 5.0 (27.03.2021)
 */
public final class GdvXmlFormatter extends AbstractFormatter {

    private static final Logger LOG = LogManager.getLogger(GdvXmlFormatter.class);
    private static final XMLOutputFactory XML_OUTPUT_FACTORY = XMLOutputFactory.newInstance();
    private final XMLStreamWriter xmlStreamWriter;
    private final List<Feld> felder = new ArrayList<>();

    /**
     * Default-Konstruktor.
     */
    public GdvXmlFormatter() {
        this(System.out);
    }

    /**
     * Der Konstruktor fuer die normale Arbeit.
     *
     * @param writer the writer
     */
    public GdvXmlFormatter(final Writer writer) {
        super(writer);
        try {
            this.xmlStreamWriter = createXMLStreamWriter(writer);
        } catch (XMLStreamException ex) {
            throw new ShitHappenedException("you should never see this", ex);
        } catch (FactoryConfigurationError ex) {
            throw new ConfigException("XML problems", ex);
        }
    }

    /**
     * Der Konstruktor fuer einen {@link OutputStream}.
     *
     * @param ostream z.B. System.out
     */
    public GdvXmlFormatter(final OutputStream ostream) {
        super(new OutputStreamWriter(ostream, Config.DEFAULT_ENCODING));
        try {
            this.xmlStreamWriter = createXMLStreamWriter(ostream);
        } catch (XMLStreamException ex) {
            throw new ShitHappenedException("you should never see this", ex);
        } catch (FactoryConfigurationError ex) {
            throw new ConfigException("XML problems", ex);
        }
    }

    private static XMLStreamWriter createXMLStreamWriter(OutputStream textWriter) throws XMLStreamException {
        XMLStreamWriter out = XML_OUTPUT_FACTORY.createXMLStreamWriter(textWriter, Config.DEFAULT_ENCODING.name());
        return writeHead(out);
    }

    private static XMLStreamWriter writeHead(XMLStreamWriter writer) throws XMLStreamException {
        XMLStreamWriter indentingWriter = toIndentingStreamWriter(writer);
        indentingWriter.writeStartDocument(StandardCharsets.ISO_8859_1.toString(), "1.0");
        indentingWriter.writeStartElement("service");
        indentingWriter.writeStartElement("satzarten");
        return indentingWriter;
    }

    /**
     * Hierueber werden noch die Felder-Definitionen und der Abspann
     * rausgeschrieben, ehe die Writer-Resource geschlossen wird.
     *
     * @throws IOException falls was schief geht
     */
    @Override
    public void close() throws IOException {
        try {
            xmlStreamWriter.writeEndElement();
            writeFelder();
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.flush();
            xmlStreamWriter.close();
        } catch (XMLStreamException ex) {
            throw new IOException("cannot close " + xmlStreamWriter, ex);
        } finally {
            super.close();
        }
    }

    /**
     * Ausgabe eines Datensatzes als XML.
     *
     * @param satz der auszugebende (Daten-)Satz
     */
    @Override
    public void write(final Satz satz) throws IOException {
        try {
            writeComment(satz.toShortString());
            xmlStreamWriter.writeStartElement("satzart");
            writeKennzeichnung(satz);
            writeElement("version", satz.getSatzversion().getInhalt());
            write(satz.getTeildatensaetze());
            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException ex) {
            throw new IOException("cannot format " + satz, ex);
        }
    }

    private void writeKennzeichnung(Satz satz) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("kennzeichnung");
        writeReferenz(satz.getSatzartFeld());
        writeSparte(satz);
        xmlStreamWriter.writeEndElement();
    }

    private void write(List<Teildatensatz> teildatensaetze) throws XMLStreamException {
        for (Teildatensatz tds : teildatensaetze) {
            writeComment(tds.toShortString());
            xmlStreamWriter.writeEmptyElement("satzanfang");
            xmlStreamWriter.writeAttribute("teilsatz", tds.getSatznummer().getInhalt());
            for (Feld feld : tds.getFelder()) {
                writeComment(feld.toString());
                writeReferenz(feld);
                felder.add(feld);
            }
            xmlStreamWriter.writeEmptyElement("satzende");
            xmlStreamWriter.flush();
        }
    }

    private void writeReferenz(Feld feld) throws XMLStreamException {
        Bezeichner bezeichner = feld.getBezeichner();
        xmlStreamWriter.writeStartElement("feldreferenz");
        xmlStreamWriter.writeAttribute("referenz", bezeichner.getTechnischerName());
        writeElement("name", bezeichner.getName());
        writeElement("technischerName", bezeichner.getTechnischerName());
        if (feld.hasValue()) {
            writeElement("auspraegung", feld.getInhalt());
        }
        xmlStreamWriter.writeEndElement();
    }

    private void writeSparte(Satz satz) throws XMLStreamException {
        if (satz.hasSparte()) {
            Feld sparteFeld = new Feld(Bezeichner.SPARTE, 11, satz.getSatzTyp().toString().substring(5), Align.LEFT);
            writeReferenz(sparteFeld);
        }
    }

    private void writeFelder() throws XMLStreamException {
        xmlStreamWriter.writeStartElement("felder");
        for (Feld feld : felder) {
            write(feld);
        }
        xmlStreamWriter.writeEndElement();
    }

    private void write(Feld feld) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("feld");
        xmlStreamWriter.writeAttribute("referenz", feld.getBezeichner().getTechnischerName());
        writeElement("name", feld.getBezeichner().getName());
        writeElement("bytes", Integer.toString(feld.getAnzahlBytes()));
        writeElement("datentyp", Datentyp.asString(feld));
        if (feld instanceof NumFeld) {
            writeNachkommastellen((NumFeld) feld);
        }
        xmlStreamWriter.writeEndElement();
    }

    private void writeNachkommastellen(NumFeld feld) throws XMLStreamException {
        if (feld.getNachkommastellen() > 0) {
            writeElement("nachkommastellen", Integer.toString(feld.getNachkommastellen()));
        }
    }

    private void writeElement(String tag, String value) throws XMLStreamException {
        xmlStreamWriter.writeStartElement(tag);
        xmlStreamWriter.writeCharacters(value);
        xmlStreamWriter.writeEndElement();
    }

    private void writeComment(String comment) throws XMLStreamException {
        xmlStreamWriter.writeComment(" " + comment.trim() + " ");
    }

    private static XMLStreamWriter createXMLStreamWriter(Writer textWriter) throws XMLStreamException {
        XMLStreamWriter out = XML_OUTPUT_FACTORY.createXMLStreamWriter(textWriter);
        return toIndentingStreamWriter(out);
    }

    //https://stackoverflow.com/questions/10105187/java-indentingxmlstreamwriter-alternative
    private static IndentingXMLStreamWriter toIndentingStreamWriter(XMLStreamWriter out) {
        IndentingXMLStreamWriter indentWriter = new IndentingXMLStreamWriter(out);
        indentWriter.setIndent("\t");
        return indentWriter;
    }

}
