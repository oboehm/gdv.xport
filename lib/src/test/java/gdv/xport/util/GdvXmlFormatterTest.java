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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 27.03.21 by oliver (ob@oasd.de)
 */
package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.ByteAdresse;
import gdv.xport.feld.Feld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.xml.SatzXml;
import gdv.xport.satz.xml.XmlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.nio.file.Files;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * Unit-Tests fuer gdv.xport.util.GdvXmlFormatter.
 *
 * @author oliver
 */
public final class GdvXmlFormatterTest extends AbstractFormatterTest {

    private static final Logger LOG = LogManager.getLogger();
    private static final File XML_DIR = new File("target", "xml");

    @Override
    protected AbstractFormatter createFormatter() {
        return new GdvXmlFormatter();
    }

    @BeforeClass
    public static void setUpXmlDir() {
        if (XML_DIR.mkdirs()) {
            LOG.info("Verzeichnis '{}' wurde angelegt.", XML_DIR);
        }
    }

    @Test
    public void testWriteSatz() throws IOException {
        try (StringWriter writer = new StringWriter()) {
            GdvXmlFormatter formatter = new GdvXmlFormatter(writer);
            Satz satz100 = XmlService.getInstance().getSatzart(SatzTyp.of(100));
            formatter.write(satz100);
            assertThat(writer.toString().trim(), containsString("<satzart"));
        }
    }

    /**
     * Wenn ein Satz Luecken enthaelt, sollte bei der Formattierung dies
     * ausgefuellt werden. Sonst klappt es mit dem Initalisierung von SatzXML
     * nicht.
     *
     * @throws IOException im Fehlerfall
     * @throws XMLStreamException im Fehlerfall
     */
    @Test
    public void testWriteSatzWithLuecken() throws IOException, XMLStreamException {
        File output = new File(XML_DIR, "SatzMitLuecken.xml");
        Satz satz = XmlService.getInstance().getSatzart(SatzTyp.of(102));
        Teildatensatz tds = satz.getTeildatensatz(1);
        tds.remove(Bezeichner.of("Taetigkeit"));
        try (OutputStream ostream = Files.newOutputStream(output.toPath());
             GdvXmlFormatter formatter = new GdvXmlFormatter(ostream)) {
            formatter.write(satz);
        }
        Satz imported = SatzXml.of(output);
        assertFalse(imported.hasFeld(Bezeichner.of("Taetigkeit")));
        Bezeichner bezug = Bezeichner.of("Bezug zu VN");
        assertEquals(satz.getFeld(bezug), imported.getFeld(bezug));
    }

    @Test
    public void testCreateSatzFromOutput() throws IOException, XMLStreamException {
        Satz adressteil = XmlService.getInstance().getSatzart(SatzTyp.of(102));
        File output = new File(XML_DIR, "satz102.xml");
        SatzXml generated = formatSatz(adressteil, output);
        assertEquals(adressteil.getNumberOfTeildatensaetze(), generated.getNumberOfTeildatensaetze());
        assertEquals(adressteil.getFelder().size(), generated.getFelder().size());
        assertEquals(102, generated.getSatzart());
        assertEquals(adressteil.getSatzversion(), generated.getSatzversion());
        assertEquals(adressteil, generated);
    }

    @Test
    public void testBausparen() throws IOException, XMLStreamException {
        SatzTyp bausparen = SatzTyp.of("0220.580.01");
        Satz satz = XmlService.getInstance().getSatzart(bausparen);
        File target = new File(XML_DIR, "satz0220.580.01.xml");
        SatzXml generated = formatSatz(satz, target);
        assertEquals(220, generated.getSatzart());
        assertEquals(580, generated.getSparte());
        assertEquals(bausparen, generated.getSatzTyp());
    }

    private static SatzXml formatSatz(Satz adressteil, File output) throws IOException, XMLStreamException {
        formatTo(output, adressteil);
        SatzXml generated = SatzXml.of(output);
        assertNotNull(generated);
        return generated;
    }

    private static void formatTo(File output, Satz satz) throws IOException {
        try (FileOutputStream ostream = new FileOutputStream(output);
             GdvXmlFormatter formatter = new GdvXmlFormatter(ostream)) {
            formatter.write(satz);
        }
        LOG.info("{} wurde nach {} geschrieben.", satz, output);
    }

    @Test
    public void testWriteDatensatz() throws IOException, XMLStreamException {
        Datenpaket datenpaket = new Datenpaket();
        formatDatenpaket(datenpaket, "TEST", "datenpaket.xml");
    }

    @Test
    public void testFormatAllSupportedSaetze() throws IOException, XMLStreamException {
        formatDatenpaket("VUVM2018.xml", "datenpaket2018.xml");
        formatDatenpaket("VUVM2018xL.xml", "datenpaket2018xL.xml");
    }

    @Test
    public void testFormatAllSupportedSaetze2015() throws IOException, XMLStreamException {
        formatDatenpaket("VUVM2015.xml", "datenpaket2015.xml");
    }

    @Test
    public void testFormatAllSupportedSaetze2013() throws IOException, XMLStreamException {
        formatDatenpaket("VUVM2013.xml", "datenpaket2013.xml");
    }

    @Test
    public void testFormatAllSupportedSaetze2009() throws IOException, XMLStreamException {
        formatDatenpaket("VUVM2009.xml", "datenpaket2009.xml");
    }

    @Test
    @Ignore // kein Unit-Test mehr, dauern zu lang (> 2s)
    public void testFormatAllSupportedSaetze20xxxL() throws IOException, XMLStreamException {
        formatDatenpaket("VUVM2023xL.xml", "datenpaket2023xL.xml");
        formatDatenpaket("VUVM2018xL.xml", "datenpaket2018xL.xml");
        formatDatenpaket("VUVM2015xL.xml", "datenpaket2015xL.xml");
        formatDatenpaket("VUVM2013xL.xml", "datenpaket2013xL.xml");
        formatDatenpaket("VUVM2009xL.xml", "datenpaket2009xL.xml");
    }

    private void formatDatenpaket(String gdvXml, String filename) throws IOException, XMLStreamException {
        Datenpaket datenpaket = SatzRegistry.getInstance(gdvXml).getAllSupportedSaetze();
        datenpaket.setVuNummer("    ");
        formatDatenpaket(datenpaket, SatzRegistry.getInstance(gdvXml).getGdvRelease(), filename);
    }

    private void formatDatenpaket(Datenpaket datenpaket, String stand, String filename) throws IOException, XMLStreamException {
        File target = new File(XML_DIR, filename);
        formatDatenpaket(datenpaket, stand, target);
        XmlService xmlService = XmlService.getInstance(target.toURI());
        checkSatz(datenpaket.getVorsatz(), xmlService.getSatzart(SatzTyp.of(1)));
        checkSatz(datenpaket.getNachsatz(), xmlService.getSatzart(SatzTyp.of(9999)));
        for (Satz satz : datenpaket.getAllSaetze()) {
            LOG.debug("{} wird geprueft.", satz);
            checkSatz(satz, xmlService.getSatzart(satz.getSatzTyp()));
        }
        writeResettedDatenpaket(datenpaket, stand, filename);
    }

    // fuer eine potentielle neue Volage wird hier ein Datenpaket ohne Werte herausgeschrieben
    private void writeResettedDatenpaket(Datenpaket datenpaket, String stand, String filename) throws IOException {
        String[] namen = {"Satzart0", "Satzart9", "Wagnisart", "Art", "Vorzeichen", "SatzN", "ZusaetzlicheSatzkennung", "Folge",
                          "AnzSaetze"};
        for (Satz satz : datenpaket.getAllSaetze()) {
            for (Teildatensatz tds: satz.getTeildatensaetze()) {
                for (Feld feld : tds.getFelder()) {
                    String technischerName = feld.getBezeichner().getTechnischerName();
                    for (String s : namen) {
                        if (technischerName.startsWith(s)) {
                            feld.resetInhalt();
                            break;
                        }
                    }
                }
            }
        }
        formatDatenpaket(datenpaket, stand, new File(XML_DIR, "r" + filename));
    }

    private void formatDatenpaket(Datenpaket datenpaket, String stand, File target) throws IOException {
        try (FileOutputStream ostream = new FileOutputStream(target);
             GdvXmlFormatter formatter = new GdvXmlFormatter(ostream, Config.EMPTY.withProperty("gdv.export.xml.stand",
                     stand))) {
            formatter.write(datenpaket);
        }
    }

    private static void checkSatz(Satz reference, Satz satz) {
        for (int i = 1; i <= reference.getNumberOfTeildatensaetze(); i++) {
            checkTeildatensatz(reference.getTeildatensatz(i), satz.getTeildatensatz(i));
        }
        assertEquals(reference, satz);
    }

    private static void checkTeildatensatz(Teildatensatz reference, Teildatensatz teildatensatz) {
        for (Feld refFeld : reference.getFelder()) {
            if (refFeld.getBezeichner().equals(Bezeichner.LEERSTELLEN)) {
                LOG.info("{} wird fuer Vergleich ignoriert.", refFeld);
                continue;
            }
            Feld feld = teildatensatz.getFeld(ByteAdresse.of(refFeld.getByteAdresse()));
            if (feld.getClass().equals(Zeichen.class) && !refFeld.getClass().equals(Zeichen.class)) {
                LOG.info("Datentyp von {} weicht von {} ab ({}).", refFeld, feld, reference);
            } else {
                assertEquals(refFeld, feld);
                assertEquals(refFeld.getAusrichtung(), feld.getAusrichtung());
            }
        }
        assertEquals(reference, teildatensatz);
    }

    @Test
    public void testMusterdatei() throws IOException, XMLStreamException {
        File xmlFile = exportMusterdatei(new GdvXmlFormatter(), "musterdatei_GdvXmlFormatter.xml");
        XmlService xmlService = XmlService.getInstance(xmlFile.toURI());
        assertNotNull(xmlService);
    }

}