/*
 * Copyright (c) 2010 by agentes
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
 * (c)reated 06.03.2011 by Oli B. (oliver.boehm@agentes.de)
 */

package gdv.xport.annotation;

import java.lang.annotation.*;

import gdv.xport.feld.Feld;

/**
 * Diese Annotation dient als Behaelter fuer einige Meta-Informationen wie
 * Byte-Adresse oder Datentyp.
 * 
 * @author oliver (oliver.boehm@agentes.de)
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

}

