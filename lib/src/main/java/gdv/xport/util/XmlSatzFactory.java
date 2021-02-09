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
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Nachsatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Vorsatz;
import gdv.xport.satz.model.SatzX;
import gdv.xport.satz.xml.SatzXml;
import gdv.xport.satz.xml.XmlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
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
 *
 * @author oliver (ob@aosd.de)
 * @since 5.0
 */
public class XmlSatzFactory implements VersionHandler {

    private static final Logger LOG = LogManager.getLogger(XmlSatzFactory.class);
    private static final Map<String, XmlSatzFactory> INSTANCES = new HashMap<>();
    private final Map<SatzTyp, Class<? extends Satz>> registeredSatzClasses = new ConcurrentHashMap<>();
    private final Map<SatzTyp, Class<? extends Datensatz>> registeredDatensatzClasses = new ConcurrentHashMap<>();
    private final Map<SatzTyp, Class<? extends Enum>> registeredEnumClasses = new ConcurrentHashMap<>();
    private final XmlService xmlService;

    {
        registerDefault();
    }

    private void registerDefault() {
        // hier enthaelt die XML-Beschreibung f. KH-Deckungssumme weniger Infos
        registerEnum(gdv.xport.satz.feld.sparte51.Feld221.class, SatzTyp.of("0221.051"));
    }

    private XmlSatzFactory(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    /**
     * Hierueber kann man sich die Default-Factory mit der aktuell gueltigen
     * XML-Beschreibung der GDV-Datensaetze holen.
     *
     * @return Factory auf Basis von VUVM2018.xml
     */
    public static XmlSatzFactory getInstance() {
        return getInstance(Config.getXmlResource());
    }

    /**
     * Hierueber kann man sich die Default-Factory mit der gewuenschten
     * XML-Beschreibung der GDV-Datensaetze holen. Es wird dabei fuer die
     * gleiche Resource auch die gleiche Instanz zurueckgegeben.
     *
     * @param resource z.B. "VUVM2015.xml"
     * @return Factory auf Basis der uebergebenen Resource
     */
    public static XmlSatzFactory getInstance(final String resource) {
        XmlSatzFactory factory = INSTANCES.get(resource);
        try {
            if (factory == null) {
                factory = new XmlSatzFactory(XmlService.getInstance(resource));
                INSTANCES.put(resource, factory);
                LOG.info("{} wurde angelegt.", factory);
            }
            return factory;
        } catch (XMLStreamException ex) {
            throw new IllegalArgumentException("invalid resource: " + resource, ex);
        }
    }

    /**
     * Mit dieser Klasse konnen die Registrierungen wieder komplett
     * rueckgaengig gemacht werden. Diese Methode wurde vor allem zur
     * Unterstuetzung der Unit-Tests eingefuehrt.
     */
    public void reset() {
        registeredSatzClasses.clear();
        registeredDatensatzClasses.clear();
        registeredEnumClasses.clear();
        registerDefault();
        LOG.debug("{} wurde zurueckgesetzt.", this);
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden. Die Kasse <em>muss</em>
     * einen Default-Konstruktor bereitstellen. Ansonsten wird hier eine
     * {@link IllegalArgumentException} geworfen (seit 0.6).
     *
     * @param clazz   the clazz
     * @param satzart the satzart
     */
    public void register(final Class<? extends Satz> clazz, final int satzart) {
        try {
            Constructor<? extends Satz> ctor = clazz.getConstructor();
            LOG.debug("Default constructor {} found.", ctor);
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException("no default constructor found in " + clazz, ex);
        }
        registeredSatzClasses.put(SatzTyp.of(satzart), clazz);
    }

    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der
     * Datensatz-Beschreibung uebergeben wird.
     *
     * @param enumClass ie Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzNr die SatzNummer (z.B. new SatzNummer(100))
     */
    public void registerEnum(final Class<? extends Enum> enumClass, final SatzTyp satzNr) {
        if (registeredDatensatzClasses.containsKey(satzNr)) {
            LOG.info("Registered " + registeredDatensatzClasses.get(satzNr) + " for " + satzNr
                    + " will be replaced by " + enumClass);
            registeredDatensatzClasses.remove(satzNr);
        }
        registeredEnumClasses.put(satzNr, enumClass);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann). Diese unregister-Methode ersetzt ab 4.2 die
     * anderen uregister-Methoden.
     *
     * @param typ SatzTyp bzw. Satzart
     */
    public void unregister(SatzTyp typ) {
        registeredSatzClasses.remove(typ);
        registeredEnumClasses.remove(typ);
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden.
     *
     * @param clazz the clazz
     * @param satzNr the satz nr
     */
    public void register(final Class<? extends Datensatz> clazz, final SatzTyp satzNr) {
        registeredDatensatzClasses.put(satzNr, clazz);
    }

    /**
     * Holt einen Satz.
     * 
     * @param satztyp der Satztyp
     * @return angeforderter Satz
     */
    public Satz getSatz(final SatzTyp satztyp) {
        Class<? extends Satz> clazz = registeredSatzClasses.get(new SatzTyp(satztyp.getSatzart()));
        if (clazz == null) {
            return getSatzFromXmlService(satztyp);
        }
        try {
            Satz satz = clazz.newInstance();
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
            return xmlService.getSatzart(satztyp);
        } catch (NotRegisteredException ex) {
            LOG.debug("{} is not avalaible via XmlService.", satztyp);
            LOG.trace("Details:", ex);
            if (satztyp.hasParent()) {
                SatzXml satz = xmlService.getSatzart(satztyp.getParent());
                satz.setSparte(satztyp.getSparte());
                return satz;
            } else {
                return generateSatz(satztyp);
            }
        }
    }

    private Datensatz generateSatz(final SatzTyp satztyp) {
        Class<? extends Enum> enumClass = registeredEnumClasses.get(satztyp);
        if (enumClass == null) {
            // ein clone() ist nun nicht mehr noetig, da XML_SERVICE.getSatzart(..) bereits eine Kopie erzeugt
            return xmlService.getSatzart(satztyp);
        }
        return new SatzX(satztyp, enumClass);
    }

    private Datensatz generateDatensatz(final SatzTyp satzNr) {
        Class<? extends Enum> enumClass = registeredEnumClasses.get(satzNr);
        if (enumClass != null) {
            return new SatzX(satzNr, enumClass);
        }
        LOG.trace("Will use fallback for Satz {}:", satzNr);
        return useFallback(satzNr);
    }

    /**
     * Versucht anhand des uebergebenen Strings herauszufinden, um was fuer eine
     * Satzart es sich handelt und liefert dann einen entsprechenden (gefuellten)
     * Satz zurueck.
     * <p>
     * Im ersten Schritt wird nach der passenden Satzart gesucht. Das klappt nur,
     * wenn satzart = 0001, 0052, 0100, 0102, 0200, 0202, 0300,
     * 0342, 0350, 0352, 0372, 0382, 0390, 0392, 0400, 0410, 0420, 0430, 0450,
     * 0500, 0550, 0600, 9950, 9951, 9952, 9999.
     * Daher wird im 2. Versuch noch die Sparte hinzugenommen.
     * </p>
     * <p>
     * Das klappt nicht, wenn satzart= 0220.580.01, 0220.580.2, 0220.020.1,
     * 0220.020.2, 0220.020.3, 0220.010.13.1, 0220.010.13.6, 0220.010.13.7,
     * 0220.010.13.8, 0220.010.13.9, 0220.010.2.1, 0220.010.2.6, 0220.010.2.7,
     * 0220.010.2.8, 0220.010.2.9, 0220.010.48.1, 0220.010.48.6,
     * 0220.010.48.8, 0220.010.48.9, 0220.010.5.1, 0220.010.5.6, 0220.010.5.8,
     * 0220.010.5.9, 0220.010.6.1, 0220.010.6.6, 0220.010.6.8, 0220.010.6.9,
     * 0220.010.7.1, 0220.010.7.6, 0220.010.7.8, 0220.010.7.9, 0220.010.9.1,
     * 0220.010.9.6, 0220.010.9.7, 0220.010.9.8, 0220.010.9.9 !!
     * Diese Satzarten benoetigen weitere Angaben (Wagnisart (Sparte 010),
     * Satznummer (Sparte 010), KrankenfolgeNummer (Sparte 020), BausparenArt
     * (Sparte 580).
     * Fuer diese Satzarten kann diese Methode nicht verwendet werden.
     * </p>
     * <p>
     * ACHTUNG: Um den ganz korrekten Satzaufbau zu liefern, muesste dazu die
     * Version der Satzatz bekannt sein! Diese Info steht immer im Vorsatz des
     * zugehörigen Datenpaketes. Lt. Auskunft vom GDV werden z.T. noch Saetze
     * aus Release 01.11.2009 verarbeitet. Da hier aber die aktuellste Version
     * verwendet wird, kann der zurueckgegebene Satz mehr Felder enthalten, als die
     * tatsaechliche Version. Diese Unschaerfe wird hier in Kauf genommen, da i.d.R.
     * immer nur Felder hinzugefuegt werden. Dies muss beim Zugriff ueber die
     * Feld-Nr. beachtet werden.
     * </p>
     *
     * @param content the content
     * @return einen gefuellten Satz
     */
    public Satz getSatz(final String content) {
        int satzart = Integer.parseInt(content.substring(0, 4));
        Satz satz;
        try {
            satz = getSatz(SatzTyp.of(satzart));
        } catch (NotRegisteredException e) {
            LOG.debug("can't get Satz " + satzart + " (" + e + "), parsing Sparte...");
            int sparte = Integer.parseInt(content.substring(10, 13));
            satz = getDatensatz(SatzTyp.of(satzart, sparte));
        }
        try {
            satz.importFrom(content);
            return satz;
        } catch (IOException ioe) {
            throw new IllegalArgumentException("can't parse " + content, ioe);
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
     *
     * @param satzNr z.B. SatzTyp.of("0210.070.1.6")
     * @return den passenden Datensatz
     */
    public Datensatz getDatensatz(final SatzTyp satzNr) {
        Class<? extends Datensatz> clazz = registeredDatensatzClasses.get(satzNr);
        if (clazz == null) {
            // wird u.a. fuer den Import von Datensaetzen benoetigt
            try {
                return generateSatz(satzNr);
            } catch (NotRegisteredException ex) {
                LOG.debug("SatzTyp {} is not part of the XML description.", satzNr);
                LOG.trace("Details:", ex);
            }
        }
        return generateDatensatz(satzNr, clazz);
    }

    private Datensatz generateDatensatz(SatzTyp satzNr, Class<? extends Datensatz> clazz) {
        if (clazz == null) {
            return generateDatensatz(satzNr);
        }
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
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("can't instantiate " + clazz, e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("can't access default constructor of " + clazz, e);
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
        Map<SatzTyp, Datensatz> supportedSaetze = new HashMap<>();
        for (Map.Entry<SatzTyp, SatzXml> entry : xmlService.getSatzarten().entrySet()) {
            supportedSaetze.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<SatzTyp, Class<? extends Enum>> entry : registeredEnumClasses.entrySet()) {
            supportedSaetze.put(entry.getKey(), new SatzX(entry.getKey(), entry.getValue()));
        }
        for (Map.Entry<SatzTyp, Class<? extends Datensatz>> entry : registeredDatensatzClasses.entrySet()) {
            supportedSaetze.put(entry.getKey(), generateDatensatz(entry.getKey(), entry.getValue()));
        }
        supportedSaetze.remove(SatzTyp.of("0001"));
        supportedSaetze.remove(SatzTyp.of("9999"));
        Datenpaket all = new Datenpaket();
        for (Datensatz satz : supportedSaetze.values()) {
            all.add(satz);
        }
        return all;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " mit " + this.xmlService;
    }

    /**
     * Liefert die (Default-)Version der angefragten Satzart
     *
     * @param satzTyp Satzart
     * @return z.B. "2.4"
     */
    @Override
    public String getVersionOf(SatzTyp satzTyp) {
        return getDatensatz(satzTyp).getSatzversion().getInhalt();
    }

}
