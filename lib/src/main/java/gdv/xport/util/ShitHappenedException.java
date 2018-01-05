/*
 * Copyright (c) 2014 by Oli B.
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
 * (c)reated 15.10.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

/**
 * Diese {@link RuntimeException} kommt dann zum Einsatz, wenn irgendewas
 * Unvorhergesehenes waehrend der Laufzeit aufgetreten ist.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (15.10.2014)
 */
public final class ShitHappenedException extends RuntimeException {

    private static final long serialVersionUID = 20141015L;

    /**
     * Instanziiert eine neue Exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public ShitHappenedException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
