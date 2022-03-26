import org.junit.Test;

import java.io.StringReader;
import java.util.List;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.MockController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.controller.Unappendable;
import cs3500.freecell.controller.Unreadable;
import cs3500.freecell.model.Card;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.SimpleFreecellModel;

import static cs3500.freecell.model.Suit.SPADES;
import static org.junit.Assert.assertEquals;

/**
 * JUnit test cases for the freecell controller.
 */
public class FreecellControllerTest {
  // your tests go here
  FreecellModel<Card> model = new SimpleFreecellModel();
  StringBuilder s;
  StringReader reader;
  FreecellController<Card> controller;


  /**
   * tests for constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    model = null;
    s = new StringBuilder();
    reader = new StringReader("C2 13 O1 Q");
    controller = new SimpleFreecellController(null, reader, s);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullReader() {
    s = null;
    reader = new StringReader("C2 13 O1 Q");
    controller = new SimpleFreecellController(model, reader, s);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullStringBuilder() {
    s = new StringBuilder();
    reader = null;
    controller = new SimpleFreecellController(model, reader, s);
  }

  /**
   * mock tests to check inputs.
   */
  @Test
  public void mockTestValidInputs() {
    s = new StringBuilder();
    reader = new StringReader("C2 13 O1 Q");
    controller = new MockController(model, reader, s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("Source pile index: C2\n"
            + "Card index: 13\n"
            + "Destination pile index: O1\n"
            + "Game quit prematurely.", s.toString());
  }

  /**
   * mock test to check error for source pile.
   */
  @Test
  public void mockTestSourceError() {
    s = new StringBuilder();
    reader = new StringReader("A C2 13 O1 Q");
    controller = new MockController(model, reader, s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("Invalid source pile index: A, enter again.\n"
            + "Source pile index: C2\n"
            + "Card index: 13\n"
            + "Destination pile index: O1\n"
            + "Game quit prematurely.", s.toString());
  }

  /**
   * mock test to check error for Card Index.
   */
  @Test
  public void mockTestCardError() {
    s = new StringBuilder();
    reader = new StringReader("C2 A 13 O1 Q");
    controller = new MockController(model, reader, s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("Source pile index: C2\n"
            + "Invalid card index: A, enter again.\n"
            + "Card index: 13\n"
            + "Destination pile index: O1\n"
            + "Game quit prematurely.", s.toString());
  }

  @Test
  public void mockTestDestError() {
    s = new StringBuilder();
    reader = new StringReader("C2 13 A O1 Q");
    controller = new MockController(model, reader, s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("Source pile index: C2\n"
            + "Card index: 13\n"
            + "Invalid destination pile: A, enter again.\n"
            + "Destination pile index: O1\n"
            + "Game quit prematurely.", s.toString());
  }

  @Test
  public void mockTestMultipleErrors() {
    s = new StringBuilder();
    reader = new StringReader("AA C2 BB 13 CC O1 Q");
    controller = new MockController(model, reader, s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("Invalid source pile index: AA, enter again.\n"
            + "Source pile index: C2\n"
            + "Invalid card index: BB, enter again.\n"
            + "Card index: 13\n"
            + "Invalid destination pile: CC, enter again.\n"
            + "Destination pile index: O1\n"
            + "Game quit prematurely.", s.toString());
  }


  /**
   * Tests that play game functions, with multiple moves before quitting.
   */
  @Test
  public void testPlayGameMultiValidMoves() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C2 13 O1 C1 13 F1 Q"),
            s);
    controller.playGame(model.getDeck(), 4, 1, false);
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
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "F1: A♦\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♣\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Game quit prematurely.", s.toString());
  }


  @Test
  public void testQuitGame() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("Q"),
            s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Game quit prematurely.", s.toString());
  }

  /**
   * tests errors for the piles.
   */
  @Test
  public void testInvalidSourcePile() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("AA C2 13 O1 Q"),
            s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Invalid source pile index: AA, enter again.\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♣\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Game quit prematurely.", s.toString());
  }

  @Test
  public void testInvalidCardIndex() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C2 AA 13 O1 Q"),
            s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Invalid card index: AA, enter again.\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♣\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Game quit prematurely.", s.toString());
  }

  @Test
  public void testInvalidDestinationIndex() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C2 13 AA O1 Q"),
            s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Invalid destination pile: AA, enter again.\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♣\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Game quit prematurely.", s.toString());
  }

  @Test
  public void testWaitForInputsComplete() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("AA 13 4 C2 13 O1 Q"), s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Invalid source pile index: AA, enter again.\n"
            + "Invalid source pile index: 13, enter again.\n"
            + "Invalid source pile index: 4, enter again.\n"
            + "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1: A♣\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Game quit prematurely.", s.toString());
  }


  @Test(expected = IllegalStateException.class)
  public void testNotEnoughInputs() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("AA 13 4 C2 13 O1"), s);
    controller.playGame(model.getDeck(), 4, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCantStartNullDeck() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C2 13 O1"), s);
    controller.playGame(null, 4, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCantStartInvalidDeck() {
    s = new StringBuilder();
    List<Card> thisDeck = model.getDeck();
    thisDeck.add(new Card(3, SPADES));
    controller = new SimpleFreecellController(model,
            new StringReader("C2 13 O1"), s);
    controller.playGame(thisDeck, 4, 1, false);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testCantStartNullModel() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(null,
            new StringReader("C2 13 O1"), s);
    controller.playGame(model.getDeck(), 4, 1, false);
  }

  @Test
  public void testCantStartCascadePiles() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C2 13 O1"), s);
    controller.playGame(model.getDeck(), 2, 1, false);
    assertEquals("Could not start game.", s.toString());
  }

  @Test
  public void testCantStartOpenPiles() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C2 13 O1"), s);
    controller.playGame(model.getDeck(), 4, 0, false);
    assertEquals("Could not start game.", s.toString());
  }


  @Test(expected = IllegalStateException.class)
  public void testNoQuit() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C1 13 F1"), s);
    controller.playGame(model.getDeck(), 4, 1, false);
  }


  @Test(expected = IllegalStateException.class)
  public void testNoQuit2() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C1"), s);
    controller.playGame(model.getDeck(), 4, 1, false);
  }


  @Test(expected = IllegalStateException.class)
  public void testNoInputsCantStart() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader(""), s);
    controller.playGame(model.getDeck(), 4, 1, false);
  }

  @Test(expected = IllegalStateException.class)
  public void testSpaceInputsCantStart() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader(" "), s);
    controller.playGame(model.getDeck(), 4, 1, false);
  }

  /**
   * tests for IO Exceptions.
   */
  @Test(expected = IllegalStateException.class)
  public void testIOExceptionAppendable() {
    Appendable a = new Unappendable();
    controller = new SimpleFreecellController(model,
            new StringReader("C2"), a);
    controller.playGame(model.getDeck(), 4, 1, false);
  }


  @Test(expected = IllegalStateException.class)
  public void testIOExceptionReadable() {
    s = new StringBuilder();
    Readable r = new Unreadable();
    controller = new SimpleFreecellController(model,
            r, s);
    controller.playGame(model.getDeck(), 4, 1, false);
  }

  @Test
  public void testInvalidMoveSourcePileIndex() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C6 13 O1 Q"), s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Move invalid, try again\n"
            + "Game quit prematurely.", s.toString());
  }

  @Test
  public void testInvalidMoveDestPileIndex() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C2 13 O4 Q"), s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Move invalid, try again\n"
            + "Game quit prematurely.", s.toString());
  }

  @Test
  public void testInvalidMoveCardIndex() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C2 20 O1 Q"), s);
    controller.playGame(model.getDeck(), 4, 1, false);
    assertEquals("F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
            + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
            + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Move invalid, try again\n"
            + "Game quit prematurely.", s.toString());
  }

  @Test
  public void testGameRules() {
    s = new StringBuilder();
    controller = new SimpleFreecellController(model,
            new StringReader("C2 13 O1 C2 12 O1 Q"), s);
    controller.playGame(model.getDeck(), 4, 1, false);
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
            + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
            + "Move invalid, try again\n"
            + "Game quit prematurely.", s.toString());
  }


  /**
   * test to check when the game ends.
   */
  @Test
  public void testEndOfGame() {
    s = new StringBuilder();
    FreecellController<Card> control = new SimpleFreecellController(model,
            new StringReader(
                    "C1 13 F1 C1 12 F1 C1 11 F1 C1 10 F1 C1 9 F1 C1 8 F1 C1 7 F1 C1 6 F1 "
                            + "C1 5 F1 C1 4 F1 C1 3 F1 C1 2 F1 C1 1 F1 "
                            + "C2 13 F2 C2 12 F2 C2 11 F2 C2 10 F2 C2 9 F2 C2 8 F2 C2 7 F2 C2 6 F2 "
                            + "C2 5 F2 C2 4 F2 C2 3 F2 C2 2 F2 C2 1 F2 "
                            + "C3 13 F3 C3 12 F3 C3 11 F3 C3 10 F3 C3 9 F3 C3 8 F3 C3 7 F3 C3 6 F3 "
                            + "C3 5 F3 C3 4 F3 C3 3 F3 C3 2 F3 C3 1 F3 "
                            + "C4 13 F4 C4 12 F4 C4 11 F4 C4 10 F4 C4 9 F4 C4 8 F4 C4 7 F4 C4 6 F4 "
                            + "C4 5 F4 C4 4 F4 C4 3 F4 C4 2 F4 C4 1 F4"), s);
    control.playGame(model.getDeck(), 4, 1, false);
    assertEquals(// cascade pile 1:
            "F1:\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦, J♦, 10♦, 9♦, 8♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦, J♦, 10♦, 9♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦, J♦, 10♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦, J♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦, Q♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1: K♦\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2:\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, A♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    //cascade pile 2
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣, 8♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣, J♣, 10♣, 9♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣, J♣, 10♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣, J♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣, Q♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2: K♣\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3:\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    // cascade pile 3
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥, 8♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥, J♥, 10♥, 9♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥, J♥, 10♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥, J♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥, Q♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3: K♥\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4:\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠, A♠\n"
                    //cascade pile 4
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠, 3♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠, 3♠, 4♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠, 3♠, 4♠, 5♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠, 8♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠, J♠, 10♠, 9♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠, J♠, 10♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠, J♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠, Q♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4: K♠\n"
                    + "F1: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
                    + "F2: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
                    + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
                    + "F4: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
                    + "O1:\n"
                    + "C1:\n"
                    + "C2:\n"
                    + "C3:\n"
                    + "C4:\n"
                    + "Game over.", s.toString());
  }


}
