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
 * (c)reated 17.11.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport;

import gdv.xport.util.XmlFormatter;

import java.io.*;

/**
 * @author oliver (oliver.boehm@agentes.de)
 * @since 0.2 (17.11.2009)
 *
 */
public class Main {

    /**
     * Diese Main-Klasse dient hautpsaechlich zu Demo-Zwecken.
     * 
     * @since 0.2
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("usage: " + Main.class.getName() + " importfile");
            System.exit(1);
        }
        Datenpaket datenpaket = new Datenpaket();
        datenpaket.importFrom(new File(args[0]));
        System.out.println(XmlFormatter.toString(datenpaket));
        if (!datenpaket.isValid()) {
            System.err.println(args[0] + " scheint ungueltige Felder zu enthalten");
        }
    }
    
    private Main() {}

}

