/*
 * Copyright (c) 2018 by Oliver Boehm
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
 * (c)reated 17.04.2018 by oboehm (ob@oasd.de)
 */
package gdv.xport.satz.model;

import gdv.xport.satz.xml.SatzXml;
import gdv.xport.satz.xml.XmlService;
import gdv.xport.util.SatzTyp;

/**
 *  Die Klasse Satz250 hat als Besonderheit, dass die Satznummer nicht am Ende
 *  auf Adresse 256 steht, sondern mittendrin auf Adresse 51. Deswegen muessen
 *  wir den Satz, den wir ums vom {@link XmlService} holen, noch anpassen.
 *
 * @author oboehm
 * @since 3.2 (17.04.2018)
 * @deprecated Sonderfall wird jetzt in SatzXml beruecksichtigt
 */
@Deprecated
public class Satz250 extends SatzXml {

    /**
     * Instantiiert einen allgemeinen Datensatz fuer die Satzart 250. Diese
     * Satzart gibt es nur fuer die Sparte 190 (Transport).
     */
    public Satz250() {
        super(setUpSatzart250());
    }

    private static SatzXml setUpSatzart250() {
        return (XmlService.getInstance().getSatzart(SatzTyp.of("0250.190")));
    }

}
