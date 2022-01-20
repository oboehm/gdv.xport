/*
 * Copyright (c) 2022 by Oli B.
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
 * (c)reated 19.01.22 by oboehm
 */
package gdv.xport.io;

import gdv.xport.satz.AbstractSatzTest;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.xml.SatzXml;
import gdv.xport.satz.xml.XmlService;
import gdv.xport.util.SatzTyp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImporterTest {

    private static final Map<SatzTyp, SatzXml> SATZARTEN = XmlService.getInstance().getSatzarten();

    @DisplayName("lese Satzarten")
    @ParameterizedTest(name = "{index}: Satzart {0}")
    @MethodSource("satztypParameters")
    void testReadSatzTyp(String satzart) throws IOException {
        SatzTyp satzTyp = SatzTyp.of(satzart);
        Satz satz = SATZARTEN.get(satzTyp);
        AbstractSatzTest.setUp(satz);
        for (Teildatensatz tds : satz.getTeildatensaetze()) {
            String content = tds.toLongString();
            try (Reader reader = new StringReader(content)) {
                Importer importer = Importer.of(reader);
                SatzTyp tdsSatzTyp = importer.readSatzTyp(tds.getSatzart());
                assertEquals(satzTyp, tdsSatzTyp);
            }
        }
    }

    static Stream<Arguments> satztypParameters() {
        return SATZARTEN.keySet().stream().map(satzTyp -> Arguments.of(satzTyp.getGdvSatzartName()));
    }

}