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

import gdv.xport.feld.*;


/**
 * @author oliver
 * @since 05.10.2009
 * @version $Revision$
 * 
 */
public final class Nachsatz extends Satz {
	
	private final NumFeld anzahlSaetze = new NumFeld("00000", 5);
	private final AlphaNumFeld vermittler = new AlphaNumFeld(10, 15);
	private final Betrag gesamtBeitrag = new Betrag(15, 25);
	private final VorzeichenBetrag gesamtBeitragBrutto = new VorzeichenBetrag(15, 40);
	private final VorzeichenBetrag gesamtProvisionsBetrag = new VorzeichenBetrag(15, 55);
	private final VorzeichenBetrag versicherungsLeistungen = new VorzeichenBetrag(15, 70);
	private final VorzeichenBetrag schadenbearbeitunsKosten = new VorzeichenBetrag(15, 85);

	public Nachsatz() {
		super("9999");
		this.createTeildatensatz();
		this.setAnzahlSaetze(0);
	}
	
	private void createTeildatensatz() {
		this.createTeildatensaetze(1);
		this.teildatensatz[0].setData(this.anzahlSaetze);
		this.teildatensatz[0].setData(this.vermittler);
		this.teildatensatz[0].setData(this.gesamtBeitrag);
		this.teildatensatz[0].setData(this.gesamtBeitragBrutto);
		this.teildatensatz[0].setData(this.gesamtProvisionsBetrag);
		this.teildatensatz[0].setData(this.versicherungsLeistungen);
		this.teildatensatz[0].setData(this.schadenbearbeitunsKosten);
	}

	public void setAnzahlSaetze(int n) {
		this.anzahlSaetze.setInhalt(n);
		this.teildatensatz[0].setData(anzahlSaetze);
	}

}
