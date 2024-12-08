/*
 * Copyright (c) 2017 by Oliver Boehm
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * (c)reated 21.02.2017 by oboehm (ob@oasd.de)
 */
package gdv.xport.util;

import gdv.xport.Datenpaket;
import gdv.xport.feld.Bezeichner;
import gdv.xport.satz.Datensatz;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.MatcherAssert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;

/**
 * Unit-Tests fuer {@link JsonFormatter}-Klasse.
 *
 * @author oboehm
 */
public final class JsonFormatterTest extends AbstractFormatterTest {

    private static final Logger LOG = LogManager.getLogger(JsonFormatterTest.class);
    private static final File TARGET_DIR = new File("target", "json");

    @Override
    protected AbstractFormatter createFormatter() {
        return new JsonFormatter();
    }

    @BeforeClass
    public static void setUpTargetDir() {
        if (!TARGET_DIR.exists() && TARGET_DIR.mkdirs()) {
            LOG.info("Verzeichnis '{}' wurde angelegt.", TARGET_DIR);
        }
    }

    /**
     * Tested den Export eines Datenpakets als JSON.
     *
     * @throws IOException falls was schief laeuft
     */
    @Test
    public void testWriteEmptyDatenpaket() throws IOException {
        checkWrite(new Datenpaket());
    }

    @Test
    @Ignore // dauert zu lang (ca. 4 Sek.)
    public void testWriteCompleteDatenpaket() throws IOException {
        Datenpaket complete = SatzRegistry.getInstance().getAllSupportedSaetze();
        checkWrite(complete);
    }

    @Test
    public void testWriteDatenpaket() throws IOException {
        Datenpaket datenpaket = SatzRegistry.getInstance().getSupportedSaetzeWith(
                SatzTyp.of("0100"),
                SatzTyp.of("0500")
        );
        datenpaket.setVermittler("MI6");
        Datensatz adressteil = datenpaket.getDatensaetze().get(0);
        adressteil.setFeld(Bezeichner.NAME1, "James");
        adressteil.setFeld(Bezeichner.NAME3, "Bond");
        String json = checkWrite(datenpaket);
        MatcherAssert.assertThat(json, containsString("MI6"));
        MatcherAssert.assertThat(json, containsString("James"));
        MatcherAssert.assertThat(json, containsString("Bond"));
    }

    private String checkWrite(Datenpaket datenpaket) throws IOException {
        File exportFile = new File(TARGET_DIR, String.format("datenpaket%03d.json", datenpaket.getDatensaetze().size()));
        try (StringWriter swriter = new StringWriter()) {
            JsonFormatter formatter = new JsonFormatter(swriter);
            formatter.write(datenpaket);
            swriter.flush();
            String jsonString = swriter.toString().trim();
            FileUtils.writeStringToFile(exportFile, jsonString, StandardCharsets.UTF_8);
            LOG.info("{} wurde zur manuellen Pruefung in '{}' abgelegt", datenpaket, exportFile);
            MatcherAssert.assertThat(jsonString, startsWith("{"));
            LOG.info("{} wurde nach JSON formatiert.", datenpaket);
            return jsonString;
        }
    }

}
