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
 * (c)reated 14.04.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.AbstractDatensatzTest;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * JUnit-Test fuer Satz221.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (14.04.2011)
 */
public class Satz221Test extends AbstractDatensatzTest {

    private static final Logger LOG = LogManager.getLogger(Satz221Test.class);

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Satz221();
    }

    /**
     * Der Lesbarkeit halber aktivieren wir das Zeilenende fuer jeden exportierten Satz.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Config.setEOD("\n");
    }

    /**
     * Test-Methode fuer die Sparte 30. Die Sparte 30 besteht aus 2 Teildatensaetze
     * mit den Nummern 2 und 3.
     */
    @Test
    public void testSparte30() {
        Datensatz wagnisdaten = new Satz221(30);
        LOG.info(wagnisdaten + " created.");
        assertEquals(30, wagnisdaten.getSparte());
        assertEquals(2, wagnisdaten.getTeildatensaetze().size());
    }

    /**
     * Test-Methode fuer den Constructor.
     */
    @Test
    public void testSparte70() {
        Datensatz rechtschutz = new Satz221(70);
        LOG.info(rechtschutz + " created.");
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
        Datensatz wagnisdaten = new Satz221(30);
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
        Datenpaket datenpaket = importDatenpaket("/gdv/xport/satz/Satz0221030.txt");
        Datensatz erweiterungssatz = datenpaket.getDatensaetze().get(0);
        Feld lfdNummer = erweiterungssatz.getFeld(Bezeichner.LFD_NUMMER_VP_PERSONENGRUPPE);
        assertEquals("000001", lfdNummer.getInhalt());
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
        Datensatz sparte51 = new SatzX(221, gdv.xport.satz.feld.sparte51.Feld221.values());
        sparte51.importFrom(input);
        List<ConstraintViolation> violations = sparte51.validate();
        assertTrue(violations + " should be empty", violations.isEmpty());
    }

}

