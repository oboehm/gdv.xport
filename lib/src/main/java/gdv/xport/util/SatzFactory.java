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
import gdv.xport.satz.model.SatzX;
import gdv.xport.satz.xml.SatzXml;
import gdv.xport.satz.xml.XmlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
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
            new ConcurrentHashMap<>();
    private static final Map<SatzTyp, Class<? extends Datensatz>> REGISTERED_DATENSATZ_CLASSES =
            new ConcurrentHashMap<>();
    private static final Map<SatzTyp, Class<? extends Enum>> REGISTERED_ENUM_CLASSES =
            new ConcurrentHashMap<>();
    private static final XmlService XML_SERVICE = XmlService.getInstance();

    static {
        registerDefault();
    }

    private static void registerDefault() {
        register(Vorsatz.class, 1);
        // Mit Ausnahme von Vorsatz und Nachsatz werden alle Saetze jetzt vom XmlService behandelt
        register(Nachsatz.class, 9999);
    }

    private SatzFactory() {
    }

    /**
     * Mit dieser Klasse konnen die Registrierungen wieder komplett
     * rueckgaengig gemacht werden. Diese Methode wurde vor allem zur
     * Unterstuetzung der Unit-Tests eingefuehrt.
     *
     * @since 4.1.1
     */
    public static void reset() {
        REGISTERED_SATZ_CLASSES.clear();
        REGISTERED_DATENSATZ_CLASSES.clear();
        REGISTERED_ENUM_CLASSES.clear();
        registerDefault();
        LOG.debug("Satzfactory wurde zurueckgesetzt.");
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
  public static void register(final Class<? extends Satz> clazz, final int satzart)  {
      try {
           Constructor<? extends Satz> ctor = clazz.getConstructor();
           LOG.debug("Default constructor {} found.", ctor);
      } catch (NoSuchMethodException ex) {
          throw new IllegalArgumentException("no default constructor found in "   + clazz, ex);
      }
      StringBuilder satzartBuf = new StringBuilder();
      satzartBuf.append(String.format("%04d", satzart));
      REGISTERED_SATZ_CLASSES.put(SatzTyp.of(satzartBuf.toString()), clazz);
  }

    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der
     * Datensatz-Beschreibung uebergeben wird.
     *
     * @param enumClass die Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzart die Satzart (1-9999)
     * @since 0.6
     * @deprecated bitte nur noch {@link #registerEnum(Class, SatzTyp)} verwenden
     */
    @Deprecated
    public static void registerEnum(final Class<? extends Enum> enumClass, final int satzart) {
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
     * @deprecated bitte nur noch {@link #registerEnum(Class, SatzTyp)} verwenden
     */
    @Deprecated
    public static void registerEnum(final Class<? extends Enum> enumClass, final int satzart, final int sparte) {
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
    public static void registerEnum(final Class<? extends Enum> enumClass, final SatzTyp satzNr) {
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
     * @deprecated in v4.3 durch {@link #unregister(SatzTyp)} ersetzt
     */
    @Deprecated
    public static void unregister(final int satzart) {
        SatzTyp key = new SatzTyp(satzart);
        REGISTERED_SATZ_CLASSES.remove(key);
        REGISTERED_ENUM_CLASSES.remove(key);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann). Diese unregister-Methode ersetzt ab 4.2 die
     * anderen uregister-Methoden.
     *
     * @param typ SatzTyp bzw. Satzart
     * @since 4.3
     */
    public static void unregister(SatzTyp typ) {
        REGISTERED_SATZ_CLASSES.remove(typ);
        REGISTERED_ENUM_CLASSES.remove(typ);
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
     * @deprecated in v4.3 durch {@link #unregister(SatzTyp)} ersetzt
     */
    @Deprecated
    public static void unregister(final int satzart, final int sparte) {
        unregister(new SatzTyp(satzart, sparte));
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann)
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @param wagnisart the wagnisart
     * @since 0.8
     * @deprecated in v4.3 durch {@link #unregister(SatzTyp)} ersetzt
     */
    @Deprecated
    public static void unregister(final int satzart, final int sparte, final int wagnisart) {
        SatzTyp key = new SatzTyp(satzart, sparte, wagnisart, -1);
        REGISTERED_DATENSATZ_CLASSES.remove(key);
        REGISTERED_ENUM_CLASSES.remove(key);
    }

    /**
     * Liefert einen Satz mit der angegebenen Satzart.
     * <p>
     * Das klappt nur, wenn satzart= 0001, 0052, 0100, 0102, 0200, 0202, 0300,
     * 0342, 0350, 0352, 0372, 0382, 0390, 0392, 0400, 0410, 0420, 0430, 0450,
     * 0500, 0550, 0600, 9950, 9951, 9952, 9999 !!
     * Deswegen wurde diese Methode durch {@link #getSatz(SatzTyp)} ersetzt
     * </p>
     *
     * @param satzart Satzart
     * @return angeforderte Satz
     * @since 0.2
     * @deprecated durch {@link #getSatz(SatzTyp)} abgeloest
     */
    @Deprecated
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

    private static Satz getSatzFromXmlService(SatzTyp satztyp) {
        try {
            return XML_SERVICE.getSatzart(satztyp);
        } catch (NotRegisteredException ex) {
            LOG.info("{} is not avalaible via XmlService.", satztyp);
            LOG.debug("Details:", ex);
            if (satztyp.hasParent()) {
                SatzXml satz = XML_SERVICE.getSatzart(satztyp.getParent());
                satz.setSparte(satztyp.getSparte());
                return satz;
            } else {
                return generateSatz(satztyp);
            }
        }
    }

    private static Satz generateSatz(final SatzTyp satztyp) {
        Class<? extends Enum> enumClass = REGISTERED_ENUM_CLASSES.get(satztyp);
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
        Class<? extends Enum> enumClass = REGISTERED_ENUM_CLASSES.get(satzNr);
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
    /*
     * Prinzipieller Fehler!! Um den korrekten Satzaufbau zu liefern, muss die
     * Version der Satzatz bekannt sein! Diese Info steht immer im Vorsatz des
     * zugehörigen Datenpaketes. Lt. Auskunft vom GDV werden z.T. noch Saetze
     * aus Release 01.11.2009 verarbeitet.
     */
        int satzart = Integer.parseInt(content.substring(0, 4));
        StringBuilder satzartBuf = new StringBuilder();
        satzartBuf.append(String.format("%04d", satzart));
        Satz satz;
        try {
      /*
       * Das klappt nur, wenn satzart= 0001, 0052, 0100, 0102, 0200, 0202, 0300,
       * 0342, 0350, 0352, 0372, 0382, 0390, 0392, 0400, 0410, 0420, 0430, 0450,
       * 0500, 0550, 0600, 9950, 9951, 9952, 9999 !!
       * 
       * Alle anderen Satzarten benoetigen weitere Angaben (Sparte, Wagnisart,
       * Satznummer (Sparte 010, 040, 190), BausparetArt)
       */
            // TODO: Umstellung auf Satzart-spezifische Implementierung (29-Nov-2020)
            satz = getSatz(SatzTyp.of(satzartBuf.toString()));
        } catch (RuntimeException e) {
            LOG.debug("can't get Satz " + satzart + " (" + e + "), parsing Sparte...");
            int sparte = Integer.parseInt(content.substring(10, 13));
            satzartBuf.append(String.format(".%03d", sparte));

      /*
       * Das klappt nicht, wenn satzart= 0220.580.01, 0220.580.2, 0220.020.1,
       * 0220.020.2, 0220.020.3, 0220.010.13.1, 0220.010.13.6, 0220.010.13.7,
       * 0220.010.13.8, 0220.010.13.9, 0220.010.2.1, 0220.010.2.6, 0220.010.2.7,
       * 0220.010.2.8, 0220.010.2.9, 0220.010.48.1, 0220.010.48.6,
       * 0220.010.48.8, 0220.010.48.9, 0220.010.5.1, 0220.010.5.6, 0220.010.5.8,
       * 0220.010.5.9, 0220.010.6.1, 0220.010.6.6, 0220.010.6.8, 0220.010.6.9,
       * 0220.010.7.1, 0220.010.7.6, 0220.010.7.8, 0220.010.7.9, 0220.010.9.1,
       * 0220.010.9.6, 0220.010.9.7, 0220.010.9.8, 0220.010.9.9 !!
       *
       * Diese Satzarten benoetigen weitere Angaben (Wagnisart (Sparte 010),
       * Satznummer (Sparte 010), KrankenfolgeNummer (Sparte 020), BausparenArt
       * (Sparte 580))
       */

            satz = getDatensatz(SatzTyp.of(satzartBuf.toString()));
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
     * @deprecated durch {@link #getDatensatz(SatzTyp)} abgeloest
     */
    @Deprecated
    public static Datensatz getDatensatz(final int satzart) {
        return (Datensatz) getSatz(satzart);
    }

    /**
     * Gets the datensatz.
     *
     * @param satzart z.B. 210
     * @param sparte z.B. 70 (Rechtsschutz)
     * @return den registrierten Datensatz fuer 'satzart', 'sparte'
     * @deprecated durch {@link #getDatensatz(SatzTyp)} abgeloest
     */
    @Deprecated
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
     * @deprecated durch {@link #getDatensatz(SatzTyp)} abgeloest
     */
    @Deprecated
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
     * <p>
     * Falls der gewuenschte Datensatz nicht registriert ist, wird der Datensatz
     * anhand der von {@link XmlService} bestimmt.
     * </p>
     *
     * @param satzNr z.B. SatzTyp.of("0210.070.1.6")
     * @return den passenden Datensatz
     */
    public static Datensatz getDatensatz(final SatzTyp satzNr) {
        Class<? extends Datensatz> clazz = REGISTERED_DATENSATZ_CLASSES.get(satzNr);
        if (clazz == null) {
            // wird u.a. fuer den Import von Datensaetzen benoetigt
            try {
                return XML_SERVICE.getSatzart(satzNr);
            } catch (NotRegisteredException ex) {
                LOG.info("SatzTyp {} is not part of the XML description.", satzNr);
                LOG.debug("Details:", ex);
            }
        }
        return generateDatensatz(satzNr, clazz);
    }

    private static Datensatz generateDatensatz(SatzTyp satzNr, Class<? extends Datensatz> clazz) {
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
     * zurueckgeben. Falls dieser nicht existiert, wird ein (allgemeiner)
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
                    LOG.warn("XML-Fallback has " + satzNr + " not registered: " + ex);
                }
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
     * </p>
     * Grund: Ein Objekt vom Typ <b><code><</code>Satz<code>></code></b> kann
     * nicht auf <b><code><</code>Datensatz<code>></code></b> gecastet werden.
     * </p>
     *
     * @return Datenpaket mit allen unterstuetzten Satzarten
     * @since 0.6
     */
    public static Datenpaket getAllSupportedSaetze() {
        Datenpaket all = new Datenpaket();

        Iterator<Map.Entry<SatzTyp, SatzXml>> itXml = XML_SERVICE.getSatzarten()
                                                                 .entrySet()
                                                                 .iterator();

        Iterator<Map.Entry<SatzTyp, Class<? extends Enum>>> itEnum =
            REGISTERED_ENUM_CLASSES.entrySet()
              .iterator();

        while (itXml.hasNext())
        {
          Map.Entry<SatzTyp, SatzXml> entry = itXml.next();
          all.add(entry.getValue());
        }

        while (itEnum.hasNext())
        {
          Map.Entry<SatzTyp, Class<? extends Enum>> entry = itEnum.next();
          all.add(new SatzX(entry.getKey(), entry.getValue()));
        }
        return all;
    }

}
