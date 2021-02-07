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
 * (c)reated 09.10.2009 by Oli B. (ob@aosd.de)
 */
package gdv.xport.satz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.io.ImportException;
import gdv.xport.io.PushbackLineNumberReader;
import gdv.xport.satz.enums.TeildatensatzEnum;
import gdv.xport.satz.feld.MetaFeldInfo;
import gdv.xport.satz.feld.common.Feld1bis7;
import gdv.xport.util.SatzTyp;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

import static gdv.xport.feld.Bezeichner.SATZART;
import static gdv.xport.feld.Bezeichner.SPARTE;
import static patterntesting.runtime.NullConstants.NULL_STRING;

/**
 * Die Satz-Klasse ist die oberste Klasse, von der alle weiteren Saetze
 * abgeleitet sind.
 *
 * @author oliver
 */
public abstract class Satz implements Cloneable {

	private static final Logger LOG = LogManager.getLogger(Satz.class);

	private final NumFeld satzart = new NumFeld((SATZART), 4, 1);
	private Teildatensatz[] teildatensatz = new Teildatensatz[0];

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
    private final AlphaNumFeld satzVersion = new AlphaNumFeld(3, 1);

  protected Satz(final int art)
  {
    this(art, 1);
  }

	protected Satz(final String art) {
		this(art, (art.length() + 255) / 256);
	}

	protected Satz(final NumFeld art) {
		this(art.getInhalt());
	}

	/**
	 * Instantiates a new satz.
	 *
	 * @param art Satzart
	 * @param n Anzahl der Teildatensaetze
	 */
	public Satz(final NumFeld art, final int n) {
		this.satzart.setInhalt(art.getInhalt());
		this.createTeildatensaetze(n);
	}

	/**
	 * Instantiates a new satz.
	 *
	 * @param content die Satzart
	 * @param n Anzahl
	 */
	public Satz(final String content, final int n) {
		this.satzart.setInhalt(content);
		this.createTeildatensaetze(n);
		if (content.length() > 4) {
			try {
				this.importFrom(content);
			} catch (IOException ioe) {
				throw new IllegalArgumentException("1st argument too short", ioe);
			}
		}
	}

	/**
	 * The Constructor.
	 *
	 * @param art z.B. 100 (f. Adressteil)
	 * @param n Anzahl der Teildatensaetze
	 */
	public Satz(final int art, final int n) {
		this.satzart.setInhalt(art);
		this.createTeildatensaetze(n);
	}

    /**
     * The Constructor.
     *
     * @param satz z.B. Satzart 0100 (f. Adressteil)
     * @param n    Anzahl der Teildatensaetze
     */
    public Satz(final Satz satz, final int n) {
        this.satzart.setInhalt(satz.getSatzart());
        this.gdvSatzartName = satz.getGdvSatzartName();
        this.gdvSatzartNummer = satz.getGdvSatzartNummer();
        this.setSatzversion(satz.getSatzversion().getInhalt());
        this.createTeildatensaetze(n);
    }

    /**
     * Instanziiert einen neuen Satz.
     *
     * @param art z.B. 100 (f. Adressteil)
     * @param tdsList Liste mit den Teildatensaetzen
     */
     public Satz(final int art, final List<? extends Teildatensatz> tdsList) {
        this(art, tdsList, null);
     }

    /**
     * Instanziiert einen neuen Satz.
     *
     * @param satz        z.B. 100 (f. Adressteil)
     * @param tdsList     Liste mit den Teildatensaetzen
     */
    public Satz(final Satz satz, final List<? extends Teildatensatz> tdsList) {
        this.satzart.setInhalt(satz.getSatzart());
        this.satzVersion.setInhalt(satz.getSatzversion().getInhalt());
        this.gdvSatzartName = satz.getGdvSatzartName();
        this.gdvSatzartNummer = satz.getGdvSatzartNummer();
        this.createTeildatensaetze(tdsList);
    }

	/**
	 * Instanziiert einen neuen Satz.
	 *
	 * @param art         z.B. 100 (f. Adressteil)
	 * @param tdsList     Liste mit den Teildatensaetzen
	 * @param satzVersion die Version des Satzes
	 */
	public Satz(final int art, final List<? extends Teildatensatz> tdsList, final AlphaNumFeld satzVersion) {
		this.satzart.setInhalt(art);
		if (satzVersion != null) this.setSatzversion(satzVersion.getInhalt());
		this.createTeildatensaetze(tdsList);
	}

    protected void createTeildatensaetze(final int n) {
        teildatensatz = new Teildatensatz[n];
		for (int i = 0; i < n; i++) {
			teildatensatz[i] = new TeildatensatzEnum(satzart, i + 1);
		}
	}

	protected void createTeildatensaetze(final List<? extends Teildatensatz> tdsList) {
		teildatensatz = new Teildatensatz[tdsList.size()];
		for (int i = 0; i < tdsList.size(); i++) {
			teildatensatz[i] = new Teildatensatz(tdsList.get(i));
		}
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
     * Teildatensatzes (siehe z.B. Satzart 0221.140).<br/>
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
		this.teildatensatz = (Teildatensatz[]) ArrayUtils.remove(this.teildatensatz, n - 1);
	}

	/**
	 * Und hierueber kann ein Teildatensatz hinzugefuegt werden.
	 *
	 * @param tds der neue (gefuellte) Teildatensatz
     * @since 0.4
	 */
	public final void add(final Teildatensatz tds) {
		this.teildatensatz = (Teildatensatz[]) ArrayUtils.add(this.teildatensatz, tds);
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
		this.remove(new Bezeichner(name));
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
     */
    public void set(final String name, final String value) {
        this.set(new Bezeichner(name), value);
    }

    /**
     * Setzt den Inhalt des gewuenschten Feldes.
     *
     * @param name  Name des Felds (Bezeichnung)
     * @param value neuer Inhalt
     */
    public void set(final Bezeichner name, final Integer value) {
        this.set(name, Integer.toString(value));
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
     * @since 2.0
     */
    public void set(final Bezeichner name, final String value) {
        boolean found = false;
        for (Teildatensatz tds : teildatensatz) {
            Feld x = tds.getFeldSafe(name);
            if (x != Feld.NULL_FELD) {
                x.setInhalt(value);
                found = true;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Feld \"" + name + "\" not found");
        }
    }

	/**
	 * Setzt den Inhalt des gewuenschten Feldes.
	 *
	 * @param feldX das gewuenschte Feld-Element
	 * @param value neuer Inhalt
	 */
	public final void set(final Enum feldX, final String value) {
		Bezeichner name = Feld.getAsBezeichner(feldX);
		this.set(name, value);
	}

    /**
     * Setzt den Inhalt des gewuenschten Feldes.
     *
     * @param feldX das gewuenschte Feld-Element
     * @param value neuer Inhalt
     * @since 0.9 (oboehm, 1-Apr-2013)
     */
    public final void set(final Enum feldX, final Integer value) {
        this.set(feldX, Integer.toString(value));
    }

    /**
     * Setzt den Inhalt des gewuenschten Feldes.
     *
     * @param feldX das gewuenschte Feld-Element
     * @param value neuer Inhalt
     * @since 0.9 (oboehm, 1-Apr-2013)
     */
    public final void set(final Enum feldX, final Character value) {
        this.set(feldX, Character.toString(value));
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
    /**
     * Setzt die Version des Satzes
     * 
     * @param version die Satzversion
     */
    public final void setSatzversion(final String version) {
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
     * Liefert den Inhalt des gewuenschten Feldes.
	 *
	 * @param name gesuchtes Feld
	 * @return Inhalt des gefundenden Felds (NULL_STRING, falls 'name' nicht
	 * gefunden wurde)
	 */
	public final String get(final String name) {
		return get(new Bezeichner(name));
	}

    /**
     * Liefert den Inhalt des gewuenschten Feldes.
     *
     * @param bezeichner gesuchtes Field
     * @return Inhalt des gefundenden Felds (NULL_STRING, falls 'name' nicht
     * gefunden wurde)
     * @since 2.0
     */
    public String get(final Bezeichner bezeichner) {
        Feld f = getFeld(bezeichner);
        if (f == Feld.NULL_FELD) {
            return NULL_STRING;
        } else {
            return f.getInhalt();
        }
    }

	/**
	 * Liefert den Inhalt des gewuenschten Feldes.
	 *
	 * @param feldX das gewuenschte Feld-Element
	 * @return Inhalt des gefundenden Felds
	 */
	public final String get(final Enum feldX) {
		Bezeichner name = Feld.getAsBezeichner(feldX);
		return this.get(name);
	}

	/**
	 * Liefert das gewuenschte Feld. Allerdings wird nur der Name des Feldes
	 * benutzt, um das Feld zu bestimmen. Dazu werden auch die Konstanten in
     * {@link gdv.xport.feld.Bezeichner} verwendet.
	 * <p>
	 * TODO: Wird in v6 entfernt werden.
	 * </p>
	 *
	 * @param feld gewuenschtes Feld-Element
	 * @return das gesuchte Feld
	 * @throws IllegalArgumentException falls es das Feld nicht gibt
	 * @deprecated inzwischen durch {@link #getFeld(Bezeichner)} abgeloest
	 */
	@Deprecated
	public Feld getFeld(final Enum feld) throws IllegalArgumentException {
		for (int i = 0; i < teildatensatz.length; i++) {
			try {
				Feld x = teildatensatz[i].getFeldSafe(feld);
				if (x != Feld.NULL_FELD) {
					return x;
				}
			} catch (IllegalArgumentException e) {
			    LOG.debug("Feld '{}‘ not found in teildatensatz {}:", feld, i, e);
			}
		}
		throw new IllegalArgumentException("Feld \"" + feld + "\" nicht in " + this.toShortString()
		        + " vorhanden!");
	}

    /**
     * Liefert das gewuenschte Feld oder {@link Feld#NULL_FELD}, wenn nicht
     * vorhanden. Allerdings wird nur der Name des Feldes benutzt, um das Feld zu
     * bestimmen. Dazu werden auch die Konstanten in
     * {@link gdv.xport.feld.Bezeichner} verwendet.
	 * <p>
	 * TODO: Wird in v6 entfernt.
	 * </p>
     *
     * @param feld gewuenschtes Feld-Element
     * @return das gesuchte Feld
	 * @deprecated durch {@link #getFeldSafe(Bezeichner)} abgeloest
     */
    @Deprecated
    public Feld getFeldSafe(final Enum feld) {
        try {
            return getFeld(feld);
        } catch (IllegalArgumentException ex) {
            return Feld.NULL_FELD;
        }
    }

	/**
	 * Liefert das gewuenschte Feld falls vorhanden oder null.
	 *
	 * @param name gewuenschter Bezeichner des Feldes
	 * @return das gesuchte Feld
	 */
	public Feld containsFeld(final String name) {
        for (Teildatensatz tds : teildatensatz) {
            Feld x = tds.getFeldSafe(name);
            if (x != Feld.NULL_FELD) {
                return x;
            }
        }
		LOG.debug("Feld \"{}\" not found in {}.", name, this);
		return null;
	}

	/**
	 * Liefert das gewuenschte Feld.
	 *
	 * @param name gewuenschter Bezeichner des Feldes
	 * @return das gesuchte Feld
	 * @throws IllegalArgumentException falls es das Feld nicht gibt
	 */
	public Feld getFeld(final String name) throws IllegalArgumentException {
		return this.getFeld(Bezeichner.of(name));
	}
	
    /**
     * Liefert das gewuenschte Feld oder {@link Feld#NULL_FELD}, wenn nicht
     * vorhanden.
     *
     * @param name gewuenschter Bezeichner des Feldes
     * @return das gesuchte Feld
     */
    public Feld getFeldSafe(final String name) {
        return this.getFeldSafe(Bezeichner.of(name));
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
     * Liefert das gewuenschte Feld.
     *
     * @param bezeichner gewuenschter Bezeichner des Feldes
     * @return das gesuchte Feld
     * @throws IllegalArgumentException falls es das Feld nicht gibt
     */
    public Feld getFeld(final Bezeichner bezeichner) throws IllegalArgumentException {
        for (Teildatensatz tds : teildatensatz) {
        	for (Bezeichner b : bezeichner.getVariants()) {
				Feld x = tds.getFeldSafe(b);
				if (x != Feld.NULL_FELD) {
					return x;
				}
			}
        }
        throw new IllegalArgumentException("Feld \"" + bezeichner + "\" nicht in " + this.toShortString()
                + " vorhanden!");
    }

	/**
	 * Liefert das gewuenschte Feld im gewuenschten Typ.
	 * Falls als Typ {@link BetragMitVorzeichen} gewuenscht wird, wird das
	 * Feld mit dem angegebenen Bezeichner und das benachbarte Vorzeichenfeld
	 * zusammengefasst und als Einheit zurueckgegeben.
	 *
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
				betrag.getAnzahlBytes()+1, betrag.getByteAdresse());
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
     * Liefert das gewuenschte Feld oder {@link Feld#NULL_FELD}, wenn nicht
     * vorhanden.
     *
     * @param bezeichner gewuenschter Bezeichner des Feldes
     * @return das gesuchte Feld
     */
    public Feld getFeldSafe(final Bezeichner bezeichner) {
        try {
            return getFeld(bezeichner);
        } catch (IllegalArgumentException ex) {
            return Feld.NULL_FELD;
        }
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
	    return getFeld(bezeichner.getName(), nr);
	}

	/**
	 * Liefert das gewuenschte Feld.
	 *
	 * @param name gewuenschter Bezeichner des Feldes
	 * @param nr Nummer des Teildatensatzes (1, 2, ...)
	 * @return NULL_FELD, falls das angegebene Feld nicht gefunden wird
     * @throws IllegalArgumentException falls es das Feld nicht gibt
	 * @since 0.2
	 */
	public final Feld getFeld(final String name, final int nr) throws IllegalArgumentException {
		assert (0 < nr) && (nr <= teildatensatz.length) : nr + " liegt ausserhalb des Bereichs";
		return teildatensatz[nr - 1].getFeld(name);
	}

	/**
	 * Liefert den Inhalt des gewuenschten Feldes.
	 *
	 * @param name gewuenschter Bezeichner des Feldes
	 * @param nr Nummer des Teildatensatzes (1, 2, ...)
	 * @return Inhalt des Feldes (getrimmt, d.h. ohne Leerzeichen am Ende)
     * @throws IllegalArgumentException falls es das Feld nicht gibt
	 * @since 0.3
	 */
	public final String getFeldInhalt(final String name, final int nr) throws IllegalArgumentException {
		return this.getFeld(name, nr).getInhalt().trim();
	}

	/**
	 * Liefert die Satzart.
	 *
	 * @return the satzart
	 */
	public final NumFeld getSatzartFeld() {
		return this.satzart;
	}

	/**
	 * Liefert die Satzart zurueck.
	 *
	 * @return die Satzart als int
     * @since 0.3
	 */
	public final int getSatzart() {
		return this.satzart.toInt();
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
		} else if (this.hasSparte() && this.getSparte() == 10 && this.hasWagnisart()) {
	        String wagnisart = this.getWagnisart();
            return SatzTyp.of(this.getSatzart(), this.getSparte(), Integer.parseInt(wagnisart));
	    } else if (this.hasSparte()) {
	        return new SatzTyp(this.getSatzart(), this.getSparte());
	    } else {
	        return new SatzTyp(this.getSatzart());
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
	    Feld sparte = this.getFeldSafe(Feld1bis7.SPARTE);
	    return (sparte != Feld.NULL_FELD) && !sparte.isEmpty();
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
        return this.getSatzart() == 220 && this.getSparte() == 20
                && (this.hasFeld(Bezeichner.FOLGE_NR_ZUR_LAUFENDEN_PERSONEN_NR_UNTER_NR_LAUFENDE_NR_TARIF)
                        || this.hasFeld(Bezeichner.FOLGE_NR_ZUR_LAUFENDEN_PERSONEN_NR_UNTER_NR_BZW_LAUFENDEN_NR_TARIF));
    }

	/**
	 * Liefert den Inhalt des Sparten-Felds. Vorher sollte allerdings mittels
	 * {@link #hasSparte()} geprueft werden, ob der Satz ein Sparten-Feld
	 * besitzt.
	 *
     * @return die Sparte
     * @since 0.9
	 */
	@JsonIgnore
	public int getSparte() {
	    NumFeld sparte = (NumFeld) this.getFeld(Feld1bis7.SPARTE);
	    return sparte.toInt();
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
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    public void importFrom(final String s) throws IOException {
        int satzlength = getSatzlength(s);
        SortedSet<Integer> importedTeilsatzIndexes = new TreeSet<>();
        for (int i = 0; i < teildatensatz.length; i++) {
            String input = s.substring(i * satzlength);
            if (input.trim().isEmpty()) {
                LOG.info("mehr Daten fuer Satz " + this.getSatzart() + " erwartet, aber nur " + i
                        + " Teildatensaetze vorgefunden");
                removeUnusedTeildatensaetze(importedTeilsatzIndexes);
                break;
            }
            int satznummer = readSatznummer(input.toCharArray()) - '0';
            int teildatensatzIndex = getTeildatensatzIndex(i, satznummer);
            teildatensatz[teildatensatzIndex].importFrom(input);
            importedTeilsatzIndexes.add(teildatensatzIndex);
        }
    }
    
    private int getTeildatensatzIndex(int index, int satznummer) {
        if (satznummer < 1) {
            return index;
        }
        for (int i = 0; i < teildatensatz.length; i++) {
            if (teildatensatz[i].getSatznummer().toInt() == satznummer) {
                return i;
            }
        }
        return index;
    }

    private void removeUnusedTeildatensaetze(SortedSet<Integer> usedIndexes) {
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
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void importFrom(final File file) throws IOException {
        try (Reader reader = new FileReader(file)) {
            this.importFrom(reader);
        }
	}

	/**
	 * Ermittelt die Satzlaenge. Je nachdem, ob das Zeilenende aus keinem, einem
	 * oder zwei Zeichen besteht, wird 256, 257 oder 258 zurueckgegeben.
	 *
	 * @param s der komplette Satz
	 * @return 256, 257 oder 258
     * @since 0.4
	 */
	protected final int getSatzlength(final String s) {
		int satzlength = 256;
		try {
			char c256 = s.charAt(256);
			if ((c256 == '\n') || (c256 == '\r')) {
				satzlength = 257;
			}
			if (s.length() > satzlength) {
				char c257 = s.charAt(257);
				if ((c257 == '\n') || (c257 == '\r')) {
					satzlength = 258;
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			LOG.trace("end of string \"" + s + "\" reached", e);
		}
		return satzlength;
	}

	/**
	 * Import from.
	 *
	 * @param istream the istream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public final void importFrom(final InputStream istream) throws IOException {
		importFrom(new InputStreamReader(istream, Config.DEFAULT_ENCODING));
	}

	/**
	 * Import from.
	 *
	 * @param reader the reader
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public final void importFrom(final Reader reader) throws IOException {
	    PushbackLineNumberReader lnr = new PushbackLineNumberReader(reader, 256);
		try {
            importFrom(lnr);
		} catch (IOException ioe) {
		    throw new ImportException(lnr, "read error", ioe);
		} catch (NumberFormatException nfe) {
            throw new ImportException(lnr, "number expected", nfe);
		}
	}

	/**
	 * Der hier verwendete PushbackReader wird benoetigt, damit die gelesene
	 * Satzart und Sparte wieder zurueckgesetllt werden kann. Seit 0.5.1 ist
	 * diese Methode nicht mehr final, da manche Satzarten wohl Eigenarten haben
	 * koennen (wie z.B. fehlende Sparten-Eintraege).
	 *
	 * @param reader the reader
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
    public void importFrom(final PushbackLineNumberReader reader) throws IOException {
        char[] cbuf = new char[257 * teildatensatz.length];
        char[] feld1to7 = null;
        Character satznummer = null;
        for (int i = 0; i < teildatensatz.length; i++) {
            reader.skipNewline();
            if (!matchesNextTeildatensatz(reader, feld1to7, satznummer)) {
                LOG.info((teildatensatz.length - i) + " more Teildatensaetze expected for " + this
                        + ", but Satzart or Sparte or Wagnisart or TeildatensatzNummer has changed");
                break;
            }
            satznummer = readSatznummer(reader);
            importFrom(reader, cbuf, i * 257);
            cbuf[i * 257 + 256] = '\n';
            feld1to7 = Arrays.copyOfRange(cbuf, i*257,  i*257 + 42);
        }
        importFrom(new String(cbuf));
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
            int art = readSatzart(reader);
            return art == this.getSatzart();
        } catch (EOFException ex) {
            LOG.info("No next teildatensatz found ({}).", ex.getLocalizedMessage());
            LOG.debug("Details:", ex);
            return false;
        }
	}

	private static void importFrom(final Reader reader, final char[] cbuf, final int i)
	        throws IOException {
		if (reader.read(cbuf, i, 256) == -1) {
			throw new EOFException("can't read 256 bytes from " + reader);
		}
	}

	/**
	 * Liest 4 Bytes, um die Satzart zu bestimmen und stellt die Bytes
	 * anschliessend wieder zurueck in den Reader.
	 *
	 * @param reader the reader
	 * @return Satzart
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static int readSatzart(final PushbackLineNumberReader reader) throws IOException {
        reader.skipWhitespace();
		char[] cbuf = new char[4];
		importFrom(reader, cbuf);
		reader.unread(cbuf);
        return Integer.parseInt(new String(cbuf).trim());
	}

	private static void importFrom(final Reader reader, final char[] cbuf) throws IOException {
		if (reader.read(cbuf) == -1) {
			String s = new String(cbuf).trim();
			throw new EOFException("can't read " + cbuf.length + " bytes from " + reader + ", only \""
			        + s + "\" ("+ s.length() + " bytes)");
		}
	}

    /**
	 * Aus Performance-Gruenden stuetzt sich diese Methode nicht auf die
	 * validate()-Methode ab.
	 *
	 * @return true/false
	 */
	public boolean isValid() {
		if (!this.satzart.isValid() || (this.getSatzart() < 1)) {
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
	 * Validiert die einzelnen Teildatensaetze.
	 *
	 * @return Liste mit Constraint-Verletzungen
	 */
	public List<ConstraintViolation> validate() {
		Validator validator = new Validator();
		List<ConstraintViolation> violations = validator.validate(this);
		if (!this.satzart.isValid() || (this.getSatzart() < 1)) {
			ConstraintViolation cv =
					new SimpleConstraintViolation("invalid Satzart " + this.satzart.getInhalt(), this, this.satzart);
			violations.add(cv);
		}
		if (this.teildatensatz != null) {
            for (Teildatensatz tds : teildatensatz) {
                violations.addAll(tds.validate());
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
        return "Satzart " + this.satzart.getInhalt();
	}

	/**
	 * To long string.
	 *
	 * @return the string
	 */
	public String toLongString() {
		StringWriter swriter = new StringWriter();
		try {
			this.export(swriter);
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
		return this.getSatzart();			// TODO: intelligentere Implementierung mit weniger Kollisionen
	}

	// /// Enum-Behandlung und Auswertung der Meta-Infos ///////////////////

	/**
	 * Hier passiert die Magie: die Annotationen der uebergebenen Enum werden
	 * ausgelesen und in eine Liste mit den Teildatensaetzen gepackt.
	 *
	 * @param satzart the satzart
	 * @param felder the felder
	 * @return eine Liste mit Teildatensaetzen
	 */
	protected static List<Teildatensatz> getTeildatensaetzeFor(final int satzart,
	        final Enum[] felder) {
		SortedMap<Integer, Teildatensatz> tdsMap = new TreeMap<>();
		List<MetaFeldInfo> metaFeldInfos = getMetaFeldInfos(felder);
        for (MetaFeldInfo metaFeldInfo : metaFeldInfos) {
			int n = metaFeldInfo.getTeildatensatzNr();
			Teildatensatz tds = tdsMap.get(n);
			if (tds == null) {
				tds = new TeildatensatzEnum(satzart, n);
				tdsMap.put(n, tds);
			}
			add(metaFeldInfo.getFeldEnum(), tds);

		}
        List<Teildatensatz> teildatensaetze = new ArrayList<>(tdsMap.values());
		setSparteFor(teildatensaetze, metaFeldInfos);
		return teildatensaetze;
	}

	/**
	 * Durch die Uebergabe eines SatzTyp kann der GdvSatzartName im
	 *       Teildatensatz besetzt werden. Bei den SatzXml ist alles eleganter..
	 *
	 *       Hier passiert die Magie: die Annotationen der uebergebenen Enum
	 *       werden ausgelesen und in eine Liste mit den Teildatensaetzen gepackt.
	 *
	 * @param satzTyp the satzTyp
	 * @param felder the felder
	 * @return eine Liste mit Teildatensaetzen
	 */
	protected static List<Teildatensatz> getTeildatensaetzeFor(
			final SatzTyp satzTyp, final Enum[] felder)
	{
		SortedMap<Integer, Teildatensatz> tdsMap = new TreeMap<>();
		List<MetaFeldInfo> metaFeldInfos = getMetaFeldInfos(felder);
		for (MetaFeldInfo metaFeldInfo : metaFeldInfos)
		{
			int n = metaFeldInfo.getTeildatensatzNr();
			Teildatensatz tds = tdsMap.get(n);
			if (tds == null)
			{
				tds = new Teildatensatz(satzTyp, n);
				tdsMap.put(n, tds);
			}
			add(metaFeldInfo.getFeldEnum(), tds);

		}
		List<Teildatensatz> teildatensaetze = new ArrayList<>(tdsMap.values());
		setSparteFor(teildatensaetze, metaFeldInfos);
		return teildatensaetze;
	}

    private static void setSparteFor(final List<Teildatensatz> teildatensaetze,
            final List<MetaFeldInfo> metaFeldInfos) {
        int sparte = getSparte(metaFeldInfos);
        if (sparte > 0) {
            setSparteFor(teildatensaetze, sparte);
        }
    }

    private static int getSparte(final List<MetaFeldInfo> metaFeldInfos) {
        for (MetaFeldInfo info : metaFeldInfos) {
            if (info.hasSparte()) {
                return info.getSparte();
            }
        }
        return -1;
    }

    private static void setSparteFor(final List<Teildatensatz> teildatensaetze, final int sparte) {
        for (Teildatensatz teildatensatz : teildatensaetze) {
            setSparteFor(teildatensatz, sparte);
        }
    }

    private static void setSparteFor(final Teildatensatz tds, final int sparte) {
        Feld spartenFeld = tds.getFeldSafe(Feld1bis7.SPARTE);
        if (spartenFeld == Feld.NULL_FELD) {
            spartenFeld = new NumFeld((SPARTE), 3, 11);
            tds.add(spartenFeld);
        }
        spartenFeld.setInhalt(sparte);
    }

    /**
	 * Wandelt das uebergebene Array in eine Liste mit MetaFeldInfos. Seit 0.7.1
	 * duerfen Feld-Enums wie {@link gdv.xport.satz.feld.Feld100} auch
	 * FelderInfo-Annotationen enthalten, die wiederum auf einen Enum verweisen.
	 *
	 * @param felder the felder
	 * @return the meta feld infos
	 */
	protected static List<MetaFeldInfo> getMetaFeldInfos(final Enum[] felder) {
		List<MetaFeldInfo> metaFeldInfos = new ArrayList<>(felder.length);
        for (Enum f : felder) {
            String name = f.name();
            try {
                Field field = f.getClass().getField(name);
                FelderInfo info = field.getAnnotation(FelderInfo.class);
                if (info == null) {
                    metaFeldInfos.add(new MetaFeldInfo(f));
                } else {
                    metaFeldInfos.addAll(getMetaFeldInfos(info));
                }
            } catch (NoSuchFieldException nsfe) {
                throw new InternalError("no field " + name + " (" + nsfe + ")");
            }
        }
		return metaFeldInfos;
	}

	private static List<MetaFeldInfo> getMetaFeldInfos(final FelderInfo info) {
		Collection<? extends Enum> enums = getAsList(info);
		List<MetaFeldInfo> metaFeldInfos = new ArrayList<>(enums.size());
		for (Enum enumX : enums) {
			metaFeldInfos.add(new MetaFeldInfo(enumX, info));
		}
		return metaFeldInfos;
	}

	private static Collection<? extends Enum> getAsList(final FelderInfo info) {
		Class<? extends Enum> enumClass = info.type();
		return getAsList(enumClass.getEnumConstants());
	}

	/**
	 * Wandelt das uebergebene Array in eine Liste mit Felder. Seit 0.7.1
	 * duerfen Feld-Enums wie {@link gdv.xport.satz.feld.Feld100} auch
	 * FelderInfo-Annotationen enthalten, die wiederum auf einen Enum verweisen.
	 *
	 * @param felder the felder
	 * @return the feld info list
	 */
	private static List<Enum> getAsList(final Enum[] felder) {
		List<Enum> feldList = new ArrayList<>(felder.length);
        for (Enum f : felder) {
            String name = f.name();
            try {
                Field field = f.getClass().getField(name);
                FelderInfo info = field.getAnnotation(FelderInfo.class);
                if (info == null) {
                    feldList.add(f);
                } else {
                    feldList.addAll(getAsList(info));
                }
            } catch (NoSuchFieldException nsfe) {
                throw new InternalError("no field " + name + " (" + nsfe + ")");
            }
        }
		return feldList;
	}

	/**
	 * Leitet aus dem uebergebenen Feldelement die Parameter ab, um daraus ein
	 * Feld anzulegen und im jeweiligen Teildatensatz einzuhaengen. Zusaetzlich
	 * wird das Feld "Satznummer" vorbelegt, falls es in den uebergebenen
	 * Feldern vorhanden ist.
	 * <p>
	 * TODO: Vorsatz wird noch nicht richtig behandelt, da die ersten 6 Felder
	 * hier etwas anders behandelt wird.
	 * </p>
	 *
	 * @param feldX das Feld-Element
	 * @param tds der entsprechende Teildatensatz
	 */
	protected static void add(final Enum feldX, final Teildatensatz tds) {
		FeldInfo info = MetaFeldInfo.getFeldInfo(feldX);
		Feld feld = Feld.createFeld(feldX, info);
		if (info.nr() < 7) {      // TODO: diese Abfrage ist eigentlich unnoetig
			LOG.debug("using default settings for " + feld);
		} else {
			tds.add(feld);
			if (isSatznummer(feldX)) {
				feld.setInhalt(info.teildatensatz());
			}
		}
	}

	private static boolean isSatznummer(final Enum feldX) {
		return (feldX.name().length() <= 11) && feldX.name().startsWith("SATZNUMMER");
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
	 * Liest die Satznummer.
     *
     * @param reader den Reader
     * @return Teildatensatz-Nummer
	 * @throws IOException bei Lesefehler
     */
    public static Character readSatznummer(final PushbackReader reader) throws IOException {
        char[] cbuf = new char[256];
        if (reader.read(cbuf) == -1) {
            throw new EOFException("can't read 1 bytes (" + new String(cbuf) + ") from " + reader);
        }
        reader.unread(cbuf);
        return readSatznummer(cbuf);
    }

	/**
	 * Liest die Satznummer.
	 *
	 * @param cbuf der eingelesene Satz in char array
	 * @return Teildatensatz -Nummer
	 */
	public static char readSatznummer(char[] cbuf) {
		if (cbuf.length < 256) {
			return 0;
		}
		String satz = new String(cbuf);
		String satzartString = satz.substring(0, 4)
								   .trim();
		int satzart = isNumber(satzartString) ? Integer.parseInt(satzartString)
				: -1;
		String sparteString = satz.substring(10, 13)
								  .trim();
		int sparte = isNumber(sparteString) ? Integer.parseInt(sparteString) : -1;

		int satznummerIndex = 255;
		switch (satzart) {
			case 210:
				satznummerIndex = getSatznummerIndexOfSatz210(sparte);
				break;
			case 211:
				switch (sparte) {
					case 0:
					case 80:
					case 170:
					case 190:
						satznummerIndex = 42;
						break;
					default:
						break;
				}
				break;
			case 220:
			case 221:
				satznummerIndex = getSatznummerIndexOf(satz, sparte);
				break;
			case 250:
			case 251:
				satznummerIndex = 50;
				break;
			case 270:
			case 280:
			case 291:
			case 292:
			case 293:
			case 294:
			case 295:
				satznummerIndex = 42;
				break;
			case 410:
			case 420:
			case 450:
				satznummerIndex = 50;
				break;
			case 550:
				satznummerIndex = 65;
				break;
			default:
				break;
		}
		return satz.charAt(satznummerIndex);
	}

	private static int getSatznummerIndexOfSatz210(int sparte) {
		switch (sparte) {
			case 0:
			case 80:
			case 170:
			case 190:
			case 550:
			case 560:
			case 570:
			case 580:
				return 42;
			case 130:
				return 250;
			default:
				return 255;
		}
	}

	private static int getSatznummerIndexOf(String satz, int sparte) {
		switch (sparte) {
			case 0:
				return 46;
			case 30:
				if ((satz.charAt(48) == '2' && satz.charAt(255) == 'X') || (satz.charAt(
						48) == '1' || satz.charAt(48) == '4')) {
					return 48;
				} else if (Character.isDigit(satz.charAt(255)) && satz.charAt(255) != '0'
						&& satz.charAt(255) != '2') {
					return 249;
				} else if (satz.charAt(42) == '3') {
					return 42;
				} else {
					return 59;
				}
			case 40:
			case 140:
				return 50;
			case 70:
				return 52;
			case 80:
			case 190:
				return 48;
			case 170:
				return 49;
			case 550:
			case 560:
			case 570:
			case 580:
				return 42;
			default:
				return 255;
		}
	}

	public static boolean isNumber(String string) {
        return string.matches("-?\\d+");
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
