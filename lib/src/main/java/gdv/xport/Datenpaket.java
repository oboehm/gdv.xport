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

import gdv.xport.config.Config;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.BetragMitVorzeichen;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Datum;
import gdv.xport.io.*;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Nachsatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Vorsatz;
import gdv.xport.satz.feld.FeldX;
import gdv.xport.satz.feld.common.TeildatensatzNummer;
import gdv.xport.satz.feld.common.WagnisartLeben;
import gdv.xport.satz.model.SatzX;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzTyp;
import gdv.xport.util.SimpleConstraintViolation;
import gdv.xport.util.URLReader;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
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
public final class Datenpaket {

    private static final Logger LOG = LogManager.getLogger(Datenpaket.class);
    private final Vorsatz vorsatz = new Vorsatz();
    private final List<Datensatz> datensaetze = new ArrayList<>();
    private Nachsatz nachsatz = new Nachsatz();

    /**
     * Wenn man den Default-Konstruktor verwendet, sollte man vorher die
     * VU-Nummer konfiguriert haben.
     *
     * @see Config#getVUNummer()
     */
    public Datenpaket() {
        this(Config.getVUNummer().getInhalt());
        this.vorsatz.setVersion(this.nachsatz);
    }

    /**
     * Falls die VU-Nummer noch nicht konfiguriert ist, kann man zu diesem
     * Konstruktor greifen.
     *
     * @param vuNummer die Nummer des Versicherungsunternehmens (VU)
     * @since 0.3
     */
    public Datenpaket(final String vuNummer) {
        this.vorsatz.setVersion(this.nachsatz);
        Datum heute = Datum.heute();
        this.setErstellungsDatumVon(heute);
        // Zeitraum-bis nicht vorbelegen ? - Warum, ist nur Default, falls nicht gesetzt wird
        this.setErstellungsDatumBis(heute);
        this.setVuNummer(vuNummer);
        // Absender nicht mit vuNummer vorbelegen! - Warum nicht? Ist das VU nicht der Absender?
        // this.setAbsender(vuNummer);
        LOG.debug(this + " created.");
    }

    /**
     * Um die VU-Nummer setzen zu koennen.
     *
     * @param vuNummer VU-Nummer (max. 5-stellig)
     */
    public void setVuNummer(final String vuNummer) {
        this.vorsatz.setVuNummer(vuNummer);
        for (Datensatz datensatz : this.datensaetze) {
            datensatz.setVuNummer(vuNummer);
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
     * Liefert eine Kopie(!) des internen Vorsatzes.
     * <p>
     * <b>Achtung:</b> Der Vorsatz wird intern durch das Datenpaket verwaltet und
     * darf nicht von aussen verändert werden.
     * </p>
     *
     * @return the vorsatz
     */
    public Vorsatz getVorsatz() {
        return new Vorsatz(this.vorsatz);
    }

    /**
     * Liefert eine Kopie(!) des internen Nachsatzes.
     * <p>
     * <b>Achtung:</b> Der Nachsatz wird intern durch das Datenpaket verwaltet und
     * darf nicht von aussen verändert werden.
     * </p>
     *
     * @return the nachsatz
     */
    public Nachsatz getNachsatz() {
        return new Nachsatz(this.nachsatz);
    }

    /**
     * Fuegt den uebergebenen Datensatz hinzu.<br/>
     * <b>Achtung:</b> Satzart 001 (Vorsatz) bzw. Satzart 9999 (Nachsatz) kann
     * nicht hinzugefuegt werden!
     *
     * @param datensatz Datensatz, der hinzugefuegt werden soll
     */
    public void add(final Datensatz datensatz) {
        if (("0001").equalsIgnoreCase(datensatz.getGdvSatzartName()) || ("9999")
                .equalsIgnoreCase(datensatz.getGdvSatzartName()))
            throw new IllegalArgumentException(("0001").equalsIgnoreCase(datensatz
                    .getGdvSatzartName()) ? "Einen Vorsatz gibt es bereits!"
                    : "Einen Nachsatz gibt es bereits!");
        datensaetze.add(datensatz);
        vorsatz.setVersion(datensatz);
        if (datensatz.getSatzTyp().equals(SatzTyp.of(200))) {
            setNachsatzSummenAus0200(datensatz);
        } else if (datensatz.getSatzTyp().equals(SatzTyp.of(400))) {
            setNachsatzSummenAus0400(datensatz);
        } else if (datensatz.getSatzTyp().equals(SatzTyp.of(500))) {
            setNachsatzSummenAus0500(datensatz);
        }
        nachsatz.setAnzahlSaetze(datensaetze.size());
    }

    /**
     * Export.
     *
     * @param file Datei, in die exportiert werden soll
     * @throws IOException falls was schiefgelaufen ist (z.B. Platte voll)
     */
    public void export(final File file) throws IOException {
        export(file, Charset.defaultCharset());
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
        LOG.info(datensaetze.size() + " Datensaetze exported.");
    }

    /**
     * Damit kann direkt ueber das Netz importiert werden. Gibt man eine
     * File-URL (oder File) an, kann man damit auch direkt aus einer Datei importieren.
     *
     * @param uri z.B.
     *            http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @throws IOException wenn z.B. das Netz weg ist
     * @since 3.0
     */
    public void importFrom(final URI uri) throws IOException {
        importFrom(uri.toURL());
    }

    /**
     * Damit kann direkt ueber das Netz importiert werden. Gibt man eine
     * File-URL (oder File) an, kann man damit auch direkt aus einer Datei importieren.
     *
     * @param url z.B.
     *            http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @throws IOException wenn z.B. das Netz weg ist
     * @since 0.3
     */
    public void importFrom(final URL url) throws IOException {
        URLReader urlReader = new URLReader(url);
        String content = urlReader.read();
        importFrom(content);
    }

    /**
     * Importiert direkt aus einem String.
     *
     * @param content Inhalt der eingelesen wird
     * @throws IOException sollte eigentlich nicht vorkommen
     * @since 0.3
     */
    public void importFrom(final String content) throws IOException {
        Reader reader = new StringReader(content);
        importFrom(reader);
        reader.close();
    }

    /**
     * Import from.
     *
     * @param istream z.B. Sytem.in
     * @throws IOException falls es Fehler beim Lesen gibt
     */
    public void importFrom(final InputStream istream) throws IOException {
        Reader reader = new RecyclingInputStreamReader(istream, Config.DEFAULT_ENCODING);
        importFrom(reader);
    }

    /**
     * Import from.
     *
     * @param reader hiervon wird importiert
     * @throws IOException falls was schiefgelaufen ist
     */
    public void importFrom(final Reader reader) throws IOException {
        PushbackLineNumberReader lnr = new PushbackLineNumberReader(new RecordReader(reader), 256);
        try {
            importFrom(lnr);
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
     *
     * @param reader PushbackReader mit einem Puffer von mind. 14 Zeichen
     * @throws IOException falls was schief gelaufen ist
     */
    public void importFrom(final PushbackLineNumberReader reader) throws IOException {
        this.vorsatz.importFrom(reader);
        while (true) {
            Satz satz = importSatz(reader);
            if (satz.getSatzart() == 9999) {
                this.nachsatz = (Nachsatz) satz;
                break;
            }
            datensaetze.add((Datensatz) satz);
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
        int satzart = Satz.readSatzart(reader);
        LOG.debug("reading Satzart " + satzart + "...");
        if (satzart == 9999) {
            Nachsatz nachsatz = new Nachsatz();
            nachsatz.importFrom(reader);
            return nachsatz;
        } else {
            return importDatensatz(reader, satzart);
        }
    }

    private static Datensatz importDatensatz(final PushbackLineNumberReader reader, final int satzart)
            throws IOException {
        int sparte = Datensatz.readSparte(reader);
        SatzTyp satzTyp = SatzTyp.of(satzart, sparte);
        if (sparte == 10 && satzart > 210) {
            WagnisartLeben wagnisart = Datensatz.readWagnisart(reader);
            if (wagnisart != WagnisartLeben.NULL) {
                // wagnisart 0 hat immer ein Leerzeichen als
                // teildatenSatzmummer. Nur groesser 0
                // besitzt per Definition Werte.
                TeildatensatzNummer teildatensatzNummer = Datensatz.readTeildatensatzNummer(reader);
                satzTyp = SatzTyp.of(satzart, sparte, wagnisart.getCode(), teildatensatzNummer.getCode());
            } else {
                satzTyp = SatzTyp.of(satzart, sparte, wagnisart.getCode());
            }
        } else if (sparte == 20 && satzart == 220) {
            // Fuer 0220.020.x ist die Krankenfolgenummer zur Identifikation der Satzart noetig
            int krankenFolgeNr = Datensatz.readKrankenFolgeNr(reader);
            // Krankenfolgenummer nicht auslesbar -> Unbekannter Datensatz
            if (krankenFolgeNr == -1) {
                Datensatz satz = new SatzX(220, 20, FeldX.class);
                satz.importFrom(reader);
                return satz;
            }
            satzTyp = SatzTyp.of(satzart, sparte, krankenFolgeNr);
        }  else if (sparte == 580 && satzart == 220) {
            // Fuer 0220.580.x ist die BausparArt zur Identifikation der Satzart
            // noetig
            int bausparArt = Datensatz.readBausparenArt(reader);
            // BausparenArt nicht auslesbar -> Unbekannter Datensatz
            if (bausparArt == -1) {
                Datensatz satz = new SatzX(220, 20, FeldX.class);
                satz.importFrom(reader);
                return satz;
            } else if (bausparArt == 0) {
                bausparArt = 1;
            }
            satzTyp = SatzTyp.of(satzart, sparte, bausparArt);
        }

        Datensatz satz = SatzFactory.getDatensatz(satzTyp);
        satz.importFrom(reader);
        return satz;
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
        this.vorsatz.setVermittler(s);
        this.nachsatz.setVermittler(s);
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
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(this);
        violations.addAll(validateVUNummer());
        violations.addAll(this.vorsatz.validate());
        for (Satz datensatz : this.datensaetze) {
            violations.addAll(datensatz.validate());
        }
        violations.addAll(this.validateFolgenummern());
        violations.addAll(this.nachsatz.validate());
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

}
