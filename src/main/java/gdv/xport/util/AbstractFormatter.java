package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Dies ist das gemeinsame Oberklasse der verschiedenen Formatter. Es wird
 * z.B. von der Main-Klasse fuer die Ausgabe verwendet.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.5.0 (29.11.2010)
 */
public abstract class AbstractFormatter {

    private Writer writer;

    /**
     * Default-Konstruktor, der als Standard-Ausgabekanal stdout einstellt.
     */
    public AbstractFormatter() {
        this(System.out);
    }

    /**
     * Instantiiert einen neuen Formatter.
     *
     * @param writer the writer
     */
    public AbstractFormatter(final Writer writer) {
        this.writer = writer;
    }

    /**
     * Instantiiert einen neuen Formatter.
     *
     * @param ostream the ostream
     */
    public AbstractFormatter(final OutputStream ostream) {
        this(new OutputStreamWriter(ostream, Config.DEFAULT_ENCODING));
    }

    /**
     * Liefert den eingestellten Writer.
     *
     * @return the writer
     */
    public final Writer getWriter() {
        return writer;
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

//    /**
//     * Lenkt den Ausgabekanal auf die uebergebene Datei.
//     * <p>
//     * <i>Bitte nicht mehr verwenden, da sonst diese Methode auch fuer das
//     * Schliessen des OutputStreams verantwortlich waere. Das kann sie aber
//     * nicht!</i>
//     * </p>
//     *
//     * @param file Ausgabedatei
//     * @throws IOException Signals that an I/O exception has occurred.
//     * @deprecated bitte nicht mehr verwenden
//     */
//    @Deprecated
//    public void setWriter(final File file) throws IOException {
//        OutputStream ostream = new FileOutputStream(file);
//        setWriter(ostream);
//        // we don't close ostream here because otherwise we get an XmlStreamException elsewhere
//    }

    /**
     * Ausgabe eines kompletten Datenpakets.
     *
     * @param datenpaket Datenpaket, das formattiert ausgegeben werden soll
     * @throws IOException bei Problemen mit der Generierung
     */
    public abstract void write(final Datenpaket datenpaket) throws IOException;

    /**
     * Ausgabe des uebergebenen Strings.
     *
     * @param content the content
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void write(final String content) throws IOException {
        this.writer.write(content);
    }

}
