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
 * (c)reated 10.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.feld;

/**
 * Versions-Feld.
 * 
 * @author oliver
 * @since 10.10.2009
 * @version $Revision$
 */
public class Version extends Feld {

    /**
     * @param name Name des Feldes
     * @param start Start-Byte (beginnend bei 1)
     */
    public Version(String name, int start) {
        super(name, 3, start, Align.LEFT);
    }

    /**
     * @param name Name des Feldes
     * @param start Start-Byte (beginnend bei 1)
     * @param v Versions-String (z.B. "1.1")
     */
    public Version(String name, int start, String v) {
        super(name, 3, start, v, Align.LEFT);
        assert v.length() == 3 : "Version hat nicht das Format x.x";
    }

}

