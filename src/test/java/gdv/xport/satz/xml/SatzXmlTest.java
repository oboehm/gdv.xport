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

import static gdv.xport.satz.xml.AbstractXmlTest.createXMLEventReader;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.feld.NumFeld;
import gdv.xport.satz.AbstractDatensatzTest;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.feld.common.Feld1bis7;
import gdv.xport.satz.model.Satz100;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import patterntesting.runtime.junit.ObjectTester;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Unit tests for {@link SatzXml} class.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (31.07.2014)
 */
@RunWith(SmokeRunner.class)
public class SatzXmlTest extends AbstractDatensatzTest {

    private static SatzXml satz100;

    /**
     * Setzt ein SatzXml-Objekt fuer den Satz 100 auf. Dabei belegen wir die
     * einzelnen Felder in den Teildatensaetzen mit einem Wert, damit wir nicht
     * nur einen leeren Satz zum Testen haben.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws XMLStreamException the XML stream exception
     */
    @BeforeClass
    public static void setUpSatz100() throws IOException, XMLStreamException {
        satz100 = getSatz("Satz100.xml");
    }

    /**
     * Setzt ein SatzXml-Objekt mit Hilfe der uebergebenen Resource auf. Diese
     * Methode ist 'protected' damit sie auch von anderen Unit-Tests (wie z.B.
     * TeildatensatzXmlTest) zum Testen verwendet werden kann.
     * <p>
     * Wir belegen die einzelnen Felder in den Teildatensaetzen mit einem Wert,
     * damit wir nicht nur einen leeren Satz zum Testen haben.
     * </p>
     *
     * @param resource Name der Resource
     * @return the satz
     * @throws XMLStreamException the XML stream exception
     */
    protected static SatzXml getSatz(final String resource) throws XMLStreamException {
        XMLEventReader parser = createXMLEventReader(resource);
        try {
            SatzXml satz = new SatzXml(parser);
            Map<String, FeldXml> felder = XmlService.parseFelder(parser);
            satz.setFelder(felder);
            setUp(satz);
            return satz;
        } finally {
            parser.close();
        }
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return satz100;
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
     * Bei Satzart 100 scheint im erzeugten Datensatz das Feld fuer die
     * Personennummer (im Teildatensatz 2 von Byte 250-255) zu fehlen.
     */
    @Test
    public void testGetPersonennummer() {
        Feld personennummer = satz100.getFeld(new Bezeichner(Bezeichner.LFD_PERSONENNR_GEVO));
        assertEquals(6, personennummer.getAnzahlBytes());
        assertEquals(250, personennummer.getByteAdresse());
    }

    /**
     * 2 Teildatensaetze sind momentan in "Satz100.xml" defniert. Normalerweise
     * sollten es 5 sein, aber aus Uebersichtsgruenden sind nur 2
     * Teildatensaetze in der Test-Resource vorhanden.
     */
    @Test
    public void testGetTeildatensaetze() {
        Collection<Teildatensatz> teildatensaetze = satz100.getTeildatensaetze();
        assertEquals(2, teildatensaetze.size());
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

    /**
     * Test-Methode fuer {@link SatzXml#setFelder(Map)}. Diese Methode sollte
     * nur Informationen ergaenzen. Tatsaechlich scheint sie auch den Wert
     * zu ueberschreiben.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testSetFelder() throws XMLStreamException {
        XMLEventReader parser = createXMLEventReader("Satz100.xml");
        try {
            SatzXml satz = new SatzXml(parser);
            String expected = satz.getFeld(Feld1bis7.SATZART).getInhalt();
            Map<String, FeldXml> felder = XmlService.parseFelder(parser);
            satz.setFelder(felder);
            assertEquals(expected, satz.getFeld(Feld1bis7.SATZART).getInhalt());
        } finally {
            parser.close();
        }
    }

    /**
     * Hier testen wir die Gleichheit zweier gleicher Saetze.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testEqualsWithSatzXml() throws XMLStreamException {
        SatzXml satz = getSatz("Satz100.xml");
        assertEquals(satz100.toLongString(), satz.toLongString());
        ObjectTester.assertEquals(satz100, satz);
    }

    /**
     * Hier vergleichen wir {@link SatzXml} mit {@link Satz100}. Bei gleichen
     * Daten sollte die equals-Methode 'true' zurueckliefern.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testEqualsWithSatz100() throws IOException {
        Satz100 satz = new Satz100();
        satz.importFrom(satz100.toLongString());
        assertEquals(satz.toLongString(), satz100.toLongString());
        ObjectTester.assertEquals(satz, satz100);
    }

}
