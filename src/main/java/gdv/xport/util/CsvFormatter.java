/*
 * Copyright (c) 2016 by Oli B.
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
 * (c)reated 06.06.2016 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import gdv.xport.feld.Feld;
import gdv.xport.satz.Satz;

/**
 * Diese Klasse dient dazu, um die verschiedenen Saetze und Felder in einer
 * einzigen CSV-Datei zu exportieren.
 *
 * @author oliver
 * @since 1.2 (06.06.2016)
 */
public final class CsvFormatter extends AbstractFormatter {

    /**
     * Instantiates a new csv formatter.
     */
    public CsvFormatter() {
        super();
    }

    /**
     * Instantiates a new csv formatter.
     *
     * @param writer the writer
     */
    public CsvFormatter(Writer writer) {
        super(writer);
    }

    /**
     * Instantiates a new csv formatter.
     *
     * @param ostream the ostream
     */
    public CsvFormatter(OutputStream ostream) {
        super(ostream);
    }

    /**
     * Formatiert den uebergebenen Satz als CSV.
     *
     * @param satz the satz
     * @throws IOException Signals that an I/O exception has occurred.
     * @see AbstractFormatter#write(Satz)
     */
    @Override
    public void write(Satz satz) throws IOException {
        writeHead(satz);
        writeContent(satz);
    }

    private void writeHead(Satz satz) throws IOException {
        for(Feld feld : satz.getFelder()) {
            this.write(feld.getBezeichner().getName());
            this.write(";");
        }
        this.write("\n");
    }

    private void writeContent(Satz satz) throws IOException {
        for(Feld feld : satz.getFelder()) {
            this.write(feld.getInhalt());
            this.write(";");
        }
        this.write("\n");
    }

}
