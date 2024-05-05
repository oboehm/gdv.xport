/*
 * Copyright (c) 2009 - 2023 by Oli B.
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
 * (c)reated 15.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import gdv.xport.Datenpaket;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.ByteAdresse;
import gdv.xport.io.Importer;
import gdv.xport.io.PushbackLineNumberReader;
import gdv.xport.satz.xml.XmlService;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzRegistry;
import gdv.xport.util.SatzTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Gemeinsame Oberklasse fuer SatzTest.
 *
 * @author oliver
 * @since 15.10.2009
 * @version $Revision$
 */
public class DatensatzTest extends AbstractDatensatzTest {

    private static final Logger LOG = LogManager.getLogger(DatensatzTest.class);

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Datensatz();
    }

    /**
     * Test method for {@link gdv.xport.satz.Datensatz#Datensatz(SatzTyp, int)}.
     * 
     * @throws IOException falls der Export schief gegangen ist
     */
    @Test
    public void testDatensatzSatzTypInt() throws IOException {
        Satz adressteil = new Datensatz(SatzTyp.of("0100"), 5);
        AlphaNumFeld schluessel = new AlphaNumFeld((Bezeichner.ANREDESCHLUESSEL), 1, ByteAdresse.of(43));
        schluessel.setInhalt('6');
        adressteil.add(schluessel);
        LOG.info("adressteil=" + adressteil.toShortString());
        checkExport(adressteil, 43, 43, "6", 1280);
    }

    /**
     * Test mit dem Datensatz "0200".
     */
    @Test
    public void testSetFeld() {
        Satz ds = new Datensatz(SatzTyp.of("0200"), 2);
        ds.add(new AlphaNumFeld((Bezeichner.INKASSOART), 1, ByteAdresse.of(43)));
        ds.setFeld(Bezeichner.INKASSOART, "2");
        assertEquals("2", ds.getFeld(Bezeichner.INKASSOART).getInhalt());
    }

    /**
     * Test-Methode fuer {@link Datensatz#setSparte(String)}.
     */
    @Test
    public void testSetSparteString() {
        Datensatz ds = new Datensatz(SatzTyp.of("0220"));
        ds.setSparte("580");
        assertEquals(580, ds.getSparte());
    }

    /**
     * Test-Methode fuer {@link Datensatz#setSparte(String)}.
     */
    @Test
    public void testSetSparteWithArt() {
        Datensatz ds = new Datensatz(SatzTyp.of("0220"));
        ds.setSparte("580.2");
        assertEquals(580, ds.getSparte());
        assertEquals(2, ds.getArt());
        String s = ds.toString();
        LOG.info("s = \"{}\"", s);
        assertTrue(s.contains("220.580.2"), s);
    }

    /**
     * Test-Methude fuer {@link Datensatz#Datensatz(Datensatz)}.
     */
    @Test
    public void testCopyConstructor() {
        Datensatz orig = XmlService.getInstance().getSatzart(SatzTyp.of(100));
        orig.setFeld(Bezeichner.NAME1, "Asterix");
        Datensatz copy = new Datensatz(orig);
        assertEquals(orig.getFeldInhalt(Bezeichner.NAME1), copy.getFeldInhalt(Bezeichner.NAME1));
        assertEquals(orig.toLongString(), copy.toLongString());
        copy.setFeld(Bezeichner.NAME1, "Obelix");
        assertEquals("Obelix", copy.getFeld(Bezeichner.NAME1).getInhalt().trim());
        assertEquals("Asterix", orig.getFeld(Bezeichner.NAME1).getInhalt().trim());
    }

    /**
     * Testfall fuer Issue <a href=
     * "https://github.com/oboehm/gdv.xport/issues/13">#13</a>).
     *
     * @throws IOException the io exception
     */
    @Test
    public void testIssue13() throws IOException {
        Datenpaket datenpaket = new Datenpaket();
        URL url = this.getClass().getResource("/test_issue13.txt");
        datenpaket.importFrom(url);
        
        // Erwartet 3 Datensaetze 100 mit je zwei Teildatensaetzen 1 und 2
        assertEquals(3, datenpaket.getDatensaetze().size());
        for (Datensatz datensatz : datenpaket.getDatensaetze()) {
            assertEquals(100, datensatz.getSatzart());
            assertEquals(2, datensatz.getNumberOfTeildatensaetze());
            assertEquals(1, datensatz.getTeildatensatz(1).getSatznummer().toInt());
            assertEquals(2, datensatz.getTeildatensatz(2).getSatznummer().toInt());
        }
    }

    @Test
    public void testReadBauspartArt() throws IOException {
        int art = 2;
        SatzTyp bausparen = SatzTyp.of("0220.580.2");
        Satz datensatz = SatzFactory.getSatz(bausparen);
        datensatz.getFeld(Bezeichner.of("Produkt")).setInhalt("580");
        datensatz.getFeld(Bezeichner.of("Art")).setInhalt(art);
        try (PushbackLineNumberReader reader = new PushbackLineNumberReader(
                new StringReader(datensatz.toLongString()), 50)) {
            int bausparArt = Importer.of(reader).readBausparenArt();
            assertEquals(art, bausparArt);
        }
    }

    @Test
    public void testInit() {
        SatzTyp wagnis13 = SatzTyp.of("0220.010.13.1");
        Datensatz datensatz = new Datensatz(wagnis13);
        assertEquals(220, datensatz.getSatzart());
        assertEquals(10, datensatz.getSparte());
        assertEquals(wagnis13, datensatz.getSatzTyp());
    }

    @Test
    public void testSetVuNummerWagnisart() {
        checkVuNummer(SatzTyp.of("0221.010.5.1"));
    }

    @Test
    @Disabled("VU-Nummer wurde in VUVM2023 durch BausparkassenNr ersetzt")
    public void testSetVuNummerBausparen() {
        Datensatz satz = checkVuNummer(SatzTyp.of("0220.580.2"));
        assertTrue(satz.hasVuNummer());
    }

    private Datensatz checkVuNummer(SatzTyp satzTyp) {
        String vunr = "12345";
        Datensatz datensatz = (Datensatz) SatzRegistry.getInstance().getSatz(satzTyp);
        datensatz.setVuNummer(vunr);
        assertEquals(vunr, datensatz.getVuNummer());
        for (Teildatensatz tds : datensatz.getTeildatensaetze()) {
            if (tds.hasFeld(Bezeichner.VU_NUMMER)) {
                assertEquals(vunr, tds.getFeld(ByteAdresse.VU_NUMMER).getInhalt());
            }
        }
        return datensatz;
    }

    @Test
    public void testHasVuNummer() {
        Datensatz datensatz = (Datensatz) SatzRegistry.getInstance().getSatz(SatzTyp.of("0220.580.01"));
        assertFalse(datensatz.hasVuNummer());
    }

    @Test
    public void testInitBausparenArt() {
        checkInitArt(SatzTyp.of("0220.580.01"), Bezeichner.ART_580);
    }

    @Test
    public void testInitBausparenArt2() {
        Datensatz satz = checkInitArt(SatzTyp.of("0220.580.2"), Bezeichner.ART_580);
        assertEquals("2", satz.getFeldInhalt(Bezeichner.ART_580));
    }

    @Test
    public void testInitWagnisArt() {
        checkInitArt(SatzTyp.of("0220.010.13"), Bezeichner.WAGNISART);
    }

    @Test
    public void testInitWagnisArt2() {
        Datensatz satz = checkInitArt(SatzTyp.of("0220.010.2"), Bezeichner.WAGNISART);
        assertEquals("2", satz.getFeldInhalt(Bezeichner.WAGNISART));
    }

    private Datensatz checkInitArt(SatzTyp satzTyp, Bezeichner art) {
        Datensatz satz = new Datensatz(satzTyp, 1);
        assertEquals(1, satz.getNumberOfTeildatensaetze());
        assertTrue(satz.getTeildatensatz(1).hasFeld(art));
        return satz;
    }

    @Test
    public void testSetFeldSatzart() {
        Satz satz0200 = SatzRegistry.getInstance().getSatz(SatzTyp.of("0200"));
        Teildatensatz tds1 = satz0200.getTeildatensatzBySatzNr(1);
        tds1.setFeld(Bezeichner.SATZART, "030");
        assertEquals("0030", tds1.getFeld(ByteAdresse.of(1)).getInhalt());
        assertEquals(30, tds1.getSatzart());
    }

    @Test
    public void testSetSparteNichtExistent()
    {
    	Datensatz vor= (Datensatz) SatzRegistry.getInstance().getSatz(SatzTyp.of(1));
    	assertThrows(IllegalArgumentException.class, () -> vor.setSparte(20));
    }

    @Test
    public void testSparte() {
        SatzTyp glas = SatzTyp.of("0210.110");
        Datensatz ds = new Datensatz(glas, 1);
        assertEquals(110, ds.getSparte());
        assertEquals(110, ds.getTeildatensatz(1).getSparte());
    }

}
