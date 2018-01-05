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
 * (c)reated 06.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import static org.junit.Assert.assertEquals;
import gdv.xport.satz.AbstractDatensatzTest;
import gdv.xport.satz.Satz;
import gdv.xport.satz.feld.Feld200;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * JUnit-Test fuer Satz200.
 *
 * @author oliver (ob@aosd.de)
 * @since 06.03.2011
 */
public class Satz200Test extends AbstractDatensatzTest {

    private static final Logger LOG = LogManager.getLogger(Satz200Test.class);
    private static final String INPUT_SPARTE30
            = "02009999  030      599999999990199990099992010520040105200901052"
            + "00511  0000000001        01052004100000         EUR000000041141 "
            + "                             0           B4LTTT                 "
            + "  04100001052004                                   EUR1        1"
            + "\n";
    private final Satz200 satz = new Satz200();

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Satz200();
    }

    /**
     * Test method for {@link gdv.xport.satz.model.Satz200#Satz200()}.
     */
    @Test
    public void testSatz0200() {
        assertEquals(200, satz.getSatzart());
    }

    /**
     * Hier testen wir den Import und Export.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportExport() throws IOException {
        checkImportExport(satz, INPUT_SPARTE30);
    }

    /**
     * Hier testen wir, ob die Import-Daten richtig interpretiert werden.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImport() throws IOException {
        satz.importFrom(INPUT_SPARTE30);
        assertEquals(200, satz.getSatzart());
        assertEquals("9999", satz.getVuNummer());
        assertEquals(30, satz.getSparte());
        assertEquals("59999999999", satz.getVersicherungsscheinNummer());
        assertEquals(1, satz.getFolgenummer());
        assertEquals("9999009999", satz.getVermittler());
        assertEquals("2", satz.get(Feld200.INKASSOART));
        assertEquals("01052004", satz.get(Feld200.VERTRAGSBEGINN));
    }

    /**
     * Import-Test fuer einen Reader.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportFromReader() throws IOException {
        Reader reader = new StringReader(INPUT_SPARTE30);
        try {
            checkImportFrom(reader);
        } finally {
            reader.close();
        }
    }

    /**
     * Import-Test fuer einen Reader. Dieses Mal lesen wir aber zweimal den
     * gleichen Satz. Hintergrund ist, dass es bis 0.9.1 Probleme mit dem
     * Reader gab, wenn mehrere Datenpakete gelesen werden sollten.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportFromReaderTwice() throws IOException {
        Reader reader = new StringReader(INPUT_SPARTE30 + INPUT_SPARTE30 + INPUT_SPARTE30);
        try {
            checkImportFrom(reader);
            checkImportFrom(reader);
        } finally {
            reader.close();
        }
    }

    private void checkImportFrom(Reader reader) throws IOException {
        Satz200 unfall = new Satz200(30);
        unfall.importFrom(reader);
        assertEquals(INPUT_SPARTE30.trim(), unfall.getTeildatensatz(1).toLongString().trim());
    }

    /**
     * Hier schauen wir nur nach der Performance der setUpDatenfelder()-
     * Methode, die im Construktor aufgerufen wird. Wegen der Timer-Aufloesung
     * sollte dieser Test unter Linux/Unix oder MacOS laufen.
     */
    @Test
    public void testCtorPerformance() {
        int n = 10;
        long t0 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            new Satz200();
        }
        long nanos = System.nanoTime() - t0;
        LOG.info("time of new Satz200(): " +  (((double)nanos)/n/1000000.0) + " ms");
    }

}

