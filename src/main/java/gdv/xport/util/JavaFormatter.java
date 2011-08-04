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
import gdv.xport.feld.*;
import gdv.xport.satz.*;

import java.io.*;
import java.text.*;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.*;

/**
 * Diese Klasse dient dazu, um entsprechende Enumerations wie zum Beispiel
 * in {@link gdv.xport.satz.feld.Feld100} zu erzeugen. Dies erleichtert
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
    public JavaFormatter(final Writer writer) {
        super(writer);
    }

    /**
     * Instantiiert einen neuen Formatter.
     * 
     * @param ostream the ostream
     */
    public JavaFormatter(final OutputStream ostream) {
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
            Datensatz datensatz = (Datensatz) satz;
            if (datensatz.hasSparte()) {
                this.write(datensatz);
                return;
            }
        } catch (ClassCastException canhappen) {
            log.trace(canhappen);
        }
        this.write(MessageFormat.format(headTemplate, new Date(), SystemUtils.USER_NAME, satz.getSatzart()));
        for (int i = 1; i <= satz.getNumberOfTeildatensaetze(); i++) {
            write(satz.getTeildatensatz(i), i);
        }
        this.write(footTemplate);
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
        try {
            NumFeld numFeld = (NumFeld) feld;
            if (numFeld.getNachkommastellen() > 0) {
                this.write(numFeld, tdsNr, feldNr);
                return;
            }
        } catch (ClassCastException normal) {
            log.trace(normal);
        }
        String bezeichnung = feld.getBezeichnung();
        if (!bezeichnung.endsWith(".")) {
            bezeichnung += ".";
        }
        this.write(MessageFormat.format(feldTemplate, 
                bezeichnung,
                tdsNr,
                feldNr,
                feld.getClass().getSimpleName(),
                feld.getAnzahlBytes(),
                feld.getByteAdresse(),
                feld.getBezeichner()
        ));
    }

    private void write(final NumFeld feld, final int tdsNr, final int feldNr) throws IOException {
        this.write(MessageFormat.format(numFeldTemplate, 
                feld.getBezeichnung(),
                tdsNr,
                feldNr,
                feld.getClass().getSimpleName(),
                feld.getAnzahlBytes(),
                feld.getByteAdresse(),
                feld.getBezeichner(),
                feld.getNachkommastellen(),
                (feld.getAnzahlBytes() - feld.getNachkommastellen())
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
    
    /**
     * Exportiert das uebergegbene Datenpaket als Java-Enum-Klassen im
     * angegebenen Verzeichnis. Dabei wird die entsprechende Package-Struktur
     * erstellt, sodass dieses Verzeichnis als Source-Verzeichnis oder zum
     * Import dienen kann.
     * 
     * @param dir Export-Verzeichnis
     * @param datenpaket das Datenpaket
     * @throws IOException wenn z.B. das Package-Verzeichnis nicht erstellt werden kann
     */
    public static void toDir(final File dir, final Datenpaket datenpaket) throws IOException {
        toDir(dir, datenpaket.getVorsatz());
        for (Datensatz datensatz : datenpaket.getDatensaetze()) {
            toDir(dir, datensatz);
        }
        toDir(dir, datenpaket.getNachsatz());
    }

    /**
     * Hiermit kann ein einzelner (Daten-)Satz als Java-Enum-Klasse exportiert
     * werden.
     *
     * @param dir Export-Verzeichnis
     * @param satz der (Daten-)Satz
     * @throws IOException wenn z.B. das Package-Verzeichnis nicht erstellt werden kann
     */
    public static void toDir(final File dir, final Satz satz) throws IOException {
        try {
            toDir(dir, (Datensatz) satz);
        } catch (ClassCastException canhappen) {
            toDir(dir, "gdv/xport/satz/feld", satz);
        }
    }

    /**
     * Hiermit kann ein einzelner DatenSatz als Java-Enum-Klasse exportiert
     * werden.
     *
     * @param dir Export-Verzeichnis
     * @param datensatz der Datensatz
     * @throws IOException wenn z.B. das Package-Verzeichnis nicht erstellt werden kann
     */
    public static void toDir(final File dir, final Datensatz datensatz) throws IOException {
        if (datensatz.hasSparte()) {
            String sparte = MessageFormat.format("sparte{0,number,##0}", datensatz.getSparte());
            toDir(dir, "gdv/xport/satz/feld/" + sparte, datensatz);
        } else {
            toDir(dir, "gdv/xport/satz/feld", datensatz);
        }
    }

    private static void toDir(final File dir, final String packageDirname, final Satz satz) throws IOException {
        File packageDir = new File(dir, packageDirname);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + " is not a directory");
        }
        if (packageDir.mkdirs()) {
            log.info("created: " + packageDir.getAbsolutePath());
        }
        String filename = MessageFormat.format("Feld{0,number,###0}.java", satz.getSatzart());
        File javaFile = new File(packageDir, filename);
        Writer writer = new FileWriter(javaFile);
        JavaFormatter formatter = new JavaFormatter(writer);
        formatter.write(satz);
        writer.close();
        log.info("created: " + javaFile);
    }

}

