/*
 * Copyright (c) 2013 by Oli B.
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
 * (c)reated 26.01.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.io;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

/**
 * Im Gegensatz zum normalen liefert verwendet diese Klasse immer den gleichen.
 * {@link Reader} fuer einen bereits verwendeten {@link InputStream}. Dies
 * vermeidet Probleme, wenn der Constructor mehrfach mit dem gleichen
 * {@link InputStream} aufgerufen wird, um den Original-Stream mit mehreren
 * Readern bis zum Ende lesen zu koennen.
 *
 * @author oliver
 * @since 0.9.2 (26.01.2014)
 * @see InputStreamReader
 */
public class RecyclingInputStreamReader extends Reader {

    /** The Constant cachedReaders. */
    private static final Map<InputStream, Reader> cachedReaders = new WeakHashMap<InputStream, Reader>();

    /** The reader. */
    private final Reader reader;

    /**
     * Instantiates a new recycling input stream reader.
     *
     * @param in the in
     */
    public RecyclingInputStreamReader(InputStream in) {
        this(in, Charset.defaultCharset());
    }

    /**
     * Instantiates a new recycling input stream reader.
     *
     * @param in the in
     * @param charsetName the charset name
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public RecyclingInputStreamReader(InputStream in, String charsetName) throws UnsupportedEncodingException {
        this.reader = getReaderFor(in, Charset.forName(charsetName));
    }

    /**
     * Instantiates a new recycling input stream reader.
     *
     * @param in the in
     * @param cs the cs
     */
    public RecyclingInputStreamReader(InputStream in, Charset cs) {
        this.reader = getReaderFor(in, cs);
    }

    /**
     * Gets the reader for.
     *
     * @param in the in
     * @param cs the cs
     * @return the reader for
     */
    private static Reader getReaderFor(InputStream in, Charset cs) {
        Reader r = cachedReaders.get(in);
        if (r == null) {
            r = new InputStreamReader(in, cs);
            cachedReaders.put(in, r);
        }
        return r;
    }

    /**
     * Removes the reader.
     *
     * @param r the r
     */
    private synchronized void removeReader(Reader r) {
        for (Entry<InputStream, Reader> entry : cachedReaders.entrySet()) {
            if (r.equals(entry.getValue())) {
                cachedReaders.remove(entry.getKey());
                break;
            }
        }
    }

    /* (non-Javadoc)
     * @see java.io.Reader#close()
     */
    @Override
    public void close() throws IOException {
        reader.close();
        removeReader(reader);
    }

    /* (non-Javadoc)
     * @see java.io.Reader#read(char[], int, int)
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return this.reader.equals(obj);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.reader.hashCode();
    }

    /* (non-Javadoc)
     * @see java.io.Reader#mark(int)
     */
    @Override
    public void mark(int readAheadLimit) throws IOException {
        this.reader.mark(readAheadLimit);
    }

    /* (non-Javadoc)
     * @see java.io.Reader#markSupported()
     */
    @Override
    public boolean markSupported() {
        return this.reader.markSupported();
    }

    /* (non-Javadoc)
     * @see java.io.Reader#read()
     */
    @Override
    public int read() throws IOException {
        return this.reader.read();
    }

    /* (non-Javadoc)
     * @see java.io.Reader#read(char[])
     */
    @Override
    public int read(char[] cbuf) throws IOException {
        return this.reader.read(cbuf);
    }

    /* (non-Javadoc)
     * @see java.io.Reader#read(java.nio.CharBuffer)
     */
    @Override
    public int read(CharBuffer target) throws IOException {
        return this.reader.read(target);
    }

    /* (non-Javadoc)
     * @see java.io.Reader#ready()
     */
    @Override
    public boolean ready() throws IOException {
        return this.reader.ready();
    }

    /* (non-Javadoc)
     * @see java.io.Reader#reset()
     */
    @Override
    public void reset() throws IOException {
        this.reader.reset();
    }

    /* (non-Javadoc)
     * @see java.io.Reader#skip(long)
     */
    @Override
    public long skip(long n) throws IOException {
        return this.reader.skip(n);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " with " + reader;
    }

}
