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

import java.io.IOException;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit-Tests fuer {@link FeldReferenz}-Klasse.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (07.08.2014)
 */
public class FeldReferenzTest extends AbstractXmlTest {

    private static FeldReferenz feldReferenz;

    /**
     * Setzt ein FeldReferenz-Objekt zum Testen auf.
     *
     * @throws XMLStreamException the XML stream exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @BeforeClass
    public static void setUpFeldReferenz() throws XMLStreamException, IOException {
        feldReferenz = getFeldReferenzFrom("feldreferenz.xml");
    }

    private static FeldReferenz getFeldReferenzFrom(final String resource) throws XMLStreamException {
        XMLEventReader parser = createXMLEventReader(resource);
        try {
            return new FeldReferenz(parser);
        } finally {
            parser.close();
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
        assertEquals("0100", feldReferenz.getAuspraegung());
    }

    /**
     * Test-Methode fuer {@link FeldReferenz#getBemerkung()}.
     */
    @Test
    public void testGetBemerkung() {
        String expected = "1 = Kapitallebensversicherung";
        assertEquals(expected, feldReferenz.getBemerkung().trim().substring(0, expected.length()));
    }

    /**
     * Test-Methode fuer {@link FeldReferenz#getDefaultWerte()}.
     */
    @Test
    public void testGetDefaultWerte() {
        List<String> defaultWerte = feldReferenz.getDefaultWerte();
        assertEquals(2, defaultWerte.size());
        assertEquals("1", defaultWerte.get(0));
        assertEquals("3", defaultWerte.get(1));
    }

    /**
     * Test-Methode fuer {@link FeldReferenz#getDefaultWerte()}.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testGetEmptyDefaultWerte() throws XMLStreamException {
        checkEmptyDefaultWerte("feldreferenzTrennzeichen.xml");
    }

    /**
     * Test-Methode fuer {@link FeldReferenz#getDefaultWerte()}. Wenn in der
     * Bemerkung keine Werte-Paare wie "0 = VP" drinsteht, soll eine leere
     * Liste zurueckgegeben werden.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testGetDefaultWerteWithBemerkung() throws XMLStreamException {
        checkEmptyDefaultWerte("feldreferenzWagnisart.xml");
    }

    private void checkEmptyDefaultWerte(final String resource) throws XMLStreamException {
        FeldReferenz ref = getFeldReferenzFrom(resource);
        List<String> defaultWerte = ref.getDefaultWerte();
        assertEquals(0, defaultWerte.size());
    }

}
