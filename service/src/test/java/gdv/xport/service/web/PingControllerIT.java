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
 * (c)reated 13.02.17 by oliver (ob@oasd.de)
 */
package gdv.xport.service.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * EInfacher Integrationstests fuer die {@link PingController}-Klasse. Die
 * Test-Klasse ist mehr oder weniger von HelloControllerIT in
 * https://github.com/spring-guides/gs-actuator-service.git abgeschaut.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public final class PingControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    /**
     * REST-URL aufsetzen.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/ping");
    }

    /**
     * Hier ueberpruefen wir, ob wir was vom Ping-Service zurueckbekommen.
     *
     * @throws Exception the exception
     */
    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("OK"));
    }

}
