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
 * (c)reated 08.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.config;

import org.apache.commons.lang.StringUtils;

/**
 * Ueber diese Klasse koennen globale Werte (wie z.B. die VU-Nummer) abgefragt
 * werden, die sich im Laufe des Programmes nicht aendern.
 * Momentan koennen (und muessen) diese Werte als System-Property gesetzt
 * werden.
 * 
 * @author oliver
 * @since 08.10.2009
 * @version $Revision$
 */
public class Config {
	
	protected static final String GDV_VU_NUMMER = "gdv.VU-Nummer";
	
	public static String getVUnummer() {
		String vuNummer = System.getProperty(GDV_VU_NUMMER);
		if (StringUtils.isBlank(vuNummer)) {
			throw new ConfigException("Property '" + GDV_VU_NUMMER + "' ist nicht gesetzt!");
		}
		return vuNummer;
	}

}

