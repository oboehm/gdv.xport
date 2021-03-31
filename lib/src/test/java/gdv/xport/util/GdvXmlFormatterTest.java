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
 * (c)reated 27.03.21 by oliver (ob@oasd.de)
 */
package gdv.xport.util;

import gdv.xport.satz.Satz;
import gdv.xport.satz.xml.SatzXml;
import gdv.xport.satz.xml.XmlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit-Tests fuer gdv.xport.util.GdvXmlFormatter.
 *
 * @author oliver
 */
public final class GdvXmlFormatterTest {

    private static final Logger LOG = LogManager.getLogger();

    @Test
    public void testWriteSatz() throws IOException {
        try (StringWriter writer = new StringWriter()) {
            GdvXmlFormatter formatter = new GdvXmlFormatter(writer);
            Satz satz100 = XmlService.getInstance().getSatzart(SatzTyp.of(100));
            formatter.write(satz100);
            assertThat(writer.toString().trim(), startsWith("<satzart"));
            LOG.info(writer.toString());
        }
    }

    @Test
    public void testCreateSatzFromOutput() throws IOException, XMLStreamException {
        Satz adressteil = XmlService.getInstance().getSatzart(SatzTyp.of(102));
        File output = new File("target", "satz102.xml");
        formatTo(output, adressteil);
        SatzXml generated = SatzXml.of(output);
        assertNotNull(generated);
        assertEquals(adressteil.getNumberOfTeildatensaetze(), generated.getNumberOfTeildatensaetze());
    }

    private void formatTo(File output, Satz satz) throws IOException {
        try (FileOutputStream ostream = new FileOutputStream(output)) {
            GdvXmlFormatter formatter = new GdvXmlFormatter(ostream);
            formatter.write(satz);
        }
    }

}