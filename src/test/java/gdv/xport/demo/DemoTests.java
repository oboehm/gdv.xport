package gdv.xport.demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import patterntesting.concurrent.junit.ParallelSuite;

/**
 * Die Klasse DemoTests ist eine Test-Suite fuer JUnit 4, mit der alle
 * JUnit-Tests parallel getestet werden.
 *
 * @author oliver (boehm@javatux.de)
 * @since 0.9.4 (14-Feb-2014)
 */
@RunWith(ParallelSuite.class)
@SuiteClasses({ ImportExportTest.class, MyUnfallDatensatzTest.class })
public class DemoTests {

}
