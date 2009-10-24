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
import static gdv.xport.feld.Bezeichner.*;

import gdv.xport.config.Config;
import gdv.xport.feld.*;
import gdv.xport.satz.*;

import java.io.*;
import java.util.Date;

import org.apache.commons.logging.*;
import org.junit.*;

/**
 * @author oliver
 * @since 23.10.2009
 * @version $Revision$
 *
 */
public class DatenpaketTest {

	private static final Log log = LogFactory.getLog(Datenpaket.class);
	/** zum Testen nehmen wir hier die VU-Nr. der Oerag */
	protected static final VUNummer VU_NUMMER = new VUNummer("5183");
	/** fuer jeden Test gibt es ein frisches Datenpaket */
	private Datenpaket datenpaket = new Datenpaket();
	
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
	
	@Test
	public void testExportFile() throws IOException {
		File file = File.createTempFile("datenpaket", ".txt");
		datenpaket.export(file);
		log.info(datenpaket + " was exported to " + file);
		assertTrue(file + " was not created", file.exists());
		assertEquals(1024, file.length());
	}
	
	@Test
	public void testAdd() {
		Datensatz datensatz = new Adressteil();
		datenpaket.add(datensatz);
		Vorsatz vorsatz = datenpaket.getVorsatz();
		assertEquals("1.0", vorsatz.getVersion(VERSION_VORSATZ));
		assertEquals("1.0", vorsatz.getVersion(VERSION_ADRESSSATZ));
		assertEquals("1.0", vorsatz.getVersion(VERSION_NACHSATZ));
	}
	
	/**
	 * Falls kein Datum gesetzt wird, sollte als Default das heutige DAtum
	 * zurueckgegeben werden.
	 */
	@Test
	public void testGetErstellungsDatum() {
		Datum von = datenpaket.getErstellungsDatumVon();
		Datum bis = datenpaket.getErstellungsDatumBis();
		Datum heute = Datum.heute();
		Date today = heute.toDate();
		assertEquals(today, von.toDate());
		assertEquals(today, bis.toDate());
	}
	
	@Test
	public void testGetAbsender() {
		String absender = "Dagobert Duck";
		datenpaket.setAbsender(absender);
		assertEquals(absender, datenpaket.getAbsender());
	}
	
	@Test
	public void testGetVermittler() {
		String vermittler = "08/15";
		datenpaket.setVermittler(vermittler);
		assertEquals(vermittler, datenpaket.getVermittler());
	}

}
