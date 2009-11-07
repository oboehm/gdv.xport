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
 * (c)reated 05.11.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import org.apache.commons.logging.*;

/**
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.1.0 (05.11.2009)
 *
 */
public class SpartenspezifischerTeil extends Datensatz {

	private static final Log log = LogFactory.getLog(SpartenspezifischerTeil.class);
	
	public SpartenspezifischerTeil(int sparte) {
		this(sparte, getNumberOfTeildatensaetzeFor(sparte));
	}
	
	/**
	 * @param sparte
	 * @param n Anzahl Teildatensaetze
	 */
	public SpartenspezifischerTeil(int sparte, int n) {
		super(220, n);
		this.sparte.setInhalt(sparte);
	}
	
	private static int getNumberOfTeildatensaetzeFor(int sparte) {
		switch (sparte) {
			case 0:
			case 20:
			case 110:
			case 130:
			case 510:
			case 550:
				return 1;
			case 40:
			case 51:
			case 52:
			case 53:
			case 54:
			case 55:
			case 59:
			case 70:
			case 80:
			case 140:
			case 190:
			case 580:
				return 2;
			case 170:
				return 3;
			case 30:
				return 4;
			case 10:
				return 9;
			default:
				log.warn("unknown Sparte " + sparte + " -> mapped to 1 Teildatensatz");
				return 1;
		}
	}

}

