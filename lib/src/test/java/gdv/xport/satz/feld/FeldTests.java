/*
 * $Id$
 *
 * Copyright (c) 2012 by Oliver Boehm
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
 * (c)reated 16.04.2013 by oliver (ob@oasd.de)
 */

package gdv.xport.satz.feld;

import gdv.xport.satz.feld.sparte10.wagnisart9.Wagnisart9Tests;
import gdv.xport.satz.feld.sparte20.Satzart220Test;
import gdv.xport.satz.feld.sparte30.Sparte30Tests;
import gdv.xport.satz.feld.sparte51.Sparte51Tests;

import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Die Klasse FeldTests ist eine Test-Suite fuer JUnit 4, mit der alle
 * JUnit-Tests in diesem Paket getestet werden.
 *
 * @author oliver
 * @since 0.9 (16.04.2013)
 */
@RunWith(Suite.class)
@SuiteClasses({Wagnisart9Tests.class, Sparte30Tests.class, Sparte51Tests.class, Feld0001Test.class, Feld100Test.class, Feld200Test.class, Feld9999Test.class, Satzart220Test.class})
public class FeldTests {

}

