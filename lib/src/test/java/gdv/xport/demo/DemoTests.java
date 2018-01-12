package gdv.xport.demo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import patterntesting.concurrent.junit.ParallelSuite;
import patterntesting.runtime.junit.SmokeRunner;

/**
 * Die Klasse DemoTests ist eine Test-Suite fuer JUnit 4, mit der alle
 * JUnit-Tests getestet werden.
 *
 * @author oliver (boehm@javatux.de)
 * @since 1.0 (14-Feb-2014)
 */
@RunWith(SmokeRunner.class)
@SuiteClasses({ ImportExportTest.class, MyUnfallDatensatzTest.class })
public class DemoTests {

}
