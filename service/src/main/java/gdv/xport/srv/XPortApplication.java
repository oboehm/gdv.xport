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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import patterntesting.runtime.monitor.ClasspathMonitor;
import patterntesting.runtime.monitor.ResourcepathMonitor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * Haupt-Anwendung fuer Spring.
 */
@SpringBootApplication(scanBasePackages = {"gdv.xport.srv"})
@EnableSwagger2
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
