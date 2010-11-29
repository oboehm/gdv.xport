package gdv.xport.util;

import gdv.xport.Datenpaket;

import java.io.*;

/**
 * Dies ist das gemeinsame Oberklasse der verschiedenen Formatter. Es wird
 * z.B. von der Main-Klasse fuer die Ausgabe verwendet.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.4.3 (29.11.2010)
 */
public abstract class AbstractFormatter {

    protected Writer writer;

    public AbstractFormatter() {
        super();
    }

    /**
     * Setzt den Ausgabekanal auf den uebergebenen Writer.
     *
     * @param writer Ausgabekanal
     */
    public void setWriter(final Writer writer) {
        this.writer = writer;
    }

    /**
     * Setzt den Ausgabekanal auf den uebergebenen OutputStream.
     *
     * @param ostream OutputStream als Ausgabekanal
     */
    public void setWriter(final OutputStream ostream) {
        setWriter(new OutputStreamWriter(ostream));
    }

    /**
     * Lenkt den Ausgabekanal auf die uebergebene Datei
     *
     * @param file Ausgabedatei
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void setWriter(final File file) throws IOException {
        setWriter(new FileWriter(file));
    }
    
    /**
     * Ausgabe eines kompletten Datenpakets.
     *
     * @param datenpaket Datenpaket, das formattiert ausgegeben werden soll
     * @throws IOException bei Problemen mit der Generierung
     */
    public abstract void write(final Datenpaket datenpaket) throws IOException;

}
