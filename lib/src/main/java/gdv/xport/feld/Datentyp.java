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
 * (c)reated 29.10.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import gdv.xport.util.ShitHappenedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Dieser Aufzaehlungstyp repraesentiert die verschiedenen Datentypen, die
 * im XML-Handbuch definiert sind. Er dient auch zur Abbildung dieser
 * Datentypen auf die tatsaechliche Implementierung.
 *
 * @author oliver
 * @since 1.0 (29.10.2014)
 */
public enum Datentyp {

    /** Alphanumerische Zeichen oder String. */
    ALPHANUMERISCH(AlphaNumFeld.class),

    /** Datum. */
    DATUM(Datum.class),

    /** Gleitkomma-Zahlen. */
    FLIESSKOMMA(NumFeld.class),

    /** Zahlen. */
    NUMERISCH(NumFeld.class),

    /** Zahlen. */
    UHRZEIT(NumFeld.class),

    /** Unbekannter Typ. */
    UNBEKANNT(Feld.class);

    private final Class<? extends Feld> feldClass;

    private Datentyp(final Class<? extends Feld> clazz) {
        this.feldClass = clazz;
    }

    /**
     * Liefert den entsprechenden Datentyp als Klasse zurueck.
     *
     * @return z.B. NumFeld.class
     */
    public Class<? extends Feld> asClass() {
        return this.feldClass;
    }

    /**
     * Liefert den Datentyp als Feld zurueck.
     *
     * @param bezeichner der Bezeichner
     * @param anzahlBytes die Anzahl an Bytes
     * @param byteAddress die Byte-Adresse
     * @return z.B. ein {@link NumFeld}-Objekt
     */
    public Feld asFeld(final Bezeichner bezeichner, final int anzahlBytes, final int byteAddress) {
        Class<? extends Feld> clazz = this.asClass();
        try {
            Constructor<? extends Feld> ctor = clazz.getConstructor(Bezeichner.class, int.class, int.class);
            return ctor.newInstance(bezeichner, anzahlBytes, byteAddress);
        } catch (SecurityException ex) {
            throw new ShitHappenedException("cannot instantiate " + clazz, ex);
        } catch (NoSuchMethodException ex) {
            throw new ShitHappenedException("cannot find needed constructor of " + clazz, ex);
        } catch (InstantiationException ex) {
            throw new ShitHappenedException("cannot instantiate " + clazz, ex);
        } catch (IllegalAccessException ex) {
            throw new ShitHappenedException("cannot access constructor of " + clazz, ex);
        } catch (InvocationTargetException ex) {
            throw new ShitHappenedException("cannot invoke " + clazz, ex);
        }
    }

    /**
     * Liefert den gewuenschten Datentyp zurueck.
     *
     * @param name Datentyp als String, z.B. "Numerisch"
     * @return the datentyp
     */
    public static Datentyp asValue(final String name) {
        return Datentyp.valueOf(name.toUpperCase());
    }

}
