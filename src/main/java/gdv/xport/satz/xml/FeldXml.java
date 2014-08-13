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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Im Gegensatz zur {@link Feld}-Klasse kommen hier die einzelnen Werte als
 * XML-Strem rein.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (11.08.2014)
 */
public final class FeldXml extends Feld {

    private static final Logger LOG = LoggerFactory.getLogger(FeldXml.class);

    private final String id;
    private final String name;
    private final String datentyp;
    private final int nachkommastellen;
    private String technischerName;

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
        LOG.trace("Parsing <feld referenz=\"{}\"...", id);
        Properties props = XmlHelper.parseSimpleElements(element.getName(), parser);
        this.name = props.getProperty("name", "");
        this.setAnzahlBytes(Integer.parseInt(props.getProperty("bytes", "1")));
        this.datentyp = props.getProperty("datentyp");
        this.nachkommastellen = Integer.parseInt(props.getProperty("nachkommastellen", "0"));
        LOG.debug("{} created.", this);
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
     * Im Gegensatz zur Oberklasse kann hier der "Bezeichner" in Gross- und
     * Kleinbuchstaben erscheinen.
     *
     * @return der technische Name
     * @see gdv.xport.feld.Feld#getBezeichner()
     */
    @Override
    public String getBezeichner() {
        return this.name;
    }

    /**
     * Im Gegensatz zur Oberklasse wird hier der technische Name aus der
     * XML-Beschreibung herangezogen.
     *
     * @see gdv.xport.feld.Feld#getBezeichnung()
     */
    @Override
    public String getBezeichnung() {
        return this.technischerName;
    }

    /**
     * Liefert den Datentyp.
     *
     * @return the datentyp
     */
    public final String getDatentyp() {
        return this.datentyp;
    }

    /**
     * Liefert die Anzahl der Nachkommastellen oder 0, falls es sich um kein
     * numerisches Feld handelt.
     *
     * @return the nachkommastellen
     */
    public final int getNachkommastellen() {
        return this.nachkommastellen;
    }

    /**
     * Setzt einige interne Werte, die ueber die {@link FeldReferenz}
     * reinkommen.
     *
     * @param referenz the new referenz
     */
    public void setReferenz(final FeldReferenz referenz) {
        this.technischerName = referenz.getTechnischerName();
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " \"" + this.id + "\" (" + this.name + ")";
    }

}
