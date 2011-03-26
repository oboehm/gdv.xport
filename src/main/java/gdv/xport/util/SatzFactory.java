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
 * (c)reated 30.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.util;

import gdv.xport.satz.*;
import gdv.xport.satz.model.*;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;

import org.apache.commons.logging.*;

/**
 * Diese Klasse dient dazu, um einen vorgegebene Satz, der z.B. aus einem
 * Import kommt, in den entsprechende Satz wandeln zu koennen.
 *
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.1.0 (30.10.2009)
 */
public final class SatzFactory {

    /** The Constant log. */
    private static final Log log = LogFactory.getLog(SatzFactory.class);
    
    /** The registered satz classes. */
    private static Map<Integer, Class<? extends Satz>> registeredSatzClasses =
        new HashMap<Integer, Class<? extends Satz>>();
    
    /** The registered datensatz classes. */
    private static Map<Integer, Class<? extends Datensatz>> registeredDatensatzClasses =
        new HashMap<Integer, Class<? extends Datensatz>>();

    static {
        registeredSatzClasses.put(1, Vorsatz.class);
        registeredSatzClasses.put(100, Satz0100.class);
        registeredSatzClasses.put(200, Satz0200.class);
        registeredSatzClasses.put(210, VertragsspezifischerTeil.class);
        registeredSatzClasses.put(220, SpartenspezifischerTeil.class);
        registeredSatzClasses.put(221, Erweiterungssatz221.class);
        registeredSatzClasses.put(9999, Nachsatz.class);
        register(Satz0210.class, 210, 10);
    }

    /**
     * Instantiates a new satz factory.
     */
    private SatzFactory() {}

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden.
     *
     * @param clazz the clazz
     * @param satzart the satzart
     * @since 0.2
     */
    public static void register(final Class<? extends Satz> clazz, final int satzart) {
        registeredSatzClasses.put(satzart, clazz);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann)
     *
     * @param satzart the satzart
     * @since 0.2
     */
    public static void unregister(final int satzart) {
        registeredSatzClasses.remove(satzart);
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
    public static void register(final Class<? extends Datensatz> clazz, final int satzart,
            final int sparte) {
        assert (0 <= satzart) && (satzart <= 9999) : "Satzart muss zwischen 0 und 9999 liegen";
        assert (0 <= sparte) && (sparte <= 999)    : "Sparte muss zwischen 0 und 999 liegen";
        int key = getAsKey(satzart, sparte);
        registeredDatensatzClasses.put(key, clazz);
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
        registeredDatensatzClasses.remove(getAsKey(satzart, sparte));
    }

    /**
     * Gets the as key.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @return the as key
     */
    private static int getAsKey(final int satzart, final int sparte) {
        return satzart * 1000 + sparte;
    }

    /**
     * Gets the satz.
     *
     * @param satzart the satzart
     * @return angeforderte Satz
     * @since 0.2
     */
    public static Satz getSatz(final int satzart) {
        Class<? extends Satz> clazz = registeredSatzClasses.get(satzart);
        if (clazz == null) {
            throw new NotRegisteredException(satzart);
        }
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            log.info("default ctor does not work (" + e + "), trying another ctor...");
            Constructor<? extends Satz> ctor = null;
            try {
                ctor = clazz.getConstructor(int.class);
                return ctor.newInstance(satzart);
            } catch (InvocationTargetException ite) {
                throw new RuntimeException(ite.getTargetException() + " in " + ctor, ite);
            } catch (NoSuchMethodException nsme) {
                throw new UnsupportedOperationException("registered " + clazz
                        + " has not the required ctor", nsme);
            } catch (InstantiationException ie) {
                throw new RuntimeException("registered " + clazz + " can't be instantiated", ie);
            } catch (IllegalAccessException iae) {
                throw new IllegalStateException("registered " + clazz + " can't be accessed", iae);
            }
        }
    }

    /**
     * Versucht anhand des uebergebenen Strings herauszufinden, um was fuer
     * eine Satzart es sich handelt und liefert dann einen entsprechende
     * (gefuellten) Satz zurueck.
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
            log.debug("can't get Satz " + satzart + " (" + e + "), parsing Sparte...");
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
        int key = getAsKey(satzart, sparte);
        Class<? extends Datensatz> clazz = registeredDatensatzClasses.get(key);
        if (clazz == null) {
            return useFallback(satzart, sparte);
        }
        try {
            return clazz.newInstance();
        } catch (RuntimeException re) {
            throw re;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("can't access default ctor");
        } catch (InstantiationException e) {
            try {
                log.info("default ctor does not work (" + e + "), trying another ctor...");
                Constructor<? extends Datensatz> ctor = clazz.getConstructor(int.class, int.class);
                return ctor.newInstance(satzart, sparte);
            } catch (NoSuchMethodException exWithTwoParams) {
                log.info("ctor " + clazz + "(int, int) not found (" + exWithTwoParams + ")");
                return getDatensatz(satzart, sparte, clazz, exWithTwoParams);
            } catch (InstantiationException exWithTwoParams) {
                log.info(clazz + "(int, int) can't be instantiated (" + exWithTwoParams + ")");
                return getDatensatz(satzart, sparte, clazz, exWithTwoParams);
            } catch (IllegalAccessException exWithTwoParams) {
                log.info(clazz + "(int, int) can't be accessed (" + exWithTwoParams + ")");
                return getDatensatz(satzart, sparte, clazz, exWithTwoParams);
            } catch (InvocationTargetException exWithTwoParams) {
                log.info("error in calling " + clazz + "(int, int): " + exWithTwoParams);
                return getDatensatz(satzart, sparte, clazz, exWithTwoParams);
            }
        }
    }

    /**
     * Gets the datensatz.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @param clazz the clazz
     * @param exWithTwoParams the ex with two params
     * @return the datensatz
     */
    private static Datensatz getDatensatz(final int satzart, final int sparte,
            final Class<? extends Datensatz> clazz, final Exception exWithTwoParams) {
        try {
            log.info("default ctor does not work (" + exWithTwoParams
                    + "), trying another ctor...");
            Constructor<? extends Datensatz> ctor = clazz.getConstructor(int.class);
            return ctor.newInstance(satzart);
        } catch (NoSuchMethodException nsme) {
            throw new IllegalArgumentException(clazz + " found for Satzart " + satzart
                    + ", but no default ctor, no " + clazz.getSimpleName() + "(" + sparte
                    + ") ctor", nsme);
        } catch (Exception exWithOneParam) {
            throw new RuntimeException("constructor problem with " + clazz, exWithOneParam);
        }
    }

    /**
     * Use fallback.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @return the datensatz
     */
    private static Datensatz useFallback(final int satzart, final int sparte) {
        try {
            Datensatz fallback = (Datensatz) getSatz(satzart);
            fallback.setSparte(sparte);
            return fallback;
        } catch (NotRegisteredException re) {
            log.warn("reduced functionality for (unknown or unsupported) Satzart " + satzart);
            Datensatz satz = new Datensatz(satzart, sparte);
            satz.addFiller();
            return satz;
        }
    }

}

