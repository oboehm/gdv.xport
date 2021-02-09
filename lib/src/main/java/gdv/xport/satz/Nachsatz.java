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

import gdv.xport.feld.*;
import gdv.xport.satz.feld.Feld9999;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzTyp;
import gdv.xport.util.XmlSatzFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;

import static gdv.xport.feld.Bezeichner.*;

/**
 * Dies ist der letzte Satz, der Nachsatz eben.
 * <p>
 * Fuer den einfacheren Umgang mit den einzelnen Betraegen, die hier im
 * Nachsatz zusammengefasst werden, sind jeweils Betrag und Vorzeichen
 * zusammengefasst und werden als {@link BetragMitVorzeichen} zurueckgegeben.
 * Ist man nur am Betrag oder Vorzeichen interessiert, kann man sich das
 * dann ueber {@link BetragMitVorzeichen#getBetrag()} und
 * {@link BetragMitVorzeichen#getVorzeichen()} abholen.
 * </p>
 *
 * @author oliver
 * @since 05.10.2009
 */
public final class Nachsatz extends Satz {

    private static final Logger LOG = LogManager.getLogger(Nachsatz.class);
    private static final Datensatz satz9999 = SatzFactory.getDatensatz(SatzTyp.of("9999"));

    /**
     * Default-Constructor.
     */
    public Nachsatz() {
        this(XmlSatzFactory.getInstance());
    }

    /**
     * Ueber die mitgegebene Factory wird der Nachsatz genauso aufgebaut, wie
     * die {@link XmlSatzFactory} als Vorlage liefert.
     *
     * @param factory sollte die Vorlage fuer den Nachsatz liefern.
     * @since 5.0
     */
    public Nachsatz(XmlSatzFactory factory) {
        this(factory.getDatensatz(SatzTyp.of("9999")));
    }

    private Nachsatz(Satz vorlage) {
        super(vorlage, vorlage.cloneTeildatensaetze());
    }

    /**
     * Erzeugt einen Nachsatz mit dem angegebenen Inhalt.
     * <p>
     * Anmerkung: Die urspruengliche Implementierung als Konstruktor wurde
     * in eine statische of()-Methode umgewandelt, da Satz(String)
     * semantisch eine andere Bedeutung hat - dort repraesentiert der
     * uebergebene Parameter die Satzart, hier den kompletten Inhalt.
     * </p>
     *
     * @param content Inhalt des Nachsatz
     * @return frisch kreierten Nachsatz
     * @since 5.0
     */
    public static Nachsatz of(final String content) {
        Nachsatz nachsatz = new Nachsatz();
        try {
            nachsatz.importFrom(content);
            LOG.debug("{} created from \"{}\"", nachsatz, content);
            return nachsatz;
        } catch (IOException ioe) {
            throw new IllegalArgumentException("argument too short", ioe);
        }
    }

    /**
     * Dies ist der Copy-Constructor, mit dem man einen bestehenden Nachsatz
     * kopieren kann.
     *
     * @param other der originale Nachsatz
     * @since 5.0
     */
    public Nachsatz(final Nachsatz other) {
        super(other, other.cloneTeildatensaetze());
    }

    /**
     * Setzt die Anzahl der Saetze.
     *
     * @param n Anzahl der eingeschlossenen Saetze
     */
    public void setAnzahlSaetze(final int n) {
        this.set(Bezeichner.ANZAHL_SAETZE, n);
    }

    /**
     * @return Anzahl der eingeschlossenen Saetze
     */
    public int getAnzahlSaetze() {
        return Integer.parseInt(this.getFeld(Bezeichner.ANZAHL_SAETZE)
                                    .getInhalt()
                                    .trim());
    }

    /**
     * Anzahl der Saetze um 1 erhoehen.
     */
    public void increaseAnzahlSaetze() {
        this.setAnzahlSaetze(this.getAnzahlSaetze() + 1);
    }

    /**
     * @param s Vermittler
     */
    public void setVermittler(final String s) {
        this.set(Bezeichner.GESCHAEFTSSTELLE_VERMITTLER, s);
    }

    /**
     * @return Vermittler
     */
    public String getVermittler() {
        return this.getFeld(Bezeichner.GESCHAEFTSSTELLE_VERMITTLER)
                   .getInhalt()
                   .trim();
    }

    /**
     * Setzt den Gesamtbeitrag.
     *
     * @param strBeitrag der neue Gesamtbeitrag
     * @since 5.0
     */
    public void setGesamtBeitrag(final String strBeitrag) {
        this.set(Bezeichner.GESAMTBEITRAG, strBeitrag);
    }

    /**
     * Setzt den Gesamtbeitrag.
     *
     * @param beitrag der neue Gesamtbeitrag
     * @deprecated durch {@link #setGesamtBeitrag(BigDecimal)} abgeloest
     */
    @Deprecated
    public void setGesamtBeitrag(final double beitrag) {
        this.setGesamtBeitrag(BigDecimal.valueOf(beitrag));
    }

    /**
     * Setzt den Gesamtbeitrag.
     *
     * @param beitrag der neue Gesamtbeitrag
     * @since 5.0
     */
    public void setGesamtBeitrag(final BigDecimal beitrag) {
        this.set(Bezeichner.GESAMTBEITRAG, beitrag.movePointRight(2).toString());
    }

    /**
     * Erhoeht den Gesamtbeitrag (Feld 4)
     *
     * @param beitrag neuer Summand fuer Gesamtbeitrag (in Cents)
     * @return aufaddierte Summe
     * @since 5.0
     */
    public BigDecimal addGesamtBeitrag(final BigDecimal beitrag) {
        BigDecimal summe = getGesamtBeitrag().add(beitrag);
        setGesamtBeitrag(summe);
        return summe;
    }

    /**
     * Diese Methode liefert den Gesamt-Beitrag als {@link Betrag} und nicht
     * als String zurueck, um die Kompatibilitaet mit v4 zu wahren.
     *
     * @return Gesamtbeitrag als Betrag
     */
    public Betrag getGesamtBeitrag() {
        return Betrag.of(this.getFeld(Bezeichner.GESAMTBEITRAG));
    }

    /**
     * Setzt den Brutto-Gesamtbeitrag, inklusive dem Vorzeichen.
     * <p>
     * TODO: wird in v6 entfernt werden.
     * </p>
     *
     * @param beitrag neuer Gesamtbeitrag (Brutto)
     * @deprecated durch {@link #setGesamtBeitragBruttoMitVorzeichen(BigDecimal)} abgeloest
     */
    @Deprecated
    public void setGesamtBeitragBrutto(final double beitrag) {
        this.setGesamtBeitragBruttoMitVorzeichen(BigDecimal.valueOf(beitrag));
    }

    /**
     * Setzt den Gesamtbeitrag-Brutto (Inkasso, Feld 5).
     *
     * @param beitrag neuer Gesamtbeitrag (Brutto)
     * @since 5.0
     */
    public void setGesamtBeitragBruttoMitVorzeichen(final BigDecimal beitrag) {
        BetragMitVorzeichen bmv = getGesamtBeitragBruttoMitVorzeichen();
        bmv.setInhalt(beitrag);
        setGesamtBeitragBrutto(bmv.getBetrag().getInhalt());
        setVorzeichenGesamtbeitragBrutto(Character.toString(bmv.getVorzeichen()));
    }

    /**
     * Setzt den Gesamtbeitrag-Brutto(Inkasso) (Feld 5)
     *
     * @param strBeitrag neuer Gesamtbeitrag-Brutto(Inkasso)
     * @since 5.0
     */
    public void setGesamtBeitragBrutto(final String strBeitrag) {
        this.getTeildatensatz(1)
            .getFeld(5)
            .setInhalt(strBeitrag);
    }

    /**
     * Erhoeht den Gesamtbeitrag-Brutto(Inkasso) (Feld 5 und Feld 6)
     *
     * @param beitrag neuer Summand fuer Gesamtbeitrag-Brutto(Inkasso)
     * @return aufaddierte Summe
     * @since 5.0
     */
    public BigDecimal addGesamtBeitragBrutto(final BigDecimal beitrag) {
        BigDecimal summe = getGesamtBeitragBruttoMitVorzeichen().add(beitrag);
        setGesamtBeitragBruttoMitVorzeichen(summe);
        return summe;
    }

    /**
     * Diese Methode wird kuenftig nur den Betragsteil zurueckliefern und damit
     * auch eine andere Semantik bekommen. Bis dahin ist diese Methode aber
     * deprecated.
     * <p>
     * TODO: Wird mit v6 entfernt oder nur den Betragsteil zurueckliefern.
     * </p>
     *
     * @return Gesamtbeitrag-Brutto(Inkasso) (Feld 5)
     * @deprecated wurde durch {@link #getGesamtBeitragBruttoMitVorzeichen()} ersetzt
     */
    @Deprecated
    public BetragMitVorzeichen getGesamtBeitragBrutto() {
        return getGesamtBeitragBruttoMitVorzeichen();
    }

    /**
     * Diese Methode ersetzt die alte {@link #getGesamtBeitragBrutto()}.
     *
     * @return Gesamtbeitrag-Brutto(Inkasso) (Feld 5)
     * @since 5.0
     */
    public BetragMitVorzeichen getGesamtBeitragBruttoMitVorzeichen() {
        NumFeld brutto = (NumFeld) getFeld(GESAMTBEITRAG_BRUTTO);
        AlphaNumFeld vorzeichen = (AlphaNumFeld) getFeld(VORZEICHEN);
        return BetragMitVorzeichen.of(brutto, vorzeichen);
    }

    /**
     * Setzt das Vorzeichen Gesamtbeitrag-Brutto(Inkasso) (Feld 6)
     *
     * @param strVorzeichen Vorzeichen
     * @since 5.0
     */
    public void setVorzeichenGesamtbeitragBrutto(final String strVorzeichen) {
        if (("+").equalsIgnoreCase(strVorzeichen) || ("-").equalsIgnoreCase(strVorzeichen))
            this.getTeildatensatz(1).getFeld(6).setInhalt(strVorzeichen);
        else throw new IllegalArgumentException(strVorzeichen + ": kein Vorzeichen");
    }

    /**
     * Setzt den Gesamtprovisions-Betrag (Feld 7).
     *
     * @param strBeitrag neuer Gesamtprovisions-Betrag
     * @since 5.0
     */
    public void setGesamtProvisionsBetrag(final String strBeitrag) {
        this.getTeildatensatz(1)
            .getFeld(7)
            .setInhalt(strBeitrag);
    }

    /**
     * Setzt den Gesamtprovisions-Betrag (Feld 7)
     *
     * @param betrag neuer Gesamtprovisions-Betrag
     * @since 5.0
     */
    public void setGesamtProvisionsBetragMitVorzeichen(final BigDecimal betrag) {
        BetragMitVorzeichen bmv = getGesamtProvisionsBetragMitVorzeichen();
        bmv.setInhalt(betrag);
        setGesamtProvisionsBetrag(bmv.getBetrag().getInhalt());
        setVorzeichenGesamtProvisionsBetrag(Character.toString(bmv.getVorzeichen()));
    }

    /**
     * Erhoeht den Gesamtprovisions-Betrag (Feld 7 und Feld 8)
     *
     * @param betrag neuer Summand fuer Gesamtprovisions-Betrag
     * @return Summe
     * @since 5.0
     */
    public BigDecimal addGesamtProvisionsBetrag(final BigDecimal betrag) {
        BigDecimal summe = getGesamtProvisionsBetragMitVorzeichen().add(betrag);
        this.setGesamtProvisionsBetragMitVorzeichen(summe);
        return summe;
    }

    /**
     * Liefert den Gesamt-Provisionsbetrag, inklusive Vorzeichen.
     *
     * @return Gesamtprovisions-Betrag (Feld 7)
     * @since 5.0
     */
    public BetragMitVorzeichen getGesamtProvisionsBetragMitVorzeichen() {
        NumFeld brutto = (NumFeld) getFeld(GESAMTPROVISIONSBETRAG);
        AlphaNumFeld vorzeichen = (AlphaNumFeld) getFeld(VORZEICHEN2);
        return BetragMitVorzeichen.of(brutto, vorzeichen);
    }

    /**
     * Setzt das Vorzeichen Gesamtprovisions-Betrag (Feld 8).
     *
     * @param strVorzeichen Vorzeichen
     * @since 5.0
     */
    public void setVorzeichenGesamtProvisionsBetrag(final String strVorzeichen) {
        if (("+").equalsIgnoreCase(strVorzeichen) || ("-").equalsIgnoreCase(
                strVorzeichen))
            this.getTeildatensatz(1)
                .getFeld(8)
                .setInhalt(strVorzeichen);
        else
            throw new IllegalArgumentException(strVorzeichen + ": kein Vorzeichen");
    }

    /**
     * Durch {@link #setVersicherungsLeistungenMitVorzeichen(BigDecimal)}
     * ersetzt.
     * <p>
     * TODO: Wird mit v6 entfernt.
     * </p>
     *
     * @param betrag neuer Betrag
     * @deprecated durch {@link #setVersicherungsLeistungenMitVorzeichen(BigDecimal)} ersetzt
     */
    @Deprecated
    public void setVersicherungsLeistungen(final double betrag) {
        setVersicherungsLeistungenMitVorzeichen(BigDecimal.valueOf(betrag));
    }

    /**
     * Ersetzt {@link #setVersicherungsLeistungen(double)}.
     *
     * @param betrag neuer Betrag
     * @since 5.0
     */
    public void setVersicherungsLeistungenMitVorzeichen(final BigDecimal betrag) {
        BetragMitVorzeichen bmv = getVersicherungsLeistungenMitVorzeichen();
        bmv.setInhalt(betrag);
        setVersicherungsLeistungen(bmv.getBetrag().getInhalt());
        setVorzeichenVersicherungsLeistungen(Character.toString(bmv.getVorzeichen()));
    }

    /**
     * Diese Methode wird kuenftig nur den Betragsteil zurueckliefern und damit
     * auch eine andere Semantik bekommen. Bis dahin ist diese Methode aber
     * deprecated.
     * <p>
     * TODO: Wird mit v6 entfernt oder liefert Betrag zurueck.
     * </p>
     *
     * @return VersicherungsLeistungen (Feld 9)
     * @deprecated durch {@link #getVersicherungsLeistungenMitVorzeichen()} ersetzt
     */
    @Deprecated
    public BetragMitVorzeichen getVersicherungsLeistungen() {
        return this.getVersicherungsLeistungenMitVorzeichen();
    }

    /**
     * Setzt die Versicherungsleistungen (Feld 9)
     *
     * @param strBeitrag neue Versicherungsleitungen
     * @since 5.0
     */
    public void setVersicherungsLeistungen(final String strBeitrag) {
        this.getTeildatensatz(1).getFeld(9).setInhalt(strBeitrag);
    }

    /**
     * Erhoeht die VersicherungsLeistungen (Feld 9 und Feld 10)
     *
     * @param betrag neuer Summand fuer Versicherungsleitungen
     * @return Summe
     * @since 5.0
     */
    public BigDecimal addVersicherungsLeistungen(final BigDecimal betrag) {
        BigDecimal summe = getVersicherungsLeistungenMitVorzeichen().add(betrag);
        this.setVersicherungsLeistungenMitVorzeichen(summe);
        return summe;
    }

    /**
     * Liefert die Versicherungsleistungen, inklusiv Vorzeichen.
     *
     * @return VersicherungsLeistungen (Feld 9)
     * @since 5.0
     */
    public BetragMitVorzeichen getVersicherungsLeistungenMitVorzeichen() {
        NumFeld brutto = (NumFeld) getFeld(VERSICHERUNGSLEISTUNGEN);
        AlphaNumFeld vorzeichen = (AlphaNumFeld) getFeld(VORZEICHEN3);
        return BetragMitVorzeichen.of(brutto, vorzeichen);
    }

    /**
     * Setzt das Vorzeichen VersicherungsLeistungen (Feld 10)
     *
     * @param strVorzeichen Vorzeichen
     * @since 5.0
     */
    public void setVorzeichenVersicherungsLeistungen(final String strVorzeichen) {
        if (("+").equalsIgnoreCase(strVorzeichen) || ("-").equalsIgnoreCase(strVorzeichen))
            this.getTeildatensatz(1).getFeld(10).setInhalt(strVorzeichen);
        else throw new IllegalArgumentException(strVorzeichen + ": kein Vorzeichen");
    }

    /**
     * Setzt die Schadenbearbeitungskosten.
     * <p>
     * TODO: Wird mit v6 entfernt.
     * </p>
     *
     * @param kosten  Kosten der Schadensbearbeitung
     * @deprecated durch {@link #setSchadenbearbeitungskostenMitVorzeichen(BigDecimal)} abgelöst
     */
    public void setSchadenbearbeitungsKosten(final double kosten) {
        setSchadenbearbeitungskostenMitVorzeichen(BigDecimal.valueOf(kosten));
    }

    /**
     * Setzt die Schadenbearbeitungskosten.
     *
     * @param beitrag neuer Gesamtbeitrag (Brutto)
     * @since 5.0
     */
    public void setSchadenbearbeitungskostenMitVorzeichen(final BigDecimal beitrag) {
        BetragMitVorzeichen bmv = getSchadenbearbeitungskostenMitVorzeichen();
        bmv.setInhalt(beitrag);
        setSchadenbearbeitungskosten(bmv.getBetrag().getInhalt());
        setVorzeichenSchadenbearbeitungskosten(Character.toString(bmv.getVorzeichen()));
    }

    /**
     * Setzt die Schadenbearbeitungskosten (Feld 11).
     *
     * @param strBeitrag neue Schadenbearbeitungskosten
     * @since 5.0
     */
    public void setSchadenbearbeitungskosten(final String strBeitrag) {
        this.getTeildatensatz(1).getFeld(11).setInhalt(strBeitrag);
    }

    /**
     * Erhoeht die Schadenbearbeitungskosten (Feld 11 und Feld 12)
     *
     * @param betrag neuer Summand fuer Schadenbearbeitungskosten
     * @return Summe
     * @since 5.0
     */
    public BigDecimal addSchadenbearbeitungskosten(final BigDecimal betrag) {
        BigDecimal summe = getSchadenbearbeitungskostenMitVorzeichen().add(betrag);
        this.setSchadenbearbeitungskostenMitVorzeichen(summe);
        return summe;
    }

    /**
     * Liefert die Schandenbearbeitunskosten.
     * <p>
     * TODO: Wird mit v6 entfernt.
     * </p>
     *
     * @return Kosten der Schadensbearbeitung
     * @deprecated durch {@link #getSchadenbearbeitungskostenMitVorzeichen()} abgeloest
     */
    @Deprecated
    public BetragMitVorzeichen getSchadenbearbeitungsKosten() {
        return getSchadenbearbeitungskostenMitVorzeichen();
    }

    /**
     * Liefert die Schandenbearbeitunskosten.
     *
     * @return Schadenbearbeitungskosten mit Vorzeichen (Feld 11+12)
     * @since 5.0
     */
    public BetragMitVorzeichen getSchadenbearbeitungskostenMitVorzeichen() {
        NumFeld betrag = (NumFeld) getFeld(SCHADENBEARBEITUNGSKOSTEN);
        AlphaNumFeld vorzeichen = (AlphaNumFeld) getFeld(VORZEICHEN4);
        return BetragMitVorzeichen.of(betrag, vorzeichen);
    }

    /**
     * Setzt das Vorzeichen Schadenbearbeitungskosten (Feld 12)
     *
     * @param strVorzeichen Vorzeichen
     */
    public void setVorzeichenSchadenbearbeitungskosten(final String strVorzeichen) {
        if (("+").equalsIgnoreCase(strVorzeichen) || ("-").equalsIgnoreCase(strVorzeichen))
            this.getTeildatensatz(1).getFeld(12).setInhalt(strVorzeichen);
        else throw new IllegalArgumentException(strVorzeichen + ": kein Vorzeichen");
    }

    /**
     * Liefert das gewuenschte Feld. Allerdings wird nur der Name des Feldes
     * benutzt, um das Feld zu bestimmen. Dazu werden auch die Konstanten in
     * {@link gdv.xport.feld.Bezeichner} verwendet.
     * <p>
     * TODO: Wird in v6 entfernt werden.
     * </p>
     *
     * @param feld gewuenschtes Feld-Element
     * @return das gesuchte Feld
     * @throws IllegalArgumentException falls es das Feld nicht gibt
     * @deprecated inzwischen durch {@link #getFeld(Bezeichner)} abgeloest
     */
    @Deprecated
    @Override
    public Feld getFeld(final Enum feld) throws IllegalArgumentException {
        if (feld instanceof Feld9999) {
            throw new UnsupportedOperationException("Vorzeichen kann jetzt ueber getVorzeichen...() ermitelt werden");
        } else {
            return super.getFeld(feld);
        }
    }

}
