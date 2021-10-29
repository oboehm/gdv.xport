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
import java.time.LocalDate;
import java.util.*;

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
    private static final String DEFAULT_INFO = "(c)reated by gdv-xport at " + LocalDate.now();
    private XMLStreamWriter xmlStreamWriter;
	private final Map<String, Feld> felder = new HashMap<>();
    private final String stand;

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
        this(writer, DEFAULT_INFO);
    }

    /**
     * Der Konstruktor fuer die normale Arbeit.
     * Als Info erscheint dabei der Zeitpunkt der Generierung (analog zum
     * {@link XmlFormatter}, wo es als Kommentar ans Ende geschrieben wird).
     *
     * @param writer the writer
     * @param stand Info, die nach dem XML-Header steht
     */
    public GdvXmlFormatter(final Writer writer, final String stand) {
        super(writer);
        this.stand = DEFAULT_INFO;
        this.xmlStreamWriter = createXMLStreamWriter(writer, this.stand);
    }

    /**
   * Der Konstruktor fuer einen {@link OutputStream}.
   *
   * @param ostream z.B. System.out
   * @param stand Datum, ab dem erzeugte XML-Beschreibung gilt (Format TT.MM.JJJJ) z.B.
   *          "01.07.2018". <br>
   *          Dieser Wert erscheint in Analogie zur GDV-XML-Beschreibung am Beginn der
   *          XML-Beschreibung in einem Tag: &ltinfo&gt&ltstand&gt...&lt/stand&gt&lt/info&gt
   */
  public GdvXmlFormatter(final OutputStream ostream, final String stand) {
    super(new OutputStreamWriter(ostream, Config.DEFAULT_ENCODING));
    this.stand = stand;
    this.xmlStreamWriter = createXMLStreamWriter(ostream, this.stand);
    }

    /**
     * Der Konstruktor fuer einen {@link OutputStream}.
     * Als Info erscheint dabei der Zeitpunkt der Generierung (analog zum
     * {@link XmlFormatter}, wo es als Kommentar ans Ende geschrieben wird).
     *
     * @param ostream z.B. System.out
     */
    public GdvXmlFormatter(final OutputStream ostream) {
        this(ostream, DEFAULT_INFO);
    }

    private static XMLStreamWriter writeHead(XMLStreamWriter writer, String gueltigAbDatum) throws XMLStreamException {
        XMLStreamWriter indentingWriter = toIndentingStreamWriter(writer);
        indentingWriter.writeStartDocument(StandardCharsets.ISO_8859_1.toString(), "1.0");
        indentingWriter.writeStartElement("service");
        writeInfo(gueltigAbDatum, indentingWriter);
        indentingWriter.writeStartElement("satzarten");
        return indentingWriter;
    }

    private static void writeInfo(String gueltigAbDatum, XMLStreamWriter indentingWriter) throws XMLStreamException {
        // Das folgende Info-Tag orientiert an sich an der GDV-XML-Beschreibung
        indentingWriter.writeStartElement("info");
        indentingWriter.writeStartElement("stand");
        indentingWriter.writeCharacters(gueltigAbDatum);
        indentingWriter.writeEndElement();
        indentingWriter.writeEndElement();
    }

    @Override
    public void setWriter(Writer writer) {
        super.setWriter(writer);
        this.xmlStreamWriter = createXMLStreamWriter(writer, this.stand);
    }

    @Override
    public void setWriter(OutputStream ostream) {
        super.setWriter(ostream);
        this.xmlStreamWriter = createXMLStreamWriter(ostream, this.stand);
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
        writeSatznummer(satz);
        xmlStreamWriter.writeEndElement();
    }

    private void write(List<Teildatensatz> teildatensaetze) throws XMLStreamException {
        for (Teildatensatz tds : teildatensaetze) {
            writeComment(tds.toShortString());
            xmlStreamWriter.writeEmptyElement("satzanfang");
            xmlStreamWriter.writeAttribute("teilsatz", tds.getSatznummer().getInhalt());
            for (Feld feld : getFelderOhneLuecken(tds)) {
                writeComment(feld.toShortString());
                writeReferenz(feld);
                felder.putIfAbsent(toFeldReferenzId(feld), feld);
            }
            xmlStreamWriter.writeEmptyElement("satzende");
            xmlStreamWriter.flush();
        }
    }

    private List<Feld> getFelderOhneLuecken(Teildatensatz tds) {
        List<Feld> felder = new ArrayList<>();
        int adresse = 1;
        for (Feld feld : tds.getFelder()) {
            if (adresse < feld.getByteAdresse()) {
                felder.add(new AlphaNumFeld(Bezeichner.of("Leerstellen"), feld.getByteAdresse()-adresse, adresse));
                LOG.info("An Adresse {} wurde {} mit Leerstellen aufgefuellt.", adresse, tds.toShortString());
            }
            felder.add(feld);
            adresse = feld.getEndAdresse() + 1;
        }
        return felder;
    }

    private void writeReferenz(Feld feld) throws XMLStreamException {
        Bezeichner bezeichner = feld.getBezeichner();
        xmlStreamWriter.writeStartElement("feldreferenz");
        xmlStreamWriter.writeAttribute("referenz", toFeldReferenzId(feld));
        writeElement("name", bezeichner.getName());
        writeElement("technischerName", bezeichner.getTechnischerName());
        if (feld.hasValue()) {
            writeElement("auspraegung", feld.getInhalt().trim());
        }
        if (feld instanceof AlphaNumFeld) {
            writeAlignment((AlphaNumFeld) feld);
        }
        xmlStreamWriter.writeEndElement();
    }

    private void writeAlignment(AlphaNumFeld feld) throws XMLStreamException {
        if (feld.getAusrichtung() == Align.RIGHT) {
            writeElement("bemerkung", "rechtsbuendig");
        }
    }

    private String toFeldReferenzId(Feld feld) {
        return String.format("%03d-%03d-%s-%s", feld.getByteAdresse(), feld.getEndAdresse(),
                feld.getBezeichner().getTechnischerName(), Datentyp.asString(feld));
    }

    private void writeSparte(Satz satz) throws XMLStreamException {
        if (satz.getSatzTyp().hasSparteInGdvSatzartName()) {
            Feld sparteFeld = new Feld(Bezeichner.SPARTE, 11, satz.getSatzTyp().getSparteMitArt(), Align.LEFT);
            writeReferenz(sparteFeld);
        }
    }

    private void writeSatznummer(Satz satz) throws XMLStreamException {
        Feld satznummer = new Feld(Bezeichner.SATZNUMMER, 256, satz.getGdvSatzartNummer(), Align.LEFT);
        writeReferenz(satznummer);
    }

    private void writeFelder() throws XMLStreamException {
        TreeMap<String, Feld> sorted = new TreeMap<>(felder);
        Set<Map.Entry<String, Feld>> mappings = sorted.entrySet();
        xmlStreamWriter.writeStartElement("felder");
         for (Map.Entry<String, Feld> entry : mappings) {
            write(entry.getValue());
        }
        xmlStreamWriter.writeEndElement();
    }

    private void write(Feld feld) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("feld");
        xmlStreamWriter.writeAttribute("referenz", toFeldReferenzId(feld));
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

    private static XMLStreamWriter createXMLStreamWriter(OutputStream textWriter, String gdvSatzVersion) {
        try {
            XMLStreamWriter out = XML_OUTPUT_FACTORY.createXMLStreamWriter(textWriter, Config.DEFAULT_ENCODING.name());
            return writeHead(out, gdvSatzVersion);
        } catch (XMLStreamException ex) {
            throw new IllegalArgumentException("can't create XmlStreamWriter with " + textWriter, ex);
        } catch (FactoryConfigurationError ex) {
            throw new ConfigException("XML problems", ex);
        }
    }

    private static XMLStreamWriter createXMLStreamWriter(Writer textWriter, String gdvSatzVersion) {
        try {
            XMLStreamWriter out = XML_OUTPUT_FACTORY.createXMLStreamWriter(textWriter);
            return writeHead(out, gdvSatzVersion);
        } catch (XMLStreamException ex) {
            throw new IllegalArgumentException("can't create XmlStreamWriter with " + textWriter, ex);
        } catch (FactoryConfigurationError ex) {
            throw new ConfigException("XML problems", ex);
        }
    }

    //https://stackoverflow.com/questions/10105187/java-indentingxmlstreamwriter-alternative
    private static IndentingXMLStreamWriter toIndentingStreamWriter(XMLStreamWriter out) {
        IndentingXMLStreamWriter indentWriter = new IndentingXMLStreamWriter(out);
        indentWriter.setIndent("\t");
        return indentWriter;
    }

}
