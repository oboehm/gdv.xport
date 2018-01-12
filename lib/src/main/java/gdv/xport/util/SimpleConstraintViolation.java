/*
 * Copyright (c) 2018 by Oliver Boehm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 12.01.2018 by oboehm (ob@oasd.de)
 */
package gdv.xport.util;

import gdv.xport.feld.Feld;
import net.sf.oval.Check;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.constraint.AssertValidCheck;
import net.sf.oval.context.ClassContext;
import net.sf.oval.context.OValContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Die Klasse SimpleConstraintViolation vereinfacht den Umgang mit
 * {@link ConstraintViolation}.
 *
 * @author oboehm
 * @since 3.1 (12.01.2018)
 */
public final class SimpleConstraintViolation extends ConstraintViolation {
    
    private static final Logger LOG = LogManager.getLogger(SimpleConstraintViolation.class);
    
    public SimpleConstraintViolation(Feld validatedObject, Throwable cause) {
        this(cause.getLocalizedMessage(), validatedObject);
        LOG.debug("{} is not valid:", validatedObject, cause);
    }

    public SimpleConstraintViolation(String message, Feld validatedObject) {
        this(message, validatedObject, validatedObject.getInhalt());
    }
    
    public SimpleConstraintViolation(String message, Object validatedObject, Object invalidValue) {
        super(new AssertValidCheck(), message, validatedObject, invalidValue, new ClassContext(validatedObject.getClass()));
    }

}
