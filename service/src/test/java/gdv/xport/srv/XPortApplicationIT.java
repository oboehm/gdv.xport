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

import gdv.xport.config.LogConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


/**
 * Integrtations-Tests fuer die gesamte Anwendung, die ueber
 * {@link XPortApplication} gestartet wird. Integrations-Test deswegen,
 * weil hier zum Testen Spring-Boot hochgefahren wird und der Test
 * dementsprechend laenger dauert.
 *
 * @author oboehm
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XPortApplication.class)
public class XPortApplicationIT {

    private static final Logger LOG = LogManager.getLogger(XPortApplicationIT.class);

    @Autowired
    private Environment env;

    @Autowired
    private XPortApplication application;

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
     * Hier ueberpruefen wir die Logging-Konfiguration.
     */
    @Test
    public void testGetLogConfig() {
        LogConfig config = application.getLogConfig();
        assertThat(config, notNullValue());
        LOG.info("config = {}", config);
    }

}
