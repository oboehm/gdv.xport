/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 25.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.feld;

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
     * Instantiates a new zeichen.
     *
     * @param name the name
     * @param start the start
     */
    public Zeichen(final String name, final int start) {
        super(name, 1, start);
    }

    /**
     * Instantiates a new zeichen.
     *
     * @param name the name
     * @param start the start
     * @param c the c
     */
    public Zeichen(final String name, final int start, final char c) {
        super(name, 1, start, c);
    }

    /**
     * Falls man keinen String will, sondern ein einzelnes Zeichen braucht.
     *
     * @return das einzige Zeichen
     */
    public char toChar() {
        return this.getInhalt().charAt(0);
    }

}

