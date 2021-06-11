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
 * (c)reated 19.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import gdv.xport.annotation.FelderInfo;
import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.Feld200;
import gdv.xport.satz.feld.MetaFeldInfo;
import gdv.xport.satz.feld.common.Feld1bis7;
import gdv.xport.satz.feld.common.Kopffelder1bis7;
import gdv.xport.satz.feld.sparte10.Feld220Wagnis0;
import gdv.xport.satz.feld.sparte10.wagnisart13.Feld220Wagnis13;
import gdv.xport.satz.feld.sparte10.wagnisart13.Feld221Wagnis13ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld220Wagnis2Wertungssummen;
import gdv.xport.satz.feld.sparte53.Feld220;
import gdv.xport.satz.model.SatzX;
import gdv.xport.satz.xml.XmlService;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzTyp;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import patterntesting.runtime.junit.CollectionTester;
import patterntesting.runtime.junit.ObjectTester;

import java.io.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

/**
 * Test-Klasse fuer Satz.
 *
 * @author oliver
 * @since 19.10.2009
 */
public final class SatzTest extends AbstractSatzTest {

    private static final Logger LOG = LogManager.getLogger(SatzTest.class);
    private static final String INPUT_SATZ_123
            = "0123Hello 007                                                   "
            + "                                                                "
            + "                                                                "
            + "                                                               1"
            + "\n";
    private final Satz satz = new Datensatz(123);

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Datensatz(123);
    }

    /**
     * Ein einfacher Test, der lediglich die Satzart ueberprueft.
     */
    @Test
    public void testSatz() {
        Satz satz100 = new Datensatz(100, 1);
        assertEquals(100, satz100.getSatzart());
    }

    /**
     * Test method for {@link gdv.xport.satz.Satz#add(gdv.xport.feld.Feld)}.
     * Falls ein Feld hinzugefuegt wird, das ein anderes Feld (teilweise)
     * ueberschreiben wuerde, sollte eine Exception geworfen werden.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAdd() {
        satz.add(new AlphaNumFeld((Bezeichner.NAME1), 30, 44));
        satz.add(new AlphaNumFeld(new Bezeichner("Bumm"), 4, 50));
    }

    /**
     * Test method for
     * {@link gdv.xport.satz.Satz#set(java.lang.String, java.lang.String)}. Es
     * kann nur ein Feld gesetzt werden, das vorher ueber "add(..)" hinzugefuegt
     * wurde.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetUndefined() {
        satz.set("gibtsnet", "plopp");
    }

    /**
     * Bei der Umstellung auf "VUVM2018.xml" ist aufgefallen, dass nach der
     * Korrektur der Feldzugriffe die Folgenummer nicht mehr erkannt wurde.
     * Mit diesem Test wurde der Folgefehler reproduziert und korrigiert.
     */
    @Test
    public void testSetEnum() {
        Satz satz100 = SatzFactory.getSatz(SatzTyp.of("0100"));
        satz100.set(Kopffelder1bis7.FOLGENUMMER.getBezeichner(), "13");
        assertEquals("13", satz100.get(Kopffelder1bis7.FOLGENUMMER.getBezeichner()));
    }

    /**
     * Test method for {@link gdv.xport.satz.Satz#get(java.lang.String)}.
     */
    @Test
    public void testGet() {
        satz.add(new AlphaNumFeld((Bezeichner.ORT), 30, 50));
        satz.set(Bezeichner.ORT.getName(), "Stuttgart");
        assertEquals("Stuttgart", satz.get(Bezeichner.ORT.getName()).trim());
    }

    /**
     * Test method for {@link gdv.xport.satz.Satz#getFeld(java.lang.String)}.
     * Fuer ein Feld, das nicht existiert, wird nicht mehr NULL_FELD als
     * Ergebnis erwartet sondern eine IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetFeld() {
        try {
            satz.getFeld("hemmernet");
            fail("IllegalArgumentException bei fehlendem Feld erwartet");
        } catch (IllegalArgumentException ex) {
            assertThat("Exception sollte Bezeichner und Satzart beschreiben", ex.getMessage(),
                    allOf(containsString("Hemmernet"), containsString(" 0123")));
            throw ex;
        }
    }

    @Test
    public void testGetFeldWagnis2() {
        Satz satz = XmlService.getInstance().getSatzart(SatzTyp.of("0220.140"));
        Bezeichner wagnis2 = new Bezeichner("Wagnis", "Wagnis2");
        Feld feld = satz.getFeld(wagnis2);
        assertEquals(wagnis2, feld.getBezeichner());
    }
    
    /**
     * Test method for {@link gdv.xport.satz.Satz#getFeld(java.lang.String)}.
     * Fuer ein Feld, das nicht existiert, wird nicht mehr NULL_FELD als
     * Ergebnis erwartet sondern eine IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetFeldBezeichner() {
        try {
            satz.getFeld(new Bezeichner("hemmernet"));
            fail("IllegalArgumentException bei fehlendem Feld erwartet");
        } catch (IllegalArgumentException ex) {
            assertThat("Exception sollte Bezeichner und Satzart beschreiben", ex.getMessage(),
                    allOf(containsString("Hemmernet"), containsString(" 0123")));
            throw ex;
        }
    }

    @Test
    public void testGetNumFeld() {
        NumFeld satzart = checkGetFeld(Bezeichner.SATZART, NumFeld.class, "0500");
        assertEquals(500, satzart.toInt());
    }

    @Test
    public void testGetBetrag() {
        Betrag betrag = checkGetFeld(Bezeichner.SCHADENBEARBEITUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN, Betrag.class, "512");
        assertEquals(new BigDecimal("5.12"), betrag.toBigDecimal());
    }

    @Test
    public void testGetDatum() {
        Datum datum = checkGetFeld(Bezeichner.SCHADENDATUM, Datum.class, "09012021");
        assertEquals(2021, datum.toLocalDate().getYear());
    }

    @Test
    public void testGetVorzeichen() {
        Zeichen vorzeichen = checkGetFeld(Bezeichner.VORZEICHEN, Zeichen.class, "+");
        assertEquals('+', vorzeichen.toChar());
    }

    private <T extends Feld> T checkGetFeld(Bezeichner bezeichner, Class<T> clazz, String inhalt) {
        Satz satz = SatzFactory.getSatz(SatzTyp.of(500));
        Feld feld = satz.getFeld(bezeichner);
        feld.setInhalt(inhalt);
        T betrag = satz.getFeld(bezeichner, clazz);
        assertEquals(feld.getByteAdresse(), betrag.getByteAdresse());
        assertEquals(feld.getInhalt(), betrag.getInhalt());
        return betrag;
    }

    @Test
    public void testGetBetragMitVorzeichen() {
        Satz satz = SatzFactory.getSatz(SatzTyp.of(500));
        satz.set(Bezeichner.SCHADENBEARBEITUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN, "00000000123");
        satz.getTeildatensatz(1).set(ByteAdresse.of(167), "-");
        BetragMitVorzeichen betrag = satz.getFeld(Bezeichner.SCHADENBEARBEITUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN, BetragMitVorzeichen.class);
        assertEquals(14, betrag.getAnzahlBytes());
        assertEquals('-', betrag.getVorzeichen());
        assertEquals(new BigDecimal("-1.23"), betrag.toBigDecimal());
    }

    /**
     * Test method for {@link gdv.xport.satz.Satz#getFeld(java.lang.String)}.
     * Fuer ein Feld, das nicht existiert, wird nicht mehr NULL_FELD als
     * Ergebnis erwartet sondern eine IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetFeldEnum() {
        try {
            satz.getFeld(Feld221Wagnis13ZukSummenaenderungen.ANFAENGLICHE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN);
            fail("IllegalArgumentException bei fehlendem Feld erwartet");
        } catch (IllegalArgumentException ex) {
            assertThat("Exception sollte Bezeichner und Satzart beschreiben", ex.getMessage(),
                    allOf(containsString("ANFAENGLICHE_ERLEBENSFALL_VS_IN_WAEHRUNGSEINHEITEN"), containsString(" 0123")));
            throw ex;
        }
    }
    
    /**
     * Testfall fuer Issue #12.
     */
    @Test
    public void testGetFeldInhalt() {
        assertEquals("0123", satz.getFeldInhalt(Bezeichner.SATZART));
    }

    /**
     * Ein Export mit einem Teildatensatz sollte aus genau 256 Bytes bestehen,
     * da in der SetUp-Methode das EOD-Zeichen auf "" gesetzt wurde.
     *
     * @throws IOException
     *             sollte nicht auftreten, da wir mit StringWriter arbeiten
     */
    @Test
    public void testExport() throws IOException {
        StringWriter swriter = new StringWriter(256);
        satz.export(swriter, "");
        swriter.close();
        String content = swriter.toString();
        assertEquals(256, content.length());
        assertEquals(satz.getSatzartFeld().getInhalt(), content.substring(0, 4));
    }

    /**
     * Test-Methode fuer {@link Satz#export(File)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testExportFile() throws IOException {
        File tmpFile = File.createTempFile("gdv", ".xport");
        LOG.info("File \"{}\" created.", tmpFile);
        try {
            satz.export(tmpFile);
            String exported = FileUtils.readFileToString(tmpFile, Config.DEFAULT_ENCODING);
            assertEquals(satz.toLongString(), exported);
        } finally {
            delete(tmpFile);
        }
    }

    /**
     * Ein einfach Import-Test.
     *
     * @throws IOException sollte eigenlich nicht passieren, da wir von einem
     *             String lesen
     */
    @Test
    public void testImport() throws IOException {
        Satz x = new Datensatz(SatzTyp.of(123));
        x.add(new AlphaNumFeld(new Bezeichner("F1"), 5, 5));
        x.importFrom(INPUT_SATZ_123);
        assertEquals(123, x.getSatzart());
        assertEquals("Hello", x.getFeld("F1").getInhalt());
        assertEquals(INPUT_SATZ_123.trim(), x.toLongString().trim());
    }

    /**
     * Hier probieren wir jetzt den Import ueber einen Reader.
     *
     * @throws IOException sollte eigenlich nicht passieren, da wir von einem
     *             String lesen
     */
    @Test
    public void testImportFromReader() throws IOException {
        Satz x = new Datensatz(SatzTyp.of(123, 7));
        try (Reader reader = new StringReader(INPUT_SATZ_123)) {
            checkImport(x, reader);
        }
    }

    /**
     * Hier probieren wir jetzt 2 Saetze ueber einen Reader einzulesen.
     *
     * @throws IOException sollte eigenlich nicht passieren, da wir von einem
     *             String lesen
     */
    @Test
    public void testImportFromReaderTwice() throws IOException {
        Satz x = new Datensatz(SatzTyp.of(123, 7));
        try (Reader reader = new StringReader(INPUT_SATZ_123 + INPUT_SATZ_123)) {
            checkImport(x, reader);
            checkImport(x, reader);
        }
    }

    private void checkImport(final Satz x, final Reader reader) throws IOException {
        x.importFrom(reader);
        assertEquals(INPUT_SATZ_123.trim(), x.toLongString().trim());
    }

    /**
     * Test-Methode fuer {@link Satz#importFrom(File)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportFile() throws IOException {
        File tmpFile = File.createTempFile("gdv", ".xport");
        LOG.info("File \"" + tmpFile + "\" created.");
        try {
            String fileContent = satz.toLongString();
            FileUtils.writeStringToFile(tmpFile, fileContent, Config.DEFAULT_ENCODING);
            satz.importFrom(tmpFile);
            assertEquals(fileContent, satz.toLongString());
        } finally {
            delete(tmpFile);
        }
    }

    private static void delete(File tmpFile) {
        boolean ok = tmpFile.delete();
        LOG.info("File \"{}\" {} deleted.", tmpFile, ok ? "successful" : "not");
    }

    /**
     * Ein unbekannte Datensatz ist nicht valide.
     */
    @Test
    public void testIsValid() {
        Satz a = new Datensatz(SatzTyp.of("0000"), 1);
        assertFalse("Diese Satzart gibt es nicht: " + a, a.isValid());
    }

    /**
     * Ein (Daten-)Satz mit einem nicht validen Feld ist auch nicht valide.
     */
    @Test
    public void testIsValidWithInvalidFeld() {
        NumFeld schrott = new NumFeld("schrott", "xxxx");
        satz.add(schrott);
        assertFalse(satz + " has invalid fields!", satz.isValid());
    }

    /**
     * Zwei gleiche Datensaetze muessen natuerlich auch den gleichen Hashcode
     * besitzen.
     */
    @Test
    public void testIsEquals() {
        Satz a = new Datensatz(SatzTyp.of(123));
        Satz b = new Datensatz(SatzTyp.of(123));
        ObjectTester.assertEquals(a, b);
        b.add(new Feld("c", 55, 'c'));
        assertNotEquals(a, b);
    }

    /**
     * Hier testen wir das Enfernen von Teildatensaetze.
     *
     * @since 0.4
     */
    @Test
    public void testRemoveTeildatensatz() {
        Satz s = new Vorsatz();
        int n = s.getTeildatensaetze().size();
        s.removeTeildatensatz(n);
        assertEquals(n - 1, s.getTeildatensaetze().size());
        s.removeTeildatensatz(1);
        assertEquals(n - 2, s.getTeildatensaetze().size());
    }

    @Test
    public void testGetAsListSimple() {
        List<MetaFeldInfo> feldInfos = SatzX.getMetaFeldInfos(Feld200.values());
        assertFalse("empty list", feldInfos.isEmpty());
        LOG.info("Feld200 has " + feldInfos.size() + " FeldInfos.");
        assertTrue("Feld200 should have more than " + Feld200.values().length + " entries",
                feldInfos.size() >= Feld200.values().length);
    }

    @Test
    public void testGetAsListComposite() {
        List<MetaFeldInfo> feldInfos = SatzX.getMetaFeldInfos(Feld220.values());
        assertFalse("empty list", feldInfos.isEmpty());
        LOG.info(Feld220.class.getName() + " has " + feldInfos.size() + " FeldInfos.");
        assertTrue("elements are missing", feldInfos.size() > Feld220.values().length);
    }

    /**
     * {@link Feld1bis7} ist ein Beispiel, wo kein Teildatensatz gesetzt ist.
     * Dieser wird z.B. beim {@link Feld200} ueber die {@link FelderInfo}-
     * Annotation gesetzt. Ob dieses Wert tatsaechlich gesetzt wird, wird
     * ueber diesen Test geprueft.
     */
    @Test
    public void testGetAsListTeildatensatz() {
        List<MetaFeldInfo> metaFeldInfos = SatzX.getMetaFeldInfos(Feld200.values());
        int found = 0;
        for (MetaFeldInfo metaFeldInfo : metaFeldInfos) {
            if (metaFeldInfo.getName().equals("SATZART")) {
                found++;
                checkSatzart(metaFeldInfo, found);
            }
        }
    }

    private static void checkSatzart(final MetaFeldInfo satzart, final int found) {
        LOG.info(found + ". MetaFeldInfo: " + satzart );
        assertEquals(1, satzart.getNr());
        assertEquals(found, satzart.getTeildatensatzNr());
    }

    /**
     * Die Satzart ist im ersten Feld (Byte 1 - 4) enthalten und ist in jedem
     * Satz vorhanden (auch Vorsatz und Nachsatz).
     */
    @Test
    public void testSatzartInhalt() {
        Feld satzart = satz.getFeld(Kopffelder1bis7.SATZART.getBezeichner());
        assertEquals("0123", satzart.getInhalt());
    }

    /**
     * Test-Methode fuer {@link Satz#getSatzTyp()}.
     */
    @Test
    public void testGetSatzTyp() {
        Satz satz220 = new SatzX(220, Feld220Wagnis0.class);
        assertEquals(SatzTyp.of("0220.010.0"), satz220.getSatzTyp());
    }

    @Test
    public void testSatzartFeld220Wagnis13() {
        Satz satz220 = new SatzX(220, Feld220Wagnis13.class);
        satz220.set(Bezeichner.WAGNISART, "1");
        assertEquals(SatzTyp.of("0220.010.13"), satz220.getSatzTyp());
    }

    @Test
    public void testSatzart022001013() {
        SatzTyp satzTyp = SatzTyp.of("0220.010.13.6");
        Satz satz220 = SatzFactory.getSatz(satzTyp);
        assertEquals(satzTyp, satz220.getSatzTyp());
    }

    /**
     * Test-Methode fuer {@link Satz#getFelder()}.
     */
    @Test
    public void testGetFelder() {
        Collection<Feld> felder = satz.getFelder();
        Teildatensatz lonelyTeildatensatz = satz.getTeildatensatz(1);
        CollectionTester.assertEquals(lonelyTeildatensatz.getFelder(), felder);
    }

    @Test
    public void testGetFelder2Teildatensaetze() {
        Satz satz = XmlService.getInstance().getSatzart(SatzTyp.of("0220.140"));
        Collection<Feld> expected = satz.getTeildatensatz(1).getFelder();
        expected.addAll(satz.getTeildatensatz(2).getFelder());
        Collection<Feld> felder = satz.getFelder();
        CollectionTester.assertEquals(expected, felder);
    }

    /**
     * Test-Methode fuer {@link Satz#getFelder()}. Im Gegensatz zur vorigen
     * Test-Methode wird hier der Vorsatz herangenommen, da er aus mehreren
     * (2) Teildatensaetzen besteht.
     */
    @Test
    public void testGetFelderWithVorsatz() {
        Satz vorsatz = new Vorsatz();
        Collection<Feld> felder = vorsatz.getFelder();
        Collection<Bezeichner> bezeichners = new HashSet<>();
        for (Feld feld : felder) {
            Bezeichner b = feld.getBezeichner();
            assertFalse(feld + " found more than once", bezeichners.contains(b));
            bezeichners.add(b);
        }
    }
    
    /**
     * Dieser Test stellt sicher, dass Satz.getSatzTyp() in Sparte 40 (Haftpflicht) das Wagnisfeld nicht in den SatzTyp kopiert, da es eine andere Bedeutung als
     * in Sparte 10 (Leben) hat.
     */
    @Test
    public void testWagnisartSparte40() {
        SatzTyp expectedSatzTyp = SatzTyp.of("0220.040");
        Satz satz = SatzFactory.getDatensatz(expectedSatzTyp);
        satz.set(Bezeichner.WAGNISART, "123456");
        SatzTyp satzTyp = satz.getSatzTyp();
        assertFalse("SatzFactory.getDatensatz(220, 40) hat keine Wagnisart im SatzTyp", satzTyp.hasWagnisart());
        assertEquals("SatzTyp von SatzFactory.getDatensatz(220, 40) sollte new SatzTyp(220, 40) entsprechen", expectedSatzTyp, satzTyp);
    }

    /**
     * Fuer die Abwaertskompatibilitaet mit der korrigierten VUVM2018-XML-Datei
     * ist es wichtig, dass auch die alten Namen (ohne "1" am Ende) weiterhin
     * funktionieren.
     */
    @Test
    public void testGetFeldWithSameNames() {
        Satz wertungssummen = new SatzX(SatzTyp.of("0220.010.2.9"), Feld220Wagnis2Wertungssummen.class);
        Feld f1 = wertungssummen.getFeld(Bezeichner.HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN);
        Feld f2 = wertungssummen.getFeld(Bezeichner.HAFTUNGSWERTUNGSSUMME_IN_WAEHRUNGSEINHEITEN2);
        assertNotEquals(f1, f2);
        Feld summe = wertungssummen.getFeld(Bezeichner.of("Haftungswertungssumme in W\u00e4hrungseinheiten 1"));
        assertEquals(f1, summe);
    }

    @Test
    public void testTeildatensatzCtor() {
        Satz satz102 = SatzFactory.getSatz(SatzTyp.of("0102"));
        Satz testsatz = new TestSatz(102, satz102.getTeildatensaetze());
        assertEquals(satz102.toLongString(), testsatz.toLongString());
        satz102.set(Bezeichner.BERUF_TEXT, "Tester");
        assertThat(testsatz.getFeld(Bezeichner.BERUF_TEXT).getInhalt().trim(), isEmptyString());
        assertNotEquals(satz102.toLongString(), testsatz.toLongString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNumFeldWithLetter() {
        Satz satz200 = XmlService.getInstance().getSatzart(SatzTyp.of("0200"));
        satz200.set(Bezeichner.GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN, "A99999999999");
    }

    static class TestSatz extends Satz {
        public TestSatz(int art, List<? extends Teildatensatz> tdsList) {
            super(art, tdsList);
        }
    }

}
