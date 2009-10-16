/*
 * $Id$
 *
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
 * (c)reated 04.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.feld;

import java.io.*;

/**
 * @author oliver
 * @since 04.10.2009
 * @version $Revision$
 *
 */
public class Feld {
	
	/** optional: Name des Felds */
    protected final String bezeichnung;
	protected final StringBuffer inhalt;
	/** Achtung - die ByteAdresse beginnt bei 1 und geht bis 256 */
	protected final int byteAdresse;
	/** Ausrichtung: rechts- oder linksbuendig */
	protected final Align ausrichtung;
	
	@Deprecated
	public Feld(String s, Align alignment) {
		this(1, s, alignment);
	}
	
	public Feld(String name, String s, Align alignment) {
		this(name, 1, s, alignment);
	}
	
	public Feld(int start, String s, Align alignment) {
		this.inhalt = new StringBuffer(s);
		this.byteAdresse = start;	
		this.ausrichtung = alignment;
		this.bezeichnung = createBezeichnung();
	}
	
	public Feld(int length, Align alignment) {
		this(length, 1, alignment);
	}
	
	public Feld(int length, int start, Align alignment) {
		this.inhalt = getEmptyStringBuffer(length);
		this.byteAdresse = start;
		this.ausrichtung = alignment;
		this.bezeichnung = createBezeichnung();
	}
	
	public Feld(String name, int start, String s, Align alignment) {
		this.bezeichnung = name;
		this.inhalt = new StringBuffer(s);
		this.byteAdresse = start;
		this.ausrichtung = alignment;
	}
	
	public Feld(String name, int length, int start, char c, Align alignment) {
		this.bezeichnung = name;
		this.inhalt = getEmptyStringBuffer(length);
		this.byteAdresse = start;
		this.ausrichtung = alignment;
		this.setInhalt(c);
	}

	private static StringBuffer getEmptyStringBuffer(int length) {
		StringBuffer sbuf = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			sbuf.append(' ');
		}
		return sbuf;
	}
	
	private String createBezeichnung() {
		return "Feld@" + this.hashCode();
	}
	
	public String getBezeichnung() {
		return this.bezeichnung;
	}
	
	public void setInhalt(String s) {
		int anzahlBytes = this.getAnzahlBytes();
		if (s.length() > anzahlBytes) {
			throw new IllegalArgumentException("Parameter (\"" + s
					+ "\" ist laenger als " + anzahlBytes + " Zeichen!");
		}
		this.resetInhalt();
		switch (this.ausrichtung) {
		case LEFT:	this.inhalt.replace(0, s.length(), s);
					break;
		case RIGHT: int l = s.length();
					int start = anzahlBytes - l;
					this.inhalt.replace(start, start + l, s);
					break;
		default:	throw new IllegalStateException("object was not properly initialized");
		}
	}
	
	public void setInhalt(char c) {
		this.resetInhalt();
		this.setInhalt(c, 0);
	}
	
	/**
	 * @param c zu setzendes Zeichen
	 * @param i index, beginnend bei 0
	 */
	public void setInhalt(char c, int i) {
		this.inhalt.setCharAt(i, c);
	}
	
	public String getInhalt() {
		return this.inhalt.toString();
	}
	
	public void resetInhalt() {
		int anzahlBytes = this.getAnzahlBytes();
		for (int i = 0; i < anzahlBytes; i++) {
			//this.inhalt.replace(i, i+1, " ");
			this.inhalt.setCharAt(i, ' ');
		}
	}
	
	public int getAnzahlBytes() {
		return this.inhalt.length();
	}
	
	/**
	 * @return Byte-Adresse, beginnend bei 1
	 */
	public int getByteAdresse() {
		return this.byteAdresse;
	}
	
	public int getEndAdresse() {
		return this.byteAdresse + this.getAnzahlBytes() - 1;
	}
	
	/**
	 * Wenn die ByteAdresse > 256 ist, kann hierueber der Teildatensatz
	 * bestimmt werden, in der das Feld liegen muesste.
	 * 
	 * @return Nummer des Teildatensatzes, beginnend bei 1
	 */
	public int getTeildatensatzNr() {
		if (this.byteAdresse > 256) {
			return 1 + (this.byteAdresse - 1) / 256;
		} else {
			return 1;
		}
	}
	
	public void write(Writer writer) throws IOException {
		writer.write(this.inhalt.toString());
	}

	@Override
	public String toString() {
		return this.inhalt.toString();
	}

}


/*
 * $Log$
 * $Source$
 */
