/*
 * Copyright (c) 2011, 2012 by Oli B.
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
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.feld.Feld100;
import gdv.xport.satz.feld.FeldX;
import gdv.xport.satz.feld.MetaFeldInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Dies ist die gemeinsame Oberklasse aller Saetze in diesem Package, die
 * nach dem SOP-Muster aufgebaut sind.
 * Eventuell wird diese Klasse mit der Oberklasse vereinigt.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (09.03.2011)
 */
public class SatzX extends Datensatz {

    private static Log log = LogFactory.getLog(SatzX.class);

    /**
     * Instantiiert einen neuen Datensatz.
     *
     * @param satzart z.B. 100
     * @param felder mit allen Elementen des Datensatzes
     */
    public SatzX(final int satzart, final Enum<?>[] felder) {
        super(satzart, getTeildatensaetzeFor(satzart, felder));
    }

    /**
     * Instantiiert einen neuen Datensatz.
     *
     * @param satzart z.B. 100
     * @param enumClass Enumerationen-Klasse mit den Feldbeschreibungen
     */
    public SatzX(final int satzart, final Class<? extends Enum<?>> enumClass) {
        super(satzart, getTeildatensaetzeFor(satzart, enumClass));
    }

    /**
     * Instantiiert einen neuen Datensatz.
     *
     * @param satzart z.B. 100
     * @param sparte Sparte
     * @param felder mit allen Elementen des Datensatzes
     */
    public SatzX(final int satzart, final int sparte, final Enum<?>[] felder) {
        super(satzart, sparte, getTeildatensaetzeFor(satzart, felder));
    }

    /**
     * Instantiiert einen neuen Datensatz.
     *
     * @param satzart z.B. 100
     * @param sparte Sparte
     * @param enumClass Enumerationen-Klasse mit den Feldbeschreibungen
     */
    public SatzX(final int satzart, final int sparte, final Class<? extends Enum<?>> enumClass) {
        super(satzart, sparte, getTeildatensaetzeFor(satzart, enumClass));
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
     * Leitet aus dem uebergebenen Feldelement die Parameter ab, um daraus
     * ein Feld anzulegen und im jeweiligen Teildatensatz einzuhaengen.
     * Zusaetzlich wird das Feld "Satznummer" vorbelegt, falls es in den
     * uebergebenen Feldern vorhanden ist.
     *
     * @param feldX das Feld-Element
     * @param tds der entsprechende Teildatensatz
     */
    protected static void add(final Enum<?> feldX, final Teildatensatz tds) {
        FeldInfo info = MetaFeldInfo.getFeldInfo(feldX);
        Feld feld = Feld.createFeld(feldX, info);
        if (info.nr() < 8) {
            log.info("using default settings for " + feld);
        } else {
            tds.add(feld);
            if (isSatznummer(feldX)) {
                feld.setInhalt(info.teildatensatz());
            }
        }
    }

    private static boolean isSatznummer(final Enum<?> feldX) {
        return (feldX.name().length() <= 11) && feldX.name().startsWith("SATZNUMMER");
    }

    /**
     * Wandelt das uebergebene Array in eine Liste mit Felder. Seit 0.7.1
     * duerfen Feld-Enums wie {@link Feld100} auch FelderInfo-Annotationen
     * enthalten, die wiederum auf einen Enum verweisen.
     *
     * @param felder the felder
     * @return the feld info list
     */
    private static List<Enum<?>> getAsList(final Enum<?>[] felder) {
        ArrayList<Enum<?>> feldList = new ArrayList<Enum<?>>(felder.length);
        for (int i = 0; i < felder.length; i++) {
            String name = felder[i].name();
            try {
                Field field = felder[i].getClass().getField(name);
                FelderInfo info = field.getAnnotation(FelderInfo.class);
                if (info == null) {
                    feldList.add(felder[i]);
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
     * Wandelt das uebergebene Array in eine Liste mit MetaFeldInfos. Seit 0.7.1
     * duerfen Feld-Enums wie {@link Feld100} auch FelderInfo-Annotationen
     * enthalten, die wiederum auf einen Enum verweisen.
     *
     * @param felder the felder
     * @return the meta feld infos
     */
    protected static List<MetaFeldInfo> getMetaFeldInfos(final Enum<?>[] felder) {
        List<MetaFeldInfo> metaFeldInfos = new ArrayList<MetaFeldInfo>(felder.length);
        for (int i = 0; i < felder.length; i++) {
            String name = felder[i].name();
            try {
                Field field = felder[i].getClass().getField(name);
                FelderInfo info = field.getAnnotation(FelderInfo.class);
                if (info == null) {
                    metaFeldInfos.add(new MetaFeldInfo(felder[i]));
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
        Collection<? extends Enum<?>> enums = getAsList(info);
        List<MetaFeldInfo> metaFeldInfos = new ArrayList<MetaFeldInfo>(enums.size());
        for (Enum<?> enumX : enums) {
            metaFeldInfos.add(new MetaFeldInfo(enumX, info));
        }
        return metaFeldInfos;
    }

    private static Collection<? extends Enum<?>> getAsList(final FelderInfo info) {
        Class<? extends Enum<?>> enumClass = info.type();
        return getAsList(enumClass.getEnumConstants());
    }

    private static List<Teildatensatz> getTeildatensaetzeFor(final int satzart, final Enum<?>[] felder) {
        SortedMap<Integer, Teildatensatz> tdsMap = new TreeMap<Integer, Teildatensatz>();
        for (MetaFeldInfo metaFeldInfo : getMetaFeldInfos(felder)) {
            int n = metaFeldInfo.getTeildatensatzNr();
            Teildatensatz tds = tdsMap.get(n);
            if (tds == null) {
                tds = new Teildatensatz(satzart, n);
                tdsMap.put(n, tds);
            }
            add(metaFeldInfo.getFeldEnum(), tds);

        }
        return new ArrayList<Teildatensatz>(tdsMap.values());
    }

    private static List<Teildatensatz> getTeildatensaetzeFor(final int satzart,
            final Class<? extends Enum<?>> enumClass) {
        Enum<?>[] constants = enumClass.getEnumConstants();
        return getTeildatensaetzeFor(satzart, constants);
    }

    /**
     * Setzt die Teildatensaetze mit den angegebenen Feldern auf.
     *
     * @param felder Felder fuer die Teildatensaetze.
     */
    protected void setUpTeildatensaetze(final Enum<?>[] felder) {
        super.createTeildatensaetze(getTeildatensaetzeFor(this.getSatzart(), felder));
        super.completeTeildatensaetze();
    }

}

