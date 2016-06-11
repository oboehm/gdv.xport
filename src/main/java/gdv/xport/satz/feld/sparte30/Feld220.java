package gdv.xport.satz.feld.sparte30;

import gdv.xport.annotation.FeldInfo;
import gdv.xport.annotation.FelderInfo;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;
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
     * Satznummer.
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
     * Bezeichnung Personengruppe.
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
     * Anzahl der VP pro Personengruppe.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 11,
            type = NumFeld.class,
            anzahlBytes = 6,
            byteAdresse = 80)
    ANZAHL_VP_PRO_PERSONENGRUPPE,

    /**
     * Name der VP.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 12,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 86)
    NAME_VP,

    /**
     * Vorname der VP.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 13,
            type = AlphaNumFeld.class,
            anzahlBytes = 30,
            byteAdresse = 116)
    VORNAME_VP,

    /**
     * Geburtsdatum der VP.
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 14,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 146)
    GEBURTSDAT_VP,

    /**
     * Geschlecht der VP.
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
     * Berufsschlüssel.
     * Berufsschlüssel der Bundesagentur für Arbeit (Stelle 1-3)
     * Berufsschlüssel der Bundesanstalt für Arbeit
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
     * Beruf - Text.
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
     * Gefahrengruppe.
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
     * Tarif.
     * 1 = Einzelunfall
     * 2 = Gruppenunfall mit Namensnennung
     * 3 = Gruppenunfall ohne Namensnennung
     * 4 = Familienunfall
     * 5 = Kinderunfall
     * 6 = Stand. Familienunfall
     * 7 = Stand. Einzel-Unfall
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
     * Deckungsumfang.
     * 1 = 24 Stunden-Deckung
     * 2 = nur Berufsunfall
     * 3 = Berufsunfall mit Wegeunfall
     * 4 = nur Wegeunfall
     * 5 = Freizeitunfall
     * 6 = Ehrenamtliche Tätigkeit
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
     * Währungsschlüssel.
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
     * Zuschlag in %.
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
     * Abschlag in %.
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
     * Beitrag pro VP oder pro Personengruppe in Währungseinheiten.
     * gemäß Zahlungsweise
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
     * Einschlussdatum VP / Personengruppe.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt
     * werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 25,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 216)
    EINSCHLUSSDAT_VP_PERSONENGRUPPE,

    /**
     * Ausschlussdatum VP / Personengruppe.
     * Sollten Tag und/oder Monat nicht vorhanden sein, muss "00" geschlüsselt
     * werden
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 26,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 224)
    AUSSCHLUSSDAT_VP_PERSONENGRUPPE,

    /**
     * Änderungsdatum.
     * Termin, zu dem der Vertragszustand wirksam wird/wurde (Gültig-ab- /
     * Wirksam-ab-Datum).
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 1,
            nr = 27,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 232)
    AENDERUNGSDAT,

    /**
     * Besondere Vereinbarungen zum Fluggastrisiko.
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
     * passives Kriegsrisiko.
     * 0 = nein
     * 1 = Boden
     * 2 = Luft
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
     * Kündigungsklausel VP/ Personengruppe gestrichen.
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
     * Eintrittsalter der VP.
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
     * Altersgruppe.
     * 01 = Kinder
     * 02 = Erwachsene
     * 03 = Senioren
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
     * Satznummernwiederholung.
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
     * Satznummer.
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
     * Tod.
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
     * Tod-Beitragssatz.
     * pro Tausend Währungseinheiten
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
     * Invalidität.
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
     * Kennung Gliedertaxe.
     * 0 = normal
     * 1 = erhöht
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
     * Kennung progressive Invalidität.
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
     * Invalidität-Beitragssatz.
     * pro Tausend Währungseinheiten
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
     * Tagegeld 1.
     * Tagegeldsatz in Währungseinheiten
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
     * Beginn Tagegeld 1 ab Tag.
     * Beginn der Tagegeldzahlung (TTT)
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
     * Tagegeld 1 Beitragssatz.
     * pro Währungseinheit (2,5 Stellen)
     */
    @FeldInfo(
            teildatensatz = 2,
            nr = 18,
            type = NumFeld.class,
            nachkommaStellen = 5,
            anzahlBytes = 7,
            byteAdresse = 94)
    TAGEGELD1_BEITRAGSSATZ,

    /**
     * Tagegeld 2.
     * Tagegeldsatz in Währungseinheiten
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
     * Beginn Tagegeld 2 ab Tag.
     * Beginn der Tagegeldzahlung (TTT)
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
     * Tagegeld 2 Beitragssatz.
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
     * Krankenhaustagegeld.
     * Krankenhaustagegeldsatz in Währungseinheiten
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
     * Krankenhaustagegeld Beitragssatz.
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
     * Genesungsgeld.
     * Genesungsgeld in Währungseinheiten
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
     * Genesungsgeld Beitragssatz.
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
     * Übergangsentschädigung.
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
     * Kennung Übergangsentschädigung.
     * 1 = nach 6 Monaten
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
     * Übergangsentschädigung Beitragssatz.
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
     * Heilkosten.
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
     * Art der Heilkosten.
     * 1 = Voll
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
     * Heilkosten Beitragssatz.
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
     * Feste Rente.
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
     * Feste Rente Beitragssatz.
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
     * Kosmetische Operationen.
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
     * Kosmetische Operationen Beitragssatz.
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
     * Kurkosten.
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
     * Kurkosten Beitragssatz.
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
     * Bergungskosten.
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
     * Bergungskosten Beitragssatz.
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
     * Überführungskosten.
     * im Todesfall
     * 0 = nein
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
     * Rückführungskosten.
     * im Verletzungsfall
     * 0 = nein
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
     * Prozentsatz progressive Invalidität.
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
     * Mehrleistungsklausel.
     * 0 = nein
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
     * Zusätzliche Satzkennung.
     *
     * Konstant "X"; dieses Feld dient der eindeutigen Unterscheidung des
     * Teilsatzes 220.030/2 von den anderen Teilsätzen der Satzart 220.030.
     * 
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
     * Satznummer.
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
     * VP-Personennummer des Versicherers.
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
     * VP-Personennummer des Vermittlers.
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
     * Serviceleistungen.
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
     * Serviceleistungen Beitragssatz.
     * pro Hundert Währungseinheiten
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
     * Beitrag Serviceleistungen in Währungseinheiten.
     * Beitrag Serviceleistungen gem. Zahlungsweise
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
     * Beitrag Tod in Währungseinheiten.
     * Beitrag Tod gem. Zahlungsweise
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
     * Beitrag Invalidität in Währungseinheiten.
     * Beitrag Invalidität gem. Zahlungsweise
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
     * Beitrag Tagegeld 1 in Währungseinheiten.
     * Beitrag Tagegeld 1 gem. Zahlungsweise
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
     * Beitrag Tagegeld 2 in Währungseinheiten.
     * Beitrag Tagegeld 2 gem. Zahlungsweise
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
     * Beitrag Krankenhaustagegeld in Währungseinheiten.
     * Beitrag Krankenhaustagegeld gem. Zahlungsweise
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
     * Beitrag Genesungsgeld in Währungseinheiten.
     * Beitrag Genesungsgeld gem. Zahlungsweise
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
     * Beitrag Übergangsentschädigung in Währungseinheiten.
     * Beitrag Übergangsentschädigung gem. Zahlungsweise
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
     * Beitrag Heilkosten in Währungseinheiten.
     * Beitrag Heilkosten gem. Zahlungsweise
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
     * Beitrag Feste Rente in Währungseinheiten.
     * Beitrag Feste Rente gem. Zahlungsweise
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
     * Beitrag Kosmetische Operation in Währungseinheiten.
     * Beitrag Kosmetische Operation gem. Zahlungsweise
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
     * Beitrag Kurkosten in Währungseinheiten-
     * Beitrag Kurkosten gem. Zahlungsweise
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
     * Beitrag Bergungskosten in Währungseinheiten-
     * Beitrag Bergungskosten gem. Zahlungsweise
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
     * Satznummernwiederholung.
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
     * Satznummer.
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
     * Beitragszahlung bis.
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
     * Rückgewährdatum.
     * Tag/Monat/Jahr (TTMMJJJJ)
     */
    @FeldInfo(
            teildatensatz = 4,
            nr = 11,
            type = Datum.class,
            anzahlBytes = 8,
            byteAdresse = 58)
    RUECKGEWAEHRDAT,

    /**
     * Rückgewährsumme zum Ablauf in Währungseinheiten.
     * Vertraglich vereinbarte Rückgewährsumme zum Ablauf des Vertrages
     * (aufgelaufene Beiträge)
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
     * Ablaufleistung inklusiv Überschussanteile in Währungseinheiten.
     * nach Überschußanrechnung erreichte Ablaufleistung
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
     * Kapitalertragssteuer bei Ablauf.
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
     * Solidaritätszuschlag bei Ablauf.
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
     * Rückkaufswert zum Berechnugsstichtag in Währungseinheiten.
     * kumuliert, incl. aller Dynamiken
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
     * Berechnungsstichtag zum Rückkaufswert.
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
     * Kapitalertragssteuer bei Rückkauf zum Berechnungsstichtag.
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
     * Solidaritätszuschlag bei Rückkauf zum Berechnungsstichtag.
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
     * Überschussanteile zum Berechnungsstichtag in Währungseinheiten.
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
     * Satznummernwiederholung.
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
     * Nummer, die im VU geführt wird.
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
     * Satznummer.
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
     * Satznummer.
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
     * Bezugsberechtigt im Leistungsfall.
     * 1 = Versicherungsnehmer
     * 2 = Versicherte Person
     * 9 = Sonstiger Bezugsberechtigter
     */
    @FeldInfo(
            teildatensatz = 9,
            nr = 11,
            type = Zeichen.class,
            anzahlBytes = 1,
            byteAdresse = 63)
    BEZUGSBERECHTIGT_IM_LEISTUNGSFALL,

    /**
     * Sonstiger Bezugsberechtigter im Leistungsfall.
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
     * Bezugsrechtanteil im Leistungsfall.
     * in Prozent
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
     * Unwiderrufliches Bezugsrecht im Leistungsfall.
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
     * Referenznummer.
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
     * Geburtsdatum der bezugsberechtigten Person.
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
     * Satznummernwiederholung.
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
