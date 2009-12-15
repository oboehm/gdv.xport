/*
 * Copyright (c) 2009 by agentes
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
 * (c)reated 21.11.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.demo;

import gdv.xport.Datenpaket;
import gdv.xport.feld.*;
import gdv.xport.satz.Datensatz;
import gdv.xport.util.*;

import java.io.IOException;
import java.net.URL;

import javax.xml.stream.XMLStreamException;

/**
 * Diese Demo-Klasse zeigt u.a., wie man einen Datensatz beim
 * gdv-xport-Framework registriert. Es repraesentiert den Datensatz
 * 0210.030 (Unfall, Vertragsspezifischer Teil), definiert aber nur
 * einige wenige ausgewaehlte Felder
 *
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.3 (21.11.2009)
 */
public class MyUnfallDatensatz extends Datensatz {

    /**
     * Hier wird diese Klasse am Framework registriert und anschliessend das
     * Beispiel von
     * {@link "http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt"}
     * importiert.
     *
     * @param args werden ignoriert
     * @throws IOException falls z.B. keine Netzverbindung da ist
     * @throws XMLStreamException falls die XML-Generierung nicht geklappt hat
     */
    public static void main(String[] args) throws IOException, XMLStreamException {
        // im Framework registrieren
        SatzFactory.register(MyUnfallDatensatz.class, 210, 30);
        // Datenpaket importieren
        Datenpaket datenpaket = new Datenpaket();
        datenpaket.importFrom(new URL(
                "http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt"));
        // jetzt den ersten Datensatz 210, Sparte 30 suchen und ausgeben
        for (Datensatz datensatz : datenpaket.getDatensaetze()) {
            if ((datensatz.getSatzart() == 210) && (datensatz.getSparte() == 30)) {
                datensatz.export(System.out);
                new XmlFormatter(System.out).write(datensatz);
                break;
            }
        }
    }

    /**
     * Hiermit initialisieren wir die Klasse mit Satzart 210 und Sparte 30
     * (Unfall)-
     */
    public MyUnfallDatensatz() {
        super(210, 30);
        this.setUpDatenfelder();
    }

    /**
     * Hier werden jetzt als Beispiel zwei Felder aufgesetzt:
     * das Beginn-Datum und der Waehrungsschluessel mit der jeweiligen
     * Byte-Adresse und Laenge.
     */
    private void setUpDatenfelder() {
        add(new Datum("mein Vertragsbeginn", 8, 44));
        add(new AlphaNumFeld("meine Waehrung", 3, 93));
    }

}

