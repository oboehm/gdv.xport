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
 * (c)reated 26.01.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.DatenpaketStreamer;
import gdv.xport.event.ImportListener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

import patterntesting.runtime.annotation.IntegrationTest;
import patterntesting.runtime.junit.FileTester;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * JUnit-Tests fuer die {@link NullFormatter}-Klasse.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.5.1 (26.01.2011)
 */
@RunWith(SmokeRunner.class)
public class NullFormatterTest {

    private static Log log = LogFactory.getLog(NullFormatterTest.class);
    private static File MUSTERDATEI = new File("src/test/resources/musterdatei_041222.txt");

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
        Writer writer = new FileWriter(output);
        NullFormatter formatter = new NullFormatter(writer);
        Datenpaket datenpaket = new Datenpaket();
        try {
            datenpaket.importFrom(MUSTERDATEI);
            formatter.write(datenpaket);
            writer.close();
            FileTester.assertContentEquals(MUSTERDATEI, output);
        } finally {
            log.info(output + " was " + (output.delete() ? "successful" : "not") + " deleted");
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
        File output = File.createTempFile("testNotice", ".txt");
        Writer writer = new FileWriter(output);
        Reader reader = new FileReader(MUSTERDATEI);
        NullFormatter formatter = new NullFormatter(writer);
        DatenpaketStreamer datenpaketStreamer = new DatenpaketStreamer(reader);
        datenpaketStreamer.register(formatter);
        try {
            datenpaketStreamer.readDatenpaket();
            reader.close();
            writer.close();
            FileTester.assertContentEquals(MUSTERDATEI, output);
        } finally {
            log.info(output + " was " + (output.delete() ? "successful" : "not") + " deleted");
        }
    }

}

