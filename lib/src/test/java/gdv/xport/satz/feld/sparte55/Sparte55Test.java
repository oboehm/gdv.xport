package gdv.xport.satz.feld.sparte55;

import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.satz.feld.AbstractFeldTest;
import gdv.xport.satz.model.SatzX;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Diese Klasse testet den Zugriff auf Felder via Enums fuer die Sparte 55.
 * 
 * @author Ken Schosinsky
 * @since 3.1.0 (16.04.2018)
 */
@RunWith(SmokeRunner.class)
public class Sparte55Test extends AbstractFeldTest {

    @Test
    public void testFeld220() {
        checkEntries(new SatzX(220, Feld220.values()), Feld220.values());
    }
    
    @Test
    public void testFeld221() {
        checkEntries(new SatzX(221, Feld221.values()), Feld221.values());
    }

}
