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
 * (c)reated 11.08.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (11.08.2014)
 *
 */
public class FeldXmlTest extends AbstractXmlTest {

    private static FeldXml feld;

    /**
     * Setzt ein FeldXml-Objekt zum Testen auf.
     *
     * @throws XMLStreamException the XML stream exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @BeforeClass
    public static void setUpFeldReferenz() throws XMLStreamException, IOException {
        XMLEventReader parser = createXMLEventReader("feld.xml");
        try {
            feld = new FeldXml(parser);
        } finally {
            parser.close();
        }
    }

    /**
     * Test-Methode fuer {@link FeldXml#getId()}.
     */
    @Test
    public void testFeldXmlXMLEventReader() {
        assertEquals("BN-2003.02.11.22.49.47.344", feld.getId());
    }

    /**
     * Test-Methode fuer {@link FeldXml#getBezeichner()}.
     */
    @Test
    public void testGetBezeichner() {
        assertEquals("Sparte", feld.getBezeichner());
    }

}
