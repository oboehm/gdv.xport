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
        switch (teildatensatz.getSatzTyp().getGdvSatzartName()) {
            case "0220.030":
            case "0221.030":
                return readAmbiguousSatznummer(reader, nr, teildatensatz);
            case "0500":
                return readSatznumme500(reader, nr);
            default:
                return readSatznummer(reader, nr);
        }
    }

    private static Satznummer readSatznummer(PushbackReader reader, Satznummer nr) throws IOException {
        return (Satznummer) readZeichen(reader, nr);
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

    private static Satznummer readAmbiguousSatznummer(PushbackReader reader, Satznummer nr, Teildatensatz teildatensatz)
            throws IOException {
        if (teildatensatz.hasFeld(Bezeichner.SATZNUMMERNWIEDERHOLUNG)) {
            Zeichen wiederholung = teildatensatz.getFeld(Bezeichner.SATZNUMMERNWIEDERHOLUNG, Zeichen.class);
            wiederholung = readZeichen(reader, wiederholung);
            if (nr.toChar() != wiederholung.toChar()) {
                LOG.debug("{} stimmt nicht mit {} ueberein.", nr, wiederholung);
                nr.resetInhalt();
            }
        } else if (teildatensatz.hasFeld(Bezeichner.ZUSAETZLICHE_SATZKENNUNG)) {
            Zeichen satzkennung = teildatensatz.getFeld(Bezeichner.ZUSAETZLICHE_SATZKENNUNG, Zeichen.class);
            satzkennung = readZeichen(reader, satzkennung);
            if (satzkennung.toChar() != 'X') {
                LOG.debug("Es fehlt {} fuer {}.", satzkennung, teildatensatz);
                nr.resetInhalt();
            }
        }
        return nr;
    }

    private static Satznummer readSatznumme500(PushbackReader reader, Satznummer nr) throws IOException {
        Satznummer feld256 = readSatznummer(reader, new Satznummer());
        nr.setInhalt(feld256.getInhalt());
        switch (feld256.toInt()) {
            case 1:
                if (nr.getByteAdresse() == 256) {
                    nr.resetInhalt();
                }
                break;
            default:
                if (nr.getByteAdresse() != 256) {
                    nr.resetInhalt();
                }
                break;
        }
        return nr;
    }

}
