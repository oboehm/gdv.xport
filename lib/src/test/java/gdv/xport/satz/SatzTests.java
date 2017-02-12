/*
 * Copyright (c) 2012, 2013 by Oli B.
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
 * (c)reated 10.07.2012 by Oli B. (boehm@javatux.de)
 */

package gdv.xport.satz;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import gdv.xport.satz.feld.FeldTests;
import gdv.xport.satz.model.ModelTests;
import gdv.xport.satz.xml.XmlTests;
import patterntesting.runtime.junit.SmokeSuite;

/**
 * Die Klasse SatzTests ist eine Test-Suite fuer JUnit 4, mit der alle
 * JUnit-Tests in diesem Paket parallel getestet werden.
 *
 * @author oliver (boehm@javatux.de)
 * @since 0.7 (10.07.2012)
 */
@RunWith(SmokeSuite.class)
@SuiteClasses({ FeldTests.class, ModelTests.class, XmlTests.class, AdressteilTest.class,
        AllgemeinerVertragsteilTest.class, DatensatzTest.class, Erweiterungssatz211Test.class,
        Erweiterungssatz221Test.class, NachsatzTest.class, SatzTest.class, TeildatensatzTest.class,
        VertragsspezifischerTeilTest.class, VorsatzTest.class })
public class SatzTests {

}
