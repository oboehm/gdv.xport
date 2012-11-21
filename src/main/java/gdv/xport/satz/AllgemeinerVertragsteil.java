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
 * (c)reated 27.10.2009 by Oli B. (ob@aosd.de)
 */

package gdv.xport.satz;

import static gdv.xport.feld.Bezeichner.ABGANGSDATUM;
import static gdv.xport.feld.Bezeichner.ABGANGSGRUND;
import static gdv.xport.feld.Bezeichner.AENDERUNGSDATUM;
import static gdv.xport.feld.Bezeichner.AENDERUNGSGRUND;
import static gdv.xport.feld.Bezeichner.AFB;
import static gdv.xport.feld.Bezeichner.ANTEIL_IN_PROZENT;
import static gdv.xport.feld.Bezeichner.ANTRAGSDATUM;
import static gdv.xport.feld.Bezeichner.ANTRAGSEINGANGSDATUM;
import static gdv.xport.feld.Bezeichner.AUFSICHTSFREIER_VERTRAG;
import static gdv.xport.feld.Bezeichner.AUFTEILUNG_VERSICHERUNGSSTEUER;
import static gdv.xport.feld.Bezeichner.AUFTRAGSNR_VERMITTLER;
import static gdv.xport.feld.Bezeichner.DRUCKAUFBEREITETE_VERSICHERUNGSSCHEINNUMMER;
import static gdv.xport.feld.Bezeichner.EINZAHLUNG_AUSSCHUETTUNG;
import static gdv.xport.feld.Bezeichner.ERWEITERUNGSSATZ_VORHANDEN;
import static gdv.xport.feld.Bezeichner.GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN;
import static gdv.xport.feld.Bezeichner.GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN;
import static gdv.xport.feld.Bezeichner.HAUPTFAELLIGKEIT;
import static gdv.xport.feld.Bezeichner.INKASSOART;
import static gdv.xport.feld.Bezeichner.INTERNES_ORDNUNGSMERKMAL_DES_VM;
import static gdv.xport.feld.Bezeichner.KENNZEICHEN_VERS_STEUER_FREI;
import static gdv.xport.feld.Bezeichner.KUENDIGUNGSKLAUSEL;
import static gdv.xport.feld.Bezeichner.KUNDENNR_VERMITTLER;
import static gdv.xport.feld.Bezeichner.KUNDENNR_VERSICHERER;
import static gdv.xport.feld.Bezeichner.LAUFZEITRABATT_IN_PROZENT;
import static gdv.xport.feld.Bezeichner.MEHRZWECKFELD;
import static gdv.xport.feld.Bezeichner.ORDNUNGSBEGRIFF;
import static gdv.xport.feld.Bezeichner.POLICIERUNGSDATUM;
import static gdv.xport.feld.Bezeichner.PRODUKTFORM;
import static gdv.xport.feld.Bezeichner.PRODUKTFORM_GUELTIG_AB;
import static gdv.xport.feld.Bezeichner.PRODUKTNAME;
import static gdv.xport.feld.Bezeichner.RATENZAHLUNGSZUSCHLAG_IN_PROZENT;
import static gdv.xport.feld.Bezeichner.REFERENZ_VERSICHERUNGSSCHEINNUMMER;
import static gdv.xport.feld.Bezeichner.REGISTRIERUNGSNUMMER_VERMITTLER;
import static gdv.xport.feld.Bezeichner.RESTLAUFZEIT_VERTRAG;
import static gdv.xport.feld.Bezeichner.SPEZIFIKATION_REFERENZ_VERSICHERUNGSSCHEINNUMMER;
import static gdv.xport.feld.Bezeichner.VERSICHERUNGSSCHEINNUMMER_VM;
import static gdv.xport.feld.Bezeichner.VERTRAGSABLAUF;
import static gdv.xport.feld.Bezeichner.VERTRAGSBEGINN;
import static gdv.xport.feld.Bezeichner.VERTRAGSSTATUS;
import static gdv.xport.feld.Bezeichner.WAEHRUNGSSCHLUESSEL;
import static gdv.xport.feld.Bezeichner.WAEHRUNG_DOKUMENTE_FUER_VN;
import static gdv.xport.feld.Bezeichner.ZAHLUNGSWEISE;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Betrag;
import gdv.xport.feld.Datum;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.Zeichen;

/**
 * Diese Klasse repraesentiert die Satzart 200 (Allgemeiner Vertragsteil).
 * 
 * @author oliver (ob@aosd.de)
 * @since 0.1.0 (27.10.2009)
 */
@Deprecated
public class AllgemeinerVertragsteil extends Datensatz {

    private void setUpDatenfelder() {
        // Teildatensatz 1
        add(new Zeichen(INKASSOART, 43));
        add(new Datum(VERTRAGSBEGINN, 44));
        add(new Datum(VERTRAGSABLAUF, 52));
        add(new Datum(HAUPTFAELLIGKEIT, 60));
        add(new Zeichen(ZAHLUNGSWEISE, 68));
        add(new Zeichen(VERTRAGSSTATUS, 69));
        add(new AlphaNumFeld(ABGANGSGRUND, 2, 70));
        add(new Datum(ABGANGSDATUM, 72));
        add(new AlphaNumFeld(AENDERUNGSGRUND, 10, 80));
        add(new Datum(AENDERUNGSDATUM, 90));
        add(new Zeichen(AFB, 98));
        add(new NumFeld(ANTEIL_IN_PROZENT, 5, 99).mitNachkommastellen(3));
        add(new AlphaNumFeld(AUFTRAGSNR_VERMITTLER, 9, 104));
        add(new AlphaNumFeld(WAEHRUNGSSCHLUESSEL, 3, 113));
        add(new Betrag(GESAMTBEITRAG_NETTO_IN_WAEHRUNGSEINHEITEN, 12, 116));
        add(new AlphaNumFeld(MEHRZWECKFELD, 30, 128));
        add(new Zeichen(KENNZEICHEN_VERS_STEUER_FREI, 158));
        add(new AlphaNumFeld(KUNDENNR_VERSICHERER, 17, 159));
        add(new AlphaNumFeld(KUNDENNR_VERMITTLER, 17, 176));
        add(new Zeichen(AUFSICHTSFREIER_VERTRAG, 193));
        add(new Zeichen(AUFTEILUNG_VERSICHERUNGSSTEUER, 194));
        add(new NumFeld(RESTLAUFZEIT_VERTRAG, 2, 195));
        add(new NumFeld(LAUFZEITRABATT_IN_PROZENT, 4, 197).mitNachkommastellen(2));
        add(new Datum(ANTRAGSDATUM, 201));
        add(new AlphaNumFeld(REFERENZ_VERSICHERUNGSSCHEINNUMMER, 17, 209));
        add(new Zeichen(SPEZIFIKATION_REFERENZ_VERSICHERUNGSSCHEINNUMMER, 226));
        add(new AlphaNumFeld(ORDNUNGSBEGRIFF, 17, 227));
        add(new AlphaNumFeld(WAEHRUNG_DOKUMENTE_FUER_VN, 3, 244));
        add(new Zeichen(ERWEITERUNGSSATZ_VORHANDEN, 247));
        add(new Zeichen(EINZAHLUNG_AUSSCHUETTUNG, 248));
        // Teildatensatz 2
        add(new Zeichen(KUENDIGUNGSKLAUSEL, 43), 2);
        add(new AlphaNumFeld(VERSICHERUNGSSCHEINNUMMER_VM, 17, 44), 2);
        add(new AlphaNumFeld(PRODUKTFORM, 5, 61), 2);
        add(new Datum(PRODUKTFORM_GUELTIG_AB, 6, 66), 2);
        add(new Betrag(GESAMTBEITRAG_BRUTTO_IN_WAEHRUNGSEINHEITEN, 12, 72), 2);
        add(new AlphaNumFeld(DRUCKAUFBEREITETE_VERSICHERUNGSSCHEINNUMMER, 30, 84), 2);
        add(new AlphaNumFeld(PRODUKTNAME, 20, 114), 2);
        add(new NumFeld(RATENZAHLUNGSZUSCHLAG_IN_PROZENT, 5, 134), 2);
        add(new Datum(ANTRAGSEINGANGSDATUM, 139), 2);
        add(new Datum(POLICIERUNGSDATUM, 147), 2);
        add(new AlphaNumFeld(REGISTRIERUNGSNUMMER_VERMITTLER, 15, 155), 2);
        add(new AlphaNumFeld(INTERNES_ORDNUNGSMERKMAL_DES_VM, 50, 170), 2);
    }

    /**
     * Default-Konstruktor.
     */
    public AllgemeinerVertragsteil() {
        super("0200", 2);
        this.setUpDatenfelder();
    }

}
