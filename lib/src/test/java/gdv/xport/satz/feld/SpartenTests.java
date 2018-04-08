package gdv.xport.satz.feld;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import gdv.xport.satz.feld.sparte10.Sparte10Test;
import gdv.xport.satz.feld.sparte10.wagnisart13.Sparte10Wagnis13Test;
import gdv.xport.satz.feld.sparte10.wagnisart2.Sparte10Wagnis2Test;
import gdv.xport.satz.feld.sparte10.wagnisart48.Sparte10Wagnis48Test;
import gdv.xport.satz.feld.sparte10.wagnisart5.Sparte10Wagnis5Test;
import gdv.xport.satz.feld.sparte10.wagnisart6.Sparte10Wagnis6Test;
import gdv.xport.satz.feld.sparte10.wagnisart7.Sparte10Wagnis7Test;
import gdv.xport.satz.feld.sparte10.wagnisart9.Sparte10Wagnis9Test;
import gdv.xport.satz.feld.sparte110.Sparte110Test;
import gdv.xport.satz.feld.sparte130.Sparte130Test;
import gdv.xport.satz.feld.sparte140.Sparte140Test;
import gdv.xport.satz.feld.sparte30.Sparte30Test;
import gdv.xport.satz.feld.sparte40.Sparte40Test;
import gdv.xport.satz.feld.sparte50.Sparte50Test;
import gdv.xport.satz.feld.sparte51.Sparte51Test;
import gdv.xport.satz.feld.sparte52.Sparte52Test;
import gdv.xport.satz.feld.sparte53.Sparte53Test;
import gdv.xport.satz.feld.sparte70.Sparte70Test;

/**
 * Die Klasse SpartenTests ist eine Test-Suite fuer JUnit, mit der alle
 * JUnit-Tests fuer den Zugriff auf Felder via Enums getestet werden.
 * 
 * @author Ken Schosinsky
 * @since 3.1.0 (08.04.2018)
 */
@RunWith(Suite.class)
@SuiteClasses({ Sparte10Wagnis13Test.class, Sparte10Wagnis2Test.class, Sparte10Wagnis48Test.class,
        Sparte10Wagnis5Test.class, Sparte10Wagnis6Test.class, Sparte10Wagnis7Test.class, Sparte10Wagnis9Test.class,
        Sparte10Test.class, Sparte110Test.class, Sparte130Test.class, Sparte140Test.class, Sparte30Test.class,
        Sparte40Test.class, Sparte50Test.class, Sparte51Test.class, Sparte52Test.class, Sparte53Test.class,
        Sparte70Test.class })
public class SpartenTests {

}
