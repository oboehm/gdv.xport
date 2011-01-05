package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;

import java.io.*;

/**
 * Dies ist das gemeinsame Oberklasse der verschiedenen Formatter. Es wird
 * z.B. von der Main-Klasse fuer die Ausgabe verwendet.
 * 
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.5.0 (29.11.2010)
 */
public abstract class AbstractFormatter {

    protected Writer writer;

    /**
     * Default-Konstruktor.
     */
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
        OutputStreamWriter ostreamWriter = new OutputStreamWriter(ostream, Config.DEFAULT_ENCODING);
        setWriter(ostreamWriter);
    }

    /**
     * Lenkt den Ausgabekanal auf die uebergebene Datei.
     * <i>Bitte nicht mehr verwenden, da sonst diese Methode auch fuer das
     * Schliessen des OutputStreams verantwortlich waere. Das kann sie aber
     * nicht!</i>
     *
     * @param file Ausgabedatei
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Deprecated
    public void setWriter(final File file) throws IOException {
        OutputStream ostream = new FileOutputStream(file);
        setWriter(ostream);
        // we don't close ostream here because otherwise we get an XmlStreamException elsewhere
    }
    
    /**
     * Ausgabe eines kompletten Datenpakets.
     *
     * @param datenpaket Datenpaket, das formattiert ausgegeben werden soll
     * @throws IOException bei Problemen mit der Generierung
     */
    public abstract void write(final Datenpaket datenpaket) throws IOException;

}
