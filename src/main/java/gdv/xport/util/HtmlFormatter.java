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
import java.util.*;

import javax.xml.stream.*;

import org.apache.commons.io.IOUtils;

/**
 * Diese Klasse gibt die verschiedenen Saetze und Felder als HTML aus.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.4.3 (23.11.2010)
 */
public class HtmlFormatter extends XmlFormatter {
    
    private String title = "GDV-Datei";

    /**
     * Instantiiert einen neuen HtmlFormatter.
     *
     * @param file the file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public HtmlFormatter(final File file) throws IOException {
        super(file);
    }

    /**
     * Instantiiert einen neuen HtmlFormatter.
     *
     * @param ostream the ostream
     */
    public HtmlFormatter(final OutputStream ostream) {
        super(ostream);
    }

    /**
     * Instantiiert einen neuen HtmlFormatter.
     *
     * @param writer the writer
     */
    public HtmlFormatter(final Writer writer) {
        super(writer);
    }

    /**
     * Instantiiert einen neuen HtmlFormatter.
     *
     * @param xmlStreamWriter the xml stream writer
     */
    public HtmlFormatter(final XMLStreamWriter xmlStreamWriter) {
        super(xmlStreamWriter);
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
     * @throws XMLStreamException bei Problemen mit der XML-Generierung
     */
    @Override
    public void write(final Datenpaket datenpaket) throws XMLStreamException {
        long t0 = System.currentTimeMillis();
        xmlStreamWriter.writeStartDocument(Config.DEFAULT_ENCODING.name(), "1.0");
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeStartElement("html");
        xmlStreamWriter.writeDefaultNamespace("http://www.w3.org/1999/xhtml");
        xmlStreamWriter.writeCharacters("\n");
        writeHead();
        writeBody(datenpaket);
        xmlStreamWriter.writeComment(" (c)reated by gdv-xport in "
                + (System.currentTimeMillis() - t0) + " ms ");
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.flush();
    }

    private void writeHead() throws XMLStreamException {
        xmlStreamWriter.writeStartElement("head");
        xmlStreamWriter.writeCharacters("\n  ");
        xmlStreamWriter.writeStartElement("title");
        xmlStreamWriter.writeCharacters(title);
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
    }

    private void writeBody(final Datenpaket datenpaket) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("body");
        xmlStreamWriter.writeCharacters("\n  ");
        xmlStreamWriter.writeStartElement("h1");
        xmlStreamWriter.writeCharacters(title);
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeStartElement("pre");
        write(datenpaket.getVorsatz());
        for (Iterator<Datensatz> iterator = datenpaket.getDatensaetze().iterator(); iterator.hasNext(); ) {
            Datensatz datensatz = iterator.next();
            write(datensatz);
        }
        write(datenpaket.getNachsatz());
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
    }
    
    /**
     * Ausgabe eines Datensatzes als HTML.
     *
     * @param satz der auszugebende (Daten-)Satz
     *
     * @throws XMLStreamException the XML stream exception
     */
    public void write(final Satz satz) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("span");
        xmlStreamWriter.writeAttribute("class", "satz");
        if (satz instanceof Datensatz) {
            Datensatz datensatz = (Datensatz) satz;
            xmlStreamWriter.writeAttribute("title", "Sparte " + datensatz.getSatzartFeld().getInhalt() + "."
                    + datensatz.getSatzartFeld().getInhalt());
        } else {
            xmlStreamWriter.writeAttribute("title", "Satzart=" + satz.getSatzartFeld().getInhalt());
        }        
        for (Iterator<Teildatensatz> iterator = satz.getTeildatensaetze().iterator(); iterator
                .hasNext();) {
            Teildatensatz teildatensatz = iterator.next();
            write(teildatensatz);
            if (iterator.hasNext()) {
                xmlStreamWriter.writeCharacters("\n");
            }
        }
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeCharacters("\n");
        xmlStreamWriter.flush();
    }
    
    /**
     * Ausgabe eines Teildatensatzes als HTML.
     *
     * @param teildatensatz the teildatensatz
     *
     * @throws XMLStreamException the XML stream exception
     */
    public void write(final Teildatensatz teildatensatz) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("span");
        xmlStreamWriter.writeAttribute("class", "teildatensatz");
        xmlStreamWriter.writeAttribute("title", "Nr. " + teildatensatz.getNummer().getInhalt());
        for (Iterator<Feld> iterator = teildatensatz.getFelder().iterator(); iterator.hasNext();) {
            Feld feld = iterator.next();
            write(feld);
        }
        xmlStreamWriter.writeEndElement();
    }
    
    /**
     * Ausgabe eines Feldes als HTML.
     *
     * @param feld the feld
     *
     * @throws XMLStreamException Signals that an I/O exception has occurred.
     */
    public void write(final Feld feld) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("span");
        xmlStreamWriter.writeAttribute("class", "feld");
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
