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

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import patterntesting.runtime.log.LogWatch;
import springfox.documentation.service.MediaTypes;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * In AbstractControllerIT sind einige Gemeinsamkeiter der verschiedenen
 * ControllerIT-Klassen zusammengefasst.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractControllerIT {

    private static final Logger LOG = LogManager.getLogger(AbstractControllerIT.class);

    @LocalServerPort
    private int port;

    @Autowired
    protected TestRestTemplate template;

    protected URI baseURI;

    /**
     * REST-URI aufsetzen.
     */
    @Before
    public void setUp() {
        this.baseURI = URI.create("http://localhost:" + port);
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

    /**
     * Baut die URL zusammen und ruft den Service als POST-Request auf.
     *
     * @param <T>        Typ-Parameter
     * @param path       Context-Pfad der URL
     * @param text       Text
     * @param type       Typ der erwarteten Antwort
     * @param mediaTypes Content-Types
     * @return Antwort des abgesendeten Requests
     */
    protected <T> T postResponseObjectFor(String path, T text, Class<T> type, MediaType... mediaTypes) {
        ResponseEntity<T> response = exchangeResponseEntity(HttpMethod.POST, path, text, type, mediaTypes);
        return response.getBody();
    }

    private <T> ResponseEntity<T> exchangeResponseEntity(HttpMethod method, String path, T text, Class<T> type, MediaType[] mediaTypes) {
        LogWatch watch = new LogWatch();
        LOG.info("Requesting {}{}...", baseURI, path);
        HttpHeaders headers = new HttpHeaders();
        if (mediaTypes.length > 0) {
            headers.setAccept(Arrays.asList(mediaTypes));
        } else {
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        }
        ResponseEntity<T> response =
                template.exchange(baseURI.toString() + path, method, new HttpEntity<>(text, headers), type);
        LOG.info("{}} {}{} successful finished with {} after {}.", method, baseURI, path, response, watch);
        return response;
    }

}
