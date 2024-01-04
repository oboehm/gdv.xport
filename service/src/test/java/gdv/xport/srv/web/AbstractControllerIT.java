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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import patterntesting.runtime.log.LogWatch;

import java.net.URI;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * In AbstractControllerIT sind einige Gemeinsamkeiter der verschiedenen
 * ControllerIT-Klassen zusammengefasst.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractControllerIT {

    private static final Logger LOG = LogManager.getLogger(AbstractControllerIT.class);

    @Autowired
    protected MockMvc mockMvc;

    //@LocalServerPort
    private int port;

    //@Autowired
    protected TestRestTemplate template;

    protected URI baseURI;

    /**
     * REST-URI aufsetzen.
     */
    @BeforeEach
    public void setUp() {
        this.baseURI = URI.create("http://localhost:" + port);
    }

    @Test
    void testSetUp() {
        assertNotNull(mockMvc);
    }

    /**
     * Baut die URL zusammen und ruft den Service als GET-Request auf.
     *
     * @param <T>        Typ-Parameter
     * @param path       Context-Pfad der URL
     * @param type       Typ der erwarteten Antwort
     * @param mediaTypes Content-Types
     * @return Antwort des abgesendeten Requests
     */
    protected <T> ResponseEntity<T> getResponseEntityFor(String path, Class<T> type, MediaType... mediaTypes) {
        return exchangeResponseEntity(HttpMethod.GET, path, null, type, mediaTypes);
    }

    protected String getResponseStringFor(String path, MediaType... mediaTypes) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(path)
                        .headers(createHeaders(mediaTypes)))
                .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

    protected String postResponseStringFor(String path, String text, MediaType... mediaTypes) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .content(text)
                        .headers(createHeaders(mediaTypes)))
                .andReturn();
        return mvcResult.getResponse().getContentAsString();
    }

    private <T> ResponseEntity<T> exchangeResponseEntity(HttpMethod method, String path, T text, Class<T> type, MediaType[] mediaTypes) {
        LogWatch watch = new LogWatch();
        LOG.info("Requesting {}{}...", baseURI, path);
        HttpHeaders headers = createHeaders(mediaTypes);
        ResponseEntity<T> response =
                template.exchange(baseURI.toString() + path, method, new HttpEntity<>(text, headers), type);
        LOG.info("{}} {}{} finished with {} after {}.", method, baseURI, path, response.getStatusCode(), watch);
        return response;
    }

    private static HttpHeaders createHeaders(MediaType[] mediaTypes) {
        HttpHeaders headers = new HttpHeaders();
        if (mediaTypes.length > 0) {
            headers.setAccept(Arrays.asList(mediaTypes));
        }
        return headers;
    }

}
