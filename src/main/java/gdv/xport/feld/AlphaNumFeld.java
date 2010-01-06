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

    /**
     * @param name Bezeichner
     * @param s Inhalt
     */
    public AlphaNumFeld(String name, String s) {
        super(name, s, Align.LEFT);
    }

    /**
     * @param name Bezeichner
     * @param start Start-Byte (beginnend bei 1)
     * @param s Inhalt
     */
    public AlphaNumFeld(String name, int start, String s) {
        super(name, start, s, Align.LEFT);
    }

    /**
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     */
    public AlphaNumFeld(String name, int length, int start) {
        super(name, length, start, Align.LEFT);
    }

    /**
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param s Inhalt
     */
    public AlphaNumFeld(String name, int length, int start, String s) {
        super(name, length, start, s, Align.LEFT);
    }

    /**
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param alignment Ausrichtung
     */
    public AlphaNumFeld(String name, int length, int start, Align alignment) {
        super(name, length, start, alignment);
    }

    /**
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     */
    public AlphaNumFeld(int length, int start) {
        super(length, start, Align.LEFT);
    }

    /**
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param alignment Ausrichtung
     */
    public AlphaNumFeld(int length, int start, Align alignment) {
        super(length, start, alignment);
    }

    /**
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param inhalt einzelnes Zeichen
     */
    public AlphaNumFeld(String name, int length, int start, char inhalt) {
        super(name, length, start, inhalt, Align.LEFT);
    }

}
