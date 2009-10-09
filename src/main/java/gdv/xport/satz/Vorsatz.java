/**
 * 
 */
package gdv.xport.satz;

import gdv.xport.config.Config;
import gdv.xport.feld.*;


/**
 * @author oliver
 */
public final class Vorsatz extends Satz {
	
	/** 5 Zeichen, Byte 5 - 9 */
	protected AlphaNumFeld vuNummer = new VUNummer(Config.getVUNummer(), 5);
	/** 30 Zeichen, Byte 10 - 39 */
	private AlphaNumFeld absender = new AlphaNumFeld(30, 10);
	/** 30 Zeichen, Byte 40 - 69 */
	private AlphaNumFeld adressat = new AlphaNumFeld(30, 40);
	/** 8 Zeichen, Byte 70 - 77 */
	private Datum von = new Datum(70);
	/** 8 Zeichen, Byte 78 - 85 */
	private Datum bis = new Datum(78);
	/** 10 Zeichen, Byte 86 - 95 */
	private AlphaNumFeld vermittler = new AlphaNumFeld(10, 86);

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
	
	/**
	 * @param startDatum im Format "TTMMJJJJ"
	 * @param endDatum im Format "TTMMJJJJ"
	 */
	public void setErstellungsZeitraum(String startDatum, String endDatum) {
		this.von.setInhalt(startDatum);
		this.bis.setInhalt(endDatum);
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].setData(this.von);
			teildatensatz[i].setData(this.bis);
		}
	}

}
