package gdv.xport.feld;

import gdv.xport.config.Config;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit-Test fuer den Feld-Ueberlauf.
 * 
 * @author junke
 */
public class FeldUeberlaufTest {

  private static final Config TRUNCATE = Config.getInstance().withProperty("gdv.feld.truncate", "true")
                                               .withProperty("gdv.feld.validate", "true");
  private static final Config STRICT = TRUNCATE.withProperty("gdv.feld.validate", "strict");

  /**
   * Test method for {@link Feld#setInhalt(String)} bei Align.RIGHT .
   */
  @Test
  public void testAlphaNumFeldRechtsbuendig()
  {
    AlphaNumFeld alphaNumFeldRechts =
        new AlphaNumFeld(Bezeichner.of("alphaNumRechtsTestFeld"), 8, 1, Align.RIGHT).mitConfig(TRUNCATE);

    alphaNumFeldRechts.setInhalt("hello world helle lello");
    assertEquals("hello wo", alphaNumFeldRechts.getInhalt());

    alphaNumFeldRechts.setInhalt(1234567890);
    assertEquals("12345678", alphaNumFeldRechts.getInhalt());

    alphaNumFeldRechts.setInhalt(1234);
    assertEquals("    1234", alphaNumFeldRechts.getInhalt());

    alphaNumFeldRechts.setInhalt("hello");
    assertEquals("   hello", alphaNumFeldRechts.getInhalt());
    // wenn man Blanks reingibt, bleiben sie erhalten.
    alphaNumFeldRechts.setInhalt(" hello  ");
    assertEquals(" hello  ", alphaNumFeldRechts.getInhalt());
    // und auch hier bleiben die Blanks erhalten - hier gibt es nix zum Auffuellen!
    alphaNumFeldRechts.setInhalt("hello   ");
    assertEquals("hello   ", alphaNumFeldRechts.getInhalt());
  }

  @Test
  public void testAlphaNumFeldRechtsbuendigStrict() {
    AlphaNumFeld alphaNumFeldRechtsStrict =
            new AlphaNumFeld(Bezeichner.of("alphaNumRechtsTestFeld"), 8, 1, Align.RIGHT).mitConfig(STRICT);

    alphaNumFeldRechtsStrict.setInhalt("hello world helle lello");
    assertEquals("hello wo", alphaNumFeldRechtsStrict.getInhalt());

    alphaNumFeldRechtsStrict.setInhalt(1234567890);
    assertEquals("12345678", alphaNumFeldRechtsStrict.getInhalt());

    alphaNumFeldRechtsStrict.setInhalt(1234);
    assertEquals("    1234", alphaNumFeldRechtsStrict.getInhalt());

    alphaNumFeldRechtsStrict.setInhalt("hello");
    assertEquals("   hello", alphaNumFeldRechtsStrict.getInhalt());
    // wenn man Blanks reingibt, werden sie getrimmt! - wie anders gueltigen Inhalt rechtbuendig
    // ausrichten?
    alphaNumFeldRechtsStrict.setInhalt(" hello  ");
    assertEquals("   hello", alphaNumFeldRechtsStrict.getInhalt());
    // und auch hier werden die Blanks getrimmt! - wie sonst anders gueltigen Inhalt rechtbuendig
    // ausrichten?
    alphaNumFeldRechtsStrict.setInhalt("hello   ");
    assertEquals("   hello", alphaNumFeldRechtsStrict.getInhalt());
  }

  /**
   * Test method for {@link Feld#setInhalt(String)} bei Align.LEFT .
   */
  @Test
  public void testAlphaNumFeldLinksbuendig()
  {
    AlphaNumFeld alphaNumFeldLinks =
        new AlphaNumFeld(Bezeichner.of("alphaNumLinksTestFeld"), 9, 10, Align.LEFT).mitConfig(TRUNCATE);

    alphaNumFeldLinks.setInhalt("hello world helle lello");
    assertEquals("hello wor", alphaNumFeldLinks.getInhalt());

    alphaNumFeldLinks.setInhalt(1234567890);
    assertEquals("123456789", alphaNumFeldLinks.getInhalt());

    alphaNumFeldLinks.setInhalt(1234);
    assertEquals("1234     ", alphaNumFeldLinks.getInhalt());

    alphaNumFeldLinks.setInhalt("hello");
    assertEquals("hello    ", alphaNumFeldLinks.getInhalt());
    // wenn man Blanks reingibt, bleiben sie erhalten.
    alphaNumFeldLinks.setInhalt(" hello  ");
    assertEquals(" hello   ", alphaNumFeldLinks.getInhalt());
    // und auch hier bleiben die Blanks erhalten - hier gibt es nix zum Auffuellen!
    alphaNumFeldLinks.setInhalt(" hello  ");
    assertEquals(" hello   ", alphaNumFeldLinks.getInhalt());
  }

  @Test
  public void testAlphaNumFeldLinksbuendigStrict() {
    AlphaNumFeld alphaNumFeldLinksStrict =
            new AlphaNumFeld(Bezeichner.of("alphaNumLinksTestFeld"), 9, 10, Align.LEFT).mitConfig(STRICT);

    alphaNumFeldLinksStrict.setInhalt("hello world helle lello");
    assertEquals("hello wor", alphaNumFeldLinksStrict.getInhalt());

    alphaNumFeldLinksStrict.setInhalt(1234567890);
    assertEquals("123456789", alphaNumFeldLinksStrict.getInhalt());

    alphaNumFeldLinksStrict.setInhalt(1234);
    assertEquals("1234     ", alphaNumFeldLinksStrict.getInhalt());

    alphaNumFeldLinksStrict.setInhalt("hello");
    assertEquals("hello    ", alphaNumFeldLinksStrict.getInhalt());
    // wenn man Blanks reingibt, werden sie getrimmt! - wie anders gueltigen Inhalt linksbuendig
    // ausrichten?
    alphaNumFeldLinksStrict.setInhalt(" hello  ");
    assertEquals("hello    ", alphaNumFeldLinksStrict.getInhalt());

    alphaNumFeldLinksStrict.setInhalt("");
  }

  /**
   * Test method for {@link Feld#setInhalt(String)} bei NumFeld .
   */
  @Test
  public void testNumFeld()
  {
    NumFeld numFeldOhneNachkomma = new NumFeld(Bezeichner.of("numOhneNachkommaTestFeld"), 5, 1).mitConfig(TRUNCATE);

    numFeldOhneNachkomma.setInhalt(100000);
    assertEquals("99999", numFeldOhneNachkomma.getInhalt());

    numFeldOhneNachkomma.setInhalt("  ");
    assertEquals("000  ", numFeldOhneNachkomma.getInhalt());

    numFeldOhneNachkomma.setInhalt("123000");
    assertEquals("99999", numFeldOhneNachkomma.getInhalt());

    numFeldOhneNachkomma.setInhalt("");
    assertEquals("00000", numFeldOhneNachkomma.getInhalt());

    numFeldOhneNachkomma.setInhalt("99");
    assertEquals("00099", numFeldOhneNachkomma.getInhalt());

    Exception exception = assertThrows(IllegalArgumentException.class, () ->
    {
      numFeldOhneNachkomma.setInhalt("AA");
    });

    assertTrue(exception.getMessage()
        .contains("AA"));
  }

  @Test
  public void testBetrag()
  {
    Betrag betrag = new Betrag(Bezeichner.of("betragTest"), 10, 20).mitConfig(TRUNCATE);

    betrag.setInhalt("123");
    assertEquals("0000000123", betrag.getInhalt());

    betrag.setInhalt("");
    assertEquals("0000000000", betrag.getInhalt());

    betrag.setInhalt(1234567890L);
    assertEquals("9999999999", betrag.getInhalt());

    betrag.setInhalt(12345678900L);
    assertEquals("9999999999", betrag.getInhalt());

    Exception exception22 = assertThrows(IllegalArgumentException.class, () ->
    {
      betrag.setInhalt("-123");
    });

    assertTrue(exception22.getMessage()
        .contains("-123"));

    Betrag betragStrict = new Betrag(Bezeichner.of("betragTest"), 10, 20).mitConfig(STRICT);
    betragStrict.setInhalt("123");
    assertEquals("0000000123", betragStrict.getInhalt());

    betragStrict.setInhalt(123);
    assertEquals("0000012300", betragStrict.getInhalt());

    Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
    {
      betrag.setInhalt("-123");
    });

    assertTrue(exception1.getMessage()
        .contains("-123"));

    Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
    {
      betrag.setInhalt("1A3");
    });

    assertTrue(exception2.getMessage()
        .contains("1A3"));

  }

  @Test
  public void testBetragMitVorzeichen()
  {
    BetragMitVorzeichen betragMVz = new BetragMitVorzeichen(Bezeichner.of("betragTEstVZ"), 10, 2).mitConfig(STRICT);

    betragMVz.setInhalt("123");
    assertEquals("000000123+", betragMVz.getInhalt());

    betragMVz.setInhalt(123456);
    assertEquals("000123456+", betragMVz.getInhalt());

    betragMVz.setInhalt(1234567890);
    assertEquals("999999999+", betragMVz.getInhalt());

    betragMVz.setInhalt("-123");
    assertEquals("000000123-", betragMVz.getInhalt());

    Long wert = 12345678901L;
    betragMVz.setInhalt(wert);
    assertEquals("999999999+", betragMVz.getInhalt());

    Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
    {
      betragMVz.setInhalt("1A3");
    });

    assertTrue(exception1.getMessage()
        .contains("1A3"));

    Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
    {
    betragMVz.setInhalt("");
    });

    assertFalse(exception2.getMessage().isEmpty());
  }
}
