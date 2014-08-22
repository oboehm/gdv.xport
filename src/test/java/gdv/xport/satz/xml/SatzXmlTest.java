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
 * (c)reated 31-Jul-2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gdv.xport.feld.Feld;
import gdv.xport.feld.NumFeld;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import patterntesting.runtime.junit.SmokeRunner;

/**
 * Unit tests for {@link SatzXml} class.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (31.07.2014)
 */
@RunWith(SmokeRunner.class)
public class SatzXmlTest extends AbstractXmlTest {

    private static SatzXml satz100;

    /**
     * Setzt ein SatzXml-Objekt fuer den Satz 100 auf.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws XMLStreamException the XML stream exception
     */
    @BeforeClass
    public static void setUpSatz100() throws IOException, XMLStreamException {
        XMLEventReader parser = createXMLEventReader("Satz100.xml");
        try {
            satz100 = new SatzXml(parser);
            Map<String, FeldXml> felder = XmlService.parseFelder(parser);
            satz100.setFelder(felder);
        } finally {
            parser.close();
        }
    }

    /**
     * Test method for {@link SatzXml#getSatzart()}.
     */
    @Test
    public void testGetSatzart() {
        checkSatzart(satz100);
    }

    /**
     * Auch der Satz 100 kann eine Sparte beinhalten.
     */
    @Test
    public void testGetSparte() {
        NumFeld sparte = satz100.getSparteFeld();
        assertEquals(3, sparte.getAnzahlBytes());
        assertEquals(11, sparte.getByteAdresse());
        assertEquals("Sparte", sparte.getBezeichnung());
    }

    /**
     * 1 Teildatensatz ist momentan in "Satz100.xml" defniert. Normalerweise
     * sollten es 5 sein, aber aus Uebersichtsgruenden ist nur 1 Teildatensatz
     * in der Test-Resource vorhanden.
     */
    @Test
    public void testGetTeildatensaetze() {
        Collection<Teildatensatz> teildatensaetze = satz100.getTeildatensaetze();
        assertEquals(1, teildatensaetze.size());
        char expectedNr = '1';
        for (Teildatensatz tds : teildatensaetze) {
            assertEquals(expectedNr, tds.getNummer().toChar());
            expectedNr++;
            checkTeildatensatz(tds);
        }
    }

    private static void checkTeildatensatz(final Teildatensatz tds) {
        checkSatzart(tds);
        Feld feld = tds.getFeld("VU-Nummer");
        assertNotNull("VU-Nummer missing", feld);
        assertEquals(5, feld.getAnzahlBytes());
        assertEquals(5, feld.getByteAdresse());
        assertEquals(feld, tds.getFeld(2));
        assertEquals(tds.getFeld("Folgenummer"), tds.getFeld(6));
    }

    private static void checkSatzart(final Satz satz) {
        assertEquals(100, satz.getSatzart());
        NumFeld satzart = satz.getSatzartFeld();
        assertEquals(4, satzart.getAnzahlBytes());
        assertEquals(1, satzart.getByteAdresse());
    }

    /**
     * Hier ueberpruefen wir exemplarisch einige Felder aus Teildatensatz 1.
     */
    @Test
    public void testTeildatensatz1() {
        Teildatensatz tds = satz100.getTeildatensatz(1);
        Feld anrede = tds.getFeld(8);
        assertEquals("wrong: " + anrede, 1, anrede.getAnzahlBytes());
        assertEquals("wrong: " + anrede, 43, anrede.getByteAdresse());
    }

    /**
     * Hier versuchen wir die Satzart 0220.580.01 (Kapitel: Produktspezifischer
     * Teil, Abschnitt:Bausparen - Sparen / Antrag) einzulesen.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testSatz0220BausparenAntrag() throws XMLStreamException {
        XMLEventReader parser = createXMLEventReader("Satz220.580.01.xml");
        try {
            SatzXml satz220 = new SatzXml(parser);
            assertEquals(220, satz220.getSatzart());
            assertEquals(580, satz220.getSparte());
            assertEquals(1, satz220.getArt());
        } finally {
            parser.close();
        }
    }

}
