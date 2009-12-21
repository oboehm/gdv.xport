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

import java.util.List;

import net.sf.oval.ConstraintViolation;

import org.apache.commons.logging.*;
import org.junit.Test;

/**
 * @author oliver
 * @since 05.10.2009
 * @version $Revision$
 *
 */
public class NumFeldTest {

    private static final Log log = LogFactory.getLog(NumFeldTest.class);

    @Test
    public void testNumFeld() {
        NumFeld nummer = new NumFeld("Feld X", 4, 1);
        assertEquals("0000", nummer.getInhalt());
    }

    /**
     * Test method for {@link gdv.xport.feld.NumFeld#setInhalt(int)}.
     */
    @Test
    public void testSetInhaltInt() {
        NumFeld nummer = new NumFeld("Feld X", "0001");
        nummer.setInhalt(2);
        assertEquals("0002", nummer.getInhalt());
    }

    @Test
    public void testIsValid() {
        NumFeld x = new NumFeld("x", "xxxx");
        assertFalse(x + " is invalid", x.isValid());
    }

    @Test
    public void testValidate() {
        NumFeld x = new NumFeld("x", "xxxx");
        List<ConstraintViolation> violations = x.validate();
        for (ConstraintViolation violation : violations) {
            log.info(violation.getValidatedObject() + ": " + violation.getMessage());
        }
        assertEquals(1, violations.size());
    }
    
    /**
     * Eine Zahl mit Nachkommastellen sollte auch als Double ausgegeben werden
     * koennen. Hier probieren wir es noch mit 0 Nachkommastellen.
     * 
     * @since 0.4
     */
    @Test
    public void testIntAsDouble() {
        NumFeld x = new NumFeld("x", "1");
        assertEquals(1.0, x.toDouble(), 0.01);
    }
    
    /**
     * Und hier testen wir eine Zahl mit 2 Nachkommastellen, ob sie korrekt
     * umgewandelt wird.
     * 
     * @since 0.4
     */
    @Test
    public void testToDouble() {
        NumFeld pi = new NumFeld("pi", "314", 2);
        assertEquals(3.14, pi.toDouble(), 0.001);
    }

}
