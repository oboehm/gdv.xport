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
 * (c)reated 31.12.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.io;

import java.io.IOException;

/**
 * Falls mal beim Import was schiefgeht.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.4 (31.12.2009)
 */
public final class ImportException extends IOException {

    private static final long serialVersionUID = 20091231L;

    /**
     * Erzeugt eine neue {@link ImportException}.
     *
     * @param message die Meldung, die mit der Exception ausgegeben wird
     */
    public ImportException(final String message) {
        super(message);
    }

    /**
     * Diese {@link ImportException} fuegt noch die Zeilennummer zur
     * Fehlermeldung hinzu.
     *
     * @param lnr fuer die Zeilennummer
     * @param message die Meldung, die mit der Exception ausgegeben wird
     * @param cause die urspruengliche Exception
     */
    public ImportException(final PushbackLineNumberReader lnr, final String message, final Throwable cause) {
        super("line " + lnr.getLineNumber() + ": " + message, cause);
    }

}

