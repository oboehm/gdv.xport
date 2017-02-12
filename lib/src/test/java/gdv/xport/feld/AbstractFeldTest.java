/*
 * Copyright (c) 2014 by Oli B.
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
 * (c)reated 19.01.2015 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import org.junit.Test;

import patterntesting.runtime.junit.CloneableTester;
import patterntesting.runtime.junit.ObjectTester;

/**
 * Die gemeinsame Oberklasse fuer alle Tests mit einem {@link Feld}-Objekt.
 *
 * @author oliver
 * @since 1.0 (19.01.2015)
 */
public abstract class AbstractFeldTest {

    /**
     * Darueber holt sich diese Test-Klasse ein {@link Feld}-Objekt zum Testen.
     *
     * @return das Test-Feld
     */
    protected abstract Feld getTestFeld();

    /**
     * Hier testen wir, ob der CopyConstructor auch mit den Unterklassen von
     * {@link Feld} funktioniert.
     */
    @Test
    public void testCopyConstructor() {
        Feld orig = this.getTestFeld();
        Feld copy = new Feld(orig);
        ObjectTester.assertEquals(orig, copy);
    }

    /**
     * Test-Methode fuer {@link Feld#clone()}:
     */
    @Test
    public void testCloneable() {
        CloneableTester.assertCloning(getTestFeld());
    }

}
