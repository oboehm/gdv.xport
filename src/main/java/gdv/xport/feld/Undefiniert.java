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
 * (c)reated 28.11.2010 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

/**
 * Dieses Unterklasse ist fuer Felder vorgesehen, die nicht definiert sind
 * (bzw. vergessen wurden).
 *
 * @author oliver (ob@aosd.de)
 * @since 0.5.0 (28.11.2010)
 */
public final class Undefiniert extends Feld {

    /**
     * Dies ist der einzige Konstruktor.
     *
     * @param length Byte-Laenge
     * @param start Start-Adresse
     */
    public Undefiniert(final int length, final int start) {
        super(new Bezeichner("undefiniert"), length, start, Align.LEFT);
    }

}

