/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 28.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static org.junit.Assert.*;

import gdv.xport.config.Config;
import gdv.xport.feld.*;

import java.io.IOException;

import org.apache.commons.logging.*;
import org.junit.*;

/**
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.1.0 (28.10.2009)
 */
public class VertragsspezifischerTeilTest extends AbstractSatzTest {

    private static final Log log = LogFactory.getLog(VertragsspezifischerTeilTest.class);

    /**
     * Damit die Assert's der Satzlaenge stimmen, muessen wir das 
     * End-of-Datensatz abschalten.
     * 
     * @since 0.3
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Config.setEOD("");
    }

    /**
     * Tested den Konstruktor.
     * @throws IOException falls die Platte voll ist (oder sowas)
     */
    @Test
    public void testSpartenspezifischerVertragsteil() throws IOException {
        VertragsspezifischerTeil vertragsteil = new VertragsspezifischerTeil(70);
        log.info(vertragsteil + " created.");
        assertEquals(70, vertragsteil.getSparte());
        checkExport(vertragsteil, 11, 13, "070", 256);
    }
    
    /**
     * Dieser Test dient nur zum Ueberpruefen, ob die Folgenummer auch
     * tatsaechlich gesetzt ist.
     */
    @Test
    public void testFolgenummer() {
        VertragsspezifischerTeil vertragsteil = new VertragsspezifischerTeil(30);
        vertragsteil.setFolgenummer(42);
        Teildatensatz teildatensatz = vertragsteil.getTeildatensatz(1);
        Feld feld = teildatensatz.getFeld(Bezeichner.FOLGENUMMER);
        assertNotNull(feld);
        assertEquals("42", feld.getInhalt());
    }

}

