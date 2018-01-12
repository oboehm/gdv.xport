/*
 * Copyright (c) 2009 - 2012 by Oli B.
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
 * (c)reated 25.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import gdv.xport.annotation.FeldInfo;

/**
 * Ein Zeichen ist ein Feld der Laenge 1.
 *
 * @author oliver
 * @since 0.0.2
 */
public class Zeichen extends AlphaNumFeld {

    /**
     * Instantiates a new zeichen.
     *
     * @param start the start
     * @param c the c
     */
    public Zeichen(final int start, final char c) {
        super(1, start);
        super.setInhalt(c);
    }

    /**
     * Instanziiert ein neues Zeichen.
     *
     * @param bezeichner der Bezeichner
     * @param start die Byte-Adresse
     * @since 1.0
     */
    public Zeichen(final Bezeichner bezeichner, final int start) {
        super(bezeichner, 1, start);
    }

    /**
     * Legt ein neues Zeichen-Feld an. Die Informationen dazu werden
     * aus der uebergebenen Enum bezogen.
     *
     * @param feldX Enum mit den Feldinformationen
     * @since 0.9
     */
    public Zeichen(final Enum<?> feldX) {
        this(feldX, Feld.getFeldInfo(feldX));
    }

    /**
     * Instantiiert ein neues Zeichen.
     *
     * @param feldX Feld
     * @param info mit Angabe der Start-Adresse
     * @since 0.6
     */
    public Zeichen(final Enum<?> feldX, final FeldInfo info) {
        super(feldX, info);
        assert info.anzahlBytes() == 1 : "Zeichen kann nur 1 Byte lang sein";
    }

    /**
     * Instantiiert ein neues Zeichen.
     *
     * @param name Bezeichner
     * @param info mit Angabe der Start-Adresse
     * @since 0.6
     */
    public Zeichen(final String name, final FeldInfo info) {
        this(new Bezeichner(name), info.byteAdresse());
        assert info.anzahlBytes() == 1 : "Zeichen kann nur 1 Byte lang sein";
    }

    /**
     * Dies ist der Copy-Constructor, mit dem man ein bestehendes Zeichen
     * kopieren kann.
     *
     * @param other das originale Zeichen
     */
    public Zeichen(final Zeichen other) {
        super(other);
    }

    /**
     * Falls man keinen String will, sondern ein einzelnes Zeichen braucht.
     *
     * @return das einzige Zeichen
     */
    public char toChar() {
        return this.getInhalt().charAt(0);
    }

    /**
     * Liefert das Zeichen als Integer zurueck oder als -1, falls es keine
     * Zahl ist.
     *
     * @return 0 bis 9 oder -1, falls es keine Zahl ist
     */
    public int toInt() {
        return Character.getNumericValue(toChar());
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#clone()
     */
    @SuppressWarnings("squid:S2975")
    @Override
    public Object clone() {
        return new Zeichen(this);
    }

}

