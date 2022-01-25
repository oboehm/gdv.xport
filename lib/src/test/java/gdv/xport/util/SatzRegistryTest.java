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
import gdv.xport.feld.Betrag;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.*;
import gdv.xport.satz.xml.SatzXml;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import javax.validation.ValidationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Unit-Tests fuer {@link SatzRegistry}.
 *
 * @author oboehm
 * @since 5.0 (13.01.2021)
 */
public final class SatzRegistryTest {

    private final SatzRegistry f2009 = SatzRegistry.getInstance("VUVM2009.xml");
    private final SatzRegistry f2015 = SatzRegistry.getInstance("VUVM2015.xml");
    private final SatzRegistry f2018 = SatzRegistry.getInstance("VUVM2018.xml");

    /**
     * Hier testen wir mit Satz fuer die Kfz-Haftpflicht (0221.051), ob keine
     * Loecher im Datensatz sind. Problem bereiteten hier urspruenglich die
     * KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL#-Bezeichner.
     *
     * @throws XMLStreamException the xml stream exception
     * @throws IOException        the io exception
     */
    @Test
    public void testSatzart0221051() throws XMLStreamException, IOException {
        SatzTyp kfz = SatzTyp.of(221, 51);
        SatzRegistry satzRegistry = SatzRegistry.getInstance();
        try {
            satzRegistry.register(SatzXml.of("Satz0221.051.xml"), kfz, SatzRegistry.NO_VALIDATOR);
            Satz satz = satzRegistry.getSatz(kfz);
            SatzFactoryTest.checkDatensatz(satz);
            checkDeckungssumme(satz, Bezeichner.KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL1);
            checkDeckungssumme(satz, Bezeichner.KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL2);
            checkDeckungssumme(satz, Bezeichner.KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL3);
        } finally {
            satzRegistry.unregister(kfz);
        }
    }

    private static void checkDeckungssumme(Satz satz, Bezeichner name) {
        Betrag betrag = (Betrag) satz.getFeld(name);
        assertEquals(14, betrag.getAnzahlBytes());
        assertEquals(2, betrag.getNachkommastellen());
    }

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
    public void testSatz220Wagnis13() {
        checkWagnis(220, 10, 13, 1);
    }

    @Test
    public void testSatz221Wagnis13() {
        checkWagnis(221, 10, 13, 1);
    }

    private void checkWagnis31(int satzart) {
        Satz wagnis13 = f2018.getSatz(SatzTyp.of(satzart, 10, 13, 1));
        assertEquals(satzart, wagnis13.getSatzart());
        assertEquals(10, wagnis13.getSparte());
        MatcherAssert.assertThat(wagnis13.getWagnisart(), either(is("1")).or(is("3")));
        for (int nr = 1; nr <= wagnis13.getNumberOfTeildatensaetze(); nr++) {
            Teildatensatz tds = wagnis13.getTeildatensatz(nr);
            assertEquals(nr, tds.getSatznummer().toInt());
        }
    }

    @Test
    public void testSatz220Tds6() {
        Satz wagnis = checkWagnis(220, 10, 13, 6);
        assertNotNull(wagnis.getTeildatensatzBySatzNr(6));
    }

    @Test
    public void testSatz220Rentenversicherung() {
        checkWagnis(220, 10, 2, 1);
    }

    @Test
    public void testSatz220Bezugsrecht() {
        checkWagnis(220, 10, 2, 6);
    }

    @Test
    public void testSatz221Auszahlung() {
        checkWagnis(221, 10, 2, 7);
    }

    @Test
    public void testSatz221Berufsunfaehigkeit() {
        Satz wagnis = checkWagnis(221, 10, 48, 1);
        assertNotNull(wagnis.getTeildatensatzBySatzNr(2));
    }

    private Satz checkWagnis(int satzart, int sparte, int wagnisart, int satznr) {
        Satz wagnis = f2018.getSatz(SatzTyp.of(satzart, sparte, wagnisart, satznr));
        assertEquals(satzart, wagnis.getSatzart());
        assertEquals(sparte, wagnis.getSparte());
        if (wagnisart < 10) {
            assertEquals(Integer.toString(wagnisart), wagnis.getWagnisart());
        } else {
            MatcherAssert.assertThat(Integer.parseInt(wagnis.getWagnisart()), either(is(wagnisart / 10)).or(is(wagnisart % 10)));
        }
        return wagnis;
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

    @Test
    public void testGetSatz() {
        SatzTyp satzTyp = SatzTyp.of("0220.020.3");
        Satz satz = SatzRegistry.getSatz(satzTyp, "1.2");
        Satz expected = f2009.getSatz(satzTyp);
        assertEquals("different versions", expected, satz);
    }

    @Test
    public void testRegisterSatz0820() throws IOException {
        try {
            SatzRegistry.getInstance().register(SatzXml.of("/gdv/xport/satz/xml/Satz0820.xml"), SatzTyp.of("0820"));
        } catch (XMLStreamException e) {
            fail("Fehler bei der Registrierung.");
        }
        Datenpaket datenpaket = new Datenpaket();
        File testfile = new File("src/test/resources", "datenpakete/test0820satzregistry.txt");
        datenpaket.importFrom(testfile, StandardCharsets.UTF_8);
        assertTrue("Datenpaket muss gueltig sein", datenpaket.isValid());
        assertEquals("Es wergen genau 6 Datensätze erwartet.", 6, datenpaket.getDatensaetze().size());

        Datensatz datensatz3 = datenpaket.getDatensaetze().get(2);
        Datensatz datensatz6 = datenpaket.getDatensaetze().get(5);

        assertNotSame("Die 0820er-Datensätze dürfen nicht ein und dasselbe Objekt sein.", datensatz3, datensatz6);

        // prüfe Datensatz 3: 0820, VsNr=59999999999, Bemerkung=Hier die Bemerkung D1
        assertEquals("SatzTyp stimmt nicht", SatzTyp.of("0820"), datensatz3.getSatzTyp());
        assertEquals("VsNr stimmt nicht", "59999999999", datensatz3.getVersicherungsscheinNummer());
        assertEquals("Bemerkung stimmt nicht", "Hier die Bemerkung D1", datensatz3.getFeld("Bemerkung").getInhalt().trim());

        // prüfe Datensatz 6: 0820, VsNr=59999999998, Bemerkung=Hier die Bemerkung D2
        assertEquals("SatzTyp stimmt nicht", SatzTyp.of("0820"), datensatz6.getSatzTyp());
        assertEquals("VsNr stimmt nicht", "59999999998", datensatz6.getVersicherungsscheinNummer());
        assertEquals("Bemerkung stimmt nicht", "Hier die Bemerkung D2", datensatz6.getFeld("Bemerkung").getInhalt().trim());

        // pack Methode muss funktionieren
        try {
            datenpaket.pack();
        } catch (NotRegisteredException e) {
            fail("Pack-Methode hat nicht funktioniert, da der Satz nicht als registriert erkannt wurde.");
        }

        // deregistrieren
        SatzRegistry.getInstance().unregister(SatzTyp.of("0820"));
    }
}
