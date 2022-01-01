/*
 * Copyright (c) 2022 by Oliver Boehm
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
 * (c)reated 01.01.2022 by oliver (ob@oasd.de)
 */
package gdv.xport.srv.web;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse wandelt eine Exception in ein Model fuer die Ausgabe
 * auf der Web-UI um.
 *
 * @since 6.0
 */
public class ErrorModel extends ExtendedModelMap {

    /**
     * Wandelt eine Exception in ein ErrorModel um.
     *
     * @param ex Exception
     * @return ErrorModel
     */
    public static ErrorModel of(Exception ex) {
        ErrorModel m = new ErrorModel();
        m.addAttribute("message", ex.getMessage());
        m.addAttribute("causes", ex);
        return m;
    }

    /**
     * Hierueber laesst sich eine {@link IOException} in eine "Violation"-Liste
     * umwandeln, die fuer die Ausgabe der Validierung zum Einsatz kommt.
     *
     * @param ex Exception
     * @return the list
     */
    public static List<Model> asModelList(Exception ex) {
        List<Model> models = new ArrayList<>();
        Model m = ErrorModel.of(ex);
        models.add(m);
        return models;
    }

}
