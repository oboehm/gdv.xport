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
 * (c)reated 17.07.2012 by Oli B. (ob@aosd.de)
 */

package gdv.xport.config;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit-Tests fuer {@link ConfigException}.
 * 
 * @author oliver
 * @since 0.7.1 (17.07.2012)
 */
public class ConfigExceptionTest {

    /**
     * Test method for {@link ConfigException#ConfigException(java.lang.String)}.
     */
    @Test
    public void testConfigExceptionString() {
        ConfigException ce = new ConfigException("hello");
        assertEquals("hello", ce.getMessage());
    }

    /**
     * Test method for {@link ConfigException#ConfigException(java.lang.String, java.lang.Throwable)}.
     */
    @Test
    public void testConfigExceptionStringThrowable() {
        Throwable cause = new IllegalArgumentException("world");
        ConfigException ce = new ConfigException("hello", cause);
        assertEquals("hello", ce.getMessage());
        assertEquals(cause, ce.getCause());
    }

}
