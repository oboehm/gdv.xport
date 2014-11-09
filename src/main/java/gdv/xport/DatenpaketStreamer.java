/*
 * Copyright (c) 2009 - 2014 by Oli B. Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express orimplied. See the License for the specific language
 * governing permissions and limitations under the License. (c)reated 23.10.2009
 * by Oli B. (ob@aosd.de)
 */
package gdv.xport;

import gdv.xport.config.Config;
import gdv.xport.event.ImportListener;
import gdv.xport.io.PushbackLineNumberReader;
import gdv.xport.io.RecordReader;
import gdv.xport.io.RecyclingInputStreamReader;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Vorsatz;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Im Gegensatz zur {@link Datenpaket}-Klasse wird hier ein Datenpaket nicht
 * komplett in den Speicher geladen, sondern satzweise gelesen und anschliessend
 * verworfen. Vorher werden noch die angemeldeten Listener informiert, damit
 * diese die Daten verarbeiten (z.B. exportieren) koennen.
 *
 * @author oliver
 * @since 1.0
 */
public class DatenpaketStreamer {

    private final PushbackLineNumberReader reader;
    private final List<ImportListener> importListener = new ArrayList<ImportListener>();

    /**
     * Legt einen neuen {@link DatenpaketStreamer} an.
     *
     * @param istream der InputStream
     */
    public DatenpaketStreamer(final InputStream istream) {
        this(new RecyclingInputStreamReader(istream, Config.DEFAULT_ENCODING));
    }

    /**
     * Legt einen neuen {@link DatenpaketStreamer} an.
     *
     * @param reader der Reader
     */
    public DatenpaketStreamer(final Reader reader) {
        this.reader = new PushbackLineNumberReader(new RecordReader(reader), 256);
    }

    /**
     * Hierueber kann sich ein Listener registrieren.
     *
     * @param listener ein Listener
     */
    public void register(final ImportListener listener) {
        this.importListener.add(listener);
    }

    /**
     * Hiermit wird ein einzelnes Datenpaket gelesen und die verschiedenen
     * Listener ueber den jeweils importierten Satz informiert. Damit koennen
     * die Listener eine weitere Verarbeitung (wie z.B. Export) anstossen.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void readDatenpaket() throws IOException {
        readVorsatz();
        while (true) {
            Satz satz = Datenpaket.importSatz(reader);
            notice(satz);
            if (satz.getSatzart() == 9999) {
                break;
            }
        }
    }

    private void readVorsatz() throws IOException {
        Vorsatz vorsatz = new Vorsatz();
        vorsatz.importFrom(reader);
        notice(vorsatz);
    }

    private void notice(Satz satz) {
        for (ImportListener listener : this.importListener) {
            listener.notice(satz);
        }
    }

}
