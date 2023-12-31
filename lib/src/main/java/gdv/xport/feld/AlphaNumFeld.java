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
import gdv.xport.config.Config;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Klasse fuer alphanumerische Zeichen. Die Default-Einstellung fuer die
 * Darstellung ist linksbuendig.
 *
 * @author oliver
 */
public class AlphaNumFeld extends Feld {

    private static final Logger LOG = LogManager.getLogger();
    private static final Validator DEFAULT_VALIDATOR = new Validator(Config.getInstance());

    /**
     * Legt ein neues alphanumerisches Feld an.
     * <p>
     * TODO: wird mit v7 entfernt
     * </p>
     *
     * @param name Bezeichner
     * @param s Inhalt
     * @deprecated wird in v7 nicht mehr unterstuetzt
     */
    @Deprecated
    public AlphaNumFeld(final String name, final String s) {
        this(Bezeichner.of(name), s.length(), 1);
        this.setInhalt(s);
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
        this(bezeichner, length, start, Align.LEFT);
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
        super(bezeichner, length, start, alignment, Config.getInstance());
    }

    /**
     * Legt ein neues alpha-numerisches Feld an.
     * <p>
     * TODO: wird mit v7 entfernt
     * </p>
     *
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @deprecated Felder ohne Bezeichner werden ab v7 nicht mehr unterstuetzt
     */
    @Deprecated
    public AlphaNumFeld(final int length, final int start) {
        this(length, start, Align.LEFT);
    }

    /**
     * Legt ein neues alpha-numerisches Feld an.
     * <p>
     * TODO: wird mit v7 entfernt
     * </p>
     *
     * @param length Laenge in Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param alignment Ausrichtung
     * @deprecated Felder ohne Bezeichner werden ab v7 nicht mehr unterstuetzt
     */
    @Deprecated
    public AlphaNumFeld(final int length, final int start, final Align alignment) {
        this(Bezeichner.of("NN"), length, start, alignment);
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
        super(other, c);
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
            } catch (RuntimeException ex) {
                ConstraintViolation cv = new SimpleConstraintViolation(this, ex);
                violations.add(cv);
            }
        }
        return violations;
    }



    /**
     * Die Validierung von Werten wurde jetzt in einer eingenen Validator-
     * Klasse zusammengefasst. Damit kann die Validierung auch unabhaengig
     * von AlphaNumFeld-Klasse im Vorfeld eingesetzt werden, um Werte auf ihre
     * Gueltigkeit pruefen zu koennen.
     *
     * @since 5.3
     */
    public static class Validator extends Feld.Validator {

        public Validator() {
            super();
        }

        public Validator(Config config) {
            super(config);
        }

        @Override
        protected String validateStrict(String value) {
            String trimmed = validateLax(value).trim();
            if ((trimmed.length() < value.length()) && (!trimmed.isEmpty())) {
                LOG.debug("Wert '{}' wurde auf '{}' fuer die weitere Verabeitung verkuerzt.", value, trimmed);
            }
            return trimmed;
        }

    }

}
