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

import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.ByteAdresse;
import gdv.xport.feld.Feld;
import gdv.xport.feld.NumFeld;
import gdv.xport.satz.AbstractDatensatzTest;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.feld.common.Kopffelder1bis7;
import gdv.xport.util.SatzTyp;
import org.hamcrest.MatcherAssert;
import org.junit.BeforeClass;
import org.junit.Test;
import patterntesting.runtime.junit.ObjectTester;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static gdv.xport.satz.xml.AbstractXmlTest.createXMLEventReader;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link SatzXml} class.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (31.07.2014)
 */
public class SatzXmlTest extends AbstractDatensatzTest {

    private static SatzXml satz100;

    /**
     * Setzt ein SatzXml-Objekt fuer den Satz 100 auf. Dabei belegen wir die
     * einzelnen Felder in den Teildatensaetzen mit einem Wert, damit wir nicht
     * nur einen leeren Satz zum Testen haben.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @BeforeClass
    public static void setUpSatz100() throws XMLStreamException {
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
            Map<String, FeldXml> felder = getFelder();
            satz.setFelder(felder);
            setUp(satz);
            return satz;
        } finally {
            parser.close();
        }
    }

    private static Map<String, FeldXml> getFelder() throws XMLStreamException {
        XMLEventReader parser = createXMLEventReader("felder.xml");
        try {
            return XmlService.parseFelder(parser);
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
        Feld personennummer = satz100.getFeld(Bezeichner.LFD_PERSONENNR_GEVO);
        assertEquals(6, personennummer.getAnzahlBytes());
        assertEquals(250, personennummer.getByteAdresse());
    }

    /**
     * 2 Teildatensaetze sind momentan in "Satz100.xml" definiert. Normalerweise
     * sollten es 5 sein, aber aus Uebersichtsgruenden sind nur 2
     * Teildatensaetze in der Test-Resource vorhanden.
     */
    @Test
    public void testGetTeildatensaetze() {
        Collection<Teildatensatz> teildatensaetze = satz100.getTeildatensaetze();
        assertEquals(2, teildatensaetze.size());
        char expectedNr = '1';
        for (Teildatensatz tds : teildatensaetze) {
            assertEquals(expectedNr, tds.getSatznummer().toChar());
            expectedNr++;
            checkTeildatensatz(tds);
        }
    }

    private static void checkTeildatensatz(final Teildatensatz tds) {
        checkSatzart(tds);
        Feld feld = tds.getFeld(Bezeichner.VU_NR);
        assertNotNull("VU-Nummer missing", feld);
        assertEquals(5, feld.getAnzahlBytes());
        assertEquals(5, feld.getByteAdresse());
        assertEquals(feld, tds.getFeld(2));
        assertEquals(tds.getFeld(Bezeichner.FOLGENUMMER), tds.getFeld(6));
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
        XMLEventReader parser = createXMLEventReader("Satz0220.580.01.xml");
        try {
            SatzXml satz220 = new SatzXml(parser);
            assertEquals(220, satz220.getSatzart());
            assertEquals(580, satz220.getSparte());
            assertEquals(1, satz220.getArt());
        } finally {
            parser.close();
        }
    }

    @Test
    public void testSatz0221070() throws XMLStreamException {
        XMLEventReader parser = createXMLEventReader("Satz0221.070.xml");
        try {
            SatzXml satz221 = new SatzXml(parser);
            satz221.setFelder(getFelder());
            setUp(satz221);
            assertEquals(221, satz221.getSatzart());
            assertEquals(70, satz221.getSparte());
            assertEquals(2, satz221.getNumberOfTeildatensaetze());
            checkSatznummer(satz221, 1, ByteAdresse.of(53));
            checkSatznummer(satz221, 2, ByteAdresse.of(53));
        } finally {
            parser.close();
        }
    }

    @Test
    public void testSatz0221030() throws XMLStreamException, IOException {
        SatzXml satz221 = SatzXml.of("Satz0221.030.xml");
        assertEquals(221, satz221.getSatzart());
        assertEquals(30, satz221.getSparte());
        assertEquals(2, satz221.getNumberOfTeildatensaetze());
        checkSatznummer(satz221, 2, ByteAdresse.of(49));
        checkSatznummer(satz221, 3, ByteAdresse.of(43));
    }

    private void checkSatznummer(SatzXml satz, int nr, ByteAdresse adr) {
        Teildatensatz tds = satz.getTeildatensatzBySatzNr(nr);
        Feld feld = tds.getFeld(Bezeichner.SATZNUMMER);
        assertEquals(Integer.toString(nr), feld.getInhalt());
        assertEquals(adr.intValue(), feld.getByteAdresse());
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
            String expected = satz.getFeld(Kopffelder1bis7.SATZART.getBezeichner()).getInhalt();
            Map<String, FeldXml> felder = getFelder();
            satz.setFelder(felder);
            assertEquals(expected, satz.getFeld(Kopffelder1bis7.SATZART.getBezeichner()).getInhalt());
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
     * Beim Satz in "Satz0212.050.xml" handelt es sich um einen Satz mit
     * Sparte 50 (und Satzart 212).
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testSparte50() throws XMLStreamException {
        SatzXml sparte50 = getSatz("Satz0212.050.xml");
        assertEquals(212, sparte50.getSatzart());
        assertEquals(50, sparte50.getSparte());
    }

    /**
     * Bei Satz 210 fehlte die anfaengliche Satznummer.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testSatz210() throws XMLStreamException {
        SatzXml satz210 = getSatz("Satz0210.xml");
        assertNotNull(satz210.getFeld(Bezeichner.ERWEITERTER_BERVERSV_SCHLUESSEL));
        assertNotNull(satz210.getFeld(Bezeichner.SATZNUMMER));
    }

    /**
     * Mit Satz 210 Sparte 10 gab es kleinere Probleme mit einigen Feldern.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testSatz210Sparte10() throws XMLStreamException {
        SatzXml satz210 = getSatz("Satz0210.010.xml");
        Feld zuzahlungsbetrag = satz210.getFeld(Bezeichner.ZUZAHLUNGSBETRAG_IN_WE);
        assertEquals(160, zuzahlungsbetrag.getByteAdresse());
    }

    /**
     * Und auch mit Satz 210 Sparte 30 gab es kleinere Probleme - mit dem Feld
     * 'Vertragsstatus'.
     *
     * @throws XMLStreamException the XML stream exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatz210Sparte30() throws XMLStreamException, IOException {
        SatzXml satz210 = getSatz("Satz0210.030.xml");
        checkVertragsstatus(satz210);
        checkImportExport(satz210, "02109999  030      599999999990199990099991010520040105200901052"
                + "0040901 0000000000000000000 EUR000000000000000000000000000000041"
                + "1410000000000 0001000                                           "
                + "           000000                                               ");
        assertEquals("1", satz210.getFeld(Bezeichner.VERTRAGSSTATUS).getInhalt());
    }

    private static void checkImportExport(final SatzXml satz, final String content) throws IOException {
        satz.importFrom(content);
        StringWriter writer = new StringWriter(256);
        satz.export(writer);
        writer.flush();
        writer.close();
        assertEquals(content.trim(), writer.toString().trim());
    }

    /**
     * Und auch mit Satz 210 Sparte 40 gab es kleinere Probleme - mit dem Feld
     * 'Vertragsstatus'.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testSatz210Sparte40() throws XMLStreamException {
        SatzXml satz210 = getSatz("Satz0210.040.xml");
        checkVertragsstatus(satz210);
    }

    private void checkVertragsstatus(SatzXml satz210) {
        Feld vertragsstatus = satz210.getFeld(Bezeichner.VERTRAGSSTATUS);
        assertEquals(43, vertragsstatus.getByteAdresse());
    }

    /**
     * Test-Methode fuer {@link SatzXml#getSupportedSatzTypen()}.
     */
    @Test
    public void testGetSupportedSatzTypen() {
        List<SatzTyp> supported = satz100.getSupportedSatzTypen();
        assertEquals(1, supported.size());
        assertEquals(SatzTyp.of(100), supported.get(0));
    }

    /**
     * Die Satzart "Spartenspezifischer Teil Leben" (0220.010.0) enthaelt die
     * Wagnisart 0. Hier wird geprueft, ob
     * {@link SatzXml#getSupportedSatzTypen()} dies korrekt zurueckgibt.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testSatz220Wagnis0() throws XMLStreamException {
        SatzXml satz220 = getSatz("Satz0220.010.0.xml");
        List<SatzTyp> supported = satz220.getSupportedSatzTypen();
        assertEquals(SatzTyp.of(220, 10, 0), supported.get(0));
    }

    @Test
    public void testCopyCtor() throws XMLStreamException {
        SatzXml orig = getSatz("Satz0220.010.0.xml");
        SatzXml copy = new SatzXml(orig);
        assertEquals(orig, copy);
        copy.setFeld(Bezeichner.BERUFSSCHLUESSEL, "123");
        assertNotEquals(orig, copy);
    }

    @Test
    public void testOfFile() throws IOException, XMLStreamException {
        File xmlFile = new File("src/test/resources/gdv/xport/satz/xml/Satz100.xml");
        assertTrue(xmlFile.exists());
        SatzXml fromXML = SatzXml.of(xmlFile);
        assertNotNull(fromXML);
        MatcherAssert.assertThat(fromXML.getFelder().size(), greaterThan(10));
        assertEquals(100, fromXML.getSatzart());
    }

    @Test
    public void testOfResource() throws IOException, XMLStreamException {
        SatzXml fromXML = SatzXml.of("Satz0221.051.xml");
        assertEquals(SatzTyp.of("0221.051"), fromXML.getSatzTyp());
    }

    @Test
    public void testOfURI() throws IOException, XMLStreamException {
        String resource = "/gdv/xport/satz/xml/Satz0820.xml";
        URI classpathURI = URI.create("classpath:" + resource);
        URI fileURI = new File("src/test/resources", resource).toURI();
        SatzXml fromResource = SatzXml.of(resource);
        SatzXml fromClasspath = SatzXml.of(classpathURI);
        SatzXml fromFile = SatzXml.of(fileURI);
        assertEquals(fromResource, fromClasspath);
        assertEquals(fromResource, fromFile);
    }

}
