/*
 * Copyright (c) 2017 by Oliver Boehm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 21.02.2017 by oboehm (ob@oasd.de)
 */
package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Ueber diese Klassen koennen die GDV-Datensaetze als JSON formattiert
 * werden.
 *
 * @author oliver
 * @since 2.1.0
 */
public class JsonFormatter extends AbstractFormatter {

    /**
     * Default-Konstruktor.
     */
    public JsonFormatter() {
        this(System.out);
    }

    /**
     * Instantiiert einen neuen JsonFormatter.
     *
     * @param ostream the ostream
     */
    public JsonFormatter(final OutputStream ostream) {
        this(new OutputStreamWriter(ostream, Config.DEFAULT_ENCODING));
    }

    /**
     * Instantiiert einen neuen JsonFormatter.
     *
     * @param writer the writer
     */
    public JsonFormatter(final Writer writer) {
        super(writer);
    }

    /**
     * Ausgabe eines kompletten Datenpakets.
     *
     * @param datenpaket Datenpaket, das formattiert ausgegeben werden soll
     * @throws IOException bei Problemen mit der Generierung
     */
    @Override
    public void write(final Datenpaket datenpaket) throws IOException {
        this.write("{\n\t{\n\t\t\"vorsatz\": ");
        this.write(datenpaket.getVorsatz());
        this.write("\t}\n\t");
        for (Datensatz satz : datenpaket.getDatensaetze()) {
            this.write(satz);
        }
        this.write("\t{\n\t\t\"nachsatz\": ");
        this.write(datenpaket.getNachsatz());
        this.write("}\n}\n");
    }

    /**
     * Wandelt den Satz in JSON um.
     *
     * @param satz Satz, der ausgegeben werden soll
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public void write(final Satz satz) throws IOException {
        this.write("\"" + satz.toLongString() + "\"");
    }

}
