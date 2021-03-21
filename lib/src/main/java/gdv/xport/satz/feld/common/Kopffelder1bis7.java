package gdv.xport.satz.feld.common;

import gdv.xport.feld.Align;
import gdv.xport.feld.AlphaNumFeld;
import gdv.xport.feld.Bezeichner;
import gdv.xport.feld.NumFeld;
import gdv.xport.feld.VUNummer;
import gdv.xport.feld.Zeichen;

/**
 * Die Kopffelder. Jeder Teildatensatz beginnt mit den gleichen 7 Feldern. Ausnahme: Vorsatz und
 * Nachsatz
 * 
 * @author junke
 * @since 5.0
 */
public final class Kopffelder1bis7
{
  /**
   * Satzart (Feld Nr 1 ab Pos 1 mit Laenge 4)
   */
  public static final NumFeld SATZART = new NumFeld(Bezeichner.of("Satzart"), 4, 1);

  /**
   * VU-Nummer (Feld Nr 2 ab Pos 5 mit Laenge 5)
   */
  public static final VUNummer VU_NUMMER = new VUNummer();

  /**
   * Bündelungskennzeichen (Feld Nr 3 ab Pos 10 mit Laenge 1)
   */
  public static final Zeichen BUENDELUNGSKENNZEICHEN =
      new Zeichen(Bezeichner.of("Bündelungskennzeichen"), VU_NUMMER.getEndAdresse() + 1);

  /**
   * Sparte (Feld Nr 4 ab Pos 11 mit Laenge 3)
   */
  public static final NumFeld SPARTE =
      new NumFeld(Bezeichner.of("sparte"), 3, BUENDELUNGSKENNZEICHEN.getEndAdresse() + 1);

  /**
   * Versicherungsscheinnummer (Feld Nr 5 ab Pos 14 mit Laenge 17)
   */
  public static final AlphaNumFeld VERSICHERUNGSSCHEINNUMMER = new AlphaNumFeld(
      Bezeichner.VERSICHERUNGSSCHEINNUMMER, 17, SPARTE.getEndAdresse() + 1, Align.LEFT);

  /**
   * Folgenummer (Feld Nr 6 ab Pos 31 mit Laenge 2)
   */
  public static final NumFeld FOLGENUMMER =
      new NumFeld(Bezeichner.of("Folgenummer"), 2, VERSICHERUNGSSCHEINNUMMER.getEndAdresse() + 1);

  /**
   * Geschäftsstelle / Vermittler (Feld Nr 7 ab Pos 33 mit Laenge 10)
   */
  public static final AlphaNumFeld VERMITTLER =
      new AlphaNumFeld(Bezeichner.of("Geschaeftsstelle/Vermittler"), 10,
          FOLGENUMMER.getEndAdresse() + 1, Align.LEFT);

  private Kopffelder1bis7()
  {
  }
}
