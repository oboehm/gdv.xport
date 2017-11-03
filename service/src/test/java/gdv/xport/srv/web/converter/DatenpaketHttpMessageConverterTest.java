package gdv.xport.srv.web.converter;/*
 * Copyright (c) 2017 by Oliver Boehm
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
 * (c)reated 15.10.17 by oliver (ob@oasd.de)
 */

import gdv.xport.*;
import gdv.xport.srv.config.AppConfig;
import org.apache.commons.lang3.*;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.springframework.http.*;
import org.springframework.mock.http.*;

import java.io.*;
import java.nio.charset.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Unit-Tests fuer {@link DatenpaketHttpMessageConverter}-Klasse.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
public final class DatenpaketHttpMessageConverterTest {

    private static final Logger LOG = LogManager.getLogger(DatenpaketHttpMessageConverterTest.class);

    /**
     * Testmethode fuer {@link DatenpaketHttpMessageConverter#supports(Class)}.
     */
    @Test
    public void testSupports() {
        DatenpaketHttpMessageConverter converter = new DatenpaketHttpMessageConverter(MediaType.TEXT_PLAIN);
        assertThat(converter.supports(Datenpaket.class), is(true));
    }

    /**
     * Als Text soll der urspruengliche Input eines Datenpakets wieder
     * ausgegeben werden.
     *
     * @throws IOException sollte nicht passieren
     */
    @Test
    public void testWriteInternalText() throws IOException {
        String output = convertEmptyDatenpaketFor(MediaType.TEXT_PLAIN);
        assertThat(output, startsWith("0001"));
    }

    /**
     * Bei XML sollen im Output spitze Klammern zu finden sein.
     *
     * @throws IOException sollte nicht passieren
     */
    @Test
    public void testWriteInternalXML() throws IOException {
        String output = convertEmptyDatenpaketFor(MediaType.TEXT_XML);
        assertThat(output, startsWith("<"));
    }

    /**
     * Der HTML-Output sollte mit &lt;html&gt; anfangen.
     *
     * @throws IOException sollte nicht passieren
     */
    @Test
    public void testWriteInternalHTML() throws IOException {
        String output = convertEmptyDatenpaketFor(MediaType.TEXT_HTML);
        assertThat(output, containsString("<html"));
    }

    /**
     * Hier sollte der Output im JSON-Format sein.
     *
     * @throws IOException sollte nicht passieren
     */
    @Test
    public void testWriteInternalJSON() throws IOException {
        String output = convertEmptyDatenpaketFor(MediaType.APPLICATION_JSON);
        assertThat(output, startsWith("{"));
    }

    /**
     * Hier testen wir den CSV-Export.
     *
     * @throws IOException sollte nicht passieren
     */
    @Test
    public void testWriteInternalCSV() throws IOException {
        String output = convertEmptyDatenpaketFor(AppConfig.MEDIA_TYPE_TEXT_CSV);
        assertThat(output, containsString(";"));
    }

    private static String convertEmptyDatenpaketFor(MediaType mediaType) throws IOException {
        DatenpaketHttpMessageConverter converter = new DatenpaketHttpMessageConverter(mediaType);
        Datenpaket datenpaket = new Datenpaket("Empty");
        MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
        converter.writeInternal(datenpaket, outputMessage);
        String output = outputMessage.getBodyAsString(StandardCharsets.ISO_8859_1);
        LOG.info("output = \"{}\"", StringUtils.abbreviate(output, 40));
        return output;
    }

}
