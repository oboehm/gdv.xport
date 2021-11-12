/*
 * Copyright 2021 Optica Abrechnungszentrum Dr. Gueldener GmbH
 */
package gdv.xport.util;

import gdv.xport.feld.VUNummer;
import gdv.xport.satz.Vorsatz;
import net.sf.oval.ConstraintViolation;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit-Test fuer gdv.xport.util.SimpleConstraintViolation ...
 *
 * @author oboehm
 * @since 12.11.21
 */
public class SimpleConstraintViolationTest {

    @Test
    public void testGetViolations() {
        ConstraintViolation cv =
                new SimpleConstraintViolation(new VUNummer("04711"), new IllegalStateException("aaaaargh"));
        List<ConstraintViolation> violations = Collections.singletonList(cv);
        SimpleConstraintViolation simple = new SimpleConstraintViolation(new Vorsatz(), violations);
        assertEquals(violations, simple.getViolations());
    }

}