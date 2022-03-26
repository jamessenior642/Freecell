import org.junit.Test;


import static cs3500.freecell.model.Suit.CLUBS;
import static cs3500.freecell.model.Suit.DIAMONDS;
import static cs3500.freecell.model.Suit.HEARTS;
import static cs3500.freecell.model.Suit.SPADES;
import static org.junit.Assert.assertEquals;

/**
 * Test class for Suits.
 */
public class SuitTest {

  /**
   * Test for toString.
   */
  @Test
  public void testToString() {
    assertEquals("♣", CLUBS.toString());
    assertEquals("♦", DIAMONDS.toString());
    assertEquals("♥", HEARTS.toString());
    assertEquals("♠", SPADES.toString());
  }

  /**
   * Test for getColor.
   */
  @Test
  public void testGetColor() {
    assertEquals("Black", CLUBS.getColor());
    assertEquals("Red", DIAMONDS.getColor());
    assertEquals("Red", HEARTS.getColor());
    assertEquals("Black", SPADES.getColor());
  }

}
