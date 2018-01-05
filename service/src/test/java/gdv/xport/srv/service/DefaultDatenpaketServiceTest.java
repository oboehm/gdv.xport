/*
 * Copyright (c) 2017 by Oliver Boehm
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
 * (c)reated 01.03.17 by oliver (ob@oasd.de)
 */
package gdv.xport.srv.service;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit-Tests DefaultDatenpaketServiceTest.
 *
 * @author oboehm
 */
public final class DefaultDatenpaketServiceTest {

    private static Logger LOG = LogManager.getLogger(DefaultDatenpaketServiceTest.class);
    private static File TEST_FILE = new File("../lib/src/test/resources/gdv/xport/test-export.txt");
    private final DatenpaketService service = new DefaultDatenpaketService();

    /**
     * Test fuer {@link DefaultDatenpaketService#validate(URI)}.
     */
    @Test
    public void testValidateUri() {
        File musterdatei = new File("../lib/src/test/resources/musterdatei_041222.txt");
        List<Model> violations = service.validate(musterdatei.toURI());
        assertEquals(0, violations.size());
    }

    /**
     * Hier testen wir die eine provozierte IO-Exception.
     */
    @Test
    public void testValidateInvalidUri() {
        URI invalidURI = new File("/invalid/uri").toURI();
        List<Model> violations = service.validate(invalidURI);
        assertEquals(1, violations.size());
        LOG.info("violations = {}", violations);
    }

    /**
     * Test-Methode fuer {@link DefaultDatenpaketService#format(URI, MimeType)}.
     * <p>
     *     Hinweis: Die verwendete Test-Datei liegt im anderen (lib-)Modul -
     *     ist etwas unschoen.
     * </p>
     *
     * @throws IOException the io exception
     */
    @Test
    public void testFormatUri() throws IOException {
        assertTrue(TEST_FILE + " does not exist", TEST_FILE.exists());
        String html = service.format(TEST_FILE.toURI(), MimeTypeUtils.TEXT_HTML);
        assertThat(html, containsString("<html"));
        assertThat(html, containsString("</html>"));
    }

    /**
     * Test-Methode fuer {@link DefaultDatenpaketService#format(String, MimeType)}.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testFormatText() throws IOException {
        String text = FileUtils.readFileToString(TEST_FILE, StandardCharsets.ISO_8859_1);
        String formatted = service.format(text, MimeTypeUtils.TEXT_PLAIN);
        assertThat(text, equalToIgnoringWhiteSpace(formatted));
    }

}
