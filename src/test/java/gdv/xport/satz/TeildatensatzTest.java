/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 14.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static org.junit.Assert.*;

import gdv.xport.feld.*;

import java.io.IOException;
import java.util.*;

import org.junit.Test;

/**
 * @author oliver
 * @since 14.10.2009
 * @version $Revision$
 *
 */
public class TeildatensatzTest extends AbstractSatzTest {

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
     *
     * @since 0.2
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

}
