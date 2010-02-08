/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 21.11.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.util;

import java.io.*;
import java.net.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.*;

/**
 * Da mit den Hausmitteln des JDKs das Lesen einer URL nicht immer so einfach
 * ist, stuetzt sich diese Klasse dazu auf Apaches HTTP-Client ab.
 *
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.3 (21.11.2009)
 */
public class URLReader {

    private static final Log log = LogFactory.getLog(URLReader.class);
    private final URL url;

    /**
     * @param url URL, von der gelesen werden soll
     */
    public URLReader(URL url) {
        this.url = url;
    }

    /**
     * Liest die komplette URL und gibt die gelesen Seite als String zurueck.
     * 
     * @return gelesene Seite
     * @throws IOException falls die URL nicht erreichbar ist
     */
    public String read() throws IOException {
        try {
            HttpClient httpClient = new HttpClient();
            GetMethod get = new GetMethod(url.toString());
            httpClient.executeMethod(get);
            String content = get.getResponseBodyAsString();
            get.releaseConnection();
            return content;
        } catch(IllegalStateException ise) {
            log.info(ise + " - fallback to URLConnection");
            URLConnection connection = url.openConnection();
            return read(connection);
        }
    }

    private static String read(URLConnection connection) throws IOException {
        connection.connect();
        InputStream istream = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(istream));
        try {
            StringBuffer sbuf = new StringBuffer();
            while (in.ready()) {
                String line = in.readLine();
                log.debug(line);
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

