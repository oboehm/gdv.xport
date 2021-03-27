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
import gdv.xport.satz.Satz;
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
    private XMLStreamWriter xmlStreamWriter;

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
            this.xmlStreamWriter = XML_OUTPUT_FACTORY.createXMLStreamWriter(writer);
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
            this.xmlStreamWriter = XML_OUTPUT_FACTORY.createXMLStreamWriter(ostream, Config.DEFAULT_ENCODING.name());
        } catch (XMLStreamException ex) {
            throw new ShitHappenedException("you should never see this", ex);
        } catch (FactoryConfigurationError ex) {
            throw new ConfigException("XML problems", ex);
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
            xmlStreamWriter.writeStartElement("satzart");
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.flush();
        } catch (XMLStreamException ex) {
            throw new IOException("cannot format " + satz, ex);
        }
    }

}
