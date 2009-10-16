/**
 * 
 */
package gdv.xport.satz;

import gdv.xport.feld.*;

import java.io.*;

import org.apache.commons.logging.*;

/**
 * @author oliver
 *
 */
public class Satz {
	
	private static final Log log = LogFactory.getLog(Satz.class);
	/** 4 Zeichen */
	protected final NumFeld satzart = new NumFeld(4, 1);
	/** Teildatensaetze */
	protected Teildatensatz[] teildatensatz;
	
	public Satz(String art) {
		this.satzart.setInhalt(art);
	}
	
	public Satz(NumFeld art) {
		this(art.getInhalt());
	}
	
	public Satz(NumFeld art, int n) {
		this(art);
		this.createTeildatensaetze(n);
	}
	
	public Satz(String art, int n) {
		this.satzart.setInhalt(art);
		this.createTeildatensaetze(n);
	}
	
	private void createTeildatensaetze(int n) {
		teildatensatz = new Teildatensatz[n];
		for (int i = 0; i < n; i++) {
			teildatensatz[i] = new Teildatensatz(satzart, i+1);
		}
	}
	
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
			Feld x = teildatensatz[i].get(name);
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
	 * @param name gesuchte Feld
	 * @return NULL_FELD, falls das angegebene Feld nicht gefunden wird
	 */
	public Feld get(String name) {
		for (int i = 0; i < teildatensatz.length; i++) {
			Feld x = teildatensatz[i].get(name);
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
		return "Satzart " + this.satzart + " ("
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
