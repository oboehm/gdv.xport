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
        for (Sparte values : getTableSparte()) {
            sparten.add(values.getAuspraegung());
        }
        return sparten;
    }

    public void exportAsProperties(File file) throws IOException {
        List<Sparte> values = getTableSparte();
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            for (Sparte s : values) {
                writer.printf("%s=%s\n", s.auspraegung, s.bedeutung);
            }
        }
        log.info("{} Zeilen wurde nach {} geschrieben.", values.size(), file);
    }

    private List<Sparte> getTableSparte() throws IOException {
        List<Sparte> satzarten = new ArrayList<>();
        Document doc = Jsoup.connect(uri.toString()).get();
        Element table = doc.selectXpath("/html/body/table/tbody/tr/td[3]/table[3]").get(0);
        Elements rows = table.select("tr");
        for (int i = 2; i < rows.size(); i++) {
            Elements td = rows.get(i).select("td");
            satzarten.add(Sparte.of(td.get(0).text(), td.get(1).text(), td.get(2).text()));
        }
        return satzarten;
    }



    private static class Sparte {

        private final int auspraegung;
        private final String bedeutung;
        private final String gruppe;

        public Sparte(int auspraegung, String bedeutung, String gruppe) {
            this.auspraegung = auspraegung;
            this.bedeutung = bedeutung;
            this.gruppe = gruppe;
        }

        public static Sparte of(String auspraegung, String bedeutung, String gruppe) {
            return new Sparte(Integer.parseInt(auspraegung), bedeutung, gruppe);
        }

        public int getAuspraegung() {
            return auspraegung;
        }

        public String getBedeutung() {
            return bedeutung;
        }

        public String getGruppe() {
            return gruppe;
        }

    }

}
