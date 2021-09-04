/*
 * Copyright (c) 2021 by Oliver Boehm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 04.09.21 by oliver (ob@oasd.de)
 */
package gdv.xport.feld;

import gdv.xport.Datenpaket;
import gdv.xport.io.PushbackLineNumberReader;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.util.SatzRegistry;
import gdv.xport.util.SatzTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit-Tests fuer {@link Satznummer}.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
@RunWith(Parameterized.class)
public final class SatznummerTest {

    private static final Logger LOG = LogManager.getLogger();
    private static final SatzRegistry SATZ_REGISTRY = SatzRegistry.getInstance();

    private final SatzTyp satzTyp;

    public SatznummerTest(SatzTyp satzTyp) {
        this.satzTyp = satzTyp;
    }

    @Parameterized.Parameters(name = "Satzart {0}")
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        Datenpaket datenpaket = SATZ_REGISTRY.getAllSupportedSaetze();
        for (Satz satz : datenpaket.getDatensaetze()) {
            data.add(new Object[] { satz.getSatzTyp() });
        }
        return data;
    }

    @Test
    public void testReadSatznummer() throws IOException {
        checkReadSatznummer(satzTyp);
    }

    private static void checkReadSatznummer(SatzTyp satzTyp) throws IOException {
        Satz satz = SATZ_REGISTRY.getSatz(satzTyp);
        for (Teildatensatz tds : satz.getTeildatensaetze()) {
            if (tds.hasFeld(Bezeichner.SATZNUMMER)) {
                checkReadSatznummer(tds);
            } else {
                LOG.info("{}: keine Satznummer", tds.toShortString());
            }
        }
    }

    private static void checkReadSatznummer(Teildatensatz tds) throws IOException {
        String content = tds.toLongString();
        try (Reader reader = new StringReader(content);
             PushbackLineNumberReader pushbackReader = new PushbackLineNumberReader(reader, 256)) {
            Satznummer satznummer = Satznummer.readSatznummer(pushbackReader, tds);
            LOG.info("{}: {}", tds.toShortString(), satznummer);
            assertEquals(tds.getSatznummer(), satznummer);
        }
    }

}