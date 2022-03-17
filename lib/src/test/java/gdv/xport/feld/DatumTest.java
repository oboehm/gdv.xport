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
 * (c)reated 24.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import gdv.xport.config.Config;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testklasse fuer Datum-Klasse.
 *
 * @author oliver
 * @since 24.10.2009
 * @version $Revision$
 */
public final class DatumTest extends AbstractFeldTest {

    private static final Logger LOG = LogManager.getLogger();

    /* (non-Javadoc)
     * @see gdv.xport.feld.AbstractFeldTest#getTestFeld()
     */
    @Override
    protected Feld getTestFeld() {
        return Datum.heute();
    }

    /**
     * Test method for {@link gdv.xport.feld.Datum#setInhalt(java.util.Date)}.
     */
    @Test
    public void testSetInhaltDate() {
        Date today = new Date();
        Datum datum = Datum.heute();
        datum.setInhalt(today);
        assertEquals(LocalDate.now(), datum.toLocalDate());
    }

    /**
     * Test to date.
     */
    @Test(expected = IllegalStateException.class)
    public void testToDate() {
        Datum silvester = new Datum("Silvester", "31122009");
        LOG.info("Silvester is at " + silvester.toDate());
        Datum invalid = new Datum("invalid", "xxxxxxxx");
        LOG.info("invalid date: " + invalid.toDate());
    }

    @Test
    public void testToLocalDate() {
        Datum valentinstag2021 = new Datum("Valentinstag", "14022021");
        assertEquals(LocalDate.of(2021, 2, 14), valentinstag2021.toLocalDate());
    }

    /**
     * Test is valid.
     */
    @Test
    public void testIsValid() {
        Datum xmas = new Datum("Xmas", "24122009");
        assertTrue(xmas + " should be a valid date", xmas.isValid());
        assertEquals(0, xmas.validate().size());
    }

    /**
     * Test validate empty datum.
     */
    @Test
    public void testValidateEmptyDatum() {
        Datum empty = new Datum(Bezeichner.of("empty"), 8, 1);
        assertTrue(empty + " should be valid", empty.isValid());
    }

    /**
     * Test is invalid.
     */
    @Test
    public void testIsInvalid() {
        checkInvalidDatum("xxxxxxxx");
    }

    /**
     * Test invalid datum.
     */
    @Test
    public void testInvalidDatum() {
        checkInvalidDatum("29022009");
    }

    /**
     * Check invalid datum.
     *
     * @param mmddjjjj the mmddjjjj
     */
    private void checkInvalidDatum(final String mmddjjjj) {
        Datum datum = new Datum("Test-Datum", mmddjjjj);
        assertTrue(datum + " is not a valid date!", datum.isInvalid());
        List<ConstraintViolation> violations = datum.validate();
        for (ConstraintViolation violation : violations) {
            LOG.info(violation);
        }
        assertThat(violations.size(), greaterThan(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInhaltStrict() {
        Datum datumTest = new Datum(Bezeichner.DATUM_SEPA, 3).mitConfig(Config.STRICT.withProperty("gdv.feld.truncate", "true"));
        datumTest.setInhalt("00011800");
    }

    @Test
    public void testIsInvalidLax() {
        Datum datumTest = new Datum(Bezeichner.DATUM_SEPA, 3).mitConfig(Config.LAX);
        datumTest.setInhalt("33011800");
        assertTrue(datumTest.isInvalid());
    }

    /**
     * Wenn ein Datum nur aus 6 Zeichen besteht, hat es laut
     * Broschuere Vermittler das Format MMJJJJ.
     *
     * @since 0.2
     */
    @Test
    public void testDatumMMJJJJ() {
        checkDatum("112009", true);
    }

    /**
     * Test datum mmjj.
     */
    @Test
    public void testDatumMMJJ() {
        checkDatum("1109", true);
    }

    /**
     * Test datum tt.
     */
    @Test
    public void testDatumTT() {
        checkDatum("30", true);
    }

    /**
     * Bei Datumsangaben ist bei Tag und Monat auch die Eingabe "00" zwar
     * moeglich, aber ab 6.2 nicht mehr guetig.
     *
     * @since 0.2
     */
    @Test
    public void testDatum00() {
        checkDatum("00", true);
        checkDatum("002009", false);
        checkDatum("00112009", false);
        checkDatum("00002009", false);
    }

    /**
     * Check datum.
     *
     * @param inhalt the inhalt
     */
    private static void checkDatum(final String inhalt, final boolean valid) {
        Datum datum = new Datum("Test-Datum", inhalt).mitConfig(Config.LAX);
        assertEquals(inhalt, datum.getInhalt());
        assertEquals(datum + " gueltig?", valid, datum.isValid());
    }

    /**
     * Test is empty.
     */
    @Test
    public void testIsEmpty() {
        Datum empty = new Datum("empty", "00000000");
        assertTrue(empty + " is empty", empty.isEmpty());
    }

    /**
     * Test-Methode fuer {@link Datum#format()}.
     */
    @Test
    public void testFormatTTMMJJJJ() {
        checkDatumFormat("20012011", "20.01.2011");
    }

    /**
     * Test-Methode fuer {@link Datum#format()}.
     */
    @Test
    public void testFormatMMJJJJ() {
        checkDatumFormat("012011", "01.2011");
    }

    /**
     * Test-Methode fuer {@link Datum#format()}.
     */
    @Test
    public void testFormatMMJJ() {
        checkDatumFormat("0111", "01.11");
    }

    /**
     * Test-Methode fuer {@link Datum#format()}.
     */
    @Test
    public void testFormatTT() {
        checkDatumFormat("20", "20");
    }

    private static void checkDatumFormat(final String inhalt, final String expected) {
        Datum datum = new Datum("Test-Datum", inhalt);
        assertEquals(expected, datum.format());
    }

    @Test
    public void testAddOne() {
        Datum d = new Datum("Test-Datum", "31032021");
        Datum e = new Datum(d);
        BigDecimal added = d.add(BigDecimal.ONE);
        assertEquals("01042021", d.getInhalt());
        e.setInhalt(added);
        assertEquals(d, e);
    }

    @Test
    public void testConfig() {
        Datum d = new Datum(Bezeichner.DATUM_SEPA, 1);
        Datum strict = d.mitConfig(Config.STRICT);
        d.setInhalt("a");
        assertFalse(d.isValid());
        assertThrows(IllegalArgumentException.class, () -> strict.setInhalt("b"));
    }

}
