/*
 * Copyright (c) 2009 - 2012 by Oli B.
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
 * (c)reated 11.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.config.Config;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Im Gegensatz zum Betrag hat diese Klasse ein Vorzeichen ('+' oder '-').
 *
 * @author oliver
 * @since 11.10.2009
 * @version $Revision$
 */
public final class BetragMitVorzeichen extends Betrag {

    private static final Logger LOG = LogManager.getLogger();

    /**
     * Legt ein neues Betrags-Feld an. Die Informationen dazu werden
     * aus der uebergebenen Enum bezogen.
     * <p>
     * TODO: Wird mit v6 entfernt.
     * </p>
     *
     * @param feldX Enum mit den Feldinformationen
     * @since 0.9
     * @deprecated durch {@link #BetragMitVorzeichen(Bezeichner, int, int)} abgeloest
     */
    @Deprecated
    public BetragMitVorzeichen(final Enum feldX) {
        this(feldX, Feld.getFeldInfo(feldX));
    }

    /**
     * Instantiiert ein neuen Betrag.
     * <p>
     * TODO: Wird mit v6 entfernt.
     * </p>
     *
     * @param feldX Feld
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 0.6
     * @deprecated durch {@link #BetragMitVorzeichen(Bezeichner, int, int)} abgeloest
     */
    @Deprecated
    public BetragMitVorzeichen(final Enum feldX, final FeldInfo info) {
        super(feldX, info);
        this.setVorzeichen('+');
    }

    /**
     * Instantiiert einen neuen BetragMitVorzeichen.
     *
     * @param name Bezeichner
     * @param length das Vorzeichen muss dabei mitgezaehlt werden
     * @param start Start-Byte (beginnend bei 1)
     * @since 1.0
     */
    public BetragMitVorzeichen(final Bezeichner name, final int length, final int start) {
        super(name, start, StringUtils.repeat('0', length-1) + "+", new Validator(Config.getInstance()));
        this.setVorzeichen('+');
    }

    /**
     * Instantiiert einen neuen BetragMitVorzeichen.
     * <p>
     * TODO: Wird mit v6 entfernt.
     * </p>
     *
     * @param name Bezeichner
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 1.0
     * @deprecated Enums werden ab v6 nicht mehr unterstuetzt
     */
    @Deprecated
    public BetragMitVorzeichen(final Bezeichner name, final FeldInfo info) {
        super(name, info);
        this.setVorzeichen('+');
    }

    /**
     * Dies ist der Copy-Constructor, mit dem man ein bestehendes Feld
     * kopieren kann.
     *
     * @param other das originale Feld
     */
    public BetragMitVorzeichen(final Feld other) {
        super(other);
    }

    /**
     * Vorzeichen setzen.
     * @param c '+' oder '-'
     */
    public void setVorzeichen(final char c) {
        this.setInhalt(c, this.getAnzahlBytes() - 1);
    }

    /**
     * Vorzeichen liefern.
     * @return '+' oder '-'
     */
    public char getVorzeichen() {
        String s = this.getInhalt();
        return s.charAt(s.length() - 1);
    }

    /**
     * Liefert nur den Betragsteil ohne Vorzeichen zurueck.
     *
     * @return Betrag ohne Vorzeichen
     * @since 5.0
     */
    public Betrag getBetrag() {
        String name = getBezeichnung();
        if (name.endsWith("mit Vorzeichen")) {
            name = name.substring(0, name.length() - 15).trim();
        } else {
            name += " ohne Vorzeichen";
        }
        Betrag betrag = new Betrag(Bezeichner.of(name), getAnzahlBytes()-1, getByteAdresse());
        betrag.setInhalt(getInhalt().substring(0, betrag.getAnzahlBytes()));
        return betrag;
    }

    /*
     * @see gdv.xport.feld.Betrag#setInhalt(double)
     */
    @Override
    public void setInhalt(final double x) {
        long n = Math.round(x * 100);
        setInhalt(n);
    }

    @Override
    public void setInhalt(final BigDecimal x) {
        int n = getNachkommastellen();
        setInhalt(x.setScale(n, RoundingMode.HALF_UP).doubleValue());
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Betrag#setInhalt(int)
     */
    @Override
    public void setInhalt(final int n) {
        this.setInhalt((long) n);
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.NumFeld#setInhalt(long)
     */
    @Override
    public void setInhalt(final long n) {
        String pattern = StringUtils.repeat("0", this.getAnzahlBytes()-1);
        NumberFormat format = new DecimalFormat(pattern);
        String formatted = format.format(Math.abs(n));
        if (n >= 0) {
            this.setInhalt(formatted + '+');
        } else {
            this.setInhalt(formatted + '-');
        }
    }

    @Override
    public void setInhalt(String s) {
        char lastChar = StringUtils.reverse(" " + s).charAt(0);
        if ((lastChar != '+') && (lastChar != '-')) {
            super.setInhalt(s + "+");
        } else {
            super.setInhalt(s);
        }
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Betrag#toDouble()
     */
    @Override
    public double toDouble() {
        return toBigDecimal().doubleValue();
    }

    @Override
    public BigDecimal toBigDecimal() {
        String s = this.getInhalt();
        String n = s.substring(0, s.length() - 1);
        if (StringUtils.isBlank(n)) {
            return BigDecimal.ZERO;
        }
        BigDecimal x = new BigDecimal(Long.parseLong(n)).movePointLeft(getNachkommastellen());
        return (this.getVorzeichen() == '-') ? x.negate() : x;
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Betrag#toInt()
     */
    @Override
    public int toInt() {
        String s = this.getInhalt();
        int x = Integer.parseInt(s.substring(0, s.length() - 1)) / 100;
        return (this.getVorzeichen() == '-') ? -x : x;
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.NumFeld#toLong()
     */
    @Override
    public long toLong() {
        String s = this.getInhalt();
        long x = Long.parseLong(s.substring(0, s.length() - 1)) / 100;
        return (this.getVorzeichen() == '-') ? -x : x;
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#resetInhalt()
     */
    @Override
    public void resetInhalt() {
        super.resetInhalt();
        this.setVorzeichen('+');
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#clone()
     */
    @SuppressWarnings("squid:S2975")
    @Override
    public Object clone() {
        return new BetragMitVorzeichen(this);
    }

    /**
     * Hiermit kann man einen Betrag mit angrenzendem Vorzeichen zu
     * {@link BetragMitVorzeichen} zusammenfassen
     *
     * @param betrag     der Betrag
     * @param vorzeichen das Vorzeichen
     * @return Betrag mit Vorzeichen
     * @since 5.0
     */
    public static BetragMitVorzeichen of(NumFeld betrag, AlphaNumFeld vorzeichen) {
        if (betrag.getEndAdresse() + 1 != vorzeichen.getByteAdresse()) {
            throw new IllegalArgumentException("gap between " + betrag + " and " + vorzeichen + " (cannot merge)");
        }
        BetragMitVorzeichen bmv = new BetragMitVorzeichen(Bezeichner.of(betrag.getBezeichnung() + " mit Vorzeichen"),
                betrag.getAnzahlBytes() + 1, betrag.getByteAdresse());
        bmv.setInhalt(betrag.getInhalt() + vorzeichen.getInhalt());
        return bmv;
    }



    /**
     * Die Validierung von Werten wurde jetzt in einer eingenen Validator-
     * Klasse zusammengefasst. Damit kann die Validierung auch unabhaengig
     * von NumFeld-Klasse im Vorfeld eingesetzt werden, um Werte auf ihre
     * Gueltigkeit pruefen zu koennen.
     *
     * @since 5.3
     */
    public static class Validator extends NumFeld.Validator {

        public Validator() {
            super();
        }

        public Validator(Config config) {
            super(config);
        }

        @Override
        protected String validateInhalt(String nummer) {
            LOG.debug("{} wird als Betrag mit Vorzeichen validiert.", nummer);
            if (StringUtils.isNotBlank(nummer)) {
                char vorzeichen = nummer.charAt(nummer.length() - 1);
                if ((vorzeichen != '+') && (vorzeichen != '-')) {
                    throw new ValidationException(String.format("'%s' hat falsches Vorzeichen ('%c')", nummer, vorzeichen));
                }
                super.validateInhalt(nummer.substring(0, nummer.length()-1));
            }
            return nummer;
        }

    }

}
