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
 * (c)reated 30.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import static gdv.xport.feld.Bezeichner.SATZNUMMER;
import static org.junit.Assert.*;
import gdv.xport.Datenpaket;
import gdv.xport.demo.MyFeld210;
import gdv.xport.feld.*;
import gdv.xport.satz.*;
import gdv.xport.satz.model.*;

import java.io.IOException;

import org.apache.commons.logging.*;
import org.junit.Test;

/**
 * JUnit-Test fuer SatzFactory.
 * 
 * @author oliver (ob@aosd.de)
 * @since 0.1.0 (30.10.2009)
 */
public final class SatzFactoryTest extends AbstractTest {
    
    private static final Log log = LogFactory.getLog(SatzFactoryTest.class);

    /**
     * Testet getSatz().
     */
    @Test
    public void testGetSatz() {
        Satz vorsatz = new Vorsatz();
        String content = vorsatz.toLongString();
        Satz satz = SatzFactory.getSatz(content);
        assertEquals(content, satz.toLongString());
        assertEquals(Vorsatz.class, satz.getClass());
    }

    /**
     * Testet getSatz().
     */
    @Test
    public void testGetSatzInt() {
        Satz satz = SatzFactory.getSatz(1);
        assertEquals(Vorsatz.class, satz.getClass());
    }

    /**
     * Testet getSatz() fuer eine Satzart, die (noch) nicht unterstuetzt
     * wird.
     */
    @Test
    public void testGetUnsupportedSatz() {
        Datensatz unsupported = new Datensatz("0123");
        unsupported.setVuNummer("56789");
        unsupported.setSparte(88);
        unsupported.add(new NumFeld("zweiundvierzig", 4, 200, 42));
        String content = unsupported.toLongString();
        Satz imported = SatzFactory.getSatz(content);
        assertEquals(content, imported.toLongString());
        assertTrue(imported + " should be valid", imported.isValid());
    }

    /**
     * Testet {@link SatzFactory#register(Class, int)}.
     */
    @Test
    public void testRegisterSatz() {
        SatzFactory.register(Datensatz.class, 47);
        Satz satz = SatzFactory.getSatz(47);
        assertEquals(Datensatz.class, satz.getClass());
        assertEquals(47, satz.getSatzart());
        SatzFactory.unregister(47);
    }

    /**
     * Testet {@link SatzFactory#register(Class, int, int)}.
     */
    @Test
    public void testRegisterDatensatz() {
        SatzFactory.register(SatzX.class, 47, 11);
        Satz satz = SatzFactory.getDatensatz(47, 11);
        assertEquals(SatzX.class, satz.getClass());
        SatzFactory.unregister(47, 11);
    }
    
    /**
     * Testet {@link SatzFactory#registerEnum(Class, int)}.
     */
    @Test
    public void testRegisterEnum() {
        SatzFactory.registerEnum(MyFeld210.class, 47);
        Satz satz = SatzFactory.getSatz(47);
        assertSatzart47(satz);
        SatzFactory.unregister(47);
    }

    /**
     * Testet {@link SatzFactory#registerEnum(Class, int, int)}.
     */
    @Test
    public void testRegisterEnum4Sparte() {
        SatzFactory.registerEnum(MyFeld210.class, 47, 11);
        Satz satz = SatzFactory.getDatensatz(47, 11);
        assertSatzart47(satz);
        SatzFactory.unregister(47, 11);
    }

    private void assertSatzart47(Satz satz) {
        assertEquals(47, satz.getSatzart());
        Feld x = satz.getFeld(MyFeld210.MEINE_WAEHRUNG);
        assertNotNull(x);
    }

    /**
     * Damit wird ueberprueft, ob Satzart 100 (Adressteil) bei der
     * SatzFactory richtig registriert ist.
     */
    @Test
    public void testGetAdressteil() {
        checkGetDatensatz(100);
    }

    /**
     * Damit wird ueberprueft, ob die Satzart 200 (AllgemeinerVertragsteil
     * oder Satz200) bei der SatzFactory registriert ist.
     */
    @Test
    public void testAllgemeinerVertragsteil() {
        checkGetDatensatz(200);
    }
    
    private static void checkGetDatensatz(final int satzart) {
        Satz datensatz = SatzFactory.getDatensatz(satzart);
        assertEquals(satzart, datensatz.getSatzart());
    }

    /**
     * Damit wird ueberprueft, ob Satzart 221 (Erweiterungssatz) bei der
     * SatzFactory registriert ist.
     */
    @Test
    public void testGetErweiterungssatz() {
        checkGetDatensatz(221, Satz221.class);
    }

    private static void checkGetDatensatz(final int satzart, final Class<? extends Datensatz> clazz) {
        Satz datensatz = SatzFactory.getDatensatz(satzart);
        assertEquals(clazz, datensatz.getClass());
        assertEquals(satzart, datensatz.getSatzart());
    }

    /**
     * Damit wird ueberprueft, ob Satzart 220 mit Sparte 70 registriert ist.
     */
    @Test
    public void testGetSpartenspezifischerTeil70() {
        checkGetDatensatz(220, 70, Satz220.class);
    }

    /**
     * Damit wird ueberprueft, ob Satzart 210 mit Sparte 70 registriert ist.
     */
    @Test
    public void testGetVertragsspezifischerTeil70() {
        checkGetDatensatz(210, 70, Satz210.class);
    }

    /**
     * Damit wird ueberprueft, ob Satzart 221 mit Sparte 30 registriert ist.
     */
    @Test
    public void testGetErweiterungssatz30() {
        checkGetDatensatz(221, 30, Satz221.class, "2");
    }
    
    @Test
    public void testGetSatzart210() {
        checkGetDatensatz(210, 10, Satz210.class, "1");
    }

    private static void checkGetDatensatz(final int satzart, final int sparte, final Class<? extends Datensatz> clazz,
            final String satzNr) {
        Satz datensatz = getDatensatz(satzart, sparte);
        assertEquals(clazz, datensatz.getClass());
        Feld satznummer = datensatz.getFeld(SATZNUMMER, 1);
        assertEquals("falsche Satznummer", satzNr, satznummer.getInhalt());
    }

    private static void checkGetDatensatz(final int satzart, final int sparte, final Class<? extends Datensatz> clazz) {
        Satz datensatz = getDatensatz(satzart, sparte);
        assertEquals(clazz, datensatz.getClass());
    }

    private static Satz getDatensatz(final int satzart, final int sparte) {
        Datensatz datensatz = SatzFactory.getDatensatz(satzart, sparte);
        assertEquals(satzart, datensatz.getSatzart());
        assertEquals(sparte, datensatz.getSparte());
        return datensatz;
    }

    /**
     * Die Daten zu diesem Test stammen aus der Musterdatei.
     * 
     * @throws IOException sollte eigentlich nicht vorkommen
     */
    @Test
    public void testImport() throws IOException {
        Datensatz datensatz = SatzFactory.getDatensatz(210, 30);
        String s = "02109999  030      599999999990199990099991010520040105200901052"
                 + "0040901 0000000000000000000 EUR000000000000000000000000000000041"
                 + "1410000000000 0001000                                           "
                 + "           000000                                               ";
        datensatz.importFrom(s);
        assertEquals(1, datensatz.getFolgenummer());
    }
    
    /**
     * Test-Methode fuer {@link SatzFactory#getAllSupportedSaetze()}.
     */
    @Test
    public void testGetAllSupportedSaetze() {
        Datenpaket all = SatzFactory.getAllSupportedSaetze();
        int n = all.getDatensaetze().size();
        log.info(n + " Satzarten supported");
        assertTrue("only " + n + " Datensaetze supported", n > 5);
    }
    
    /**
     * Das Registrieren/Deregistrieren von Enum-Saetzen scheint nicht richtig
     * zu funktionieren. Dies wird mit diesem Test nachgestellt
     * (s. Issue 1).
     * 
     * @since 0.6.3
     */
    @Test
    public void testIssue1() {
        Datensatz satz = SatzFactory.getDatensatz(210, 30);
        assertEquals(Satz210.class, satz.getClass());
        try {
            SatzFactory.registerEnum(MyFeld210.class, 210, 30);
            satz = SatzFactory.getDatensatz(210, 30);
            assertNotSame(Satz210.class, satz.getClass());
        } finally {
            SatzFactory.register(Satz210.class, 210, 30);
        }
    }

}


