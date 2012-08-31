/*
 * Copyright (c) 2011, 2012 by Oli B.
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
 * (c)reated 05.01.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.SPARTE;
import static org.junit.Assert.assertEquals;
import gdv.xport.config.Config;
import gdv.xport.feld.*;

import org.apache.commons.logging.*;
import org.junit.Test;

/**
 * JUnit-Test fuer Erweiterungssatz211.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.5.0 (05.01.2011)
 */
public class Erweiterungssatz211Test {

    private static final Log log = LogFactory.getLog(Erweiterungssatz211Test.class);

    /**
     * Test method for {@link Erweiterungssatz211#Erweiterungssatz211(int)}.
     */
    @Test
    public void testSparte10() {
        createSparte(10);
    }

    /**
     * Hier testen wir die Sparte 50, allerdings nur sehr rudimentaer.
     */
    @Test
    public void testSparte50() {
        createSparte(50);
    }

    private void createSparte(final int sparte) {
        Erweiterungssatz211 erweiterungssatz = new Erweiterungssatz211(sparte);
        log.info(erweiterungssatz + " created.");
        assertEquals(sparte, erweiterungssatz.getSparte());
        assertEquals(Config.getVUNummer().getInhalt().trim(), erweiterungssatz.getVuNummer());
        Feld spartenFeld = erweiterungssatz.getFeld(Bezeichner.SPARTE);
        assertEquals(new NumFeld(SPARTE, 3, 11, sparte), spartenFeld);
        Feld vermittler = erweiterungssatz.getFeld(Bezeichner.VERMITTLER);
        assertEquals(33, vermittler.getByteAdresse());
    }

}
