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
 * (c)reated 19.01.2014 by Oli B. (oliver.boehm@gmail.com)
 */

package gdv.xport.io;

import java.io.*;
import java.nio.CharBuffer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Dies ist ein {@link PushbackReader}, der um Eigenschaften des.
 * {@link LineNumberReader}s angereichert wurde.
 * <p>
 * Der Einfachhalt wegen wird zum Zaehlen nur das Newline-Zeichen (\n)
 * herangezogen.
 * </p>
 *
 * @see PushbackReader
 * @see LineNumberReader
 * @author oliver (ob@aosd.de)
 * @since 0.9.2 (19.01.2014)
 */
public class PushbackLineNumberReader extends PushbackReader {

    private static final Log log = LogFactory.getLog(PushbackLineNumberReader.class);
    private int lineNumber = 0;

    /**
     * Instantiates a new pushback line number reader.
     *
     * @param in the in
     */
    public PushbackLineNumberReader(final Reader in) {
        super(in);
    }

    /**
     * Instantiates a new pushback line number reader.
     *
     * @param in the in
     * @param size the size
     */
    public PushbackLineNumberReader(final Reader in, final int size) {
        super(in, size);
    }

    /* (non-Javadoc)
     * @see java.io.PushbackReader#read()
     */
    @Override
    public int read() throws IOException {
        int ch = super.read();
        if (ch == '\n') {
            this.lineNumber++;
        }
        return ch;
    }

    /* (non-Javadoc)
     * @see java.io.Reader#read(char[])
     */
    @Override
    public int read(char[] cbuf) throws IOException {
        return this.read(cbuf, 0, cbuf.length);
    }

    /* (non-Javadoc)
     * @see java.io.Reader#read(java.nio.CharBuffer)
     */
    @Override
    public int read(CharBuffer target) throws IOException {
        int ret = super.read(target);
        countLineNumber(target.array());
        return ret;
    }

    private void countLineNumber(char[] cbuf) {
        for (int i = 0; i < cbuf.length; i++) {
            if (cbuf[i] == '\n') {
                this.lineNumber++;
            }
        }
    }

    /* (non-Javadoc)
     * @see java.io.PushbackReader#read(char[], int, int)
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int ret = super.read(cbuf, off, len);
        for (int i = off; i < off + len; i++) {
            if (cbuf[i] == '\n') {
                this.lineNumber++;
            }
        }
        return ret;
    }

    /* (non-Javadoc)
     * @see java.io.PushbackReader#reset()
     */
    @Override
    public void reset() throws IOException {
        super.reset();
        this.lineNumber = 0;
    }

    /* (non-Javadoc)
     * @see java.io.PushbackReader#unread(char[], int, int)
     */
    @Override
    public void unread(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            if (cbuf[i] == '\n') {
                this.lineNumber--;
            }
        }
        super.unread(cbuf, off, len);
    }

    /* (non-Javadoc)
     * @see java.io.PushbackReader#unread(char[])
     */
    @Override
    public void unread(char[] cbuf) throws IOException {
        this.unread(cbuf, 0, cbuf.length);
    }

    /* (non-Javadoc)
     * @see java.io.PushbackReader#unread(int)
     */
    @Override
    public void unread(int c) throws IOException {
        if (c == '\n') {
            this.lineNumber--;
        }
        super.unread(c);
    }

    /**
     * Gets the line number.
     *
     * @return the line number
     */
    public int getLineNumber() {
        return this.lineNumber;
    }

    /**
     * Wenn das naechste Zeichen das Zeilenende ist, wird es uebersprungen.
     * Ansonsten wird es wieder in den Eingabepuffer zurueckgestellt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void skipNewline() throws IOException {
        char[] cbuf = new char[1];
        do {
            if (this.read(cbuf) == -1) {
                log.info("end of file detected");
                return;
            }
        } while ((cbuf[0] == '\n') || (cbuf[0] == '\r'));
        this.unread(cbuf);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "line " + this.lineNumber;
    }

}

