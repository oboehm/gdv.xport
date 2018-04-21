package gdv.xport.satz.feld.sparte20;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import gdv.xport.Datenpaket;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.SatzTyp;

/**
 * Unit-Tests fuer 0220.020.[1-3].
 *
 * @author Ken Schosinsky
 * @since 17.04.2018
 */
public class Satzart220Test {

    @Test
    public void testSatzart220FolgeNr1() {
        Satz satz = SatzFactory.getDatensatz(new SatzTyp(220, 20, -1, 1, -1));
        assertThat("0220.020.1 hat einen Teildatensatz", satz.getNumberOfTeildatensaetze(), is(1));
        
        Teildatensatz tds1 = satz.getTeildatensatz(1);
        assertThat("0220.020.1, Teildatensatz 1 hat 33 Felder in Version 2015", tds1.getFelder().size(), is(33));
    }
    
    @Test
    public void testSatzart220FolgeNr2() {
        Satz satz = SatzFactory.getDatensatz(new SatzTyp(220, 20, -1, 2, -1));
        assertThat("0220.020.2 hat einen Teildatensatz", satz.getNumberOfTeildatensaetze(), is(1));
        
        Teildatensatz tds1 = satz.getTeildatensatz(1);
        assertThat("0220.020.2, Teildatensatz 1 hat 41 Felder in Version 2015", tds1.getFelder().size(), is(41));
    }
    
    @Test
    public void testSatzart220FolgeNr3() {
        Satz satz = SatzFactory.getDatensatz(new SatzTyp(220, 20, -1, 3, -1));
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
        assertThat("0220.020.1 hat einen Teildatensatz", datensatz1.getNumberOfTeildatensaetze(), is(1));
        Teildatensatz tds1_1 = datensatz1.getTeildatensatz(1);
        assertThat("0220.020.1, Teildatensatz 1 hat 33 Felder in Version 2015", tds1_1.getFelder().size(), is(33));
        
        Datensatz datensatz2 = datensaetze.get(1);
        assertThat("0220.020.2 hat einen Teildatensatz", datensatz2.getNumberOfTeildatensaetze(), is(1));
        Teildatensatz tds2_1 = datensatz2.getTeildatensatz(1);
        assertThat("0220.020.2, Teildatensatz 1 hat 41 Felder in Version 2015", tds2_1.getFelder().size(), is(41));
        
        Datensatz datensatz3 = datensaetze.get(2);
        assertThat("0220.020.3 hat einen Teildatensatz", datensatz3.getNumberOfTeildatensaetze(), is(1));
        Teildatensatz tds3_1 = datensatz3.getTeildatensatz(1);
        assertThat("0220.020.3, Teildatensatz 1 hat 40 Felder in Version 2015", tds3_1.getFelder().size(), is(40));
    }

}
