/*
 * Copyright (c) 2017 by Oliver Boehm
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
 * (c)reated 08.11.2017 by oboehm (ob@oasd.de)
 */
package gdv.xport.feld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.fail;

/**
 * Klasse BezeichnerIT ueberpreuft die Konstanten in der {@link Bezeichner}-
 * Klasse, ob der technische Name in "VUVM2018.xml" tatsaechlich existiert.
 * Da der Test die komplette Bezeichner-Klasse durchscannt und mit der
 * 5MB-großen XML-Datei abgleicht, dauert er etwas laenger und ist daher als
 * Integrations-Test mit Suffix IT gekennzeichnet.
 *
 * @author oboehm
 * @since 3.0 (08.11.2017)
 */
@RunWith(Parameterized.class)
public class BezeichnerIT {

    private static final Logger LOG = LogManager.getLogger(BezeichnerIT.class);
    private static final Set<String> TECHNISCHE_NAMEN = new TreeSet<>();
    private final Bezeichner bezeichner;

    /**
     * Hierueber werden die Test-Werte per Konstruktor "injected".
     *
     * @param bezeichner Konstante aus der Bezeichner-Klasse
     */
    public BezeichnerIT(Bezeichner bezeichner) {
        this.bezeichner = bezeichner;
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> values = new ArrayList<>();
        for (Field field : Bezeichner.class.getFields()) {
            if (field.getType().equals(Bezeichner.class) && !isExcludedFromTest(field)) {
                try {
                    Bezeichner b = (Bezeichner) field.get(null);
                    addTo(values, b);
                } catch (IllegalAccessException ex) {
                    LOG.warn("Field {} is ignored:", field, ex);
                }
            }
        }
        return values;
    }

    private static boolean isExcludedFromTest(Field field) {
        String[] prefixes =
                {"ABSTAND", "ABWEICHENDE_LEISTUNGSDAUER", "AENDERUNG", "ANFAENGLICHE", "AUSZAHLUNGSWEISE",
                        "ANZAHL_DER_AUSZAHLUNGEN",
                        "BE", "BUZ", "EINSCHLUSS_PROZENT_SATZ", "ERLEBENSFALL_VS", "ERSTELLUNGSDAT_ZEITRAUM",
                        "FALLENDE", "GES", "INTRO", "KARENZZEIT", "KENN", "KU_", 
                        "LAUFZEITVERKUERZUNG", "LEISTUNGSDAUER", "NAECHSTE", "ORDNUNGS_NUMMER_FUER_WAGNISZU",
                        "PRODUKTSPEZIFISCHE_ANTRAGSDATEN", "TEILDATENSATZNUMMER", "RENTE_INCL_UEBERSCHUSS",
                        "SONSTIGER_BEZUGSBERECHTIGTER_IM_", "TODESFALL", "SATZART", "SPARVORGANG", "UN", "VER"};
        for (String prefix : prefixes) {
            if (field.getName().startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    private static void addTo(Collection<Object[]> values, Bezeichner b) {
        Object[] row = new Bezeichner[1];
        row[0] = b;
        values.add(row);
    }

    /**
     * Hier werden die moeglichen technischen Namen aus der XML-Beschreibung
     * ("VUVM2018.xml") ausgelesen und gesammelt. Ergaenzt wird die Liste um
     * technische Namen, die manuell mit "VUVM2018.xml" abgeglichen wurden,
     * aber nicht ueber die Satzarten eingelesen wurden.
     *
     * @throws ParserConfigurationException im Fehlerfall
     * @throws SAXException                 im Fehlerfall
     * @throws IOException                  im Fehlerfall
     */
    @BeforeClass
    public static void readTechnischeNamen() throws ParserConfigurationException, SAXException, IOException {
        VuvmHandler handler = new VuvmHandler();
        handler.scan("src/main/resources/gdv/xport/satz/xml/VUVM2018.xml");
        TECHNISCHE_NAMEN.addAll(handler.getTechnischeNamen());
        TECHNISCHE_NAMEN.addAll(Arrays.asList(
                "KhDeckungssummenInWETeil1", "KhDeckungssummenInWETeil2", "KhDeckungssummenInWETeil3",
                "RueckkaufswertGueltigAb"));
    }

    /**
     * Dieser Test ueberprueft die technische Schreibweise eines Bezeichners.
     * Falls die technische Schreibweise richtig ist, muss sie auch in der
     * XML-Beschreibung (VUVM2018.xml) vorkommen.
     */
    @Test
    public void testTechnischerName() {
        if (!mapsTechnischerName(bezeichner.getTechnischerName())) {
            fail("wrong technischer Name: " + bezeichner);
        }
    }

    private boolean mapsTechnischerName(String name) {
        if (TECHNISCHE_NAMEN.contains(name) || TECHNISCHE_NAMEN.contains(name + "1")) {
            return true;
        }
        char lastchar = name.charAt(name.length()-1);
        if (Character.isDigit(lastchar)) {
            return mapsTechnischerName(name.substring(0, name.length()-1));
        }
        return false;
    }



    static class VuvmHandler extends DefaultHandler {

        private final Set<String> technischeNamen = new HashSet<>();
        private String elementValue;

        public void scan(String resource) throws ParserConfigurationException, SAXException, IOException {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(resource, this);
        }

        public Set<String> getTechnischeNamen() {
            return technischeNamen;
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            elementValue = new String(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if ("technischerName".equals(qName)) {
                technischeNamen.add(elementValue);
            }
        }

    }

}
