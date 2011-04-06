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
 * (c)reated 09.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.feld;

import static gdv.xport.feld.Bezeichner.*;
import gdv.xport.annotation.FeldInfo;

/**
 * Die VUNummer (Versicherungs-Unternehmen-Nummer) ist ein fuenfstelliges
 * alphanumerisches Feld (ab 1.1.1993 wohl nur noch vierstellig).
 *
 * @author oliver
 * @since 09.10.2009
 * @version $Revision$
 */
public class VUNummer extends AlphaNumFeld {

    /**
     * Erzeugt ein neues VUNummer-Objekt.
     * 
     * @param nr VU-Nummer (max. 5 Stellen)
     */
    public VUNummer(final String nr) {
        super(VU_NUMMER, nr);
        assert nr.length() <= 5 : "nur max. 5 Stellen erlaubt";
        super.setAnzahlBytes(5);
    }

    /**
     * Erzeugt ein neues VUNummer-Objekt.
     * 
     * @param nr VU-Nummer (max. 5 Stellen)
     * @param start Start-Byte (beginnend bei 1)
     */
    public VUNummer(final VUNummer nr, final int start) {
        super(VU_NUMMER, 5, start, nr.getInhalt());
    }
    
    /**
     * Instantiiert ein neues VUNummer-Objekt.
     * 
     * @param name Bezeichner
     * @param info mit der Start-Adresse und weiteren Angaben
     * @since 0.6
     */
    public VUNummer(final String name, final FeldInfo info) {
        this(name, info.anzahlBytes(), info.byteAdresse());
    }

    /**
     * Der eigentliche Default-Konstruktor fuer alle Feld-Derivate.
     *
     * @param name muss immer "VU-Nummer" sein
     * @param length die Laenge (ueblicherweise 5)
     * @param start die Start-Adresse (ueblicherweise 5)
     * @since 0.6
     */
    public VUNummer(final String name, final int length, final int start) {
        super(name, length, start);
        if (!VU_NUMMER.equalsIgnoreCase(name)) {
            throw new IllegalArgumentException(VU_NUMMER + " (not '" + name + "') expected as 1st argument");
        }
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.getInhalt() + " ("
                + this.getByteAdresse() + "-" + this.getEndAdresse() + ")";
    }

}

