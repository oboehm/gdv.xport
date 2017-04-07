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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import patterntesting.runtime.log.LogWatch;

import javax.servlet.http.HttpServletRequest;
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
     * @param body Text, der ueber die Leitung reinkommt.
     * @param text alternativ kann der Text auch als Parameter reinkommen
     * @return the response entity
     */
    @PostMapping("/validate")
    public ResponseEntity<List<Model>> validate(@RequestBody(required = false) String body, @RequestParam(required = false) String text) {
        LogWatch watch = new LogWatch();
        String content = (StringUtils.isBlank(text)) ? body : text;
        LOG.info("Validating Datenpakete in posted stream of {} bytes...", StringUtils.length(content));
        List<Model> violations = service.validate(content);
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
     */
    @PostMapping("/validateUploaded")
    public ResponseEntity<List<Model>> validate(@RequestParam("file") MultipartFile file) {
        LogWatch watch = new LogWatch();
        LOG.info("Validating Datenpakete in posted file '{}'...", file);
        try {
            String text = new String(file.getBytes());
            List<Model> violations = service.validate(text);
            LOG.info("Validating Datenpakete in {} finished with {} violation(s) in {}.", file, violations.size(), watch);
            return ResponseEntity.ok(violations);
        } catch (IOException ioe) {
            LOG.warn("Cannot upload and validate {}:", file.getOriginalFilename(), ioe);
            return ResponseEntity.badRequest().body(DefaultDatenpaketService.asModelList(ioe));
        }
    }

    /**
     * Formattiert das Datenpaket, das von der uebergebenen URI abgeholt wird,
     * in das gewuenscht Format wie HTML, XML, JSON oder CSV.
     *
     * @param uri     z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @param request der urspruengliche Request (zur Format-Bestimmung)
     * @return erzeugtes Format als Text
     * @throws IOException the io exception
     */
    @GetMapping("/format")
    @ApiOperation(value = "formattiert die uebergebene URI")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "uri",
                    value = "z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt",
                    required = true,
                    dataType = "string",
                    paramType = "query"
            )
    })
    public @ResponseBody String format(@RequestParam("uri") URI uri, HttpServletRequest request) throws IOException {
        LogWatch watch = new LogWatch();
        MimeType type = toMimeType(request);
        LOG.info("Formatting Datenpakete from {} as {}...", uri, type);
        String response = service.format(uri, type);
        LOG.info("Formatting Datenpakete from {} as {} finished after {}.", uri, type, watch);
        return response;
    }

    /**
     * Formattiert das Datenpaket, das als Text reinkommt, in das gewuenschte
     * Format wie HTML, XML, JSON oder CSV.
     *
     * @param body    the body
     * @return erzeugtes Format als Text
     * @param request der urspruengliche Request (zur Format-Bestimmung)
     */
    @PostMapping(
            value = "/format",
            produces = { MediaType.TEXT_HTML_VALUE, MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, "text/csv" }
    )
    public @ResponseBody String format(@RequestBody(required = false) String body,
                                       @RequestParam(required = false) String text,
                                       HttpServletRequest request) {
        LogWatch watch = new LogWatch();
        MimeType type = toMimeType(request);
        String content = (StringUtils.isBlank(text)) ? body : text;
        LOG.info("Formatting Datenpakete in posted stream of {} bytes as {}...", StringUtils.length(content), type);
        String response = service.format(content, type);
        LOG.info("Formatting Datenpakete in posted stream as {} finished in {}.", type, watch);
        return response;
    }

    /**
     * Laedt die gewuenschte Datei und formattiert die darin enthaltenen
     * Datenpakete. Da hierueber der Inhalt der Datei mit uebertragen wird,
     * wird dieser Service ueber POST angesprochen.
     *
     * @param file    gewuenschte Datei
     * @param request der urspruengliche Request (zur Format-Bestimmung)
     * @return erzeugtes Format als Text
     * @throws IOException the io exception
     */
    @PostMapping("/formatUploaded")
    public @ResponseBody String format (@RequestParam("file") MultipartFile file,
                                        HttpServletRequest request) throws IOException {
        LogWatch watch = new LogWatch();
        MimeType type = toMimeType(request);
        LOG.info("Formatting Datenpakete in posted file '{}' as {}...", file, type);
        String text = new String(file.getBytes());
        String response = service.format(text, type);
        LOG.info("Formatting Datenpakete in posted file '{}' as {} finished after {}.", file, type, watch);
        return response;
    }

    private static MimeType toMimeType(HttpServletRequest request) {
        String format = StringUtils.substringAfterLast(request.getServletPath(), ".").toLowerCase();
        if (StringUtils.isBlank(format)) {
            String[] accepted = request.getHeader("accept").split(",");
            format = accepted[0];
        }
        switch (format) {
            case "html":
                return MediaType.TEXT_HTML;
            case "xml":
                return MediaType.TEXT_XML;
            case "csv":
                return MediaType.valueOf("text/csv");
            case "json":
                return MediaType.APPLICATION_JSON;
            default:
                LOG.info("Will use '{}' as MimeType for unknown format parameter '{}'.", MediaType.TEXT_PLAIN, format);
                return MediaType.TEXT_PLAIN;
        }
    }

}
