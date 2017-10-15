/*
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
 * (c)reated 14.10.17 by oliver (ob@oasd.de)
 */
package gdv.xport.srv.web.util;

import gdv.xport.Datenpaket;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Class DatenpaketHttpMessageConverter.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 * @since 3.0.0 (14.10.17)
 */
public class DatenpaketHttpMessageConverter extends AbstractHttpMessageConverter<Datenpaket> {
    @Override
    protected boolean supports(Class<?> aClass) {
        return Datenpaket.class.equals(aClass);
    }

    @Override
    protected Datenpaket readInternal(Class<? extends Datenpaket> aClass, HttpInputMessage httpInputMessage)
            throws IOException, HttpMessageNotReadableException {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected void writeInternal(Datenpaket datenpaket, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        OutputStream out = outputMessage.getBody();
        datenpaket.export(out);
        out.flush();
    }

}
