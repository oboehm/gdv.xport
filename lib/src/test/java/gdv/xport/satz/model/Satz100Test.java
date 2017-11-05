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
 * (c)reated 09.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import gdv.xport.Datenpaket;
import gdv.xport.feld.Bezeichner;
import gdv.xport.satz.AbstractDatensatzTest;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.feld.Feld100;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * JUnit-Test fuer Satz100.
 *
 * @author oliver (ob@aosd.de)
 * @since 09.03.2011
 */
public class Satz100Test extends AbstractDatensatzTest {

    private static final String INPUT =
        "01009999  030      599999999980199990099991Pollsmann            " +
        "                                       Rudolf                   " +
        "                         D  50825 Cologne                  Elsho" +
        "lunderstr. 10                    11111979D  01            99  11" +
        "01009999  030      59999999998019999009999           W45WWW     " +
        "                                          0123456789  10000000  " +
        "                            100221 12345899       200211 123456 " +
        "        300211 345678         410221 445566         1          2" +
        "01009999  030      59999999998019999009999420221 777772         " +
        "                                        50polly(a)example.com   " +
        "                                                                " +
        "                                                               3";
    private static final String INPUT_050 =
        "01001111  0501068000980357452001109-60013 1xxxxx                " +
        "                                       xxxx                     " +
        "                         x  21465 xxxxxxx                  xxxxx" +
        "xxx 16 x                         29071966x  01                  ";

    private final Satz100 satz = new Satz100();

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Satz100();
    }

    /**
     * Test method for {@link gdv.xport.satz.model.Satz100#Satz100()}.
     */
    @Test
    public void testSatz0100() {
        assertEquals(100, satz.getSatzart());
    }

    /**
     * Hier testen wir den Import und Export.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportExport() throws IOException {
        checkImportExport(satz, INPUT);
    }

    /**
     * Hier testen wir, ob die Import-Daten richtig interpretiert werden.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImport() throws IOException {
        satz.importFrom(INPUT);
        assertEquals(100, satz.getSatzart());
        assertEquals("9999", satz.getVuNummer());
        assertEquals(30, satz.getSparte());
        assertEquals("59999999998", satz.getVersicherungsscheinNummer());
        assertEquals(1, satz.getFolgenummer());
        assertEquals("9999009999", satz.getVermittler());
        assertEquals("1", satz.get(Feld100.ANREDESCHLUESSEL));
        assertEquals("Pollsmann", satz.get(Feld100.NAME1).trim());
        assertEquals("           W45WWW", satz.get(Feld100.KUNDENNR_VERSICHERER));
    }

    /**
     * Hier nehmen wir zum Testen einen 100er-Satz aus "igor_110120.txt", der
     * beim Testen mehrerer Datenpakete Probleme bereitet hat.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportReader() throws IOException {
        Reader reader = new StringReader(INPUT_050);
        try {
            satz.importFrom(reader);
            assertEquals(50, satz.getSparte());
            int ch = reader.read();
            assertEquals(-1, ch);
        } finally {
            reader.close();
        }
    }

    /**
     * Hier nehmen wir zum Testen einen 100er-Satz aus "igor_110120.txt", der
     * beim Testen mehrerer Datenpakete Probleme bereitet hat.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportStream() throws IOException {
        Reader reader = new StringReader(INPUT_050);
        try {
            satz.importFrom(reader);
            assertEquals(50, satz.getSparte());
            int ch = reader.read();
            assertEquals(-1, ch);
        } finally {
            reader.close();
        }
    }

    /**
     * Hier testen wir, ob das Alignment in den FeldInfos tatsaechlich
     * ausgewertet wird.
     */
    @Test
    public void testAlignment() {
        satz.set(Feld100.KUNDENNR_VERSICHERER, "1234567890abcdef");
        assertEquals(" 1234567890abcdef", satz.get(Feld100.KUNDENNR_VERSICHERER));
    }

    /**
     * Test-Methode fuer {@link Satz100#getName(int)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testGetName() throws IOException {
        satz.importFrom(INPUT);
        assertEquals("Pollsmann", satz.getName(1));
        assertEquals("Rudolf", satz.getName(3));
    }

    /**
     * Test-Methode fuer {@link Satz100#getName(int)} mit einem illegalen
     * Argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetNameIllegalArgument() {
        satz.getName(4);
    }

    /**
     * Dies ist der Unit-Test fuer Issue #8. Der Datensatz enthaelt dabei
     * Teildatensatz 1, 2 und 4, nicht aber Teildatensatz 3.
     *
     * @throws IOException falls der Test-Satz nicht gelesen werden kann
     */
    @Test
    public void testImportSatz100MitLuecke() throws IOException {
        Datenpaket datenpaket = importDatenpaket("/gdv/xport/satz/Satz0100030.txt");
        Datensatz satz100 = datenpaket.getDatensaetze().get(0);
        List<Teildatensatz> teildatensaetze = satz100.getTeildatensaetze();
        assertEquals(1, teildatensaetze.get(0).getNummer().toInt());
        assertEquals(2, teildatensaetze.get(1).getNummer().toInt());
        assertEquals(4, teildatensaetze.get(2).getNummer().toInt());
        Teildatensatz tds4 = teildatensaetze.get(2);
        assertEquals("GENODEF1JEV", tds4.getFeld(Bezeichner.BIC1).getInhalt().trim());
        assertEquals("DE41300606010006605605", tds4.getFeld(Bezeichner.IBAN1).getInhalt().trim());
    }

}

