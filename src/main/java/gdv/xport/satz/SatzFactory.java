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
			case 100:
				satz = new Adressteil();
				break;
			case 200:
				satz = new AllgemeinerVertragsteil();
				break;
			case 210:
				int sparte = Integer.parseInt(content.substring(10, 13));
				satz = new SpartenspezifischerVertragsteil(sparte);
				break;
			case 9999:
				satz = new Nachsatz();
				break;
			default:
				log.warn("reduced functionality for (unknown or unsupported) Satzart " + satzart);
				return new Satz(content);
		}
		try {
	        satz.importFrom(content);
	        return satz;
        } catch (IOException ioe) {
	        throw new IllegalArgumentException("can't parse " + content, ioe);
        }
	}

}

