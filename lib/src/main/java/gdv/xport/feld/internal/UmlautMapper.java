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
 * (c)reated 25.10.2016 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld.internal;

/**
 * Dies ist eine interne Hilfsklasse, um Umlaute aufzuloesen.
 *
 * @since 1.2
 */
public final class UmlautMapper {

    private UmlautMapper() {
    }

    /**
     * Ersetzt das uebergebene Zeichen, falls es ein Umlaut ist. Ansonsten
     * wird das Zeichen direkt ausgegeben.
     *
     * @param umlaut Zeichen (kann auch ein Umlaut sein)
     * @return aufgeloester Umlaut oder Zeichen selbst
     */
    public static String replaceUmlaut(char umlaut) {
        switch (umlaut) {
            case '\u00c4':
                return "Ae";
            case '\u00d6':
                return "Oe";
            case '\u00dc':
                return "Ue";
            case '\u00e4':
                return "ae";
            case '\u00f6':
                return "oe";
            case '\u00fc':
                return "ue";
            case '\u00df':
                return "ss";
            default:
                return Character.toString(umlaut);
        }
    }

}
