/*
 * Copyright (c) 2013 by Oli B.
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
 * (c)reated 31.03.2013 by Oli B. (oliver.boehm@gmail.com)
 */

package gdv.xport.demo;

import gdv.xport.Datenpaket;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.feld.Feld100;
import gdv.xport.util.SatzFactory;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Diese Klasse enthaelt einige Beispiele fuer den Import und Export von
 * Datensaetzen.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.9 (31.03.2013)
 */
public final class ImportExport {

    private static final Logger LOG = LogManager.getLogger(ImportExport.class);

    /**
     * Dies ist ein Beispiel, wie man einen bestimmten Datensatz exportieren
     * kann. Als Beispiel wollen wir den Satz 100 (Adressteil) exportieren. Dazu
     * muessen wir ihn erst einmal generieren.
     * <p>
     * Der Satz 100 kann auf drei verschiedene Arten angelegt werden:
     * </p>
     * <ol>
     * <li><code>Datensatz satz100 = SatzFactory.getDatensatz(100);</code></li>
     * <li><code>Datensatz satz100 = new Satz100();</code></li>
     * <li><code>Datensatz satz100 = new SatzX(100, Feld100.values());</code></li>
     * </ol>
     *
     * @param file the file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void exportSatz100(final File file) throws IOException {
        Datensatz satz100 = SatzFactory.getDatensatz(100);
        satz100.set(Feld100.ANREDESCHLUESSEL, 1);
        satz100.set(Feld100.NAME1, "Duck");
        satz100.set(Feld100.NAME3, "Dagobert");
        satz100.set(Feld100.GESCHLECHT, '1');
        satz100.export(file);
    }

    /**
     * Dies ist ein Beispiel, wie man einen bestimmten Datensatz importieren
     * kann. Als Beispiel nehmen wir dazu den Satz 100 (Adressteil).
     *
     * @param file the file
     * @return importierter Satz 100
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static Datensatz importSatz100(final File file) throws IOException {
        Datensatz satz100 = SatzFactory.getDatensatz(100);
        satz100.importFrom(file);
        LOG.info("Datensatz " + satz100.getSatzart() + " von " + satz100.getFeld(Feld100.NAME3) + " "
                + satz100.getFeld(Feld100.NAME1) + " importiert.");
        return satz100;
    }

    /**
     * Dies ist ein Beispiel, wie sich mehrere Datenpakete importieren lassen.
     *
     * @param inputStream the istream
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static List<Datenpaket> importDatenpakete(final InputStream inputStream) throws IOException {
        List<Datenpaket> datenpakete = new ArrayList<Datenpaket>();
        while (inputStream.available() >= 0) {
            Datenpaket paket = new Datenpaket();
            try {
                paket.importFrom(inputStream);
                datenpakete.add(paket);
            } catch (EOFException ex) {
                LOG.info("EOF nach " + datenpakete.size() + " Datenpaketen erreicht.", ex);
                break;
            }
        }
        return datenpakete;
    }

    /**
     * Damit diese Klasse nicht instantiiert werden kann, ist der Konstruktor
     * "private".
     */
    private ImportExport() {
    }

}

