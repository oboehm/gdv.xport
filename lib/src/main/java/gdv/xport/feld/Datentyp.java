/*
 * Copyright (c) 2014-2024 by Oli B.
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
import org.apache.commons.text.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    /** VU-Nummer. */
    VUNUMMER(VUNummer.class),

    /** Zeichen. */
    ZEICHEN(Zeichen.class),

    /** Alphanumerische Zeichen oder String. */
    ALPHANUMERISCH(AlphaNumFeld.class),

    /** Datum. */
    DATUM(Datum.class),

    /** Betrag. */
    BETRAG(Betrag.class),

    /** Gleitkomma-Zahlen. */
    FLIESSKOMMA(NumFeld.class),

    /** Zahlen. */
    NUMERISCH(NumFeld.class),

    /** Zahlen. */
    UHRZEIT(NumFeld.class),

    /** Unbekannter Typ. */
    UNBEKANNT(Feld.class);

    private static final Logger LOG = LogManager.getLogger();
    private final Class<? extends Feld> feldClass;

    Datentyp(final Class<? extends Feld> clazz) {
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
     * @deprecated bitte entsprechende Methode mit ByteAdresse verwenden
     *             (TODO: wird mit v9 entsorgt)
     */
    @Deprecated
    public Feld asFeld(final Bezeichner bezeichner, final int anzahlBytes, final int byteAddress) {
        Class<? extends Feld> clazz = this.asClass();
        try {
            Constructor<? extends Feld> ctor = getConstructor(clazz, int.class);
            return ctor.newInstance(bezeichner, anzahlBytes, byteAddress);
        } catch (SecurityException | InstantiationException ex) {
            throw new ShitHappenedException("cannot instantiate " + clazz, ex);
        } catch (IllegalAccessException ex) {
            throw new ShitHappenedException("cannot access constructor of " + clazz, ex);
        } catch (InvocationTargetException ex) {
            throw new ShitHappenedException("cannot invoke " + clazz, ex);
        }
    }

    /**
     * Liefert den Datentyp als Feld zurueck.
     *
     * @param bezeichner der Bezeichner
     * @param anzahlBytes die Anzahl an Bytes
     * @param byteAddress die Byte-Adresse
     * @return z.B. ein {@link NumFeld}-Objekt
     * @since 7.1 (08-Jan-2024)
     */
    public Feld asFeld(final Bezeichner bezeichner, final int anzahlBytes, final ByteAdresse byteAddress) {
        Class<? extends Feld> clazz = this.asClass();
        try {
            Constructor<? extends Feld> ctor = getConstructor(clazz, ByteAdresse.class);
            return ctor.newInstance(bezeichner, anzahlBytes, byteAddress);
        } catch (SecurityException | InstantiationException ex) {
            throw new ShitHappenedException("cannot instantiate " + clazz, ex);
        } catch (IllegalAccessException ex) {
            throw new ShitHappenedException("cannot access constructor of " + clazz, ex);
        } catch (InvocationTargetException ex) {
            throw new ShitHappenedException("cannot invoke " + clazz, ex);
        }
    }

    private Constructor<? extends Feld> getConstructor(Class<? extends Feld> clazz, Class<?> arg3) {
        try {
            return clazz.getConstructor(Bezeichner.class, int.class, arg3);
        } catch (NoSuchMethodException ex) {
            LOG.debug("{} hat keinen public Constructor ({}).", clazz, ex.getMessage());
            LOG.trace("Details:", ex);
            for (Constructor<?> ctor : clazz.getDeclaredConstructors()) {
                Class<?>[] types = ctor.getParameterTypes();
                if ((types.length == 3) && types[0].isAssignableFrom(Bezeichner.class) && types[1].isAssignableFrom(int.class) && types[2].isAssignableFrom(arg3)) {
                    ctor.setAccessible(true);
                    return (Constructor<? extends Feld>) ctor;
                }
            }
            throw new ShitHappenedException("cannot find needed constructor of " + clazz, ex);
        }
    }

    public String capitalize() {
        return WordUtils.capitalize(toString().toLowerCase());
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

    /**
     * Wandelt einen Datentyp wieder zurueck in den String aus der
     * GDV-XML-Beschreibung.
     *
     * @param feld z.B. ein numerischer Wert (ohne Nachkommastellen)
     * @return z.B. "Numerisch"
     * @since 5.0
     */
    public static String asString(Feld feld) {
        if ((feld instanceof NumFeld) && !(feld instanceof Betrag) && !(feld instanceof Datum)) {
            NumFeld n = (NumFeld) feld;
            if (n.getNachkommastellen() > 0) {
                return FLIESSKOMMA.capitalize();
            } else {
                return NUMERISCH.capitalize();
            }
        }
        return asString(feld.getClass());
    }

    /**
     * Wandelt einen Datentyp wieder zurueck in den String aus der
     * GDV-XML-Beschreibung.
     *
     * @param clazz z.B. NumFeld.class
     * @return z.B. "Numerisch"
     * @since 5.0
     */
    public static String asString(Class<? extends Feld> clazz) {
        for (Datentyp t : Datentyp.values()) {
            if (t.feldClass.isAssignableFrom(clazz)) {
                return WordUtils.capitalize(t.toString().toLowerCase());
            }
        }
        throw new IllegalArgumentException("no presentation available for " + clazz);
    }

}
