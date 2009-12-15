/*
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
 * (c)reated 12.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;

import java.io.*;

import gdv.xport.config.Config;
import gdv.xport.feld.*;

/**
 * @author oliver
 * @since 12.10.2009
 */
public class Datensatz extends Satz {

    /** 5 Zeichen, Byte 5 - 9 */
    protected VUNummer vuNummer = new VUNummer(Config.getVUNummer(), 5);
    /** 1 Zeichen, Byte 10 */
    protected AlphaNumFeld buendelungsKennzeichen = new AlphaNumFeld(BUENDELUNGSKENNZEICHEN, 1, 10);
    /** 3 Zeichen, Byte 11 - 13 */
    protected NumFeld sparte = new NumFeld(SPARTE, 3, 11);
    /** 17 Zeichen, Byte 14 - 30 */
    protected AlphaNumFeld versicherungsscheinNr = new AlphaNumFeld(VERSICHERUNGSSCHEINNUMMER, 17, 14);
    /** 2 Zeichen, Byte 31 + 32 */
    protected NumFeld folgeNr = new NumFeld(FOLGENUMMER, 2, 31);
    /** 10 Zeichen, Byte 33 - 42 */
    protected AlphaNumFeld vermittler = new AlphaNumFeld(VERMITTLER, 10, 33);

    public Datensatz(String satzart) {
        super(satzart);
        this.setUpTeildatensaetze();
    }

    public Datensatz(int satzart) {
        super(satzart, 1);
        this.setUpTeildatensaetze();
    }

    /**
     * @param satzart z.B. 100
     * @param n Anzahl der Teildatensaetze
     */
    public Datensatz(final String satzart, final int n) {
        super(satzart, n);
        this.setUpTeildatensaetze();
    }

    public Datensatz(final int satzart, final int sparte) {
        this(satzart, sparte, 1);
    }

    public Datensatz(final int satzart, final int sparte, final int n) {
        super(satzart, n);
        this.setSparte(sparte);
        this.setUpTeildatensaetze();
    }

    protected void setUpTeildatensaetze() {
        for (int i = 0; i < teildatensatz.length; i++) {
            teildatensatz[i].add(this.vuNummer);
            teildatensatz[i].add(this.buendelungsKennzeichen);
            teildatensatz[i].add(this.sparte);
            teildatensatz[i].add(this.versicherungsscheinNr);
            teildatensatz[i].add(this.folgeNr);
            teildatensatz[i].add(this.vermittler);
        }
    }

    /* (non-Javadoc)
     * @see gdv.xport.satz.Satz#addFiller()
     */
    @Override
    public void addFiller() {
        for (int i = 0; i < teildatensatz.length; i++) {
            teildatensatz[i].add(new AlphaNumFeld(LEERSTELLEN, 213, 43));
        }
    }

    public void setSparte(final int x) {
        this.sparte.setInhalt(x);
    }

    /**
     * @return die Sparte als int
     */
    public int getSparte() {
        return this.sparte.toInt();
    }

    /**
     * @return die Sparte als Feld
     */
    public NumFeld getSparteFeld() {
        return this.sparte;
    }

    public void setVuNummer(final String s) {
        this.vuNummer.setInhalt(s);
    }

    public String getVuNummer() {
        return this.vuNummer.getInhalt();
    }

    /**
     * @since 0.3
     * @param nr die Versicherungsschein-Nummer
     */
    public void setVersicherungsscheinNummer(final String nr) {
        this.versicherungsscheinNr.setInhalt(nr);
    }

    /**
     * @since 0.3
     * @return die Versicherungsschein-Nummer
     */
    public String getVersicherungsscheinNummer() {
        return this.versicherungsscheinNr.getInhalt();
    }

    /**
     * Hiermit kann die Folgenummer gesetzt werden.
     *
     * @since 0.3
     * @param nr man sollte hier bei 1 anfangen mit zaehlen
     */
    public void setFolgenummer(final int nr) {
        this.folgeNr.setInhalt(nr);
    }

    /**
     * @since 0.3
     * @return die Folgenummer
     */
    public int getFolgenummer() {
        return this.folgeNr.toInt();
    }

    /**
     * Liest 14 Bytes, um die Satzart zu bestimmen und stellt die Bytes
     * anschliessend wieder zurueck in den Reader.
     *
     * @param reader
     *            muss mind. einen Pushback-Puffer von 14 Zeichen bereitstellen
     * @return Satzart
     * @throws IOException falls was schief gegangen ist
     */
    public static int readSparte(PushbackReader reader) throws IOException {
        char[] cbuf = new char[14];
        if (reader.read(cbuf) == -1) {
            throw new IOException("can't read 14 bytes (" + new String(cbuf) + ") from " + reader);
        }
        reader.unread(cbuf);
        return Integer.parseInt(new String(cbuf).substring(10, 13));
    }

}

