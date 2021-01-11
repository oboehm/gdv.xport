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

import gdv.xport.Datenpaket;
import gdv.xport.srv.config.AppConfig;
import gdv.xport.srv.service.DatenpaketService;
import gdv.xport.srv.service.DefaultDatenpaketService;
import gdv.xport.util.URLReader;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import patterntesting.runtime.log.LogWatch;
import patterntesting.runtime.util.Converter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    @ApiOperation(value = "validiert die uebergebene URI und gibt die gefundenen Abweichungen zurueck")
    @GetMapping("/v1/Abweichungen")
    public @ResponseBody List<Model> validate(
            @ApiParam(value = "z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt") @RequestParam("uri") URI uri) {
        try {
            String content = readFrom(uri);
            return validate(content);
        } catch (IOException ioe) {
            LOG.warn("Cannot validate '{}':", uri, ioe);
            return DefaultDatenpaketService.asModelList(ioe);
        }
    }

    /**
     * Validiert die eingelesenen Datenpakete und gibt die gefundenen
     * Abweichungen bzw. Verletzungen als Liste zurueck.
     *
     * @param body Text, der ueber die Leitung reinkommt.
     * @param text alternativ kann der Text auch als Parameter reinkommen
     * @return gefundene Abweichungen bzw. Validierungs-Fehler
     */
    @ApiOperation(value = "validiert den uebergebene Text im GDV-Format und gibt die gefundenen Abweichungen zurueck")
    @PostMapping("/v1/Abweichungen")
    public @ResponseBody
    List<Model> validate(
            @ApiParam(value = "Datenpaket im GDV-Format") @RequestBody(required = false) String body,
            @ApiParam(value = "Datenpaket im GDV-Format (alternativ als Parameter)") @RequestParam(required = false) String text) {
        String content = (StringUtils.isBlank(text)) ? body : text;
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
    @ApiOperation("dient zur Validierung einer Datei im GDV-Format")
    @PostMapping("/v1/Abweichungen/uploaded")
    public @ResponseBody List<Model> validate(@RequestParam("file") MultipartFile file) {
        try {
            String text = readFrom(file);
            return validate(text);
        } catch (IOException ioe) {
            LOG.warn("Cannot upload and validate {}:", file.getOriginalFilename(), ioe);
            return DefaultDatenpaketService.asModelList(ioe);
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
     * @param uri z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @param format statt per Content Negotiation kann auch der format-Parameter
     *               belegt werden
     * @return Datenpaket, das dann ueber Content Negotiation in das
     *         angeforderte Format transformiert wird
     * @throws IOException the io exception
     */
    @ApiOperation(value = "Liest das Datenpaket von der angegebenen URI und gibt es im gewuenschten Format zurueck." +
            " Der Stern '*' in /Datenpaket* steht dabei fuer ein beliebiges Muster." +
            " So kann z.B. auch /Datenpaket.csv als URI angegeben werden." +
            " Das erleichtert das Abspeichern des Ergebnisses im Web-Browser.")
    @GetMapping(path = "/v1/Datenpaket*")
    public @ResponseBody String importDatenpaket(
            @ApiParam(value = "URI, die auf einen Datensatz verweist",
                    example = "http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt") @RequestParam("uri") URI uri,
            @ApiParam(value = "Ausgabe-Format (HTML, XML, JSON, CSV oder TEXT);" +
                    " normalerweise wird das Format ueber den Accept-Header vorgegeben, kann aber hierueber explizit gesetzt werden.",
                    example = "JSON") @RequestParam(required = false) String format,
            HttpServletRequest request) throws IOException {
        String content = readFrom(uri);
        return formatDatenpaket(content, format, request);
    }

    @ApiOperation(value = "Liest das Datenpaket von der angegebenen URI und gibt es im gewuenschten Format zurueck." +
            " Der Stern '*' in /Datenpaket* steht dabei fuer ein beliebiges Muster." +
            " So kann z.B. auch /Datenpaket.csv als URI angegeben werden." +
            " Das erleichtert das Abspeichern des Ergebnisses im Web-Browser." +
            " Im Gegensatz zu v1 wird hier das Format nicht ueber den format-Parameter bestimmt," +
            " sondern ueber den Content-Type (Content-Negotiation).")
    @GetMapping(path = "/v2/Datenpaket*",
            produces = {MediaType.TEXT_HTML_VALUE, MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE, AppConfig.TEXT_CSV, MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody Datenpaket importDatenpaketV2(
            @ApiParam(value = "URI, die auf einen Datensatz verweist",
                    example = "http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt") @RequestParam("uri") URI uri)
            throws IOException {
        String content = readFrom(uri);
        return service.importDatenpaket(content);
    }

    private static String readFrom(@RequestParam("uri") URI uri) throws IOException {
        LogWatch watch = new LogWatch();
        LOG.info("Reading Datenpakete from {}...", uri);
        URLReader urlReader = new URLReader(uri.toURL());
        String content = urlReader.read();
        LOG.info("Reading Datenpakete from {} finished after {} with {} bytes.", uri, watch, content.length());
        return content;
    }

    /**
     * Die Umwandlung eines Datenpakets in das gewuenschte Datenformat wird
     * anhand des Accept-Headers (Content Negotiation) oder anhand des Suffixes
     * durchgefuehrt. Das Datenpaket kommt dabei als Text im GDV-Format rein.
     *
     * @param body Datenpaket im GDV-Format
     * @param text alternativ kann das Datenpaket auch als Parameter reinkommen
     * @param format statt per Content Negotiation kann auch der format-Parameter
     *               belegt werden
     * @return Datenpaket
     */
    @ApiOperation("Liest das uebergebene Datenpaket und gibt es im gewuenschten Format zurueck." +
            " Der Stern '*' in /Datenpaket* steht dabei fuer ein beliebiges Muster." +
            " So kann z.B. auch /Datenpaket.csv als URI angegeben werden." +
            " Das erleichtert das Abspeichern des Ergebnisses im Web-Browser.")
    @PostMapping(
            path = "/v1/Datenpaket*", produces = {MediaType.TEXT_HTML_VALUE, MediaType.TEXT_XML_VALUE,
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, TEXT_CSV}
    )
    public @ResponseBody String importDatenpaket(
            @ApiParam(value = "Datenpaket im GDV-Format") @RequestBody(required = false) String body,
            @ApiParam(value = "Datenpaket im GDV-Format (als Alternative zur Uebergabe im Body)") @RequestParam(required = false) String text,
            @ApiParam(value = "Ausgabe-Format (HTML, XML, JSON, CSV oder TEXT);" +
                    " normalerweise wird das Format ueber den Accept-Header vorgegeben, kann aber hierueber explizit gesetzt werden.",
                    example = "JSON") @RequestParam(required = false) String format,
            HttpServletRequest request) {
        String content = (StringUtils.isBlank(text)) ? body : text;
        return formatDatenpaket(content, format, request);
    }

    private String formatDatenpaket(String content, String format, HttpServletRequest request) {
        MimeType type = toMimeType(format, request);
        return service.format(content, type);
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
    @ApiOperation("dient zum Laden und Anzeigen einer Datei im GDV-Format")
    @PostMapping("/v1/Datenpaket/uploaded")
    public @ResponseBody Datenpaket uploadDatenpaket (
            @RequestParam("file") MultipartFile file) throws IOException {
        String text = readFrom(file);
        return importDatenpaketFrom(text);
    }

    private String readFrom(@RequestParam("file") MultipartFile file) throws IOException {
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

    private static MimeType toMimeType(String format, HttpServletRequest request) {
        if (StringUtils.isBlank(format)) {
            return toMimeTypes(request).get(0);
        } else {
            return toMimeType(format);
        }
    }

    private static List<MimeType> toMimeTypes(HttpServletRequest request) {
        Set<MimeType> mimeTypes = new LinkedHashSet<>();
        String format = StringUtils.substringAfterLast(request.getServletPath(), ".");
        if (StringUtils.isNotBlank(format)) {
            mimeTypes.add(toMimeType(format));
        }
        String[] accepted = request.getHeader("accept").split(",");
        for (String accept : accepted) {
            mimeTypes.add(toMimeType(accept));
        }
        mimeTypes.add(MimeTypeUtils.TEXT_PLAIN);
        return new ArrayList<>(mimeTypes);
    }

    private static MimeType toMimeType(String format) {
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
