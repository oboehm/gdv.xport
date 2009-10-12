/**
 * 
 */
package gdv.xport.feld;

import java.text.*;

import org.apache.commons.lang.StringUtils;

/**
 * Klasse fuer numerische Zeichen. Die Default-Einstellung fuer die
 * Darstellung ist rechtsbuendig.
 * <br/>
 * Siehe Broschuere_gdv-datensatz_vu-vermittler.pdf, Seite 16
 * ("Datenfelder/Feldformate").
 * 
 * @author oliver
 */
public class NumFeld extends Feld {
	
	public NumFeld(String s) {
		super(s, Align.RIGHT);
	}
	
	public NumFeld(String s, int start) {
		super(s, start, Align.RIGHT);
	}
	
	public NumFeld(int length, int start) {
		super(length, start, Align.RIGHT);
		this.setInhalt(0);
	}
	
	public void setInhalt(int n) {
		String pattern = StringUtils.repeat("0", this.getAnzahlBytes());
		NumberFormat format = new DecimalFormat(pattern);
		String formatted = format.format(n);
		this.setInhalt(formatted);
	}
	
	public int toInt() {
		return Integer.parseInt(this.inhalt.toString());
	}

}
