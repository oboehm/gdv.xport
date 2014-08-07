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
 * (c)reated 07.08.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit-Tests fuer {@link FeldReferenz}-Klasse.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (07.08.2014)
 */
public class FeldReferenzTest {

    private static final XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    private static FeldReferenz feldReferenz;

    /**
     * Setzt ein FeldReferenz-Objekt zum Testen auf.
     *
     * @throws XMLStreamException the XML stream exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @BeforeClass
    public static void setUpFeldReferenz() throws XMLStreamException, IOException {
        InputStream istream = FeldReferenzTest.class.getResourceAsStream("feldreferenz.xml");
        assertNotNull("resource 'feldreferenz.xml' not found", istream);
        XMLEventReader parser = xmlInputFactory.createXMLEventReader(istream);
        try {
            feldReferenz = new FeldReferenz(parser);
        } finally {
            istream.close();
        }
    }

    /**
     * Test-Methode fuer {@link FeldReferenz#getId()}.
     */
    @Test
    public void testSatzReferenz() {
        assertEquals("BN-2003.02.11.22.49.47.214", feldReferenz.getId());
    }

    /**
     * Test-Methode fuer {@link FeldReferenz#getName()}.
     */
    @Test
    public void testGetName() {
        assertEquals("Satzart", feldReferenz.getName());
    }

    /**
     * Test-Methode fuer {@link FeldReferenz#getTechnischerName()}.
     */
    @Test
    public void testGetTechnischerName() {
        assertEquals("Satzart", feldReferenz.getTechnischerName());
    }

    /**
     * Test-Methode fuer {@link FeldReferenz#getAuspraegung()}.
     */
    @Test
    public void testGetAuspraegung() {
        assertEquals(100, feldReferenz.getAuspraegung());
    }

}
