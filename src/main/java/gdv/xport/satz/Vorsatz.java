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
 * (c)reated 05.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;
import gdv.xport.config.Config;
import gdv.xport.feld.*;

import java.io.IOException;
import java.util.*;

import org.apache.commons.logging.*;


/**
 * @author oliver
 */
public final class Vorsatz extends Satz {

    private static final Log log = LogFactory.getLog(Vorsatz.class);
    /** 5 Zeichen, Byte 5 - 9 */
    protected AlphaNumFeld vuNummer = new VUNummer(Config.getVUNummer(), 5);
    /** 30 Zeichen, Byte 10 - 39 */
    private AlphaNumFeld absender = new AlphaNumFeld(ABSENDER, 30, 10);
    /** 30 Zeichen, Byte 40 - 69 */
    private AlphaNumFeld adressat = new AlphaNumFeld(ADRESSAT, 30, 40);
    /** 8 Zeichen, Byte 70 - 77 */
    private Datum von = new Datum(ERSTELLUNGSDATUM_ZEITRAUM_VOM, 70);
    /** 8 Zeichen, Byte 78 - 85 */
    private Datum bis = new Datum(ERSTELLUNGSDATUM_ZEITRAUM_BIS, 78);
    /** 10 Zeichen, Byte 86 - 95 */
    private AlphaNumFeld vermittler = new AlphaNumFeld(VERMITTLER, 10, 86);
    /** die Versionen fuer die verschiedenen Datensaetze */
    private Map<Integer, Version> versions = new HashMap<Integer, Version>();

    public Vorsatz() {
        super("0001", 3);
        setUpTeildatensaetze();
        setUpVersions();
    }

    public Vorsatz(String content) {
        this();
        try {
            this.importFrom(content);
            log.debug(this + " created from \"" + content + '"');
        } catch (IOException ioe) {
            throw new IllegalArgumentException("argument too short", ioe);
        }
    }

    private void setUpTeildatensaetze() {
        for (int i = 0; i < teildatensatz.length; i++) {
            teildatensatz[i].add(this.vuNummer);
            teildatensatz[i].add(this.absender);
            teildatensatz[i].add(this.adressat);
            teildatensatz[i].add(this.von);
            teildatensatz[i].add(this.bis);
            teildatensatz[i].add(this.vermittler);
        }
    }

    private void setUpVersions() {
        addVersion(1, new Version(VERSION_VORSATZ, 96, "2.1"));
        addVersion(100, 99, "2.1");
        addVersion(200, 102, "2.2");
        addVersion(210, 50, 105, "   ");
        addVersion(220, 51, 108, "   ");
        addVersion(220, 52, 111, "   ");
        addVersion(220, 53, 114, "   ");
        addVersion(220, 54, 117, "   ");
        addVersion(220, 59, 120, "   ");
        addVersion(210, 70, 165, "1.4");
        addVersion(220, 70, 168, "1.4");
        addVersion(9999, new Version(VERSION_NACHSATZ, 225, "1.1"));
    }

    private void addVersion(Integer art, Version version) {
        versions.put(art, version);
        add(version);
    }

    private void addVersion(int art, int byteadresse, String version) {
        String s = getVersionBezeichnung(art);
        addVersion(art, new Version(s, byteadresse, version));
    }

    private void addVersion(int art, int sparte, int byteadresse, String version) {
        assert sparte < 1000 : "unbekannte Sparte " + sparte;
        String s = getVersionBezeichnung(art, sparte);
        addVersion(art * 1000 + sparte, new Version(s, byteadresse, version));
    }
    
    private static String getVersionBezeichnung(int art) {
        return new Formatter().format("Version Satzart %04d", art).toString();
    }
    
    private static String getVersionBezeichnung(int art, int sparte) {
        return new Formatter().format("Version Satzart %04d %03d", art, sparte).toString();
    }

    public String getVuNummer() {
        return this.vuNummer.getInhalt().trim();
    }

    /**
     * Absender ist Byte 10 - 39 im Teildatensatz.
     * @param name
     */
    public void setAbsender(String name) {
        this.absender.setInhalt(name);
    }

    public String getAbsender() {
        return this.absender.getInhalt().trim();
    }

    public void setAdressat(String name) {
        this.adressat.setInhalt(name);
    }

    public String getAdressat() {
        return this.adressat.getInhalt().trim();
    }

    /**
     * @param startDatum im Format "TTMMJJJJ"
     * @param endDatum im Format "TTMMJJJJ"
     */
    public void setErstellungsZeitraum(String startDatum, String endDatum) {
        this.von.setInhalt(startDatum);
        this.bis.setInhalt(endDatum);
    }

    public void setVermittler(String s) {
        this.vermittler.setInhalt(s);
    }

    public String getVermittler() {
        return this.vermittler.getInhalt().trim();
    }

    /**
     * Ermittelt die Version des uebergebenen Bezeichners.
     *
     * @param bezeichner
     *            z.B. VERSION_VORSATZ; hier koennen alle die
     *            Bezeichner-Konstanten gewaehlt werden, die mit "VERSION_"
     *            anfangen.
     * @return Version des gewuenschten Bezeichners
     */
    public String getVersion(String bezeichner) {
        return this.getFeld(bezeichner).getInhalt();
    }

    public String getVersion(int art) {
        return this.getVersion(getVersionBezeichnung(art));
    }

    public String getVersion(int art, int sparte) {
        return this.getVersion(getVersionBezeichnung(art, sparte));
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        // TODO Auto-generated method stub
        return super.equals(other);
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#hashCode()
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

}
