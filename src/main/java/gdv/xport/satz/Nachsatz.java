/*
 * $Id$
 *
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
 * (c)reated 05.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.*;

import gdv.xport.feld.*;


/**
 * @author oliver
 * @since 05.10.2009
 * @version $Revision$
 *
 */
public final class Nachsatz extends Satz {
	
	private final NumFeld anzahlSaetze = new NumFeld(ANZAHL_SAETZE, 10, 5);
	private final AlphaNumFeld vermittler = new AlphaNumFeld(VERMITTLER, 10, 15);
	private final Betrag gesamtBeitrag = new Betrag(GESAMTBEITRAG, 15, 25);
	private final BetragMitVorzeichen gesamtBeitragBrutto = new BetragMitVorzeichen(
	        GESAMTBEITRAG_BRUTTO, 15, 40);
	private final BetragMitVorzeichen gesamtProvisionsBetrag = new BetragMitVorzeichen(
	        GESAMTPROVISIONSBETRAG, 15, 55);
	private final BetragMitVorzeichen versicherungsLeistungen = new BetragMitVorzeichen(
	        VERSICHERUNGSLEISTUNGEN, 15, 70);
	private final BetragMitVorzeichen schadenbearbeitungsKosten = new BetragMitVorzeichen(
	        SCHADENBEARBEITUNGSKOSTEN, 15, 85);

	public Nachsatz() {
		super("9999", 1);
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
	}

	public void setAnzahlSaetze(int n) {
		this.anzahlSaetze.setInhalt(n);
		this.teildatensatz[0].add(anzahlSaetze);
	}
	
	public int getAnzahlSaetze() {
		return this.anzahlSaetze.toInt();
	}
	
	public void increaseAnzahlSaetze() {
		int n = this.getAnzahlSaetze();
		this.setAnzahlSaetze(n + 1);
	}
	
	public void setVermittler(String s) {
		this.vermittler.setInhalt(s);
	}
	
	public String getVermittler() {
		return this.vermittler.getInhalt().trim();
	}
	
	public void setGesamtBeitrag(double beitrag) {
		this.gesamtBeitrag.setInhalt(beitrag);
	}
	
	public Betrag getGesamtBeitrag() {
		return this.gesamtBeitrag;
	}
	
	public void setGesamtBeitragBrutto(double beitrag) {
		this.gesamtBeitrag.setInhalt(beitrag);
	}
	
	public BetragMitVorzeichen getGesamtBeitragBrutto() {
		return this.gesamtBeitragBrutto;
	}
	
	public void setVersicherungsLeistungen(Double betrag) {
		this.versicherungsLeistungen.setInhalt(betrag);
	}
	
	public BetragMitVorzeichen getVersicherungsLeistungen() {
		return this.versicherungsLeistungen;
	}
	
	public void setSchadenbearbeitungsKosten(double kosten) {
		this.schadenbearbeitungsKosten.setInhalt(kosten);
	}
	
	public BetragMitVorzeichen getSchadenbearbeitungsKosten() {
		return this.schadenbearbeitungsKosten;
	}
	
}
