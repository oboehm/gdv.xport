/*
 * Copyright (c) 2009 - 2014 by Oli B. Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express orimplied. See the License for the specific language
 * governing permissions and limitations under the License. (c)reated 23.10.2009
 * by Oli B. (ob@aosd.de)
 */

package gdv.xport;

import static org.junit.Assert.assertTrue;
import gdv.xport.event.ImportStatistic;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import patterntesting.runtime.annotation.IntegrationTest;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Unit-Tests fuer {@link DatenpaketStreamer}.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (14.02.2014)
 */
@RunWith(SmokeRunner.class)
public final class DatenpaketStreamerTest {

    private static final Logger LOG = LogManager.getLogger(DatenpaketStreamerTest.class);

    /**
     * Test-Methode fuer {@link DatenpaketStreamer#readDatenpaket()}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @IntegrationTest
    @Test
    public void testReadDatenpaket() throws IOException {
        InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt");
        try {
            readDatenpaket(istream);
        } finally {
            istream.close();
        }
    }

    /**
     * Test-Methode fuer {@link DatenpaketStreamer#readDatenpaket()}. Dies ist
     * (fast) der gleiche Test wie vorher, nur dass wir hier jetzt 2 Datenpkete
     * lesen.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @IntegrationTest
    @Test
    public void testRead2Datenpakete() throws IOException {
        InputStream istream = this.getClass().getResourceAsStream("/zwei_datenpakete.txt");
        try {
            readDatenpaket(istream);
            readDatenpaket(istream);
        } finally {
            istream.close();
        }
    }

    private static void readDatenpaket(InputStream istream) throws IOException {
        ImportStatistic statistic = new ImportStatistic();
        DatenpaketStreamer streamer = new DatenpaketStreamer(istream);
        streamer.register(statistic);
        streamer.readDatenpaket();
        LOG.info("Statistik: " + statistic);
        assertTrue("expected: number of imported saetze > 2", statistic.getImportedSaetze() > 2);
    }

}
