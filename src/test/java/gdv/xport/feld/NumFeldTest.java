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

package gdv.xport.feld;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author oliver
 * @since 05.10.2009
 * @version $Revision$
 *
 */
public class NumFeldTest {
	
	@Test
	public void testNumFeld() {
		NumFeld nummer = new NumFeld(4, 1);
		assertEquals("0000", nummer.toString());
	}

	/**
	 * Test method for {@link gdv.xport.feld.NumFeld#setInhalt(int)}.
	 */
	@Test
	public void testSetInhaltInt() {
		NumFeld nummer = new NumFeld("0001");
		nummer.setInhalt(2);
		assertEquals("0002", nummer.toString());
	}

}


/*
 * $Log$
 * $Source$
 */
