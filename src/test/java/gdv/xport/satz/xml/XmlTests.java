/*
 * Copyright (c) 2014 by Oli B.
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
 * (c)reated 27.09.2014 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.xml;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Diese Testsuite fasst alle Tests in dem xml-Package zusammen.
 *
 * @since 1.0
 */
@RunWith(Suite.class)
@SuiteClasses({ FeldReferenzTest.class, FeldXmlTest.class, SatzXmlTest.class, TeildatensatzXmlTest.class,
        XmlServiceTest.class })
public class XmlTests {

}
