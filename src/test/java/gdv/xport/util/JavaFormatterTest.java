/*
 * Copyright (c) 2011 by agentes
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
 * (c)reated 27.03.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.util;

import static org.junit.Assert.*;
import gdv.xport.Datenpaket;
import gdv.xport.satz.Nachsatz;

import java.io.*;
import java.text.MessageFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.*;
import org.apache.commons.logging.*;
import org.junit.Test;

/**
 * JUnit-Test fuer JavaFormatter.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 27.03.2011
 */
public class JavaFormatterTest extends AbstractFormatterTest {
    
    private static final Log log = LogFactory.getLog(JavaFormatterTest.class);
    private Datenpaket datenpaket = new Datenpaket();

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
        formatter.write(new Nachsatz());
        swriter.close();
        String nachsatz = getResource("Feld9999.txt");
        assertEquals(nachsatz, swriter.toString());
    }
    
    /**
     * Test to string.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testToString() throws IOException {
        String vorsatz = getResource("Feld0001.txt");
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

}

