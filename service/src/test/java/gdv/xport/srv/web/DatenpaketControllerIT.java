/*
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
 * (c)reated 16.02.17 by oliver (ob@oasd.de)
 */
package gdv.xport.srv.web;

import gdv.xport.*;
import gdv.xport.config.*;
import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.http.*;
import org.springframework.mock.web.*;
import org.springframework.test.context.junit4.*;
import org.springframework.util.*;

import java.io.*;
import java.net.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Integrationstests fuer den {@link DatenpaketController}.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
@RunWith(SpringRunner.class)
public final class DatenpaketControllerIT extends AbstractControllerIT {

    private static final Logger LOG = LogManager.getLogger(DatenpaketControllerIT.class);

    /**
     * Hier testen wir, ob wir mit dem Musterdatensatz eine leere Liste von
     * Violations zurueckbekommen. Aus folgenden Gruenden kann dieser Test
     * fehlschlagen:
     * <ul>
     *     <li>keine Internetverbindung,</li>
     *     <li>die verwendete URI ist nicht (mehr) erreichbar,</li>
     *     <li>Programmierfehler.</li>
     * </ul>
     */
    @Test
    public void testValidateURI() {
        ResponseEntity<String> response = getResponseEntityFor(
                "/Datenpakete/validate?uri=http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt", String.class);
        assertThat(response.getBody(), equalTo("[]"));
    }

    /**
     * Hier testen wir ein leeres Dummy-Datenpaket, bei dem die VU-Nummer nicht
     * gesetzt ist. Dies sollte zu einem Validierungs-Fehler fuehren.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testValidatePost() throws IOException {
        String response = callRestWithDummyDatenpaket("/Datenpakete/validate");
        assertThat(response, containsString("VU-Nummer is not set"));
    }

    /**
     * Hier schicken wir ein leeres Dummy-Paket und erwarten als Antwort das
     * Datenpaket wieder zurueck.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testFormat() throws IOException {
        String response = callRestWithDummyDatenpaket("/Datenpakete/format");
        assertThat(response, not(isEmptyString()));
    }

    /**
     * Hier testen wir die HTML-Formattierung.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testFormatHtml() throws IOException {
        String response = callRestWithDummyDatenpaket("/Datenpakete/format.html");
        assertThat(response, containsString("<html"));
    }

    /**
     * Hier schicken wir eine URI und erwarten als Antwort CSV-Datei. Aus
     * folgenden Gruenden kann dieser Tes fehlschlagen:
     * <ul>
     *     <li>keine Internetverbindung,</li>
     *     <li>die verwendete URI ist nicht (mehr) erreichbar,</li>
     *     <li>Programmierfehler.</li>
     * </ul>
     *
     * @throws IOException the io exception
     */
    @Test
    public void testFormatURI() throws IOException {
        ResponseEntity<String> response = getResponseEntityFor(
                "/Datenpakete/format.csv?uri=http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt",
                String.class);
        assertThat(response.getBody(), containsString(";"));
    }

    /**
     * Hier testen wir, ob die Fehlermeldung im bevorzugten Format (JSON)
     * zurueckgegeben wird. JSON ist das bevorzugte Format, wenn Wildcards
     * angegeben werden.
     */
    @Test
    public void testHandleExceptions() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/testHandleExceptions");
        request.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        IllegalArgumentException cause = new IllegalArgumentException("test accepted");
        DatenpaketController controller = new DatenpaketController();
        controller.setRequest(request);
        String response = controller.handleException(cause);
        assertThat(response, containsString("status"));
    }

    /**
     * Mit der Endung ".txt" soll der Restservice ein Datenpaket in Textform
     * zurueckliefern.
     *
     * @throws IOException sollte nicht passieren
     */
    @Test
    public void testGetDatenpaketAsText() throws IOException {
        checkGetDatenpaketAs(".txt");
    }

    /**
     * Mit der Endung ".xml" soll der Restservice ein Datenpaket als XML
     * zurueckliefern.
     *
     * @throws IOException sollte nicht passieren
     */
    @Test
    public void testGetDatenpaketAsXML() throws IOException {
        String response = checkGetDatenpaketAs(".xml");
        assertThat(response, containsString("<"));
    }

    private String checkGetDatenpaketAs(String suffix) throws IOException {
        String response = callRestWithDummyDatenpaket("/Datenpakete/Datenpaket" + suffix);
        assertThat(response, not(isEmptyString()));
        assertThat(response, not(containsString("error")));
        return response;
    }

    private String callRestWithDummyDatenpaket(String path) throws IOException {
        String text = createDummyDatenpaketText();
        String response = postResponseObjectFor(path, text, String.class);
        LOG.info("Response of '{}' is '{}'.", path, response);
        assertThat(response, not(containsString("Internal Server Error")));
        assertThat(response, notNullValue());
        return response;
    }

    private static String createDummyDatenpaketText() throws IOException {
        Datenpaket dummy = new Datenpaket(Config.DUMMY_VU_NUMMER);
        StringWriter writer = new StringWriter();
        dummy.export(writer);
        writer.flush();
        writer.close();
        return writer.toString();
    }

    /**
     * Hier setzen wir nur den Accept-Header auf XML, um die Funktionsweise
     * der Content-Negotiation zu testen. Das Beispiel mit dem Aufsetzen des
     * Headers stammt aus dem Buch "Spring im Einsatz" (S. 326).
     *
     * @throws IOException sollte nicht passieren
     */
    @Test
    public void testContentNegotiation() throws IOException {
        HttpEntity<Object> requestEntity = createPostRequestWithAcceptHeader();
        URI uri = URI.create(baseURI.toString() + "/Datenpakete/Datenpaket");
        ResponseEntity<String> response = template.exchange(uri, HttpMethod.POST, requestEntity, String.class);
        assertThat(response.getBody(), containsString("<"));
    }

    private static HttpEntity<Object> createPostRequestWithAcceptHeader() throws IOException {
        String text = createDummyDatenpaketText();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", MediaType.TEXT_XML_VALUE);
        return new HttpEntity<>(text, headers);
    }

}