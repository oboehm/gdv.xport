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

    private static final Map<String, SatzTyp> MAPPING = new HashMap<>();

    static {
        MAPPING.put("Satzart02102", SatzTyp.of("0210.170"));
        MAPPING.put("Satzart02202", SatzTyp.of("0220.170"));
        MAPPING.put("Satzart02103", SatzTyp.of("0210.190"));
        MAPPING.put("Satzart02203", SatzTyp.of("0220.190"));
        MAPPING.put("Satzart0250Einzelanmeldung", SatzTyp.of("0250.190"));
        MAPPING.put("Satzart0260Umsatzanmeldung", SatzTyp.of("0260.190"));
        MAPPING.put("Satzart02104", SatzTyp.of("0210.000"));
        MAPPING.put("Satzart02204", SatzTyp.of("0220.000"));
        MAPPING.put("KfzBaustein", SatzTyp.of("0220.055"));
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

    /**
     * Leitet den SatzTyp aus dem Bezeichner ab.
     *
     * @return z.B. SatzTyp.of("0100") für "Satzart 0001"
     * @since 2.5
     */
    @JsonIgnore
    public SatzTyp getSatzTyp() {
        String technischerName = getBezeichner().getTechnischerName();
        SatzTyp satzTyp = MAPPING.get(technischerName);
        if (satzTyp == null) {
            satzTyp = getSatzTypFrom(technischerName);
        }
        return satzTyp;
    }

    private static SatzTyp getSatzTypFrom(String technischerName) {
        StringBuilder bufSatzTyp = new StringBuilder();
        String typ = StringUtils.substringAfter(technischerName.toLowerCase(), "satzart").trim();
        bufSatzTyp.append(typ, 0, 4);
        if (typ.length() > 4) {
            bufSatzTyp.append('.');
            bufSatzTyp.append(typ, 4, 7);
        }
        return SatzTyp.of(bufSatzTyp.toString());
    }

}
