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
 * (c)reated 30.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import java.io.IOException;

import org.apache.commons.logging.*;

/**
 * Diese Klasse dient dazu, um einen vorgegebene Satz, der z.B. aus einem
 * Import kommt, in den entsprechende Satz wandeln zu koennen.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.1.0 (30.10.2009)
 */
public class SatzFactory {
	
	private static final Log log = LogFactory.getLog(SatzFactory.class);
	
	public static Satz getSatz(String content) {
		int satzart = Integer.parseInt(content.substring(0, 4));
		Satz satz;
		switch (satzart) {
			case 1:
				satz = new Vorsatz();
				break;
			case 9999:
				satz = new Nachsatz();
				break;
			default:
				int sparte = Integer.parseInt(content.substring(10, 13));
				satz = getDatensatz(satzart, sparte);
				break;
		}		
		try {
	        satz.importFrom(content);
	        return satz;
        } catch (IOException ioe) {
	        throw new IllegalArgumentException("can't parse " + content, ioe);
        }
	}

	/**
     * @param satzart
     * @param content
     * @return
     */
    public static Datensatz getDatensatz(int satzart, int sparte) {
	    switch (satzart) {
			case 100:
				return new Adressteil();
			case 200:
				return new AllgemeinerVertragsteil();
			case 210:
				return createVertragsspezifischerTeil(sparte);
			case 220:
				return createSpartenspezifischerTeil(sparte);
			default:
				log.warn("reduced functionality for (unknown or unsupported) Satzart " + satzart);
				Datensatz satz = new Datensatz(satzart);
				satz.addFiller();
				return satz;
		}
    }
	
	private static VertragsspezifischerTeil createVertragsspezifischerTeil(int sparte) {
		VertragsspezifischerTeil vertragsteil;
		switch (sparte) {
			case 30:
			case 40:
			case 80:
			case 110:
			case 140:
			case 190:
			case 510:
			case 550:
				vertragsteil = new VertragsspezifischerTeil(sparte);
				break;
			case 0:
			case 10:
			case 20:
			case 50:
			case 130:
			case 170:
			case 580:
				vertragsteil = new VertragsspezifischerTeil(sparte, 2);
				break;
			case 70:
				return new VertragsspezifischerTeilRechtsschutz();
			default:
				vertragsteil = new VertragsspezifischerTeil(sparte);
				break;
		}
		log.warn("reduced functionality for (unsupported or unknown) Satzart 210." + sparte);
		vertragsteil.addFiller();
		return vertragsteil;
	}
	
	private static Datensatz createSpartenspezifischerTeil(int sparte) {
		SpartenspezifischerTeil datensatz;
		switch (sparte) {
			case 0:
			case 20:
			case 110:
			case 130:
			case 510:
			case 550:
				datensatz = new SpartenspezifischerTeil(sparte);
				break;
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
				datensatz = new SpartenspezifischerTeil(sparte, 2);
				break;
			case 170:
				datensatz = new SpartenspezifischerTeil(sparte, 3);
				break;
			case 30:
				datensatz = new SpartenspezifischerTeil(sparte, 4);
				break;
			case 10:
				datensatz = new SpartenspezifischerTeil(sparte, 9);
				break;
			default:
				datensatz = new SpartenspezifischerTeil(sparte);
				break;
		}
		log.warn("reduced functionality for (unsupported or unknown) Satzart 220." + sparte);
		datensatz.addFiller();
		return datensatz;
    }

}

