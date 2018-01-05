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

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Unit-Tests fuer {@link Config}-Klasse.
 *
 * @author oliver
 * @since 08.10.2009
 * @version $Revision$
 */
public class ConfigTest {

    private static final Logger LOG = LogManager.getLogger(ConfigTest.class);

    /**
     * Test method for {@link gdv.xport.config.Config#getVUNummer()}:
     * Entweder ist die entsprechende Property gesetzt, oder wir erwarten hier
     * eine ConfigException.
     */
    @Test
    public void testGetVUnummer() {
        Config.reset();
        String vuNummer = System.getProperty(Config.GDV_VU_NUMMER);
        if (StringUtils.isEmpty(vuNummer)) {
            try {
                Config.getVUNummer();
            } catch (ConfigException expected) {
                LOG.info("expected: " + expected);
            }
        } else {
            assertEquals(vuNummer, Config.getVUNummer().getInhalt().trim());
        }
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

}
