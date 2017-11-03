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
 * (c)reated 14.11.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.feld.Align;
import gdv.xport.feld.Feld;
import gdv.xport.satz.Nachsatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.Vorsatz;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xml.sax.SAXException;
import patterntesting.runtime.annotation.IntegrationTest;
import patterntesting.runtime.junit.SmokeRunner;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * JUnit-Test fuer XmlFormatter.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.2 (14.11.2009)
 */
@RunWith(SmokeRunner.class)
public class XmlFormatterTest extends AbstractFormatterTest {

    private static final Logger LOG = LogManager.getLogger(XmlFormatterTest.class);

    @Override
    protected AbstractFormatter createFormatter() {
        return new XmlFormatter();
    }

    /**
     * Test method for {@link gdv.xport.util.XmlFormatter#write(gdv.xport.feld.Feld)}.
     * @throws XMLStreamException falls was schiefgelaufen ist
     */
    @Test
    public void testWriteFeld() throws XMLStreamException {
        Feld x = new Feld("Hello", "World", Align.LEFT);
        String xmlString = XmlFormatter.toString(x);
        LOG.info("{} as XML: {}", x, xmlString);
        checkXML(xmlString);
    }

    /**
     * Testet den Export eines Teildatensatzes als XML.
     * @throws XMLStreamException falls was schiefgelaufen ist
     */
    @Test
    public void testWriteTeildatensatz() throws XMLStreamException {
        Teildatensatz teildatensatz = new Vorsatz().getTeildatensatz(1);
        String xmlString = XmlFormatter.toString(teildatensatz);
        LOG.info(teildatensatz + " as XML:\n" + xmlString);
        checkXML(xmlString);
    }

    /**
     * Testet den Export eines Satzes als XML.
     * @throws XMLStreamException falls was schiefgelaufen ist
     */
    @Test
    public void testWriteSatz() throws XMLStreamException {
        Satz satz = new Nachsatz();
        String xmlString = XmlFormatter.toString(satz);
        LOG.info(satz + " as XML:\n" + xmlString);
        checkXML(xmlString);
    }

    /**
     * Tested den Export eines Datenpakets als XML.
     *
     * @throws XMLStreamException falls was schiefgelaufen ist
     * @throws SAXException falls was schiefgelaufen ist
     * @throws IOException falls was schiefgelaufen ist
     */
    @Test
    public void testWriteDatenpaket() throws XMLStreamException, SAXException, IOException {
        Datenpaket datenpaket = new Datenpaket();
        String xmlString = XmlFormatter.toString(datenpaket);
        LOG.debug(datenpaket + " as XML:\n" + xmlString);
        checkXML(xmlString);
        XmlHelper.validate(xmlString, "/xsd/datenpaket.xsd");
    }

    /**
     * Tested die Formattierung der Musterdatei als HTML.
     *
     * @throws XMLStreamException falls was schiefgelaufen ist
     * @throws IOException falls was schiefgelaufen ist
     */
    @Test
    @IntegrationTest
    public void testMusterdatei() throws IOException, XMLStreamException {
        exportMusterdatei(new XmlFormatter(), "musterdatei_041222.xml");
    }

    /**
     * Hier testen wir die Eignung des {@link XmlFormatter} als
     * {@link gdv.xport.event.ImportListener}.
     *
     * @throws IOException falls was schiefgelaufen ist
     */
    @Test
    @IntegrationTest
    public void testNotice() throws IOException {
        checkNotice(new XmlFormatter(), "musterdatei_041222.xml");
    }

    /**
     * Beim Testen ist aufgefallen, dass der Default-Konstruktor mit einer
     * {@link NullPointerException} aussteigt. Dies sollte natuerlich nicht
     * passieren.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testXmlFormatter() throws IOException {
        XmlFormatter formatter = new XmlFormatter();
        formatter.write(new Datenpaket("4711"));
    }

}

