/*
 * Copyright (c) 2010 - 2014 by Oli B.
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
 * (c)reated 30.11.2010 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import static org.junit.Assert.assertEquals;
import gdv.xport.Datenpaket;
import gdv.xport.DatenpaketStreamer;
import gdv.xport.event.ImportListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import patterntesting.runtime.junit.FileTester;


/**
 * Gemeinsame Oberklasse fuer die verschiedenen Formatter-Tests.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.5.0 (30.11.2010)
 */
public abstract class AbstractFormatterTest extends AbstractTest {

    private static Log log = LogFactory.getLog(AbstractFormatterTest.class);
    private static XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    /** Die Musterdatei, die wir fuer einige Tests verwenden. */
    protected static File MUSTERDATEI = new File("src/test/resources/musterdatei_041222.txt");

    /**
     * Einige Tests passieren auf das korrekte Encoding. Da die Beispieldaten
     * vom GDV alle ISO-8859-1-kodiert sind, sollte das File-Encoding beim
     * Start der VM ebenfalls darauf eingestellt sein, d.h. die VM sollte mit
     * <pre>
     * -Dfile.encoding=ISO-8859-1
     * </pre>
     * gestartet werden. Falsl nicht, wird dieser Test fehlschlagen.
     */
    @Test
    public void testFileEncoding() {
        String defaultEncoding = Charset.defaultCharset().name();
        String fileEncoding = System.getProperty("file.encoding", defaultEncoding);
        assertEquals("wrong launch config", "ISO-8859-1", fileEncoding);
    }

    /**
     * Tested die Formattierung der Musterdatei als HTML.
     *
     * @param formatter the formatter
     * @param filename the filename
     * @throws IOException falls was schiefgelaufen ist
     * @throws XMLStreamException falls was schiefgelaufen ist
     */
    protected static void exportMusterdatei(final AbstractFormatter formatter, final String filename)
            throws IOException, XMLStreamException {
        Datenpaket datenpaket = new Datenpaket();
        InputStream istream = AbstractFormatterTest.class.getResourceAsStream("/musterdatei_041222.txt");
        File siteDir = new File("target", "site");
        if (siteDir.mkdirs()) {
            log.info("created: " + siteDir);
        }
        File exportFile = new File(siteDir, filename);
        OutputStream ostream = new FileOutputStream(exportFile);
        try {
            datenpaket.importFrom(istream);
            formatter.setWriter(ostream);
            formatter.write(datenpaket);
            log.info(datenpaket + " exported to " + exportFile);
        } finally {
            ostream.close();
            istream.close();
        }
    }

    /**
     * Verwendet {@link AbstractFormatter#notice(gdv.xport.satz.Satz)} fuer
     * den Export und ueberprueft das Ergebnis mit einer bereits exportierten
     * Datei.
     *
     * @param formatter the formatter
     * @param filename the filename
     * @throws IOException falls was schiefgelaufen ist
     */
    protected static void checkNotice(final AbstractFormatter formatter, final String filename) throws IOException {
        File output = File.createTempFile("test-notice", ".export");
        Writer writer = new FileWriter(output);
        formatter.setWriter(writer);
        try {
            exportMusterdatei(formatter);
            log.info("Musterdatei was exported to " + output);
        } finally {
            writer.close();
            output.deleteOnExit();
        }
        File exported = new File("target/site", filename);
        if (exported.exists()) {
            log.info(output + " will be compared with already generated " + exported);
            FileTester.assertContentEquals(exported, output, Charset.forName("ISO-8859-1"),
                    Pattern.compile("<!--.*-->"));
        }
    }

    /**
     * Hier exportieren wir die Musterdatei mit dem uebergebenen
     * {@link AbstractFormatter}. Im Gegensatz zu
     * {@link #exportMusterdatei(AbstractFormatter, String)} verwenden wir
     * hier den {@link DatenpaketStreamer} und das {@link ImportListener}
     * interface, um den Export durchzufuehren.
     *
     * @param formatter the formatter
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected static void exportMusterdatei(final AbstractFormatter formatter) throws IOException {
        Reader reader = new FileReader(MUSTERDATEI);
        DatenpaketStreamer datenpaketStreamer = new DatenpaketStreamer(reader);
        datenpaketStreamer.register(formatter);
        try {
            datenpaketStreamer.readDatenpaket();
        } finally {
            reader.close();
        }
    }

    /**
     * We use the XMLStreams to validate the XML.
     *
     * @param xmlString XML-String
     * @throws XMLStreamException
     *             the given XML string is not a valid XML
     */
    protected static void checkXML(final String xmlString) throws XMLStreamException {
        XMLStreamReader xmlr = xmlInputFactory.createXMLStreamReader(new StringReader(xmlString));
        int n = 0;
        while (xmlr.hasNext()) {
            int eventType = xmlr.next();
            if (eventType == XMLStreamConstants.CHARACTERS) {
                n += xmlr.getText().length();
            }
        }
        log.info(n + " bytes text in " + xmlString.length() + " bytes XML");
    }

}

