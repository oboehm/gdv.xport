/*
 * Copyright (c) 2014 by Oli B.
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
 * (c)reated 03.02.2016 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

/**
 * Diese Klasse wurde inzwischen in {@link SatzTyp} umbenannt und ist nur
 * noch aus Kompatiblitaetsgrunden vorhanden.
 * <p>
 * TODO: Diese Klasse wird mit 1.2 wieder entsorgt.
 * </p>
 *
 * @author oliver
 * @deprecated: bitte {@link SatzTyp} verwenden
 */
@Deprecated
public final class SatzNummer extends SatzTyp {

    /**
     * Instantiates a new satz nummer.
     *
     * @param satzart the satzart
     */
    public SatzNummer(int satzart) {
        super(satzart);
    }

    /**
     * Instantiates a new satz nummer.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     */
    public SatzNummer(int satzart, int sparte) {
        super(satzart, sparte);
    }

    /**
     * Instantiates a new satz nummer.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @param wagnisart the wagnisart
     */
    public SatzNummer(int satzart, int sparte, int wagnisart) {
        super(satzart, sparte, wagnisart);
    }

    /**
     * Instantiates a new satz nummer.
     *
     * @param satzart the satzart
     * @param sparte the sparte
     * @param wagnisart the wagnisart
     * @param lfdNummer the lfd nummer
     */
    public SatzNummer(int satzart, int sparte, int wagnisart, int lfdNummer) {
        super(satzart, sparte, wagnisart, lfdNummer);
    }

}
