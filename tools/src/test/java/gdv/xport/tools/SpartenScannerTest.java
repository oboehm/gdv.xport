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
 * (c)reated 30.04.24 by oboehm
 */
package gdv.xport.tools;

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
 * Unit-Tests fuer gdv.xport.tools.SpartenScanner.
 *
 * @author oboehm
 */
class SpartenScannerTest {

    private final SpartenScanner scanner = new SpartenScanner();

    @Test
    void getURI() {
        URI uri = scanner.getURI();
        assertNotNull(uri);
    }

    @Test
    void getSparten() throws IOException {
        List<Integer> sparten = scanner.getSparten();
        assertFalse(sparten.isEmpty());
    }

    @Test
    void exportAsProperties() throws IOException {
        File file = new File("target", "sparten.properties");
        scanner.exportAsProperties(file);
        assertTrue(file.exists());
        Properties props = new Properties();
        try (FileReader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            props.load(reader);
            assertFalse(props.isEmpty());
            assertEquals(props.getProperty("120"), "Sturmversicherung");
        }
    }

    @Test
    void exportAsGruppenProperties() throws IOException {
        File file = new File("target", "sparten-gruppen.properties");
        scanner.exportAsGruppenProperties(file);
        assertTrue(file.exists());
    }

}