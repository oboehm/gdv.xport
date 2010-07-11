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

import static org.junit.Assert.*;
import gdv.xport.Datenpaket;
import gdv.xport.satz.Datensatz;
import gdv.xport.util.*;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;

import patterntesting.runtime.annotation.IntegrationTest;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * JUnit-Test fuer die MyUnfallDatensatz-Klasse.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.4 (11.07.2010)
 */
@RunWith(SmokeRunner.class)
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

}

