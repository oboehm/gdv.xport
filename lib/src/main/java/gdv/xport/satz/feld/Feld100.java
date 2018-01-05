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
 * (c)reated 08.03.2011 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz.feld;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;


/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 100
 * (Adressteil).
 *
 * @author oliver (ob@aosd.de)
 * @since 0.6 (08.03.2011)
 */
public enum Feld100 {

    /////   Teildatensatz 1   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 1,
            type = Feld1bis7.class
    )
    INTRO1,

    /**
     * Anredeschluessel.
     * Hinweis fuer das eVB-Verfahren (elektronische Versicherungsbestaetigung):
     * Ist als VN eine Vereinigung gewaehlt, so muss zusaetzlich eine
     * natuerliche Person benannt werden.
     * <pre>
     * 0 = (ohne Anrede)
     * 1 = Herr
     * 2 = Frau
     * 3 = Firma
     * 4 = Herr und Frau
     * 5 = Fraeulein
     * 6 = Vereinigung
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 43
    )
    ANREDESCHLUESSEL,

    /**
     * Hinweis fuer das eVB-Verfahren (elektronische Versicherungsbestaetigung).
     * Im eVB-Verfahren ist der Vorname im Feld "Name 3" und der Nachname im
     * Feld "Name 1" sinnvoll zu fuellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 44
    )
    NAME1,

    /**
     * Name2.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 74
    )
    NAME2,

    /**
     * Name 3: Bei natuerlichen Personen und wenn im Bestand
     * gespeichert, muss in Name 3 der Vorname angegeben werden.
     * Hinweis fuer das eVB-Verfahren (elektronische Versicherungsbestaetigung):
     * Im eVB-Verfahren ist der Vorname im Feld "Name 3" und der Nachname im
     * Feld "Name 1" sinnvoll zu fuellen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 104
    )
    NAME3,

    /**
     * Titel.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 134
    )
    TITEL,

    /**
     * KFZ-Laenderkennzeichen, zum Beispiel Laenderkennzeichen fuer
     * D = Deutschland, B = Belgien, DK = Daenemark, F = Frankreich,
     * CDN = Kanada.
     * Hinweis fuer das eVB-Verfahren (elektronische Versicherungsbestaetigung):
     * Im eVB-Verfahren ist das Feld "KFZ-Laenderkennzeichen" ein Pflichtfeld.
     * Des Weiteren muss die Adresse vollstaendig geliefert werden.
     * siehe Anlage 63
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 154
    )
    LAENDERKENNZEICHEN,

    /**
     * Postleitzahl linksbuendig.
     * Hinweis fuer das eVB-Verfahren (elektronische Versicherungsbestaetigung):
     * Die Adresse muss im eVB-Verfahren vollstaendig ergaenzt um das
     * Laenderkennzeichen geliefert werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 157
    )
    POSTLEITZAHL,

    /**
     * Ort.
     * Hinweis fuer das eVB-Verfahren (elektronische Versicherungsbestaetigung):
     * Die Adresse muss im eVB-Verfahren vollstaendig ergaenzt um das
     * Laenderkennzeichen geliefert werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 25,
            byteAdresse = 163
    )
    ORT,

    /**
     * Strasse.
     * Hinweis fuer das eVB-Verfahren (elektronische Versicherungsbestaetigung):
     * Die Adresse muss im eVB-Verfahren vollstaendig ergaenzt um das
     * Laenderkennzeichen geliefert werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 188
    )
    STRASSE,

    /**
     * Postfach.
     * Hinweis fuer das eVB-Verfahren (elektronische Versicherungsbestaetigung):
     * Die Adresse muss im eVB-Verfahren vollstaendig ergaenzt um das
     * Laenderkennzeichen geliefert werden.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 8,
            byteAdresse = 218
    )
    POSTFACH,

    /**
     * Geburtsdatum: Sollten Tag und/oder Monat nicht vorhanden sein, muss "00"
     * geschluesselt werden.
     * Rechtschutz: Gruendungsdatum der Firma, Tag/Monat/Jahr (TTMMJJJJ).
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 226
    )
    GEBURTSDAT,

    /**
     * Verwendung Schluessel Laenderkennzeichen, siehe Anlage 63.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 234
    )
    STAATSANGEHOERIGKEIT,

    /**
     * Siehe Anlage 7.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 237
    )
    ADRESSKENNZEICHEN,

    /**
     * Aktenzeichen des Sicherungsglaeubigers.
     * Bei Kredit "Versicherungsschein-Unternummer".
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 239
    )
    AKTENZEICHEN_SICHERUNGSGLAEUBIGER,

    /**
     * Siehe Anlage 18.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 251
    )
    ZIELGRUPPENSCHLUESSEL,

    /**
     * Nach EG-Richtlinien 01-07-1990 0 = nein, 1 = ja.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 253
    )
    GROSSRISIKEN,

    /**
     * Postalisches Kennzeichen (O = fuer die neuen Bundeslaender).
     * Dieses Feld wird nicht mehr verwendet!
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 254
    )
    POSTALISCHES_KENNZEICHEN,

    /**
     * Hinweis fuer das eVB-Verfahren (elektronische Versicherungsbestaetigung):
     * Im eVB-Verfahren ist das Feld "Geschlecht" ein Pflichtfeld.
     * <pre>
     * 0 = juristische Person,
     * 1 = maennlich,
     * 2 = weiblich.
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 255
    )
    GESCHLECHT,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER1,

    /////   Teildatensatz 2   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 2,
            type = Feld1bis7.class
    )
    INTRO2,

    /**
     * Personen-/Kundennummer des Versicherers, rechtsbuendig.
     * Mit Leerstellen linksbuendig auffuellen, ohne Sonderzeichen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = AlphaNumFeld.class,
            align = Align.RIGHT,
            anzahlBytes = 17,
            byteAdresse = 43
    )
    KUNDENNR_VERSICHERER,

    /**
     * Personen-/Kundennummer des Vermittlers, rechtsbuendig.
     * Mit Leerstellen linksbuendig auffuellen, ohne Sonderzeichen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = AlphaNumFeld.class,
            align = Align.RIGHT,
            anzahlBytes = 17,
            byteAdresse = 60
    )
    KUNDENNR_VERMITTLER,

    /**
     * Kundengruppe im Klartext.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 77
    )
    KUNDENGRUPPE,

    /**
     * Kontonummer fuer das jeweilige Adresskennzeichen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 107
    )
    KONTONR1,

    /**
     * Siehe Kontonummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 8,
            byteAdresse = 119
    )
    BLZ1,

    /**
     * Gegebenenfalls Name, falls abweichend.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 127
    )
    ABWEICHENDER_KONTOINHABER1,

    /**
     * Kommunikationstyp.
     * Weitere, groessere Felder: "Kommunikationsnummern" (z. B. E-Mail) stehen
     * in 0100.1/3 zur Verfuegung. Siehe Anlage 76.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 157
    )
    KOMMUNIKATIONSTYP1,

    /**
     * Wenn moeglich, Vorwahl(en) und Rufnummer durch Leerzeichen (blank) getrennt.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 159
    )
    KOMMUNIKATIONSNR1,

    /**
     * Siehe Anlage 76.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 179
    )
    KOMMUNIKATIONSTYP2,

    /**
     * Siehe Kommunikationsnummer 1.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 181
    )
    KOMMUNIKATIONSNR2,

    /**
     * Siehe Anlage 76.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 201
    )
    KOMMUNIKATIONSTYP3,

    /**
     * Siehe Kommunikationsnummer 1.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 203
    )
    KOMMUNIKATIONSNR3,

    /**
     * Siehe Anlage 76.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 223
    )
    KOMMUNIKATIONSTYP4,

    /**
     * Siehe Kommunikationsnummer 1.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 20,
            byteAdresse = 225
    )
    KOMMUNIKATIONSNR4,

    /**
     * Zahlungsart.
     * <pre>
     * 1 = Lastschrift,
     * 2 = Rechnung,
     * 3 = Dauerauftrag,
     * 4 = Depot,
     * 5 = Gehaltsabzug,
     * 9 = sonstige.
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 22,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 245
    )
    ZAHLUNGSART,

    /**
     * Familienstand.
     * <pre>
     * 1 = ledig,
     * 2 = verheiratet,
     * 3 = geschieden,
     * 4 = verwitwet,
     * 5 = getrennt lebend,
     * 9 = sonstiges.
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 23,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 246
    )
    FAMILIENSTAND,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 24,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 247
    )
    LEERSTELLEN,

    /**
     * Fortlaufende Nummer der Person im GeVo beginnend mit 1
     * (wird nur Verfahren "Antragsdaten" verwendet und bleibt ansonsten leer).
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 25,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 250
    )
    LFD_PERSONENNR_GEVO,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 26,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER2,

    /////   Teildatensatz 3   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 3,
            type = Feld1bis7.class
    )
    INTRO3,

    /**
     * Siehe Anlage 76.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 43
    )
    KOMMUNIKATIONSTYP5,

    /**
     * Siehe Kommunikationsnummer 1.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 60,
            byteAdresse = 45
    )
    KOMMUNIKATIONSNR5,

    /**
     * Siehe Anlage 76.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 105
    )
    KOMMUNIKATIONSTYP6,

    /**
     * Siehe Kommunikationsnummer 1.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 60,
            byteAdresse = 107
    )
    KOMMUNIKATIONSNR6,

    /**
     * Siehe Anlage 76.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 167
    )
    KOMMUNIKATIONSTYP7,

    /**
     * Siehe Kommunikationsnummer 1.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 60,
            byteAdresse = 169
    )
    KOMMUNIKATIONSNR7,

    /**
     * Rechtsform.
     * <pre>
     * 01 = natuerliche Person,
     * 02 = Gesellschaft des Buergerlichen Rechts GdBR,
     * 03 = OHG,
     * 04 = KG,
     * 05 = GmbH,
     * 06 = gGmbH,
     * 07 = GmbH &amp; Co KG,
     * 08 = KGaA,
     * 09 = AG,
     * 10 = eV,
     * 11 = Verein nach BGB,
     * 12 = eG,
     * 13 = Gesellschaft des oeffentlichen Rechts,
     * 99 = Sonstiges.
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 229
    )
    RECHTSFORM,


    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 25,
            byteAdresse = 231
    )
    LEERSTELLEN3,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 16,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER3,

    /////   Teildatensatz 4   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 4,
            type = Feld1bis7.class
    )
    INTRO4,

    /**
     * Geburtsname.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 43
    )
    GEBURTSNAME,

    /**
     * Name des Kreditinstituts.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 73
    )
    NAME_KREDITINSTITUT1,

    /**
     * Typ der Bankverbindung 1.
     * <pre>
     * 01 = Praemienzahlungskonto,
     * 02 = Schadenzahlungskonto,
     * 03 = ohne Spezifikation.
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 103
    )
    TYP_BANKVERBINDUNG1,

    /**
     * Typ der Bankverbindung 1.
     * <pre>
     * Siehe Feld 10: Typ der Bankverbindung.
     * 01 = Praemienzahlungskonto,
     * 02 = Schadenzahlungskonto,
     * 03 = ohne Spezifikation.
     * </pre>
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 105
    )
    TYP_BANKVERBINDUNG2,

    /**
     * Kontonummer fuer das jeweilige Adresskennzeichen.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 12,
            byteAdresse = 107
    )
    KONTONR2,

    /**
     * Siehe Kontonummer.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 8,
            byteAdresse = 119
    )
    BLZ2,

    /**
     * Gegebenfalls Name, falls abweichend.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 127
    )
    ABWEICHENDER_KONTOINHABER2,

    /**
     * Name des Kreditinstituts.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 157
    )
    NAME_KREDITINSTITUT2,

    /**
     * Internationaler SWIFT Bankcode.
     * Der BIC oder SWIFT-Code hat folgenden Aufbau:
     * BBBB 4-stelliger Bankcode, vom Geldinstitut frei waehlbar (nur Alphazeichen)
     * CC 2-stelliger Laendercode nach ISO 3166-1 (nur Alphazeichen)
     * LL 2-stellige Codierung des Ortes (alphanumerische Zeichen;
     * zweites Zeichen = 1: passiver SWIFT-Teilnehmer)
     * bbb 3-stellige Kennzeichnung der Filiale oder Abteilung (optional,
     * Standard: "XXX", kann weggelassen werden, andere Kennzeichen nicht)
     * (alphanumerische Zeichen)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 11,
            byteAdresse = 187
    )
    BIC1,

    /**
     * Internationaler SWIFT Bankcode.
     * Der BIC oder SWIFT-Code hat folgenden Aufbau:
     * BBBB 4-stelliger Bankcode, vom Geldinstitut frei waehlbar (nur Alphazeichen)
     * CC 2-stelliger Laendercode nach ISO 3166-1 (nur Alphazeichen)
     * LL 2-stellige Codierung des Ortes (alphanumerische Zeichen;
     * zweites Zeichen = 1: passiver SWIFT-Teilnehmer)
     * bbb 3-stellige Kennzeichnung der Filiale oder Abteilung (optional,
     * Standard: "XXX", kann weggelassen werden, andere Kennzeichen nicht)
     * (alphanumerische Zeichen)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 11,
            byteAdresse = 198
    )
    BIC2,

    /**
     * Internationale Kontonummer.
     * Die IBAN setzt sich folgendermassen zusammen:
     * 2-stelliger Laendercode gemaess ISO 3166-1 (Buchstaben)
     * 2-stellige Pruefziffer gemaess ISO 7064 (Ziffern)
     * max. 30-stellige Kontoidentifikation (Buchstaben und Ziffern)
     * Die IBAN kann somit maximal 34 Stellen umfassen. Zur besseren Lesbarkeit
     * wird die IBAN in Vierergruppen eingeteilt. Eine deutsche IBAN hat z.B.
     * immer 22 Stellen, kuerzere Kontonummern werden mit fuehrenden Nullen auf
     * 10 Stellen erweitert.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 34,
            byteAdresse = 209
    )
    IBAN1,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 19,
            type = AlphaNumFeld.class,
            anzahlBytes = 13,
            byteAdresse = 243
    )
    LEERSTELLEN4,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 20,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER4,

    /////   Teildatensatz 5   /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            teildatensatz = 5,
            type = Feld1bis7.class
    )
    INTRO5,

    /**
     * Internationale Kontonummer.
     * Die IBAN setzt sich folgendermassen zusammen:
     * 2-stelliger Laendercode gemaess ISO 3166-1 (Buchstaben)
     * 2-stellige Pruefziffer gemaess ISO 7064 (Ziffern)
     * max. 30-stellige Kontoidentifikation (Buchstaben und Ziffern)
     * Die IBAN kann somit maximal 34 Stellen umfassen. Zur besseren Lesbarkeit
     * wird die IBAN in Vierergruppen eingeteilt. Eine deutsche IBAN hat z.B.
     * immer 22 Stellen, kuerzere Kontonummern werden mit fuehrenden Nullen auf
     * 10 Stellen erweitert.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 34,
            byteAdresse = 43
    )
    IBAN2,

    /**
     * Geburtsort.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 77
    )
    GEBURTSORT,

    /**
     * Siehe Anlage 63.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 107
    )
    GEBURTSLAND,

    /**
     * Steuernummer bei juristischen Personen.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 11,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 110
    )
    STEUERNR_JURISTISCHE_PERSON,

    /**
     * Umsatzsteuer-Identifikationsnummer.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 127
    )
    UMSATZSTEUER_ID,

    /**
     * Vorsteuerabszugsberechtigung Ja/Nein.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 13,
            type = Zeichen.class,
            byteAdresse = 144
    )
    VORZUGSSTEUERBERECHTIGUNG,

    /**
     * Vorsteuerabszugsberechtigung in Prozent.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 14,
            type = AlphaNumFeld.class,
            anzahlBytes = 5,
            byteAdresse = 145
    )
    VORZUGSSEUERBERECHTIGUNG_PROZENT,

    // die folgenden Felder sind mit v2013 hinzugekommen

    /**
     * Sparte
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 15,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 150
    )
    SPARTE2,

    /**
     * Referenznummer
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 16,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 153
    )
    REFERENZNUMMER,

    /**
     * SEPA-Glaeubigeridentifikationsnummer
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 35,
            byteAdresse = 160
    )
    SEPA_GLAEUBIGERIDENTIFIKATIONSNUMMER,

    /**
     * SEPA-Mandat Referenznummer
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 18,
            type = AlphaNumFeld.class,
            anzahlBytes = 35,
            byteAdresse = 195
    )
    SEPA_MANDAT_REFERENZNUMMER,

    /**
     * SEPA-Mandat Ausstellungsdatum
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 19,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 230
    )
    SEPA_MANDAT_AUSSTELLUNGSDATUM,

    /**
     * SEPA-Mandat Kontoreferenz.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 20,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 238
    )
    SEPA_MANDAT_KONTOREFERENZ,

    /**
     * SEPA-Mandat einmalig/wiederkehrend.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 239
    )
    SEPA_MANDAT_EINMALIG_WIEDERKEHREND,

    /**
     * SEPA-Mandat Basis-/Firmenlastschrift.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 22,
            type = AlphaNumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 240
    )
    SEPA_MANDAT_BASIS_FIRMENLASTSCHRIFT,

    /**
     * SEPA-Mandat Basis-/Firmenlastschrift.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 23,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 241
    )
    DATUM_SEPA,

    /**
     * Leerstellen.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 249
    )
    LEERSTELLEN5,

    /**
     * Satznummer.
     */
    @FeldInfo(
            teildatensatz = 5,
            nr = 22,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256
    )
    SATZNUMMER5;

}
