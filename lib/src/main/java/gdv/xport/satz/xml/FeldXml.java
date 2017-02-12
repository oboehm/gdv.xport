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

import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;

import org.apache.logging.log4j.*;

import gdv.xport.feld.*;
import gdv.xport.util.XmlHelper;

/**
 * Im Gegensatz zur {@link Feld}-Klasse kommen hier die einzelnen Werte als
 * XML-Strem rein.
 *
 * @author oliver (oliver.boehm@gmail.com)
 * @since 1.0 (11.08.2014)
 */
public final class FeldXml extends Feld {

    private static final Logger LOG = LogManager.getLogger(FeldXml.class);

    private final String id;
    private final Datentyp datentyp;
    private final int nachkommastellen;

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
     * @param element das Start-Element &lt;feldreferenz referenz=... &gt;
     * @throws XMLStreamException the XML stream exception
     */
    public FeldXml(final XMLEventReader parser, final StartElement element) throws XMLStreamException {
        this(parse(parser, element));
    }

    private FeldXml(final Properties props) {
        super(new Bezeichner(props), Integer.parseInt(props.getProperty("bytes", "1")), 0, Align.UNKNOWN);
        this.id = props.getProperty("ID");
        this.datentyp = Datentyp.asValue(props.getProperty("datentyp"));
        this.nachkommastellen = Integer.parseInt(props.getProperty("nachkommastellen", "0"));
        LOG.debug("{} created.", this);
    }

    private static Properties parse(final XMLEventReader parser, final StartElement element) throws XMLStreamException {
        String xid = element.getAttributeByName(new QName("referenz")).getValue();
        LOG.trace("Parsing <feld referenz=\"{}\"...", xid);
        Properties props = XmlHelper.parseSimpleElements(element.getName(), parser);
        props.put("ID", xid);
        return props;
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
     * Liefert den Datentyp.
     *
     * @return the datentyp
     */
    public final Datentyp getDatentyp() {
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
     * Wandelt das FeldXml-Objekt in ein {@link Feld}-Objekt um.
     *
     * @param byteAddress die Byte-Adresse
     * @return das entsprechende Feld
     */
    public Feld toFeld(final int byteAddress) {
        return this.toFeld(byteAddress, this.getBezeichner());
    }

    /**
     * Wandelt das FeldXml-Objekt in ein {@link Feld}-Objekt um.
     *
     * @param byteAddress die Byte-Adresse
     * @param neuerBezeichner the neuer bezeichner
     * @return das entsprechende Feld
     */
    public Feld toFeld(final int byteAddress, final Bezeichner neuerBezeichner) {
        Bezeichner merged = this.getBezeichner().mergeWith(neuerBezeichner);
        switch (this.datentyp) {
            case NUMERISCH:
            case FLIESSKOMMA:
                return new NumFeld(merged, this.getAnzahlBytes(), byteAddress)
                        .mitNachkommastellen(this.nachkommastellen);
            default:
                return this.datentyp.asFeld(merged, this.getAnzahlBytes(), byteAddress);
        }
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " \"" + this.id + "\" (" + this.getBezeichner() + ")";
    }

}
