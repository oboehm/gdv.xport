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
 * (c)reated 30.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Nachsatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Vorsatz;
import gdv.xport.satz.xml.XmlService;

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

    private static final SatzRegistry FACTORY = SatzRegistry.getInstance();

    private SatzFactory() {
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
     *
     * @param clazz the clazz
     * @param satzNr the satz nr
     * @since 5.0
     */
    public static void register(final Class<? extends Datensatz> clazz, final SatzTyp satzNr) {
        FACTORY.register(clazz, satzNr);
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
     * @deprecated durch {@link SatzRegistry#getSatz(SatzTyp)} abgeloest (TODO: wird mit v8 entfernt)
     */
    @Deprecated
    public static Datensatz getDatensatz(final SatzTyp satzNr) {
        return (Datensatz) FACTORY.getSatz(satzNr);
    }

    /**
     * Liefert ein Datenpaket mit allen unterstuetzten Satzarten.
     * <p>
     * Satzarten, die mit <b>{@link #register(Class, int)}</b> registriert wurden,
     * werden nicht aufgefuehrt!
     * </p><p>
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
