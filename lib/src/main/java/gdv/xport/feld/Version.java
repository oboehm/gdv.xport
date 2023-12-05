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

import java.util.Collection;
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
        MAPPING.put("AllgemeineAntragsdaten0202", SatzTyp.of("0202"));
        MAPPING.put("Satzart0210", SatzTyp.of("0210.080"));
        MAPPING.put("Satzart0220", SatzTyp.of("0220.080"));
        MAPPING.put("Satzart0211", SatzTyp.of("0211.080"));
        MAPPING.put("Satzart0221", SatzTyp.of("0221.080"));
        MAPPING.put("Satzart02102", SatzTyp.of("0210.170"));
        MAPPING.put("Satzart02202", SatzTyp.of("0220.170"));
        MAPPING.put("Satzart02112", SatzTyp.of("0211.170"));
        MAPPING.put("Satzart02212", SatzTyp.of("0221.170"));
        MAPPING.put("Satzart02103", SatzTyp.of("0210.190"));
        MAPPING.put("Satzart02203", SatzTyp.of("0220.190"));
        MAPPING.put("Satzart02113", SatzTyp.of("0211.190"));
        MAPPING.put("Satzart02213", SatzTyp.of("0221.190"));
        MAPPING.put("Satzart02104", SatzTyp.of("0210.000"));
        MAPPING.put("Satzart02204", SatzTyp.of("0220.000"));
        MAPPING.put("Satzart02214", SatzTyp.of("0221.000"));
        MAPPING.put("Satzart02114", SatzTyp.of("0211.000"));
        MAPPING.put("KfzBaustein", SatzTyp.of("0220.055"));
        MAPPING.put("Satzart0212", SatzTyp.of("0212.050"));
        MAPPING.put("LebenRenteLeistungsarten", SatzTyp.of("0225.010"));
        MAPPING.put("FondsdatensatzLeben0230", SatzTyp.of("0230.010"));
        MAPPING.put("UnfallspezifischeAntragsdaten0222", SatzTyp.of("0222.030"));
        MAPPING.put("UnfallLeistungsarten0230", SatzTyp.of("0230.030"));
        MAPPING.put("KFZWechselkennzeichenWAKZ", SatzTyp.of("0230.050"));
        MAPPING.put("VerbundeneGebaeudeVersicherteSachenUndKosten", SatzTyp.of("230.140"));
        MAPPING.put("Satzart0250Einzelanmeldung", SatzTyp.of("0250.190"));
        MAPPING.put("Satzart0251Einzelanmeldung", SatzTyp.of("0251.190"));
        MAPPING.put("Satzart0260Umsatzanmeldung", SatzTyp.of("0260.190"));
        MAPPING.put("BegleitdokumenteundSignaturen0342", SatzTyp.of("0342"));
        MAPPING.put("BeteiligungsInformationssatzsatzart0300", SatzTyp.of("0300"));
        MAPPING.put("KlauselDatensatzsatzart0350", SatzTyp.of("0350"));
        MAPPING.put("ProduktspezifischeAntragsdaten0372", SatzTyp.of("0372"));
        MAPPING.put("RabatteundZuschlaege0390", SatzTyp.of("0390"));
        MAPPING.put("eVBNummer0392", SatzTyp.of("0392"));
        MAPPING.put("AllgemeineInkassoDatensatzart0400", SatzTyp.of("0400"));
        MAPPING.put("InkassoTeilspartesatzart0410", SatzTyp.of("0410"));
        MAPPING.put("AllgemeineInkassoDatensatzart0430", SatzTyp.of("0430"));
        MAPPING.put("Schadeninformationssatzsatzart0500", SatzTyp.of("0500"));
        MAPPING.put("VerssteuerabrechnungssatzGemaessEgRichtliniesatzart0420", SatzTyp.of("0420"));
        MAPPING.put("InkassoAbrechnungssatzTransportsatzart0450", SatzTyp.of("0450"));
        MAPPING.put("Schadenabrechnungssatzsatzart0550", SatzTyp.of("0550"));
        MAPPING.put("ProduktspezifischeStammdaten", SatzTyp.of("0600"));
        MAPPING.put("MIMEDatei9951", SatzTyp.of("9951"));
        MAPPING.put("Nachsatzsatzart9999", SatzTyp.of("9999"));
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

    public static Version of(SatzTyp satzTyp, Collection<Feld> felder) {
        String satzart = String.format("%04d", satzTyp.getSatzart());
        for (Feld f : felder) {
            String technischerName = f.getBezeichner().getTechnischerName();
            if (technischerName.contains(satzart) && satzTyp.equals(getSatzTypFrom(technischerName))) {
                return new Version(f);
            }
        }
        return of(satzTyp);
    }

    public static Version of(SatzTyp satzTyp) {
        for (Map.Entry<String, SatzTyp> entry : MAPPING.entrySet()) {
            if (satzTyp.equals(entry.getValue())) {
                return new Version(Bezeichner.of(entry.getKey()), 1);
            }
        }
        String name = "Satzart " + satzTyp.toString().replace('.', ' ');
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
        String technischerName = getBezeichner().getTechnischerName();
        SatzTyp satzTyp = MAPPING.get(technischerName);
        if (satzTyp == null) {
            satzTyp = getSatzTypFrom(technischerName);
        }
        return satzTyp;
    }

    private static SatzTyp getSatzTypFrom(String technischerName) {
        SatzTyp mapped = MAPPING.get(technischerName);
        if (mapped != null) {
            return mapped;
        }
        StringBuilder bufSatzTyp = new StringBuilder();
        String typ = technischerName.replaceAll("[a-zA-Z]", "");
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
