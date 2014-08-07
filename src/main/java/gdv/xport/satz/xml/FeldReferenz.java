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
 * (c)reated 07.08.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Diese Klasse repraesentiert die Wert, die als "<feldreferenz>" ueber XML
 * reinkommen.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (07.08.2014)
 */
public final class FeldReferenz {

    private static final Logger log = LoggerFactory.getLogger(FeldReferenz.class);

    private final String id;

    /**
     * Instantiiert eine Objekt mit den Werten, die ueber den uebergebenen
     * Parser gelesen werden.
     *
     * @param parser the parser
     * @throws XMLStreamException the XML stream exception
     */
    public FeldReferenz(final XMLEventReader parser) throws XMLStreamException {
        id = parse(parser);
    }

    /**
     * Liefert die Referenz (Id).
     *
     * @return z.B. "BN-2003.02.11.22.49.47.214"
     */
    public String getId() {
        return this.id;
    }



    /////   XML-Verarbeitung   ////////////////////////////////////////////////

    private String parse(final XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (isStartElement(event, "feldreferenz")) {
                return event.asStartElement().getAttributeByName(new QName("referenz")).getValue();
            }
            log.trace("Event {} is ignored.", event);
        }
        throw new XMLStreamException("element <feldreferenz> not found");
    }

    private static boolean isStartElement(final XMLEvent event, final String name) {
        if (event.isStartElement()) {
            return event.asStartElement().getName().getLocalPart().equals(name);
        } else {
            return false;
        }
    }

}
