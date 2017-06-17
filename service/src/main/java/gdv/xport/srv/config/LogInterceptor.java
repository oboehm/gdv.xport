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

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

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
        logAccess("<-", request, response);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Headers:");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                LOG.debug("\t{}=\"{}\"", name, request.getHeader(name));
            }
        }
        return true;
    }

    /**
     * Hierueber wird der Status-Code der Anfrage protokolliert.
     *
     * @param request reinkommender Request
     * @param response fuer den Status-Code
     * @param handler uninteressant
     * @param ex Exception im Fehlerfall
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex == null) {
            logAccess("->", request, response);
        } else {
            LOG.error("{} ** \"{} {} {}\":", request.getRemoteHost(), request.getMethod(), request.getRequestURI(),
                    request.getProtocol(), ex);
        }
    }

    private static void logAccess(String prefix, HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        Level level = (status < 400) ? Level.INFO : Level.WARN;
        LOG.log(level, "{} {} \"{} {} {}\" {} {} \"{}\"", request.getRemoteHost(), prefix, request.getMethod(),
                request.getRequestURI(), request.getProtocol(), status, request.getContentLength(),
                request.getHeader("user-agent"));
    }

}
