package gdv.xport.satz.feld.sparte10.wagnisart5;

import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.satz.feld.AbstractFeldTest;
import gdv.xport.satz.model.SatzX;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Diese Klasse testet den Zugriff auf Felder via Enums fuer die Sparte 10, Wagnisart 5.
 * 
 * @author Ken Schosinsky
 * @since 3.1.0 (08.04.2018)
 */
@RunWith(SmokeRunner.class)
public class Sparte10Wagnis5Test extends AbstractFeldTest {

    @Test
    public void testFeld220Wagnis5() {
        checkEntries(new SatzX(220, Feld220Wagnis5.values()), Feld220Wagnis5.values());
    }

    @Test
    public void testFeld220Wagnis5Bezugsrechte() {
        checkEntries(new SatzX(220, Feld220Wagnis5Bezugsrechte.values()), Feld220Wagnis5Bezugsrechte.values());
    }

    @Test
    public void testFeld220Wagnis5Wertungssummen() {
        checkEntries(new SatzX(220, Feld220Wagnis5Wertungssummen.values()), Feld220Wagnis5Wertungssummen.values());
    }

    @Test
    public void testFeld220Wagnis5ZukSummenaenderungen() {
        checkEntries(new SatzX(220, Feld220Wagnis5ZukSummenaenderungen.values()),
                Feld220Wagnis5ZukSummenaenderungen.values());
    }

    @Test
    public void testFeld221Wagnis5() {
        checkEntries(new SatzX(221, Feld221Wagnis5.values()), Feld221Wagnis5.values());
    }

    @Test
    public void testFeld221Wagnis5ZukSummenaenderungen() {
        checkEntries(new SatzX(221, Feld221Wagnis5ZukSummenaenderungen.values()),
                Feld221Wagnis5ZukSummenaenderungen.values());
    }

}
