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
 * (c)reated 01.03.17 by oliver (ob@oasd.de)
 */
package gdv.xport.srv.service;

import org.junit.Test;
import org.springframework.ui.Model;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit-Tests DefaultDatenpaketServiceTest.
 *
 * @author oboehm
 */
public final class DefaultDatenpaketServiceTest {

    private final DatenpaketService service = new DefaultDatenpaketService();

    /**
     * Test fuer {@link DefaultDatenpaketService#validate(URI)}.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testValidateUri() throws IOException {
        File musterdatei = new File("../lib/src/test/resources/musterdatei_041222.txt");
        List<Model> violations = service.validate(musterdatei.toURI());
        assertEquals(0, violations.size());
    }

}
