package gdv.xport.satz.feld.sparte40;

import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.satz.feld.AbstractFeldTest;
import gdv.xport.satz.model.SatzX;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Diese Klasse testet den Zugriff auf Felder via Enums fuer die Sparte 40.
 * 
 * @author Ken Schosinsky
 * @since 3.1.0 (08.04.2018)
 */
@RunWith(SmokeRunner.class)
public class Sparte40Test extends AbstractFeldTest {

    @Test
    public void testFeld210() {
        checkEntries(new SatzX(210, Feld210.values()), Feld210.values());
    }

    @Test
    public void testFeld211() {
        checkEntries(new SatzX(211, Feld211.values()), Feld211.values());
    }

    @Test
    public void testFeld220() {
        checkEntries(new SatzX(220, Feld220.values()), Feld220.values());
    }

    @Test
    public void testFeld221() {
        checkEntries(new SatzX(221, Feld221.values()), Feld221.values());
    }

}
