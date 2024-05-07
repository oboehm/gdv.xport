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
import gdv.xport.satz.Satz;
import gdv.xport.satz.Teildatensatz;
import gdv.xport.satz.xml.XmlService;
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
import java.util.List;
import java.util.stream.Collectors;

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
        for (Satzart art : getSatzartTable()) {
            satzarten.add(art.getTyp());
        }
        return satzarten;
    }

    public void exportAsProperties(File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            List<Satzart> lines = getSatzartTable();
            for (Satzart satzart : lines) {
                writer.printf("%s=%s\n", satzart.typ, satzart.bezeichnung);
            }
            log.info("{} Zeilen wurde nach {} geschrieben.", lines.size(), file);
        }
    }

    public void exportWithSpartenAsProperties(File file) throws IOException {
        SpartenScanner spartenScanner = new SpartenScanner();
        List<Integer> sparten = spartenScanner.getSparten();
        List<Satzart> satzarten = getSatzartTable();
        for (Satzart satzart : satzarten) {
            SatzTyp satzTyp = satzart.getTyp();
            int n = satzTyp.getSatzart();
            if (n > 1 && n < 9999) {
                if (satzTyp.getSparte() == 0) {
                    List<Integer> satzTypSparten = getSatzTypSparten(satzTyp.getSatzart(), satzarten);
                    List<Integer> remainingSparten = new ArrayList<>(sparten);
                    remainingSparten.removeAll(satzTypSparten);
                    satzart.addSparten(remainingSparten);
                } else {
                    satzart.addSparte(satzTyp.getSparte());
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            for (Satzart satzart : satzarten) {
                writer.printf("%s=%s\n", satzart.typ,
                        satzart.getSparten().stream().map(String::valueOf).collect(Collectors.joining(",")));
            }
            log.info("{} Zeilen wurde nach {} geschrieben.", satzarten.size(), file);
        }
    }

    private List<Integer> getSatzTypSparten(int satzart, List<Satzart> satzarten) {
        List<Integer> sparten = new ArrayList<>();
        for (Satzart sa : satzarten) {
            SatzTyp satzTyp = sa.getTyp();
            if (satzart == satzTyp.getSatzart()) {
                sparten.add(satzTyp.getSparte());
            }
        }
        return sparten;
    }

    public void exportAsCSV(File file) throws IOException {
        List<Satzart> table = getSatzartTable();
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            String[] header = { "Satzart","Satzbezeichnung","Versionsnummer","href" };
            writer.writeNext(header);
            for (Satzart satzart : table) {
                String[] values = new String[4];
                values[0] = satzart.getTyp().toString();
                values[1] = satzart.getBezeichnung();
                values[2] = satzart.getVersionsnummer();
                values[3] = satzart.getHref().toString();
                writer.writeNext(values);
            }
        }
    }

    public void exportSatznummernAsCSV(File file) throws IOException {
        List<Satzart> table = getSatzartTable();
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            String[] header = { "Satztyp","Satzart","Satznummer","ByteAdr" };
            writer.writeNext(header);
            for (Satzart satzart : table) {
                Satz satz = XmlService.getInstance().getSatzart(satzart.getTyp());
                String[] values = new String[header.length];
                values[0] = satzart.getTyp().toString();
                values[1] = Integer.toString(satzart.getTyp().getSatzart());
                for (Teildatensatz tds : satz.getTeildatensaetze()) {
                    values[2] = tds.getSatznummer().getInhalt();
                    values[3] = Integer.toString(tds.getSatznummer().getByteAdresse());
                    writer.writeNext(values);
                }
            }
        }
    }

    private List<Satzart> getSatzartTable() throws IOException {
        List<Satzart> satzarten = new ArrayList<>();
        Document doc = Jsoup.connect(uri.toString()).get();
        Element table = doc.selectXpath("/html/body/table/tbody/tr/td[3]/table[3]").get(0);
        Elements rows = table.select("tr");
        for (int i = 2; i < rows.size(); i++) {
            Satzart satzart = parseSatzart(rows.get(i));
            satzarten.add(satzart);
        }
        return satzarten;
    }

    private Satzart parseSatzart(Element tr) {
        Elements td = tr.select("td");
        Node node = td.get(0).childNodes().get(0);
        URI hrefURI = URI.create(uri + "/..").normalize();
        if (node instanceof Element) {
            hrefURI = URI.create(hrefURI + node.attr("href"));
        }
        return Satzart.of(td.get(0).text(), td.get(1).text(), td.get(2).text(), hrefURI);
    }



    private static class Satzart {

        private final SatzTyp typ;
        private final String bezeichnung;
        private final String versionsnummer;
        private final URI href;
        private final List<Integer> sparten = new ArrayList<>();

        public Satzart(SatzTyp typ, String bezeichnung, String versionsnummer, URI href) {
            this.typ = typ;
            this.bezeichnung = bezeichnung;
            this.versionsnummer = versionsnummer;
            this.href = href;
        }

        public static Satzart of(String art, String bezeichnung, String versionsnummer, URI href) {
            return new Satzart(SatzTyp.of(art), bezeichnung, versionsnummer, href);
        }

        public URI getHref() {
            return href;
        }

        public String getVersionsnummer() {
            return versionsnummer;
        }

        public String getBezeichnung() {
            return bezeichnung;
        }

        public SatzTyp getTyp() {
            return typ;
        }

        public List<Integer> getSparten() {
            return sparten;
        }

        public void addSparte(int n) {
            sparten.add(n);
        }

        public void addSparten(List<Integer> list) {
            sparten.addAll(list);
        }

    }

}
