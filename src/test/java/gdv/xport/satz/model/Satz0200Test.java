/*
 * Copyright (c) 2010 by agentes
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
 * (c)reated 06.03.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz.model;

import static org.junit.Assert.assertEquals;
import gdv.xport.satz.AbstractSatzTest;
import gdv.xport.satz.feld.Feld200;

import java.io.IOException;

import org.apache.commons.logging.*;
import org.junit.Test;

/**
 * JUnit-Test fuer Satz0200.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 06.03.2011
 */
public class Satz0200Test extends AbstractSatzTest {

    private static final Log log = LogFactory.getLog(Satz0200Test.class);
    private static final String input = 
        "02009999  030      599999999990199990099992010520040105200901052" +
        "00511  0000000001        01052004100000         EUR000000041141 " +
        "                             0           B4LTTT                 " +
        "  04100001052004                                   EUR1        1";
    private final Satz0200 satz = new Satz0200();

    /**
     * Test method for {@link gdv.xport.satz.model.Satz0200#Satz0200()}.
     */
    @Test
    public void testSatz0200() {
        assertEquals(200, satz.getSatzart());
    }
    
    /**
     * Hier testen wir den Import und Export.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportExport() throws IOException {
        checkImportExport(satz, input);
    }
    
    /**
     * Hier testen wir, ob die Import-Daten richtig interpretiert werden.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImport() throws IOException {
        satz.importFrom(input);
        assertEquals(200, satz.getSatzart());
        assertEquals("9999", satz.getVuNummer());
        assertEquals(30, satz.getSparte());
        assertEquals("59999999999", satz.getVersicherungsscheinNummer());
        assertEquals(1, satz.getFolgenummer());
        assertEquals("9999009999", satz.getVermittler());
        assertEquals("2", satz.get(Feld200.INKASSOART));
        assertEquals("01052004", satz.get(Feld200.VERTRAGSBEGINN));
    }
    
    /**
     * Hier schauen wir nur nach der Performance der setUpDatenfelder()-
     * Methode, die im Construktor aufgerufen wird. Wegen der Timer-Aufloesung
     * sollte dieser Test unter Linux/Unix oder MacOS laufen.
     */
    @Test
    public void testCtorPerformance() {
        int n = 10;
        long t0 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            new Satz0200();
        }
        long nanos = System.nanoTime() - t0;
        log.info("time of new Satz0200(): " +  (nanos/n/1000000.0) + " ms");
    }

}

