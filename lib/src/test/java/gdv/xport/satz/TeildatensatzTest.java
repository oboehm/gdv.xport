/*
 * Copyright (c) 2009 - 2024 by Oli B.
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
 * (c)reated 14.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.satz.xml.XmlService;
import gdv.xport.util.NotUniqueException;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzRegistry;
import gdv.xport.util.SatzTyp;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test-Klasse fuer {@link Teildatensatz}.
 *
 * @author oliver
 * @since 14.10.2009
 */
public class TeildatensatzTest extends AbstractSatzTest {

    private static final Logger LOG = LogManager.getLogger(TeildatensatzTest.class);
    private static final SatzRegistry SATZ_REGISTRY = SatzRegistry.getInstance();

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Teildatensatz(SatzTyp.of(123, 1));
    }

    /**
     * Die einzelnen Felder sollten in der Reihenfolge der Byte-Adresse
     * geliefert werden.
     */
    @Test
    public void testGetFelder() {
        Teildatensatz teildatensatz = new Vorsatz().getTeildatensatz(1);
        Iterator<Feld> iterator = teildatensatz.getFelder().iterator();
        Feld prev = iterator.next();
        while (iterator.hasNext()) {
            Feld next = iterator.next();
            LOG.info("Feld: {}", next);
            assertTrue("wrong sorted: " + prev + " > " + next, prev.getByteAdresse() < next
                    .getByteAdresse());
            prev = next;
        }
    }

    /**
     * Test-Methode fuer {@link Teildatensatz#getFeld(Bezeichner)}.
     */
    @Test
    public void testGetFeldBezeichner() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of(100), 1);
        Bezeichner hello = Bezeichner.of("Hello");
        Feld feld = new NumFeld(hello, 5, ByteAdresse.of(55));
        feld.setInhalt("World");
        tds.add(feld);
        assertEquals(feld, tds.getFeld(hello));
    }

    @Test
    public void testGetFeldByteAdresse() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of(4711), 1);
        ByteAdresse adresse = ByteAdresse.of(11);
        Feld feld = new NumFeld(Bezeichner.PRODUKTNAME, 47, adresse);
        tds.add(feld);
        assertEquals(feld, tds.getFeld(adresse));
    }

    /**
     * Bei der internen Umstellung des {@link Teildatensatz}es auf die
     * erweiterte {@link Bezeichner}-Klasse gab es Probleme mit dem Loeschen
     * von Feldern.
     */
    @Test
    public void testRemove() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of(100), 1);
        Zeichen satznummer = new Zeichen(Bezeichner.of("Satznummer"), ByteAdresse.of(256));
        satznummer.setInhalt('1');
        tds.add(satznummer);
        assertEquals(satznummer, tds.getFeld(satznummer.getBezeichner()));
        tds.remove(satznummer.getBezeichnung());
        Executable executable = () -> tds.getFeld(satznummer.getBezeichner());
        IllegalArgumentException assertThrows = assertThrows(IllegalArgumentException.class, executable);
        MatcherAssert.assertThat("Exception sollte Bezeichner und Satzart beschreiben", assertThrows.getMessage(),
                allOf(containsString(satznummer.getBezeichner().toString()), containsString("Satzart 0100")));
    }

    /**
     * Bei der internen Umstellung des {@link Teildatensatz}es auf die
     * erweiterte {@link Bezeichner}-Klasse gab es Probleme mit dem Loeschen
     * von Feldern.
     */
    @Test
    public void testRemoveSafe() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of(100), 1);
        Zeichen satznummer = new Zeichen(Bezeichner.of("Satznummer"), ByteAdresse.of(256));
        satznummer.setInhalt('1');
        tds.add(satznummer);
        assertEquals(satznummer, tds.getFeld(satznummer.getBezeichner()));
        tds.remove(satznummer.getBezeichnung());
        assertFalse(tds.hasFeld(satznummer.getBezeichner()));
    }

    /**
     * Hier testen wir, ob mit dem CopyConstructor
     * {@link Teildatensatz#Teildatensatz(Teildatensatz)} tatsaechlich eine
     * Kopie angelegt wird.
     */
    @Test
    public void testCopyConstructor() {
        Teildatensatz orig = new Teildatensatz(SatzTyp.of(100), 1);
        Feld name1 = new AlphaNumFeld(Bezeichner.NAME1, 30, ByteAdresse.of(44));
        name1.setInhalt("Mickey");
        orig.add(name1);
        Teildatensatz copy = new Teildatensatz(orig);
        assertEqualsFeld(orig.getFeld(Bezeichner.NAME1), copy.getFeld(Bezeichner.NAME1));
        assertEquals(orig, copy);
        copy.setFeld(Bezeichner.NAME1, "Goofy");
        assertEquals("Goofy", copy.getFeld(Bezeichner.NAME1).getInhalt().trim());
        assertEquals("Mickey", orig.getFeld(Bezeichner.NAME1).getInhalt().trim());
    }

    private static void assertEqualsFeld(final Feld one, final Feld two) {
        assertEquals(one, two);
        assertEquals(one.getClass(), two.getClass());
    }

    /**
     * Mit der Validierung sollten auch fehlerhafte IBANs erkannt werden.
     */
    @Test
    public void testValidateIBAN() {
        Teildatensatz adressteil4 = SatzFactory.getSatz(SatzTyp.of("0100")).getTeildatensatz(4);
        assertTrue("should be valid: " + adressteil4, adressteil4.isValid());
        Feld iban1 = adressteil4.getFeld(Bezeichner.IBAN1);
        iban1.setInhalt("DE99300606010006605605");
        List<ConstraintViolation> violations = adressteil4.validate();
        assertEquals(1, violations.size());
    }

    /**
     * Falls versucht wird, ueberlappende Felder hinzuzufuegen, sollte das
     * zurueckgewiesen werden.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddOverlapping() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of(4711), 1);
        tds.add(new AlphaNumFeld(Bezeichner.NAME1, 10, ByteAdresse.of(100)));
        tds.add(new AlphaNumFeld(Bezeichner.NAME2, 10, ByteAdresse.of(101)));
    }

    @Test
    public void testGetSatznummer() {
        Satz satz220 = SATZ_REGISTRY.getSatz(SatzTyp.of("0220.010.13.1"));
        for (int n = 1; n < satz220.getNumberOfTeildatensaetze(); n++) {
            checkSatznummer(satz220, n, ByteAdresse.of(256), Character.forDigit(n, 10));
        }
    }

    @Test
    public void testGetSatznummer220Fahrzeughaftpflicht() {
        Satz satz220 = SATZ_REGISTRY.getSatz(SatzTyp.of("0220.051"));
        checkSatznummer(satz220, 1, ByteAdresse.of(256), '1');
        checkSatznummer(satz220, 2, ByteAdresse.of(256), '2');
    }

    @Test
    public void testGetSatznummer220Wagnisdaten() {
        Satz satz220 = SATZ_REGISTRY.getSatz(SatzTyp.of("0220.030"));
        checkSatznummer(satz220, 1, ByteAdresse.of(49), '1');
        checkSatznummer(satz220, 2, ByteAdresse.of(49), '2');
        checkSatznummer(satz220, 3, ByteAdresse.of(43), '3');
        checkSatznummer(satz220, 4, ByteAdresse.of(49), '4');
        checkSatznummer(satz220, 5, ByteAdresse.of(60), '9');
    }

    private void checkSatznummer(Satz satz, int n, ByteAdresse start, char inhalt) {
        Teildatensatz tds = satz.getTeildatensatz(n);
        Zeichen satznummer = tds.getSatznummer();
        Zeichen expected = new Zeichen(satznummer.getBezeichner(), start, inhalt);
        assertEquals(expected, satznummer);
    }

    @Test
    public void testGetMulipleSatznummer() throws XMLStreamException, IOException {
        Satz satz220 = XmlService.getInstance("VUVM2013.xml").getSatzart(SatzTyp.of("0220.140"));
        Teildatensatz tds = satz220.getTeildatensatz(2);
        Zeichen satznummer = tds.getSatznummer();
        Feld satzNr2 = tds.getFeld(ByteAdresse.of(51));
        Feld satzNr = tds.getFeld(ByteAdresse.of(256));
        assertEquals("2", satznummer.getInhalt());
        assertEquals("2", satzNr2.getInhalt());
        assertEquals("2", satzNr.getInhalt());
    }

    /**
     * Dieser Test geht auf Klaus zurueck. Hier der Auszug aus seiner Mail:
     * <p>
     * In der Version 2013 gibt es 2 Teildatensaetze, die irrtuemlich 2x das
     * Feld „Satznummer“ beinhalten:
     * </p>
     * <ul>
     * <li>SA0220.140, TD2, Feld 10 und Feld 38</li>
     * <li>SA0500, TD1, Feld 10 und Feld 40.</li>
     * </ul>
     * <p>
     * Die Adresse der „Satznummer“-Felder spielt hier keine Rolle, weil
     * nirgends festgelegt ist, welche Adresse massgeblich ist für die Adresse
     * in SATZNUMMER! Sie muss nur zu der eines Feldes mit Bezeichnung
     * „Satznummer“ passen.
     * Das Problem ist hier einfach der Fehler in der Satzstruktur.
     * </p>
     *
     * @throws XMLStreamException im Fehlerfall
     * @throws IOException        im Fehlerfall
     */
    @Test
    public void testValidateMulipleSatznummer() throws XMLStreamException, IOException {
        Satz satz220 = XmlService.getInstance("VUVM2013.xml").getSatzart(SatzTyp.of("0220.140"));
        Teildatensatz tds = satz220.getTeildatensatz(2);
        List<ConstraintViolation> violations = tds.validate();
        assertTrue(violations.isEmpty());
        tds.setFeld(ByteAdresse.of(256), "9");
        violations = tds.validate(Config.STRICT);
        assertFalse("unterschiedliche Satznummern", violations.isEmpty());
    }

    @Test
    public void testValidateWithNoSatznummer() {
        Satz hausrat = SATZ_REGISTRY.getSatz(SatzTyp.of("0221.130"));
        List<ConstraintViolation> violations = hausrat.getTeildatensatz(1).validate(Config.STRICT);
        assertEquals(0, violations.size());
    }

    @Test
    public void testGetSatznummerInvalid() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of(123), 1);
        Zeichen nr = new Zeichen(Bezeichner.SATZNUMMER, ByteAdresse.of(222), '0');
        new Satznummer(nr);
        tds.add(nr);
        assertEquals(1, tds.getSatznummer().toInt());
        assertEquals(222, tds.getSatznummer().getByteAdresse());
    }

    @Test
    public void testCopyCtor() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of(800), 1);
        Teildatensatz copy = new Teildatensatz(tds);
        assertEquals(tds, copy);
        checkSetSatzart(tds);
        LOG.info("Original wude geaendert: {}", tds);
        checkSetSatzart(copy);
        LOG.info("Kopie wude geaendert: {}", tds);
    }

    private static void checkSetSatzart(Teildatensatz tds) {
        Feld f1 = tds.getFeld(Bezeichner.SATZART);
        Feld f2 = tds.getFeld(ByteAdresse.of(1));
        assertEquals(f1, f2);
        f2.setInhalt("0888");
        assertEquals(f1, f2);
    }

    @Test
    public void testCtorSatzart() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of(800), 1);
        assertEquals(800, tds.getSatzart());
        assertEquals(SatzTyp.of(800), tds.getSatzTyp());
        assertEquals("0800", tds.getFeld(Bezeichner.SATZART).getInhalt());
    }

    @Test
    public void testGetBezeichnerClass() {
        Teildatensatz tds = SATZ_REGISTRY.getSatz(SatzTyp.of("0210.040")).getTeildatensatz(1);
        NumFeld summe = tds.getFeld(Bezeichner.DECKUNGSSUMME_4_IN_TAUSEND_WAEHRUNGSEINHEITEN, NumFeld.class);
        assertNotNull(summe);
    }

    @Test
    public void testEOD() throws IOException {
        String exported = exportSatz100(Config.DEFAULT.withProperty("gdv.eod", "\t==ende==\n\n"));
        MatcherAssert.assertThat(exported, containsString("==ende=="));
        exported = exportSatz100(Config.DEFAULT);
        MatcherAssert.assertThat(exported, not(containsString("==ende==")));
    }

    private static String exportSatz100(Config cfg) throws IOException {
        Teildatensatz tds = SatzRegistry.getInstance(cfg).getSatz(SatzTyp.of(100)).getTeildatensatz(1);
        try (StringWriter writer = new StringWriter()) {
            tds.export(writer);
            writer.flush();
            return writer.toString();
        }
    }

    @Test
    public void testGetNotUniqFeld() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of("0815"));
        Bezeichner b = Bezeichner.of("Testfeld");
        Zeichen z1 = new Zeichen(b, ByteAdresse.of(50));
        Zeichen z2 = new Zeichen(b, ByteAdresse.of(51));
        z1.setInhalt('x');
        z2.setInhalt('y');
        tds.add(z1);
        tds.add(z2);
        assertThrows(NotUniqueException.class, () -> tds.getFeld(b));
        tds.remove(z1);
        assertEquals(z2, tds.getFeld(b));
    }


    @Test
    public void testSetNotUniqFeld() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of("0815"));
        Bezeichner b = Bezeichner.of("Testfeld");
        Zeichen z1 = new Zeichen(b, ByteAdresse.of(50));
        Zeichen z2 = new Zeichen(b, ByteAdresse.of(51));
        tds.add(z1);
        tds.add(z2);
        assertThrows(NotUniqueException.class, () -> tds.setFeld(b, "z"));
    }

    @Test
    public void testGetUniqFeld() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of("0815"));
        Bezeichner b = Bezeichner.of("Testfeld");
        Zeichen z1 = new Zeichen(b, ByteAdresse.of(50));
        Zeichen z2 = new Zeichen(b, ByteAdresse.of(51));
        tds.add(z1);
        tds.add(z2);
        assertEquals(tds.getFeld(b).getInhalt(), z2.getInhalt());
    }

    /**
     * In Satzart 100 hat TDS 1 die Besonderheit, dass bis 2018 im PDF das
     * Satznummer-Feld die Nummer 27 statt 26 traegt. Daher kann man auf
     * dieses Feld sowohl mit Nr. 26 als auch 27 zugreifen.
     * <p>
     * Aehnliches gilt auch fuer Satzart 0210.050, 0220.010.13.1, 0600, 9950
     * und 9951.
     * </p>
     */
    @Test
    public void testGetFeldSatznummer() {
        checkGetSatznummerFeld(SatzTyp.of("100"), 1, 26);
        checkGetSatznummerFeld(SatzTyp.of("0210.050"), 1, 34);
        checkGetSatznummerFeld(SatzTyp.of("0220.010.13.1"), 1, 45);
        checkGetSatznummerFeld(SatzTyp.of("0600"), 2, 12);
        checkGetSatznummerFeld(SatzTyp.of("0600"), 3, 13);
        checkGetSatznummerFeld(SatzTyp.of("9950"), 1, 10);
        checkGetSatznummerFeld(SatzTyp.of("9951"), 1, 10);
    }

    private static void checkGetSatznummerFeld(SatzTyp satzTyp, int tdsNr, int feldNr) {
        Teildatensatz tds = XmlService.getInstance().getSatzart(satzTyp).getTeildatensatz(tdsNr);
        Zeichen satznummer = tds.getSatznummer();
        assertEquals(satznummer, tds.getFeld(feldNr));
        assertEquals(satznummer, tds.getFeld(feldNr+1));
    }

}
