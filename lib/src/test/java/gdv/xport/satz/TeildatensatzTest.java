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
 * (c)reated 14.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import gdv.xport.feld.*;
import gdv.xport.satz.feld.Feld100;
import gdv.xport.satz.feld.common.VertragsStatus;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzRegistry;
import gdv.xport.util.SatzTyp;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Test-Klasse fuer {@link Teildatensatz}.
 *
 * @author oliver
 * @since 14.10.2009
 */
public class TeildatensatzTest extends AbstractSatzTest {

    private static final Logger LOG = LogManager.getLogger(TeildatensatzTest.class);
    private static final SatzRegistry SATZ_REGISTRY = SatzRegistry.getInstance();

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Teildatensatz(SatzTyp.of(123, 1));
    }

    /**
     * Die einzelnen Felder sollten in der Reihenfolge der Byte-Adresse
     * geliefert werden.
     */
    @Test
    public void testGetFelder() {
        Teildatensatz teildatensatz = new Vorsatz().getTeildatensatz(1);
        Iterator<Feld> iterator = teildatensatz.getFelder().iterator();
        Feld prev = iterator.next();
        while(iterator.hasNext()) {
            Feld next = iterator.next();
            LOG.info("Feld: {}", next);
            assertTrue("wrong sorted: " + prev + " > " + next, prev.getByteAdresse() < next
                    .getByteAdresse());
            prev = next;
        }
    }

    /**
     * Test-Methode fuer {@link Teildatensatz#hasFeld(Enum)}.
     */
    @Test
    public void testHasFeld() {
        Teildatensatz tds = new Teildatensatz(new NumFeld("Feld42", "0042"), 1);
        tds.add(new NumFeld(VertragsStatus.AUSSCHLUSS));
        assertFalse("unexpected: VERTRAGSSTATUS in " + tds, tds.hasFeld(VertragsStatus.VERTRAGSSTATUS));
        assertTrue("expected: AUSSCHLUSS in " + tds, tds.hasFeld(VertragsStatus.AUSSCHLUSS));
    }

    /**
     * Test-Methode fuer {@link Teildatensatz#getFeld(String)}.
     */
    @Test
    public void testGetFeldString() {
        Teildatensatz tds = new Teildatensatz(100, 1);
        Feld feld = new NumFeld("Hello", 55, "World");
        tds.add(feld);
        assertEquals(feld, tds.getFeld("Hello"));
    }

    @Test
    public void testGetFeldByteAdresse() {
        Teildatensatz tds = new Teildatensatz(4711, 1);
        ByteAdresse adresse = ByteAdresse.of(11);
        Feld feld = new NumFeld(Bezeichner.PRODUKTNAME, 47, adresse.intValue() );
        tds.add(feld);
        assertEquals(feld, tds.getFeld(adresse));
    }

    /**
     * Bei der internen Umstellung des {@link Teildatensatz}es auf die
     * erweiterte {@link Bezeichner}-Klasse gab es Probleme mit dem Loeschen
     * von Feldern.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemove() {
        Teildatensatz tds = new Teildatensatz(100, 1);
        Zeichen satznummer = new Zeichen(new Bezeichner("Satznummer"), 256);
        satznummer.setInhalt('1');
        tds.add(satznummer);
        assertEquals(satznummer, tds.getFeld(satznummer.getBezeichnung()));
        tds.remove(satznummer.getBezeichnung());
        try {
            tds.getFeld(satznummer.getBezeichnung());
            fail("IllegalArgumentException bei fehlendem Feld erwartet");
        } catch (IllegalArgumentException ex) {
            MatcherAssert.assertThat("Exception sollte Bezeichner und Satzart beschreiben", ex.getMessage(),
                    allOf(containsString("Satznummer"), containsString("Satzart 0100")));
            throw ex;
        }
    }
    
    /**
     * Bei der internen Umstellung des {@link Teildatensatz}es auf die
     * erweiterte {@link Bezeichner}-Klasse gab es Probleme mit dem Loeschen
     * von Feldern.
     */
    @Test
    public void testRemoveSafe() {
        Teildatensatz tds = new Teildatensatz(100, 1);
        Zeichen satznummer = new Zeichen(new Bezeichner("Satznummer"), 256);
        satznummer.setInhalt('1');
        tds.add(satznummer);
        assertEquals(satznummer, tds.getFeld(satznummer.getBezeichner()));
        tds.remove(satznummer.getBezeichnung());
        assertFalse(tds.hasFeld(satznummer.getBezeichner()));
    }

    /**
     * Hier testen wir, ob mit dem CopyConstructor
     * {@link Teildatensatz#Teildatensatz(Teildatensatz)} tatsaechlich eine
     * Kopie angelegt wird.
     */
    @Test
    public void testCopyConstructor() {
        Teildatensatz orig = new Teildatensatz(100, 1);
        Feld name1 = new AlphaNumFeld(Feld100.NAME1);
        name1.setInhalt("Mickey");
        orig.add(name1);
        Teildatensatz copy = new Teildatensatz(orig);
        assertEqualsFeld(orig.getFeld(Bezeichner.NAME1), copy.getFeld(Bezeichner.NAME1));
        assertEquals(orig, copy);
        copy.setFeld(Bezeichner.NAME1, "Goofy");
        assertEquals("Goofy", copy.getFeld(Bezeichner.NAME1).getInhalt().trim());
        assertEquals("Mickey", orig.getFeld(Bezeichner.NAME1).getInhalt().trim());
    }

    private static void assertEqualsFeld(final Feld one, final Feld two) {
        assertEquals(one, two);
        assertEquals(one.getClass(), two.getClass());
    }

    /**
     * Mit der Validierung sollten auch fehlerhafte IBANs erkannt werden.
     */
    @Test
    public void testValidateIBAN() {
        Teildatensatz adressteil4 = SatzFactory.getSatz(SatzTyp.of("0100")).getTeildatensatz(4);
        assertTrue("should be valid: " + adressteil4, adressteil4.isValid());
        Feld iban1 = adressteil4.getFeld(Bezeichner.IBAN1);
        iban1.setInhalt("DE99300606010006605605");
        List<ConstraintViolation> violations = adressteil4.validate();
        assertEquals(1, violations.size());
    }

    /**
     * Falls versucht wird, ueberlappende Felder hinzuzufuegen, sollte das
     * zurueckgewiesen werden.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddOverlapping() {
        Teildatensatz tds = new Teildatensatz(4711, 1);
        tds.add(new AlphaNumFeld(Bezeichner.NAME1, 10, 100));
        tds.add(new AlphaNumFeld(Bezeichner.NAME2, 10, 101));
    }

    @Test
    public void testGetSatznummer() {
        Datensatz satz220 = SATZ_REGISTRY.getDatensatz(SatzTyp.of("0220.010.13.1"));
        for (int n = 1; n < satz220.getNumberOfTeildatensaetze(); n++) {
            checkSatznummer(satz220, n, ByteAdresse.of(256), Character.forDigit(n, 10));
        }
    }

    @Test
    public void testGetSatznummer220Fahrzeughaftpflicht() {
        Datensatz satz220 = SATZ_REGISTRY.getDatensatz(SatzTyp.of("0220.051"));
        checkSatznummer(satz220, 1, ByteAdresse.of(256), '1');
        checkSatznummer(satz220, 2, ByteAdresse.of(256), '2');
    }

    @Test
    public void testGetSatznummer220Wagnisdaten() {
        Datensatz satz220 = SATZ_REGISTRY.getDatensatz(SatzTyp.of("0220.030"));
        checkSatznummer(satz220, 1, ByteAdresse.of(49), '1');
        checkSatznummer(satz220, 2, ByteAdresse.of(49), '2');
        checkSatznummer(satz220, 3, ByteAdresse.of(43), '3');
        checkSatznummer(satz220, 4, ByteAdresse.of(49), '4');
        checkSatznummer(satz220, 5, ByteAdresse.of(60), '9');
    }

    private void checkSatznummer(Datensatz satz, int n, ByteAdresse start, char inhalt) {
        Teildatensatz tds = satz.getTeildatensatz(n);
        Zeichen satznummer = tds.getSatznummer();
        Zeichen expected = new Zeichen(satznummer.getBezeichner(), start.intValue(), inhalt);
        assertEquals(expected, satznummer);
    }

    @Test
    public void testGetSatznummerInvalid() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of(123), 1);
        Zeichen nr = new Zeichen(Bezeichner.SATZNUMMER, 222, '0');
        new Satznummer(nr);
        tds.add(nr);
        assertEquals(1, tds.getSatznummer().toInt());
        assertEquals(222, tds.getSatznummer().getByteAdresse());
    }

    @Test
    public void testCopyCtor() {
        Teildatensatz tds = new Teildatensatz(SatzTyp.of(800), 1);
        Teildatensatz copy = new Teildatensatz(tds);
        assertEquals(tds, copy);
        checkSetSatzart(tds);
        LOG.info("Original wude geaendert: {}", tds);
        checkSetSatzart(copy);
        LOG.info("Kopie wude geaendert: {}", tds);
    }

    private static void checkSetSatzart(Teildatensatz tds) {
        Feld f1 = tds.getFeld(Bezeichner.SATZART);
        Feld f2 = tds.getFeld(ByteAdresse.of(1));
        assertEquals(f1, f2);
        f2.setInhalt("0888");
        assertEquals(f1, f2);
    }

}
