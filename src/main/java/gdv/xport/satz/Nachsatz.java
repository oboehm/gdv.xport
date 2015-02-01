/*
 * $Id$
 *
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
 * (c)reated 05.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.BetragMitVorzeichen;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.NumFeld;

/**
 * Dies ist der letzte Satz, der Nachsatz eben.
 * <p>
 * Da Vorsatz und Nachsatz von der Datenpaket-Klasse benoetigt werden, habe
 * ich das "deprecated" wieder entfernt (24-Nov-2012, oboehm).
 * </p>
 * @author oliver
 * @since 05.10.2009
 */
public final class Nachsatz extends Satz {

    private final NumFeld anzahlSaetze = new NumFeld(new Bezeichner(Bezeichner.NAME_ANZAHL_SAETZE), 10, 5);
    private final AlphaNumFeld vermittler = new AlphaNumFeld(new Bezeichner(Bezeichner.NAME_VERMITTLER), 10, 15);
    private final Betrag gesamtBeitrag = new Betrag(new Bezeichner(Bezeichner.NAME_GESAMTBEITRAG), 15, 25);
    private final BetragMitVorzeichen gesamtBeitragBrutto = new BetragMitVorzeichen(new Bezeichner(
            Bezeichner.NAME_GESAMTBEITRAG_BRUTTO), 15, 40);
    private final BetragMitVorzeichen gesamtProvisionsBetrag = new BetragMitVorzeichen(new Bezeichner(
            Bezeichner.NAME_GESAMTPROVISIONSBETRAG), 15, 55);
    private final BetragMitVorzeichen versicherungsLeistungen = new BetragMitVorzeichen(new Bezeichner(
            Bezeichner.NAME_VERSICHERUNGSLEISTUNGEN), 15, 70);
    private final BetragMitVorzeichen schadenbearbeitungsKosten = new BetragMitVorzeichen(new Bezeichner(
            Bezeichner.NAME_SCHADENBEARBEITUNGSKOSTEN), 15, 85);

    /**
     * Default-Constructor.
     */
    public Nachsatz() {
        super("9999", 1);
        this.remove(Bezeichner.NAME_SATZNUMMER);
        this.setUpTeildatensatz();
        this.setAnzahlSaetze(0);
    }

    private void setUpTeildatensatz() {
        add(this.anzahlSaetze);
        add(this.vermittler);
        add(this.gesamtBeitrag);
        add(this.gesamtBeitragBrutto);
        add(this.gesamtProvisionsBetrag);
        add(this.versicherungsLeistungen);
        add(this.schadenbearbeitungsKosten);
        add(new AlphaNumFeld(new Bezeichner(Bezeichner.NAME_LEERSTELLEN), 157, 100));
    }

    /**
     * Setzt die Anzahl der Saetze.
     *
     * @param n Anzahl der eingeschlossenen Saetze
     */
    public void setAnzahlSaetze(final int n) {
        this.anzahlSaetze.setInhalt(n);
        this.getTeildatensatz(1).add(anzahlSaetze);
    }

    /**
     * @return Anzahl der eingeschlossenen Saetze
     */
    public int getAnzahlSaetze() {
        return this.anzahlSaetze.toInt();
    }

    /**
     * Anzahl der Saetze um 1 erhoehen.
     */
    public void increaseAnzahlSaetze() {
        int n = this.getAnzahlSaetze();
        this.setAnzahlSaetze(n + 1);
    }

    /**
     * @param s
     *            Vermittler
     */
    public void setVermittler(final String s) {
        this.vermittler.setInhalt(s);
    }

    /**
     * @return Vermittler
     */
    public String getVermittler() {
        return this.vermittler.getInhalt().trim();
    }

    /**
     * Setzt den Gesamtbeitrag.
     *
     * @param beitrag
     *            der neue Gesamtbeitrag
     */
    public void setGesamtBeitrag(final double beitrag) {
        this.gesamtBeitrag.setInhalt(beitrag);
    }

    /**
     * @return Gesamtbeitrag
     */
    public Betrag getGesamtBeitrag() {
        return this.gesamtBeitrag;
    }

    /**
     * @param beitrag
     *            neuer Gesamtbeitrag
     */
    public void setGesamtBeitragBrutto(final double beitrag) {
        this.gesamtBeitrag.setInhalt(beitrag);
    }

    /**
     * @return Gesamtbeitrag (Brutto)
     */
    public BetragMitVorzeichen getGesamtBeitragBrutto() {
        return this.gesamtBeitragBrutto;
    }

    /**
     * @param betrag
     *            neuer Betrag
     */
    public void setVersicherungsLeistungen(final Double betrag) {
        this.versicherungsLeistungen.setInhalt(betrag);
    }

    /**
     * @return Versichuerungsleistungen
     */
    public BetragMitVorzeichen getVersicherungsLeistungen() {
        return this.versicherungsLeistungen;
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

}
