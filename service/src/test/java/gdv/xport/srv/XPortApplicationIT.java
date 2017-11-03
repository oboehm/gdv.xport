/*
 * Copyright (c) 2017 by Oli B.
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
 * (c)reated 13.02.2017 by Oli B. (ob@aosd.de)
 */

package gdv.xport.srv;

import org.apache.logging.log4j.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.context.embedded.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.core.env.*;
import org.springframework.http.*;
import org.springframework.test.context.junit4.*;

import java.net.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


/**
 * Integrtations-Tests fuer die gesamte Anwendung, die ueber
 * {@link XPortApplication} gestartet wird. Integrations-Test deswegen,
 * weil hier zum Testen Spring-Boot hochgefahren wird und der Test
 * dementsprechend laenger dauert.
 *
 * @author oboehm
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XPortApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XPortApplicationIT {

    private static final Logger LOG = LogManager.getLogger(XPortApplicationIT.class);

    @Autowired
    private Environment env;

    @Autowired
    private XPortApplication application;

    @LocalServerPort
    private int port;

    @Autowired private TestRestTemplate template;
    private URI converterURI;

    @Before
    public void setUpBaseURI() {
        converterURI = URI.create("http://localhost:" + port);
    }

    /**
     * Hier ueberpruefen wir die Spring-Konfiguration aus 'application.yml'.
     */
	@Test
    public void testApplicationConfig() {
        String applName = env.getProperty("application.name");
        LOG.info("applName = \"{}\".", applName);
        assertThat(applName, not(isEmptyOrNullString()));
    }

    /**
     * Hier ueberprufen wir den Info-Endpoint des Actuators, der einige
     * Informationen zur Anwendung (wie die Version) liefern sollte.
     */
    @Test
    public void testInfo() {
        ResponseEntity<String> response = template.getForEntity(converterURI + "/info.json", String.class);
        String info = response.getBody();
        LOG.info("info = \"{}\"", info);
        assertThat(info, containsString("build"));
    }

    /**
     * Wenn die Datenpaket-Resource aufgerufen wird, sollte kein Response-Code 500
     * zurueckkommen.
     */
    @Test
    public void testDatenpaketError() {
        ResponseEntity<String> response =
                template.postForEntity(converterURI + "/api/v1/Datenpaket.json", "xxxx", String.class);
        assertThat(response.getStatusCodeValue(), not(500));
        assertEquals(400, response.getStatusCodeValue());
        LOG.info("response = '{}'", response.getBody());
    }

}
