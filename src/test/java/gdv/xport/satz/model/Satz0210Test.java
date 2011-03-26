/*
 * Copyright (c) 2011 by agentes
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
 * (c)reated 26.03.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz.model;

import static org.junit.Assert.*;

import java.io.*;

import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.satz.*;

import org.apache.commons.logging.*;
import org.junit.*;

/**
 * JUnit tests for Satz0210.
 * Some of the tests are transfered from {@link VertragsspezifischerTeilTest}
 * to here.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.6 (26.03.2011)
 */
public final class Satz0210Test extends AbstractSatzTest {
    
    private static final Log log = LogFactory.getLog(Satz0210.class);

    /**
     * Damit die Assert's der Satzlaenge stimmen, schalten wir das
     * End-of-Datensatz ab.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Config.setEOD("");
    }

    /**
     * Test method for {@link gdv.xport.satz.model.Satz0210#Satz0210(int)}.
     * @throws IOException falls die Platte voll ist (oder sowas)
     */
    @Test
    public void testSatz0210() throws IOException {
        Satz0210 vertragsteil = new Satz0210(10);
        log.info(vertragsteil + " created.");
        assertEquals(10, vertragsteil.getSparte());
        checkExport(vertragsteil, 11, 13, "010", 512);
    }

    /**
     * Dieser Test dient nur zum Ueberpruefen, ob die Folgenummer auch
     * tatsaechlich gesetzt ist.
     */
    @Test
    public void testGetFolgenummer() {
        Satz0210 vertragsteil = new Satz0210(10);
        vertragsteil.setFolgenummer(42);
        Teildatensatz teildatensatz = vertragsteil.getTeildatensatz(1);
        Feld feld = teildatensatz.getFeld(Bezeichner.FOLGENUMMER);
        assertNotNull(feld);
        assertEquals("42", feld.getInhalt());
    }
    
    /**
     * Ein sehr rudimentaerer Test fuer Sparte 10 (Leben).
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSparte10() throws IOException {
        Satz0210 leben = new Satz0210(10);
        StringWriter exported = new StringWriter();
        leben.export(exported);
        exported.close();
        Satz0210 imported = new Satz0210(10);
        imported.importFrom(exported.toString());
        assertEquals(leben, imported);
    }

}

