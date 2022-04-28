/*
 * Copyright (c) 2021 by Oli B.
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
 * (c)reated 06.01.2021 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import de.jfachwert.Fachwert;
import de.jfachwert.SimpleValidator;
import de.jfachwert.pruefung.exception.InvalidValueException;
import org.apache.commons.lang3.Range;

/**
 * Mit der Klasse ByteAdresse wird ein Feld innherhalb eines Teildatensatzes
 * adressiert. Es geht von Adresse 1 bis 256.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 * @since 5.0 (06.01.21)
 */
public class ByteAdresse extends Number implements Fachwert {

    private static final Validator VALIDATOR = new Validator();
    final byte adresse;

    /** Default-Adresse fuer Nummer des Versischerungsunternehmen. */
    public static final ByteAdresse VU_NUMMER = ByteAdresse.of(5);
    /** Default-Adresse fuer Versichungsschein- und Vertragsnummer. */
    public static final ByteAdresse VERSICHERUNGSSCHEINNUMMER = ByteAdresse.of(14);

    private ByteAdresse(int adresse) {
        this.adresse = (byte) (VALIDATOR.verify(adresse) - 129);
    }

    public static ByteAdresse of(int n) {
        return new ByteAdresse(n);
    }

    public static ByteAdresse of(byte n) {
        return new ByteAdresse(129+n);
    }

    public byte byteValue() {
        return adresse;
    }

    @Override
    public int intValue() {
        return 129 + ((int) adresse);
    }

    @Override
    public long longValue() {
        return intValue();
    }

    @Override
    public float floatValue() {
        return intValue();
    }

    @Override
    public double doubleValue() {
        return intValue();
    }

    @Override
    public String toString() {
        return Integer.toString(intValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteAdresse that = (ByteAdresse) o;
        return adresse == that.adresse;
    }

    @Override
    public int hashCode() {
        return adresse;
    }



    public static class Validator implements SimpleValidator<Integer> {

        /**
         * Eine gueltige Byte-Adresse liegt zwischen 1 und 256.
         *
         * @param n Adresse, die validiert wird
         * @return Zahl selber, wenn sie gueltig ist
         */
        @Override
        public Integer validate(Integer n) {
            if ((n < 1) || (n > 256)) {
                throw new InvalidValueException(n, "Adresse", Range.between(1, 256));
            }
            return n;
        }

    }

}
