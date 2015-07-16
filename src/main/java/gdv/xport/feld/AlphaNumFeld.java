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
     * Legt ein neues alphanumerisches Feld an. Die Informationen dazu werden
     * aus der uebergebenen Enum bezogen.
     *
     * @param feldX Enum mit den Feldinformationen
     * @since 0.9
     */
    public AlphaNumFeld(final Enum<?> feldX) {
        super(feldX);
    }

    /**
     * Legt ein neues alphanumerisches Feld an.
     *
     * @param name Bezeichner
     * @param s Inhalt
     */
    public AlphaNumFeld(final String name, final String s) {
        super(name, s, Align.LEFT);
    }

    /**
     * Legt ein neues alphanumerisches Feld an.
     * <p>
     * TODO: Bitte nicht mehr benutzen - wird in 1.2 entfernt!
     * </p>
     *
     * @param name Bezeichner
     * @param start Start-Byte (beginnend bei 1)
     * @param s Inhalt
     * @deprecated bitte {@link #AlphaNumFeld(Bezeichner, int, int)} verwenden
     */
    @Deprecated
    public AlphaNumFeld(final String name, final int start, final String s) {
        super(name, start, s, Align.LEFT);
    }

    /**
     * Legt ein neues alphanumerisches Feld an.
     * <p>
     * TODO: Bitte nicht mehr benutzen - wird in 1.2 entfernt!
     * </p>
     *
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @deprecated durch {@link #AlphaNumFeld(Bezeichner, int, int)} abgeloest
     */
    @Deprecated
    public AlphaNumFeld(final String name, final int length, final int start) {
        this(new Bezeichner(name), length, start);
    }

    /**
     * Legt ein neues alphanumerisches Feld an.
     *
     * @param bezeichner Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @since 1.0
     */
    public AlphaNumFeld(final Bezeichner bezeichner, final int length, final int start) {
        super(bezeichner, length, start, Align.LEFT);
    }

    /**
     * Legt ein neues alphanumerisches Feld an.
     * <p>
     * TODO: bitte nicht verwenden, wird in 1.2 entfernt!
     * </p>
     *
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param s Inhalt
     * @deprecated bitte {@link #AlphaNumFeld(Bezeichner, int, int, Align)} verwendent
     */
    @Deprecated
    public AlphaNumFeld(final String name, final int length, final int start, final String s) {
        super(name, length, start, s, Align.LEFT);
    }

    /**
     * Legt ein neues alphanumerisches Feld an.
     * <p>
     * TODO: bitte nicht verwenden, wird in 1.2 entfernt!
     * </p>
     *
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param alignment Ausrichtung
     * @deprecated bitte {@link #AlphaNumFeld(Bezeichner, int, int, Align)} verwendent
     */
    @Deprecated
    public AlphaNumFeld(final String name, final int length, final int start, final Align alignment) {
        this(new Bezeichner(name), length, start, alignment);
    }

    /**
     * Legt ein neues alpha-numerisches Feld an.
     *
     * @param bezeichner Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param alignment Ausrichtung
     * @since 1.0
     */
    public AlphaNumFeld(final Bezeichner bezeichner, final int length, final int start, final Align alignment) {
        super(bezeichner, length, start, alignment);
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
     * @param feldX Feld
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 0.6
     */
    public AlphaNumFeld(final Enum<?> feldX, final FeldInfo info) {
        super(feldX, info);
    }

    /**
     * Instantiiert ein neues alpha-numerisches Feld.
     * <p>
     * TODO: Bitte nicht mehr benutzen - wird in 1.2 entfernt!
     * </p>
     *
     * @param name Bezeichner
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 0.6
     * @deprecated bitte {@link #AlphaNumFeld(Bezeichner, FeldInfo)} verwenden
     */
    @Deprecated
    public AlphaNumFeld(final String name, final FeldInfo info) {
        this(new Bezeichner(name), info);
    }

    /**
     * Instantiiert ein neues alpha-numerisches Feld.
     *
     * @param bezeichner Bezeichner
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 1.0
     */
    public AlphaNumFeld(final Bezeichner bezeichner, final FeldInfo info) {
        super(bezeichner, info.anzahlBytes(), info.byteAdresse(), info.align() == Align.UNKNOWN ? Align.LEFT : info.align());
    }

    /**
     * Legt ein neues alphanumerisches Feld an.
     * <p>
     * TODO: bitte nicht verwenden, wird in 1.2 entfernt!
     * </p>
     *
     * @param name Bezeichner
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param inhalt einzelnes Zeichen
     * @deprecated bitte {@link #AlphaNumFeld(Bezeichner, int, int, Align)} verwendent
     */
    @Deprecated
    public AlphaNumFeld(final String name, final int length, final int start, final char inhalt) {
        super(name, length, start, inhalt, Align.LEFT);
    }

    /**
     * Dies ist der Copy-Constructor, mit dem man ein bestehendes Feld
     * kopieren kann.
     *
     * @param other das originale Feld
     */
    public AlphaNumFeld(final Feld other) {
        super(other);
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#clone()
     */
    @Override
    public Object clone() {
        return new AlphaNumFeld(this);
    }

}
