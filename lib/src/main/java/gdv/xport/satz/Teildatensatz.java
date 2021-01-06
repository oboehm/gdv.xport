/*
 * Copyright (c) 2009 - 2019 by Oli B.
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

package gdv.xport.satz;

import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.io.ImportException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.Map.Entry;

import static gdv.xport.feld.Bezeichner.SATZNUMMER;

/**
 * Ein Teildatensatz hat immer genau 256 Bytes. Dies wird beim Export
 * beruecksichtigt. Und ein Teildatensatz besteht aus mehreren Datenfeldern.
 *
 * @author ob@aosd.de
 * @since 04.10.2009
 */
public class Teildatensatz extends Satz {

    private static final Logger LOG = LogManager.getLogger(Teildatensatz.class);

    /** Diese Map dient fuer den Zugriff ueber den Namen. */
    private final Map<Bezeichner, Feld> datenfelder = new HashMap<>();

    /** Dieses Set dient zum Zugriff ueber die Nummer. */
    private final SortedSet<Feld> sortedFelder = new TreeSet<>();

    /** Dieses Feld brauchen wir, um die Satznummer abzuspeichern. */
    private Zeichen satznummer = new Zeichen(SATZNUMMER, 256);

    /**
     * Instantiiert einen neuen Teildatensatz mit der angegebenen Satzart.
     *
     * @param satzart z.B. 100
     */
    public Teildatensatz(final NumFeld satzart) {
        super(satzart, 0);
        this.satznummer.setInhalt(' ');
        this.initDatenfelder();
    }

    /**
     * Instantiiert einen neuen Teildatensatz mit der angegebenen Satzart und
     * Nummer.
     *
     * @param satzart z.B. 1 (Vorsatz)
     * @param nr Nummer des Teildatensatzes (zwischen 1 und 9)
     */
    public Teildatensatz(final NumFeld satzart, final int nr) {
        super(satzart, 0);
        initSatznummer(nr);
    }

    /**
     * Instantiiert einen neuen Teildatensatz mit der angegebenen Satzart
     * und Nummer.
     *
     * @param satzart z.B. 100
     * @param nr Nummer des Teildatensatzes (zwischen 1 und 9)
     */
    public Teildatensatz(final int satzart, final int nr) {
        super(satzart, 0);
        initSatznummer(nr);
    }

    /**
     * Instantiiert einen neuen Teildatensatz mit der angegebenen Satzart, Nummer
     * und Version des zugeheorigen Satzes.
     *
     * @param satz        z.B. 100
     * @param nr          Nummer des Teildatensatzes (zwischen 1 und 9)
     */
    public Teildatensatz(final Satz satz, final int nr) {
        super(satz, 0);
        initSatznummer(nr);
    }

    /**
     * Dies ist der Copy-Constructor, falls man eine Kopie eines Teildatensatzes
     * braucht.
     *
     * @param other der andere Teildatensatz
     */
    public Teildatensatz(final Teildatensatz other) {
        this(other.getSatzart(), other.getNummer());
        this.satznummer = other.satznummer;
        for (Entry<Bezeichner, Feld> entry : other.datenfelder.entrySet()) {
            Feld copy = (Feld) entry.getValue().clone();
            this.datenfelder.put(entry.getKey(), copy);
            this.sortedFelder.add(copy);
        }
    }
    
    private Teildatensatz(final int satzart, final Zeichen satznummer) {
        super(satzart, 0);
        this.satznummer = satznummer;
    }

    /**
     * Inits the satznummer.
     *
     * @param nr the nr
     */
    private void initSatznummer(final int nr) {
        if ((nr < 1) || (nr > 9)) {
            throw new IllegalArgumentException("Satznummer (" + nr
                    + ") muss zwischen 1 und 9 liegen");
        }
        this.satznummer.setInhalt(Character.forDigit(nr, 10));
        this.initDatenfelder();
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#createTeildatensaetze(int)
     */
    @Override
    protected void createTeildatensaetze(final int n) {
        assert n == 0 : "ein Teildatensatz hat keine weiteren Teildatensaetze";
    }

    private void initDatenfelder() {
        this.add(this.getSatzartFeld());
    }

    /**
     * Liefert die Satznummer zurueck.
     *
     * @return Satznummer als einzelnes Zeichen ('1' ... '9')
     * @since 0.2
     * @deprecated durch {@link #getSatznummer()} abgeloest
     */
    @Deprecated
    public Zeichen getNummer() {
        return this.getSatznummer();
    }

    /**
     * Liefert die Satznummer zurueck. Sie wurde aus Symmetriegruenden
     * zu {@link #setSatznummer(Zeichen)} eingefuehrt und loest die alte
     * getNummer()-Methode ab.
     *
     * @return Satznummer als einzelnes Zeichen ('1' ... '9')
     * @since 4.3
     */
    public Zeichen getSatznummer() {
        return this.satznummer;
    }

    /**
     * Da nicht alle Satzarten die Satznummer am Ende des Satzes haben, kann
     * man dies ueber diese Methode korrigieren.
     *
     * @param satznummer das neue Feld fuer die Satznummer
     * @since 3.2
     */
    public void setSatznummer(Zeichen satznummer) {
        String nr = this.satznummer.getInhalt();
        remove(Bezeichner.SATZNUMMER);
        this.satznummer = new Zeichen(satznummer);
        this.satznummer.setInhalt(nr);
        add(this.satznummer);
    }

    /**
     * Fuegt das angegebene Feld in den Teildatensatz ein.
     * Bei Einfuegen wird ueberprueft, ob es zu Ueberschneidungen mit
     * anderen Feldern kommt. Ausnahme hierbei ist das Satznummern-Feld
     * auf Byte 256, mit dem der Teildatensatz vorinitialisiert wurde.
     * Kommt es hier zu einer Ueberlappung, wird das Satznummern-Feld
     * entfernt, da nicht alle Saetze dieses Feld besitzen.
     *
     * @param feld Feld mit Name
     */
    @Override
    public void add(final Feld feld) {
        for (Feld f : datenfelder.values()) {
            if (LOG.isDebugEnabled() && f.getBezeichnung().startsWith("Satznummer")
                    && feld.getBezeichnung().startsWith("Satznummer")) {
                LOG.debug(f.getBezeichnung() + "(" + f.getBezeichner().getTechnischerName() + ") gefunden in "
                        + this + this.satznummer);
            }
            if (!feld.equals(f) && feld.overlapsWith(f)) {
                if (isSatznummer(f)) {
                    remove(f);
                    LOG.debug(f + " is removed from " + this);
                    break;
                } else {
                    throw new IllegalArgumentException("conflict: " + feld + " overlaps with " + f);
                }
            }
        }
        if (feld.getBezeichnung().startsWith("Satznummer")) {
            feld.setInhalt(this.satznummer.getInhalt());
        }
        if (feld.getBezeichnung().startsWith("Vorzeichen")) {
            LOG.debug("{}({}) einfuegen in {} +", feld.getBezeichnung(), feld.getBezeichner().getTechnischerName(), this);
            feld.setInhalt("+");
        }
        if (this.getSatzart() == 1 && feld.getBezeichner().getTechnischerName().equals("Satzart0001")) {
            LOG.debug("{}({}) einfuegen in {} {}}", feld.getBezeichnung(), feld.getBezeichner().getTechnischerName(),
                    this, this.getSatzversion());
            feld.setInhalt(this.getSatzversion().getInhalt());
        }
        datenfelder.put(feld.getBezeichner(), feld);
        if (!sortedFelder.add(feld)) {
            LOG.debug("Bezeichner {} schon vorhanden in {} {}.", feld.getBezeichner(), this, this.satznummer);
        }
    }

    /**
     * Checks if is satznummer.
     *
     * @param feld the feld
     * @return true, if is satznummer
     */
    private static boolean isSatznummer(final Feld feld) {
        if ((feld.getByteAdresse() == 256) && (feld.getAnzahlBytes() == 1)) {
            String bezeichnung = feld.getBezeichnung();
            return bezeichnung.length() <= 11 && bezeichnung.startsWith("Satznummer");
        }
        return false;
    }

    /**
     * Falls ein Feld zuviel gesetzt wurde, kann es mit 'remove" wieder
     * entfernt werden.
     *
     * @param feld das Feld, das entfernt werden soll
     */
    public void remove(final Feld feld) {
        this.remove(feld.getBezeichnung());
    }

    /**
     * Falls ein Feld zuviel gesetzt wurde, kann es mit 'remove" wieder entfernt
     * werden.
     *
     * @param bezeichner der Feld-Beezeichner
     * @since 1.0
     */
    @Override
    public void remove(final Bezeichner bezeichner) {
        Feld feld = this.datenfelder.get(bezeichner);
        if (feld != null) {
            this.datenfelder.remove(bezeichner);
            this.sortedFelder.remove(feld);
            LOG.debug("{} was removed from {}.", bezeichner, this);
        }
    }

    /**
     * Setzt das gewuenschte Feld. Falls es nicht vorhanden ist, wird analog
     * zur Oberklasse eine {@link IllegalArgumentException} geworfen.
     *
     * @param name der Name des Feldes
     * @param value der gewuenschte Werte als String
     * @see Satz#set(String, String)
     */
    @Override
    public void set(final Bezeichner name, final String value) {
        Feld x = this.getFeld(name);
        if (x == Feld.NULL_FELD) {
            throw new IllegalArgumentException("Feld \"" + name + "\" not found");
        }
        x.setInhalt(value);
    }

    /**
     * Liefert das gewuenschte Feld. Allerdings wird nur der Name des Feldes
     * benutzt, um das Feld zu bestimmen. Dazu werden auch die Konstanten in
     * {@link gdv.xport.feld.Bezeichner} verwendet.
     *
     * @param feldX gewuenschtes Feld-Element
     * @return das gesuchte Feld
     * @throws IllegalArgumentException falls es das Feld nicht gibt
     */
    @Override
    public Feld getFeld(final Enum feldX) throws IllegalArgumentException {
        return getFeld(Bezeichner.of(feldX));
    }

    /**
     * Liefert das gewuenschte Feld.
     *
     * @param bezeichner gewuenschter Bezeichner des Feldes
     * @return das gesuchte Feld
     */
    @Override
    public Feld getFeld(final Bezeichner bezeichner) {
        for (Bezeichner b : bezeichner.getVariants()) {
            Feld feld = datenfelder.get(b);
            if (feld != null) {
                return feld;
            }
        }
        return findFeld(bezeichner);
    }

    private Feld findFeld(final Bezeichner bezeichner) {
        for (Entry<Bezeichner, Feld> entry : datenfelder.entrySet()) {
            if (entry.getKey().getName().equals(bezeichner.getName())) {
                return entry.getValue();
            }
        }
        throw new IllegalArgumentException("Feld \"" + bezeichner + "\" nicht in " + this.toShortString()
                + " vorhanden!");
    }

    /**
     * Liefert das Feld mit der gewuenschten Nummer zurueck.
     *
     * @param nr z.B. 1
     * @return das Feld (z.B. mit der Satzart)
     */
    public Feld getFeld(final int nr) {
        return (Feld) sortedFelder.toArray()[nr -1];
    }

    /**
     * Liefert das Feld mit der angegebenen Byte-Adresse. Im Gegensatz zur
     * Nr. in {@link #getFeld(int)} aendert sich diese nicht, wenn neue
     * Elemente in einem Teildatensatz hinzukommen.
     *
     * @param adresse zwischen 1 und 256
     * @return das entsprechende Feld
     * @since 5.0
     */
    public Feld getFeld(final ByteAdresse adresse) {
        for (Feld f : getFelder()) {
            if (adresse.intValue() == f.getByteAdresse()) {
                return f;
            }
        }
        throw new IllegalArgumentException("invalid address " + adresse);
    }

    /**
     * Ueberprueft, ob das uebergebene Feld vorhanden ist.
     *
     * @param feldX the feld x
     * @return true, falls Feld vorhanden ist.
     * @since 0.9
     */
    public boolean hasFeld(final Enum feldX) {
        return this.hasFeld(new Bezeichner(Feld.toBezeichnung(feldX)));
    }

    /**
     * Fraegt ab, ob das entsprechende Feld vorhanden ist.
     *
     * @param bezeichner gewuenschter Bezeichner des Feldes
     * @return true / false
     * @see gdv.xport.satz.Satz#hasFeld(Bezeichner)
     * @since 1.0
     */
    @Override
    public boolean hasFeld(final Bezeichner bezeichner) {
        for (Bezeichner b : bezeichner.getVariants()) {
            if (this.datenfelder.containsKey(b)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ueberprueft, ob das uebergebene Feld vorhanden ist.
     * <p>
     * Anmerkung: Es wird nur der Name ueberprueft. D.h. es wird nicht
     * ueberprueft, ob es evtl. einen Konflikt mit der Start- und End-Adresse
     * gibt.
     * </p>
     *
     * @param feld the feld
     * @return true, if successful
     * @since 1.0
     */
    public boolean hasFeld(final Feld feld) {
        return this.datenfelder.containsKey(feld.getBezeichner());
    }

    /**
     * Liefert alle Felder in der Reihenfolge innerhalb des Teildatensatzes
     * zurueck.
     *
     * @return List der Felder (sortiert)
     * @since 0.2
     */
    @Override
    public Collection<Feld> getFelder() {
        return new TreeSet<>(datenfelder.values());
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Datensatz#export(java.io.Writer)
     */
    @Override
    public void export(final Writer writer) throws IOException {
        String eod = Config.hasEOD() ? Config.getEOD() : "";
        export(writer, eod);
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#export(java.io.Writer, java.lang.String)
     */
    @Override
    public void export(final Writer writer, final String eod) throws IOException {
        StringBuilder data = new StringBuilder(256);
        for (int i = 0; i < 256; i++) {
            data.append(' ');
        }
        for (Entry<Bezeichner, Feld> entry : datenfelder.entrySet()) {
            Feld feld = datenfelder.get(entry.getKey());
            int start = (feld.getByteAdresse() - 1) % 256;
            int end = start + feld.getAnzahlBytes();
            data.replace(start, end, feld.getInhalt());
        }
        assert data.length() == 256 : "Teildatensatz ist nicht 256 Bytes lang";
        writer.write(data.toString());
        writer.write(eod);
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#importFrom(java.lang.String)
     */
    @Override
    public void importFrom(final String content) throws IOException {
        for (Feld feld : datenfelder.values()) {
            int begin = (feld.getByteAdresse() - 1) % 256;
            int end = begin + feld.getAnzahlBytes();
            if (end > content.length()) {
                throw new ImportException("input string is too short (" + (end - content.length())
                        + " bytes missing): " + content);
            }
            String s = content.substring(begin, end);
            feld.setInhalt(s);
        }
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#isValid()
     */
    @Override
    public boolean isValid() {
        if (!super.isValid()) {
            return false;
        }
        for (Feld feld : datenfelder.values()) {
            if (!feld.isValid()) {
                LOG.info(feld + " is not valid");
                return false;
            }
        }
        return true;
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#validate()
     */
    @Override
    public List<ConstraintViolation> validate() {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(this);
        for (Feld feld : datenfelder.values()) {
            violations.addAll(feld.validate());
        }
        return violations;
    }

    @Override
    public String toShortString() {
        String s = String.format("Teildatensatz %d Satzart %04d", this.getSatznummer().toInt(), this.getSatzart());
        if (sortedFelder.size() < 4) {
            return s;
        }
        return s + "." + getFeld(4).getInhalt();
    }

    /**
     * Legt eine Kopie des Teildatensatzes an.
     *
     * @return Kopie
     * @see Cloneable
     */
    @Override
    public Object clone() {
        return new Teildatensatz(this);
    }

}