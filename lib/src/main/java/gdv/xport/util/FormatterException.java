/*
 * Copyright (c) 2009 - 2014 by Oli B. Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express orimplied. See the License for the specific language
 * governing permissions and limitations under the License. (c)reated 23.10.2009
 * by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;


/**
 * Wenn der Formatter ein Problem hat, kann (und wird) er diese Exception
 * werfen.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (17.02.2014)
 */
public class FormatterException extends RuntimeException {

    private static final long serialVersionUID = 20140217L;

    /**
     * Erzeugt eine neue {@link FormatterException}.
     *
     * @param msg Fehlermeldung
     * @param cause ursaechliche Exception
     */
    public FormatterException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

}
