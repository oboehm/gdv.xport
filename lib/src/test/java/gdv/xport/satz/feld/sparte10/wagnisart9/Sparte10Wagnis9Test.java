package gdv.xport.satz.feld.sparte10.wagnisart9;

import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.satz.feld.AbstractFeldTest;
import gdv.xport.satz.model.SatzX;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Diese Klasse testet den Zugriff auf Felder via Enums fuer die Sparte 10, Wagnisart 9.
 * 
 * @author Ken Schosinsky
 * @since 3.1.0 (08.04.2018)
 */
@RunWith(SmokeRunner.class)
public class Sparte10Wagnis9Test extends AbstractFeldTest {

    @Test
    public void testFeld220Wagnis9() {
        checkEntries(new SatzX(220, Feld220Wagnis9.values()), Feld220Wagnis9.values());
    }

    @Test
    public void testFeld220Wagnis9Auszahlungen() {
        checkEntries(new SatzX(220, Feld220Wagnis9Auszahlungen.values()), Feld220Wagnis9Auszahlungen.values());
    }

    @Test
    public void testFeld220Wagnis9Bezugsrechte() {
        checkEntries(new SatzX(220, Feld220Wagnis9Bezugsrechte.values()), Feld220Wagnis9Bezugsrechte.values());
    }

    @Test
    public void testFeld220Wagnis9Wertungssummen() {
        checkEntries(new SatzX(220, Feld220Wagnis9Wertungssummen.values()), Feld220Wagnis9Wertungssummen.values());
    }

    @Test
    public void testFeld220Wagnis9ZukSummenaenderungen() {
        checkEntries(new SatzX(220, Feld220Wagnis9ZukSummenaenderungen.values()),
                Feld220Wagnis9ZukSummenaenderungen.values());
    }

    @Test
    public void testFeld230() {
        checkEntries(new SatzX(230, Feld230.values()), Feld230.values());
    }

}
