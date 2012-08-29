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
 * (c)reated 09.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import static org.junit.Assert.*;

import gdv.xport.config.Config;
import gdv.xport.feld.VUNummer;

import org.junit.*;

/**
 * Hier setzen wir eine Standard-Konfiguration auf, die wir in den
 * verschiedenen JUnit-Tests verwenden.
 *
 * @author oliver
 * @since 09.10.2009
 * @version $Revision$
 */
public abstract class AbstractTest {

    /** zum Testen nehmen wir hier die VU-Nr. der Oerag */
    protected static final VUNummer VU_NUMMER = new VUNummer("5183");

    /**
     * Hierueber setzen wir eine Default-VU-Nummer zum Testen.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        Config.setVUNummer(VU_NUMMER);
    }
    
    /**
     * Dieser Test ist nur dazu da, um sicherzugehen, dass die VU-Nummer auch
     * wirklich gesetzt wurde. Und um die daemliche Fehlermeldung von
     * Checkstyle wegzubekommen, dass diese Klasse eine Utility-Klasse waere
     * (und daher einen privaten Konstruktor haben sollte).
     * 
     * @since 0.5.0
     */
    @Test
    public void testVUNummer() {
        assertEquals(VU_NUMMER, Config.getVUNummer());
    }

}

