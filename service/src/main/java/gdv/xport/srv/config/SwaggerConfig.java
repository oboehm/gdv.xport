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
 * (c)reated 20.02.2017 by oliver (ob@oasd.de)
 */

package gdv.xport.srv.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ueber diese Klasse wird Swagger aktiviert.
 *
 * @author oboehm
 * @since 3.0 (20.02.2017)
 */
@Configuration
public class SwaggerConfig {

    @Value("${application.version: application.version}")
    private String version;

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("GDV-Xport API")
                        .description("Diese API dient als Demo fuer die gdv-xport-lib.")
                        .version(version)
                        .contact(new Contact().name("Oli B.").url("https://github.com/oboehm"))
                        .license(new License().name("Apache License 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Home")
                        .url("/")
                );
    }

}
