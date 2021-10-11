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
 * (c)reated 04.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Datum;
import gdv.xport.feld.Feld;
import gdv.xport.feld.Version;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzRegistry;
import gdv.xport.util.SatzTyp;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author oliver
 * @since 04.10.2009
 * @version $Revision$
 *
 */
public final class VorsatzTest extends AbstractSatzTest {

    private final Vorsatz vorsatz = new Vorsatz();

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Vorsatz();
    }

    /**
     * Test method for {@link gdv.xport.satz.Vorsatz#Vorsatz()}.
     *
     * @throws IOException falls der Export schief geht
     */
    @Test
    public void testVorsatz() throws IOException {
        String expected = "0001" + Config.getVUNummer().getInhalt();
        checkExport(1, 9, expected);
        checkExport(257, 265, expected);
        checkExport(256+246, 256+256, "          2");
        checkExport(768, 768, "3");
    }

    /**
     * Wird das Absender-Feld richtig gesetzt? Schau 'mer mal.
     *
     * @throws IOException falls der Export schief geht
     */
    @Test
    public void testSetAbsender() throws IOException {
        String absender = "agentes AG                    ";
        vorsatz.setAbsender(absender.trim());
        Feld absenderFeld = vorsatz.getFeld(Bezeichner.ABSENDER);
        assertEquals(absenderFeld.getInhalt().trim(), vorsatz.getAbsender());
        checkExport(10, 39, absender);
    }

    /**
     * Hier wird das Start- und End-Datum ueberprueft.
     *
     * @throws IOException falls der Export schief geht
     */
    @Test
    public void testSetErstellungsZeitraum() throws IOException {
        String startDatum = "01011900";
        String endDatum = "09102009";
        vorsatz.setErstellungsZeitraum(startDatum, endDatum);
        checkExport(70, 85, startDatum + endDatum);
        assertEquals(startDatum + endDatum, vorsatz.getErstellungsZeitraum());
        assertEquals(startDatum, vorsatz.get(Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_VOM));
        assertEquals(endDatum, vorsatz.get(Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_BIS));
    }

    @Test
    public void testGetFeldErstellungsdat() {
        Datum von = new Datum(Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_VOM, 8, 70);
        Datum bis = new Datum(Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_BIS, 8, 78);
        von.setInhalt("20201220");
        bis.setInhalt("20201222");
        vorsatz.setErstellungsZeitraum(von, bis);
        assertEquals(von, vorsatz.getFeld(Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_VOM));
        assertEquals(bis, vorsatz.getFeld(Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_BIS));
    }

    /**
     * Hier ueberpruefen wir den Export.
     * Damit ein Datensatz auch 256 Bytes lang ist, setzen wir das
     * EOD-Zeichen auf nichts ("").
     *
     * @param startByte beginnend bei 1
     * @param endByte   beginnend bei 1
     * @param expected erwartetes Ergebnis
     * @throws IOException falls was schief geht
     */
    private void checkExport(final int startByte, final int endByte, final String expected) throws IOException {
        Config.setEOD("");
        super.checkExport(this.vorsatz, startByte, endByte, expected, 768);
    }

    /**
     * Hier testen wir den Import.
     */
    @Test
    public void testImport() {
        String content = vorsatz.toLongString();
        Vorsatz imported = new Vorsatz(content);
        assertEquals(content, imported.toLongString());
        assertEquals(vorsatz, imported);
    }

    @Test
    public void testImportVersion() throws IOException {
        File musterdatei = new File("src/test/resources/musterdatei_041222.txt");
        vorsatz.importFrom(musterdatei);
        Map<SatzTyp, Version> versionen = vorsatz.getSatzartVersionen();
        assertEquals("1.9", versionen.get(SatzTyp.of("0001")).getInhalt());
        assertEquals("1.9", versionen.get(SatzTyp.of("0100")).getInhalt());
        assertEquals("1.9", versionen.get(SatzTyp.of("0200")).getInhalt());
        assertEquals("2.1", versionen.get(SatzTyp.of("0210.050")).getInhalt());
    }

    /**
     * Zum Testen verwenden wir hier die Musterdatei.
     *
     * @throws IOException falls die Musterdatei nicht importiert werden kann
     */
    @Test
    public void testImportReader() throws IOException {
        try (InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt")) {
            vorsatz.importFrom(istream);
            assertTrue(vorsatz + " should be valid", vorsatz.isValid());
            assertEquals("9999", vorsatz.getVuNummer());
            assertEquals("XXX Versicherung AG", vorsatz.getAbsender());
            assertEquals("BRBRIENNEE,J\u00dcRGEN", vorsatz.getAdressat());
        }
    }

    /**
     * Zum Testen nehmen wir hier den Vorsatz aus der Musterdatei, allerdings
     * ohne Umlaute.
     *
     * @throws IOException falls der Im- oder Export schief geht
     */
    @Test
    public void testExport() throws IOException {
        String input = "00019999 XXX Versicherung AG           BRBRIENNEE,JURGEN        "
            + "     220720042207200499990099991.91.91.92.12.12.12.12.1   1.51.3"
            + "1.62.0      1.51.4                                              "
            + "                                1.1         1 0000     Z0ZAG0011"
            + "\n"
            + "00019999 XXX Versicherung AG           BRBRIENNEE,JURGEN        "
            + "     220720042207200499990099991.01.01.01.01.0      1.01.01.1   "
            + "   1.01.0                                                       "
            + "                                                       Z0ZAG0022"
            + "\n";
        vorsatz.importFrom(input);
        StringWriter swriter = new StringWriter(input.length());
        Config.setEOD("\n");
        vorsatz.export(swriter);
        swriter.close();
        assertEquals(input, swriter.toString());
    }

    /**
     * Test-Methode fuer {@link Vorsatz#setAdressat(String)}.
     */
    @Test
    public void testSetAdressat() {
        String adressat = "Obelix";
        vorsatz.setAdressat(adressat);
        assertEquals(adressat, vorsatz.getAdressat());
    }

    @Test
    public void testSetVersion() {
        SatzTyp satzTyp = SatzTyp.of("0100");
        vorsatz.setVersion(satzTyp);
        String expected = SatzFactory.getDatensatz(satzTyp).getSatzversion().getInhalt();
        assertEquals(expected, vorsatz.getVersion(100));
    }

    @Test
    public void testSetVersionSatzartSparte() {
        SatzTyp satzTyp = SatzTyp.of("0210.050");
        vorsatz.setVersion(satzTyp);
        String expected = SatzFactory.getDatensatz(satzTyp).getSatzversion().getInhalt();
        assertEquals(expected, vorsatz.getVersion(210, 50));
    }

    @Test
    public void testSetVersionVorsatz() {
        assertNotNull(vorsatz.getVersion(Bezeichner.VERSION_SATZART_0001));
    }

    @Test
    public void testSetVersionNachsatz() {
        assertNotNull(vorsatz.getVersion(Bezeichner.VERSION_SATZART_9999));
    }

    @Test
    public void testSetVersion100() {
        vorsatz.setVersion(Bezeichner.VERSION_SATZART_0100, "2.1");
        assertEquals("2.1", vorsatz.getVersion(100));
    }

    @Test
    public void testSetVersionString() {
        vorsatz.setVersion(Bezeichner.VERSION_SATZART_0102, "1.2");
        assertEquals("1.2", vorsatz.getVersion(102));
    }

    @Test
    public void testSetVersionSatzart() {
        vorsatz.setVersion(Bezeichner.SATZART_0200, "2.0");
        assertEquals("2.0", vorsatz.getVersion(200));
    }

    @Test
    public void testSetVersionSaztartSparte() {
        vorsatz.setVersion(210, 50,"2.5");
        assertEquals("2.5", vorsatz.getVersion(210, 50));
    }

    @Test
    public void testBezeichner() {
        assertNotNull(vorsatz.get(Bezeichner.ABSENDER));
        assertNotNull(vorsatz.get(Bezeichner.ADRESSAT));
        assertNotNull(vorsatz.get(Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_VOM));
        assertNotNull(vorsatz.get(Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_BIS));
        assertNotNull(vorsatz.get(Bezeichner.VERMITTLER));
    }

    @Test
    public void testImportVersionPreferSparte() throws IOException {
        Datenpaket dp = SatzRegistry.getInstance("VUVM2018.xml").getAllSupportedSaetze();
        for (Satz satz : dp.getAllSaetze()) {
            AbstractSatzTest.setUp(satz);
        }
        File exportFile = new File("target/export/testVersionenHashMapPreferSpalte.txt");
        dp.export(exportFile);
        vorsatz.importFrom(exportFile);
        Map<SatzTyp, Version> versionen = vorsatz.getSatzartVersionen();
        assertEquals("2.4", versionen.get(SatzTyp.of(1)).getInhalt());
        assertEquals("2.4", versionen.get(SatzTyp.of(220, 30)).getInhalt());
        assertEquals("1.7", versionen.get(SatzTyp.of(210, 190)).getInhalt());
        assertEquals("1.3", versionen.get(SatzTyp.of(220, 0)).getInhalt());
        assertEquals("1.5", versionen.get(SatzTyp.of(220, 80)).getInhalt());
        assertEquals("1.5", versionen.get(SatzTyp.of(220, 81)).getInhalt());
        assertEquals("1.3", versionen.get(SatzTyp.of(220, 295)).getInhalt());
    }

    @Test
    public void testSetVermittler() {
        vorsatz.setVermittler("12345");
        assertEquals("12345", vorsatz.getVermittler());
        assertEquals("12345", vorsatz.getFeld(Bezeichner.VERMITTLER).getInhalt().trim());
    }

}
