/*
 * Copyright (c) 2009 - 2012 by Oli B.
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
 * (c)reated 19.11.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.demo;

import gdv.xport.Datenpaket;
import gdv.xport.satz.Vorsatz;

import java.io.IOException;

/**
 * Ein einfaches Hello-World-Projekt fuer gdv.xport.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.3 (19.11.2009)
 */
public final class HelloWorld {

    /**
     * In diesem Beispiel erzeugen wir ein leeres Datenpaket, setzen die
     * VU-Nummer auf "Hello" und den Adressat (der im Vorsatz zu finden ist)
     * auf "World".
     * Am Ende "exportieren" wir dann das Datenpaket auf System.out.
     *
     * @since 0.3
     * @param args wird nicht ausgewertet.
     * @throws IOException falls auf System.out nicht geschrieben werden kann
     */
    public static void main(final String[] args) throws IOException {
        // wir erzeugen ein Datenpaket mit "Hello" als VU-Nummer
        Datenpaket datenpaket = new Datenpaket("Hello");
        // jetzt wollen wir den Adressat (der im Vorsatz steht) auf "World" setzen
        Vorsatz vorsatz = datenpaket.getVorsatz();
        vorsatz.setAdressat("World");
        // und jetzt geben wir das Datenpaket auf System.out aus
        datenpaket.export(System.out);
    }

    /**
     * Damit diese Klasse nicht instantiiert werden kann, ist der Konstruktor
     * "private".
     */
    private HelloWorld() {
    }

}

