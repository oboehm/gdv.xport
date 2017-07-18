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
 * (c)reated 02.07.2017 by oboehm (ob@oasd.de)
 */
package gdv.xport.srv.config;

import org.apache.logging.log4j.*;
import org.junit.*;
import org.springframework.mock.web.*;

/**
 * Unit-Teests fuer {@link LogInterceptor}.
 *
 * @author oboehm
 */
public final class LogInterceptorTest {

    private static final Logger LOG = LogManager.getLogger(LogInterceptorTest.class);
    private final LogInterceptor interceptor = new LogInterceptor();
    private final MockHttpServletRequest request = new MockHttpServletRequest();
    private final MockHttpServletResponse response = new MockHttpServletResponse();

    /**
     * Hier setzen wir den Request auf.
     */
    @Before
    public void setUpRequest() {
        request.setRequestURI("/gdv/xport");
        request.setMethod("GET");
        request.addParameter("hello", "world");
    }

    /**
     * Hier testen wir nur, ob der Aufruf ohne Exception durchlaeuft und
     * beobachten das Log.
     */
    @Test
    public void testPreHandle() {
        interceptor.preHandle(request, response, "testPreHandle");
    }

    /**
     * Auch hier beobachten wir nur die Log-Ausgabe.
     */
    @Test
    public void testAfterCompletion() {
        interceptor.afterCompletion(request, response, "testAfterCompletion", null);
    }

    /**
     * Hier testen wir (manuell) die Log-Ausgabe einer Exception.
     */
    @Test
    public void testAfterCompletionWithException() {
        LOG.info("Simulating an exception for testing...");
        interceptor.afterCompletion(request, response, "testAfterCompletion", new IllegalStateException("bumm"));
    }

}
