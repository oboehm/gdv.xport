/*
 * Copyright (c) 2014 by Oli B.
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
 * (c)reated 15.08.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit-Test fuer {@link XmlService}-Klasse
 *
 * @author oliver
 * @since 1.0 (15.08.2014)
 */
public class XmlServiceTest extends AbstractXmlTest {

    private static XmlService xmlService;

    /**
     * Setzt das {@link XmlService}-Objekt auf
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws XMLStreamException the XML stream exception
     */
    @BeforeClass
    public static void setUpXmlService() throws IOException, XMLStreamException {
        XMLEventReader parser = createXMLEventReader("VUVM2013.xml");
        try {
            xmlService = new XmlService(parser);
        } finally {
            parser.close();
        }
    }

    /**
     * Test method for {@link XmlService#getSatzart(int)}.
     */
    @Test
    public void testGetSatzart() {
        SatzXml satz100 = xmlService.getSatzart(100);
        assertNotNull(satz100);
        assertEquals(100, satz100.getSatzart());
    }

}
