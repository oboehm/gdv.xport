/*
 * Copyright (c) 2010 - 2012 by Oli B.
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
 * (c)reated 19.11.2010 by Oli B. (ob@aosd.de)
 */

package gdv.xport;

import org.junit.*;
import org.junit.runner.*;
import patterntesting.runtime.annotation.*;
import patterntesting.runtime.junit.*;

import java.io.*;


/**
 * Dieser JUnit-Test ueberprueft einige grundlegende Dinge wie die korrekte
 * Implementierung aller equals-Methoden. Es greift dabei auf den ObjectTester
 * und anderen Test-Hilfsmittel von PatternTesting zurueck.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.5.0 (19.11.2010)
 */
@RunWith(SmokeRunner.class)
@IntegrationTest
public class BasisTest {

    private static final Package xportPackage = BasisTest.class.getPackage();

    /**
     * Hiermit werden alle ueberschriebenen equals()-Methoden und
     * toString()-Methoden ueberprueft.
     */
    @Test
    public void testClasses() {
        ObjectTester.assertAll(xportPackage);
    }

    /**
     * Dieser Test dient nur zur Vorbeugung fuer kuenftige Erweiterungen.
     * Momentan sind noch keine Cloneable-Klassen vorhanden.
     */
    @Test
    public void testCloneables() {
        CloneableTester.assertCloning(xportPackage);
    }

    /**
     * Dieser Test dient nur zur Vorbeugung fuer kuenftige Erweiterungen.
     * Momentan sind noch keine Serializable-Klassen vorhanden.
     *
     * @throws NotSerializableException
     *             falls die Serialisierung schief gegangen ist.
     */
    @Test
    public void testSerializables() throws NotSerializableException {
        SerializableTester.assertSerialization(xportPackage);
    }

}

