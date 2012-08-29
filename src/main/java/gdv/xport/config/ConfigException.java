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
 * (c)reated 08.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.config;

/**
 * @author oliver
 * @since 08.10.2009
 */
public class ConfigException extends RuntimeException {

    private static final long serialVersionUID = 20091008L;

    /**
     * Falls mal die Konfiguration nicht stimmt...
     * @param message Hinweis fuer die Ursache
     */
    public ConfigException(final String message) {
        super(message);
    }

    /**
     * Falls mal die Konfiguration nicht stimmt...
     * @since 0.2
     * @param message Hinweis fuer die Ursache
     * @param cause die schuldige Exception
     */
    public ConfigException(final String message, final Throwable cause) {
        super(message, cause);
    }

}

