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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 21.02.2017 by oboehm (ob@oasd.de)
 */
package gdv.xport.util;

import gdv.xport.Datenpaket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

/**
 * Unit-Tests fuer {@link JsonFormatter}-Klasse.
 *
 * @author oboehm
 */
public final class JsonFormatterTest extends AbstractFormatterTest {

    private static final Logger LOG = LogManager.getLogger(JsonFormatterTest.class);

    @Override
    protected AbstractFormatter createFormatter() {
        return new JsonFormatter();
    }

    /**
     * Tested den Export eines Datenpakets als JSON.
     *
     * @throws IOException falls was schief laeuft
     */
    @Test
    public void testWriteDatenpaket() throws IOException {
        try (StringWriter swriter = new StringWriter()) {
            JsonFormatter formatter = new JsonFormatter(swriter);
            formatter.write(new Datenpaket());
            swriter.flush();
            String jsonString = swriter.toString().trim();
            LOG.info("jsonString = '{}'", jsonString);
            assertThat(jsonString, startsWith("{"));
        }
    }

}
