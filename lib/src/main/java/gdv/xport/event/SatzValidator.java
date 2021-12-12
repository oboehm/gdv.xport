/*
 * Copyright (c) 2021 by Oli B.
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
 * (c)reated 12-11-2021 by $USER
 */
package gdv.xport.event;

import gdv.xport.config.Config;
import gdv.xport.satz.Satz;
import gdv.xport.util.SimpleConstraintViolation;
import net.sf.oval.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Der Klasse SatzValidator ist als {@link ImportListener} ausgelegt. Damit
 * kann er waehrend eines Imports ueber den {@link gdv.xport.DatenpaketStreamer}
 * registriert werden und den Import ueberwachen (validieren).
 *
 * @author oboehm
 * @since 5.4 (12.11.21)
 */
public class SatzValidator implements ImportListener {

    private static final Logger LOG = LogManager.getLogger();
    private final Config config;
    private final List<ConstraintViolation> violations = new ArrayList<>();
    private int satzNr = 1;
    private int tdsNr = 1;

    public SatzValidator() {
        this(Config.STRICT);
    }

    public SatzValidator(Config config) {
        this.config = config;
    }

    @Override
    public void notice(Satz satz) {
        List<ConstraintViolation> constraintViolations = satz.validate(config);
        if (!constraintViolations.isEmpty()) {
            LOG.warn("Record {} Satz {} {}:", tdsNr, satzNr, satz.toShortString());
            for (ConstraintViolation cv : constraintViolations) {
                LOG.warn("\t* {}", cv);
                if (cv instanceof SimpleConstraintViolation) {
                    logViolations((SimpleConstraintViolation) cv);
                }
            }
        }
        satzNr++;
        tdsNr += satz.getNumberOfTeildatensaetze();
        violations.addAll(constraintViolations);
    }

    private static void logViolations(SimpleConstraintViolation scv) {
        for (ConstraintViolation cv : scv.getViolations()) {
            LOG.warn("\t\t* {}", cv);
        }
    }

    public List<ConstraintViolation> getViolations() {
        return violations;
    }

}
