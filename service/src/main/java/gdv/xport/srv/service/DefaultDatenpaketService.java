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
 * (c)reated 21.02.2017 by oliver (ob@oasd.de)
 */

package gdv.xport.srv.service;

import gdv.xport.Datenpaket;
import net.sf.oval.ConstraintViolation;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Implementierung der Service-Klasse fuer das {@link gdv.xport.Datenpaket}.
 *
 * @author oboehm
 * @since 3.0.0 (21.02.2017)
 */
@Service
@Description("Default-Implementierung des Datenpaket-Services")
public final class DefaultDatenpaketService implements DatenpaketService {

    /**
     * Validiert die uebergebene URI.
     *
     * @param uri z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @return List mit Constraint-Verletzungen
     * @throws IOException the io exception
     */
    @Override
    public List<Model> validate(URI uri) throws IOException {
        Datenpaket datenpaket = new Datenpaket();
        datenpaket.importFrom(uri);
        return validate(datenpaket);
    }

    /**
     * Validiert die eingelesenen Datenpakete.
     *
     * @param text Text, der ueber die Leitung reinkommt.
     * @return the response entity
     * @throws IOException the io exception
     */
    @Override
    public List<Model> validate(String text) throws IOException {
        Datenpaket datenpaket = new Datenpaket();
        datenpaket.importFrom(text);
        return validate(datenpaket);
    }

    private static List<Model> validate(Datenpaket datenpaket) {
        List<ConstraintViolation> violations = datenpaket.validate();
        return toModelList(violations);
    }

    private static List<Model> toModelList(List<ConstraintViolation> violations) {
        List<Model> models = new ArrayList<Model>();
        for (ConstraintViolation cv : violations) {
            Model m = new ExtendedModelMap();
            m.addAttribute("context", cv.getContext().getCompileTimeType());
            m.addAttribute("invalidValue", cv.getInvalidValue());
            m.addAttribute("message", cv.getMessage());
            m.addAttribute("validatedObject", cv.getValidatedObject().toString());
            models.add(m);
        }
        return models;
    }

}
