/*
 * $Id$
 *
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
 * (c)reated 04.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.SATZNUMMER;
import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.io.ImportException;

import java.io.*;
import java.util.*;

import net.sf.oval.*;

import org.apache.commons.logging.*;

/**
 * Ein Teildatensatz hat immer genau 256 Bytes. Dies wird beim Export
 * beruecksichtigt. Und ein Teildatensatz besteht aus mehreren Datenfeldern.
 *
 * @author ob@aosd.de
 * @since 04.10.2009
 */
public final class Teildatensatz extends Satz {

    private static final Log log = LogFactory.getLog(Teildatensatz.class);
    private final Map<Object, Feld> datenfelder = new HashMap<Object, Feld>();
    private final Zeichen satznummer = new Zeichen(SATZNUMMER, 256);

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
     * @param satzart z.B. 1 (Vorsatz)
     * @param nr Nummer des Teildatensatzes (zwischen 1 und 9)
     */
    public Teildatensatz(final NumFeld satzart, final int nr) {
        super(satzart, 0);
        initSatznummer(nr);
    }

    /**
     * Instantiiert einen neuen Teildatensatz mit der angegebenen Satzart.
     *
     * @param satzart z.B. 100
     * @param nr Nummer des Teildatensatzes (zwischen 1 und 9)
     */
    public Teildatensatz(final int satzart, final int nr) {
        super(satzart, 0);
        initSatznummer(nr);
    }

    private void initSatznummer(final int nr) {
        if ((nr < 1) || (nr > 9)) {
            throw new IllegalArgumentException("Satznummer (" + nr
                    + ") muss zwischen 1 und 9 liegen");
        }
        this.satznummer.setInhalt(Character.forDigit(nr, 10));
        this.initDatenfelder();
    }

    @Override
    protected void createTeildatensaetze(final int n) {
        assert n == 0 : "ein Teildatensatz hat keine weiteren Teildatensaetze";
    }

    private void initDatenfelder() {
        datenfelder.put("Satzart", this.getSatzartFeld());
        datenfelder.put("Satznummer", this.satznummer);
    }

    /**
     * Liefert die Satznummer zurueck.
     *
     * @since 0.2
     * @return Satznummer als einzelnes Zeichen ('1' ... '9')
     */
    public Zeichen getNummer() {
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
        for (Iterator<Feld> iterator = datenfelder.values().iterator(); iterator.hasNext();) {
            Feld f = iterator.next();
            if (feld.overlapsWith(f)) {
                if (isSatznummer(f)) {
                    remove(f);
                    log.debug(f + " is removed from " + this);
                    break;
                } else {
                    throw new IllegalArgumentException("conflict: " + feld + " overlaps with " + f);
                }
            }
        }
        String name = feld.getBezeichnung();
        datenfelder.put(name, feld);
    }

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
     * Falls ein Feld zuviel gesetzt wurde, kann es mit 'remove" wieder
     * entfernt werden.
     *
     * @param name Name des Feldes
     */
    @Override
    public void remove(final String name) {
        datenfelder.remove(name);
    }

    /**
     * Verpasst dem angegebenen Feld einen Namen.
     *
     * @param feld ein Feld
     */
    public void set(final Feld feld) {
        String name = feld.getBezeichnung();
        this.set(name, feld);
    }

    /**
     * @param name Name des Felds
     * @param feld Feld
     */
    public void set(final String name, final Feld feld) {
        datenfelder.put(name, feld);
    }

    /**
     * Liefert das gewuenschte Feld. Allerdings wird nur der Name des Feldes
     * benutzt, um das Feld zu bestimmen. Dazu werden auch die Konstanten in
     * {@link Bezeichner} verwendet.
     * <p>
     * TODO: Eigentlich waere es sinnvoller, hier die restlichen Annotationen
     * auszuwerten, da der Name nur auf Konvention beruht und etwas wackelig
     * ist (oboehm, 1-Apr-2013).
     *
     * </p>
     *
     * @param feldX gewuenschtes Feld-Element
     * @return das gesuchte Feld
     * @throws IllegalArgumentException falls es das Feld nicht gibt
     */
    @Override
    public Feld getFeld(final Enum<?> feldX) throws IllegalArgumentException {
        Feld found = datenfelder.get(feldX);
        if (found == null) {
            found = getFeld(feldX.name());
        }
        if (found == Feld.NULL_FELD) {
            found = getFeld(Feld.toBezeichnung(feldX));
        }
        return found;
    }

    /**
     * Liefert das gewuenschte Feld.
     *
     * @param name gewuenschter Bezeichner des Feldes
     * @return das gesuchte Feld
     */
    @Override
    public Feld getFeld(final String name) {
        Feld found = datenfelder.get(name);
        if (found == null) {
            return Feld.NULL_FELD;
        } else {
            return found;
        }
    }

    /**
     * Ueberprueft, ob das uebergebene Feld vorhanden ist.
     *
     * @param name gesuchter Bezeichner des Feldes
     * @return true, falls Feld vorhanden ist.
     * @since 0.9
     */
    public boolean hasFeld(final String name) {
        return this.datenfelder.containsKey(name);
    }

    /**
     * Liefert alle Felder in der Reihenfolge innerhalb des Teildatensatzes
     * zurueck.
     *
     * @since 0.2
     * @return List der Felder (sortiert)
     */
    public Collection<Feld> getFelder() {
        return new TreeSet<Feld>(datenfelder.values());
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
        StringBuffer data = new StringBuffer(256);
        for (int i = 0; i < 256; i++) {
            data.append(' ');
        }
        datenfelder.keySet().iterator();
        for (Object key : datenfelder.keySet()) {
            Feld feld = datenfelder.get(key);
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
                log.info(feld + " is not valid");
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

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        }
        try {
            return this.equals((Teildatensatz) other);
        } catch (ClassCastException cce) {
            return false;
        }
    }

    /**
     * 2 Teildatensaetze sind gleich, wenn all ihre Felder gleich sind.
     *
     * @param other der andere Teildatensatz
     * @return true, wenn beide Teildatensaetze gleich sind
     */
    public boolean equals(final Teildatensatz other) {
        if (this.datenfelder.size() != other.datenfelder.size()) {
            return false;
        }
        for (Feld feld : datenfelder.values()) {
            if (!feld.equals(other.getFeld(feld.getBezeichnung()))) {
                return false;
            }
        }
        return true;
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#hashCode()
     */
    @Override
    public int hashCode() {
        return this.getSatzart() + this.satznummer.getInhalt().hashCode();
    }

}
