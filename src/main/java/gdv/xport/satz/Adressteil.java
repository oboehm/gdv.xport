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
		add(new AlphaNumFeld(ANREDESCHLUESSEL, 1, 43, '0'));
	    add(new AlphaNumFeld(NAME1, 30, 44));
	    add(new AlphaNumFeld(NAME2, 30, 74));
	    add(new AlphaNumFeld(NAME3, 30, 104));
//		private AlphaNumFeld titel = new AlphaNumFeld(20, 134);
//		private AlphaNumFeld laenderKennzeichen = new AlphaNumFeld(3, 154);
//		private AlphaNumFeld postleitzahl = new AlphaNumFeld(6, 157, Align.LEFT);
//		private AlphaNumFeld ort = new AlphaNumFeld(25, 163);
//		private AlphaNumFeld strasse = new AlphaNumFeld(30, 188);
//		private AlphaNumFeld postfach = new AlphaNumFeld(8, 218);
//		private Datum geburtsDatum = new Datum(8, 226);
//		private AlphaNumFeld staatsangehoerigkeit = new AlphaNumFeld(3, 234);
//		private AlphaNumFeld adressKennzeichen = new AlphaNumFeld(2, 237);
//		private AlphaNumFeld aktenzeichenSicherungsglaeubiger = new AlphaNumFeld(12, 239);
//		private AlphaNumFeld zielgruppenSchluessel = new AlphaNumFeld(2, 251);
//		private AlphaNumFeld grossrisiken = new AlphaNumFeld(1, 253);
//		private AlphaNumFeld postalischesKennzeichen = new AlphaNumFeld(1, 254);
//		private AlphaNumFeld geschlecht = new AlphaNumFeld(1, 255);
//		private AlphaNumFeld kundenrVersicherer = new AlphaNumFeld(17, 256+43, Align.RIGHT);
//		private AlphaNumFeld kundennrVermittler = new AlphaNumFeld(17, 256+60, Align.RIGHT);
//		private AlphaNumFeld kundenGruppe = new AlphaNumFeld(30, 256+77);
//		private AlphaNumFeld kontonr1 = new AlphaNumFeld(12, 256+107);
//		private AlphaNumFeld blz1 = new AlphaNumFeld(8, 256+119);
//		private AlphaNumFeld abweichenderKontoinhaber = new AlphaNumFeld(30, 256+127);
//		private AlphaNumFeld kommunikationsTyp1 = new AlphaNumFeld(2, 256+157);
//		private AlphaNumFeld kommunikationsNr1 = new AlphaNumFeld(20, 159+256);
//		private AlphaNumFeld kommunikationsTyp2 = new AlphaNumFeld(2, 179+256);
//		private AlphaNumFeld kommunikationsNr2 = new AlphaNumFeld(20, 181+256);
//		private AlphaNumFeld kommunikationsTyp3 = new AlphaNumFeld(2, 201+256);
//		private AlphaNumFeld kommunikationsNr3 = new AlphaNumFeld(20, 203+256);
//		private AlphaNumFeld kommunikationsTyp4 = new AlphaNumFeld(2, 223+256);
//		private AlphaNumFeld kommunikationsNr4 = new AlphaNumFeld(20, 225+256);
//		private AlphaNumFeld zahlungsart = new AlphaNumFeld(1, 245+256);
//		private AlphaNumFeld familienstand = new AlphaNumFeld(1, 246+256);
//		private NumFeld lfdPersonennrGeVo = new NumFeld(6, 250+256);
	}
	
//	private AlphaNumFeld name1 = new AlphaNumFeld(30, 44);
//	private AlphaNumFeld name2 = new AlphaNumFeld(30, 74);
//	private AlphaNumFeld name3 = new AlphaNumFeld(30, 104);
//	private AlphaNumFeld titel = new AlphaNumFeld(20, 134);
//	private AlphaNumFeld laenderKennzeichen = new AlphaNumFeld(3, 154);
//	private AlphaNumFeld postleitzahl = new AlphaNumFeld(6, 157, Align.LEFT);
//	private AlphaNumFeld ort = new AlphaNumFeld(25, 163);
//	private AlphaNumFeld strasse = new AlphaNumFeld(30, 188);
//	private AlphaNumFeld postfach = new AlphaNumFeld(8, 218);
//	private Datum geburtsDatum = new Datum(8, 226);
//	private AlphaNumFeld staatsangehoerigkeit = new AlphaNumFeld(3, 234);
//	private AlphaNumFeld adressKennzeichen = new AlphaNumFeld(2, 237);
//	private AlphaNumFeld aktenzeichenSicherungsglaeubiger = new AlphaNumFeld(12, 239);
//	private AlphaNumFeld zielgruppenSchluessel = new AlphaNumFeld(2, 251);
//	private AlphaNumFeld grossrisiken = new AlphaNumFeld(1, 253);
//	private AlphaNumFeld postalischesKennzeichen = new AlphaNumFeld(1, 254);
//	private AlphaNumFeld geschlecht = new AlphaNumFeld(1, 255);
//	private AlphaNumFeld kundenrVersicherer = new AlphaNumFeld(17, 256+43, Align.RIGHT);
//	private AlphaNumFeld kundennrVermittler = new AlphaNumFeld(17, 256+60, Align.RIGHT);
//	private AlphaNumFeld kundenGruppe = new AlphaNumFeld(30, 256+77);
//	private AlphaNumFeld kontonr1 = new AlphaNumFeld(12, 256+107);
//	private AlphaNumFeld blz1 = new AlphaNumFeld(8, 256+119);
//	private AlphaNumFeld abweichenderKontoinhaber = new AlphaNumFeld(30, 256+127);
//	private AlphaNumFeld kommunikationsTyp1 = new AlphaNumFeld(2, 256+157);
//	private AlphaNumFeld kommunikationsNr1 = new AlphaNumFeld(20, 159+256);
//	private AlphaNumFeld kommunikationsTyp2 = new AlphaNumFeld(2, 179+256);
//	private AlphaNumFeld kommunikationsNr2 = new AlphaNumFeld(20, 181+256);
//	private AlphaNumFeld kommunikationsTyp3 = new AlphaNumFeld(2, 201+256);
//	private AlphaNumFeld kommunikationsNr3 = new AlphaNumFeld(20, 203+256);
//	private AlphaNumFeld kommunikationsTyp4 = new AlphaNumFeld(2, 223+256);
//	private AlphaNumFeld kommunikationsNr4 = new AlphaNumFeld(20, 225+256);
//	private AlphaNumFeld zahlungsart = new AlphaNumFeld(1, 245+256);
//	private AlphaNumFeld familienstand = new AlphaNumFeld(1, 246+256);
//	private NumFeld lfdPersonennrGeVo = new NumFeld(6, 250+256);
	
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
			return this.get(NAME1).toString().trim();
		case 2:
			return this.get(NAME2).toString().trim();
		case 3:
			return this.get(NAME3).toString().trim();
		default:
			throw new IllegalArgumentException("1 <= n <= 3 expected");
		}
	}

}

