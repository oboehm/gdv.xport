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
 * (c)reated 13.11.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

/**
 * Falls eine Satzart angefordert wird, die nicht eindeutig ist (z.B. weil
 * noch die Angabe der Sparte fehlt), kann diese Exception geworfen werden.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (13.11.2014)
 */
public class NotUniqueException extends RuntimeException {

    private static final long serialVersionUID = 20141113L;

    /**
     * Instantiates a new not unique exception.
     *
     * @param message the message
     */
    public NotUniqueException(final String message) {
        super(message);
    }

}
