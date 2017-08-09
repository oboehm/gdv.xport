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

import java.io.*;
import java.time.*;

/**
 * Klasse ErrorDetail enthaelt Angaben zum Fehler und aufgetretenen Exception.
 *
 * @author oboehm
 * @since 3.0 (09.08.2017)
 */
public class ErrorDetail implements Serializable {

    private final LocalDateTime when;
    private final String text;
    private final Throwable cause;

    /**
     * Instanzierung.
     *
     * @param text Fehlertext (fuer den Anwender)
     * @param cause eigentliche Ursache
     */
    public ErrorDetail(String text, Throwable cause) {
        this.when = LocalDateTime.now();
        this.text = text;
        this.cause = cause;
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
     * Rueckgabe des Textes, der fuer den Anwender angezeigt werden soll.
     *
     * @return z.B. "Input ist korrupt"
     */
    public String getText() {
        return text;
    }

    /**
     * Rueckgabe der ursaechlichen Exception. Diese Exception ist nur zur
     * Unterstuetzung fuer die Analyse, nicht aber fur den Endbenutzer
     * vorgesehen.
     *
     * @return z.B. eine IllegalArgumentException bei Parameter-Fehlern
     */
    public Throwable getCause() {
        return cause;
    }

}
