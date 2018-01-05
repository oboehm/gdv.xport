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
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 08.08.2017 by oboehm (ob@oasd.de)
 */
package gdv.xport.srv.web.util;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.time.*;
import java.time.format.*;

/**
 * Da Spring noch keine Deserializer fuer {@link LocalDateTime} aus Java 8
 * mitbring, muessen wir das vorlaeufig noch selbst machen.
 */
public final class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE_TIME;

	/**
	 * Deserialisierung, handgemacht.
	 *
	 * @param jsonparser Parser fuer die Deserialisierung
	 * @param context Deserialisierungskontext
	 * @return LocalDateTime
	 * @throws IOException sollte nicht passieren
	 */
	@Override
	public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext context)
			throws IOException {
		String date = jsonparser.getText();
		return LocalDateTime.parse(date, dateFormat);
	}

}
