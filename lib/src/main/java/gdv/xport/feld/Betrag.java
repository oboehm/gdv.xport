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
 * (c)reated 11.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import gdv.xport.config.Config;

/**
 * Standardmaessig hat das Beitrags-Feld 12,2 Stellen (12 Vorkommastellen +
 * 2 Nachkommastellen.
 *
 * @author oliver
 * @since 11.10.2009
 * @version $Revision$
 */
public class Betrag extends NumFeld {

    private static final Feld.Validator DEFAULT_VALIDATOR =new NumFeld.Validator(Config.getInstance());

    /**
     * Instantiiert einen neuen Betrag.
     *
     * @param name Name des Feldes
     * @param length Laenge
     * @param start Start-Byte (beginnend bei 1)
     * @since 1.0
     * @deprecated durch entsprechenden Constructor mit ByteAdresse ersetzt
     *             (TODO: wird mit v8 entsorgt)
     */
    @Deprecated
    public Betrag(final Bezeichner name, final int length, final int start) {
        this(name, length, ByteAdresse.of(start), Config.getInstance());
    }

    /**
     * Instantiiert einen neuen Betrag.
     *
     * @param name Name des Feldes
     * @param length Laenge
     * @param start Start-Byte (beginnend bei 1)
     * @since 7.0 (07-Jan-2024)
     */
    public Betrag(final Bezeichner name, final int length, final ByteAdresse start) {
        this(name, length, start, Config.getInstance());
    }

    private Betrag(final Betrag other, final Config config) {
        this(other.getBezeichner(), other.getByteAdresse(), other.getInhalt(), config);
    }

    private Betrag(final Bezeichner name, final int length, final ByteAdresse start, Config config) {
        super(name, length, start, 2, config);
    }

    /**
     * Legt einen neuen Betrag an.
     *
     * @param name   Name
     * @param start  Start-Adresse
     * @param value  Wert
     * @param config Konfiguration
     * @deprecated wird mit v8 entsorgt (TODO)
     */
    @Deprecated
    protected Betrag(final Bezeichner name, final int start, final String value, Config config) {
        this(name, value.length(), ByteAdresse.of(start), config);
        this.setInhalt(value);
    }

    /**
     * Legt einen neuen Betrag an.
     *
     * @param name   Name
     * @param start  Start-Adresse
     * @param value  Wert
     * @param config Konfiguration
     */
    protected Betrag(final Bezeichner name, final ByteAdresse start, final String value, Config config) {
        this(name, value.length(), start, config);
        this.setInhalt(value);
    }

    /**
     * Dies ist der Copy-Constructor, mit dem man ein bestehendes Feld
     * kopieren kann.
     *
     * @param other das originale Feld
     */
    public Betrag(final Feld other) {
        super(other);
    }

    /**
     * Liefert einen neuen Betrag mit neuer Konfiguration
     *
     * @param c neue Konfiguration
     * @return neuer Betrag
     * @since 5.3
     */
    @Override
    public Betrag mitConfig(Config c) {
        return new Betrag(this, c);
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#clone()
     */
    @SuppressWarnings("squid:S2975")
    @Override
    public Object clone() {
        return new Betrag(this);
    }

    /**
     * Erzeugt einen Betrag mit den Daten des uebergebenen Feldes.
     *
     * @param feld Feld
     * @return Betrag
     */
    public static Betrag of(Feld feld) {
        Betrag betrag = new Betrag(feld.getBezeichner(), feld.getAnzahlBytes(), feld.getByteAdresse());
        betrag.setInhalt(feld.getInhalt());
        return betrag;
    }

}

