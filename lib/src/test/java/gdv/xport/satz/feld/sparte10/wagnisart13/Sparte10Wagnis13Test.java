package gdv.xport.satz.feld.sparte10.wagnisart13;

import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.satz.feld.AbstractFeldTest;
import gdv.xport.satz.model.SatzX;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Diese Klasse testet den Zugriff auf Felder via Enums fuer die Sparte 10, Wagnisart 1 und 3.
 * 
 * @author Ken Schosinsky
 * @since 3.1.0 (08.04.2018)
 */
@RunWith(SmokeRunner.class)
public class Sparte10Wagnis13Test extends AbstractFeldTest {

    @Test
    public void testFeld220Wagnis13() {
        checkEntries(new SatzX(220, Feld220Wagnis13.values()), Feld220Wagnis13.values());
    }

    @Test
    public void testFeld220Wagnis13Auszahlungen() {
        checkEntries(new SatzX(220, Feld220Wagnis13Auszahlungen.values()), Feld220Wagnis13Auszahlungen.values());
    }

    @Test
    public void testFeld220Wagnis13Bezugsrechte() {
        checkEntries(new SatzX(220, Feld220Wagnis13Bezugsrechte.values()), Feld220Wagnis13Bezugsrechte.values());
    }

    @Test
    public void testFeld220Wagnis13Wertungssummen() {
        checkEntries(new SatzX(220, Feld220Wagnis13Wertungssummen.values()), Feld220Wagnis13Wertungssummen.values());
    }

    @Test
    public void testFeld220Wagnis13ZukSummenaenderungen() {
        checkEntries(new SatzX(220, Feld220Wagnis13ZukSummenaenderungen.values()),
                Feld220Wagnis13ZukSummenaenderungen.values());
    }

    @Test
    public void testFeld221Wagnis13() {
        checkEntries(new SatzX(221, Feld221Wagnis13.values()), Feld221Wagnis13.values());
    }

    @Test
    public void testFeld221Wagnis13Auszahlungen() {
        checkEntries(new SatzX(221, Feld221Wagnis13Auszahlungen.values()), Feld221Wagnis13Auszahlungen.values());
    }

    @Test
    public void testFeld221Wagnis13ZukSummenaenderungen() {
        checkEntries(new SatzX(221, Feld221Wagnis13ZukSummenaenderungen.values()),
                Feld221Wagnis13ZukSummenaenderungen.values());
    }

}
