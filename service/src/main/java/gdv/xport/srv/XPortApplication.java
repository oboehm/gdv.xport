/*
 * Copyright (c) 2017 by Oli B.
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
 * (c)reated 13.02.2017 by Oli B. (ob@aosd.de)
 */

package gdv.xport.srv;

import org.apache.logging.log4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import patterntesting.runtime.monitor.*;

import java.util.*;

/**
 * Haupt-Anwendung fuer Spring.
 */
@ComponentScan(basePackages = {"gdv.xport.srv"})
@SpringBootApplication
public class XPortApplication {

    private static final Logger LOG = LogManager.getLogger(XPortApplication.class);

    static {
        ClasspathMonitor.registerAsMBean();
        ResourcepathMonitor.getInstance().registerMeAsMBean();
    }

    /**
     * Einstiegspunkt fuer die Spring-Anwendung
     *
     * @param args Input-Argumente, ueblicherweise leer
     */
    public static void main(String[] args) {
        LOG.info("XPortApplicaton will be started with args={}.", Arrays.toString(args));
		SpringApplication.run(XPortApplication.class, args);
        LOG.info("All services are up and running.");
	}

}
