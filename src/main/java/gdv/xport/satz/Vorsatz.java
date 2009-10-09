/**
 * 
 */
package gdv.xport.satz;

import gdv.xport.feld.*;


/**
 * @author oliver
 */
public final class Vorsatz extends Datensatz {
	
	/** 30 Zeichen, Byte 10 - 39 */
	private Zeichen absender = new Zeichen(30, 10);
	/** 30 Zeichen, Byte 40 - 69 */
	private Zeichen adressat = new Zeichen(30, 40);
	/** 8 Zeichen, Byte 70 - 77 */
	private Datum von = new Datum(70);
	/** 8 Zeichen, Byte 78 - 85 */
	private Datum bis = new Datum(78);
	/** 10 Zeichen, Byte 86 - 95 */
	private Zeichen vermittler = new Zeichen(10, 86);

	public Vorsatz() {
		super("0001");
		this.createTeildatensaetze(3);
		initTeildatensaetze();
	}
	
	private void initTeildatensaetze() {
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].setData(this.vuNummer);
			teildatensatz[i].setData(this.absender);
			teildatensatz[i].setData(this.adressat);
			teildatensatz[i].setData(this.von);
			teildatensatz[i].setData(this.bis);
			teildatensatz[i].setData(this.vermittler);
		}
	}
	
	/**
	 * Absender ist Byte 10 - 39 im Teildatensatz.
	 * @param absender
	 */
	public void setAbsender(String absender) {
		this.absender.setInhalt(absender);
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].setData(this.absender);			
		}
	}

}
