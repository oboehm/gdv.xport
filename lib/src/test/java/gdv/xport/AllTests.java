/*
 * Copyright (c) 2012 by Oli B.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express orimplied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 11.01.2012 by Oli B. (boehm@javatux.de)
 */

package gdv.xport;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import gdv.xport.config.ConfigTests;
import gdv.xport.demo.DemoTests;
import gdv.xport.feld.FeldTests;
import gdv.xport.io.IoTests;
import gdv.xport.satz.SatzTests;
import gdv.xport.satz.feld.SpartenTests;
import gdv.xport.util.UtilTests;
import patterntesting.runtime.junit.SmokeSuite;

/**
 * Die Klasse AllTests ist eine Test-Suite fuer JUnit 4, mit der alle
 * JUnit-Tests parallel getestet werden koennten, wenn die ParallelSuite-
 * Klasse verwendet wird. Da aber die Tests nicht komplette parallelisierbar
 * sind, wurde stattdessen die SmokeSuite-Klasse verwendet.
 *
 * @author oliver (boehm@javatux.de)
 * @since 0.7 (11.01.2012)
 */
@RunWith(SmokeSuite.class)
@SuiteClasses({ ConfigTests.class, DemoTests.class, FeldTests.class, SpartenTests.class, IoTests.class, SatzTests.class, UtilTests.class,
        BasisTest.class, DatenpaketStreamerTest.class, DatenpaketTest.class, MainTest.class })
public class AllTests {

}
