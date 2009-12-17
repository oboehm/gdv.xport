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
 * Im Gegensatz zum Betrag hat diese Klasse ein Vorzeichen ('+' oder '-').
 *
 * @author oliver
 * @since 11.10.2009
 * @version $Revision$
 */
public class BetragMitVorzeichen extends Betrag {

    /**
     * @param length das Vorzeichen muss dabei mitgezaehlt werden
     * @param start
     */
    public BetragMitVorzeichen(String name, int length, int start) {
        super(name, length, start);
        this.setVorzeichen('+');
    }

    public void setVorzeichen(char c) {
        this.setInhalt(c, this.getAnzahlBytes() - 1);
    }

    public char getVorzeichen() {
        String s = this.getInhalt();
        return s.charAt(s.length() - 1);
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Betrag#setInhalt(double)
     */
    @Override
    public void setInhalt(double x) {
        if (x >= 0) {
            super.setInhalt(x * 10);
            this.setVorzeichen('+');
        } else {
            super.setInhalt(-x * 10);
            this.setVorzeichen('-');
        }
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Betrag#setInhalt(int)
     */
    @Override
    public void setInhalt(int n) {
        this.setInhalt((long) n);
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.NumFeld#setInhalt(long)
     */
    @Override
    public void setInhalt(long n) {
        if (n >= 0) {
            super.setInhalt(n * 10);
            this.setVorzeichen('+');
        } else {
            super.setInhalt(-n * 10);
            this.setVorzeichen('-');
        }
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Betrag#toDouble()
     */
    @Override
    public double toDouble() {
        String s = this.getInhalt().toString();
        double x = Integer.parseInt(s.substring(0, s.length() - 1)) / 100.0;
        return (this.getVorzeichen() == '-') ? -x : x;
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Betrag#toInt()
     */
    @Override
    public int toInt() {
        String s = this.getInhalt().toString();
        int x = Integer.parseInt(s.substring(0, s.length() - 1)) / 100;
        return (this.getVorzeichen() == '-') ? -x : x;
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#resetInhalt()
     */
    @Override
    public void resetInhalt() {
        super.resetInhalt();
        this.setVorzeichen('+');
    }



}

