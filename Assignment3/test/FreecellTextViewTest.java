import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import cs3500.freecell.controller.Unappendable;
import cs3500.freecell.model.Card;
import cs3500.freecell.model.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;

import static cs3500.freecell.model.PileType.CASCADE;
import static cs3500.freecell.model.PileType.FOUNDATION;
import static cs3500.freecell.model.PileType.OPEN;
import static cs3500.freecell.model.Suit.CLUBS;
import static cs3500.freecell.model.Suit.DIAMONDS;
import static cs3500.freecell.model.Suit.HEARTS;
import static cs3500.freecell.model.Suit.SPADES;
import static org.junit.Assert.assertEquals;


/**
 * test class for FreeCellTextView.
 */
public class FreecellTextViewTest {

  Card aClubs;
  Card twoClubs;
  Card threeClubs;
  Card fourClubs;
  Card fiveClubs;
  Card sixClubs;
  Card sevenClubs;
  Card eightClubs;
  Card nineClubs;
  Card tenClubs;
  Card jClubs;
  Card qClubs;
  Card kClubs;
  Card aHearts;
  Card twoHearts;
  Card threeHearts;
  Card fourHearts;
  Card fiveHearts;
  Card sixHearts;
  Card sevenHearts;
  Card eightHearts;
  Card nineHearts;
  Card tenHearts;
  Card jHearts;
  Card qHearts;
  Card kHearts;
  Card aSpades;
  Card twoSpades;
  Card threeSpades;
  Card fourSpades;
  Card fiveSpades;
  Card sixSpades;
  Card sevenSpades;
  Card eightSpades;
  Card nineSpades;
  Card tenSpades;
  Card jSpades;
  Card qSpades;
  Card kSpades;
  Card aDiamonds;
  Card twoDiamonds;
  Card threeDiamonds;
  Card fourDiamonds;
  Card fiveDiamonds;
  Card sixDiamonds;
  Card sevenDiamonds;
  Card eightDiamonds;
  Card nineDiamonds;
  Card tenDiamonds;
  Card jDiamonds;
  Card qDiamonds;
  Card kDiamonds;
  List<Card> deck;
  List<Card> custDeck;
  SimpleFreecellModel model;


  /**
   * initialize examples.
   */
  @Before
  public void setUp() {
    aClubs = new Card(1, CLUBS);
    twoClubs = new Card(2, CLUBS);
    threeClubs = new Card(3, CLUBS);
    fourClubs = new Card(4, CLUBS);
    fiveClubs = new Card(5, CLUBS);
    sixClubs = new Card(6, CLUBS);
    sevenClubs = new Card(7, CLUBS);
    eightClubs = new Card(8, CLUBS);
    nineClubs = new Card(9, CLUBS);
    tenClubs = new Card(10, CLUBS);
    jClubs = new Card(11, CLUBS);
    qClubs = new Card(12, CLUBS);
    kClubs = new Card(13, CLUBS);

    aSpades = new Card(1, SPADES);
    twoSpades = new Card(2, SPADES);
    threeSpades = new Card(3, SPADES);
    fourSpades = new Card(4, SPADES);
    fiveSpades = new Card(5, SPADES);
    sixSpades = new Card(6, SPADES);
    sevenSpades = new Card(7, SPADES);
    eightSpades = new Card(8, SPADES);
    nineSpades = new Card(9, SPADES);
    tenSpades = new Card(10, SPADES);
    jSpades = new Card(11, SPADES);
    qSpades = new Card(12, SPADES);
    kSpades = new Card(13, SPADES);

    aDiamonds = new Card(1, DIAMONDS);
    twoDiamonds = new Card(2, DIAMONDS);
    threeDiamonds = new Card(3, DIAMONDS);
    fourDiamonds = new Card(4, DIAMONDS);
    fiveDiamonds = new Card(5, DIAMONDS);
    sixDiamonds = new Card(6, DIAMONDS);
    sevenDiamonds = new Card(7, DIAMONDS);
    eightDiamonds = new Card(8, DIAMONDS);
    nineDiamonds = new Card(9, DIAMONDS);
    tenDiamonds = new Card(10, DIAMONDS);
    jDiamonds = new Card(11, DIAMONDS);
    qDiamonds = new Card(12, DIAMONDS);
    kDiamonds = new Card(13, DIAMONDS);

    aHearts = new Card(1, HEARTS);
    twoHearts = new Card(2, HEARTS);
    threeHearts = new Card(3, HEARTS);
    fourHearts = new Card(4, HEARTS);
    fiveHearts = new Card(5, HEARTS);
    sixHearts = new Card(6, HEARTS);
    sevenHearts = new Card(7, HEARTS);
    eightHearts = new Card(8, HEARTS);
    nineHearts = new Card(9, HEARTS);
    tenHearts = new Card(10, HEARTS);
    jHearts = new Card(11, HEARTS);
    qHearts = new Card(12, HEARTS);
    kHearts = new Card(13, HEARTS);
    model = new SimpleFreecellModel();
    deck = model.getDeck();
    custDeck = Arrays.asList(kDiamonds, kClubs, kHearts, kSpades, qDiamonds, qClubs, qHearts,
            qSpades, jDiamonds, jClubs, jHearts, jSpades, tenDiamonds, tenClubs, tenHearts,
            tenSpades, nineDiamonds, nineClubs, nineHearts, nineSpades, eightDiamonds, eightClubs,
            eightHearts, eightSpades, sevenDiamonds, sevenClubs, sevenHearts, sevenSpades,
            sixDiamonds, sixClubs, sixHearts, sixSpades, fiveDiamonds, fiveClubs, fiveHearts,
            fiveSpades, fourDiamonds, fourClubs, fourHearts, fourSpades, threeDiamonds, threeClubs,
            threeHearts, threeSpades, twoDiamonds, twoClubs, twoHearts, twoSpades, aDiamonds,
            aClubs, aHearts, aSpades);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    model = null;
    FreecellTextView modelNull = new FreecellTextView(model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewConstructor() {
    model = null;
    StringBuilder s = new StringBuilder();
    FreecellTextView modelNull2 = new FreecellTextView(model, s);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewConstructor2() {
    model = new SimpleFreecellModel();
    StringBuilder s = null;
    FreecellTextView modelNull3 = new FreecellTextView(model, s);
  }

  @Test
  public void testToString() {
    model.startGame(custDeck, 4, 2, false);
    FreecellTextView s = new FreecellTextView(model);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", s.toString());
    model.move(CASCADE, 0, 12, FOUNDATION, 0);
    model.move(CASCADE, 1, 12, OPEN, 0);
    FreecellTextView s2 = new FreecellTextView(model);
    assertEquals("F1: A♦\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♣\n"
            + "O2:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", s2.toString());
    SimpleFreecellModel notStarted = new SimpleFreecellModel();
    FreecellTextView s3 = new FreecellTextView(notStarted);
    assertEquals("", s3.toString());
  }


  /**
   * tests for Render Board.
   */
  @Test
  public void testRenderBoard() {
    model = new SimpleFreecellModel();
    StringBuilder s = new StringBuilder();
    model.startGame(custDeck, 4, 1, false);
    FreecellTextView view = new FreecellTextView(model, s);
    try {
      view.renderBoard();
    } catch (IOException io) {
      io.printStackTrace();
    }
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n", s.toString());
    model.move(CASCADE, 1, 12, OPEN, 0);
    try {
      view.renderBoard();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♣\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n", s.toString());
  }

  @Test(expected = IOException.class)
  public void testRenderBoardError() throws IOException {
    Appendable a = new Unappendable();
    model.startGame(deck, 4, 1, false);
    FreecellTextView state = new FreecellTextView(model, a);
    state.renderBoard();
  }

  /**
   * tests for Render Message.
   */

  @Test
  public void testRenderMessage() {
    model = new SimpleFreecellModel();
    StringBuilder s = new StringBuilder();
    model.startGame(custDeck, 4, 1, false);
    FreecellTextView view = new FreecellTextView(model, s);
    try {
      view.renderBoard();
    } catch (IOException io) {
      io.printStackTrace();
    }
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n", s.toString());
    model.move(CASCADE, 1, 12, OPEN, 0);
    try {
      view.renderMessage("Message.");
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Message.", s.toString());
  }

  @Test(expected = IOException.class)
  public void testRenderMessageError() throws IOException {
    Appendable a = new Unappendable();
    model.startGame(deck, 4, 1, false);
    FreecellTextView state = new FreecellTextView(model, a);
    state.renderMessage("test");
  }

}