package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;
import gdv.xport.event.ImportListener;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;

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
public abstract class AbstractFormatter implements ImportListener {

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
        this.setWriter(ostreamWriter);
    }

    /**
     * Sobald diese Methode aufgerufen, wird der uebergebene Satz formattiert
     * ausgegeben.
     *
     * @param satz der soeben importierte Satz
     * @see ImportListener#notice(gdv.xport.satz.Satz)
     */
    @Override
    public void notice(final Satz satz) {
        try {
            this.write(satz);
        } catch (IOException ioe) {
            throw new FormatterException("cannot format " + satz, ioe);
        }
    }

    /**
     * Wenn ein Formatter als {@link ImportListener} eingesetzt werden soll,
     * <em>muss</em> er diese Methode ueberschreiben.
     *
     * @param satz Satz, der ausgegeben werden soll
     * @throws IOException Signals that an I/O exception has occurred.
     * @see #write(Datenpaket)
     */
    public void write(final Satz satz) throws IOException {
        throw new UnsupportedOperationException("write of " + satz + " not yet supported by " + this.getClass());
    }

    /**
     * Ausgabe eines kompletten Datenpakets. Diese Methode war urspruenglich
     * abstrakt, ist aber jetzt nicht mehr unbedingt noetig, wenn
     * {@link #write(Satz)} ueberschrieben wurde.
     *
     * @param datenpaket Datenpaket, das formattiert ausgegeben werden soll
     * @throws IOException bei Problemen mit der Generierung
     * @see #write(Satz)
     */
    public void write(final Datenpaket datenpaket) throws IOException {
        this.write(datenpaket.getVorsatz());
        for (Datensatz satz : datenpaket.getDatensaetze()) {
            this.write(satz);
        }
        this.write(datenpaket.getNachsatz());
        this.writer.flush();
    }

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
