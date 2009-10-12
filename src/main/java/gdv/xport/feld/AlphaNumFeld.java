/**
 * 
 */
package gdv.xport.feld;

/**
 * Klasse fuer alphanumerische Zeichen. Die Default-Einstellung fuer die
 * Darstellung ist linksbuendig.
 * 
 * @author oliver
 */
public class AlphaNumFeld extends Feld {

	public AlphaNumFeld(String s) {
		super(s, Align.LEFT);
	}
	
	public AlphaNumFeld(String s, int start) {
		super(s, start, Align.LEFT);
	}
	
	public AlphaNumFeld(int length, int start) {
		super(length, start, Align.LEFT);
	}
	
	public AlphaNumFeld(int length, int start, Align alignment) {
		super(length, start, alignment);
	}
	
	/**
	 * Wenn sich das Feld vergroessert, werden rechts Leerzeichen aufgefuellt
	 * (alphanumerische Zeichen sind standardmaessig linksbuendig).
	 * 
	 * @param n
	 */
	public void setAnzahlBytes(int n) {
		assert this.inhalt.length() <= n : "drohender Datenverlust";
		for (int i = this.inhalt.length(); i < n; i++) {
			this.inhalt.append(' ');
		}
	}

}
