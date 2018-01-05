/*
 * Copyright (c) 2009 - 2012 by Oli B.
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
 * (c)reated 13.11.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

/**
 * Falls eine Satzart noch nicht registriert wurde, wird dies Exception hier
 * geworfen.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.2 (13.11.2009)
 */
public class NotRegisteredException extends RuntimeException {

    private static final long serialVersionUID = 20091113L;

    /**
     * Instanziiert eine {@link NotRegisteredException}.
     *
     * @param satzart Satzart, die nicht registriert wurde
     */
    public NotRegisteredException(final int satzart) {
        super("unregistered Satzart " + satzart);
    }

    /**
     * Instanziiert eine {@link NotRegisteredException}.
     *
     * @param satzNr Satzart, die nicht registriert wurde
     */
    public NotRegisteredException(final SatzTyp satzNr) {
        super("unregistered Satzart " + satzNr);
    }

}

