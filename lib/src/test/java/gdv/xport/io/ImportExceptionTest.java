/*
 * Copyright (c) 2010 - 2012 by Oli B.
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
 * (c)reated 08.02.2010 by Oli B. (ob@aosd.de)
 */

package gdv.xport.io;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author oliver (ob@aosd.de)
 * @since 0.4 (08.02.2010)
 */
public final class ImportExceptionTest {

    /**
     * Test method for {@link gdv.xport.io.ImportException#ImportException(java.lang.String)}.
     */
    @Test
    public void testImportException() {
        String msg = "bumm";
        try {
            throw new ImportException(msg);
        } catch (ImportException ie) {
            assertEquals(msg, ie.getMessage());
        }
    }

}

