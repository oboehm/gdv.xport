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

package gdv.xport.satz;

import java.io.*;

import gdv.xport.feld.*;

/**
 * Ein Teildatensatz hat immer genau 256 Bytes.
 * Fuer einen ersten Prototyp wird dabei ein StringBuffer fuer die Ablage
 * benutzt, was aus Performance-Gruenden sicherlich nicht die beste Wahl
 * darstellt. Aber das kann ja spaeter immer noch geaendert werden...
 * 
 * @author oliver
 * @since 04.10.2009
 * @version $Revision$
 */
public class Teildatensatz extends Satz {
	
	protected final char satznummer;
	private StringBuffer data = new StringBuffer(256);
	
	public Teildatensatz(NumFeld satzart) {
		super(satzart);
		this.satznummer = ' ';
		this.initData();
	}
	
	public Teildatensatz(NumFeld satzart, int nr) {
		super(satzart);
		if ((nr < 1) || (nr > 9)) {
			throw new IllegalArgumentException("Satznummer (" + nr
					+ ") muss zwischen 1 und 9 liegen");
		}
		this.satznummer = Character.forDigit(nr, 10);
		this.initData();
	}
	
	/**
	 * Die Satznummer ist einstellig und liegt immer zwischen 1 und 9.
	 * @return Satznummer
	 */
	public final char getSatznummer() {
		return this.satznummer;
	}
	
	private void initData() {
		for (int i = 0; i < 255; i++) {
			this.data.append(' ');
		}
		this.data.append(this.getSatznummer());
		assert (this.data.length() == 256) : "wrong record length " + data.length();
		this.setData(this.satzart);
	}
	
	public void setData(Feld feld) {
		int start = feld.getByteAdresse() - 1;
		int end = start + feld.getAnzahlBytes();
		data.replace(start, end, feld.toString());
	}

	/* (non-Javadoc)
	 * @see gdv.xport.satz.Datensatz#export(java.io.Writer)
	 */
	@Override
	public void export(Writer writer) throws IOException {
		writer.write(this.data.toString());
	}

}


/*
 * $Log$
 * $Source$
 */
