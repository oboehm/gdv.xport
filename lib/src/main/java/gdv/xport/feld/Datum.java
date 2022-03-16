/*
 * Copyright (c) 2009-2019 by Oliver Boehm
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
 * (c)reated 04.10.2009 by oliver (ob@oasd.de)
 */

package gdv.xport.feld;

import gdv.xport.config.Config;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * The Class Datum.
 *
 * @author oliver
 * @since 04.10.2009
 * @version $Revision$
 */
public final class Datum extends NumFeld {

    private static final Logger LOG = LogManager.getLogger(Feld.class);
    private static final Feld.Validator DEFAULT_VALIDATOR = new Datum.Validator(Config.getInstance());
    private final DateFormat dateFormat;

    /**
     * Dies ist der Copy-Constructor, mit dem man ein bestehendes Feld
     * kopieren kann.
     *
     * @param other das originale Feld
     */
    public Datum(final Feld other) {
        this(other, other.config);
    }

    private Datum(final Feld other, final Config cfg) {
        super(other, cfg);
        dateFormat = getDateFormat(other.getAnzahlBytes());
    }

    /**
     * Erstellt ein neues Datum.
     *
     * @param bezeichner Bezeichner
     * @param start the start
     * @since 2.0
     */
    public Datum(final Bezeichner bezeichner, final int start) {
        this(bezeichner, 8, start);
    }

    /**
     * Erstellt ein neues Datum.
     *
     * @param name the name
     * @param start the start
     */
    public Datum(final String name, final int start) {
        this(Bezeichner.of(name), 8, start);
    }

    /**
     * Instantiates a new datum.
     *
     * @param name the name
     * @param inhalt Datum der Form "ddmmjjjj" oder "ddjjjj" oder "dd"
     */
    public Datum(final String name, final String inhalt) {
        this(name, inhalt.length(), 1, inhalt);
    }

    /**
     * Legt ein neues Datum an.
     *
     * @param bezeichner Bezeichner
     * @param length Anzahl Bytes
     * @param start Byte-Adresse
     */
    public Datum(Bezeichner bezeichner, int length, int start) {
        super(bezeichner, length, start);
        dateFormat = getDateFormat(length);
    }

    /**
     * Instantiates a new datum.
     *
     * @param name the name
     * @param length the length
     * @param start the start
     * @param inhalt Datum der Form "ddmmjjjj" oder "ddjjjj" oder "dd"
     */
    public Datum(final String name, final int length, final int start, final String inhalt) {
        this(Bezeichner.of(name), length, start);
        this.setInhalt(inhalt);
    }

    private Datum() {
        this(Bezeichner.of("Datum"), 8, 1);
    }

    /**
     * Dies ist der Copy-Constructor, mit dem man ein bestehendes Datum
     * kopieren kann.
     *
     * @param other das originale Feld
     */
    public Datum(final Datum other) {
        super(other);
        this.dateFormat = other.dateFormat;
    }

    /**
     * Liefert eine neues Datum mit neuer Konfiguration.
     *
     * @param c neue Konfiguration
     * @return neues Datzm
     * @since 6.1
     */
    @Override
    public Datum mitConfig(Config c) {
        return new Datum(this, c);
    }

    private static DateFormat getDateFormat(final int length) {
        return getDateFormat(length, "");
    }

    private static DateFormat getDateFormat(final int length, final String separator) {
        switch (length) {
            case 2:
                return new SimpleDateFormat("dd");
            case 4:
                return new SimpleDateFormat("MM" + separator + "yy");
            case 6:
                return new SimpleDateFormat("MM" + separator + "yyyy");
            case 8:
                return new SimpleDateFormat("dd" + separator + "MM" + separator + "yyyy");
            default:
                throw new IllegalArgumentException("length=" + length
                        + " not allowed - only 2, 4, 6 or 8");
        }
    }

    /**
     * Sets the inhalt.
     *
     * @param d the new inhalt
     */
    public void setInhalt(final Datum d) {
        this.setInhalt(d.getInhalt());
    }

    /**
     * Setzt den Inhalt anhand des uebergebenen Datums.
     *
     * @param d neues Datum
     */
    public void setInhalt(final Date d) {
        this.setInhalt(dateFormat.format(d));
    }

    @Override
    public void setInhalt(String neuerInhalt) {
        if (!isEmpty(neuerInhalt)) {
            Datum.Validator validator = (Datum.Validator) getValidator();
            String value = validator.verifyFormat(dateFormat, neuerInhalt);
        }
        super.setInhalt(neuerInhalt);
    }

    /**
     * Setzt den Inhalt anhand des uebergebenen Datums.
     *
     * @param localDate neues Datum
     * @since 5.0
     */
    public void setInhalt(final LocalDate localDate) {
        this.setInhalt(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * Addiert den Summand auf und liefert das Datum als Zahl zurueck.
     *
     * @param anzahlTage Anzahl Tage
     * @return Summe
     * @since 5.0
     */
    @Override
    public BigDecimal add(BigDecimal anzahlTage) {
        LocalDate d = toLocalDate();
        d = d.plusDays(anzahlTage.intValue());
        setInhalt(d);
        return toBigDecimal();
    }

    /**
     * To date.
     *
     * @return the date
     */
    public Date toDate() {
        try {
            return dateFormat.parse(this.getInhalt());
        } catch (ParseException e) {
            throw new IllegalStateException(this + " has an invalid date (\""
                    + this.getInhalt() + "\")");
        }
    }

    /**
     * Wandelt das Datum in ein {@link LocalDate} um
     *
     * @return Datum als {@link LocalDate}
     * @since 5.0
     */
    public LocalDate toLocalDate() {
        SimpleDateFormat df = (SimpleDateFormat) dateFormat;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(df.toPattern());
        return LocalDate.parse(this.getInhalt(), formatter);
    }

    /**
     * Heute.
     *
     * @return the datum
     */
    public static Datum heute() {
        Datum d = new Datum();
        d.setInhalt(new Date());
        return d;
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        if (super.isEmpty()) {
            return true;
        }
        return isEmpty(this.getInhalt());
    }

    private static boolean isEmpty(String value) {
        try {
            int n = Integer.parseInt(value);
            return n == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Aus Performance-Gruenden verwenden wir hier nicht die
     * validate()-Methode.
     *
     * @return true/false
     *
     * @see gdv.xport.feld.Feld#isValid()
     */
    @Override
    public boolean isValid() {
        if (this.isEmpty()) {
            return true;
        }
        if (!super.isValid()) {
            return false;
        }
        return this.hasValidDate();
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#isInvalid()
     */
    @Override
    public boolean isInvalid() {
        return !this.isValid();
    }

    private boolean hasValidDate() {
        Datum.Validator validator = (Datum.Validator) getValidator();
        try {
            validator.validateFormat(dateFormat, this.getInhalt());
            return true;
        } catch (ValidationException e) {
            LOG.info(e + " -> mapped to false");
            return false;
        }
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#validate()
     */
    @Override
    public List<ConstraintViolation> validate() {
        List<ConstraintViolation> violations = super.validate();
        if (!this.isEmpty() && !this.hasValidDate()) {
            ConstraintViolation cv =
                    new SimpleConstraintViolation("'" + this.getInhalt() + "' is not a valid date", this);
            violations.add(cv);
        }
        return violations;
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#format()
     */
    @Override
    public String format() {
        DateFormat df = getDateFormat(this.getAnzahlBytes(), ".");
        return df.format(this.toDate());
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#clone()
     */
    @SuppressWarnings("squid:S2975")
    @Override
    public Object clone() {
        return new Datum(this);
    }



    /**
     * Die Validierung von Datum-Felder ist etwas strikter als bei NumFeldern.
     * Einze Zahl ist nicht automatisch ein gueltiges Datum. Es muss schon mit
     * dem eingestellten Datumsformat uebereinstimmen.
     *
     * @since 6.2
     */
    public static class Validator extends NumFeld.Validator {

        public Validator() {
            super();
        }

        public Validator(Config config) {
            super(config);
        }

        public String verifyFormat(DateFormat format, String value) {
            if ((getConfig().getValidateMode() == Config.ValidateMode.STRICT) && (format != null)) {
                try {
                    return validateFormat(format, value);
                } catch (ValidationException ex) {
                    throw new IllegalArgumentException("kein Datum: " + value, ex);
                }
            }
            return value;
        }

        public String validateFormat(DateFormat format, String value) {
            if (value.startsWith("00")) {
                return value;
            }
            try {
                Date date = format.parse(value);
                String converted = format.format(date);
                if (!value.equals(converted)) {
                    throw new ValidationException(String.format(
                            "'%s' ist kein korrektes Datum - ist vielleicht '%s' gemeint?", value, converted));
                }
            } catch (ParseException ex) {
                throw new ValidationException(String.format("'%s' ist kein Datum", value), ex);
            }
            return value;
        }

    }

}
