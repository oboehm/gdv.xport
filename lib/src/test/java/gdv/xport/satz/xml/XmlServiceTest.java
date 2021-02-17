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

import gdv.xport.config.Config;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.AbstractSatzTest;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.feld.sparte10.Feld220Wagnis0;
import gdv.xport.satz.model.*;
import gdv.xport.util.NotRegisteredException;
import gdv.xport.util.NotUniqueException;
import gdv.xport.util.SatzTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import patterntesting.runtime.junit.CollectionTester;
import patterntesting.runtime.junit.ObjectTester;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit-Test fuer {@link XmlService}-Klasse
 *
 * @author oliver
 * @since 1.0 (15.08.2014)
 */
public class XmlServiceTest extends AbstractXmlTest {

    private static final Logger LOG = LogManager.getLogger(XmlServiceTest.class);
    private static final XmlService xmlService = XmlService.getInstance();

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
    @Test(expected = NotRegisteredException.class)
    public void testGetSatzartNonExisting() {
        xmlService.getSatzart(SatzTyp.of(451));
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
        one.set(Bezeichner.VERSICHERUNGSSCHEINNUMMER.getName(), "4711");
        two.set(Bezeichner.VERSICHERUNGSSCHEINNUMMER.getName(), "4722");
        assertEquals("4722", two.get(Bezeichner.VERSICHERUNGSSCHEINNUMMER.getName()).trim());
        assertEquals("4711", one.get(Bezeichner.VERSICHERUNGSSCHEINNUMMER.getName()).trim());
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

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz100} uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart100() throws IOException {
        if ("VUVM2018.xml".equals(Config.getXmlResource()) || "VUVM2015.xml".equals(Config.getXmlResource())) {
            checkSatzart(100, new Satz100());
        }
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz200} uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart200() throws IOException {
        if ("VUVM2018.xml".equals(Config.getXmlResource()) || "VUVM2015.xml".equals(Config.getXmlResource())) {
            checkSatzart(200, new Satz200());
        }
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz210} uebereinstimmt.
     * Fuer diese Satzart gibt es wohl eine allgemeine Definition, weswegen wir
     * hier keine {@link NotUniqueException} erwarten.
     * <p>
     * Der Vergleich mit dem Default-Constructor von {@link Satz210} schlaegt
     * leider fehl. Dies liegt aber an der {@link Satz210}-Klasse, da hier
     * die allgemeine Definition fuer Sparte "0" fehlt.
     * </p>
     */
    @Test
    public void testSatzart210() {
        SatzXml satzXml = xmlService.getSatzart(SatzTyp.of(210));
        assertEquals(210, satzXml.getSatzart());
        assertNotNull(satzXml.getFeld(Bezeichner.SATZ_NR_1));
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz210}, Sparte 50
     * uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart210Sparte50() throws IOException {
        checkSatzart(SatzTyp.of("0210.050"), new Satz210(50));
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz211} uebereinstimmt.
     * Fuer diese Satzart gibt es wohl eine allgemeine Definition, weswegen wir
     * hier keine {@link NotUniqueException} erwarten.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart211() throws IOException {
        setUpAndCheckSatz(xmlService.getSatzart(SatzTyp.of(211)), new Satz211());
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz220} uebereinstimmt.
     * Da aber die Angabe der Satzart nicht eindeutig ist, sondern noch die
     * Angabe der Sparte fehlt, erwarten wir eine {@link NotUniqueException}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart220() throws IOException {
        setUpAndCheckSatz(xmlService.getSatzart(SatzTyp.of(220)), new Satz220());
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz221} uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart221() throws IOException {
        checkSatzart221(30);
        checkSatzart221(40);
        checkSatzart221(52);
        checkSatzart221(53);
        checkSatzart221(55);
        checkSatzart221(59);
    }

    private void checkSatzart221(int sparte) throws IOException {
        checkSatzart(SatzTyp.of(221, sparte), new Satz221(sparte));
    }

    /**
     * Satzart 0221.070 hat einige Eigenheiten wie Satznummer auf Position 53
     * oder 2 Teildatensaetze. Deswegen wird er hier gesondert getestet.
     */
    @Test
    public void testSatzart0221070() {
        SatzXml satzXml = xmlService.getSatzart(SatzTyp.of("0221.070"));
        Satz221 reference = new Satz221(70);
        checkTeildatensaetze(satzXml, reference.getTeildatensaetze());
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz230} uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart230() throws IOException {
        checkSatzart230(10);
        if ("VUVM2018.xml".equals(Config.getXmlResource())) {
            checkSatzart230(30);
        }
    }

    private void checkSatzart230(int sparte) throws IOException {
        checkSatzart(SatzTyp.of(230, sparte), new Satz230(sparte));
    }

    private static void checkSatzart(final int nr, final Satz reference) throws IOException {
        SatzXml satzXml = xmlService.getSatzart(SatzTyp.of(String.format("%04d", nr)));
        assertEquals(nr, satzXml.getSatzart());
        checkSatz(satzXml, reference);
    }

    private static void checkSatzart(final SatzTyp satzNr, final Satz reference) throws IOException {
        SatzXml satzXml = xmlService.getSatzart(satzNr);
        assertEquals(satzNr.getSparte(), satzXml.getSparte());
        checkSatz(satzXml, reference);
    }

    private static void checkSatz(SatzXml satzXml, final Satz reference) throws IOException, AssertionError {
        checkTeildatensaetze(satzXml, reference.getTeildatensaetze());
        setUpAndCheckSatz(satzXml, reference);
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
     * Hier testen wir, ob die XML-Variante mit Satz 220 Wagnisart 0
     * uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart220Wagnis0() throws IOException {
        checkSatzart(SatzTyp.of(220, 10, 0), Feld220Wagnis0.class);
    }

    private static void checkSatzart(final SatzTyp satzNr, final Class<? extends Enum> enumClass)
            throws IOException {
        checkSatz(xmlService.getSatzart(satzNr), new SatzX(satzNr, enumClass));
    }

    /**
     * In "fehlendeFelder.xml" finden sich die Felder mit fehlenden
     * Referenzen. Dies ist der Test, ob sich diese Resource einlesen
     * laesst.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @Test
    public void testGetFehlendeFelder() throws XMLStreamException {
        XmlService felderService = XmlService.getInstance("fehlendeFelder.xml");
        Map<String, FeldXml> fehlendeFelder = felderService.getFelder();
        assertFalse("should be not empty: " + fehlendeFelder, fehlendeFelder.isEmpty());
        LOG.info("fehlendeFelder = {}", fehlendeFelder);
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
            //assertEquals(51, satznummer.getByteAdresse());
        }
        checkImport(satz220);
    }

    @Test
    public void testGetSatzarten() throws IOException {
        Map<SatzTyp, SatzXml> satzarten = xmlService.getSatzarten();
        for (Map.Entry<SatzTyp, SatzXml> entry : satzarten.entrySet()) {
            LOG.info("Pruefe Import fuer {}...", entry.getKey());
            checkImport(entry.getValue());
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
        assertEquals("Feld 21 ist nicht Beitragssumme, sondern " + beitragssumme, 129, beitragssumme.getByteAdresse());
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
    public void testGetSatzVersion() {
        String expected = "VUVM2013.xml".equals(Config.getXmlResource()) ? "2.3" :"2.4";
        assertEquals(expected, xmlService.getSatzVersion(SatzTyp.of("0001")));
    }

}
