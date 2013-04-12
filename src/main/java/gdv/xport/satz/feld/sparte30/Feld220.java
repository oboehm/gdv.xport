package gdv.xport.satz.feld.sparte30;

import gdv.xport.annotation.*;
import gdv.xport.feld.*;
import gdv.xport.satz.feld.common.Feld1bis7;

/**
 * Diese Enum-Klasse repraesentiert die Felder fuer Satzart 220, Sparte 30.
 * "Unfall - Wagnisdaten /-zusatz" (Satzart 0220)-
 *
 * @author rklemmer
 * @since 20.02.2013
 */
public enum Feld220 {

    // /// Teildatensatz 1 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 30,
            teildatensatz = 1,
            type = Feld1bis7.class)
    INTRO1,

    /**
     * Laufende Nummer der versicherten Person (VP) / Personengruppe.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 8,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE1,

    /**
     * Satznummer.<br/>
     * konstant 1
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 9,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 49)
    SATZNUMMER1,

    /**
     * Bezeichnung Personengruppe.<br/>
     * Bei namentlich nicht genannten Personen.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 50)
    BEZEICHNUNG_PERSONENGRUPPE,

    /**
     * Anzahl der VP pro Personengruppe.<br/>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 80)
    ANZAHL_VP_PRO_PERSONENGRUPPE,

    /**
     * Name der VP.<br/>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 86)
    NAME_VP,

    /**
     * Vorname der VP.<br/>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 116)
    VORNAME_VP,

    /**
     * Geburtsdatum der VP.<br/>
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 146)
    GEBURTSDATUM_VP,

    /**
     * Geschlecht der VP.<br/>
     * 0 = juristische Person, 1 = männlich, 2 = weiblich
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 15,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 154)
    GESCHLECHT_VP,

    /**
     * Berufsschlüssel.<br/>
     * Berufsschlüssel der Bundesagentur für Arbeit (Stelle 1-3)<br/>
     * Berufsschlüssel der Bundesanstalt für Arbeit<br/>
     * Kann bei der zuständigen Bundesagentur für Arbeit angefordert werden
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 16,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 155)
    BERUFSSCHLUESSEL,

    /**
     * Beruf - Text.<br/>
     * Klartext
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 158)
    BERUF_TEXT,

    /**
     * Gefahrengruppe.<br/>
     * A = Gefahrengruppe A, B = Gefahrengruppe B, E = Einheitsgefahren
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 18,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 188)
    GEFAHRENGRUPPE,

    /**
     * Tarif.<br/>
     * 1 = Einzelunfall<br/>
     * 2 = Gruppenunfall mit Namensnennung<br/>
     * 3 = Gruppenunfall ohne Namensnennung<br/>
     * 4 = Familienunfall<br/>
     * 5 = Kinderunfall<br/>
     * 6 = Stand. Familienunfall<br/>
     * 7 = Stand. Einzel-Unfall<br/>
     * 9 = sonstige
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 19,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 189)
    TARIF,

    /**
     * Deckungsumfang.<br/>
     * 1 = 24 Stunden-Deckung<br/>
     * 2 = nur Berufsunfall<br/>
     * 3 = Berufsunfall mit Wegeunfall<br/>
     * 4 = nur Wegeunfall<br/>
     * 5 = Freizeitunfall<br/>
     * 6 = Ehrenamtliche Tätigkeit<br/>
     * 9 = sonstige
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 20,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 190)
    DECKUNGSUMFANG,

    /**
     * Währungsschlüssel.<br/>
     * ISO-Code, siehe Anlage 3
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 191)
    WAEHRUNGSSCHLUESSEL,

    /**
     * Zuschlag in %.<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 22,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 194)
    ZUSCHLAG_IN_PROZENT,

    /**
     * Abschlag in %.<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 23,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 199)
    ABSCHLAG_IN_PROZENT,

    /**
     * Beitrag pro VP oder pro Personengruppe in Währungseinheiten.<br/>
     * gemäß Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 24,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 204)
    BEITRAG_PRO_VP_IN_WAEHRUNGSEINHEITEN,

    /**
     * Einschlussdatum VP / Personengruppe.<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt
     * werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 216)
    EINSCHLUSSDATUM_VP,

    /**
     * Ausschlussdatum VP / Personengruppe.<br/>
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt
     * werden<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 224)
    AUSSCHLUSSDATUM_VP,

    /**
     * Änderungsdatum.<br/>
     * Termin, zu dem der Vertragszustand wirksam wird/wurde (Gültig-ab- /
     * Wirksam-ab-Datum).<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 232)
    AENDERUNGSDATUM,

    /**
     * Besondere Vereinbarungen zum Fluggastrisiko.<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 28,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 240)
    BESONDERE_VEREINBARUNG_ZUM_FLUGGASTRISIKO,

    /**
     * passives Kriegsrisiko.<br/>
     * 0 = nein<br/>
     * 1 = Boden<br/>
     * 2 = Luft<br/>
     * 3 = Luft und Boden
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 29,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 241)
    PASSIVES_KRIEGSRISIKO,

    /**
     * Kündigungsklausel VP/ Personengruppe gestrichen.<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 30,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 242)
    KUENDIGUNGSKLAUSEL_VP,

    /**
     * Eintrittsalter der VP.<br/>
     * in Jahren
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 31,
            type = NumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 243)
    EINTRITTSALTER_DER_VP,

    /**
     * Altersgruppe.<br/>
     * 01 = Kinder<br/>
     * 02 = Erwachsene<br/>
     * 03 = Senioren<br/>
     * 99 = Sonstige
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 32,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 245)
    ALTERSGRUPPE,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 33,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 247)
    LEERSTELLEN,

    /**
     * Satznummernwiederholung.<br/>
     * Hier konstant
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 34,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 250)
    SATZNUMMERWIEDERHOLUNG1,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 35,
            type = AlphaNumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 251)
    LEERSTELLEN1,

    // /// Teildatensatz 2 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 30,
            teildatensatz = 2,
            type = Feld1bis7.class)
    INTRO2,

    /**
     * Laufende Nummer der versicherten Person (VP) / Personengruppe.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 8,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE2,

    /**
     * Satznummer.<br/>
     * konstant 2
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 9,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 49)
    SATZNUMMER2,

    /**
     * Tod.<br/>
     * Vers.-Summe in Währungseinheiten
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 10,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 50)
    TOD,

    /**
     * Tod-Beitragssatz.<br/>
     * pro Tausend Währungseinheiten<br/>
     * (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 11,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 59)
    TOD_BEITRAGSSATZ,

    /**
     * Invalidität.<br/>
     * Vers.-Summe in Währungseinheiten
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 12,
            type = NumFeld.class,
            nachkommaStellen = 0,
            anzahlBytes = 9,
            byteAdresse = 66)
    INVALIDITAET,

    /**
     * Kennung Gliedertaxe.<br/>
     * 0 = normal<br/>
     * 1 = erhöht<br/>
     * 2 = geändert
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 13,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 75)
    KENNUNG_GLIEDERTAXE,

    /**
     * Kennung progressive Invalidität.<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 14,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 76)
    KENNUNG_PROGRESSIVE_INVALIDITAET,

    /**
     * Invalidität-Beitragssatz.<br/>
     * pro Tausend Währungseinheiten<br/>
     * (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 15,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 77)
    INVALIDITAET_BEITRAGSSATZ,

    /**
     * Tagegeld 1.<br/>
     * Tagegeldsatz in Währungseinheiten<br/>
     * (6,1 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 16,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 7,
            byteAdresse = 84)
    TAGEGELD1,

    /**
     * Beginn Tagegeld 1 ab Tag.<br/>
     * Beginn der Tagegeldzahlung (TTT)<br/>
     * (6,1 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 17,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 91)
    BEGINN_TAGEGELD1_AB_TAG,

    /**
     * Tagegeld 1 Beitragssatz.<br/>
     * pro Währungseinheit (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 94)
    TAGEGELD_1_BEITRAGSSATZ,

    /**
     * Tagegeld 2.<br/>
     * Tagegeldsatz in Währungseinheiten<br/>
     * (6,1 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 19,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 7,
            byteAdresse = 101)
    TAGEGELD2,

    /**
     * Beginn Tagegeld 2 ab Tag.<br/>
     * Beginn der Tagegeldzahlung (TTT)<br/>
     * (6,1 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 20,
            type = NumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 108)
    BEGINN_TAGEGELD2_AB_TAG,

    /**
     * Tagegeld 2 Beitragssatz.<br/>
     * pro Währungseinheit (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 21,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 111)
    TAGEGELD2_BEITRAGSSATZ,

    /**
     * Krankenhaustagegeld.<br/>
     * Krankenhaustagegeldsatz in Währungseinheiten<br/>
     * (6,1 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 22,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 7,
            byteAdresse = 118)
    KRANKENHAUSTAGEGELD,

    /**
     * Krankenhaustagegeld Beitragssatz.<br/>
     * pro Währungseinheit (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 23,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 125)
    KRANKENHAUSTAGEGELD_BEITRAGSSATZ,

    /**
     * Genesungsgeld.<br/>
     * Genesungsgeld in Währungseinheiten<br/>
     * (6,1 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 24,
            type = NumFeld.class,
            nachkommaStellen = 1,
            anzahlBytes = 7,
            byteAdresse = 132)
    GENESUNGSGELD,

    /**
     * Genesungsgeld Beitragssatz.<br/>
     * pro Währungseinheit (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 25,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 139)
    GENESUNGSGELD_BEITRAGSSATZ,

    /**
     * Übergangsentschädigung.<br/>
     * Vers.-Summe in Währungseinheiten
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 26,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 146)
    UEBERGANGSENTSCHAEDIGUNG,

    /**
     * Kennung Übergangsentschädigung.<br/>
     * 1 = nach 6 Monaten<br/>
     * 2 = andere
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 27,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 155)
    KENNUNG_UEBERGANGSENTSCHAEDIGUNG,

    /**
     * Übergangsentschädigung Beitragssatz.<br/>
     * pro Hundert Währungseinheit (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 28,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 156)
    UEBERGANGSENTSCHAEDIGUNG_BEITRAGSSATZ,

    /**
     * Heilkosten.<br/>
     * Vers.-Summe in Währungseinheiten
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 29,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 163)
    HEILKOSTEN,

    /**
     * Art der Heilkosten.<br/>
     * 1 = Voll<br/>
     * 2 = Zusatz
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 30,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 172)
    ART_DER_HEILKOSTEN,

    /**
     * Heilkosten Beitragssatz.<br/>
     * pro Hundert Währungseinheit (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 31,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 173)
    HEILKOSTEN_BEITRAGSSATZ,

    /**
     * Feste Rente.<br/>
     * Vers.-Summe in Währungseinheiten
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 32,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 180)
    FESTE_RENTE,

    /**
     * Feste Rente Beitragssatz.<br/>
     * pro Hundert Währungseinheit (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 33,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 189)
    FESTE_RENTE_BEITRAGSSATZ,

    /**
     * Kosmetische Operationen.<br/>
     * Vers.-Summe in Währungseinheiten
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 34,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 196)
    KOSMETISCHE_OPERATIONEN,

    /**
     * Kosmetische Operationen Beitragssatz.<br/>
     * pro Hundert Währungseinheit (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 35,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 205)
    KOSMETISCHE_OPERATIONEN_BEITRAGSSATZ,

    /**
     * Kurkosten.<br/>
     * Vers.-Summe in Währungseinheiten
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 36,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 212)
    KURKOSTEN,

    /**
     * Kurkosten Beitragssatz.<br/>
     * pro Hundert Währungseinheit (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 37,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 221)
    KURKOSTEN_BEITRAGSSATZ,

    /**
     * Bergungskosten.<br/>
     * Vers.-Summe in Währungseinheiten
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 38,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 228)
    BERGUNGSKOSTEN,

    /**
     * Bergungskosten Beitragssatz.<br/>
     * pro Hundert Währungseinheit (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 39,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 237)
    BERGUNGSKOSTEN_BEITRAGSSATZ,

    /**
     * Überführungskosten.<br/>
     * im Todesfall<br/>
     * 0 = nein<br/>
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 40,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 244)
    UEBERFUEHRUNGSKOSTEN,

    /**
     * Rückführungskosten.<br/>
     * im Verletzungsfall<br/>
     * 0 = nein<br/>
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 41,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 245)
    RUECKFUEHRUNGSKOSTEN,

    /**
     * Prozentsatz progressive Invalidität.<br/>
     * (4,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 42,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 6,
            byteAdresse = 246)
    PROZENTSATZ_PROGRESSIVE_INVALIDITAET,

    /**
     * Mehrleistungsklausel.<br/>
     * 0 = nein<br/>
     * 1 = ja
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 43,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 252)
    MEHRLEISTUNGSKLAUSEL,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 44,
            type = AlphaNumFeld.class,
            anzahlBytes = 3,
            byteAdresse = 253)
    LEERSTELLEN2,

    /**
     * Zusätzliche Satzkennung.<br/>
     *
     * Konstant "X"; dieses Feld dient der eindeutigen Unterscheidung des
     * Teilsatzes 220.030/2 von den anderen Teilsätzen der Satzart 220.030.<br/>
     * <br/>
     * Da bei Gruppen-Unfallversicherungen mit lfd. Nummer der vers. Person die
     * Satznummer 3 (Satzart 220.030/3, Feld 8) mit der ersten Stelle der lfd.
     * Nummer der VP (Satzarten 220.1/1, 220.1/2 und 220.1/9, Feld 8)
     * verwechselt werden kann, wurde in allen Teilsätzen auf der Position 250
     * ein Feld Satznummernwiederholung eingeführt. Nicht jedoch im Teilsatz
     * 0220.030/2, da hier die Position schon belegt war.
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 45,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 256)
    ZUSAETZLICHE_SATZKENNUNG,

    // /// Teildatensatz 3 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 30,
            teildatensatz = 3,
            type = Feld1bis7.class)
    INTRO3,

    /**
     * Satznummer.<br/>
     * konstant 3
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 8,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 43)
    SATZNUMMER3,

    /**
     * VP-Personennummer des Versicherers.<br/>
     * Personennummer des Versicherers, rechtsbündig, mit Leerstellen
     * linksbündig auffüllen, ohne Sonderzeichen
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 9,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 44)
    VP_PERSONENNUMMER_VERSICHERER,

    /**
     * VP-Personennummer des Vermittlers.<br/>
     * Personennummer des Vermittlers, rechtsbündig, mit Leerstellen
     * linksbündig auffüllen, ohne Sonderzeichen
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 61)
    VP_PERSONENNUMMER_VERMITTLER,

    /**
     * Serviceleistungen.<br/>
     * Vers.-summe in Währungseinheiten
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 11,
            type = NumFeld.class,
            anzahlBytes = 9,
            byteAdresse = 78)
    SERVICELEISTUNGEN,

    /**
     * Serviceleistungen Beitragssatz.<br/>
     * pro Hundert Währungseinheiten<br/>
     * (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 12,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 87)
    SERVICELEISTUNGEN_BEITRAGSSATZ,

    /**
     * Beitrag Serviceleistungen in Währungseinheiten.<br/>
     * Beitrag Serviceleistungen gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 13,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 94)
    BEITRAG_SERVICELEISTUNGEN_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Tod in Währungseinheiten.<br/>
     * Beitrag Tod gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 14,
            type = Betrag.class,
            anzahlBytes = 12,
            byteAdresse = 106)
    BEITRAG_TOD_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Invalidität in Währungseinheiten.<br/>
     * Beitrag Invalidität gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 15,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 118)
    BEITRAG_INVALIDITAET_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Tagegeld 1 in Währungseinheiten.<br/>
     * Beitrag Tagegeld 1 gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 16,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 130)
    BEITRAG_TAGEGELD1_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Tagegeld 2 in Währungseinheiten.<br/>
     * Beitrag Tagegeld 2 gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 17,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 142)
    BEITRAG_TAGEGELD2_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Krankenhaustagegeld in Währungseinheiten.<br/>
     * Beitrag Krankenhaustagegeld gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 18,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 154)
    BEITRAG_KRANKENHAUSTAGEGELD_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Genesungsgeld in Währungseinheiten.<br/>
     * Beitrag Genesungsgeld gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 19,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 166)
    BEITRAG_GENESUNGSGELD_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Übergangsentschädigung in Währungseinheiten.<br/>
     * Beitrag Übergangsentschädigung gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 20,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 178)
    BEITRAG_UEBERGANGSENTSCHAEDIGUNG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Heilkosten in Währungseinheiten.<br/>
     * Beitrag Heilkosten gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 21,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 190)
    BEITRAG_HEILKOSTEN_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Feste Rente in Währungseinheiten.<br/>
     * Beitrag Feste Rente gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 22,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 202)
    BEITRAG_FESTE_RENTE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Kosmetische Operation in Währungseinheiten.<br/>
     * Beitrag Kosmetische Operation gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 23,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 214)
    BEITRAG_KOSMETISCHE_OPERATION_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Kurkosten in Währungseinheiten-<br/>
     * Beitrag Kurkosten gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 24,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 226)
    BEITRAG_KURKOSTEN_IN_WAEHRUNGSEINHEITEN,

    /**
     * Beitrag Bergungskosten in Währungseinheiten-<br/>
     * Beitrag Bergungskosten gem. Zahlungsweise<br/>
     * (10,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 25,
            type = Betrag.class,
            nachkommaStellen = 2,
            anzahlBytes = 12,
            byteAdresse = 238)
    BEITRAG_BERGUNGSKOSTEN_IN_WAEHRUNGSEINHEITEN,

    /**
     * Satznummernwiederholung.<br/>
     * Hier konstant.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 26,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 250)
    SATZNUMMERWIEDERHOLUNG2,

    /**
     * Laufende Nummer der versicherten Person (VP) / Personengruppe.
     */
    @FeldInfo(
            teildatensatz = 3,
            nr = 27,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 251)
    LFD_NUMMER_VP_PERSONENGRUPPE3,

    // /// Teildatensatz 4 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 30,
            teildatensatz = 4,
            type = Feld1bis7.class)
    INTRO4,

    /**
     * Laufende Nummer der versicherten Person (VP) / Personengruppe.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 8,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE4,

    /**
     * Satznummer.<br/>
     * konstant 4
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 9,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 49)
    SATZNUMMER4,

    /**
     * Beitragszahlung bis.<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 10,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 50)
    BEITRAGSZAHLUNG_BIS,

    /**
     * Rückgewährdatum.<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 58)
    RUECKGEWAEHRDATUM,

    /**
     * Rückgewährsumme zum Ablauf in Währungseinheiten.<br/>
     * Vertraglich vereinbarte Rückgewährsumme zum Ablauf des Vertrages
     * (aufgelaufene Beiträge)<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 12,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 66)
    RUECKGEWAEHRSUMME_ZUM_ABLAUF_IN_WAEHRUNGSEINHEITEN,

    /**
     * Ablaufleistung inklusiv Überschussanteile in Währungseinheiten.<br/>
     * nach Überschußanrechnung erreichte Ablaufleistung<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 13,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 80)
    ABLAUFLEISTUNG_INKL_UEBERSCHUSSANTEILE_IN_WAEHRUNGSEINHEITEN,

    /**
     * Kapitalertragssteuer bei Ablauf.<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 14,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 94)
    KAPITALERTRAGSSTEUER_BEI_ABLAUF,

    /**
     * Solidaritätszuschlag bei Ablauf.<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 15,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 108)
    SOLIDARITAETSZUSCHLAG_BEI_ABLAUF,

    /**
     * Rückkaufswert zum Berechnugsstichtag in Währungseinheiten.<br/>
     * kumuliert, incl. aller Dynamiken<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 16,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 122)
    RUECKKAUFSWERT_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Berechnungsstichtag zum Rückkaufswert.<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 17,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 136)
    BERECHNUNGSSTICHTAG_ZUM_RUECKKAUFSWERT,

    /**
     * Kapitalertragssteuer bei Rückkauf zum Berechnungsstichtag.<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 18,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 144)
    KAPITALERTRAGSSTEUER_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG,

    /**
     * Solidaritätszuschlag bei Rückkauf zum Berechnungsstichtag.<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 19,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 158)
    SOLIDARITAETSZUSCHLAG_BEI_RUECKKAUF_ZUM_BERECHNUNGSSTICHTAG,

    /**
     * Überschussanteile zum Berechnungsstichtag in Währungseinheiten.<br/>
     * (12,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 20,
            type = Betrag.class,
            anzahlBytes = 14,
            byteAdresse = 172)
    UEBERSCHUSSANTEILE_ZUM_BERECHNUNGSSTICHTAG_IN_WAEHRUNGSEINHEITEN,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 21,
            type = AlphaNumFeld.class,
            anzahlBytes = 64,
            byteAdresse = 186)
    LEERSTELLEN4,

    /**
     * Satznummernwiederholung.<br/>
     * Hier konstant
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 22,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 250)
    SATZNUMMERWIEDERHOLUNG4,

    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 23,
            type = AlphaNumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 251)
    LEERSTELLEN5,

    // /// Teildatensatz 9 /////////////////////////////////////////////////

    /** Feld 1 - 7 sind fuer jeden (Teil-)Datensatz identisch. */
    @FelderInfo(
            sparte = 30,
            teildatensatz = 9,
            type = Feld1bis7.class)
    INTRO9,

    /**
     * Laufende Nummer der versicherten Person (VP) / Personengruppe laufende
     * Nummer, die im VU geführt wird.<br/>
     * (Das Feld ist durch einen Kopierfehler entstanden. Wenn hier die lfd.
     * Nummer befüllt wird, ist diese im Feld
     * "Lfd. Nummer der versicherten Person (VP) / Personengruppe" auf der
     * Byteadresse 251 zu wiederholen.)
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 8,
            type = AlphaNumFeld.class,
            anzahlBytes = 17,
            byteAdresse = 43)
    LFD_NUMMER_VP_PERSONENGRUPPE9,

    /**
     * Satznummer.<br/>
     * konstant 9
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 9,
            type = NumFeld.class,
            anzahlBytes = 1,
            byteAdresse = 60)
    SATZNUMMER9,

    /**
     * Satznummer.<br/>
     * Lfd. Nummer der Satzart 0220.030/9 innerhalb der gleichen Folgenummer (z.
     * B. n-fache hintereinanderfolgende Lieferung der Satzart 0220.030/9, wenn
     * mehrere Bezugsrechte vorhanden)
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 10,
            type = AlphaNumFeld.class,
            anzahlBytes = 2,
            byteAdresse = 61)
    LFD_NUMMER_SATZART,

    /**
     * Bezugsberechtigt im Leistungsfall.<br/>
     * 1 = Versicherungsnehmer<br/>
     * 2 = Versicherte Person<br/>
     * 9 = Sonstiger Bezugsberechtigter<br/>
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 11,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 63)
    BEZUGSBERECHTIGT_IM_LEISTUNGSFALL,

    /**
     * Sonstiger Bezugsberechtigter im Leistungsfall.<br/>
     * Klartext (z. B. Name, Vorname)
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 64)
    SONSTIGER_BEZUGSBERECHTIGTER_IM_LEISTUNGSFALL,

    /**
     * Bezugsrechtanteil im Leistungsfall.<br/>
     * in Prozent<br/>
     * (3,2 Stellen)
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 13,
            type = NumFeld.class,
            nachkommaStellen = 2,
            anzahlBytes = 5,
            byteAdresse = 94)
    BEZUGSRECHTANTEIL_IM_LEISTUNGSFALL,

    /**
     * Unwiderrufliches Bezugsrecht im Leistungsfall.<br/>
     * 0 = nein, 1 = ja
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 14,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 99)
    UNWIDERRUFLICHES_BEZUGSRECHT_IM_LEISTUNGSFALL,

    /**
     * Referenznummer.<br/>
     *
     * Wenn in einem gebündelten Vertrag (Bündelungskennzeichen = 1) mehrere
     * gleiche Sparten unter der selben Versicherungsscheinnummer gebündelt
     * werden, müssen diese im Feld Referenznummer unterschieden werden.
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 15,
            type = AlphaNumFeld.class,
            anzahlBytes = 7,
            byteAdresse = 100)
    REFERENZNUMMER,

    /**
     * Geburtsdatum der bezugsberechtigten Person.<br/>
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 16,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 107)
    GEBURTSDATUM_DER_BEZUGSBERECHTIGTEN_PERSON,


    /**
     * Leerstellen. Freie Stellen fuer weitere Belegung.
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 17,
            type = AlphaNumFeld.class,
            anzahlBytes = 135,
            byteAdresse = 115)
    LEERSTELLEN3,

    /**
     * Satznummernwiederholung.<br/>
     * Hier konstant
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 18,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 250)
    SATZNUMMERWIEDERHOLUNG9,

    /**
     * Laufende Nummer der versicherten Person (VP) / Personengruppe.
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 19,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 251)
    LFD_NUMMER_VP,

}
