/*
 * Copyright (c) 2010 - 2012 by Oli B.
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
 * (c)reated 11.07.2010 by Oli B. (ob@aosd.de)
 */

package gdv.xport.demo;

import static org.junit.Assert.assertEquals;
import gdv.xport.Datenpaket;
import gdv.xport.feld.Feld;
import gdv.xport.satz.Datensatz;
import gdv.xport.util.SatzFactory;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import patterntesting.concurrent.junit.ParallelRunner;
import patterntesting.runtime.annotation.IntegrationTest;

/**
 * JUnit-Test fuer die MyUnfallDatensatz-Klasse.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.4 (11.07.2010)
 */
@RunWith(ParallelRunner.class)
public final class MyUnfallDatensatzTest {

    private static final Log log = LogFactory.getLog(MyUnfallDatensatzTest.class);

    /**
     * Registriert MyUnfallDatensatz und importiert dann zu Testzwecken
     * die Musterdatei.
     *
     * @throws IOException wenn die musterdatei nicht gelesen werden kann
     */
    @Test
    @IntegrationTest
    public void testMyUnfallDatensatz() throws IOException {
        // im Framework registrieren
        SatzFactory.register(MyUnfallDatensatz.class, 210, 30);
        // Datenpaket importieren
        Datenpaket datenpaket = new Datenpaket();
        URL url = this.getClass().getResource("/musterdatei_041222.txt");
        datenpaket.importFrom(url);
        // jetzt den ersten Datensatz 210, Sparte 30 suchen und testen
        for (Datensatz datensatz : datenpaket.getDatensaetze()) {
            if ((datensatz.getSatzart() == 210) && (datensatz.getSparte() == 30)) {
                assertEquals("EUR", datensatz.getFeld(MyFeld210.MEINE_WAEHRUNG).getInhalt());
                break;
            }
        }
    }

    /**
     * Die Bezeichnung von MyFeld210.MEINE_WAEHRUNG sollte "Meine Waehrung"
     * im Datensatz lauten.
     *
     * @since 0.6
     */
    @Test
    public void testBezeichner() {
        Datensatz myDatensatz = new MyUnfallDatensatz();
        Feld meineWaehrung = myDatensatz.getFeld(MyFeld210.MEINE_WAEHRUNG);
        assertEquals("MEINE_WAEHRUNG", meineWaehrung.getBezeichner());
        assertEquals("Meine Waehrung", meineWaehrung.getBezeichnung());
    }

    /**
     * Testet die main-Methode.
     *
     * @throws XMLStreamException bei fehlerhaftem XML
     * @throws IOException falls die URL nicht erreicht werden kann
     */
    @Test
    @IntegrationTest
    public void testMain() throws IOException, XMLStreamException {
        try {
            MyUnfallDatensatz.main(new String[] {});
        } catch (UnknownHostException mayhappen) {
            log.warn("Offline? testMain() abgebrochen!", mayhappen);
        }
    }

    /**
     * Hier setzen die SatzFactory auf den Ausgangsstand zurueck.
     *
     * @since 0.5.0
     */
    @AfterClass
    public static void restoreSatzFactory() {
        SatzFactory.unregister(210, 30);
    }

}

