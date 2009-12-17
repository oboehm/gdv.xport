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

    public NumFeld(String name, String s) {
        super(name, s, Align.RIGHT);
    }

    public NumFeld(String name, int length, int start) {
        super(name, length, start, Align.RIGHT);
        this.setInhalt(0);
    }

    public NumFeld(String name, int length, int start, int value) {
        super(name, length, start, Align.RIGHT);
        this.setInhalt(value);
    }

    public NumFeld(int length, int start) {
        super(length, start, Align.RIGHT);
        this.setInhalt(0);
    }

    public void setInhalt(int n) {
        this.setInhalt((long) n);
    }

    public void setInhalt(long n) {
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

    public int toInt() {
        return Integer.parseInt(this.getInhalt().toString());
    }

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
