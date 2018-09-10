/*
 * Copyright (c) 2018 by Oliver Boehm
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
 * (c)reated 10.09.2018 by oboehm (ob@oasd.de)
 */
package gdv.xport.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.Assert.assertNotNull;

/**
 * Unit-Tests fuer {@link LogConfig}-Klasse.
 *
 * @author oboehm
 */
public class LogConfigIT {

    private static final Logger LOG = LogManager.getLogger(LogConfigIT.class);

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();
    
    @Test
    public void testConnection() {
        String jdbcURL = postgreSQLContainer.getJdbcUrl();
        assertNotNull(jdbcURL);
        LOG.info("jdbcURL = {}", jdbcURL);
    }

}
