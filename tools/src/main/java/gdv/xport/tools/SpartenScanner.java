/*
 * Copyright (c) 2024 by Oli B.
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
 * (c)reated 30.04.24 by oboehm
 */
package gdv.xport.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Der SpartenScanner liest die Online-Beschreibung der Sparten, die fuer
 * das 2023er-Release unter <a href=
 * "http://www.gdv-online.de/vuvm/bestand/rel2023/anl1.htm"
 * >Spartenverzeichnis</a> zu finden sind.
 *
 * @author oboehm
 * @since x.x (30.04.24)
 */
public class SpartenScanner {

    private static final Logger log = LogManager.getLogger(SatzartenScanner.class);
    private final URI uri;

    public SpartenScanner() {
        this(2023);
    }

    public SpartenScanner(int jahr) {
        this.uri = URI.create(String.format("http://www.gdv-online.de/vuvm/bestand/rel%d/anl1.htm", jahr));
    }

    public URI getURI() {
        return uri;
    }

    public List<Integer> getSparten() throws IOException {
        List<Integer> sparten = new ArrayList<>();
        for (String[] values : getTableValues()) {
            sparten.add(Integer.parseInt(values[0]));
        }
        return sparten;
    }

    public void exportAsProperties(File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            List<String[]> values = getTableValues();
            for (String[] value : values) {
                writer.printf("%s=%s\n", value[0], value[1]);
            }
        }
        log.info("{} Zeilen wurde nach {} geschrieben.", getTableValues().size(), file);
    }

    private List<String[]> getTableValues() throws IOException {
        List<String[]> satzarten = new ArrayList<>();
        Document doc = Jsoup.connect(uri.toString()).get();
        Element table = doc.selectXpath("/html/body/table/tbody/tr/td[3]/table[3]").get(0);
        Elements rows = table.select("tr");
        for (int i = 2; i < rows.size(); i++) {
            String[] values = new String[3];
            for (int j = 0; j < values.length; j++) {
                values[j] = rows.get(i).select("td").get(j).text();
            }
            satzarten.add(values);
        }
        return satzarten;
    }

}
