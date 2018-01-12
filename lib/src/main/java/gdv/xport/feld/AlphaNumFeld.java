/**
 *
 */
package gdv.xport.feld;

import de.jfachwert.bank.BIC;
import de.jfachwert.bank.IBAN;
import gdv.xport.annotation.FeldInfo;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.constraint.AssertValidCheck;
import net.sf.oval.constraint.SizeCheck;
import net.sf.oval.context.ClassContext;

import javax.validation.ValidationException;
import java.util.List;

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
     *
     * @param bezeichner Bezeichner
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 1.0
     */
    public AlphaNumFeld(final Bezeichner bezeichner, final FeldInfo info) {
        super(bezeichner, info.anzahlBytes(), info.byteAdresse(), info.align() == Align.UNKNOWN ? Align.LEFT : info.align());
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

    /**
     * Bestimmte Feld-Typen wie IBAN oder BIC werden ebenfalls validiert,
     * sofern dies moeglich ist.
     * 
     * @return eine Liste von Validierungs-Fehlern
     */
    @Override
    public List<ConstraintViolation> validate() {
        List<ConstraintViolation> violations = super.validate();
        if (!this.isEmpty()) {
            try {
                String name = this.getBezeichner().getName();
                if (name.startsWith("IBAN")) {
                    IBAN.validate(this.getInhalt());
                } else if (name.startsWith("BIC")) {
                    BIC.validate(this.getInhalt().trim());
                }
            } catch (ValidationException ex) {
                ConstraintViolation cv = new ConstraintViolation(new AssertValidCheck(), ex.getLocalizedMessage(), this,
                        this.getInhalt(), new ClassContext(this.getClass()));
                violations.add(cv);
            }
        }
        return violations;
    }

}
