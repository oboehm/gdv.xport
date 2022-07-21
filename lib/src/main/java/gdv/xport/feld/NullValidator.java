/*
 * Copyright (c) 2022 by Oli B.
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
 * (c)reated 20.07.22 by oboehm
 */
package gdv.xport.feld;

import gdv.xport.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Der NullValidator kann hergenommen werden, um fuer einzelne Feldarten die
 * Validierung abzuschalten, ohne das komplett auf Validierung verzichtet
 * werden muss.
 *
 * @author oboehm
 * @since 6.3 (20.07.22)
 */
public class NullValidator extends Feld.Validator {

    private static final Logger LOG = LogManager.getLogger(NullValidator.class);

    /**
     * Hierueber wird die Validierung einfach abgeschaltet.
     *
     * @param value            Wert, der unveraendert zurueckgegeben wird
     * @param validationConfig wird ignoriert
     * @return urspruenglicher Wert
     */
    @Override
    public String validate(String value, Config validationConfig) {
        LOG.trace("{} wird ignoriert.", validationConfig);
        return value;
    }

}
