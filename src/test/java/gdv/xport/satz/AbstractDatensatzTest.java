/*
 * Copyright (c) 2013 by Oli B.
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
 * (c)reated 17.04.2013 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gdv.xport.feld.Feld;
import gdv.xport.satz.feld.common.Feld1bis7;

import org.junit.Before;
import org.junit.Test;

/**
 * Dies ist die gemeinsame Oberklasse fuer alle Tests, die abgeleitete
 * {@link Datensatz}-Klasse testen. Es werden hier vor allem die ersten
 * sieben Felder getestet, die fuer jeden Datensatz gleich sind.
 *
 * @author oliver
 * @since 0.9 (17.04.2013)
 */
public abstract class AbstractDatensatzTest extends AbstractSatzTest {

    private Datensatz datensatz;

    /**
     * Liefert einen Datensatz.
     *
     * @return der Datensatz
     */
    protected Datensatz getDatensatz() {
        return (Datensatz) this.getSatz();
    }

    /**
     * Hier legen wir einen Datensatz zum Testen an.
     */
    @Before
    public void setUpDatensatz() {
        this.datensatz = this.getDatensatz();
    }

    /**
     * Byte 5 - 9 enthaelt die Nummer des Versicherungsunternehmen (VU-Nummer).
     * Dieses Feld wird hier ueberprueft.
     */
    @Test
    public void testVuNummer() {
        this.datensatz.setVuNummer("12345");
        Feld vuNummer = datensatz.getFeld(Feld1bis7.VU_NUMMER);
        assertTrue("expected: is valid", vuNummer.isValid());
        assertEquals("12345", vuNummer.getInhalt());
    }

    /**
     * Byte 10 enthaelt das Buendelungskennzeichen, das wir hier zum Testen
     * auf '1' setzen.
     */
    @Test
    public void testBuendelungskennzeichen() {
        this.datensatz.set(Feld1bis7.BUENDELUNGSKENNZEICHEN, '1');
        Feld kennzeichen = this.datensatz.getFeld(Feld1bis7.BUENDELUNGSKENNZEICHEN);
        assertEquals("1", kennzeichen.getInhalt());
    }

    /**
     * Die Sparte sollte mit einem gueltigen Wert zwischen 0 und 999 belegt
     * sein.
     */
    @Test
    public void testSparte() {
        int sparte = this.datensatz.getSparte();
        assertTrue(sparte + " >= 0", sparte >= 0);
        assertTrue(sparte + " <= 999", sparte <= 999);
        Feld feld = this.datensatz.getFeld(Feld1bis7.SPARTE);
        assertEquals(sparte, Integer.parseInt(feld.getInhalt()));
    }

    /**
     * Byte 14 bis 30 beinhaltet den Versichungsscheinnummer, der hier
     * getestet wird.
     */
    @Test
    public void testVersicherungsscheinNummer() {
        String nr = "Scheinnummer34567";
        this.datensatz.setVersicherungsscheinNummer(nr);
        assertEquals(nr, this.datensatz.getFeld(Feld1bis7.VERSICHERUNGSSCHEINNUMMER).getInhalt());
    }

}
