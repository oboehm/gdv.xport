/*
 * Copyright (c) 2010 - 2014 by Oli B.
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
 * (c)reated 26.01.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.event.ImportListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import patterntesting.runtime.annotation.IntegrationTest;
import patterntesting.runtime.junit.FileTester;
import patterntesting.runtime.junit.SmokeRunner;

import java.io.*;

/**
 * JUnit-Tests fuer die {@link NullFormatter}-Klasse.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.5.1 (26.01.2011)
 */
@RunWith(SmokeRunner.class)
public class NullFormatterTest extends AbstractFormatterTest {

    private static final Logger LOG = LogManager.getLogger(NullFormatterTest.class);

    @Override
    protected AbstractFormatter createFormatter() {
        return new NullFormatter();
    }

    /**
     * Test-Methode fuer {@link NullFormatter#write(Datenpaket)}.
     * Die Ausgabe sollte dabei auf System.out geschrieben werden. Vor allem
     * sollte es nicht zu einer NPE kommen, wenn kein Writer/OutputStream
     * gesetzt ist.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testWriteDatenpaketToStdout() throws IOException {
        Datenpaket datenpaket = new Datenpaket();
        NullFormatter formatter = new NullFormatter();
        formatter.write(datenpaket);
    }

    /**
     * Test-Methode fuer {@link NullFormatter#write(Datenpaket)}.
     * Hier testen wir, ob der Formatter tatsaechlich keine Formattierung
     * vornimmt und als Result die gleiche Datei wie die Eingabe-Datei
     * rausschreibt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @IntegrationTest
    @Test
    public void testWriteDatenpaketToFile() throws IOException {
        File output = File.createTempFile("output", ".txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(output), "ISO-8859-1");
        NullFormatter formatter = new NullFormatter(writer);
        Datenpaket datenpaket = new Datenpaket();
        try {
            datenpaket.importFrom(MUSTERDATEI, "ISO-8859-1");
            formatter.write(datenpaket);
            writer.close();
            FileTester.assertContentEquals(MUSTERDATEI, output, "ISO-8859-1");
        } finally {
            LOG.info(output + " was " + (output.delete() ? "successful" : "not") + " deleted");
        }
    }

    /**
     * Test-Methode fuer {@link NullFormatter#notice(gdv.xport.satz.Satz)}.
     * Hier testen wir im Wesentlichen die Eigenschaften als
     * {@link ImportListener}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @IntegrationTest
    @Test
    public void testNotice() throws IOException {
        File output = new File("target", "testNotice.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(output), "ISO-8859-1");
        try {
            exportMusterdatei(new NullFormatter(writer));
            writer.close();
            FileTester.assertContentEquals(MUSTERDATEI, output, "ISO-8859-1");
        } finally {
            LOG.info(output + " was " + (output.delete() ? "successful" : "not") + " deleted");
        }
    }

}

