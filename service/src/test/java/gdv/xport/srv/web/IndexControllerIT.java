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
 * (c)reated 19.02.17 by oliver (ob@oasd.de)
 */

package gdv.xport.srv.web;

import gdv.xport.srv.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Integrationstests fuer den {@link IndexController}.
 *
 * @author oboehm
 */
@AutoConfigureMockMvc
@ContextConfiguration(classes = {IndexController.class, AppConfig.class})
@WebMvcTest
class IndexControllerIT {

    private static final Logger log = LogManager.getLogger(IndexControllerIT.class);

    @Autowired
    private MockMvc mockMVC;

    @Test
    void testSetup() {
        log.info("testSetup() wurde gestartet.");
        assertNotNull(mockMVC);
    }

    /**
     * Mit diesem Test pruefen wir, ob wir die "index.html"-Seite bekommen.
     */
    @Test
    void testIndexHtml() throws Exception {
        MockHttpServletResponse response = mockMVC.perform(get("/")).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        MatcherAssert.assertThat(response.getContentAsString(), containsString("<title>"));
    }

}
