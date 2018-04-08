package gdv.xport.satz.feld.sparte10.wagnisart48;

import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.satz.feld.AbstractFeldTest;
import gdv.xport.satz.model.SatzX;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Diese Klasse testet den Zugriff auf Felder via Enums fuer die Sparte 10, Wagnisart 4 und 8.
 * 
 * @author Ken Schosinsky
 * @since 3.1.0 (08.04.2018)
 */
@RunWith(SmokeRunner.class)
public class Sparte10Wagnis48Test extends AbstractFeldTest {

    @Test
    public void testFeld220Wagnis48() {
        checkEntries(new SatzX(220, Feld220Wagnis48.values()), Feld220Wagnis48.values());
    }

    @Test
    public void testFeld220Wagnis48Bezugsrechte() {
        checkEntries(new SatzX(220, Feld220Wagnis48Bezugsrechte.values()), Feld220Wagnis48Bezugsrechte.values());
    }

    @Test
    public void testFeld220Wagnis48Wertungssummen() {
        checkEntries(new SatzX(220, Feld220Wagnis48Wertungssummen.values()), Feld220Wagnis48Wertungssummen.values());
    }

    @Test
    public void testFeld220Wagnis48ZukSummenaenderungen() {
        checkEntries(new SatzX(220, Feld220Wagnis48ZukSummenaenderungen.values()),
                Feld220Wagnis48ZukSummenaenderungen.values());
    }

    @Test
    public void testFeld221Wagnis48() {
        checkEntries(new SatzX(221, Feld221Wagnis48.values()), Feld221Wagnis48.values());
    }

}
