/*
 * $Id$
 *
 * Copyright (c) 2009 by Oliver Boehm
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

import gdv.xport.annotation.FeldInfo;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The Class Datum.
 *
 * @author oliver
 * @since 04.10.2009
 * @version $Revision$
 */
public final class Datum extends Feld {

    private static final Logger LOG = LogManager.getLogger(Feld.class);
    private final DateFormat dateFormat;

    /**
     * Legt ein neues Datums-Feld an. Die Informationen dazu werden
     * aus der uebergebenen Enum bezogen.
     *
     * @param feldX Enum mit den Feldinformationen
     * @since 0.9
     */
    public Datum(final Enum<?> feldX) {
        this(feldX, Feld.getFeldInfo(feldX));
    }

    /**
     * Instantiiert ein neues Datum.
     *
     * @param feldX Feld
     * @param info mit Angabe der Start-Adresse
     * @since 0.6
     */
    public Datum(final Enum<?> feldX, final FeldInfo info) {
        super(feldX, info);
        this.dateFormat = getDateFormat(info.anzahlBytes());
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
        this(new Bezeichner(name), 8, start);
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
        super(bezeichner, length, start, Align.RIGHT);
        dateFormat = getDateFormat(length);
    }

    /**
     * Instantiiert ein neues Datum.
     *
     * @param bezeichner Bezeichner
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 1.0
     */
    public Datum(final Bezeichner bezeichner, final FeldInfo info) {
        super(bezeichner, info.anzahlBytes(), info.byteAdresse(), info.align() == Align.UNKNOWN ? Align.RIGHT : info.align());
        dateFormat = getDateFormat(info.anzahlBytes());
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
        this(new Bezeichner(name), length, start);
        this.setInhalt(inhalt);
    }

    /**
     * Instantiates a new datum.
     */
    public Datum() {
        this(1);
    }

    /**
     * Instantiates a new datum.
     *
     * @param start the start
     */
    public Datum(final int start) {
        this(8, start);
    }

    /**
     * Instantiates a new datum.
     *
     * @param length the length
     * @param start the start
     */
    public Datum(final int length, final int start) {
        super(length, start, Align.RIGHT);
        dateFormat = getDateFormat(length);
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
     * Sets the inhalt.
     *
     * @param d the new inhalt
     */
    public void setInhalt(final Date d) {
        this.setInhalt(dateFormat.format(d));
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
