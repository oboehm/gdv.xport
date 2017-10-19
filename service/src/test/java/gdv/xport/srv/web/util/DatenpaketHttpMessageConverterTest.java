package gdv.xport.srv.web.util;/*
 * Copyright (c) 2017 by Oliver Boehm
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
 * (c)reated 15.10.17 by oliver (ob@oasd.de)
 */

import gdv.xport.Datenpaket;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.mock.http.MockHttpOutputMessage;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Unit-Tests fuer {@link DatenpaketHttpMessageConverter}-Klasse.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 */
public final class DatenpaketHttpMessageConverterTest {

    private final DatenpaketHttpMessageConverter converter = new DatenpaketHttpMessageConverter(MediaType.TEXT_PLAIN);

    @Test
    public void testSupports() {
        assertThat(converter.supports(Datenpaket.class), is(true));
    }

    @Test
    public void writeInternal() throws IOException {
        Datenpaket datenpaket = new Datenpaket();
        MockHttpOutputMessage outputMessage = new MockHttpOutputMessage();
        converter.writeInternal(datenpaket, outputMessage);
        Writer writer = new StringWriter();
        datenpaket.export(writer);
        assertEquals(writer.toString(), outputMessage.getBodyAsString(StandardCharsets.ISO_8859_1));
    }

}
