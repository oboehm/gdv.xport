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
 * (c)reated 15.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.ANREDESCHLUESSEL;
import static gdv.xport.feld.Bezeichner.INKASSOART;
import static org.junit.Assert.assertEquals;
import gdv.xport.feld.AlphaNumFeld;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.junit.Test;

/**
 * Gemeinsame Oberklasse fuer SatzTest.
 * 
 * @author oliver
 * @since 15.10.2009
 * @version $Revision$
 */
public class DatensatzTest extends AbstractSatzTest {

    private static final Log log = LogFactoryImpl.getLog(DatensatzTest.class);

    /**
     * Test method for {@link gdv.xport.satz.Datensatz#Datensatz(java.lang.String, int)}.
     * @throws IOException falls der Export schief gegangen ist
     */
    @Test
    public void testDatensatzStringInt() throws IOException {
        Satz adressteil = new Datensatz("0100", 5);
        adressteil.add(new AlphaNumFeld(ANREDESCHLUESSEL, 1, 43, '6'));
        log.info("adressteil=" + adressteil.toShortString());
        checkExport(adressteil, 43, 43, "6", 1280);
    }

    /**
     * Test mit dem Datensatz "0200".
     */
    @Test
    public void testSet() {
        Satz ds = new Datensatz("0200", 2);
        ds.add(new AlphaNumFeld(INKASSOART, 1, 43));
        ds.set(INKASSOART, "2");
        assertEquals(ds.get(INKASSOART), "2");
    }

}

