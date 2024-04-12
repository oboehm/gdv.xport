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

import gdv.xport.util.SatzTyp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
