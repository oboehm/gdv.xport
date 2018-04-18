package gdv.xport.satz.feld.sparte51;

import java.io.IOException;

import org.junit.Test;

import gdv.xport.feld.Bezeichner;
import gdv.xport.satz.AbstractDatensatzTest;
import gdv.xport.satz.Satz;
import gdv.xport.satz.model.Satz220;
import gdv.xport.satz.model.SatzX;

/**
 * Unit-Tests fuer {@link Feld220}.
 *
 * @author Ken Schosinsky
 * @since 17.04.2018
 */
public class Feld220Test extends AbstractDatensatzTest {

    private final SatzX satz = new SatzX(220, Feld220.values());

    @Override
    protected Satz getSatz() {
        return new Satz220(51);
    }
    
    @Test
    public void testKhDeckungssummenInWaehrungseinheiten() throws IOException {
        satz.get(Bezeichner.KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL1);
        satz.get(Bezeichner.KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL2);
        satz.get(Bezeichner.KH_DECKUNGSSUMMEN_IN_WAEHRUNGSEINHEITEN_TEIL3);
    }

}
