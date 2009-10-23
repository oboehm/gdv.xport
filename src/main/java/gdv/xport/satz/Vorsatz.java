/**
 * 
 */
package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;

import java.util.*;

import gdv.xport.config.Config;
import gdv.xport.feld.*;


/**
 * @author oliver
 */
public final class Vorsatz extends Satz {
	
	/** 5 Zeichen, Byte 5 - 9 */
	protected AlphaNumFeld vuNummer = new VUNummer(Config.getVUNummer(), 5);
	/** 30 Zeichen, Byte 10 - 39 */
	private AlphaNumFeld absender = new AlphaNumFeld(ABSENDER, 30, 10);
	/** 30 Zeichen, Byte 40 - 69 */
	private AlphaNumFeld adressat = new AlphaNumFeld(ADRESSAT, 30, 40);
	/** 8 Zeichen, Byte 70 - 77 */
	private Datum von = new Datum(ERSTELLUNGSDATUM_ZEITRAUM_VOM, 70);
	/** 8 Zeichen, Byte 78 - 85 */
	private Datum bis = new Datum(ERSTELLUNGSDATUM_ZEITRAUM_BIS, 78);
	/** 10 Zeichen, Byte 86 - 95 */
	private AlphaNumFeld vermittler = new AlphaNumFeld(VERMITTLER, 10, 86);
	/** die Versionen fuer die verschiedenen Datensaetze */
	private Map<Integer, Version> versions = new HashMap<Integer, Version>();

	public Vorsatz() {
		super("0001", 3);
		setUpTeildatensaetze();
		setUpVersions();
		setUpDatensaetze();
	}
	
	private void setUpTeildatensaetze() {
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].add(this.vuNummer);
			teildatensatz[i].add(this.absender);
			teildatensatz[i].add(this.adressat);
			teildatensatz[i].add(this.von);
			teildatensatz[i].add(this.bis);
			teildatensatz[i].add(this.vermittler);
		}
	}
	
	private void setUpVersions() {
		addVersion(1, new Version(VERSION_VORSATZ, 96, "1.0"));
		addVersion(100, new Version(VERSION_ADRESSSATZ, 99));
		addVersion(200, new Version(VERSION_ALLGEMEINER_VERTRAGSTEIL, 102));
		addVersion(9999, new Version(VERSION_NACHSATZ, 225, "1.0"));
	}
	
	private void setUpDatensaetze() {
	}
	
	private void addVersion(Integer satzart, Version version) {
		versions.put(satzart, version);
		add(version);
	}
	
	/**
	 * Absender ist Byte 10 - 39 im Teildatensatz.
	 * @param absender
	 */
	public void setAbsender(String absender) {
		this.absender.setInhalt(absender);
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].add(this.absender);			
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
			teildatensatz[i].add(this.von);
			teildatensatz[i].add(this.bis);
		}
	}
	
	public String getVersionAdresssatz() {
		return this.getFeld(VERSION_ADRESSSATZ).getInhalt();
	}
	
	/**
	 * Momentan wird die Version immer auf "1.0" fuer den uebergebenen
	 * Datensatz gesetzt.
	 * 
	 * @param datensatz
	 */
	public void setVersionFor(Datensatz datensatz) {
		int satzart = datensatz.getSatzart();
		Version version = versions.get(satzart);
		version.setInhalt("1.0");
	}

}
