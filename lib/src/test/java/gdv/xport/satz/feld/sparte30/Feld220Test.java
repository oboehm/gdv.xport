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
 * (c)reated 10.02.2013 by Oli B. (oliver.boehm@gmail.com)
 */

package gdv.xport.satz.feld.sparte30;

import static org.junit.Assert.assertEquals;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.AbstractDatensatzTest;
import gdv.xport.satz.Satz;
import gdv.xport.satz.model.Satz220;
import gdv.xport.satz.model.SatzX;

import java.io.IOException;

import org.junit.Test;

/**
 * Unit-Tests fuer {@link Feld220}.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.9 (10.02.2013)
 */
public class Feld220Test extends AbstractDatensatzTest {

    private final SatzX wagnisdaten = new SatzX(220, Feld220.values());

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Satz220(30);
    }

	/**
	 * Hier importieren wir einen Satz 220, Sparte 30 aus dem Musterdatensatz,
	 * um zu testen, ob die Typen in {@link Feld220} richtig definiert wurden.
	 * <p>
	 * Der Test ist in aehnlicher Form urspruenglich Bestandteil von
	 * Satz220Test.
	 * </p>
	 *
	 * @throws IOException sollte eigentlich nicht vorkommen, da wir von einem
	 *         String importieren
	 */
	@Test
	public void testSparte30() throws IOException {
        String input = "02209999  030      599999999990199990099990000011Kitzelpfutze   "
                + "               000000Kitzelpfutze                  Martina      "
                + "                 111119791000Hausfrau                      A 1EU"
                + "R0000000000000000041141010520040000000001052004          1      "
                + "\n"
                + "02209999  030      599999999990199990099990000012000000000011305"
                + "0000000000000141950000000000000000000000000000000000000000000000"
                + "0000000000000000000000000010000000000000000 00000000000000000000"
                + "000000000000000000000000000000000000000000000000000  000000    X"
                + "\n";
        wagnisdaten.importFrom(input);
        Feld x = wagnisdaten.getFeld(Bezeichner.ZUSAETZLICHE_SATZKENNUNG, 2);
        assertEquals("X", x.getInhalt());
        checkDatensatz(wagnisdaten, input);
	}

	/**
	 * Der spezielle Teildatensatz 9 der Sparte 30 bereitet Probleme, da er
	 * etwas aus der Reihe tanzt - er kann naemlich als erster Teildatensatz
	 * auftreten.
	 * <p>
	 * Dies ist auch der Grund, warum es die Satz220-Klasse gibt. Dort wird
	 * dieser Speziall-Fall abgehandelt. Daher wird dieser Test hier nicht
	 * funktionieren und hat auch deswegen keine "@Test"-Annotation.
	 * </p>
	 *
	 * @throws IOException sollte eigentlich nicht vorkommen, da wir von einem
	 *         String importieren
	 */
    public void testSparte30Teildatensatz9() throws IOException {
        String input = "02209999  030      59999999997019999009999000000001        900 M"
            + "artina Kitzekpfutze          00000                              "
            + "                                                                "
            + "                                                         9000000"
            + "\n"
            + "02209999  030      599999999970199990099990000021Kitzelpfutze   "
            + "               000000Kitzelpfutze                  Martina      "
            + "                 121219792000                              A 1EU"
            + "R0000000000000000012632010620040000000001062004          1      "
            + "\n"
            + "02209999  030      599999999970199990099990000022000000000009310"
            + "0000000000000116900000000000000000000000000000000000000000000000"
            + "0000000000000000000000000010000000000000000 00000000000000000000"
            + "000000000000000000000000000000000000000000000000000  000000    X"
            + "\n";
        wagnisdaten.importFrom(input);
        checkDatensatz(wagnisdaten, input);
    }

}

