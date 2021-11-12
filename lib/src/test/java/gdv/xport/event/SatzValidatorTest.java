/*
 * Copyright 2021 Optica Abrechnungszentrum Dr. Gueldener GmbH
 */
package gdv.xport.event;

import gdv.xport.DatenpaketStreamer;
import gdv.xport.feld.Bezeichner;
import gdv.xport.satz.Satz;
import gdv.xport.satz.xml.XmlService;
import gdv.xport.util.SatzTyp;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit-Test fuer gdv.xport.event.SatzValidator.
 *
 * @author oboehm
 * @since 12.11.21
 */
public class SatzValidatorTest {

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
        List<ConstraintViolation> violations = satzValidator.getViolations();
        LOG.info("violations = {}", violations);
        assertFalse(violations.isEmpty());
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