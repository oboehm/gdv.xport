/*
 * Copyright (c) 2011, 2012 by Oli B.
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
 * (c)reated 06.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.annotation;

import gdv.xport.feld.Align;
import gdv.xport.feld.Feld;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Diese Annotation dient als Behaelter fuer einige Meta-Informationen wie
 * Byte-Adresse oder Datentyp.
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (06.03.2011)
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FeldInfo {

    /**
     * Teildatensatz.
     */
    int teildatensatz() default 1;

    /**
     * Feld-Nummer im Teildatensatz.
     */
    int nr() default 1;

    /**
     * Erwarteter Datentyp, der angegeben werden <b>muss</b>.
     */
    Class<? extends Feld> type();

    /**
     * Ausrichtung: rechts- oder linksbuendig.
     */
    Align align() default Align.UNKNOWN;

    /**
     * Anzahl bytes.
     */
    int anzahlBytes() default 1;

    /**
     * Byte adresse.
     */
    int byteAdresse() default -1;

    /**
     * Anzahl Nachkommastellen.
     */
    int nachkommaStellen() default 0;

    /**
     * Erlaeuterung.
     */
    String erlaeuterung() default "siehe Handbuch GDV-Datensatz";

    /**
     * Normalerweise wird der Bezeichnung aus dem Namen abgeleitet. In manchen
     * Faellen muss man (wie z.B. der VU-Nummer) koennen wir etwas nachhelfen.
     *
     * @since 0.9
     */
    String bezeichnung() default "";

    /**
     * Hierueber kann ein Default-Wert fuer ein Element mitgegeben werden.
     *
     * @sincd 1.0
     */
    String value() default "";

}

