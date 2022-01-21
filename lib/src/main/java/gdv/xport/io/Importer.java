/*
 * Copyright (c) 2021-2022 by Oli B.
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
 * (c)reated 20.10.2021 by Oli B. (ob@aosd.de)
 */
package gdv.xport.io;

import gdv.xport.feld.Satznummer;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.feld.common.WagnisartLeben;
import gdv.xport.util.SatzTyp;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/**
 * In der Klasse Importer sind einige (statische) Methoden zum Lesen von
 * (Teil)Datensaetzen zusammengefasst. Diese waren voher in der Datensatz-
 * oder Satzklasse zu finden.
 *
 * @author oboehm
 * @since 6.1 (20.10.21)
 */
public class Importer {

    private final PushbackLineNumberReader reader;

    private Importer(PushbackLineNumberReader reader) {
        this.reader = reader;
    }

    /**
     * Liefert einen Importer mit dem angegebenen Reader.
     *
     * @param reader zum Lesen
     * @return eine Importer
     */
    public static Importer of(PushbackLineNumberReader reader) {
        return new Importer(reader);
    }

    /**
     * Liefert einen Importer mit dem angegebenen Reader.
     *
     * @param reader zum Lesen
     * @return eine Importer
     */
    public static Importer of(Reader reader) {
        if (reader instanceof PushbackLineNumberReader) {
            return of((PushbackLineNumberReader) reader);
        } else {
            return of(new PushbackLineNumberReader(reader));
        }
    }

    /**
     * Liest 4 Bytes, um die Satzart zu bestimmen und stellt die Bytes
     * anschliessend wieder zurueck in den Reader.
     *
     * @return Satzart (z.B. 100)
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public int readSatzart() throws IOException {
        reader.skipWhitespace();
        char[] cbuf = new char[4];
        importFrom(cbuf);
        reader.unread(cbuf);
        return Integer.parseInt(new String(cbuf).trim());
    }

    private void importFrom(final char[] cbuf) throws IOException {
        if (reader.read(cbuf) == -1) {
            String s = new String(cbuf).trim();
            throw new EOFException("can't read " + cbuf.length + " bytes from " + reader + ", only \""
                    + s + "\" ("+ s.length() + " bytes)");
        }
    }

    /**
     * Bestimmt den SatzTyp eines Datensatzes.
     *
     * @param satzart Satzart, z.B. 100
     * @return den ermittelten SatzTyp
     * @throws IOException bei Lesefehlern
     */
    public SatzTyp readSatzTyp(int satzart) throws IOException {
        if ((satzart == 1) || (satzart == 9999)) {
            return SatzTyp.of(satzart);
        }
        int sparte = readSparte();
        SatzTyp satzTyp = SatzTyp.of(satzart, sparte);
        if (satzart >= 210 && satzart < 300) {
            if (sparte == 10 && ((satzart == 220) || (satzart == 221))) {
                WagnisartLeben wagnisart = readWagnisart();
                if (wagnisart.getCode() > 0) {
                    int satznr = Satznummer.readSatznummer(reader).toInt();
                    satzTyp = SatzTyp.of(satzart, sparte, wagnisart.getCode(), satznr > 5 ? satznr : 1);
                } else {
                    satzTyp = SatzTyp.of(satzart, sparte, wagnisart.getCode());
                }
            } else if (sparte == 20 && satzart == 220) {
                // Fuer 0220.020.x ist die Krankenfolgenummer zur Identifikation der Satzart noetig
                int krankenFolgeNr = Datensatz.readKrankenFolgeNr(reader);
                satzTyp = SatzTyp.of(satzart, sparte, krankenFolgeNr);
            }  else if (sparte == 580 && satzart == 220) {
                // Fuer 0220.580.x ist die BausparArt zur Identifikation der Satzart
                // noetig
                // Fuer 0220.580.x ist die BausparArt zur Identifikation der Satzart noetig
                int bausparArt = Datensatz.readBausparenArt(reader);
                // BausparenArt nicht auslesbar -> Unbekannter Datensatz
                satzTyp = SatzTyp.of(satzart, sparte, bausparArt);
            }
        }
        return satzTyp;
    }

    /**
     * Liest 14 Bytes, um die Sparte zu bestimmen und stellt die Bytes
     * anschliessend wieder zurueck in den Reader.
     *
     * @return Sparte
     * @throws IOException falls was schief gegangen ist
     */
    public int readSparte() throws IOException {
        char[] cbuf = new char[14];
        if (reader.read(cbuf) == -1) {
            throw new IOException("can't read 14 bytes (" + new String(cbuf) + ") from " + reader);
        }
        reader.unread(cbuf);
        String intro = new String(cbuf);
        try {
            return Integer.parseInt(intro.substring(10, 13));
        } catch (NumberFormatException ex) {
            throw new ImportException("cannot read sparte from first 14 bytes (\"" + intro + "\")");
        }
    }

    /**
     * Liest 1 Byte, um die Wagnisart zu bestimmen und stellt das Byte
     * anschliessend wieder zurueck in den Reader.
     *
     * @return Wagnisart
     * @throws IOException falls was schief gegangen ist
     */
    public WagnisartLeben readWagnisart() throws IOException {
        char[] cbuf = new char[60];
        if (reader.read(cbuf) == -1) {
            throw new IOException("can't read 1 bytes (" + new String(cbuf) + ") from " + reader);
        }
        reader.unread(cbuf);
        String wagnisart = new String(cbuf).substring(59, 60);
        return WagnisartLeben.isIn(wagnisart);
    }

}
