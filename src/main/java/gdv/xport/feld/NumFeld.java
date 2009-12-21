/**
 *
 */
package gdv.xport.feld;

import java.text.*;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.constraint.MatchPatternCheck;
import net.sf.oval.context.ClassContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.*;

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

    private static final Log log = LogFactory.getLog(NumFeld.class);
    private final int nachkommastellen;

    /**
     * @param name Feld-Bezeichner (z.B. "Anzahl Saetze")
     * @param s z.B. "4"
     */
    public NumFeld(final String name, final String s) {
        super(name, s, Align.RIGHT);
        this.nachkommastellen = 0;
    }

    /**
     * @param name Feld-Bezeichner (z.B. "Anzahl Saetze")
     * @param length Anzahl Bytes
     * @param start Start-Byte (beginnend bei 1)
     */
    public NumFeld(final String name, final int length, final int start) {
        super(name, length, start, Align.RIGHT);
        this.nachkommastellen = 0;
        this.setInhalt(0);
    }

    /**
     * @param name Feld-Bezeichner (z.B. "Anzahl Saetze")
     * @param length Anzahl Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param value z.B. "01"
     */
    public NumFeld(final String name, final int length, final int start, final int value) {
        super(name, length, start, Align.RIGHT);
        this.nachkommastellen = 0;
        this.setInhalt(value);
    }

    /**
     * @since 0.4
     * @param name Feld-Bezeichner (z.B. "Anzahl Saetze")
     * @param start Start-Byte (beginnend bei 1)
     * @param value z.B. "01"
     */
    public NumFeld(final String name, final int start, final String value) {
        super(name, start, value, Align.RIGHT);
        this.nachkommastellen = 0;
        this.setInhalt(value);
    }
    
    /**
     * @since 0.4
     * @param name Feld-Bezeichner (z.B. "pi")
     * @param s der Inhalt (z.B. "314")
     * @param nachkommastellen Anzahl der Nachkommastellen (z.B. 2)
     */
    public NumFeld(final String name, final String s, final int nachkommastellen) {
        super(name, s, Align.RIGHT);
        this.nachkommastellen = nachkommastellen;
    }
    
    /**
     * @since 0.4
     * @param name Feld-Bezeichner (z.B. "pi")
     * @param start Start-Byte (beginnend ab 1)
     * @param value der Inhalt (z.B. "314")
     * @param nachkommastellen Anzahl der Nachkommastellen (z.B. 2)
     */
    public NumFeld(final String name, final int start, final String value,
            final int nachkommastellen) {
        super(name, start, value, Align.RIGHT);
        this.nachkommastellen = nachkommastellen;
        this.setInhalt(value);
    }

    /**
     * @since 0.4
     * @param name Feld-Bezeichner (z.B. "pi")
     * @param length Gesamtlaenge
     * @param start Start-Byte (beginnend ab 1)
     * @param value der Inhalt (z.B. 314)
     * @param nachkommastellen Anzahl der Nachkommastellen (z.B. 2)
     */
    public NumFeld(final String name, final int length, final int start, final int value,
            final int nachkommastellen) {
        super(name, length, start, Align.RIGHT);
        this.nachkommastellen = nachkommastellen;
        this.setInhalt(value);
    }
    
    /**
     * Liefert ein neues NumFeld mit der gewuenschten Anzahl von Nachkommastellen zurueck.
     * 
     * @since 0.4
     * @param n Anzahl der Nachkommastellen
     * @return neues NumFeld mit n Nachkommastellen
     */
    public NumFeld mitNachkommastellen(final int n) {
        return new NumFeld(this.getBezeichnung(), this.getByteAdresse(),
                this.getInhalt(), n);
        
    }

    /**
     * @param n neuer Inhalt
     */
    public void setInhalt(final int n) {
        this.setInhalt((long) n);
    }

    /**
     * @param n neuer Inhalt
     */
    public void setInhalt(final long n) {
        String pattern = StringUtils.repeat("0", this.getAnzahlBytes());
        NumberFormat format = new DecimalFormat(pattern);
        String formatted = format.format(n);
        this.setInhalt(formatted);
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#resetInhalt()
     */
    @Override
    public void resetInhalt() {
        int anzahlBytes = this.getAnzahlBytes();
        for (int i = 0; i < anzahlBytes; i++) {
            this.setInhalt('0', i);
        }
    }

    /**
     * @return den Inhalt als int
     */
    public int toInt() {
        return Integer.parseInt(this.getInhalt().toString());
    }
    
    /**
     * Wenn eine Zahl Nachkommastellen hat, sollte sie auch als Double
     * ausgegeben werden koennen.
     * 
     * @since 0.4
     * @return die Zahl als Double
     */
    public double toDouble() {
        double n = toInt();
        for (int i = 0; i < this.nachkommastellen; i++) {
            n /= 10;
        }
        return n;
    }

    /**
     * @return true, wenn der Inhalt eine Zahl ist
     */
    public boolean isValid() {
        if (!super.isValid()) {
            return false;
        }
        if (this.isEmpty()) {
            return true;
        }
        try {
            this.toInt();
        } catch (NumberFormatException nfe) {
            log.info(this + " is invalid: not a number (" + nfe + ")");
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#validate()
     */
    @Override
    public List<ConstraintViolation> validate() {
        List<ConstraintViolation> violations = super.validate();
        if (!this.isEmpty()) {
            try {
                this.toInt();
            } catch (NumberFormatException nfe) {
                ConstraintViolation cv = new ConstraintViolation(new MatchPatternCheck(),
                        "not a number (" + nfe + ")", this, this.getInhalt(), new ClassContext(this
                                .getClass()));
                violations.add(cv);
            }
        }
        return violations;
    }

}
