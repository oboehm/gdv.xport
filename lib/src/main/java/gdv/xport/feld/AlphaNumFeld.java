/*
 * Copyright (c) 2009 - 2021 by Oli B.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 08.10.2009 by Oli B. (ob@aosd.de)
 */
package gdv.xport.feld;

import de.jfachwert.FachwertFactory;
import gdv.xport.annotation.FeldInfo;
import gdv.xport.config.Config;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;

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
     * <p>
     * TODO: Wird mit v6 entfernt.
     * </p>
     *
     * @param feldX Enum mit den Feldinformationen
     * @since 0.9
     * @deprecated Enums werden ab v6 nicht mehr unterstuetzt
     */
    @Deprecated
    public AlphaNumFeld(final Enum feldX) {
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
     * <p>
     * TODO: Wird mit v6 entfernt.
     * </p>
     *
     * @param feldX Feld
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 0.6
     * @deprecated Enums werden ab v6 nicht mehr unterstuetzt
     */
    @Deprecated
    public AlphaNumFeld(final Enum feldX, final FeldInfo info) {
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

    protected AlphaNumFeld(AlphaNumFeld other, Config c) {
        super(other, new Feld.Validator(c));
    }

    /**
     * Liefert eine neues Feld mit neuer Konfiguration
     *
     * @param c neue Konfiguration
     * @return neues NumFeld
     * @since 5.3
     */
    @Override
    public AlphaNumFeld mitConfig(Config c) {
        return new AlphaNumFeld(this, c);
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#clone()
     */
    @SuppressWarnings("squid:S2975")
    @Override
    public Object clone() {
        return new AlphaNumFeld(this);
    }

    /**
     * Bestimmte Feld-Typen wie IBAN oder BIC werden ebenfalls validiert,
     * sofern dies moeglich ist.
     *
     * @param validationConfig Konfiguration fuer Validierung (off, lax, strict)
     * @return eine Liste von Validierungs-Fehlern
     */
    @Override
    public List<ConstraintViolation> validate(Config validationConfig) {
        List<ConstraintViolation> violations = super.validate(validationConfig);
        if (this.hasValue()) {
            try {
                String name = this.getBezeichner().getName();
                FachwertFactory.getInstance().validate(name, this.getInhalt().trim());
            } catch (ValidationException ex) {
                ConstraintViolation cv = new SimpleConstraintViolation(this, ex);
                violations.add(cv);
            }
        }
        return violations;
    }

}
