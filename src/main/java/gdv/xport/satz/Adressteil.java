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
 * (c)reated 12.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import gdv.xport.feld.*;
import static gdv.xport.feld.Bezeichner.*;

/**
 * @author oliver
 * @since 12.10.2009
 * @version $Revision$
 */
public class Adressteil extends Datensatz {
	
	private void setUpDatenfelder() {
		// Teildatensatz 1
		add(new AlphaNumFeld(ANREDESCHLUESSEL, 1, 43, '0'));
	    add(new AlphaNumFeld(NAME1, 30, 44));
	    add(new AlphaNumFeld(NAME2, 30, 74));
	    add(new AlphaNumFeld(NAME3, 30, 104));
        add(new AlphaNumFeld(TITEL, 20, 134));
        add(new AlphaNumFeld(LAENDERKENNZEICHEN, 3, 154));
        add(new AlphaNumFeld(POSTLEITZAHL, 6, 157, Align.LEFT));
        add(new AlphaNumFeld(ORT, 25, 163));
        add(new AlphaNumFeld(STRASSE, 30, 188));
        add(new AlphaNumFeld(POSTFACH, 8, 218));
        add(new Datum(GEBURTSDATUM, 8, 226));
        add(new AlphaNumFeld(STAATSANGEHOERIGKEIT, 3, 234));
        add(new AlphaNumFeld(ADRESSKENNZEICHEN, 2, 237));
        add(new AlphaNumFeld(AKTENZEICHEN_SICHERUNGSGLAEUBIGER, 12, 239));
        add(new AlphaNumFeld(ZIELGRUPPENSCHLUESSEL, 2, 251));
        add(new AlphaNumFeld(GROSSRISIKEN, 1, 253));
        add(new AlphaNumFeld(POSTALISCHES_KENNZEICHEN, 1, 254));
        add(new AlphaNumFeld(GESCHLECHT, 1, 255));
        // Teildatensatz 2
        add(new AlphaNumFeld(KUNDENNR_VERSICHERER, 17, 256+43, Align.RIGHT));
        add(new AlphaNumFeld(KUNDENNR_VERMITTLER, 17, 256+60, Align.RIGHT));
        add(new AlphaNumFeld(KUNDENGRUPPE, 30, 256+77));
        add(new AlphaNumFeld(KONTONR1, 12, 256+107));
        add(new AlphaNumFeld(BLZ1, 8, 256+119));
        add(new AlphaNumFeld(ABWEICHENDER_KONTOINHABER1, 30, 256+127));
        add(new AlphaNumFeld(KOMMUNIKATIONSTYP1, 2, 157+256));
        add(new AlphaNumFeld(KOMMUNIKATIONSNR1, 20, 159+256));
        add(new AlphaNumFeld(KOMMUNIKATIONSTYP2, 2, 179+256));
        add(new AlphaNumFeld(KOMMUNIKATIONSNR2, 20, 181+256));
        add(new AlphaNumFeld(KOMMUNIKATIONSTYP3, 2, 201+256));
        add(new AlphaNumFeld(KOMMUNIKATIONSNR3, 20, 203+256));
        add(new AlphaNumFeld(KOMMUNIKATIONSTYP4, 2, 223+256));
        add(new AlphaNumFeld(KOMMUNIKATIONSNR4, 20, 225+256));
        add(new AlphaNumFeld(ZAHLUNGSART, 1, 245+256));
        add(new AlphaNumFeld(FAMILIENSTAND, 1, 246+256));
        add(new NumFeld(LFD_PERSONENNR_GEVO, 6, 250+256));
        // Teildatensatz 3
        add(new AlphaNumFeld(KOMMUNIKATIONSTYP5, 2, 43+512));
        add(new AlphaNumFeld(KOMMUNIKATIONSNR5, 60, 45+512));
        add(new AlphaNumFeld(KOMMUNIKATIONSTYP6, 2, 105+512));
        add(new AlphaNumFeld(KOMMUNIKATIONSNR6, 60, 107+512));
        add(new AlphaNumFeld(KOMMUNIKATIONSTYP7, 2, 167+512));
        add(new AlphaNumFeld(KOMMUNIKATIONSNR7, 60, 169+512));
        add(new AlphaNumFeld(RECHTSFORM, 2, 229+512));
        // Teildatensatz 4
        add(new AlphaNumFeld(GEBURTSNAME, 30, 43+768));
        add(new AlphaNumFeld(NAME_KREDITINSTITUT1, 30, 73+768));
        add(new AlphaNumFeld(TYP_BANKVERBINDUNG1, 2, 103+768));
        add(new AlphaNumFeld(TYP_BANKVERBINDUNG2, 2, 105+768));
        add(new AlphaNumFeld(KONTONR2, 12, 107+768));
        add(new AlphaNumFeld(BLZ2, 8, 119+768));
        add(new AlphaNumFeld(ABWEICHENDER_KONTOINHABER2, 30, 127+768));
        add(new AlphaNumFeld(NAME_KREDITINSTITUT2, 30, 157+768));
        add(new AlphaNumFeld(BIC1, 11, 187+768));
        add(new AlphaNumFeld(BIC2, 11, 198+768));
        add(new AlphaNumFeld(IBAN1, 34, 209+768));
        // Teildatensatz 5
        add(new AlphaNumFeld(IBAN2, 34, 43+1024));
        add(new AlphaNumFeld(GEBURTSORT, 30, 77+1024));
        add(new AlphaNumFeld(GEBURTSLAND, 3, 107+1024));
        add(new AlphaNumFeld(STEUERNR_JURISTISCHE_PERSON, 17, 110+1024));
        add(new AlphaNumFeld(UMSATZSTEUER_ID, 17, 127+1024));
        add(new AlphaNumFeld(VORZUGSSTEUERBERECHTIGUNG, 1, 144+1024));
        add(new AlphaNumFeld(VORZUGSSEUERBERECHTIGUNG_PROZENT, 5, 145+1024));
	}
	
	public Adressteil() {
		super("0100", 5);
		this.setUpDatenfelder();
	}
	
	public void setName(String vorname, String nachname) {
		this.set(NAME1, vorname);
		this.set(NAME3, nachname);
	}
	
	/**
	 * @param n 1, 2 oder 3
	 * @return Name1, Name2 oder Name3
	 */
	public String getName(int n) {
		assert (n >= 1) && (n <= 3) : "1 <= n <= 3 expected";
		switch(n) {
		case 1:
			return this.getFeld(NAME1).getInhalt().trim();
		case 2:
			return this.getFeld(NAME2).getInhalt().trim();
		case 3:
			return this.getFeld(NAME3).getInhalt().trim();
		default:
			throw new IllegalArgumentException("1 <= n <= 3 expected");
		}
	}

}

