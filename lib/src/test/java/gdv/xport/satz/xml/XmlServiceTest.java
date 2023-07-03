/*
 * Copyright (c) 2014-2023 by Oli B.
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

import gdv.xport.config.Config;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.AbstractSatzTest;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.util.NotRegisteredException;
import gdv.xport.util.SatzTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIn;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import patterntesting.runtime.junit.CollectionTester;
import patterntesting.runtime.junit.ObjectTester;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-Test fuer {@link XmlService}-Klasse
 *
 * @author oliver
 * @since 1.0 (15.08.2014)
 */
public class XmlServiceTest extends AbstractXmlTest {

    private static final Logger LOG = LogManager.getLogger(XmlServiceTest.class);
    private static XmlService xmlService;

    @BeforeAll
    public static void setUpXmlService() throws XMLStreamException, IOException {
        xmlService = XmlService.getInstance("VUVM2018.xml");
    }

    /**
     * Einfache Test-Methode fuer {@link XmlService#getSatzart(SatzTyp)}.
     */
    @Test
    public void testGetSatzart() {
        SatzXml satz = xmlService.getSatzart(SatzTyp.of(100));
        assertNotNull(satz);
        assertEquals(100, satz.getSatzart());
    }

    /**
     * Fuer eine nicht registrierte Satzart sollte eine entsprechende
     * Excption kommen.
     */
    @Test
    public void testGetSatzartNonExisting() {
        assertThrows(NotRegisteredException.class, () -> xmlService.getSatzart(SatzTyp.of(451)));
    }

    /**
     * Einfache Test-Methode fuer {@link XmlService#getSatzart(SatzTyp)}. Wir
     * moechten jedesmal ein "frisches" {@link SatzXml}-Objekt bekommen. Daher
     * holen wir ihn uns zweimal...
     */
    @Test
    public void testGetSatzartTwice() {
        SatzXml one = xmlService.getSatzart(SatzTyp.of(100));
        SatzXml two = xmlService.getSatzart(SatzTyp.of(100));
        assertEquals(one, two);
        assertNotSame(one, two);
        for (int i = 1; i <= one.getNumberOfTeildatensaetze(); i++) {
            assertEquals(one.getTeildatensatz(i), two.getTeildatensatz(i));
            assertNotSame(one.getTeildatensatz(i), two.getTeildatensatz(i));
        }
        one.setFeld(Bezeichner.VERSICHERUNGSSCHEINNUMMER, "4711");
        two.setFeld(Bezeichner.VERSICHERUNGSSCHEINNUMMER, "4722");
        assertEquals("4722", two.getFeld(Bezeichner.VERSICHERUNGSSCHEINNUMMER).getInhalt().trim());
        assertEquals("4711", one.getFeld(Bezeichner.VERSICHERUNGSSCHEINNUMMER).getInhalt().trim());
    }

    /**
     * Der Satz 1 (Vorsatz) weicht etwas von den uebrigen Saetzen ab.
     * Deswegen testen wir ihn hier gesondert.
     */
    @Test
    public void testGetVorsatz() {
        SatzXml vorsatz = xmlService.getSatzart(SatzTyp.of(1));
        Feld version = vorsatz.getFeld("Satzart 0100");
        assertNotNull(version);
    }

    /**
     * Hier begutachten wir etwas genauer den von
     * {@link XmlService#getSatzart(SatzTyp)} zurueckgelieferten Satz.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testGetSatzart200() throws IOException {
        SatzXml satz200 = xmlService.getSatzart(SatzTyp.of(200));
        assertEquals(2, satz200.getTeildatensaetze().size());
        Feld inkasso = satz200.getFeld(Bezeichner.INKASSOART);
        assertEquals(1, inkasso.getAnzahlBytes());
        assertEquals(43, inkasso.getByteAdresse());
        checkImport(satz200);
    }

    @Test
    public void testSatzart210() {
        SatzXml satzXml = xmlService.getSatzart(SatzTyp.of(210));
        assertEquals(210, satzXml.getSatzart());
        assertNotNull(satzXml.getFeld(Bezeichner.SATZNUMMER));
    }

    @Test
    public void testSatzart210Sparte0() {
        SatzXml satzXml = xmlService.getSatzart(SatzTyp.of("0210.000"));
        assertEquals(210, satzXml.getSatzart());
        assertEquals(SatzTyp.of("0210.000"), satzXml.getSatzTyp());
    }

    private static void checkImport(SatzXml satz) throws IOException {
        SatzXml reference = new SatzXml(satz);
        setUpAndCheckSatz(satz, reference);
    }

    private static void setUpAndCheckSatz(SatzXml satzXml, final Satz reference) throws IOException, AssertionError {
        AbstractSatzTest.setUp(reference);
        String importString = reference.toLongString();
        satzXml.importFrom(importString);
        assertEquals(importString, satzXml.toLongString());
        ObjectTester.assertEquals(reference, satzXml);
    }

    private static void checkTeildatensaetze(final SatzXml satzXml, final List<Teildatensatz> reference) {
        for (int i = 0; i < reference.size(); i++) {
            LOG.debug("Checking Teildatensatz {}...", i+1);
            checkFelder(satzXml.getTeildatensatz(i+1), reference.get(i).getFelder());
        }
    }

    private static void checkFelder(final Teildatensatz tds, Collection<Feld> felder) {
        CollectionTester.assertEquals(tds.getFelder(), felder);
    }

    /**
     * Dies ist ein weiterer Testfall fuer Issue #33. Hierbei sollte keine
     * {@link NotRegisteredException} auftauchen.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testSatzart350() throws IOException {
        SatzXml satz350 = xmlService.getSatzart(SatzTyp.of(350));
        assertNotNull(satz350);
        checkImport(satz350);
    }

    /**
     * Hier ist die Besonderheit, dass Satzart "0220.140" die Satznummer auf
     * Position 51 hat.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testSatzart0220140() throws IOException {
        SatzXml satz220 = xmlService.getSatzart(SatzTyp.of("0220.140"));
        List<Teildatensatz> teildatensaetze = satz220.getTeildatensaetze();
        assertEquals(2, teildatensaetze.size());
        for (int i = 0; i < teildatensaetze.size(); i++) {
            Teildatensatz tds = teildatensaetze.get(i);
            Zeichen satznummer = tds.getSatznummer();
            assertEquals(i+1, satznummer.toInt());
        }
        checkImport(satz220);
    }

    @Test
    public void testSatzart0225() {
        SatzXml satz = xmlService.getSatzart(SatzTyp.of("0225.010"));
        assertNotNull(satz);
    }

    @Test
    public void testSatzart022058001() {
        SatzXml satz = xmlService.getSatzart(SatzTyp.of("0220.580.01"));
        assertFalse(satz.hasVuNummer());
        MatcherAssert.assertThat(satz.getArt(), IsIn.oneOf(0, 1));
        assertEquals(4, satz.getNumberOfTeildatensaetze());
        checkSatzart220580(satz);
    }

    @Test
    public void testSatzart02205802() {
        SatzXml satz = xmlService.getSatzart(SatzTyp.of("0220.580.2"));
        assertTrue(satz.hasVuNummer());
        assertEquals(2, satz.getArt());
        assertEquals(3, satz.getNumberOfTeildatensaetze());
        checkSatzart220580(satz);
    }

    private void checkSatzart220580(SatzXml satz) {
        for (int n = 1; n <= satz.getNumberOfTeildatensaetze(); n++) {
            Zeichen satznr = satz.getTeildatensatz(n).getSatznummer();
            assertEquals(n, satznr.toInt());
            assertEquals(43, satznr.getByteAdresse());
        }
    }

    /**
     * Testfall fuer Issue
     * <a href="https://github.com/oboehm/gdv.xport/issues/85">#85</a>.
     */
    @Test
    public void testSatzart220052() {
        SatzXml satz = xmlService.getSatzart(SatzTyp.of("0220.052"));
        NumFeld deckungsart = satz.getFeld(Bezeichner.KFV_DECKUNGSART, NumFeld.class);
        assertEquals(2, deckungsart.getAnzahlBytes());
        assertEquals(0, deckungsart.getNachkommastellen());
    }

    @Test
    public void testGetSatzarten() throws IOException {
        Map<SatzTyp, SatzXml> satzarten = xmlService.getSatzarten();
        for (Map.Entry<SatzTyp, SatzXml> entry : satzarten.entrySet()) {
            LOG.debug("Pruefe Import fuer {}...", entry.getKey());
            checkImport(entry.getValue());
        }
    }

    @Test
    public void testGetSatzarten221Wagnisart48() {
        Map<SatzTyp, SatzXml> satzarten = xmlService.getSatzarten();
        SatzXml satz = satzarten.get(SatzTyp.of("0221.010.48.1"));
        MatcherAssert.assertThat(satz.getWagnisart(), either(is("4")).or(is("8")));
    }

    @Test
    public void testGetSatzart221Wagnisart48() {
        SatzXml satz = xmlService.getSatzart(SatzTyp.of("0221.010.48.1"));
        MatcherAssert.assertThat(satz.getWagnisart(), either(is("4")).or(is("8")));
    }

    @Test
    public void testSatzart220Wagnisdaten() throws IOException {
        checkImport(xmlService.getSatzart(SatzTyp.of("0220.030")));
    }

    @Test
    public void testCloning() {
        Map<SatzTyp, SatzXml> satzarten = xmlService.getSatzarten();
        for (Map.Entry<SatzTyp, SatzXml> entry : satzarten.entrySet()) {
            LOG.debug("Pruefe Clonen von {}...", entry.getKey());
            checkCloning(entry.getValue());
        }
    }

    private static void checkCloning(SatzXml orig) {
        SatzXml copy = new SatzXml(orig);
        assertEquals(orig, copy);
        assertNotSame(orig, copy);
        checkFeldCloning(orig, copy);
        for (int i = 1; i <= orig.getNumberOfTeildatensaetze(); i++) {
            checkFeldCloning(orig.getTeildatensatz(i), copy.getTeildatensatz(i));
        }
    }

    private static void checkFeldCloning(Satz orig, Satz copy) {
        for (Feld feld : orig.getFelder()) {
            Feld copyFeld = copy.getFeld(feld.getBezeichner());
            assertEquals(feld, copyFeld);
            assertNotSame(feld, copyFeld);
            String inhalt = feld.getInhalt();
            feld.setInhalt("9");
            copyFeld.setInhalt("8");
            assertNotEquals(feld, copyFeld);
            feld.setInhalt(inhalt);
            copyFeld.setInhalt(inhalt);
        }
    }

    /**
     * Satzart 0220.010.9.9 macht noch Probleme. Feld Nr. 21 - 28 scheinen
     * im Teildatensatz 9 zu fehlen.
     */
    @Test
    public void testLebenTeildatensatz9() {
        SatzXml wertungssummen = xmlService.getSatzart(SatzTyp.of("0220.010.9.9"));
        Teildatensatz teildatensatz9 = wertungssummen.getTeildatensatz(1);
        Feld haftungsbeginn = teildatensatz9.getFeld(20);
        assertEquals(128, haftungsbeginn.getEndAdresse());
        Feld beitragssumme = teildatensatz9.getFeld(21);
        assertEquals(129, beitragssumme.getByteAdresse(), "Feld 21 ist nicht Beitragssumme, sondern " + beitragssumme);
    }

    @Test
    public void testBausparenArt() {
        SatzTyp bausparen = SatzTyp.of("0220.580.2");
        Satz darlehen = xmlService.getSatzart(bausparen);
        assertEquals(220, darlehen.getSatzart());
        assertEquals(580, darlehen.getSparte());
        assertEquals(580, darlehen.getTeildatensatz(2).getSparte());
        assertEquals(580, darlehen.getTeildatensatz(3).getSparte());
        Feld produkt = darlehen.getFeld(Bezeichner.of("Produkt"));
        assertEquals("580", produkt.getInhalt());
        assertEquals("580", darlehen.getTeildatensatz(2).getFeld(Bezeichner.SPARTE).getInhalt());
        assertEquals("580", darlehen.getTeildatensatz(3).getFeld(Bezeichner.SPARTE).getInhalt());
        Feld art = darlehen.getFeld(Bezeichner.of("Art"));
        assertEquals("2", art.getInhalt());
    }

    @Test
    public void testGetSatzartenAndChangeBausparenArt() throws IOException {
        SatzTyp bausparen = SatzTyp.of("0220.580.2");
        Map<SatzTyp, SatzXml> satzarten = xmlService.getSatzarten();
        SatzXml satzXml = satzarten.get(bausparen);
        checkImport(satzXml);
        SatzXml darlehen = xmlService.getSatzart(bausparen);
        assertEquals("2", darlehen.getFeld(Bezeichner.of("Art")).getInhalt());
    }

    @Test
    public void testZusaetzlicheKennung() {
        Satz satz = xmlService.getSatzart(SatzTyp.of("0220.030"));
        Zeichen satzkennung = satz.getFeld(Bezeichner.ZUSAETZLICHE_SATZKENNUNG, Zeichen.class);
        assertEquals("X", satzkennung.getInhalt());
    }

    @Test
    public void testGetSatzVersion2018() throws XMLStreamException, IOException {
        assertEquals("2.4", XmlService.getInstance("VUVM2018.xml").getSatzVersion(SatzTyp.of("0001")));
    }

    @Test
    public void testGetSatzVersion2015() throws XMLStreamException, IOException {
        assertEquals("2.3", XmlService.getInstance("VUVM2013.xml").getSatzVersion(SatzTyp.of("0001")));
    }

    @Test
    public void testGetInstanceURI() throws URISyntaxException, XMLStreamException, IOException {
        URI uri = getClass().getResource("VUVM2018.xml").toURI();
        XmlService instance = XmlService.getInstance(uri);
        assertNotNull(instance);
    }

    @Test
    public void testVUVM2018() throws XMLStreamException, IOException {
        compareXml("VUVM2018xL.xml", "VUVM2018.xml");
    }

    @Test
    public void testVUVM2015() throws XMLStreamException, IOException {
        compareXml("VUVM2015xL.xml", "VUVM2015.xml");
    }

    @Test
    public void testVUVM2013() throws XMLStreamException, IOException {
        compareXml("VUVM2013xL.xml", "VUVM2013.xml");
    }

    @Test
    public void testVUVM2009() throws XMLStreamException, IOException {
        compareXml("VUVM2009xL.xml", "VUVM2009.xml");
    }

    private static void compareXml(String refResource, String resource) throws XMLStreamException, IOException {
        XmlService refService = XmlService.getInstance(refResource);
        XmlService service = XmlService.getInstance(resource);
        assertEquals(refService.getGdvRelease(), service.getGdvRelease());
        for (Map.Entry<SatzTyp, SatzXml> entry : refService.getSatzarten().entrySet()) {
            SatzXml satz = service.getSatzart(entry.getKey());
            assertEquals(entry.getValue().toLongString(), satz.toLongString());
        }
    }

    @Test
    public void testAlignment() throws XMLStreamException, IOException {
        XmlService service = XmlService.getInstance("VUVM2018.xml");
        SatzXml satz = service.getSatzart(SatzTyp.of("0100"));
        Feld rechtsbuendig = satz.getFeld(Bezeichner.VERSICHERUNGSSCHEINNUMMER);
        rechtsbuendig.setInhalt("0123456789abcdef");
        assertEquals(" 0123456789abcdef", rechtsbuendig.getInhalt());
    }

    @Test
    public void testConfig() throws XMLStreamException, IOException {
        XmlService service = XmlService.getInstance(Config.STRICT);
        Satz satz100 = service.getSatzart(SatzTyp.of(100));
        assertThrows(IllegalArgumentException.class, () -> satz100.setFeld(Bezeichner.FOLGENUMMER, "x"));
        Feld folgenummer = satz100.getFeld(Bezeichner.FOLGENUMMER);
        assertThrows(IllegalArgumentException.class, () -> folgenummer.setInhalt("x"));
    }

    @Test
    public void testSetNumFeldWithLetter() throws XMLStreamException, IOException {
        Config mitValidierung = Config.DEFAULT.withProperty("gdv.feld.validate", "lax");
        Satz satz200 = XmlService.getInstance(mitValidierung).getSatzart(SatzTyp.of("0200"));
        assertThrows(IllegalArgumentException.class,
                () -> satz200.setFeld(Bezeichner.GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN, "A99999999999"));
    }

    @Test
    public void testConfigVorsatz() throws XMLStreamException, IOException {
        SatzXml vorsatz = XmlService.getInstance(Config.STRICT).getSatzart(SatzTyp.of(1));
        assertEquals(SatzTyp.of(1), vorsatz.getSatzTyp());
        assertEquals(Config.STRICT, vorsatz.getConfig());
    }

//    @Test
//    public void testRegisterSatzart() throws XMLStreamException, IOException {
//        xmlService.registerSatzart(URI.create("classpath:/gdv/xport/satz/xml/Satz0820.xml"));
//        Satz satz820 = xmlService.getSatzart(SatzTyp.of(820));
//        assertNotNull(satz820);
//        assertEquals(SatzTyp.of(820), satz820.getSatzTyp());
//    }

}
