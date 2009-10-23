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
 * (c)reated 23.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport;

import static org.junit.Assert.*;

import gdv.xport.config.Config;
import gdv.xport.feld.VUNummer;

import java.io.*;

import org.junit.*;

/**
 * @author oliver
 * @since 23.10.2009
 * @version $Revision$
 *
 */
public class DatenpaketTest {

	/** zum Testen nehmen wir hier die VU-Nr. der Oerag */
	protected static final VUNummer VU_NUMMER = new VUNummer("5183");

	@BeforeClass
	public static void setUpBeforeClass() {
		Config.setVUNummer(VU_NUMMER);
	}

	/**
	 * Test method for {@link gdv.xport.Datenpaket#export(java.io.Writer)}.
	 * @throws IOException 
	 */
	@Test
	public void testEmptyExport() throws IOException {
		Datenpaket empty = new Datenpaket();
		StringWriter swriter = new StringWriter(1024);
		empty.export(swriter);
		String data = swriter.toString();
		assertEquals(1024, data.length());
	}

}

