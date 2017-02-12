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
 * (c)reated 27.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static org.junit.Assert.assertEquals;
import gdv.xport.feld.Bezeichner;
import gdv.xport.satz.model.Satz200;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * Urspruenglich war dieser JUnit-Tests fuer die AllgemeinerVertragsteil-Klasse
 * vorgesehen. Nachdem diese Klasse aber inzwischen durch {@link Satz200}
 * abgeloest wurde, ist es ein weiterer Test fuer diese neue Klasse.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.1.0 (27.10.2009)
 */
public class AllgemeinerVertragsteilTest extends AbstractSatzTest {

    private static final Logger LOG = LogManager.getLogger(AllgemeinerVertragsteilTest.class);
    private static final String input = "02009999  030      599999999990199990099992010520040105200901052"
            + "00511  0000000001        01052004100000         EUR000000041141 "
            + "                             0           B4LTTT                 "
            + "  04100001052004                                   EUR1        1";
    private final Datensatz vertragsteil = new Satz200();

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
     * Testet den allgemeinen Vertragsteil (Satz 200).
     */
    @Test
    public void testAllgemeinerVertragsteil() {
        assertEquals(200, vertragsteil.getSatzart());
    }

    /**
     * Hier testen wir den Import und Export.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportExport() throws IOException {
        checkImportExport(vertragsteil, input);
    }

    /**
     * Hier testen wir, ob die Import-Daten richtig interpretiert werden.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImport() throws IOException {
        vertragsteil.importFrom(input);
        assertEquals(200, vertragsteil.getSatzart());
        assertEquals("9999", vertragsteil.getVuNummer());
        assertEquals(30, vertragsteil.getSparte());
        assertEquals("59999999999", vertragsteil.getVersicherungsscheinNummer());
        assertEquals(1, vertragsteil.getFolgenummer());
        assertEquals("9999009999", vertragsteil.getVermittler());
        assertEquals("2", vertragsteil.get(Bezeichner.INKASSOART));
        assertEquals("01052004", vertragsteil.get(Bezeichner.VERTRAGSBEGINN));
    }

    /**
     * Hier schauen wir nur nach der Performance des Konstruktors. Wegen der
     * Timer-Aufloesung sollte dieser Test unter Linux/Unix oder MacOS laufen.
     * <p>
     * Zum Vergleich: die alte AllgemeinerVertragsteil-Klasse benoetigte auf
     * einem aktuelle Mac-Mini (Bj. 2012) ca. 2 ms, waehrend Satz200 ca. 20 ms
     * benoetigte.
     * </p>
     */
    @Test
    public void testCtorPerformance() {
        int n = 10;
        long t0 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            new Satz200();
        }
        long nanos = System.nanoTime() - t0;
        LOG.info("time of new Satz(): " + (((double) nanos)/n/1000000.0) + " ms");
    }

}

