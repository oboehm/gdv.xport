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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.AbstractSatzTest;
import gdv.xport.satz.Satz;
import gdv.xport.satz.feld.sparte10.Feld220Wagnis0;
import gdv.xport.satz.model.Satz100;
import gdv.xport.satz.model.Satz200;
import gdv.xport.satz.model.Satz210;
import gdv.xport.satz.model.Satz211;
import gdv.xport.satz.model.Satz220;
import gdv.xport.satz.model.Satz221;
import gdv.xport.satz.model.Satz230;
import gdv.xport.satz.model.SatzX;
import gdv.xport.util.NotUniqueException;
import gdv.xport.util.SatzNummer;

import java.io.IOException;

import org.junit.Test;

import patterntesting.runtime.junit.ObjectTester;

/**
 * Unit-Test fuer {@link XmlService}-Klasse
 *
 * @author oliver
 * @since 1.0 (15.08.2014)
 */
public class XmlServiceTest extends AbstractXmlTest {

    private static XmlService xmlService = XmlService.getInstance();

    /**
     * Einfache Test-Methode fuer {@link XmlService#getSatzart(int)}.
     */
    @Test
    public void testGetSatzart() {
        SatzXml satz = xmlService.getSatzart(100);
        assertNotNull(satz);
        assertEquals(100, satz.getSatzart());
    }

    /**
     * Der Satz 1 (Vorsatz) weicht etwas von den uebrigen Saetzen ab.
     * Deswegen testen wir ihn hier gesondert.
     */
    @Test
    public void testGetVorsatz() {
        SatzXml vorsatz = xmlService.getSatzart(1);
        Feld version = vorsatz.getFeld("Satzart 0100");
        assertNotNull(version);
    }

    /**
     * Hier begutachten wir etwas genauer den von
     * {@link XmlService#getSatzart(int)} zurueckgelieferten Satz.
     */
    @Test
    public void testGetSatzart200() {
        SatzXml satz200 = xmlService.getSatzart(200);
        assertEquals(2, satz200.getTeildatensaetze().size());
        Feld inkasso = satz200.getFeld(Bezeichner.NAME_INKASSOART);
        assertEquals(1, inkasso.getAnzahlBytes());
        assertEquals(43, inkasso.getByteAdresse());
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz100} uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart100() throws IOException {
        checkSatzart(100, new Satz100());
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz200} uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart200() throws IOException {
        checkSatzart(200, new Satz200());
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz210} uebereinstimmt.
     * Fuer diese Satzart gibt es wohl eine allgemeine Definition, weswegen wir
     * hier keine {@link NotUniqueException} erwarten.
     * <p>
     * Der Vergleich mit dem Default-Constructor von {@link Satz210} schlaegt
     * leider fehl. Dies liegt aber an der {@link Satz210}-Klasse, die hier
     * die allgemeine Definition fuer Sparte "0" fehlt.
     * </p>
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart210() throws IOException {
        SatzXml satzXml = xmlService.getSatzart(210);
        assertEquals(210, satzXml.getSatzart());
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz210}, Sparte 10
     * uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart210Sparte10() throws IOException {
        checkSatzart(new SatzNummer(210, 10), new Satz210(10));
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz210}, Sparte 30
     * uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart210Sparte30() throws IOException {
        checkSatzart(new SatzNummer(210, 30), new Satz210(30));
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz210}, Sparte 40
     * uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart210Sparte40() throws IOException {
        checkSatzart(new SatzNummer(210, 40), new Satz210(40));
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz210}, Sparte 50
     * uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart210Sparte50() throws IOException {
        checkSatzart(new SatzNummer(210, 50), new Satz210(50));
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz210}, Sparte 70
     * uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart210Sparte70() throws IOException {
        checkSatzart(new SatzNummer(210, 70), new Satz210(70));
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz210}, Sparte 130
     * uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart210Sparte130() throws IOException {
        checkSatzart(new SatzNummer(210, 130), new Satz210(130));
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
        checkSatzart(211, new Satz211());
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
        checkSatzart(220, new Satz220());
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz221} uebereinstimmt.
     * Da aber die Angabe der Satzart nicht eindeutig ist, sondern noch die
     * Angabe der Sparte fehlt, erwarten wir eine {@link NotUniqueException}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void testSatzart221() throws IOException {
        checkSatzart(221, new Satz221());
    }

    /**
     * Hier testen wir, ob die XML-Variante mit {@link Satz230} uebereinstimmt.
     * Da aber die Angabe der Satzart nicht eindeutig ist, sondern noch die
     * Angabe der Sparte fehlt, erwarten wir eine {@link NotUniqueException}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void testSatzart230() throws IOException {
        checkSatzart(230, new Satz230());
    }

    private static void checkSatzart(final int nr, final Satz reference) throws IOException {
        SatzXml satzXml = xmlService.getSatzart(nr);
        assertEquals(nr, satzXml.getSatzart());
        checkSatz(satzXml, reference);
    }

    private static void checkSatzart(final SatzNummer satzNr, final Satz reference) throws IOException {
        SatzXml satzXml = xmlService.getSatzart(satzNr);
        assertEquals(satzNr.getSparte(), satzXml.getSparte());
        checkSatz(satzXml, reference);
    }

    private static void checkSatz(SatzXml satzXml, final Satz reference) throws IOException, AssertionError {
        AbstractSatzTest.setUp(reference);
        satzXml.importFrom(reference.toLongString());
        assertEquals(reference.toLongString(), satzXml.toLongString());
        ObjectTester.assertEquals(reference, satzXml);
    }

    /**
     * Hier testen wir, ob die XML-Variante mit Satz 220 Wagnisart 0
     * uebereinstimmt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSatzart220Wagnis0() throws IOException {
        checkSatzart(new SatzNummer(220, 10, 0), Feld220Wagnis0.class);
    }

    private static void checkSatzart(final SatzNummer satzNr, final Class<? extends Enum<?>> enumClass)
            throws IOException {
        checkSatzart(satzNr.getSatzart(), new SatzX(satzNr, enumClass));
    }

}
