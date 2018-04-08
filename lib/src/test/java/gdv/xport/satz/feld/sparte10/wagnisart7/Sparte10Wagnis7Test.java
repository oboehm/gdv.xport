package gdv.xport.satz.feld.sparte10.wagnisart7;

import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.satz.feld.AbstractFeldTest;
import gdv.xport.satz.model.SatzX;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Diese Klasse testet den Zugriff auf Felder via Enums fuer die Sparte 10, Wagnisart 7.
 * 
 * @author Ken Schosinsky
 * @since 3.1.0 (08.04.2018)
 */
@RunWith(SmokeRunner.class)
public class Sparte10Wagnis7Test extends AbstractFeldTest {

    @Test
    public void testFeld220Wagnis7() {
        checkEntries(new SatzX(220, Feld220Wagnis7.values()), Feld220Wagnis7.values());
    }

    @Test
    public void testFeld220Wagnis7Bezugsrechte() {
        checkEntries(new SatzX(220, Feld220Wagnis7Bezugsrechte.values()), Feld220Wagnis7Bezugsrechte.values());
    }

    @Test
    public void testFeld220Wagnis7Wertungssummen() {
        checkEntries(new SatzX(220, Feld220Wagnis7Wertungssummen.values()), Feld220Wagnis7Wertungssummen.values());
    }

    @Test
    public void testFeld220Wagnis7ZukSummenaenderungen() {
        checkEntries(new SatzX(220, Feld220Wagnis7ZukSummenaenderungen.values()),
                Feld220Wagnis7ZukSummenaenderungen.values());
    }
    @Test
    public void testFeld221Wagnis7() {
        checkEntries(new SatzX(221, Feld221Wagnis7.values()), Feld221Wagnis7.values());
    }

    @Test
    public void testFeld221Wagnis7ZukSummenaenderungen() {
        checkEntries(new SatzX(221, Feld221Wagnis7ZukSummenaenderungen.values()),
                Feld221Wagnis7ZukSummenaenderungen.values());
    }

}
