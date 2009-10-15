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

import gdv.xport.config.Config;
import gdv.xport.feld.*;

/**
 * @author oliver
 * @since 12.10.2009
 */
public class Datensatz extends Satz {
	
	/** 5 Zeichen, Byte 5 - 9 */
	protected VUNummer vuNummer = new VUNummer(Config.getVUNummer(), 5);
	/** 1 Zeichen, Byte 10 */
	protected AlphaNumFeld buendelungsKennzeichen = new AlphaNumFeld(1, 10);
	/** 3 Zeichen, Byte 11 - 13 */
	protected NumFeld sparte = new NumFeld(3, 11);
	/** 17 Zeichen, Byte 14 - 30 */
	protected AlphaNumFeld versicherungsscheinNr = new AlphaNumFeld(17, 14);
	/** 2 Zeichen, Byte 31 + 32 */
	protected NumFeld folgeNr = new NumFeld(2, 31);
	/** 10 Zeichen, Byte 33 - 42 */
	protected AlphaNumFeld vermittler = new AlphaNumFeld(10, 33);
	
	public Datensatz(String satzart) {
		super(satzart);
	}
	
	/**
	 * @param satzart
	 * @param n Anzahl der Teildatensaetze
	 */
	public Datensatz(String satzart, int n) {
		super(satzart, n);
		this.setUpTeildatensaetze();
	}

	protected void setUpTeildatensaetze() {
		for (int i = 0; i < teildatensatz.length; i++) {
			teildatensatz[i].setDatenfeld("VU-Nummer", this.vuNummer);
			teildatensatz[i].setDatenfeld("Buendelungskennzeichen", this.buendelungsKennzeichen);
			teildatensatz[i].setDatenfeld("Sparte", this.sparte);
			teildatensatz[i].setDatenfeld("Versicherungschein-Nummer", this.versicherungsscheinNr);
			teildatensatz[i].setDatenfeld("Folgenummer", this.folgeNr);
			teildatensatz[i].setDatenfeld("Vermittler", this.vermittler);
		}
	}

}

