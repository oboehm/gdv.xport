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
 * (c)reated 05.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;

import java.io.IOException;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gdv.xport.config.Config;
import gdv.xport.feld.*;

/**
 * Dies ist der erste Satz, der Vorsatz eben.
 * <p>
 * Da Vorsatz und Nachsatz von der Datenpaket-Klasse benoetigt werden, habe
 * ich das "deprecated" wieder entfernt (24-Nov-2012, oboehm).
 * </p>
 *
 * @author oliver
 * @since 0.0.1 (09-Okt-2009)
 */
public final class Vorsatz extends Satz {

    private static final Logger LOG = LogManager.getLogger(Vorsatz.class);
    /** 5 Zeichen, Byte 5 - 9. */
    private final AlphaNumFeld vuNummer = Config.getVUNummer();
    /** 30 Zeichen, Byte 10 - 39. */
    private final AlphaNumFeld absender = new AlphaNumFeld((ABSENDER), 30, 10);
    /** 30 Zeichen, Byte 40 - 69. */
    private final AlphaNumFeld adressat = new AlphaNumFeld((ADRESSAT), 30, 40);
    /** 8 Zeichen, Byte 70 - 77. */
    private final Datum von = new Datum(ERSTELLUNGSDAT_ZEITRAUM_VOM, 70);
    /** 8 Zeichen, Byte 78 - 85. */
    private final Datum bis = new Datum(ERSTELLUNGSDAT_ZEITRAUM_BIS, 78);
    /** 10 Zeichen, Byte 86 - 95. */
    private final AlphaNumFeld vermittler = new AlphaNumFeld((VERMITTLER), 10, 86);
    /** Die Versionen fuer die verschiedenen Datensaetze. */
    private final Map<Integer, Version> versions = new HashMap<Integer, Version>();

    /**
     * Hiermit wird ein Vorsatz mit 3 Teildatensaetzen erstellt.
     */
    public Vorsatz() {
        // TODO: super("0001", Feld0001.values()) aufrufen, sobald Ctor verfuegbar
        super("0001", 3);
        setUpTeildatensaetze();
        setUpVersions();
    }

    /**
     * Legt einen Vorsatz mit dem angegebenen Inhalt an.
     *
     * @param content Inhalt des Vorsatzes
     */
    public Vorsatz(final String content) {
        this();
        try {
            this.importFrom(content);
            LOG.debug(this + " created from \"" + content + '"');
        } catch (IOException ioe) {
            throw new IllegalArgumentException("argument too short", ioe);
        }
    }

    private void setUpTeildatensaetze() {
        int nr = 1;
        for (Teildatensatz tds : this.getTeildatensaetze()) {
            this.setUpTeildatensatz(nr, tds);
            nr++;
        }
    }

    /**
     * Hier wird ein Teildatensatz aufgesetzt.
     * <p>
     * TODO: Infos zum Aufbau Teildatensatz aus Feld0001 beziehen!
     * </p>
     *
     * @param n Nummer des Teildatensatzes
     * @param tds Teildatensatz
     */
    private void setUpTeildatensatz(final int n, final Teildatensatz tds) {
        tds.add(this.vuNummer);
        tds.add(this.absender);
        tds.add(this.adressat);
        tds.add(this.von);
        tds.add(this.bis);
        tds.add(this.vermittler);
        switch (n) {
            case 1: // Teildatensatz 1
                tds.add(new Zeichen((ART_DES_ABSENDERS), 237));
                tds.add(new Zeichen((ART_DES_ADRESSATEN), 238));
                tds.add(new AlphaNumFeld((VU_ABRECHNUNGSSTELLE), 2, 239));
                tds.add(new AlphaNumFeld((BESTANDSFUEHRENDE_GESCHAEFTSSTELLE), 2, 241));
                tds.add(new AlphaNumFeld((LEERSTELLEN), 10, 246));
                break;
            case 2: // Teildatensatz 2
                tds.add(new AlphaNumFeld((PRODUKTSPEZIFISCHE_ANTRAGSDATEN), 3, 240));
                tds.add(new AlphaNumFeld((PRODUKTSPEZIFISCHE_STAMMDATEN), 3, 243));
                tds.add(new AlphaNumFeld((LEERSTELLEN), 10, 246));
                break;
            case 3: // Teildatensatz 3
                tds.add(new AlphaNumFeld((VERSION_SATZART_0211_050), 3, 96));
                tds.add(new AlphaNumFeld((VERSION_SATZART_0221_051), 3, 99));
                tds.add(new AlphaNumFeld((VERSION_SATZART_0221_052), 3, 102));
                tds.add(new AlphaNumFeld((VERSION_SATZART_0221_053), 3, 105));
                tds.add(new AlphaNumFeld((VERSION_SATZART_0221_054), 3, 108));
                tds.add(new AlphaNumFeld((VERSION_SATZART_0221_059), 3, 111));
                tds.add(new AlphaNumFeld((VERSION_SATZART_0221_055), 3, 114));
                tds.add(new AlphaNumFeld((VERSION_SATZART_0211_040), 3, 117));
                tds.add(new AlphaNumFeld((LEERSTELLEN), 118, 138));
                break;
            default:
                LOG.debug("no special setup for Teildatensatz " + n);
                break;
        }
    }

    private void setUpVersions() {
        setUpVersionTds1();
        setUpVersionTds2();
    }

    private void setUpVersionTds1() {
        addVersion(1, new Version(VERSION_SATZART_0001, 96, "2.1"));
        addVersion(100, 99, "2.1");
        addVersion(200, 102, "2.2");
        addVersion(210, 50, 105, "   ");
        addVersion(220, 51, 108, "   ");
        addVersion(220, 52, 111, "   ");
        addVersion(220, 53, 114, "   ");
        addVersion(220, 54, 117, "   ");
        addVersion(220, 59, 120, "   ");
        addVersion(210, 40, 123, "   ");
        addVersion(220, 40, 126, "   ");
        addVersion(210, 30, 129, "   ");
        addVersion(220, 30, 132, "   ");
        addVersion(210, 10, 135, "   ");
        addVersion(220, 10, 138, "   ");
        addVersion(210, 130, 141, "   ");
        addVersion(220, 130, 144, "   ");
        addVersion(210, 110, 147, "   ");
        addVersion(220, 110, 150, "   ");
        addVersion(210, 140, 153, "   ");
        addVersion(220, 140, 156, "   ");
        addVersion(210, 20, 159, "   ");
        addVersion(220, 20, 162, "   ");
        addVersion(210, 70, 165, "1.4");
        addVersion(220, 70, 168, "1.4");
        addVersion(210, 171, "   ");
        addVersion(220, 174, "   ");
        addVersion(210, 510, 177, "   ");
        addVersion(220, 510, 180, "   ");
        addVersion(210, 183, "   ");
        addVersion(220, 186, "   ");
        addVersion(210, 189, "   ");
        addVersion(220, 192, "   ");
        addVersion(250, 195, "   ");
        addVersion(260, 198, "   ");
        addVersion(210, 201, "   ");
        addVersion(220, 204, "   ");
        addVersion(220, 55, 207, "   ");
        addVersion(300, 210, "   ");
        addVersion(400, 213, "   ");
        addVersion(410, 216, "   ");
        addVersion(430, 219, "   ");
        addVersion(500, 222, "   ");
        addVersion(9999, new Version(VERSION_SATZART_9999, 225, "1.1"));
        addVersion(420, 228, "   ");
        addVersion(450, 231, "   ");
        addVersion(550, 234, "   ");
        addVersion(350, 243, "   ");
    }

    private void setUpVersionTds2() {
        Teildatensatz tds = this.getTeildatensatz(2);
        addVersion(tds, 211, 50, 96, "   ");
        addVersion(tds, 221, 51, 99, "   ");
        addVersion(tds, 221, 52, 102, "   ");
        addVersion(tds, 221, 53, 105, "   ");
        addVersion(tds, 221, 54, 108, "   ");
        addVersion(tds, 221, 59, 111, "   ");
        addVersion(tds, 221, 55, 114, "   ");
        addVersion(tds, 211, 40, 117, "   ");
        addVersion(tds, 221, 40, 120, "   ");
        addVersion(tds, 221, 30, 123, "1.2");
        addVersion(tds, 211, 10, 126, "   ");
        addVersion(tds, 221, 10, 129, "   ");
        addVersion(tds, 211, 130, 132, "   ");
        addVersion(tds, 221, 130, 135, "   ");
        addVersion(tds, 211, 110, 138, "   ");
        addVersion(tds, 221, 110, 141, "   ");
        addVersion(tds, 211, 140, 144, "   ");
        addVersion(tds, 221, 140, 147, "   ");
        addVersion(tds, 221, 70, 150, "1.2");
        addVersion(tds, 211, 153, "   ");
        addVersion(tds, 221, 156, "1.2");
        addVersion(tds, 221, 510, 159, "   ");
        addVersion(tds, 211, 162, "   ");
        addVersion(tds, 221, 165, "1.2");
        addVersion(tds, 211, 168, "   ");
        addVersion(tds, 221, 171, "1.2");
        addVersion(tds, 251, 174, "   ");
        addVersion(tds, 211, 177, "   ");
        addVersion(tds, 221, 180, "1.2");
        addVersion(tds, 210, 550, 183, "   ");
        addVersion(tds, 220, 550, 186, "   ");
        addVersion(tds, 270, 550, 189, "   ");
        addVersion(tds, 280, 550, 192, "   ");
        addVersion(tds, 291, 550, 195, "   ");
        addVersion(tds, 292, 550, 198, "   ");
        addVersion(tds, 293, 550, 201, "   ");
        addVersion(tds, 294, 550, 204, "   ");
        addVersion(tds, 295, 550, 207, "   ");
        addVersion(tds, 52, 210, "   ");
        addVersion(tds, 102, 213, "   ");
        addVersion(tds, 212, 216, "   ");
        addVersion(tds, 352, 219, "   ");
        addVersion(tds, 362, 222, "   ");
        addVersion(tds, 382, 225, "   ");
        addVersion(tds, 9950, 228, "   ");
        addVersion(tds, 9952, 231, "   ");
        addVersion(tds, 210, 580, 234, "   ");
        addVersion(tds, 220, 580, 237, "   ");
    }

    private void addVersion(final Integer art, final Version version) {
        addVersion(this.getTeildatensatz(1), art, version);
    }

    private void addVersion(final Teildatensatz tds, final Integer art, final Version version) {
        versions.put(art, version);
        tds.add(version);
    }

    private void addVersion(final int art, final int byteadresse, final String version) {
        addVersion(this.getTeildatensatz(1), art, byteadresse, version);
    }

    private void addVersion(final Teildatensatz tds, final int art, final int byteadresse, final String version) {
        String s = getVersionBezeichnung(art);
        addVersion(tds, art, new Version(s, byteadresse, version));
    }

    private void addVersion(final int art, final int sparte, final int byteadresse, final String version) {
        addVersion(this.getTeildatensatz(1), art, sparte, byteadresse, version);
    }

    private void addVersion(final Teildatensatz tds, final int art, final int sparte, final int byteadresse,
            final String version) {
        assert sparte < 1000 : "unbekannte Sparte " + sparte;
        String s = getVersionBezeichnung(art, sparte);
        addVersion(tds, art * 1000 + sparte, new Version(s, byteadresse, version));
    }

    private static String getVersionBezeichnung(final int art) {
        Formatter formatter = new Formatter();
        try  {
            return formatter.format("Version Satzart %04d", art).toString();
        } finally {
            formatter.close();
        }
    }

    private static String getVersionBezeichnung(final int art, final int sparte) {
        Formatter formatter = new Formatter();
        try  {
            return formatter.format("Version Satzart %04d %03d", art, sparte).toString();
        } finally {
            formatter.close();
        }
    }

    /**
     * Um die VU-Nummer setzen zu koennen.
     *
     * @param s
     *            VU-Nummer (max. 5-stellig)
     */
    public void setVuNummer(final String s) {
        assert s.length() <= 5 : s + " darf nur max. 5 Zeichen lang sein";
        this.vuNummer.setInhalt(s);
    }

    /**
     * @return VU-Nummer
     */
    public String getVuNummer() {
        return this.vuNummer.getInhalt().trim();
    }

    /**
     * Absender ist Byte 10 - 39 im Teildatensatz.
     *
     * @param name
     *            Absender
     */
    public void setAbsender(final String name) {
        this.absender.setInhalt(name);
    }

    /**
     * @return Absender
     */
    public String getAbsender() {
        return this.absender.getInhalt().trim();
    }

    /**
     * @param name
     *            neuer Adressat
     */
    public void setAdressat(final String name) {
        this.adressat.setInhalt(name);
    }

    /**
     * @return Adressat
     */
    public String getAdressat() {
        return this.adressat.getInhalt().trim();
    }

    /**
     * @param startDatum
     *            im Format "TTMMJJJJ"
     * @param endDatum
     *            im Format "TTMMJJJJ"
     */
    public void setErstellungsZeitraum(final String startDatum, final String endDatum) {
        this.von.setInhalt(startDatum);
        this.bis.setInhalt(endDatum);
    }

    /**
     * @param s
     *            neuer Vermittler
     */
    public void setVermittler(final String s) {
        this.vermittler.setInhalt(s);
    }

    /**
     * @return Vermittler
     */
    public String getVermittler() {
        return this.vermittler.getInhalt().trim();
    }

    /**
     * Ermittelt die Version des uebergebenen Bezeichners.
     *
     * @param bezeichner z.B. VERSION_VORSATZ; hier koennen alle die
     *            Bezeichner-Konstanten gewaehlt werden, die mit "VERSION_"
     *            anfangen.
     * @return Version des gewuenschten Bezeichners
     * @since 2.0
     */
    public String getVersion(final Bezeichner bezeichner) {
        return this.getFeld(bezeichner).getInhalt();
    }

    /**
     * Ermittelt die Version des uebergebenen Bezeichners.
     *
     * @param bezeichner z.B. VERSION_VORSATZ; hier koennen alle die
     *            Bezeichner-Konstanten gewaehlt werden, die mit "VERSION_"
     *            anfangen.
     * @return Version des gewuenschten Bezeichners
     */
    public String getVersion(final String bezeichner) {
        return this.getFeld(bezeichner).getInhalt();
    }

    /**
     * @param art
     *            Satzart
     * @return z.B. 1.1
     */
    public String getVersion(final int art) {
        return this.getVersion(getVersionBezeichnung(art));
    }

    /**
     * @param art
     *            Satzart
     * @param sparte
     *            z.B. 70 (Rechtsschutz)
     * @return z.B. 1.1
     */
    public String getVersion(final int art, final int sparte) {
        return this.getVersion(getVersionBezeichnung(art, sparte));
    }

}
