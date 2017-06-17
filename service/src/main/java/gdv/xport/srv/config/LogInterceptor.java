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
 * (c)reated 17.06.17 by oliver (ob@oasd.de)
 */
package gdv.xport.srv.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Der LogInterceptor protokolliert die ein- und ausgehenden Requests.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
public final class LogInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOG = LogManager.getLogger(LogInterceptor.class);

    /**
     * Hierueber wird die Anfrage mitprotokolliert.
     *
     * @param request reinkommender Request
     * @param response uninteressant
     * @param handler ebenfalls uninteressant
     * @return immer true
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LOG.info("URI {} was called.", request.getRequestURI());
        return true;
    }

}
