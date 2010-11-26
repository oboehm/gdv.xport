/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 23.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport;

import static gdv.xport.feld.Bezeichner.*;
import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.satz.*;
import gdv.xport.util.*;

import java.io.*;
import java.net.*;
import java.util.*;

import net.sf.oval.*;
import net.sf.oval.constraint.AssertCheck;
import net.sf.oval.context.ClassContext;

import org.apache.commons.logging.*;

/**
 * @author oliver
 * @since 23.10.2009
 * @version $Revision$
 *
 */
public final class Datenpaket {

    private static final Log log = LogFactory.getLog(Datenpaket.class);
    private final Vorsatz vorsatz = new Vorsatz();
    private List<Datensatz> datensaetze = new ArrayList<Datensatz>();
    private final Nachsatz nachsatz = new Nachsatz();

    /**
     * Wenn man den Default-Konstruktor verwendet, sollte man vorher die
     * VU-Nummer konfiguriert haben.
     *
     * @see Config#getVUNummer()
     */
    public Datenpaket() {
        this(Config.getVUNummer().getInhalt());
    }

    /**
     * Falls die VU-Nummer noch nicht konfiguriert ist, kann man zu diesem
     * Konstruktor greifen.
     *
     * @since 0.3
     * @param vuNummer die Nummer des Versicherungsunternehmens (VU)
     */
    public Datenpaket(final String vuNummer) {
        Datum heute = Datum.heute();
        this.setErstellungsDatumVon(heute);
        this.setErstellungsDatumBis(heute);
        this.setVuNummer(vuNummer);
        this.setAbsender(vuNummer);
        log.debug(this + " created.");
    }

    /**
     * Um die VU-Nummer setzen zu koennen.
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
     * @since 0.3
     * @return VU-Nummer aus dem Vorsatz
     */
    public String getVuNummer() {
        return this.vorsatz.getVuNummer();
    }

    /**
     * @return the datensaetze
     */
    public List<Datensatz> getDatensaetze() {
        return datensaetze;
    }

    /**
     * @param datensaetze the datensaetze to set
     */
    public void setDatensaetze(final List<Datensatz> datensaetze) {
        this.datensaetze = datensaetze;
    }

    /**
     * @return the vorsatz
     */
    public Vorsatz getVorsatz() {
        return vorsatz;
    }

    /**
     * @return the nachsatz
     */
    public Nachsatz getNachsatz() {
        return nachsatz;
    }

    /**
     * @param datensatz Datensatz, der hinzugefuegt werden soll
     */
    public void add(final Datensatz datensatz) {
        datensaetze.add(datensatz);
        nachsatz.increaseAnzahlSaetze();
    }

    /**
     * @param file Datei, in die exportiert werden soll
     * @throws IOException falls was schiefgelaufen ist (z.B. Platte voll)
     */
    public void export(final File file) throws IOException {
        Writer writer = new FileWriter(file);
        try {
            export(writer);
        } finally {
            writer.close();
        }
    }

    /**
     * Falls wir einen Stream haben, koennen wir diese Methode benutzen.
     *
     * @since 0.3
     * @param ostream z.B. System.out
     * @throws IOException falls was schiefgelaufen ist
     */
    public void export(final OutputStream ostream) throws IOException {
       Writer writer = new OutputStreamWriter(ostream);
       export(writer);
       writer.flush();
       ostream.flush();
    }

    /**
     * @param writer wird zum Export verwendet
     * @throws IOException falls was schiefgelaufen ist
     */
    public void export(final Writer writer) throws IOException {
        vorsatz.export(writer);
        for (Iterator<Datensatz> iterator = datensaetze.iterator(); iterator.hasNext();) {
            Datensatz datensatz = iterator.next();
            datensatz.export(writer);
        }
        nachsatz.export(writer);
        log.info(datensaetze.size() + " Datensaetze exported.");
    }

    /**
     * @since 0.3
     * @param url z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @throws IOException wenn z.B. das Netz weg ist
     */
    public void importFrom(final URL url) throws IOException {
        URLReader urlReader = new URLReader(url);
        String content = urlReader.read();
        importFrom(content);
    }

    /**
     * @since 0.3
     * @param content Inhalt der eingelesen wird
     * @throws IOException sollte eigentlich nicht vorkommen
     */
    public void importFrom(final String content) throws IOException {
        Reader reader = new StringReader(content);
        importFrom(reader);
        reader.close();
    }

    /**
     * @param istream z.B. Sytem.in
     * @throws IOException falls es Fehler beim Lesen gibt
     */
    public void importFrom(final InputStream istream) throws IOException {
        Reader reader = new InputStreamReader(istream, Config.DEFAULT_ENCODING);
        importFrom(reader);
    }

    /**
     * @param reader hiervon wird importiert
     * @throws IOException falls was schiefgelaufen ist
     */
    public void importFrom(final Reader reader) throws IOException {
        importFrom(new PushbackReader(reader, 14));
    }

    /**
     * Der hier verwendete PushbackReader wird benoetigt, damit die gelesene
     * Satzart und Sparte wieder zurueckgesetllt werden kann.
     *
     * @param reader PushbackReader mit einem Puffer von mind. 14 Zeichen
     * @throws IOException falls was schiefgelaufen ist
     */
    public void importFrom(final PushbackReader reader) throws IOException {
        this.vorsatz.importFrom(reader);
        while(true) {
            int satzart = Satz.readSatzart(reader);
            log.debug("reading Satzart " + satzart + "...");
            if (satzart == 9999) {
                break;
            }
            int sparte = Datensatz.readSparte(reader);
            Datensatz satz = SatzFactory.getDatensatz(satzart, sparte);
            satz.importFrom(reader);
            this.add(satz);
        }
        this.nachsatz.importFrom(reader);
    }

    /**
     * Importieren einer Datei.
     *
     * @since 0.2
     * @param file Import-Datei
     * @throws IOException falls was schiefgelaufen ist
     */
    public void importFrom(final File file) throws IOException {
        Reader reader = new FileReader(file);
        try {
            this.importFrom(reader);
        } finally {
            reader.close();
        }
    }

    /**
     * @param d Erstellungsdatum von
     */
    public void setErstellungsDatumVon(final Datum d) {
        Datum von = this.getErstellungsDatumVon();
        von.setInhalt(d);
    }

    /**
     * @return Erstellungsdatum bis
     */
    public Datum getErstellungsDatumVon() {
        return (Datum) this.vorsatz.getFeld(ERSTELLUNGSDATUM_ZEITRAUM_VOM);
    }

    /**
     * @param d Erstellungsdatum bis
     */
    public void setErstellungsDatumBis(final Datum d) {
        Datum bis = this.getErstellungsDatumBis();
        bis.setInhalt(d);
    }

    /**
     * @return Erstellungdatum bis
     */
    public Datum getErstellungsDatumBis() {
        return (Datum) this.vorsatz.getFeld(ERSTELLUNGSDATUM_ZEITRAUM_BIS);
    }

    /**
     * @param s neuer Absender
     */
    public void setAbsender(final String s) {
        Feld absender = this.getAbsenderFeld();
        absender.setInhalt(s);
    }

    /**
     * @return Absender
     */
    public String getAbsender() {
        return this.getAbsenderFeld().getInhalt().trim();
    }

    /**
     * @return das komplette Absender-Feld
     */
    private Feld getAbsenderFeld() {
        return this.vorsatz.getFeld(ABSENDER);
    }

    /**
     * @param s Adressat
     */
    public void setAdressat(final String s) {
        Feld adressat = this.getAdressatFeld();
        adressat.setInhalt(s);
    }

    /**
     * @return Adressat
     */
    public String getAdressat() {
        return this.getAdressatFeld().getInhalt().trim();
    }

    /**
     * @return das komplette Adressat-Feld
     */
    private Feld getAdressatFeld() {
        return this.vorsatz.getFeld(ADRESSAT);
    }

    /**
     * @param s Vermittler
     */
    public void setVermittler(final String s) {
        this.vorsatz.setVermittler(s);
        this.nachsatz.setVermittler(s);
    }

    /**
     * @return Vermittler
     */
    public String getVermittler() {
        String vermittler = this.vorsatz.getVermittler();
        assert vermittler.equals(this.nachsatz.getVermittler()) : vorsatz
                + " or " + nachsatz + " is corrupt";
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
            log.info(this.vorsatz + " is not valid");
            return false;
        }
        if (!this.nachsatz.isValid()) {
            log.info(this.nachsatz + " is not valid");
            return false;
        }
        for (Datensatz satz : this.datensaetze) {
            if (!satz.isValid()) {
                log.info(satz + " is not valid");
                return false;
            }
        }
        if (this.validateFolgenummern().size() > 0) {
            log.info("Folgenummern stimmen nicht");
            return false;
        }
        if (this.validateVUNummer().size() > 0) {
            log.info("VU-Nummer is not set / not valid");
            return false;
        }
        return true;
    }

    /**
     * Validiert die einzelnen Saetze (inkl. Vorsatz und Nachsatz).
     *
     * @return the list< constraint violation>
     */
    public List<ConstraintViolation> validate() {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(this);
        violations.addAll(validateVUNummer());
        violations.addAll(this.vorsatz.validate());
        for (Datensatz datensatz : this.datensaetze) {
            violations.addAll(datensatz.validate());
        }
        violations.addAll(this.validateFolgenummern());
        violations.addAll(this.nachsatz.validate());
        return violations;
    }

    private List<ConstraintViolation> validateVUNummer() {
        List<ConstraintViolation> violations = new ArrayList<ConstraintViolation>();
        if (Config.DUMMY_VU_NUMMER.equals(this.getVuNummer())) {
            ConstraintViolation cv = new ConstraintViolation(new AssertCheck(),
                    "VU-Nummer is not set", this, Config.DUMMY_VU_NUMMER, new ClassContext(this
                            .getClass()));
            violations.add(cv);
        }
        return violations;
    }

    /**
     * Fuer eine Versicherungsscheinnummer muss die Folgenummer immer mit 1
     * anfangen. Taucht dieser Versicherungsscheinnummer fuer den gleichen Satz
     * ein zweites Mal auf, muss die Folgenummer entsprechend erhoeht werden.
     * Es sei denn, es handelt sich doch noch um den gleichen Vertrag.
     * Aber die Nummern duerfen keine Spruenge machen - dies wird hier
     * kontrolliert.
     *
     * @since 0.3
     * @return eine Liste, die die verletzten Folgenummern enthaelt
     */
    private List<ConstraintViolation> validateFolgenummern() {
        List<ConstraintViolation> violations = new ArrayList<ConstraintViolation>();
        Map<String, Integer> folgenummern = new HashMap<String, Integer>();
        for (Datensatz datensatz : this.datensaetze) {
            String nr = datensatz.getVersicherungsscheinNummer().trim();
            String key = nr + datensatz.getSatzartFeld().getInhalt()
                    + datensatz.getSparteFeld().getInhalt();
            Integer expected = folgenummern.get(key);
            if (expected == null) {
                expected = 1;
                folgenummern.put(key, expected);
            }
            int folgenr = datensatz.getFolgenummer();
            if (folgenr == expected) {
                continue;
            }
            expected++;
            folgenummern.put(key, expected);
            if (folgenr != expected) {
                ConstraintViolation cv = new ConstraintViolation(new AssertCheck(),
                        "falsche Folgenummer (erwartet: " + expected + ")", datensatz, folgenr,
                        new ClassContext(this.getClass()));
                violations.add(cv);
            }
        }
        return violations;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " for " + this.getVuNummer() + " with "
                + this.datensaetze.size() + "+2 (Daten-)Saetze";
    }

}

