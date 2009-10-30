/**
 * 
 */
package gdv.xport.satz;

import static patterntesting.runtime.NullConstants.*;
import static gdv.xport.feld.Bezeichner.*;

import gdv.xport.feld.*;

import java.io.*;

import org.apache.commons.logging.*;

/**
 * @author oliver
 */
public class Satz {
	
	private static final Log log = LogFactory.getLog(Satz.class);
	/** 4 Zeichen */
	protected final NumFeld satzart = new NumFeld(SATZART, 4, 1);
	/** Teildatensaetze */
	protected Teildatensatz[] teildatensatz;
	
	protected Satz(int art) {
		this(art, 1);
	}
	
	protected Satz(String art) {
		this(art, 1);
	}
	
	protected Satz(NumFeld art) {
		this(art.getInhalt());
	}
	
	public Satz(NumFeld art, int n) {
		this.satzart.setInhalt(art.getInhalt());
		this.createTeildatensaetze(n);
	}
	
	public Satz(String art, int n) {
		this.satzart.setInhalt(art);
		this.createTeildatensaetze(n);
	}
	
	/**
	 * @param art z.B. 100 (f. Adressteil)
	 * @param n Anzahl der Teildatensaetze
	 */
	public Satz(int art, int n) {
		this.satzart.setInhalt(art);
		this.createTeildatensaetze(n);
	}
	
	protected void createTeildatensaetze(int n) {
		teildatensatz = new Teildatensatz[n];
		for (int i = 0; i < n; i++) {
			teildatensatz[i] = new Teildatensatz(satzart, i+1);
		}
	}
	
	/**
	 * Fuegt das uebergebene Feld zur Liste der Datenfelder hinzu
	 * TODO: Konflikterkennung bei ueberlappenden Feldern (-> Exception)
	 * @param feld
	 */
	public void add(Feld feld) {
		int n = feld.getTeildatensatzNr();
		this.teildatensatz[n-1].add(feld);
	}
	
	/**
	 * Setzt das angegebene Feld in allen Teildatensaetzen, in denen es
	 * gefunden wird. Normalerweise braeuchten wir eigentlich nur die erste
	 * Fundstelle setzen, da die anderen Teildatensaetze (hoffentlich) auf
	 * die gleiche Referenz verweisen - aber sicher ist sicher.
	 * Falls das Feld nicht gefunden wird, wird eine IllegalArgumentException
	 * geworfen.
	 * 
	 * @param name Name des Felds (Bezeichnung)
	 * @param value
	 */
	public void set(String name, String value) {
		boolean found = false;
		for (int i = 0; i < teildatensatz.length; i++) {
			Feld x = teildatensatz[i].getFeld(name);
			if (x != Feld.NULL_FELD) {
				x.setInhalt(value);
				found = true;
			}
		}
		if (!found) {
			throw new IllegalArgumentException("Feld \"" + name +"\" not found");
		}
	}
	
	/**
	 * @param name gesuchtes Feld
	 * @return Inhalt des gefundenden Felds
	 *         (NULL_STRING, falls 'name' nicht gefunden wurde)
	 */
	public String get(String name) {
		Feld f = getFeld(name);
		if (f == Feld.NULL_FELD) {
			return NULL_STRING;
		} else {
			return f.getInhalt();
		}
	}
	
	/**
	 * @param name gesuchtes Feld
	 * @return NULL_FELD, falls das angegebene Feld nicht gefunden wird
	 */
	public Feld getFeld(String name) {
		for (int i = 0; i < teildatensatz.length; i++) {
			Feld x = teildatensatz[i].getFeld(name);
			if (x != Feld.NULL_FELD) {
				return x;
			}
		}
		return Feld.NULL_FELD;
	}
	
	public int getSatzart() {
		return satzart.toInt();
	}
	
	public void export(Writer writer) throws IOException {
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].export(writer);
		}
	}
	
	public boolean isValid() {
		if (!this.satzart.isValid()) {
			log.info(this + " is invalid: invalid Satzart " + this.satzart);
			return false;
		}
		if (this.teildatensatz != null) {
			for (int i = 0; i < teildatensatz.length; i++) {
		        if (!teildatensatz[i].isValid()) {
		        	log.info("Teildatensatz " + (i+1) + " is invalid");
		        	return false;
		        }
	        }
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			return this.toShortString();
		} catch (RuntimeException shouldNeverHappen) {
			log.error("shit happens in toString()", shouldNeverHappen);
			return super.toString();
		}
	}
	
	public String toShortString() {
		return "Satzart " + this.satzart.getInhalt() + " ("
				+ this.toLongString().substring(0, 60) + "...)";
	}
	
	public String toLongString() {
		StringWriter swriter = new StringWriter();
		try {
			this.export(swriter);
		} catch (IOException canthappen) {
			log.warn(canthappen + " ignored", canthappen);
			swriter.write(canthappen.getLocalizedMessage());
		}
		return swriter.toString();
	}

}
