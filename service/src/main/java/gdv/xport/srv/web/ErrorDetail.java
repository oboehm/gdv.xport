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
 * (c)reated 09.08.2017 by oboehm (ob@oasd.de)
 */
package gdv.xport.srv.web;

import com.fasterxml.jackson.databind.annotation.*;
import gdv.xport.srv.web.util.*;
import org.apache.logging.log4j.*;
import org.springframework.http.*;

import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import java.time.*;

/**
 * Klasse ErrorDetail enthaelt Angaben zum Fehler und aufgetretener Exception.
 *
 * @author oboehm
 * @since 3.0 (09.08.2017)
 */
public class ErrorDetail implements Serializable {

    private final URI request;
    private final HttpStatus status;
    private final String message;
    private final LocalDateTime when;

    /**
     * Instanzierung.
     *
     * @param request urspruenglicher Request
     * @param status HTTP-Status
     * @param cause eigentliche Ursache
     */
    public ErrorDetail(HttpServletRequest request, HttpStatus status, Throwable cause) {
        this(URI.create(request.getRequestURL().toString()), status, cause.getLocalizedMessage());
    }

    /**
     * Instanzierung.
     *
     * @param requestURI aufgerufene URI, bei der es Probleme gab
     * @param status HTTP-Status
     * @param text Fehlertext (fuer den Anwender)
     */
    public ErrorDetail(URI requestURI, HttpStatus status, String text) {
        this.when = LocalDateTime.now();
        this.request = requestURI;
        this.status = status;
        this.message = text;
    }

    /**
     * Zeitpunkt, wann der Fehler passiert ist.
     *
     * @return Zeitpunkt der Anlage
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getWhen() {
        return when;
    }

    /**
     * Liefert die URI, bei der das Problem aufgetreten ist.
     *
     * @return URI
     */
    public URI getRequest() {
        return request;
    }

    /**
     * HTTP-Status, der zurueckgeliefert wurde.
     *
     * @return z.B. 400 (Bad Request)
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Rueckgabe des Textes, der fuer den Anwender angezeigt werden soll.
     *
     * @return z.B. "Input ist korrupt"
     */
    public String getMessage() {
        return message;
    }

    /**
     * Ausgabe der wichtigsten Attribute.
     *
     * @return Status mit URI und Message
     */
    @Override
    public String toString() {
        return status + " " + request +  " (\"" + message + "\")";
    }

}
