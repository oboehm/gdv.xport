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

import org.apache.logging.log4j.*;
import org.springframework.util.*;
import org.springframework.web.servlet.handler.*;

import javax.servlet.http.*;
import java.util.*;

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
            LOG.error("{} ** \"{} {} {}\" {}:", request.getRemoteHost(), request.getMethod(), request.getRequestURI(),
                    request.getProtocol(), response.getStatus(), ex);
        }
    }

    private static void logAccess(String prefix, HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        if (status >= 500) {
            LOG.error("{} {} \"{} {} {}\" {} {} {} {} {}", request.getRemoteHost(), prefix, request.getMethod(),
                    getRequestURIwithParams(request), request.getProtocol(), status, request.getContentLength(),
                    getHeader(request, "Content-Type"), getHeader(request, "accept"), getHeader(request, "user-agent"));
        } else if (status >= 400) {
            LOG.warn("{} {} \"{} {} {}\" {} {} {} {} {}", request.getRemoteHost(), prefix, request.getMethod(),
                    getRequestURIwithParams(request), request.getProtocol(), status, request.getContentLength(),
                    getHeader(request, "Content-Type"), getHeader(request, "accept"), getHeader(request, "user-agent"));
        } else {
            LOG.info("{} {} \"{} {} {}\" {} {} {} {} {}", request.getRemoteHost(), prefix, request.getMethod(),
                    getRequestURIwithParams(request), request.getProtocol(), status, request.getContentLength(),
                    getHeader(request, "Content-Type"), getHeader(request, "accept"), getHeader(request, "user-agent"));
        }
    }

    private static String getRequestURIwithParams(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if ("/error".equals(requestURI)) {
            Object errorRequestURI = request.getAttribute("javax.servlet.error.request_uri");
            if (errorRequestURI != null) {
                LOG.debug("Request '{}' is mapped to '{}'.", requestURI, errorRequestURI);
                requestURI = errorRequestURI.toString();
            }
        }
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            requestURI += toParameterString(request.getParameterMap());
        }
        return requestURI;
    }

    private static String toParameterString(Map<String, String[]> parameterMap) {
        if (parameterMap.isEmpty()) {
            return "";
        }
        StringBuilder buf = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            buf.append('&');
            buf.append(entry.getKey());
            buf.append('=');
            buf.append(StringUtils.arrayToCommaDelimitedString(entry.getValue()));
        }
        buf.setCharAt(0, '?');
        return buf.toString();
    }

    private static String getHeader(HttpServletRequest request, String name) {
        return name + "=\"" + request.getHeader(name) + '"';
    }

}
