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

import javax.annotation.concurrent.Immutable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Ueber diese Klassen koennen Default-Werte abgefragt und das Verhalten der
 * Anwendung gesteuert werden. Mit v5.3 wurde die Klasse umgebaut, um
 * verschiedene Konfigurationen zu unterstuetzen.
 * <p>
 * Ueber die Option "-Dgdv.config=..." koennen eigene Property-Dateien fuer
 * die Vorbelegung angegeben werden. So kann mit
 * <pre>
 *     -Dgdv.config=/gdv/xport/config/default6.properties
 * </pre>
 * das Verhalten fuer v6 eingestellt werden, in der sich z.B. das Verhalten von
 * Setzen von Feldern mit zu grossen Werten aendern wird. Einzelne Properties
 * koennen aber auch durch SystemProperties (z.B. "-Dgdv.feld.truncate=true")
 * uebersteuert werden.
 * </p>
 *
 * @author oliver
 * @since 08.10.2009
 */
@Immutable
public final class Config {

    private static final Logger LOG = LogManager.getLogger(Config.class);
    private static String eod = "\n";
    private static Config instance = new Config();

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

    public static Config getInstance() {
        return instance;
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

    /**
     * Liefert den Wert einer Property zuruecke
     *
     * @param key Name der Property
     * @param defaultValue Default-Wert, falls Property nicht gesetzt ist
     * @return Wert der Property als String
     * @since 5.3
     */
    public String getProperty(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    /**
     * Liefert eine Property als Bool-Wert zurueck.
     *
     * @param key Name der Property
     * @return true oder false
     * @since 5.3
     */
    public boolean getBool(String key) {
        return Boolean.parseBoolean(getProperty(key, "false"));
    }

    /**
     * Diese Methode ist ohne Funktion.
     *
     * @deprecated wird in kuenftigen Versionen nicht mehr unterstuetzt
     */
    @Deprecated
    public static synchronized void reset() {
        LOG.warn("reset() ist ohne Version und wird in kuenftigen Versionen entfernt.");
    }

    /**
     * Damit kann die VU-Nummer gesetzt werden.
     *
     * @param nr die VU-Nummer als String
     * @deprecated wird kuenftig nicht mehr unterstuetzt
     */
    @Deprecated
    public static synchronized void setVUNummer(final String nr) {
        setVUNummer(new VUNummer(nr));
    }

    /**
     * Damit kann die VU-Nummer gesetzt werden.
     *
     * @param nr VU-Nummer
     * @deprecated wird kuenftig nicht mehr unterstuetzt
     */
    @Deprecated
    public static synchronized void setVUNummer(final VUNummer nr) {
        if (!getVUNummer().equals(nr)) {
            // da die Config nicht veraendert werden soll, muessen wir eine eine neue Config erzeugen
            Properties props = new Properties(instance.properties);
            props.setProperty(GDV_VU_NUMMER, nr.getInhalt());
            instance = new Config(props);
            LOG.info("konfigurierte VU-Nummer: " + nr);
        }
    }

    /**
     * Hiermit kann die gesetzte VU-Nummer abgefragt werden.
     * Falls sie noch nicht gesetzt wurde, wird ein "DUMMY" zurueckgegeben.
     *
     * @return VU-Nummer bzw. "DUMMY"
     */
    public static synchronized VUNummer getVUNummer() {
        return new VUNummer(instance.getProperty(GDV_VU_NUMMER, DUMMY_VU_NUMMER));
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
