/*
 * Copyright (c) 2011 by agentes
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
 * (c)reated 27.03.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.feld.Feld;
import gdv.xport.satz.*;

import java.io.*;
import java.text.MessageFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.*;

/**
 * Diese Klasse dient dazu, um entsprechende Enumerations wie zum Beispiel
 * in {@link gdv.xport.satz.feld.Feld0100} zu erzeugen. Dies erleichtert
 * die Migration der alten Datensaetze auf das neue Format nach dem
 * SOP-Ansatz, der in 0.6 hinzugekommen ist.
 *
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.6 (27.03.2011)
 */
public final class JavaFormatter extends AbstractFormatter {
    
    private static final Log log = LogFactory.getLog(JavaFormatter.class);
    private static final String headTemplate = getTemplate("java/head.txt");
    private static final String headSparteTemplate = getTemplate("java/headSparte.txt");
    private static final String footTemplate = getTemplate("java/foot.txt");
    private static final String teildatensatzTemplate = getTemplate("java/teildatensatz.txt");
    private static final String feldTemplate = getTemplate("java/feld.txt");
    private static final String numFeldTemplate = getTemplate("java/numFeld.txt");
    
    private static String getTemplate(final String name) {
        InputStream istream = JavaFormatter.class.getResourceAsStream(name);
        try {
            return IOUtils.toString(istream);
        } catch (IOException e) {
            ExceptionInInitializerError initError = new ExceptionInInitializerError("resource " + name + " not found");
            initError.initCause(e);
            throw initError;
        } finally {
            IOUtils.closeQuietly(istream);
        }
    }

    /**
     * Default constructor.
     */
    public JavaFormatter() {
        super();
    }
    
    /**
     * Instantiiert einen neuen Formatter.
     *
     * @param writer the writer
     */
    public JavaFormatter(Writer writer) {
        super(writer);
    }

    /**
     * Instantiiert einen neuen Formatter.
     * 
     * @param ostream the ostream
     */
    public JavaFormatter(OutputStream ostream) {
        super(ostream);
    }

    /**
     * Ausgabe der einzelnen Datensaetze als Java-Enum-Klasse.
     * 
     * @param datenpaket mit den Datensaetzen, die als Java ausgegeben werden
     *        sollen
     * @throws IOException bei Problemen mit der Java-Generierung
     * @see AbstractFormatter#write(gdv.xport.Datenpaket)
     */
    @Override
    public void write(final Datenpaket datenpaket) throws IOException {
        write(datenpaket.getVorsatz());
        for (Datensatz datensatz : datenpaket.getDatensaetze()) {
            write(datensatz);
        }
        write(datenpaket.getNachsatz());
    }

    /**
     * Da ein Datensatz nachher einer Java-Enum-Klasse entspricht, ist diese
     * Klasse "public".
     *
     * @param satz Datensatz, der als Java-Enum-Klasse ausgegeben werden soll
     * @throws IOException bei Problemen mit der Java-Generierung
     */
    public void write(final Satz satz) throws IOException {
        try {
            this.write((Datensatz) satz);
            return;
        } catch (ClassCastException cce) {
            log.trace(cce);
            this.write(MessageFormat.format(headTemplate, new Date(), SystemUtils.USER_NAME, satz.getSatzart()));
            for (int i = 1; i <= satz.getNumberOfTeildatensaetze(); i++) {
                write(satz.getTeildatensatz(i), i);
            }
            this.write(footTemplate);
        }
    }
    
    private void write(final Datensatz datensatz) throws IOException {
        this.write(MessageFormat.format(headSparteTemplate, new Date(), SystemUtils.USER_NAME, datensatz.getSatzart(),
                datensatz.getSparte()));
        for (int i = 1; i <= datensatz.getNumberOfTeildatensaetze(); i++) {
            write(datensatz.getTeildatensatz(i), i);
        }
        this.write(footTemplate);
    }

    private void write(final Teildatensatz tds, final int tdsNr) throws IOException {
        if (tdsNr > 1) {
            this.write(",\n\n");
        }
        this.write(MessageFormat.format(teildatensatzTemplate, tdsNr));
        int feldNr = 0;
        for (Feld feld : tds.getFelder()) {
            feldNr++;
            this.write(feld, tdsNr, feldNr);
        }
    }
    
    private void write(final Feld feld, final int tdsNr, final int feldNr) throws IOException {
        if (feldNr > 1) {
            this.write(",\n\n");
        }
        this.write(MessageFormat.format(feldTemplate, 
                feld.getBezeichnung(),
                tdsNr,
                feldNr,
                feld.getClass().getSimpleName(),
                feld.getAnzahlBytes(),
                feld.getByteAdresse(),
                feld.getBezeichner()
        ));
    }

    /**
     * Wandelt das uebergebene Datenpaket die entsprechenden Java-Definitionen
     * um. Aus Gruenden der Symmetrie (die anderen Formatter-Klassen besitzten
     * ebenfalls diese Methode) und aus Testueberlegen wurde diese Methode auch
     * in diese Klasse uebernommen.
     *
     * @param datenpaket das Datenpaket
     * @return Datenpaket als XML-String
     */
    public static String toString(final Datenpaket datenpaket) {
        StringWriter swriter = new StringWriter();
        JavaFormatter formatter = new JavaFormatter(swriter);
        try {
            formatter.write(datenpaket);
        } catch (IOException shouldnothappen) {
            throw new RuntimeException("can't convert " + datenpaket + " to String",
                    shouldnothappen);
        }
        IOUtils.closeQuietly(swriter);
        return swriter.toString();
    }

}

