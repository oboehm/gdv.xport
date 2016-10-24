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

import java.io.*;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;

import gdv.xport.Datenpaket;
import gdv.xport.DatenpaketStreamer;
import gdv.xport.event.ImportListener;
import patterntesting.runtime.junit.FileTester;


/**
 * Gemeinsame Oberklasse fuer die verschiedenen Formatter-Tests.
 *
 * Einige Tests passieren auf korrektem Encoding. Da die Beispieldaten
 * vom GDV alle ISO-8859-1-kodiert sind, kann das File-Encoding beim
 * Start der VM ebenfalls darauf eingestellt sein, d.h. die VM kann mit
 * <pre>
 * -Dfile.encoding=ISO-8859-1
 * </pre>
 * gestartet werden, falls einige Tests fehlschlagen sollten.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.5.0 (30.11.2010)
 */
public abstract class AbstractFormatterTest extends AbstractTest {

    private static final Logger LOG = LogManager.getLogger(AbstractFormatterTest.class);
    private static XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    /** Die Musterdatei, die wir fuer einige Tests verwenden. */
    protected static final File MUSTERDATEI = new File("src/test/resources/musterdatei_041222.txt");

    /** Ein Muster-Datenpaket, das fuer einige Tests benoetigt wird. */
    protected static final Datenpaket MUSTER_DATENPAKET = new Datenpaket();

    /**
     * Hier laden wir die Muster-Datei, um ein Datenpaket zum Testen zu haben.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @BeforeClass
    public static void loadMusterDatenpaket() throws IOException {
        MUSTER_DATENPAKET.importFrom(MUSTERDATEI, "ISO-8859-1");
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
            LOG.info("created: " + siteDir);
        }
        File exportFile = new File(siteDir, filename);
        Writer writer = new OutputStreamWriter(new FileOutputStream(exportFile), "ISO-8859-1");
        try {
            datenpaket.importFrom(istream);
            formatter.setWriter(writer);
            formatter.write(datenpaket);
            LOG.debug("{} exported to {} .", datenpaket, exportFile);
        } finally {
            writer.close();
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
        Writer writer = new OutputStreamWriter(new FileOutputStream(output), "ISO-8859-1");
        formatter.setWriter(writer);
        try {
            exportMusterdatei(formatter);
            LOG.info("Musterdatei was exported to '{}'.", output);
        } finally {
            writer.close();
            output.deleteOnExit();
        }
        File exported = new File("target/site", filename);
        if (exported.exists()) {
            LOG.info(output + " will be compared with already generated " + exported);
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
        Reader reader = new InputStreamReader(new FileInputStream(MUSTERDATEI), "ISO-8859-1");
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
        LOG.info(n + " bytes text in " + xmlString.length() + " bytes XML");
    }

}

