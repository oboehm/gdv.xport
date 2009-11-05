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
 * (c)reated 28.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

/**
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.1.0 (28.10.2009)
 *
 */
public class VertragsspezifischerTeil extends Datensatz {
	
	public VertragsspezifischerTeil(int sparte) {
		this(sparte, 1);
	}
	
	/**
	 * @param sparte
	 * @param n Anzahl Teildatensaetze
	 */
	public VertragsspezifischerTeil(int sparte, int n) {
		super(210, n);
		this.sparte.setInhalt(sparte);
	}

}

