package gdv.xport.satz.feld.sparte20;

import gdv.xport.Datenpaket;
import gdv.xport.config.Config;
import gdv.xport.feld.Bezeichner;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzTyp;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Unit-Tests fuer 0220.020.[1-3].
 *
 * @author Ken Schosinsky
 * @since 17.04.2018
 */
public class Satzart220Test {

    @Test
    public void testSatzart220FolgeNr1() {
        Satz satz = SatzFactory.getDatensatz(SatzTyp.of("0220.020.1"));
        assertThat("0220.020.1 hat einen Teildatensatz", satz.getNumberOfTeildatensaetze(), is(1));
        
        Teildatensatz tds1 = satz.getTeildatensatz(1);
        if ("VUVM2018.xml".equals(Config.getXmlResource()) || "VUVM2015.xml".equals(Config.getXmlResource())) {
            assertThat("0220.020.1, Teildatensatz 1 hat 33 Felder in Version 2015", tds1.getFelder().size(), is(33));
        }
    }
    
    @Test
    public void testSatzart220FolgeNr2() {
        Satz satz = SatzFactory.getDatensatz(SatzTyp.of("0220.020.2"));
        assertThat("0220.020.2 hat einen Teildatensatz", satz.getNumberOfTeildatensaetze(), is(1));
        if ("VUVM2018.xml".equals(Config.getXmlResource()) || "VUVM2015.xml".equals(Config.getXmlResource())) {
            Teildatensatz tds1 = satz.getTeildatensatz(1);
            assertThat("0220.020.2, Teildatensatz 1 hat 41 Felder in Version 2015", tds1.getFelder().size(), is(41));
        }
    }
    
    @Test
    public void testSatzart220FolgeNr3() {
        Satz satz = SatzFactory.getDatensatz(SatzTyp.of("0220.020.3"));
        assertThat("0220.020.3 hat einen Teildatensatz", satz.getNumberOfTeildatensaetze(), is(1));
        
        Teildatensatz tds1 = satz.getTeildatensatz(1);
        assertThat("0220.020.3, Teildatensatz 1 hat 40 Felder in Version 2015", tds1.getFelder().size(), is(40));
    }
    
    @Test
    public void testSatzart220Import() throws IOException {
        Datenpaket datenpaket = new Datenpaket();
        URL url = this.getClass().getResource("/satz220.020.txt");
        datenpaket.importFrom(url);

        List<Datensatz> datensaetze = datenpaket.getDatensaetze();
        assertThat("satz220.020.txt hat drei Datensaetze", datensaetze.size(), is(3));
        
        Datensatz datensatz1 = datensaetze.get(0);
        SatzTyp satztyp1 = datensatz1.getSatzTyp();
        assertThat("Satztyp hat Satzart 220", satztyp1.getSatzart(), is(220));
        assertThat("Satztyp hat Sparte 20", satztyp1.getSparte(), is(20));
        assertThat("Satztyp hat Krankenfolgenummer 1", satztyp1.getKrankenFolgeNr(), is(1));
        assertThat("0220.020.1 hat einen Teildatensatz", datensatz1.getNumberOfTeildatensaetze(), is(1));
        Teildatensatz tds1_1 = datensatz1.getTeildatensatz(1);
        if ("VUVM2018.xml".equals(Config.getXmlResource()) || "VUVM2015.xml".equals(Config.getXmlResource())) {
            assertThat("0220.020.1, Teildatensatz 1 hat 33 Felder in Version 2015", tds1_1.getFelder().size(), is(33));
            Datensatz datensatz2 = datensaetze.get(1);
            SatzTyp satztyp2 = datensatz2.getSatzTyp();
            assertThat("Satztyp hat Satzart 220", satztyp2.getSatzart(), is(220));
            assertThat("Satztyp hat Sparte 20", satztyp2.getSparte(), is(20));
            assertThat("Satztyp hat Krankenfolgenummer 2", satztyp2.getKrankenFolgeNr(), is(2));
            assertThat("0220.020.2 hat einen Teildatensatz", datensatz2.getNumberOfTeildatensaetze(), is(1));
            Teildatensatz tds2_1 = datensatz2.getTeildatensatz(1);
            assertThat("0220.020.2, Teildatensatz 1 hat 41 Felder in Version 2015", tds2_1.getFelder().size(), is(41));

            Datensatz datensatz3 = datensaetze.get(2);
            SatzTyp satztyp3 = datensatz3.getSatzTyp();
            assertThat("Satztyp hat Satzart 220", satztyp3.getSatzart(), is(220));
            assertThat("Satztyp hat Sparte 20", satztyp3.getSparte(), is(20));
            assertThat("Satztyp hat Krankenfolgenummer 3", satztyp3.getKrankenFolgeNr(), is(3));
            assertThat("0220.020.3 hat einen Teildatensatz", datensatz3.getNumberOfTeildatensaetze(), is(1));
            Teildatensatz tds3_1 = datensatz3.getTeildatensatz(1);
            assertThat("0220.020.3, Teildatensatz 1 hat 40 Felder in Version 2015", tds3_1.getFelder().size(), is(40));
        }
    }

    @Test
    public void testFehlendeKrankenFolgeNr() throws IOException {
        Datenpaket datenpaket = new Datenpaket();
        URL url = this.getClass().getResource("/satz220.20.fehlerhaft.txt");
        datenpaket.importFrom(url);

        List<Datensatz> datensaetze = datenpaket.getDatensaetze();
        assertThat("0220.020, Drei Datensaetze", datensaetze.size(), is(3));

        for (Datensatz datensatz : datensaetze) {
            SatzTyp satztyp = datensatz.getSatzTyp();
            assertThat("Satztyp hat Satzart 220", satztyp.getSatzart(), is(220));
            assertThat("Satztyp hat Sparte 20", satztyp.getSparte(), is(20));
            assertFalse("Satztyp hat keine Krankenfolgenummer", satztyp.hasKrankenFolgeNr());

            assertThat("Datensatz hat einen Teildatensatz", datensatz.getNumberOfTeildatensaetze(), is(1));
            assertThat("Teildatensatz 1 hat UNBEKANNT", datensatz.getTeildatensatz(1).hasFeld(Bezeichner.UNBEKANNT), is(true));
        }
    }

}
