/*
 * Copyright (c) 2018 by Oliver Boehm
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
 * (c)reated 05.10.2018 by oboehm (ob@oasd.de)
 */
package gdv.xport.util;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * Unit-Tests fuer {@link URLReader}-Klasse.
 *
 * @author oboehm
 */
public final class URLReaderTest {

    /**
     * Beim Lesen von Dateien kann es Probleme mit dem Encoding geben.
     *
     * @throws IOException bei Lesefehlern
     */
    @Test
    public void testReadFile() throws IOException {
        File file = new File("src/test/resources", "test_issue13.txt");
        URLReader urlReader = new URLReader(file.toURI().toURL());
        String content = urlReader.read().trim();
        String expected = FileUtils.readFileToString(file, StandardCharsets.UTF_8).trim();
        assertEquals(expected, content);
    }

}
