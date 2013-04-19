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

import static org.junit.Assert.assertEquals;
import gdv.xport.satz.AbstractDatensatzTest;
import gdv.xport.satz.Satz;
import gdv.xport.satz.feld.Feld100;

import java.io.IOException;

import org.junit.Test;

/**
 * JUnit-Test fuer Satz100.
 *
 * @author oliver (ob@aosd.de)
 * @since 09.03.2011
 */
public class Satz100Test extends AbstractDatensatzTest {

    private static final String input =
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
        checkImportExport(satz, input);
    }

    /**
     * Hier testen wir, ob die Import-Daten richtig interpretiert werden.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImport() throws IOException {
        satz.importFrom(input);
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
     * Hier testen wir, ob das Alignment in den FeldInfos tatsaechlich
     * ausgewertet wird.
     */
    @Test
    public void testAlignment() {
        satz.set(Feld100.KUNDENNR_VERSICHERER, "1234567890abcdef");
        assertEquals(" 1234567890abcdef", satz.get(Feld100.KUNDENNR_VERSICHERER));
    }

}

