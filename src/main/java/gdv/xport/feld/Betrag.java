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
 * (c)reated 11.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.feld;

/**
 * Standardmaessig hat das Beitrags-Feld 12,2 Stellen (12 Vorkommastellen +
 * 2 Nachkommastellen.
 *
 * @author oliver
 * @since 11.10.2009
 * @version $Revision$
 */
public class Betrag extends NumFeld {

    public Betrag(String name) {
        super(name, "00000000000000");
    }

    public Betrag(String name, int length, int start) {
        super(name, length, start);
    }

    public Betrag(int length, int start) {
        super(length, start);
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.NumFeld#setInhalt(int)
     */
    @Override
    public void setInhalt(int n) {
        assert n >= 0 : "Betrag can't store negative number (" + n + ")";
        super.setInhalt(n * 100);
    }

    public void setInhalt(double x) {
        assert x >= 0 : "Betrag can't store negative number (" + x + ")";
        long n = Math.round(x * 100);
        super.setInhalt(n);
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.NumFeld#toInt()
     */
    @Override
    public int toInt() {
        return super.toInt() / 100;
    }

    public double toDouble() {
        return super.toInt() / 100.0;
    }

}

