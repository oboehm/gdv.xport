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

import gdv.xport.srv.web.converter.DatenpaketHttpMessageConverter;
import gdv.xport.srv.web.converter.ErrorDetailHttpMessageConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Ueber AppConfig werden einige Konfigurationseinstellungen vorgenommen.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

    private static final Logger LOG = LogManager.getLogger(AppConfig.class);

    /** MediaType als String fuer CSV. */
    public static final String TEXT_CSV = "text/comma-separated-values";

    /** MediaType fuer CSV. */
    public static final MediaType MEDIA_TYPE_TEXT_CSV = MediaType.valueOf(TEXT_CSV);

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
        // see https://github.com/springdoc/springdoc-openapi/issues/2143
        converters.add(new StringHttpMessageConverter());
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new DatenpaketHttpMessageConverter(MediaType.TEXT_HTML));
        converters.add(new DatenpaketHttpMessageConverter(MediaType.TEXT_XML, MediaType.APPLICATION_XML));
        converters.add(new DatenpaketHttpMessageConverter(MediaType.TEXT_PLAIN));
        converters.add(new DatenpaketHttpMessageConverter(MediaType.APPLICATION_JSON));
        converters.add(new DatenpaketHttpMessageConverter(MEDIA_TYPE_TEXT_CSV));
        converters.add(new ErrorDetailHttpMessageConverter(MediaType.TEXT_HTML));
        converters.add(new ErrorDetailHttpMessageConverter(MediaType.TEXT_XML, MediaType.APPLICATION_XML));
        converters.add(new ErrorDetailHttpMessageConverter(MediaType.TEXT_PLAIN));
        //converters.add(new ErrorDetailHttpMessageConverter(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8));
        converters.add(new ErrorDetailHttpMessageConverter(MEDIA_TYPE_TEXT_CSV));
        LOG.info("Message converters {} are configured.", converters);
    }

    /**
     * Hierueber verknuepfen wir die Endung ".csv" mit dem entsprechenden
     * {@link MediaType}. Zusammen mit dem DatenpaketHttpMessageConverter
     * sorgt das dafuer, das Datenpakete als CSV zurueckgegeben werden koennen.
     *
     * @param configurer darueber wird ".csv" als Suffix bekannt gemacht
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.mediaType("csv", MEDIA_TYPE_TEXT_CSV)
                  .mediaType("html", MediaType.TEXT_HTML)
                  .mediaType("text", MediaType.TEXT_PLAIN)
                  .mediaType("txt", MediaType.TEXT_PLAIN)
                  .defaultContentType(MediaType.TEXT_PLAIN)
                  .favorParameter(false)
                  .favorPathExtension(true)
                  ;
    }

}
