import org.junit.Before;
import org.junit.Test;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.FreecellModelCreator;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.SimpleFreecellModel;
import cs3500.freecell.model.Suit;
import cs3500.freecell.model.multimove.MultiMoveFreecellModel;
import cs3500.freecell.view.FreecellTextView;

import static cs3500.freecell.model.FreecellModelCreator.GameType.MULTIMOVE;
import static cs3500.freecell.model.FreecellModelCreator.GameType.SINGLEMOVE;
import static cs3500.freecell.model.FreecellModelCreator.create;
import static cs3500.freecell.model.PileType.CASCADE;
import static cs3500.freecell.model.PileType.FOUNDATION;
import static cs3500.freecell.model.PileType.OPEN;
import static org.junit.Assert.assertEquals;

/**
 * test class representing tests for the MultiMove Freecell model.
 */
public class TestMultiMoveModel {
  private FreecellModel<Card> simpleModel;
  private FreecellModel<Card> multiModel;
  private FreecellTextView state1;
  private Object hello;

  @Before
  public void setUp() {
    simpleModel = new SimpleFreecellModel();
    multiModel = new MultiMoveFreecellModel();
  }

  /**
   * tests for the FreecellModelCreator class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreator() {
    create(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreatorWrongGameType() {
    multiModel = create((FreecellModelCreator.GameType) hello);
    assertEquals(null, multiModel);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testCreatorSimpleModel() {
    create(SINGLEMOVE);
    simpleModel.startGame(simpleModel.getDeck(), 4, 4, false);
    simpleModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    simpleModel.move(CASCADE, 0, 11, FOUNDATION, 0);
    simpleModel.move(CASCADE, 1, 12, FOUNDATION, 1);
    simpleModel.move(CASCADE, 2, 12, CASCADE, 1);
    //exception thrown when multi move is attempted
    simpleModel.move(CASCADE, 1, 11, CASCADE, 0);
  }


  @Test
  public void testCreatorMultiModel() {
    create(MULTIMOVE);
    multiModel.startGame(multiModel.getDeck(), 4, 4, false);
    multiModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 11, FOUNDATION, 0);
    multiModel.move(CASCADE, 1, 12, FOUNDATION, 1);
    multiModel.move(CASCADE, 2, 12, CASCADE, 1);
    //makes sure multi move can be done
    multiModel.move(CASCADE, 1, 11, CASCADE, 0);
    assertEquals(new Card(1, Suit.HEARTS),
            multiModel.getCascadeCardAt(0, 12));
  }

  /**
   * tests for multimove: Moving from cascade pile.
   */
  @Test
  public void testMultiMoveCasctoCasc() {
    multiModel.startGame(multiModel.getDeck(), 4, 4, false);
    state1 = new FreecellTextView(multiModel);
    multiModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 11, FOUNDATION, 0);
    multiModel.move(CASCADE, 1, 12, FOUNDATION, 1);
    multiModel.move(CASCADE, 2, 12, CASCADE, 1);
    assertEquals("F1: A♦, 2♦\n"
            + "F2: A♣\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♥\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
    //multimove: move two cards to cascade pile 1.
    multiModel.move(CASCADE, 1, 11, CASCADE, 0);
    assertEquals("F1: A♦, 2♦\n"
            + "F2: A♣\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♣, A♥\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
  }

  @Test
  public void testAnyCardToEmpty() {
    multiModel.startGame(multiModel.getDeck(), 4, 4, false);
    state1 = new FreecellTextView(multiModel);
    multiModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 11, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 10, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 9, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 8, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 7, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 6, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 5, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 4, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 3, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 2, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 1, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 0, FOUNDATION, 0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1:\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
    multiModel.move(CASCADE, 2, 12, FOUNDATION, 2);
    multiModel.move(CASCADE, 2, 11, FOUNDATION, 2);
    multiModel.move(CASCADE, 2, 10, FOUNDATION, 2);
    multiModel.move(CASCADE, 2, 9, FOUNDATION, 2);
    multiModel.move(CASCADE, 2, 8, FOUNDATION, 2);
    // tests that a card can be moved to empty
    multiModel.move(CASCADE, 2, 7, CASCADE, 0);
  }

  @Test
  public void testMoveCascEmptyCasc() {
    multiModel.startGame(simpleModel.getDeck(), 4, 4, false);
    state1 = new FreecellTextView(multiModel);
    multiModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 11, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 10, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 9, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 8, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 7, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 6, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 5, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 4, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 3, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 2, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 1, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 0, FOUNDATION, 0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1:\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
    multiModel.move(CASCADE, 2, 12, OPEN, 0);
    multiModel.move(CASCADE, 1, 12, CASCADE, 2);
    multiModel.move(CASCADE, 2, 11, CASCADE, 0);
    assertEquals("F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♥\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: 2♥, A♣\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveCasctoOpen() {
    multiModel.startGame(simpleModel.getDeck(), 4, 4, false);
    state1 = new FreecellTextView(multiModel);
    multiModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 11, FOUNDATION, 0);
    multiModel.move(CASCADE, 1, 12, FOUNDATION, 1);
    multiModel.move(CASCADE, 2, 12, CASCADE, 1);
    assertEquals("F1: A♦, 2♦\n"
            + "F2: A♣\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♥\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
    //try to move 2 cards to open pile
    multiModel.move(CASCADE, 1, 11, OPEN, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveCasctoFound() {
    multiModel.startGame(simpleModel.getDeck(), 4, 4, false);
    state1 = new FreecellTextView(multiModel);
    multiModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 11, FOUNDATION, 0);
    multiModel.move(CASCADE, 1, 12, FOUNDATION, 1);
    multiModel.move(CASCADE, 2, 12, CASCADE, 1);
    assertEquals("F1: A♦, 2♦\n"
            + "F2: A♣\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♥\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
    //try to move 2 cards to foundation pile
    multiModel.move(CASCADE, 1, 11, FOUNDATION, 2);
  }

  /**
   * test for moving from open pile.
   */
  @Test
  public void testMultiMoveFromOpen() {
    multiModel.startGame(multiModel.getDeck(), 4, 4, false);
    state1 = new FreecellTextView(multiModel);
    multiModel.move(CASCADE, 0, 12, OPEN, 0);
    multiModel.move(OPEN, 0, 0, OPEN, 1);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2: A♦\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
  }

  /**
   * test for moving from Foundation pile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveFromFound() {
    multiModel.startGame(multiModel.getDeck(), 4, 4, false);
    state1 = new FreecellTextView(multiModel);
    multiModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 11, FOUNDATION, 0);
    multiModel.move(CASCADE, 1, 11, FOUNDATION, 1);
    multiModel.move(FOUNDATION, 1, 0, CASCADE, 1);
  }


  /**
   * test different errors for multimove.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveTooFewSlots() {
    multiModel.startGame(simpleModel.getDeck(), 4, 4, false);
    state1 = new FreecellTextView(multiModel);
    multiModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 11, OPEN, 1);
    multiModel.move(CASCADE, 1, 12, OPEN, 2);
    multiModel.move(CASCADE, 2, 12, CASCADE, 1);
    multiModel.move(CASCADE, 1, 11, CASCADE, 0);
    multiModel.move(PileType.CASCADE, 1, 10, OPEN, 0);
    assertEquals("F1: A♦\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: 3♣\n"
            + "O2: 2♦\n"
            + "O3: A♣\n"
            + "O4:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♣, A♥\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
    multiModel.move(CASCADE, 0, 10, OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveInvalidBuild() {
    multiModel.startGame(simpleModel.getDeck(), 4, 4, false);
    state1 = new FreecellTextView(multiModel);
    multiModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 11, FOUNDATION, 0);
    assertEquals("F1: A♦, 2♦\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
    // tries multimove with an illegal build
    multiModel.move(CASCADE, 1, 11, FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveIncorrectValue() {
    multiModel.startGame(simpleModel.getDeck(), 4, 4, false);
    state1 = new FreecellTextView(multiModel);
    multiModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 11, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 10, FOUNDATION, 0);
    multiModel.move(CASCADE, 1, 12, FOUNDATION, 1);
    multiModel.move(CASCADE, 2, 12, CASCADE, 1);
    assertEquals("F1: A♦, 2♦, 3♦\n"
            + "F2: A♣\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♥\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
    // tries multimove with invalid numbers
    multiModel.move(CASCADE, 1, 11, CASCADE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveIncorrectColor() {
    multiModel.startGame(simpleModel.getDeck(), 4, 4, false);
    state1 = new FreecellTextView(multiModel);
    multiModel.move(CASCADE, 0, 12, FOUNDATION, 0);
    multiModel.move(CASCADE, 0, 11, FOUNDATION, 0);
    multiModel.move(CASCADE, 2, 12, FOUNDATION, 1);
    multiModel.move(CASCADE, 1, 12, CASCADE, 2);
    assertEquals("F1: A♦, 2♦\n"
            + "F2: A♥\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♣\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠", state1.toString());
    // tries multimove with invalid color
    multiModel.move(CASCADE, 2, 11, CASCADE, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveNullSource() {
    multiModel.startGame(multiModel.getDeck(), 4, 1, false);
    multiModel.move(null, 0, 12, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveNullDest() {
    multiModel.startGame(multiModel.getDeck(), 4, 1, false);
    multiModel.move(PileType.CASCADE, 0, 12, null, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultiMoveNullSourceAndDest() {
    multiModel.startGame(multiModel.getDeck(), 4, 1, false);
    multiModel.move(null, 0, 12, null, 0);
  }

}
