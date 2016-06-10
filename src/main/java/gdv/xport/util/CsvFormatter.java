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
import java.util.ArrayList;
import java.util.List;

import gdv.xport.Datenpaket;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;

/**
 * Diese Klasse dient dazu, um die verschiedenen Saetze und Felder in einer
 * einzigen CSV-Datei zu exportieren.
 *
 * @author oliver
 * @since 1.2 (06.06.2016)
 */
public final class CsvFormatter extends AbstractFormatter {

    private final List<Bezeichner> head = new ArrayList<>();

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
     * Ausgabe eines kompletten Datenpakets im CSV-Format. Dabei tauchen
     * unterschiedliche Saetze nebeneinander in der generierten Tabelle auf.
     *
     * @param datenpaket Datenpaket, das als CSV exportiert werden soll
     * @throws IOException bei Problemen mit der Generierung
     */
    @Override
    public void write(final Datenpaket datenpaket) throws IOException {
        this.writeHead(datenpaket);
        this.writeBody(datenpaket);
        this.write(datenpaket.getVorsatz());
        for (Datensatz satz : datenpaket.getDatensaetze()) {
            this.write(satz);
        }
        this.write(datenpaket.getNachsatz());
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
        this.buildHead(satz);
        this.writeHead();
        this.writeBody(satz);
    }

    private void writeHead(Datenpaket datenpaket) {
        // TODO Auto-generated method stub
        //
        throw new UnsupportedOperationException("not yet implemented");
    }

    private void buildHead(Satz satz) throws IOException {
        for (Feld feld : satz.getFelder()) {
            if (!head.contains(feld.getBezeichner())) {
                head.add(feld.getBezeichner());
            }
        }
    }

    private void writeHead() throws IOException {
        for (Bezeichner bezeichner : this.head) {
            this.write(bezeichner.getName());
            this.write(";");
        }
        this.write("\n");
    }

    private void writeBody(Datenpaket datenpaket) {
        // TODO Auto-generated method stub
        //
        throw new UnsupportedOperationException("not yet implemented");
    }

    private void writeBody(Satz satz) throws IOException {
        for(Feld feld : satz.getFelder()) {
            this.write(feld.getInhalt());
            this.write(";");
        }
        this.write("\n");
    }

}
