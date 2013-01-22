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
 * (c)reated 30.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.feld.Feld0001;
import gdv.xport.satz.feld.Feld100;
import gdv.xport.satz.feld.Feld200;
import gdv.xport.satz.feld.Feld9999;
import gdv.xport.satz.feld.common.TeildatensatzNummer;
import gdv.xport.satz.feld.sparte10.Feld220Wagnis0;
import gdv.xport.satz.feld.sparte10.wagnisart1_3.Feld220Wagnis1_3;
import gdv.xport.satz.feld.sparte10.wagnisart1_3.Feld220Wagnis1_3Auszahlungen;
import gdv.xport.satz.feld.sparte10.wagnisart1_3.Feld220Wagnis1_3Bezugsrechte;
import gdv.xport.satz.feld.sparte10.wagnisart1_3.Feld220Wagnis1_3Wertungssummen;
import gdv.xport.satz.feld.sparte10.wagnisart1_3.Feld220Wagnis1_3ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart1_3.Feld221Wagnis1_3;
import gdv.xport.satz.feld.sparte10.wagnisart1_3.Feld221Wagnis1_3Auszahlungen;
import gdv.xport.satz.feld.sparte10.wagnisart1_3.Feld221Wagnis1_3ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld220Wagnis2;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld220Wagnis2Auszahlungen;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld220Wagnis2Bezugsrechte;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld220Wagnis2Wertungssummen;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld220Wagnis2ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld221Wagnis2;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld221Wagnis2Auszahlungen;
import gdv.xport.satz.feld.sparte10.wagnisart2.Feld221Wagnis2ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart4_8.Feld220Wagnis4_8;
import gdv.xport.satz.feld.sparte10.wagnisart4_8.Feld220Wagnis4_8Bezugsrechte;
import gdv.xport.satz.feld.sparte10.wagnisart4_8.Feld220Wagnis4_8Wertungssummen;
import gdv.xport.satz.feld.sparte10.wagnisart4_8.Feld220Wagnis4_8ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart4_8.Feld221Wagnis4_8;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld220Wagnis5;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld220Wagnis5Bezugsrechte;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld220Wagnis5Wertungssummen;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld220Wagnis5ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld221Wagnis5;
import gdv.xport.satz.feld.sparte10.wagnisart5.Feld221Wagnis5ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld220Wagnis6;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld220Wagnis6Bezugsrechte;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld220Wagnis6Wertungssummen;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld220Wagnis6ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld221Wagnis6;
import gdv.xport.satz.feld.sparte10.wagnisart6.Feld221Wagnis6ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld220Wagnis7;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld220Wagnis7Bezugsrechte;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld220Wagnis7Wertungssummen;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld220Wagnis7ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld221Wagnis7;
import gdv.xport.satz.feld.sparte10.wagnisart7.Feld221Wagnis7ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart9.Feld220Wagnis9;
import gdv.xport.satz.feld.sparte10.wagnisart9.Feld220Wagnis9Auszahlungen;
import gdv.xport.satz.feld.sparte10.wagnisart9.Feld220Wagnis9Bezugsrechte;
import gdv.xport.satz.feld.sparte10.wagnisart9.Feld220Wagnis9Wertungssummen;
import gdv.xport.satz.feld.sparte10.wagnisart9.Feld220Wagnis9ZukSummenaenderungen;
import gdv.xport.satz.feld.sparte10.wagnisart9.Feld230;
import gdv.xport.satz.model.Satz210;
import gdv.xport.satz.model.Satz211;
import gdv.xport.satz.model.Satz220;
import gdv.xport.satz.model.Satz221;
import gdv.xport.satz.model.SatzX;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Diese Klasse dient dazu, um einen vorgegebene Satz, der z.B. aus einem Import kommt, in den entsprechende Satz
 * wandeln zu koennen.
 * 
 * @author oliver (ob@aosd.de)
 * @since 0.1.0 (30.10.2009)
 */
public final class SatzFactory {

    /** The Constant log. */
    private static final Log log = LogFactory.getLog(SatzFactory.class);

    /** The registered satz classes. */
    private static final Map<Integer, Class<? extends Satz>> registeredSatzClasses = new HashMap<Integer, Class<? extends Satz>>();

    /** The registered datensatz classes. */
    private static final Map<Integer, Class<? extends Datensatz>> registeredDatensatzClasses = new ConcurrentHashMap<Integer, Class<? extends Datensatz>>();

    /** The registered Enum classes. */
    private static final Map<Integer, Class<? extends Enum<?>>> registeredEnumClasses = new HashMap<Integer, Class<? extends Enum<?>>>();

    static {
        registerEnum(Feld0001.class, 1);
        registerEnum(Feld100.class, 100);
        registerEnum(Feld200.class, 200);
        register(Satz210.class, 210);
        register(Satz211.class, 211);
        register(Satz220.class, 220);
        register(Satz221.class, 221);
        registerEnum(Feld9999.class, 9999);

        // Sparte 10 - Leben
        registerEnum(gdv.xport.satz.feld.sparte10.Feld210.class, 210, 10, -1, -1);
        registerEnum(gdv.xport.satz.feld.sparte10.Feld211.class, 211, 10, -1, -1);
        // Sparte 10 - Leben - Wagnisart 0
        registerEnum(Feld220Wagnis0.class, 220, 10, 0, -1);

        // Sparte 10 - Leben - Wagnisart 1 & 3
        registerEnum(Feld220Wagnis1_3.class, 220, 10, 1, -1);
        registerEnum(Feld220Wagnis1_3.class, 220, 10, 3, -1);
        registerEnum(Feld220Wagnis1_3Auszahlungen.class, 220, 10, 1, TeildatensatzNummer.AUSZAHLUNGEN.getNummer());
        registerEnum(Feld220Wagnis1_3Auszahlungen.class, 220, 10, 3, TeildatensatzNummer.AUSZAHLUNGEN.getNummer());
        registerEnum(Feld220Wagnis1_3Bezugsrechte.class, 220, 10, 1, TeildatensatzNummer.BEZUGSRECHTE.getNummer());
        registerEnum(Feld220Wagnis1_3Bezugsrechte.class, 220, 10, 3, TeildatensatzNummer.BEZUGSRECHTE.getNummer());
        registerEnum(Feld220Wagnis1_3ZukSummenaenderungen.class, 220, 10, 1,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());
        registerEnum(Feld220Wagnis1_3ZukSummenaenderungen.class, 220, 10, 3,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());
        registerEnum(Feld220Wagnis1_3Wertungssummen.class, 220, 10, 1, TeildatensatzNummer.WERTUNGSSUMMEN.getNummer());
        registerEnum(Feld220Wagnis1_3Wertungssummen.class, 220, 10, 3, TeildatensatzNummer.WERTUNGSSUMMEN.getNummer());

        registerEnum(Feld221Wagnis1_3.class, 221, 10, 1, -1);
        registerEnum(Feld221Wagnis1_3.class, 221, 10, 3, -1);
        registerEnum(Feld221Wagnis1_3Auszahlungen.class, 221, 10, 1, TeildatensatzNummer.AUSZAHLUNGEN.getNummer());
        registerEnum(Feld221Wagnis1_3Auszahlungen.class, 221, 10, 3, TeildatensatzNummer.AUSZAHLUNGEN.getNummer());
        registerEnum(Feld221Wagnis1_3ZukSummenaenderungen.class, 221, 10, 1,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());
        registerEnum(Feld221Wagnis1_3ZukSummenaenderungen.class, 221, 10, 3,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());

        // Sparte 10 - Leben - Wagnisart 2
        registerEnum(Feld220Wagnis2.class, 220, 10, 2, -1);
        registerEnum(Feld220Wagnis2Bezugsrechte.class, 220, 10, 2, TeildatensatzNummer.BEZUGSRECHTE.getNummer());
        registerEnum(Feld220Wagnis2Auszahlungen.class, 220, 10, 2, TeildatensatzNummer.AUSZAHLUNGEN.getNummer());
        registerEnum(Feld220Wagnis2Wertungssummen.class, 220, 10, 2, TeildatensatzNummer.WERTUNGSSUMMEN.getNummer());
        registerEnum(Feld220Wagnis2ZukSummenaenderungen.class, 220, 10, 2,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());

        registerEnum(Feld221Wagnis2.class, 221, 10, 2, -1);
        registerEnum(Feld221Wagnis2Auszahlungen.class, 221, 10, 2, TeildatensatzNummer.AUSZAHLUNGEN.getNummer());
        registerEnum(Feld221Wagnis2ZukSummenaenderungen.class, 221, 10, 2,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());

        // Sparte 10 - Leben - Wagnisart 4 & 8
        registerEnum(Feld220Wagnis4_8.class, 220, 10, 4, -1);
        registerEnum(Feld220Wagnis4_8.class, 220, 10, 8, -1);
        registerEnum(Feld220Wagnis4_8Bezugsrechte.class, 220, 10, 4, TeildatensatzNummer.BEZUGSRECHTE.getNummer());
        registerEnum(Feld220Wagnis4_8Bezugsrechte.class, 220, 10, 8, TeildatensatzNummer.BEZUGSRECHTE.getNummer());
        registerEnum(Feld220Wagnis4_8Wertungssummen.class, 220, 10, 4, TeildatensatzNummer.WERTUNGSSUMMEN.getNummer());
        registerEnum(Feld220Wagnis4_8Wertungssummen.class, 220, 10, 8, TeildatensatzNummer.WERTUNGSSUMMEN.getNummer());
        registerEnum(Feld220Wagnis4_8ZukSummenaenderungen.class, 220, 10, 4,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());
        registerEnum(Feld220Wagnis4_8ZukSummenaenderungen.class, 220, 10, 8,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());

        registerEnum(Feld221Wagnis4_8.class, 221, 10, 4, -1);
        registerEnum(Feld221Wagnis4_8.class, 221, 10, 8, -1);

        // Sparte 10 - Leben - Wagnisart 5
        registerEnum(Feld220Wagnis5.class, 220, 10, 5, -1);
        registerEnum(Feld220Wagnis5Bezugsrechte.class, 220, 10, 5, TeildatensatzNummer.BEZUGSRECHTE.getNummer());
        registerEnum(Feld220Wagnis5Wertungssummen.class, 220, 10, 5, TeildatensatzNummer.WERTUNGSSUMMEN.getNummer());
        registerEnum(Feld220Wagnis5ZukSummenaenderungen.class, 220, 10, 5,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());

        registerEnum(Feld221Wagnis5.class, 221, 10, 5, -1);
        registerEnum(Feld221Wagnis5ZukSummenaenderungen.class, 221, 10, 5,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());

        // Sparte 10 - Leben - Wagnisart 6
        registerEnum(Feld220Wagnis6.class, 220, 10, 6, -1);
        registerEnum(Feld220Wagnis6Bezugsrechte.class, 220, 10, 6, TeildatensatzNummer.BEZUGSRECHTE.getNummer());
        registerEnum(Feld220Wagnis6Wertungssummen.class, 220, 10, 6, TeildatensatzNummer.WERTUNGSSUMMEN.getNummer());
        registerEnum(Feld220Wagnis6ZukSummenaenderungen.class, 220, 10, 6,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());

        registerEnum(Feld221Wagnis6.class, 221, 10, 6, -1);
        registerEnum(Feld221Wagnis6ZukSummenaenderungen.class, 221, 10, 6,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());

        // Sparte 10 - Leben - Wagnisart 7
        registerEnum(Feld220Wagnis7.class, 220, 10, 7, -1);
        registerEnum(Feld220Wagnis7Bezugsrechte.class, 220, 10, 7, TeildatensatzNummer.BEZUGSRECHTE.getNummer());
        registerEnum(Feld220Wagnis7Wertungssummen.class, 220, 10, 7, TeildatensatzNummer.WERTUNGSSUMMEN.getNummer());
        registerEnum(Feld220Wagnis7ZukSummenaenderungen.class, 220, 10, 7,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());

        registerEnum(Feld221Wagnis7.class, 221, 10, 7, -1);
        registerEnum(Feld221Wagnis7ZukSummenaenderungen.class, 221, 10, 7,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());

        registerEnum(Feld230.class, 230, 10, 7, -1);

        // Sparte 10 - Leben - Wagnisart 9
        registerEnum(Feld220Wagnis9.class, 220, 10, 9, -1);
        registerEnum(Feld220Wagnis9Bezugsrechte.class, 220, 10, 9, TeildatensatzNummer.BEZUGSRECHTE.getNummer());
        registerEnum(Feld220Wagnis9Auszahlungen.class, 220, 10, 9, TeildatensatzNummer.AUSZAHLUNGEN.getNummer());
        registerEnum(Feld220Wagnis9Wertungssummen.class, 220, 10, 9, TeildatensatzNummer.WERTUNGSSUMMEN.getNummer());
        registerEnum(Feld220Wagnis9ZukSummenaenderungen.class, 220, 10, 9,
                TeildatensatzNummer.ZUKUENFTIGE_SUMMENAENDERUNG.getNummer());

        registerEnum(Feld230.class, 230, 10, 9, -1);

        // Sparte 30 - ?????
        registerEnum(gdv.xport.satz.feld.sparte30.Feld210.class, 210, 30, -1, -1);
        registerEnum(gdv.xport.satz.feld.sparte30.Feld220.class, 220, 30, -1, -1);
        registerEnum(gdv.xport.satz.feld.sparte30.Feld221.class, 221, 30, -1, -1);
        // Sparte 50 - ?????
        registerEnum(gdv.xport.satz.feld.sparte50.Feld210.class, 210, 50, -1, -1);
        registerEnum(gdv.xport.satz.feld.sparte50.Feld211.class, 211, 50, -1, -1);
        // Sparte 51 - ?????
        registerEnum(gdv.xport.satz.feld.sparte51.Feld220.class, 220, 51, -1, -1);
        registerEnum(gdv.xport.satz.feld.sparte51.Feld221.class, 221, 51, -1, -1);
        // Sparte 52 - ?????
        registerEnum(gdv.xport.satz.feld.sparte52.Feld220.class, 220, 52, -1, -1);
        registerEnum(gdv.xport.satz.feld.sparte52.Feld221.class, 221, 52, -1, -1);
        // Sparte 53 - ?????
        registerEnum(gdv.xport.satz.feld.sparte53.Feld220.class, 220, 53, -1, -1);
        registerEnum(gdv.xport.satz.feld.sparte53.Feld221.class, 221, 53, -1, -1);
        // Sparte 70 - ?????
        registerEnum(gdv.xport.satz.feld.sparte70.Feld210.class, 210, 70, -1, -1);
        registerEnum(gdv.xport.satz.feld.sparte70.Feld220.class, 220, 70, -1, -1);
        registerEnum(gdv.xport.satz.feld.sparte70.Feld221.class, 221, 70, -1, -1);
    }

    /**
     * Instantiates a new satz factory.
     */
    private SatzFactory() {
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht unterstuetzte Datensaetze) registriert werden.
     * Die Kasse <em>muss</em> einen Default-Konstruktor bereitstellen. Ansonsten wird hier eine
     * {@link IllegalArgumentException} geworfen (seit 0.6).
     * 
     * @param clazz
     *            the clazz
     * @param satzart
     *            the satzart
     * @since 0.2
     */
    public static void register(final Class<? extends Satz> clazz, final int satzart) {
        try {
            Constructor<? extends Satz> ctor = clazz.getConstructor();
            if (log.isDebugEnabled()) {
                log.debug("default constructor " + ctor + " found");
            }
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("no default constructor found in " + clazz);
        }
        registeredSatzClasses.put(satzart, clazz);
    }

    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der Datensatz-Beschreibung uebergeben wird.
     * 
     * @param enumClass
     *            die Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzart
     *            die Satzart
     * @since 0.6
     */
    public static void registerEnum(final Class<? extends Enum<?>> enumClass, final int satzart) {
        assert (0 <= satzart) && (satzart <= 9999) : "Satzart muss zwischen 0 und 9999 liegen";
        registeredEnumClasses.put(satzart, enumClass);
    }

    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der Datensatz-Beschreibung uebergeben wird.
     * 
     * @param enumClass
     *            die Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzart
     *            die Satzart
     * @param sparte
     *            die Sparte
     * @param wagnisart
     *            die Wagnisart
     * @param laufNumSatzart
     *            die laufende Nummer der Satzart
     * @since 0.8
     */
    public static void registerEnum(final Class<? extends Enum<?>> enumClass, final int satzart, final int sparte,
            final int wagnisart, final int laufNumSatzart) {
        assert (0 <= satzart) && (satzart <= 9999) : "Satzart muss zwischen 0 und 9999 liegen";
        assert (0 <= sparte) && (sparte <= 999) : "Sparte muss zwischen 0 und 999 liegen";
        if (wagnisart != -1) {
            assert (0 <= wagnisart) && (wagnisart <= 9) : "Wagnisart muss zwischen 0 und 9 liegen";
        }
        if (laufNumSatzart != -1) {
            assert (0 <= laufNumSatzart) && (laufNumSatzart <= 9) : "Laufende Nummer Satzart muss zwischen 0 und 9 liegen";
        }
        int key = getAsKey(satzart, sparte, wagnisart, laufNumSatzart);
        registeredDatensatzClasses.remove(key);
        registeredEnumClasses.put(key, enumClass);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's Testen hilfreich sein kann)
     * 
     * @param satzart
     *            the satzart
     * @since 0.2
     */
    public static void unregister(final int satzart) {
        registeredSatzClasses.remove(satzart);
        registeredEnumClasses.remove(satzart);
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht unterstuetzte Datensaetze) registriert werden.
     * 
     * @param clazz
     *            the clazz
     * @param satzart
     *            the satzart
     * @param sparte
     *            the sparte
     * @since 0.2
     */
    public static void register(final Class<? extends Datensatz> clazz, final int satzart, final int sparte) {
        register(clazz, satzart, sparte, -1, -1);
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht unterstuetzte Datensaetze) registriert werden.
     * 
     * @param clazz
     *            the clazz
     * @param satzart
     *            the satzart
     * @param sparte
     *            the sparte
     * @param wagnisart
     *            the wagnisart
     * @param teildatensatzNummer
     *            the teildatensatzNummer
     * @since 0.8
     */
    public static void register(final Class<? extends Datensatz> clazz, final int satzart, final int sparte,
            final int wagnisart, final int teildatensatzNummer) {
        assert (0 <= satzart) && (satzart <= 9999) : "Satzart muss zwischen 0 und 9999 liegen";
        assert (0 <= sparte) && (sparte <= 999) : "Sparte muss zwischen 0 und 999 liegen";
        if (wagnisart != -1) {
            assert (0 <= wagnisart) && (wagnisart <= 9) : "Wagnisart muss zwischen 0 und 9 liegen";
            if (teildatensatzNummer != -1) {
                assert (0 <= teildatensatzNummer) && (teildatensatzNummer <= 9) : "teildatensatzNummer muss zwischen 0 und 9 liegen";
            }
        }
        int key = getAsKey(satzart, sparte, wagnisart, teildatensatzNummer);
        registeredDatensatzClasses.put(key, clazz);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's Testen hilfreich sein kann)
     * 
     * @param satzart
     *            the satzart
     * @param sparte
     *            the sparte
     * @since 0.2
     */
    public static void unregister(final int satzart, final int sparte) {
        unregister(satzart, sparte, -1);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's Testen hilfreich sein kann)
     * 
     * @param satzart
     *            the satzart
     * @param sparte
     *            the sparte
     * @param wagnisart
     *            the wagnisart
     * @since 0.8
     */
    public static void unregister(final int satzart, final int sparte, final int wagnisart) {
        int key = getAsKey(satzart, sparte, wagnisart, -1);
        registeredDatensatzClasses.remove(key);
        registeredEnumClasses.remove(key);
    }

    /**
     * Gets the as key.
     * 
     * @param satzart
     *            the satzart
     * @param sparte
     *            the sparte
     * @param wagnisart
     *            the wagnisart
     * @return the as key
     */
    private static int getAsKey(final int satzart, final int sparte, final int wagnisart, final int teildatensatzNummer) {
        int key = satzart * 1000 + sparte;

        if (wagnisart != -1) {
            key = key * 10 + wagnisart;
            if (teildatensatzNummer != -1) {
                key = key * 10 + teildatensatzNummer;
            }
        }

        return key;
    }

    /**
     * Gets the satz.
     * 
     * @param satzart
     *            the satzart
     * @return angeforderte Satz
     * @since 0.2
     */
    public static Satz getSatz(final int satzart) {
        Class<? extends Satz> clazz = registeredSatzClasses.get(satzart);
        if (clazz == null) {
            return generateSatz(satzart);
        }
        try {
            Satz satz = clazz.newInstance();
            if (satz.getSatzart() != satzart) {
                Constructor<? extends Satz> ctor = clazz.getConstructor(int.class);
                satz = ctor.newInstance(satzart);
            }
            return satz;
        } catch (Exception e) {
            log.info("default constructor does not work (" + e + "), trying another ctor...");
            Constructor<? extends Satz> ctor = null;
            try {
                ctor = clazz.getConstructor(int.class);
                return ctor.newInstance(satzart);
            } catch (InvocationTargetException ite) {
                throw new RuntimeException(ite.getTargetException() + " in " + ctor, ite);
            } catch (NoSuchMethodException nsme) {
                throw new UnsupportedOperationException("registered " + clazz + " has not the required ctor", nsme);
            } catch (InstantiationException ie) {
                throw new RuntimeException("registered " + clazz + " can't be instantiated", ie);
            } catch (IllegalAccessException iae) {
                throw new IllegalStateException("registered " + clazz + " can't be accessed", iae);
            }
        }
    }

    private static Satz generateSatz(final int satzart) {
        Class<? extends Enum<?>> enumClass = registeredEnumClasses.get(satzart);
        if (enumClass == null) {
            throw new NotRegisteredException(satzart);
        }
        return new SatzX(satzart, enumClass);
    }

    private static Datensatz generateDatensatz(final int satzart, final int sparte, final int wagnisart,
            final int teildatensatzNummer) {
        int key = getAsKey(satzart, sparte, wagnisart, teildatensatzNummer);
        Class<? extends Enum<?>> enumClass = registeredEnumClasses.get(key);
        if (enumClass == null) {
            return useFallback(satzart, sparte);
        }
        return new SatzX(satzart, sparte, wagnisart, teildatensatzNummer, enumClass);
    }

    /**
     * Versucht anhand des uebergebenen Strings herauszufinden, um was fuer eine Satzart es sich handelt und liefert
     * dann einen entsprechende (gefuellten) Satz zurueck.
     * 
     * @param content
     *            the content
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
     * @param satzart
     *            den registrierten Datensatz fuer
     * @return den registrierten Datensatz fuer 'satzart'
     * @since 0.2
     */
    public static Datensatz getDatensatz(final int satzart) {
        return (Datensatz) getSatz(satzart);
    }

    /**
     * Gets the datensatz.
     * 
     * @param satzart
     *            z.B. 210
     * @param sparte
     *            z.B. 70 (Rechtsschutz)
     * @return den registrierten Datensatz fuer 'satzart', 'sparte'
     */
    public static Datensatz getDatensatz(final int satzart, final int sparte) {
        return getDatensatz(satzart, sparte, -1, -1);
    }

    /**
     * Gets the datensatz.
     * 
     * @param satzart
     *            z.B. 210
     * @param sparte
     *            z.B. 70 (Rechtsschutz)
     * @param wagnisart
     *            z.B. 1 (Kapitallebensversicherung)
     * @return den registrierten Datensatz fuer 'satzart', 'sparte', 'wagnisart'
     * 
     * @since 0.8
     */
    public static Datensatz getDatensatz(final int satzart, final int sparte, final int wagnisart) {
        return getDatensatz(satzart, sparte, -1, -1);
    }

    /**
     * Gets the datensatz.
     * 
     * @param satzart
     *            z.B. 210
     * @param sparte
     *            z.B. 70 (Rechtsschutz)
     * @param wagnisart
     *            z.B. 1 (Kapitallebensversicherung)
     * @param teildatensatzNummer
     *            6 = Bezugsrechte, 7 = Auszahlungen, 8 = zukünftige Summenänderungen, 9 = Wertungssummen
     * @return den registrierten Datensatz fuer 'satzart', 'sparte', 'wagnisart', 'teildatensatzNummer'
     * 
     * @since 0.9
     */
    public static Datensatz getDatensatz(final int satzart, final int sparte, final int wagnisart,
            final int teildatensatzNummer) {
        int key = getAsKey(satzart, sparte, wagnisart, teildatensatzNummer);
        Class<? extends Datensatz> clazz = registeredDatensatzClasses.get(key);
        if (clazz == null) {
            return generateDatensatz(satzart, sparte, wagnisart, teildatensatzNummer);
        }
        try {
            Constructor<? extends Datensatz> ctor = clazz.getConstructor(int.class, int.class);
            return ctor.newInstance(satzart, sparte);
        } catch (NoSuchMethodException exWithTwoParams) {
            log.info("constructor " + clazz + "(int, int) not found (" + exWithTwoParams + ")");
            return getDatensatz(sparte, clazz);
        } catch (InstantiationException exWithTwoParams) {
            log.info(clazz + "(int, int) can't be instantiated (" + exWithTwoParams + ")");
            return getDatensatz(sparte, clazz);
        } catch (IllegalAccessException exWithTwoParams) {
            log.info(clazz + "(int, int) can't be accessed (" + exWithTwoParams + ")");
            return getDatensatz(sparte, clazz);
        } catch (InvocationTargetException exWithTwoParams) {
            log.info("error in calling " + clazz + "(int, int): " + exWithTwoParams);
            return getDatensatz(sparte, clazz);
        }
    }

    /**
     * Gets the datensatz.
     * 
     * @param sparte
     *            the sparte
     * @param clazz
     *            the clazz
     * @return the datensatz
     */
    private static Datensatz getDatensatz(final int sparte, final Class<? extends Datensatz> clazz) {
        try {
            Constructor<? extends Datensatz> ctor = clazz.getConstructor(int.class);
            return ctor.newInstance(sparte);
        } catch (NoSuchMethodException nsme) {
            log.info(clazz + " found but no " + clazz.getSimpleName() + "(" + sparte + ") constructor (" + nsme + ")");
            return getDatensatz(clazz);
        } catch (Exception exWithOneParam) {
            log.warn("constructor problem with " + clazz, exWithOneParam);
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
     * Use fallback.
     * 
     * @param satzart
     *            the satzart
     * @param sparte
     *            the sparte
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

    /**
     * Liefert ein Datenpaket mit allen unterstuetzten Satzarten und Sparten.
     * <p>
     * FIXME: beruecksichtigt noch nicht die Wagnisart (d.h. Saetze mit einer Wagnisart sind noch nicht im
     * zurueckgegebenen Datenpaket enthalten)
     * </p>
     * 
     * @since 0.6
     * @return Datenpaket mit allen unterstuetzten Satzarten
     */
    public static Datenpaket getAllSupportedSaetze() {
        Datenpaket all = new Datenpaket();
        for (Integer satzart : registeredSatzClasses.keySet()) {
            try {
                all.add(getDatensatz(satzart));
            } catch (ClassCastException canhappen) {
                if ((satzart != 1) && (satzart != 9999)) {
                    log.warn("Satzart " + satzart + " not added as supported Satz", canhappen);
                }
            }
        }
        for (Integer key : registeredDatensatzClasses.keySet()) {
            addDatensatzTo(all, key);
        }
        for (Integer key : registeredEnumClasses.keySet()) {
            addDatensatzTo(all, key);
        }
        return all;
    }

    private static void addDatensatzTo(final Datenpaket all, final Integer key) {
        int sparte = key % 1000;
        int satzart = key / 1000;
        all.add(getDatensatz(satzart, sparte));
    }

}
