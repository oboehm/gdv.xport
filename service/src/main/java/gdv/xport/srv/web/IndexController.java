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
 * (c)reated 19.02.17 by oliver (ob@oasd.de)
 */

package gdv.xport.srv.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Dieser Controller ist die Verbindung zur Index-Seite. Sie stammt aus
 * https://springframework.guru/spring-boot-web-application-part-2-using-thymeleaf/.
 *
 * @author oboehm
 * @since 3.0 (19.02.2017)
 */
@Controller
public class IndexController {

    /**
     * Diese Methode verwendet Spring-Boot, um "/" auf "index.html" zu mappen.
     *
     * @return "index"
     */
    @GetMapping("/")
    String index(){
        return "index";
    }

    /**
     * Mapping fuer Validate-Seite
     *
     * @return "validate"
     */
    @GetMapping("/validate")
    String validate(){
        return "validate";
    }

    /**
     * Mapping fuer Format-Seite
     *
     * @return "format"
     */
    @GetMapping("/format")
    String format(){
        return "format";
    }

}
