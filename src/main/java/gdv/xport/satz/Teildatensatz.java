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
import java.util.*;

import gdv.xport.feld.*;

/**
 * Ein Teildatensatz hat immer genau 256 Bytes. Dies wird beim Export
 * beruecksichtigt. Und ein Teildatensatz besteht aus mehreren Datenfeldern.
 * 
 * @author oliver.boehm@agentes.de
 * @since 04.10.2009
 */
public class Teildatensatz extends Satz {
	
	private final Map<String, Feld> datenfelder = new HashMap<String, Feld>();
	private final AlphaNumFeld satznummer = new AlphaNumFeld(1, 256);
	
	public Teildatensatz(NumFeld satzart) {
		super(satzart);
		this.satznummer.setInhalt(' ');
		this.initDatenfelder();
	}
	
	public Teildatensatz(NumFeld satzart, int nr) {
		super(satzart);
		if ((nr < 1) || (nr > 9)) {
			throw new IllegalArgumentException("Satznummer (" + nr
					+ ") muss zwischen 1 und 9 liegen");
		}
		this.satznummer.setInhalt(Character.forDigit(nr, 10));
		this.initDatenfelder();
	}
	
	private void initDatenfelder() {
		datenfelder.put("Satzart", this.satzart);
		datenfelder.put("Satznummer", this.satznummer);
	}
	
	/**
	 * Falls ein Datenfeld kein Name hat, benennen wir ihn einfach mit seinem
	 * Hashwert.
	 * 
	 * @param feld
	 */
	public void add(Feld feld) {
		String name = feld.getBezeichnung();
		datenfelder.put(name, feld);
	}
	
	public void setDatenfeld(String name, Feld feld) {
		datenfelder.put(name, feld);
	}

	/* (non-Javadoc)
	 * @see gdv.xport.satz.Datensatz#export(java.io.Writer)
	 */
	@Override
	public void export(Writer writer) throws IOException {
	    StringBuffer data = new StringBuffer(256);
	    for (int i = 0; i < 256; i++) {
			data.append(' ');
		}
	    datenfelder.keySet().iterator();
	    for (String key : datenfelder.keySet()) {
			Feld feld = datenfelder.get(key);
			int start = (feld.getByteAdresse() - 1) % 256;
			int end = start + feld.getAnzahlBytes();
			data.replace(start, end, feld.toString());
		}
	    assert data.length() == 256 : "Teildatensatz ist nicht 256 Bytes lang";
		writer.write(data.toString());
	}

}


/*
 * $Log$
 * $Source$
 */
