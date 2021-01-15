/*
 * Copyright (c) 2021 by Oliver Boehm
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
 * (c)reated 15.01.21 by oliver (ob@oasd.de)
 */

package gdv.xport.util;

/**
 * Der VersionHandler ist fuer die Abfrage der Default-Version eines Satzes
 * zustaendig.
 *
 * @author <a href="ob@aosd.de">oliver</a>
 * @since 5.0 (15.01.21)
 */
public interface VersionHandler {

    /**
     * Liefert die (Default-)Version der angefragten Satzart
     *
     * @param satzTyp Satzart, z.B. "0001"
     * @return z.B. "2.4"
     */
    String getVersionOf(SatzTyp satzTyp);

}
