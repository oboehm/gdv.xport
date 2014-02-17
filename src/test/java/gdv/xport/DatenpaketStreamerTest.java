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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

import patterntesting.runtime.annotation.IntegrationTest;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Unit-Tests fuer {@link DatenpaketStreamer}.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 0.9.4 (14.02.2014)
 */
@RunWith(SmokeRunner.class)
public class DatenpaketStreamerTest {

    private static final Log log = LogFactory.getLog(DatenpaketStreamerTest.class);

    /**
     * Test-Methode fuer {@link DatenpaketStreamer#readDatenpaket()}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @IntegrationTest
    @Test
    public void testReadDatenpaket() throws IOException {
        ImportStatistic statistic = new ImportStatistic();
        InputStream istream = this.getClass().getResourceAsStream("/musterdatei_041222.txt");
        try {
            DatenpaketStreamer streamer = new DatenpaketStreamer(istream);
            streamer.register(statistic);
            streamer.readDatenpaket();
            log.info("Statistik: " + statistic);
            assertTrue("expected: number of imported saetze > 2", statistic.getImportedSaetze() > 2);
        } finally {
            istream.close();
        }
    }

}
