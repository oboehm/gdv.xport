/*
 * Copyright (c) 2016 by Oli B.
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
 * (c)reated 06.06.2016 by Oli B. (ob@aosd.de)
 */

package gdv.xport.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import gdv.xport.satz.Satz;
import gdv.xport.satz.Vorsatz;

/**
 * Unit-Test for {@link CsvFormatter}.
 *
 * @author oliver
 * @since 1.2 (06.06.2016)
 */
public final class CsvFormatterTest extends AbstractFormatterTest {

    /**
     * Basis-Test fuer {@link CsvFormatter#write(Satz)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testWrite() throws IOException {
        StringWriter writer = new StringWriter();
        CsvFormatter formatter = new CsvFormatter(writer);
        Satz satz = new Vorsatz();
        formatter.write(satz);
        writer.flush();
        writer.close();
        String content = writer.toString();
        assertEquals("Satzart;", content.substring(0,  8));
    }

}
