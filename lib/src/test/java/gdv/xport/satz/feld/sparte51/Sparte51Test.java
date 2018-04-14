package gdv.xport.satz.feld.sparte51;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.satz.feld.AbstractFeldTest;
import gdv.xport.satz.model.SatzX;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Diese Klasse testet den Zugriff auf Felder via Enums fuer die Sparte 51.
 * 
 * @author Ken Schosinsky
 * @since 3.1.0 (08.04.2018)
 */
@RunWith(SmokeRunner.class)
public class Sparte51Test extends AbstractFeldTest {

    @Test
    public void testFeld220() {
        // Feld220.TEILDATENSATZ2 nur als Referenz zu Feld220Teil2.
        Feld220[] felderExklusiveTeildatensatz2 = Arrays.stream(Feld220.values())
                .filter(feld -> !Feld220.TEILDATENSATZ2.equals(feld))
                .toArray(Feld220[]::new);
        checkEntries(new SatzX(220, Feld220.values()), felderExklusiveTeildatensatz2);
    }

    @Test
    public void testFeld221() {
        checkEntries(new SatzX(221, Feld221.values()), Feld221.values());
    }

}
