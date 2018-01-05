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
 * (c)reated 30.01.2014 by Oli B. (oliver.boehm@gmail.com)
 */

package gdv.xport.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.CharBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Unit-Test fuer {@link RecyclingInputStreamReader}.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.9.2 (30.01.2014)
 */
public class RecyclingInputStreamReaderTest {

    private static Log log = LogFactory.getLog(RecyclingInputStreamReaderTest.class);
    private static String HELLO = "hello world";
    private final InputStream istream = new ByteArrayInputStream(HELLO.getBytes());

    /**
     * Test method for {@link RecyclingInputStreamReader#read()}
     * and other methods of {@link RecyclingInputStreamReader}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testRead() throws IOException {
        RecyclingInputStreamReader reader = new RecyclingInputStreamReader(istream);
        try {
            assertTrue(reader.ready());
            char[] cbuf = new char[HELLO.length()];
            reader.read(cbuf);
            assertEquals(HELLO, new String(cbuf));
        } finally {
            reader.close();
            log.info(reader + " was closed.");
        }
    }

    /**
     * Hier testen wir die Recycling-Eigenschaft des Reader, ob er tatsaechlich
     * den gleichen {@link InputStream} wiederverwendet.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testRecycling() throws IOException {
        byte [] input = HELLO.getBytes();
        RecyclingInputStreamReader reader = new RecyclingInputStreamReader(istream);
        for (int i = 0; i < input.length; i++) {
            reader = new RecyclingInputStreamReader(istream);
            int ch = reader.read();
            assertEquals(input[i], ch);
        }
        reader.close();
        istream.close();
    }

    /**
     * Test-Methode fuer {@link RecyclingInputStreamReader#skip(long)} und
     * {@link RecyclingInputStreamReader#read(CharBuffer)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSkip() throws IOException {
        RecyclingInputStreamReader reader = new RecyclingInputStreamReader(istream, "ASCII");
        try {
            reader.skip(1L);
            CharBuffer cbuf = CharBuffer.allocate(HELLO.length());
            reader.read(cbuf);
            assertEquals(HELLO.substring(1), new String(cbuf.array()).trim());
        } finally {
            reader.close();
        }
    }

}

