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
 * (c)reated 13.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static org.junit.Assert.assertEquals;
import gdv.xport.config.Config;
import gdv.xport.satz.model.Satz100;

import java.io.IOException;

import org.junit.Test;

/**
 * Urspruenglich war diese Klasse fuer den Test der Adressteil-Klasse
 * vorgesehen. Da diese Klasse aber inzwischen durch die Satz100-Klasse
 * abgeloest wurde, ist es jetzt ein zusaetzlicher Test fuer diese Klasse.
 *
 * @author oliver
 * @since 13.10.2009
 * @version $Revision$
 */
public class AdressteilTest extends AbstractSatzTest {

    private final Satz100 adressteil = new Satz100();

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    public Satz getSatz() {
        return new Satz100();
    }

    /**
     * Test-Methode fuer den Export des Adressteils.
     *
     * @throws IOException falls der Export schief geht
     */
    @Test
    public void testAdressteil() throws IOException {
        Config.setEOD("");
        checkExport(adressteil, 256, 256, "1", 1280);
        checkExport(adressteil, 512, 512, "2", 1280);
    }

    /**
     * Test-Methode fuer setName().
     */
    @Test
    public void testSetName() {
        adressteil.setName("Donald", "Duck");
        assertEquals("Donald", adressteil.getName(1));
        assertEquals("Duck", adressteil.getName(3));
    }

    /**
     * Test-Methode fuer setName().
     */
    @Test
    public void testSetAlleNamen() {
        adressteil.setName("J.", "R.", "Ewing");
        assertEquals("J.", adressteil.getName(1));
        assertEquals("R.", adressteil.getName(2));
        assertEquals("Ewing", adressteil.getName(3));
    }

}

