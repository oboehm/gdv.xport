/*
 * Copyright (c) 2009-2024 by Oli B.
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
package gdv.xport.satz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.io.ImportException;
import gdv.xport.io.Importer;
import gdv.xport.io.PushbackLineNumberReader;
import gdv.xport.util.SatzRegistry;
import gdv.xport.util.SatzTyp;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.*;

import static gdv.xport.feld.Bezeichner.SATZART;

/**
 * Die Satz-Klasse ist die oberste Klasse, von der alle weiteren Saetze
 * abgeleitet sind.
 *
 * @author oliver
 */
public abstract class Satz implements Cloneable {

	private static final Logger LOG = LogManager.getLogger(Satz.class);

	private Teildatensatz[] teildatensatz = new Teildatensatz[0];
	private final Config config;

  /**
   * Zum Abspeichern der Satznummer einer 0220er-GdvSatzart der Sparte 010
   * (Leben). Die Namen dieser Satzarten bestehen bestehen aus 4 Teilen:
   * <satzart>.<sparte>.<wagnisart>.<gdvSatzartNummer>. Beispiel:
   * "0220.010.13.6" (siehe Online-Version bei gdv-online.de). Wird benoetigt,
   * um 0220er-Satzarten bei Leben zu unterscheiden wg. Bezugsrechte,
   * Auszahlungen, zukünftige Summenänderungen und Wertungssummen. Nicht
   * verwechseln mit Satznummer eines Teildatensatzes!
   */
  private String gdvSatzartNummer = "";
  /**
   * Zum Abspeichern des Namens einer Gdv-Satzart gemaess Online-Version bei
   * gdv-online.de
   */
  private String gdvSatzartName = "";
    private final AlphaNumFeld satzVersion = new AlphaNumFeld(Bezeichner.of("Version"), 3, ByteAdresse.of(1), Align.LEFT);

	/**
	 * Mit diesem Konstruktor wird ein Satz fuer die entsprechende Satzart
	 * mit n Teildatensaetzen angelegt.
	 *
	 * @param art z.B. Satzart 0100 (f. Adressteil)
	 * @param n Anzahl der Teildatensaetze
	 * @since 5.0
	 */
	public Satz(final SatzTyp art, final int n) {
		this(art, n, Config.getInstance());
	}

	protected Satz(final SatzTyp art, final int n, final Config cfg) {
		this.config = cfg;
		this.createTeildatensaetze(art, n);
	}

	protected Satz() {
		this.config = Config.getInstance();
	}

    /**
     * The Constructor.
     *
     * @param satz z.B. Satzart 0100 (f. Adressteil)
     * @param n    Anzahl der Teildatensaetze
     */
    public Satz(final Satz satz, final int n) {
		this.config = satz.config;
		this.createTeildatensaetze(satz.getSatzTyp(), n);
        this.gdvSatzartName = satz.getGdvSatzartName();
        this.gdvSatzartNummer = satz.getGdvSatzartNummer();
        this.setSatzversion(satz.getSatzversion().getInhalt());
    }

	/**
	 * Instanziiert einen neuen Satz.
	 *
	 * @param art     Satzart, z.B. 100 (f. Adressteil)
	 * @param tdsList Liste mit den Teildatensaetzen
	 * @since 5.0
	 */
	public Satz(final SatzTyp art, final List<? extends Teildatensatz> tdsList) {
		this.config = Config.getInstance();
		this.createTeildatensaetze(tdsList);
	}

	/**
     * Instanziiert einen neuen Satz.
     *
     * @param satz        z.B. 100 (f. Adressteil)
     * @param tdsList     Liste mit den Teildatensaetzen
     */
    protected Satz(final Satz satz, final List<? extends Teildatensatz> tdsList) {
		this.config = satz.config;
		this.createTeildatensaetze(tdsList);
        this.getSatzartFeld().setInhalt(satz.getSatzart());
        this.satzVersion.setInhalt(satz.getSatzversion().getInhalt());
        this.gdvSatzartName = satz.getGdvSatzartName();
        this.gdvSatzartNummer = satz.getGdvSatzartNummer();
    }

	private void createTeildatensaetze(final SatzTyp art, final int n) {
		teildatensatz = new Teildatensatz[n];
		for (int i = 0; i < n; i++) {
			teildatensatz[i] = new Teildatensatz(art, i + 1);
		}
		this.getSatzartFeld().setInhalt(art.getSatzart());
	}

	private void createTeildatensaetze(final List<? extends Teildatensatz> tdsList) {
		teildatensatz = new Teildatensatz[tdsList.size()];
		for (int i = 0; i < tdsList.size(); i++) {
			teildatensatz[i] = new Teildatensatz(tdsList.get(i));
		}
	}

	/**
	 * Liefert die aktuelle Konfiguration zurueck.
	 *
	 * @return aktuelles Config-Objekt
	 * @since 6.2
	 */
	public final Config getConfig() {
		return this.config;
	}

	/**
	 * Liefert alle Teildatensaetze zurueck. Aus Performance-Gruenden wird
	 * keine Kopie zurueckgegeben. Sollte eine Kopie gewuenscht sein, kann
	 * man auf {@link #cloneTeildatensaetze()} zurueckgreifen.
	 *
	 * @return Teildatensaetze
	 * @since 0.2
	 */
	public final List<Teildatensatz> getTeildatensaetze() {
		return Arrays.asList(this.teildatensatz);
	}

	/**
     * Hier wird eine Kopie aller Teildatensaetze zurueckgegeben.
	 *
	 * @return Liste mit Teildatensaetzen
	 * @since 1.0
	 */
	protected final List<Teildatensatz> cloneTeildatensaetze() {
        List<Teildatensatz> cloned = new ArrayList<>(this.teildatensatz.length);
        for (Teildatensatz tds : this.teildatensatz) {
            cloned.add(new Teildatensatz(tds));
        }
	    return cloned;
	}

	/**
	 * Liefert die Anzahl der Teildatensaetze.
	 *
	 * @return Anzahl der Teildatensaetze.
	 * @since 0.6
	 */
	public final int getNumberOfTeildatensaetze() {
		return teildatensatz.length;
	}

	/**
     * Liefert den n-ten Teildatensatz aus der Liste der Teildatensätze zurueck.
     * <p>
     * <b>Achtung</b> n ist nicht immer identisch mit der Satznummer des
     * Teildatensatzes (siehe z.B. Satzart 0221.140).
     * Dazu besser {@link #getTeildatensatzBySatzNr(int)} verwenden.
	 * </p>
	 *
     * @param n Nummer (Index n-1 in der Liste der Teildatensätze) des
     *          Teildatensatzes (beginnend mit 1)
	 * @return the teildatensatz
	 * @since 0.2
	 */
	public final Teildatensatz getTeildatensatz(final int n) {
		return this.teildatensatz[n - 1];
	}

    /**
     * Liefert den Teildatensatz mit der wirklichen Satznummer n zurueck.
     *
     * @param n Satznummer des Teildatensatzes
     * @return the teildatensatz
     */
    public final Teildatensatz getTeildatensatzBySatzNr(final int n) {
        for (Teildatensatz tds : this.teildatensatz) {
            if (Integer.parseInt(tds.getSatznummer().getInhalt()) == n) return tds;
        }

        throw new IllegalArgumentException("Satznummer " + n + " nicht vorhanden.");
    }
	/**
	 * Hiermit koennen Unterklassen alle Teildatensaetze wieder entfernen (wird
	 * z.B. vom Satz 0220.030 benoetigt).
	 *
	 * @since 0.4
	 */
	public final void removeAllTeildatensaetze() {
		this.teildatensatz = new Teildatensatz[0];
	}

	/**
	 * Entfernt den gewuenschten Teildatensatz. Ein neuer Teildatensatz kann
	 * ueber add() hinzugefuegt werden.
	 *
     * @param n der gewuenschte Teildatensatz (beginnend bei 1)
	 * @see #add(Teildatensatz)
	 * @since 0.4
	 */
	public final void removeTeildatensatz(final int n) {
		if ((n < 1) || (n > this.teildatensatz.length)) {
			throw new IllegalArgumentException(n + " liegt nicht zwischen 1 und "
			        + this.teildatensatz.length);
		}
		this.teildatensatz = ArrayUtils.remove(this.teildatensatz, n - 1);
	}

    /**
     * Entfernt den gewuenschten Teildatensatz mit der wirklichen Satznummer n.
     *
     * @param n wirkliche Satznummer des Teildatensatzes
     */
    public final void removeTeildatensatzBySatzNr(final int n) {
        boolean treffer = false;
        int index = 0;

        for (Teildatensatz tds : this.teildatensatz) {
            if (Integer.parseInt(tds.getSatznummer() .getInhalt()) == n) {
                 treffer = true;
                 break;
             }
             else
                 index++;
        }

        if (!treffer)
            throw new IllegalArgumentException("Teildatensatz " + n + " existiert nicht.");

        this.teildatensatz = ArrayUtils.remove(this.teildatensatz, index);
    }

	/**
	 * Und hierueber kann ein Teildatensatz hinzugefuegt werden.
	 *
	 * @param tds der neue (gefuellte) Teildatensatz
     * @since 0.4
	 */
	public final void add(final Teildatensatz tds) {
		this.teildatensatz = ArrayUtils.add(this.teildatensatz, tds);
	}

	/**
	 * Fuegt das uebergebene Feld zur Liste der Datenfelder hinzu.
	 *
	 * @param feld das Feld
	 */
	public void add(final Feld feld) {
		this.add(feld, 1);
	}

	/**
	 * Fuegt das uebergebene Feld in jeden Teildatensatz hinzu.
	 *
	 * @param feld das Feld
	 * @since 6.1
	 */
	public void addAll(final Feld feld) {
		for (int n = 1; n <= getNumberOfTeildatensaetze(); n++) {
			add(feld, n);
		}
	}

	/**
	 * Fuegt das uebergebene Feld zur Liste der Datenfelder hinzu.
	 *
	 * @param feld the feld
	 * @param teildatensatzNr the teildatensatz nr
	 */
	public void add(final Feld feld, final int teildatensatzNr) {
		if (feld.getByteAdresse() > 256) {
			throw new IllegalArgumentException(feld + " ueberschreitet Teildatensatz-Grenze");
		}
		if ((teildatensatzNr < 1) || (teildatensatzNr > this.teildatensatz.length)) {
			throw new IllegalArgumentException("Teildatensatz-Nr. " + teildatensatzNr + " fuer "
			        + feld + " liegt nicht zwischen 1 und " + teildatensatz.length);
		}
		this.teildatensatz[teildatensatzNr - 1].add(feld);
	}

	/**
	 * Fuellt fuer alle leeren Stellen ein entsprechendes Fuellfeld auf.
	 */
	public void addFiller() {
		throw new UnsupportedOperationException("not yet implemented");
	}

	/**
	 * Falls ein Feld zuviel gesetzt wurde, kann es mit 'remove" wieder entfernt
	 * werden.
	 *
	 * @param name Name des Feldes
	 */
	public void remove(final String name) {
		this.remove(Bezeichner.of(name));
	}

    /**
     * Falls ein Feld zuviel gesetzt wurde, kann es mit 'remove" wieder entfernt
     * werden.
     *
     * @param bezeichner der Feld-Beezeichner
     * @since 1.0
     */
    public void remove(final Bezeichner bezeichner) {
        for (Teildatensatz tds : this.teildatensatz) {
            tds.remove(bezeichner);
        }
    }

	/**
	 * Setzt das angegebene Feld in allen Teildatensaetzen, in denen es gefunden
	 * wird. Normalerweise braeuchten wir eigentlich nur die erste Fundstelle
	 * setzen, da die anderen Teildatensaetze (hoffentlich) auf die gleiche
	 * Referenz verweisen - aber sicher ist sicher. Falls das Feld nicht
	 * gefunden wird, wird eine IllegalArgumentException geworfen.
	 *
	 * @param name Name des Felds (Bezeichnung)
	 * @param value the value
	 * @since 5.2
	 */
	public void setFeld(final String name, final String value) {
		this.setFeld(Bezeichner.of(name), value);
	}

	/**
	 * Setzt den Inhalt des gewuenschten Feldes.
	 *
	 * @param name  Name des Felds (Bezeichnung)
	 * @param value neuer Inhalt
	 * @since 5.2
	 */
	public void setFeld(final Bezeichner name, final Integer value) {
		this.setFeld(name, Integer.toString(value));
	}

	/**
	 * Setzt das angegebene Feld in allen Teildatensaetzen, in denen es gefunden
	 * wird. Normalerweise braeuchten wir eigentlich nur die erste Fundstelle
	 * setzen, da die anderen Teildatensaetze (hoffentlich) auf die gleiche
	 * Referenz verweisen - aber sicher ist sicher. Falls das Feld nicht
	 * gefunden wird, wird eine IllegalArgumentException geworfen.
	 *
	 * @param name Name des Felds (Bezeichnung)
	 * @param value the value
	 * @since 5.2
	 */
	public void setFeld(final Bezeichner name, final String value) {
		boolean found = false;
		for (Teildatensatz tds : teildatensatz) {
			if (tds.hasFeld(name)) {
				tds.setFeld(name, value);
				found = true;
			}
		}
		if (!found) {
			throw new IllegalArgumentException("Feld \"" + name + "\" not found");
		}
	}

	/**
	 * Setzt das angegebene Feld anhand der Adresse in allen Teildatensaetzen.
	 * Das macht nur Sinn fuer Felder, die in allen Teildatensaezten vorkommen
	 * (z.B. die ersten 7 Felder). Moechte man hingegen nur das Feld in einem
	 * Teildatensatz setzen, so sollte man sich den entsprechenden
	 * Teildatensatz holen und das Feld dort setzen.
	 *
	 * @param adresse Adresse des Felds (Bezeichnung)
	 * @param value   neuer Wert
	 * @since 6.2
	 */
	public void setFeld(final ByteAdresse adresse, final String value) {
		for (Teildatensatz tds : teildatensatz) {
			tds.setFeld(adresse, value);
		}
	}

	/**
	 * Setzt den Vermittler in das entsprechende Feld.
	 *
	 * @param vermittler der Vermittler
	 * @since 5.2
	 */
	public final void setVermittler(String vermittler) {
		setFeld(Bezeichner.VERMITTLER, vermittler);
	}

	/**
	 * Liefert den Vermittler zurueck.
	 *
	 * @return Vermittler
	 * @since 5.2
	 */
	@JsonIgnore
	public final String getVermittler() {
		return getFeld(Bezeichner.VERMITTLER).getInhalt().trim();
	}

    /**
     * Setzt die Satzartnummer einer Satzart. Nicht verwechseln mit Satznummer!
     *
     * @param x z.B. "6" fuer Satzart 0220, Sparte 010, Wagnisart 2, Bezugsrechte
     */
    protected void setGdvSatzartNummer(final String x) {
        this.gdvSatzartNummer = x;
    }

    /**
     * Gets die Satzartnummer. Nicht verwechseln mit Satznummer!
     * <p>
     * Manche Satzarten wie Leben haben ein Element fuer die Satznummer, im Feld
     * Satzartnummer gespeichert. Dies ist z.B. fuer Satz 0220.010.13.6
     * (Bezugsrechte) der Fall.
     *
     * @return die Satzartnummer als String
     */
    public String getGdvSatzartNummer() {
        return this.gdvSatzartNummer;
    }

    /**
     * @return Name der GDV-Satzart gemaess Online-Version bei gdv-online.de
     */
    public String getGdvSatzartName() {
        return this.gdvSatzartName;
    }

    /**
     * Setzen des Namens einer Gdv-Satzart.
     * <p>
     * Der <code>string</code> wird mit dem Trennzeichen '.' an den bisherigen
     * Inhalt angehaengt.
     * </p>
     *
     * @param string Satzart-Name
     */
    protected void setGdvSatzartName(String string) {
        StringBuilder buf = new StringBuilder();

        if (this.gdvSatzartName.isEmpty()) {
            buf.append(string);
        } else {
            buf.append(this.gdvSatzartName).append(".").append(string);
        }

        this.gdvSatzartName = buf.toString();
    }

    public void resetGdvSatzartName() {
    	this.gdvSatzartName = "";
	}

    private void setSatzversion(final String version) {
        this.satzVersion.setInhalt(version);
    }

    /**
     * Liefert die Satzversion
     * 
     * @return die Satzversion
     */
    public final AlphaNumFeld getSatzversion() {
        return this.satzVersion;
    }

	/**
	 * Liefert die Version des Satzes.
	 *
	 * @return z.B. "1.2"
	 * @since 5.2
	 */
	public final String getVersion() {
    	return this.satzVersion.getInhalt();
	}

	/**
	 * Liefert das gewuenschte Feld.
	 *
	 * @param name gewuenschter Bezeichner des Feldes
	 * @return das gesuchte Feld
	 * @throws IllegalArgumentException falls es das Feld nicht gibt
	 * @deprecated bitte getFeld(Bezeichner) verwenden
	 * 			   (TODO: wird mit v8 oder v9 entsorgt)
	 */
	@Deprecated
	public Feld getFeld(final String name) throws IllegalArgumentException {
		return this.getFeld(Bezeichner.of(name));
	}

    /**
     * Fraegt ab, ob das entsprechende Feld vorhanden ist.
     *
     * @param bezeichner gewuenschter Bezeichner des Feldes
     * @return true / false
     */
    public boolean hasFeld(final Bezeichner bezeichner) {
        for (Teildatensatz tds : teildatensatz) {
            if (tds.hasFeld(bezeichner)) {
                return true;
            }
        }
        return false;
    }

	/**
	 * Fraegt ab, ob das entsprechende Feld vorhanden ist.
	 *
	 * @param adresse gewuenschter Bezeichner des Feldes
	 * @return true / false
	 */
	public boolean hasFeld(final ByteAdresse adresse) {
		for (Teildatensatz tds : teildatensatz) {
			if (tds.hasFeld(adresse)) {
				return true;
			}
		}
		return false;
	}

	/**
     * Liefert das gewuenschte Feld.
     *
     * @param bezeichner gewuenschter Bezeichner des Feldes
     * @return das gesuchte Feld
     * @throws IllegalArgumentException falls es das Feld nicht gibt
     */
	public Feld getFeld(final Bezeichner bezeichner) throws IllegalArgumentException {
		for (Teildatensatz tds : teildatensatz) {
			if (tds.hasFeld(bezeichner)) {
				return tds.getFeld(bezeichner);
			}
		}
		throw new IllegalArgumentException("Feld \"" + bezeichner + "\" nicht in " + this.toShortString()
				+ " vorhanden!");
	}

	/**
	 * Holt sich das entsprechende Feld aus dem Teildatensatz.
	 *
	 * @param adresse ByteAdresse des entsprechende Feld
	 * @return das Feld aus dem ersten Teildatensatz.
	 * @since 6.2
	 */
	public Feld getFeld(final ByteAdresse adresse) {
		return getTeildatensatz(1).getFeld(adresse);
	}

	/**
	 * Liefert das gewuenschte Feld im gewuenschten Typ.
	 * Falls als Typ {@link BetragMitVorzeichen} gewuenscht wird, wird das
	 * Feld mit dem angegebenen Bezeichner und das benachbarte Vorzeichenfeld
	 * zusammengefasst und als Einheit zurueckgegeben.
	 *
	 * @param <T> 	     Unterklasse von Feld
	 * @param bezeichner gewuenschter Bezeichner des Feldes
	 * @param clazz      Feld-Typ
	 * @return das gesuchte Feld
	 * @throws IllegalArgumentException falls es das Feld nicht gibt
	 * @since 5.0
	 */
    public <T extends Feld> T getFeld(final Bezeichner bezeichner, final Class<T> clazz) {
    	if (clazz.equals(BetragMitVorzeichen.class)) {
    		return (T) getBetragMitVorzeichen(bezeichner);
		}
		Feld feld = getFeld(bezeichner);
		if (clazz.isAssignableFrom(feld.getClass())) {
			return (T) feld;
		} else {
			try {
				Constructor<T> ctor = clazz.getConstructor(Feld.class);
				return ctor.newInstance(feld);
			} catch (ReflectiveOperationException ex) {
				throw new IllegalArgumentException("cannot instantiate " + clazz, ex);
			}
		}
	}

	private BetragMitVorzeichen getBetragMitVorzeichen(final Bezeichner bezeichner) {
    	Betrag betrag = getFeld(bezeichner, Betrag.class);
    	Feld vorzeichen = getVorzeichenOf(bezeichner);
    	BetragMitVorzeichen bmv = new BetragMitVorzeichen(Bezeichner.of(bezeichner.getName() + " mit Vorzeichen"),
				betrag.getAnzahlBytes()+1, ByteAdresse.of(betrag.getByteAdresse()));
    	bmv.setInhalt(betrag.getInhalt() + vorzeichen.getInhalt());
    	return bmv;
	}

	private Feld getVorzeichenOf(final Bezeichner bezeichner) {
		for (int n = 1; n <= getNumberOfTeildatensaetze(); n++) {
			Teildatensatz tds = getTeildatensatz(n);
			if (tds.hasFeld(bezeichner)) {
				Feld beforeVorzeichen = tds.getFeld(bezeichner);
				return tds.getFeld(ByteAdresse.of(beforeVorzeichen.getEndAdresse()+1));
			}
		}
		throw new IllegalArgumentException(bezeichner + " does not exist");
	}

    /**
     * Liefert den Inhalt des gewuenschten Feldes.
     *
     * @param bezeichner gewuenschter Bezeichner des Feldes
     * @return Inhalt des Feldes (getrimmt, d.h. ohne Leerzeichen am Ende)
     * @throws IllegalArgumentException falls es das Feld nicht gibt
     * @since 2.0
     */
    public final String getFeldInhalt(final Bezeichner bezeichner) throws IllegalArgumentException {
        return this.getFeld(bezeichner).getInhalt().trim();
    }

    /**
     * Liefert das gewuenschte Feld.
     *
     * @param bezeichner gewuenschter Bezeichner des Feldes
     * @param nr Nummer des Teildatensatzes (1, 2, ...)
     * @return NULL_FELD, falls das angegebene Feld nicht gefunden wird
     * @throws IllegalArgumentException falls es das Feld nicht gibt
     * @since 2.0
     */
	public final Feld getFeld(final Bezeichner bezeichner, final int nr) throws IllegalArgumentException {
		return teildatensatz[nr - 1].getFeld(bezeichner);
	}

	/**
	 * Liefert das gewuenschte Feld.
	 *
	 * @param name gewuenschter Bezeichner des Feldes
	 * @param nr Nummer des Teildatensatzes (1, 2, ...)
	 * @return NULL_FELD, falls das angegebene Feld nicht gefunden wird
	 * @since 0.2
	 * @deprecated bitte getFeld(Bezeichner, int) verwenden
	 * 	           (TODO: wird mit v8 oder v9 entsorgt)
	 */
	@Deprecated
	public final Feld getFeld(final String name, final int nr) {
		assert (0 < nr) && (nr <= teildatensatz.length) : nr + " liegt ausserhalb des Bereichs";
		return teildatensatz[nr - 1].getFeld(name);
	}

	/**
	 * Liefert den Inhalt des gewuenschten Feldes.
	 *
	 * @param name gewuenschter Bezeichner des Feldes
	 * @param nr Nummer des Teildatensatzes (1, 2, ...)
	 * @return Inhalt des Feldes (getrimmt, d.h. ohne Leerzeichen am Ende)
	 * @since 0.3
	 */
	public final String getFeldInhalt(final String name, final int nr) {
		return this.getFeld(name, nr).getInhalt().trim();
	}

	/**
	 * Liefert die Satzart.
	 *
	 * @return the satzart
	 */
	public NumFeld getSatzartFeld() {
		if (teildatensatz.length > 0) {
			return teildatensatz[0].getSatzartFeld();
		} else {
			return new NumFeld(SATZART, 4, ByteAdresse.of(1));
		}
	}

	/**
	 * Liefert die Satzart zurueck.
	 *
	 * @return die Satzart als int
     * @since 0.3
	 */
	public int getSatzart() {
		return getSatzartFeld().toInt();
	}

	/**
	 * Liefert den Satz-Typ zurueck. Der Satz-Typ ist eine Zusammenfassung aus
	 * Satzart und Sparte.
	 *
	 * @return den Satz-Typ
	 * @since 1.0
	 */
	@JsonIgnore
	public SatzTyp getSatzTyp() {
		if (StringUtils.isNotEmpty(this.gdvSatzartName)) {
			return SatzTyp.of(this.gdvSatzartName);
	    } else {
			Optional<NumFeld> sparte = getFeldSparte();
			if (sparte.isPresent()) {
				if (this.hasWagnisart() && this.getWagnisart().matches("\\d")) {
					return SatzTyp.of(this.getSatzart(), sparte.get().toInt(),
							Integer.parseInt(this.getWagnisart()));
				} else if (this.hasKrankenFolgeNr() && this.getKrankenFolgeNr().matches("\\d")) {
					return SatzTyp.of(this.getSatzart(), sparte.get().toInt(),
							Integer.parseInt(this.getKrankenFolgeNr()));
				} else if (this.hasBausparenArt() && this.getBausparenArt().matches("\\d")) {
					return SatzTyp.of(this.getSatzart(), sparte.get().toInt(),
							Integer.parseInt(this.getBausparenArt()));
				} else {
					return SatzTyp.of(this.getSatzart(), sparte.get().toInt());
				}
			} else {
				return SatzTyp.of(this.getSatzart());
			}
		}
	}

	/**
	 * Schaut nach einem Feld "SPARTE" und liefert true zurueck, falls es
	 * existiert.
	 *
     * @return true, falls Sparten-Feld vorhanden ist
	 * @since 0.9
	 */
	public boolean hasSparte() {
		return getFeldSparte().isPresent();
	}

	/**
     * Schaut nach einem Feld "WAGNISART" und liefert true zurueck, falls es
     * existiert.
     *
     * @return true, falls Wagnisart-Feld vorhanden ist
     * @since 1.0
	 */
	public boolean hasWagnisart() {
	    return this.hasFeld((Bezeichner.WAGNISART));
	}

	/**
     * Schaut nach dem 10. Feld in Satzart 220, Sparte 20 (Kranken) und liefert
     * true zurueck, falls es existiert.
	 * 
	 * @return true, falls das Feld existiert
	 * @since 18.04.2018
	 */
    public boolean hasKrankenFolgeNr() {
        return this.getSatzart() == 220 && this.getFeldSparte().get().toInt() == 20
                && (this.hasFeld(Bezeichner.FOLGE_NR_ZUR_LAUFENDEN_PERSONEN_NR_UNTER_NR_LAUFENDE_NR_TARIF)
                        || this.hasFeld(Bezeichner.FOLGE_NR_ZUR_LAUFENDEN_PERSONEN_NR_UNTER_NR_BZW_LAUFENDEN_NR_TARIF));
    }

	/**
	 * Schaut nach dem 9. Feld in Satzart 220, Sparte 580 (Bausparen) und liefert true zurueck, falls
	 * es existiert.
	 *
	 * @return true, falls das Feld existiert
	 * @since 30.06.2021
	 */
	public boolean hasBausparenArt() {
		Optional<NumFeld> feldSparte = this.getFeldSparte();
		return this.getSatzart() == 220 && feldSparte.isPresent() && feldSparte.get().toInt() == 580
				&& (this.hasFeld(Bezeichner.ART_580));
	}

	/**
	 * Liefert den Inhalt des Sparten-Felds. Vorher sollte allerdings mittels
	 * {@link #hasSparte()} geprueft werden, ob der Satz ein Sparten-Feld
	 * besitzt.
	 * <p>
	 * Anmerkung: diese Methode liefert nicht die Sparte, sondern den Inhalt
	 * des Spartenfelds an Byte-Adresse 11 zurueck. Im Normalfall entspricht
	 * dies der Sparte, kann aber in Sonderfaellen davon abweichen.
	 * </p>
	 *
     * @return die Sparte
     * @since 0.9
	 */
	@JsonIgnore
	public int getSparte() {
		Optional<NumFeld> sparte = getFeldSparte();
		if (sparte.isPresent()) {
			return sparte.get().toInt();
		} else {
			throw new IllegalArgumentException(
					this.toShortString() + " hat kein Feld \"Sparte\" an Pos 11 in den Kopfdaten!");
		}
	}

	/**
	 * Diese Methode dient als Ersatz fuer die getSparte()- und hasSparte()-
	 * Implementierung, die durch den Mehrdeutigkeit von "Sparte" in die Irre
	 * fuehren koennen.
	 *
	 * @return Optional.empty() fuer Vor- und Nachsatz, ansonsten Sparte-Feld
	 * 	       an Byte-Adresse 11
	 * @since  7.1
	 */
	@JsonIgnore
	public Optional<NumFeld> getFeldSparte() {
		ByteAdresse adresseSparte = ByteAdresse.of(11);
		if (hasFeld(adresseSparte)) {
			Feld feld = getFeld(adresseSparte);
			if (feld.getBezeichner().isVariantOf(Bezeichner.SPARTE)) {
				return Optional.of((NumFeld) feld);
			}
		}
		return Optional.empty();
	}

	/**
     * Liefert den Inhalt des Wagnisart-Felds. Vorher sollte allerdings mittels
     * {@link #hasWagnisart()} geprueft werden, ob der Satz ein Wagnisart-Feld
     * besitzt.
     * <p>
     * Anmerkung: Vor 1.0 war diese Methode noch in der Datensatz-Klasse
     * beheimatet.
     * </p>
     *
     * @return die Wagnisart
     */
    @JsonIgnore
    public final String getWagnisart() {
        Feld wagnisart = this.getFeld(Bezeichner.WAGNISART);
        return wagnisart.getInhalt();
    }

	/**
	 * Liefert den Inhalt des Feldes, in dessen Bezeichnung "Folge-Nr" oder
	 * "Folgenummer" gefolgt von "zur laufenden" vorkommt (ByteAdresse 48), wenn es
	 * existiert.
	 * <p>
	 * Die Methode funktioniert bei allen Satzarten und besonders auch bei frei
	 * definierten Satzarten!
	 * </p>
	 *
	 * @return die KrankenFolgeNr (wenn vorhanden)
	 */
	@JsonIgnore
	public final String getKrankenFolgeNr() {
		if (this.hasKrankenFolgeNr()) {
			return this.getFeld(ByteAdresse.of(48)).getInhalt();
		}

		return "";
	}

  /**
   * Liefert den Inhalt des 9. Feldes in Satzart 0220, Sparte 580 (Bausparen). Vorher sollte
   * allerdings mittels {@link #hasBausparenArt()} geprueft werden, ob der Satz ein
   * Bausparenart-Feld besitzt.
   * <p>
   *
   * @return die Bausparenart
   */
  @JsonIgnore
  public final String getBausparenArt()
  {
    Feld art = this.getFeld(Bezeichner.ART_580);
    return art.getInhalt();
  }
	/**
     * Exportiert den Satz.
	 *
	 * @param writer the writer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void export(final Writer writer) throws IOException {
        for (Teildatensatz tds : teildatensatz) {
            tds.export(writer);
        }
	}

	/**
	 * Exportiert den Satz.
	 *
	 * @param file Datei
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void export(final File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            this.export(writer);
        }
	}

	/**
	 * Export.
	 *
	 * @param writer the writer
	 * @param eod das End-of-Datensatz- oder Trennzeichen (z.B. linefeed)
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void export(final Writer writer, final String eod) throws IOException {
        for (Teildatensatz tds : teildatensatz) {
            tds.export(writer, eod);
        }
	}

	/**
	 * @param ostream z.B. System.out
	 * @throws IOException falls mal was schief geht
     * @since 0.3
	 */
	public void export(final OutputStream ostream) throws IOException {
		Writer writer = new OutputStreamWriter(ostream);
		export(writer);
		writer.flush();
		ostream.flush();
	}

	/**
     * Eigentlich wollte ich ja diese Methode "import" nennen, aber das kollidiert
     * leider mit dem Schluesselwort "import" in Java. Inzwischen beruecksichtigt
     * diese Import-Methode auch zusaetzlich eingestreute Newlines ("\n") oder/und
     * Wagenruecklaeufe ("\r").
     * <p>
     * Vor der Behebung von Issue #8 ist man davon ausgegangen, dass die
     * Teildatensaetze hintereinander kommen und dass es keine Luecken gibt. Dies
     * ist aber nicht der Fall. Jetzt koennen die Teildatensaetze in beliebiger
     * Reihenfolge kommen. Nicht importierte Teildatensaetze werden am Ende
     * aussortiert.
	 *
	 * @param s String zum Importieren
	 * @return Satz zur Weiterverabeitung (seit 5.2)
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    public Satz importFrom(final String s) throws IOException {
    	return importFrom(new PushbackLineNumberReader(new StringReader(s), 256));
    }
    
    protected void removeUnusedTeildatensaetze(SortedSet<Integer> usedIndexes) {
        Teildatensatz[] usedTeildatensaetze = new Teildatensatz[usedIndexes.size()];
        int i = 0;
        for (int teilsatzIndex : usedIndexes) {
            usedTeildatensaetze[i] = teildatensatz[teilsatzIndex];
            i++;
        }
        this.teildatensatz = usedTeildatensaetze;
    }

    /**
	 * Importiert einen Satz von der angegebenen Datei.
	 *
	 * @param file die Import-Datei
	 * @return Satz zur Weiterverabeitung (seit 5.2)
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Satz importFrom(final File file) throws IOException {
        try (Reader reader = new FileReader(file)) {
            return this.importFrom(reader);
        }
	}

//	/**
//	 * Ermittelt die Satzlaenge. Je nachdem, ob das Zeilenende aus keinem, einem
//	 * oder zwei Zeichen besteht, wird 256, 257 oder 258 zurueckgegeben.
//	 *
//	 * @param s der komplette Satz
//	 * @return 256, 257 oder 258
//     * @since 0.4
//	 */
//	protected final int getSatzlength(final String s) {
//		int satzlength = 256;
//		try {
//			char c256 = s.charAt(256);
//			if ((c256 == '\n') || (c256 == '\r')) {
//				satzlength = 257;
//			}
//			if (s.length() > satzlength) {
//				char c257 = s.charAt(257);
//				if ((c257 == '\n') || (c257 == '\r')) {
//					satzlength = 258;
//				}
//			}
//		} catch (StringIndexOutOfBoundsException e) {
//			LOG.trace("end of string \"" + s + "\" reached", e);
//		}
//		return satzlength;
//	}

	/**
	 * Import von einem {@link InputStream}.
	 *
	 * @param istream the istream
	 * @return Satz zur Weiterverabeitung (seit 5.2)
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public final Satz importFrom(final InputStream istream) throws IOException {
		return importFrom(new InputStreamReader(istream, Config.DEFAULT_ENCODING));
	}

	/**
	 * Import von einem {@link Reader}.
	 *
	 * @param reader the reader
	 * @return Satz zur Weiterverabeitung (seit 5.2)
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public final Satz importFrom(final Reader reader) throws IOException {
	    PushbackLineNumberReader lnr = new PushbackLineNumberReader(reader, 256);
		try {
            return importFrom(lnr);
		} catch (IOException ioe) {
		    throw new ImportException(lnr, "read error", ioe);
		} catch (NumberFormatException nfe) {
            throw new ImportException(lnr, "number expected", nfe);
		}
	}

	/**
	 * Der hier verwendete PushbackReader wird benoetigt, damit die gelesene
	 * Satzart und Sparte wieder zurueckgesetllt werden kann.
	 *
	 * @param reader the reader
	 * @return Satz zur Weiterverabeitung (seit 5.2)
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    public final Satz importFrom(final PushbackLineNumberReader reader) throws IOException {
    	SortedSet<Integer> used = new TreeSet<>();
        char[] feld1to7 = null;
        char satznummer = '0';
        for (int i = 0; i < teildatensatz.length; i++) {
            reader.skipNewline();
            if (!matchesNextTeildatensatz(reader, feld1to7, satznummer)) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("Zeile {}: mehr Teildatensaetze erwartet fuer {}.", reader.getLineNumber(), this);
				}
                break;
            }
            boolean teildatensatzGefunden = false;
            for (int j = i; j < teildatensatz.length; j++) {
            	// pruefe ob dieser oder einer der naechsten Teildatensaetze zur gelieferten Satznummer passen
				char nr = Satznummer.readSatznummer(reader, teildatensatz[j]).toChar();
				if (!Character.isDigit(nr) || (teildatensatz[j].getSatznummer().toChar() != nr)) {
					LOG.debug("Zeile {}: {} erwartet statt {} - ueberspringe Teildatensatz {}.",
							reader.getLineNumber(), teildatensatz[j].getSatznummer(), nr, j+1);
					continue;
				}
				i = j; // falls wir einen Datensatz finden, der passt, machen wir damit weiter
				teildatensatzGefunden = true;
				satznummer = nr;
				break;
			}
            if (!teildatensatzGefunden && i > 0) {
            	// erster Teildatensatz wird verwendet, falls keiner der Teildatensaetze via Satznummer identifiziert
				// werden kann.
				break;
			}
			used.add(i);
			char[] cbuf = new char[257];
			importFrom(reader, cbuf);
			teildatensatz[i].importFrom(new String(cbuf));
			feld1to7 = Arrays.copyOfRange(cbuf, 0, 42);
        }
		removeUnusedTeildatensaetze(used);
        return this;
    }

	/**
	 * Prueft, ob die kommende Zeile noch zu dem aktuellen Datensatz gehoert.
	 * D.h. es wird geprueft, ob es ein weiterer Teildatensatz oder ein neuer
	 * Datensatz ist.
	 * <p>
	 * Unterklassen (wie Datensatz) sind dafuer verantwortlich, dass auch noch
	 * die Sparte/... ueberprueft wird, ob sie noch richtig ist oder ob da schon der
	 * naechste Satz beginnt. Hier (fuer den allgemeinen Fall) wird nur die
	 * Satzart ueberprueft.
	 * </p>
	 *
	 * @param reader       den Reader
	 * @param lastFeld1To7 die Felder 1 .. 7 aus dem letzten Datensatz
	 * @param satznummer   die Satznummer
	 * @return true wenn wenigstens die Satzart uebereinstimmt (nur für Vor/Nachsatz anwendbar)
	 * @throws IOException bei I/O-Fehlern
	 * @since 0.5.1
	 */
	protected boolean matchesNextTeildatensatz(final PushbackLineNumberReader reader, char[] lastFeld1To7, Character satznummer) throws IOException {
		try {
            int art = Importer.of(reader).readSatzart();
            return art == this.getSatzart();
        } catch (EOFException ex) {
            LOG.info("No next teildatensatz found ({}).", ex.getLocalizedMessage());
            LOG.debug("Details:", ex);
            return false;
        }
	}

	private static void importFrom(final Reader reader, final char[] cbuf)
	        throws IOException {
		if (reader.read(cbuf, 0, 256) == -1) {
			throw new EOFException("can't read 256 bytes from " + reader);
		}
	}

    /**
	 * Aus Performance-Gruenden stuetzt sich diese Methode nicht auf die
	 * validate()-Methode ab.
	 *
	 * @return true/false
	 */
	public boolean isValid() {
		if (!this.getSatzartFeld().isValid() || (this.getSatzart() < 1)) {
			return false;
		}
		if (this.teildatensatz != null) {
			for (int i = 0; i < teildatensatz.length; i++) {
				if (!teildatensatz[i].isValid()) {
					LOG.info("Teildatensatz " + (i + 1) + " is invalid");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Sind alle Teildatensaetze eines Satzes vorhanden und ausgefuellt,
	 * liefert diese Methode 'true' zurueck.
	 *
	 * @return false, wenn ein Teildatensatz fehlt
	 * @since 5.2
	 */
	public boolean isComplete() {
		Satz reference = SatzRegistry.getInstance().getSatz(getSatzTyp());
		return getNumberOfTeildatensaetze() == reference.getNumberOfTeildatensaetze();
	}

	/**
	 * Vereinigt den anderen Satz mit dem aktuellen Satz, falls das moeglich
	 * ist. In diesem Fall werden aus dem anderen Satz alle Teildatensaetze
	 * entfernt.
	 *
	 * @param other der andere Satz, aus dem die Teildatensaetze gezogen werden
	 * @since 5.2
	 */
	public void mergeWith(Satz other) {
		if (canBeMergedWith(other)) {
			LOG.info("{} wird mit {} zusammengefasst.", this, other);
			for (Teildatensatz otherTds : other.teildatensatz) {
				add(otherTds);
			}
			other.removeUnusedTeildatensaetze(new TreeSet<>());
		}
	}

	private boolean canBeMergedWith(Satz other) {
		for (Teildatensatz otherTds : other.teildatensatz) {
			Zeichen satznr = otherTds.getSatznummer();
			Feld versicherungscheinnr = otherTds.getFeld(ByteAdresse.VERSICHERUNGSSCHEINNUMMER);
			Feld folgenummer = otherTds.getFeld(Bezeichner.FOLGENUMMER);
			for (Teildatensatz thisTds : teildatensatz) {
				if (satznr.equals(thisTds.getSatznummer())) {
					return false;
				}
				if (!versicherungscheinnr.equals(thisTds.getFeld(ByteAdresse.VERSICHERUNGSSCHEINNUMMER)) &&
						!folgenummer.equals(thisTds.getFeld(Bezeichner.FOLGENUMMER))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Validiert die einzelnen Teildatensaetze mit der eingestellten
	 * Standard-Konfiguration.
	 *
	 * @return Liste mit Constraint-Verletzungen
	 */
	public List<ConstraintViolation> validate() {
		return validate(config);
	}

	/**
	 * Im Unterschied zur normalen validate-Methode kann man hier eine
	 * die Validierung ueber {@link Config#LAX} oder {@link Config#STRICT}
	 * verschaerfen oder abmildern.
	 *
	 * @param validationConfig z.B. {@link Config#STRICT}
	 * @return Liste mit Constraint-Verletzungen
	 * @since 5.4
	 */
	public List<ConstraintViolation> validate(Config validationConfig) {
		List<ConstraintViolation> violations = new ArrayList<>();
		if (!this.getSatzartFeld().isValid() || (this.getSatzart() < 1)) {
			ConstraintViolation cv =
					new SimpleConstraintViolation("invalid Satzart " + this.getSatzartFeld().getInhalt(), this, this.getSatzartFeld());
			violations.add(cv);
		}
		if (this.teildatensatz != null) {
			for (Teildatensatz tds : teildatensatz) {
				List<ConstraintViolation> tdsViolations = tds.validate(validationConfig);
				if (!tdsViolations.isEmpty()) {
					violations.add(new SimpleConstraintViolation(tds, tdsViolations));
				}
			}
		}
		violations.addAll(validateUniqueEntries());
		return violations;
	}

	private List<ConstraintViolation> validateUniqueEntries() {
		List<ConstraintViolation> violations = new ArrayList<>();
		Bezeichner[] bezeichner = { Bezeichner.VU_NR, Bezeichner.VS_NR, Bezeichner.VERMITTLER };
		for (Bezeichner b : bezeichner) {
			if (!hasFeld(b)) {
				continue;
			}
			String inhalt = getFeldInhalt(b);
			for (Teildatensatz tds : teildatensatz) {
				if (tds.hasFeld(b) && !inhalt.equals(tds.getFeldInhalt(b))) {
					ConstraintViolation cv = new SimpleConstraintViolation("has different values: " + getFeld(b),
							this, tds.getFeld(b));
					violations.add(cv);
				}
			}
		}
		return violations;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		try {
            return this.toShortString() + " (" + StringUtils.abbreviate(this.toLongString(), 47) + ")";
		} catch (RuntimeException shouldNeverHappen) {
			LOG.error("shit happens in toString()", shouldNeverHappen);
			return super.toString();
		}
	}

	/**
	 * To short string.
	 *
	 * @return the string
	 */
	public String toShortString() {
        return "Satzart " + this.getSatzTyp();
	}

	/**
	 * To long string.
	 *
	 * @return the string
	 */
	public String toLongString() {
		StringWriter swriter = new StringWriter();
		try {
			this.export(swriter, System.lineSeparator());
		} catch (IOException canthappen) {
			LOG.warn(canthappen + " ignored", canthappen);
			swriter.write(canthappen.getLocalizedMessage());
		}
		return swriter.toString();
	}

	/**
	 * Zwei Saetze sind gleich, wenn sie die gleichen Daten besitzen. Die
	 * Idee dabei ist, dass wir die beiden Saetze exportieren und dann das
	 * Resultat vergleichen.
	 *
	 * @param obj der andere Satz
	 * @return true, wenn beide Saetze gleich sind
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
	    if (!(obj instanceof Satz)) {
	        return false;
	    }
	    Satz other = (Satz) obj;
        return this.toLongString().equals(other.toLongString());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.getSatzart() * 1_000_000 + teildatensatz.length;
	}

    /**
     * Liefert die Felder aller Teildatensaetze zurueck.
     *
     * @return the felder
     * @since 1.2
     */
    public Collection<Feld> getFelder() {
        List<Feld> felder = new ArrayList<>();
        for (Teildatensatz tds : this.getTeildatensaetze()) {
            for (Feld feld : tds.getFelder()) {
                if (!contains(feld.getBezeichner(), felder)) {
                    felder.add(feld);
                }
            }
        }
        return felder;
    }

    private static boolean contains(Bezeichner bezeichner, List<Feld> felder) {
        for (Feld feld : felder) {
            if (bezeichner.equals(feld.getBezeichner())) {
                return true;
            }
        }
        return false;
    }

	/**
	 * Legt eine Kopie des Satzes an.
	 * 
	 * @return Kopie
	 * @throws CloneNotSupportedException sollte nicht auftreten
	 * @see Cloneable
	 */
	@SuppressWarnings("squid:S2975")
	@Override
	public Object clone() throws CloneNotSupportedException {
		Satz cloned = (Satz) super.clone();
		cloned.teildatensatz = new Teildatensatz[teildatensatz.length];
		for (int i = 0; i < teildatensatz.length; i++) {
			cloned.teildatensatz[i] = new Teildatensatz(teildatensatz[i]);
		}
		return cloned;
	}

}
