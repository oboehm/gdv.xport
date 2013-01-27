/*
 * Copyright (c) 2011, 2012 by Oli B.
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
 * (c)reated 09.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.feld.FeldX;
import gdv.xport.util.SatzNummer;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Dies ist die gemeinsame Oberklasse aller Saetze in diesem Package, die nach dem SOP-Muster aufgebaut sind. Eventuell
 * wird diese Klasse mit der Oberklasse vereinigt.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (09.03.2011)
 */
public class SatzX extends Datensatz {

    private static Log log = LogFactory.getLog(SatzX.class);

    /**
     * Instantiiert einen neuen Datensatz.
     *
     * @param satzart
     *            z.B. 100
     * @param felder
     *            mit allen Elementen des Datensatzes
     */
    public SatzX(final int satzart, final Enum<?>[] felder) {
        super(satzart, getTeildatensaetzeFor(satzart, felder));
    }

    /**
     * Instantiiert einen neuen Datensatz.
     *
     * @param satzart
     *            z.B. 100
     * @param enumClass
     *            Enumerationen-Klasse mit den Feldbeschreibungen
     */
    public SatzX(final int satzart, final Class<? extends Enum<?>> enumClass) {
        super(satzart, getTeildatensaetzeFor(satzart, enumClass));
    }

    /**
     * Instantiiert einen neuen Datensatz.
     *
     * @param satzart
     *            z.B. 100
     * @param sparte
     *            Sparte
     * @param felder
     *            mit allen Elementen des Datensatzes
     */
    public SatzX(final int satzart, final int sparte, final Enum<?>[] felder) {
        super(satzart, sparte, getTeildatensaetzeFor(satzart, felder));
    }

    /**
     * Instantiiert einen neuen Datensatz.
     *
     * @param satzart
     *            z.B. 100
     * @param sparte
     *            Sparte
     * @param enumClass
     *            Enumerationen-Klasse mit den Feldbeschreibungen
     */
    public SatzX(final int satzart, final int sparte, final Class<? extends Enum<?>> enumClass) {
        super(satzart, sparte, getTeildatensaetzeFor(satzart, enumClass));
    }

	/**
	 * Instantiiert einen allgemeinen Datensatz fuer die angegebene Satzart und
	 * Sparte. Dieser Konstruktor ist hauptsaechlich als Fallback fuer
	 * Satzarten/Sparten gedacht, die noch nicht unterstuetzt werden.
	 *
	 * @param satzart z.B. 100
	 * @param sparte Sparte
	 */
    public SatzX(final int satzart, final int sparte) {
        this(satzart, sparte, FeldX.values());
    }

	/**
	 * Instantiiert einen allgemeinen Datensatz fuer die angegebene Satznummer.
	 *
	 * @param satzNr Satznummer mit Satzart, Sparte, Wagnisart, lfd. Nummer
	 * @param enumClass Enum-Klasse, die den Datensatz beschreibt
	 * @since 0.9
	 */
    public SatzX(final SatzNummer satzNr, final Class<? extends Enum<?>> enumClass) {
        super(satzNr, getTeildatensaetzeFor(satzNr.getSatzart(), enumClass));
    }

    private static List<Teildatensatz> getTeildatensaetzeFor(final int satzart, final Class<? extends Enum<?>> enumClass) {
        Enum<?>[] constants = enumClass.getEnumConstants();
        return getTeildatensaetzeFor(satzart, constants);
    }

    /**
     * Setzt die Teildatensaetze mit den angegebenen Feldern auf.
     *
     * @param felder
     *            Felder fuer die Teildatensaetze.
     */
    protected void setUpTeildatensaetze(final Enum<?>[] felder) {
        super.createTeildatensaetze(getTeildatensaetzeFor(this.getSatzart(), felder));
        super.completeTeildatensaetze();
    }

}
