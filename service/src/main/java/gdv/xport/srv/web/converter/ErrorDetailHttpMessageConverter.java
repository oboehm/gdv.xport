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
 * (c)reated 02.11.2017 by oboehm (ob@oasd.de)
 */
package gdv.xport.srv.web.converter;

import gdv.xport.srv.web.*;
import org.apache.logging.log4j.*;
import org.springframework.http.*;
import org.springframework.http.converter.*;

import java.io.*;
import java.nio.charset.*;

import static gdv.xport.srv.config.AppConfig.*;

/**
 * Klasse ErrorDetailHttpMessageConverter.
 *
 * @author oboehm
 * @since 3.0 (02.11.2017)
 */
public final class ErrorDetailHttpMessageConverter extends AbstractHttpMessageConverter<ErrorDetail> {

    private static final Logger LOG = LogManager.getLogger(ErrorDetailHttpMessageConverter.class);

    /**
     * Erzeugt eine MessageConverter fuer die angegebene Media-Typen.
     *
     * @param supportedMediaTypes unterstuetzte Media-Typen
     */
    public ErrorDetailHttpMessageConverter(MediaType... supportedMediaTypes) {
        super(supportedMediaTypes);
    }

    /**
     * Indicates whether the given class is supported by this converter.
     *
     * @param clazz the class to test for support
     * @return {@code true} if supported; {@code false} otherwise
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return ErrorDetail.class.equals(clazz);
    }

    @Override
    protected ErrorDetail readInternal(Class<? extends ErrorDetail> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        LOG.info("Reading internal {}...", clazz);
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void writeInternal(ErrorDetail errorDetail, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        LOG.info("Writing {} for {}.", errorDetail, getSupportedMediaTypes());
        Writer writer = new OutputStreamWriter(outputMessage.getBody(), StandardCharsets.ISO_8859_1);
        switch (getSupportedMediaTypes().get(0).toString()) {
            case MediaType.TEXT_HTML_VALUE:
                writeInternalHTML(errorDetail, writer);
                break;
            case TEXT_CSV:
                writeInternalCSV(errorDetail, writer);
                break;
            default:
                writer.write(errorDetail.toString());
                writer.write('\n');
                break;
        }
        writer.flush();
    }

    private static void writeInternalHTML(ErrorDetail errorDetail, Writer writer) throws IOException {
        writer.write("<html>\n");
        writer.write("  <body>\n");
        writer.write("    <h1>Status " + errorDetail.getStatus() + " (" + errorDetail.getStatus().name() + ")</h1>\n");
        writer.write("    <p>" + errorDetail.getMessage() + "</p>\n");
        writer.write("  </body>\n");
        writer.write("</html>\n");
    }

    private static void writeInternalCSV(ErrorDetail errorDetail, Writer writer) throws IOException {
        writer.write("URI;" + errorDetail.getRequest() + ";\n");
        writer.write("Status;" + errorDetail.getStatus() + " (" + errorDetail.getStatus().getReasonPhrase() + ");\n");
        writer.write("Message;" + errorDetail.getMessage() + ";\n");
        writer.write("Time;" + errorDetail.getWhen() + ";\n");
    }

}
