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

import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private final DateFormat dateFormat;

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
        try {
            int n = Integer.parseInt(this.getInhalt());
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
        String orig = this.getInhalt();
        if (orig.startsWith("00")) {
            return true;
        }
        try {
            Date date = this.toDate();
            String conv = this.dateFormat.format(date);
            return conv.equals(orig);
        } catch (RuntimeException e) {
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

}
