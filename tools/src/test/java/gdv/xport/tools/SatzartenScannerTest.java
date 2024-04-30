/*
 * Copyright (c) 2024 by Oli B.
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
 * (c)reated 12.04.24 by oboehm
 */

package gdv.xport.tools;

import gdv.xport.util.SatzTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-Tests fuer {@link SatzartenScanner}.
 */
class SatzartenScannerTest {

    private static final Logger log = LogManager.getLogger(SatzartenScannerTest.class);
    private final SatzartenScanner scanner = new SatzartenScanner();

    @Test
    void getURI() {
        URI uri = scanner.getURI();
        assertNotNull(uri);
    }

    @Test
    void getSatzarten() throws IOException {
        List<SatzTyp> satzarten = scanner.getSatzarten();
        assertFalse(satzarten.isEmpty());
    }

    @Test
    void exportAsProperties() throws IOException {
        File file = createFile("satzarten.properties");
        scanner.exportAsProperties(file);
        assertTrue(file.exists());
        Properties props = new Properties();
        try (FileReader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            props.load(reader);
            assertFalse(props.isEmpty());
            assertEquals(props.getProperty("0052"), "Gesch\u00e4ftsvorfall - Vorsatz");
        }
    }

    @Test
    void exportAsCSV() throws IOException {
        File file = createFile("satzarten.csv");
        scanner.exportAsCSV(file);
        assertTrue(file.exists());
    }

    private static File createFile(String filename) {
        File file = new File("target", filename);
        if (file.delete()) {
            log.info("{} wurde vor dem Test geloescht.", file);
        }
        return file;
    }

}