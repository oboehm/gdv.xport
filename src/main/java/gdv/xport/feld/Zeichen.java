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
public class Zeichen extends Feld {

	public Zeichen(String s) {
		super(s);
	}
	
	public Zeichen(String s, int start) {
		super(s, start);
	}
	
	public Zeichen(int length, int start) {
		super(length, start);
	}

}
