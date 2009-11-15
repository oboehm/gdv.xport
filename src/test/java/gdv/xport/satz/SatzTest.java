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
 * (c)reated 19.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.satz;

import static org.junit.Assert.*;
import static gdv.xport.feld.Bezeichner.*;

import java.io.*;
import java.util.List;

import gdv.xport.feld.*;

import net.sf.oval.ConstraintViolation;

import org.apache.commons.logging.*;
import org.junit.Test;

/**
 * @author oliver
 * @since 19.10.2009
 * @version $Revision$
 *
 */
public class SatzTest {

    private static final Log log = LogFactory.getLog(SatzTest.class);
    private Satz satz = new Satz(123);

    @Test
    public void testSatz() {
        Satz satz100 = new Satz(100, 1);
        assertEquals(satz100.getSatzart().toInt(), 100);
    }

    /**
     * Test method for {@link gdv.xport.satz.Satz#add(gdv.xport.feld.Feld)}.
     * Falls ein Feld hinzugefuegt wird, das ein anderes Feld (teilweise)
     * ueberschreiben wuerde, sollte eine Exception geworfen werden.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testAdd() {
        satz.add(new AlphaNumFeld(NAME1, 30, 44));
        satz.add(new AlphaNumFeld("Bumm", 4, 50));
    }

    /**
     * Test method for {@link gdv.xport.satz.Satz#set(java.lang.String, java.lang.String)}.
     * Es kann nur ein Feld gesetzt werden, das vorher ueber "add(..)"
     * hinzugefuegt wurde.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testSet() {
        satz.set("gibtsnet", "plopp");
    }

    /**
     * Test method for {@link gdv.xport.satz.Satz#get(java.lang.String)}.
     */
    @Test
    public void testGet() {
        satz.add(new AlphaNumFeld(ORT, 30, 10));
        satz.set(ORT, "Stuttgart");
        assertEquals("Stuttgart", satz.get(ORT).trim());
    }

    /**
     * Test method for {@link gdv.xport.satz.Satz#getFeld(java.lang.String)}.
     * Fuer ein Feld, das nicht existiert, wird NULL_FELD als Ergebnis
     * erwartet.
     */
    @Test
    public void testGetFeld() {
        Feld f = satz.getFeld("hemmernet");
        assertSame(Feld.NULL_FELD, f);
    }

    @Test
    public void testExport() throws IOException {
         StringWriter swriter = new StringWriter(256);
         satz.export(swriter);
         swriter.close();
         String content = swriter.toString();
         assertEquals(256, content.length());
         assertEquals(satz.getSatzart().getInhalt(), content.substring(0, 4));
    }

    @Test
    public void testImport() throws IOException {
        Satz x = new Satz(123);
        x.add(new AlphaNumFeld("F1", 5, 5));
        StringBuffer sbuf = new StringBuffer(256);
        sbuf.append("0123Hello                                                       ");
        sbuf.append("                                                                ");
        sbuf.append("                                                                ");
        sbuf.append("                                                               1");
        assertEquals(256, sbuf.length());
        x.importFrom(sbuf.toString());
        assertEquals(123, x.getSatzart().toInt());
        assertEquals("Hello", x.getFeld("F1").getInhalt());
        assertEquals(sbuf.toString(), x.toLongString());
    }

    @Test
    public void testIsValid() {
        Satz a = new Satz("xxxx", 1);
        assertFalse("Diese Satzart gibt es nicht: " + a, a.isValid());
    }

    @Test
    public void testIsValidWithInvalidFeld() {
        NumFeld schrott = new NumFeld("schrott", "xxxx");
        satz.add(schrott);
        assertFalse(satz + " has invalid fields!", satz.isValid());
    }

    @Test
    public void testValidate() {
        Satz a = new Satz("xxxx", 1);
        assertFalse("Diese Satzart gibt es nicht: " + a, a.isValid());
        List<ConstraintViolation> violations = a.validate();
        for (ConstraintViolation violation : violations) {
            log.info("ConstraintViolation: " + violation);
        }
        assertEquals(2, violations.size());
    }

    @Test
    public void testIsEquals() {
        Satz a = new Satz(123);
        Satz b = new Satz(123);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        b.add(new Feld("c", 5, 'c'));
        assertFalse(a + " differs from " + b, a.equals(b));
    }

}

