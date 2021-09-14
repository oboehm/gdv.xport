/*
 * Copyright (c) 2021 by Oliver Boehm
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
 * (c)reated 13.01.2021 by oboehm
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.*;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import javax.validation.ValidationException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Unit-Tests fuer {@link SatzRegistry}.
 *
 * @author oboehm
 * @since 5.0 (13.01.2021)
 */
public final class SatzRegistryTest {

    private final SatzRegistry f2015 = SatzRegistry.getInstance("VUVM2015.xml");
    private final SatzRegistry f2018 = SatzRegistry.getInstance("VUVM2018.xml");

    @Test
    public void testGetInstance2015() {
        assertNotNull(f2015);
    }

    @Test
    public void testGetSameInstance() {
        SatzRegistry f1 = SatzRegistry.getInstance();
        SatzRegistry f2 = SatzRegistry.getInstance();
        assertSame(f1, f2);
    }

    @Test
    public void testGetDifferentInstances() {
        assertNotEquals(f2015, f2018);
    }

    @Test
    public void testGetVersionOf() {
        assertEquals("1.1", f2015.getVersionOf(SatzTyp.of("0052")));
    }

    @Test
    public void testVorsatz2015() {
        assertEquals("1.1", getVersionSatzart0052(f2015));
    }

    @Test
    public void testVorsatz2018() {
        assertEquals("1.2", getVersionSatzart0052(f2018));
    }

    private String getVersionSatzart0052(SatzRegistry factory) {
        Vorsatz vorsatz = factory.getVorsatz();
        return getVersionSatzart0052(vorsatz);
    }

    private String getVersionSatzart0052(Vorsatz vorsatz) {
        vorsatz.setVersion(SatzTyp.of("0052"));
        Feld version = vorsatz.getFeld(Bezeichner.VERSION_SATZART_0052);
        return version.getInhalt();
    }

    @Test
    public void testGetNachsatz2015() {
        Nachsatz nachsatz = f2015.getNachsatz();
        assertEquals("1.1", nachsatz.getSatzversion().getInhalt());
    }

    @Test(expected = ValidationException.class)
    public void testRegister() {
        f2018.register(Datensatz.class, 47);
    }

    @Test
    public void testSatz200Wagnis13() {
        Satz wagnis13 = f2018.getSatz(SatzTyp.of("0220.010.13.1"));
        assertEquals(220, wagnis13.getSatzart());
        assertEquals(10, wagnis13.getSparte());
        MatcherAssert.assertThat(wagnis13.getWagnisart(), either(is("1")).or(is("3")));
        for (int nr = 1; nr <= 3; nr++) {
            Teildatensatz tds = wagnis13.getTeildatensatz(nr);
            assertEquals(nr, tds.getSatznummer().toInt());
        }
    }

    @Test
    public void testGetAllSupportedSaetze2015() {
        Datenpaket d2015 = f2015.getAllSupportedSaetze();
        assertEquals(159, d2015.getDatensaetze().size());
        assertEquals(161, d2015.getAllSaetze().size());
        assertEquals("1.1", getVersionSatzart0052(d2015.getVorsatz()));
        assertEquals("1.1", d2015.getNachsatz().getSatzversion().getInhalt());
    }

    @Test
    public void testGetSatz2013() {
        Satz satz = SatzRegistry.getInstance("VUVM2013.xml").getSatz(SatzTyp.of("0230.030"), "1.0");
        assertEquals(SatzTyp.of("0230.030"), satz.getSatzTyp());
        assertEquals("1.0", satz.getVersion());
    }

}
