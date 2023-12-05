/*
 * Copyright (c) 2009 - 2019 by Oli B.
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
 * (c)reated 05.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.util.SatzRegistry;
import gdv.xport.util.SatzTyp;
import gdv.xport.util.VersionHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;

import static gdv.xport.feld.Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_BIS;
import static gdv.xport.feld.Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_VOM;

/**
 * Dies ist der erste Satz, der Vorsatz eben.
 * <p>
 * Da Vorsatz und Nachsatz von der Datenpaket-Klasse benoetigt werden, habe
 * ich das "deprecated" wieder entfernt (24-Nov-2012, oboehm).
 * </p>
 *
 * @author oliver
 * @since 0.0.1 (09-Okt-2009)
 */
public class Vorsatz extends Satz {

    /** Satzart fuer Vorsatz. */
    public static final SatzTyp SATZART = SatzTyp.of("0001");

    private static final Logger LOG = LogManager.getLogger(Vorsatz.class);
    private final VersionHandler versionHandler;

    /**
     * Hiermit wird ein Vorsatz mit 3 Teildatensaetzen erstellt.
     */
    public Vorsatz() {
        this(SatzRegistry.getInstance());
    }

    /**
     * Ueber die mitgegebene Factory wird der Vorsatz genauso aufgebaut, wie
     * die {@link SatzRegistry} als Vorlage liefert.
     *
     * @param factory sollte die Vorlage fuer den Vorsatz liefern.
     * @since 5.0
     */
    public Vorsatz(SatzRegistry factory) {
        this(factory.getSatz(SATZART), factory);
    }

    private Vorsatz(Satz vorlage, VersionHandler versionHandler) {
        super(vorlage, vorlage.cloneTeildatensaetze());
        this.versionHandler = versionHandler;
        setVuNummer(Config.getInstance().getVUNr().getInhalt());
    }

    /**
     * Legt einen Vorsatz mit dem angegebenen Inhalt an.
     *
     * @param content Inhalt des Vorsatzes
     */
    public Vorsatz(final String content) {
        this();
        try {
            this.importFrom(content);
            LOG.debug(this + " created from \"" + content + '"');
        } catch (IOException ioe) {
            throw new IllegalArgumentException("argument too short", ioe);
        }
    }

    /**
     * Dies ist der Copy-Constructor, mit dem man einen bestehenden Vorsatz
     * kopieren kann.
     *
     * @param other der originale Vorsatz
     */
    public Vorsatz(final Vorsatz other) {
        this(other, other.versionHandler);
    }

    private static Bezeichner getVersionBezeichner(final int art) {
        try (Formatter formatter = new Formatter()) {
            String bezeichnung = formatter.format("Version Satzart %04d", art).toString();
            return Bezeichner.of(bezeichnung);
        }
    }

    private static Bezeichner getVersionBezeichner(final int art, final int sparte) {
        try (Formatter formatter = new Formatter()) {
            String bezeichnung = formatter.format("Version Satzart %04d.%03d", art, sparte).toString();
            return Bezeichner.of(bezeichnung);
        }
    }

    /**
     * Um die VU-Nummer (Byte 5 - 9) in allen Teildatensaetzen setzen zu koennen.
     *
     * @param s
     *            VU-Nummer (max. 5-stellig)
     */
    public void setVuNummer(final String s) {
        assert s.length() <= 5 : s + " darf nur max. 5 Zeichen lang sein";
        this.setFeld(ByteAdresse.VU_NUMMER, s);
    }

    /**
     * @return VU-Nummer
     */
    public String getVuNummer() {
        return this.getFeld(ByteAdresse.VU_NUMMER)
                .getInhalt()
                .trim();
    }

    /**
     * Um Absender (Byte 10 - 39) in allen Teildatensaetzen setzen zu koennen.
     *
     * @param name Absender
     */
    public void setAbsender(final String name) {
        this.setFeld(Bezeichner.ABSENDER, name);
    }

    /**
     * Liefert den Absender (Byte 10 - 39).
     *
     * @return Absender
     */
    public String getAbsender() {
        return this.getFeld(Bezeichner.ABSENDER).getInhalt().trim();
    }

    /**
     * Um Adressat (Byte 40 - 69) in allen Teildatensaetzen setzen zu koennen.
     *
     * @param name neuer Adressat
     */
    public void setAdressat(final String name) {
        this.setFeld(Bezeichner.ADRESSAT, name);
    }

    /**
     * @return Adressat
     */
    public String getAdressat() {
        return this.getFeld(Bezeichner.ADRESSAT)
                .getInhalt()
                .trim();
    }

    /**
     * Um Erstellungs-Datum Zeitraum vom- Zeitraum bis (Byte 70 - 85) in allen
     * Teildatensaetzen setzen zu koennen.
     *
     * @param startDatum im Format "TTMMJJJJ"
     * @param endDatum   im Format "TTMMJJJJ"
     */
    public void setErstellungsZeitraum(final String startDatum, final String endDatum) {
        Datum von = new Datum(Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_VOM, 70);
        Datum bis = new Datum(Bezeichner.ERSTELLUNGSDAT_ZEITRAUM_BIS, 78);
        von.setInhalt(startDatum);
        bis.setInhalt(endDatum);

        this.setFeld(Bezeichner.ERSTELLUNGS_DAT_ZEITRAUM_VOM_ZEITRAUM_BIS, (von
                .getInhalt()).concat(bis.getInhalt()));
    }

    /**
     * Um Erstellungs-Datum Zeitraum vom- Zeitraum bis (Byte 70 - 85) in allen
     * Teildatensaetzen setzen zu koennen.
     *
     * @param startDatum im Format "TTMMJJJJ"
     * @param endDatum   im Format "TTMMJJJJ"
     */
    public void setErstellungsZeitraum(final Datum startDatum,
                                       final Datum endDatum) {
        this.setFeld(Bezeichner.ERSTELLUNGS_DAT_ZEITRAUM_VOM_ZEITRAUM_BIS, (startDatum
                .getInhalt()).concat(endDatum.getInhalt()));
    }

    /**
     * @return Erstellungszeitraum (VonDatum, BisDatum)
     */
    public String getErstellungsZeitraum() {
        return this.getFeld(ByteAdresse.of(70)).getInhalt().trim();
    }

    public void setErstellungsZeitraumVon(Datum von) {
        setErstellungsZeitraum(von, getErstellungsZeitraumBis());
    }

    public Datum getErstellungsZeitraumVon() {
        String vonBis = getErstellungsZeitraum();
        Datum von = new Datum(ERSTELLUNGSDAT_ZEITRAUM_VOM, 8, 70);
        von.setInhalt(vonBis.substring(0, 8));
        return von;
    }

    public void setErstellungsZeitraumBis(Datum bis) {
        setErstellungsZeitraum(getErstellungsZeitraumVon(), bis);
    }

    public Datum getErstellungsZeitraumBis() {
        String vonBis = getErstellungsZeitraum();
        Datum bis = new Datum(ERSTELLUNGSDAT_ZEITRAUM_BIS, 8, 78);
        bis.setInhalt(vonBis.substring(8));
        return bis;
    }

    /**
     * Setzen der Satzart-Version eines Datensatzes, falls die Satzart im Vorsatz
     * bekannt ist.
     *
     * @param satz der Satz
     * @return die Satzversion oder <code>null</code> wenn Satzart unbekannt
     */
    public String getVersion(Satz satz) {
        return getVersion(satz.getSatzTyp());
    }

    /**
     * Ermittelt die Version des uebergebenen Bezeichners.
     *
     * @param bezeichner z.B. VERSION_VORSATZ; hier koennen alle die
     *            Bezeichner-Konstanten gewaehlt werden, die mit "VERSION_"
     *            anfangen.
     * @return Version des gewuenschten Bezeichners
     * @since 2.0
     */
    public String getVersion(final Bezeichner bezeichner) {
        return this.getFeld(bezeichner).getInhalt();
    }

    /**
     * Ermittelt die Version des uebergebenen Bezeichners.
     *
     * @param bezeichner z.B. VERSION_VORSATZ; hier koennen alle die
     *            Bezeichner-Konstanten gewaehlt werden, die mit "VERSION_"
     *            anfangen.
     * @return Version des gewuenschten Bezeichners
     */
    public String getVersion(final String bezeichner) {
        return this.getFeld(bezeichner).getInhalt();
    }

    /**
     * @param art Satzart
     * @return z.B. 1.1
     */
    public String getVersion(final int art) {
        return this.getVersion(getVersionBezeichner(art));
    }

    /**
     * @param art Satzart
     * @param sparte z.B. 70 (Rechtsschutz)
     * @return z.B. 1.1
     */
    public String getVersion(final int art, final int sparte) {
        return this.getVersion(getVersionBezeichner(art, sparte));
    }

    /**
     * Liefert die Version zum gewuenschten SatzTyp.
     *
     * @param satzTyp z.B. SatzTyp.of("0100");
     * @return z.B. 2.3
     * @since 5.0
     */
    public String getVersion(SatzTyp satzTyp) {
        Bezeichner bezeichner = Bezeichner.of("Version Satzart " + satzTyp);
        return this.getVersion(bezeichner);
    }

    /**
     * Setzen der Satzart-Version eines Datensatzes.
     *
     * @param satz der Satz
     */
    @JsonIgnore
    public void setVersion(Satz satz) {
        StringBuilder buf = new StringBuilder();
        String[] parts = StringUtils.split(satz.getSatzTyp()
                .toString(), '.');

        buf.append("Satzart");
        buf.append(parts[0]);

        if (parts.length > 1)
            buf.append(parts[1]);

        Bezeichner bezeichner = Bezeichner.of(buf.toString());

        if (this.hasFeld(bezeichner)) {
            this.setFeld(bezeichner, satz.getSatzversion().getInhalt());
        } else {
            Version version = Version.of(satz.getSatzTyp(), getFelder());
            if (this.hasFeld(version.getBezeichner())) {
                this.setFeld(version.getBezeichner(), satz.getSatzversion().getInhalt());
            } else {
                LOG.warn("Version Satzart {} ist im Vorsatz unbekannt.", satz.getSatzTyp());
            }
        }
    }

    /**
     * Setzen der Satzart-Version eines SatzTyps.
     *
     * @param satzTyp der Satztyp
     */
    public void setVersion(SatzTyp satzTyp) {
        StringBuilder buf = new StringBuilder();
        String[] parts = StringUtils.split(satzTyp.toString(), '.');

        buf.append("Satzart");
        buf.append(parts[0]);

        if (parts.length > 1)
            buf.append(parts[1]);

        Bezeichner bezeichner = Bezeichner.of(buf.toString());

        if (this.hasFeld(bezeichner)) {
            this.setFeld(bezeichner, versionHandler.getVersionOf(satzTyp));
        } else {
            throw new IllegalArgumentException("Version Satzart " + bezeichner + " unbekannt");
        }
    }

    /**
     * Setzen der Version.
     *
     * @param bezeichner Bezeichner
     * @param version    z.B. "1.2"
     * @since 4.1.1
     */
    public void setVersion(Bezeichner bezeichner, String version) {
        this.getFeld(bezeichner).setInhalt(version);
    }

    /**
     * Setzen der Version.
     * <p>
     * TODO: wird ab v7 entfernt
     * </p>
     *
     * @param art     Satzart
     * @param version z.B. "1.2"
     * @since 4.1.1
     * @deprecated durch {@link #setVersion(Bezeichner, String)} ersetzt
     */
    @Deprecated
    public void setVersion(int art, String version) {
        this.setVersion(getVersionBezeichner(art), version);
    }

    /**
     * Setzen der Version.
     *
     * @param art     Satzart
     * @param sparte  Sparte
     * @param version z.B. "1.2"
     * @since 4.1.1
     */
    public void setVersion(int art, int sparte, String version) {
        this.setVersion(getVersionBezeichner(art, sparte), version);
    }

    /**
     * Liefert eine Liste aller gesetzter Versionen im Vorsatz.
     *
     * @return Liste mit Versionen
     * @since 5.2
     */
    public Map<SatzTyp, Version> getSatzartVersionen() {
        Map<SatzTyp, Version> versionen = new HashMap<>();
        for (Feld f : getFelder()) {
            if (!f.isEmpty() && f.getBezeichner().getTechnischerName().toLowerCase().contains("satzart")
                    && !f.getBezeichner().equals(Bezeichner.SATZART)) {
                Version v = new Version(f);
                switch (f.getBezeichner().getTechnischerName()) {
                    case "Satzart0220020":
                        // alle 0220.020er Satzarten haben die gleiche Version!
                        versionen.put(SatzTyp.of("0220.020.1"), v);
                        versionen.put(SatzTyp.of("0220.020.2"), v);
                        versionen.put(SatzTyp.of("0220.020.3"), v);
                        break;
                    case "Satzart0220580":
                        // alle 0220.580er Satzarten haben die gleiche Version!
                        versionen.put(SatzTyp.of("0220.580.01"), v);
                        versionen.put(SatzTyp.of("0220.580.2"), v);
                        break;
                    case "Satzart0220010":
                        // alle 0220.010er Satzarten haben die gleiche Version!
                        versionen.put(SatzTyp.of("0220.010.0"), v);
                        versionen.put(SatzTyp.of("0220.010.13.1"), v);
                        versionen.put(SatzTyp.of("0220.010.13.6"), v);
                        versionen.put(SatzTyp.of("0220.010.13.7"), v);
                        versionen.put(SatzTyp.of("0220.010.13.8"), v);
                        versionen.put(SatzTyp.of("0220.010.13.9"), v);
                        versionen.put(SatzTyp.of("0220.010.2.1"), v);
                        versionen.put(SatzTyp.of("0220.010.2.6"), v);
                        versionen.put(SatzTyp.of("0220.010.2.7"), v);
                        versionen.put(SatzTyp.of("0220.010.2.8"), v);
                        versionen.put(SatzTyp.of("0220.010.2.9"), v);
                        versionen.put(SatzTyp.of("0220.010.48.1"), v);
                        versionen.put(SatzTyp.of("0220.010.48.6"), v);
                        versionen.put(SatzTyp.of("0220.010.48.8"), v);
                        versionen.put(SatzTyp.of("0220.010.48.9"), v);
                        versionen.put(SatzTyp.of("0220.010.5.1"), v);
                        versionen.put(SatzTyp.of("0220.010.5.6"), v);
                        versionen.put(SatzTyp.of("0220.010.5.8"), v);
                        versionen.put(SatzTyp.of("0220.010.5.9"), v);
                        versionen.put(SatzTyp.of("0220.010.6.1"), v);
                        versionen.put(SatzTyp.of("0220.010.6.6"), v);
                        versionen.put(SatzTyp.of("0220.010.6.8"), v);
                        versionen.put(SatzTyp.of("0220.010.6.9"), v);
                        versionen.put(SatzTyp.of("0220.010.7.1"), v);
                        versionen.put(SatzTyp.of("0220.010.7.6"), v);
                        versionen.put(SatzTyp.of("0220.010.7.8"), v);
                        versionen.put(SatzTyp.of("0220.010.7.9"), v);
                        versionen.put(SatzTyp.of("0220.010.9.1"), v);
                        versionen.put(SatzTyp.of("0220.010.9.6"), v);
                        versionen.put(SatzTyp.of("0220.010.9.7"), v);
                        versionen.put(SatzTyp.of("0220.010.9.8"), v);
                        versionen.put(SatzTyp.of("0220.010.9.9"), v);
                        break;
                    case "Satzart0221010":
                        // alle 0221.010er Satzarten haben die gleiche Version!
                        versionen.put(SatzTyp.of("0221.010.13.1"), v);
                        versionen.put(SatzTyp.of("0221.010.13.7"), v);
                        versionen.put(SatzTyp.of("0221.010.13.8"), v);
                        versionen.put(SatzTyp.of("0221.010.2.1"), v);
                        versionen.put(SatzTyp.of("0221.010.2.7"), v);
                        versionen.put(SatzTyp.of("0221.010.2.8"), v);
                        versionen.put(SatzTyp.of("0221.010.48.1"), v);
                        versionen.put(SatzTyp.of("0221.010.5.1"), v);
                        versionen.put(SatzTyp.of("0221.010.5.8"), v);
                        versionen.put(SatzTyp.of("0221.010.6.1"), v);
                        versionen.put(SatzTyp.of("0221.010.6.8"), v);
                        versionen.put(SatzTyp.of("0221.010.7.1"), v);
                        versionen.put(SatzTyp.of("0221.010.7.8"), v);
                        break;
                    default:
                        versionen.put(v.getSatzTyp(), v);
                }
            }
        }
        return versionen;
    }

    /**
     * Da im Feld "Erstellungs-Datum Zeitraum vom- Zeitraum bis" (Adresse 70-85)
     * 2 Datumsfelder zusammengefasst sind, ist diese Methode ueberschrieben,
     * um diese beiden Felder auch einzeln abfragen zu koennen.
     *
     * @param bezeichner gesuchtes Field
     * @return Feld
     */
    @Override
    public Feld getFeld(Bezeichner bezeichner) throws IllegalArgumentException {
        if (bezeichner.equals(ERSTELLUNGSDAT_ZEITRAUM_BIS)) {
            return getErstellungsZeitraumBis();
        } else if (bezeichner.equals(ERSTELLUNGSDAT_ZEITRAUM_VOM)) {
            return getErstellungsZeitraumVon();
        }
        return super.getFeld(bezeichner);
    }

    /**
     * Hier wird {@link Satz#getFelder()} ueberschrieben, um das Feld
     * "Erstellungs-Datum, Zeitraum von, Zeitraum bis" in zwei Felder
     * aufzuteilen. Dies wird u.a. von den verschiedenen Formattern
     * (wie z.B. {@link gdv.xport.util.CsvFormatter} fuer die Aufbereitung
     * der Ausgabe verwendet.
     *
     * @return alle Felder in der richtigen Reihenfolge
     */
    @Override
    public Collection<Feld> getFelder() {
        List<Feld> felder = new ArrayList<>();
        for (Feld f : super.getFelder()) {
            if (f.getBezeichner().equals(Bezeichner.ERSTELLUNGS_DAT_ZEITRAUM_VOM_ZEITRAUM_BIS)) {
                felder.add(getErstellungsZeitraumVon());
                felder.add(getErstellungsZeitraumBis());
            } else {
                felder.add(f);
            }
        }
        return felder;
    }



//    /**
//     * Die Idee bei der VersionenHashMap ist, bei der Abfrage der Versionen fuer
//     * einen bestimmte Satzart (also z.B. 0220.010.7.6) eben nur auf die
//     * "uebergeordnete" Gruppe zu gehen (in dem Falle 0220.010, da nur für diese
//     * Kombination eine Version im Vorsatz geliefert wird).
//     * Diese Logik ist bei {@link VersionenHashMap#get(Object)} abgebildet (s.a.
//     * https://github.com/oboehm/gdv.xport/issues/64#issuecomment-924107450).
//     *
//     * @since 5.2
//     * @author markusneidhart
//     */
//    private static class VersionenHashMap extends HashMap<SatzTyp, Version> {
//
//        @Override
//        public boolean containsKey(Object satzTyp) {
//            return findEntry(satzTyp).isPresent();
//        }
//
//        @Override
//        public Version get(Object satzTyp) {
//            Version v = super.get(satzTyp);
//            if (v == null) {
//                Optional<Entry<SatzTyp, Version>> entry = findEntry(satzTyp);
//                v = entry.map(Entry::getValue).orElse(null);
//            }
//            return v;
//        }
//
//        private Optional<Entry<SatzTyp, Version>> findEntry(Object key) {
//            if (!(key instanceof SatzTyp)) {
//                return Optional.empty();
//            }
//            SatzTyp satzTyp = (SatzTyp) key;
//            return entrySet().stream()
//                    .filter(e -> matches(e, satzTyp)).min(Comparator.comparingInt(e1 -> -e1.getKey().getSparte()));
//        }
//
//        private static boolean matches(Entry<SatzTyp, Version> e, SatzTyp satzTyp) {
//            SatzTyp stored = e.getKey();
//            return stored.getSatzart() == satzTyp.getSatzart() &&
//                    (!satzTyp.hasSparte() || !stored.hasSparte() || stored.getSparte() == satzTyp.getSparte());
//        }
//
//    }

}
