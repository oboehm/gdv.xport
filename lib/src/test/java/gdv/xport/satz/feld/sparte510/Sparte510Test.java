package gdv.xport.satz.feld.sparte510;

import org.junit.Test;
import org.junit.runner.RunWith;

import gdv.xport.satz.feld.AbstractFeldTest;
import gdv.xport.satz.model.SatzX;
import patterntesting.runtime.junit.SmokeRunner;

@RunWith(SmokeRunner.class)
public class Sparte510Test  extends AbstractFeldTest {
    
    @Test
    public void testFeld210() {
        checkEntries(new SatzX(210, Feld210.values()), Feld210.values());
    }

    @Test
    public void testFeld220() {
        checkEntries(new SatzX(220, Feld220.values()), Feld220.values());
    }

}
