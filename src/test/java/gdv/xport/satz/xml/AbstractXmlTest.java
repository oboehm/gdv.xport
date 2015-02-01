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

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 * Die gemeinsame Oberklasse fuer einige Unit-Tests aus dem xml-Paket, die vor
 * allem das Setup erleichtern.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (11.08.2014)
 */
public abstract class AbstractXmlTest {

    /** The Constant xmlInputFactory. */
    protected static final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

    /**
     * Creates the xml event reader.
     *
     * @param resourceName the resource name
     * @return the XML event reader
     * @throws XMLStreamException the XMcreateXMLEventReaderL stream exception
     */
    protected static XMLEventReader createXMLEventReader(final String resourceName) throws XMLStreamException {
        InputStream istream = FeldReferenzTest.class.getResourceAsStream(resourceName);
        assertNotNull("resource '" + resourceName + "' not found", istream);
        XMLEventReader parser = xmlInputFactory.createXMLEventReader(istream);
        return parser;
    }

}
