/*
 * Copyright (c) 2021 by Oliver Boehm
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
 * (c)reated 03.09.21 by oliver (ob@oasd.de)
 */
package gdv.xport.feld;

import gdv.xport.io.Importer;
import gdv.xport.io.PushbackLineNumberReader;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.EOFException;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.List;

import static gdv.xport.feld.Bezeichner.SATZNUMMER;

/**
 * In der Satznummer ist alle Logik rund um die Satznummer zusammengefasst.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 * @since 5.2 (03.09.21)
 */
public class Satznummer extends Zeichen {

    private static final Logger LOG = LogManager.getLogger();

    /**
     * Default-Constructor.
     */
    public Satznummer() {
        this(ByteAdresse.of(256));
    }

    /**
     * Nicht jede Satznummer faengt auf Position 256 an. Daher dieser
     * Constructor.
     *
     * @param start Start-Adresse (z.B. 256)
     * @deprecated durch entsprechenden Constructor mit ByteAdresse ersetzt
     *             (TODO: wird mit v8 entsorgt)
     */
    @Deprecated
    public Satznummer(int start) {
        this(new Zeichen(SATZNUMMER, start));
    }

    /**
     * Nicht jede Satznummer faengt auf Position 256 an. Daher dieser
     * Constructor.
     *
     * @param start Start-Adresse (z.B. 256)
     * @since 7.0 (07-Jan-2024)
     */
    public Satznummer(ByteAdresse start) {
        this(new Zeichen(SATZNUMMER, start));
    }

    /**
     * Copy-Constructor.
     *
     * @param feld Zeichen-Feld, das kopiert wird
     */
    public Satznummer(Feld feld) {
        super(feld);
    }

    /**
     * Liest das letzte Feld eines Teildatensatzes, in dem (im Normalfall) die
     * Satznummer steht. Die Logik dieser Methode stammt urspruenglich aus
     * der Methode readTeildatensatzNummer(..) in Datensatz.
     *
     * @param reader der Reader zum Lesen
     * @return die Satznummer
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static Satznummer readSatznummer(final PushbackLineNumberReader reader) throws IOException {
        Satznummer satznr = new Satznummer();
        int satzart = Importer.of(reader).readSatzart();
        switch (satzart) {
            case 210:
            case 211:
                satznr = getSatznummer21x(reader);
                break;
            case 220:
            case 221:
                satznr = getSatznummer22x(reader);
                break;
            case 250:
            case 251:
            case 450:
                satznr = new Satznummer(51);
                break;
            case 270:
            case 280:
            case 291:
            case 292:
            case 293:
            case 294:
            case 295:
                satznr = new Satznummer(43);
                break;
            case 500:
                satznr.read(reader);
                if (satznr.toChar() == '2') {
                    return satznr;
                }
                satznr = new Satznummer(66);
                break;
            case 550:
                satznr = new Satznummer(66);
                break;
        }
        satznr.read(reader);
        return satznr;
    }

    private static Satznummer getSatznummer21x(PushbackLineNumberReader reader) throws IOException {
        char[] satz = readRecord(reader, 14);
        int sparte = Integer.parseInt(String.valueOf(satz, 10, 3));
        switch (sparte) {
            case 0:
            case 80:
            case 170:
            case 190:
            case 550:
            case 560:
            case 570:
            case 580:
                return new Satznummer(43);
            case 130:
                return new Satznummer(251);
            default:
                return new Satznummer(256);
        }
    }

    private static Satznummer getSatznummer22x(PushbackLineNumberReader reader) throws IOException {
        char[] satz = readRecord(reader, 256);
        int sparte = Integer.parseInt(String.valueOf(satz, 10, 3));
        switch (sparte) {
            case 0:
                return new Satznummer(47);
            case 30:
                if ((satz[48] == '2' && satz[255] == 'X') || satz[48] == '1' || satz[48] == '4') {
                    return new Satznummer(49);
                } else if (satz[59] == '9') {
                    return new Satznummer(60);
                } else if (satz[42] == '3') {
                    return new Satznummer(43);
                }
                return new Satznummer(60);
            case 40:
            case 140:
                return new Satznummer(51);
            case 70:
                return new Satznummer(53);
            case 80:
            case 190:
                return new Satznummer(49);
            case 170:
                return new Satznummer(50);
            case 550:
            case 560:
            case 570:
            case 580:
                return new Satznummer(43);
            default:
                return new Satznummer(256);
        }
    }

    private void read(PushbackReader reader) throws IOException {
        char[] cbuf = readRecord(reader, getByteAdresse());
        String teildatenSatz = new String(cbuf).substring(cbuf.length - 1, cbuf.length);
        setInhalt(teildatenSatz);
    }

    private static char[] readRecord(PushbackReader reader, int size) throws IOException {
        char[] cbuf = new char[size];
        if (reader.read(cbuf) < 0) {
            throw new EOFException("can't read " + size + " bytes from " + reader);
        }
        reader.unread(cbuf);
        return cbuf;
    }

    /**
     * Ermittelt aus dem uebergebenen Teildatensatz die korrekte Satznummer.
     *
     * @param reader PushbackReader, um die gelesenen Zeichen wieder
     *               zurueckzustellen
     * @param teildatensatz Teildatensat
     * @return ermittelt Satznummer
     * @throws IOException bei Lesefehlern
     */
    public static Satznummer readSatznummer(PushbackReader reader, Teildatensatz teildatensatz) throws IOException{
        Satznummer nr = new Satznummer(teildatensatz.getSatznummer());
        return readSatznummer(reader, nr, teildatensatz);
    }

    private static Satznummer readSatznummer(PushbackReader reader, Satznummer nr, Teildatensatz teildatensatz)
            throws IOException {
         List<Zeichen> satzIdents = teildatensatz.getSatzIdent();
         for (Zeichen ident : satzIdents) {
             Zeichen inhalt = readZeichen(reader, new Zeichen(ident));
             if (inhalt.toChar() != ident.toChar()) {
                 LOG.debug("{} passt nicht, {} wird zurueckgesetzt.", ident, nr);
                 nr.resetInhalt();
                 break;
             }
         }
         return nr;
    }

    private static Zeichen readZeichen(PushbackReader reader, Zeichen nr) throws IOException {
        int position = nr.getByteAdresse();
        char[] cbuf = new char[position];
        if (reader.read(cbuf) == -1) {
            throw new EOFException("can't read 1 bytes (" + new String(cbuf) + ") from " + reader);
        }
        reader.unread(cbuf);
        nr.setInhalt(cbuf[position-1]);
        return nr;
    }

    @Override
    public List<ConstraintViolation> validate() {
        List<ConstraintViolation> violations = super.validate();
        char c = toChar();
        if (c == '0') {
            violations.add(new SimpleConstraintViolation("'0' is not allowed as Satznummer", this, c));
        } else if (!Character.isDigit(c)) {
            violations.add(new SimpleConstraintViolation("only digits are allowed as Satznummer", this, c));
        }
        return violations;
    }

    @Override
    public Object clone() {
        return new Satznummer(this);
    }

}
