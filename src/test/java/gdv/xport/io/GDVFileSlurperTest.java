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
 * (c)reated 18.01.2014 by Oli B. (oliver.boehm@gmail.com)
 */

package gdv.xport.io;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import patterntesting.runtime.junit.FileTester;

/**
 * @author oliver (ob@aosd.de)
 * @since 0.9.2 (18.01.2014)
 */
public class GDVFileSlurperTest {

    /**
     * Test method for {@link GDVFileSlurper#slurp(File)}.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void testSlurpFile() throws IOException {
        File inputFile = new File("src/test/resources/zwei_datenpakete.txt");
        File outputFile = new File("target/datenpaket-1.txt");
        ArrayList<ByteArrayInputStream> inputStreams = GDVFileSlurper.slurp(inputFile);
        assertEquals(2, inputStreams.size());
        String s0 = IOUtils.toString(inputStreams.get(0), "ISO-8859-1");
        FileUtils.writeStringToFile(outputFile, s0, "ISO-8859-1");
        FileTester.assertContentEquals(inputFile, outputFile, 1, 165);
    }

}
