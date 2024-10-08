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
 * (c)reated 11.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

/**
 * JUnit-Test fuer die Betrag-Klasse.
 *
 * @author oliver
 * @since 11.10.2009
 * @version $Revision$
 */
public final class BetragTest extends AbstractNumFeldTest {

    private final Betrag betrag = new Betrag(Bezeichner.of("test"), 5, 1);

    /*
     * (non-Javadoc)
     * 
     * @see gdv.xport.feld.AbstractFeldTest#getTestFeld()
     */
    @Override
    protected Betrag getTestBetrag() {
        return this.betrag;
    }

}
