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

import static gdv.xport.feld.Bezeichner.*;

import java.io.*;
import java.util.*;

import net.sf.oval.*;

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
	private final Zeichen satznummer = new Zeichen(SATZNUMMER, 256);
	
	public Teildatensatz(NumFeld satzart) {
		super(satzart, 0);
		this.satznummer.setInhalt(' ');
		this.initDatenfelder();
	}
	
	public Teildatensatz(NumFeld satzart, int nr) {
		super(satzart, 0);
		if ((nr < 1) || (nr > 9)) {
			throw new IllegalArgumentException("Satznummer (" + nr
					+ ") muss zwischen 1 und 9 liegen");
		}
		this.satznummer.setInhalt(Character.forDigit(nr, 10));
		this.initDatenfelder();
	}
	
	protected void createTeildatensaetze(int n) {
		assert n == 0 : "ein Teildatensatz hat keine weiteren Teildatensaetze";
		this.teildatensatz = null;
	}
	
	private void initDatenfelder() {
		datenfelder.put("Satzart", this.satzart);
		datenfelder.put("Satznummer", this.satznummer);
	}
	
	/**
	 * @param feld Feld mit Name
	 */
	public void add(Feld feld) {
		for (Iterator<Feld> iterator = datenfelder.values().iterator(); iterator.hasNext();) {
			Feld f = iterator.next();
			if (feld.overlapsWith(f)) {
				throw new IllegalArgumentException("conflict: " + feld + " overlaps with " + f);
			}
		}
		String name = feld.getBezeichnung();
		datenfelder.put(name, feld);
	}
	
	public void set(String name, Feld feld) {
		datenfelder.put(name, feld);
	}
	
	public Feld getFeld(String name) {
		Feld found = datenfelder.get(name);
		if (found == null) {
			return Feld.NULL_FELD;
		} else {
			return found;
		}
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
			data.replace(start, end, feld.getInhalt());
		}
	    assert data.length() == 256 : "Teildatensatz ist nicht 256 Bytes lang";
		writer.write(data.toString());
	}

	/* (non-Javadoc)
     * @see gdv.xport.satz.Satz#importFrom(java.lang.String)
     */
    @Override
    public void importFrom(String content) throws IOException {
	    for (Feld feld : datenfelder.values()) {
	        int begin = (feld.getByteAdresse() - 1) % 256;
	        int end = begin + feld.getAnzahlBytes();
	        if (end > content.length()) {
				throw new IOException("input string is too short (" + (end - content.length())
				        + " bytes missing): " + content);
	        }
	        String s = content.substring(begin, end);
	    	feld.setInhalt(s);
        }
    }

	/* (non-Javadoc)
     * @see gdv.xport.satz.Satz#isValid()
     */
    @Override
    public boolean isValid() {
	    if (!super.isValid()) {
	    	return false;
	    }
	    for (Feld feld : datenfelder.values()) {
	        if (!feld.isValid()) {
	        	return false;
	        }
        }
	    return true;
    }

	/* (non-Javadoc)
     * @see gdv.xport.satz.Satz#validate()
     */
    @Override
    public List<ConstraintViolation> validate() {
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(this);
	    for (Feld feld : datenfelder.values()) {
	        violations.addAll(feld.validate());
        }
	    return violations;
    }

	/* (non-Javadoc)
     * @see gdv.xport.satz.Satz#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
	    try {
	    	return this.equals((Teildatensatz) other);
	    } catch (ClassCastException cce) {
	    	return false;
	    }
    }
   
    public boolean equals(Teildatensatz other) {
    	if (this.datenfelder.size() != other.datenfelder.size()) {
    		return false;
    	}
	    for (Feld feld : datenfelder.values()) {
	        if (!feld.equals(other.getFeld(feld.getBezeichnung()))) {
	        	return false;
	        }
        }
	    return true;
    }

	/* (non-Javadoc)
     * @see gdv.xport.satz.Satz#hashCode()
     */
    @Override
    public int hashCode() {
	    return this.getSatzart() + this.satznummer.getInhalt().hashCode();
    }

}


/*
 * $Log$
 * $Source$
 */
