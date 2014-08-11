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
 * (c)reated 11.08.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import gdv.xport.feld.Feld;
import gdv.xport.util.XmlHelper;

import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;

/**
 * Im Gegensatz zur {@link Feld}-Klasse kommen hier die einzelnen Werte als
 * XML-Strem rein.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (11.08.2014)
 */
public final class FeldXml extends Feld {

    private final String id;
    private final String name;

    /**
     * Instantiiert eine Objekt mit den Werten, die ueber den uebergebenen
     * Parser gelesen werden.
     *
     * @param parser the parser
     * @throws XMLStreamException the XML stream exception
     */
    public FeldXml(final XMLEventReader parser) throws XMLStreamException {
        this(parser, XmlHelper.getNextStartElement(parser));
    }

    /**
     * Instantiiert eine Objekt mit den Werten, die ueber den uebergebenen
     * Parser gelesen werden.
     *
     * @param parser the parser
     * @param element das Start-Element <feldreferenz referenz=... >
     * @throws XMLStreamException the XML stream exception
     */
    public FeldXml(final XMLEventReader parser, final StartElement element) throws XMLStreamException {
        id = element.getAttributeByName(new QName("referenz")).getValue();
        Properties props = XmlHelper.parseSimpleElements(element.getName(), parser);
        this.name = props.getProperty("name", "");
    }

    /**
     * Liefert die Referenz (Id).
     *
     * @return z.B. "BN-2003.02.11.22.49.47.214"
     */
    public String getId() {
        return this.id;
    }

    /**
     * Im Gegensatz zur Oberflaeche kann hier der "Bezeichner" in Gross- und
     * Kleinbuchstaben erscheinen.
     *
     * @return der technische Name
     * @see gdv.xport.feld.Feld#getBezeichner()
     */
    @Override
    public String getBezeichner() {
        return this.name;
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " \"" + this.id + "\" (" + this.name + ")";
    }

}
