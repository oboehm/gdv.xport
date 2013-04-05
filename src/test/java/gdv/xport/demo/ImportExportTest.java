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
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.feld.Feld100;

import java.io.*;

import org.apache.commons.logging.*;
import org.junit.Test;

/**
 * JUnit-Test fuer {@link ImportExport}.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.9 (01.04.2013)
 */
public class ImportExportTest {

    private static Log log = LogFactory.getLog(ImportExportTest.class);

    /**
     * Test-Methode fuer {@link ImportExport#importSatz100(File)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testImportSatz100() throws IOException {
        File tmpFile = File.createTempFile("datensatz", ".gdv");
        log.info("file \"" + tmpFile + "\" created.");
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

}
