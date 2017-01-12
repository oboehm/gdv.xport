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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;
import org.junit.runner.RunWith;

import patterntesting.runtime.junit.ObjectTester;
import patterntesting.runtime.junit.SmokeRunner;

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
        ObjectTester.assertEquals(new Bezeichner(Bezeichner.NAME_VERMITTLER),
                new Bezeichner("Gesch\u00e4ftsstelle / Vermittler"));
    }

    /**
     * Auch die VU-Nummer hat so ihre Besonderheiten. So ist der technische
     * Name dafuer "VuNr", was sich nicht direkt aus dem Name ("VU-Nummer")
     * ableiten laesst.
     */
    @Test
    public void testEqualsVuNummer() {
        ObjectTester.assertEquals(Bezeichner.VU_NR, new Bezeichner(Bezeichner.VU_NR.getName()));
    }

    /**
     * Und auch mit {@link Bezeichner#LFD_PERSONENNR_GEVO} gab es Probleme...
     */
    @Test
    public void testEqualsPersonenNr() {
        ObjectTester.assertEquals(Bezeichner.LFD_PERSONEN_NR_IM_GEVO,
                new Bezeichner(Bezeichner.LFD_PERSONEN_NR_IM_GEVO.getName()));
    }

    /**
     * Das gleiche wie fuer den vorigen Test gilt auch fuer die
     * "Versicherungsschein-Nummer": diese wird als "VsNr" abgekuerzt.
     */
    @Test
    public void testEqualsVsNr() {
        ObjectTester.assertEquals(Bezeichner.VS_NR, new Bezeichner(Bezeichner.VS_NR.getName()));
    }

    /**
     * Test-Methode fuer {@link Bezeichner#getField(String)}.
     *
     * @throws IllegalAccessException the illegal access exception
     */
    @Test
    public void testGetField() throws IllegalAccessException {
        String bezeichnung = "Abgangsdatum";
        Field field = Bezeichner.getField(bezeichnung);
        Bezeichner abgangsdat = (Bezeichner) field.get(null);
        assertEquals(bezeichnung, abgangsdat.getName());
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

}
