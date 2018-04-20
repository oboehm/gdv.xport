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

import gdv.xport.Datenpaket;
import gdv.xport.annotation.FeldInfo;
import gdv.xport.demo.MyFeld210;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.feld.NumFeld;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.Vorsatz;
import gdv.xport.satz.model.SatzX;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import patterntesting.runtime.junit.SmokeRunner;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * JUnit-Test fuer SatzFactory.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.1.0 (30.10.2009)
 */
@RunWith(SmokeRunner.class)
public final class SatzFactoryTest extends AbstractTest {

    private static final Logger LOG = LogManager.getLogger(SatzFactoryTest.class);

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
        Satz satz = getSatz(1);
        assertNotNull(satz.getFeld(Bezeichner.VERSION_SATZART_0100));
    }

    /**
     * Satz 0342 ("Begleitdokumente und Signaturen") ist nur als
     * XML-Beschreibung vorhanden. Daher wird dieser Satz zum Testen verwendet.
     */
    @Test
    public void testGetSatz342() {
        Satz satz342 = getSatz(342);
        Feld dokumenttyp = satz342.getFeld("Dokumenttyp");
        assertEquals(2, dokumenttyp.getAnzahlBytes());
        assertEquals(46, dokumenttyp.getByteAdresse());
    }

    private static Satz getSatz(final int satzart) {
        Satz satz = SatzFactory.getSatz(satzart);
        assertEquals(satzart, satz.getSatzart());
        return satz;
    }

    /**
     * Testet getSatz() fuer eine Satzart, die (noch) nicht unterstuetzt wird.
     */
    @Test
    public void testGetUnsupportedSatz() {
        Datensatz unsupported = new Datensatz("0123");
        unsupported.setVuNummer("56789");
        unsupported.setSparte(88);
        unsupported.add(new NumFeld(new Bezeichner("zweiundvierzig"), 4, 200, 42));
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
        try {
            satz = SatzFactory.getSatz(47);
            fail("unregister failed for " + satz);
        } catch (NotRegisteredException expected) {
            LOG.info(satz + " successful unregistered (" + expected + ")");
        }
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

    private void assertSatzart47(final Satz satz) {
        assertEquals(47, satz.getSatzart());
        Feld x = satz.getFeld(MyFeld210.BAUJAHR);
        assertNotNull(x);
    }

    /**
     * Damit wird ueberprueft, ob Satzart 100 (Adressteil) bei der SatzFactory richtig registriert ist.
     */
    @Test
    public void testGetAdressteil() {
        checkGetDatensatz(100);
    }

    /**
     * Damit wird ueberprueft, ob die Satzart 200 (AllgemeinerVertragsteil oder Satz200) bei der SatzFactory registriert
     * ist.
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
     * Damit wird ueberprueft, ob Satzart 220 mit Sparte 70 registriert ist.
     */
    @Test
    public void testGetSpartenspezifischerTeil70() {
        checkGetDatensatz(220, 70, gdv.xport.satz.feld.sparte70.Feld220.values());
    }

    /**
     * Damit wird ueberprueft, ob Satzart 210 mit Sparte 70 registriert ist.
     */
    @Test
    public void testGetVertragsspezifischerTeil70() {
        checkGetDatensatz(210, 70, gdv.xport.satz.feld.sparte70.Feld210.values());
    }

    /**
     * Damit wird ueberprueft, ob Satzart 221 mit Sparte 30 registriert ist.
     */
    @Test
    public void testGetErweiterungssatz30() {
        checkGetDatensatz(221, 30, gdv.xport.satz.feld.sparte30.Feld221.values(), "2");
    }

    /**
     * Test von Satzart 210.
     * <p>
     * Nach eine Hinweis von Frank Berger wird jetzt auch die Sparte 130
     * ueberprueft, da sie vorher gefehlt hatte.
     * </p>
     */
    @Test
    public void testGetSatzart210() {
        checkGetDatensatz(210, 10, gdv.xport.satz.feld.sparte10.Feld210.values(), "1");
        checkGetDatensatz(210, 30, gdv.xport.satz.feld.sparte30.Feld210.values());
        checkGetDatensatz(210, 40, gdv.xport.satz.feld.sparte40.Feld210.values());
        checkGetDatensatz(210, 50, gdv.xport.satz.feld.sparte50.Feld210.values(), "1");
        checkGetDatensatz(210, 70, gdv.xport.satz.feld.sparte70.Feld210.values());
        checkGetDatensatz(210, 130, gdv.xport.satz.feld.sparte130.Feld210.values(), "1");
    }

    /**
     * Falls der Satz vom XmlService kommt, gab es Probleme, dass die
     * allgemeine Satz fuer z.B. Satzart 210 zurueckkam, und nicht der
     * spezielle Satz fuer die entsprechende Sparte.
     */
    @Test
    public void testGetSatzart210Sparte30() {
        Datensatz satz210 = getDatensatz(210, 30);
        Feld vertragsstatus = satz210.getFeld(Bezeichner.VERTRAGSSTATUS);
        assertEquals(43, vertragsstatus.getByteAdresse());
    }

    /**
     * Test von Satzart 220.
     */
    @Test
    public void testGetSatzart220() {
        checkGetDatensatz(220, 10, gdv.xport.satz.feld.sparte10.Feld220Wagnis0.values(), "1");
        checkGetDatensatz(220, 140, gdv.xport.satz.feld.sparte140.Feld220.values(), "1");
    }

    private void checkGetDatensatz(final int satzart, final int sparte, final Enum<?>[] felder, final String satzNr) {
        checkGetDatensatz(satzart, sparte, felder);
        Satz datensatz = getDatensatz(satzart, sparte);
        Feld satznummer = datensatz.getFeld(Bezeichner.SATZNUMMER, 1);
        assertEquals("falsche Satznummer", satzNr, satznummer.getInhalt());
    }

    private void checkGetDatensatz(final int satzart, final int sparte, final Enum<?>[] felder) {
        Satz datensatz = getDatensatz(satzart, sparte);
        for (int i = 0; i < felder.length; i++) {
            Enum<?> feldInfo = felder[i];
            if (feldInfo instanceof FeldInfo) {
                String inhalt = datensatz.get(feldInfo);
                assertNotNull("not found: " + feldInfo, inhalt);
            }
        }
    }

    private static Datensatz getDatensatz(final int satzart, final int sparte) {
        Datensatz datensatz = SatzFactory.getDatensatz(satzart, sparte);
        assertEquals(satzart, datensatz.getSatzart());
        assertEquals(sparte, datensatz.getSparte());
        return datensatz;
    }

    /**
     * Die Daten zu diesem Test stammen aus der Musterdatei.
     *
     * @throws IOException
     *             sollte eigentlich nicht vorkommen
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
        List<Datensatz> datensaetze = all.getDatensaetze();
        Set<Integer> supportedSatzarten = new TreeSet<Integer>();
        for (Datensatz datensatz : datensaetze) {
            supportedSatzarten.add(datensatz.getSatzart());
        }
        int n = datensaetze.size();
        LOG.info(n + " Satzarten supported: " + supportedSatzarten);
        assertTrue("only " + n + " Datensaetze supported", n > 5);
        assertTrue("Satzart 342 expected to be supported", supportedSatzarten.contains(342));
    }

    /**
     * Das Registrieren/Deregistrieren von Enum-Saetzen scheint nicht richtig zu funktionieren. Dies wird mit diesem
     * Test nachgestellt (s. Issue 1).
     *
     * @since 0.6.3
     */
    @Test
    public void testIssue1() {
        checkGetDatensatz(210, 30, gdv.xport.satz.feld.sparte30.Feld210.values());
        try {
            SatzFactory.registerEnum(MyFeld210.class, 210, 30);
            checkGetDatensatz(210, 30, MyFeld210.values());
        } finally {
            SatzFactory.registerEnum(gdv.xport.satz.feld.sparte30.Feld210.class, 210, 30);
        }
    }

    /**
     * Hier testen wir mit Satz fuer die Kfz-Haftpflicht (0221.051), ob keine
     * Loecher im Datensatz sind. Problem bereiteten hier urspruenglich die
     * KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL#-Bezeichner.
     */
    @Test
    public void testSatzart0221051() {
        SatzTyp kfz = new SatzTyp(221, 51);
        Datensatz satz = SatzFactory.getDatensatz(kfz);
        checkDatensatz(satz);
    }

    /**
     * Hier testen wir, ob brav alle Felder ausgefuellt und keine Luecken
     * vorhanden sind. Bei Satzart 250 fehlt noch die Satznummer auf
     * Adresse 51, weswegen der Test diese Satzart (ncoh) ausblendet.
     */
    @Test
    public void testSatzarten() {
        Datenpaket datenpaket = SatzFactory.getAllSupportedSaetze();
        for (Datensatz datensatz : datenpaket.getDatensaetze()) {
            if (datensatz.getSatzart() <= 250) {
                checkDatensatz(datensatz);
            }
        }
    }

    private static void checkDatensatz(Datensatz satz) {
        for (Teildatensatz tds : satz.getTeildatensaetze()) {
            checkTeildatensatz(tds);
        }
    }

    private static void checkTeildatensatz(Teildatensatz tds) {
        int startByte = 1;
        for (Feld feld : tds.getFelder()) {
            assertEquals(tds + ": Feld missing before " + feld.getBezeichner(), startByte, feld.getByteAdresse());
            startByte = feld.getEndAdresse() + 1;
        }
    }

    /**
     * Wenn zweimal der gleiche Satz geholt wird, sollte nicht derselbe
     * zurueckgeliefert werden. Sonst kann es beim Auffuellen des Satzes
     * zu unerwuenschten Wechselwirkungen mit dem ersten Satz kommen.
     */
    @Test
    public void testGetSatzDifferent() {
        Satz one = SatzFactory.getSatz(100);
        Satz two = SatzFactory.getSatz(100);
        assertEquals(one, two);
        assertNotSame(one, two);
        for (int i = 1; i <= one.getNumberOfTeildatensaetze(); i++) {
            assertEquals(one.getTeildatensatz(i), two.getTeildatensatz(i));
            assertNotSame(one.getTeildatensatz(i), two.getTeildatensatz(i));
        }
    }
    
    /**
     * Der gleiche Test wie vorher, nur dass wir hier ncoh einen Schritte
     * tiefer gehen und zwei Felder vergleichen, ob sie nicht dieselben sind.
     */
    @Test
    public void testGetSatzManipulated() {
        Teildatensatz one = SatzFactory.getSatz(100).getTeildatensatz(4);
        Teildatensatz two = SatzFactory.getSatz(100).getTeildatensatz(4);
        assertNotSame(one, two);
        Feld oneIban = one.getFeld(Bezeichner.IBAN1);
        Feld twoIban = two.getFeld(Bezeichner.IBAN1);
        assertNotSame(oneIban, twoIban);
    }

}
