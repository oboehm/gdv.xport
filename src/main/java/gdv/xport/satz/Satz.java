/**
 *
 */
package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.SATZART;
import static patterntesting.runtime.NullConstants.NULL_STRING;
import gdv.xport.config.Config;
import gdv.xport.feld.*;

import java.io.*;
import java.util.*;

import net.sf.oval.*;
import net.sf.oval.constraint.AssertCheck;
import net.sf.oval.context.ClassContext;

import org.apache.commons.lang.*;
import org.apache.commons.logging.*;

/**
 * Die Satz-Klasse ist die oberste Klasse, von der alle weiteren Saetze
 * abgeleitet sind.
 *
 * @author oliver
 */
public abstract class Satz {

    private static final Log log = LogFactory.getLog(Satz.class);
    /** 4 Zeichen. */
    private final NumFeld satzart = new NumFeld(SATZART, 4, 1);
    /** Teildatensaetze. */
    protected Teildatensatz[] teildatensatz;

    protected Satz(final int art) {
        this(art, 1);
    }

    protected Satz(final String art) {
        this(art, (art.length() + 255) / 256);
    }

    protected Satz(final NumFeld art) {
        this(art.getInhalt());
    }

    /**
     * Instantiates a new satz.
     *
     * @param art the art
     * @param n the n
     */
    public Satz(final NumFeld art, final int n) {
        this.satzart.setInhalt(art.getInhalt());
        this.createTeildatensaetze(n);
    }

    /**
     * Instantiates a new satz.
     *
     * @param content the content
     * @param n the n
     */
    public Satz(final String content, final int n) {
        this.satzart.setInhalt(content.substring(0, 4));
        this.createTeildatensaetze(n);
        if (content.length() > 4) {
            try {
                this.importFrom(content);
            } catch (IOException ioe) {
                throw new IllegalArgumentException("1st argument too short", ioe);
            }
        }
    }

    /**
     * The Constructor.
     *
     * @param art z.B. 100 (f. Adressteil)
     * @param n Anzahl der Teildatensaetze
     */
    public Satz(final int art, final int n) {
        this.satzart.setInhalt(art);
        this.createTeildatensaetze(n);
    }

    protected void createTeildatensaetze(final int n) {
        teildatensatz = new Teildatensatz[n];
        for (int i = 0; i < n; i++) {
            teildatensatz[i] = new Teildatensatz(satzart, i+1);
        }
    }

    /**
     * Liefert alle Teildatensaetze zurueck.
     *
     * @return Teildatensaetze
     *
     * @since 0.2
     */
    public final Collection<Teildatensatz> getTeildatensaetze() {
        return Arrays.asList(this.teildatensatz);
    }

    /**
     * Liefert den n-ten Teildatensatz zurueck.
     *
     * @param n Nummer des Teildatensatzes (beginnend mit 1)
     *
     * @return the teildatensatz
     *
     * @since 0.2
     */
    public final Teildatensatz getTeildatensatz(final int n) {
        return this.teildatensatz[n-1];
    }

    /**
     * Hiermit koennen Unterklassen alle Teildatensaetze wieder entfernen
     * (wird z.B. vom Satz 0220.030 benoetigt).
     *
     * @since 0.4
     */
    public final void removeAllTeildatensaetze() {
        this.teildatensatz = new Teildatensatz[0];
    }

    /**
     * Entfernt alle Teildatensaetze >= n.
     *
     * @since 0.4
     * @param n ab hier wird abgeschnitten (n >= 1)
     */
    public final void removeAllTeildatensaetze(final int n) {
        if ((n < 1) || (n > this.teildatensatz.length)) {
            throw new IllegalArgumentException(n + " liegt nicht zwischen 1 und "
                    + this.teildatensatz.length);
        }
        this.teildatensatz = (Teildatensatz[]) ArrayUtils.subarray(this.teildatensatz, 0, n-1);
    }

    /**
     * Entfernt den gewuenschten Teildatensatz.
     * Ein neuer Teildatensatz kann ueber add() hinzugefuegt werden.
     *
     * @see #add(Teildatensatz)
     * @since 0.4
     * @param n der gewuenschte Teildatensatz (beginnend bei 1)
     */
    public final void removeTeildatensatz(final int n) {
        if ((n < 1) || (n > this.teildatensatz.length)) {
            throw new IllegalArgumentException(n + " liegt nicht zwischen 1 und "
                    + this.teildatensatz.length);
        }
        this.teildatensatz = (Teildatensatz[]) ArrayUtils.remove(this.teildatensatz, n-1);
    }

    /**
     * Und hierueber kann ein Teildatensatz hinzugefuegt werden.
     *
     * @since 0.4
     * @param tds der neue (gefuellte) Teildatensatz
     */
    public final void add(final Teildatensatz tds) {
        this.teildatensatz = (Teildatensatz[]) ArrayUtils.add(this.teildatensatz, tds);
    }

    /**
     * Fuegt das uebergebene Feld zur Liste der Datenfelder hinzu.
     *
     * @param feld the feld
     */
    public void add(final Feld feld) {
        this.add(feld, 1);
    }

    /**
     * Adds the.
     *
     * @param feld the feld
     * @param teildatensatzNr the teildatensatz nr
     */
    public void add(final Feld feld, final int teildatensatzNr) {
        if (feld.getByteAdresse() > 256) {
            throw new IllegalArgumentException(feld + " ueberschreitet Teildatensatz-Grenze");
        }
        this.teildatensatz[teildatensatzNr-1].add(feld);
    }

    /**
     * Fuellt fuer alle leeren Stellen ein entsprechendes Fuellfeld auf.
     */
    public void addFiller() {
        throw new UnsupportedOperationException("not yet implemented");
    }
    
    /**
     * Falls ein Feld zuviel gesetzt wurde, kann es mit 'remove" wieder
     * entfernt werden.
     *
     * @param name Name des Feldes
     */
    public void remove(final String name) {
        for (int i = 0; i < this.teildatensatz.length; i++) {
            this.teildatensatz[i].remove(name);
        }
    }

    /**
     * Setzt das angegebene Feld in allen Teildatensaetzen, in denen es
     * gefunden wird. Normalerweise braeuchten wir eigentlich nur die erste
     * Fundstelle setzen, da die anderen Teildatensaetze (hoffentlich) auf
     * die gleiche Referenz verweisen - aber sicher ist sicher.
     * Falls das Feld nicht gefunden wird, wird eine IllegalArgumentException
     * geworfen.
     *
     * @param name Name des Felds (Bezeichnung)
     * @param value the value
     */
    public final void set(final String name, final String value) {
        boolean found = false;
        for (int i = 0; i < teildatensatz.length; i++) {
            Feld x = teildatensatz[i].getFeld(name);
            if (x != Feld.NULL_FELD) {
                x.setInhalt(value);
                found = true;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Feld \"" + name +"\" not found");
        }
    }

    /**
     * Liefert den Inhalt des gewuenschten Feldes.
     *
     * @param name gesuchtes Feld
     *
     * @return Inhalt des gefundenden Felds
     * (NULL_STRING, falls 'name' nicht gefunden wurde)
     */
    public final String get(final String name) {
        Feld f = getFeld(name);
        if (f == Feld.NULL_FELD) {
            return NULL_STRING;
        } else {
            return f.getInhalt();
        }
    }

    /**
     * Liefert das gewuenschte Feld.
     *
     * @param name gewuenschter Bezeichner des Feldes
     * @return das gesuchte Feld
     * @throws IllegalArgumentException falls es das Feld nicht gibt
     */
    public Feld getFeld(final String name) throws IllegalArgumentException {
        for (int i = 0; i < teildatensatz.length; i++) {
            try {
                Feld x = teildatensatz[i].getFeld(name);
                if (x != Feld.NULL_FELD) {
                    return x;
                }
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        throw new IllegalArgumentException("Feld \"" + name + "\" nicht in "
                + this.toShortString() + " vorhanden!");
    }

    /**
     * Liefert den Inhalt des gewuenschten Feldes.
     *
     * @since 0.3
     * @param name gewuenschter Bezeichner des Feldes
     * @return Inhalt des Feldes (getrimmt, d.h. ohne Leerzeichen am Ende)
     */
    public final String getFeldInhalt(final String name) {
        return this.getFeld(name).getInhalt().trim();
    }

    /**
     * Liefert das gewuenschte Feld.
     *
     * @param name gewuenschter Bezeichner des Feldes
     * @param nr Nummer des Teildatensatzes (1, 2, ...)
     * @return NULL_FELD, falls das angegebene Feld nicht gefunden wird
     * @since 0.2
     */
    public final Feld getFeld(final String name, final int nr) {
        assert (0 < nr) &&  (nr <= teildatensatz.length) : nr + " liegt ausserhalb des Bereichs";
        return teildatensatz[nr-1].getFeld(name);
    }

    /**
     * Liefert den Inhalt des gewuenschten Feldes.
     *
     * @param name gewuenschter Bezeichner des Feldes
     * @param nr Nummer des Teildatensatzes (1, 2, ...)
     * @return Inhalt des Feldes (getrimmt, d.h. ohne Leerzeichen am Ende)
     * @since 0.3
     */
    public final String getFeldInhalt(final String name, final int nr) {
        return this.getFeld(name, nr).getInhalt().trim();
    }

    /**
     * Liefert die Satzart.
     *
     * @return the satzart
     */
    public final NumFeld getSatzartFeld() {
        return this.satzart;
    }

    /**
     * @since 0.3
     * @return die Satzart als int
     */
    public final int getSatzart() {
        return this.satzart.toInt();
    }

    /**
     * Export.
     *
     * @param writer the writer
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void export(final Writer writer) throws IOException {
        for (int i = 0; i < teildatensatz.length; i++) {
            teildatensatz[i].export(writer);
        }
    }

    /**
     * @since 0.3
     * @param ostream z.B. System.out
     * @throws IOException falls mal was schief geht
     */
    public void export(final OutputStream ostream) throws IOException {
        Writer writer = new OutputStreamWriter(ostream);
        export(writer);
        writer.flush();
        ostream.flush();
    }

    /**
     * Eigentlich wollte ich ja diese Methode "import" nennen, aber das
     * kollidiert leider mit dem Schluesselwort "import" in Java.
     * Inzwischen beruecksichtigt diese Import-Methode auch zusaetzlich
     * eingestreute Newlines ("\n") oder/und Wagenruecklaeufe ("\r").
     *
     * @param s the s
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void importFrom(final String s) throws IOException {
        int satzlength = getSatzlength(s);
        for (int i = 0; i < teildatensatz.length; i++) {
            String input = s.substring(i * satzlength);
            if (input.trim().isEmpty()) {
                log.info("mehr Daten fuer Satz " + this.getSatzart() + " erwartet, aber nur "
                        + i + " Teildatensaetze vorgefunden");
                this.removeAllTeildatensaetze(i+1);
                break;
            }
            teildatensatz[i].importFrom(input);
        }
    }

    /**
     * Ermittelt die Satzlaenge.
     * Je nachdem, ob das Zeilenende aus keinem, einem oder zwei Zeichen
     * besteht, wird 256, 257 oder 258 zurueckgegeben.
     *
     * @since 0.4
     * @param s der komplette Satz
     * @return 256, 257 oder 258
     */
    protected final int getSatzlength(final String s) {
        int satzlength = 256;
        try {
            char c256 = s.charAt(256);
            if ((c256 == '\n') || (c256 == '\r')) {
                satzlength = 257;
            }
            char c257 = s.charAt(257);
            if ((c257 == '\n') || (c257 == '\r')) {
                satzlength = 258;
            }
        } catch (StringIndexOutOfBoundsException e) {
            log.trace("end of string \"" + s + "\" reached", e);
        }
        return satzlength;
    }

    /**
     * Import from.
     *
     * @param istream the istream
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public final void importFrom(final InputStream istream) throws IOException {
        importFrom(new InputStreamReader(istream, Config.DEFAULT_ENCODING));
    }

    /**
     * Import from.
     *
     * @param reader the reader
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public final void importFrom(final Reader reader) throws IOException {
        importFrom(new PushbackReader(reader, 4));
    }

    /**
     * Import from.
     *
     * @param reader the reader
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public final void importFrom(final PushbackReader reader) throws IOException {
        char[] cbuf = new char[257 * teildatensatz.length];
        for (int i = 0; i < teildatensatz.length; i++) {
            int art = readSatzart(reader);
            if (art != this.getSatzart()) {
                log.info((teildatensatz.length - i) + " more Teildatensaetze expected for " + this
                        + ", but got data for Satzart " + art);
                break;
            }
            importFrom(reader, cbuf, i*257);
            cbuf[i*257+256] = '\n';
            skipNewline(reader);
        }
        importFrom(new String(cbuf));
    }

    private static void importFrom(final Reader reader, final char[]cbuf, final int i) throws IOException {
        if (reader.read(cbuf, i, 256) == -1) {
            String s = new String(cbuf).substring(i);
            throw new IOException("can't read 256 bytes from " + reader + ", only " + s.length()
                    + " bytes: " + s);
        }
    }

    /**
     * Liest 4 Bytes, um die Satzart zu bestimmen und stellt die Bytes
     * anschliessend wieder zurueck in den Reader.
     *
     * @param reader the reader
     *
     * @return Satzart
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static int readSatzart(final PushbackReader reader) throws IOException {
        char[] cbuf = new char[4];
        importFrom(reader, cbuf);
        reader.unread(cbuf);
        return Integer.parseInt(new String(cbuf));
    }

    private static void importFrom(final Reader reader, final char[]cbuf) throws IOException {
        if (reader.read(cbuf) == -1) {
            String s = new String(cbuf);
            throw new IOException("can't read " + cbuf.length + " bytes from " + reader + ", only "
                    + s.length() + " bytes: " + s);
        }
    }

    private static void skipNewline(final PushbackReader reader) throws IOException {
        char[] cbuf = new char[1];
        do {
            if (reader.read(cbuf) == -1) {
                log.info("end of file detected");
                return;
            }
        } while ((cbuf[0] == '\n') || (cbuf[0] == '\r'));
        reader.unread(cbuf);
    }

    /**
     * Aus Performance-Gruenden stuetzt sich diese Methode nicht auf die
     * validate()-Methode ab.
     *
     * @return true/false
     */
    public boolean isValid() {
        if (!this.satzart.isValid()) {
            return false;
        }
        if (this.teildatensatz != null) {
            for (int i = 0; i < teildatensatz.length; i++) {
                if (!teildatensatz[i].isValid()) {
                    log.info("Teildatensatz " + (i+1) + " is invalid");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Validiert die einzelnen Teildatensaetze.
     *
     * @return the list< constraint violation>
     */
    public List<ConstraintViolation> validate() {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(this);
        if (!this.satzart.isValid()) {
            ConstraintViolation cv = new ConstraintViolation(new AssertCheck(), "invalid Satzart "
                    + this.satzart.getInhalt(), this, this.satzart, new ClassContext(this
                    .getClass()));
            violations.add(cv);
        }
        if (this.teildatensatz != null) {
            for (int i = 0; i < teildatensatz.length; i++) {
                violations.addAll(teildatensatz[i].validate());
            }
        }
        return violations;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
        try {
            return "Satzart " + this.satzart.getInhalt() + " ("
                    + this.toLongString().substring(0, 60) + "...)";
        } catch (RuntimeException shouldNeverHappen) {
            log.error("shit happens in toString()", shouldNeverHappen);
            return super.toString();
        }
    }

    /**
     * To short string.
     *
     * @return the string
     */
    public String toShortString() {
        return "Satzart " + this.satzart.getInhalt();
    }

    /**
     * To long string.
     *
     * @return the string
     */
    public String toLongString() {
        StringWriter swriter = new StringWriter();
        try {
            this.export(swriter);
        } catch (IOException canthappen) {
            log.warn(canthappen + " ignored", canthappen);
            swriter.write(canthappen.getLocalizedMessage());
        }
        return swriter.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        try {
            return this.equals((Satz) other);
        } catch (ClassCastException cce) {
            return false;
        }
    }

    /**
     * Equals.
     *
     * @param other the other
     *
     * @return true, if successful
     */
    public final boolean equals(final Satz other) {
        if (other == null) {
            return false;
        }
        if (this.getSatzart() != other.getSatzart()) {
            return false;
        }
        for (int i = 0; i < teildatensatz.length; i++) {
            if (!this.teildatensatz[i].equals(other.teildatensatz[i])) {
                return false;
            }
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.toLongString().hashCode();
    }

}
