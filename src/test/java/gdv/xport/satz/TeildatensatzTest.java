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
 * (c)reated 14.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.feld.common.VertragsStatus;

import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

/**
 * @author oliver
 * @since 14.10.2009
 * @version $Revision$
 *
 */
public class TeildatensatzTest extends AbstractSatzTest {

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Teildatensatz(123, 1);
    }

    /**
     * Test method for {@link gdv.xport.satz.Teildatensatz#export(java.io.Writer)}.
     * @throws IOException sollte eigentlich nicht auftreten
     */
    @Test
    public void testExport() throws IOException {
        Teildatensatz teildatensatz = new Teildatensatz(new NumFeld("Feld42", "0042"), 1);
        this.checkExport(teildatensatz, 1, 4, "0042", 256);
        this.checkExport(teildatensatz, 255, 256, " 1", 256);
    }

    /**
     * Die einzelnen Felder sollten in der Reihenfolge der Byte-Adresse
     * geliefert werden.
     */
    @Test
    public void testGetFelder() {
        Teildatensatz teildatensatz = new Vorsatz().getTeildatensatz(1);
        Iterator<Feld> iterator = teildatensatz.getFelder().iterator();
        Feld prev = iterator.next();
        while(iterator.hasNext()) {
            Feld next = iterator.next();
            assertTrue("wrong sorted: " + prev + " > " + next, prev.getByteAdresse() < next
                    .getByteAdresse());
            prev = next;
        }
    }

    /**
     * Test-Methode fuer {@link Teildatensatz#hasFeld(Enum)}.
     */
    @Test
    public void testHasFeld() {
        Teildatensatz tds = new Teildatensatz(new NumFeld("Feld42", "0042"), 1);
        tds.add(new NumFeld(VertragsStatus.AUSSCHLUSS));
        assertFalse("unexpected: VERTRAGSSTATUS in " + tds, tds.hasFeld(VertragsStatus.VERTRAGSSTATUS));
        assertTrue("expected: AUSSCHLUSS in " + tds, tds.hasFeld(VertragsStatus.AUSSCHLUSS));
    }

    /**
     * Test-Methode fuer {@link Teildatensatz#getFeld(int)}.
     */
    @Test
    public void testGetFeld() {
        Teildatensatz tds = new Teildatensatz(100, 1);
        assertEquals(tds.getSatzartFeld(), tds.getFeld(1));
        assertEquals(tds.getNummer(), tds.getFeld(2));
        NumFeld two = new NumFeld("two", 2, 5);
        tds.add(two);
        Feld feld = tds.getFeld(2);
        assertEquals(two, feld);
    }

    /**
     * Test-Methode fuer {@link Teildatensatz#getFeld(String)}.
     */
    @Test
    public void testGetFeldString() {
        Teildatensatz tds = new Teildatensatz(100, 1);
        Feld feld = new NumFeld("Hello", 55, "World");
        tds.add(feld);
        assertEquals(feld, tds.getFeld("Hello"));
    }

    /**
     * Bei der internen Umstellung des {@link Teildatensatz}es auf die
     * erweiterte {@link Bezeichner}-Klasse gab es Probleme mit dem Loeschen
     * von Feldern.
     */
    @Test
    public void testRemove() {
        Teildatensatz tds = new Teildatensatz(100, 1);
        Zeichen satznummer = new Zeichen("Satznummer", 256, '1');
        tds.add(satznummer);
        assertEquals(satznummer, tds.getFeld(satznummer.getBezeichnung()));
        tds.remove(satznummer.getBezeichnung());
        assertEquals("remove failed", Feld.NULL_FELD, tds.getFeld(satznummer.getBezeichnung()));
    }

}
