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

import gdv.xport.config.LogConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import patterntesting.runtime.monitor.ClasspathMonitor;
import patterntesting.runtime.monitor.ResourcepathMonitor;

import java.util.Arrays;

/**
 * Haupt-Anwendung fuer Spring.
 */
@ComponentScan(basePackages = {"gdv.xport.srv"})
@SpringBootApplication
public class XPortApplication {

    private static final Logger LOG = LogManager.getLogger(XPortApplication.class);

    @Value("${logging.url}")
    private LogConfig logConfig;

    static {
        ClasspathMonitor.registerAsMBean();
        ResourcepathMonitor.getInstance().registerMeAsMBean();
    }

    /**
     * Liefert die Logging-Konfiguration.
     *
     * @return Logging-Konfiguration
     */
    public LogConfig getLogConfig() {
        return this.logConfig;
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
