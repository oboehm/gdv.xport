/*
 * Copyright (c) 2010 - 2012 by Oli B.
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
 * (c)reated 11.07.2010 by Oli B. (ob@aosd.de)
 */

package gdv.xport.demo;

import gdv.xport.Datenpaket;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.xml.SatzXml;
import gdv.xport.util.GdvXmlFormatter;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import patterntesting.runtime.annotation.IntegrationTest;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;

/**
 * JUnit-Test fuer die MyUnfallDatensatz-Klasse.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.4 (11.07.2010)
 */
public final class MyUnfallDatensatzTest {

    private static final Logger LOG = LogManager.getLogger(MyUnfallDatensatzTest.class);

    /**
     * Registriert MyUnfallDatensatz und importiert dann zu Testzwecken
     * die Musterdatei.
     *
     * @throws IOException wenn die musterdatei nicht gelesen werden kann
     */
    @Test
    @Ignore
    public void testMyUnfallDatensatz() throws IOException {
        Datenpaket datenpaket = new Datenpaket();
        URL url = this.getClass().getResource("/musterdatei_041222.txt");
        try {
            // im Framework registrieren
            SatzFactory.register(MyUnfallDatensatz.class, SatzTyp.of(210, 30));
            // Datenpaket importieren
            datenpaket.importFrom(url);
            // jetzt den ersten Datensatz 210, Sparte 30 suchen und testen
            for (Datensatz datensatz : datenpaket.getDatensaetze()) {
                if ((datensatz.getSatzart() == 210) && (datensatz.getSparte() == 30)) {
                    assertEquals("EUR", datensatz.getFeld(Bezeichner.of("Baujahr")).getInhalt());
                    break;
                }
            }
        } finally {
            SatzFactory.unregister(SatzTyp.of(210, 30));
        }
    }

    @Test
    public void testBezeichner() throws XMLStreamException, IOException {
        Datensatz myDatensatz = new MyUnfallDatensatz();
        Feld baujahr = myDatensatz.getFeld(Bezeichner.of("Baujahr"));
        assertEquals("Baujahr", baujahr.getBezeichnung());
    }

    /**
     * Testet die main-Methode.
     *
     * @throws IOException falls die URL nicht erreicht werden kann
     */
    @Test
    @IntegrationTest
    public void testMain() throws IOException {
        try {
            MyUnfallDatensatz.main(new String[] {});
        } catch (UnknownHostException mayhappen) {
            LOG.warn("Offline? testMain() abgebrochen!", mayhappen);
        }
    }

    @Test
    public void testFormatter() throws IOException, XMLStreamException {
        File file = new File("target", "MyUnfalldatensatz.xml");
        Satz satz = new MyUnfallDatensatz();
        try (OutputStream ostream = new FileOutputStream(file);
             GdvXmlFormatter formatter = new GdvXmlFormatter(ostream)) {
            formatter.write(satz);
        }
        Satz imported = SatzXml.of(file);
        assertEquals(satz, imported);
    }

    /**
     * Hier setzen die SatzFactory auf den Ausgangsstand zurueck.
     *
     * @since 0.5.0
     */
    @AfterClass
    public static void restoreSatzFactory() {
        SatzFactory.unregister(SatzTyp.of("0210.030"));
    }

}

