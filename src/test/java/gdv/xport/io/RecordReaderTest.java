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
 * (c)reated 03.02.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.io;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

/**
 * Unit-Tests fuer {@link RecordReader}.
 *
 * @author oliver
 * @since 0.9.3 (03.02.2014)
 */
public class RecordReaderTest {

    /**
     * Test-Methode fuer {@link gdv.xport.io.RecordReader#read()}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testRead() throws IOException {
        RecordReader reader = new RecordReader(new StringReader("abc\n"));
        try {
            assertEquals('a', reader.read());
            assertEquals('b', reader.read());
            assertEquals('c', reader.read());
            for (int i = 3; i < 256; i++) {
                assertEquals(i + ". character", ' ', reader.read());
            }
            assertEquals('\n', reader.read());
        } finally {
            reader.close();
        }
    }

    /**
     * Ein Record wird meistens mit einem Zeilenvorschub abgeschlossen. Das
     * muss aber nicht sein. Deswegen wird dieser Fall hier getestet.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testReadWithoutNewline() throws IOException {
        char[] line1 = createRecord('a');
        char[] line2 = createRecord('b');
        RecordReader reader = new RecordReader(new StringReader(new String(line1) + new String(line2)));
        assertRecord(line1, reader);
        assertRecord(line2, reader);
    }

    /**
     * Hier machen wir die Gegenprobe und testen, ob Records mit einem
     * Zeilenvorschub am Ende richtig verarbeitet werden.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testReadWithNewline() throws IOException {
        char[] line1 = createRecord('c');
        char[] line2 = createRecord('d');
        RecordReader reader = new RecordReader(new StringReader(new String(line1) + '\n' + new String(line2)));
        try {
            assertRecord(line1, reader);
            assertEquals("newline expected", '\n', reader.read());
            assertRecord(line2, reader);
        } finally {
            reader.close();
        }
    }

    private char[] createRecord(final char filler) {
        char[] record = new char[256];
        for (int i = 0; i < record.length; i++) {
            record[i] = filler;
        }
        return record;
    }

    private void assertRecord(final char[] expected, final RecordReader reader) throws IOException {
        for (int i = 0; i < 256; i++) {
            assertEquals("character " + i, expected[i], (char) reader.read());
        }
    }

    /**
     * Test-Methode fuer {@link RecordReader#read(char[])}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testReadCharArray() throws IOException {
        char[] line = new char[256];
        String input = "Hello world!\n";
        RecordReader reader = new RecordReader(new StringReader(input));
        try {
            reader.read(line);
            assertEquals(input.trim(), new String(line).trim());
        } finally {
            reader.close();
        }
    }

}
