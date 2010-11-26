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

import static org.junit.Assert.*;

import java.io.*;

import javax.xml.stream.XMLStreamException;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.*;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * JUnit-Test fuer HtmlFormatter.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.4.3 (23.11.2010)
 */
public class HtmlFormatterTest extends XmlFormatterTest {
    
    private static Log log = LogFactory.getLog(HtmlFormatter.class);

    /**
     * Tested den Export eines Datenpakets als HTML.
     *
     * @throws XMLStreamException falls was schiefgelaufen ist
     * @throws SAXException falls was schiefgelaufen ist
     * @throws IOException falls was schiefgelaufen ist
     */
    @Test
    public void testWriteDatenpaket() throws XMLStreamException, SAXException, IOException {
        Datenpaket datenpaket = new Datenpaket();
        String htmlString = HtmlFormatter.toString(datenpaket);
        log.info(datenpaket + " as HTML:\n" + htmlString);
        checkXML(htmlString);
        assertTrue("no <html> inside", htmlString.contains("<html"));
        assertTrue("no </html> inside", htmlString.contains("</html"));
    }
    
    /**
     * Tested die Formattierung der Musterdatei als HTML.
     *
     * @throws XMLStreamException falls was schiefgelaufen ist
     * @throws IOException falls was schiefgelaufen ist
     */
    @Test
    public void testMusterdatei() throws IOException, XMLStreamException {
        Datenpaket datenpaket = new Datenpaket();
        InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt");
        datenpaket.importFrom(istream);
        istream.close();
        File siteDir = new File("target", "site");
        siteDir.mkdirs();
        HtmlFormatter formatter = new HtmlFormatter(new File(siteDir, "musterdatei_041222.html"));
        formatter.setTitle("GDV-Datei musterdatei_041222.txt");
        formatter.write(datenpaket);
        log.info(datenpaket + " exported as HTML to dir " + siteDir);
    }

}

