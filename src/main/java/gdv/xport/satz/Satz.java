/**
 * 
 */
package gdv.xport.satz;

import gdv.xport.feld.NumFeld;

import java.io.*;

/**
 * @author oliver
 *
 */
public class Satz {
	
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
	
	protected void createTeildatensaetze(int n) {
		teildatensatz = new Teildatensatz[n];
		for (int i = 0; i < n; i++) {
			teildatensatz[i] = new Teildatensatz(satzart, i+1);
		}
	}
	
	public int getSatzart() {
		return satzart.toInt();
	}
	
	public void export(Writer writer) throws IOException {
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].export(writer);
		}
	}

}
