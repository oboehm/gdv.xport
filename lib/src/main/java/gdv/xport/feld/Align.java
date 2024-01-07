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
 * (c)reated 12.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.feld;

/**
 * Ausrichtung: rechts- oder linksbuendig (left, right).
 * 
 * @author oliver
 * @since 12.10.2009
 * @version $Revision$
 */
public enum Align {
    /** unbekannt. */
    UNKNOWN(0),
    /** linksbuendig. */
    LEFT(1),
    /** rechtsbuendig. */
    RIGHT(2);

    private final byte code;

    Align(int n) {
        code = (byte) n;
    }

    /**
     * Liefert den Code zurueck.
     *
     * @return 1 oder 2
     */
    public byte getCode() {
        return code;
    }

    /**
     * Wandelt das uebergebene Byte in ein Aling-Objekt um.
     *
     * @param b 1 oder 2
     * @return LEFT oder RIGHT
     */
    public static Align of(byte b) {
        switch (b) {
            case 1:
                return LEFT;
            case 2:
                return RIGHT;
            default:
                return UNKNOWN;
        }
    }

}

