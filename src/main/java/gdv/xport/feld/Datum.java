/*
 * $Id$
 *
 * Copyright (c) 2009 by Oliver Boehm
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
 * (c)reated 04.10.2009 by oliver (ob@oasd.de)
 */

package gdv.xport.feld;

/**
 * @author oliver
 * @since 04.10.2009
 * @version $Revision$
 */
public class Datum extends Feld {
	
	public Datum() {
		super("        ");
	}
	
	/**
	 * @param s im Format TTMMJJJJ
	 */
	public Datum(String s) {
		super(s);
	}
	
	public Datum(int start) {
		super(8, start);
	}
	
}


/*
 * $Log$
 * $Source$
 */
