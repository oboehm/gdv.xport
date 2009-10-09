/**
 * 
 */
package gdv.xport.satz;

import java.io.*;

import gdv.xport.feld.*;

/**
 * @author oliver
 *
 */
public class Datensatz {
	
	/** 4 Zeichen */
	protected final Nummer satzart = new Nummer(4, 1);
	/** 5 Zeichen, linksbuendig */
	protected Zeichen vuNummer = new Zeichen(System.getProperty("gdv.xport.vuNummer", "k.A. "), 5);
	/** Teildatensaetze */
	protected Teildatensatz[] teildatensatz;
	
	public Datensatz(String art) {
		this.satzart.setInhalt(art);
	}
	
	public Datensatz(Nummer art) {
		this(art.getInhalt());
	}
	
	protected void createTeildatensaetze(int n) {
		teildatensatz = new Teildatensatz[n];
		for (int i = 0; i < n; i++) {
			teildatensatz[i] = new Teildatensatz(satzart, i+1);
			teildatensatz[i].setData(vuNummer);
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
