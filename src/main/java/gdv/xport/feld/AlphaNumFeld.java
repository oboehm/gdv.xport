/**
 *
 */
package gdv.xport.feld;

import gdv.xport.annotation.FeldInfo;

/**
 * Klasse fuer alphanumerische Zeichen. Die Default-Einstellung fuer die
 * Darstellung ist linksbuendig.
 *
 * @author oliver
 */
public class AlphaNumFeld extends Feld {

    /**
     * @param name Bezeichner
     * @param s Inhalt
     */
    public AlphaNumFeld(final String name, final String s) {
        super(name, s, Align.LEFT);
    }

    /**
     * @param name Bezeichner
     * @param start Start-Byte (beginnend bei 1)
     * @param s Inhalt
     */
    public AlphaNumFeld(final String name, final int start, final String s) {
        super(name, start, s, Align.LEFT);
    }

    /**
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     */
    public AlphaNumFeld(final String name, final int length, final int start) {
        super(name, length, start, Align.LEFT);
    }

    /**
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param s Inhalt
     */
    public AlphaNumFeld(final String name, final int length, final int start, final String s) {
        super(name, length, start, s, Align.LEFT);
    }

    /**
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param alignment Ausrichtung
     */
    public AlphaNumFeld(final String name, final int length, final int start, final Align alignment) {
        super(name, length, start, alignment);
    }

    /**
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     */
    public AlphaNumFeld(final int length, final int start) {
        super(length, start, Align.LEFT);
    }

    /**
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param alignment Ausrichtung
     */
    public AlphaNumFeld(final int length, final int start, final Align alignment) {
        super(length, start, alignment);
    }
    
    /**
     * Instantiiert ein neues alpha-numerisches Feld.
     * 
     * @param name Bezeichner
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 0.6
     */
    public AlphaNumFeld(final String name, final FeldInfo info) {
        super(name, info.anzahlBytes(), info.byteAdresse(), info.align() == Align.UNKNOWN ? Align.LEFT : info.align());
    }

    /**
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param inhalt einzelnes Zeichen
     */
    public AlphaNumFeld(final String name, final int length, final int start, final char inhalt) {
        super(name, length, start, inhalt, Align.LEFT);
    }

}
