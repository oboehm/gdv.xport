/*
 * Copyright (c) 2010 by agentes
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
 * (c)reated 11.07.2010 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.demo;

import static org.junit.Assert.assertEquals;
import gdv.xport.Datenpaket;
import gdv.xport.satz.Datensatz;
import gdv.xport.util.SatzFactory;

import java.io.IOException;
import java.net.URL;

import javax.xml.stream.XMLStreamException;

import org.junit.*;
import org.junit.runner.RunWith;

import patterntesting.concurrent.junit.ParallelRunner;
import patterntesting.runtime.annotation.IntegrationTest;

/**
 * JUnit-Test fuer die MyUnfallDatensatz-Klasse.
 *
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.4 (11.07.2010)
 */
@RunWith(ParallelRunner.class)
@IntegrationTest
public final class MyUnfallDatensatzTest {

    /**
     * Registriert MyUnfallDatensatz und importiert dann zu Testzwecken
     * die Musterdatei.
     *
     * @throws IOException wenn die musterdatei nicht gelesen werden kann
     */
    @Test
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
                assertEquals("EUR", datensatz.getFeld("meine Waehrung").getInhalt());
                break;
            }
        }
    }

    /**
     * Testet die main-Methode.
     *
     * @throws XMLStreamException bei fehlerhaftem XML
     * @throws IOException falls die URL nicht erreicht werden kann
     */
    @Test
    public void testMain() throws IOException, XMLStreamException {
        MyUnfallDatensatz.main(new String[] {});
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

