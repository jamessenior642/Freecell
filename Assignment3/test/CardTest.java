import org.junit.Before;
import org.junit.Test;

import cs3500.freecell.model.Card;

import static cs3500.freecell.model.Suit.CLUBS;
import static cs3500.freecell.model.Suit.HEARTS;
import static cs3500.freecell.model.Suit.SPADES;
import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for the Card class.
 */
public class CardTest {
  private Card card1;
  private Card card2;
  private Card card3;

  /**
   * initializing examples.
   */
  @Before
  public void setUp() {
    card1 = new Card(2, SPADES);
    card2 = new Card(11, CLUBS);
    card3 = new Card(1, HEARTS);
  }

  /**
   * tests Constructor Exception for Cards.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstException() {
    Card card3 = new Card(0, CLUBS);
  }


  /**
   * tests getRank method.
   */
  @Test
  public void testGetRank() {
    assertEquals(2, card1.getRank());
    assertEquals(11, card2.getRank());
  }

  /**
   * tests getSuit method.
   */
  @Test
  public void testGetSuit() {
    assertEquals(SPADES, card1.getSuit());
    assertEquals(CLUBS, card2.getSuit());
  }

  /**
   * tests getColor method.
   */
  @Test
  public void testGetColor() {
    assertEquals("Black", card1.getColor());
    assertEquals("Red", card3.getColor());
  }

  /**
   * tests toString method.
   */
  @Test
  public void testToString() {
    assertEquals("2♠", card1.toString());
    assertEquals("J♣", card2.toString());
  }
}




