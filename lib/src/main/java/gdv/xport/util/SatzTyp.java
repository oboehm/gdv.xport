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
 * Der SatzTyp ist eine Repraesentation des Namens einer GDV-Satzdefinition bzw. seiner Bestandteile.
 * </br>
 * Der Aufbau des <b>GDV-Satzartnames</b> folgt dem Schema
 * &lt;satzart&gt;[.&lt;sparte&gt;[.&lt;art&gt;[.&lt;gdvsatzartnummer&gt;]]].</br>
 * (siehe auch {{@link #getGdvSatzartName()})
 * <p/>
 * Näheres findet sich unter "online-version" z.B. hier (rel 01.07.2018):</br>
 * "http://www.gdv-online.de/vuvm/bestand/best_2018.htm"
 * <p>
 * Es gilt:
 * <ul>
 * <li><b>&lt;satzart&gt; </b>: 1. Teil aus 4 Ziffern z.B. "0200" bzw. "0100" bzw. "0220"</li>
 * <li><b>&lt;sparte&gt; </b>: 2. Teil aus 3 Ziffern z.B. "000" bzw. "010" bzw."030" bzw "580". Nur
 * bei Satzarten "02XX" </br>
 * (z.B. "0210", "0211", "0220", "0221" u.a.) ! </br>
 * Achtung: nicht verwechseln mit der "GDV-Sparte" als Inhalt von Feld 4 (Adresse 11-13) eines
 * Teildatensatzes!</br>
 * Nur in wenigen vordefinierten Spartensaetzen entspricht &lt;sparte&gt; der GDV-Sparte (z.B.
 * GDV-Sparte "040" in "0210.040").</br>
 * Die meisten GDV-Sparten werden ueber gemeinsame Satzdefinitionen abgebildet.</br>
 * Beispiel: GDV-Sparten "296" und "299" werden gemäß GDV-Satzarten "0210.000", "0211.000",
 * "0220.000", "0221.000" beschrieben. </br>
 * GDV-Sparten "080", "081", "082, "083" und "089" werden über GDV-Satzarten "0210.080", "0211.080",
 * "0220.080", "0221.080" beschrieben.</br>
 * Weitere Infos liefern finden sich z.B. hier (rel 01.07.2018):
 * "http://www.gdv-online.de/vuvm/bestand/rel2018/anl1.htm"</li>
 * <li><b>&lt;art&gt; </b>: 3. Teil aus 1-2 Ziffern, Bedeutung ist abhängig von &lt;sparte&gt;:
 * <ul>
 * <li>&lt;sparte&gt; <b>"010"</b> (Leben): Wagnisart "13", "2", "48", "5", "6", "7", "9". </br>
 * Achtung: nicht verwechseln mit "GDV-Wagnisart" als Inhalt von Feld 9 (Adresse 60-60) eines
 * Teildatensatzes der </br>
 * "02XX"er-Satzarten fuer Leben (GDV-Sparte "010")! "13" bzw. "48" steht jeweils als Abkürzung fuer
 * "GDV-Wagnisart 1 und 3" bzw. "GDV-Wagnisart 4 und 8".</li>
 * <li>&lt;sparte&gt; <b>"020"</b> (Kranken): KrankenfolgeNummer "1", "2", "3" im Feld 10 (Adresse
 * 48-48) des Teildatensatzes. Nur bei Satzart "0220.020.1", "0220.020.2", "0220.010.3".</li>
 * <li>&lt;sparte&gt; <b>"580"</b>; (Bausparen): Bausparart "01", "2" im Feld 9 (Adresse 44-44) bei
 * Satzart "0220.580.01", "0220.580.2". "01" steht als Abkürzung fuer Bausparart "0" und "1".</li>
 * </ul>
 * Weitere Infos liefern finden sich hier: "http://www.gdv-online.de/vuvm/bestand/best_2018.htm"
 * </li>
 * <li><b>&lt;gdvsatzartnummer&gt; </b>: 4. Teil aus 1 Ziffer (nur fuer &lt;sparte&gt; <b>"010"</b>
 * in den Satzarten "0220" bzw. "0221"). Wird benoetigt, um zu unterscheiden zwischen</br>
 * 'Standard' ("1") und den Erweiterungen 'Bezugsrechte' ("6"), 'Auszahlung' ("7"), zukünftige
 * Summenänderung' ("8") und 'Wertungssummen' ("9").</br>
 * Achtung: die Erweiterungen bestehen jeweils aus 1 Teildatensatz mit Satznummer =
 * &lt;gdvsatzartnummer&gt; !</li>
 * </ul>
 * </ul>
 * <p>
 * Mit v5.0 wurde die Klasse einem kompletten Refactoring unterzogen, da sich zuviele Redundanzen
 * eingeschlichen haben.
 * <p>
 * Vorher hiess diese Klasse "SatzNummer", wurde aber mit 1.1 in SatzTyp
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

  // Stand: seit Release 01.07.2013
  private static final int[] spartenIdentischZu_000 = { 60, 63, 65, 69, 160, 161, 162, 169, 233,
      240, 241, 242, 243, 249, 250, 251, 252, 290, 291, 293, 294, 296, 299, 630, 650 };

  // Stand: seit Release 01.07.2013
  private static final int[] spartenIdentischZu_080 =
      { 80, 81, 82, 83, 89, 90, 99, 100, 109, 120, 123, 124, 150, 210, 230, 231 };

  // Stand: seit Release 01.07.2013
  private static final int[] spartenIdentischZu_170 = { 170, 171, 172, 174, 175, 176, 179, 232 };

  // Stand: seit Release 01.07.2013
  private static final int[] spartenIdentischZu_190 =
      { 180, 181, 182, 183, 184, 185, 189, 190, 191, 192, 193, 194, 197, 199 };

  // Stand: seit Release 01.07.2013
  private static final int[] spartenIdentischZu_510 = { 241, 244, 510 };

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
   * Anhand der übergebenen Zahlen wird der entsprechende SatzTyp aufgebaut.</br>
   * Es gilt: &lt;satzart&gt;[.&lt;sparte&gt;[.&lt;art&gt;[.&lt;gdvsatzartnummer&gt;]]]
	 *
   * @param args the args (max. Anzahl 4)
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

	private SatzTyp(String nr) {
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
	 * <p>
	 * TODO: 'private' machen
	 * </p>
	 *
	 * @param args z.B. 0210, 050
	 * @deprecated bitte {@link SatzTyp#of(int...)} verwenden
	 */
	@Deprecated
	public SatzTyp(int... args) {
		this.teil = createArray(VALIDATOR.verify(args));
	}

	private static short[] createArray(int[] args) {
		short[] array = new short[args.length];
		for (int i = 0; i < args.length; i++) {
			array[i] = (short) args[i];
		}
		int satzart = array[0];
		if ((array.length == 2) && (satzart == 220) && (array[1] == 10)) {
			array = ArrayUtils.add(array, (short) 0);
		}
    if ((array.length == 3) && (satzart == 220 || satzart == 221) && (array[1] == 10) && (array[2] > 0)) {
			array = ArrayUtils.add(array, (short) 1);
		}
		return array;
	}

	private static boolean isAllgemeineSatzart(int satzart) {
		return (satzart == 210) || (satzart == 211) || (satzart == 220) || (satzart == 221);
	}

	private boolean isAllgemeineSatzart() {
		return isAllgemeineSatzart(getSatzart());
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
  public String getSparteAsString() {
    return String.format("%03d", this.getSparte());
	}

	/**
	 * Liefert die Sparte zusammen mit der Art (falls vorhanden).
	 *
	 * @return z.B. "010.2"
	 */
	public String getSparteMitArt() {
		StringBuilder buf = new StringBuilder();
		/*
		String[] parts = StringUtils.split(this.getGdvSatzartName(), '.');
		if (parts.length > 1) {
			buf.append(parts[1]);
		 */
		if (hasSparte()) {
			buf.append(getSparteAsString());
			if (this.hasArt()) {
				buf.append(".");
				buf.append(this.getArtAsString());
			}
		}
		return buf.toString();
	}

	/**
   * Gets the wagnisart.</br>
   * <b>Achtung:</b> Anders als bei den Kindklassen von <code>"Satz.java"</code> kann die Wagnisart
   * hier 1- bis 2-stellig sein abhaengig von der Instanziierung dieses "SatzTyp"-Objektes!
	 *
	 * @return the wagnisart
	 */
	public int getWagnisart() {
		assertTrue("Wagnisart", hasWagnisart());
		return teil[2];
	}

	/**
	 * Liefert die Wagnisart als String.
	 * <p>
	 * TODO: abhaengigen Test ueberarbeiten, Methode eintfernen
	 * </p>
	 *
	 * @return z.B. "9"
	 * @since 5.0
	 * @deprecated wird nur zum Testen verwendet
	 */
	@Deprecated
	public String getWagnisartAsString() {
		return Integer.toString(this.getWagnisart());
	}

	/**
   * Liefert die BausparenArt zurueck. Dies ist bei SatzTyp "0220.580.01" der letzte Teil. Diese
   * Methode macht nur bei den Satz-Typen "0220.580.01" und "0220.580.2" Sinn. </br>
   * <b>Achtung:</b> BausparenArt ist immer 1-stellig unabhaengig von der Instanziierung dieses
   * "SatzTyp"-Objektes!
	 *
	 * @return z.B. 1 bei SatzTyp "0220.580.01"
	 */
	public int getBausparenArt() {
		assertTrue("BausparenArt", hasBausparenArt());
		return teil[2];
	}

	/**
   * Liefert die BausparenArt zurueck als 3. Teil des GdvSatzartNamens. </br>
   * Dies ist bei SatzTyp "0220.580.01" der letzte Teil ("01"). Diese Methode macht nur bei den
   * Satz-Typen "0220.580.01" und "0220.580.2" Sinn.
	 *
   * @return z.B. "01" bei SatzTyp "0220.580.01" und "2" bei bei SatzTyp "0220.580.2"
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
	 * zurueck. Dies ist der dritte Teil nach der Sparte, also z.B. die
	 * "0" bei SatzTyp.of("0220.010.0").
	 * <p>
	 * TODO: Tests ueberarbeiten und 'private' machen
	 * </p>
	 *
	 * @return z.B. "01" bei SatzTyp "0220.580.01"
	 * @deprecated wird nur intern bzw. zum Testen verwendet
	 */
	@Deprecated
	public String getArtAsString() {
		if (this.hasBausparenArt() && this.getBausparenArt() == 1) {
			return "01";
		} else {
			return Integer.toString(this.getArt());
		}
	}

	/**
   * Liefert true oder false zurueck, je nachdem, ob der SatzTyp eine Art hat (3. Teil im
   * GdvSatzartNamen). Dies ist z.B. bei den Satz-Typen 0220.580.01" und "0220.580.2" der Fall.
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
   * Dies ist die laufende Nummer bei der Wagnisart (4. Teil im GdvSatzartNamen bei Leben).
   *
   * @return the GdvSatzartNummer
   */
  public int getGdvSatzartNummer() {
    assertTrue("GdvSatzartNummer", hasGdvSatzartNummer());
    return teil.length > 3 ? teil[3] : 0;
  }

  /**
   * Liefert true zurueck, wenn in diesem Objekt die Sparte gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasSparte() {
		return teil.length > 1 && teil[1] > 0;
	}

	public boolean hasParent() {
		return teil.length > 1;
	}

	public SatzTyp getParent() {
		String parent = StringUtils.substringBeforeLast(this.toString(), ".");
		return SatzTyp.of(parent);
	}

	/**
   * Liefert true zurueck, wenn in diesem Objekt die Wagnisart gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasWagnisart() {
    return teil.length > 2 && teil[2] >= 0 && getSparte() == 10;
	}

	/**
   * Liefert true zurueck, wenn in diesem Objekt die Folge-Nr in Sparte 20, Satzart 220 gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasKrankenFolgeNr() {
    return teil.length > 2 && teil[2] > 0 && getSparte() == 20;
	}

	/**
   * Liefert true zurueck, wenn in diesem Objekt die Bausparen-Art in Sparte 580, Satzart 220 gesetzt ist.
	 *
	 * @return true, if successful
	 */
	public boolean hasBausparenArt() {
    return teil.length > 2 && teil[2] >= 0 && getSparte() == 580;
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

	/**
	 * Liefert true zurueck, wenn die GdvSatzartNummer (Teil 4 im GdvSatzartNamen) gesetzt ist (nur
	 * GDV-Sparte "010"!).
	 *
	 * @return true, if successful
	 */
	public boolean hasGdvSatzartNummer() {
		return teil.length > 3;
	}

  /**
   * Gets the GdvSatzartName gemaess Online-Version bei gdv-online.de z.B. "0220.040" oder
   * "0220.010.13.1"
   *
   * @return the GdvSatzartName
   */
  public String getGdvSatzartName() {
	  StringBuilder buf = new StringBuilder();
	  buf.append(String.format("%04d", this.getSatzart()));
	  if (this.getSatzart() >= 210 && this.getSatzart() < 300) {
		  if (this.hasSparte()) {
			  buf.append(".");
			  if (isIdentischZu000(this.getSparte()))
				  buf.append("000");
			  else if (isIdentischZu080(this.getSparte()))
				  buf.append("080");
			  else if (isIdentischZu170(this.getSparte()))
				  buf.append("170");
			  else if (isIdentischZu190(this.getSparte()))
				  buf.append("190");
			  else if (isIdentischZu510(this.getSparte()))
				  buf.append("510");
			  else if (600 == this.getSparte())
				  // lt. GDV-Spartenverseichnis wird Moped wie Sparte 050 behandelt
				  buf.append("050");
			  else {
				  buf.append(this.getSparteAsString());

				  if (this.hasArt()) {
					  buf.append(".");
					  buf.append(this.getArtAsString());
				  }
				  if (this.hasGdvSatzartNummer()) {
					  buf.append(".");
					  buf.append(this.getGdvSatzartNummer());
				  }
			  }
		  } else if (isAllgemeineSatzart()) {
			  buf.append(".000");
		  }
	  }
	  return buf.toString();
  }

  /**
   * Liefert true zurueck, wenn der GdvSatzartName einen Spartenteil enthaelt, z.B. "000" in
   * "0220.000"
   *
   * @return true, if successful
   */
  public boolean hasSparteInGdvSatzartName() {
    return StringUtils.split(this.getGdvSatzartName(), '.').length > 1;
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
		return getGdvSatzartName();
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
        case 211:
        case 212:
        case 222:
        case 225:
				case 230:
        case 250:
        case 251:
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

  /**
   * Sparten, die gemaess Spartenverzeichnis (Anlagen_GDV-Datendatz_VU-Vermittler) nach
   * Satzdefinition vom Allgemeinen Satz geliefert werden.
   */
  private static boolean isIdentischZu000(int sparte) {
    boolean ret = false;

    for (int sparte000 : spartenIdentischZu_000) {
      if (sparte000 == sparte)
        return true;
    }
    return ret;
  }

  /**
   * Sparten, die gemaess Spartenverzeichnis (Anlagen_GDV-Datendatz_VU-Vermittler) nach
   * Satzdefinition vom Feuer-Industrie/Gewerbl. Sachvers. geliefert werden.
   */
  private static boolean isIdentischZu080(int sparte) {
    boolean ret = false;

    for (int sparte000 : spartenIdentischZu_080) {
      if (sparte000 == sparte)
        return true;
    }
    return ret;
  }

  /**
   * Sparten, die gemaess Spartenverzeichnis (Anlagen_GDV-Datendatz_VU-Vermittler) nach
   * Satzdefinition von Technische Versicherung geliefert werden.
   */
  private static boolean isIdentischZu170(int sparte) {
    boolean ret = false;

    for (int sparte170 : spartenIdentischZu_170) {
      if (sparte170 == sparte)
        return true;
    }
    return ret;
  }

  /**
   * Sparten, die gemaess Spartenverzeichnis (Anlagen_GDV-Datendatz_VU-Vermittler) nach
   * Satzdefinition von Transport geliefert werden.
   */
  private static boolean isIdentischZu190(int sparte) {
    boolean ret = false;

    for (int sparte190 : spartenIdentischZu_190) {
      if (sparte190 == sparte)
        return true;
    }
    return ret;
  }

  /**
   * Sparten, die gemaess Spartenverzeichnis (Anlagen_GDV-Datendatz_VU-Vermittler) nach
   * Satzdefinition von Verkehrsservice geliefert werden.
   */
  private static boolean isIdentischZu510(int sparte) {
    boolean ret = false;

    for (int sparte510 : spartenIdentischZu_510) {
      if (sparte510 == sparte)
        return true;
    }
    return ret;
  }
}
