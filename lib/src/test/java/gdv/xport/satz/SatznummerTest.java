package gdv.xport.satz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Testklasse fuer {@link Datensatz#readSatznummer(char[])} in Satzarten 0210,
 * 0211, 0220, 0221, 0250 und 0251.
 * 
 * @author Ken Schosinsky
 */
@RunWith(Parameterized.class)
public class SatznummerTest {
    private static final Logger LOG = LogManager.getLogger(SatznummerTest.class);

    private static final String LINE = "                                                                "
            + "                                                                "
            + "                                                                "
            + "                                                                ";

    @Parameterized.Parameters(name = "Satz {0}, Pos. {1}")
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<>();

        // Satzart 0210
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.000.htm
        data.add(new Object[] { "0210      000", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.560.htm
        data.add(new Object[] { "0210      560", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.570.htm
        data.add(new Object[] { "0210      570", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.580.htm
        data.add(new Object[] { "0210      580", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.080.htm
        data.add(new Object[] { "0210      080", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.110.htm
        data.add(new Object[] { "0210      110", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.040.htm
        data.add(new Object[] { "0210      040", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.550.htm
        data.add(new Object[] { "0210      550", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.050.htm
        data.add(new Object[] { "0210      050", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.020.htm
        data.add(new Object[] { "0210      020", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.684.htm
        data.add(new Object[] { "0210      684", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.010.htm
        data.add(new Object[] { "0210      010", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.070.htm
        data.add(new Object[] { "0210      070", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.170.htm
        data.add(new Object[] { "0210      170", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.190.htm
        data.add(new Object[] { "0210      190", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.030.htm
        data.add(new Object[] { "0210      030", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.140.htm
        data.add(new Object[] { "0210      140", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.130.htm
        data.add(new Object[] { "0210      130", 250, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0210.510.htm
        data.add(new Object[] { "0210      510", 255, 'F' });

        // Satzart 211
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0211.000.htm
        data.add(new Object[] { "0211      000", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0211.080.htm
        data.add(new Object[] { "0211      080", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0211.110.htm
        data.add(new Object[] { "0211      110", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0211.040.htm
        data.add(new Object[] { "0211      040", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0211.050.htm
        data.add(new Object[] { "0211      050", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0211.010.htm
        data.add(new Object[] { "0211      010", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0211.170.htm
        data.add(new Object[] { "0211      170", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0211.190.htm
        data.add(new Object[] { "0211      190", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0211.140.htm
        data.add(new Object[] { "0211      140", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0211.130.htm
        data.add(new Object[] { "0211      130", 255, 'F' });

        // Satzart 220
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.000.htm
        data.add(new Object[] { "0220      000", 46, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.560.htm
        data.add(new Object[] { "0220      560", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.570.htm
        data.add(new Object[] { "0220      570", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.580.01.htm
        data.add(new Object[] { "0220      580.01", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.580.2.htm
        data.add(new Object[] { "0220      580.2", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.080.htm
        data.add(new Object[] { "0220      080", 48, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.110.htm
        data.add(new Object[] { "0220      110", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.040.htm
        data.add(new Object[] { "0220      040", 50, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.550.htm
        data.add(new Object[] { "0220      550", 42, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.051.htm
        data.add(new Object[] { "0220      051", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.052.htm
        data.add(new Object[] { "0220      052", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.053.htm
        data.add(new Object[] { "0220      053", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.054.htm
        data.add(new Object[] { "0220      054", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.059.htm
        data.add(new Object[] { "0220      059", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.055.htm
        data.add(new Object[] { "0220      055", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.020.1.htm
        data.add(new Object[] { "0220      020                                  1", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.020.2.htm
        data.add(new Object[] { "0220      020                                  2", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.020.3.htm
        data.add(new Object[] { "0220      020                                  3", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.684.htm
        data.add(new Object[] { "0220      684", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.0.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.010.htm
        data.add(new Object[] { "0220      010", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.070.htm
        data.add(new Object[] { "0220      070", 52, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.170.htm
        data.add(new Object[] { "0220      170", 49, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.190.htm
        data.add(new Object[] { "0220      190", 48, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.030.htm
        // TDS 1
        data.add(new Object[] {
                "0220      030                                  1",
                48, '1' });
        // TDS 2
        data.add(new Object[] {
                "0220      030                                  2                                                                                                                                                                                                               X",
                48, '2' });
        // TDS 3
        data.add(new Object[] {
                "0220      030                            3",
                42, '3' });
        // TDS 4
        data.add(new Object[] {
                "0220      030                                  4",
                48, '1' });
        // TDS 9
        data.add(new Object[] {
                "0220      030                                              9",
                59, '9' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.140.htm
        data.add(new Object[] { "0220      140", 50, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.130.htm
        data.add(new Object[] { "0220      130", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0220.510.htm
        data.add(new Object[] { "0220      510", 255, 'F' });

        // Satzart 221
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.000.htm
        data.add(new Object[] { "0221      000", 46, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.080.htm
        data.add(new Object[] { "0221      080", 48, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.110.htm
        data.add(new Object[] { "0221      110", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.040.htm
        data.add(new Object[] { "0221      040", 50, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.051.htm
        data.add(new Object[] { "0221      051", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.052.htm
        data.add(new Object[] { "0221      052", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.053.htm
        data.add(new Object[] { "0221      053", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.054.htm
        data.add(new Object[] { "0221      054", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.059.htm
        data.add(new Object[] { "0221      059", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.055.htm
        data.add(new Object[] { "0221      055", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.13.1.htm
        data.add(new Object[] { "0221      010.13.1", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.13.7.htm
        data.add(new Object[] { "0221      010.13.7", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.13.8.htm
        data.add(new Object[] { "0221      010.13.8", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.2.1.htm
        data.add(new Object[] { "0221      010.2.1", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.2.7.htm
        data.add(new Object[] { "0221      010.2.7", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.2.8.htm
        data.add(new Object[] { "0221      010.2.8", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.48.1.htm
        data.add(new Object[] { "0221      010.48.1", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.5.1.htm
        data.add(new Object[] { "0221      010.5.1", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.5.8.htm
        data.add(new Object[] { "0221      010.5.8", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.6.1.htm
        data.add(new Object[] { "0221      010.6.1", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.6.8.htm
        data.add(new Object[] { "0221      010.6.8", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.7.1.htm
        data.add(new Object[] { "0221      010.7.1", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.010.7.8.htm
        data.add(new Object[] { "0221      010.7.8", 255, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.070.htm
        data.add(new Object[] { "0221      070", 52, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.170.htm
        data.add(new Object[] { "0221      170", 49, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.190.htm
        data.add(new Object[] { "0221      190", 48, 'F' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.030.htm
        // TDS 2
        data.add(new Object[] {
                "0221      030                                  2                                                                                                                                                                                                               X",
                48, '2' });
        // TDS 3
        data.add(new Object[] {
                "0221      030                            3",
                42, '3' });
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0221.140.htm
        data.add(new Object[] { "0221      140", 50, 'F' });

        // Satzart 250
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0250.190.htm
        data.add(new Object[] { "0250      190", 50, 'F' });

        // Satzart 251
        // http://www.gdv-online.de/vuvm/bestand/rel2015/ds0251.190.htm
        data.add(new Object[] { "0251      190", 50, 'F' });

        return data;
    }

    @Parameter
    public String prefix;
    @Parameter(1)
    public int expected;
    @Parameter(2)
    public Character value;

    @Test
    public void testReadSatznummer() throws IOException {
        char[] line = updateLine(LINE.toCharArray(), prefix, expected, value);
        char satznummer = Satz.readSatznummer(line);
        if (satznummer != value) {
            LOG.error("Falsche Satznummer fuer Prefix '" + prefix + "'");
        }
        assertThat(Satz.readSatznummer(line), is(value));
    }

    private char[] updateLine(char[] line, String prefix, int set, Character value) {
        for (int i = 0, s = prefix.length(); i < s; i++) {
            line[i] = prefix.charAt(i);
        }

        line[set] = value;

        return line;
    }

}
