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

import gdv.xport.satz.Datensatz;
import gdv.xport.satz.feld.Feld100;
import gdv.xport.util.SatzFactory;

import java.io.*;

/**
 * Diese Klasse enthaelt einige Beispiele fuer den Import und Export von
 * Datensaetzen.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.9 (31.03.2013)
 */
public final class ImportExport {

    /**
     * Dies ist ein Beispiel, wie man einen bestimmten Datensatz exportieren kann. Als Beispiel
     * wollen wir den Satz 100 (Adressteil) exportieren. Dazu muessen wir ihn erst einmal
     * generieren.
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
        satz100.set(Feld100.ANREDESCHLUESSEL, "1");
        satz100.export(file);
    }

    /**
     * Damit diese Klasse nicht instantiiert werden kann, ist der Konstruktor
     * "private".
     */
    private ImportExport() {}

}

