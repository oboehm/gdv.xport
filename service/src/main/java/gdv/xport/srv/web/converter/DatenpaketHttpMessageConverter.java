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
 * (c)reated 14.10.17 by oliver (ob@oasd.de)
 */
package gdv.xport.srv.web.converter;

import gdv.xport.*;
import gdv.xport.srv.config.AppConfig;
import gdv.xport.util.*;
import org.apache.logging.log4j.*;
import org.springframework.http.*;
import org.springframework.http.converter.*;

import java.io.*;

/**
 * Class DatenpaketHttpMessageConverter.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 * @since 3.0.0 (14.10.17)
 */
public final class DatenpaketHttpMessageConverter extends AbstractHttpMessageConverter<Datenpaket> {

    private static final Logger LOG = LogManager.getLogger(DatenpaketHttpMessageConverter.class);
    private final AbstractFormatter formatter;

    public DatenpaketHttpMessageConverter(MediaType... type) {
        this(getFormatterFor(type[0]), type);
    }

    private DatenpaketHttpMessageConverter(AbstractFormatter formatter, MediaType... type) {
        super(type);
        this.formatter = formatter;
    }

    private static AbstractFormatter getFormatterFor(MediaType type) {
        switch (type.toString()) {
            case MediaType.TEXT_HTML_VALUE:
                return new HtmlFormatter();
            case MediaType.TEXT_XML_VALUE:
            case MediaType.APPLICATION_XML_VALUE:
                return new XmlFormatter();
            case MediaType.APPLICATION_JSON_VALUE:
            case MediaType.APPLICATION_JSON_UTF8_VALUE:
                return new JsonFormatter();
            case AppConfig.TEXT_CSV:
                return new CsvFormatter();
            default:
                LOG.info("Using NullFormatter for MediaType {}.", type);
                return new NullFormatter();
        }
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return Datenpaket.class.equals(aClass);
    }

    @Override
    protected Datenpaket readInternal(Class<? extends Datenpaket> aClass, HttpInputMessage httpInputMessage)
            throws IOException, HttpMessageNotReadableException {
        LOG.info("Reading internal {}...", aClass);
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void writeInternal(Datenpaket datenpaket, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        LOG.info("Writing {} for {}.", datenpaket, getSupportedMediaTypes());
        OutputStream out = outputMessage.getBody();
        formatter.setWriter(out);
        formatter.write(datenpaket);
        out.flush();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + getSupportedMediaTypes();
    }

}
