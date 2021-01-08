/*
 * Copyright (c) 2009 - 2019 by Oli B.
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
 * (c)reated 05.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import gdv.xport.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * JUnit-Test fuer Nachsatz.
 *
 * @author oliver
 * @since 05.10.2009
 */
public class NachsatzTest extends AbstractSatzTest {

    private static final Logger LOG = LogManager.getLogger(NachsatzTest.class);
    private final Nachsatz nachsatz = new Nachsatz();
    /** aus musterdatei_041222.txt */
    private static final String INPUT
            = "99990000000162999900999900000000048060000000000000000+0000000000"
            + "0000+00000000000000+00000000000000+                             "
            + "                                                                "
            + "                                                       Z0ZAG999 "
            + "\n";

    /**
     * Einige Tests erwarten das "\n" am Ende des Nachsatzes.
     */
    @Before
    public void setUpEOD() {
        Config.setEOD("\n");
    }

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Nachsatz();
    }

    /**
     * Test method for {@link gdv.xport.satz.Nachsatz#Nachsatz()}.
     */
    @Test
    public void testNachsatz() {
        assertEquals(9999, nachsatz.getSatzart());
    }

    /**
     * Test method for {@link gdv.xport.satz.Satz#export(java.io.Writer)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testExport() throws IOException {
        checkExport(1, 19, "99990000000000     ");
        checkExport(246, 255, "          ");
        LOG.info(nachsatz);
    }

    /**
     * Check export.
     *
     * @param startByte beginnend bei 1
     * @param endByte   beginnend bei 1
     * @param expected the expected result
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void checkExport(final int startByte, final int endByte, final String expected) throws IOException {
        super.checkExport(this.nachsatz, startByte, endByte, expected, 256);
    }

    /**
     * Test-Methode fuer {@link Nachsatz#setAnzahlSaetze(int)}.
     */
    @Test
    public void testSetAnzahl() {
        nachsatz.setAnzahlSaetze(42);
        assertEquals(42, nachsatz.getAnzahlSaetze());
    }

    /**
     * Statt den Leerzeichen am Ende des Nachsatzes ist auch eine freie Belegung
     * moeglich. Diese soll natuerlich beim Import erhalten bleiben. Der
     * Test-Input dazu stammt von der musterdatei_041222.txt von gdv-online.
     *
     * @throws IOException sollte eigentlich nicht vorkommen, da wir von einem
     *             String importieren
     * @since 0.5.0
     */
    @Test
    public void testImport() throws IOException {
        assertEquals(257, INPUT.length());
        nachsatz.importFrom(INPUT);
        checkImport();
    }

    /**
     * Hier testen wir den Import von einem Reader.
     *
     * @throws IOException sollte eigentlich nicht vorkommen, da wir von einem
     *             String importieren
     * @since 0.9.2
     */
    @Test
    public void testImportFromReader() throws IOException {
        Reader reader = new StringReader(INPUT);
        nachsatz.importFrom(reader);
        checkImport();
    }

    /**
     * Der Import des Nachsatzes sollte auch mehrfach moeglich sein.
     *
     * @throws IOException sollte eigentlich nicht vorkommen, da wir von einem
     *             String importieren
     * @since 0.9.2
     */
    @Test
    public void testImportFromReaderTwice() throws IOException {
        Reader reader = new StringReader(INPUT + INPUT);
        nachsatz.importFrom(reader);
        checkImport();
        nachsatz.importFrom(reader);
        checkImport();
    }

    private void checkImport() throws IOException {
        StringWriter swriter = new StringWriter(INPUT.length());
        nachsatz.export(swriter);
        swriter.close();
        assertEquals(INPUT, swriter.toString());
    }

    /**
     * Testfall fuer
     * <a href="https://github.com/oboehm/gdv.xport/issues/34">Issue #34</a>.
     */
    @Test
    public void testSetGesamtBeitragBrutto() {
        nachsatz.setGesamtBeitragBruttoMitVorzeichen(BigDecimal.valueOf(47.11));
        assertEquals(47.11, nachsatz.getGesamtBeitragBruttoMitVorzeichen().toDouble(), 0.0001);
    }

    @Test
    public void testAddProvisonBetrag() {
        nachsatz.setGesamtProvisionsBetragMitVorzeichen(BigDecimal.valueOf(2.50));
        nachsatz.addGesamtProvisionsBetrag(BigDecimal.valueOf(-2.55));
        assertEquals(-0.05, nachsatz.getGesamtProvisionsBetragMitVorzeichen().toDouble(), 0.0001);
    }

    @Test
    public void testAddVersicherungsleistungen() {
        nachsatz.setVersicherungsLeistungenMitVorzeichen(BigDecimal.valueOf(12.50));
        nachsatz.addVersicherungsLeistungen(BigDecimal.valueOf(0.55));
        assertEquals(13.05, nachsatz.getVersicherungsLeistungenMitVorzeichen().toDouble(), 0.0001);
    }

    @Test
    public void testAddGesamtBeitrag() {
        nachsatz.setGesamtBeitrag("12340");
        BigDecimal betrag = nachsatz.addGesamtBeitrag(new BigDecimal("0.05"));
        assertEquals(new BigDecimal("123.45"), betrag);
        assertEquals(betrag, nachsatz.getGesamtBeitrag().toBigDecimal());
    }

    @Test
    public void testAddGesamtBeitragBrutto() {
        nachsatz.setGesamtBeitragBruttoMitVorzeichen(BigDecimal.valueOf(-10.50));
        BigDecimal betrag = nachsatz.addGesamtBeitragBrutto(BigDecimal.valueOf(11));
        assertEquals(new BigDecimal("0.50"), betrag);
        assertEquals(betrag, nachsatz.getGesamtBeitragBruttoMitVorzeichen().toBigDecimal());
    }

    @Test
    public void testAddSchadenbarbeitung() {
        nachsatz.setSchadenbearbeitungskostenMitVorzeichen(BigDecimal.valueOf(100));
        nachsatz.addSchadenbearbeitungskosten(BigDecimal.valueOf(23.45));
        assertEquals(123.45, nachsatz.getSchadenbearbeitungskostenMitVorzeichen().toDouble(), 0.0001);
    }

    @Test
    public void testCopyCtor() {
        Nachsatz copy = new Nachsatz(nachsatz);
        assertEquals(nachsatz, copy);
    }

}
