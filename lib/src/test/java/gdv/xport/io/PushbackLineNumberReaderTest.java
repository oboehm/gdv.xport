/*
 * Copyright (c) 2014 by Oli B.
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
 * (c)reated 17.01.2015 by Oli B. (ob@aosd.de)
 */

package gdv.xport.io;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit-Tests fuer den {@link PushbackLineNumberReader}.
 *
 * @author oliver
 * @since 1.0 (17.01.2015)
 */
public final class PushbackLineNumberReaderTest {

    private PushbackLineNumberReader lineNumberReader;

    /**
     * Sets the up line number reader.
     */
    @Before
    public void setUpLineNumberReader() {
        StringReader reader = new StringReader("hello\nworld");
        lineNumberReader = new PushbackLineNumberReader(reader);
    }

    /**
     * Test method for {@link PushbackLineNumberReader#read()}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testRead() throws IOException {
        assertEquals('h', lineNumberReader.read());
    }

    /**
     * Test method for {@link PushbackLineNumberReader#unread(int)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testUnreadInt() throws IOException {
        assertEquals('h', lineNumberReader.read());
        lineNumberReader.unread('h');
        assertEquals('h', lineNumberReader.read());
    }

    /**
     * Test method for {@link PushbackLineNumberReader#readLine()}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testReadLine() throws IOException {
        String line = lineNumberReader.readLine();
        assertEquals("hello", line);
    }

}
