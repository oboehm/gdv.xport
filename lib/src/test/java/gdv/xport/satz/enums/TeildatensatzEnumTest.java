/*
 * Copyright (c) 2020 by Oliver Boehm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 25.11.2020 by oboehm
 */

package gdv.xport.satz.enums;

import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.TeildatensatzTest;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzTyp;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Unit-Tests fuer {@link TeildatensatzEnum}.
 */
public class TeildatensatzEnumTest extends TeildatensatzTest {

    @Override
    protected Satz getSatz() {
        return new TeildatensatzEnum(123, 1);
    }

    /**
     * Test-Methode fuer {@link Teildatensatz#getFeld(int)}.
     */
    @Test
    public void testGetFeld() {
        Teildatensatz tds = new TeildatensatzEnum(100, 1);
        assertEquals(tds.getSatzartFeld(), tds.getFeld(1));
        assertEquals(tds.getSatznummer(), tds.getFeld(2));
        NumFeld two = new NumFeld(new Bezeichner("two"), 2, 5);
        tds.add(two);
        Feld feld = tds.getFeld(2);
        assertEquals(two, feld);
    }

    /**
     * Test method for {@link gdv.xport.satz.Teildatensatz#export(java.io.Writer)}.
     * @throws IOException sollte eigentlich nicht auftreten
     */
    @Test
    public void testExport() throws IOException {
        Teildatensatz teildatensatz = new TeildatensatzEnum(new NumFeld("Feld42", "0042"), 1);
        this.checkExport(teildatensatz, 1, 4, "0042", 256);
        this.checkExport(teildatensatz, 255, 256, " 1", 256);
    }

    @Test
    public void testTeildatensatz500() {
        Teildatensatz tds = new TeildatensatzEnum(500, 1);
        Zeichen satznummer = tds.getSatznummer();
        assertEquals(66, satznummer.getByteAdresse());
        assertEquals("1", satznummer.getInhalt());
    }

}