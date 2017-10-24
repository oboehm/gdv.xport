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
 * (c)reated 17.06.17 by oliver (ob@oasd.de)
 */
package gdv.xport.srv.config;

import gdv.xport.srv.web.util.*;
import org.apache.logging.log4j.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.web.servlet.config.annotation.*;

import java.util.*;

/**
 * Ueber AppConfig werden einige Konfigurationseinstellungen vorgenommen.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LogManager.getLogger(AppConfig.class);

    /**
     * Hierueber wird der LogIntercepter registriert.
     *
     * @param registry fuer die Registrierung
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
    }

    /**
     * Fuer Content-Negotiationen registrieren wir hier einen eigenen
     * HttpMessageConverter, der die Konvertierung eines Datenpakets in
     * verschiedene Formate wie Text, CSV oder XML unterstuetzt.
     *
     * @param converters Liste mit registrierten Converter
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new DatenpaketHttpMessageConverter(MediaType.TEXT_XML, MediaType.APPLICATION_XML));
        converters.add(new DatenpaketHttpMessageConverter(MediaType.TEXT_PLAIN));
        converters.add(new DatenpaketHttpMessageConverter(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8));
        converters.add(new DatenpaketHttpMessageConverter(MediaType.valueOf("text/comma-separated-values")));
        LOG.info("Message converters {} are configured.", converters);
    }

}