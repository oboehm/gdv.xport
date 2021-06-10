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

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ValidationException;
import java.util.Arrays;

/**
 * Der SatzTyp fuehrt Satzart, Sparte, Wagnisart und laufende Nummer eines
 * Teildatensatz zusammen. Sie wird von der {@link SatzFactory} fuer die
 * Registrierung verwendet.
 * <p>
 * Mit v5.0 wurde die Klasse einem kompletten Refactoring unterzogen, da sich
 * zuviele Redundanzen eingeschlichen haben.
 * </p>
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

	private static final Validator VALIDATOR = new Validator();
	private final short[] teil;

	/**
	 * Damit laesst sich ein SatzTyp anhand der entsprechenden String-
	 * Repraesentation erzeugen.
	 *
	 * @param nr z.B. "0210.050"
	 * @return der entsprechende SatzTyp
	 * @since 5.0
	 */
	public static SatzTyp of(String nr)  {
		return new SatzTyp(nr);
	}

	/**
	 * Anhand der übergebenen Zahlen wird der entsprechende SatzTyp aufgebaut.
	 *
	 * @param args the args
	 * @return the satz typ
	 * @since 5.0
	 */
	public static SatzTyp of(int... args) {
		switch(args.length) {
			case 1:
				return of(String.format("%04d", args[0]));
			case 2:
				return of(String.format("%04d.%03d", args[0], args[1]));
			case 3:
				return of(String.format("%04d.%03d.%d", args[0], args[1], args[2]));
			case 4:
				return of(String.format("%04d.%03d.%d.%d", args[0], args[1], args[2], args[3]));
			default:
				throw new IllegalArgumentException("1 - 4 arguments expected, not " + args.length);
		}
	}

	/**
	 * Damit laesst sich ein SatzTyp anhand der entsprechenden String-
	 * Repraesentation erzeugen.
	 *
	 * @param nr z.B. "0210.050"
	 */
	public SatzTyp(String nr) {
		this(toIntArray(nr));
	}

	private static int[] toIntArray(String nr) {
		String[] parts = StringUtils.split(nr, '.');
		int[] array = new int[parts.length];
		try {
			for (int i = 0; i < parts.length; i++) {
				array[i] = Integer.parseInt(parts[i]);
			}
			return array;
		} catch (NumberFormatException ex)  {
			throw new IllegalArgumentException("kein Satz-Typ: '" + nr + "'", ex);
		}
	}

	/**
	 * Damit laesst sich ein SatzTyp anhand der Einzelteile zusammensetzen.
	 *
	 * @param args z.B. 0210, 050
	 */
	public SatzTyp(int... args) {
		this.teil = createArray(VALIDATOR.verify(args));
	}

	private static short[] createArray(int[] args) {
		short[] array = new short[args.length];
		for (int i = 0; i < args.length; i++) {
			array[i] = (short) args[i];
		}
		int satzart = array[0];
		if (((satzart == 210 ) || (satzart == 211 ) || (satzart == 220 )|| (satzart == 221)) && (args.length < 2)) {
			array = ArrayUtils.add(array, (short) 0);
		}
		if ((array.length == 2) && (satzart == 220) && (array[1] == 10)) {
			array = ArrayUtils.add(array, (short) 0);
		}
		if ((array.length == 3) && (array[1] == 10) && (array[2] > 0)) {
			array = ArrayUtils.add(array, (short) 1);
		}
		return array;
	}

	/**
	 * Gets the satzart.
	 *
	 * @return the satzart
	 */
	public int getSatzart() {
		return teil[0];
	}

	/**
	 * Gets the sparte.
	 *
	 * @return the sparte
	 */
	public int getSparte() {
		return teil[1];
	}

	/**
	 * Liefert die Sparte als String.
	 *
	 * @return z.B. "030"
	 * @since 5.0
	 */
	public String getSparteAsString()
	{
		return Integer.toString(this.getSparte());
	}

	/**
	 * Liefert die Sparte zusammen mit der Art (falls vorhanden).
	 *
	 * @return z.B. "010.2"
	 */
	public String getSparteMitArt() {
		StringBuilder buf = new StringBuilder();
		if (hasSparte()) {
			buf.append(String.format("%03d", this.getSparte()));
			if (this.hasArt()) {
				buf.append(".");
				buf.append(this.getArtAsString());
			}
		}
		return buf.toString();
	}

	/**
	 * Gets the wagnisart.
	 *
	 * @return the wagnisart
	 */
	public int getWagnisart() {
		assertTrue("Wagnisart", hasWagnisart());
		return teil[2];
	}

	/**
	 * Liefert die Wagnisart als String.
	 *
	 * @return z.B. "9"
	 * @since 5.0
	 */
	public String getWagnisartAsString() {
		return Integer.toString(this.getWagnisart());
	}

	/**
	 * Liefert die BausparenArt zurueck. Dies ist bei SatzTyp "0220.580.01" der letzte
	 * Teil ("01"). Diese Methode macht nur bei den Satz-Typen
	 * "0220.580.01" und "0220.580.2" Sinn.
	 *
	 * @return z.B. 1 bei SatzTyp "0220.580.01"
	 */
	public int getBausparenArt() {
		assertTrue("BausparenArt", hasBausparenArt());
		return teil[2];
	}

	/**
	 * Liefert die BausparenArt zurueck. Dies ist bei SatzTyp "0220.580.01" der letzte
	 * Teil ("01"). Diese Methode macht nur bei den Satz-Typen
	 * "0220.580.01" und "0220.580.2" Sinn.
	 *
	 * @return z.B. "01" bei SatzTyp "0220.580.01"
	 */
	public String getBausparenArtAsString() {
		if (!this.hasBausparenArt()) {
			return "";
		}
		if (this.getBausparenArt() == 1) {
			return "01";
		} else {
			return Integer.toString(this.getBausparenArt());
		}
	}

	/**
	 * Liefert die Wagnisart, BausparenArt oder KrankenFolgeNr zurueck.
	 * Dies ist der dritte Teil nach der Sparte, als z.B. die 0 bei
	 * SatzTyp.of("0220.010.0").
	 *
	 * @return z.B. 1 bei SatzTyp "0220.580.01"
	 */
	public int getArt() {
		assertTrue("Art", hasArt());
		if (this.getSparte() == 10) {
			switch (this.getWagnisart()) {
				case 1:
				case 3:
					return 13;
				case 4:
				case 8:
					return 48;
				default:
					return this.getWagnisart();
			}
		} else if (this.getSparte() == 20) {
			return this.getKrankenFolgeNr();
		} else if (this.getSparte() == 580) {
			return this.getBausparenArt();
		}
		return -1;
	}

	/**
	 * Liefert die Wagnisart, BausparenArt oder KrankenFolgeNr als String
	 * zurueck. Dies ist der dritte Teil nach der Sparte, als z.B. die
	 * "0" bei SatzTyp.of("0220.010.0").
	 *
	 * @return z.B. "01" bei SatzTyp "0220.580.01"
	 */
	public String getArtAsString() {
		if (this.hasBausparenArt() && this.getBausparenArt() == 1) {
			return "01";
		} else {
			return Integer.toString(this.getArt());
		}
	}

	/**
	 * Liefert true oder false zurueck, je nachdem, ob der SatzTyp eine Art
	 * hat. Dies ist z.B. bei den Satz-Typen 0220.580.01" und "0220.580.2"
	 * der Fall.
	 *
	 * @return true oder false
	 */
	public boolean hasArt() {
		return this.hasWagnisart() || this.hasBausparenArt() || this.hasKrankenFolgeNr();
	}

	/**
	 * Gets the krankenFolgeNr.
	 *
	 * @return the krankenFolgeNr
	 */
	public int getKrankenFolgeNr() {
		assertTrue("KrankenFolgeNr", hasKrankenFolgeNr());
		return teil[2];
	}

	/**
	 * Dies ist die laufende Nummer bei der Wagnisart.
	 *
	 * @return the lfd nummer
	 */
	public int getTeildatensatzNummer() {
		assertTrue("TeildatensatzNummer", hasTeildatensatzNummer());
		return teil.length > 3 ? teil[3] : 0;
	}

	/**
	 * Liefert true zurueck, wenn die Sparte gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasSparte() {
		return teil.length > 1 && teil[1] > 0;
	}

	public boolean hasParent() {
		return StringUtils.countMatches(this.toString(), '.') > 0;
	}

	public SatzTyp getParent() {
		String parent = StringUtils.substringBeforeLast(this.toString(), ".");
		return SatzTyp.of(parent);
	}

	/**
	 * Liefert true zurueck, wenn die Wagnisart gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasWagnisart() {
		return teil.length > 2 && teil[2] >= 0;
	}

	/**
	 * Liefert true zurueck, wenn die Folge-Nr in Sparte 20, Satzart 220 gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasKrankenFolgeNr() {
		return teil.length > 2 && teil[2] >= 0 && getSparte() == 20;
	}

	/**
	 * Liefert true zurueck, wenn die Bausparen-Artin Sparte 580, Satzart 220
	 * gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasBausparenArt() {
		return getSatzart() == 220 && getSparte() == 580;
	}

	/**
	 * Liefert true zurueck, wenn die laufende Nummer (fuer Wagnisart) gesetzt
	 * ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasTeildatensatzNummer() {
		return teil.length > 3;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
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
		return this.toString().equals(obj.toString());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(String.format("%04d", this.getSatzart()));
		if (this.hasSparte()) {
			buf.append(".");
			buf.append(getSparteMitArt());
			if (this.hasTeildatensatzNummer()) {
				buf.append(".");
				buf.append(this.getTeildatensatzNummer());
			}
		}
		return buf.toString();
	}

	private void assertTrue(String attribute, boolean condition) {
		if (!condition) {
			throw new IllegalArgumentException(this + " hat keine " + attribute);
		}
	}



	public static class Validator {

		/**
		 * Ein gueltiger SatzTyp besteht aus 1 bis 4 Teilen.
		 *
		 * @param args Array, das ueberprueft wird
		 * @return das Array selber (zur Weiterverarbeitung)
		 */
		public int[] validate(int[] args) {
			validateLength(args, 4);
			switch(args[0]) {
				case 1:
				case 9999:
					validateLength(args, 1);
					break;
				case 52:
				case 100:
				case 102:
				case 200:
				case 202:
				case 210:
				case 230:
				case 260:
				case 270:
				case 280:
				case 291:
				case 292:
				case 293:
				case 294:
				case 295:
				case 300:
				case 342:
				case 350:
				case 352:
				case 362:
				case 372:
				case 382:
				case 390:
				case 392:
				case 400:
				case 410:
				case 420:
				case 430:
				case 450:
				case 500:
				case 550:
				case 600:
				case 9950:
				case 9951:
				case 9952:
					validateLength(args, 2);
					break;
				case 220:
					validateSatzart0220(args);
					break;
        case 221:
          validateSatzart0221(args);
          break;
			}
			return args;
		}

		private static void validateLength(int[] args, int max) {
			if ((args.length < 1) || (args.length > max)) {
				throw new ValidationException("array " + Arrays.toString(args) + ": expected size is 1.." + max);
			}
		}

		private void validateSatzart0220(int[] args) {
			if (args.length > 1) {
				switch (args[1]) {
					case 0:
					case 30:
					case 40:
					case 51:
					case 52:
					case 53:
					case 54:
					case 55:
					case 59:
					case 70:
					case 80:
					case 110:
					case 170:
					case 190:
					case 510:
					case 550:
					case 560:
					case 570:
					case 684:
						validateLength(args, 2);
						break;
					case 20:
					case 580:
						validateLength(args, 3);
						break;
				}
			}
		}

    private void validateSatzart0221(int[] args)
    {
      if (args.length > 1)
      {
        switch (args[1])
        {
          case 0:
          case 30:
          case 40:
          case 51:
          case 52:
          case 53:
          case 54:
          case 55:
          case 59:
          case 70:
          case 80:
          case 110:
          case 130:
          case 140:
          case 170:
          case 190:
          case 510:
          case 550:
          case 560:
          case 570:
          case 684:
            validateLength(args, 2);
            break;
        }
      }
    }

		/**
		 * Der Unterschied zu validate liegt nur in der ausgeloesten Exception.
		 *
		 * @param args Array, das ueberprueft wird
		 * @return das Array selber (zur Weiterverarbeitung)
		 */
		public int[] verify(int[] args) {
			try {
				return validate(args);
			} catch (ValidationException ex) {
				throw new IllegalArgumentException(ex);
			}
		}

	}

}
