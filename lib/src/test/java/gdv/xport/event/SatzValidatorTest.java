/*
 * Copyright (c) 2021-2023 by Oli B.
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
 * (c)reated 12-11-2021 by $USER
 */
package gdv.xport.event;

import gdv.xport.DatenpaketStreamer;
import gdv.xport.feld.Bezeichner;
import gdv.xport.satz.Satz;
import gdv.xport.satz.xml.XmlService;
import gdv.xport.util.SatzTyp;
import io.github.netmikey.logunit.api.LogCapturer;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit-Test fuer gdv.xport.event.SatzValidator.
 *
 * @author oboehm
 * @since 12.11.21
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class SatzValidatorTest {

    @RegisterExtension
    LogCapturer logCapturer = LogCapturer.create().captureForType(SatzValidator.class);

    private static final Logger LOG = LogManager.getLogger();
    private final SatzValidator satzValidator = new SatzValidator();

    @Test
    public void testNotice() {
        Satz valide = XmlService.getInstance().getSatzart(SatzTyp.of(100));
        satzValidator.notice(valide);
        List<ConstraintViolation> violations = satzValidator.getViolations();
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testNoticeWithViolations() {
        Satz kaputt = XmlService.getInstance().getSatzart(SatzTyp.of(100));
        kaputt.setFeld(Bezeichner.SATZNUMMER, "x");
        satzValidator.notice(kaputt);
        checkLogMessages("keine Zahl");
        List<ConstraintViolation> violations = satzValidator.getViolations();
        LOG.info("violations = {}", violations);
        assertFalse(violations.isEmpty());
    }

    private void checkLogMessages(String expected) {
        if (logCapturer.getEvents().isEmpty()) {
            LOG.info("Noch keine Log-Events aufgezeichet...");
        } else {
            LOG.info("Pruefe Log-Meldungen...");
            logCapturer.assertContains(expected);
        }
    }

    @Test
    public void testStreaming() throws IOException {
        try (InputStream istream = DatenpaketStreamer.class.getResourceAsStream("/zwei_datenpakete.txt")) {
            DatenpaketStreamer streamer = new DatenpaketStreamer(istream);
            streamer.register(satzValidator);
            while (streamer.canReadDatenpaket()) {
                streamer.readDatenpaket();
            }
            List<ConstraintViolation> violations = satzValidator.getViolations();
            LOG.info("violations = {}", violations);
            assertFalse(violations.isEmpty());
        }
    }

}