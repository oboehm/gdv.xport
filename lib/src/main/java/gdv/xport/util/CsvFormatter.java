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

import gdv.xport.Datenpaket;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Diese Klasse dient dazu, um die verschiedenen Saetze und Felder in einer
 * einzigen CSV-Datei zu exportieren.
 *
 * @author oliver
 * @since 1.2 (06.06.2016)
 */
public final class CsvFormatter extends AbstractFormatter {

    private final Map<Bezeichner, Feld> felder = new LinkedHashMap<>();

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
        buildHead(datenpaket);
        this.writeHead();
        this.writeBody(datenpaket);
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

    private void buildHead(final Datenpaket datenpaket) throws IOException {
        this.buildHead(datenpaket.getVorsatz());
        for (Datensatz satz : datenpaket.getDatensaetze()) {
            this.buildHead(satz);
        }
        this.buildHead(datenpaket.getNachsatz());
    }

    private void buildHead(Satz satz) throws IOException {
        for (Feld feld : satz.getFelder()) {
            if (!felder.containsKey(feld.getBezeichner())) {
                felder.put(feld.getBezeichner(), feld);
            }
        }
    }

    private void writeHead() throws IOException {
        for (Bezeichner bezeichner : this.felder.keySet()) {
            this.write(bezeichner.getName());
            this.write(";");
        }
        this.write("\n");
    }

    private void writeBody(Datenpaket datenpaket) throws IOException {
        this.writeBody(datenpaket.getVorsatz());
        for (Datensatz satz : datenpaket.getDatensaetze()) {
            this.writeBody(satz);
        }
        this.writeBody(datenpaket.getNachsatz());
    }

    private void writeBody(Satz satz) throws IOException {
        this.resetFelder();
        for (Feld feld : satz.getFelder()) {
            this.felder.put(feld.getBezeichner(), feld);
        }
        for (Feld feld : this.felder.values()) {
            this.write(feld.getInhalt().trim());
            this.write(";");
        }
        this.write("\n");
        this.getWriter().flush();
    }

    private void resetFelder() {
        for (Entry<Bezeichner, Feld> entry : this.felder.entrySet()) {
            entry.setValue(Feld.NULL_FELD);
        }
    }

}
