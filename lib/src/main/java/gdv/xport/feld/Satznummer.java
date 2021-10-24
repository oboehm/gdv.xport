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

import gdv.xport.satz.Teildatensatz;
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
        this(new Zeichen(SATZNUMMER, 256));
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
    public static Satznummer readSatznummer(final PushbackReader reader) throws IOException {
        Satznummer satznr = new Satznummer();
        satznr.read(reader);
        return satznr;
    }

    private void read(PushbackReader reader) throws IOException {
        char[] cbuf = new char[getByteAdresse()];
        if (reader.read(cbuf) == -1) {
            throw new EOFException("can't read 1 bytes (" + new String(cbuf) + ") from " + reader);
        }
        reader.unread(cbuf);
        String teildatenSatz = new String(cbuf).substring(cbuf.length - 1, cbuf.length);
        setInhalt(teildatenSatz);
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
    public Object clone() {
        return new Satznummer(this);
    }

}
