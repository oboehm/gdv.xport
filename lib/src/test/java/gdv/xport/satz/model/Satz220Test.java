/*
 * Copyright (c) 2011, 2012 by Oli B.
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
 * (c)reated 08.04.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.AbstractDatensatzTest;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.feld.common.Feld1bis7;
import gdv.xport.satz.feld.common.Satz220Teil2;
import gdv.xport.util.SatzFactory;

/**
 * JUnit-Test fuer Satz220.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (08.04.2011)
 */
public class Satz220Test extends AbstractDatensatzTest {

    private static final Logger LOG = LogManager.getLogger(Satz220Test.class);

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Satz220();
    }

    /**
     * Der Lesbarkeit halber aktivieren wir das Zeilenende fuer jeden
     * exportierten Satz.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Config.setEOD("\n");
    }

    /**
     * Test method for {@link Satz220#Satz220(int)}.
     */
    @Override
    @Test
    public void testSparte() {
        Satz220 rechtsschutz = new Satz220(70);
        LOG.info(rechtsschutz + " created.");
        assertEquals(70, rechtsschutz.getSparte());
    }

    /**
     * Da inzwischen auch Sparte 30 (Unfall) unterstuetzt wird, sollte ein
     * Import dafuer kein Problem mehr sein. Der Test-Input dazu stammt von der
     * musterdatei_041222.txt von gdv-online.
     *
     * @throws IOException sollte eigentlich nicht vorkommen, da wir von einem
     *             String importieren
     */
    @Test
    public void testSparte30() throws IOException {
        String input = "02209999  030      599999999990199990099990000011Kitzelpfutze   "
                + "               000000Kitzelpfutze                  Martina      "
                + "                 111119791000Hausfrau                      A 1EU"
                + "R0000000000000000041141010520040000000001052004          1      " + "\n"
                + "02209999  030      599999999990199990099990000012000000000011305"
                + "0000000000000141950000000000000000000000000000000000000000000000"
                + "0000000000000000000000000010000000000000000 00000000000000000000"
                + "000000000000000000000000000000000000000000000000000  000000    X" + "\n";
        assertEquals(514, input.length());
        Datensatz wagnisdaten = SatzFactory.getDatensatz(220, 30);
        wagnisdaten.importFrom(input);
        Feld x = wagnisdaten.getFeld(Bezeichner.ZUSAETZLICHE_SATZKENNUNG, 2);
        assertEquals("X", x.getInhalt());
        checkDatensatz(wagnisdaten, input);
    }
    
    /**
     * Testet den Import der Teildatensaetze 1-4 und 9. Testet die Erweiterung vom {@link Satz220} um 0220.030.4. Zuvor nur Teildatensaetze 1 bis 3 und 9
     * unterstuetzt.
     * 
     * @since 19.04.2018
     * @throws IOException sollte eigentlich nicht vorkommen, da wir von einem String importieren
     */
    @Test
    public void testSparte30Teildatensatz1bis4und9() throws IOException    {
        String input = "02209999  030      5999999999901   90099991     1                                                                                                                                                                                                               \n" + 
                "02209999  030      5999999999901   90099991     2                                                                                                                                                                                                               \n" + 
                "02209999  030      5999999999901   90099993                                                                                                                                                                                                                     \n" + 
                "02209999  030      5999999999901   90099991     4                                                                                                                                                                                                               \n" + 
                "02209999  030      5999999999901   90099991                9                                                                                                                                                                                                    \n"; 
        
        assertEquals(1285, input.length());
        Datensatz satz220 = SatzFactory.getDatensatz(220, 30);
        satz220.importFrom(input);
        checkDatensatz(satz220, input);
    }
    
    /**
     * In der Sparte 30 haengt die Identifikation der entsprechenden Teildatensaetze von dem Feld "Zusaetzliche Satzkennung" ab. Dieser Test validiert die
     * korrekte Gruppierung der Datensaetze.
     *
     * @throws IOException sollte eigentlich nicht vorkommen, da wir von einem String importieren
     */
    @Test
    public void testSparte30Teildatensatz9() throws IOException {
        String input1 = "02209999  030      59999999997019999009999000000001        900 M"
                + "artina Kitzekpfutze          00000                              "
                + "                                                                "
                + "                                                         9000000" + "\n";
        String input2 =  "02209999  030      599999999970199990099990000021Kitzelpfutze   "
                + "               000000Kitzelpfutze                  Martina      "
                + "                 121219792000                              A 1EU"
                + "R0000000000000000012632010620040000000001062004          1      " + "\n";
        String input3 =  "02209999  030      599999999970199990099990000022000000000009310"
                + "0000000000000116900000000000000000000000000000000000000000000000"
                + "0000000000000000000000000010000000000000000 00000000000000000000"
                + "000000000000000000000000000000000000000000000000000  000000    X" + "\n";
        String input4 =  "02219999  030      599999999970199990099990000022               "
                + "                                                                "
                + "                                                                "
                + "                                                               X" + "\n";
        
        Datenpaket datenpaket = new Datenpaket();
        URL url = this.getClass().getResource("/satz220.030.txt");
        datenpaket.importFrom(url);

        List<Datensatz> datensaetze = datenpaket.getDatensaetze();
        assertThat("satz220.030.txt hat drei Datensaetze", datensaetze.size(), is(3));
        
        List<Teildatensatz> teildatensaetze1 = datensaetze.get(0).getTeildatensaetze();
        assertThat("satz220.030.txt erster Datensatz hat einen Teilsatz", teildatensaetze1.size(), is(1));
        checkDatensatz(teildatensaetze1.get(0), input1);
        assertThat("satz220.030.txt erster Datensatz, erster Teilsatz hat die Satznummer 9", teildatensaetze1.get(0).getNummer().toInt(), is(9));
        
        List<Teildatensatz> teildatensaetze2 = datensaetze.get(1).getTeildatensaetze();
        assertThat("satz220.030.txt zweiter Datensatz hat zwei Teilsaetze", teildatensaetze2.size(), is(2));
        checkDatensatz(teildatensaetze2.get(0), input2);
        assertThat("satz220.030.txt zweiter Datensatz, erster Teilsatz hat die Satznummer 1", teildatensaetze2.get(0).getNummer().toInt(), is(1));
        checkDatensatz(teildatensaetze2.get(1), input3);
        assertThat("satz220.030.txt zweiter Datensatz, zweiter Teilsatz hat die Satznummer 2", teildatensaetze2.get(1).getNummer().toInt(), is(2));

        List<Teildatensatz> teildatensaetze3 = datensaetze.get(2).getTeildatensaetze();
        assertThat("satz220.030.txt dritter Datensatz hat einen Teilsatz", teildatensaetze3.size(), is(1));
        checkDatensatz(teildatensaetze3.get(0), input4);
    }

    /**
     * Der Import von Sparte 51 scheint manchmal einfach uebergangen zu werden,
     * wenn er nur aus einem Teildatensatz besteht und danach gleich ein Satz
     * mit Sparte 53 kommt.
     *
     * @throws IOException falls der Test-Satz nicht gelesen werden kann
     */
    @Test
    public void testImportSparte51() throws IOException {
        Datenpaket datenpaket = importDatenpaket("/gdv/xport/satz/Satz0220051.txt");
        List<Datensatz> datensaetze = datenpaket.getDatensaetze();
        assertEquals(51, datensaetze.get(0).getSparte());
        assertEquals(53, datensaetze.get(1).getSparte());
    }

    /**
     * Hier testen wir hauptsaechlich Teil 2 des kreierten Datensatzes.
     */
    @Test
    public void testSparte51() {
        Satz220 sparte51 = new Satz220(51);
        assertEquals(2, sparte51.getTeildatensaetze().size());
        Teildatensatz teil2 = sparte51.getTeildatensatz(2);
        Feld satzart = teil2.getFeld(Feld1bis7.SATZART);
        assertEquals(1, satzart.getByteAdresse());
        Feld produktkennung = teil2.getFeld(Satz220Teil2.PRODUKTKENNUNG);
        assertEquals(43, produktkennung.getByteAdresse());
    }

    /**
     * Bei Tests in DatenpaketTest hat dieser Satz hier aus "igor_110120.txt"
     * Probleme bereitet, da er "invalid" war. Letzendlich war es aber nur ein
     * Copy&amp;Paste-Fehler des Datensatzes.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testIsValid() throws IOException {
        String input = "02201111  0531068000901829702001109-60013 0908200600000000000000"
                + "00x   7200000469000000000000000000000000001050000000000000150000"
                + "000469                                                          "
                + "                                                                ";
        Satz220 sparte53 = new Satz220(53);
        sparte53.importFrom(input);
        assertTrue(sparte53.isValid());
    }

    /**
     * Hier testen wir, ob der Umgang mit der Wagnisart einigermassen
     * funktioniert.
     */
    @Test
    public void testSparte40Wagnisart() {
        Satz220 sparte40 = new Satz220(40);
        sparte40.set(Bezeichner.WAGNISART, "1");
        assertEquals("1", sparte40.get(Bezeichner.WAGNISART).trim());
        assertThat(sparte40.hasWagnisart(), is(Boolean.TRUE));
        assertEquals("1", sparte40.getWagnisart().trim());
    }

}
