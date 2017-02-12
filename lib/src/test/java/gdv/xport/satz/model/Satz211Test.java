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
 * (c)reated 06.04.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gdv.xport.config.Config;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.feld.NumFeld;

import java.io.IOException;
import java.util.List;

import net.sf.oval.ConstraintViolation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


/**
 * JUnit-Test fuer Satz211.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (06.04.2011)
 */
public class Satz211Test {

    private static final Logger LOG = LogManager.getLogger(Satz211Test.class);

    /**
     * Test method for {@link Satz211#Satz211(int)}.
     */
    @Test
    public void testSparte10() {
        Satz211 leben = new Satz211(10);
        LOG.info(leben + " created.");
        assertEquals(10, leben.getSparte());
        assertEquals(Config.getVUNummer().getInhalt().trim(), leben.getVuNummer());
        Feld sparte = leben.getFeld(Bezeichner.SPARTE);
        assertEquals(new NumFeld((Bezeichner.SPARTE), 3, 11, 10), sparte);
        Feld vermittler = leben.getFeld(Bezeichner.VERMITTLER);
        assertEquals(33, vermittler.getByteAdresse());
    }

    /**
     * Test import.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImport() throws IOException {
        String input = "02119999  050      599999999900199990099990000000000000000000000"
            + "00                                                              "
            + "                                                                "
            + "                                                               1";
        Satz211 sparte50 = new Satz211();
        sparte50.setSparte(50);
        sparte50.importFrom(input);
        assertEquals(50, sparte50.getSparte());
        assertEquals("000000000000", sparte50.getFeld(Bezeichner.NEUPREIS_IN_WAEHRUNGSEINHEITEN).getInhalt());
        List<ConstraintViolation> violations = sparte50.validate();
        assertTrue(violations + " should be empty", violations.isEmpty());
    }

}

