package gdv.xport.srv.web;/*
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
 * (c)reated 14.08.2017 by oboehm (ob@oasd.de)
 */

import org.apache.logging.log4j.*;
import org.junit.*;
import org.springframework.http.*;

import java.net.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Unit-Tests fuer {@link ErrorDetail}-Klasse.
 *
 * @author oboehm
 */
public final class ErrorDetailTest {

    private static final Logger LOG = LogManager.getLogger(ErrorDetailTest.class);
    private final ErrorDetail errorDetail =
            new ErrorDetail(URI.create("http://test"), HttpStatus.BAD_REQUEST, "Unit-Test");

    /**
     * Da die ErrorDetail-Klasse auch zum Loggen verwendet wird, sollt sie eine
     * vernuenftige toString-Methode haben.
     */
    @Test
    public void testToString() {
        String s = errorDetail.toString();
        assertThat("looks like default implementation", s, not(containsString("@")));
        LOG.info("s = \"{}\"", s);
    }

}
