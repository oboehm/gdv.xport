/*
 * Copyright (c) 2009 - 2012 by Oli B.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gdv.xport.Datenpaket;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.feld.Feld100;
import gdv.xport.util.SatzTyp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.apache.logging.log4j.*;

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
     * Test method for {@link gdv.xport.satz.Datensatz#Datensatz(java.lang.String, int)}.
     * 
     * @throws IOException falls der Export schief gegangen ist
     */
    @Test
    public void testDatensatzStringInt() throws IOException {
        Satz adressteil = new Datensatz("0100", 5);
        AlphaNumFeld schluessel = new AlphaNumFeld((Bezeichner.ANREDESCHLUESSEL), 1, 43);
        schluessel.setInhalt('6');
        adressteil.add(schluessel);
        LOG.info("adressteil=" + adressteil.toShortString());
        checkExport(adressteil, 43, 43, "6", 1280);
    }

    /**
     * Test mit dem Datensatz "0200".
     */
    @Test
    public void testSet() {
        Satz ds = new Datensatz("0200", 2);
        ds.add(new AlphaNumFeld((Bezeichner.INKASSOART), 1, 43));
        ds.set(Bezeichner.INKASSOART, "2");
        assertEquals(ds.get(Bezeichner.INKASSOART), "2");
    }

    /**
     * Test-Methode fuer {@link Datensatz#setSparte(String)}.
     */
    @Test
    public void testSetSparteString() {
        Datensatz ds = new Datensatz("0220");
        ds.setSparte("580");
        assertEquals(580, ds.getSparte());
    }

    /**
     * Test-Methode fuer {@link Datensatz#setSparte(String)}.
     */
    @Test
    public void testSetSparteWithArt() {
        Datensatz ds = new Datensatz("0220");
        ds.setSparte("580.2");
        assertEquals(580, ds.getSparte());
        assertEquals(2, ds.getArt());
        String s = ds.toString();
        LOG.info("s = \"{}\"", s);
        assertTrue(s, s.contains("220.580.2"));
    }

    /**
     * Test-Methude fuer {@link Datensatz#Datensatz(Datensatz)}.
     */
    @Test
    public void testCopyConstructor() {
        List<Teildatensatz> teildatensaetze = new ArrayList<Teildatensatz>();
        Teildatensatz tds = new Teildatensatz(100, 1);
        tds.add(new Feld(Feld100.NAME1));
        tds.set(Feld100.NAME1, "Asterix");
        teildatensaetze.add(tds);
        teildatensaetze.add(new Teildatensatz(100, 2));
        Datensatz orig = new Datensatz(new SatzTyp(100), teildatensaetze);
        Datensatz copy = new Datensatz(orig);
        assertEquals(orig.get(Feld100.NAME1), copy.get(Feld100.NAME1));
        assertEquals(orig.toLongString(), copy.toLongString());
        copy.set(Feld100.NAME1, "Obelix");
        assertEquals("Obelix", copy.getFeld(Feld100.NAME1).getInhalt().trim());
        assertEquals("Asterix", orig.getFeld(Feld100.NAME1).getInhalt().trim());
    }

    /**
     * Testfall fuer Issue 13 (https://github.com/oboehm/gdv.xport/issues/13).
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
            assertEquals(1, datensatz.getTeildatensatz(1).getNummer().toInt());
            assertEquals(2, datensatz.getTeildatensatz(2).getNummer().toInt());
        }
    }

}
