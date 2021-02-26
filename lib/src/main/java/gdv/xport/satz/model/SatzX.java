/*
 * Copyright (c) 2011-2013 by Oli B.
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
 * (c)reated 09.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.model;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.Feld;
import gdv.xport.feld.NumFeld;
import gdv.xport.io.PushbackLineNumberReader;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.enums.TeildatensatzEnum;
import gdv.xport.satz.feld.FeldX;
import gdv.xport.satz.feld.MetaFeldInfo;
import gdv.xport.satz.feld.common.Feld1bis7;
import gdv.xport.satz.feld.common.TeildatensatzNummer;
import gdv.xport.satz.feld.common.WagnisartLeben;
import gdv.xport.util.SatzTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import static gdv.xport.feld.Bezeichner.SPARTE;

/**
 * Dies ist die gemeinsame Oberklasse aller Saetze in diesem Package, die nach
 * dem SOP-Muster aufgebaut sind. Eventuell wird diese Klasse mit der Oberklasse
 * vereinigt.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (09.03.2011)
 */
public class SatzX extends Datensatz {

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Instantiiert einen neuen Datensatz.
	 *
	 * @param satzTyp         Satzart
	 * @param teildatensaetze Teildatensaetze
	 * @since 5.0
	 */
	public SatzX(SatzTyp satzTyp, List<Teildatensatz> teildatensaetze) {
		super(satzTyp, teildatensaetze);
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 * <p>
	 * TODO: Wird mit v6 entfernt.
	 * </p>
	 *
	 * @param satzart z.B. 100
	 * @param felder mit allen Elementen des Datensatzes
	 * @deprecated bitte {@link SatzX#SatzX(SatzTyp, List)} verwenden
	 */
	@Deprecated
	public SatzX(final int satzart, final Enum[] felder) {
		this(SatzTyp.of(satzart), getTeildatensaetzeFor(satzart, felder));
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 * <p>
	 * TODO: Wird mit v6 entfernt.
	 * </p>
	 *
	 * @param satzart z.B. 100
	 * @param enumClass Enumerationen-Klasse mit den Feldbeschreibungen
	 * @deprecated bitte {@link SatzX#SatzX(SatzTyp, List)} verwenden
	 */
	@Deprecated
	public SatzX(final int satzart, final Class<? extends Enum> enumClass) {
		this(SatzTyp.of(satzart), getTeildatensaetzeFor(SatzTyp.of(satzart), enumClass));
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 * <p>
	 * TODO: Wird mit v6 entfernt.
	 * </p>
	 *
	 * @param satzart z.B. 100
	 * @param sparte Sparte
	 * @param felder mit allen Elementen des Datensatzes
	 * @deprecated bitte {@link SatzX#SatzX(SatzTyp, List)} verwenden
	 */
	@Deprecated
	public SatzX(final int satzart, final int sparte, final Enum[] felder) {
		this(SatzTyp.of(satzart, sparte), getTeildatensaetzeFor(satzart, felder));
	}

	/**
	 * Instantiiert einen neuen Datensatz.
	 * <p>
	 * TODO: Wird mit v6 entfernt.
	 * </p>
	 *
	 * @param satzart z.B. 100
	 * @param sparte Sparte
	 * @param enumClass Enumerationen-Klasse mit den Feldbeschreibungen
	 * @deprecated bitte {@link SatzX#SatzX(SatzTyp, List)} verwenden
	 */
	@Deprecated
	public SatzX(final int satzart, final int sparte, final Class<? extends Enum> enumClass) {
		this(SatzTyp.of(satzart, sparte), getTeildatensaetzeFor(SatzTyp.of(satzart, sparte), enumClass));
	}

	/**
	 * Instantiiert einen allgemeinen Datensatz fuer die angegebene Satzart und
	 * Sparte. Dieser Konstruktor ist hauptsaechlich als Fallback fuer
	 * Satzarten/Sparten gedacht, die noch nicht unterstuetzt werden.
	 *
	 * @param satzart z.B. 100
	 * @param sparte Sparte
	 */
	public SatzX(final int satzart, final int sparte) {
		this(satzart, sparte, FeldX.values());
	}

	/**
	 * Instantiiert einen allgemeinen Datensatz fuer die angegebene Satznummer.
	 *
	 * @param satzNr Satznummer mit Satzart, Sparte, Wagnisart, lfd. Nummer
	 * @param enumClass Enum-Klasse, die den Datensatz beschreibt
	 * @since 0.9
	 */
  public SatzX(final SatzTyp satzNr, final Class<? extends Enum> enumClass)
  {
    super(satzNr, getTeildatensaetzeFor(satzNr, enumClass));

    this.setGdvSatzartName(satzNr.toString());
    if (satzNr.hasSparte())
      this.setSparte(satzNr.getSparteAsString());
	  if (satzNr.hasTeildatensatzNummer())
		  this.setGdvSatzartNummer(String.valueOf(satzNr
				  .getTeildatensatzNummer()));
	}

  /**
   * hierdurch werden Teildatensaetze erzeugt, die ihren GdvSatzartNamen kennen
   */
  private static List<Teildatensatz> getTeildatensaetzeFor(final SatzTyp satzNr,
      final Class<? extends Enum> enumClass)
  {
    Enum[] constants = enumClass.getEnumConstants();
    return getTeildatensaetzeFor(satzNr, constants);
  }
	/**
	 * Setzt die Teildatensaetze mit den angegebenen Feldern auf.
	 *
	 * @param felder Felder fuer die Teildatensaetze.
	 */
	protected void setUpTeildatensaetze(final Enum[] felder) {
		super.createTeildatensaetze(getTeildatensaetzeFor(this.getSatzart(), felder));
		super.completeTeildatensaetze();
	}

	/**
	 * Unterklassen (wie Datensatz) sind dafuer verantwortlich, dass auch noch
	 * die Wagnisart und die TeildatensatzNummer ueberprueft wird, ob sie noch
	 * richtig ist oder ob da schon der naechste Satz beginnt.
	 *
	 * @param reader ein Reader
	 * @param lastFeld1To7 Feld1..7 als Char-Array (42 Zeichen) der letzten Zeile oder {@code null} für ersten Teildatensatz
	 * @return true (Default-Implementierung)
	 * @throws IOException bei I/O-Fehlern
	 * @since 0.9
	 * @see gdv.xport.satz.Satz#matchesNextTeildatensatz(PushbackLineNumberReader, char[], Character)
	 */
	@Override
	protected boolean matchesNextTeildatensatz(final PushbackLineNumberReader reader, char[] lastFeld1To7, Character satznummer) throws IOException {
		if (super.matchesNextTeildatensatz(reader, lastFeld1To7, satznummer)) {
			WagnisartLeben nextLineWagnisEnum = readWagnisart(reader);
			TeildatensatzNummer nextLineTeildatensatzNummerEnum = readTeildatensatzNummer(reader);

			boolean nextLineWagnisartIsSet = nextLineWagnisEnum != WagnisartLeben.NULL;
            boolean nextLineTeildatensatzNummerIsSet =
                    nextLineTeildatensatzNummerEnum != TeildatensatzNummer.NULL;
            boolean currentLineWagnisartIsSet = this.hasWagnisart() && this.getWagnisart().trim().length() > 0;
			boolean currentLineTeildatensatzNummerIsSet = this.getTeildatensatzNummer().trim()
			        .length() > 0;

			if (!currentLineWagnisartIsSet) {
				return true;
			}

			TeildatensatzNummer currentLineTeildatensatzEnum = TeildatensatzNummer.NULL;
			if (currentLineTeildatensatzNummerIsSet) {
				currentLineTeildatensatzEnum = TeildatensatzNummer.isIn(Integer.parseInt(this
				        .getTeildatensatzNummer()));
			}

            if ((nextLineWagnisartIsSet)
                    && (nextLineWagnisEnum == WagnisartLeben.isIn(Integer.parseInt(this.getWagnisart())))) {
                if (currentLineTeildatensatzNummerIsSet || nextLineTeildatensatzNummerIsSet) {
					return nextLineTeildatensatzNummerEnum == currentLineTeildatensatzEnum;
                } else {
                    // wagnisarten sind gleich und die
                    // Teildatensatznummer sind beide nicht gesetzt
                    return true;
                }
            }
		}
		return false;
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
		if (!tds.hasFeld(Feld1bis7.SPARTE)) {
			Feld spartenFeld = new NumFeld((SPARTE), 3, 11);
			tds.add(spartenFeld);
		}
		tds.getFeld(Feld1bis7.SPARTE).setInhalt(sparte);
	}

	/**
	 * Wandelt das uebergebene Array in eine Liste mit MetaFeldInfos. Seit 0.7.1
	 * duerfen Feld-Enums wie {@link gdv.xport.satz.feld.Feld100} auch
	 * FelderInfo-Annotationen enthalten, die wiederum auf einen Enum verweisen.
	 *
	 * @param felder the felder
	 * @return the meta feld infos
	 */
	public static List<MetaFeldInfo> getMetaFeldInfos(final Enum[] felder) {
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
	private static void add(final Enum feldX, final Teildatensatz tds) {
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

}
