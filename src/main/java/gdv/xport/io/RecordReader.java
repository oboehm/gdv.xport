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

import java.io.IOException;
import java.io.Reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Ein Record enthaelt hier immer genau 256 Bytes. Danach kommt i.d.R. ein
 * Zeilenvorschub. Es gibt aber auch Kandidaten, die es mit den 256 Bytes
 * nicht so genau nehmen und Leerstellen am Ende eines Records einfach
 * abschneiden. Damit gdv.xport damit klarkommt, werden fuer diese Faelle
 * der Record einfach mit Leerzeichen aufgefuellt.
 *
 * @author oliver
 * @since 0.9.3 (03.02.2014)
 */
public class RecordReader extends Reader {

    private static final Logger LOG = LogManager.getLogger(RecordReader.class);
    private final Reader reader;
    private int pos = 257;
    private final int[] buffer = new int[257];
    private int recordNo = 0;

    /**
     * Instantiates a new record reader.
     *
     * @param in the in
     */
    public RecordReader(Reader in) {
        super();
        this.reader = in;
    }

    /* (non-Javadoc)
     * @see java.io.Reader#read()
     */
    @Override
    public int read() throws IOException {
        if (this.isBufferEmpty()) {
            this.fillBuffer();
        }
        return this.buffer[pos++];
    }

    /* (non-Javadoc)
     * @see java.io.Reader#read(char[], int, int)
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int nrOfChars = 0;
        if (len == 0) return 0;
        for (int i = off; i < (off + len); i++) {
            int c = this.read();
            if (c == -1) break;
            cbuf[i] = (char) c;
            nrOfChars++;
        }
        return nrOfChars > 0 ? nrOfChars : -1;
    }

    private void fillBuffer() throws IOException {
        buffer[256] = 0;
        pos = 0;
        recordNo++;
        for (int i = 0; i < 256; i++) {
            int ch = this.reader.read();
            if ((ch == '\n') || (ch == '\r') || (ch == -1)) {
                buffer[256] = ch;
                if (i == 0) {
                    pos = 256;
                    break;
                }
                LOG.info("Record " + recordNo + " has only " + i + " characters and is filled with " + (256 - i)
                        + " spaces.");
                fillBufferWithSpaces(i);
                break;
            }
            buffer[i] = ch;
        }
    }

    private void fillBufferWithSpaces(int start) {
        for (int i = start; i < 256; i++) {
            buffer[i] = ' ';
        }
    }

    private boolean isBufferEmpty() {
        return (pos >= buffer.length) || (buffer[pos] == 0);
    }

    /* (non-Javadoc)
     * @see java.io.Reader#skip(long)
     */
    @Override
    public long skip(final long n) throws IOException {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /* (non-Javadoc)
     * @see java.io.Reader#close()
     */
    @Override
    public void close() throws IOException {
        this.reader.close();
    }

}
