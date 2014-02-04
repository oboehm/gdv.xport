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
 * (c)reated 18.01.2014 by Oli B. (oliver.boehm@gmail.com)
 */

package gdv.xport.io;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import patterntesting.concurrent.junit.ParallelSuite;

/**
 * Hiermit werden die Tests in diesem Paket parallel gestartet.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.9.2 (18.01.2014)
 */
@RunWith(ParallelSuite.class)
@SuiteClasses({ ImportExceptionTest.class, RecordReaderTest.class, RecyclingInputStreamReaderTest.class })
public class IoTests {

}

