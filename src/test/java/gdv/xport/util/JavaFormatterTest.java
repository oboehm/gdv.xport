/*
 * Copyright (c) 2011, 2012 by Oli B.
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
 * (c)reated 27.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import static org.junit.Assert.*;
import gdv.xport.Datenpaket;
import gdv.xport.satz.*;
import gdv.xport.satz.model.Satz210;

import java.io.*;
import java.text.MessageFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.*;
import org.apache.commons.logging.*;
import org.junit.*;
import org.junit.runner.RunWith;

import patterntesting.concurrent.junit.ParallelRunner;

/**
 * JUnit-Test fuer JavaFormatter.
 * 
 * @author oliver (ob@aosd.de)
 * @since 27.03.2011
 */
@RunWith(ParallelRunner.class)
public final class JavaFormatterTest extends AbstractFormatterTest {
    
    private static final Log log = LogFactory.getLog(JavaFormatterTest.class);
    private static final File distDir = new File("target/generated-sources");
    private Datenpaket datenpaket = new Datenpaket();
    
    /**
     * Creates the dist dir.
     */
    @BeforeClass
    public static void createDistDir() {
        if (distDir.mkdirs()) {
            log.info("created: " + distDir.getAbsolutePath());
        }
    }
    
    /**
     * Test method for {@link JavaFormatter#write(gdv.xport.Datenpaket)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testWriteDatenpaket() throws IOException {
        StringWriter swriter = new StringWriter();
        JavaFormatter formatter = new JavaFormatter(swriter);
        formatter.write(datenpaket);
        swriter.close();
        log.trace(swriter);
        assertTrue("empty result!", StringUtils.isNotEmpty(swriter.toString()));
    }
    
    /**
     * Test-Methode fuer {@link JavaFormatter#write(gdv.xport.satz.Satz)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testWriteSatz() throws IOException {
        StringWriter swriter = new StringWriter();
        JavaFormatter formatter = new JavaFormatter(swriter);
        formatter.write(new Satz210(10));
        swriter.close();
        log.trace(swriter.toString());
        String satz = getResource("Feld210.txt");
        assertEquals(satz, swriter.toString());
    }
    
    /**
     * Test to string.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testToString() throws IOException {
        String vorsatz = getResource("Feld1.txt");
        String nachsatz = getResource("Feld9999.txt");
        String expected = vorsatz + nachsatz;
        assertEquals(expected, JavaFormatter.toString(datenpaket));
    }

    private static String getResource(final String name) throws IOException {
        InputStream istream = JavaFormatterTest.class.getResourceAsStream(name);
        try {
            String template = IOUtils.toString(istream);
            return MessageFormat.format(template, new Date(), SystemUtils.USER_NAME);
        } finally {
            istream.close();
        }
    }
    
    /**
     * Tested das Exportieren einer (normalen) Java-Enum-Klasse.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testToDirVorsatz() throws IOException {
        Satz satz = new Vorsatz();
        generateClass(satz, "gdv/xport/satz/feld/Feld1.java");
    }

    /**
     * Tested das Exportieren einer Java-Enum-Klasse fuer eine Satzart mit
     * Sparte.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testToDirSatzWithSparte() throws IOException {
        Satz satz = new Satz210(10);
        generateClass(satz, "gdv/xport/satz/feld/sparte10/Feld210.java");
    }

    private void generateClass(final Satz satz, final String javaFilename) throws IOException {
        JavaFormatter.toDir(distDir, satz);
        File expected = new File(distDir, javaFilename);
        assertTrue("not found: " + expected, expected.exists());
    }

}

