/*
 * $Id$
 *
 * Copyright (c) 2009 - 2012 by Oli B.
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
 * (c)reated 04.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import gdv.xport.config.Config;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.satz.feld.Feld0001;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author oliver
 * @since 04.10.2009
 * @version $Revision$
 *
 */
public final class VorsatzTest extends AbstractSatzTest {

    private final Vorsatz vorsatz = new Vorsatz();

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Vorsatz();
    }

    /**
     * Test method for {@link gdv.xport.satz.Vorsatz#Vorsatz()}.
     *
     * @throws IOException falls der Export schief geht
     */
    @Test
    public void testVorsatz() throws IOException {
        String expected = "0001" + Config.getVUNummer().getInhalt();
        checkExport(1, 9, expected);
        checkExport(257, 265, expected);
        checkExport(256+246, 256+256, "          2");
        checkExport(225, 227, "1.1");
        checkExport(768, 768, "3");
    }

    /**
     * Wird das Absender-Feld richtig gesetzt? Schau 'mer mal.
     *
     * @throws IOException falls der Export schief geht
     */
    @Test
    public void testSetAbsender() throws IOException {
        String absender = "agentes AG                    ";
        vorsatz.setAbsender(absender.trim());
        Feld absenderFeld = vorsatz.getFeld(Bezeichner.ABSENDER);
        assertEquals(absenderFeld.getInhalt().trim(), vorsatz.getAbsender());
        checkExport(10, 39, absender);
    }

    /**
     * Hier wird das Start- und End-Datum ueberprueft.
     *
     * @throws IOException falls der Export schief geht
     */
    @Test
    public void testSetErstellungsZeitraum() throws IOException {
        String startDatum = "01011900";
        String endDatum = "09102009";
        vorsatz.setErstellungsZeitraum(startDatum, endDatum);
        checkExport(70, 85, startDatum + endDatum);
    }

    /**
     * Hier ueberpruefen wir den Export.
     * Damit ein Datensatz auch 256 Bytes lang ist, setzen wir das
     * EOD-Zeichen auf nichts ("").
     *
     * @param startByte beginnend bei 1
     * @param endByte   beginnend bei 1
     * @param expected erwartetes Ergebnis
     * @throws IOException falls was schief geht
     */
    private void checkExport(final int startByte, final int endByte, final String expected) throws IOException {
        Config.setEOD("");
        super.checkExport(this.vorsatz, startByte, endByte, expected, 768);
    }

    /**
     * Hier testen wir den Import.
     */
    @Test
    public void testImport() {
        String content = vorsatz.toLongString();
        Vorsatz imported = new Vorsatz(content);
        assertEquals(content, imported.toLongString());
        assertEquals(vorsatz, imported);
    }

    /**
     * Zum Testen verwenden wir hier die Musterdatei.
     *
     * @throws IOException falls die Musterdatei nicht importiert werden kann
     */
    @Test
    public void testImportReader() throws IOException {
        try (InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt")) {
            vorsatz.importFrom(istream);
            assertTrue(vorsatz + " should be valid", vorsatz.isValid());
            assertEquals("9999", vorsatz.getVuNummer());
            assertEquals("XXX Versicherung AG", vorsatz.getAbsender());
            assertEquals("BRBRIENNEE,J\u00dcRGEN", vorsatz.getAdressat());
        }
    }

    /**
     * Zum Testen nehmen wir hier den Vorsatz aus der Musterdatei, allerdings
     * ohne Umlaute.
     *
     * @throws IOException falls der Im- oder Export schief geht
     */
    @Test
    public void testExport() throws IOException {
        String input = "00019999 XXX Versicherung AG           BRBRIENNEE,JURGEN        "
            + "     220720042207200499990099991.91.91.92.12.12.12.12.1   1.51.3"
            + "1.62.0      1.51.4                                              "
            + "                                1.1         1 0000     Z0ZAG0011"
            + "\n"
            + "00019999 XXX Versicherung AG           BRBRIENNEE,JURGEN        "
            + "     220720042207200499990099991.01.01.01.01.0      1.01.01.1   "
            + "   1.01.0                                                       "
            + "                                                       Z0ZAG0022"
            + "\n";
        vorsatz.importFrom(input);
        StringWriter swriter = new StringWriter(input.length());
        Config.setEOD("\n");
        vorsatz.export(swriter);
        swriter.close();
        assertEquals(input, swriter.toString());
    }

    /**
     * Test-Methode fuer {@link Vorsatz#setAdressat(String)}.
     */
    @Test
    public void testSetAdressat() {
        String adressat = "Obelix";
        vorsatz.setAdressat(adressat);
        assertEquals(adressat, vorsatz.getAdressat());
    }

    /**
     * Testfall fuer Issue 10
     * (https://github.com/oboehm/gdv.xport/issues/10).
     */
    @Test
    public void testIssue10() {
        Feld a1 = vorsatz.getFeld(Bezeichner.ART_DES_ADRESSATEN);
        Feld a2 = vorsatz.getFeld(Feld0001.ART_DES_ADRESSATEN);
        assertEquals(a1, a2);
    }

}
