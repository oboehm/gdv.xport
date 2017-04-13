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
import gdv.xport.util.*;
import net.sf.oval.ConstraintViolation;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
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

    private static final Logger LOG = LogManager.getLogger(DefaultDatenpaketService.class);

    /**
     * Validiert die uebergebene URI.
     *
     * @param uri z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @return List mit Constraint-Verletzungen
     */
    @Override
    public List<Model> validate(URI uri) {
        Datenpaket datenpaket = new Datenpaket();
        try {
            datenpaket.importFrom(uri);
            return validate(datenpaket);
        } catch (IOException ioe) {
            LOG.warn("Cannot validate '{}':", uri, ioe);
            return asModelList(ioe);
        }
    }

    /**
     * Validiert die eingelesenen Datenpakete.
     *
     * @param text Text, der ueber die Leitung reinkommt.
     * @return the response entity
     */
    @Override
    public List<Model> validate(String text) {
        Datenpaket datenpaket = new Datenpaket();
        try {
            datenpaket.importFrom(text);
            return validate(datenpaket);
        } catch (IOException ioe) {
            LOG.warn("Cannot validate '{}':", StringUtils.abbreviate(text, 18), ioe);
            return asModelList(ioe);
        }
    }

    /**
     * Holt sich das Datenpaket von der angegebenen URI und formattiert das
     * Datenpaket anhand des uebergebenen Formatters.
     *
     * @param uri z.B. http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt
     * @param mimeType gewuenschte Formattierung
     * @return string formatiertes Datenpaket
     * @throws IOException kann beim Lesen der URI auftreten
     */
    @Override
    public String format(URI uri, MimeType mimeType) throws IOException {
        Datenpaket datenpaket = new Datenpaket();
        datenpaket.importFrom(uri);
        return format(datenpaket, mimeType);
    }

    /**
     * Holt sich das Datenpaket, das als Text im GDV-Format uebergeben wird
     * und formattiert das Datenpaket anhand des uebergebenen Formatters.
     *
     * @param text Text, der ueber die Leitung reinkommt.
     * @param mimeType gewuenschte Formattierung
     * @return string formatiertes Datenpaket
     */
    @Override
    public String format(String text, MimeType mimeType) {
        Datenpaket datenpaket = new Datenpaket();
        try {
            datenpaket.importFrom(text);
            return format(datenpaket, mimeType);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("strange input '" + text + "'", ioe);
        }
    }

    private static String format(Datenpaket datenpaket, MimeType mimeType) throws IOException {
        StringWriter swriter = new StringWriter();
        try {
            AbstractFormatter formatter = getFormatterFor(mimeType, swriter);
            formatter.write(datenpaket);
        } finally {
            swriter.close();
        }
        return swriter.toString();
    }

    private static AbstractFormatter getFormatterFor(MimeType mimeType, Writer writer) {
        String type = mimeType.getSubtype().toLowerCase();
        switch(type) {
            case "html":
                return new HtmlFormatter(writer);
            case "plain":
                return new NullFormatter(writer);
            case "xml":
                return new XmlFormatter(writer);
            case "csv":
            case "comma-separated-values":
                return new CsvFormatter(writer);
            case "json":
                return new JsonFormatter(writer);
            default:
                throw new UnsupportedOperationException("mime-type '" + mimeType + "' is not (yet) supported");
        }
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
            m.addAttribute("causes", cv.getCauses());
            models.add(m);
        }
        return models;
    }

    /**
     * Hierueber laesst sich eine {@link IOException} in eine "Violation"-Liste
     * umwandeln, die fuer die Ausgabe der Validierung zum Einsatz kommt.
     * <p>
     *     Die Methode ist public, da sie nicht nur intern, sondern auch an
     *     anderer Stelle benoetigt wird. Eventuell wird sie in eine eigene
     *     Util-Klasse wandern (eher ungern) oder an einer besser geeigneten
     *     Stelle abgelegt (ob, 09-Feb-2017),
     *     Momentan ist diese Methode hier nur geparkt, bis ich einen besseren
     *     Platz dafuer
     * </p>
     * <p>
     * TODO: besseren Platz fuer diese Methode suchen (ob, 09-Feb-2017)
     * </p>
     *
     * @param ioe the ioe
     * @return the list
     */
    public static List<Model> asModelList(IOException ioe) {
        List<Model> models = new ArrayList<Model>();
        Model m = new ExtendedModelMap();
        m.addAttribute("message", ioe.getMessage());
        m.addAttribute("causes", ioe);
        models.add(m);
        return models;
    }

}
