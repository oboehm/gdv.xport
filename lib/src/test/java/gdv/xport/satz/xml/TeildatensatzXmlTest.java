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
 * (c)reated 24.09.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import static org.junit.Assert.assertEquals;

import javax.xml.stream.XMLStreamException;

import org.junit.BeforeClass;
import org.junit.Test;

import gdv.xport.feld.*;
import gdv.xport.satz.*;
import gdv.xport.satz.feld.Feld100;

/**
 * JUnit-Tests fuer die {@link TeildatensatzXml}-Klasse.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (24.09.2014)
 */
public class TeildatensatzXmlTest extends TeildatensatzTest {

    private static SatzXml satz100;
    private static TeildatensatzXml tds100;

    /**
     * Setzt einen Teildatensatz mit Hilfe von "Satz100.xml" auf.
     *
     * @throws XMLStreamException the XML stream exception
     */
    @BeforeClass
    public static void setUpTeildatensatz() throws XMLStreamException {
        satz100 = SatzXmlTest.getSatz("Satz100.xml");
        tds100 = (TeildatensatzXml) satz100.getTeildatensatz(1);
    }

    /**
     * Hier erzeugen wir einen Teildatensatz zum Testen.
     *
     * @return Teildatensatz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return tds100;
    }

    /**
     * Test-Methode fuer {@link Teildatensatz#getFeld(String)}. Bei dem
     * verwendeten Feld "Anredeschluessel" handelt es sich laut Handbuch um ein
     * alphanumerisches Feld von Byte 43 bis 43 (Laenge = 1).
     */
    @Override
    @Test
    public void testGetFeldString() {
        checkFeld(Bezeichner.ANREDESCHLUESSEL, 1, 43);
    }

    /**
     * Hier testen wir, ob wir das Feld "VU-Nummer" erwischen. Dieser Test
     * wurde ausgesucht, weil der Name ("VU-Nummer") einen Bindestrich ("-")
     * enthaelt.
     */
    @Test
    public void testGetFeldVuNr() {
        checkFeld(Bezeichner.VU_NR, 5, 5);
    }

    /**
     * Das Feld "Geschaeftsstelle / Vermittler" koennte Probleme bereiten, da
     * Leerzeichen enthaelt.
     */
    @Test
    public void testGetFeldVermittler() {
        checkFeld(Bezeichner.VERMITTLER, 10, 33);
    }

    /**
     * Wird Feld "Name 1" gefunden?
     */
    @Test
    public void testGetFeldName1() {
        checkFeld(Bezeichner.NAME1, 30, 44);
    }

    private static void checkFeld(final Bezeichner name, final int length, final int address) {
        Feld feld = tds100.getFeld(name);
        assertEquals("Anzahl Bytes", length, feld.getAnzahlBytes());
        assertEquals("Byte-Adresse", address, feld.getByteAdresse());
    }

    /**
     * Hier pruefen wir, ob die Satzart richtig gesetzt wird.
     */
    @Test
    public void testGetSatzart() {
        TeildatensatzXml tds = new TeildatensatzXml(200, 1);
        assertEquals(200, tds.getSatzart());
        tds.set(Bezeichner.SATZART.getName(), "0222");
        assertEquals(222, tds.getSatzart());
    }

    /**
     * Im ersten Teildatensatz von Satz 100 gibt es keine Leerstellen.
     * Deshalb sollte hier ein Null-Feld zurueckgegeben werden.
     */
    @Test
    public void testGetLeerstellen() {
        checkLeerstellen(1, Feld.NULL_FELD);
        checkLeerstellen(2, new AlphaNumFeld(Feld100.LEERSTELLEN));
    }

    private void checkLeerstellen(final int satznummer, final Feld expected) {
        Feld leerstellen = satz100.getTeildatensatz(satznummer).getFeld(new Bezeichner("Leerstellen"));
        assertEquals(expected.getAnzahlBytes(), leerstellen.getAnzahlBytes());
        assertEquals(expected.getByteAdresse(), leerstellen.getByteAdresse());
    }

    /**
     * Test-Methode fuer {@link TeildatensatzXml#getFeldRefenz(Bezeichner)}.
     */
    @Test
    public void testGetFeldReferenz() {
        FeldReferenz ref = tds100.getFeldRefenz((Bezeichner.TITEL));
        assertEquals("Titel", ref.getBemerkung());
    }

}
