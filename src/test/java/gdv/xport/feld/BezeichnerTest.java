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

import patterntesting.runtime.junit.ObjectTester;

/**
 * JUnit-Tests fuer die {@link Bezeichner}-Klasse.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (25.09.2014)
 */
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
     * Test-Methode fuer {@link Bezeichner#getTechnischerName()}. Die Daten
     * dazu stammen aus der XML-Datei ("VUVM2013").
     */
    @Test
    public void testGetTechnischerName() {
        Bezeichner vermittler = new Bezeichner("Gesch\u00e4ftsstelle / Vermittler");
        assertEquals("GeschaeftsstelleVermittler", vermittler.getTechnischerName());
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
        ObjectTester.assertEquals(new Bezeichner(Bezeichner.VERMITTLER),
                new Bezeichner("Gesch\u00e4ftsstelle / Vermittler"));
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
        assertEquals(bezeichnung, field.get(null).toString());
    }

}
