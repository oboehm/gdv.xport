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

import gdv.xport.feld.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (11.08.2014)
 *
 */
public class FeldXmlTest extends AbstractXmlTest {

    private static FeldXml feldXml;

    /**
     * Setzt ein FeldXml-Objekt zum Testen auf.
     *
     * @throws XMLStreamException the XML stream exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @BeforeClass
    public static void setUpFeldReferenz() throws XMLStreamException, IOException {
        feldXml = createFeldXmlFrom("feld.xml");
    }

    private static FeldXml createFeldXmlFrom(final String resource) throws XMLStreamException {
        XMLEventReader parser = createXMLEventReader(resource);
        try {
            return new FeldXml(parser);
        } finally {
            parser.close();
        }
    }

    /**
     * Test-Methode fuer {@link FeldXml#getId()}.
     */
    @Test
    public void testFeldXmlXMLEventReader() {
        assertEquals("BN-2003.02.11.22.49.47.344", feldXml.getId());
    }

    /**
     * Test-Methode fuer {@link FeldXml#getBezeichner()}.
     */
    @Test
    public void testGetBezeichner() {
        assertEquals(Bezeichner.of("Sparte"), feldXml.getBezeichner());
    }

    /**
     * Test-Methode fuer {@link FeldXml#getDatentyp()}.
     */
    @Test
    public void testGetDatentyp() {
        assertEquals(Datentyp.NUMERISCH, feldXml.getDatentyp());
    }

    /**
     * Test-Methode fuer {@link FeldXml#getNachkommastellen()}.
     */
    @Test
    public void testGetNachkommastellen() {
        assertEquals(0, feldXml.getNachkommastellen());
    }

    /**
     * Testet, ob der XML-Teil mit der VU-Nummer korrekt geparst wird.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testVuNummer() throws XMLStreamException {
        FeldXml vuNr = createFeldXmlFrom("feldVuNr.xml");
        assertEquals(Bezeichner.VU_NR, vuNr.getBezeichner());
        assertEquals(5, vuNr.getAnzahlBytes());
        assertEquals("test ", vuNr.getInhalt());
    }

    @Test
    public void testToNumFeld() {
        checkToFeld(feldXml, NumFeld.class);
    }

    @Test
    public void testToAlphaNumFeld() throws XMLStreamException {
        checkToFeld(createFeldXmlFrom("feldVuNr.xml"), AlphaNumFeld.class);
    }

    @Test
    public void testToDatumFeld() throws XMLStreamException {
        checkToFeld(createFeldXmlFrom("feldDatum.xml"), Datum.class);
    }

    @Test
    public void testToGleitkomma() throws XMLStreamException {
        FeldXml gleitkomma = createFeldXmlFrom("feldFliesskomma.xml");
        checkToFeld(gleitkomma, NumFeld.class);
        assertEquals(12, gleitkomma.getAnzahlBytes());
    }

    /**
     * Der Bezeichner wurde nicht richtig uebernommen in der Vergangenheit.
     * Deswegen dieser Test hier.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testToFeldBezeichner() throws XMLStreamException {
        FeldXml gleitkomma = createFeldXmlFrom("feldFliesskomma.xml");
        FeldReferenz referenz = new FeldReferenz(createXMLEventReader("feldreferenzZuzahlungsbetragInWE.xml"));
        Feld feld = gleitkomma.toFeld(ByteAdresse.of(160), referenz);
        assertEquals(Bezeichner.ZUZAHLUNGSBETRAG_IN_WE, feld.getBezeichner());
    }

    @Test
    public void testToUhrzeit() throws XMLStreamException {
        checkToFeld(createFeldXmlFrom("feldUhrzeit.xml"), NumFeld.class);
    }

    private static void checkToFeld(final FeldXml input, final Class<? extends Feld> expected) {
        Feld converted = input.toFeld(ByteAdresse.of(42));
        assertEquals(42, converted.getByteAdresse());
        assertTrue(converted + ": bezeichnung expected", StringUtils.isNotEmpty(converted.getBezeichnung()));
        assertEquals(input.getBezeichnung(), converted.getBezeichnung());
        assertEquals(input.getAnzahlBytes(), converted.getAnzahlBytes());
        assertEquals(expected, converted.getClass());
    }

    @Test
    public void testToDatum() throws XMLStreamException {
        FeldXml datum = createFeldXmlFrom("feldDatum.xml");
        datum.setInhalt("21052021");
        FeldReferenz referenz = new FeldReferenz(createXMLEventReader("feldreferenzDatum.xml"));
        Feld converted = datum.toFeld(ByteAdresse.of(10), referenz);
        assertEquals(datum.getInhalt(), converted.getInhalt());
    }

}
