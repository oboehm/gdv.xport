/*
 * Copyright (c) 2023 by Oli B.
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
 * (c)reated 29.01.23 by oboehm
 */
package gdv.xport.srv.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Klasse ThymeleafConfig.
 *
 * @author oboehm
 * @since 6.5 (29.01.23)
 */
@Configuration
@EnableWebMvc
public class ThymeleafConfig { //extends WebMvcConfigurerAdapter implements ApplicationContextAware {

//    private ApplicationContext applicationContext;
//
//    public void setApplicationContext(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    @Bean
//    public ViewResolver viewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        resolver.setCharacterEncoding("UTF-8");
//        return resolver;
//    }
//
//    @Bean
//    public ISpringTemplateEngine templateEngine() {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setEnableSpringELCompiler(true);
//        engine.setTemplateResolver(templateResolver());
//        return engine;
//    }
//
//    private ITemplateResolver templateResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setApplicationContext(applicationContext);
//        resolver.setPrefix("/WEB-INF/templates/");
//        resolver.setTemplateMode(TemplateMode.HTML);
//        return resolver;
//    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

}
