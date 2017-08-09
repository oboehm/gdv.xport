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
 * (c)reated 08.08.2017 by oboehm (ob@oasd.de)
 */
package gdv.xport.srv.web.util;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.*;

import java.io.*;
import java.time.*;

/**
 * Die Klasse LocalDateSerializer wandelt ein LocalDate in einen einfachen
 * String.
 *
 * @author oboehm
 */
public final class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    private static final long serialVersionUID = 20170809L;

    /**
     * Default-Konstruktor fuer Standardaufrufe aus Spring oder anderen
     * Frameworks.
     */
    public LocalDateTimeSerializer() {
        this(LocalDateTime.class);
    }

    public LocalDateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    /**
     * Bei der Serialisierung muessen wir das Datum extra behandeln, weil wir
     * dieses nicht als JSON-Struktur wollen, sondern nur als einfachen String.
     *
     * @param date das LocalDate, das serialisiert wird
     * @param jsonGenerator Json-Generator
     * @param serializerProvider Provider
     * @throws IOException sollte eigentlich nicht auftreten
     */
    @Override
    public void serialize(LocalDateTime date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(date.toString());
    }

}
