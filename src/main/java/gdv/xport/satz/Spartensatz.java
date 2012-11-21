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
 * (c)reated 22.11.2010 by Oli B. (ob@aosd.de)
 */
package gdv.xport.satz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Dies ist eine gemeinsame Oberklasse aller Datensaetze, die mehrere Sparten aufnehmen koennen.
 * 
 * @author oliver (ob@aosd.de)
 * @since 0.5.0 (22.11.2010)
 */
@Deprecated
public abstract class Spartensatz extends Datensatz {

    /** The Constant log. */
    private static final Log log = LogFactory.getLog(Spartensatz.class);

    /**
     * Instantiates a new spartensatz.
     * 
     * @param satzart
     *            the satzart
     */
    public Spartensatz(final String satzart) {
        super(satzart);
    }

    /**
     * Instantiates a new spartensatz.
     * 
     * @param satzart
     *            the satzart
     */
    public Spartensatz(final int satzart) {
        super(satzart);
    }

    /**
     * Instantiates a new spartensatz.
     * 
     * @param satzart
     *            the satzart
     * @param n
     *            the n
     */
    public Spartensatz(final String satzart, final int n) {
        super(satzart, n);
    }

    /**
     * Instantiates a new spartensatz.
     * 
     * @param satzart
     *            the satzart
     * @param sparte
     *            the sparte
     */
    public Spartensatz(final int satzart, final int sparte) {
        super(satzart, sparte);
    }

    /**
     * Instantiates a new spartensatz.
     * 
     * @param satzart
     *            the satzart
     * @param sparte
     *            the sparte
     * @param n
     *            the n
     */
    public Spartensatz(final int satzart, final int sparte, final int n) {
        super(satzart, sparte, n);
    }

    /**
     * Abhaengig von der Sparte muessen wir hier noch die verschiedenen Teildatensaetze aufsetzen.
     * 
     * @param x
     *            Sparte (z.B. 30)
     * @see gdv.xport.satz.Datensatz#setSparte(int)
     */
    @Override
    public void setSparte(final int x) {
        if (this.getSparte() == x) {
            log.debug("nothing to do here - old Sparte = new Sparte (" + x + ")");
            return;
        }
        super.setSparte(x);
        this.createTeildatensaetzeFor(x);
        this.setUpTeildatensaetze();
        this.setUpDatenfelder(x);
    }

    /**
     * Legt die entsprechende Anzahl von Teildatensaetze fuer die angegebene Sparte an.
     * 
     * @param x
     *            Sparte (z.B. 30)
     */
    protected abstract void createTeildatensaetzeFor(final int x);

    /**
     * Initialisiert die Teildatensaetze fuer die angegebene Sparte.
     * 
     * @param x
     *            Sparte (z.B. 30)
     */
    protected abstract void setUpDatenfelder(final int x);

}
