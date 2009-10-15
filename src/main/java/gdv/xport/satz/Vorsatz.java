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
	/** 3 Zeichen, Byte 225 - 227 */
	private Version versionNachsatz = new Version("1.1", 225);

	public Vorsatz() {
		super("0001", 3);
		initTeildatensaetze();
	}
	
	private void initTeildatensaetze() {
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].setDatenfeld(this.vuNummer);
			teildatensatz[i].setDatenfeld(this.absender);
			teildatensatz[i].setDatenfeld(this.adressat);
			teildatensatz[i].setDatenfeld(this.von);
			teildatensatz[i].setDatenfeld(this.bis);
			teildatensatz[i].setDatenfeld(this.vermittler);
		}
		teildatensatz[0].setDatenfeld(this.versionNachsatz);
	}
	
	/**
	 * Absender ist Byte 10 - 39 im Teildatensatz.
	 * @param absender
	 */
	public void setAbsender(String absender) {
		this.absender.setInhalt(absender);
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].setDatenfeld(this.absender);			
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
			teildatensatz[i].setDatenfeld(this.von);
			teildatensatz[i].setDatenfeld(this.bis);
		}
	}

}
