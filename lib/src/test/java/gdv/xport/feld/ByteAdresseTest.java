package gdv.xport.feld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import patterntesting.runtime.junit.ObjectTester;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-Tests fuer gdv.xport.feld.ByteAdresse.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
public final class ByteAdresseTest {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    public void testAdresseNull() {
        assertThrows(IllegalArgumentException.class, () ->
            ByteAdresse.of(0));
    }

    @Test
    public void testAdresseEins() {
        assertEquals(1, ByteAdresse.of(1).intValue());
    }

    @Test
    public void testAdresse256() {
        assertEquals(256, ByteAdresse.of(256).intValue());
    }

    @Test
    public void testByteValue() {
        for (int i = 1; i <= 256; i++) {
            ByteAdresse adresse = ByteAdresse.of(i);
            assertEquals(adresse, ByteAdresse.of(adresse.byteValue()));
        }
    }

    @Test
    public void testAdresseZuGross() {
        assertThrows(IllegalArgumentException.class, () ->
            ByteAdresse.of(257));
    }

    @Test
    public void testToString() {
        assertEquals("222", ByteAdresse.of(222).toString());
    }

    @Test
    public void testToMap() {
        ByteAdresse adresse = ByteAdresse.of(128);
        try {
            Map<String, Object> map = adresse.toMap();
            assertThat(map.values(), Matchers.not(empty()));
        } catch (UnsupportedOperationException mayhappen) {
            LOG.info("{}.toMap() schlug fehl:", adresse, mayhappen);
        }
    }

    @Test
    public void testEquals() {
        ByteAdresse a = ByteAdresse.of(11);
        ByteAdresse b = ByteAdresse.of(11);
        ObjectTester.assertEquals(a, b);
    }

    @Test
    public void testEqualsWithInt() {
        int n = 42;
        ByteAdresse a = ByteAdresse.of(n);
        assertTrue(a.equals(n));
    }

}
