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
 * (c)reated 10.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gdv.xport.util.SatzTyp;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Versions-Feld.
 *
 * @author oliver
 * @since 10.10.2009
 * @version $Revision$
 */
public class Version extends Feld {

    private static final Map<Bezeichner, SatzTyp> MAPPING = new HashMap<>();

    static {
        MAPPING.put(Bezeichner.SATZART_0202_ALLG_ANTRAGSDATEN, SatzTyp.of("0202"));
        MAPPING.put(Bezeichner.SATZART_0210_NICHT_DEF_SPARTEN, SatzTyp.of("0210.000"));
        MAPPING.put(Bezeichner.SATZART_0210_FEUER, SatzTyp.of("0210.080"));
        MAPPING.put(Bezeichner.SATZART_0210_TECH_VERS, SatzTyp.of("0210.170"));
        MAPPING.put(Bezeichner.SATZART_0210_TRANSPORT, SatzTyp.of("0210.190"));
        MAPPING.put(Bezeichner.SATZART_0211_NICHT_DEF_SPARTEN, SatzTyp.of("0211.000"));
        MAPPING.put(Bezeichner.SATZART_0211_FEUER, SatzTyp.of("0211.080"));
        MAPPING.put(Bezeichner.SATZART_0211_TECH_VERS, SatzTyp.of("0211.170"));
        MAPPING.put(Bezeichner.SATZART_0211_TRANSPORT, SatzTyp.of("0211.190"));
        MAPPING.put(Bezeichner.SATZART_0220_NICHT_DEF_SPARTEN, SatzTyp.of("0220.000"));
        MAPPING.put(Bezeichner.SATZART_0220_010, SatzTyp.of("0220.010.0"));
        MAPPING.put(Bezeichner.SATZART_0220_FEUER, SatzTyp.of("0220.080"));
        MAPPING.put(Bezeichner.SATZART_0220_TECH_VERS, SatzTyp.of("0220.170"));
        MAPPING.put(Bezeichner.SATZART_0220_TRANSPORT, SatzTyp.of("0220.190"));
        MAPPING.put(Bezeichner.SATZART_0221_FEUER, SatzTyp.of("0221.080"));
        MAPPING.put(Bezeichner.SATZART_0221_TECH_VERS, SatzTyp.of("0221.170"));
        MAPPING.put(Bezeichner.SATZART_0221_TRANSPORT, SatzTyp.of("0221.190"));
        MAPPING.put(Bezeichner.SATZART_0221_NICHT_DEF_SPARTEN, SatzTyp.of("0221.000"));
        MAPPING.put(Bezeichner.SATZART_0220_055, SatzTyp.of("0220.055"));
        MAPPING.put(Bezeichner.SATZART_0212, SatzTyp.of("0212.050"));
        MAPPING.put(Bezeichner.SATZART_0225_LEBEN, SatzTyp.of("0225.010"));
        MAPPING.put(Bezeichner.SATZART_0230_LEBEN, SatzTyp.of("0230.010"));
        MAPPING.put(Bezeichner.SATZART_0222, SatzTyp.of("0222.030"));
        MAPPING.put(Bezeichner.SATZART_0230_UNFALL, SatzTyp.of("0230.030"));
        MAPPING.put(Bezeichner.SATZART_0230_KFZ_WECHSEL_AKZ, SatzTyp.of("0230.050"));
        MAPPING.put(Bezeichner.SATZART_0230_GEBAEUDE, SatzTyp.of("0230.140"));
        MAPPING.put(Bezeichner.SATZART_0250_TRANSPORT, SatzTyp.of("0250.190"));
        MAPPING.put(Bezeichner.SATZART_0251_TRANSPORT, SatzTyp.of("0251.190"));
        MAPPING.put(Bezeichner.SATZART_0260_TRANSPORT, SatzTyp.of("0260.190"));
        MAPPING.put(Bezeichner.SATZART_0300_BETEILIGUNGSINFORMATION, SatzTyp.of("0300"));
        MAPPING.put(Bezeichner.SATZART_0342_BEGLEITDOK, SatzTyp.of("0342"));
        MAPPING.put(Bezeichner.SATZART_0350_KLAUSELN, SatzTyp.of("0350"));
        MAPPING.put(Bezeichner.SATZART_0372, SatzTyp.of("0372"));
        MAPPING.put(Bezeichner.SATZART_0390_RABATTE, SatzTyp.of("0390"));
        MAPPING.put(Bezeichner.SATZART_0392_EVB, SatzTyp.of("0392"));
        MAPPING.put(Bezeichner.SATZART_0400_INKASSO, SatzTyp.of("0400"));
        MAPPING.put(Bezeichner.SATZART_0410_INKASSO, SatzTyp.of("0410"));
        MAPPING.put(Bezeichner.SATZART_0420_VERSICHERUNGSTEUERABRECHNUNG, SatzTyp.of("0420"));
        MAPPING.put(Bezeichner.SATZART_0430_INKASSO, SatzTyp.of("0430"));
        MAPPING.put(Bezeichner.SATZART_0450_INKASSOABRECHNUNG, SatzTyp.of("0450"));
        MAPPING.put(Bezeichner.SATZART_0500_SCHADENINFORMATION, SatzTyp.of("0500"));
        MAPPING.put(Bezeichner.SATZART_0550_SCHADENABRECHNUNG, SatzTyp.of("0550"));
        MAPPING.put(Bezeichner.SATZART_0600, SatzTyp.of("0600"));
        MAPPING.put(Bezeichner.SATZART_9951_MIME, SatzTyp.of("9951"));
        MAPPING.put(Bezeichner.VERSION_SATZART_9999, SatzTyp.of("9999"));
    }

    /**
     * Legt ein neues Versions-Feld an.
     *
     * @param name Name des Feldes
     * @param start Start-Byte (beginnend bei 1)
     * @since 1.0
     */
    public Version(final Bezeichner name, final int start) {
        super(name, 3, start, Align.LEFT);
    }

    /**
     * Legt ein neues Versions-Feld an (Copy-Constructor).
     *
     * @param feld andere Feld, aus dem kopiert wird.
     * @since 5.2
     */
    public Version(final Feld feld) {
        super(feld);
    }

    /**
     * Instantiiert ein neues Versions-Objekt.
     *
     * @param bezeichner Name des Feldes
     * @param start Start-Byte (beginnend bei 1)
     * @param v Versions-String (z.B. "1.1")
     */
    public Version(final Bezeichner bezeichner, final int start, final String v) {
        this(bezeichner.getName(), start, v);
    }

    /**
     * Instantiiert ein neues Versions-Objekt.
     *
     * @param name Name des Feldes
     * @param start Start-Byte (beginnend bei 1)
     * @param v Versions-String (z.B. "1.1")
     */
    public Version(final String name, final int start, final String v) {
        super(name, 3, start, v, Align.LEFT);
        assert v.length() == 3 : "Version hat nicht das Format x.x";
    }

    public static Version of(SatzTyp satzTyp) {
        String s = satzTyp.toString();
        if ((s.length() > 8) && !s.endsWith(".0")) {
            return of(SatzTyp.of(s.substring(0, 8)));
        }
        for (Map.Entry<Bezeichner, SatzTyp> entry : MAPPING.entrySet()) {
            if (satzTyp.equals(entry.getValue())) {
                return new Version(entry.getKey(), 1);
            }
        }
        String name = "Satzart " + satzTyp;
        return new Version(Bezeichner.of(name), 1);
    }

    /**
     * Leitet den SatzTyp aus dem Bezeichner ab.
     *
     * @return z.B. SatzTyp.of("0100") für "Satzart 0001"
     * @since 2.5
     */
    @JsonIgnore
    public SatzTyp getSatzTyp() {
        SatzTyp satzTyp = MAPPING.get(getBezeichner());
        if (satzTyp == null) {
            satzTyp = getSatzTypFrom(getBezeichner());
        }
        return satzTyp;
    }

    private static SatzTyp getSatzTypFrom(Bezeichner b) {
        StringBuilder bufSatzTyp = new StringBuilder();
        String typ = b.getTechnischerName().replaceAll("[a-zA-Z]", "");
        bufSatzTyp.append(typ, 0, 4);
        if (typ.length() > 4) {
            String subTyp = typ.substring(4, 7);
            if (StringUtils.isNumeric(subTyp)) {
                bufSatzTyp.append('.').append(subTyp);
            }
        }
        return SatzTyp.of(bufSatzTyp.toString());
    }

}
