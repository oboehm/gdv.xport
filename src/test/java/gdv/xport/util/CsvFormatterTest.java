/*
 * Copyright (c) 2016 by Oli B.
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
 * (c)reated 06.06.2016 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.Datenpaket;
import gdv.xport.satz.Satz;
import patterntesting.runtime.junit.FileTester;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Unit-Test for {@link CsvFormatter}.
 *
 * @author oliver
 * @since 1.2 (06.06.2016)
 */
@RunWith(SmokeRunner.class)
public final class CsvFormatterTest extends AbstractFormatterTest {

    /**
     * Basis-Test fuer {@link CsvFormatter#write(Satz)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testWriteSatz() throws IOException {
        File output = new File("target", "vorsatz.csv");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(output), "ISO-8859-1")) {
            CsvFormatter formatter = new CsvFormatter(writer);
            Satz satz = MUSTER_DATENPAKET.getVorsatz();
            formatter.write(satz);
        }
        List<String> lines = FileUtils.readLines(output);
        assertEquals(2, lines.size());
        assertEquals("Satzart;", lines.get(0).substring(0, 8));
        File vorsatz = new File("src/test/resources/gdv/xport/util/vorsatz.csv");
        FileTester.assertContentEquals(vorsatz, output);
    }

    /**
     * Test-Methode fuer {@link CsvFormatter#write(Datenpaket)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    //@Broken(why = "implementation not finished", till = "01-Jul-2016")
    public void testWriteDatenpaket() throws IOException {
        File output = new File("target", "musterdatei.csv");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(output), "ISO-8859-1")) {
            CsvFormatter formatter = new CsvFormatter(writer);
            formatter.write(MUSTER_DATENPAKET);
        }
        int n = getNumberOfLines(output);
        assertEquals(129, n);
    }

    private int getNumberOfLines(File file) throws IOException {
        return FileUtils.readLines(file).size();
    }

}
