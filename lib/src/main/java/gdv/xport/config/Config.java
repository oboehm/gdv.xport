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
 * (c)reated 08.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.config;

import gdv.xport.feld.VUNummer;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Ueber diese Klasse koennen globale Werte (wie z.B. die VU-Nummer) konfiguriert
 * (d.h. gesetzt) und auch abgefragt werden.
 * Ueblicherweise sollten diese Werte am Anfang programmatisch gesetzt werden.
 * Alternativ koennen sie auch ueber System-Properties konfiguriert werden.
 *
 * @author oliver
 * @since 08.10.2009
 */
public final class Config {

    private static final Logger LOG = LogManager.getLogger(Config.class);
    private static VUNummer vunummer;
    private static String eod = "\n";

    /** Standard-Encoding ist "ISO-8859-1". */
    public static final Charset DEFAULT_ENCODING = StandardCharsets.ISO_8859_1;
    /** Standard-Encoding als String. */
    public static final String DEFAULT_ENCODING_NAME = DEFAULT_ENCODING.toString();
    /** Falls VUNummer nicht gesetzt ist, wird dies als Dummy eingesetzt. */
    public static final String DUMMY_VU_NUMMER = "DUMMY";
    /** Property-Name fuer die VU-Nummer. */
    public static final String GDV_VU_NUMMER = "gdv.VU-Nummer";
    /** Default-Konfiguration fuer v5. */
    public static final Config V5 = new Config("/gdv/xport/config/default.properties");
    /** Default-Konfiguration fuer v6. */
    public static final Config V6 = new Config("/gdv/xport/config/default6.properties");

    private final Properties properties;

    public static Config getDefault() {
        return V5;
    }

    /**
     * Zum Testen mit einer Standard-Konfiguration.
     * Ueber "-Dgdv.config=meine.properties" kann man eine andere
     * Resource fuer die Standard-Konfiguration einstellen.
     *
     * @since 5.3
     */
    public Config() {
        this(System.getProperty("gdv.config", "/gdv/xport/config/default.properties"));
    }

    /**
     * Moechte man eine andere Konfiguration, kann man hierueber eine
     * alternative Resource angeben.
     *
     * @param resource z.B. "/gdv/xport/config/default6.properties"
     * @since 5.3
     */
    public Config (String resource) {
        this(loadProperties(resource));
    }

    private Config(Properties props) {
        this.properties = props;
        this.properties.putAll(System.getProperties());
    }

    private static Properties loadProperties(String resource) {
        Properties properties = new Properties();
        try (InputStream input = Config.class.getResourceAsStream(resource)) {
            if (input == null) {
                throw new IllegalArgumentException(String.format("Resource '%s' exitiert nicht", resource));
            }
            LOG.info("Properties werden aus '{}' eingelesen.", resource);
            properties.load(input);
            return properties;
        } catch (IOException ex) {
            throw new IllegalArgumentException(String.format("'%s' ist fehlerhaft", ex));
        }
    }

    public String getProperty(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    public boolean getBoolProperty(String key) {
        return Boolean.parseBoolean(getProperty(key, "false"));
    }

    /**
     * Diese Methode dient zwar hauptsaechlich zu Testzwecken, kann aber auch
     * aufgerufen werden, wenn man nicht mehr sicher ist, was denn alles
     * konfiguriert ist.
     */
    public static synchronized void reset() {
        vunummer = null;
    }

    /**
     * Damit kann die VU-Nummer gesetzt werden.
     * @param nr die VU-Nummer als String
     */
    public static synchronized void setVUNummer(final String nr) {
        setVUNummer(new VUNummer(nr));
    }

    /**
     * Damit kann die VU-Nummer gesetzt werden.
     * @param nr VU-Nummer
     */
    public static synchronized void setVUNummer(final VUNummer nr) {
        vunummer = nr;
        LOG.info("konfigurierte VU-Nummer: " + vunummer);
    }

    /**
     * Hiermit kann die gesetzte VU-Nummer abgefragt werden.
     * Falls sie noch nicht gesetzt wurde, wird ein "DUMMY" zurueckgegeben.
     *
     * @return VU-Nummer bzw. "DUMMY"
     */
    public static synchronized VUNummer getVUNummer() {
        if (vunummer == null) {
            vunummer = new VUNummer(System.getProperty(GDV_VU_NUMMER, DUMMY_VU_NUMMER));
        }
        return vunummer;
    }

    /**
     * Liefert den Namen der XML-Resource zurueck, in der die XML-Beschreibung
     * der GDV-Datensaetze enhalten ist. Ueber "-Dgdv.XML-Resource=..." kann
     * hierueber eine andere Resource (z.B. VUVM2015.xml) eingestellt werden.
     *
     * @return "VUVM2018.xml", wenn nicht per System-Property was anderes
     *         angegeben ist
     * @since 5.0
     */
    public static String getXmlResource() {
        return System.getProperty("gdv.XML-Resource", "VUVM2018.xml");
    }

    /**
     * Hier kann der "End Of Datensatz" (EOD) gesetzt werden.
     * Dieses Zeichen (oder Zeichenkette) wird am Ende jeden
     * Datensatzes mit ausgegeben.
     *
     * @since 0.3
     * @param linefeed z.B. "\n"
     */
    public static synchronized void setEOD(final String linefeed) {
        eod = linefeed;
    }

    /**
     * Hier wird das "End Of Datensatz" (EOD) zurueckgegeben.
     *
     * @since 0.3
     * @return End-of-Datensatz
     */
    public static synchronized String getEOD() {
        return eod;
    }

    /**
     * Ist Zeichen fuer "End Of Datensatz" (EOD) gegeben?
     *
     * @return true, falls ja
     */
    public static synchronized boolean hasEOD() {
        return StringUtils.isNotEmpty(eod);
    }

}
