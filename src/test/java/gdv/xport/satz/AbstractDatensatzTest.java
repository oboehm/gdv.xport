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

import static org.junit.Assert.*;
import gdv.xport.feld.Feld;
import gdv.xport.satz.feld.common.Feld1bis7;

import org.junit.*;

/**
 * Dies ist die gemeinsame Oberklasse fuer alle Tests, die abgeleitete
 * {@link Datensatz}-Klasse testen.
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

}
