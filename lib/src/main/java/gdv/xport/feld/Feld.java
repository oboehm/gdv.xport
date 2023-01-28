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
 * (c)reated 04.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.jfachwert.SimpleValidator;
import de.jfachwert.Text;
import gdv.xport.config.Config;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ValidationException;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Feld-Klasse bezieht ihre Information hauptsaechlich aus Enum-Klassen wie
 * Feld100 oder Feld1bis7, die mit Annotationen versehen sind.
 *
 * @author oliver
 * @since 04.10.2009
 */
public class Feld implements Comparable<Feld>, Cloneable, Serializable {

    private static final Logger LOG = LogManager.getLogger(Feld.class);
    private static final Validator DEFAULT_VALIDATOR = new Validator(Config.getInstance());
    /** statt "null". */
    public static final Feld NULL_FELD = new Feld();
    private final Bezeichner bezeichner;
    protected String inhalt;
    private final byte byteAdresse;
    private final byte length;
    private byte ausrichtung;
    protected final Config config;

    /**
     * Legt ein neues Feld an. Dieser Default-Konstruktor ist fuer Unterklassen
     * vorgesehen. Da er aber auch fuer die {@link Cloneable}-Implementierung
     * benoetigt wird, ist er 'public'.
     *
     * @since 1.0
     */
    public Feld() {
        this(Bezeichner.UNBEKANNT, 213, 43, Align.LEFT);
    }

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
        this(Bezeichner.of(name), 1, s, alignment);
    }

    /**
     * Liefert eine neues Feld mit neuer Konfiguration
     *
     * @param c neue Konfiguration
     * @return neues NumFeld
     * @since 5.3
     */
    public Feld mitConfig(Config c) {
        return new Feld(this, c);
    }

    /**
     * Erzeugt ein neues Feld.
     *
     * @param name  Name
     * @param start Start-Adresse
     * @param s der Inhalt
     * @param alignment the alignment
     */
    public Feld(final Bezeichner name, final int start, final String s, final Align alignment) {
        this.bezeichner = name;
        this.inhalt = s;
        this.length = toByteLength(s.length());
        this.byteAdresse = ByteAdresse.of(start).byteValue();
        this.ausrichtung = alignment.getCode();
        this.config = Config.getInstance();
    }

    private static byte toByteLength(int n) {
        if (n > 0) {
            return ByteAdresse.of(n).byteValue();
        } else {
            return ByteAdresse.of(1).byteValue();
        }
    }

    /**
     * Erzeugt ein neues Feld.
     *
     * @param bezeichner der Name des Felds
     * @param length die Anzahl der Bytes
     * @param start die Start-Adresse
     * @param alignment die Ausrichtung
     * @since 1.0
     */
    public Feld(final Bezeichner bezeichner, final int length, final int start, final Align alignment) {
        this(bezeichner, length, start, alignment, Config.getInstance());
    }

    protected Feld(Bezeichner bezeichner, int length, int start, Align alignment, Config config) {
        this.bezeichner = bezeichner;
        this.inhalt = StringUtils.repeat(" ", length);
        this.length = toByteLength(length);
        this.byteAdresse = ByteAdresse.of(start).byteValue();
        this.ausrichtung = alignment.getCode();
        this.config = config;
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
    public Feld(final String name, final int length, final int start, final char c, final Align alignment) {
        this(Bezeichner.of(name), length, start, alignment);
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
    public Feld(final String name, final int length, final int start, final String s, final Align alignment) {
        this(Bezeichner.of(name), length, start, alignment);
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
        this.inhalt = s;
        this.length = toByteLength(s.length());
        this.byteAdresse = ByteAdresse.of(start).byteValue();
        this.ausrichtung = alignment.getCode();
        this.bezeichner = createBezeichner();
        this.config = Config.getInstance();
    }

    /**
     * Instantiates a new feld.
     * <p>
     * TODO: wird mit v7 entfernt
     * </p>
     *
     * @param length
     *            the length
     * @param alignment
     *            the alignment
     * @deprecated Felder ohne Bezeichner werden ab v7 nicht mehr unterstuetzt
     */
    @Deprecated
    public Feld(final int length, final Align alignment) {
        this(length, 1, alignment);
    }

    /**
     * Instantiates a new feld.
     * <p>
     * TODO: wird mit v7 entfernt
     * </p>
     *
     * @param length
     *            the length
     * @param start
     *            the start
     * @param alignment
     *            the alignment
     * @deprecated Felder ohne Bezeichner werden ab v7 nicht mehr unterstuetzt
     */
    @Deprecated
    public Feld(final int length, final int start, final Align alignment) {
        this.inhalt = StringUtils.repeat(" ", length);
        this.length = toByteLength(length);
        this.byteAdresse = ByteAdresse.of(start).byteValue();
        this.ausrichtung = alignment.getCode();
        this.bezeichner = createBezeichner();
        this.config = Config.getInstance();
    }

    /**
     * Dies ist der Copy-Constructor, mit dem man ein bestehendes Feld
     * kopieren kann.
     *
     * @param other das originale Feld
     */
    public Feld(final Feld other) {
        this(other.getBezeichner(), other.getAnzahlBytes(), other.getByteAdresse(), other.getAusrichtung(), other.config);
        this.setInhalt(other.getInhalt());
    }

    protected Feld(final Feld other, final Config cfg) {
        this(other.getBezeichner(), other.getAnzahlBytes(), other.getByteAdresse(), other.getAusrichtung(), cfg);
        this.setInhalt(other.getInhalt());
    }

    /**
     * Liefert die Ausrichtung eines Feldes. Dies ist hauptsaechlich fuer
     * alhpanumerische Felder interessant.
     *
     * @return linksbuendig oder rechtsbuendig
     */
    public Align getAusrichtung() {
        return Align.of(ausrichtung);
    }

    /**
     * Die Default-Ausrichtung ist links-buendig. Diese Vorgabe kann aber von den Unterklassen ueberschrieben werde.
     * <p>
     * TODO: wird mit v7 entfernt
     * </p>
     *
     * @return links-buendig
     * @deprecated
     */
    @Deprecated
    protected Align getDefaultAlignment() {
        return Align.LEFT;
    }

    /**
     * Hiermit kann man die Ausrichtung umstellen.
     *
     * @param alignment z.B. {@link Align#LEFT}
     * @since 6.1
     */
    public void setAusrichtung(Align alignment) {
        this.ausrichtung = alignment.getCode();
    }

    private Bezeichner createBezeichner() {
        return Bezeichner.of(this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode()));
    }

    /**
     * Gets the bezeichnung.
     *
     * @return the bezeichnung
     */
    public String getBezeichnung() {
        return this.bezeichner.getName();
    }

    /**
     * Liefert den Bezeichner eines Feldes zurueck.
     * <p>
     * Vor 1.0 lieferte diese Methode einen "String" zurueck. Aus
     * Konsistenz-Gruenden wurde die alte Implementierung in
     * "GetBzeichnerAsString" umbenannt.
     * </p>
     *
     * @return den Bezeichner des Feldes
     * @since 1.0
     */
    public Bezeichner getBezeichner() {
        return this.bezeichner;
    }

    /**
     * Setzt den Inhalt. Hierueber kann auch ein Inhalt gesetzt werden, der
     * nicht zum Datentyp passt (z.B. Buchstaben in einem {@link NumFeld},
     * damit ein Import nicht beim ersten fehlerhaften Feld abbricht.
     * <p>
     * Um festzustellen, ob ein Feld einen gueltigen Wert hat, kann die
     * {@link #isValid()}-Methode verwendet werden.
     * </p>
     *
     * @param neuerInhalt der neue Inhalt
     */
    public void setInhalt(final String neuerInhalt) {
        int anzahlBytes = this.getAnzahlBytes();
        String s = getValidator().verify(neuerInhalt, this);
        s = config.getBool("gdv.feld.truncate") ? truncate(s) : s;
        if (s.length() > anzahlBytes) {
            throw new IllegalArgumentException("Feld " + this.getBezeichner() + ": Parameter \"" + s
                    + "\" ist laenger als " + anzahlBytes + " Zeichen!");
        }
        if (s.length() != anzahlBytes) {
            this.resetInhalt();
        }
        this.inhalt = pack(s);
    }

    protected String pack(String s) {
        if (getAusrichtung() == Align.LEFT) {
            return StringUtils.stripEnd(s, " ");
        } else {
            return StringUtils.stripStart(s, " ");
        }
    }

    /**
     * Schneidet einen zu langen String unabhaengig vom Alignment rechts ab.
     *
     * @param s String, der evtl. gekuerzt wird
     * @return String der Laenge {@link #getAnzahlBytes()}
     */
    protected String truncate(String s) {
        if (s.length() <= getAnzahlBytes()) {
            return s;
        }
        return s.substring(0, getAnzahlBytes());
    }

    /**
     * Setzt den Inhalt mit der uebergebenen Zahl.
     *
     * @param n Zahl
     * @since 5.0
     */
    public void setInhalt(BigInteger n) {
        setInhalt(n.toString());
    }

    /**
     * Setzt den Inhalt aus der uebergebenen Zahl.
     *
     * @param n der neue Inhalt
     */
    public void setInhalt(final BigDecimal n) {
        this.setInhalt(n.toString());
    }

//    /**
//     * Setzt den Inhalt aus der uebergebenen Zahl.
//     *
//     * @param n der neue Inhalt
//     */
//    public void setInhalt(final int n) {
//        this.setInhalt((long) n);
//    }

    /**
     * Setzt den Inhalt aus der uebergebenen Zahl.
     *
     * @param n der neue Inhalt
     */
    public void setInhalt(final long n) {
        this.setInhalt(Long.toString(n));
    }

    /**
     * Sets the inhalt.
     *
     * @param c
     *            the new inhalt
     */
    @JsonIgnore
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
        StringBuilder sb = new StringBuilder(this.getInhalt());
        sb.setCharAt(i, c);
        this.inhalt = sb.toString();
    }

    /**
     * Liefert den Inhalt, so wie er im Record steht (ungetrimm't).
     *
     * @return den Inhalt
     */
    public String getInhalt() {
        String blanks = StringUtils.repeat(' ', this.getAnzahlBytes() - this.inhalt.length());
        if (getAusrichtung() == Align.LEFT) {
            return this.inhalt + blanks;
        } else {
            return blanks + inhalt;
        }
    }

    /**
     * Setzt das Feld und liefert es als Ergebnis zurueck.
     *
     * @param inhalt neuer Inhalt
     * @return das gesetzte Feld
     * @since 5.0
     */
    public Feld withInhalt(String inhalt) {
        this.setInhalt(inhalt);
        return this;
    }

    /**
     * Reset inhalt.
     */
    public void resetInhalt() {
        int anzahlBytes = this.getAnzahlBytes();
        this.inhalt = StringUtils.repeat(" ", anzahlBytes);
    }

    /**
     * Wenn sich das Feld vergroessert, werden rechts Leerzeichen aufgefuellt (alphanumerische Zeichen sind
     * standardmaessig linksbuendig).
     * <p>
     * TODO: wird in v7 entsorgt
     * </p>
     *
     * @param n neue Groesse
     * @deprecated wird nicht mehr benoetigt
     */
    @Deprecated
    public void setAnzahlBytes(final int n) {
        this.inhalt = this.inhalt + StringUtils.repeat(" ", n - this.inhalt.length());
    }

    /**
     * Gets the anzahl bytes.
     *
     * @return the anzahl bytes
     */
    public final int getAnzahlBytes() {
        return ByteAdresse.of(length).intValue();
    }

    /**
     * Gets the byte adresse.
     *
     * @return Byte-Adresse, beginnend bei 1
     */
    public final int getByteAdresse() {
        return ByteAdresse.of(this.byteAdresse).intValue();
    }

    /**
     * Gets the end adresse.
     *
     * @return absolute End-Adresse
     */
    public final int getEndAdresse() {
        return this.getByteAdresse() + this.getAnzahlBytes() - 1;
    }

    /**
     * Ueberprueft, ob sich zwei Felder mit unterschiedlichen Start-Adressen ueberlagern.
     *
     * @param other
     *            das andere Feld
     * @return true, falls sich die Felder ueberlappen
     */
    public final boolean overlapsWith(final Feld other) {
        if (this.byteAdresse == other.byteAdresse) {
            return false;
        }
        if (this.byteAdresse < other.byteAdresse) {
            return this.getEndAdresse() >= other.getByteAdresse();
        }
        return other.getEndAdresse() >= this.getByteAdresse();
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
    public final void write(final Writer writer) throws IOException {
        writer.write(this.inhalt);
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
     * Dient zum Ermittel, ob ein Werte schon gesetzt wurde. Dabei werden
     * typische Initialisierungswerte wie "0" als "nicht gesetzt"
     * interpretiert.
     *
     * @return true, falls Feld mit einem Wert belegt ist
     * @since 3.1
     */
    public boolean hasValue() {
        String value = getInhalt();
        return StringUtils.isNotBlank((this.getAusrichtung().compareTo(Align.RIGHT) == 0) ? StringUtils.replaceChars(value, '0', ' ') : value);
    }

    /**
     * Valid bedeutet: Byte-Adresse &gt;= 1, Feld geht nicht ueber
     * (Teil-)Datensatz-Grenze hinaus, Ausrichtung ist bekannt.
     * <p>
     * Aus Performance-Gruenden stuetzt sich diese Methode nicht direkt auf 
     * validate(), sondern implementiert die entsprechenden Abfragen selbst
     * und bricht ab, wenn er etwas ungueltiges findet.
     * </p>
     *
     * @return false, falls Verletzung erkannt wird
     * @since 0.1.0
     */
    public boolean isValid() {
        if (this.getByteAdresse() < 1) {
            return false;
        }
        if (this.getEndAdresse() > 256) {
            return false;
        }
        if (this.getAusrichtung() == Align.UNKNOWN) {
            return false;
        }
        return this.validate().isEmpty();
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
     * @return eine Liste mit Constraint-Verletzungen
     */
    public List<ConstraintViolation> validate() {
        return validate(Config.LAX);
    }

    public List<ConstraintViolation> validate(Config validationConfig) {
        List<ConstraintViolation> violations = validateInvariants();
        if (this.getEndAdresse() > 256) {
            ConstraintViolation cv = new SimpleConstraintViolation(this + ": Endadresse ueberschritten", this,
                    this.getEndAdresse());
            violations.add(cv);
        }
        try {
            this.getValidator().validate(getInhalt(), validationConfig);
        } catch (ValidationException ex) {
            ConstraintViolation cv = new SimpleConstraintViolation(this, ex);
            violations.add(cv);
        }
        return violations;
    }

    private List<ConstraintViolation> validateInvariants() {
        List<ConstraintViolation> violations = new ArrayList<>();
        if (getAusrichtung() == Align.UNKNOWN) {
            violations.add(new SimpleConstraintViolation("Ausrichtung darf nicht UNKNOWN sein", this,
                    this.ausrichtung));
        }
        return violations;
    }

    /**
     * Diese Methode ist dafuer vorgesehen, das Feld als normalen String ausgeben zu koennen. Zahlen koennen so z.B. in
     * der Form "123,45" ausgegeben werden, unter Beruecksichtigung der eingestellten "Locale".
     *
     * @return Inhalt des Feldes
     * @since 0.5.1
     */
    public String format() {
        return this.getInhalt();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return toShortString() + ": \"" + this.getInhalt() + "\"";
    }

    public String toShortString() {
        return this.getClass().getSimpleName() + " " + this.getBezeichner() + " (" + this.getByteAdresse() + "-"
                    + this.getEndAdresse() + ")";
    }

    /**
     * Zwei Felder sind gleich, wenn sie die gleiche Adresse und den gleichen
     * Inhalt haben.
     * <p>
     * ACHTUNG: Bis v5.1 wurde noch die Ausrichtung fuer die Gleichheit
     * herangezogen. Ab v5.1 spielt dies aber keine Rolle mehr.
     * </p>
     *
     * @param obj das andere Feld
     * @return true, wenn beide Felder gleich sind
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Feld)) {
            return false;
        }
        Feld other = (Feld) obj;
        return this.bezeichner.equals(other.bezeichner) && this.getInhalt().equals(other.getInhalt())
                && (this.byteAdresse == other.byteAdresse);
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
     * Es gilt fuer Feld a und b: a &lt; b, wenn die Start-Adresse von a vor b
     * liegt.
     *
     * @param other das andere Feld
     * @return 0 wenn beide Felder die gleiche Startadresse haben
     */
    @Override
    public final int compareTo(final Feld other) {
        return this.byteAdresse - other.byteAdresse;
    }

    /**
     * Hierueber kann der Validator zur Pruefung im Vorfeld
     * geholt werden.
     *
     * @return aktuellen Validator
     */
    @JsonIgnore
    public Feld.Validator getValidator() {
        return config.getValidatorFor(getClass());
    }

    /**
     * Die clone-Methode hat gegenueber dem CopyConstructor
     * {@link Feld#Feld(Feld)} den Vorteil, dass es den richtigen Typ fuer die
     * abgeleiteten Klassen zurueckliefert.
     *
     * @return eine Kopie
     */
    @SuppressWarnings("squid:S2975")
    @Override
    public Object clone() {
        return new Feld(this);
    }



    /**
     * Die Validierung von Werten wurde jetzt in einer eingenen Validator-
     * Klasse zusammengefasst. Damit kann die Validierung auch unabhaengig
     * von Feld-Klasse im Vorfeld eingesetzt werden, um Werte auf ihre
     * Gueltigkeit pruefen zu koennen.
     *
     * @since 5.3
     */
    public static class Validator implements SimpleValidator<String> {

        private final Config config;

        public Validator() {
            this(Config.LAX);
        }

        public Validator(Config config) {
            this.config = config;
        }

        protected Config getConfig() {
            return this.config;
        }

        /**
         * Im Gegensatzu zur validate-Methode wird hier eine
         * {@link IllegalArgumentException} ausgeloest und das
         * betroffene Feld noch mit ausgegeben
         *
         * @param value Wert, der validiert werden soll
         * @param validatedFeld Feld, das validiert wurde
         * @return der Wert selber zur Weiterverarbeitung
         */
        public String verify(String value, Feld validatedFeld) {
            try {
                return validate(value);
            } catch (ValidationException ex) {
                throw new IllegalArgumentException(
                        String.format("%s: Wert '%s' ist nicht erlaubt (%s)", validatedFeld.toShortString(), value, ex.getMessage()), ex);
            }
        }

        /**
         * Dieser validate-Methode validiert nur bei enstsprechender
         * Konfiguration.
         *
         * @param value Wert, der validiert werden soll
         * @return der Wert selber zur Weiterverarbeitung
         */
        @Override
        public String validate(String value) {
            return validate(value, config);
        }

        public String validate(String value, Config validationConfig) {
            if (value == null) {
                throw new ValidationException("null-Werte sind nicht erlaubt");
            }
            switch (validationConfig.getValidateMode()) {
                case LAX:
                    return validateLax(value);
                case STRICT:
                    return validateStrict(value);
                default:
                    return value;
            }
        }

        /**
         * Dieser validate-Methode bietet eine Basis-Validierung fuer die
         * Standard-Faelle.
         *
         * @param value Wert, der validiert werden soll
         * @return der Wert selber zur Weiterverarbeitung
         */
        protected String validateLax(String value) {
            LOG.debug("Inhalt von '{}' wird validiert.", value);
            for (char c : value.toCharArray()) {
                validateChar(value, c);
            }
            return value;
        }

        private void validateChar(String value, char c) {
            if (!Text.of(Character.toString(c)).isPrintable()) {
                throw new ValidationException(String.format("Text '%s' enthaelt ungueltige Zeichen '%c'", value, c));
            }
        }

        /**
         * Dieser validate-Methode validiert strenger und kann von Unterklassen
         * ueberschrieben werden.
         *
         * @param value Wert, der validiert werden soll
         * @return der Wert selber zur Weiterverarbeitung
         */
        protected String validateStrict(String value) {
            return validateLax(value);
        }

    }

}
