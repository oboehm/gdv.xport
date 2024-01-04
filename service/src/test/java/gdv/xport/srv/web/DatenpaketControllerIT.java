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

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;

import java.io.IOException;
import java.io.StringWriter;

import static gdv.xport.srv.config.AppConfig.TEXT_CSV;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

;

/**
 * Integrationstests fuer den {@link DatenpaketController}.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
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
     *
     * @throws Exception im Fehlerfall
     */
    @Test
    public void testValidateURI() throws Exception {
        String response = getResponseStringFor(
                "/api/v1/Abweichungen?uri=http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt",
                MediaType.APPLICATION_JSON);
        MatcherAssert.assertThat(response, equalTo("[]"));
    }

    /**
     * Hier testen wir ein leeres Dummy-Datenpaket, bei dem die VU-Nummer nicht
     * gesetzt ist. Dies sollte zu einem Validierungs-Fehler fuehren.
     *
     * @throws Exception im Fehlerfall
     */
    @Test
    public void testValidatePost() throws Exception {
        String response = callRestWithDummyDatenpaket("/api/v1/Abweichungen", MediaType.APPLICATION_JSON);
        MatcherAssert.assertThat(response, containsString("VU-Nummer is not set"));
    }

    /**
     * Hier schicken wir ein leeres Dummy-Paket und erwarten als Antwort das
     * Datenpaket wieder zurueck.
     *
     * @throws Exception im Fehlerfall
     */
    @Test
    public void testFormat() throws Exception {
        String response = callRestWithDummyDatenpaket("/api/v1/Datenpaket");
        MatcherAssert.assertThat(response, not(emptyString()));
    }

    /**
     * Hier testen wir die HTML-Formatierung.
     *
     * @throws Exception im Fehlerfall
     */
    @Test
    public void testDatenpaketAsHtml() throws Exception {
        String response = callRestWithDummyDatenpaket("/api/v1/Datenpaket.html", MediaType.TEXT_HTML);
        MatcherAssert.assertThat(response, containsString("<html"));
    }

    @Test
    public void testDatenpaketV2() throws Exception {
        String response = callRestWithDummyDatenpaket("/api/v2/Datenpaket", MediaType.TEXT_HTML);
        MatcherAssert.assertThat(response, containsString("<html"));
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
     * @throws Exception im Fehlerfall
     */
    @Test
    public void testDatenpaketFromURI() throws Exception {
        String response = getResponseStringFor(
                "/api/v1/Datenpaket.csv?uri=http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt");
        MatcherAssert.assertThat(response, containsString(";"));
    }

    /**
     * Hier testen wir, ob die Fehlermeldung im bevorzugten Format (JSON)
     * zurueckgegeben wird.
     *
     * @throws Exception im Fehlerfall
     */
    @Test
    public void testErrorDetailAsJSON() throws Exception {
        checkMalformedURL("json");
    }

    /**
     * Fuer die XML-Serialisierung muessen die entsprechenden XML-Bibliotheken
     * eingebunden sein. Falls dies nicht der Fall ist, kommt eine
     * "ClassNotFoundException: com.fasterxml.jackson.dataformat.xml.XmlMapper"
     * oder eine HttpMediaTypeNotAcceptableException.
     *
     * @throws Exception im Fehlerfall
     */
    @Test
    public void testErrorDetailAsXML() throws Exception {
        checkMalformedURL("xml");
    }

    /**
     * Test, ob ErorDetail auch als HTML geliefert wird.
     *
     * @throws Exception im Fehlerfall
     */
    @Test
    public void testErrorDetailAsHTML() throws Exception {
        checkMalformedURL("html");
    }

    private void checkMalformedURL(String format) throws Exception {
        String response =
                getResponseStringFor("/api/v1/Datenpaket." + format + "?uri=xxx:gibts.net");
        assertNotNull(response);
        MatcherAssert.assertThat(response.toLowerCase(), containsString("bad"));
        MatcherAssert.assertThat(response, not(containsString("500")));
    }

    /**
     * Hier testen wir den Exceptionhandler direkt. Im Gegensatz zur vorigen
     * Version dieser Testmethode testen wir nicht mehr das zurueckgegebene
     * Format (JSON), sondern das zurueckgegebenen Objekt. Trotzdem setzen
     * wir hier im accept-Header das bevorzugte Format, falls wir den Test
     * nochmal umschreiben wollen. Da hier Wildcards angegeben werden, sollte
     * dabei JSON zurueckgegeben werden.
     */
    @Test
    public void testHandleExceptions() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/testHandleExceptions");
        request.addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        IllegalArgumentException cause = new IllegalArgumentException("test accepted");
        DatenpaketController controller = new DatenpaketController();
        ErrorDetail response = controller.handleException(request, cause);
        MatcherAssert.assertThat(response.getStatus(), Matchers.is(HttpStatus.BAD_REQUEST));
    }

    /**
     * Mit der Endung ".txt" soll der Restservice ein Datenpaket in Textform
     * zurueckliefern.
     *
     * @throws Exception im Fehlerfall
     */
    @Test
    public void testGetDatenpaketAsText() throws Exception {
        checkGetDatenpaketAs(".txt");
    }

    /**
     * Mit der Endung ".xml" soll der Restservice ein Datenpaket als XML
     * zurueckliefern.
     *
     * @throws Exception im Fehlerfall
     */
    @Test
    public void testGetDatenpaketAsXML() throws Exception {
        String response = checkGetDatenpaketAs(".xml");
        MatcherAssert.assertThat(response, containsString("<"));
    }

    /**
     * Mit der Endung ".csv" soll der Restservice ein Datenpaket als CSV
     * zurueckliefern.
     *
     * @throws Exception sollte nicht passieren
     */
    @Test
    public void testGetDatenpaketAsCSV() throws Exception {
        String response = checkGetDatenpaketAs(".csv");
        MatcherAssert.assertThat(response, containsString(";"));
    }

    private String checkGetDatenpaketAs(String suffix) throws Exception {
        String response = callRestWithDummyDatenpaket("/api/v1/Datenpaket" + suffix);
        MatcherAssert.assertThat(response, not(emptyString()));
        MatcherAssert.assertThat(response, not(containsString("error")));
        return response;
    }

    private String callRestWithDummyDatenpaket(String path, MediaType... mediaTypes) throws Exception {
        String text = createDummyDatenpaketText();
        mediaTypes = addMediaTypeFor(path, mediaTypes);
        //String response = postResponseObjectFor(path, text, String.class, mediaTypes);
        String response = postResponseStringFor(path, text, mediaTypes);
        LOG.info("Response of '{}' is '{}'.", path, response);
        MatcherAssert.assertThat(response, not(containsString("Internal Server Error")));
        MatcherAssert.assertThat(response, notNullValue());
        return response;
    }

    private static MediaType[] addMediaTypeFor(String path, MediaType[] mediaTypes) {
        String extension = FilenameUtils.getExtension(path);
        switch (extension.toLowerCase()) {
            case "xml":
                mediaTypes = ArrayUtils.add(mediaTypes, MediaType.TEXT_XML);
                break;
            case "csv":
                mediaTypes = ArrayUtils.add(mediaTypes, MediaType.valueOf(TEXT_CSV));
                break;
        }
        return mediaTypes;
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
     * der Content-Negotiation zu testen.
     *
     * @throws Exception sollte nicht passieren
     */
    @Test
    public void testContentNegotiation() throws Exception {
        String response = postResponseStringFor("/api/v1/Datenpaket", createDummyDatenpaketText(), MediaType.TEXT_XML);
        MatcherAssert.assertThat(response, containsString("<"));
    }

    /**
     * Dies ist der Testfall fuer Issue 48.
     *
     * @throws Exception im Fehlerfall
     */
    @Test
    public void testFormatArgument() throws Exception {
        String response = getResponseStringFor(
                "/api/v1/Datenpaket*?format=JSON&uri=http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt");
        String json = response.trim();
        MatcherAssert.assertThat(json, startsWith("{"));
    }

}
