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
 * (c)reated 21.11.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.config.Config;
import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Da mit den Hausmitteln des JDKs das Lesen einer URL nicht immer so einfach
 * ist, stuetzt sich diese Klasse dazu auf Apaches HTTP-Client ab.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.3 (21.11.2009)
 */
public class URLReader {

    private static final Logger LOG = LogManager.getLogger(URLReader.class);
    private final URI url;

    /**
     * @param url URL, von der gelesen werden soll
     */
    public URLReader(final URL url) {
        this(URI.create(url.toString()));
    }

    public URLReader(final URI uri) {
        this.url = uri;
    }

    /**
     * Liest die komplette URL und gibt die gelesen Seite als String zurueck.
     *
     * @return gelesene Seite
     * @throws IOException falls die URL nicht erreichbar ist
     */
    public String read() throws IOException {
        if (url.getScheme().equals("file")) {
            return readFile();
        }
        try {
            Content content = Request.get(url).execute().returnContent();
            return content.asString();
        } catch(IOException ioe) {
            LOG.info(ioe + " - fallback to URLConnection");
            URLConnection connection = url.toURL().openConnection();
            return read(connection);
        }
    }

    @SuppressWarnings("javasecurity:S2083")
    private String readFile() throws IOException {
        File file = new File(url);
        return FileUtils.readFileToString(file, Config.DEFAULT_ENCODING);
    }

    private static String read(final URLConnection connection) throws IOException {
        connection.connect();
        InputStream istream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(istream));
        try {
            StringBuilder sbuf = new StringBuilder();
            while (in.ready()) {
                String line = in.readLine();
                LOG.debug(line);
                sbuf.append(line);
                sbuf.append("\n");
            }
        return sbuf.toString().trim();
        } finally {
            in.close();
            istream.close();
        }
    }

}

