/*
 * Copyright (c) 2009 - 2021 by Oli B.
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
 * (c)reated 23.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport;

import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.io.PushbackLineNumberReader;
import gdv.xport.satz.*;
import gdv.xport.satz.xml.XmlService;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzRegistry;
import gdv.xport.util.SatzTyp;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.junit.BeforeClass;
import org.junit.Test;
import patterntesting.runtime.junit.FileTester;
import patterntesting.runtime.junit.NetworkTester;
import patterntesting.runtime.junit.ObjectTester;

import java.io.*;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * JUnit- und Integrations-Tests fuer {@link Datenpaket}-Klasse. Die
 * Integrationstests waren urspruenglich durch "@IntegrationTest" gekennzeichnet.
 * Mit dem JUnit-Vintage-Runner aus JUnit 5 has aber nicht mehr zuverlaessig
 * funktioniert, sodass jetzt darauf verzichtet wird.
 *
 * @author oliver
 * @since 23.10.2009
 */
public final class DatenpaketTest {

    private static final Logger LOG = LogManager.getLogger(DatenpaketTest.class);
    private static final SatzRegistry SATZ_REGISTRY = SatzRegistry.getInstance(Config.VUVM2018);
    private static String muster;
    private final Datenpaket datenpaket = new Datenpaket();

    @BeforeClass
    public static void setUpExportDir() throws IOException {
        File exportDir = new File("target", "export");
        if (exportDir.mkdir()) {
            LOG.info("Verzeichnis {} wurde angelegt.", exportDir);
        }
        muster = getResourceAsString("/musterdatei_041222.txt");
    }

    /**
     * Test-Methode fuer {@link gdv.xport.Datenpaket#export(java.io.Writer)}.
     * Damit die Assert's der Satzlaenge stimmen, muessen wir das
     * End-of-Datensatz abschalten.
     *
     * @throws IOException falls z.B. die Platte voll ist
     */
    @Test
    public void testEmptyExport() throws IOException {
        Datenpaket empty = new Datenpaket();
        StringWriter swriter = new StringWriter(1024);
        empty.export(swriter);
        String data = swriter.toString();
        swriter.close();
        int expectedLength = 1024 + 4 * Config.getEOD().length();
        assertEquals(expectedLength, data.length());
        Vorsatz vorsatz = datenpaket.getVorsatz();
        if ("VUVM2018.xml".equals(Config.getXmlResource()) || "VUVM2015.xml".equals(Config.getXmlResource())) {
            assertEquals("2.4", vorsatz.getVersion(Bezeichner.VERSION_SATZART_0001));
        }
        assertNotNull(vorsatz.getVersion(Bezeichner.VERSION_SATZART_9999));
        Nachsatz nachsatz = datenpaket.getNachsatz();
        assertEquals(0, nachsatz.getAnzahlSaetze());
        assertEquals(0.0, nachsatz.getGesamtBeitrag().toDouble(), 0.001);
        assertEquals(0.0, nachsatz.getGesamtBeitragBruttoMitVorzeichen().toDouble(), 0.001);
        assertEquals(0.0, nachsatz.getVersicherungsLeistungenMitVorzeichen().toDouble(), 0.001);
        assertEquals(0.0, nachsatz.getSchadenbearbeitungskostenMitVorzeichen().toDouble(), 0.001);
    }

    /**
     * Tested den Export. Damit die Assert's der Satzlaenge stimmen, muessen wir
     * das End-of-Datensatz abschalten.
     *
     * @throws IOException
     *             falls Temp-Datei nicht angelegt werden kann.
     */
    @Test
    public void testExportFile() throws IOException {
        datenpaket.setVuNummer("Hello");
        datenpaket.setAbsender("World");
        datenpaket.setAdressat("Test-Adressat");
        datenpaket.setVermittler("845/666666");
        Datum datum = new Datum(Bezeichner.of("Testdatum"), 1);
        datum.setInhalt("13022014");
        datenpaket.setErstellungsDatumVon(datum);
        datenpaket.setErstellungsDatumBis(datum);
        datenpaket.getVorsatz().setFeld(Bezeichner.VERSION_SATZART_9999, "1.1");
        File file = File.createTempFile("datenpaket", ".txt");
        datenpaket.export(file);
        LOG.info(datenpaket + " was exported to " + file);
        assertTrue(file + " was not created", file.exists());
        if ("VUVM2018.xml".equals(Config.getXmlResource()) || "VUVM2015.xml".equals(Config.getXmlResource())) {
            FileTester.assertContentEquals(new File("src/test/resources/gdv/xport/test-export.txt"), file);
        }
    }

    /**
     * Testet das Hinzuefuegen eines Datensatzes.
     */
    @Test
    public void testAdd() {
        datenpaket.add((Datensatz) SatzFactory.getSatz(SatzTyp.of(220)));
        Vorsatz vorsatz = datenpaket.getVorsatz();
        if ("VUVM2018.xml".equals(Config.getXmlResource()) || "VUVM2015.xml".equals(Config.getXmlResource())) {
            assertEquals("2.4", vorsatz.getVersion(Bezeichner.VERSION_SATZART_0001));
        }
        assertNotNull(vorsatz.getVersion(Bezeichner.VERSION_SATZART_9999));
        Nachsatz nachsatz = datenpaket.getNachsatz();
        assertEquals(2, nachsatz.getAnzahlSaetze());
    }

    @Test
    public void testAddMehrereDatensaetze() throws IOException {
        datenpaket.importFrom(muster);
        Datenpaket dp = new Datenpaket();
        for (Datensatz datensatz : datenpaket.getDatensaetze()) {
            dp.add(datensatz);
        }
        assertEquals(datenpaket.getNachsatz().getAnzahlSaetze(), dp.getNachsatz().getAnzahlSaetze());
    }

    @Test
    public void testAddVorbelegung() {
        datenpaket.setVuNummer("007");
        datenpaket.setVermittler("JamesBond");
        datenpaket.add((Datensatz) SatzFactory.getSatz(SatzTyp.of(100)));
        assertEquals(datenpaket.getVuNummer(), datenpaket.getDatensaetze().get(0).getVuNummer());
        assertEquals(datenpaket.getVermittler(), datenpaket.getDatensaetze().get(0).getVermittler());
    }

    /**
     * Falls kein Datum gesetzt wird, sollte als Default das heutige DAtum
     * zurueckgegeben werden.
     */
    @Test
    public void testGetErstellungsDatumVon() {
        Datum von = datenpaket.getErstellungsDatumVon();
        Datum heute = Datum.heute();
        Date today = heute.toDate();
        assertEquals(today, von.toDate());
    }

    /**
     * Test-Methode fuer {@link gdv.xport.Datenpaket#getAbsender()}.
     */
    @Test
    public void testGetAbsender() {
        String absender = "Dagobert Duck";
        datenpaket.setAbsender(absender);
        assertEquals(absender, datenpaket.getAbsender());
    }

    /**
     * Test-Methode fuer {@link gdv.xport.Datenpaket#getVermittler()}.
     */
    @Test
    public void testSetVermittler() {
        Datenpaket dp = SatzRegistry.getInstance().getAllSupportedSaetze();
        String vermittler = "08/15";
        dp.setVermittler(vermittler);
        assertEquals(vermittler, dp.getVermittler());
        Vorsatz vorsatz = dp.getVorsatz();
        assertEquals(vermittler, vorsatz.getVermittler());
        Nachsatz nachsatz = dp.getNachsatz();
        assertEquals(vermittler, nachsatz.getVermittler());
        for (Datensatz satz : dp.getDatensaetze()) {
            assertEquals(vermittler, satz.getVermittler());
        }
    }

    @Test
    public void testSetVuNummer() {
        Datenpaket dp = SatzRegistry.getInstance().getAllSupportedSaetze();
        dp.setVuNummer("12345");
        for (Datensatz satz : dp.getDatensaetze()) {
            if (satz.hasFeld(Bezeichner.VU_NUMMER)) {
                assertEquals("12345", satz.getVuNummer());
            }
        }
    }

    /**
     * Tested den Import.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportMusterdatei() throws IOException {
        try (InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt")) {
            checkImport(datenpaket, istream);
        }
    }

    @Test
    public void testImportMusterdateiStrict() throws IOException {
        Datenpaket paket = new Datenpaket(Config.getInstance().withProperty("gdv.feld.validate", "strict"));
        try (InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt")) {
            paket.importFrom(istream);
            List<ConstraintViolation> violations = paket.validate();
            LOG.info("VIOLATIONS:\t{}", SimpleConstraintViolation.toString(violations));
            assertFalse(violations.isEmpty());
        }
    }

    /**
     * Tested einen Import von 2 Datenpaketen.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImport2Datenpakete() throws IOException {
        try (InputStream istream = this.getClass().getResourceAsStream("/zwei_datenpakete.txt")) {
            checkImport(datenpaket, istream);
            Datenpaket zwei = new Datenpaket();
            checkImport(zwei, istream);
            LOG.info(datenpaket + " / " + zwei + " imported.");
            assertNotEquals(datenpaket, zwei);
        }
    }

    private static void checkImport(final Datenpaket paket, final InputStream istream) throws IOException {
        paket.importFrom(istream);
        List<ConstraintViolation> violations = paket.validate();
        LOG.info("VIOLATIONS:\t{}", SimpleConstraintViolation.toString(violations));
        assertTrue(violations.isEmpty());
    }

    /**
     * Tested einen Import von 2 Datenpaketen.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImport2DatenpaketeWithReader() throws IOException {
        checkZweiDatenpakete("src/test/resources/zwei_datenpakete.txt", Config.LAX);
    }

    @Test
    public void testImport2DatenpaketeStrict() throws IOException {
        checkZweiDatenpakete("src/test/resources/datenpakete/zwei_datenpakete_strict.txt", Config.STRICT);
    }

    private void checkZweiDatenpakete(String filename, Config config) throws IOException {
        try (Reader reader = new InputStreamReader(new FileInputStream(filename), StandardCharsets.ISO_8859_1)) {
            checkImport(datenpaket, reader);
            Datenpaket zwei = new Datenpaket();
            checkImport(zwei, reader);
            LOG.info(datenpaket + " / " + zwei + " imported.");
            assertNotEquals(datenpaket, zwei);
        }
        List<ConstraintViolation> violations = datenpaket.validate(config);
        LOG.info("violations = {}", violations);
        assertTrue(violations.isEmpty());
    }

    private static void checkImport(final Datenpaket paket, final Reader reader) throws IOException {
        paket.importFrom(reader);
        List<ConstraintViolation> violations = paket.validate();
        LOG.info("violations = {}", violations);
        assertTrue(paket.isValid());
    }

    /**
     * Tested den Import von einer URL.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportFromURL() throws IOException {
        URL url = this.getClass().getResource("/musterdatei_041222.txt");
        datenpaket.importFrom(url);
        assertTrue(datenpaket.isValid());
    }

    /**
     * Ursache fuer Issue 37 war ein Encoding-Problem beim Import. Hiermit
     * wurde das Problem nachgestellt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportFromFile() throws IOException {
        File file = new File("src/test/resources", "musterdatei_041222.txt");
        datenpaket.importFrom(file);
        List<ConstraintViolation> violations = datenpaket.validate();
        LOG.info("violations = {}", violations);
        assertTrue(datenpaket.isValid());

        // test that every teildatensatz of all datensaetze has the correct satznummer (according to the identified
        // teildatensatz
        List<Pair<Integer, Integer>> wrongDatensatzTeildatensatzList = new ArrayList<>();
        for (int i = 0; i < datenpaket.getDatensaetze().size(); i++) {
            Datensatz datensatz = datenpaket.getDatensaetze().get(i);
            for (int j = 0; j < datensatz.getTeildatensaetze().size(); j++) {
                Teildatensatz teildatensatz = datensatz.getTeildatensaetze().get(j);
                char satznummerOfTeildatensatz = teildatensatz.getSatznummer().toChar();
                if (teildatensatz.hasFeld(Bezeichner.SATZNUMMER)
                        && StringUtils.isNumeric(teildatensatz.getFeldInhalt(Bezeichner.SATZNUMMER))
                        && !String.valueOf(satznummerOfTeildatensatz).equals(teildatensatz.getFeldInhalt(Bezeichner.SATZNUMMER))) {
                    wrongDatensatzTeildatensatzList.add(Pair.of(i, j));
                }
            }
        }

        assertTrue("There are teildatensaetze with wrong satznummern: (datensatz, teildatensatz) -> " + wrongDatensatzTeildatensatzList, wrongDatensatzTeildatensatzList.isEmpty());

        assertEquals("BRBRIENNEE,J\u00dcRGEN", datenpaket.getAdressat());
    }

    /**
     * Der Test wurde als IntegrationTest markiert, da dazu eine Online-Verbindung
     * noetig ist (die nicht immer vorausgesetzt werden kann).
     *
     * @throws IOException falls man z.B. offline ist
     * @since 0.3
     */
    @Test
    public void testImportFromHTTP() throws IOException {
        URI uri = URI.create("http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt");
        if (NetworkTester.exists(uri)) {
            datenpaket.importFrom(uri);
            assertTrue(datenpaket.isValid());
        } else {
            LOG.info("Offline? Import von {} wird uebersprungen.", uri);
        }
    }

    /**
     * Falls Leerzeichen am Zeilenende weggelassen wurden, sollen die einzelnen
     * Saetze trotzdem importiert werden koennen.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     * @since 0.9.3
     */
    @Test
    public void testImportTrimmed() throws IOException {
        StringBuilder buffer = new StringBuilder();
        try (InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(istream, StandardCharsets.ISO_8859_1));
            for (String line = reader.readLine(); StringUtils.isNotEmpty(line); line = reader.readLine()) {
                buffer.append(line.trim()).append('\n');
            }
            datenpaket.importFrom(buffer.toString());
            List<ConstraintViolation> violations = datenpaket.validate();
            LOG.info("violations = {}", violations);
            assertTrue(datenpaket.isValid());
        }
    }


	/**
	 * Pruefe, ob der Wechsel zum naechsten Datensatz korrekt funktioniert,
     * auch wenn der folgende Satz die gleiche Satzart+Sparte besitzt...
     *
     * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testTeildatensatzWechsel() throws IOException {
		InputStream istream = this.getClass().getResourceAsStream("/teildatensatz_wechsel.gdv");
		datenpaket.importFrom(istream);
		assertTrue(datenpaket.isValid());
		assertEquals(2, datenpaket.getDatensaetze().size());
		assertEquals("123456789001", datenpaket.getDatensaetze().get(0).getVersicherungsscheinNummer());
		assertEquals("123456789002", datenpaket.getDatensaetze().get(1).getVersicherungsscheinNummer());
	}


	/**
     * Der Export eines zuvor importierten Datenpakets sollte identisch mit der
     * Ausgangsdatei sein.
     *
     * @since 0.5.0
     * @throws IOException falls die Platte kaputt ist
     */
    @Test
    public void testImportExport() throws IOException {
        datenpaket.importFrom(muster);
        Satz vertragsteil = datenpaket.getDatensaetze().get(1);
        Feld vertragsstatus = vertragsteil.getFeld(Bezeichner.VERTRAGSSTATUS);
        assertEquals("1", vertragsstatus.getInhalt());
        checkExportWith(muster);
    }

    @Test
    public void testImportExportAllDatensaetze() throws IOException {
        Datenpaket dp = SATZ_REGISTRY.getAllSupportedSaetze();
        for (Satz satz : dp.getAllSaetze()) {
            AbstractSatzTest.setUp(satz);
        }
        File exportFile = new File("target/export/all.txt");
        dp.export(exportFile);
        datenpaket.importFrom(exportFile);
        assertEquals(dp, datenpaket);
    }

    /**
     * Hier wird die Import-Datei getestet, die mir Igor geschickt hat und
     * mit der es anfangs Probleme gab.
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    public void testImportIgor() throws IOException {
        importResource("/igor_110120.txt");
    }

    /**
     * Hier wird die Import-Datei getestet, die mir Igor geschickt hat und
     * mit dem es anfangs Probleme gab. Dieses Mal wird aber die Datei als
     * Stream eingelesen.
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    public void testImportIgorAsStream() throws IOException {
        try (InputStream istream = DatenpaketTest.class.getResourceAsStream("/igor_110120.txt")) {
            datenpaket.importFrom(istream);
            assertTrue(datenpaket.validate().toString(), datenpaket.isValid());
        }
    }

    /**
     * Test-Import von "Bender_Leben.GDV".
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    public void testBenderLeben() throws IOException {
        importResource("/Bender_Leben.GDV");
    }

    /**
     * Test-Import von "Leben_Wagnis2_Riester.GDV".
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    public void testLebenWagnis2Riester() throws IOException {
        importResource("/Leben_Wagnis2_Riester.GDV");
    }

    /**
     * Test-Import von "Leben_Wagnis3_Risikoversicherung.GDV".
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    public void testLebenWagnis3Riskikoversicherung() throws IOException {
        importResource("/Leben_Wagnis3_Risikoversicherung.GDV");
    }

    /**
     * Test-Import von "Leben_Wagnis4_BU.GDV".
     *
     * @throws IOException bei I/O-Problemen
     */
    @Test
    public void testLebenWagnis4BU() throws IOException {
        importResource("/Leben_Wagnis4_BU.GDV");
    }

    private void importResource(final String name) throws IOException {
        String content = getResourceAsString(name);
        datenpaket.importFrom(content);
        checkExportWith(content);
    }

    private static String getResourceAsString(final String resource) throws IOException {
        try (InputStream istream = DatenpaketTest.class.getResourceAsStream(resource)) {
            assertNotNull(resource + " not found", istream);
            return IOUtils.toString(istream, Config.DEFAULT_ENCODING);
        }
    }

    private void checkExportWith(final String content) throws IOException {
        StringWriter swriter = new StringWriter(content.length());
        datenpaket.export(swriter);
        swriter.close();
        assertLines(content, swriter.toString());
    }

    private static void assertLines(final String expected, final String paket) throws IOException {
        try (BufferedReader expectedReader = new BufferedReader(new StringReader(expected));
             BufferedReader paketReader = new BufferedReader(new StringReader(paket))) {
            for (int line = 1; ; line++) {
                String expectedLine = readNextLine(expectedReader);
                String paketLine = readNextLine(paketReader);
                if (expectedLine == null) {
                    LOG.info("{} lines compared (no difference)", line);
                    break;
                }
                if (expectedLine.startsWith("0001")) {
                    assertEquals("difference in Feld 1-6 of line " + line, expectedLine.substring(0, 96),
                            paketLine.substring(0, 96));
                } else {
                    assertEquals("difference in line " + line, expectedLine, paketLine);
                }
            }
        }
    }

    private static String readNextLine(final BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            LOG.debug("EOF reached");
            return null;
        }
        if (line.isEmpty()) {
            LOG.debug("skipping empty line");
            return readNextLine(reader);
        }
        return line;
    }

    /**
     * Wenn keine VU-Nummer gesetzt wurde, sollte es mind. ein Validierungs-
     * Fehler geben.
     */
    @Test
    public void testValidate() {
        Datenpaket dummy = new Datenpaket(Config.DUMMY_VU_NUMMER);
        checkViolations(dummy);
    }

    /**
     * Da die Validierung ziemlich lange dauert, wurde sie einfach durch
     * eine isValid()-Afrage ersetzt. Falls man die Validierung doch noch
     * will, setzt man einfach den Log-Level auf TRACE.
     *
     * @param defect das defekte Datenpaket
     */
    private void checkViolations(final Datenpaket defect) {
        if (LOG.isTraceEnabled()) {
            List<ConstraintViolation> violations = defect.validate();
            assertTrue("at least 1 violation is expected", (violations.size() > 0));
            for (ConstraintViolation cv : violations) {
                LOG.trace("Violation: " + cv);
            }
        }
        assertFalse("at least 1 violation is expected", defect.isValid());
    }

    /**
     * Fuer eine Versicherungsscheinnummer muss die Folgenummer immer mit 1
     * anfangen. Taucht dieser Versicherungsscheinnummer fuer den gleichen Satz
     * ein zweites Mal auf, muss die Folgenummer entsprechend erhoeht werden.
     */
    @Test
    public void testValidateFolgenummer() {
        Datenpaket defect = new Datenpaket("08/15");
        defect.add(createDatensatzWithFolgenummer(1));
        defect.add(createDatensatzWithFolgenummer(3));
        defect.add(createDatensatzWithFolgenummer(2));
        checkViolations(defect);
    }

    private static Datensatz createDatensatzWithFolgenummer(final int nr) {
        Datensatz datensatz = XmlService.getInstance().getSatzart(SatzTyp.of(100));
        datensatz.setVersicherungsscheinNummer("4711");
        datensatz.setFolgenummer(nr);
        return datensatz;
    }

    @Test
    public void testAddDatensatz200() {
        BigDecimal summe = addDatensatz(SatzTyp.of(200), Bezeichner.GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN,
                ByteAdresse.of(256),
                new BigDecimal("200.50"), new BigDecimal("21.72"));
        Nachsatz nachsatz = datenpaket.getNachsatz();
        assertEquals(summe, nachsatz.getGesamtBeitrag().toBigDecimal());
    }

    @Test
    public void testAddDatensatz400BeitragBrutto() {
        BigDecimal summe = addDatensatz(SatzTyp.of(400), Bezeichner.GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN,
                ByteAdresse.of(150),
                new BigDecimal(-100), new BigDecimal("11.11"));
        Nachsatz nachsatz = datenpaket.getNachsatz();
        assertEquals(summe, nachsatz.getGesamtBeitragBruttoMitVorzeichen().toBigDecimal());
    }

    @Test
    public void testAddDatensatz400Provision() {
        BigDecimal summe = addDatensatz(SatzTyp.of(400), Bezeichner.GESAMTPROVISIONSBETRAG_IN_WAEHRUNGSEINHEITEN,
                ByteAdresse.of(162),
                new BigDecimal("0.99"), new BigDecimal("-0.11"));
        Nachsatz nachsatz = datenpaket.getNachsatz();
        assertEquals(summe, nachsatz.getGesamtProvisionsBetragMitVorzeichen().toBigDecimal());
    }

    @Test
    public void testAddDatensatz500VersicherungsLeistungen() {
        BigDecimal summe = addDatensatz(SatzTyp.of(500), Bezeichner.BETRAG_IN_WAEHRUNGSEINHEITEN_GEMAESS_ZAHLUNGSART,
                ByteAdresse.of(153),
                new BigDecimal(500), new BigDecimal("55.50"), new BigDecimal(-600));
        Nachsatz nachsatz = datenpaket.getNachsatz();
        assertEquals(summe, nachsatz.getVersicherungsLeistungenMitVorzeichen().toBigDecimal());
    }

    @Test
    public void testAddDatensatz500Schaden() {
        BigDecimal summe = addDatensatz(SatzTyp.of(500), Bezeichner.SCHADENBEARBEITUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN,
                ByteAdresse.of(167),
                new BigDecimal(500), new BigDecimal("55.50"), new BigDecimal(-600));
        Nachsatz nachsatz = datenpaket.getNachsatz();
        assertEquals(summe, nachsatz.getSchadenbearbeitungskostenMitVorzeichen().toBigDecimal());
    }

    private BigDecimal addDatensatz(SatzTyp satzTyp, Bezeichner bezeichner, ByteAdresse vorzeichen, BigDecimal... beitraege) {
        BigDecimal summe = BigDecimal.ZERO;
        for (BigDecimal beitrag : beitraege) {
            BigDecimal positiv = beitrag.abs();
            Datensatz datensatz = (Datensatz) SatzFactory.getSatz(satzTyp);
            datensatz.setFeld(bezeichner, positiv.movePointRight(2).toString());
            if (beitrag.compareTo(BigDecimal.ZERO) < 0) {
                datensatz.getTeildatensatz(1).setFeld(vorzeichen, "-");
            }
            datenpaket.add(datensatz);
            summe = summe.add(beitrag);
        }
        return summe;
    }

    @Test
    public void testSetVersionNachsatz() {
        Bezeichner satzart9999 = Bezeichner.VERSION_SATZART_9999;
        datenpaket.getVorsatz().setVersion(satzart9999, "0.9");
        assertEquals("0.9", datenpaket.getVorsatz().getVersion(satzart9999));
    }

    @Test
    public void testEquals() {
        Datenpaket one = new Datenpaket();
        Datenpaket two = new Datenpaket();
        ObjectTester.assertEquals(one, two);
    }

    @Test
    public void testNotEquals() {
        Datenpaket x = new Datenpaket();
        x.add((Datensatz) SATZ_REGISTRY.getSatz(SatzTyp.of("0220.010.13.1")));
        assertNotEquals(datenpaket, x);
    }

    /**
     * Hierueber wird der fehlerhafte Import aus Issue #61 nachgestellt.
     *
     * @throws IOException im Fehlerfall
     */
    @Test
    public void testImportWagnisart13() throws IOException {
        checkImport("gdv/xport/satz/testcase_0220_010_13.txt", Charset.forName("IBM850"));
    }

    private static Datenpaket checkImport(String filename, Charset encoding) throws IOException {
        File testfile = new File("src/test/resources", filename);
        Datenpaket imported = new Datenpaket();
        imported.importFrom(testfile, encoding);
        File exportDir = new File("target", "export");
        if (exportDir.mkdir()) {
            LOG.info("Verzeichnis {} wurde angelegt.", exportDir);
        }
        File exportFile = new File(exportDir, testfile.getName());
        for (Datensatz ds : imported.getDatensaetze()) {
            assertTrue(ds.isValid());
        }
        imported.export(exportFile, encoding);
        FileTester.assertContentEquals(testfile, exportFile);
        LOG.info("{} wurde nach {} exportiert.", imported, exportFile);
        return imported;
    }

    @Test
    public void testExportImportWagnisart13() throws IOException {
        File testfile = new File("target", "issue61.gdv");
        Datensatz leben = (Datensatz) SATZ_REGISTRY.getSatz(SatzTyp.of("0220.010.13.1"));
        leben.removeTeildatensatz(3);
        datenpaket.add(leben);
        datenpaket.add((Datensatz) SATZ_REGISTRY.getSatz(SatzTyp.of("0220.010.13.6")));
        datenpaket.export(testfile);
        Datenpaket imported = new Datenpaket();
        imported.importFrom(testfile);
        assertEquals(2, imported.getDatensaetze().size());
    }

    @Test
    public void testExportImport221Wagnisdaten() throws IOException {
        File testfile = new File("target", "exported_0221_030.txt");
        Datensatz unfall = (Datensatz) SATZ_REGISTRY.getSatz(SatzTyp.of("0221.030"));
        datenpaket.add(unfall);
        datenpaket.export(testfile);
        Datenpaket imported = new Datenpaket();
        imported.importFrom(testfile);
        assertEquals(1, imported.getDatensaetze().size());
        assertEquals(unfall, imported.getDatensaetze().get(0));
    }


    /**
     * Dies ist Testfall fuer Issue #62.
     *
     * @throws IOException im Fehlerfall
     */
    @Test
    public void testPack() throws IOException {
        Datenpaket x = checkImport("gdv/xport/satz/testcase_0220_mit_0221_versetzt.txt", Charset.forName("IBM850"));
//        MatcherAssert.assertThat(x.getDatensaetze().size(), equalTo(27));

        Datenpaket unpacked = x.pack();
        /*
        After pack, expected the following:
        3x0100 for 2222222
        1x0200 for 2222222
        1x0210 for 2222222
        1x0220.010.0 for 2222222
        1x0220.010.13.1 for 2222222
        1x0221.010.13.1 for 2222222
        3x0220.010.13.6 for 2222222
        3x0100 for 2222244
        1x0200 for 2222244
        1x0210 for 2222244
        1x0220.010.0 for 2222244
        1x0220.010.13.1 for 2222244
        1x0221.010.13.1 for 2222244
        3x0220.010.13.6 for 2222244
        Total: 22
         */
        MatcherAssert.assertThat(unpacked.getDatensaetze().size(), equalTo(22));
        checkDatensatz(x, SatzTyp.of("0220.010.13.1"));
        checkDatensatz(x, SatzTyp.of("0221.010.13.1"));
    }

    private void checkDatensatz(Datenpaket paket, SatzTyp typ) {
        List<Datensatz> datensaetze = paket.getDatensaetze(typ);
        assertFalse(datensaetze.isEmpty());
        for (Datensatz ds : datensaetze) {
            MatcherAssert.assertThat(ds.getNumberOfTeildatensaetze(), greaterThan(1));
            assertTrue(ds.isValid());
        }
    }

    @Test
    public void testPackMusterdatei() throws IOException {
        try (InputStream istream = getClass().getResourceAsStream("/musterdatei_041222.txt")) {
            assertNotNull(istream);
            datenpaket.importFrom(istream).pack();
        }
        MatcherAssert.assertThat(datenpaket.getDatensaetze().size(), lessThan(129));
    }

    @Test
    public void testExportImportSchaden500V1_8() throws IOException {
        /*
        In der aktuellen Version (VUVM2018.xml) ist fuer Datensatz 0500 im ersten Teilsatz eine Satznummerwiederholung vorgesehen.
        Der Rueckimport muss korrekt zwei Datensaetze erkennen, selbst wenn wir jeweils den zweiten Datensatz nicht exportieren.
         */
        File testfile = new File("target", "schadensatz_500_zwei_datensaetze.gdv");
        Datensatz schaden = (Datensatz) SATZ_REGISTRY.getSatz(SatzTyp.of("0500"));
        schaden.removeTeildatensatz(2);
        Datensatz schaden2 = (Datensatz) SATZ_REGISTRY.getSatz(SatzTyp.of("0500"));
        schaden2.removeTeildatensatz(2);
        datenpaket.add(schaden);
        datenpaket.add(schaden2);
        datenpaket.export(testfile);
        Datenpaket imported = new Datenpaket();
        imported.importFrom(testfile);
        assertEquals(2, imported.getDatensaetze().size());

        Version version0500 = imported.getVorsatz().getSatzartVersionen().get(SatzTyp.of("0500"));
        assertEquals("Version vom Datensatz 0500 stimmt nicht", "1.8", version0500 != null ? version0500.getInhalt() : "nicht vorhanden");
    }

    @Test
    public void testExportImportSchaden500V1_5() throws IOException {
        /*
        In Version 1.5 war fuer Datensatz 0500 im ersten Teilsatz die Satznummerwiederholung noch nicht vorgesehen.
        Wir simulieren das, indem die Satznummerwiederholung mit 'leer' geliefert wird.
        Trotzdem muss der Rueckimport korrekt zwei Datensaetze erkennen und nicht nur einen.
         */
        SatzRegistry satzRegistry2009 = SatzRegistry.getInstance(Config.VUVM2009);
        File testfile = new File("target", "schadensatz_500_zwei_datensaetze_v1_5.gdv");
        Datensatz schaden = (Datensatz) satzRegistry2009.getSatz(SatzTyp.of("0500"));
        schaden.removeTeildatensatz(2);
        Datensatz schaden2 = (Datensatz) satzRegistry2009.getSatz(SatzTyp.of("0500"));
        schaden2.removeTeildatensatz(2);
        datenpaket.add(schaden);
        datenpaket.add(schaden2);
        datenpaket.export(testfile);
        Datenpaket imported = new Datenpaket();
        imported.importFrom(testfile);
        assertEquals(2, imported.getDatensaetze().size());

        Version version0500 = imported.getVorsatz().getSatzartVersionen().get(SatzTyp.of("0500"));
        assertEquals("Version vom Datensatz 0500 stimmt nicht", "1.5", version0500 != null ? version0500.getInhalt() : "nicht vorhanden");
    }

    /**
     * Test dass ein Datensatz in einer alten Version korrekt ist, in einer neuen Version aber nicht korrekt
     * Testfall eines Beispiels eines 0220.010.48-er Datensatzes, Aenderung zwischen VUVM2015 und VUVM2018 (Risikozuschlag in %)
     *
     * @throws IOException im Fehlerfall
     */
    @Test
    public void testImport0220_010_48_version_differences() throws IOException {
        Datenpaket datenpaket2_1 = new Datenpaket();
        File testfile = new File("src/test/resources", "gdv/xport/satz/testcase_0220_010_48_v2_1.txt");
        datenpaket2_1.importFrom(testfile, Charset.forName("IBM850"));
        assertTrue("Datenpaket muss gueltig sein", datenpaket2_1.isValid());

        Datenpaket datenpaket2_2 = new Datenpaket();
        testfile = new File("src/test/resources", "gdv/xport/satz/testcase_0220_010_48_v2_2_error.txt");
        datenpaket2_2.importFrom(testfile, Charset.forName("IBM850"));
        List<ConstraintViolation> violations = datenpaket2_2.validate();
        LOG.info("violations = {}", violations);
    }

    @Test
    public void testImportUnbekannterSatz() throws IOException {
        File testfile = new File("src/test/resources", "gdv/xport/satz/Satz0123.txt");
        try (PushbackLineNumberReader reader = new PushbackLineNumberReader(new FileReader(testfile))) {
            Satz satz = Datenpaket.importSatz(reader);
            assertEquals(123, satz.getSatzart());
        }
    }

    @Test
    public void testImport0220_020_1_satz_kaputt() throws IOException {
        Datenpaket datenpaket = new Datenpaket();
        File testfile = new File("src/test/resources", "gdv/xport/satz/Test_import_0220_020_1-satz-kaput.txt");
        datenpaket.importFrom(testfile);
        File targetFile = new File("target", "satz-kaputt.txt");
        datenpaket.export(targetFile);
        FileTester.assertContentEquals(testfile, targetFile);
        List<ConstraintViolation> violations = datenpaket.validate(Config.LAX);
        LOG.info("violations = {}", violations);
    }

    /**
     * Dieser Test dient zum Messen des Speicherverbrauchs. Aktuell steigt er
     * nach 7946 kompletten Datensaetzen (ca. 1.26 Mio Saetze) mit einer OOME
     * aus (nach 4 Minunten bei 8 GB Haupt-Speicher).
     * <p>
     * Nach der Optimierung des Speicherabrdrucks steigt dieser Test unter
     * gleichen Ausgangsbedingungen erst nach 15.000 kompletten Datensaetzen
     * (2.0 Mio Saetzen, 2.9 Mio Teildatensaetzen) aus.
     * </p>
     * <p>
     * Eine Abloesung der Speicherung der Felder als HashMap durch eine
     * SortedSet lies den Speicherverbrauch wieder um ca. 20% anstelgen
     * (ca. 12.200 komplette Datensaetze). Durch den Austausch der SortedSet
     * durch eine Arraylist brachte hingegen einen weiteren Speicherreduktion
     * um 20% (17.700 Datensaetze).
     * </p>
     *
     * @throws CloneNotSupportedException sollte nicht vorkommen
     */
    @Test
    public void testMemoryVerbrauch() throws CloneNotSupportedException {
        if (System.getProperty("memtest", "false").equalsIgnoreCase("true")) {
            Datenpaket datenpaket = new Datenpaket();
            Datenpaket supportedSaetze = SATZ_REGISTRY.getAllSupportedSaetze();
            fill(datenpaket, supportedSaetze.getDatensaetze(), 20_000);
            LOG.info("{} wurde aufgebaut.", datenpaket);
        }
    }

    private void fill(Datenpaket datenpaket, List<Datensatz> datensaetze, int n) throws CloneNotSupportedException {
        for (int i = 1; i <= n; i++) {
            try {
                for (Datensatz datensetz : datensaetze) {
                    datenpaket.add((Datensatz) datensetz.clone());
                }
                if (i % 100 == 0) {
                    int records = 4 + i * 228;
                    LOG.info("{} komplette Datensaetze wurden hinzugefuegt ({} Saetze, {} Teildatensaetze).",
                            i, datenpaket.getAllSaetze().size(), records);
                }
            } catch (OutOfMemoryError ex) {
                LOG.error("Abbruch nach {} kompletten Datensaetzen:", i, ex);
                throw ex;
            }
        }
    }

    @Test
    public void testConfigVorsatzNachsatz() {
        Datenpaket datenpaket = new Datenpaket(Config.STRICT);
        assertEquals(Config.STRICT, datenpaket.getVorsatz().getConfig());
        assertEquals(Config.STRICT, datenpaket.getNachsatz().getConfig());
    }

}
