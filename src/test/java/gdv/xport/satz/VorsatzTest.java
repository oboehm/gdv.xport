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
 * (c)reated 04.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static org.junit.Assert.assertEquals;

import java.io.*;

import org.apache.commons.logging.*;
import org.junit.*;

/**
 * @author oliver
 * @since 04.10.2009
 * @version $Revision$
 *
 */
public class VorsatzTest extends AbstractSatzTest {
	
	private static final Log log = LogFactory.getLog(VorsatzTest.class);
	private Vorsatz vorsatz = new Vorsatz();

	/**
	 * Test method for {@link gdv.xport.satz.Vorsatz#Vorsatz()}.
	 * @throws IOException 
	 */
	@Test
	public void testVorsatz() throws IOException {
		StringWriter swriter = new StringWriter(768);
		vorsatz.export(swriter);
		String expected = "0001" + VU_NUMMER;
		checkExport(1, 9, expected);
		checkExport(257, 265, expected);
		checkExport(256+246, 256+256, "          2");
		checkExport(225, 227, "1.1");
	}
	
	@Test
	public void testSetAbsender() throws IOException {
		String absender = "agentes AG                    ";
		vorsatz.setAbsender(absender.trim());
		checkExport(10, 39, absender);
	}
	
	@Test
	public void testSetErstellungsZeitraum() throws IOException {
		String startDatum = "01011900";
		String endDatum = "09102009";
		vorsatz.setErstellungsZeitraum(startDatum, endDatum);
		checkExport(70, 85, startDatum + endDatum);
	}
	
	/**
	 * @param startByte beginnend bei 1
	 * @param endByte   beginnend bei 1
	 * @param expected
	 * @throws IOException 
	 */
	private void checkExport(int startByte, int endByte, String expected) throws IOException {
		StringWriter swriter = new StringWriter(768);
		vorsatz.export(swriter);
		String data = swriter.toString();
		assertEquals(768, data.length());
		String toBeChecked = data.substring(startByte-1, endByte);
		log.info("data: " + data.substring(0, 9) + "..." + toBeChecked + "...");
		assertEquals(expected, toBeChecked);
	}

}


/*
 * $Log$
 * $Source$
 */
