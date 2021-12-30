/*
 * Copyright (c) 2009 - 2012 by Oli B.
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
 * (c)reated 08.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.config;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import patterntesting.runtime.junit.ObjectTester;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Unit-Tests fuer {@link Config}-Klasse.
 *
 * @author oliver
 * @since 08.10.2009
 * @version $Revision$
 */
public class ConfigTest {

    private static final Logger LOG = LogManager.getLogger(ConfigTest.class);
    private final Config config = new Config();

    /**
     * Test method for {@link Config#getVUNr()} ()}.
     */
    @Test
    public void testGetVUnummer() {
        Config newConfig = config.withProperty(Config.GDV_VU_NUMMER, "James");
        assertEquals("James", newConfig.getVUNr().getInhalt().trim());
    }

    /**
     * Hier testen wir, ob das File-Encoding auf ISO-8859-1 eingestellt ist.
     * Falls nicht, wird der Test fehlschlagen. In diesem Fall kann man
     * versuchen, bei der Run-Konfiguration die VM mit <tt>java
     * -Dfile.encoding=ISO-8859-1 ...</tt> aufzurufen. Aber normalerweise
     * sollte die Config-Klasse das Encoding richtig einstellen.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testEncoding() throws IOException {
        InputStream istream = ConfigTest.class.getResourceAsStream("umlaute.txt");
        assertNotNull(istream);
        try {
            String umlaute = IOUtils.toString(istream, Config.DEFAULT_ENCODING_NAME).trim();
            String expected = "\u00e4\u00f6\u00fc\u00df\u00c4\u00d6\u00dc";
            LOG.info("expected = \"" + expected + "\"");
            assertEquals(expected, umlaute);
        } finally {
            istream.close();
        }
    }

    @Test
    public void testEOD() {
        String eod = config.getString("gdv.eod");
        assertEquals("\n", eod);
    }

    @Test
    public void testEquals() {
        Config a = new Config();
        Config b = new Config();
        ObjectTester.assertEquals(a, b);
    }

    @Test
    public void testConfigFromResource() throws IOException {
        Config c1 = new Config("experimental.properties");
        File experimentalFile = new File("src/main/resources/gdv/xport/config/experimental.properties");
        File gdvFile = new File("target/test-classes", "gdv.properties");
        try {
            FileUtils.copyFile(experimentalFile, gdvFile);
            LOG.info("{} wurde nach {} kopiert.", experimentalFile, gdvFile);
            Config c2 = new Config();
            assertEquals(c1, c2);
        } finally {
            if (gdvFile.delete()) {
                LOG.info("{} wurde wieder geloescht.", gdvFile);
            }
        }
    }

    @Test
    public void testConfigFromFile() {
        String resource = "/gdv/xport/config/experimental.properties";
        File file = new File("src/main/resources", resource);
        assertTrue(file.exists());
        String fileURI = file.toURI().toString();
        Config c1 = new Config(resource);
        Config c2 = new Config(fileURI);
        assertEquals(c1, c2);
    }

    @Test
    public void testConfigFromClasspath() {
        String resource = "/gdv/xport/config/experimental.properties";
        String classpathURI = "classpath:" + resource;
        Config c1 = new Config(resource);
        Config c2 = new Config(classpathURI);
        assertEquals(c1, c2);
    }

    @Test
    public void testGetValidateMode() {
        Config c = Config.EMPTY.withProperty("gdv.feld.validate", "true");
        assertEquals(Config.ValidateMode.LAX, c.getValidateMode());
    }

}
