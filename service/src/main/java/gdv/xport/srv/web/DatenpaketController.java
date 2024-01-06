/*
 * Copyright (c) 2017-2023 by Oliver Boehm
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

import gdv.xport.Datenpaket;
import gdv.xport.srv.config.AppConfig;
import gdv.xport.srv.service.DatenpaketService;
import gdv.xport.util.URLReader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import patterntesting.runtime.log.LogWatch;
import patterntesting.runtime.util.Converter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static gdv.xport.srv.config.AppConfig.TEXT_CSV;

/**
 * Dieser Controller repraesentiert das REST-Interface zur Datenpaket-Klasse.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
@RestController
@RequestMapping("/api")
public final class DatenpaketController {

    private static final Logger LOG = LogManager.getLogger(DatenpaketController.class);

    @Autowired
    private DatenpaketService service;

    /**
     * Validiert die uebergebene URI.
     *
     * @param uri z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @return gefundene Abweichungen bzw. Validierungs-Fehler
     */
    @Operation(summary = "validiert die uebergebene URI und gibt die gefundenen Abweichungen zurueck")
    @GetMapping("/v1/Abweichungen")
    public @ResponseBody List<Model> validate(
            @Parameter(description = "z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt") @RequestParam("uri") URI uri) {
        try {
            String content = readFrom(uri);
            return validate(content);
        } catch (IOException ioe) {
            LOG.warn("Cannot validate '{}':", uri, ioe);
            return ErrorModel.asModelList(ioe);
        }
    }

    /**
     * Validiert die eingelesenen Datenpakete und gibt die gefundenen
     * Abweichungen bzw. Verletzungen als Liste zurueck.
     *
     * @param text alternativ kann der Text auch als Parameter reinkommen
     * @return gefundene Abweichungen bzw. Validierungs-Fehler
     * @throws IOException bei Lesefehlern
     */
    @Operation(summary = "validiert den uebergebene Text im GDV-Format und gibt die gefundenen Abweichungen zurueck")
    @PostMapping("/v1/Abweichungen")
    public @ResponseBody
    List<Model> validate(
            @Parameter(description = "Datenpaket im GDV-Format (alternativ als Parameter)") @RequestParam(value = "text", required = false) String text,
            HttpServletRequest request) throws IOException {
        String body = IOUtils.toString(request.getInputStream(), StandardCharsets.ISO_8859_1);
        String content = (StringUtils.isBlank(text)) ? body : text;
        return validate(content);
    }

    /**
     * Validiert das eingegebene Formular und gibt die gefundenen
     * Abweichungen bzw. Verletzungen als Liste zurueck.
     *
     * @param map Eingabe-Formular mit "text"-Eintrag
     * @return gefundene Abweichungen bzw. Validierungs-Fehler
     * @since 6.5 (30.01.23)
     */
    @Operation(summary = "validiert den eingegebenen Text im GDV-Format und gibt die gefundenen Abweichungen zurueck")
    @PostMapping("/v1/Abweichungen/form")
    public List<Model> validate(
            @Parameter(description = "Eingabe-Formular mit Text im GDV-Format") @RequestParam("map") MultiValueMap map) {
        String content = Objects.toString(map.getFirst("text"), "");
        return validate(content);
    }

    /**
     * Laedt die gewuenschte Datei und validiert die darin enthaltenen
     * Datenpakete. Da hierueber der Inhalt der Datei mit uebertragen wird,
     * wird dieser Service ueber POST angesprochen.
     *
     * @param file gewuenschte Datei
     * @return gefundene Abweichungen bzw. Validierungs-Fehler
     */
    @Operation(summary = "dient zur Validierung einer Datei im GDV-Format")
    @PostMapping("/v1/Abweichungen/uploaded")
    public @ResponseBody List<Model> validate(@RequestParam("file") MultipartFile file) {
        try {
            String text = readFrom(file);
            return validate(text);
        } catch (IOException ioe) {
            LOG.warn("Cannot upload and validate {}:", file.getOriginalFilename(), ioe);
            return ErrorModel.asModelList(ioe);
        }
    }

    private List<Model> validate(String content) {
        LogWatch watch = new LogWatch();
        LOG.info("Validating Datenpakete of {}...", Converter.getMemoryAsString(StringUtils.length(content)));
        LOG.debug(content);
        List<Model> violations = service.validate(content);
        LOG.info("Validating Datenpakete finished with {} violation(s) in {}.", violations.size(), watch);
        return violations;
    }

    /**
     * Formattiert das Datenpaket, das von der uebergebenen URI abgeholt wird,
     * in das gewuenscht Format wie HTML, XML, JSON oder CSV. Das Format der
     * gewuenschten Rueckgabe wird dabei ueber Content Negotiation bestimmt,
     * d.h. anhand des Accept-Headers, der Endung oder des format-Parameters.
     *
     * @param uri     z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @param format  statt per Content Negotiation kann auch der format-Parameter               belegt werden
     * @param request der HTTP-Request
     * @return Datenpaket, das in das angeforderte Format transformiert wird
     * @throws IOException the io exception
     */
    @Operation(summary = "Liest das Datenpaket von der angegebenen URI und gibt es im gewuenschten Format zurueck." +
            " Der Stern '*' in /Datenpaket* steht dabei fuer ein beliebiges Muster." +
            " So kann z.B. auch /Datenpaket.csv als URI angegeben werden." +
            " Das erleichtert das Abspeichern des Ergebnisses im Web-Browser.")
    @GetMapping(path = "/v1/Datenpaket*")
    public @ResponseBody ResponseEntity<Datenpaket> importDatenpaket(
            @Parameter(description = "URI, die auf einen Datensatz verweist",
                    example = "http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt") @RequestParam("uri") URI uri,
            @Parameter(description = "Ausgabe-Format (HTML, XML, JSON, CSV oder TEXT);" +
                    " normalerweise wird das Format ueber den Accept-Header vorgegeben, kann aber hierueber explizit gesetzt werden.",
                    example = "JSON") @RequestParam(value = "format", required = false) String format,
            HttpServletRequest request) throws IOException {
        String content = readFrom(uri);
        return getDatenpaketResponseEntity(format, content, request);
    }

    @Operation(summary = "Liest das Datenpaket von der angegebenen URI und gibt es im gewuenschten Format zurueck." +
            " Der Stern '*' in /Datenpaket* steht dabei fuer ein beliebiges Muster." +
            " So kann z.B. auch /Datenpaket.csv als URI angegeben werden." +
            " Das erleichtert das Abspeichern des Ergebnisses im Web-Browser." +
            " Im Gegensatz zu v1 wird hier das Format nicht ueber den format-Parameter bestimmt," +
            " sondern ueber den Content-Type (Content-Negotiation).")
    @GetMapping(path = "/v2/Datenpaket*",
            produces = {MediaType.TEXT_HTML_VALUE, MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE, AppConfig.TEXT_CSV, MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody Datenpaket importDatenpaketV2(
            @Parameter(description = "URI, die auf einen Datensatz verweist",
                    example = "http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt") @RequestParam("uri") URI uri)
            throws IOException {
        String content = readFrom(uri);
        return service.importDatenpaket(content);
    }

    private static String readFrom(URI uri) throws IOException {
        LogWatch watch = new LogWatch();
        LOG.info("Reading Datenpakete from {}...", uri);
        URLReader urlReader = new URLReader(uri.toURL());
        String content = urlReader.read();
        LOG.info("Reading Datenpakete from {} finished after {} with {} bytes.", uri, watch, content.length());
        return content;
    }

    @Operation(summary = "Liest das Datenpaket von der angegebenen URI und gibt es im gewuenschten Format zurueck." +
            " Der Stern '*' in /Datenpaket* steht dabei fuer ein beliebiges Muster." +
            " So kann z.B. auch /Datenpaket.csv als URI angegeben werden." +
            " Das erleichtert das Abspeichern des Ergebnisses im Web-Browser." +
            " Im Gegensatz zu v1 wird hier das Format nicht ueber den format-Parameter bestimmt," +
            " sondern ueber den Content-Type (Content-Negotiation).")
    @PostMapping(
            path = "/v2/Datenpaket*", produces = {MediaType.TEXT_HTML_VALUE, MediaType.TEXT_XML_VALUE,
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, TEXT_CSV}
    )
    public @ResponseBody ResponseEntity<Datenpaket> importDatenpaketV2(
            HttpServletRequest request) throws IOException {
        String content = IOUtils.toString(request.getInputStream(), StandardCharsets.ISO_8859_1);
        return getDatenpaketResponseEntity("", content, request);
    }

    /**
     * Die Umwandlung eines Datenpakets in das gewuenschte Datenformat wird
     * anhand des Accept-Headers (Content Negotiation) oder anhand des Suffixes
     * durchgefuehrt. Das Datenpaket kommt dabei als Text im GDV-Format rein.
     *
     * @param format  statt per Content Negotiation kann auch der format-Parameter belegt werden
     * @param request der HTTP-Request mit Datenpaket im Body
     * @return Datenpaket string
     */
    @Operation(summary = "Liest das uebergebene Datenpaket und gibt es im gewuenschten Format zurueck." +
            " Der Stern '*' in /Datenpaket* steht dabei fuer ein beliebiges Muster." +
            " So kann z.B. auch /Datenpaket.csv als URI angegeben werden." +
            " Das erleichtert das Abspeichern des Ergebnisses im Web-Browser.")
    @PostMapping(
            path = "/v1/Datenpaket*", produces = {MediaType.TEXT_HTML_VALUE, MediaType.TEXT_XML_VALUE,
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, TEXT_CSV}
    )
    public @ResponseBody ResponseEntity<Datenpaket> importDatenpaket(
            @Parameter(description = "Ausgabe-Format (HTML, XML, JSON, CSV oder TEXT);" +
                    " normalerweise wird das Format ueber den Accept-Header vorgegeben, kann aber hierueber explizit gesetzt werden.",
                    example = "JSON") @RequestParam(required = false) String format,
            HttpServletRequest request) throws IOException {
        String content = IOUtils.toString(request.getInputStream(), StandardCharsets.ISO_8859_1);
        return getDatenpaketResponseEntity(format, content, request);
    }

    /**
     * Die Umwandlung eines Datenpakets in das gewuenschte Datenformat wird
     * anhand des Accept-Headers (Content Negotiation) oder anhand des Suffixes
     * durchgefuehrt. Das Datenpaket kommt dabei als Text im GDV-Format rein.
     *
     * @param body     Eingabe-Formular mit "text"- und "format"-Eintrag
     * @param request  der HTTP-Request
     * @return Datenpaket Datenpaket
     */
    @Operation(summary = "Liest das uebergebene Datenpaket und gibt es im gewuenschten Format zurueck.")
    @PostMapping(
            path = "/v1/Datenpaket/form", produces = {MediaType.TEXT_HTML_VALUE, MediaType.TEXT_XML_VALUE,
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, TEXT_CSV}
    )
    public @ResponseBody ResponseEntity<Datenpaket> importDatenpaketForm(
            @Parameter(description = "Eingabe-Formular mit Text im GDV-Format") @RequestParam MultiValueMap body,
            HttpServletRequest request) {
        String content = Objects.toString(body.getFirst("text"), "");
        String format = Objects.toString(body.getFirst("format"), "");
        return getDatenpaketResponseEntity(format, content, request);
    }

    private ResponseEntity<Datenpaket> getDatenpaketResponseEntity(String format, String content, HttpServletRequest request) {
        Datenpaket datenpaket = service.importDatenpaket(content);
        MediaType type = toMediaType(format, request);
        return ResponseEntity
                .ok()
                .contentType(type)
                .body(datenpaket);
    }

    /**
     * Laedt die gewuenschte Datei und formattiert die darin enthaltenen
     * Datenpakete. Da hierueber der Inhalt der Datei mit uebertragen wird,
     * wird dieser Service ueber POST angesprochen.
     *
     * @param file gewuenschte Datei
     * @return erzeugtes Format als Text
     * @throws IOException the io exception
     */
    @Operation(summary = "dient zum Laden und Anzeigen einer Datei im GDV-Format")
    @PostMapping("/v1/Datenpaket/uploaded")
    public @ResponseBody Datenpaket uploadDatenpaket (
            @RequestParam MultipartFile file) throws IOException {
        String text = readFrom(file);
        return importDatenpaketFrom(text);
    }

    private String readFrom(MultipartFile file) throws IOException {
        LogWatch watch = new LogWatch();
        LOG.info("Reading Datenpakete from {}...", file);
        String text = new String(file.getBytes());
        LOG.info("Reading Datenpakete from {} finished after {} with {} bytes.", file, watch, text.length());
        return text;
    }

    private static Datenpaket importDatenpaketFrom(String content) throws IOException {
        Datenpaket datenpaket = new Datenpaket();
        datenpaket.importFrom(content);
        return datenpaket;
    }

    private static MediaType toMediaType(String format, HttpServletRequest request) {
        if (StringUtils.isBlank(format)) {
            return toMediaTypes(request).get(0);
        } else {
            return toMediaType(format);
        }
    }

    private static List<MediaType> toMediaTypes(HttpServletRequest request) {
        Set<MediaType> mimeTypes = new LinkedHashSet<>();
        String format = StringUtils.substringAfterLast(request.getServletPath(), ".");
        if (StringUtils.isNotBlank(format)) {
            mimeTypes.add(toMediaType(format));
        }
        String[] accepted = Objects.toString(request.getHeader("accept"), "").split(",");
        for (String accept : accepted) {
            mimeTypes.add(toMediaType(accept));
        }
        mimeTypes.add(MediaType.TEXT_PLAIN);
        return new ArrayList<>(mimeTypes);
    }

    private static MediaType toMediaType(String format) {
        switch (format.toLowerCase()) {
            case "html":
                return MediaType.TEXT_HTML;
            case "xml":
                return MediaType.TEXT_XML;
            case "csv":
                return MediaType.valueOf(TEXT_CSV);
            case "json":
                return MediaType.APPLICATION_JSON;
            case "plain":
                return MediaType.TEXT_PLAIN;
            default:
                try {
                    return MediaType.valueOf(format);
                } catch (InvalidMediaTypeException ex) {
                    LOG.info("Will use '{}' as MimeType for unknown format parameter '{}'.", MediaType.TEXT_PLAIN,
                            format);
                    LOG.debug("Details:", ex);
                    return MediaType.TEXT_PLAIN;
                }
        }
    }

    /**
     * Falsche URI wurde als Parameter angegeben - bad request.
     *
     * @param request Anfrage-Request
     * @param ex      Ursache
     * @return ErrorDetail
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MalformedURLException.class, IllegalArgumentException.class})
    public ErrorDetail handleException(HttpServletRequest request, Exception ex) {
        ErrorDetail errDetail = new ErrorDetail(request, HttpStatus.BAD_REQUEST, ex);
        LOG.info("Call of '{}' fails: {}", request.getRequestURI(), errDetail);
        return errDetail;
    }

}
