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
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Nachsatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Vorsatz;
import gdv.xport.satz.xml.XmlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Diese Klasse dient dazu, um einen vorgegebene Satz, der z.B. aus einem Import
 * kommt, in den entsprechenden Satz wandeln zu koennen.
 * <p>
 * Mit 5.0 wurde die Funktionalitaet in {@link SatzRegistry} ausgelagert. Im
 * Gegensatz zu SatzFactory kann man sich von der {@link SatzRegistry} auch
 * verschiedene Instanzen geben lassen - z.B. zur Unterstuetzung von aelteren
 * Versionen der GDV-Satzbeschreibungen.
 * </p>
 *
 * @author oliver (ob@aosd.de)
 * @since 0.1.0 (30.10.2009)
 */
public final class SatzFactory {

    private static final Logger LOG = LogManager.getLogger(SatzFactory.class);
    private static final SatzRegistry FACTORY = SatzRegistry.getInstance();

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
        FACTORY.reset();
        LOG.debug("Satzfactory wurde zurueckgesetzt.");
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden. Die Kasse <em>muss</em>
     * einen Default-Konstruktor bereitstellen. Ansonsten wird hier eine
     * {@link IllegalArgumentException} geworfen (seit 0.6).
     *
     * @param clazz   the clazz
     * @param satzart the satzart
     * @since 0.2
     */
    public static void register(final Class<? extends Satz> clazz, final int satzart) {
        FACTORY.register(clazz, satzart, SatzRegistry.NO_VALIDATOR);
    }

    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der
     * Datensatz-Beschreibung uebergeben wird.
     * <p>
     * TODO: Wird mit v6 nicht mehr zur Verfuegung stehen.
     * </p>
     *
     * @param enumClass die Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzart die Satzart (1-9999)
     * @since 0.6
     * @deprecated bitte nur noch {@link #registerEnum(Class, SatzTyp)} verwenden
     */
    @Deprecated
    public static void registerEnum(final Class<? extends Enum> enumClass, final int satzart) {
        registerEnum(enumClass, SatzTyp.of(satzart));
    }

    /**
     * Mit dieser Registrierung reicht es, wenn nur ein Aufzaehlungstyp mit der
     * Datensatz-Beschreibung uebergeben wird.
     * <p>
     * TODO: Wird mit v6 nicht mehr zur Verfuegung stehen.
     * </p>
     *
     * @param enumClass die Aufzaehlungsklasse, z.B. Feld100.class
     * @param satzart die Satzart (1-9999)
     * @param sparte die Sparte (0-999)
     * @since 0.6
     * @deprecated bitte nur noch {@link #registerEnum(Class, SatzTyp)} verwenden
     */
    @Deprecated
    public static void registerEnum(final Class<? extends Enum> enumClass, final int satzart, final int sparte) {
        registerEnum(enumClass, SatzTyp.of(satzart, sparte));
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
        FACTORY.registerEnum(enumClass, satzNr);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann)
     * <p>
     * TODO: Wird mit v6 nicht mehr zur Verfuegung stehen.
     * </p>
     *
     * @param satzart the satzart
     * @since 0.2
     * @deprecated in v5.0 durch {@link #unregister(SatzTyp)} ersetzt
     */
    @Deprecated
    public static void unregister(final int satzart) {
        SatzTyp key = SatzTyp.of(satzart);
        unregister(key);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann). Diese unregister-Methode ersetzt ab 4.2 die
     * anderen uregister-Methoden.
     *
     * @param typ SatzTyp bzw. Satzart
     * @since 5.0
     */
    public static void unregister(SatzTyp typ) {
        FACTORY.unregister(typ);
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden.
     * <p>
     * TODO: Wird mit v6 nicht mehr zur Verfuegung stehen.
     * </p>
     *
     * @param clazz the clazz
     * @param satzart the satzart
     * @param sparte the sparte
     * @since 0.2
     * @deprecated in v5 durch {@link #register(Class, SatzTyp)} ersetzt
     */
    @Deprecated
    public static void register(final Class<? extends Datensatz> clazz, final int satzart, final int sparte) {
        register(clazz, SatzTyp.of(satzart, sparte));
    }

    /**
     * Mit dieser Methode koennen eigene Klassen fuer (z.B. noch nicht
     * unterstuetzte Datensaetze) registriert werden.
     *
     * @param clazz the clazz
     * @param satzNr the satz nr
     * @since 5.0
     */
    public static void register(final Class<? extends Datensatz> clazz, final SatzTyp satzNr) {
        FACTORY.register(clazz, satzNr);
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann)
     * <p>
     * TODO: Wird mit v6 nicht mehr zur Verfuegung stehen.
     * </p>
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @since 0.2
     * @deprecated in v5.0 durch {@link #unregister(SatzTyp)} ersetzt
     */
    @Deprecated
    public static void unregister(final int satzart, final int sparte) {
        unregister(SatzTyp.of(satzart, sparte));
    }

    /**
     * Hiermit kann man eine Registrierung rueckgaengig machen (was z.B. fuer's
     * Testen hilfreich sein kann)
     * <p>
     * TODO: Wird mit v6 nicht mehr zur Verfuegung stehen.
     * </p>
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @param wagnisart the wagnisart
     * @since 0.8
     * @deprecated in v5.0 durch {@link #unregister(SatzTyp)} ersetzt
     */
    @Deprecated
    public static void unregister(final int satzart, final int sparte, final int wagnisart) {
        SatzTyp key = SatzTyp.of(satzart, sparte, wagnisart);
        unregister(key);
    }

    /**
     * Liefert einen Satz mit der angegebenen Satzart.
     * <p>
     * Das klappt nur, wenn satzart= 0001, 0052, 0100, 0102, 0200, 0202, 0300,
     * 0342, 0350, 0352, 0372, 0382, 0390, 0392, 0400, 0410, 0420, 0430, 0450,
     * 0500, 0550, 0600, 9950, 9951, 9952, 9999 !!
     * Deswegen wurde diese Methode durch {@link #getSatz(SatzTyp)} ersetzt
     * </p>
     * <p>
     * TODO: Wird mit v6 nicht mehr zur Verfuegung stehen.
     * </p>
     *
     * @param satzart Satzart
     * @return angeforderte Satz
     * @since 0.2
     * @deprecated durch {@link #getSatz(SatzTyp)} abgeloest
     */
    @Deprecated
    public static Satz getSatz(final int satzart) {
        return getSatz(SatzTyp.of(satzart));
    }
    
    /**
     * Holt einen Satz.
     * 
     * @param satztyp der Satztyp
     * @return angeforderter Satz
     * @since 3.2
     */
    public static Satz getSatz(final SatzTyp satztyp) {
        return FACTORY.getSatz(satztyp);
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
     * @since 0.2
     */
    public static Satz getSatz(final String content) {
        return FACTORY.getSatz(content);
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
     * Liefert einen Datensatz.
     * <p>
     * TODO: Wird mit v6 nicht mehr zur Verfuegung stehen.
     * </p>
     *
     * @param satzart z.B. 210
     * @param sparte z.B. 70 (Rechtsschutz)
     * @return den registrierten Datensatz fuer 'satzart', 'sparte'
     * @deprecated durch {@link #getDatensatz(SatzTyp)} abgeloest
     */
    @Deprecated
    public static Datensatz getDatensatz(final int satzart, final int sparte) {
        return getDatensatz(SatzTyp.of(satzart, sparte));
    }

    /**
     * Liefert einen Datensatz.
     * <p>
     * TODO: Wird mit v6 nicht mehr zur Verfuegung stehen.
     * </p>
     *
     * @param satzart z.B. 210
     * @param sparte z.B. 70 (Rechtsschutz)
     * @param wagnisart z.B. 1 (Kapitallebensversicherung)
     * @return den registrierten Datensatz fuer 'satzart', 'sparte', 'wagnisart'
     * @since 0.8
     * @deprecated durch {@link #getDatensatz(SatzTyp)} abgeloest
     */
    @Deprecated
    public static Datensatz getDatensatz(final int satzart, final int sparte, final int wagnisart) {
        return getDatensatz(SatzTyp.of(satzart, sparte, wagnisart));
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
    public static Datensatz getDatensatz(final SatzTyp satzNr) {
        return FACTORY.getDatensatz(satzNr);
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
     * @since 0.6
     */
    public static Datenpaket getAllSupportedSaetze() {
        return FACTORY.getAllSupportedSaetze();
    }

    /**
     * Liefert den passenden Vorsatz.
     *
     * @return Vorsatz
     * @since 5.0
     */
    public static Vorsatz getVorsatz() {
        return FACTORY.getVorsatz();
    }

    /**
     * Liefert den passenden Nachsatz.
     *
     * @return Nachsatz
     * @since 5.0
     */
    public static Nachsatz getNachsatz() {
        return FACTORY.getNachsatz();
    }

  /**
   * Liefert das Release der jeweiligen XML-Beschreibung, aus der die GDV-Datensaetze erzeugt
   * wurden.
   *
   * @return das Release der erzeugten XmlSaetze
   */
  public static String getGdvRelease() {
    return FACTORY.getGdvRelease();
  }

}
