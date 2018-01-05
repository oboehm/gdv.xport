/*
 * Copyright (c) 2013 by Oli B.
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
 * (c)reated 01.04.2013 by Oli B. (oliver.boehm@gmail.com)
 */

package gdv.xport.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gdv.xport.Datenpaket;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.feld.Feld100;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

import patterntesting.runtime.annotation.IntegrationTest;
import patterntesting.runtime.annotation.RunTestOn;
import patterntesting.runtime.annotation.SkipTestOn;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * JUnit-Test fuer {@link ImportExport}.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.9 (01.04.2013)
 */
@RunWith(SmokeRunner.class)
public class ImportExportTest {

    private static Log log = LogFactory.getLog(ImportExportTest.class);

    /**
     * Dies ist ein Beispiel, wie man einen Test nicht schreiben sollte,
     * da er nur unter Windows funktionieren wird.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    @RunTestOn(osName = "Windows", hide = true)
    public void testImportSatz100Windows() throws IOException {
        File tmpFile = new File("C:\\temp", "datensatz.gdv");
        checkImportSatz100(tmpFile);
    }

    /**
     * Dies ist ein Beispiel, wie man einen Test nicht schreiben sollte,
     * da er nur unter unixoiden Betriebssystemen funktionieren wird.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    @RunTestOn(osName = {"Mac", "Linux"}, hide = true)
    public void testImportSatz100WUnix() throws IOException {
        File tmpFile = new File("/tmp", "datensatz.gdv");
        checkImportSatz100(tmpFile);
    }

    /**
     * Test-Methode fuer {@link ImportExport#importSatz100(File)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportSatz100() throws IOException {
        File tmpFile = File.createTempFile("datensatz", ".gdv");
        log.info("file \"" + tmpFile + "\" created.");
        checkImportSatz100(tmpFile);
    }

    private void checkImportSatz100(File tmpFile) throws IOException {
        try {
            ImportExport.exportSatz100(tmpFile);
            Datensatz satz100 = ImportExport.importSatz100(tmpFile);
            assertEquals(100, satz100.getSatzart());
            assertEquals("1", satz100.getFeld(Feld100.ANREDESCHLUESSEL).getInhalt());
            assertEquals("Duck", satz100.getFeld(Feld100.NAME1).getInhalt().trim());
            assertEquals("1", satz100.getFeld(Feld100.GESCHLECHT).getInhalt());
        } finally {
            tmpFile.delete();
            log.info("file \"" + tmpFile + "\" deleted.");
        }
    }

    /**
     * Test-Metude fuer {@link ImportExport#importDatenpakete(InputStream)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @IntegrationTest
    @Test
    @SkipTestOn(property = "SKIP_IMPORT_TEST", hide = true)
    public void testImportDatenpakete() throws IOException {
        InputStream istream = this.getClass().getResourceAsStream("/zwei_datenpakete.txt");
        assertNotNull(istream);
        try {
            List<Datenpaket> datenpakete = ImportExport.importDatenpakete(istream);
            assertEquals(2, datenpakete.size());
        } finally {
            istream.close();
        }
    }

}
