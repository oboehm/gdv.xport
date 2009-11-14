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

import java.io.*;

import javax.xml.stream.*;

import org.apache.commons.io.IOUtils;

/**
 * Diese Klasse dient dazu, um die verschiedenen Saetze und Felder in einer
 * XML-Struktur ausgeben zu koennen.
 *
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.2 (13.11.2009)
 */
public class XmlFormatter {

    private static final XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
    private final XMLStreamWriter xmlStreamWriter;

    /**
     * Der einzige Konstruktor.
     * @param writer
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
    
    public XmlFormatter(XMLStreamWriter xmlStreamWriter) {
        this.xmlStreamWriter = xmlStreamWriter;
    }

    /**
     * Ausgabe eines Feldes als XML
     *
     * @param feld the feld
     *
     * @throws XMLStreamException Signals that an I/O exception has occurred.
     */
    public void write(Feld feld) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("feld");
        xmlStreamWriter.writeAttribute("bezeichnung", feld.getBezeichnung());
        xmlStreamWriter.writeAttribute("bytes", feld.getByteAdresse() + "-" + feld.getEndAdresse());
        xmlStreamWriter.writeCharacters(feld.getInhalt());
        xmlStreamWriter.writeEndElement();
    }
    
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

}

