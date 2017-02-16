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
 * (c)reated 15.02.17 by oliver (ob@oasd.de)
 */
package gdv.xport.service.web;

import gdv.xport.Datenpaket;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import patterntesting.runtime.log.LogWatch;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Dieser Controller repraesentiert das REST-Interface zur Datenpaket-Klasse.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
@RestController
@RequestMapping("/Datenpakete")
public final class DatenpaketController {

    private static final Logger LOG = LogManager.getLogger(DatenpaketController.class);

    /**
     * Validiert die uebergebene URI.
     *
     * @param uri the uri
     * @return the response entity
     * @throws IOException the io exception
     */
    @GetMapping("/validate")
    public ResponseEntity<List<ConstraintViolation>> validate(@RequestParam("uri") URI uri) throws IOException {
        LogWatch watch = new LogWatch();
        LOG.info("Validating Datenpakete in {}...", uri);
        Datenpaket datenpaket = new Datenpaket();
        datenpaket.importFrom(uri);
        List<ConstraintViolation> violations = datenpaket.validate();
        LOG.info("Validating Datenpakete in {} finished with {} violation(s) in {}.", uri, violations.size(), watch);
        return ResponseEntity.ok(violations);
    }

}
