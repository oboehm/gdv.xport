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
public class FeldTest {

	/**
	 * Test method for {@link gdv.xport.feld.Feld#resetInhalt()}.
	 */
	@Test
	public void testResetInhalt() {
		Feld feld = new Feld("abc", Align.LEFT);
		feld.resetInhalt();
		assertEquals("   ", feld.toString());
	}
	
	@Test
	public void testSetInhalt() {
		Feld linksbuendig = new Feld("hello", Align.LEFT);
		linksbuendig.setInhalt("abc");
		assertEquals("abc  ", linksbuendig.toString());
		Feld rechtsbuendig = new Feld("world", Align.RIGHT);
		rechtsbuendig.setInhalt("hi");
		assertEquals("   hi", rechtsbuendig.toString());
	}
	
	@Test
	public void testFeld() {
		Feld zeichen = new Feld("Testfeld", 1, 1, 'x', Align.LEFT);
		assertEquals("x", zeichen.getInhalt());
	}

}


/*
 * $Log$
 * $Source$
 */
