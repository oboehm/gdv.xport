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
 * (c)reated 01.02.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.io;

import java.io.EOFException;

/**
 * Im Gegensatz zur normalen {@link EOFException} erlaubt diese Exception hier
 * auch die Uebergabe der ursaechlichen Exception (Cause).
 *
 * @author oliver
 * @since 0.9.2 (01.02.2014)
 */
public class ExtendedEOFException extends EOFException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 20140201L;

    /** The cause. */
    private final Throwable cause;

    /**
     * Instantiates a new extended eof exception.
     *
     * @param msg the msg
     * @param cause the cause
     */
    public ExtendedEOFException(final String msg, final Throwable cause) {
        super(msg);
        this.cause = cause;
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#getCause()
     */
    @Override
    public Throwable getCause() {
        return this.cause;
    }

}
