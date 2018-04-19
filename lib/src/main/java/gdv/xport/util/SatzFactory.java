/*
 * Copyright (c) 2009 - 2013 by Oli B.
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
 * (c)reated 30.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.feld.Bezeichner;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Nachsatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Vorsatz;
import gdv.xport.satz.feld.common.TeildatensatzNummer;
import gdv.xport.satz.feld.sparte10.Feld220Wagnis0;
import gdv.xport.satz.feld.sparte10.wagnisart13.*;
import gdv.xport.satz.feld.sparte10.wagnisart2.*;
import gdv.xport.satz.feld.sparte10.wagnisart48.*;
import gdv.xport.satz.feld.sparte10.wagnisart5.*;
import gdv.xport.satz.feld.sparte10.wagnisart6.*;
import gdv.xport.satz.feld.sparte10.wagnisart7.*;
import gdv.xport.satz.feld.sparte10.wagnisart9.*;
import gdv.xport.satz.feld.sparte51.Feld221;
import gdv.xport.satz.model.*;
import gdv.xport.satz.xml.XmlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Diese Klasse dient dazu, um einen vorgegebene Satz, der z.B. aus einem Import
 * kommt, in den entsprechende Satz wandeln zu koennen.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.1.0 (30.10.2009)
 */
public final class SatzFactory {

    private static final Logger LOG = LogManager.getLogger(SatzFactory.class);
    private static final Map<SatzTyp, Class<? extends Satz>> REGISTERED_SATZ_CLASSES =
            new ConcurrentHashMap<SatzTyp, Class<? extends Satz>>();
    private static final Map<SatzTyp, Class<? extends Datensatz>> REGISTERED_DATENSATZ_CLASSES =
            new ConcurrentHashMap<SatzTyp, Class<? extends Datensatz>>();
    private static final Map<SatzTyp, Class<? extends Enum<?>>> REGISTERED_ENUM_CLASSES =
            new ConcurrentHashMap<SatzTyp, Class<? extends Enum<?>>>();
    private static final XmlService XML_SERVICE = XmlService.getInstance();

    static {
        register(Vorsatz.class, 1);
        // Satz100 und Satz200 werden jetzt vom XmlService behandelt
        register(Satz210.class, 210);
        register(Satz211.class, 211);
        register(Satz220.class, 220);
        register(Satz221.class, 221);
        register(Satz230.class, 230);
        register(Satz250.class, 250);
        register(Nachsatz.class, 9999);

        // Sparte 10 - Leben - Wagnisart 0
        registerEnum(Feld220Wagnis0.class, new SatzTyp(220, 10, 0));

        // Sparte 10 - Leben - Wagnisart 1 & 3
        registerEnum(Feld220Wagnis13.class, new SatzTyp(220, 10, 1));
        registerEnum(Feld220Wagnis13.class, new SatzTyp(220, 10, 3));
        registerEnum(Feld220Wagnis13Auszahlungen.class,
                new SatzTyp(220, 10, 1, TeildatensatzNummer.AUSZAHLUNGEN.getCode()));
        registerEnum(Feld220Wagnis13Auszahlungen.class,
                new SatzTyp(220, 10, 3, TeildatensatzNummer.AUSZAHLUNGEN.getCode()));
        registerEnum(Feld220Wagnis13Bezugsrechte.class,
                new SatzTyp(220, 10, 1, TeildatensatzNummer.BEZUGSRECHTE.getCode()));
        registerEnum(Feld220Wagnis13Bezugsrechte.class,
                new SatzTyp(220, 10, 3, TeildatensatzNummer.BEZUGSRECHTE.getCode()));
        registerEnum(Feld220Wagnis13ZukSummenaenderungen.class, new SatzTyp(220, 10, 1,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));
        registerEnum(Feld220Wagnis13ZukSummenaenderungen.class, new SatzTyp(220, 10, 3,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));
        registerEnum(Feld220Wagnis13Wertungssummen.class,
                new SatzTyp(220, 10, 1, TeildatensatzNummer.WERTUNGSSUMMEN.getCode()));
        registerEnum(Feld220Wagnis13Wertungssummen.class,
                new SatzTyp(220, 10, 3, TeildatensatzNummer.WERTUNGSSUMMEN.getCode()));

        registerEnum(Feld221Wagnis13.class, new SatzTyp(221, 10, 1));
        registerEnum(Feld221Wagnis13.class, new SatzTyp(221, 10, 3));
        registerEnum(Feld221Wagnis13Auszahlungen.class,
                new SatzTyp(221, 10, 1, TeildatensatzNummer.AUSZAHLUNGEN.getCode()));
        registerEnum(Feld221Wagnis13Auszahlungen.class,
                new SatzTyp(221, 10, 3, TeildatensatzNummer.AUSZAHLUNGEN.getCode()));
        registerEnum(Feld221Wagnis13ZukSummenaenderungen.class, new SatzTyp(221, 10, 1,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));
        registerEnum(Feld221Wagnis13ZukSummenaenderungen.class, new SatzTyp(221, 10, 3,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));

        // Sparte 10 - Leben - Wagnisart 2
        registerEnum(Feld220Wagnis2.class, new SatzTyp(220, 10, 2));
        registerEnum(Feld220Wagnis2Bezugsrechte.class,
                new SatzTyp(220, 10, 2, TeildatensatzNummer.BEZUGSRECHTE.getCode()));
        registerEnum(Feld220Wagnis2Auszahlungen.class,
                new SatzTyp(220, 10, 2, TeildatensatzNummer.AUSZAHLUNGEN.getCode()));
        registerEnum(Feld220Wagnis2Wertungssummen.class,
                new SatzTyp(220, 10, 2, TeildatensatzNummer.WERTUNGSSUMMEN.getCode()));
        registerEnum(Feld220Wagnis2ZukSummenaenderungen.class, new SatzTyp(220, 10, 2,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));

        registerEnum(Feld221Wagnis2.class, new SatzTyp(221, 10, 2));
        registerEnum(Feld221Wagnis2Auszahlungen.class,
                new SatzTyp(221, 10, 2, TeildatensatzNummer.AUSZAHLUNGEN.getCode()));
        registerEnum(Feld221Wagnis2ZukSummenaenderungen.class, new SatzTyp(221, 10, 2,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));

        // Sparte 10 - Leben - Wagnisart 4 & 8
        registerEnum(Feld220Wagnis48.class, new SatzTyp(220, 10, 4));
        registerEnum(Feld220Wagnis48.class, new SatzTyp(220, 10, 8));
        registerEnum(Feld220Wagnis48Bezugsrechte.class,
                new SatzTyp(220, 10, 4, TeildatensatzNummer.BEZUGSRECHTE.getCode()));
        registerEnum(Feld220Wagnis48Bezugsrechte.class,
                new SatzTyp(220, 10, 8, TeildatensatzNummer.BEZUGSRECHTE.getCode()));
        registerEnum(Feld220Wagnis48Wertungssummen.class,
                new SatzTyp(220, 10, 4, TeildatensatzNummer.WERTUNGSSUMMEN.getCode()));
        registerEnum(Feld220Wagnis48Wertungssummen.class,
                new SatzTyp(220, 10, 8, TeildatensatzNummer.WERTUNGSSUMMEN.getCode()));
        registerEnum(Feld220Wagnis48ZukSummenaenderungen.class, new SatzTyp(220, 10, 4,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));
        registerEnum(Feld220Wagnis48ZukSummenaenderungen.class, new SatzTyp(220, 10, 8,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));

        registerEnum(Feld221Wagnis48.class, new SatzTyp(221, 10, 4));
        registerEnum(Feld221Wagnis48.class, new SatzTyp(221, 10, 8));

        // Sparte 10 - Leben - Wagnisart 5
        registerEnum(Feld220Wagnis5.class, new SatzTyp(220, 10, 5));
        registerEnum(Feld220Wagnis5Bezugsrechte.class,
                new SatzTyp(220, 10, 5, TeildatensatzNummer.BEZUGSRECHTE.getCode()));
        registerEnum(Feld220Wagnis5Wertungssummen.class,
                new SatzTyp(220, 10, 5, TeildatensatzNummer.WERTUNGSSUMMEN.getCode()));
        registerEnum(Feld220Wagnis5ZukSummenaenderungen.class, new SatzTyp(220, 10, 5,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));

        registerEnum(Feld221Wagnis5.class, new SatzTyp(221, 10, 5));
        registerEnum(Feld221Wagnis5ZukSummenaenderungen.class, new SatzTyp(221, 10, 5,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));

        // Sparte 10 - Leben - Wagnisart 6
        registerEnum(Feld220Wagnis6.class, new SatzTyp(220, 10, 6));
        registerEnum(Feld220Wagnis6Bezugsrechte.class,
                new SatzTyp(220, 10, 6, TeildatensatzNummer.BEZUGSRECHTE.getCode()));
        registerEnum(Feld220Wagnis6Wertungssummen.class,
                new SatzTyp(220, 10, 6, TeildatensatzNummer.WERTUNGSSUMMEN.getCode()));
        registerEnum(Feld220Wagnis6ZukSummenaenderungen.class, new SatzTyp(220, 10, 6,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));

        registerEnum(Feld221Wagnis6.class, new SatzTyp(221, 10, 6));
        registerEnum(Feld221Wagnis6ZukSummenaenderungen.class, new SatzTyp(221, 10, 6,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));

        // Sparte 10 - Leben - Wagnisart 7
        registerEnum(Feld220Wagnis7.class, new SatzTyp(220, 10, 7));
        registerEnum(Feld220Wagnis7Bezugsrechte.class,
                new SatzTyp(220, 10, 7, TeildatensatzNummer.BEZUGSRECHTE.getCode()));
        registerEnum(Feld220Wagnis7Wertungssummen.class,
                new SatzTyp(220, 10, 7, TeildatensatzNummer.WERTUNGSSUMMEN.getCode()));
        registerEnum(Feld220Wagnis7ZukSummenaenderungen.class, new SatzTyp(220, 10, 7,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));

        registerEnum(Feld221Wagnis7.class, new SatzTyp(221, 10, 7));
        registerEnum(Feld221Wagnis7ZukSummenaenderungen.class, new SatzTyp(221, 10, 7,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));

        registerEnum(Feld230.class, new SatzTyp(230, 10, 7));

        // Sparte 10 - Leben - Wagnisart 9
        registerEnum(Feld220Wagnis9.class, new SatzTyp(220, 10, 9));
        registerEnum(Feld220Wagnis9Bezugsrechte.class,
                new SatzTyp(220, 10, 9, TeildatensatzNummer.BEZUGSRECHTE.getCode()));
        registerEnum(Feld220Wagnis9Auszahlungen.class,
                new SatzTyp(220, 10, 9, TeildatensatzNummer.AUSZAHLUNGEN.getCode()));
        registerEnum(Feld220Wagnis9Wertungssummen.class,
                new SatzTyp(220, 10, 9, TeildatensatzNummer.WERTUNGSSUMMEN.getCode()));
        registerEnum(Feld220Wagnis9ZukSummenaenderungen.class, new SatzTyp(220, 10, 9,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getCode()));
        
        // Sparte 51
        registerEnum(Feld221.class, new SatzTyp(221, 51));

    }

    /**
     * Instantiates a new satz factory.
     */
    private SatzFactory() {
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden. Die Kasse <em>muss</em>
     * einen Default-Konstruktor bereitstellen. Ansonsten wird hier eine
     * {@link IllegalArgumentException} geworfen (seit 0.6).
     *
     * @param clazz the clazz
     * @param satzart the satzart
     * @since 0.2
     */
    public static void register(final Class<? extends Satz> clazz, final int satzart) {
        try {
            Constructor<? extends Satz> ctor = clazz.getConstructor();
            LOG.debug("Default constructor {} found.", ctor);
        } catch (NoSuchMethodException ex) {
            throw new IllegalArgumentException("no default constructor found in " + clazz, ex);
        }
        REGISTERED_SATZ_CLASSES.put(new SatzTyp(satzart), clazz);
    }

    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der
     * Datensatz-Beschreibung uebergeben wird.
     *
     * @param enumClass die Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzart die Satzart (1-9999)
     * @since 0.6
     */
    public static void registerEnum(final Class<? extends Enum<?>> enumClass, final int satzart) {
        registerEnum(enumClass, new SatzTyp(satzart));
    }

    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der
     * Datensatz-Beschreibung uebergeben wird.
     *
     * @param enumClass die Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzart die Satzart (1-9999)
     * @param sparte die Sparte (0-999)
     * @since 0.6
     */
    public static void registerEnum(final Class<? extends Enum<?>> enumClass, final int satzart, final int sparte) {
        registerEnum(enumClass, new SatzTyp(satzart, sparte));
    }

    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der
     * Datensatz-Beschreibung uebergeben wird.
     *
     * @param enumClass ie Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzNr die SatzNummer (z.B. new SatzNummer(100))
     * @since 0.9
     */
    public static void registerEnum(final Class<? extends Enum<?>> enumClass, final SatzTyp satzNr) {
        if (REGISTERED_DATENSATZ_CLASSES.containsKey(satzNr)) {
            LOG.info("Registered " + REGISTERED_DATENSATZ_CLASSES.get(satzNr) + " for " + satzNr
                    + " will be replaced by " + enumClass);
            REGISTERED_DATENSATZ_CLASSES.remove(satzNr);
        }
        REGISTERED_ENUM_CLASSES.put(satzNr, enumClass);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann)
     *
     * @param satzart the satzart
     * @since 0.2
     */
    public static void unregister(final int satzart) {
        SatzTyp key = new SatzTyp(satzart);
        REGISTERED_SATZ_CLASSES.remove(key);
        REGISTERED_ENUM_CLASSES.remove(key);
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden.
     *
     * @param clazz the clazz
     * @param satzart the satzart
     * @param sparte the sparte
     * @since 0.2
     */
    public static void register(final Class<? extends Datensatz> clazz, final int satzart, final int sparte) {
        register(clazz, new SatzTyp(satzart, sparte));
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden.
     *
     * @param clazz the clazz
     * @param satzNr the satz nr
     */
    public static void register(final Class<? extends Datensatz> clazz, final SatzTyp satzNr) {
        REGISTERED_DATENSATZ_CLASSES.put(satzNr, clazz);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann)
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @since 0.2
     */
    public static void unregister(final int satzart, final int sparte) {
        unregister(satzart, sparte, -1);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann)
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @param wagnisart the wagnisart
     * @since 0.8
     */
    public static void unregister(final int satzart, final int sparte, final int wagnisart) {
        SatzTyp key = new SatzTyp(satzart, sparte, wagnisart, -1);
        REGISTERED_DATENSATZ_CLASSES.remove(key);
        REGISTERED_ENUM_CLASSES.remove(key);
    }

    /**
     * Gets the satz.
     *
     * @param satzart the satzart
     * @return angeforderte Satz
     * @since 0.2
     */
    public static Satz getSatz(final int satzart) {
        return getSatz(new SatzTyp(satzart));
    }
    
    /**
     * Holt einen Satz.
     * 
     * @param satztyp der Satztyp
     * @return angeforderter Satz
     * @since 18.04.2018
     */
    public static Satz getSatz(final SatzTyp satztyp) {
        Class<? extends Satz> clazz = REGISTERED_SATZ_CLASSES.get(new SatzTyp(satztyp.getSatzart()));
        if (clazz == null) {
            return generateSatz(satztyp);
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
    
    private static Satz generateSatz(final SatzTyp satztyp) {
        Class<? extends Enum<?>> enumClass = REGISTERED_ENUM_CLASSES.get(satztyp);
        if (enumClass == null) {
            Satz satz = XML_SERVICE.getSatzart(satztyp);
            if (satz == null) {
                throw new NotRegisteredException(satztyp);
            }
            try {
                return (Satz) satz.clone();
            } catch (CloneNotSupportedException e) {
                LOG.warn("Cannot clone {} - will return object itself.", satz);
                return satz;
            }
        }
        return new SatzX(satztyp, enumClass);
    }

    private static Datensatz generateDatensatz(final SatzTyp satzNr) {
        Class<? extends Enum<?>> enumClass = REGISTERED_ENUM_CLASSES.get(satzNr);
        if (enumClass != null) {
            return new SatzX(satzNr, enumClass);
        }
        LOG.trace("Will use fallback for Satz {}:", satzNr);
        return useFallback(satzNr);
    }

    /**
     * Versucht anhand des uebergebenen Strings herauszufinden, um was fuer eine
     * Satzart es sich handelt und liefert dann einen entsprechende (gefuellten)
     * Satz zurueck.
     *
     * @param content the content
     * @return einen gefuellten Satz
     * @since 0.2
     */
    public static Satz getSatz(final String content) {
        int satzart = Integer.parseInt(content.substring(0, 4));
        Satz satz;
        try {
            satz = getSatz(satzart);
        } catch (RuntimeException e) {
            LOG.debug("can't get Satz " + satzart + " (" + e + "), parsing Sparte...");
            int sparte = Integer.parseInt(content.substring(10, 13));
            satz = getDatensatz(satzart, sparte);
        }
        try {
            satz.importFrom(content);
            return satz;
        } catch (IOException ioe) {
            throw new IllegalArgumentException("can't parse " + content, ioe);
        }
    }

    /**
     * Gets the datensatz.
     *
     * @param satzart den registrierten Datensatz fuer
     * @return den registrierten Datensatz fuer 'satzart'
     * @since 0.2
     */
    public static Datensatz getDatensatz(final int satzart) {
        return (Datensatz) getSatz(satzart);
    }

    /**
     * Gets the datensatz.
     *
     * @param satzart z.B. 210
     * @param sparte z.B. 70 (Rechtsschutz)
     * @return den registrierten Datensatz fuer 'satzart', 'sparte'
     */
    public static Datensatz getDatensatz(final int satzart, final int sparte) {
        return getDatensatz(new SatzTyp(satzart, sparte));
    }

    /**
     * Gets the datensatz.
     *
     * @param satzart z.B. 210
     * @param sparte z.B. 70 (Rechtsschutz)
     * @param wagnisart z.B. 1 (Kapitallebensversicherung)
     * @return den registrierten Datensatz fuer 'satzart', 'sparte', 'wagnisart'
     *
     * @since 0.8
     */
    public static Datensatz getDatensatz(final int satzart, final int sparte, final int wagnisart) {
        return getDatensatz(new SatzTyp(satzart, sparte, wagnisart));
    }

    /**
     * Liefert den gewuenschten Datensatz. Mit der uebergebenen Satznummer wird
     * der Datensatz spezifizert, die folgendes enthaelt:
     * <ul>
     * <li>Satzart (z.B. 210)</li>
     * <li>Sparte (z.B. 70 fuer Rechtsschutz)</li>
     * <li>Wagnisart (z.B. 1 fuer Kapitallebensversicherung)</li>
     * <li>Teildatensatz-Nummer (6 = Bezugsrechte, 7 = Auszahlungen, 8 =
     * zukünftige Summenänderungen, 9 = Wertungssummen)</li>
     * </ul>
     *
     * @param satzNr z.B. new Satznummer(210, 70, 1, 6)
     * @return den registrierten Datensatz fuer 'satzart', 'sparte',
     *         'wagnisart', 'teildatensatzNummer'
     */
    public static Datensatz getDatensatz(final SatzTyp satzNr) {
        Class<? extends Datensatz> clazz = REGISTERED_DATENSATZ_CLASSES.get(satzNr);
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

    /**
     * Gets the datensatz.
     *
     * @param sparte the sparte
     * @param clazz the clazz
     * @return the datensatz
     */
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
     * zurueckgeben. Falls dieser nicht exisitert, wird ein (allgemeiner)
     * Datensatz mit Satzart und Sparte als Parameter erzeugt.
     * <p>
     * TODO: Besser waere es, zuerst den Datensatz zu suchen, der am besten
     * passt. D.h. auch die anderen Werte der uebergebenen SatzNummer wie Sparte
     * oder Wagnisart sollten dabei beruecksichtigt werden.
     * </p>
     *
     * @param satzNr die SatzNummer
     * @return der erzeugte Datensatz
     */
    private static Datensatz useFallback(final SatzTyp satzNr) {
        try {
            Datensatz fallback = (Datensatz) getSatz(satzNr);
            if (satzNr.hasSparte()) {
                fallback.setSparte(satzNr.getSparte());
            }
            if (fallback.hasFeld(Bezeichner.UNBEKANNT)) {
                try {
                    return (Datensatz) generateSatz(satzNr);
                } catch (NotRegisteredException ex) {
                    LOG.warn("XML-Fallback has " + satzNr + " not registed: " + ex);
                }
            }
            return fallback;
        } catch (NotRegisteredException re) {
            LOG.warn("Reduced functionality for (unknown or unsupported) Satzart " + satzNr + ":", re);
            Datensatz satz = new Datensatz(satzNr.getSatzart(), satzNr.getSparte());
            satz.addFiller();
            return satz;
        }
    }

    /**
     * Liefert ein Datenpaket mit allen unterstuetzten Satzarten.
     *
     * @return Datenpaket mit allen unterstuetzten Satzarten
     * @since 0.6
     */
    public static Datenpaket getAllSupportedSaetze() {
        Datenpaket all = new Datenpaket();
        for (int i = 2; i < 9999; i++) {
            try {
                all.add((Datensatz) getSatz(i));
            } catch (NotRegisteredException ex) {
                LOG.trace("Datensatz " + i + " is not a supported: ", ex);
            }
        }
        return all;
    }

}
