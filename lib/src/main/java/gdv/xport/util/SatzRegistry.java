/*
 * Copyright (c) 2021 by Oli B.
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
 * (c)reated 13.01.2021 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;
import gdv.xport.io.PushbackLineNumberReader;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Nachsatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Vorsatz;
import gdv.xport.satz.model.SatzX;
import gdv.xport.satz.xml.SatzXml;
import gdv.xport.satz.xml.XmlService;
import org.apache.commons.lang3.Range;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ValidationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Diese Klasse wurde von SatzFactory kopiert und so angepasst, dass auch
 * verschiedene Instanzen fuer die jeweilige XML-Beschreibung der GDV-
 * Datensaetze erzeugt werden koennen. Dies ist mit der SatzFactory-Klasse
 * nicht moeglich, da sie ein rein statische Klasse ist.
 * <p>
 * Da die Klasse auch zum Registrieren eigener Satzarten gedacht ist, wurde
 * sie in SatzRegistry umbenannt. Funktional entspricht sie aber der
 * {@link SatzFactory}-Klasse (s.o.).
 * </p>
 *
 * @author oliver (ob@aosd.de)
 * @since 5.0
 */
public class SatzRegistry implements VersionHandler {

    private static final Logger LOG = LogManager.getLogger(SatzRegistry.class);
    /** Default-Validator, der nur Satzart 800 - 899 akzeptiert. */
    public static final Validator VALIDATOR = new Validator();
    /** Dieser Validator akzeptiert alle Satzarten zwischen 0 und 9999. */
    public static final Validator NO_VALIDATOR = new Validator(Range.between(0, 9999));
    private static final Map<Config, SatzRegistry> INSTANCES = new HashMap<>();
    private final Map<SatzTyp, Satz> registeredSaetze = new ConcurrentHashMap<>();
    private final XmlService xmlService;

    private SatzRegistry(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    /**
     * Hierueber kann man sich die Default-Factory mit der aktuell gueltigen
     * XML-Beschreibung der GDV-Datensaetze holen.
     *
     * @return Factory auf Basis von VUVM2018.xml
     */
    public static SatzRegistry getInstance() {
        return getInstance(Config.getInstance());
    }

    /**
     * Hierueber kann man sich die Default-Factory anhand der gewuenschten
     * Konfiguration die XML-Beschreibung der GDV-Datensaetze holen.
     *
     * @param cfg gewuenschte Konfiguration
     * @return Factory auf Basis der uebergebenen Config
     */
    public static SatzRegistry getInstance(final Config cfg) {
        SatzRegistry factory = INSTANCES.get(cfg);
        try {
            if (factory == null) {
                factory = new SatzRegistry(XmlService.getInstance(cfg));
                INSTANCES.put(cfg, factory);
                LOG.info("{} wurde angelegt.", factory);
            }
            return factory;
        } catch (XMLStreamException | IOException ex) {
            throw new IllegalArgumentException("invalid config: " + cfg, ex);
        }
    }

    /**
     * Hierueber kann man sich die Default-Factory mit der gewuenschten
     * XML-Beschreibung der GDV-Datensaetze holen. Es wird dabei fuer die
     * gleiche Resource auch die gleiche Instanz zurueckgegeben.
     *
     * @param resource z.B. "VUVM2015.xml"
     * @return Factory auf Basis der uebergebenen Resource
     */
    public static SatzRegistry getInstance(final String resource) {
        return getInstance(Config.EMPTY.withProperty("gdv.XML-Resource", resource));
    }

    /**
     * Liefert den Datensatz mit der gewuenschten Version. Dazu werden die
     * Instanzen mit den verschiedenen XML-Beschreibungen durchsucht. Wird
     * keine gefunden, wird der Satz der aktuellen Instanz zurueckgegeben.
     *
     * @param satzTyp SatzTyp
     * @param version gewuenschte Version
     * @return Satz mit der gewuenschten Version
     * @since 5.2
     */
    public static Satz getSatz(SatzTyp satzTyp, String version) {
        createInstances();
        Satz satz = getInstance().getSatz(satzTyp);
        float satzVersion = asFloat(satz.getVersion());
        float requiredVersion = asFloat(version);
        for (SatzRegistry registry : INSTANCES.values()) {
            Satz ds = null;
            try {
                ds = registry.getSatz(satzTyp);
            } catch (NotRegisteredException e) {
                // satzTyp ist in dieser Version nicht registriert, gehe zur nächsten Version
                LOG.debug("Satzart {} in {} nicht registriert, suche weiter", satzTyp, registry);
                continue;
            }
            if (version.equals(ds.getVersion())) {
                return ds;
            } else if ((asFloat(ds.getVersion()) < satzVersion) && (asFloat(ds.getVersion()) > requiredVersion)) {
                satz = ds;
                satzVersion = asFloat(ds.getVersion());
            }
        }
        LOG.info("Exakte Version {} fuer {} wurde nicht gefunden - verwende {} (Version {}).", version,
                satzTyp, satz.toShortString(), satz.getVersion());
        return satz;
    }

    private static float asFloat(String version) {
        try {
            return Float.valueOf(version);
        } catch (NumberFormatException ex) {
            LOG.info("Kann aus '{}' keine Version ermitteln ({}).", version, ex);
            LOG.debug("Details:", ex);
            return 0.0F;
        }
    }

    private static void createInstances() {
        getInstance("VUVM2009.xml");
        getInstance("VUVM2013.xml");
        getInstance("VUVM2015.xml");
        getInstance("VUVM2018.xml");
    }

    /**
     * Mit dieser Klasse konnen die Registrierungen wieder komplett
     * rueckgaengig gemacht werden. Diese Methode wurde vor allem zur
     * Unterstuetzung der Unit-Tests eingefuehrt, wird aber seit der
     * Umstellung von {@link SatzFactory} auf {@link SatzRegistry}
     * nicht mehr benoetigt.
     * <p>
     * TODO: wird mit v7 entfernt
     * </p>
     *
     * @deprecated wird nicht mehr benoetigt
     */
    @Deprecated
    public void reset() {
        registeredSaetze.clear();
        LOG.debug("{} wurde zurueckgesetzt.", this);
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden. Die Kasse <em>muss</em>
     * einen Default-Konstruktor bereitstellen. Ansonsten wird hier eine
     * {@link IllegalArgumentException} geworfen.
     *
     * @param clazz   the clazz
     * @param satzart the satzart
     */
    public void register(final Class<? extends Satz> clazz, final int satzart) {
        register(clazz, satzart, VALIDATOR);
    }

    /**
     * Mit dieser Methode kann ein Validator mit uebergeben werden. So kann
     * z.B. {@link SatzRegistry#NO_VALIDATOR} mit uebergeben werden, um die
     * Default-Validierung abzuschalten. Als Default werden nur Datensaetze
     * mit der Satzart 800 - 899 zugelassen.
     *
     * @param clazz   the clazz
     * @param satzart the satzart
     * @param validator fuer die Validierung der Satzart
     * @see #register(Satz, SatzTyp)
     */
    public void register(final Class<? extends Satz> clazz, final int satzart, Validator validator) {
        try {
            Constructor<? extends Satz> ctor = clazz.getConstructor();
            LOG.debug("Default constructor {} found.", ctor);
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException("no default constructor found in " + clazz, ex);
        }
        SatzTyp satzTyp = SatzTyp.of(satzart);
        register(newInstance(satzTyp, clazz), satzTyp, validator);
    }

    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der
     * Datensatz-Beschreibung uebergeben wird.
     *
     * @param enumClass ie Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzNr die SatzNummer (z.B. new SatzNummer(100))
     * @deprecated durch {@link #register(Satz, SatzTyp)} abgeloest
     */
    @Deprecated
    public void registerEnum(final Class<? extends Enum> enumClass, final SatzTyp satzNr) {
        register(new SatzX(satzNr, enumClass), satzNr, NO_VALIDATOR);
    }

    /**
     * Mit dieser Methode kann ein beliebiger Satz registriert werden. Sie
     * loest die alter registerEnum-Methode ab, in der ein Satz mithilfe einer
     * Enum-Beschreibung registriert werden konnte.
     * <p>
     * Mit der verbesserten Unterstuetzung der GDV-XML-Beschreibung in v5.0
     * kann jetzt auch diese XML-Beschreibung fuer die Registrierung eigener
     * Datensaetze verwendet und hierueber registriert werden. So kann z.B.
     * mit
     * </p>
     * <pre>
     * SatzRegistry.getDefault().register(SatzXml.of("Satz0221.051.xml"), SatzTyp.of("0221.051"));
     * </pre>
     * <p>
     * eine eigene Beschreibung fuer Satzart 0221.051 registriert werden.
     * </p>
     *
     * @param satz   Satz-Vorlage (z.B. SatzXml.of("Satz0221.051.xml"))
     * @param satzNr Satzart (z.B. SatzTyp.of("0221.051"))
     */
    public void register(final Satz satz, final SatzTyp satzNr) {
        register(satz, satzNr, VALIDATOR);
    }

    /**
     * Mit dieser register-Methode kann ein eigener Validator mit uebergeben
     * werden. Dies ist hilfreich, wenn man den vom GDV vorgegebenen Bereich
     * fuer eigene Datensaetze (800-899) verlassen will. Dann kann man z.B.
     * den NO_VALIDATOR uebergeben, der alle Satzarten zwischen 0 und 9999
     * akzeptiert.
     *
     * @param satz   Satz-Vorlage (z.B. SatzXml.of("Satz0221.051.xml"))
     * @param satzNr Satzart (z.B. SatzTyp.of("0221.051"))
     * @param validator Validator fuer den SatzTyp
     */
    public void register(final Satz satz, final SatzTyp satzNr, Validator validator) {
        validator.validate(satzNr);
        registeredSaetze.put(satzNr, satz);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann). Diese unregister-Methode ersetzt ab 4.2 die
     * anderen unregister-Methoden.
     *
     * @param typ SatzTyp bzw. Satzart
     */
    public void unregister(SatzTyp typ) {
        registeredSaetze.remove(typ);
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden.
     *
     * @param clazz the clazz
     * @param satzNr the satz nr
     */
    public void register(final Class<? extends Datensatz> clazz, final SatzTyp satzNr) {
        registeredSaetze.put(satzNr, generateDatensatz(satzNr, clazz));    }

    /**
     * Holt einen Satz.
     * 
     * @param satztyp der Satztyp
     * @return angeforderter Satz
     */
    public Satz getSatz(final SatzTyp satztyp) {
        Satz satz = registeredSaetze.get(satztyp);
        if (satz == null) {
            return getSatzFromXmlService(satztyp);
        }
        try {
            return (Satz) satz.clone();
        } catch (CloneNotSupportedException ex) {
            throw new IllegalArgumentException(satztyp + " laesst sich nicht clonen", ex);
        }
    }

    private static Satz newInstance(SatzTyp satztyp, Class<? extends Satz> clazz) {
        try {
            Satz satz = clazz.getDeclaredConstructor().newInstance();
            if (satz.getSatzart() != satztyp.getSatzart()) {
                Constructor<? extends Satz> ctor = clazz.getConstructor(int.class);
                satz = ctor.newInstance(satztyp.getSatzart());
            }
            return satz;
        } catch (Exception e) {
            LOG.info("default constructor does not work (" + e + "), trying another ctor...");
            Constructor<? extends Satz> ctor = null;
            try {
                ctor = clazz.getConstructor(int.class);
                return ctor.newInstance(satztyp.getSatzart());
            } catch (InvocationTargetException ite) {
                throw new ShitHappenedException(ite.getTargetException() + " in " + ctor, ite);
            } catch (NoSuchMethodException nsme) {
                throw new UnsupportedOperationException("registered " + clazz + " has not the required ctor", nsme);
            } catch (InstantiationException ie) {
                throw new ShitHappenedException("registered " + clazz + " can't be instantiated", ie);
            } catch (IllegalAccessException iae) {
                throw new IllegalStateException("registered " + clazz + " can't be accessed", iae);
            }
        }
    }

    private Satz getSatzFromXmlService(SatzTyp satztyp) {
        try {
            SatzXml satz = xmlService.getSatzart(satztyp);
            satz.init(satztyp);
            return satz;
        } catch (NotRegisteredException ex) {
            if (satztyp.hasParent()) {
                LOG.warn("{} is not available via XmlService, trying {} now.", satztyp, satztyp.getParent());
                LOG.trace("Details:", ex);
                SatzXml satz = xmlService.getSatzart(satztyp.getParent());
                satz.setSparte(satztyp.getSparte());
                if (satztyp.getSparte() != satz.getSatzTyp().getSparte()) {
                    satz.resetGdvSatzartName();
                }
                return satz;
            } else {
                throw ex;
            }
        }
    }

    private Datensatz generateDatensatz(final SatzTyp satzNr) {
        LOG.trace("Will use fallback for Satz {}:", satzNr);
        return useFallback(satzNr);
    }

    /**
     * Versucht anhand des uebergebenen Strings herauszufinden, um was fuer eine
     * Satzart es sich handelt und liefert dann einen entsprechenden (gefuellten)
     * Satz zurueck.
     * <p>
     * Im ersten Schritt wird versucht, einen moeglichst passenden SatzTyp zu ermitteln. Zu diesem
     * SatzTyp wird dann versucht, eine registrierte Satzart zu finden. Wenn das fehlschlaegt, wird im
     * 2. Schritt als Ersatz eine Satzart generiert aus "satzart" und "sparte".
     * </p>
     * <p>
     * ACHTUNG: Um den ganz korrekten Satzaufbau zu liefern, muesste dazu die Version der Satzatz
     * bekannt sein! Diese Info steht immer im Vorsatz des zugehörigen Datenpaketes. Lt. Auskunft vom
     * GDV werden z.T. noch Saetze aus Release 01.11.2009 verarbeitet. Da hier aber die aktuellste
     * Version verwendet wird, kann der zurueckgegebene Satz mehr Felder enthalten, als die
     * tatsaechliche Version. Diese Unschaerfe wird hier in Kauf genommen, da i.d.R. immer nur Felder
     * hinzugefuegt werden. Dies muss beim Zugriff ueber die Feld-Nr. beachtet werden.
     * </p>
     *
     * @param content the content
     * @return einen gefuellten Satz
     */
    public Satz getSatz(final String content) {
        /*
         * @Oli: diese Variante sollte deutlich besser passen als die bisherige. Jedoch habe ich nicht
         *       bis zum Exzess getestet. Bitte pruef das doch noch mal.
         */
        SatzTyp satzTyp;

        try {
            satzTyp = errateSatzTyp(content);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("can't recognize SatzTyp " + content, ioe);
        }

        Satz satz;
        try {
            satz = getSatz(satzTyp);
        } catch (NotRegisteredException e) {
            LOG.debug("can't get Satz " + satzTyp.toString() + " (" + e + "), try fallback...");
            satz = getDatensatz(satzTyp);
        }
        try {
            satz.importFrom(content);
            return satz;
        } catch (IOException ioe) {
            throw new IllegalArgumentException("can't parse " + content, ioe);
        }
    }

    private SatzTyp errateSatzTyp(final String content) throws IOException {
        int satzart = Integer.parseInt(content.substring(0, 4));
        if (satzart == 1 || satzart == 9999) {
            return SatzTyp.of(satzart);
        }
        // Da das im Wesentlichen der Methode "Datenpaket.importDatensatz(...)" entspricht...
        try (PushbackLineNumberReader reader = new PushbackLineNumberReader(new StringReader(content))) {
            Satz satz = Datenpaket.importSatz(reader);
            return satz.getSatzTyp();
        }
    }

    /**
     * Liefert den passenden Vorsatz.
     *
     * @return Vorsatz
     * @since 5.0
     */
    public Vorsatz getVorsatz() {
        return new Vorsatz(this);
    }

    /**
     * Liefert den passenden Nachsatz.
     *
     * @return Nachsatz
     * @since 5.0
     */
    public Nachsatz getNachsatz() {
        return new Nachsatz(this);
    }

    /**
     * Liefert den gewuenschten Datensatz. Mit der uebergebenen Satznummer wird
     * der Datensatz spezifizert, der folgendes enthaelt:
     * <ul>
     * <li>Satzart (z.B. 210)</li>
     * <li>Sparte (z.B. 70 fuer Rechtsschutz)</li>
     * <li>Wagnisart (z.B. 1 fuer Kapitallebensversicherung)</li>
     * <li>Teildatensatz-Nummer (6 = Bezugsrechte, 7 = Auszahlungen, 8 =
     * zukünftige Summenänderungen, 9 = Wertungssummen)</li>
     * </ul>
     * <p>
     * Falls der gewuenschte Datensatz nicht registriert ist, wird der Datensatz
     * anhand der von {@link XmlService} bestimmt.
     * </p>
     * <p>
     * Im Gegensatz zu {@link #getSatz(SatzTyp)} wird hier auf jeden Fall
     * ein Datensatz zurueckgeliefert, auch wenn der SatzTyp weder registriert
     * noch ueber den {@link XmlService} verfuegbar ist. Dies ist vor allem
     * fuer den Import relevant, damit er nicht bei unbekannten Datensaetzen
     * abbricht.
     * </p>
     *
     * @param satzNr z.B. SatzTyp.of("0210.070.1.6")
     * @return den passenden Datensatz
     */
    public Datensatz getDatensatz(final SatzTyp satzNr) {
        Satz satz = registeredSaetze.get(satzNr);
        if (satz instanceof Datensatz) {
            try {
                return (Datensatz) satz.clone();
            } catch (CloneNotSupportedException ex) {
                throw new IllegalArgumentException(satzNr + " laesst sich nicht clonen", ex);
            }

        }
        return generateDatensatz(satzNr);
    }

    private Datensatz generateDatensatz(SatzTyp satzNr, Class<? extends Datensatz> clazz) {
        try {
            Constructor<? extends Datensatz> ctor = clazz.getConstructor(int.class, int.class);
            return ctor.newInstance(satzNr.getSatzart(), satzNr.getSparte());
        } catch (NoSuchMethodException exWithTwoParams) {
            LOG.info("constructor " + clazz + "(int, int) not found (" + exWithTwoParams + ")");
            return getDatensatz(satzNr.getSparte(), clazz);
        } catch (InstantiationException exWithTwoParams) {
            LOG.info(clazz + "(int, int) can't be instantiated (" + exWithTwoParams + ")");
            return getDatensatz(satzNr.getSparte(), clazz);
        } catch (IllegalAccessException exWithTwoParams) {
            LOG.info(clazz + "(int, int) can't be accessed (" + exWithTwoParams + ")");
            return getDatensatz(satzNr.getSparte(), clazz);
        } catch (InvocationTargetException exWithTwoParams) {
            LOG.info("error in calling " + clazz + "(int, int): " + exWithTwoParams);
            return getDatensatz(satzNr.getSparte(), clazz);
        }
    }

    private static Datensatz getDatensatz(final int sparte, final Class<? extends Datensatz> clazz) {
        try {
            Constructor<? extends Datensatz> ctor = clazz.getConstructor(int.class);
            return ctor.newInstance(sparte);
        } catch (NoSuchMethodException nsme) {
            LOG.info(clazz + " found but no " + clazz.getSimpleName() + "(" + sparte + ") constructor (" + nsme + ")");
            return getDatensatz(clazz);
        } catch (Exception exWithOneParam) {
            LOG.warn("constructor problem with " + clazz, exWithOneParam);
            return getDatensatz(clazz);
        }
    }

    private static Datensatz getDatensatz(final Class<? extends Datensatz> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("can't instantiate " + clazz, e);
        }
    }

    /**
     * Als Fallback wird nur der Datensatz fuer die entsprechende Satzart
     * zurueckgeben. Falls dieser nicht existiert, wird ein (allgemeiner)
     * Datensatz mit Satzart und Sparte als Parameter erzeugt.
     *
     * @param satzNr die SatzNummer
     * @return der erzeugte Datensatz
     */
    private Datensatz useFallback(final SatzTyp satzNr) {
        try {
            Datensatz fallback = (Datensatz) getSatz(satzNr);
            if (satzNr.hasSparte()) {
                fallback.setSparte(satzNr.getSparte());
            }
            return fallback;
        } catch (NotRegisteredException re) {
            // Dieser Teil wird fuer den Import benoetigt, um auch unsupported Datensaetze zu unterstuetzen
            LOG.warn("Reduced functionality for (unknown or unsupported) Satzart {}:",  satzNr, re);
            Datensatz satz = new Datensatz(satzNr.getSatzart(), satzNr.getSparte());
            satz.addFiller();
            return satz;
        }
    }

    /**
     * Liefert ein Datenpaket mit allen unterstuetzten Satzarten.
     * 
     * Satzarten, die mit <b>{@link #register(Class, int)}</b> registriert wurden,
     * werden nicht aufgefuehrt!
     * <p>
     * Grund: Ein Objekt vom Typ &lt;code&gt;Satz&lt;/code&gt; kann
     * nicht auf &lt;code&gt;Datensatz&lt;/code&gt; gecastet werden.
     * </p>
     *
     * @return Datenpaket mit allen unterstuetzten Satzarten
     */
    public Datenpaket getAllSupportedSaetze() {
        Map<SatzTyp, Satz> supportedSaetze = new HashMap<>(xmlService.getSatzarten());
        for (Map.Entry<SatzTyp, Satz> entry : registeredSaetze.entrySet()) {
            Satz value = entry.getValue();
            if (value instanceof Datensatz) {
                supportedSaetze.put(entry.getKey(), value);
            }
        }
        return createDatenpaket(supportedSaetze);
    }

    /**
     * Liefert ein Datenpaket mit den angegebenen Satzarten.
     *
     * @param typen gewuenschte Satzarten
     * @return Datenpaket mit gewuenschten Satzarten
     * @since 5.2
     */
    public Datenpaket getSupportedSaetzeWith(SatzTyp ... typen) {
        Map<SatzTyp, Satz> supportedSaetze = new HashMap<>();
        for (SatzTyp t : typen) {
            supportedSaetze.put(t, xmlService.getSatzart(t));
        }
        return createDatenpaket(supportedSaetze);
    }

    private Datenpaket createDatenpaket(Map<SatzTyp, Satz> supportedSaetze) {
        supportedSaetze.remove(Vorsatz.SATZART);
        supportedSaetze.put(Vorsatz.SATZART, new Vorsatz(this));
        supportedSaetze.remove(Nachsatz.SATZART);
        supportedSaetze.put(Nachsatz.SATZART, new Nachsatz(this));
        return Datenpaket.of(supportedSaetze.values());
    }

    /**
     * Liefert das Release der jeweiligen XML-Beschreibung, aus der die GDV-Datensaetze erzeugt
     * wurden.
     *
     * @return das Release der erzeugten XmlSaetze
     */
    public String getGdvRelease() {
        return xmlService.getGdvRelease();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " mit " + this.xmlService;
    }

    /**
     * Liefert die Version der angefragten Satzart
     *
     * @param satzTyp Satzart
     * @return z.B. "2.4"
     */
    @Override
    public String getVersionOf(SatzTyp satzTyp) {
        return getDatensatz(satzTyp).getSatzversion().getInhalt();
    }



    static class Validator {
        private final Range<Integer> allowed;
        public Validator() {
            this(Range.between(800, 899));
        }
        public Validator(Range<Integer> allowed) {
            this.allowed = allowed;
        }
        public SatzTyp validate(SatzTyp x) {
            if (allowed.contains(x.getSatzart())) {
                return x;
            } else {
                throw new ValidationException(x.getSatzart() + " liegt ausserhalb von " + allowed);
            }
        }
    }

}
