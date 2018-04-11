/*
 * Copyright (c) 2011-2013 by Oli B.
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

import gdv.xport.io.PushbackLineNumberReader;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.feld.FeldX;
import gdv.xport.satz.feld.common.TeildatensatzNummer;
import gdv.xport.satz.feld.common.WagnisartLeben;
import gdv.xport.util.SatzTyp;

import java.io.IOException;
import java.util.List;

/**
 * Dies ist die gemeinsame Oberklasse aller Saetze in diesem Package, die nach
 * dem SOP-Muster aufgebaut sind. Eventuell wird diese Klasse mit der Oberklasse
 * vereinigt.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (09.03.2011)
 */
public class SatzX extends Datensatz {

	/**
	 * Instantiiert einen neuen Datensatz.
	 *
	 * @param satzart z.B. 100
	 * @param felder mit allen Elementen des Datensatzes
	 */
	public SatzX(final int satzart, final Enum<?>[] felder) {
		super(satzart, getTeildatensaetzeFor(satzart, felder));
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 *
	 * @param satzart z.B. 100
	 * @param enumClass Enumerationen-Klasse mit den Feldbeschreibungen
	 */
	public SatzX(final int satzart, final Class<? extends Enum<?>> enumClass) {
		super(satzart, getTeildatensaetzeFor(satzart, enumClass));
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 *
	 * @param satzart z.B. 100
	 * @param sparte Sparte
	 * @param felder mit allen Elementen des Datensatzes
	 */
	public SatzX(final int satzart, final int sparte, final Enum<?>[] felder) {
		super(satzart, sparte, getTeildatensaetzeFor(satzart, felder));
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 *
	 * @param satzart z.B. 100
	 * @param sparte Sparte
	 * @param enumClass Enumerationen-Klasse mit den Feldbeschreibungen
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
	public SatzX(final SatzTyp satzNr, final Class<? extends Enum<?>> enumClass) {
		super(satzNr, getTeildatensaetzeFor(satzNr.getSatzart(), enumClass));
	}

	private static List<Teildatensatz> getTeildatensaetzeFor(final int satzart,
	        final Class<? extends Enum<?>> enumClass) {
		Enum<?>[] constants = enumClass.getEnumConstants();
		return getTeildatensaetzeFor(satzart, constants);
	}

	/**
	 * Setzt die Teildatensaetze mit den angegebenen Feldern auf.
	 *
	 * @param felder Felder fuer die Teildatensaetze.
	 */
	protected void setUpTeildatensaetze(final Enum<?>[] felder) {
		super.createTeildatensaetze(getTeildatensaetzeFor(this.getSatzart(), felder));
		super.completeTeildatensaetze();
	}

	/**
	 * Unterklassen (wie Datensatz) sind dafuer verantwortlich, dass auch noch
	 * die Wagnisart und die TeildatensatzNummer ueberprueft wird, ob sie noch
	 * richtig ist oder ob da schon der naechste Satz beginnt.
	 *
	 * @param reader the reader
	 * @param lastFeld1To7 Feld1..7 als Char-Array (42 Zeichen) der letzten Zeile oder {@code null} für ersten Teildatensatz
	 * @return true (Default-Implementierung)
	 * @throws IOException bei I/O-Fehlern
	 * @since 0.9
	 * @see gdv.xport.satz.Satz#matchesNextTeildatensatz(PushbackLineNumberReader, char[])
	 */
	@Override
	protected boolean matchesNextTeildatensatz(final PushbackLineNumberReader reader, char[] lastFeld1To7, Character lastFeld256) throws IOException {
		if (super.matchesNextTeildatensatz(reader, lastFeld1To7, lastFeld256)) {
			WagnisartLeben nextLineWagnisEnum = readWagnisart(reader);
			TeildatensatzNummer nextLineTeildatensatzNummerEnum = readTeildatensatzNummer(reader);

			boolean nextLineWagnisartIsSet = nextLineWagnisEnum != WagnisartLeben.NULL;
            boolean nextLineTeildatensatzNummerIsSet =
                    nextLineTeildatensatzNummerEnum != TeildatensatzNummer.NULL;
            boolean currentLineWagnisartIsSet = this.hasWagnisart() && this.getWagnisart().trim().length() > 0;
			boolean currentLineTeildatensatzNummerIsSet = this.getTeildatensatzNummer().trim()
			        .length() > 0;

			WagnisartLeben currentLineWagnisEnum = WagnisartLeben.NULL;
			if (currentLineWagnisartIsSet) {
				currentLineWagnisEnum = WagnisartLeben.isIn(Integer.parseInt(this.getWagnisart()));
			}

			TeildatensatzNummer currentLineTeildatensatzEnum = TeildatensatzNummer.NULL;
			if (currentLineTeildatensatzNummerIsSet) {
				currentLineTeildatensatzEnum = TeildatensatzNummer.isIn(Integer.parseInt(this
				        .getTeildatensatzNummer()));
			}

			if (!currentLineWagnisartIsSet) {
				return true;
			}

            if ((nextLineWagnisartIsSet)
                    && (nextLineWagnisEnum == currentLineWagnisEnum)) {
                if (currentLineTeildatensatzNummerIsSet || nextLineTeildatensatzNummerIsSet) {
                    if (nextLineTeildatensatzNummerEnum == currentLineTeildatensatzEnum) {
                        return true;
                    }
                } else {
                    // wagnisarten sind gleich und die
                    // Teildatensatznummer sind beide nicht gesetzt
                    return true;
                }
            }
		}
		return false;
	}
}
