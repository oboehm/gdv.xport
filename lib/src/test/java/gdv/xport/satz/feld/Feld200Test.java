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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 07.04.18 by oliver (ob@oasd.de)
 */
package gdv.xport.satz.feld;

import gdv.xport.satz.model.Satz200;
import org.junit.Test;

/**
 * Unit-Tests fuer {@link Feld100}-Klasse.
 *
 * @author oliver (ob@jfachwert.de)
 */
public class Feld200Test extends AbstractFeldTest {

    /**
     * Weiterer Testfall fuer Issue 10
     * (https://github.com/oboehm/gdv.xport/issues/10).
     */
    @Test
    public void testFeld200Konstanten() {
        checkEntries(new Satz200(), Feld200.values());
    }

}
