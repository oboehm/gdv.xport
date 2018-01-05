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

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Integrationstests fuer den {@link IndexController}.
 *
 * @author oboehm
 */
public final class IndexControllerIT extends AbstractControllerIT{

    /**
     * Mit diesem Test pruefen wir, ob wir die "index.html"-Seite bekommen.
     */
    @Test
    public void testIndexHtml() {
        ResponseEntity<String> response = getResponseEntityFor("/", String.class);
        assertThat(response.getBody(), containsString("<title>"));
    }

}
