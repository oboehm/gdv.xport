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
	
	protected final StringBuffer inhalt;
	/** Achtung - die ByteAdresse beginnt bei 1 und geht bis 256 */
	protected final int byteAdresse;
	
	public Feld(String s) {
		this(s, 1);
	}
	
	public Feld(String s, int start) {
		this.inhalt = new StringBuffer(s);
		this.byteAdresse = start;		
	}
	
	public Feld(int length) {
		this(length, 1);
	}
	
	public Feld(int length, int start) {
		this.inhalt = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			this.inhalt.append(' ');
		}
		this.byteAdresse = start;
	}
	
	public void setInhalt(String s) {
		int anzahlBytes = this.getAnzahlBytes();
		if (s.length() > anzahlBytes) {
			throw new IllegalArgumentException("Parameter (\"" + s
					+ "\" ist laenger als " + anzahlBytes + " Zeichen!");
		}
		this.inhalt.replace(0, s.length(), s);
		for (int i = s.length() + 1; i < anzahlBytes; i++) {
			this.inhalt.replace(i, i, " ");
		}
	}
	
	public String getInhalt() {
		return this.inhalt.toString();
	}
	
	public void resetInhalt() {
		int anzahlBytes = this.getAnzahlBytes();
		for (int i = 0; i < anzahlBytes; i++) {
			this.inhalt.replace(i, i+1, " ");
		}
	}
	
	public int getAnzahlBytes() {
		return this.inhalt.length();
	}
	
	public int getByteAdresse() {
		return this.byteAdresse;
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
