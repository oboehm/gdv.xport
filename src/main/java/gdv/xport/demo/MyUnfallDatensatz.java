/*
 * Copyright (c) 2009 - 2016 by Oli B.
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
 * (c)reated 21.11.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.demo;

import java.io.IOException;
import java.net.URL;

import gdv.xport.Datenpaket;
import gdv.xport.satz.Datensatz;
import gdv.xport.satz.model.SatzX;
import gdv.xport.util.SatzFactory;
import gdv.xport.util.XmlFormatter;

/**
 * Diese Demo-Klasse zeigt u.a., wie man eine Enumeration beim gdv-xport-Framework registriert. Sie repraesentiert den
 * Datensatz 0210.030 (Unfall, Vertragsspezifischer Teil), definiert aber nur einige wenige ausgewaehlte Felder.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.3 (21.11.2009)
 */
public class MyUnfallDatensatz extends SatzX {

    /**
     * Hier wird die Enum-Klasse {@link MyFeld210} mit der Datenbeschreibung am
     * Framework registriert und anschliessend das Beispiel von <a href=
     * "http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt">
     * musterdatei_041222.txt</a> importiert.
     *
     * @param args werden ignoriert
     * @throws IOException falls z.B. keine Netzverbindung da ist
     */
    public static void main(final String[] args) throws IOException {
        // im Framework registrieren
        SatzFactory.registerEnum(MyFeld210.class, 210, 30);
        importMusterdatei();
        // und hiermit melden wir den Datensatz wieder vom Framework ab
        SatzFactory.unregister(210, 30);
    }

    private static void importMusterdatei() throws IOException {
        // Datenpaket importieren
        Datenpaket datenpaket = new Datenpaket();
        datenpaket.importFrom(new URL("http://www.gdv-online.de/vuvm/musterdatei_bestand/musterdatei_041222.txt"));
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
     * Hiermit initialisieren wir die Klasse mit Satzart 210 und Sparte 30 (Unfall).
     */
    public MyUnfallDatensatz() {
        super(210, 30, MyFeld210.values());
    }

}
