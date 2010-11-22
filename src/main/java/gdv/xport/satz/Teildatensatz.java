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

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;

import java.io.*;
import java.util.*;

import org.apache.commons.logging.*;

import net.sf.oval.*;

import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.io.ImportException;

/**
 * Ein Teildatensatz hat immer genau 256 Bytes. Dies wird beim Export
 * beruecksichtigt. Und ein Teildatensatz besteht aus mehreren Datenfeldern.
 *
 * @author oliver.boehm@agentes.de
 * @since 04.10.2009
 */
public final class Teildatensatz extends Satz {

    private static final Log log = LogFactory.getLog(Teildatensatz.class);
    private final Map<String, Feld> datenfelder = new HashMap<String, Feld>();
    private final Zeichen satznummer = new Zeichen(SATZNUMMER, 256);

    /**
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
        this.teildatensatz = null;
    }

    private void initDatenfelder() {
        datenfelder.put("Satzart", this.satzart);
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
     * 
     * @param feld Feld mit Name
     */
    @Override
    public void add(final Feld feld) {
        for (Iterator<Feld> iterator = datenfelder.values().iterator(); iterator.hasNext();) {
            Feld f = iterator.next();
            if (feld.overlapsWith(f)) {
                throw new IllegalArgumentException("conflict: " + feld + " overlaps with " + f);
            }
        }
        String name = feld.getBezeichnung();
        datenfelder.put(name, feld);
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
        StringBuffer data = new StringBuffer(256);
        for (int i = 0; i < 256; i++) {
            data.append(' ');
        }
        datenfelder.keySet().iterator();
        for (String key : datenfelder.keySet()) {
            Feld feld = datenfelder.get(key);
            int start = (feld.getByteAdresse() - 1) % 256;
            int end = start + feld.getAnzahlBytes();
            data.replace(start, end, feld.getInhalt());
        }
        assert data.length() == 256 : "Teildatensatz ist nicht 256 Bytes lang";
        writer.write(data.toString());
        if (Config.hasEOD()) {
            writer.write(Config.getEOD());
        }
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


/*
 * $Log$
 * $Source$
 */
