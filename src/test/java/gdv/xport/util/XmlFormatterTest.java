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
 * (c)reated 14.11.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.util;

import gdv.xport.feld.*;

import java.io.StringReader;

import javax.xml.stream.*;

import org.apache.commons.logging.*;
import org.junit.Test;

/**
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.2 (14.11.2009)
 *
 */
public class XmlFormatterTest {

    private static Log log = LogFactory.getLog(XmlFormatter.class);
    private static XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    /**
     * Test method for {@link gdv.xport.util.XmlFormatter#write(gdv.xport.feld.Feld)}.
     * @throws XMLStreamException 
     */
    @Test
    public void testWrite() throws XMLStreamException {
        Feld x = new Feld("Hello", "World", Align.LEFT);
        String xmlString = XmlFormatter.toString(x);
        log.info(x + " as XML: " + xmlString);
        checkXML(xmlString);
    }
    
    /**
     * We use the XMLStreams to validate the XML
     * 
     * @param xmlString
     * @throws XMLStreamException
     *             the given XML string is not a valid XML
     */
    private static void checkXML(final String xmlString) throws XMLStreamException {
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

