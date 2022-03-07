/*
 * Copyright (c) 2009 - 2012 by Oli B. Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express orimplied. See the License for the specific language
 * governing permissions and limitations under the License. (c)reated 23.10.2009
 * by Oli B. (ob@aosd.de)
 */

package gdv.xport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gdv.xport.config.Config;
import gdv.xport.event.ImportListener;
import gdv.xport.event.SatzValidator;
import gdv.xport.feld.*;
import gdv.xport.io.*;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Nachsatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Vorsatz;
import gdv.xport.util.*;
import net.sf.oval.ConstraintViolation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Ein Datenpaket besteht aus {@link Vorsatz}, mehrere {@link Datensatz}-Elementen
 * und einem {@link Nachsatz}.
 *
 * @author oliver
 * @since 23.10.2009
 */
public class Datenpaket implements ImportListener {

    private static final Logger LOG = LogManager.getLogger(Datenpaket.class);
    private Vorsatz vorsatz;
    private final List<Datensatz> datensaetze = new ArrayList<>();
    private final Config config;
    private Nachsatz nachsatz;

    /**
     * Wenn man den Default-Konstruktor verwendet, sollte man vorher die
     * VU-Nummer konfiguriert haben.
     *
     * @see Config#getVUNr()
     */
    public Datenpaket() {
        this(Config.getInstance());
    }

    /**
     * Hierueber kann eine eigene Config mit uebergeben werden.
     *
     * @param config eigene Config
     * @since 5.3
     */
    public Datenpaket(Config config) {
        this.config = config;
        this.vorsatz = new Vorsatz(SatzRegistry.getInstance(config));
        this.nachsatz = new Nachsatz(SatzRegistry.getInstance(config));
        this.vorsatz.setVersion(this.nachsatz);
        Datum heute = Datum.heute();
        this.setErstellungsDatumVon(heute);
        this.setVuNummer(config.getVUNr().getInhalt());
        LOG.debug("{} created.", this);
    }

    /**
     * Falls die VU-Nummer noch nicht konfiguriert ist, kann man zu diesem
     * Konstruktor greifen.
     * <p>
     * Absender wird jetzt nicht mehr vorbelegt, da der Absender der Klarname
     * des VUs ist (und nicht bekannt ist). Auch das ErstellungsDatumBis wird
     * nicht mehr vorbelegt.
     * </p>
     *
     * @param vuNummer die Nummer des Versicherungsunternehmens (VU)
     * @since 0.3
     */
    public Datenpaket(final String vuNummer) {
        this(Config.getInstance().withProperty("gdv.VU-Nummer", vuNummer));
    }

    /**
     * Legt ein Datenpaket mit den angegebenen Datensaetze an.
     *
     * @param datensaetze fuers Datenpaket
     * @return ein neues Datenpaket
     * @since 5.2
     */
    public static Datenpaket of(Collection<Satz> datensaetze) {
        Datenpaket datenpaket = new Datenpaket();
        List<Datensatz> dsList = new ArrayList<>();
        for (Satz satz : datensaetze) {
            if (satz instanceof Vorsatz) {
                datenpaket.vorsatz = (Vorsatz) satz;
            } else if (satz instanceof Nachsatz) {
                datenpaket.nachsatz = (Nachsatz) satz;
                datenpaket.vorsatz.setVersion(datenpaket.nachsatz);
            } else {
                dsList.add((Datensatz) satz);
            }
        }
        for (Datensatz satz : dsList) {
            datenpaket.add(satz);
        }
        return datenpaket;
    }

    /**
     * Um die VU-Nummer setzen zu koennen.
     *
     * @param vuNummer VU-Nummer (max. 5-stellig)
     */
    public void setVuNummer(final String vuNummer) {
        this.vorsatz.setVuNummer(vuNummer);
        for (Datensatz datensatz : this.datensaetze) {
            if (datensatz.hasFeld(Bezeichner.VU_NUMMER)) {
                datensatz.setVuNummer(vuNummer);
            }
        }
    }

    /**
     * Dazu verwenden wir den Vorsatz, um die VU-Nummer zu bestimmen.
     *
     * @return VU-Nummer aus dem Vorsatz
     * @since 0.3
     */
    public String getVuNummer() {
        return this.vorsatz.getVuNummer();
    }

    /**
     * Gets the datensaetze.
     *
     * @return the datensaetze
     */
    public List<Datensatz> getDatensaetze() {
        return Collections.unmodifiableList(datensaetze);
    }

    /**
     * Liefert die Liste der Datensaetze zurueck, die mit dem uebergebenen
     * {@link SatzTyp} uebereinstimmen.
     *
     * @param typ gewuenschter {@link SatzTyp}
     * @return Liste von Datensaetzen
     * @since 5.2
     */
    public List<Datensatz> getDatensaetze(SatzTyp typ) {
        List<Datensatz> saetze = new ArrayList<>();
        for (Datensatz ds : datensaetze) {
            if (typ.equals(ds.getSatzTyp())) {
                saetze.add(ds);
            }
        }
        return saetze;
    }

    /**
     * Gets the saetze.
     *
     * @return the saetze
     * @since 5.0
     */
    public List<Satz> getAllSaetze() {
        List<Satz> satzListe = new ArrayList<>();
        satzListe.add(this.vorsatz);
        satzListe.addAll(datensaetze);
        satzListe.add(this.nachsatz);
        return Collections.unmodifiableList(satzListe);
    }
    /**
     * Sets the datensaetze.
     *
     * @param datensaetze the datensaetze to set
     */
    public void setDatensaetze(final List<Datensatz> datensaetze) {
        this.datensaetze.clear();
        this.datensaetze.addAll(datensaetze);
    }

    /**
     * Liefert den internen Vorsatz.
     * <p>
     * <b>Achtung:</b>Der Vorsatz wird intern durch das Datenpaket verwaltet.
     * Er kann aber veraendert werden, wenn dies notwendig ist (z.B. zum
     * Setzen der Satzart-Versionen).
     * </p>
     *
     * @return Vorsatz
     */
    public Vorsatz getVorsatz() {
        return this.vorsatz;
    }

    /**
     * Liefert den internen Nachsatz.
     * <p>
     * <b>Achtung:</b>Der Nachsatz wird intern durch das Datenpaket verwaltet.
     * Aus Symmetriegruenden zum Vorsatz kann auch der Nachsatz veraendert
     * werden. Normalerweise sollte das aber nicht notwendig sein.
     * </p>
     *
     * @return Nachsatz
     */
    public Nachsatz getNachsatz() {
        return this.nachsatz;
    }

    /**
     * Fuegt den uebergebenen Datensatz hinzu.
     * <p>
     * <b>Achtung:</b> Satzart 001 (Vorsatz) bzw. Satzart 9999 (Nachsatz) kann
     * nicht hinzugefuegt werden!
     * </p>
     *
     * @param datensatz Datensatz, der hinzugefuegt werden soll
     */
    public void add(final Datensatz datensatz) {
        if (("0001").equalsIgnoreCase(datensatz.getGdvSatzartName()) || ("9999")
                .equalsIgnoreCase(datensatz.getGdvSatzartName()))
            throw new IllegalArgumentException(("0001").equalsIgnoreCase(datensatz
                    .getGdvSatzartName()) ? "Einen Vorsatz gibt es bereits!"
                    : "Einen Nachsatz gibt es bereits!");
        preset(datensatz);
        datensaetze.add(datensatz);
        vorsatz.setVersion(datensatz);
        if (datensatz.getSatzTyp().equals(SatzTyp.of(200))) {
            setNachsatzSummenAus0200(datensatz);
        } else if (datensatz.getSatzTyp().equals(SatzTyp.of(400))) {
            setNachsatzSummenAus0400(datensatz);
        } else if (datensatz.getSatzTyp().equals(SatzTyp.of(500))) {
            setNachsatzSummenAus0500(datensatz);
        }
        nachsatz.setAnzahlSaetze(nachsatz.getAnzahlSaetze() + datensatz.getNumberOfTeildatensaetze());
    }

    private void preset(Datensatz datensatz) {
        if (StringUtils.isNotEmpty(getVuNummer()) && datensatz.hasVuNummer() && StringUtils.isEmpty(datensatz.getVuNummer())) {
            datensatz.setVuNummer(getVuNummer());
        }
        if (StringUtils.isEmpty(datensatz.getVermittler())) {
            datensatz.setVermittler(getVermittler());
        }
    }

    /**
     * Export.
     *
     * @param file Datei, in die exportiert werden soll
     * @throws IOException falls was schiefgelaufen ist (z.B. Platte voll)
     */
    public void export(final File file) throws IOException {
        export(file, Config.DEFAULT_ENCODING);
    }

    /**
     * Export.
     *
     * @param file     Datei, in die exportiert werden soll
     * @param encoding z.B. "ISO-8859-1"
     * @throws IOException falls was schiefgelaufen ist (z.B. Platte voll)
     * @since 1.0
     */
    public void export(final File file, final String encoding) throws IOException {
        export(file, Charset.forName(encoding));
    }

    /**
     * Export.
     *
     * @param file     Datei, in die exportiert werden soll
     * @param encoding z.B. "ISO-8859-1"
     * @throws IOException falls was schiefgelaufen ist (z.B. Platte voll)
     * @since 1.0
     */
    public void export(final File file, final Charset encoding) throws IOException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), encoding)) {
            export(writer);
        }
    }

    /**
     * Falls wir einen Stream haben, koennen wir diese Methode benutzen.
     *
     * @param ostream z.B. System.out
     * @throws IOException falls was schiefgelaufen ist
     * @since 0.3
     */
    public void export(final OutputStream ostream) throws IOException {
        Writer writer = new OutputStreamWriter(ostream, Config.DEFAULT_ENCODING);
        export(writer);
        writer.flush();
        ostream.flush();
    }

    /**
     * Export.
     *
     * @param writer wird zum Export verwendet
     * @throws IOException falls was schiefgelaufen ist
     */
    public void export(final Writer writer) throws IOException {
        vorsatz.export(writer);
        for (Datensatz datensatz : datensaetze) {
            datensatz.export(writer);
        }
        nachsatz.export(writer);
        writer.flush();
        LOG.info("{} Saetze exportiert.", nachsatz.getAnzahlSaetze());
    }

    /**
     * Damit kann direkt ueber das Netz importiert werden. Gibt man eine
     * File-URL (oder File) an, kann man damit auch direkt aus einer Datei importieren.
     *
     * @param uri z.B.
     *            http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @return das Datenpaket zur Weiterverabeitung (seit 5.2)
     * @throws IOException wenn z.B. das Netz weg ist
     * @since 3.0
     */
    public Datenpaket importFrom(final URI uri) throws IOException {
        return importFrom(uri.toURL());
    }

    /**
     * Damit kann direkt ueber das Netz importiert werden. Gibt man eine
     * File-URL (oder File) an, kann man damit auch direkt aus einer Datei importieren.
     *
     * @param url z.B.
     *            http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @return das Datenpaket zur Weiterverabeitung (seit 5.2)
     * @throws IOException wenn z.B. das Netz weg ist
     * @since 0.3
     */
    public Datenpaket importFrom(final URL url) throws IOException {
        URLReader urlReader = new URLReader(url);
        String content = urlReader.read();
        return importFrom(content);
    }

    /**
     * Importiert direkt aus einem String.
     *
     * @param content Inhalt der eingelesen wird
     * @return das Datenpaket zur Weiterverabeitung (seit 5.2)
     * @throws IOException sollte eigentlich nicht vorkommen
     * @since 0.3
     */
    public Datenpaket importFrom(final String content) throws IOException {
        try (Reader reader = new StringReader(content)) {
            importFrom(reader);
        }
        return this;
    }

    /**
     * Importiert von einem {@link InputStream}.
     *
     * @param istream z.B. Sytem.in
     * @return das Datenpaket zur Weiterverabeitung (seit 5.2)
     * @throws IOException falls es Fehler beim Lesen gibt
     */
    public Datenpaket importFrom(final InputStream istream) throws IOException {
        Reader reader = new RecyclingInputStreamReader(istream, Config.DEFAULT_ENCODING);
        return importFrom(reader);
    }

    /**
     * Import von einem {@link Reader}.
     *
     * @param reader hiervon wird importiert
     * @return das Datenpaket zur Weiterverabeitung (seit 5.2)
     * @throws IOException falls was schiefgelaufen ist
     */
    public Datenpaket importFrom(final Reader reader) throws IOException {
        PushbackLineNumberReader lnr = new PushbackLineNumberReader(new RecordReader(reader), 256);
        try {
            return importFrom(lnr);
        } catch (EOFException eofe) {
            throw new ExtendedEOFException("line " + lnr.getLineNumber() + ": " + eofe.getMessage(), eofe);
        } catch (IOException ioe) {
            throw new ImportException(lnr, "read error", ioe);
        } catch (NumberFormatException nfe) {
            throw new ImportException(lnr, "number expected, but found: \"" + lnr.readLine() + '"', nfe);
        }
    }

    /**
     * Der hier verwendete PushbackReader wird benoetigt, damit die gelesene
     * Satzart und Sparte wieder zurueckgestellt werden kann.
     * <p>
     * Im Gegensatz zu {@link Datenpaket#importSatz(PushbackLineNumberReader, Map)}
     * werden die Versionen (aus dem Vorsatz) nicht beruecksichtigt.
     * </p>
     *
     * @param reader PushbackReader mit einem Puffer von mind. 14 Zeichen
     * @return das Datenpaket zur Weiterverabeitung (seit 5.2)
     * @throws IOException falls was schief gelaufen ist
     */
    public Datenpaket importFrom(final PushbackLineNumberReader reader) throws IOException {
        this.vorsatz.importFrom(reader);
        Map<SatzTyp, Version> satzartVersionen = this.vorsatz.getSatzartVersionen();
        while (true) {
            Satz satz = importSatz(reader, satzartVersionen);
            if (satz.getSatzart() == 9999) {
                this.nachsatz = (Nachsatz) satz;
                break;
            }
            datensaetze.add((Datensatz) satz);
        }
        return this;
    }

    /**
     * Der hier verwendete PushbackReader wird benoetigt, damit die gelesene
     * Satzart und Sparte wieder zurueckgestellt werden kann.
     *
     * @param reader PushbackReader mit einem Puffer von mind. 14 Zeichen
     * @param satzartVersionen Satz-Versionen aus dem Vorsatz
     * @return das Datenpaket zur Weiterverabeitung
     * @throws IOException falls was schief gelaufen ist
     */
    protected static Satz importSatz(PushbackLineNumberReader reader, Map<SatzTyp, Version> satzartVersionen) throws IOException {
        int satzart = Importer.of(reader).readSatzart();
        LOG.debug("Satzart {} wird importiert...", satzart);
        if (satzart == 9999) {
            return importNachsatzFrom(reader);
        } else {
            return importSatzFrom(reader, satzart, satzartVersionen);
        }
    }

    private static Satz importSatzFrom(PushbackLineNumberReader reader, int satzart,
                                       Map<SatzTyp, Version> satzartVersionen) throws IOException {
        SatzTyp satzTyp = Importer.of(reader).readSatzTyp(satzart);
        Version wanted = satzartVersionen.get(satzTyp);
        if (wanted == null) {
            return importDatensatz(reader, satzart);
        } else {
            Satz satz = SatzRegistry.getSatz(satzTyp, satzartVersionen.get(satzTyp).getInhalt());
            satz.importFrom(reader);
            return satz;
        }
    }

    /**
     * Importiert einen einzelnen Satz. Dies kann entweder ein Datensatz, oder
     * aber der Nachsatz sein.
     *
     * @param reader the reader
     * @return the satz
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static Satz importSatz(final PushbackLineNumberReader reader) throws IOException {
        int satzart = Importer.of(reader).readSatzart();
        LOG.debug("reading Satzart " + satzart + "...");
        if (satzart == 9999) {
            return importNachsatzFrom(reader);
        } else {
            return importDatensatz(reader, satzart);
        }
    }

    private static Nachsatz importNachsatzFrom(PushbackLineNumberReader reader) throws IOException {
        Nachsatz nachsatz = new Nachsatz();
        nachsatz.importFrom(reader);
        return nachsatz;
    }

    private static Satz importDatensatz(final PushbackLineNumberReader reader, final int satzart)
            throws IOException {
        SatzTyp satzTyp = Importer.of(reader).readSatzTyp(satzart);
        Satz satz = getSatz(satzTyp);
        satz.importFrom(reader);
        return satz;
    }

    private static Satz getSatz(SatzTyp satzTyp) {
        try {
            return SatzRegistry.getInstance().getSatz(satzTyp);
        } catch (NotRegisteredException ex) {
            LOG.warn("Satzart '{}' ist nicht registriert und wird generiert.", satzTyp);
            LOG.debug("Details:", ex);
            Datensatz satz = new Datensatz(SatzTyp.of(satzTyp.getSatzart(), satzTyp.getSparte()));
            satz.addFiller();
            return satz;
        }
    }

    /**
     * Importieren einer Datei.
     *
     * @param file Import-Datei
     * @throws IOException falls was schiefgelaufen ist
     * @since 0.2
     */
    public void importFrom(final File file) throws IOException {
        importFrom(file, Config.DEFAULT_ENCODING);
    }

    /**
     * Importieren einer Datei.
     *
     * @param file     Import-Datei
     * @param encoding z.B. "ISO-8859-1"
     * @throws IOException falls was schiefgelaufen ist
     * @since 1.0
     */
    public void importFrom(final File file, final String encoding) throws IOException {
        importFrom(file, Charset.forName(encoding));
    }

    /**
     * Importieren einer Datei.
     *
     * @param file     Import-Datei
     * @param encoding z.B. "ISO-8859-1"
     * @throws IOException falls was schiefgelaufen ist
     * @since 1.0
     */
    public void importFrom(final File file, final Charset encoding) throws IOException {
        try (Reader reader = new InputStreamReader(new FileInputStream(file), encoding)) {
            this.importFrom(reader);
        }
    }

    /**
     * Fasst benachbarte Saetze mit Luecken zusammen, sofern es sinnvoll ist.
     * So kann z.B. folgende Reihenfolge in den Datensaetzen vorkommen:
     * <pre>
     *     0220.010.13.1 Teildatensatz 1
     *     0221.010.13.1 Teildatensatz 1
     *     0220.010.13.1 Teildatensatz 2
     * </pre>
     * Logisch gehoeren Teildatensatz 1 und 2 von Satzart 0220.010.13.1
     * zusammen. Gemaess den FAQ des GDVs ist es wohl zulaessig, dass
     * Teildatensatz 1 von Satzart 0221.xxx dazwischen stehen darf. Daher
     * fasst die pack-Methode dieses getrennten Teildatensaetze wieder
     * zusammen.
     * <p>
     * Diese Version wurde mit Issue #62 eingefuehrt. Naehere Infos siehe
     * https://github.com/oboehm/gdv.xport/issues/62.
     * </p>
     *
     * @return das Datenpaket selbst zur Weiterverarbeitung
     * @since 5.2
     */
    public Datenpaket pack() {
        for (int i = 0; i < datensaetze.size(); i++) {
            Datensatz ds = datensaetze.get(i);
            if (!ds.isComplete()) {
                Optional<Datensatz> next = findNextDatensatz(ds.getSatzTyp(), i+1);
                next.ifPresent(ds::mergeWith);
            }
        }
        removeEmptyDatensaetze();
        return this;
    }

    private void removeEmptyDatensaetze() {
        List<Datensatz> cleaned = new ArrayList<>();
        for (Datensatz ds : datensaetze) {
            if (ds.getNumberOfTeildatensaetze() > 0) {
                cleaned.add(ds);
            }
        }
        datensaetze.clear();
        datensaetze.addAll(cleaned);
    }

    private Optional<Datensatz> findNextDatensatz(SatzTyp satzTyp, int position) {
        for (int i = position; i < datensaetze.size(); i++) {
            Datensatz ds = datensaetze.get(i);
            if (satzTyp.equals(ds.getSatzTyp())) {
                return Optional.of(ds);
            }
        }
        return Optional.empty();
    }

    /**
     * Sets the erstellungsDatumVon im Vorsatz (Byte 70 - 77) (alle
     * Teildatensätze)
     *
     * @param d Erstellungsdatum von
     */
    public void setErstellungsDatumVon(final Datum d) {
        this.vorsatz.setErstellungsZeitraumVon(d);
    }

    /**
     * Gets the erstellungs datum von.
     *
     * @return Erstellungsdatum bis
     */
    public Datum getErstellungsDatumVon() {
        return this.vorsatz.getErstellungsZeitraumVon();
    }

    /**
     * Sets the erstellungs datum bis.
     *
     * @param d Erstellungsdatum bis
     */
    public void setErstellungsDatumBis(final Datum d) {
        this.vorsatz.setErstellungsZeitraumBis(d);
    }

    /**
     * Gets the erstellungs datum bis.
     *
     * @return Erstellungdatum bis
     */
    public Datum getErstellungsDatumBis() {
        return this.vorsatz.getErstellungsZeitraumBis();
    }

    /**
     * Sets the absender im Vorsatz (Byte 10 - 39) (alle Teildatensätze)
     *
     * @param absender neuer Absender
     */
    public void setAbsender(final String absender) {
        this.vorsatz.setAbsender(absender);
    }

    /**
     * Gets the absender im Vorsatz (Byte 10 - 39) (alle Teildatensätze)
     *
     * @return Absender
     */
    public String getAbsender() {
        return this.vorsatz.getAbsender();
    }

    /**
     * Sets the adressat.
     *
     * @param s Adressat
     */
    public void setAdressat(final String s) {
        this.vorsatz.setAdressat(s);
    }

    /**
     * Gets the adressat.
     *
     * @return Adressat
     */
    public String getAdressat() {
        return this.vorsatz.getAdressat();
    }

    /**
     * Sets the vermittler Um Geschaeftsstelle/Vermittler in Vorsatz (alle
     * Teildatensaetze) und Nachsatz setzen zu koennen.
     *
     * @param s Vermittler
     */
    public void setVermittler(final String s) {
        for (Satz satz : getAllSaetze()) {
            satz.setVermittler(s);
        }
    }

    /**
     * Gets the vermittler.
     *
     * @return Vermittler
     */
    public String getVermittler() {
        String vermittler = this.vorsatz.getVermittler();
        assert vermittler.equals(this.nachsatz.getVermittler()) : vorsatz + " or " + nachsatz + " is corrupt";
        return vermittler;
    }

    /**
     * Aus Performance-Gruenden wird nicht auf die validate-Methode
     * zurueckgegriffen (die dauert zu lang).
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isValid() {
        if (!this.vorsatz.isValid()) {
            LOG.info(this.vorsatz + " is not valid");
            return false;
        }
        if (!this.nachsatz.isValid()) {
            LOG.info(this.nachsatz + " is not valid");
            return false;
        }
        for (Satz satz : this.datensaetze) {
            if (!satz.isValid()) {
                LOG.info(satz + " is not valid");
                return false;
            }
        }
        if (!this.validateFolgenummern().isEmpty()) {
            LOG.info("Folgenummern stimmen nicht");
            return false;
        }
        if (!this.validateVUNummer().isEmpty()) {
            LOG.info("VU-Nummer is not set / not valid");
            return false;
        }
        return true;
    }

    /**
     * Validiert die einzelnen Saetze (inkl. Vorsatz und Nachsatz).
     *
     * @return Liste der ConstraintViolations
     */
    public List<ConstraintViolation> validate() {
        return validate(config);
    }

    public List<ConstraintViolation> validate(Config validationConfig) {
        SatzValidator satzValidator = new SatzValidator(validationConfig);
        satzValidator.notice(this.vorsatz);
        for (Satz datensatz : this.datensaetze) {
            satzValidator.notice(datensatz);
        }
        satzValidator.notice(this.nachsatz);
        List<ConstraintViolation> violations = satzValidator.getViolations();
        violations.addAll(validateVUNummer());
        violations.addAll(this.nachsatz.validate(validationConfig));
        return violations;
    }

    private List<ConstraintViolation> validateVUNummer() {
        List<ConstraintViolation> violations = new ArrayList<>();
        if (Config.DUMMY_VU_NUMMER.equals(this.getVuNummer())) {
            ConstraintViolation cv =
                    new SimpleConstraintViolation("VU-Nummer is not set", this, Config.DUMMY_VU_NUMMER);
            violations.add(cv);
        }
        return violations;
    }

    /**
     * Fuer eine Versicherungsscheinnummer muss die Folgenummer immer mit 1
     * anfangen. Taucht dieser Versicherungsscheinnummer fuer den gleichen Satz
     * ein zweites Mal auf, muss die Folgenummer entsprechend erhoeht werden. Es
     * sei denn, es handelt sich doch noch um den gleichen Vertrag. Aber die
     * Nummern duerfen keine Spruenge machen - dies wird hier kontrolliert.
     *
     * @return eine Liste, die die verletzten Folgenummern enthaelt
     * @since 0.3
     */
    private List<ConstraintViolation> validateFolgenummern() {
        List<ConstraintViolation> violations = new ArrayList<>();
        Map<String, Integer> folgenummern = new HashMap<>();
        for (Datensatz datensatz : this.datensaetze) {
            String nr = datensatz.getVersicherungsscheinNummer().trim();
            String key = nr + datensatz.getSatzartFeld().getInhalt() + datensatz.getSparteFeld().getInhalt();
            Integer expected = folgenummern.computeIfAbsent(key, k -> 1);
            int folgenr = datensatz.getFolgenummer();
            if (folgenr == expected) {
                continue;
            }
            expected++;
            folgenummern.put(key, expected);
            if (folgenr != expected) {
                ConstraintViolation cv =
                        new SimpleConstraintViolation("falsche Folgenummer (erwartet: " + expected + ")", datensatz,
                                folgenr);
                violations.add(cv);
            }
        }
        return violations;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " for " + this.getVuNummer() + " with " + this.datensaetze.size() +
                "+2 (Daten-)Saetze";
    }

    private void setNachsatzSummenAus0200(Datensatz datensatz) {
        Betrag beitrag = datensatz.getFeld(Bezeichner.GESAMTBEITRAG_IN_WAEHRUNGSEINHEITEN, Betrag.class);
        nachsatz.addGesamtBeitrag(beitrag.toBigDecimal());
    }

    private void setNachsatzSummenAus0400(Datensatz datensatz) {
        BetragMitVorzeichen betrag = datensatz.getFeld(Bezeichner.GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN,
                BetragMitVorzeichen.class);
        nachsatz.addGesamtBeitragBrutto(betrag.toBigDecimal());
        betrag = datensatz.getFeld(Bezeichner.GESAMTPROVISIONSBETRAG_IN_WAEHRUNGSEINHEITEN, BetragMitVorzeichen.class);
        nachsatz.addGesamtProvisionsBetrag(betrag.toBigDecimal());
    }

    private void setNachsatzSummenAus0500(Datensatz datensatz) {
        BetragMitVorzeichen betrag = datensatz.getFeld(Bezeichner.BETRAG_IN_WAEHRUNGSEINHEITEN_GEMAESS_ZAHLUNGSART,
                BetragMitVorzeichen.class);
        nachsatz.addVersicherungsLeistungen(betrag.toBigDecimal());
        betrag = datensatz.getFeld(Bezeichner.SCHADENBEARBEITUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN,
                BetragMitVorzeichen.class);
        nachsatz.addSchadenbearbeitungskosten(betrag.toBigDecimal());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Datenpaket other = (Datenpaket) obj;
        return Objects.equals(vorsatz, other.vorsatz) && isEquals(datensaetze, other.datensaetze) && Objects.equals(nachsatz, other.nachsatz);
    }

    private static boolean isEquals(List<Datensatz> d1, List<Datensatz> d2) {
        if (d1.size() != d2.size()) {
            return false;
        }
        for (int i = 0; i < d1.size(); i++) {
            if (!d1.get(i).equals(d2.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return datensaetze.size();
    }

    /**
     * Damit kann das Datenpaket selbst als Listener beim
     * {@link DatenpaketStreamer} registriert werden.
     *
     * @param satz der importierte Satz
     * @since 5.3
     */
    @Override
    public void notice(Satz satz) {
        try {
            if (satz.getSatzart() == 1) {
                LOG.info("Vorsatz {} wurde erkannt - {} wird zurueckgesetzt.", satz,this);
                this.datensaetze.clear();
                this.vorsatz.importFrom(satz.toLongString());
            } else if (satz.getSatzart() == 9999) {
                this.nachsatz.importFrom(satz.toLongString());
            } else {
                add((Datensatz) satz);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Import-Fehler in " + satz, ex);
        }
    }

}
