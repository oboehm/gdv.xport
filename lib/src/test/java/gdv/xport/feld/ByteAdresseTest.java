package gdv.xport.feld;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit-Tests fuer gdv.xport.feld.ByteAdresse.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
public final class ByteAdresseTest {

    @Test(expected = IllegalArgumentException.class)
    public void testAdresseNull() {
        ByteAdresse.of(0);
    }

    @Test
    public void testAdresseEins() {
        assertEquals(1, ByteAdresse.of(1).intValue());
    }

    @Test
    public void testAdresse256() {
        assertEquals(256, ByteAdresse.of(256).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAdresseZuGross() {
       ByteAdresse.of(257);
    }

}
