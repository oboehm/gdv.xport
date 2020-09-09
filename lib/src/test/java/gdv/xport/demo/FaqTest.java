package gdv.xport.demo;

import gdv.xport.Datenpaket;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.Feld;
import gdv.xport.feld.NumFeld;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Vorsatz;
import gdv.xport.satz.feld.Feld200;
import gdv.xport.util.SatzFactory;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class FaqTest.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 * @since 4.2 (08.09.20)
 */
public final class FaqTest {

    /**
     * FRAGE:
     *
     * In Klasse Feld210 (package gdv.xport.satz.feld.sparte30) steht als Beschreibung:
     * * Gegenueber der XML-Beschreibung "VUVM2018.xml" fehlt das letzte Feld
     * * "Erweiterter BerVersV-Schluessel".
     * * Von daher sollte die XML-Variante fuer die Instanziierung von
     * * Satz 210.030 herangezogen werden.
     *
     * Was ist damit gemeint? Ein Beispiel wäre prima für VUVM2013_010713.xml
     * z.B. von hier: http://www.gdv-online.de/vuvm/bestand/best_2013.htm
     *
     * ANTWORT:
     *
     * Geht man ueber die SatzFactory, landet man automatisch bei der aktuellen
     * XML-Variante. Fuer die Frage nach dem Bezeichner schaut man am besten ins
     * Handbuch und uebernimmt dort einfach den Eintrag fuer "Bezeichnung"
     * (ohne Umlaute). In diesem Fall ist das:
     *
     *  Bezeichner.of("Erweiterter BerVersV-Schluessel")
     *
     * Fuer die allermeisten Bezeichner gibt es bereits eine Konstante, die
     * stattdessen verwendet werden kann:
     *
     *  Bezeichner.ERWEITERTER_BERVERSV_SCHLUESSEL
     */
    @Test
    public void testFeld210Sparte30() throws IOException {
        Datensatz satz = SatzFactory.getDatensatz(210, 30);
        satz.set(Bezeichner.of("Erweiterter BerVersV-Schluessel"), "12345");
        String schluessel = satz.get(Bezeichner.ERWEITERTER_BERVERSV_SCHLUESSEL);
        assertEquals("12345", schluessel);
        Feld schluesselFeld = satz.getFeld(Bezeichner.ERWEITERTER_BERVERSV_SCHLUESSEL);
        assertTrue(schluesselFeld instanceof NumFeld);
        assertEquals(239, schluesselFeld.getByteAdresse());
        assertEquals(5, schluesselFeld.getAnzahlBytes());
        assertEquals("12345", schluesselFeld.getInhalt());
        satz.export(System.out);
    }

    /**
     * FRAGE:
     *
     * In der Standard-Variante habe ich ein Datenpaket erzeugt.
     * Beim Export sind die Felder für die Satzversionen in Satzart 001 falsch
     * bzw. gar nicht besetzt:
     *
     * Satzversionen in SA0001 falsch für SA0001, SA0200,
     * Satzversionen in SA0001, Teildatensatz 1:
     * Position 105 (Version SA0210, Sparte 50 fehlt)
     * Position 123 (Version SA0210, Sparte 40 fehlt)
     * Position 129 (Version SA0210, Sparte 30 fehlt)
     * Position 135 (Version SA0210, Sparte 10 fehlt)
     * Position 141 (Version SA0210, Sparte 130 fehlt)
     * Position 147 (Version SA0210, Sparte 110 fehlt)
     * Position 153 (Version SA0210, Sparte 140 fehlt)
     * Position 159 (Version SA0210, Sparte 20 fehlt)
     * Position 165 (Version SA0210, Sparte 70 falsch)
     * Position 168 (Version SA0220, Sparte 70 besetzt, obwohl nicht in Datenpaket)
     * Position 171 (Version SA0210, Sparte 80 fehlt)
     * Position 177 (Version SA0210, Sparte 510 fehlt)
     *
     * Was habe ich falsch gemacht?
     *
     * ANTWORT:
     *
     * Aktuell werden die Version noch nicht aktiv gesetzt, sondern nur
     * mit dem Stand von 2009 (teilweise) vorbelegt. D.h. die Version im
     * Vorsatz muss explizit gesetzt werden.
     *
     * Leider werden bei der Initialisierung auch Versionen fuer Satzarten
     * gesetzt, die nicht verwendet werden.
     *
     * Denkbar waere, dass kuenftig bei der Datenpaket.add(..)-Methode eine
     * Version mit angegeben werden kann. Aktuell ist das noch nicht der Fall.
     * Auch die Versions-Infos aus der XML-Beschreibung werden noch nicht
     * ausgenutzt.
     */
    @Test
    public void testCreateDatenpaket() {
        Datenpaket dd = new Datenpaket("Hello");
        Datensatz satz200 = SatzFactory.getDatensatz(200);
        satz200.set(Feld200.ABGANGSGRUND, 10);
        dd.add(satz200);
        Vorsatz vorsatz = dd.getVorsatz();
        Feld version = vorsatz.getFeld(Bezeichner.VERSION_SATZART_0200);
        assertEquals(102, version.getByteAdresse());
        //assertEquals("2.5", version.getInhalt());   // schlaegt fehl, da mit "2.2" vorbelegt ist
    }

}
