/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 08.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.config;

import static org.junit.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.*;
import org.junit.Test;

/**
 * @author oliver
 * @since 08.10.2009
 * @version $Revision$
 */
public class ConfigTest {

    private static final Log log = LogFactory.getLog(ConfigTest.class);

    /**
     * Test method for {@link gdv.xport.config.Config#getVUNummer()}:
     * Entweder ist die entsprechende Property gesetzt, oder wir erwarten hier
     * eine ConfigException.
     */
    @Test
    public void testGetVUnummer() {
        Config.reset();
        String vuNummer = System.getProperty(Config.GDV_VU_NUMMER);
        if (StringUtils.isEmpty(vuNummer)) {
            try {
                Config.getVUNummer();
            } catch (ConfigException expected) {
                log.info("expected: " + expected);
            }
        } else {
            assertEquals(vuNummer, Config.getVUNummer().getInhalt().trim());
        }
    }

}

