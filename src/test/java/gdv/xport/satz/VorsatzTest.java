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

import static org.junit.Assert.*;
import gdv.xport.feld.*;

import java.io.*;

import org.junit.Test;

/**
 * @author oliver
 * @since 04.10.2009
 * @version $Revision$
 *
 */
public final class VorsatzTest extends AbstractSatzTest {

    private Vorsatz vorsatz = new Vorsatz();

    /**
     * Test method for {@link gdv.xport.satz.Vorsatz#Vorsatz()}.
     * @throws IOException falls ser Export schief geht
     */
    @Test
    public void testVorsatz() throws IOException {
        String expected = "0001" + VU_NUMMER.getInhalt();
        checkExport(1, 9, expected);
        checkExport(257, 265, expected);
        checkExport(256+246, 256+256, "          2");
        checkExport(225, 227, "1.1");
        checkExport(768, 768, "3");
    }

    @Test
    public void testSetAbsender() throws IOException {
        String absender = "agentes AG                    ";
        vorsatz.setAbsender(absender.trim());
        Feld absenderFeld = vorsatz.getFeld(Bezeichner.ABSENDER);
        assertEquals(absenderFeld.getInhalt(), vorsatz.getAbsender());
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
        super.checkExport(this.vorsatz, startByte, endByte, expected, 768);
    }

    @Test
    public void testImport() {
        String content = vorsatz.toLongString();
        Vorsatz imported = new Vorsatz(content);
        assertEquals(content, imported.toLongString());
        assertEquals(vorsatz, imported);
    }

    @Test
    public void testImportReader() throws IOException {
        InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt");
        try {
            vorsatz.importFrom(istream);
            assertTrue(vorsatz + " should be valid", vorsatz.isValid());
            assertEquals("9999", vorsatz.getVuNummer());
            assertEquals("XXX Versicherung AG", vorsatz.getAbsender());
            assertEquals("BRBRIENNEE,J\u00dcRGEN", vorsatz.getAdressat());
        } finally {
            istream.close();
        }
    }

}


/*
 * $Log$
 * $Source$
 */
