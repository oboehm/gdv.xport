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
 * (c)reated 21.02.2017 by oliver (ob@oasd.de)
 */

package gdv.xport.srv.service;

import org.springframework.ui.Model;
import org.springframework.util.MimeType;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Die Definition der Service-Klasse fuer das {@link gdv.xport.Datenpaket}.
 *
 * @author oboehm
 * @since 3.0.0 (21.02.2017)
 */
public interface DatenpaketService {

    /**
     * Validiert die uebergebene URI.
     *
     * @param uri z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @return List mit Constraint-Verletzungen
     */
    List<Model> validate(URI uri);

    /**
     * Validiert die eingelesenen Datenpakete.
     *
     * @param text Text, der ueber die Leitung reinkommt.
     * @return the response entity
     */
    List<Model> validate(String text);

    /**
     * Holt sich das Datenpaket von der angegebenen URI und formattiert das
     * Datenpaket anhand des uebergebenen Formatters.
     *
     * @param uri z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @param type gewuenschte Formattierung
     * @return string formatiertes Datenpaket
     * @throws IOException kann beim Lesen der URI auftreten
     */
    String format(URI uri, MimeType type) throws IOException;

    /**
     * Holt sich das Datenpaket, das als Text im GDV-Format uebergeben wird
     * und formattiert das Datenpaket anhand des uebergebenen Formatters.
     *
     * @param text Text, der ueber die Leitung reinkommt.
     * @param type gewuenschte Formattierung
     * @return string formatiertes Datenpaket
     */
    String format(String text, MimeType type);

}
