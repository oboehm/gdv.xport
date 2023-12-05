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
        MAPPING.put(new Bezeichner("Allgemeine Antragsdaten 0202", "AllgemeineAntragsdaten0202", "Satzart0202"), SatzTyp.of("0202"));
        MAPPING.put(new Bezeichner("Satzart 0210", "Satzart0210", "Satzart0210080"), SatzTyp.of("0210.080"));
        MAPPING.put(new Bezeichner("Satzart 0220", "Satzart0220", "Satzart0220080"), SatzTyp.of("0220.080"));
        MAPPING.put(new Bezeichner("Satzart 0211", "Satzart0211", "Satzart0211080"), SatzTyp.of("0211.080"));
        MAPPING.put(new Bezeichner("Satzart 0221", "Satzart0221", "Satzart0221080"), SatzTyp.of("0221.080"));
        MAPPING.put(new Bezeichner("Satzart 02102", "Satzart02102", "Satzart0210170"), SatzTyp.of("0210.170"));
        MAPPING.put(new Bezeichner("Satzart 02202", "Satzart02202", "Satzart0220170"), SatzTyp.of("0220.170"));
        MAPPING.put(new Bezeichner("Satzart 02112", "Satzart02112", "Satzart0211170"), SatzTyp.of("0211.170"));
        MAPPING.put(new Bezeichner("Satzart 02212", "Satzart02212", "Satzart0221170"), SatzTyp.of("0221.170"));
        MAPPING.put(new Bezeichner("Satzart 02103", "Satzart02103", "Satzart0210190"), SatzTyp.of("0210.190"));
        MAPPING.put(new Bezeichner("Satzart 02203", "Satzart02203", "Satzart0220190"), SatzTyp.of("0220.190"));
        MAPPING.put(new Bezeichner("Satzart 02113", "Satzart02113", "Satzart0211190"), SatzTyp.of("0211.190"));
        MAPPING.put(new Bezeichner("Satzart 02213", "Satzart02213", "Satzart0221190"), SatzTyp.of("0221.190"));
        MAPPING.put(new Bezeichner("Satzart 02104", "Satzart02104", "Satzart0210000"), SatzTyp.of("0210.000"));
        MAPPING.put(new Bezeichner("Satzart 02204", "Satzart02204", "Satzart0220000"), SatzTyp.of("0220.000"));
        MAPPING.put(new Bezeichner("Satzart 02214", "Satzart02214", "Satzart0221000"), SatzTyp.of("0221.000"));
        MAPPING.put(new Bezeichner("Satzart 02114", "Satzart02114", "Satzart0211000"), SatzTyp.of("0211.000"));
        MAPPING.put(new Bezeichner("Satzart 0220 010", "Satzart0220010"), SatzTyp.of("0220.010.0"));
        MAPPING.put(new Bezeichner("Kfz-Baustein", "KfzBaustein", "Satzart0220055"), SatzTyp.of("0220.055"));
        MAPPING.put(new Bezeichner("Satzart 0212", "Satzart0212", "Satzart0212050"), SatzTyp.of("0212.050"));
        MAPPING.put(new Bezeichner("Leben/Rente-Leistungsarten", "LebenRenteLeistungsarten", "Satzart0225010"), SatzTyp.of("0225.010"));
        MAPPING.put(new Bezeichner("Fondsdatensatz - Leben 0230", "FondsdatensatzLeben0230", "Satzart0230010"), SatzTyp.of("0230.010"));
        MAPPING.put(new Bezeichner("Unfallspezifische Antragsdaten 0222", "UnfallspezifischeAntragsdaten0222", "Satzart0222030"), SatzTyp.of("0222.030"));
        MAPPING.put(new Bezeichner("Unfall Leistungsarten 0230", "UnfallLeistungsarten0230", "Satzart0230030"), SatzTyp.of("0230.030"));
        MAPPING.put(new Bezeichner("KFZ - Wechselkennzeichen W-AKZ", "KFZWechselkennzeichenWAKZ", "Satzart0230050"), SatzTyp.of("0230.050"));
        MAPPING.put(new Bezeichner("Verbundene Geb\u00e4ude - Versicherte Sachen und Kosten", "VerbundeneGebaeudeVersicherteSachenUndKosten", "Satzart0230140"), SatzTyp.of("0230.140"));
        MAPPING.put(new Bezeichner("Satzart 0250 Einzelanmeldung", "Satzart0250Einzelanmeldung", "Satzart0250190"), SatzTyp.of("0250.190"));
        MAPPING.put(new Bezeichner("Satzart 0251 Einzelanmeldung", "Satzart0251Einzelanmeldung", "Satzart0251190"), SatzTyp.of("0251.190"));
        MAPPING.put(new Bezeichner("Satzart 0260 Umsatzanmeldung", "Satzart0260Umsatzanmeldung", "Satzart0260190"), SatzTyp.of("0260.190"));
        MAPPING.put(new Bezeichner("Begleitdokumente und Signaturen 0342", "BegleitdokumenteundSignaturen0342", "Satzart0342"), SatzTyp.of("0342"));
        MAPPING.put(new Bezeichner("Beteiligungs-Informationssatz Satzart 0300", "BeteiligungsInformationssatzSatzart0300", "Satzart0300"), SatzTyp.of("0300"));
        MAPPING.put(new Bezeichner("Klausel-Datensatz Satzart 0350", "KlauselDatensatzSatzart0350", "Satzart0350"), SatzTyp.of("0350"));
        MAPPING.put(new Bezeichner("Produktspezifische Antragsdaten 0372", "ProduktspezifischeAntragsdaten0372", "Satzart0372"), SatzTyp.of("0372"));
        MAPPING.put(new Bezeichner("Rabatte und Zuschl\u00e4ge 0390", "RabatteundZuschlaege0390", "Satzart0390"), SatzTyp.of("0390"));
        MAPPING.put(new Bezeichner("eVB-Nummer 0392", "eVBNummer0392", "Satzart0392"), SatzTyp.of("0392"));
        MAPPING.put(new Bezeichner("Allgemeine Inkasso-Daten Satzart 0400", "AllgemeineInkassoDatenSatzart0400", "Satzart0400"), SatzTyp.of("0400"));
        MAPPING.put(new Bezeichner("Inkasso Teilsparte Satzart 0410", "InkassoTeilsparteSatzart0410", "Satzart0410"), SatzTyp.of("0410"));
        MAPPING.put(new Bezeichner("Allgemeine Inkasso-Daten Satzart 0430", "AllgemeineInkassoDatenSatzart0430", "Satzart0430"), SatzTyp.of("0430"));
        MAPPING.put(new Bezeichner("Schadeninformationssatz Satzart 0500", "Schadeninformationssatzsatzart0500", "Satzart0500"), SatzTyp.of("0500"));
        MAPPING.put(new Bezeichner("Versicherungsteuerabrechnungssatz gem\u00e4\u00df EG-Richtlinie Satzart 0420", "VerssteuerabrechnungssatzGemaessEgRichtliniesatzart0420", "Satzart0420"), SatzTyp.of("0420"));
        MAPPING.put(new Bezeichner("Inkasso Abrechnungssatz Transport Satzart 0450", "InkassoAbrechnungssatzTransportSatzart0450", "Satzart0450"), SatzTyp.of("0450"));
        MAPPING.put(new Bezeichner("Schadenabrechnungssatz Satzart 0550", "Schadenabrechnungssatzsatzart0550", "Satzart0550"), SatzTyp.of("0550"));
        MAPPING.put(new Bezeichner("Produktspezifische Stammdaten 0600", "ProduktspezifischeStammdaten", "Satzart0600"), SatzTyp.of("0600"));
        MAPPING.put(new Bezeichner("MIME-Datei 9951", "MIMEDatei9951", "Satzart9951"), SatzTyp.of("9951"));
        MAPPING.put(new Bezeichner("Nachsatz Satzart 9999", "Nachsatzsatzart9999", "Satzart9999"), SatzTyp.of("9999"));
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
        SatzTyp mapped = MAPPING.get(b);
        if (mapped != null) {
            return mapped;
        }
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
