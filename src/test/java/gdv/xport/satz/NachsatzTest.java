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

package gdv.xport.satz;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.logging.*;
import org.junit.Test;

/**
 * @author oliver
 * @since 05.10.2009
 * @version $Revision$
 *
 */
public class NachsatzTest extends AbstractSatzTest {
	
	private static final Log log = LogFactory.getLog(NachsatzTest.class);
	private Nachsatz nachsatz = new Nachsatz();

	/**
	 * Test method for {@link gdv.xport.satz.Nachsatz#Nachsatz()}.
	 */
	@Test
	public void testNachsatz() {
		assertEquals(9999, nachsatz.getSatzart());
	}

	/**
	 * Test method for {@link gdv.xport.satz.Satz#export(java.io.Writer)}.
	 * @throws IOException
	 */
	@Test
	public void testExport() throws IOException {
		checkExport(1, 19, "99990000000000     ");
		checkExport(246, 255, "          ");
		log.info(nachsatz);
	}
	
	/**
	 * @param startByte beginnend bei 1
	 * @param endByte   beginnend bei 1
	 * @param expected
	 * @throws IOException
	 */
	private void checkExport(int startByte, int endByte, String expected) throws IOException {
		super.checkExport(this.nachsatz, startByte, endByte, expected, 256);
	}
	
	@Test
	public void testSetAnzahl() {
		nachsatz.setAnzahlSaetze(42);
		assertEquals(42, nachsatz.getAnzahlSaetze());
	}

}


/*
 * $Log$
 * $Source$
 */
