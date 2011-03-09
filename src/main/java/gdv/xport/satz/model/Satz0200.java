/*
 * Copyright (c) 2010 by agentes
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
 * (c)reated 06.03.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz.model;

import gdv.xport.satz.sop.Feld0200;

/**
 * Diese Klasse repraesentiert die Satzart 200.
 * Es handelt es sich dabei um eine alternative Implementierung der
 * {@link gdv.xport.satz.AllgemeinerVertragsteil}-Klasse, die nach dem Soplet-
 * Ansatz (s. <a href="http://www.soplets.org/">soplets.org</a>) implementiert
 * wurde.
 *
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.6 (06.03.2011)
 */
public class Satz0200 extends SatzX {
    
    /**
     * Default-Konstruktor.
     */
    public Satz0200() {
        super("0200", 2, Feld0200.values());
    }

}

