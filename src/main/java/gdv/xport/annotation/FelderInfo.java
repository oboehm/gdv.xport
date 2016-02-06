/*
 * Copyright (c) 2012 by Oli B.
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
 * (c)reated 17.07.2012 by Oli B. (ob@aosd.de)
 */

package gdv.xport.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Diese Annotation verweist auf eine Enum mit FeldInfos.
 * Da Java leider keine Ableitung von Annotations zulaesst, muessen wir
 * zu diesem Trick greifen, um Gemeinsamkeiten in eine gemeinsame Enum
 * auslagern zu koennen.
 *
 * @author oliver
 * @since 0.7.1 (17.07.2012)
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FelderInfo {

    /**
     * Enum mit den FeldInfos, der angegeben werden <b>muss</b>.
     *
     * @return den Typ der Enum
     */
    Class<? extends Enum<?>> type();

    /**
     * Sparte, fuer den die referenzierten FeldInfos gelten. Mit diesem Wert
     * wird die Spalte vorbelegt.
     *
     * @return die Sparte
     * @since 0.9
     */
    int sparte() default 0;

    /**
     * Teildatensatz, fuer den die referenzierten FeldInfos gelten sollen,
     * falls er dort nicht angegeben ist. -1 bedeutet dabei, dass kein Wert
     * vorbelegt wird.
     *
     * @return Nummer des Teildatensatzes
     */
    int teildatensatz() default -1;

    /**
     * Erlaeuterung.
     *
     * @return einen Text
     */
    String erlaeuterung() default "Enum mit weiteren FeldInfos";

}
