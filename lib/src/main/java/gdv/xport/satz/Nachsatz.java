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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static gdv.xport.feld.Bezeichner.*;

/**
 * Dies ist der letzte Satz, der Nachsatz eben.
 * <p>
 * Da Vorsatz und Nachsatz von der Datenpaket-Klasse benoetigt werden, habe
 * ich das "deprecated" wieder entfernt (24-Nov-2012, oboehm).
 * </p>
 *
 * @author oliver
 * @since 05.10.2009
 */
public final class Nachsatz extends Satz {

    private static final Logger LOG = LogManager.getLogger(Nachsatz.class);
    private static final Datensatz satz9999 = SatzFactory.getDatensatz(SatzTyp.of("9999"));

    private final BetragMitVorzeichen schadenbearbeitungsKosten = new BetragMitVorzeichen(SCHADENBEARBEITUNGSKOSTEN, 15,
            85);

    /**
     * Default-Constructor.
     */
    public Nachsatz() {
        super(satz9999, satz9999.cloneTeildatensaetze());
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
     * @param numFeldBeitrag der neue Gesamtbeitrag
     * @since 5.0
     */
    public void setGesamtBeitrag(final NumFeld numFeldBeitrag) {
        this.set(Bezeichner.GESAMTBEITRAG, numFeldBeitrag.getInhalt());
    }

    /**
     * Erhoeht den Gesamtbeitrag (Feld 4)
     *
     * @param beitrag neuer Summand fuer Gesamtbeitrag (in Cents)
     * @since 5.0
     */
    public void addGesamtBeitrag(final long beitrag) {
        Long beitragNach;

        try {
            beitragNach = Long.parseLong(this.getTeildatensatz(1)
                                             .getFeld(4)
                                             .getInhalt()
                                             .trim());
        } catch (NumberFormatException e) {
            beitragNach = 0L;
        }

        beitragNach += beitrag;

        this.getTeildatensatz(1)
            .getFeld(4)
            .setInhalt(beitragNach.toString());
    }

    /**
     * Diese Methode liefert den Gesamt-Beitrag als {@link Betrag} und nicht
     * als String zurueck, um nicht die Kompatibilitaet mit v4 zu brechen.
     *
     * @return Gesamtbeitrag als Betrag
     */
    public Betrag getGesamtBeitrag() {
        return Betrag.of(this.getFeld(Bezeichner.GESAMTBEITRAG));
    }

    /**
     * @param beitrag neuer Gesamtbeitrag (Brutto)
     * @deprecated durch {@link #setGesamtBeitragBruttoMitVorzeichen(double)} abgeloest
     */
    @Deprecated
    public void setGesamtBeitragBrutto(final double beitrag) {
        this.setGesamtBeitragBruttoMitVorzeichen(beitrag);
    }

    /**
     * @param beitrag neuer Gesamtbeitrag (Brutto)
     * @since 5.0
     */
    public void setGesamtBeitragBruttoMitVorzeichen(final double beitrag) {
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
     * Setzt den Gesamtbeitrag-Brutto(Inkasso) (Feld 5)
     *
     * @param numFeldBeitrag neuer Gesamtbeitrag-Brutto(Inkasso)
     * @since 5.0
     */
    public void setGesamtBeitragBrutto(final NumFeld numFeldBeitrag) {
        this.getTeildatensatz(1)
            .getFeld(5)
            .setInhalt(numFeldBeitrag.getInhalt());
    }

    /**
     * Erhoeht den Gesamtbeitrag-Brutto(Inkasso) (Feld 5 und Feld 6)
     *
     * @param beitrag neuer Summand fuer Gesamtbeitrag-Brutto(Inkasso)
     * @since 5.0
     */
    public void addGesamtBeitragBrutto(final long beitrag) {
        Long beitragNach;

        try {
            beitragNach = Long.parseLong(this.getTeildatensatz(1)
                                             .getFeld(5)
                                             .getInhalt()
                                             .trim());
        } catch (NumberFormatException e) {
            beitragNach = 0L;
        }

        if (("-").equals(this.getTeildatensatz(1)
                             .getFeld(6)
                             .getInhalt()
                             .trim()))
            beitragNach *= -1;

        beitragNach += beitrag;

        if (beitragNach >= 0) {
            this.getTeildatensatz(1)
                .getFeld(5)
                .setInhalt(beitragNach.toString());
            this.getTeildatensatz(1)
                .getFeld(6)
                .setInhalt("+");
        } else {
            beitragNach *= -1;
            this.getTeildatensatz(1)
                .getFeld(5)
                .setInhalt(beitragNach.toString());
            this.getTeildatensatz(1)
                .getFeld(6)
                .setInhalt("-");
        }
    }

    /**
     * Diese Methode wird kuenftig nur den Betragsteil zurueckliefern und damit
     * auch eine andere Semantik bekommen. Bis dahin ist diese Methode aber
     * deprecated.
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
     * Liefert das Vorzeichen Gesamtbeitrag-Brutto(Inkasso) (Feld 6)
     *
     * @return das Vorzeichen
     * @since 5.0
     */
    public String getVorzeichenGesamtbeitragBrutto() {
        return this.getTeildatensatz(1).getFeld(6).getInhalt().trim();
    }

    /**
     * Setzt den Gesamtprovisions-Betrag (Feld 7)
     *
     * @param strBeitrag neuer Gesamtprovisions-Betrag
     * @since 5.0
     */
    public void setGesamtProvisonBetrag(final String strBeitrag) {
        this.getTeildatensatz(1)
            .getFeld(7)
            .setInhalt(strBeitrag);
    }

    /**
     * Setzt den Gesamtprovisions-Betrag (Feld 7)
     *
     * @param numFeldBeitrag neuer Gesamtprovisions-Betrag
     * @since 5.0
     */
    public void setGesamtProvisonBetrag(final NumFeld numFeldBeitrag) {
        this.getTeildatensatz(1)
            .getFeld(7)
            .setInhalt(numFeldBeitrag.getInhalt());
    }

    /**
     * Setzt den Gesamtprovisions-Betrag (Feld 7)
     *
     * @param betrag neuer Gesamtprovisions-Betrag
     * @since 5.0
     */
    public void setGesamtProvisonBetragMitVorzeichen(final double betrag) {
        BetragMitVorzeichen bmv = getGesamtProvisonBetragMitVorzeichen();
        bmv.setInhalt(betrag);
        setGesamtProvisonBetrag(bmv.getBetrag().getInhalt());
        setVorzeichenGesamtProvisonBetrag(Character.toString(bmv.getVorzeichen()));
    }

    /**
     * Erhoeht den Gesamtprovisions-Betrag (Feld 7 und Feld 8)
     *
     * @param betrag neuer Summand fuer Gesamtprovisions-Betrag
     * @since 5.0
     */
    public void addGesamtProvisionsBetrag(final long betrag) {
        Long betragNach;

        try {
            betragNach = Long.parseLong(this.getTeildatensatz(1)
                                            .getFeld(7)
                                            .getInhalt()
                                            .trim());
        } catch (NumberFormatException e) {
            betragNach = 0L;
        }

        if (("-").equals(this.getTeildatensatz(1)
                             .getFeld(8)
                             .getInhalt()
                             .trim()))
            betragNach *= -1;

        betragNach += betrag;

        if (betragNach >= 0) {
            this.getTeildatensatz(1)
                .getFeld(7)
                .setInhalt(betragNach.toString());
            this.getTeildatensatz(1)
                .getFeld(8)
                .setInhalt("+");
        } else {
            betragNach *= -1;
            this.getTeildatensatz(1)
                .getFeld(7)
                .setInhalt(betragNach.toString());
            this.getTeildatensatz(1)
                .getFeld(8)
                .setInhalt("-");
        }
    }

    /**
     * Analog zu den anderen Be(i)trags-Methoden liefert diese Methode ein
     * Betrags-Feld zurueck.
     *
     * @return Gesamtprovisions-Betrag (Feld 7)
     * @since 5.0
     */
    public Betrag getGesamtProvisonBetrag() {
        return Betrag.of(this.getTeildatensatz(1).getFeld(7));
    }

    /**
     * @return Gesamtprovisions-Betrag (Feld 7)
     * @since 5.0
     */
    public BetragMitVorzeichen getGesamtProvisonBetragMitVorzeichen() {
        NumFeld brutto = (NumFeld) getFeld(GESAMTPROVISIONSBETRAG);
        AlphaNumFeld vorzeichen = (AlphaNumFeld) getFeld(VORZEICHEN2);
        return BetragMitVorzeichen.of(brutto, vorzeichen);
    }

    /**
     * Setzt das Vorzeichen Gesamtprovisions-Betrag (Feld 8)
     *
     * @param strVorzeichen Vorzeichen
     * @since 5.0
     */
    public void setVorzeichenGesamtProvisonBetrag(final String strVorzeichen) {
        if (("+").equalsIgnoreCase(strVorzeichen) || ("-").equalsIgnoreCase(
                strVorzeichen))
            this.getTeildatensatz(1)
                .getFeld(8)
                .setInhalt(strVorzeichen);
        else
            throw new IllegalArgumentException(strVorzeichen + ": kein Vorzeichen");
    }

    /**
     * Liefert das Vorzeichen Schadenbearbeitungskosten (Feld 12)
     *
     * @return das Vorzeichen
     * @since 5.0
     */
    public String getVorzeichenGesamtProvisonBetrag() {
        return this.getTeildatensatz(1)
                   .getFeld(8)
                   .getInhalt()
                   .trim();
    }

    /**
     * Durch {@link #setVersicherungsLeistungenMitVorzeichen(double)}
     * ersetzt.
     *
     * @param betrag neuer Betrag
     * @deprecated durch {@link #setVersicherungsLeistungenMitVorzeichen(double)} ersetzt
     */
    public void setVersicherungsLeistungen(final double betrag) {
        setVersicherungsLeistungenMitVorzeichen(betrag);
    }

    /**
     * Ersetzt {@link #setVersicherungsLeistungen(double)}.
     *
     * @param betrag neuer Betrag
     * @since 5.0
     */
    public void setVersicherungsLeistungenMitVorzeichen(final double betrag) {
        BetragMitVorzeichen bmv = getVersicherungsLeistungenMitVorzeichen();
        bmv.setInhalt(betrag);
        setVersicherungsLeistungen(bmv.getBetrag().getInhalt());
        setVorzeichenVersicherungsLeistungen(Character.toString(bmv.getVorzeichen()));
    }

    /**
     * Diese Methode wird kuenftig nur den Betragsteil zurueckliefern und damit
     * auch eine andere Semantik bekommen. Bis dahin ist diese Methode aber
     * deprecated.
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
     * Setzt die Versicherungsleistungen (Feld 9)
     *
     * @param numFeldBeitrag neue Versicherungsleitungen
     * @since 5.0
     */
    public void setVersicherungsLeistungen(final NumFeld numFeldBeitrag) {
        this.getTeildatensatz(1).getFeld(9).setInhalt(numFeldBeitrag.getInhalt());
    }

    /**
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
     * Liefert das Vorzeichen VersicherungsLeistungen (Feld 10)
     *
     * @return das Vorzeichen
     * @since 5.0
     */
    public String getVorzeichenVersicherungsLeistungen() {
        return this.getTeildatensatz(1).getFeld(10).getInhalt().trim();
    }

    /**
     * @param kosten
     *            Kosten der Schadensbearbeitung
     */
    public void setSchadenbearbeitungsKosten(final double kosten) {
        this.schadenbearbeitungsKosten.setInhalt(kosten);
    }

    /**
     * @return Kosten der Schadensbearbeitung
     */
    public BetragMitVorzeichen getSchadenbearbeitungsKosten() {
        return this.schadenbearbeitungsKosten;
    }

    /**
     * Liefert das gewuenschte Feld. Allerdings wird nur der Name des Feldes
     * benutzt, um das Feld zu bestimmen. Dazu werden auch die Konstanten in
     * {@link gdv.xport.feld.Bezeichner} verwendet.
     *
     * @param feld gewuenschtes Feld-Element
     * @return das gesuchte Feld
     * @throws IllegalArgumentException falls es das Feld nicht gibt
     */
    @Override
    public Feld getFeld(final Enum feld) throws IllegalArgumentException {
        if (feld instanceof Feld9999) {
            return getFeld((Feld9999) feld);
        } else {
            return super.getFeld(feld);
        }
    }

    private Feld getFeld(Feld9999 feld) {
        switch (feld) {
            case VORZEICHEN:
                return getVorzeichenOf(getGesamtBeitragBrutto());
            case VORZEICHEN2:
                return getVorzeichenOf(getGesamtBeitragBruttoMitVorzeichen());
            case VORZEICHEN3:
                return getVorzeichenOf(getVersicherungsLeistungenMitVorzeichen());
            case VORZEICHEN4:
                return getVorzeichenOf(schadenbearbeitungsKosten);
            default:
                return super.getFeld(feld);
        }
    }

    private Zeichen getVorzeichenOf(BetragMitVorzeichen beitrag) {
        return new Zeichen(beitrag.getEndAdresse(), beitrag.getVorzeichen());
    }

}
