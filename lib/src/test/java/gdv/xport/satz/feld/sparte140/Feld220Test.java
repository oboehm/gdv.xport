package gdv.xport.satz.feld.sparte140;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import gdv.xport.satz.AbstractDatensatzTest;
import gdv.xport.satz.Satz;
import gdv.xport.satz.model.Satz220;
import gdv.xport.satz.model.SatzX;
import gdv.xport.satz.xml.SatzXml;
import gdv.xport.satz.xml.XmlService;
import gdv.xport.util.SatzTyp;

/**
 * Unit-Tests fuer {@link Feld220}.
 *
 * @author Ken Schosinsky
 * @since 26.04.2018
 */
public class Feld220Test extends AbstractDatensatzTest {

    /**
     * Hier erzeugen wir einen Satz zum Testen.
     *
     * @return Satz zum Testen
     * @see gdv.xport.satz.AbstractSatzTest#getSatz()
     */
    @Override
    protected Satz getSatz() {
        return new Satz220(140);
    }

    /**
     * Dieser Test ueberprueft, ob wie erwartet zwei Teildatensaetze eingelesen werden.
     * 
     * @throws IOException sollte eigentlich nicht vorkommen, da wir von einem String importieren
     */
    @Test
    public void testTeildatensaetze() throws IOException {
        String input = 
                  "02209999  140      59999999997019999009999                      "
                + "                                                                "
                + "                                                                "
                + "                                                               1" + "\n"
                + "02209999  140      59999999997019999009999                      "
                + "                                                                "
                + "                                                                "
                + "                                                               2" + "\n";

        // Teste SatzXml
        SatzXml satzXml = XmlService.getInstance().getSatzart(new SatzTyp(220, 140));
        satzXml.importFrom(input);
        assertEquals("Zwei Teildatensaetze erwartet (SatzXml)", satzXml.getNumberOfTeildatensaetze(), 2);

        // Teste SatzX
        SatzX satzX = new SatzX(220, Feld220.values());
        satzX.importFrom(input);
        assertEquals("Zwei Teildatensaetze erwartet (SatzX)", satzX.getNumberOfTeildatensaetze(), 2);

        // Teste Satz220 (default via Datenpaket)
        Satz220 satz220 = new Satz220(140);
        satz220.importFrom(input);
        assertEquals("Zwei Teildatensaetze erwartet (Satz220)", satz220.getNumberOfTeildatensaetze(), 2);
    }

}
