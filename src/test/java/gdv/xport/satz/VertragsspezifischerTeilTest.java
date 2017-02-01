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
 * (c)reated 28.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.LAUFZEITRABATT_IN_PROZENT;
import static gdv.xport.feld.Bezeichner.VERTRAGSSTATUS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.satz.model.Satz210;

/**
 * Urspruenglich war dies die Testklasse fuer die Klasse
 * VertragsspezifischerTeil. Da diese Klasse inzwischen durch {@link Satz210}
 * ersetzt wurde, ist dies jetzt ein zusaetzlicher Test fuer {@link Satz210}.
 * Die alte VertragsspezifischerTeil-Klasse wird nicht mehr zum Testen
 * verwendet.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.1.0 (28.10.2009)
 */
public class VertragsspezifischerTeilTest extends AbstractSatzTest {

    private static final Logger LOG = LogManager.getLogger(VertragsspezifischerTeilTest.class);

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Satz210();
    }

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
        Satz vertragsteil = new Satz210(70);
        LOG.info(vertragsteil + " created.");
        assertEquals(70, vertragsteil.getSparte());
        checkExport(vertragsteil, 11, 13, "070", 256);
    }

    /**
     * Dieser Test dient nur zum Ueberpruefen, ob die Folgenummer auch
     * tatsaechlich gesetzt ist.
     */
    @Test
    public void testFolgenummer() {
        Datensatz vertragsteil = new Satz210(30);
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
        Satz leben = new Satz210(10);
        StringWriter exported = new StringWriter();
        leben.export(exported);
        exported.close();
        Satz imported = new Satz210(10);
        imported.importFrom(exported.toString());
        assertEquals(leben, imported);
    }

    /**
     * Da inzwischen auch Sparte 30 (Unfall) unterstuetzt wird, sollte ein Import
     * dafuer kein Problem mehr sein.
     * Der Test-Input dazu stammt von der musterdatei_041222.txt von gdv-online.
     *
     * @since 0.4
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
        Datensatz vertragsteil = new Satz210(30);
        vertragsteil.importFrom(input);
        assertEquals("9999", vertragsteil.getVuNummer().trim());
        Feld rabatt = vertragsteil.getFeld(LAUFZEITRABATT_IN_PROZENT);
        assertEquals("1000", rabatt.getInhalt());
        NumFeld prozent = (NumFeld) rabatt;
        assertEquals(10.00, prozent.toDouble(), 0.001);
        Feld vertragsstatus = vertragsteil.getFeld(VERTRAGSSTATUS);
        assertEquals("1", vertragsstatus.getInhalt());
        StringWriter swriter = new StringWriter(256);
        vertragsteil.export(swriter, "");
        assertEquals(input, swriter.toString());
    }

}

