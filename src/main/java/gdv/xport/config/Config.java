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
 * (c)reated 08.10.2009 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.config;

import gdv.xport.feld.VUNummer;

import java.nio.charset.Charset;

import org.apache.commons.logging.*;

/**
 * Ueber diese Klasse koennen globale Werte (wie z.B. die VU-Nummer) konfiguriert
 * (d.h. gesetzt) und auch abgefragt werden.
 * Ueblicherweise sollten diese Werte am Anfang programmatisch gesetzt werden.
 * Alternativ koennen sie auch ueber System-Properties konfiguriert werden.
 *
 * @author oliver
 * @since 08.10.2009
 * @version $Revision$
 */
public class Config {

    /** Standard-Encoding ist "ISO-8859-1" */
    public static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    /** Fals VUNummer nicht gesetzt ist, wird dies als Dummy eingesetzt */
    public static final String DUMMY_VU_NUMMER = "DUMMY";
    private static final Log log = LogFactory.getLog(Config.class);
    protected static final String GDV_VU_NUMMER = "gdv.VU-Nummer";
    private static VUNummer vunummer;
    /* end of datensatz */
    private static String eod = "\n";

    private Config() {}

    /**
     * Diese Methode dient zwar hauptsaechlich zu Testzwecken, kann aber auch
     * aufgerufen werden, wenn man nicht mehr sicher ist, was denn alles
     * konfiguriert ist.
     */
    public static void reset() {
        vunummer = null;
    }

    public static synchronized void setVUNummer(String nr) {
        setVUNummer(new VUNummer(nr));
    }

    public static synchronized void setVUNummer(VUNummer nr) {
        vunummer = nr;
        log.info("konfigurierte VU-Nummer: " + vunummer);
    }

    public static synchronized VUNummer getVUNummer() {
        if (vunummer == null) {
            vunummer = new VUNummer(System.getProperty(GDV_VU_NUMMER, DUMMY_VU_NUMMER));
        }
        return vunummer;
    }

    /**
     * Hier kann der "End Of Datensatz" (EOD) gesetzt werden.
     * Dieses Zeichen (oder Zeichenkette) wird am Ende jeden
     * Datensatzes mit ausgegeben.
     *
     * @since 0.3
     * @param linefeed z.B. "\n"
     */
    public static void setEOD(String linefeed) {
        eod = linefeed;
    }

    /**
     * @since 0.3
     * @return End-of-Datensatz
     */
    public static String getEOD() {
        return eod;
    }

//    private static String getStringProperty(String key) {
//        String value = System.getProperty(key);
//        if (StringUtils.isBlank(value)) {
//            throw new ConfigException("Property '" + key + "' ist nicht gesetzt!");
//        }
//        return value;
//    }

}

