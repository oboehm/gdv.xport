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
 * (c)reated 29.08.2012 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.feld;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;

/**
 * In dieser Klasse sind einige Meta-Informationen zu Feldern wie die
 * {@link FeldInfo}- oder {@link FelderInfo}-Annotation zusammengefasst.
 *
 * @author oliver
 * @since 0.7.1 (29.08.2012)
 */
public final class MetaFeldInfo {

    private final Enum<?> feldEnum;
    private final FelderInfo felderInfo;

    /**
     * Erzeugt ein neues MetaFeldInfo-Objekt.
     *
     * @param enumX das Aufzaehlungs-Feld
     */
    public MetaFeldInfo(final Enum<?> enumX) {
        this(enumX, null);
    }

    /**
     * Erzeugt ein neues MetaFeldInfo-Objekt.
     *
     * @param enumX das Aufzaehlungs-Feld
     * @param info weiere Infos zum enumX
     */
    public MetaFeldInfo(final Enum<?> enumX, final FelderInfo info) {
        this.feldEnum = enumX;
        this.felderInfo = info;
    }

    /**
     * Liefert das Aufzaehlungs-Feld.
     *
     * @return das Aufzaehlungs-Feld
     */
    public Enum<?> getFeldEnum() {
        return this.feldEnum;
    }

   /**
     * Liefert den Feld-Namen.
     *
     * @return z.B. "SATZART"
     */
    public String getName() {
        return feldEnum.name();
    }

    /**
     * Liefert das angehaengte FeldInfo zu einem Feld zurueck.
     *
     * @param feldX the feld x
     * @return the feld info
     */
    public static FeldInfo getFeldInfo(final Enum<?> feldX) {
        String name = feldX.name();
        try {
            FeldInfo info = feldX.getClass().getField(name).getAnnotation(FeldInfo.class);
            if (info == null) {
                throw new IllegalArgumentException("@FeldInfo missing for " + name);
            }
            return info;
        } catch (NoSuchFieldException nsfe) {
            throw new InternalError("no field " + name + " (" + nsfe + ")");
        }
    }

    /**
     * Liefert das angehaengte FeldInfo zu einem Feld zurueck.
     *
     * @return the feld info
     */
    public FeldInfo getFeldInfo() {
        return getFeldInfo(this.feldEnum);
    }

    /**
     * Liefert die Nummer im Teildatensatz zurueck.
     *
     * @return eine Nummer &gt;= 1
     */
    public int getNr() {
        return this.getFeldInfo().nr();
    }

    /**
     * Liefert die Nummer des Teildatensatzes zurueck. Diese ermittelt sich
     * aus dem enthaltenen {@link FelderInfo}, falls es dort gesetzt ist.
     *
     * @return the teildatensatz nr
     */
    public int getTeildatensatzNr() {
        if ((felderInfo != null) && (felderInfo.teildatensatz() > 0)) {
            return felderInfo.teildatensatz();
        }
        return this.getFeldInfo().teildatensatz();
    }

    /**
     * Ermittelt, ob die Sparte vorhanden ist.
     *
     * @since 0.9
     * @return true, falls Sparte vorhanden ist
     */
    public boolean hasSparte() {
        return (this.felderInfo != null) && (this.felderInfo.sparte() > 0);
    }

    /**
     * Liefert den Wert der Sparten-Annotation zurueck. Vorher sollte man
     * aber mit {@link #hasSparte()} abfragen, ob der Wert ueberhaupt bekannt
     * ist.
     *
     * @since 0.9
     * @return den Wert der Sparte
     */
    public int getSparte() {
        return this.felderInfo.sparte();
    }

    /**
     * Fuer Logging und Debugging wurde die Default-Implementierung
     * ueberschrieben.
     *
     * @return the string
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getTeildatensatzNr() + "." + this.getNr() + " " + this.getName();
    }

}
