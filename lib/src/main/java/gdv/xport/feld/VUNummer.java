/*
 * Copyright (c) 2009 - 2012 by Oli B.
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
 * (c)reated 09.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import gdv.xport.satz.feld.common.Feld1bis7;

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
     * @since 0.9
     */
    public VUNummer() {
        super(Feld1bis7.VU_NUMMER);
    }

    /**
     * Erzeugt ein neues VUNummer-Objekt.
     *
     * @param nr VU-Nummer (max. 5 Stellen)
     */
    public VUNummer(final String nr) {
        this();
        assert nr.length() <= 5 : "nur max. 5 Stellen erlaubt";
        this.setInhalt(nr);
        super.setAnzahlBytes(5);
    }

    /**
     * Dies ist der Copy-Constructor, mit dem man ein bestehendes Feld
     * kopieren kann.
     *
     * @param other das originale Feld
     */
    public VUNummer(final VUNummer other) {
        super(other);
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.getInhalt() + " ("
                + this.getByteAdresse() + "-" + this.getEndAdresse() + ")";
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.AlphaNumFeld#clone()
     */
    @SuppressWarnings("squid:S2975")
    @Override
    public Object clone() {
        return new VUNummer(this);
    }

}

