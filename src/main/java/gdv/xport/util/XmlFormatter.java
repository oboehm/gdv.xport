/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 13.11.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.util;

import java.io.*;

import gdv.xport.feld.Feld;

/**
 * Diese Klasse dient dazu, um die verschiedenen Saetze und Felder in einer
 * XML-Struktur ausgeben zu koennen.
 *
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.2 (13.11.2009)
 */
public class XmlFormatter {

    private final Writer writer;

    /**
     * Der einzige Konstruktor.
     * @param writer
     */
    public XmlFormatter(Writer writer) {
        this.writer = writer;
    }

    /**
     * Ausgabe eines Feldes als XML
     *
     * @param feld the feld
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void write(Feld feld) throws IOException {
        this.writer.write("<Feld bezeichner=\"" + feld.getBezeichnung() + "\">");
        this.writer.write(feld.getInhalt());
        this.writer.write("</Feld>\n");
    }

}

