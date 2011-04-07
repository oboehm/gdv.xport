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

import static gdv.xport.feld.Bezeichner.*;
import static org.junit.Assert.*;

import java.io.*;

import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.satz.*;

import org.apache.commons.logging.*;
import org.junit.*;

/**
 * JUnit tests for Satz210.
 * Some of the tests are transfered from {@link VertragsspezifischerTeilTest}
 * to here.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.6 (26.03.2011)
 */
public final class Satz210Test extends AbstractSatzTest {
    
    private static final Log log = LogFactory.getLog(Satz210.class);

    /**
     * Damit die Assert's der Satzlaenge stimmen, schalten wir das
     * End-of-Datensatz ab.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Config.setEOD("");
    }

    /**
     * Test method for {@link gdv.xport.satz.model.Satz210#Satz210(int)}.
     * @throws IOException falls die Platte voll ist (oder sowas)
     */
    @Test
    public void testSatz0210() throws IOException {
        SpartensatzX vertragsteil = new Satz210(10);
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
        SpartensatzX vertragsteil = new Satz210(10);
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
        SpartensatzX leben = new Satz210(10);
        StringWriter exported = new StringWriter();
        leben.export(exported);
        exported.close();
        SpartensatzX imported = new Satz210(10);
        imported.importFrom(exported.toString());
        assertEquals(leben, imported);
    }

    /**
     * Da inzwischen auch Sparte 30 (Unfall) unterstuetzt wird, sollte ein Import
     * dafuer kein Problem mehr sein.
     * Der Test-Input dazu stammt von der musterdatei_041222.txt von gdv-online.
     *
     * @throws IOException
     *             sollte eigentlich nicht vorkommen, da wir von einem String
     *             importieren
     */
    @Test
    public void testSparte30() throws IOException {
        String input = "02109999  030      599999999980199990099991010520040105200901052"
                + "0040901 0000000000000000000 EUR000000000000000000000000000000002"
                + "4290000000000 0001000                                           "
                + "           000000                                               ";
        assertEquals(256, input.length());
        SpartensatzX unfall = new Satz210(30);
        unfall.importFrom(input);
        assertEquals("9999", unfall.getVuNummer().trim());
        Feld rabatt = unfall.getFeld(LAUFZEITRABATT_IN_PROZENT);
        assertEquals("1000", rabatt.getInhalt());
        NumFeld prozent = (NumFeld) rabatt;
        assertEquals(10.00, prozent.toDouble(), 0.001);
        Feld vertragsstatus = unfall.getFeld(VERTRAGSSTATUS);
        assertEquals("1", vertragsstatus.getInhalt());
        StringWriter swriter = new StringWriter(256);
        unfall.export(swriter);
        assertEquals(input, swriter.toString());
    }

}

