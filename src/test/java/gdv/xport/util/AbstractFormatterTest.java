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
 * (c)reated 30.11.2010 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;

import java.io.*;

import javax.xml.stream.*;

import org.apache.commons.logging.*;


/**
 * Gemeinsame Oberklasse fuer die verschiedenen Formatter-Tests.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.5.0 (30.11.2010)
 */
public abstract class AbstractFormatterTest extends AbstractTest {

    private static Log log = LogFactory.getLog(AbstractFormatterTest.class);
    private static XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

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
            istream.close();
            ostream.close();
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

