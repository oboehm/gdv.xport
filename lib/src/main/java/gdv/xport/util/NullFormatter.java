/*
 * Copyright (c) 2010 - 2012 by Oli B.
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
 * (c)reated 29.11.2010 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import gdv.xport.satz.Satz;

import java.io.IOException;
import java.io.Writer;

/**
 * Dieser Formatter macht keine eigentliche Formattierung, sondern reicht den
 * Datensatz einfach durch.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.5.0 (29.11.2010)
 */
public final class NullFormatter extends AbstractFormatter {

    /**
     * Instantiates a new null formatter.
     */
    public NullFormatter() {
        super();
    }

    /**
     * Instantiates a new null formatter.
     *
     * @param writer the writer
     */
    public NullFormatter(final Writer writer) {
        super(writer);
    }

    /**
     * Hier geben wir nur einen einzelnen Satz ueber den internen Writer aus.
     *
     * @param satz Satz, der unformattiert ausgegeben wird
     * @throws IOException bei Problemen mit der Generierung
     * @see AbstractFormatter#write(Satz)
     */
    @Override
    public void write(Satz satz) throws IOException {
        satz.export(this.getWriter());
        this.getWriter().flush();
    }

}

