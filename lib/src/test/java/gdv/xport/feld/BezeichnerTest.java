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
 * (c)reated 25.09.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import gdv.xport.satz.Satz;
import gdv.xport.satz.xml.SatzXml;
import gdv.xport.satz.xml.XmlService;
import gdv.xport.util.SatzRegistry;
import gdv.xport.util.SatzTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import patterntesting.runtime.junit.ObjectTester;
import patterntesting.runtime.junit.SerializableTester;

import java.io.NotSerializableException;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

/**
 * JUnit-Tests fuer die {@link Bezeichner}-Klasse.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (25.09.2014)
 */
public class BezeichnerTest {

    private static final Logger LOG = LogManager.getLogger();

    /**
     * Test-Methode fuer {@link Bezeichner#toString()}.
     */
    @Test
    public void testToString() {
        String name = "Anrede";
        String s = Bezeichner.of(name).toString();
        assertTrue(s, s.contains(name));
    }

    /**
     * Die Konstante NAME1 sollte als toSring() "Name1" zuruckliefern, sonst
     * klappt das Mapping der Bezeichner aus den annotierten Feld-Enums nicht.
     */
    @Test
    public void testNAME1toString() {
        Bezeichner createdName1 = Bezeichner.of(Bezeichner.NAME1.toString());
        assertEquals(Bezeichner.NAME1, createdName1);
    }

    /**
     * Test-Methode fuer {@link Bezeichner#getTechnischerName()}. Die Daten
     * dazu stammen aus der XML-Datei ("VUVM2013").
     */
    @Test
    public void testGetTechnischerName() {
        Bezeichner vermittler = Bezeichner.of("Gesch\u00e4ftsstelle / Vermittler");
        assertEquals("GeschaeftsstelleVermittler", vermittler.getTechnischerName());
    }

    /**
     * Test-Methode fuer {@link Bezeichner#getTechnischerName()}. Namen, die auf
     * "Waehrungseinheit" aufhoeren, haben meist "WE" als Endung fuer den
     * technischen Namen.
     */
    @Test
    public void testGetTechnischerNameForWaehrungseinheit() {
        Bezeichner zuzahlungsdatum = Bezeichner.of("Zuzahlungsbetrag in Waehrungseinheiten");
        assertEquals("ZUZAHLUNGSBETRAGINWE", zuzahlungsdatum.getTechnischerName().toUpperCase());
    }

    /**
     * Test-Methode fuer {@link Bezeichner#getTechnischerName()}. Aus "%-Satz"
     * wird "...ProzSatz" als technischer Name.
     */
    @Test
    public void testGetTechnischerNameForProzSatz() {
        Bezeichner prozSatz = Bezeichner.EINSCHLUSS_PROZENT_SATZ;
        assertEquals("EinschlussProzSatz", prozSatz.getTechnischerName());
    }

    /**
     * Artikel wie "der" sind nicht Bestandteil eines technischen Namens.
     */
    @Test
    public void testGetTechnischerNameWithArtikel() {
        Bezeichner alex = new Bezeichner("Alexander der Grosse");
        assertEquals("AlexanderGrosse", alex.getTechnischerName());
    }

    /**
     * Artikel wie "den" sind dagegen Bestandteil des technischen Namens.
     */
    @Test
    public void testGetTechnischerNameWithDen() {
        assertEquals("AbstandJahresrentenaenderungstermine", Bezeichner.ABSTAND_DER_JAHRESRENTENAENDERUNGSTERMINE.getTechnischerName());
    }

    /**
     * "Versicherung" wird als "Vers" abgekuerzt.
     */
    @Test
    public void testGetTechnischerNameWithVersicherung() {
        assertEquals("ErweiterteNeuwertVers", Bezeichner.ERWEITERTE_NEUWERTVERSICHERUNG.getTechnischerName());
    }

    /**
     * "eVB" wird als "eVB" abgekuerzt.
     */
    @Test
    public void testGetTechnischerNameEVB() {
        Bezeichner evbnr = new Bezeichner("eVB-Nr");
        assertEquals("eVBNr", evbnr.getTechnischerName());
    }

    /**
     * Zwei Bezeichner mit dem identischen Namen sollten natuerlich gleich
     * sein.
     */
    @Test
    public void testEqualsExact() {
        String name = "Hello";
        ObjectTester.assertEquals(new Bezeichner(name), new Bezeichner(name));
    }

    /**
     * Gross-/Kleinschreibung sollte egal fuer den Vergleich sein.
     */
    @Test
    public void testEqualsUpperCase() {
        ObjectTester.assertEquals(new Bezeichner("Gross"), new Bezeichner("GROSS"));
    }

    /**
     * Mit {@link Bezeichner#VERMITTLER} gab es Probleme, da er sowohl
     * Leerzeichen, Sonderzeichen ("/") und einen Umlaut enthielt, die eine
     * besondere Herausforderung fuer den Vergleich darstellten.
     */
    @Test
    public void testEqualsVermittler() {
        ObjectTester.assertEquals((Bezeichner.VERMITTLER),
                new Bezeichner("Gesch\u00e4ftsstelle / Vermittler"));
    }

    /**
     * Auch die VU-Nummer hat so ihre Besonderheiten. So ist der technische
     * Name dafuer "VuNr".
     */
    @Test
    public void testEqualsVuNummer() {
        ObjectTester.assertEquals(Bezeichner.VU_NR, new Bezeichner("VuNr"));
    }

    /**
     * Das gleiche wie fuer den vorigen Test gilt auch fuer die
     * "Versicherungsschein-Nummer": diese wird als "VsNr" abgekuerzt.
     */
    @Test
    public void testEqualsVsNr() {
        ObjectTester.assertEquals(Bezeichner.VS_NR, new Bezeichner("VsNr"));
    }

    /**
     * Test-Methode fuer {@link Bezeichner#of(String)}.
     */
    @Test
    public void testOfString() {
        String bezeichnung = "Abgangsdatum";
        Bezeichner abgangsdat = Bezeichner.of(bezeichnung);
        assertEquals(bezeichnung, abgangsdat.getName());
        assertEquals(Bezeichner.ABGANGSDAT, abgangsdat);
    }

    @Test
    public void testOfVersionSatzart() {
        assertEquals(Bezeichner.VERSION_SATZART_0100, Bezeichner.of("VersionSatzart0100"));
        assertEquals(Bezeichner.VERSION_SATZART_0100, Bezeichner.of("VersionSatzart0100000"));
    }

    @Test
    public void testOfVersionSatzartSparte() {
        Bezeichner version = Bezeichner.of("VersionSatzart0210050");
        assertEquals(Bezeichner.VERSION_SATZART_0210_050, version);
    }

    @Test
    public void testKagNrAbsendendenGesellschaft() {
        Bezeichner b = new Bezeichner("KAG-Nummer der absendenden Gesellschaft");
        assertEquals("KagNrAbsendendenGesellschaft", b.getTechnischerName());
    }

    /**
     * Es gibt mehere Bezeichner mit "Satznummer" als Namen. Hier gilt es, den
     * richtigen Bezeichner zu finden ;-)
     */
    @Test
    public void testOfSatznummer() {
        Bezeichner satznummer = Bezeichner.of("Satznummer");
        assertEquals(Bezeichner.SATZNUMMER, satznummer);
    }

    @Test
    public void testIsVariantOf() {
        Bezeichner nr2 = new Bezeichner("Nr2");
        Bezeichner nr = new Bezeichner("Nr", "Nr", "Nr1", "Nr2");
        assertTrue(nr2.isVariantOf(nr));
        assertFalse(nr.isVariantOf(nr2));
    }

    @Test
    public void testIsNotVariantOf() {
        assertFalse(Bezeichner.ABSENDER.isVariantOf(Bezeichner.ADRESSAT));
    }

    @Test
    public void testGetVariants() {
        Bezeichner b = new Bezeichner("v", "v", "v1");
        Set<Bezeichner> variants = b.getVariants();
        assertThat(variants.size(), greaterThan(1));
    }

    @Test
    public void testgetVersionVariants() {
        assertThat(Bezeichner.SATZART_0100.getVariants(), hasItem(Bezeichner.VERSION_SATZART_0100));
        assertThat(Bezeichner.VERSION_SATZART_0100.getVariants(), hasItem(Bezeichner.SATZART_0100));
    }

    @Test
    public void testVariantsWEs() {
        Satz satz220 = SatzRegistry.getInstance("VUVM2018.xml").getSatz(SatzTyp.of("0220.010.13.9"));
        Feld we1 = satz220.getFeld(Bezeichner.of("HaftungswertungssummeInWE"));
        Feld we2 = satz220.getFeld(Bezeichner.of("HaftungswertungssummeInWE2"));
        assertNotEquals(we1, we2);
        LOG.info("{} und {} sind unterschiedlich.", we1, we2);
        Feld we = satz220.getFeld(Bezeichner.of("HaftungswertungssummeInWE"));
        assertEquals(we, we1);
    }

    @Test
    public void testVersionSatzart9999() {
        Bezeichner satzart9999 = new Bezeichner("Satzart9999");
        assertThat(Bezeichner.VERSION_SATZART_9999.getVariants(), hasItem(satzart9999));
    }

    @Test
    public void testSerializable() throws NotSerializableException {
        SerializableTester.assertSerialization(Bezeichner.ABLAUF);
    }

    @Test
    public void testOf() {
        XmlService xmlService = XmlService.getInstance();
        SatzXml testsatz022101051 = xmlService.getSatzart(SatzTyp.of("0221.010.5.1"));
        // Feld 8 "Lfd. Nummer der versicherten Person (VP)"
        Bezeichner bezeichnerAusSatz = testsatz022101051.getTeildatensatzBySatzNr(1).getFeld(ByteAdresse.of(43))
                .getBezeichner();
        Bezeichner bezeichnerAusBezeichnerOf = Bezeichner.of(bezeichnerAusSatz.getName());
        assertEquals(bezeichnerAusBezeichnerOf, bezeichnerAusSatz);
    }

}
