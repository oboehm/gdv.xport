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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 03.11.2017 by oboehm (ob@oasd.de)
 */

import gdv.xport.srv.web.*;
import org.junit.*;
import org.springframework.http.*;
import org.springframework.mock.http.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;

import static gdv.xport.srv.config.AppConfig.MEDIA_TYPE_TEXT_CSV;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Unit-Tests fuer {@link ErrorDetailHttpMessageConverter}-Klasse.
 *
 * @author oboehm
 * @since 3.0 (03.11.2017)
 */
public final class ErrorDetailHttpMessageConverterTest {

    private static final ErrorDetail TEST_ERROR_DETAIL =
            new ErrorDetail(URI.create("http://test"), HttpStatus.I_AM_A_TEAPOT, "coffee needed");

    /**
     * Testmethode fuer {@link ErrorDetailHttpMessageConverter#supports(Class)}.
     */
    @Test
    public void testSupports() {
        ErrorDetailHttpMessageConverter converter = new ErrorDetailHttpMessageConverter(MediaType.TEXT_HTML);
        assertThat(converter.supports(ErrorDetail.class), is(true));
    }

    /**
     * Der HTML-Output sollte mit &lt;html&gt; anfangen.
     *
     * @throws IOException sollte nicht passieren
     */
    @Test
    public void writeInternalHTML() throws IOException {
        String output = convertErrorDetailFor(MediaType.TEXT_HTML);
        assertThat(output, containsString("<html>"));
    }

    /**
     * Bei Text als MediaType sollte Statuscode und Message enthalten sein.
     *
     * @throws IOException sollte nicht passieren
     */
    @Test
    public void writeInternalText() throws IOException {
        String output = convertErrorDetailFor(MediaType.TEXT_PLAIN);
        assertEquals(TEST_ERROR_DETAIL.toString(), output.trim());
    }

    /**
     * Wird ErrorDetail als CVS ausgegben, sollten die einzelnen Elemente mit
     * Inhalt im CSV enthalten sein.
     *
     * @throws IOException sollte nicht passieren
     */
    @Test
    public void writeInternalCSV() throws IOException {
        String output = convertErrorDetailFor(MEDIA_TYPE_TEXT_CSV);
        assertThat(output, containsString("Status"));
        assertThat(output, containsString(Integer.toString(TEST_ERROR_DETAIL.getStatus().value())));
        assertThat(output, containsString(";"));
    }

    private String convertErrorDetailFor(MediaType mediaType) throws IOException {
        ErrorDetailHttpMessageConverter converter = new ErrorDetailHttpMessageConverter(mediaType);
        MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
        converter.writeInternal(TEST_ERROR_DETAIL, outputMessage);
        return outputMessage.getBodyAsString(StandardCharsets.ISO_8859_1);
    }

}
