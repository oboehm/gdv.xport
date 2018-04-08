package gdv.xport.satz.feld.sparte10.wagnisart6;

import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.satz.feld.AbstractFeldTest;
import gdv.xport.satz.model.SatzX;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Diese Klasse testet den Zugriff auf Felder via Enums fuer die Sparte 10, Wagnisart 6.
 * 
 * @author Ken Schosinsky
 * @since 3.1.0 (08.04.2018)
 */
@RunWith(SmokeRunner.class)
public class Sparte10Wagnis6Test extends AbstractFeldTest {

    @Test
    public void testFeld220Wagnis6() {
        checkEntries(new SatzX(220, Feld220Wagnis6.values()), Feld220Wagnis6.values());
    }

    @Test
    public void testFeld220Wagnis6Bezugsrechte() {
        checkEntries(new SatzX(220, Feld220Wagnis6Bezugsrechte.values()), Feld220Wagnis6Bezugsrechte.values());
    }

    @Test
    public void testFeld220Wagnis6Wertungssummen() {
        checkEntries(new SatzX(220, Feld220Wagnis6Wertungssummen.values()), Feld220Wagnis6Wertungssummen.values());
    }

    @Test
    public void testFeld220Wagnis6ZukSummenaenderungen() {
        checkEntries(new SatzX(220, Feld220Wagnis6ZukSummenaenderungen.values()),
                Feld220Wagnis6ZukSummenaenderungen.values());
    }

    @Test
    public void testFeld221Wagnis6() {
        checkEntries(new SatzX(221, Feld221Wagnis6.values()), Feld221Wagnis6.values());
    }

    @Test
    public void testFeld221Wagnis6ZukSummenaenderungen() {
        checkEntries(new SatzX(221, Feld221Wagnis6ZukSummenaenderungen.values()),
                Feld221Wagnis6ZukSummenaenderungen.values());
    }

}
