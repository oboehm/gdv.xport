/*
 * $Id$
 *
 * Copyright (c) 2009 by agentes
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
 * (c)reated 04.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.feld;

import java.io.*;
import java.util.List;

import net.sf.oval.*;
import net.sf.oval.constraint.*;
import net.sf.oval.context.ClassContext;

import org.apache.commons.lang.StringUtils;

/**
 * The Class Feld.
 * 
 * @author oliver
 * @since 04.10.2009
 */
public class Feld implements Comparable<Feld> {

    /** statt "null". */
    public static final Feld NULL_FELD = new Feld("null", 0, 0, Align.UNKNOWN);
    /** optional: Name des Felds. */
    private final String bezeichnung;
    private final StringBuffer inhalt;
    /** Achtung - die ByteAdresse beginnt bei 1 und geht bis 256. */
    @Min(1)
    private final int byteAdresse;
    /** Ausrichtung: rechts- oder linksbuendig. */
    @NotEqual("UNKNOWN")
    private final Align ausrichtung;

    /**
     * Instantiates a new feld.
     * 
     * @param name
     *            the name
     * @param s
     *            the s
     * @param alignment
     *            the alignment
     */
    public Feld(final String name, final String s, final Align alignment) {
        this(name, 1, s, alignment);
    }

    /**
     * Instantiates a new feld.
     * 
     * @param name
     *            the name
     * @param start
     *            the start
     * @param s
     *            the s
     * @param alignment
     *            the alignment
     */
    public Feld(final String name, final int start, final String s, final Align alignment) {
        this.bezeichnung = name;
        this.inhalt = new StringBuffer(s);
        this.byteAdresse = start;
        this.ausrichtung = alignment;
    }

    /**
     * Instantiates a new feld.
     * 
     * @param name
     *            the name
     * @param length
     *            the length
     * @param start
     *            the start
     * @param alignment
     *            the alignment
     */
    public Feld(final String name, final int length, final int start, final Align alignment) {
        this.bezeichnung = name;
        this.inhalt = getEmptyStringBuffer(length);
        this.byteAdresse = start;
        this.ausrichtung = alignment;
    }

    /**
     * Instantiates a new feld.
     * 
     * @param name
     *            the name
     * @param length
     *            the length
     * @param start
     *            the start
     * @param c
     *            the c
     * @param alignment
     *            the alignment
     */
    public Feld(final String name, final int length, final int start, final char c,
            final Align alignment) {
        this(name, length, start, alignment);
        this.setInhalt(c);
    }

    /**
     * Instantiates a new feld.
     * 
     * @param name
     *            the name
     * @param length
     *            the length
     * @param start
     *            the start
     * @param s
     *            the s
     * @param alignment
     *            the alignment
     */
    public Feld(final String name, final int length, final int start, final String s,
            final Align alignment) {
        this(name, length, start, alignment);
        this.setInhalt(s);
    }

    /**
     * Instantiates a new feld.
     * 
     * @param name
     *            the name
     * @param start
     *            the start
     * @param c
     *            the c
     */
    public Feld(final String name, final int start, final char c) {
        this(name, 1, start, c, Align.LEFT);
    }

    /**
     * Instantiates a new feld.
     * 
     * @param start
     *            the start
     * @param s
     *            the s
     * @param alignment
     *            the alignment
     */
    public Feld(final int start, final String s, final Align alignment) {
        this.inhalt = new StringBuffer(s);
        this.byteAdresse = start;
        this.ausrichtung = alignment;
        this.bezeichnung = createBezeichnung();
    }

    /**
     * Instantiates a new feld.
     * 
     * @param length
     *            the length
     * @param alignment
     *            the alignment
     */
    public Feld(final int length, final Align alignment) {
        this(length, 1, alignment);
    }

    /**
     * Instantiates a new feld.
     * 
     * @param length
     *            the length
     * @param start
     *            the start
     * @param alignment
     *            the alignment
     */
    public Feld(final int length, final int start, final Align alignment) {
        this.inhalt = getEmptyStringBuffer(length);
        this.byteAdresse = start;
        this.ausrichtung = alignment;
        this.bezeichnung = createBezeichnung();
    }

    private static StringBuffer getEmptyStringBuffer(final int length) {
        StringBuffer sbuf = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            sbuf.append(' ');
        }
        return sbuf;
    }

    private String createBezeichnung() {
        return this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode());
    }

    /**
     * Gets the bezeichnung.
     * 
     * @return the bezeichnung
     */
    public String getBezeichnung() {
        return this.bezeichnung;
    }

    /**
     * Sets the inhalt.
     * 
     * @param s
     *            the new inhalt
     */
    public void setInhalt(final String s) {
        int anzahlBytes = this.getAnzahlBytes();
        if (s.length() > anzahlBytes) {
            throw new IllegalArgumentException("Parameter (\"" + s + "\" ist laenger als "
                    + anzahlBytes + " Zeichen!");
        }
        this.resetInhalt();
        switch (this.ausrichtung) {
            case LEFT:
                this.inhalt.replace(0, s.length(), s);
                break;
            case RIGHT:
                int l = s.length();
                int start = anzahlBytes - l;
                this.inhalt.replace(start, start + l, s);
                break;
            default:
                throw new IllegalStateException("object was not properly initialized");
        }
    }

    /**
     * Sets the inhalt.
     * 
     * @param c
     *            the new inhalt
     */
    public void setInhalt(final char c) {
        this.resetInhalt();
        this.setInhalt(c, 0);
    }

    /**
     * Sets the inhalt.
     * 
     * @param c
     *            zu setzendes Zeichen
     * @param i
     *            index, beginnend bei 0
     */
    public void setInhalt(final char c, final int i) {
        this.inhalt.setCharAt(i, c);
    }

    /**
     * Gets the inhalt.
     * 
     * @return the inhalt
     */
    public String getInhalt() {
        return this.inhalt.toString();
    }

    /**
     * Reset inhalt.
     */
    public void resetInhalt() {
        int anzahlBytes = this.getAnzahlBytes();
        for (int i = 0; i < anzahlBytes; i++) {
            this.inhalt.setCharAt(i, ' ');
        }
    }

    /**
     * Wenn sich das Feld vergroessert, werden rechts Leerzeichen aufgefuellt
     * (alphanumerische Zeichen sind standardmaessig linksbuendig).
     *
     * @param n neue Groesse
     */
    public void setAnzahlBytes(int n) {
        assert this.inhalt.length() <= n : "drohender Datenverlust";
        for (int i = this.inhalt.length(); i < n; i++) {
            this.inhalt.append(' ');
        }
    }

    /**
     * Gets the anzahl bytes.
     * 
     * @return the anzahl bytes
     */
    public int getAnzahlBytes() {
        return this.inhalt.length();
    }

    /**
     * Gets the byte adresse.
     * 
     * @return Byte-Adresse, beginnend bei 1
     */
    public int getByteAdresse() {
        return this.byteAdresse;
    }

    /**
     * Gets the end adresse.
     * 
     * @return absolute End-Adresse
     */
    public int getEndAdresse() {
        return this.byteAdresse + this.getAnzahlBytes() - 1;
    }

    /**
     * Overlaps with.
     * 
     * @param other
     *            das andere Feld
     * 
     * @return true, falls sich die Felder ueberlappen
     */
    public boolean overlapsWith(final Feld other) {
        if (this.byteAdresse == other.byteAdresse) {
            return false;
        }
        if (this.byteAdresse < other.byteAdresse) {
            return (this.getEndAdresse() >= other.byteAdresse);
        }
        return (other.getEndAdresse() >= this.byteAdresse);
    }

    /**
     * Write.
     * 
     * @param writer
     *            the writer
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void write(Writer writer) throws IOException {
        writer.write(this.inhalt.toString());
    }

    /**
     * Checks if is empty.
     * 
     * @return true, if is empty
     */
    public boolean isEmpty() {
        return StringUtils.isBlank(this.getInhalt());
    }

    /**
     * Valid bedeutet: Byte-Adresse >= 1, Feld geht nicht ueber
     * (Teil-)Datensatz-Grenze hinaus, Ausrichtung ist bekannt. <br/>
     * Auf Performance-Gruenden wurde diese Methode stuetzt sich diese Methode
     * nicht auf validate(), sondern implementiert die entsprechenden Abfragen
     * selbst.
     * 
     * @return false, falls Verletzung erkannt wird
     * 
     * @since 0.1.0
     */
    public boolean isValid() {
        // return this.validate().isEmpty();
        if (this.getByteAdresse() < 1) {
            return false;
        }
        if (this.getEndAdresse() > 256) {
            return false;
        }
        if (this.ausrichtung == Align.UNKNOWN) {
            return false;
        }
        return true;
    }

    /**
     * Checks if is invalid.
     * 
     * @return true, if is invalid
     */
    public boolean isInvalid() {
        return !isValid();
    }

    /**
     * Validate.
     * 
     * @return the list< constraint violation>
     */
    public List<ConstraintViolation> validate() {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(this);
        if (this.getEndAdresse() > 256) {
            ConstraintViolation cv = new ConstraintViolation(new SizeCheck(), this
                    + ": boundary exceeded", this, this.getEndAdresse(), new ClassContext(this
                    .getClass()));
            violations.add(cv);
        }
        return violations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.bezeichnung + "(" + this.byteAdresse
                + "-" + this.getEndAdresse() + "): \"" + this.getInhalt().trim() + "\"";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        }
        try {
            return this.equals((Feld) other);
        } catch (ClassCastException cce) {
            return false;
        }
    }

    /**
     * Equals.
     * 
     * @param other
     *            the other
     * 
     * @return true, if successful
     */
    public boolean equals(final Feld other) {
        return this.bezeichnung.equals(other.bezeichnung)
                && this.getInhalt().equals(other.getInhalt())
                && (this.byteAdresse == other.byteAdresse) && this.ausrichtung == other.ausrichtung;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.byteAdresse + this.getInhalt().hashCode();
    }

    /**
     * Es gilt fuer Feld a und b: a < b, wenn die Start-Adresse von a vor b
     * liegt.
     * 
     * @param other
     *            das andere Feld
     * 
     * @return 0 wenn beide Felder die gleiche Startadresse haben
     */
    public int compareTo(final Feld other) {
        return this.byteAdresse - other.byteAdresse;
    }

}
