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
 * (c)reated 19.01.2015 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

import com.fasterxml.jackson.databind.ObjectMapper;
import gdv.xport.config.Config;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.junit.BeforeClass;
import org.junit.Test;
import patterntesting.runtime.junit.CloneableTester;
import patterntesting.runtime.junit.ObjectTester;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

/**
 * Die gemeinsame Oberklasse fuer alle Tests mit einem {@link Feld}-Objekt.
 *
 * @author oliver
 * @since 1.0 (19.01.2015)
 */
public abstract class AbstractFeldTest {

    private static final Logger log = LogManager.getLogger(AbstractFeldTest.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final File JSON_DIR = new File("target", "json");

    /**
     * Darueber holt sich diese Test-Klasse ein {@link Feld}-Objekt zum Testen.
     *
     * @return das Test-Feld
     */
    protected abstract Feld getTestFeld();

    @BeforeClass
    public static void setUpJsonDir() {
        if (!JSON_DIR.exists() && JSON_DIR.mkdirs()) {
            log.info("Verzeichnis '{}' wurde angelegt.", JSON_DIR);
        }
    }

    /**
     * Hier testen wir, ob der CopyConstructor auch mit den Unterklassen von
     * {@link Feld} funktioniert.
     */
    @Test
    public void testCopyConstructor() {
        Feld orig = this.getTestFeld();
        Feld copy = new Feld(orig);
        ObjectTester.assertEquals(orig, copy);
    }

    /**
     * Test-Methode fuer {@link Feld#clone()}:
     */
    @Test
    public void testCloneable() {
        CloneableTester.assertCloning(getTestFeld());
    }

    @Test
    public void testClone() {
        Feld a = getTestFeld().mitConfig(Config.STRICT);
        Feld b = (Feld) a.clone();
        assertEquals(a, b);
        assertEquals(a.config, b.config);
    }

    @Test
    public void testToJSON() throws IOException {
        Feld feld = this.getTestFeld();
        feld.setInhalt("x");
        checkJSON(feld);
    }

    protected static String checkJSON(Feld feld) throws IOException {
        String json = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(feld);
        File exportFile = new File(JSON_DIR, String.format("%s.json", feld.getClass().getSimpleName()));
        FileUtils.writeStringToFile(exportFile, json, StandardCharsets.UTF_8);
        log.info("{} wurde zur manuellen Pruefung in '{}' abgelegt", feld, exportFile);
        MatcherAssert.assertThat(json, containsString(feld.getInhalt().trim()));
        return json;
    }

}
