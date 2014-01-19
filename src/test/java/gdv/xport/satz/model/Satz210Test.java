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
 * (c)reated 26.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import static gdv.xport.feld.Bezeichner.LAUFZEITRABATT_IN_PROZENT;
import static gdv.xport.feld.Bezeichner.VERTRAGSSTATUS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gdv.xport.feld.*;
import gdv.xport.satz.*;

import java.io.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;

/**
 * JUnit-Tests fuer Satz210.
 * Einige Tests stammen vom {@link VertragsspezifischerTeilTest}.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (26.03.2011)
 */
public final class Satz210Test extends AbstractDatensatzTest {

    private static final Log log = LogFactory.getLog(Satz210.class);
    private static final String INPUT_SPARTE30
            = "02109999  030      599999999980199990099991010520040105200901052"
            + "0040901 0000000000000000000 EUR000000000000000000000000000000002"
            + "4290000000000 0001000                                           "
            + "           000000                                               "
            + "\n";

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
     * Test method for {@link gdv.xport.satz.model.Satz210#Satz210(int)}.
     * @throws IOException falls die Platte voll ist (oder sowas)
     */
    @Test
    public void testSatz210() throws IOException {
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
        SpartensatzX unfall = new Satz210(30);
        unfall.importFrom(INPUT_SPARTE30);
        assertEquals("9999", unfall.getVuNummer().trim());
        Feld rabatt = unfall.getFeld(LAUFZEITRABATT_IN_PROZENT);
        assertEquals("1000", rabatt.getInhalt());
        NumFeld prozent = (NumFeld) rabatt;
        assertEquals(10.00, prozent.toDouble(), 0.001);
        Feld vertragsstatus = unfall.getFeld(VERTRAGSSTATUS);
        assertEquals("1", vertragsstatus.getInhalt());
        StringWriter swriter = new StringWriter(256);
        unfall.export(swriter);
        assertEquals(INPUT_SPARTE30, swriter.toString());
    }

    /**
     * Testet den Import/Export einer noch nicht unterstuetzten Sparte (130).
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportExportUnsupportet() throws IOException {
        String input = "02109999 1130      599999999860199990099991000000000000000000000"
            + "000FELS                                                         "
            + "             EUR000000000000000000000000000000000000000000011985"
            + "VHB920       00001033 01100000 00000 000000KOMP 092001     00000"
            + "\n";
        Satz210 unfall = new Satz210();
        unfall.importFrom(input);
        StringWriter swriter = new StringWriter(256);
        unfall.export(swriter);
        swriter.close();
        assertEquals(input, swriter.toString());
    }

    /**
     * Import-Test fuer einen Reader.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportFromReader() throws IOException {
        Reader reader = new StringReader(INPUT_SPARTE30);
        try {
            checkImportFrom(reader);
        } finally {
            reader.close();
        }
    }

    /**
     * Import-Test fuer einen Reader. Dieses Mal lesen wir aber zweimal den
     * gleichen Satz. Hintergrund ist, dass es bis 0.9.1 Probleme mit dem
     * Reader gab, wenn mehrere Datenpakete gelesen werden sollten.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    @Ignore // Fehler erkannt, aber noch nicht behoben (19-Jan-2014)
    public void testImportFromReaderTwice() throws IOException {
        Reader reader = new StringReader(INPUT_SPARTE30 + INPUT_SPARTE30);
        try {
            checkImportFrom(reader);
            checkImportFrom(reader);
        } finally {
            reader.close();
        }
    }

    private void checkImportFrom(Reader reader) throws IOException {
        Satz210 unfall = new Satz210(30);
        unfall.importFrom(reader);
        assertEquals(INPUT_SPARTE30, unfall.toLongString());
    }

}

