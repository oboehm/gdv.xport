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
 * (c)reated 12.04.24 by oboehm
 */
package gdv.xport.tools;

import com.opencsv.CSVWriter;
import gdv.xport.util.SatzTyp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Der SatzartenScanner liest die Online-Beschreibung der Satzarten, die fuer
 * das 2023er-Release unter <a href=
 * "http://www.gdv-online.de/vuvm/bestand/rel2023/samenue.html"
 * >Satzarten</a> zu finden sind.
 *
 * @author oboehm
 * @since 8.0 (12.04.24)
 */
public final class SatzartenScanner {

    private static final Logger log = LogManager.getLogger(SatzartenScanner.class);
    private final URI uri;

    public SatzartenScanner() {
        this(2023);
    }

    public SatzartenScanner(int jahr) {
        this.uri = URI.create(String.format("http://www.gdv-online.de/vuvm/bestand/rel%d/samenue.html", jahr));
    }

    public URI getURI() {
        return uri;
    }

    public List<SatzTyp> getSatzarten() throws IOException {
        List<SatzTyp> satzarten = new ArrayList<>();
        for (String[] values : getTableValues()) {
            satzarten.add(SatzTyp.of(values[0]));
        }
        return satzarten;
    }

    public void exportAsProperties(File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            List<String[]> lines = getTableValues();
            for (String[] value : lines) {
                writer.printf("%s=%s\n", value[0], value[1]);
            }
        }
        log.info("{} Zeilen wurde nach {} geschrieben.", getTableValuesWithHeader().size(), file);
    }

    public void exportAsCSV(File file) throws IOException {
        List<String[]> lines = getTableValuesWithHeader();
        List<Map<String, String>> table = toMapList(lines);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            String[] header = lines.get(0);
            writer.writeNext(header);
            for (Map<String, String> cells : table) {
                String[] values = new String[header.length];
                for (int i = 0; i < values.length; i++) {
                    values[i] = cells.get(header[i]);
                }
                writer.writeNext(values);
            }
        }
    }

    private List<Map<String, String>> toMapList(List<String[]> lines) {
        List<Map<String, String>> mapList = new ArrayList<>();
        String[] header = lines.get(0);
        for (String[] cells : lines.subList(1, lines.size())) {
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < header.length; i++) {
                map.put(header[i], cells[i]);
            }
            mapList.add(map);
        }
        return mapList;
    }

    private List<String[]> getTableValues() throws IOException {
        List<String[]> lines = getTableValuesWithHeader();
        return lines.subList(1, lines.size() - 1);
    }

    private List<String[]> getTableValuesWithHeader() throws IOException {
        List<String[]> satzarten = new ArrayList<>();
        Document doc = Jsoup.connect(uri.toString()).get();
        Element table = doc.selectXpath("/html/body/table/tbody/tr/td[3]/table[3]").get(0);
        Elements rows = table.select("tr");
        satzarten.add(parseHead(rows.get(1)));
        for (int i = 2; i < rows.size(); i++) {
            String[] values = parseRow(rows.get(i));
            satzarten.add(values);
        }
        return satzarten;
    }

    private static String[] parseHead(Element tr) {
        String[] values = parseRow(tr);
        values[3] = "href";
        return values;
    }

    private static String[] parseRow(Element tr) {
        String[] values = new String[4];
        Elements td = tr.select("td");
        for (int j = 0; j < 3; j++) {
            values[j] = td.get(j).text();
        }
        Node node = td.get(0).childNodes().get(0);
        if (node instanceof Element) {
            values[3] = node.attr("href");
        }
        return values;
    }

}
