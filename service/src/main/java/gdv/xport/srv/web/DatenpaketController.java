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
package gdv.xport.srv.web;

import gdv.xport.srv.service.DatenpaketService;
import gdv.xport.srv.service.DefaultDatenpaketService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    private DatenpaketService service;

    /**
     * Validiert die uebergebene URI.
     *
     * @param uri z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @return the response entity
     */
    @GetMapping("/validate")
    @ApiOperation(value = "validiert die uebergebene URI")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "uri",
                    value = "z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt",
                    required = true,
                    dataType = "string",
                    paramType = "query"
            )
    })
    public ResponseEntity<List<Model>> validate(@RequestParam("uri") URI uri) {
        LogWatch watch = new LogWatch();
        LOG.info("Validating Datenpakete in {}...", uri);
        List<Model> violations = service.validate(uri);
        LOG.info("Validating Datenpakete in {} finished with {} violation(s) in {}.", uri, violations.size(), watch);
        return ResponseEntity.ok(violations);
    }

    /**
     * Validiert die eingelesenen Datenpakete.
     *
     * @param text Text, der ueber die Leitung reinkommt.
     * @return the response entity
     */
    @PostMapping("/validate")
    public ResponseEntity<List<Model>> validate(@RequestBody String text) {
        LogWatch watch = new LogWatch();
        LOG.info("Validating Datenpakete in posted stream of {} length...", StringUtils.length(text));
        List<Model> violations = service.validate(text);
        LOG.info("Validating Datenpakete in posted stream finished with {} violation(s) in {}.", violations.size(), watch);
        return ResponseEntity.ok(violations);
    }

    /**
     * Laedt die gewuenschte Datei und validiert die darin enthaltenen
     * Datenpakete. Da hierueber der Inhalt der Datei mit uebertragen wird,
     * wird dieser Service ueber POST angesprochen.
     *
     * @param file gewuenschte Datei
     * @return the response entity
     * @throws IOException the io exception
     */
    @PostMapping("/validateUploaded")
    public ResponseEntity<List<Model>> validate(@RequestParam("file") MultipartFile file) {
        LogWatch watch = new LogWatch();
        LOG.info("Validating Datenpakete in posted file '{}'...", file);
        String text = null;
        try {
            text = new String(file.getBytes());
            List<Model> violations = service.validate(text);
            LOG.info("Validating Datenpakete in {} finished with {} violation(s) in {}.", file, violations.size(), watch);
            return ResponseEntity.ok(violations);
        } catch (IOException ioe) {
            LOG.warn("Cannot upload and validate {}:", file.getOriginalFilename(), ioe);
            return ResponseEntity.badRequest().body(DefaultDatenpaketService.asModelList(ioe));
        }
    }

}
