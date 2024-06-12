/*
 * Copyright (c) 2014-2024 by Oli B.
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

import de.jfachwert.Text;
import gdv.xport.feld.*;
import gdv.xport.util.XmlHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import java.util.Properties;

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
        super(new Bezeichner(props), ByteAdresse.of(1), toInhalt(props), Align.LEFT);
        this.id = props.getProperty("ID");
        this.datentyp = Datentyp.asValue(props.getProperty("datentyp"));
        this.nachkommastellen = Integer.parseInt(props.getProperty("nachkommastellen", "0"));
        LOG.debug("{} created.", this);
    }

    private static String toInhalt(Properties props) {
        int l = Integer.parseInt(props.getProperty("bytes", "1"));
        String s = props.getProperty("auspraegung", " ");
        return StringUtils.rightPad(s, l);
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
    public Datentyp getDatentyp() {
        return this.datentyp;
    }

    /**
     * Liefert die Anzahl der Nachkommastellen oder 0, falls es sich um kein
     * numerisches Feld handelt.
     *
     * @return the nachkommastellen
     */
    public int getNachkommastellen() {
        return this.nachkommastellen;
    }

    /**
     * Wandelt das FeldXml-Objekt in ein {@link Feld}-Objekt um.
     *
     * @param byteAddress die Byte-Adresse
     * @return das entsprechende Feld
     * @deprecated bitte Methode mit ByteAdresse verwenden (TODO: wird mit v9 entsorgt)
     */
    @Deprecated
    public Feld toFeld(final int byteAddress) {
        return this.toFeld(ByteAdresse.of(byteAddress));
    }

    /**
     * Wandelt das FeldXml-Objekt in ein {@link Feld}-Objekt um.
     *
     * @param byteAddress die Byte-Adresse
     * @return das entsprechende Feld
     * @since 7.1
     */
    public Feld toFeld(final ByteAdresse byteAddress) {
        return this.toFeld(byteAddress, this.getBezeichner(), "");
    }

    /**
     * Wandelt das FeldXml-Objekt in ein {@link Feld}-Objekt um.
     *
     * @param byteAddress die Byte-Adresse
     * @param neuerBezeichner the neuer bezeichner
     * @return das entsprechende Feld
     * @deprecated alte Version (TODO: wird mit v8 entsorgt)
     */
    @Deprecated
    public Feld toFeld(final int byteAddress, final Bezeichner neuerBezeichner) {
        return toFeld(ByteAdresse.of(byteAddress), neuerBezeichner, "");
    }

    /**
     * Wandelt das FeldXml-Objekt in ein {@link Feld}-Objekt um.
     *
     * @param byteAddress die Byte-Adresse
     * @param referenz mit Bezeichner und Bemerkung
     * @return das entsprechende Feld
     * @deprecated bitte Methode mit ByteAdresse verwenden (TODO: wird mit v9 entsorgt)
     */
    @Deprecated
    public Feld toFeld(final int byteAddress, final FeldReferenz referenz) {
        return toFeld(ByteAdresse.of(byteAddress), referenz);
    }

    /**
     * Wandelt das FeldXml-Objekt in ein {@link Feld}-Objekt um.
     *
     * @param byteAddress die Byte-Adresse
     * @param referenz mit Bezeichner und Bemerkung
     * @return das entsprechende Feld
     * @since 7.1
     */
    public Feld toFeld(final ByteAdresse byteAddress, final FeldReferenz referenz) {
        return toFeld(byteAddress, referenz.getBezeichner(), referenz.getBemerkung());
    }

    /**
     * Wandelt das FeldXml-Objekt in ein {@link Feld}-Objekt um, dessen Bezeichner eindeutig im
     * aktuellen Teildatensatz ist.
     * <p>
     * In Feldern innerhalb der TDs von SA > "0001" wird der technischen Namen aus der
     * Feld-Bezeichnung ermitteln. Dadurch kann ein Feld, dessen Bezeichnung im
     * Teildatensatz eindeutig ist, sicher durch die Feld-Bezeichnung aus GDV-Online
     * adressiert werden. Felder mit mehrdeutigem Namen im Teildatensatz (s.u.) koennen nur
     * via ByteAdresse adressiert werden (wie bisher auch).
     * </p><p>
     * Eine Ausnahme ist das Feld an Position 43 in SA0220.030, TD9. Dieses Feld ist durch
     * einen Kopierfehler beim GDV entstanden. Aus 'historischen' Gruenden und wg.
     * Abwaertskompatibilitaet muss der technische Name hier identisch sein zu
     * {@link gdv.xport.feld.Bezeichner#LFD_NUMMER_VP_PERSONENGRUPPE9}. Ergo wird hier wie
     * bisher der Bezeichner aus der Referenz verwendet.
     * </p>
     *
     * @param byteAddress die Byte-Adresse
     * @param referenz    mit Bezeichner und Bemerkung
     * @param tdXml       der aktuelle Teildatensatz
     * @return das entsprechende Feld
     * @deprecated bitte Methode mit ByteAdresse-Parameter verwenden (TODO: wird mit v9 entsorgt)
     */
    @Deprecated
    public Feld toFeld(final int byteAddress, final FeldReferenz referenz, final TeildatensatzXml tdXml) {
        return toFeld(ByteAdresse.of(byteAddress), referenz, tdXml);
    }

    /**
     * Wandelt das FeldXml-Objekt in ein {@link Feld}-Objekt um, dessen Bezeichner eindeutig im
     * aktuellen Teildatensatz ist.
     * <p>
     * In Feldern innerhalb der TDs von SA > "0001" wird der technischen Namen aus der
     * Feld-Bezeichnung ermitteln. Dadurch kann ein Feld, dessen Bezeichnung im
     * Teildatensatz eindeutig ist, sicher durch die Feld-Bezeichnung aus GDV-Online
     * adressiert werden. Felder mit mehrdeutigem Namen im Teildatensatz (s.u.) koennen nur
     * via ByteAdresse adressiert werden (wie bisher auch).
     * </p>
     *
     * @param byteAddress die Byte-Adresse
     * @param referenz    mit Bezeichner und Bemerkung
     * @param tdXml       der aktuelle Teildatensatz
     * @return das entsprechende Feld
     * @since 7.1
     */
    public Feld toFeld(final ByteAdresse byteAddress, final FeldReferenz referenz, final TeildatensatzXml tdXml) {
        Bezeichner bezeichner = referenz.getBezeichner();
        if ((!(tdXml.getGdvSatzartName().equals("0001") && byteAddress.intValue() >= 96)) &&
                (!(tdXml.getGdvSatzartName().equals("0220.030")
                        && tdXml.getSatznummer().toChar() == '9'
                        && byteAddress.intValue() == 43))) {
            bezeichner = new Bezeichner(referenz.getBezeichner().getName());
        }
        Feld feld = toFeld(byteAddress, bezeichner, referenz.getBemerkung());
        if (!feld.getBezeichner().getTechnischerName().equalsIgnoreCase("Satzart")) {
            for (int i = 2; tdXml.hasFeld(feld.getBezeichner()); i++) {
                Bezeichner bezeichnerNeu = new Bezeichner(bezeichner.getName(),
                        bezeichner.getTechnischerName() + i);
                feld = toFeld(byteAddress, bezeichnerNeu, referenz.getBemerkung());
            }
        }
        return feld;
    }

    private Feld toFeld(final ByteAdresse byteAddress, final Bezeichner neuerBezeichner, final String bemerkung) {
        Feld f = this.datentyp.asFeld(neuerBezeichner, this.getAnzahlBytes(), byteAddress);
        switch (this.datentyp) {
            case NUMERISCH:
            case FLIESSKOMMA:
                f = new NumFeld(neuerBezeichner, this.getAnzahlBytes(), byteAddress)
                        .mitNachkommastellen(this.nachkommastellen);
                if (bemerkung.contains("MMJJJJ")) {
                    f = new Datum(neuerBezeichner, this.getAnzahlBytes(), byteAddress);
                }
                break;
            case ALPHANUMERISCH:
                if (Text.replaceUmlaute(bemerkung).toLowerCase().contains("rechtsbuendig")) {
                    f = new AlphaNumFeld(neuerBezeichner, this.getAnzahlBytes(), byteAddress, Align.RIGHT);
                }
                break;
        }
        if (StringUtils.isNotBlank(inhalt)) {
            f.setInhalt(getInhalt());
        }
        return f;
    }

    /* (non-Javadoc)
     * @see gdv.xport.feld.Feld#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " \"" + this.id + "\" (" + this.getBezeichner() + ")";
    }

}
