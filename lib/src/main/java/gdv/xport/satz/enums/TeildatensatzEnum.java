/*
 * Copyright (c) 2020 by Oliver Boehm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 25.11.2020 by oboehm
 */

package gdv.xport.satz.enums;

import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.util.SatzTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static gdv.xport.feld.Bezeichner.SATZNUMMER;

/**
 * In die Klasse TeildatensatzEnum wurde die Teile aus {@link Teildatensatz}
 * herausgezogen, die Enum-spezifisch sind und bei der Umstellung auf die
 * XML-Variante des Teildatensatzes eher hinderlich sind.
 *
 * @author oboehm
 * @since 4.3 (25.11.2020)
 */
public final class TeildatensatzEnum extends Teildatensatz {

    private static final Logger LOG = LogManager.getLogger();
    private static final Map<SatzTyp, Integer[]> ABWEICHENDE_SATZNUMMERN = new HashMap<>();

    static {
        addAbweichendeSatznummer(SatzTyp.of("0220.570"), 43, 43);
        // TODO ab hier muessen die Satznummer der Teildatensaetze noch geprueft werden
        addAbweichendeSatznummer(SatzTyp.of("0210.580"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0220.580.01"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0220.580.2"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0210.080"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0211.080"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0220.080"), 49);
        addAbweichendeSatznummer(SatzTyp.of("0221.080"), 49);
        addAbweichendeSatznummer(SatzTyp.of("0220.040"), 51);
        addAbweichendeSatznummer(SatzTyp.of("0221.040"), 51);
        addAbweichendeSatznummer(SatzTyp.of("0210.550"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0220.550"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0270.550"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0280.550"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0291.550"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0292.550"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0293.550"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0294.550"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0295.550"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0220.070"), 53);
        addAbweichendeSatznummer(SatzTyp.of("0221.070"), 53);
        addAbweichendeSatznummer(SatzTyp.of("0210.170"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0211.170"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0220.170"), 50);
        addAbweichendeSatznummer(SatzTyp.of("0221.170"), 50);
        addAbweichendeSatznummer(SatzTyp.of("0210.190"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0211.190"), 43);
        addAbweichendeSatznummer(SatzTyp.of("0220.190"), 49);
        addAbweichendeSatznummer(SatzTyp.of("0221.190"), 49);
        addAbweichendeSatznummer(SatzTyp.of("0250.190"), 51);
        addAbweichendeSatznummer(SatzTyp.of("0251.190"), 51);
        addAbweichendeSatznummer(SatzTyp.of("0220.030"), 49);
        addAbweichendeSatznummer(SatzTyp.of("0221.030"), 49);
        addAbweichendeSatznummer(SatzTyp.of("0220.140"), 51);
        addAbweichendeSatznummer(SatzTyp.of("0210.130"), 251);
        addAbweichendeSatznummer(SatzTyp.of("0450"), 51);
        addAbweichendeSatznummer(SatzTyp.of("0500"), 66, 256);
        addAbweichendeSatznummer(SatzTyp.of("0550"), 66);
    }

    private static void addAbweichendeSatznummer(SatzTyp satzTyp, Integer... startAdresse) {
        ABWEICHENDE_SATZNUMMERN.put(satzTyp, startAdresse);
    }

    public TeildatensatzEnum(NumFeld satzart) {
        super(satzart);
    }

    public TeildatensatzEnum(NumFeld satzart, int nr) {
        super(satzart, nr);
        initSatznummer(nr);
    }

    public TeildatensatzEnum(int satzart, int nr) {
        super(satzart, nr);
        initSatznummer(nr);
    }

    public TeildatensatzEnum(Teildatensatz other) {
        super(other);
    }

    private void initSatznummer(final int nr) {
        if ((nr < 1) || (nr > 9)) {
            throw new IllegalArgumentException("Satznummer (" + nr
                    + ") muss zwischen 1 und 9 liegen");
        }
        this.satznummer.setInhalt(Character.forDigit(nr, 10));
        this.initDatenfelder();
    }

    private void initDatenfelder() {
        this.add(this.getSatzartFeld());
        if (this.hasSatznummer()) {
            initSatznummer();
        }
    }

    private void initSatznummer() {
        SatzTyp satzTyp = getSatzTyp();
        Integer[] startAdressen = ABWEICHENDE_SATZNUMMERN.get(satzTyp);
        int nr = Integer.parseInt(this.satznummer.getInhalt());
        if ((startAdressen != null) && (nr <= startAdressen.length)) {
            this.satznummer = new Zeichen(SATZNUMMER, startAdressen[nr-1]).withInhalt(this.satznummer.getInhalt());
            LOG.debug("{}. Satznummer is moved to {}.", nr, this.satznummer);
        }
        this.add(this.satznummer);
    }

}
