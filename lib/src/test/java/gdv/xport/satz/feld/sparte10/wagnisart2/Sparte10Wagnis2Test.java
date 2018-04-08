package gdv.xport.satz.feld.sparte10.wagnisart2;

import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.satz.feld.AbstractFeldTest;
import gdv.xport.satz.model.SatzX;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Diese Klasse testet den Zugriff auf Felder via Enums fuer die Sparte 10, Wagnisart 2.
 * 
 * @author Ken Schosinsky
 * @since 3.1.0 (08.04.2018)
 */
@RunWith(SmokeRunner.class)
public class Sparte10Wagnis2Test extends AbstractFeldTest {

    @Test
    public void testFeld220Wagnis2() {
        checkEntries(new SatzX(220, Feld220Wagnis2.values()), Feld220Wagnis2.values());
    }

    @Test
    public void testFeld220Wagnis2Auszahlungen() {
        checkEntries(new SatzX(220, Feld220Wagnis2Auszahlungen.values()), Feld220Wagnis2Auszahlungen.values());
    }

    @Test
    public void testFeld220Wagnis2Bezugsrechte() {
        checkEntries(new SatzX(220, Feld220Wagnis2Bezugsrechte.values()), Feld220Wagnis2Bezugsrechte.values());
    }

    @Test
    public void testFeld220Wagnis2Wertungssummen() {
        checkEntries(new SatzX(220, Feld220Wagnis2Wertungssummen.values()), Feld220Wagnis2Wertungssummen.values());
    }

    @Test
    public void testFeld220Wagnis2ZukSummenaenderungen() {
        checkEntries(new SatzX(220, Feld220Wagnis2ZukSummenaenderungen.values()),
                Feld220Wagnis2ZukSummenaenderungen.values());
    }

    @Test
    public void testFeld221Wagnis2() {
        checkEntries(new SatzX(221, Feld221Wagnis2.values()), Feld221Wagnis2.values());
    }

    @Test
    public void testFeld221Wagnis2Auszahlungen() {
        checkEntries(new SatzX(221, Feld221Wagnis2Auszahlungen.values()), Feld221Wagnis2Auszahlungen.values());
    }

    @Test
    public void testFeld221Wagnis2ZukSummenaenderungen() {
        checkEntries(new SatzX(221, Feld221Wagnis2ZukSummenaenderungen.values()),
                Feld221Wagnis2ZukSummenaenderungen.values());
    }
    
}
