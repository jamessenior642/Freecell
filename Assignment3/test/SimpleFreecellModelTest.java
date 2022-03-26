import static cs3500.freecell.model.PileType.CASCADE;
import static cs3500.freecell.model.PileType.FOUNDATION;
import static cs3500.freecell.model.PileType.OPEN;
import static cs3500.freecell.model.Suit.CLUBS;
import static cs3500.freecell.model.Suit.DIAMONDS;
import static cs3500.freecell.model.Suit.HEARTS;
import static cs3500.freecell.model.Suit.SPADES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.SimpleFreecellModel;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


/**
 * test class for SimpleFreeCellModel.
 */
public class SimpleFreecellModelTest {

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
  //examples of models.
  SimpleFreecellModel model;
  SimpleFreecellModel model2;
  List<Card> deck;
  List<Card> custDeck;

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
    model2 = new SimpleFreecellModel();

    custDeck = Arrays.asList(kDiamonds, kClubs, kHearts, kSpades, qDiamonds, qClubs, qHearts,
            qSpades, jDiamonds, jClubs, jHearts, jSpades, tenDiamonds, tenClubs, tenHearts,
            tenSpades, nineDiamonds, nineClubs, nineHearts, nineSpades, eightDiamonds, eightClubs,
            eightHearts, eightSpades, sevenDiamonds, sevenClubs, sevenHearts, sevenSpades,
            sixDiamonds, sixClubs, sixHearts, sixSpades, fiveDiamonds, fiveClubs, fiveHearts,
            fiveSpades, fourDiamonds, fourClubs, fourHearts, fourSpades, threeDiamonds, threeClubs,
            threeHearts, threeSpades, twoDiamonds, twoClubs, twoHearts, twoSpades, aDiamonds,
            aClubs, aHearts, aSpades);
    deck = model.getDeck();
  }


  /**
   * tests for getDeck.
   */
  @Test
  public void testGetDeck() {
    List<Card> deck = model.getDeck();
    assertEquals(52, deck.size());
    assertEquals(deck.get(0), kDiamonds);
    assertEquals(deck.get(51), aSpades);
  }

  /**
   * tests for startGame.
   */
  @Test
  public void testStartGame() {
    List<Card> deck2 = model2.getDeck();
    model.startGame(deck, 4, 1, false);
    assertEquals(4, model.getNumCascadePiles());
    assertEquals(1, model.getNumOpenPiles());
    assertEquals(deck.get(0), model.getCascadeCardAt(0, 0));
    assertEquals(deck.get(1), model.getCascadeCardAt(1, 0));
    assertEquals(deck.get(2), model.getCascadeCardAt(2, 0));
    assertEquals(deck.get(4), model.getCascadeCardAt(0, 1));
    assertEquals(deck.get(8), model.getCascadeCardAt(0, 2));
    assertEquals(13, model.getNumCardsInCascadePile(0));
    assertEquals(13, model.getNumCardsInCascadePile(1));
    model2.startGame(deck2, 4, 1, true);
    assertNotEquals(deck.get(0), model2.getCascadeCardAt(0, 0));
  }

  /**
   * test errors for StartGame.
   */
  @Test(expected = IllegalArgumentException.class)
  public void duplicateDeckStart() {
    deck.add(qClubs);
    deck.remove(qHearts);
    model.startGame(deck, 4, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDeckStart() {
    deck.add(qClubs);
    model.startGame(deck, 4, 1, false);
  }


  @Test(expected = NullPointerException.class)
  public void testFoo1() {
    deck = null;
    model.startGame(deck, 4, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFoo2() {
    deck.remove(51);
    deck.remove(50);
    model.startGame(deck, 4, 1, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFoo3() {
    model.startGame(deck, 4, 0, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFoo4() {
    model.startGame(deck, 3, 1, false);
  }

  /**
   * test initial errors for move method.
   */
  @Test(expected = IllegalStateException.class)
  public void moveGameNoStart() {
    model.move(CASCADE, 2, 12, OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveNull1() {
    model.startGame(deck, 4, 1, false);
    model.move(null, 1, 12, OPEN, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveNull2() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 3, 12, null, 3);
  }

  /**
   * test move for open piles.
   */
  @Test
  public void moveOpen() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 3, 12, OPEN, 0);
    assertEquals(1, model.getNumCardsInOpenPile(0));
    assertEquals(aSpades, model.getOpenCardAt(0));
    assertEquals(12, model.getNumCardsInCascadePile(3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveOpenFullPile() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 2, 12, OPEN, 0);
    model.move(CASCADE, 2, 11, OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveOpenIncorrectPileIndex() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 2, 12, OPEN, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveOpenIncorrectPileIndex2() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 2, 12, OPEN, -4);
  }

  /**
   * test move for Cascade piles.
   */
  @Test
  public void moveCascade() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 2, 12, OPEN, 0);
    assertEquals(12, model.getNumCardsInCascadePile(2));
    model.move(CASCADE, 1, 12, CASCADE, 2);
    assertEquals(aClubs, model.getCascadeCardAt(2, 12));
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveCascadeSameColor() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 3, 12, OPEN, 0);
    model.move(CASCADE, 1, 12, CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveCascadeLessThan() {
    model.startGame(custDeck, 4, 2, false);
    model.move(CASCADE, 3, 12, OPEN, 0);
    model.move(CASCADE, 3, 11, OPEN, 1);
    model.move(CASCADE, 2, 12, CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveCascadeBiggerThan() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 3, 12, OPEN, 0);
    model.move(OPEN, 0, 12, CASCADE, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveCascadeIncorrectPileIndex() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 3, 12, OPEN, 0);
    model.move(CASCADE, 2, 12, CASCADE, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveCascadeIncorrectPileIndex2() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 1, 12, OPEN, 0);
    model.move(CASCADE, 3, 12, CASCADE, -3);
  }


  /**
   * test move for Foundation Piles.
   */
  @Test
  public void moveFoundation() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, FOUNDATION, 0);
    assertEquals(12, model.getNumCardsInCascadePile(0));
    assertEquals(aDiamonds, model.getFoundationCardAt(0, 0));
    model.move(CASCADE, 0, 11, FOUNDATION, 0);
    assertEquals(11, model.getNumCardsInCascadePile(0));
    assertEquals(2, model.getNumCardsInFoundationPile(0));
    assertEquals(twoDiamonds, model.getFoundationCardAt(0, 1));
  }


  @Test(expected = IllegalArgumentException.class)
  public void moveFoundationDifferentSuits() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, FOUNDATION, 0);
    model.move(CASCADE, 1, 12, OPEN, 0);
    model.move(CASCADE, 1, 11, FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFoundationFirstCard() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, OPEN, 0);
    model.move(CASCADE, 0, 11, FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFoundationWrongStacking() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, FOUNDATION, 0);
    model.move(CASCADE, 0, 11, OPEN, 0);
    model.move(CASCADE, 0, 10, FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveToFoundationIncorrectPileIndex() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 3, 12, FOUNDATION, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFoundationIncorrectPileIndex2() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 3, 12, FOUNDATION, 5);
  }



  /**
   * Move from errors.
   */
  @Test(expected = IllegalArgumentException.class)
  public void moveFFoundationIncorrectPileIndex() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 3, 12, FOUNDATION, -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFFoundationIncorrectPileIndex2() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 3, 12, FOUNDATION, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFCascadeWrongCard() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 11, OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFCascadeCardMissing() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, OPEN, 0);
    model.move(CASCADE, 0, 11, OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFromCascadeIncorrectCardIndex() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 3, 12, OPEN, 0);
    model.move(CASCADE, 2, 15, CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFromCascadeIncorrectCardIndex2() {
    model.startGame(deck, 4, 1, false);
    model.move(CASCADE, 3, 12, OPEN, 0);
    model.move(CASCADE, 2, -1, CASCADE, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFromOpenMissingCard() {
    model.startGame(deck, 4, 1, false);
    model.move(OPEN, 0, 0, OPEN, 1);
  }


  @Test(expected = IllegalArgumentException.class)
  public void moveFromOpenIncorrectPileIndex() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, OPEN, 0);
    model.move(OPEN, 5, 0, OPEN, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void moveFromOpenIncorrectPileIndex2() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, OPEN, 0);
    model.move(OPEN, -1, 0, OPEN, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void moveFFoundation() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, FOUNDATION, 0);
    model.move(FOUNDATION, 0, 0, OPEN, 0);
  }


  /**
   * tests end of the game.
   */
  @Test
  public void testIsGameOver() {
    model.startGame(custDeck, 4, 4, false);
    assertFalse(model.isGameOver());
    model.move(CASCADE, 0, 12, FOUNDATION, 0);
    model.move(CASCADE, 0, 11, FOUNDATION, 0);
    model.move(CASCADE, 0, 10, FOUNDATION, 0);
    model.move(CASCADE, 0, 9, FOUNDATION, 0);
    model.move(CASCADE, 0, 8, FOUNDATION, 0);
    model.move(CASCADE, 0, 7, FOUNDATION, 0);
    model.move(CASCADE, 0, 6, FOUNDATION, 0);
    model.move(CASCADE, 0, 5, FOUNDATION, 0);
    model.move(CASCADE, 0, 4, FOUNDATION, 0);
    model.move(CASCADE, 0, 3, FOUNDATION, 0);
    model.move(CASCADE, 0, 2, FOUNDATION, 0);
    model.move(CASCADE, 0, 1, FOUNDATION, 0);
    model.move(CASCADE, 0, 0, FOUNDATION, 0);
    model.move(CASCADE, 1, 12, FOUNDATION, 1);
    model.move(CASCADE, 1, 11, FOUNDATION, 1);
    model.move(CASCADE, 1, 10, FOUNDATION, 1);
    model.move(CASCADE, 1, 9, FOUNDATION, 1);
    model.move(CASCADE, 1, 8, FOUNDATION, 1);
    model.move(CASCADE, 1, 7, FOUNDATION, 1);
    model.move(CASCADE, 1, 6, FOUNDATION, 1);
    model.move(CASCADE, 1, 5, FOUNDATION, 1);
    model.move(CASCADE, 1, 4, FOUNDATION, 1);
    model.move(CASCADE, 1, 3, FOUNDATION, 1);
    model.move(CASCADE, 1, 2, FOUNDATION, 1);
    model.move(CASCADE, 1, 1, FOUNDATION, 1);
    model.move(CASCADE, 1, 0, FOUNDATION, 1);
    model.move(CASCADE, 2, 12, FOUNDATION, 2);
    model.move(CASCADE, 2, 11, FOUNDATION, 2);
    model.move(CASCADE, 2, 10, FOUNDATION, 2);
    model.move(CASCADE, 2, 9, FOUNDATION, 2);
    model.move(CASCADE, 2, 8, FOUNDATION, 2);
    model.move(CASCADE, 2, 7, FOUNDATION, 2);
    model.move(CASCADE, 2, 6, FOUNDATION, 2);
    model.move(CASCADE, 2, 5, FOUNDATION, 2);
    model.move(CASCADE, 2, 4, FOUNDATION, 2);
    model.move(CASCADE, 2, 3, FOUNDATION, 2);
    model.move(CASCADE, 2, 2, FOUNDATION, 2);
    model.move(CASCADE, 2, 1, FOUNDATION, 2);
    model.move(CASCADE, 2, 0, FOUNDATION, 2);
    model.move(CASCADE, 3, 12, FOUNDATION, 3);
    model.move(CASCADE, 3, 11, FOUNDATION, 3);
    model.move(CASCADE, 3, 10, FOUNDATION, 3);
    model.move(CASCADE, 3, 9, FOUNDATION, 3);
    model.move(CASCADE, 3, 8, FOUNDATION, 3);
    model.move(CASCADE, 3, 7, FOUNDATION, 3);
    model.move(CASCADE, 3, 6, FOUNDATION, 3);
    model.move(CASCADE, 3, 5, FOUNDATION, 3);
    model.move(CASCADE, 3, 4, FOUNDATION, 3);
    model.move(CASCADE, 3, 3, FOUNDATION, 3);
    model.move(CASCADE, 3, 2, FOUNDATION, 3);
    model.move(CASCADE, 3, 1, FOUNDATION, 3);
    model.move(CASCADE, 3, 0, FOUNDATION, 3);
    assertTrue(model.isGameOver());
  }


  /**
   * test for num cards in foundationPile.
   */
  @Test
  public void testNumberCardsInFoundationPile() {
    model.startGame(custDeck, 4, 1, false);
    assertEquals(0, model.getNumCardsInFoundationPile(0));
    model.move(CASCADE, 0, 12, FOUNDATION, 0);
    assertEquals(1, model.getNumCardsInFoundationPile(0));
  }

  @Test(expected = IllegalStateException.class)
  public void testNumberCardsInFoundationPileGameNotStarted() {
    model.getNumCardsInFoundationPile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumberCardsInFoundationPileSmallIndex() {
    model.startGame(custDeck, 4, 1, false);
    model.getNumCardsInFoundationPile(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumberCardsInFoundationPileLargeIndex() {
    model.startGame(custDeck, 4, 1, false);
    model.getNumCardsInFoundationPile(6);
  }

  @Test
  public void testNumberCascadePiles() {
    model.startGame(custDeck, 4, 3, false);
    assertEquals(4, model.getNumCascadePiles());
    model2.startGame(custDeck, 6, 1, false);
    assertEquals(6, model2.getNumCascadePiles());
  }

  @Test
  public void testNumberCascadePilesGameState() {
    assertEquals(-1, model.getNumCascadePiles());
  }

  @Test(expected = IllegalStateException.class)
  public void testNumberInCascPileGameState() {
    model.getNumCardsInCascadePile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumberCardsInCascPileSmallIndex() {
    model.startGame(custDeck, 4, 1, false);
    model.getNumCardsInCascadePile(-13);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumberCardsInCascPileLargeIndex() {
    model.startGame(custDeck, 4, 1, false);
    model.getNumCardsInCascadePile(6);
  }

  @Test
  public void testNumberCardsInCascPile() {
    model.startGame(custDeck, 4, 1, false);
    assertEquals(13, model.getNumCardsInCascadePile(1));
    model.move(CASCADE, 1, 12, OPEN, 0);
    assertEquals(12, model.getNumCardsInCascadePile(1));
  }

  @Test
  public void testNumberCardsInOpenPile() {
    model.startGame(custDeck, 4, 2, false);
    assertEquals(0, model.getNumCardsInOpenPile(0));
    model.move(CASCADE, 0, 12, OPEN, 0);
    assertEquals(1, model.getNumCardsInOpenPile(0));
    model.move(OPEN, 0, 0, FOUNDATION, 0);
    assertEquals(0, model.getNumCardsInOpenPile(0));
  }

  @Test(expected = IllegalStateException.class)
  public void testNumberCardsInOpenPileGameStart() {
    model.getNumCardsInOpenPile(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumberCardsInOpenPileSmallIndex() {
    model.startGame(custDeck, 4, 1, false);
    model.getNumCardsInOpenPile(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumberCardsInOpenPileLargeIndex() {
    model.startGame(custDeck, 4, 1, false);
    model.getNumCardsInOpenPile(6);
  }

  @Test
  public void testNumberOpenPiles() {
    model.startGame(custDeck, 5, 2, false);
    assertEquals(2, model.getNumOpenPiles());

    model2.startGame(deck, 4, 6, false);
    assertEquals(6, model2.getNumOpenPiles());
  }

  @Test
  public void testNumberOpenPilesGameState() {
    assertEquals(-1, model.getNumOpenPiles());
  }

  @Test
  public void testFoundationCardAt() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, FOUNDATION, 0);
    assertEquals(aDiamonds, model.getFoundationCardAt(0, 0));
    model.move(CASCADE, 0, 11, FOUNDATION, 0);
    assertEquals(twoDiamonds, model.getFoundationCardAt(0, 1));
  }

  @Test(expected = IllegalStateException.class)
  public void testFoundCardAtErrGameState() {
    model.getFoundationCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFoundCardAtMissingCard() {
    model.startGame(custDeck, 4, 1, false);
    model.getFoundationCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFoundCardAtLargePileIndex() {
    model.startGame(custDeck, 4, 1, false);
    model.getFoundationCardAt(5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFoundCardAtLargeCardIndex() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, FOUNDATION, 0);
    model.getFoundationCardAt(0, 1);
  }

  @Test
  public void getCascadeCardAt() {
    model.startGame(custDeck, 4, 1, false);
    assertEquals(kDiamonds, model.getCascadeCardAt(0, 0));
  }

  @Test(expected = IllegalStateException.class)
  public void testCascCardAtGameStartError() {
    model.getCascadeCardAt(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCascCardAtMissingCard() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, OPEN, 0);
    model.getCascadeCardAt(0, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCascCardAtLargePileIndex() {
    model.startGame(custDeck, 4, 1, false);
    model.getCascadeCardAt(6, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCascCardAtErrorLargeCardIndex() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, FOUNDATION, 0);
    model.getCascadeCardAt(0, 12);
  }

  @Test
  public void getOpenCardAt() {
    model.startGame(custDeck, 4, 1, false);
    model.move(CASCADE, 0, 12, OPEN, 0);
    assertEquals(aDiamonds, model.getOpenCardAt(0));
  }

  @Test
  public void testOpenCardAtMissing() {
    model.startGame(custDeck, 4, 1, false);
    assertEquals(null, model.getOpenCardAt(0));
  }

  @Test(expected = IllegalStateException.class)
  public void testOpenCardAtGameState() {
    model.getOpenCardAt(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOpenCardAtErrPileIndex() {
    model.startGame(custDeck, 4, 3, false);
    model.getOpenCardAt(4);
  }
}

















