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
 * (c)reated 18.11.2010 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.LFD_NUMMER_VP_PERSONENGRUPPE;
import static org.junit.Assert.*;
import gdv.xport.Datenpaket;
import gdv.xport.config.Config;
import gdv.xport.feld.Feld;

import java.io.*;
import java.util.List;

import net.sf.oval.ConstraintViolation;

import org.apache.commons.logging.*;
import org.junit.*;

/**
 * JUnit-Test fuer Erweiterungssatz221.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.5.0 (18.11.2010)
 */
public final class Erweiterungssatz221Test extends AbstractSatzTest {

    private static final Log log = LogFactory.getLog(Erweiterungssatz221Test.class);

    /**
     * Der Lesbarkeit halber aktivieren wir das Zeilenende fuer jeden exportierten Satz.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Config.setEOD("\n");
    }

    /**
     * Test method for {@link Erweiterungssatz221#Erweiterungssatz221(int)}.
     */
    @Test
    public void testSparte70() {
        Erweiterungssatz221 rechtschutz = new Erweiterungssatz221(70);
        log.info(rechtschutz + " created.");
        assertEquals(70, rechtschutz.getSparte());
    }

    /**
     * Hier wird der Teildatensatz 2 der Sparte 30 (Rechtschutz) getestet. Der Test-Input dazu stammt von der
     * musterdatei_041222.txt von gdv-online.
     * 
     * @throws IOException
     *             sollte eigentlich nicht vorkommen, da wir von einem String importieren
     */
    @Test
    public void testSparte30Teildatensatz2() throws IOException {
        String input = "02219999  030      599999999990199990099990000012000000050000000"
                + "0000025000000000000000000000000000000000000000000000000000000000"
                + "0000000000000000000000000000000000000000000000000000010000000000"
                + "000000000                                                      X" + "\n";
        checkWagnisdaten(input);
    }

    /**
     * Hier wird der Teildatensatz 3 der Sparte 30 (Rechtschutz) getestet. Der Test-Input dazu stammt von der
     * musterdatei_041222.txt von gdv-online.
     * 
     * @throws IOException
     *             sollte eigentlich nicht vorkommen, da wir von einem String importieren
     */
    @Test
    public void testSparte30Teildatensatz3() throws IOException {
        String input = "02219999  030      59999999999019999009999300000000000000       "
                + "                                                                "
                + "                                                                "
                + "                                                         3000001" + "\n";
        checkWagnisdaten(input);
    }

    private void checkWagnisdaten(final String input) throws IOException {
        assertEquals(257, input.length());
        Erweiterungssatz221 wagnisdaten = new Erweiterungssatz221(30);
        wagnisdaten.importFrom(input);
        checkDatensatz(wagnisdaten, input);
    }

    /**
     * Der normale Import bereitet noch Probleme. Mit diesem Test wollen wir dem Problem auf die Spur kommen.
     * 
     * @throws IOException
     *             falls der Test-Satz nicht gelesen werden kann
     */
    @Test
    public void testImportSparte30() throws IOException {
        InputStream istream = this.getClass().getResourceAsStream("Satz0221030.txt");
        try {
            Datenpaket datenpaket = new Datenpaket();
            datenpaket.importFrom(istream);
            Satz erweiterungssatz = datenpaket.getDatensaetze().get(0);
            Feld lfdNummer = erweiterungssatz.getFeld(LFD_NUMMER_VP_PERSONENGRUPPE);
            assertEquals("000001", lfdNummer.getInhalt());
        } finally {
            istream.close();
        }
    }
    
    /**
     * Test import.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportSparte51() throws IOException {
        String input = "02219999  051      599999999900199990099990000500000000000000000"
            + "0000000000000000000000000008668700000000000000000000000000000000"
            + "0000000000000000                                                "
            + "                                                               1";
        Erweiterungssatz221 sparte51 = new Erweiterungssatz221();
        sparte51.importFrom(input);
        List<ConstraintViolation> violations = sparte51.validate();
        assertTrue(violations + " should be empty", violations.isEmpty());
    }

}