/*
 * Copyright (c) 2009-2020 by Oli B.
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
 * (c)reated 12.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.io.ImportException;
import gdv.xport.io.PushbackLineNumberReader;
import gdv.xport.satz.feld.common.Feld1bis7;
import gdv.xport.satz.feld.common.TeildatensatzNummer;
import gdv.xport.satz.feld.common.WagnisartLeben;
import gdv.xport.util.SatzTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.EOFException;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.List;

import static gdv.xport.feld.Bezeichner.*;

/**
 * Datensatz ist von {@link Satz} abgeleitet, enthaelt aber zusaetzlich noch
 * die Sparte.
 *
 * @author oliver
 * @since 12.10.2009
 */
public class Datensatz extends Satz {

	private static final Logger LOG = LogManager.getLogger(Datensatz.class);
	/** 3 Zeichen, Byte 11 - 13. */
    private final NumFeld sparte = new NumFeld(Feld1bis7.SPARTE);
	/** 1 Zeichen, Byte 59. */
	private final AlphaNumFeld wagnisart = new AlphaNumFeld((WAGNISART), 1, 59);
	/** 1 Zeichen, Byte 256 - 256. */
    private final AlphaNumFeld teildatensatzNummer = new AlphaNumFeld((TEILDATENSATZNUMMER), 1, 256);
	/** Zum Abspeichern der Wagnisart oder Art (Unter-Sparte). */
	private int art;

	/**
	 * Default-Konstruktor (wird zur Registrierung bei der.
	 * {@link gdv.xport.util.SatzFactory} benoetigt).
	 *
	 * @since 0.6
	 */
	public Datensatz() {
		this(SatzTyp.of(0));
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 * <p>
	 * TODO: Wird mit v6 entfernt.
	 * </p>
	 *
	 * @param satzart z.B. "0100"
	 * @deprecated bitte {@link Datensatz#Datensatz(SatzTyp)} verwenden
	 */
	@Deprecated
	public Datensatz(final String satzart) {
		this(SatzTyp.of(satzart));
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 * <p>
	 * TODO: Wird mit v6 entfernt.
	 * </p>
	 *
	 * @param satzart z.B. 100
	 * @deprecated bitte {@link Datensatz#Datensatz(SatzTyp)} verwenden
	 */
	@Deprecated
	public Datensatz(final int satzart) {
		this(SatzTyp.of(satzart));
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 * <p>
	 * TODO: Wird mit v6 entfernt.
	 * </p>
	 *
	 * @param satzart z.B. 100
	 * @param n Anzahl der Teildatensaetze
	 * @deprecated bitte {@link Datensatz#Datensatz(SatzTyp, int)} verwenden
	 */
	@Deprecated
	public Datensatz(final String satzart, final int n) {
		this(SatzTyp.of(satzart), n);
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 * <p>
	 * TODO: Wird mit v6 entfernt.
	 * </p>
	 *
	 * @param satzart z.B. 100
	 * @param tdsList Liste mit den Teildatensaetzen
	 * @deprecated bitte {@link Datensatz#Datensatz(SatzTyp, List)} verwenden
	 */
	@Deprecated
	public Datensatz(final int satzart, final List<? extends Teildatensatz> tdsList) {
		this(SatzTyp.of(satzart), tdsList);
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 * <p>
	 * TODO: Wird mit v6 entfernt.
	 * </p>
	 *
	 * @param satzart z.B. 100
	 * @param sparte z.B. 70 (Rechtsschutz)
	 * @deprecated bitte {@link Datensatz#Datensatz(SatzTyp)} verwenden
	 */
	@Deprecated
	public Datensatz(final int satzart, final int sparte) {
		this(SatzTyp.of(satzart, sparte));
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 *
	 * @param satzTyp z.B. "0100.070" (Adressteil, Rechtsschutz)
	 * @since 5.0
	 */
	public Datensatz(SatzTyp satzTyp) {
		this(satzTyp, 1);
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 * <p>
	 * TODO: Wird mit v6 entfernt.
	 * </p>
	 *
	 * @param satzart z.B. 100
	 * @param sparte z.B. 70 (Rechtsschutz)
	 * @param n Anzahl der Teildatensaetze
	 * @deprecated bitte {@link Datensatz#Datensatz(SatzTyp, int)} verwenden
	 */
	@Deprecated
	public Datensatz(final int satzart, final int sparte, final int n) {
		this(SatzTyp.of(satzart, sparte), n);
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 *
	 * @param satzTyp z.B. "0100.070" (Adressteil, Rechtsschutz)
	 * @param n Anzahl der Teildatensaetze
	 * @since 5.0
	 */
  public Datensatz(final SatzTyp satzTyp, final int n)
  {
    super(satzTyp, n);
    if (satzTyp.hasSparte())
      this.setSparte(satzTyp.getSparte());
		this.setUpTeildatensaetze();
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 * <p>
	 * TODO: Wird mit v6 entfernt.
	 * </p>
	 *
	 * @param satzart z.B. 100
	 * @param sparte z.B. 70 (Rechtsschutz)
	 * @param tdsList Liste mit den Teildatensaetzen
	 * @deprecated bitte {@link Datensatz#Datensatz(SatzTyp, List)} verwenden
	 */
	@Deprecated
	public Datensatz(final int satzart, final int sparte, final List<Teildatensatz> tdsList) {
		this(SatzTyp.of(satzart, sparte), tdsList);
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 *
	 * @param satzNr  die SatzNummer
	 * @param tdsList Liste mit den Teildatensaetzen
	 * @since 0.9
	 */
	public Datensatz(final SatzTyp satzNr, final List<? extends Teildatensatz> tdsList) {
		super(satzNr, tdsList);
		if (tdsList.get(0).hasSparte()) {
			this.sparte.setInhalt(tdsList.get(0).getSparte());
		}
		this.completeTeildatensaetze();
		if (satzNr.hasSparte()) {
			this.setSparte(satzNr.getSparte());
		}
		if (satzNr.hasWagnisart() && satzNr.getWagnisart() < 10) {
			this.set(Bezeichner.WAGNISART, Integer.toString(satzNr.getWagnisart()));
		}
		if (satzNr.hasTeildatensatzNummer()) {
			this.setTeildatensatzNummer("" + satzNr.getTeildatensatzNummer());
		}
	}

	/**
	 * Dies ist der Copy-Constructor, mit dem man einen bestehenden Datensatz
	 * kopieren kann.
	 *
	 * @param other der originale Datensatz
	 */
	public Datensatz(final Datensatz other) {
		super(other, other.cloneTeildatensaetze());
		this.sparte.setInhalt(other.sparte.getInhalt());
		this.art = other.art;
		this.teildatensatzNummer.setInhalt(other.teildatensatzNummer.getInhalt());
		this.wagnisart.setInhalt(other.wagnisart.getInhalt());
	}

    /**
	 * Kann von Unterklassen verwendet werden, um die Teildatensaetze
	 * aufzusetzen.
	 */
	protected void setUpTeildatensaetze() {
		for (Teildatensatz tds : this.getTeildatensaetze()) {
			setUpTeildatensatz(tds);
		}
	}

	/**
	 * Hiermit kann ein einzelner Teildatensatz aufgesetzt werden.
	 * <p>
	 * Wenn alle Datensaetze nur noch ueber Enums (Soplets) initialisiert
	 * werden, duerfte die Inialisierung hier hinfaellig sein.
	 * </p>
	 *
	 * @param tds der (leere) Teildatensatz
	 * @since 0.4
	 */
	protected void setUpTeildatensatz(final Teildatensatz tds) {
		setUpTeildatensatz(tds, this.sparte);
	}

	protected static void setUpTeildatensatz(final Teildatensatz tds, final NumFeld sparte) {
        if (!tds.hasFeld(Feld1bis7.VU_NUMMER) && !tds.get(Feld1bis7.SATZART)
                                                     .equalsIgnoreCase("9999")) {
			setUp(tds, Feld1bis7.VU_NUMMER, Config.getVUNummer());
			setUp(tds, Feld1bis7.BUENDELUNGSKENNZEICHEN, new AlphaNumFeld(Feld1bis7.BUENDELUNGSKENNZEICHEN));
			setUp(tds, Feld1bis7.SPARTE, sparte);
			setUp(tds, Feld1bis7.VERSICHERUNGSSCHEINNUMMER, new AlphaNumFeld(Feld1bis7.VERSICHERUNGSSCHEINNUMMER));
			setUp(tds, Feld1bis7.FOLGENUMMER, new NumFeld(Feld1bis7.FOLGENUMMER));
			setUp(tds, Feld1bis7.VERMITTLER, new AlphaNumFeld(Feld1bis7.VERMITTLER));
			LOG.trace(tds + " is set up.");
		} else if (tds.hasFeld(Feld1bis7.SPARTE)) {
			tds.getFeld(Feld1bis7.SPARTE).setInhalt(sparte.getInhalt());
		}
	}

	private static void setUp(final Teildatensatz tds, final Enum feldX, final Feld value) {
		if (!tds.hasFeld(feldX)) {
			LOG.trace("{} initialized with value {}.", tds, value);
			tds.add(value);
		}
    }

    /**
	 * Kann von Unterklassen verwendet werden, um fehlende Felder in den
	 * Teildatensaetze zu vervollstaendigen. Kann aber seit 1.0 nicht mehr
	 * ueberschrieben werden, da diese Methode vom Konstruktor waehrend der
	 * Objekt-Kreierung benoetigt wird.
	 *
	 * @since 0.6
	 */
	protected final void completeTeildatensaetze() {
		for (Teildatensatz tds : this.getTeildatensaetze()) {
			setUpTeildatensatz(tds);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see gdv.xport.satz.Satz#addFiller()
	 */
	@Override
	public void addFiller() {
		for (Teildatensatz tds : this.getTeildatensaetze()) {
			tds.add(new AlphaNumFeld((LEERSTELLEN), 213, 43));
		}
	}

	/**
	 * Sets the sparte.
	 *
	 * @param x z.B. 70 (Rechtsschutz)
	 */
	public void setSparte(final int x) {
		this.sparte.setInhalt(x);
		for (Teildatensatz tds : getTeildatensaetze()) {
			if (tds.getFelder().size() > 3) {
				tds.getFeld(4).setInhalt(x);
			}
		}
	}

	/**
	 * Setzt die Sparte. Der uebergebene String kann dabei auch die Art der
	 * Sparte enthalten.
	 *
	 * @param x z.B. "580.01" fuer Sparte 580, Art 01
	 */
	public void setSparte(final String x) {
	    String[] parts = x.split("\\.");
	    this.setSparte(Integer.parseInt(parts[0]));
	    if (parts.length > 1) {
	        this.art = Integer.parseInt(parts[1]);
	    }
	}

	/**
	 * Gets the sparte.
	 *
	 * @return die Sparte als int
	 */
	@Override
    public int getSparte() {
		return this.sparte.toInt();
	}

	/**
     * Manche Satzarten wie Bausparen haben eine Element fuer die Untersparte,
     * im Feld Wagnisart oder Art abgespeichert. Dies ist z.B. fuer Satz
     * 220.580.1 (Bausparen) der Fall.
     *
     * @return 0 oder Untersparte / Art
     */
	public int getArt() {
	    return this.art;
	}

	/**
	 * Wenn der Datensatz ein Element fuer eine Untersparte hat, wird 'true'
	 * zurueckgegeben. Dies ist z.B. fuer Satz 220.580.1 (Bausparen) der Fall.
	 *
	 * @return true, falls der Datensatz eine Untersparte hat.
	 */
	public boolean hasArt() {
	    return this.art > 0;
	}

	/**
	 * Ueberprueft, ob der Datensatz ueberhaupt eine Sparte gesetzt hat.
	 *
	 * @return true, if successful
	 * @since 0.6
	 */
	@Override
    public boolean hasSparte() {
		return !this.sparte.isEmpty() && this.getSparte() > 0;
	}

	/**
	 * Gets the sparte feld.
	 *
	 * @return die Sparte als Feld
	 */
	public NumFeld getSparteFeld() {
		return this.sparte;
	}


  /**
   * Wenn der Datensatz ein Element fuer eine Untersparte hat, wird 'true'
   * zurueckgegeben. Dies ist z.B. fuer Satz 220.580.1 (Bausparen) der Fall.
   *
   * @return true, falls der Datensatz eine Untersparte hat.
   */
    public boolean hasSatzartNummer() {
        return !this.getGdvSatzartNummer().isEmpty();
  }

	/**
	 * Sets the vu nummer.
	 *
	 * @param s VU-Nummer (max. 5 Stellen)
	 */
	public void setVuNummer(final String s) {
	    this.getFeld(Feld1bis7.VU_NUMMER).setInhalt(s);
	}

	/**
	 * Gets the vu nummer.
	 *
	 * @return die VU-Nummer
	 */
	public String getVuNummer() {
		return this.getFeld(Feld1bis7.VU_NUMMER).getInhalt().trim();
	}

	/**
	 * Sets the versicherungsschein nummer.
	 *
	 * @param nr die Versicherungsschein-Nummer
	 * @since 0.3
	 */
	public void setVersicherungsscheinNummer(final String nr) {
	    this.getFeld(Feld1bis7.VERSICHERUNGSSCHEINNUMMER).setInhalt(nr);
	}

	/**
	 * Gets the versicherungsschein nummer.
	 *
	 * @return die Versicherungsschein-Nummer
	 * @since 0.3
	 */
	public String getVersicherungsscheinNummer() {
		return this.getFeld(Feld1bis7.VERSICHERUNGSSCHEINNUMMER).getInhalt().trim();
	}

	/**
	 * Gets the teildatensatz nummer.
	 *
	 * @return the teildatensatz nummer
	 */
	public String getTeildatensatzNummer() {
		return teildatensatzNummer.getInhalt().trim();
	}

	/**
	 * Da nicht alle Satzarten die Satznummer am Ende des Satzes haben, kann
	 * man dies ueber diese Methode korrigieren.
	 * 
	 * @param satznummer das neue Feld fuer die Satznummer
	 * @since 3.2   
	 */
	public void setSatznummer(Zeichen satznummer) {
		remove(Bezeichner.SATZNUMMER);
		for (Teildatensatz tds : getTeildatensaetze()) {
			tds.setSatznummer(satznummer);
		}
	}

	/**
	 * Sets the teildatensatz nummer.
	 *
	 * @param teildatensatzNummer the new teildatensatz nummer
	 */
	public void setTeildatensatzNummer(final String teildatensatzNummer) {
		this.teildatensatzNummer.setInhalt(teildatensatzNummer);
	}

	/**
	 * Hiermit kann die Folgenummer gesetzt werden.
	 *
	 * @param nr man sollte hier bei 1 anfangen mit zaehlen
	 * @since 0.3
	 */
	public void setFolgenummer(final int nr) {
	    this.getFeld(Feld1bis7.FOLGENUMMER).setInhalt(nr);
	}

	/**
	 * Gets the folgenummer.
	 *
	 * @return die Folgenummer
	 * @since 0.3
	 */
	public int getFolgenummer() {
	    NumFeld folgenummer = (NumFeld) this.getFeld(Feld1bis7.FOLGENUMMER);
		return folgenummer.toInt();
	}

	/**
	 * Gets the vermittler.
	 *
	 * @return the vermittler
	 * @since 0.6
	 */
	public String getVermittler() {
		return this.getFeldInhalt(VERMITTLER);
	}

	/**
	 * Liest 14 Bytes, um die Satzart zu bestimmen und stellt die Bytes
	 * anschliessend wieder zurueck in den Reader.
	 *
	 * @param reader muss mind. einen Pushback-Puffer von 14 Zeichen
	 * bereitstellen
	 * @return Satzart
	 * @throws IOException falls was schief gegangen ist
	 */
	public static int readSparte(final PushbackReader reader) throws IOException {
		char[] cbuf = new char[14];
		if (reader.read(cbuf) == -1) {
			throw new IOException("can't read 14 bytes (" + new String(cbuf) + ") from " + reader);
		}
		reader.unread(cbuf);
		String intro = new String(cbuf);
		try {
            return Integer.parseInt(intro.substring(10, 13));
        } catch (NumberFormatException ex) {
            throw new ImportException("cannot read sparte from first 14 bytes (\"" + intro + "\")");
        }
	}

	/**
     * Liest 49 Bytes, um die Folge-Nr. in Satzart 220, Sparte 20 (Kranken) zu bestimmen und stellt die Bytes
     * anschliessend wieder zurueck in den Reader.
     *
     * @param reader muss mind. einen Pushback-Puffer von 14 Zeichen
     * bereitstellen
     * @return Folge-Nr
     * @throws IOException falls was schief gegangen ist
     */
    public static int readKrankenFolgeNr(final PushbackLineNumberReader reader) throws IOException {
        int satzart = readSatzart(reader);
        if (satzart != 220) {
            throw new IllegalArgumentException("can't read Kranken Folge-Nr., wrong satzart " + satzart +", must be 220");
        }

        int sparte = readSparte(reader);
        if (sparte != 20) {
            throw new IllegalArgumentException("can't read Kranken Folge-Nr., wrong sparte " + sparte + ", must be 20");
        }
        
        char[] cbuf = new char[49];
        if (reader.read(cbuf) == -1) {
            throw new IOException("can't read 49 bytes (" + new String(cbuf) + ") from " + reader);
        }
        reader.unread(cbuf);
        String first10Fields = new String(cbuf);
        try {
            return Integer.parseInt(first10Fields.substring(47, 48));
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    /**
     * Liest 45 Bytes, um die Bauspar-Art in Satzart 220, Sparte 580 (Bausparen)
     * zu bestimmen und stellt die Bytes anschliessend wieder zurueck in den
     * Reader.
     *
     * @param reader muss mind. einen Pushback-Puffer von 14 Zeichen bereitstellen
     * @return Folge-Nr
     * @throws IOException falls was schief gegangen ist
     */
    public static int readBausparenArt(final PushbackLineNumberReader reader) throws IOException {
		int satzart = readSatzart(reader);
		if (satzart != 220) {
			throw new IllegalArgumentException("can't read Bauspar-Art, wrong satzart " + satzart +", must be 220");
		}
		int sparte = readSparte(reader);
		if (sparte != 580) {
			throw new IllegalArgumentException("can't read Bauspar-Art, wrong sparte " + sparte + ", must be 580");
		}
		char[] cbuf = new char[45];
		if (reader.read(cbuf) == -1) {
			throw new IOException("can't read 45 bytes (" + new String(cbuf) + ") from " + reader);
		}
		reader.unread(cbuf);
		String first10Fields = new String(cbuf);
		try {
			return Integer.parseInt(first10Fields.substring(43, 44));
		} catch (NumberFormatException ex) {
			return -1;
		}
	}

	/**
	 * Liest 1 Byte, um die Wagnisart zu bestimmen und stellt das Byte
	 * anschliessend wieder zurueck in den Reader.
	 *
     * @param reader muss mind. einen Pushback-Puffer von 1 Zeichen bereitstellen
	 * @return Wagnisart
	 * @throws IOException falls was schief gegangen ist
	 */
	public static WagnisartLeben readWagnisart(final PushbackReader reader) throws IOException {
		char[] cbuf = new char[60];
		if (reader.read(cbuf) == -1) {
			throw new IOException("can't read 1 bytes (" + new String(cbuf) + ") from " + reader);
		}
		reader.unread(cbuf);
		String wagnisart = new String(cbuf).substring(59, 60);

		if (wagnisart.trim().length() == 0) {
			return WagnisartLeben.NULL;
		} else {
			try {
				return WagnisartLeben.isIn(Integer.parseInt(wagnisart));
			} catch (NumberFormatException e) {
				LOG.warn("Not allowed value for wagnisart found. Type Number is required but was \""
				        + wagnisart + "\".");
				return WagnisartLeben.NULL;
			}
		}
	}

	/**
	 * Prüfe ob die kommende Zeile ein Teildatensatz der letzten ist. Dazu
	 * werden (normalerweise) die ersten 7 Felder abgeglichen. Leider fuehrt
	 * dieses Verfahren nicht immer zum Erfolg, sodass wir uns in bestimmten
	 * Situationen doch den ganzen naechsten Teildatensatz anschauen muessen.
	 *
	 * @param reader ein Reader
     * @param lastFeld1To7 Feld1..7 als Char-Array (42 Zeichen) der letzten Zeile
     *                     oder {@code null} für ersten Teildatensatz
     * @return {@code true}, falls ein Teildatensatz, {@code false} falls nicht,
     * d.h. neuer Datensatz.
	 * @throws IOException bei I/O-Fehlern
	 * @see Satz#matchesNextTeildatensatz(PushbackLineNumberReader, char[], Character)
	 * @since 0.5.1
	 */
	@Override
    protected boolean matchesNextTeildatensatz(final PushbackLineNumberReader reader, char[] lastFeld1To7, Character satznummer) throws IOException {
        if (super.matchesNextTeildatensatz(reader, lastFeld1To7, satznummer)) {
			if (lastFeld1To7 == null) {
				//erster Teildatensatz hat noch keine lastFeld...
				return matchesFirstTeildatensatz(reader);
			} else {
				// TODO: ugly aber ich sehe bisher noch keinen eleganten Weg in der aktuellen Struktur ohne umfangreiche Refaktorings.
				char[] newLine = new char[256];
				int res = reader.read(newLine);
                if (res < 256) {
                    return false;// EOF
				}
				reader.unread(newLine);
				
                // wir vergleichen komplett die ersten 7 Felder (42 Zeichen) auf
                // Gleichheit....wenn ein Unterschied -> neuer Datensatz,
				for (int i = 0; i < 42; i++) {
					if (lastFeld1To7[i] != newLine[i]) return false;
				}
				return matchesLastFeld(satznummer, newLine);
			}
		}
		return false;
	}

	private boolean matchesFirstTeildatensatz(PushbackLineNumberReader reader) throws IOException {
		if (this.hasSparte()) {
			return this.getSparte() == readSparte(reader);
		}
		return true;
	}

	private static boolean matchesLastFeld(Character satznummer, char[] newLine) {
        // Das letzte Feld wird darauf verglichen, dass es groesser als das
        // vorherige ist, falls Teildatensaetze uebersprungen werden
		char newSatznummer = readSatznummer(newLine);
		return !(Character.isDigit(newSatznummer) && Character.isDigit(satznummer) && newSatznummer <= satznummer);
	}

	/*
	 * (non-Javadoc)
	 * @see gdv.xport.satz.Satz#toShortString()
	 */
	@Override
	public String toShortString() {
	    StringBuilder buf = new StringBuilder(super.toShortString());
	    if (this.hasSparte()) {
	        buf.append(".");
	        buf.append(this.sparte.getInhalt());
	        if (this.hasArt()) {
	            buf.append(".");
	            buf.append(this.getArt());
                if (this.hasSatzartNummer()) {
                    buf.append(".");
                    buf.append(this.getGdvSatzartNummer());
                }
	        }
        }
		return buf.toString();
	}

	/**
	 * Read teildatensatz nummer.
	 *
	 * @param reader the reader
	 * @return the teildatensatz nummer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    public static TeildatensatzNummer readTeildatensatzNummer(final PushbackReader reader) throws IOException {
        char[] cbuf = new char[256];
        if (reader.read(cbuf) == -1) {
            throw new EOFException("can't read 1 bytes (" + new String(cbuf) + ") from " + reader);
        }
        reader.unread(cbuf);
        String teildatenSatz = new String(cbuf).substring(cbuf.length - 1, cbuf.length);
        if (teildatenSatz.trim().length() == 0) {
            return TeildatensatzNummer.NULL;
        } else {
            try {
                return TeildatensatzNummer.isIn(Integer.parseInt(teildatenSatz));
            } catch (NumberFormatException e) {
                LOG.warn("Value \"" + teildatenSatz + "\" for TeildatensatzNummer found, but Number expected.");
                return TeildatensatzNummer.NULL;
            }
        }
    }

	@Override
	public boolean equals(Object obj) {
		if (! super.equals(obj)) {
			return false;
		}
		Datensatz other = (Datensatz) obj;
        return this.art == other.art;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + this.art;
	}

}
