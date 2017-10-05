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

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.nio.CharBuffer;

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
    public int read(final char[] cbuf) throws IOException {
        return this.read(cbuf, 0, cbuf.length);
    }

    /* (non-Javadoc)
     * @see java.io.Reader#read(java.nio.CharBuffer)
     */
    @Override
    public int read(final CharBuffer target) throws IOException {
        int ret = super.read(target);
        countLineNumber(target.array());
        return ret;
    }

    private void countLineNumber(final char[] cbuf) {
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
    public int read(final char[] cbuf, final int off, final int len) throws IOException {
        int ret = super.read(cbuf, off, len);
        for (int i = 0; i < ret; i++) {
            if (cbuf[off+i] == '\n') {
                this.lineNumber++;
            }
        }
        return ret;
    }

    /**
     * Read line.
     *
     * @return the string
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public String readLine() throws IOException {
        char[] buf = new char[257];
        int len = 257;
        for (int i = 0; i < len; i++) {
            int ch = this.read();
            if ((ch == -1) || (ch == '\n') || (ch == '\r') || (ch == 0)) {
                len = i;
                break;
            }
            buf[i] = (char) ch;
        }
        return new String(buf).substring(0, len);
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
    public void unread(final char[] cbuf, final int off, final int len) throws IOException {
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
    public void unread(final char[] cbuf) throws IOException {
        this.unread(cbuf, 0, cbuf.length);
    }

    /* (non-Javadoc)
     * @see java.io.PushbackReader#unread(int)
     */
    @Override
    public void unread(final int c) throws IOException {
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
        skip('\n', '\r');
    }

    /**
     * Wenn das naechste Zeichen in Leerzeichen oder Zeilenende ist, wird es
     * uebersprungen. Ansonsten wird es wieder in den Eingabepuffer
     * zurueckgestellt.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void skipWhitespace() throws IOException {
        skip(' ', '\n', '\r', '\t');
    }

    /**
     * If the next characters in the input stream is one of the given
     * characters these characters will be skipped.
     *
     * @param chars the chars
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void skip(final char... chars) throws IOException {
        char[] cbuf = new char[1];
        while (this.read(cbuf) != -1) {
            if (!isInArray(cbuf[0], chars)) {
                this.unread(cbuf);
                break;
            }
        }
    }

    private boolean isInArray(final char ch, final char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (ch == chars[i]) {
                return true;
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "line " + this.lineNumber;
    }

}

