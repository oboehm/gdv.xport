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

import gdv.xport.satz.feld.Feld0001;
import org.junit.Test;
import org.junit.runner.RunWith;
import patterntesting.runtime.junit.ObjectTester;
import patterntesting.runtime.junit.SmokeRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * JUnit-Tests fuer die {@link Bezeichner}-Klasse.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (25.09.2014)
 */
@RunWith(SmokeRunner.class)
public class BezeichnerTest {

    /**
     * Test-Methode fuer {@link Bezeichner#toString()}.
     */
    @Test
    public void testToString() {
        String name = "Anrede";
        String s = new Bezeichner(name).toString();
        assertTrue(s, s.contains(name));
    }

    /**
     * Die Konstante NAME1 sollte als toSring() "Name1" zuruckliefern, sonst
     * klappt das Mapping der Bezeichner aus den annotierten Feld-Enums nicht.
     */
    @Test
    public void testNAME1toString() {
        Bezeichner createdName1 = new Bezeichner(Bezeichner.NAME1.toString());
        assertEquals(Bezeichner.NAME1, createdName1);
    }

    /**
     * Test-Methode fuer {@link Bezeichner#getTechnischerName()}. Die Daten
     * dazu stammen aus der XML-Datei ("VUVM2013").
     */
    @Test
    public void testGetTechnischerName() {
        Bezeichner vermittler = new Bezeichner("Gesch\u00e4ftsstelle / Vermittler");
        assertEquals("GeschaeftsstelleVermittler", vermittler.getTechnischerName());
    }

    /**
     * Test-Methode fuer {@link Bezeichner#getTechnischerName()}. Namen, die
     * auf "Datum" aufhoeren, haben meist "Dat" als Endung fuer den technischen
     * Namen.
     */
    @Test
    public void testGetTechnischerNameForDatum() {
        Bezeichner zuzahlungsdatum = new Bezeichner("Zuzahlungsdatum");
        assertEquals("Zuzahlungsdat", zuzahlungsdatum.getTechnischerName());
    }

    /**
     * Test-Methode fuer {@link Bezeichner#getTechnischerName()}. Namen, die
     * "Datum" als Bestandteil haben, haben "Dat" als Teil des technischen
     * Namens.
     */
    @Test
    public void testGetTechnischerNameForDatumInside() {
        Bezeichner dat = new Bezeichner("Aufgabedatum dieses Geschaeftsvorfalls");
        assertEquals("AufgabedatDiesesGeschaeftsvorfalls", dat.getTechnischerName());
    }

    /**
     * Test-Methode fuer {@link Bezeichner#getTechnischerName()}. Namen, die auf
     * "Waehrungseinheit" aufhoeren, haben meist "WE" als Endung fuer den
     * technischen Namen.
     */
    @Test
    public void testGetTechnischerNameForWaehrungseinheit() {
        Bezeichner zuzahlungsdatum = new Bezeichner("Zuzahlungsbetrag in Waehrungseinheiten");
        assertEquals("ZuzahlungsbetragInWE", zuzahlungsdatum.getTechnischerName());
    }

    /**
     * Test-Methode fuer {@link Bezeichner#getTechnischerName()}. Namen, die auf
     * "VS" aufhoeren, haben meist "Vs" (mit kleinem 's') als Endung fuer den
     * technischen Namen.
     */
    @Test
    public void testGetTechnischerNameForVS() {
        Bezeichner zuzahlungsdatum = new Bezeichner("Erlebensfall VS");
        assertEquals("ErlebensfallVs", zuzahlungsdatum.getTechnischerName());
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
     * Test-Methode fuer {@link Bezeichner#getTechnischerName()}. Aus
     * "...VP..." wird "...Vp..." als technischer Name.
     */
    @Test
    public void testGetTechnischerNameForVp() {
        Bezeichner vp = Bezeichner.EINSCHLUSSDAT_VP_PERSONENGRUPPE;
        assertEquals("EinschlussdatVpPersonengruppe", vp.getTechnischerName());
    }

    /**
     * Artikel wie "der" sind nicht Bestandteil eines technischen Namens.
     */
    @Test
    public void testGetTechnischerNameWithArtikel() {
        assertEquals("ErsteZulassungAufDenVn", Bezeichner.ERSTE_ZULASSUNG_AUF_DEN_VN.getTechnischerName());
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
        assertEquals("eVBNummer", Bezeichner.EVB_NUMMER.getTechnischerName());
    }

    /**
     * "...nummer" wird nur manchmal als "...Nr" abgekuerzt.
     */
    @Test
    public void testGetTechnischerNameWithNummer() {
        assertEquals("Referenznummer", Bezeichner.REFERENZNUMMER.getTechnischerName());
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
     * Name dafuer "VuNr", was sich nicht direkt aus dem Name ("VU-Nummer")
     * ableiten laesst.
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
     * Test-Methode fuer {@link Bezeichner#of(Enum)}, die als Ergebnis aus
     * Issue 10 entstanden ist.
     */
    @Test
    public void testOfEnum() {
        Bezeichner version = Bezeichner.of(Feld0001.VERSION_SATZART_0210_FEUER);
        assertEquals(Bezeichner.VERSION_SATZART_0210_FEUER, version);
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

    /**
     * Test-Method fuer {@link Bezeichner#mergeWith(Bezeichner)}.
     */
    @Test
    public void testMergeWith() {
        Bezeichner nrImGevo = new Bezeichner("Lfd. Personennummer im GeVo");
        Bezeichner nr = new Bezeichner("Lfd. Personennummer", "LfdPersonenNrImGevo");
        Bezeichner merged = nrImGevo.mergeWith(nr);
        assertEquals("LfdPersonenNrImGevo", merged.getTechnischerName());
    }

    /**
     * Da doppelte Eintraege in Enums nicht erlaubt sind, sind manche
     * Enum-Felder durch eine laufende Nummer gekennzeichnet. Z.B. gibt
     * es in vielen enums ein INTRO1 und INTRO2, obwohl der Bezeichner
     * dazu eigentlich "INTRO" ist.
     */
    @Test
    public void testOfIntro() {
        Bezeichner intro = Bezeichner.of(Feld0001.INTRO2);
        assertEquals(Bezeichner.INTRO, intro);
    }

}
