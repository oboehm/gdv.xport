/*
 * Copyright (c) 2013 by Oli B.
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

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;

/**
 * Der SatzTyp fuehrt Satzart, Sparte, Wagnisart und laufende Nummer eines
 * Teildatensatz zusammen. Sie wird von der {@link SatzFactory} fuer die
 * Registrierung verwendet.
 * <p>
 * Vorher hiess diese Klasse "SatzNummer", wurde aber mit 1.1 in SatzType
 * umbenannt, da "Satznummer" als Klassenname etwas irritierend ist, da es ein
 * Feld "Satznummer" innerhalb eines Satzes bereits gibt.
 * </p>
 *
 * @author oliver
 * @since 0.9 (26.01.2013)
 */
public class SatzTyp {

	/** The satzart. */
	private final int satzart;

	/** The sparte. */
	private final int sparte;

	/** The wagnisart. */
	private final int wagnisart;
	
	/** Kranken, Folgenummer */
	private final int krankenFolgeNr;

	/** The lfd nummer. */
	private final int teildatensatzNummer;

	/** Bausparen, bausparenArt */
	private final int bausparenArt;

	/**
	 * Damit laesst sich ein SatzTyp anhand der entsprechenden String-
	 * Repraesentation erzeugen.
	 *
	 * @param nr z.B. "0210.050"
	 * @return der entsprechende SatzTyp
	 * @since 4.3
	 */
	public static SatzTyp of(String nr) {
		int[] numbers = { -1, -1, -1, -1 };
		try {
			String[] parts = StringUtils.split(nr, '.');
			for (int i = 0; i < parts.length; i++) {
				numbers[i] = Integer.parseInt(parts[i]);
			}
			if (numbers[1] == 20) {
				//bei Kranken muss krankenFolgeNr belegt werden
				return new SatzTyp(numbers[0], numbers[1], numbers[2], numbers[2], -1);
			} else if (numbers[1] == 580) {
				// bei Bausparen muss bausparenArt belegt werden
				return new SatzTyp(numbers[0], numbers[1], numbers[2], -1, -1, numbers[2]);
			} else {
				return new SatzTyp(numbers[0], numbers[1], numbers[2], numbers[3]);
			}
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("kein Satz-Typ: '" + nr + "'", ex);
		}
	}

	/**
	 * Instantiates a new satz nummer.
	 *
	 * @param satzart the satzart
	 */
	public SatzTyp(final int satzart) {
		this(satzart, -1);
	}

	/**
	 * Instantiates a new satz nummer.
	 *
	 * @param satzart Satzart
	 * @param sparte Sparte
	 */
	public SatzTyp(final int satzart, final int sparte) {
		this(satzart, sparte, -1);
	}

	/**
	 * Instantiates a new satz nummer.
	 *
	 * @param satzart the satzart
	 * @param sparte the sparte
	 * @param artFolgeNr Wagnisart (Sparte 10) bzw. krankenFolgeNr (Sparte 20) bzw. bausparenArt (Sparte 580, Satzart 220 (Wert 1 - 2))
	 * @deprecated wurde ersetzt durch {@link #of(String)}
	 */
	@Deprecated
	public SatzTyp(final int satzart, final int sparte, final int artFolgeNr) {
		this(satzart, sparte, artFolgeNr, -1);
		// Alternativ: (scheitert aktuell daran, dass Attribute 'final' sind)
//		if (sparte == 20) {
//			this(satzart, sparte, -1, artFolgeNr, -1);
//		} else if (sparte == 580) {
//			this(satzart, sparte, -1, -1, -1, artFolgeNr);
//		} else {
//			this(satzart, sparte, artFolgeNr, -1);
//		}
	}
	
	/**
	 * Legt eine neue SatzNummer an.
	 * 
     * @param satzart die Satzart (vierstellig)
     * @param sparte die Sparte (dreistellig)
     * @param wagnisart die Wagnisart (ein- bis zweisstellig)
     * @param lfdNummer die laufende Nummer (Teildatensatz-Nummer)
	 * @deprecated wurde ersetzt durch {@link #of(String)}
	 */
	@Deprecated
	public SatzTyp(final int satzart, final int sparte, final int wagnisart, final int lfdNummer) {
	    this(satzart, sparte, wagnisart, -1, lfdNummer);
	}
	
	/**
	 * Legt eine neue SatzNummer an.
	 *
	 * @param satzart die Satzart (vierstellig)
	 * @param sparte die Sparte (dreistellig)
	 * @param wagnisart die Wagnisart (ein- bis zweisstellig)
	 * @param krankenFolgeNr Folge-Nr. aus Sparte 20, Satzart 220 (Wert 1-3)
	 * @param lfdNummer die laufende Nummer (Teildatensatz-Nummer)
	 * @deprecated wurde ersetzt durch {@link #of(String)}
	 */
	@Deprecated
	public SatzTyp(final int satzart, final int sparte, final int wagnisart, final int krankenFolgeNr, final int lfdNummer) {
      this(satzart, sparte,  wagnisart, krankenFolgeNr, lfdNummer, -1);
  }

	/**
	 * Legt eine neue SatzNummer an.
	 *
	 * @param satzart die Satzart (vierstellig)
	 * @param sparte die Sparte (dreistellig)
	 * @param wagnisart die Wagnisart (ein- bis zweisstellig)
	 * @param krankenFolgeNr Folge-Nr. aus Sparte 20, Satzart 220 (Wert 1-3)
	 * @param lfdNummer die laufende Nummer (Teildatensatz-Nummer)
	 * @param bausparenArt die Art bei Sparte 580, Satzart 220 (Wert 1 - 2)
	 * @since 4.X
	 */
	private SatzTyp(final int satzart, final int sparte, final int wagnisart, final int krankenFolgeNr, final int lfdNummer, final int bausparenArt) {
		assert (0 <= satzart) && (satzart <= 9999) : "Satzart " + satzart
		        + " muss zwischen 0 und 9999 liegen";
		assert (sparte == -1) || ((0 <= sparte) && (sparte <= 999)) : "Sparte " + sparte
		        + " muss zwischen 0 und 999 liegen";
		assert (wagnisart == -1) || ((0 <= wagnisart) && (wagnisart <= 9)) || (wagnisart == 13) || (wagnisart == 48) :
				"Wagnisart " + wagnisart + " muss zwischen 0 und 9 liegen";
		assert (krankenFolgeNr == -1) || ((1 <= krankenFolgeNr) && (krankenFolgeNr <= 3)) : "Kranken Folge-Nr. "
		        + krankenFolgeNr + " muss zwischen 1 und 3 liegen";
		assert (lfdNummer == -1) || ((0 <= lfdNummer) && (lfdNummer <= 9)) : "teildatensatzNummer "
		        + lfdNummer + " muss zwischen 0 und 9 liegen";
		assert (bausparenArt == -1) || ((0 <= bausparenArt) && (bausparenArt <= 9)) : "bausparenArt "
		        + bausparenArt + " muss zwischen 0 und 9 liegen";
		this.satzart = satzart;
		this.sparte = sparte;
		this.wagnisart = wagnisart;
		this.krankenFolgeNr = krankenFolgeNr;
		this.teildatensatzNummer = lfdNummer;
		this.bausparenArt = bausparenArt;
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
	 * Liefert die BausparenArt zurueck. Dies ist bei SatzTyp "0220.580.01" der letzte
	 * Teil ("01"). Diese Methode macht nur bei den Satz-Typen
	 * "0220.580.01" und "0220.580.2" Sinn.
	 *
	 * @return z.B. 1 bei SatzTyp "0220.580.01"
	 * @since 4.3
	 */
	public int getBausparenArt() {
		return this.bausparenArt;
	}

	/**
	 * Liefert die BausparenArt zurueck. Dies ist bei SatzTyp "0220.580.01" der letzte
	 * Teil ("01"). Diese Methode macht nur bei den Satz-Typen
	 * "0220.580.01" und "0220.580.2" Sinn.
	 *
	 * @return z.B. "01" bei SatzTyp "0220.580.01"
	 * @since 4.3
	 */
	public String getBausparenArtAsString() {
		if (this.getBausparenArt() < 0) {
			return "";
		}
		if (this.getBausparenArt() == 1) {
			return "01";
		} else {
			return Integer.toString(this.getBausparenArt());
		}
	}

	/**
	 * Liefert die Wagnisart, BausparenArt oder KrankenFolgeNr als String
	 * zurueck. Dies ist der dritte Teil nach der Sparte, als z.B. die
	 * "0" bei SatzTyp.of("0220.010.0").
	 *
	 * @return z.B. "01" bei SatzTyp "0220.580.01"
	 * @since 4.3
	 */
	public String getArt() {
		if (this.wagnisart < 0) {
			return "";
		} else if ((this.wagnisart == 1) && (this.sparte == 580)) {
			return "01";
		} else if (this.sparte == 10) {
			switch (this.wagnisart) {
				case 1:
				case 3:
					return "13";
				case 4:
				case 8:
					return "48";
			}
		}
		return Integer.toString(this.wagnisart);
	}

	/**
	 * Liefert true oder false zurueck, je nachdem, ob der SatzTyp eine Art
	 * hat. Dies ist z.B. bei den Satz-Typen 0220.580.01" und "0220.580.2"
	 * der Fall.
	 *
	 * @return true oder false
	 * @since 4.3
	 */
	public boolean hasArt() {
		return (this.wagnisart >= 0) || (this.bausparenArt >= 0) || (this.krankenFolgeNr >= 0);
	}
	
	/**
	 * Gets the krankenFolgeNr.
	 * 
	 * @return the krankenFolgeNr
	 */
	public int getKrankenFolgeNr() {
	    return this.krankenFolgeNr;
	}

	/**
	 * Gets the lfd nummer.
	 *
	 * @return the lfd nummer
	 */
	public int getTeildatensatzNummer() {
		return this.teildatensatzNummer;
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
	 * Liefert true zurueck, wenn die Folge-Nr in Sparte 20, Satzart 220 gesetzt ist.
	 * 
	 * @return true, if successful
	 */
	public boolean hasKrankenFolgeNr() {
	    return this.krankenFolgeNr >= 0;
	}

	/**
	 * Liefert true zurueck, wenn die laufende Nummer (Teildatensatz-Nummer)
	 * gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasTeildatensatzNummer() {
		return this.teildatensatzNummer >= 0;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return satzart * 10000000 + sparte * 10000 + krankenFolgeNr * 100 + teildatensatzNummer;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SatzTyp)) {
			return false;
		}
		SatzTyp other = (SatzTyp) obj;
		return (this.satzart == other.satzart) && (this.sparte == other.sparte)
		        && (this.getArt().equals(other.getArt()))
		        && (this.krankenFolgeNr == other.krankenFolgeNr)
		        && (this.teildatensatzNummer == other.teildatensatzNummer);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(new DecimalFormat("0000").format(this.satzart));
		if (this.sparte >= 0) {
			buf.append("." + new DecimalFormat("000").format(this.sparte));
			if (this.hasArt()) {
				buf.append(".");
				buf.append(this.getArt());
				if (this.teildatensatzNummer >= 0) {
					buf.append(".");
	                buf.append(this.teildatensatzNummer);
				}
			} else if (this.krankenFolgeNr >= 0) {
                buf.append(".");
                buf.append(this.krankenFolgeNr);
			}
		}
		return buf.toString();
	}

}
