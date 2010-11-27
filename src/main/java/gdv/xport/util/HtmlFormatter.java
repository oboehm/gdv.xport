/*
 * Copyright (c) 2010 by agentes
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
 * (c)reated 23.11.2010 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;
import gdv.xport.feld.Feld;
import gdv.xport.satz.*;

import java.io.*;
import java.text.MessageFormat;
import java.util.Iterator;

import javax.xml.stream.*;

import org.apache.commons.io.IOUtils;

/**
 * Diese Klasse gibt die verschiedenen Saetze und Felder als HTML aus.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.4.3 (23.11.2010)
 */
public final class HtmlFormatter {
    
    private static final XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
    private static final String template;
    private final Writer writer;
    private String title = "GDV-Datei";
    
    static {
        try {
            template = readTemplate();
        } catch (IOException ioe) {
            throw new ExceptionInInitializerError(ioe);
        }
    }
    
    private static String readTemplate() throws IOException {
        InputStream istream = HtmlFormatter.class.getResourceAsStream("template.html");
        try {
            return IOUtils.toString(istream);
        } finally {
            istream.close();
        }
    }

    /**
     * Instantiiert einen neuen HtmlFormatter.
     *
     * @param file the file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public HtmlFormatter(final File file) throws IOException {
        this(new FileWriter(file));
        this.title = "GDV-Datei " + file;
    }

    /**
     * Instantiiert einen neuen HtmlFormatter.
     *
     * @param ostream the ostream
     */
    public HtmlFormatter(final OutputStream ostream) {
        this(new OutputStreamWriter(ostream));
    }

    /**
     * Instantiiert einen neuen HtmlFormatter.
     *
     * @param writer the writer
     */
    public HtmlFormatter(final Writer writer) {
        this.writer = writer;
    }

    /**
     * Hiermit kann der Titel (Ueberschrift) gesetzt werden.
     * 
     * @param title Titel bzw. Ueberschrift
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Ausgabe eines kompletten Datenpakets als XML.
     *
     * @param datenpaket Datenpaket, das als XML ausgegeben werden soll
     * @throws XMLStreamException bei Problemen mit der HTML-Generierung
     */
    public void write(final Datenpaket datenpaket) throws XMLStreamException {
        long t0 = System.currentTimeMillis();
        StringWriter buffer = new StringWriter();
        XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(buffer);
        writeTo(xmlStreamWriter, datenpaket.getVorsatz());
        for (Iterator<Datensatz> iterator = datenpaket.getDatensaetze().iterator(); iterator.hasNext(); ) {
            Datensatz datensatz = iterator.next();
            writeTo(xmlStreamWriter, datensatz);
        }
        writeTo(xmlStreamWriter, datenpaket.getNachsatz());
        xmlStreamWriter.close();
        try {
            buffer.close();
            String content = MessageFormat.format(template, Config.DEFAULT_ENCODING.name(), title, buffer.toString());
            writer.write(content);
            writer.write("<!-- (c)reated by gdv-xport in " + (System.currentTimeMillis() - t0) + " ms -->\n");
            writer.flush();
        } catch (IOException ioe) {
            throw new XMLStreamException(ioe);
        }
    }
    
    private static void writeTo(final XMLStreamWriter xmlStreamWriter, final Satz satz) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("div");
        xmlStreamWriter.writeAttribute("class", "Satz");
        if (satz instanceof Datensatz) {
            Datensatz datensatz = (Datensatz) satz;
            xmlStreamWriter.writeAttribute("title", "Satzart " + datensatz.getSatzartFeld().getInhalt() + "."
                    + datensatz.getSparteFeld().getInhalt());
        } else {
            xmlStreamWriter.writeAttribute("title", "Satzart " + satz.getSatzartFeld().getInhalt());
        }
        for (Iterator<Teildatensatz> iterator = satz.getTeildatensaetze().iterator(); iterator.hasNext();) {
            Teildatensatz teildatensatz = iterator.next();
            writeTo(xmlStreamWriter, teildatensatz);
            if (iterator.hasNext()) {
                xmlStreamWriter.writeCharacters("\n");
            }
        }
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.flush();
    }

    private static void writeTo(final XMLStreamWriter xmlStreamWriter, final Teildatensatz teildatensatz)
            throws XMLStreamException {
        xmlStreamWriter.writeStartElement("span");
        xmlStreamWriter.writeAttribute("class", "Teildatensatz");
        xmlStreamWriter.writeAttribute("title", "Nr. " + teildatensatz.getNummer().getInhalt());
        for (Iterator<Feld> iterator = teildatensatz.getFelder().iterator(); iterator.hasNext();) {
            Feld feld = iterator.next();
            writeTo(xmlStreamWriter, feld);
        }
        xmlStreamWriter.writeEndElement();
    }

    private static void writeTo(final XMLStreamWriter xmlStreamWriter, final Feld feld) throws XMLStreamException {
        String feldType = feld.getClass().getSimpleName();
        xmlStreamWriter.writeStartElement("span");
        xmlStreamWriter.writeAttribute("class", feldType);
        xmlStreamWriter.writeAttribute("title", feld.getBezeichnung());
        xmlStreamWriter.writeCharacters(feld.getInhalt());
        xmlStreamWriter.writeEndElement();
    }

    /**
     * Wandelt das uebergebene Datenpaket in einen HTML-String um.
     *
     * @param datenpaket das Datenpaket
     * @return Datenpaket als XML-String
     */
    public static String toString(final Datenpaket datenpaket) {
        StringWriter swriter = new StringWriter();
        HtmlFormatter formatter = new HtmlFormatter(swriter);
        try {
            formatter.write(datenpaket);
        } catch (XMLStreamException shouldnothappen) {
            throw new RuntimeException("can't convert " + datenpaket + " to String",
                    shouldnothappen);
        }
        IOUtils.closeQuietly(swriter);
        return swriter.toString();
    }

}
