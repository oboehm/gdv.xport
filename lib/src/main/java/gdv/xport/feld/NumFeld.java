/*
 * Copyright (c) 2009 - 2017 by Oli B.
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
 * (c)reated 09.10.2009 by Oli B. (ob@aosd.de)
 */
package gdv.xport.feld;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Klasse fuer numerische Zeichen. Die Default-Einstellung fuer die
 * Darstellung ist rechtsbuendig.
 * <p>
 * Siehe Broschuere_gdv-datensatz_vu-vermittler.pdf, Seite 16
 * ("Datenfelder/Feldformate").
 * </p>
 *
 * @author oliver
 */
public class NumFeld extends Feld {

    private static final Logger LOG = LogManager.getLogger(NumFeld.class);
    private final int nachkommastellen;

    /**
     * Legt ein neues Betrags-Feld an. Die Informationen dazu werden
     * aus der uebergebenen Enum bezogen.
     *
     * @param feldX Enum mit den Feldinformationen
     * @since 0.9
     */
    public NumFeld(final Enum<?> feldX) {
        this(feldX, Feld.getFeldInfo(feldX));
    }

    /**
     * Instantiiert ein neues numerisches Feld.
     *
     * @param feldX Feld
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 0.6
     */
    public NumFeld(final Enum<?> feldX, final FeldInfo info) {
        super(feldX, info);
        this.nachkommastellen = info.nachkommaStellen();
        this.setInhalt(0);
    }

    /**
     * @param name Feld-Bezeichner (z.B. "Anzahl Saetze")
     * @param s z.B. "4"
     */
    public NumFeld(final String name, final String s) {
        super(name, s, Align.RIGHT);
        this.nachkommastellen = 0;
    }

    /**
     * Legt ein neues numerisches Feld an.
     *
     * @param bezeichner Feld-Bezeichner (z.B. "Anzahl Saetze")
     * @param length Anzahl Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @since 1.0
     */
    public NumFeld(Bezeichner bezeichner, int length, int start) {
        super(bezeichner, length, start, Align.RIGHT);
        this.nachkommastellen = 0;
        this.setInhalt(0);
    }

    /**
     * Legt ein neues numerisches Feld an.
     *
     * @param bezeichner Feld-Bezeichner (z.B. "Anzahl Saetze")
     * @param length Anzahl Bytes
     * @param start Start-Byte (beginnend bei 1)
     * @param value z.B. "01"
     * @since 1.0
     */
    public NumFeld(final Bezeichner bezeichner, final int length, final int start, final int value) {
        super(bezeichner, length, start, Align.RIGHT);
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
     * Instantiiert ein neues numerisches Feld.
     *
     * @param name Bezeichner
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 1.0
     */
    public NumFeld(final Bezeichner name, final FeldInfo info) {
        super(name, info.anzahlBytes(), info.byteAdresse(), info.align() == Align.UNKNOWN ? Align.RIGHT : info.align());
        this.nachkommastellen = info.nachkommaStellen();
    }

    /**
     * Instantiiert ein neues numerisches Feld.
     *
     * @param name Feld-Bezeichner (z.B. "pi")
     * @param start Start-Byte (beginnend ab 1)
     * @param value der Inhalt (z.B. "314")
     * @param nachkommastellen Anzahl der Nachkommastellen (z.B. 2)
     * @since 0.4
     */
    public NumFeld(final String name, final int start, final String value,
            final int nachkommastellen) {
        super(name, start, value, Align.RIGHT);
        this.nachkommastellen = nachkommastellen;
        this.setInhalt(value);
    }

    /**
     * Legt ein neues numerisches Feld an.
     *
     * @param name Feld-Bezeichner (z.B. "pi")
     * @param length Gesamtlaenge
     * @param start Start-Byte (beginnend ab 1)
     * @param value der Inhalt (z.B. 314)
     * @param nachkommastellen Anzahl der Nachkommastellen (z.B. 2)
     * @since 1.0
     */
    public NumFeld(final Bezeichner name, final int length, final int start, final int value,
            final int nachkommastellen) {
        super(name, length, start, Align.RIGHT);
        this.nachkommastellen = nachkommastellen;
        this.setInhalt(value);
    }

    /**
     * Dies ist der Copy-Constructor, mit dem man ein bestehendes Feld
     * kopieren kann.
     *
     * @param other das originale Feld
     */
    public NumFeld(final NumFeld other) {
        super(other);
        this.nachkommastellen = other.nachkommastellen;
    }

    /**
     * Die Default-Ausrichtung ist rechts-buendig fuer Zahlen.
     *
     * @return rechts-buendig
     */
    @Override
    protected Align getDefaultAlignment() {
        return Align.RIGHT;
    }

    /**
     * Liefert ein neues NumFeld mit der gewuenschten Anzahl von Nachkommastellen zurueck.
     *
     * @since 0.4
     * @param n Anzahl der Nachkommastellen
     * @return neues NumFeld mit n Nachkommastellen
     */
    public NumFeld mitNachkommastellen(final int n) {
        if (n > this.getAnzahlBytes()) {
            throw new IllegalArgumentException(n + " Nachkommastellen sind zuviel (max. "
                    + this.getAnzahlBytes() + " moeglich)");
        }
        return new NumFeld(this.getBezeichnung(), this.getByteAdresse(),
                this.getInhalt(), n);

    }

    /**
     * Liefert die Anzahl der Nachkommastellen.
     *
     * @return Anzahl der Nachkommastellen
     */
    public int getNachkommastellen() {
        return this.nachkommastellen;
    }

    /**
     * @param n neuer Inhalt
     */
    @Override
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
     * Wandelt den Inhalt in einen Integer (ohne Beruecksichtigung etwaiger
     * Nachkommastellen).
     *
     * @return den Inhalt als int
     */
    public int toInt() {
        String s = this.getInhalt().trim();
        if (s.startsWith("+")) {
            return Integer.parseInt(s.substring(1));
        }
        return Integer.parseInt(s);
    }

    /**
     * Wandelt den Inhalt in einen Long (ohne Beruecksichtigung etwaiger
     * Nachkommastellen).
     *
     * @return den Inhalt als long
     */
    public long toLong() {
        String s = this.getInhalt().trim();
        if (s.startsWith("+")) {
            return Long.parseLong(s.substring(1));
        }
        return Long.parseLong(s);
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
    @Override
    public boolean isValid() {
        if (!super.isValid()) {
            return false;
        }
        if (this.isEmpty()) {
            return true;
        }
        try {
            this.toLong();
        } catch (NumberFormatException nfe) {
            LOG.info(this + " is invalid: not a number (" + nfe + ")");
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
        if (this.hasValue()) {
            try {
                this.toLong();
            } catch (NumberFormatException nfe) {
                ConstraintViolation cv = new SimpleConstraintViolation(this, nfe);
                violations.add(cv);
            }
        }
        return violations;
    }


    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#format()
     */
    @Override
    public String format() {
        NumberFormat nf = NumberFormat.getInstance();
        if (this.nachkommastellen == 0) {
            return nf.format(this.toLong());
        }
        nf.setMinimumFractionDigits(this.nachkommastellen);
        nf.setMaximumFractionDigits(this.nachkommastellen);
        return nf.format(this.toDouble());
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#clone()
     */
    @SuppressWarnings("squid:S2975")
    @Override
    public Object clone() {
        return new NumFeld(this);
    }

}
