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

import com.fasterxml.jackson.databind.ObjectMapper;
import gdv.xport.Datenpaket;
import gdv.xport.config.Config;

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

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

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
        String value = OBJECT_MAPPER.writeValueAsString(datenpaket);
        this.write(value);
    }

}
