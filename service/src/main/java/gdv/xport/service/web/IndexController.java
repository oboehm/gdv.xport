/*
 * Copyright 2017 Dr. Gueldener Firmengruppe
 */

package gdv.xport.service.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * Diese Methode verwendet Spring-Boot, um "/" auf "index.hmtl" zu mappen.
     *
     * @return "index"
     */
    @RequestMapping("/")
    String index(){
        return "index";
    }

}
