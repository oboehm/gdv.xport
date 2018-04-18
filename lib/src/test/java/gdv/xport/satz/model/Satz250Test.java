/*
 * Copyright (c) 2018 by Oliver Boehm
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
 * (c)reated 17.04.2018 by oboehm (ob@oasd.de)
 */
package gdv.xport.satz.model;

import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.Teildatensatz;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit-Tests fuer {@link Satz250}-Klasse.
 *
 * @author oboehm
 */
public final class Satz250Test {
    
    private final Satz250 satz = new Satz250();

    /**
     * Hier testen wir, ob das erste Feld nach den Intro-Feldern (Feld 1 - 7)
     * gefunden wird.
     */
    @Test
    public void testGetLfdNrDeklaration() {
        Feld lfdNr = satz.getFeld(Bezeichner.LFD_NUMMER_DEKLARATION);
        assertEquals("00000000", lfdNr.getInhalt());
    }

    /**
     * Die Satznummer kommt nach der "Lfd. Nr. Deklaration" und sollte laut
     * Spezifikation immer auf 1 stehen.
     */
    @Test
    public void testGetSatznummer() {
        Teildatensatz eins = satz.getTeildatensatz(1);
        Feld satznummer = eins.getFeld(Bezeichner.SATZNUMMER);
        assertEquals("1", satznummer.getInhalt());
        assertEquals(51, satznummer.getByteAdresse());
    }

}
