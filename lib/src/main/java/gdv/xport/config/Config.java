/*
 * Copyright (c) 2009 - 2021 by Oli B.
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

import gdv.xport.feld.*;
import gdv.xport.satz.xml.FeldXml;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.concurrent.Immutable;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Ueber diese Klassen koennen Default-Werte abgefragt und das Verhalten der
 * Anwendung gesteuert werden. Mit v5.3 wurde die Klasse umgebaut, um
 * verschiedene Konfigurationen zu unterstuetzen.
 * <p>
 * Ueber die Option "-Dgdv.config=..." koennen eigene Property-Dateien fuer
 * die Vorbelegung angegeben werden. So kann mit
 * </p>
 * <pre>
 *     -Dgdv.config=/gdv/xport/config/experimental.properties
 * </pre>
 * <p>
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
public final class Config implements Serializable {

    private static final Logger LOG = LogManager.getLogger(Config.class);
    private static Config instance = new Config();

    /** Standard-Encoding ist "ISO-8859-1". */
    public static final Charset DEFAULT_ENCODING = StandardCharsets.ISO_8859_1;
    /** Standard-Encoding als String. */
    public static final String DEFAULT_ENCODING_NAME = DEFAULT_ENCODING.toString();
    /** Falls VUNummer nicht gesetzt ist, wird dies als Dummy eingesetzt. */
    public static final String DUMMY_VU_NUMMER = "DUMMY";
    /** Property-Name fuer die VU-Nummer. */
    public static final String GDV_VU_NUMMER = "gdv.VU-Nummer";
    /** Default-Konfiguration fuer VUVM2018er-Version. */
    public static final Config DEFAULT = new Config("/gdv/xport/config/default.properties");
    /** Experimentale Konfiguration zum Testen neuer Features. */
    public static final Config EXPERIMENTAL = new Config("/gdv/xport/config/experimental.properties");
    /** Eine leere Konfiguration zum Ueberschreiben. */
    public static final Config EMPTY = new Config(new Properties());
    /** Die Konfiguration fuer die Default-Validierung. */
    public static final Config LAX = EMPTY.withProperty("gdv.feld.validate", "lax");
    /** Die Konfiguration fuer die strikte Validierung. */
    public static final Config STRICT = EMPTY.withProperty("gdv.feld.validate", "strict");
    /** Default-Konfiguration fuer 2018. */
    public static final Config VUVM2018 = DEFAULT;
    /** Default-Konfiguration fuer 2015. */
    public static final Config VUVM2015 = DEFAULT.withProperty("gdv.XML-Resource", "VUVM2015.xml");
    /** Default-Konfiguration fuer 2013. */
    public static final Config VUVM2013 = DEFAULT.withProperty("gdv.XML-Resource", "VUVM2013.xml");
    /** Default-Konfiguration fuer 2009. */
    public static final Config VUVM2009 = DEFAULT.withProperty("gdv.XML-Resource", "VUVM2009.xml");

    private final Properties properties;
    private final Map<Class<? extends Feld>, Feld.Validator> defaultValidators = new HashMap<>();

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
        //this(System.getProperty("gdv.config", "/gdv/xport/config/default.properties"));
        this(System.getProperty("gdv.config", "/gdv.properties"));
    }

    /**
     * Moechte man eine andere Konfiguration, kann man hierueber eine
     * alternative Resource angeben.
     *
     * @param resource z.B. "/gdv/xport/config/experimental.properties"
     * @since 5.3
     */
    public Config (String resource) {
        this(loadProperties(resource));
    }

    private Config(Properties props) {
        this.properties = props;
        this.defaultValidators.put(Feld.class, new Feld.Validator(this));
        this.defaultValidators.put(FeldXml.class, new FeldXml.Validator(this));
        this.defaultValidators.put(NumFeld.class, new NumFeld.Validator(this));
        this.defaultValidators.put(AlphaNumFeld.class, new AlphaNumFeld.Validator(this));
        this.defaultValidators.put(Zeichen.class, new Zeichen.Validator(this));
        this.defaultValidators.put(Satznummer.class, new Satznummer.Validator(this));
        this.defaultValidators.put(Betrag.class, new Betrag.Validator(this));
        this.defaultValidators.put(BetragMitVorzeichen.class, new BetragMitVorzeichen.Validator(this));
        this.defaultValidators.put(Datum.class, new Datum.Validator(this));
        this.defaultValidators.put(VUNummer.class, new VUNummer.Validator(this));
        this.defaultValidators.put(Version.class, new Version.Validator(this));
        init(this.defaultValidators, props);
    }

    private void init(Map<Class<? extends Feld>, Feld.Validator> validators, Properties props) {
        for (String key : props.stringPropertyNames()) {
            if (key.startsWith("gdv.validator.")) {
                String classname = key.substring(14);
                addTo(validators, classname, props.getProperty(key));
            }
        }
    }

    private void addTo(Map<Class<? extends Feld>, Feld.Validator> validators, String classname, String validatorName) {
        try {
            Class<? extends Feld> feldClass = (Class<? extends Feld>) Class.forName(classname);
            Class<? extends Feld.Validator> validatorClass = (Class<? extends Feld.Validator>) Class.forName(validatorName);
            Feld.Validator v = validatorClass.getConstructor().newInstance();
            validators.put(feldClass, v);
            LOG.info("Validator {} wurde fuer {} registriert.", v, feldClass);
        } catch (ReflectiveOperationException ex) {
            throw new ConfigException(String.format("Validator '%s' fuer '%s' nicht gefunden", classname, validatorName), ex);
        }
    }

    private static Properties loadProperties(String resource) {
        Properties properties = new Properties();
        try (InputStream input = getInputStream(resource)) {
            if (input == null) {
                LOG.info("default.properties werden geladen, da Resource '{}' nicht vorhanden.", resource);
                return loadProperties("/gdv/xport/config/default.properties");
            }
            LOG.info("Properties werden aus '{}' eingelesen.", resource);
            properties.load(input);
            addGdvSystemProperties(properties);
            return properties;
        } catch (IOException ex) {
            throw new IllegalArgumentException(String.format("'%s' ist fehlerhaft", resource), ex);
        }
    }

    private static InputStream getInputStream(String resource) throws FileNotFoundException {
        try {
            URI uri = new URI(resource);
            if (uri.getScheme() != null) {
                return getInputStream(uri);
            }
        } catch (URISyntaxException ex) {
            LOG.debug("'{}' wird als Resource betrachtet ({}).", resource, ex);
            LOG.trace("Details:", ex);
        }
        return Config.class.getResourceAsStream(resource);
    }

    private static InputStream getInputStream(URI uri) throws FileNotFoundException {
        String scheme = uri.getScheme().toLowerCase();
        switch (scheme) {
            case "file":
                return new FileInputStream(new File(uri));
            case "classpath":
                return getInputStream(uri.getPath());
            default:
                throw new UnsupportedOperationException(String.format("Schema '%s' in %s wird noch nicht unterstuetzt", scheme, uri));
        }
    }

    // nur die SystemProperties, die mit "gdv." anfangen, werden uebernommen
    private static void addGdvSystemProperties(Properties props) {
        for (Map.Entry<Object, Object> entry : System.getProperties().entrySet()) {
            String key = (String) entry.getKey();
            if (key.startsWith("gdv.")) {
                props.setProperty(key, (String) entry.getValue());
            }
        }
    }

    /**
     * Dient zum Erzeugen einer neuen Config mit der gewuenschten Property.
     *
     * @param key z.B. "gdv.numfeld.fill-blanks"
     * @param value neuer Wert
     * @return neue Config mit key=value
     */
    public Config withProperty(String key, String value) {
        Properties props = new Properties();
        props.putAll(properties);
        props.setProperty(key, value);
        return new Config(props);
    }

    /**
     * Liefert den Wert einer Property zurueck.
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
     * Liefert den Validator mit dieser Konfiguration fuer die gewuenschte
     * Feld-Klasse.
     *
     * @param clazz Klasse, fuer den der Validator bestimmt ist
     * @return Validator
     * @since 6.2
     */
    public Feld.Validator getValidatorFor(Class<? extends Feld> clazz) {
        return defaultValidators.get(clazz);
    }

    /**
     * Liefert den Wert einer Property als String zurueck
     *
     * @param key Name der Property
     * @return Wert der Property als String
     * @since 5.3
     */
    public String getString(String key) {
        return this.properties.getProperty(key, "");
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
     * <p>
     * TODO: Wird mit v7 entfernt.
     * </p>
     *
     * @param nr VU-Nummer
     * @deprecated wird ab v7 nicht mehr unterstuetzt
     */
    @Deprecated
    public static synchronized void setVUNummer(final VUNummer nr) {
        if (!getVUNummer().equals(nr)) {
            // da die Config nicht veraendert werden soll, muessen wir eine eine neue Config erzeugen
            instance = createConfigWith(GDV_VU_NUMMER, nr.getInhalt());
            LOG.info("konfigurierte VU-Nummer: " + nr);
        }
    }

    /**
     * Hiermit kann die gesetzte VU-Nummer abgefragt werden.
     * Falls sie noch nicht gesetzt wurde, wird ein "DUMMY" zurueckgegeben.
     * <p>
     * TODO: Wird mit v7 entfernt.
     * </p>
     *
     * @return VU-Nummer bzw. "DUMMY"
     * @deprecated ersetzt durch {@link Config#getVUNr()}
     */
    @Deprecated
    public static synchronized VUNummer getVUNummer() {
        return new VUNummer(instance.getProperty(GDV_VU_NUMMER, DUMMY_VU_NUMMER));
    }

    /**
     * Hiermit kann die voreingestellte VU-Nummer abgefragt werden.
     *
     * @return VU-Nummer
     * @since 5.3
     */
    public VUNummer getVUNr() {
        return new VUNummer(getString(GDV_VU_NUMMER));
    }

    /**
     * Ist der Debug-Mode eingestellt?
     *
     * @return 'true' oder 'false'
     * @since 6.2
     */
    public boolean isDebug() {
        return getBool("gdv.debug");
    }

    /**
     * Liefert den Validierungsmode fuer Felder zurueck, der ueber die
     * Property "gdv.feld.validate" eingestellt werden kann.
     *
     * @return OFF, LAX oder STRICT
     * @since 6.0
     */
    public ValidateMode getValidateMode() {
        return ValidateMode.of(getProperty("gdv.feld.validate", "off"));
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
     * @deprecated wird kuenftig nicht mehr unterstuetzt
     */
    @Deprecated
    public static synchronized void setEOD(final String linefeed) {
        if (Objects.equals(getEOD(), linefeed)) {
            // da die Config nicht veraendert werden soll, muessen wir eine eine neue Config erzeugen
            instance = createConfigWith("gdv.eod", linefeed);
        }
    }

    private static Config createConfigWith(String key, String value) {
        Properties props = new Properties(instance.properties);
        props.setProperty(key, value);
        return new Config(props);
    }

    /**
     * Hier wird das "End Of Datensatz" (EOD) zurueckgegeben.
     *
     * @since 0.3
     * @return End-of-Datensatz
     */
    public static synchronized String getEOD() {
        return instance.getString("gdv.eod");
    }

    /**
     * Ist Zeichen fuer "End Of Datensatz" (EOD) gegeben?
     *
     * @return true, falls ja
     */
    public static synchronized boolean hasEOD() {
        return StringUtils.isNotEmpty(getEOD());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return properties.equals(config.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + properties;
    }



    public enum ValidateMode {

        OFF,
        LAX,
        STRICT;

        public static ValidateMode of(String value) {
            String s = value.toUpperCase();
            for (ValidateMode mode : values()) {
                if (mode.name().equals(s)) {
                    return mode;
                }
            }
            if ("TRUE".equals(s) || "ON".equals(s)) {
                return LAX;
            }
            LOG.debug("'{}' wird als 'OFF' interpretiert.", s);
            return OFF;
        }

    }

}
