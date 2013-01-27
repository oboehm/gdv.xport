/*
 * Copyright (c) 2012 by Oli B.
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
 * (c)reated 26.01.2013 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import java.text.DecimalFormat;


// TODO: Auto-generated Javadoc
/**
 * Die SatzNummer fuehrt Satzart, Sparte, Wagnisart und laufende Nummer eines
 * Teildatensatz zusammen. Sie wird von der {@link SatzFactory} fer die
 * Registrierung verwendet.
 *
 * @author oliver
 * @since 0.9 (26.01.2013)
 */
public final class SatzNummer {

    /** The satzart. */
    private final int satzart;

    /** The sparte. */
    private final int sparte;

    /** The wagnisart. */
    private final int wagnisart;

    /** The lfd nummer. */
    private final int lfdNummer;

    /**
     * Instantiates a new satz nummer.
     *
     * @param satzart the satzart
     */
    public SatzNummer(final int satzart) {
        this(satzart, -1);
    }

    /**
     * Instantiates a new satz nummer.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     */
    public SatzNummer(final int satzart, final int sparte) {
        this(satzart, sparte, -1);
    }

    /**
     * Instantiates a new satz nummer.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @param wagnisart the wagnisart
     */
    public SatzNummer(final int satzart, final int sparte, final int wagnisart) {
        this(satzart, sparte,  wagnisart,  -1);
    }

    /**
     * Legt eine neue SatzNummer an.
     *
     * @param satzart die Satzart (vierstellig)
     * @param sparte die Sparte (dreistellig)
     * @param wagnisart die Wagnisart (ein- bis zweisstellig)
     * @param lfdNummer die laufende Nummer (Teildatensatz-Nummer)
     */
    public SatzNummer(final int satzart, final int sparte, final int wagnisart, final int lfdNummer) {
		assert (0 <= satzart) && (satzart <= 9999) :
			"Satzart " + satzart + " muss zwischen 0 und 9999 liegen";
        assert (sparte == -1) || ((0 <= sparte) && (sparte <= 999)) :
        	"Sparte " + sparte + " muss zwischen 0 und 999 liegen";
        assert (wagnisart == -1) || ((0 <= wagnisart) && (wagnisart <= 9)) :
        	"Wagnisart " + wagnisart + " muss zwischen 0 und 9 liegen";
        assert (lfdNummer == -1) || ((0 <= lfdNummer) && (lfdNummer <= 9)) :
        	"teildatensatzNummer " + lfdNummer + " muss zwischen 0 und 9 liegen";
        this.satzart = satzart;
        this.sparte = sparte;
        this.wagnisart = wagnisart;
        this.lfdNummer = lfdNummer;
    }

    /**
     * Gets the satzart.
     *
     * @return the satzart
     */
    public int getSatzart() {
        return this.satzart;
    }

    /**
     * Gets the sparte.
     *
     * @return the sparte
     */
    public int getSparte() {
        return this.sparte;
    }

    /**
     * Gets the wagnisart.
     *
     * @return the wagnisart
     */
    public int getWagnisart() {
        return this.wagnisart;
    }

    /**
     * Gets the lfd nummer.
     *
     * @return the lfd nummer
     */
    public int getLfdNummer() {
        return this.lfdNummer;
    }

	/**
	 * Liefert true zurueck, wenn die Sparte gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasSparte() {
	    return this.sparte >= 0;
    }

	/**
	 * Liefert true zurueck, wenn die Wagnisart gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasWagnisart() {
		return this.wagnisart >= 0;
	}

	/**
	 * Liefert true zurueck, wenn die laufende Nummer (Teildatensatz-Nummer)
	 * gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasLfdNummer() {
		return this.lfdNummer >= 0;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return satzart * 1000000 + sparte * 1000 + wagnisart * 100 + lfdNummer;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SatzNummer)) {
            return false;
        }
        SatzNummer other = (SatzNummer) obj;
        return (this.satzart == other.satzart) && (this.sparte == other.sparte) && (this.wagnisart == other.wagnisart)
                && (this.lfdNummer == other.lfdNummer);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(new DecimalFormat("0000").format(this.satzart));
        if (this.sparte >= 0) {
            buf.append("." + new DecimalFormat("000").format(this.sparte));
            if (this.wagnisart >= 0) {
                buf.append("." + this.wagnisart);
                if (this.lfdNummer >= 0) {
                    buf.append("." + this.lfdNummer);
                }
            }
        }
        return buf.toString();
    }

}
