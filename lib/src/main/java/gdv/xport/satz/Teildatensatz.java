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

package gdv.xport.satz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.io.ImportException;
import gdv.xport.util.NotUniqueException;
import gdv.xport.util.SatzTyp;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

import static gdv.xport.feld.Bezeichner.SATZART;
import static gdv.xport.feld.Bezeichner.SPARTE;

/**
 * Ein Teildatensatz hat immer genau 256 Bytes. Dies wird beim Export
 * beruecksichtigt. Und ein Teildatensatz besteht aus mehreren Datenfeldern.
 *
 * @author ob@aosd.de
 * @since 04.10.2009
 */
public class Teildatensatz extends Datensatz {

    private static final Logger LOG = LogManager.getLogger(Teildatensatz.class);
    private final Collection<Feld> datenfelder = Config.getInstance().isDebug() ? new TreeSet<>() : new ArrayList<>();
    /** Dieses Feld brauchen wir, um die Satznummer abzuspeichern. */
    private Satznummer satznummer = new Satznummer();

    /**
     * Instantiiert einen neuen Teildatensatz mit der angegebenen Satzart.
     *
     * @param satzTyp z.B. 0220.050
     */
    public Teildatensatz(final SatzTyp satzTyp) {
        super(satzTyp, 0);
        this.initDatenfelder(satzTyp);
    }

    /**
     * Instantiiert einen neuen Teildatensatz mit der angegebenen Satzart und
     * Nummer.
     *
     * @param satzTyp z.B. 0220.050
     * @param nr      Nummer des Teildatensatzes (zwischen 1 und 9)
     */
    public Teildatensatz(final SatzTyp satzTyp, final int nr) {
        this(satzTyp);
        setSatznummer(nr);
        this.setGdvSatzartName(satzTyp.toString());
    }

    /**
     * Instantiiert einen neuen Teildatensatz mit der angegebenen Satzart, Nummer
     * und Version des zugeheorigen Satzes.
     *
     * @param satz        z.B. 100
     * @param nr          Nummer des Teildatensatzes (zwischen 1 und 9)
     */
    public Teildatensatz(final Datensatz satz, final int nr) {
        super(satz);
        initDatenfelder(satz.getSatzTyp());
        setSatznummer(nr);
    }

    /**
     * Dies ist der Copy-Constructor, falls man eine Kopie eines Teildatensatzes
     * braucht.
     *
     * @param other der andere Teildatensatz
     */
    public Teildatensatz(final Teildatensatz other) {
        super(other);
        this.satznummer = other.satznummer;
        for (Feld f : other.datenfelder) {
            Feld copy = (Feld) f.clone();
            this.datenfelder.add(copy);
        }
    }

    private void setSatznummer(int nr) {
        if ((nr < 1) || (nr > 9)) {
            throw new IllegalArgumentException("Satznummer (" + nr
                    + ") muss zwischen 1 und 9 liegen");
        }
        this.satznummer.setInhalt(Character.forDigit(nr, 10));
    }

    private void initDatenfelder(SatzTyp satzTyp) {
        NumFeld satzart = new NumFeld((SATZART), 4, ByteAdresse.of(1)).mitConfig(getConfig());
        satzart.setInhalt(satzTyp.getSatzart());
        this.add(satzart);
    }

    @Override
    public NumFeld getSatzartFeld() {
        Optional<Feld> satzart = findFeld(Bezeichner.SATZART);
        return satzart.map(feld -> (NumFeld) feld).orElseGet(() -> new NumFeld(SATZART, 4, ByteAdresse.of(1)));
    }

    /**
     * Liefert die Satznummer zurueck. Sie loest die alte
     * getNummer()-Methode ab.
     *
     * @return Satznummer als einzelnes Zeichen ('1' ... '9')
     * @since 5.0
     */
    public Satznummer getSatznummer() {
        if ((this.satznummer.getByteAdresse() == 256) && hasFeld(Bezeichner.SATZNUMMER)) {
            Satznummer nr = getFeld(Bezeichner.SATZNUMMER, Satznummer.class);
            if (nr.isEmpty() || nr.isInvalid()) {
                nr.setInhalt(this.satznummer.getInhalt());
            }
            this.satznummer = nr;
        }
        return this.satznummer;
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
        for (Feld f : getFelder()) {
            if (LOG.isDebugEnabled() && f.getBezeichnung().startsWith("Satznummer")
                    && feld.getBezeichnung().startsWith("Satznummer")) {
                LOG.debug(f.getBezeichnung() + "(" + f.getBezeichner().getTechnischerName() + ") gefunden in "
                        + this + this.getSatznummer());
            }
            if (!feld.equals(f) && feld.overlapsWith(f)) {
                throw new IllegalArgumentException("conflict: " + feld + " overlaps with " + f);
            } else if (feld.compareTo(f) == 0) {
                remove(f);
                LOG.debug("{} wird durch {} ersetzt.", f, feld);
            }
        }
        setUpFeld(feld);
        this.datenfelder.add(feld);
    }

    private void setUpFeld(Feld feld) {
        if (feld.getBezeichnung().startsWith("Satznummernwiederholung")) {
            feld.setInhalt(getSatznummer().getInhalt());
        } else if (feld.getBezeichnung().startsWith("Satznummer")) {
            LOG.debug("{}({}) einfuegen in {} +", feld.getBezeichnung(), feld.getBezeichner().getTechnischerName(), this);
            feld.setInhalt(getSatznummer().getInhalt());
            if (this.getSatznummer().getByteAdresse() >= feld.getByteAdresse()) {
                this.satznummer = new Satznummer(feld);
            }
        } else if (feld.getBezeichner().equals(Bezeichner.ZUSAETZLICHE_SATZKENNUNG)) {
            feld.setInhalt("X");
        } else if (feld.getBezeichnung().startsWith("Vorzeichen")) {
            LOG.debug("{}({}) einfuegen in {} +", feld.getBezeichnung(), feld.getBezeichner().getTechnischerName(), this);
            feld.setInhalt("+");
        } else if (this.getSatzart() == 1 && feld.getBezeichner().getTechnischerName().equals("Satzart0001")) {
            LOG.debug("{}({}) einfuegen in {} {}}", feld.getBezeichnung(), feld.getBezeichner().getTechnischerName(),
                    this, this.getSatzversion());
            feld.setInhalt(this.getSatzversion().getInhalt());
        } else if (this.getGdvSatzartName().startsWith("0220.020")
                && feld.getBezeichner().getTechnischerName().startsWith("FolgeNrZurLaufendenPersonenNrUnterNr")) {
            // bei den 0220.020er-Saetzen ist die KrankenFolgeNr wichtig fuer die Erkennbarkeit der
            // Satzart.
            LOG.debug("{}({}) einfuegen in {} +", feld.getBezeichnung(), feld.getBezeichner()
                    .getTechnischerName(), this);
            feld.setInhalt(this.getSatzTyp().getKrankenFolgeNr());
        }
    }

    /**
     * Falls ein Feld zuviel gesetzt wurde, kann es mit 'remove" wieder
     * entfernt werden.
     *
     * @param feld das Feld, das entfernt werden soll
     */
    public void remove(final Feld feld) {
        datenfelder.remove(feld);
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
        if (hasFeld(bezeichner)) {
            datenfelder.remove(getFeld(bezeichner));
            LOG.debug("{} was removed from {}.", bezeichner, this);
        }
    }

    /**
     * Setzt das gewuenschte Feld. Falls es nicht vorhanden ist, wird analog
     * zur Oberklasse eine {@link IllegalArgumentException} geworfen.
     *
     * @param name der Name des Feldes
     * @param value der gewuenschte Werte als String
     * @since 5.2
     */
    @Override
    public void setFeld(final Bezeichner name, final String value) {
        List<Feld> felder = this.getAllFelder(name);
        if (felder.isEmpty()) {
            throw new IllegalArgumentException("Feld \"" + name + "\" not found");
        }
        if (felder.size() > 1) {
            LOG.info("Mit Bezeichner {} werden mehrere Felder in '{}' mit '{}' belegt: {}", name,this, value, felder);
            throw new NotUniqueException(String.format("Bezeichner '%s' in %s nicht eindeutig: %s, %s...", name, toShortString(), felder.get(0), felder.get(1)));
        }
        for (Feld x : felder) {
            setFeld(x, value);
        }
    }

    /**
     * Setzt das gewuenschte Feld anhand der uebergebenen ByteAdresse.
     *
     * @param adresse Adresse des gewuenschten Feldes
     * @param value   Wert
     * @since 5.0
     * @deprecated wurde durch {@link Teildatensatz#setFeld(ByteAdresse, String)} ersetzt
     */
    @Deprecated
    public void set(final ByteAdresse adresse, final String value) {
        Feld x = this.getFeld(adresse);
        x.setInhalt(value);
    }

    /**
     * Setzt das gewuenschte Feld anhand der uebergebenen ByteAdresse.
     *
     * @param adresse Adresse des gewuenschten Feldes
     * @param value   Wert
     * @since 5.2
     */
    @Override
    public void setFeld(final ByteAdresse adresse, final String value) {
        Feld x = this.getFeld(adresse);
        setFeld(x, value);
    }

    private void setFeld(Feld x, String value) {
        try {
            LOG.debug("{} in '{}' wird mit '{}' belegt.", x, this, value);
            x.setInhalt(value);
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(String.format(
                    "%s: illegal value '%s' for %s", this.toShortString(), value, x), iae);
        }
    }

    public void setSparte(final int x) {
        NumFeld numFeld = new NumFeld(SPARTE, 3, ByteAdresse.of(11));
        if (!hasFeld(SPARTE)) {
            add(numFeld);
        } else {
            numFeld = getFeld(SPARTE, NumFeld.class);
        }
        numFeld.setInhalt(x);
    }

    @Override
    public int getSparte() {
        return getFeld(SPARTE, NumFeld.class).toInt();
    }

    /**
     * Liefert das gewuenschte Feld.
     * <p>
     * Falls kein Feld mit dem Bezeichner vorhanden ist, wird eine
     * {@link IllegalArgumentException} geworfen. Ebenso wenn das Feld
     * nicht eindeutig ist und unterschiedliche Werte hat. Dann gibt es
     * eine {@link NotUniqueException} (Ausnahme: Satznummer).
     * </p>
     * @param bezeichner gewuenschter Bezeichner des Feldes
     * @return das gesuchte Feld
     */
    @Override
    public Feld getFeld(final Bezeichner bezeichner) {
        List<Feld> found = getAllFelder(bezeichner);
        if (found.isEmpty()) {
            Optional<Feld> feld = findFeld(bezeichner);
            if (feld.isPresent()) {
                return feld.get();
            }
            throw new IllegalArgumentException("Feld \"" + bezeichner + "\" nicht in " + this.toShortString()
                    + " nicht vorhanden!");
        } else if (found.size() > 1) {
            checkUniqueness(found);
        }
        return found.get(0);
    }

    private static void checkUniqueness(List<Feld> felder) {
        Feld f1 = felder.get(0);
        for (int i = 1; i < felder.size(); i++) {
            Feld fn = felder.get(i);
            if (!f1.getInhalt().equals(fn.getInhalt())) {
                throw new NotUniqueException(String.format("same Bezeichner, different values: '%s', '%s'", f1, fn));
            }
        }
        LOG.debug("{} hat gleichen Wert wie gleichlautende Felder.", f1);
    }

    private List<Feld> getAllFelder(Bezeichner bezeichner) {
        List<Feld> found = new ArrayList<>();
        for (Bezeichner b : bezeichner.getVariants()) {
            for (Feld feld : datenfelder) {
                if (b.equals(feld.getBezeichner())) {
                    found.add(feld);
                }
            }
        }
//        String technischerName = bezeichner.getTechnischerName();
//        char last = technischerName.charAt(technischerName.length()-1);
//        if (!Character.isDigit(last)) {
//            for (int i = 2; i <= 12; i++) {
//                found.addAll(getAllFelder(Bezeichner.of(technischerName + i)));
//            }
//        }
        return found;
    }

    private Optional<Feld> findFeld(final Bezeichner bezeichner) {
        if (datenfelder == null) {
            return Optional.empty();
        }
        for (Feld f : datenfelder) {
            if (f.getBezeichner().getName().equals(bezeichner.getName())) {
                return Optional.of(f);
            }
        }
        return Optional.empty();
    }

    /**
     * Liefert das Feld mit der gewuenschten Nummer zurueck.
     * <p>
     * In der Beschreibung zur 2018er-Version gibt es bei der Feld-Nr. bei
     * manchen Satzarten Ungereimtheiten. So hat im Teildatensatz 1 der
     * Satzart 100 das Feld fuer die Satznummer die Feld-Nr. 27 und es gibt
     * kein Feld 26. Auch bei diesen Satzarten ist eine Luecke in der
     * Nummerierung:
     * </p>
     * <ul>
     * <li>Satart 0100, TDS 1: Feld 26 fehlt</li>
     * <li>Satart 0210.050, TDS 1: Feld 34 fehlt</li>
     * <li>Satart SA0220.010.13.1, TDS 1: Feld 45 fehlt</li>
     * <li>Satart 0600, TDS 2: Feld 12 fehlt</li>
     * <li>Satart 0600, TDS 3: Feld 13 fehlt</li>
     * <li>Satart 9950, TDS 1: Feld 10 fehlt</li>
     * <li>Satart 9951, TDS 1: Feld 10 fehlt</li>
     * </ul>
     * <p>
     * In der 2023er-Version wurde das in der Beschreibung korrigiert.
     * Um den Zugriff zu vereinfachen, wird daher bei Feld-Nummern, die
     * zu gross sind, das letzte Feld zurueckgegeben.
     * </p>
     *
     * @param nr z.B. 1
     * @return das Feld (z.B. mit der Satzart)
     */
    public Feld getFeld(int nr) {
        Feld[] array = getFelder().toArray(new Feld[0]);
        if (nr > array.length) {
            LOG.info("Feld {} in {} wird auf letztes Feld {} abgebildet.", nr, toShortString(), array.length);
            nr = array.length;
        }
        return array[nr - 1];
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
        for (Feld f : datenfelder) {
            if (adresse.intValue() == f.getByteAdresse()) {
                return f;
            }
        }
        throw new IllegalArgumentException(
                String.format("Adresse %s existiert nicht in %s", adresse, this.toShortString()));
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
            for (Feld f : datenfelder) {
                if (b.equals(f.getBezeichner())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Ueberprueft, ob das uebergebene Feld vorhanden ist.
     * <p>
     * Anmerkung: Mit 4.4 wird nicht nur der Name ueberprueft sondern alle
     * Attribute.
     * </p>
     *
     * @param feld the feld
     * @return true, if successful
     * @since 1.0
     */
    public boolean hasFeld(final Feld feld) {
        for (Feld f : datenfelder) {
            if (feld.equals(f)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ueberprueft, ein Feld mit der angegebenen Adresse vorhanden ist.
     *
     * @param adresse gesuchte Adresse
     * @return true, if successful
     * @since 7.1
     */
	@Override
    public boolean hasFeld(final ByteAdresse adresse) {
        for (Feld f : datenfelder) {
            if (adresse.intValue() == f.getByteAdresse()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Liefert alle Felder in der Reihenfolge innerhalb des Teildatensatzes
     * zurueck.
     *
     * @return List der Felder (sortiert)
     * @since 0.2
     */
    @Override
    public final Collection<Feld> getFelder() {
        return new TreeSet<>(datenfelder);
    }

    /**
     * Liefert die Liste der speziellen Kennzeichen zur Identifikation beim Import zurueck.
     * Jedes Element enthaelt Byte-Adresse und Inhalt.
     *
     * @return Liste der speziellen Kennzeichen
     */
    @JsonIgnore
    public List<Zeichen> getSatzIdent() {
        String[] identBezeichner = {"FolgeNrZurLaufendenPersonenNrUnterNrBzwLaufendenNrTarif",
                "FolgeNrZurLaufendenPersonenNrUnterNrLaufendeNrTarif", "SatzNr", "SatzNr1",
                "SatzNr2", "SatzNr3", "SatzNr4", "SatzNr9", "SatzNrnwiederholung",
                "SatzNrnwiederholung1", "SatzNrnwiederholung2", "SatzNrnwiederholung3",
                "Satznummer", "ZusaetzlicheSatzkennung"};
        List<Zeichen> satzIdent = new ArrayList<>();
        for (String s : identBezeichner) {
            Bezeichner b = Bezeichner.of(s);
            if (hasFeld(b)) {
                satzIdent.add(getFeld(b, Zeichen.class));
            }
        }
        return satzIdent;
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Datensatz#export(java.io.Writer)
     */
    @Override
    public void export(final Writer writer) throws IOException {
        String eod = getConfig().getProperty("gdv.eod", System.lineSeparator());
        export(writer, eod);
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#export(java.io.Writer, java.lang.String)
     */
    @Override
    public void export(final Writer writer, final String eod) throws IOException {
        StringBuilder data = new StringBuilder(256);
        data.append(" ".repeat(256));
        for (Feld feld : datenfelder) {
            int start = feld.getByteAdresse() - 1;
            int end = start + feld.getAnzahlBytes();
            data.replace(start, end, feld.getInhalt());
        }
        assert data.length() == 256 : "Teildatensatz ist " + data.length() + " und nicht 256 Bytes lang";
        writer.write(data.toString());
        writer.write(eod);
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#importFrom(java.lang.String)
     */
    @Override
    public Teildatensatz importFrom(final String content) throws IOException {
        for (Feld feld : datenfelder) {
            int begin = (feld.getByteAdresse() - 1) % 256;
            int end = begin + feld.getAnzahlBytes();
            if (end > content.length()) {
                throw new ImportException("input string is too short (" + (end - content.length())
                        + " bytes missing): " + content);
            }
            String s = content.substring(begin, end);
            feld.setInhalt(s);
        }
        return this;
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#isValid()
     */
    @Override
    public boolean isValid() {
        if (!super.isValid()) {
            return false;
        }
        for (Feld feld : datenfelder) {
            if (!feld.isValid()) {
                LOG.info(feld + " is not valid");
                return false;
            }
        }
        return true;
    }

    @Override
    public List<ConstraintViolation> validate(Config validationConfig) {
        List<ConstraintViolation> violations = validateSatznummern(validationConfig);
        for (Feld feld : datenfelder) {
            violations.addAll(feld.validate(validationConfig));
        }
        return violations;
    }

    private List<ConstraintViolation> validateSatznummern(Config validationConfig) {
        List<ConstraintViolation> violations = new ArrayList<>();
        if (validationConfig.getValidateMode() != Config.ValidateMode.OFF) {
            LOG.debug("Satznummern werden validiert in {}.", this);
            Zeichen satznr = getSatznummer();
            for (Feld feld : datenfelder) {
                if (feld.getBezeichner().isVariantOf(Bezeichner.SATZNUMMER)
                        && !satznr.getInhalt().equals(feld.getInhalt())) {
                    ConstraintViolation cv = new SimpleConstraintViolation("different Satznummern: " + satznr,
                            this, feld);
                    violations.add(cv);
                }
            }
        }
        return violations;
    }

    @Override
    public String toShortString() {
        if (datenfelder.size() < 4)
            return String.format("Teildatensatz Satzart %04d", getSatzart());
        else
            return String.format("Teildatensatz %c Satzart %s", this.getSatznummer().toChar(),
                   this.getSatzTyp());
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