package cs3500.freecell.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static cs3500.freecell.model.PileType.CASCADE;
import static cs3500.freecell.model.PileType.FOUNDATION;



/**
 * class representing a Simple Freecell Model.
 * Implementation of the FreecellModel Interface that seta parameters for the game.
 */
public class SimpleFreecellModel implements FreecellModel<Card> {
  protected List<List<Card>> cascPiles;
  protected List<List<Card>> openPiles;
  protected List<List<Card>> foundationPiles;
  protected boolean gameStarted;

  /**
   * constructs a SimpleFreeCellModel that has piles of Cards.
   */
  public SimpleFreecellModel() {
    this.cascPiles = new ArrayList<>();
    this.openPiles = new ArrayList<>();
    this.foundationPiles = new ArrayList<>();
    this.gameStarted = false;
  }


  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<>();
    for (int i = 13; i > 0; i--) {
      for (Suit s : Suit.values()) {
        deck.add(new Card(i, s));
      }
    }
    return deck;
  }


  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {
    cascPiles = new ArrayList<>();
    openPiles = new ArrayList<>();
    foundationPiles = new ArrayList<>();
    if (!validDeck(deck)) {
      throw new IllegalArgumentException("given deck is invalid");
    }
    if (numOpenPiles < 1 || numCascadePiles < 4) {
      throw new IllegalArgumentException("incorrect number of piles.");
    }
    for (int i = 0; i < numCascadePiles; i++) {
      cascPiles.add(new ArrayList<>());
    }
    for (int i = 0; i < numOpenPiles; i++) {
      openPiles.add(new ArrayList<>());
    }
    for (int i = 0; i < 4; i++) {
      foundationPiles.add(new ArrayList<>());
    }
    if (shuffle) {
      Collections.shuffle(deck);
    }
    for (int i = 0; i < deck.size(); i++) {
      cascPiles.get(i % numCascadePiles).add(deck.get(i));
    }
    gameStarted = true;
  }

  /**
   * checks if the deck is valid.
   *
   * @param deck the given deck of cards
   * @return true if the deck is valid
   */
  private boolean validDeck(List<Card> deck) {
    String values = "1 2 3 4 5 6 7 8 9 10 11 12 13";
    String suits = "♦ ♣ ♥ ♠";
    for (Card c : deck) {
      if (!values.contains(String.valueOf(c.getRank()))
              || !suits.contains(c.getSuit().toString())) {
        return false;
      }
    }
    return deck != null && !anyDupes(deck) && deck.size() == 52;
  }

  /**
   * checks if the deck has any duplicate cards.
   *
   * @param deck the given deck of cards
   * @return true if there are any duplicate cards in the deck
   */
  private boolean anyDupes(List<Card> deck) {
    List<Card> soFar = new ArrayList();
    for (Card c : deck) {
      if (!soFar.contains(c)) {
        soFar.add(c);
      } else {
        return true;
      }
    }
    return false;
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) {
    if (!gameStarted) {
      throw new IllegalStateException("Game was not started yet");
    }
    if (source == null || destination == null) {
      throw new IllegalArgumentException("piletype cant be null.");
    }
    List<List<Card>> f;
    List<List<Card>> t;
    f = initLists(source);
    t = initLists(destination);
    if (firstTest(pileNumber, f, t, cardIndex, destPileNumber)) {
      if (f.get(pileNumber) == null || f.get(pileNumber).get(cardIndex) == null
              || t.get(destPileNumber) == null) {
        throw new IllegalArgumentException("given List can't be null.");
      }
      if (invalidSource(source, cardIndex, f, pileNumber)) {
        throw new IllegalArgumentException("move isn't possible.");
      }
      if (validPile(f.get(pileNumber).get(cardIndex),
              t.get(destPileNumber), destination)) {
        t.get(destPileNumber).add(f.get(pileNumber).remove(cardIndex));
      }
      else {
        throw new IllegalArgumentException("move isn't possible.");
      }
    }
    else {
      throw new IllegalArgumentException("move isn't possible.");
    }
  }

  /**
   * Helper to initialize lists for move method.
   *
   * @param p the type of pile being used
   * @return the list of piles for the given PileType.
   */
  private List<List<Card>> initLists(PileType p) {
    List<List<Card>> f;
    switch (p) {
      case CASCADE:
        f = cascPiles;
        break;
      case OPEN:
        f = openPiles;
        break;
      case FOUNDATION:
        f = foundationPiles;
        break;
      default:
        f = new ArrayList<>();
    }
    return f;
  }

  private boolean firstTest(int pileNumber, List<List<Card>> f, List<List<Card>> t,
                            int cardIndex, int destPileNumber) {
    return pileNumber < f.size() && pileNumber >= 0 && cardIndex < f.get(pileNumber).size()
            && cardIndex >= 0 && destPileNumber < t.size() && destPileNumber >= 0;
  }

  private boolean invalidSource(PileType source, int cardIndex,
                                List<List<Card>> f, int pileNumber) {
    return (source.equals(CASCADE) && cardIndex != f.get(pileNumber).size() - 1)
            || (source.equals(FOUNDATION));
  }


  /**
   * checks the conditions of the current pile to see if card can be moved there.
   *
   * @param current     the current card
   * @param destination the destination card
   * @param pile        the type of pile
   * @return true if the pile can accept a card
   */
  private boolean validPile(Card current, List<Card> destination, PileType pile) {
    switch (pile) {
      case CASCADE:
        if (destination.size() == 0) {
          return true;
        }
        else {
          return (destination.size() > 0
                  && current.getColor() != destination.get(destination.size() - 1)
                  .getColor())
                  && (destination.get(destination.size() - 1).getRank()) - current.getRank() == 1;
        }
      case OPEN:
        return destination.size() == 0;
      case FOUNDATION:
        if (destination.size() == 0 && current.getRank() == 1) {
          return true;
        }
        else {
          return (destination.size() > 0
                  && current.getRank() - (destination.get(destination.size() - 1).getRank()) == 1
                  && current.getSuit().equals(destination.get(destination.size() - 1).getSuit()));
        }
      default:
        return false;
    }
  }

  @Override
  public boolean isGameOver() {
    for (List<Card> foundationPile : foundationPiles) {
      if (foundationPile.size() != 13) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int getNumCardsInFoundationPile(int pileIndex) {
    return getCardsInPileAbst(foundationPiles, pileIndex);
  }

  @Override
  public int getNumCascadePiles() {
    return getPileCountAbst(cascPiles);
  }

  @Override
  public int getNumCardsInCascadePile(int pileIndex) {
    return getCardsInPileAbst(cascPiles, pileIndex);
  }


  @Override
  public int getNumCardsInOpenPile(int pileIndex) {
    return getCardsInPileAbst(openPiles, pileIndex);
  }

  @Override
  public int getNumOpenPiles() {
    return getPileCountAbst(openPiles);
  }

  /**
   * Get the number of cards in a given pile.
   *
   * @param ind the index of the pile, starting at 0
   * @return the number of cards in the given pile
   * @throws IllegalArgumentException if the provided index is invalid
   * @throws IllegalStateException    if the game has not started
   */
  private int getCardsInPileAbst(List<List<Card>> p, int ind) {
    if (!gameStarted) {
      throw new IllegalStateException("Game has not started yet");
    }
    if (ind < 0 || ind > p.size() - 1) {
      throw new IllegalArgumentException("Invalid index");
    }
    return p.get(ind).size();
  }

  /**
   * Get the number of piles in a given type.
   *
   * @param pile the pile type
   */
  private int getPileCountAbst(List<List<Card>> pile) {
    if (!gameStarted) {
      return -1;
    }
    return pile.size();
  }

  @Override
  public Card getFoundationCardAt(int pileIndex, int cardIndex) {
    return getCard(pileIndex, cardIndex, foundationPiles);
  }


  @Override
  public Card getCascadeCardAt(int pileIndex, int cardIndex) {
    return getCard(pileIndex, cardIndex, cascPiles);
  }

  /**
   * Helper method to get the given card in any pile.
   *
   * @param pileIndex the index of the cascade pile, starting at 0
   * @param cardIndex the index of the card in the above cascade pile, starting at 0
   * @param pileList  the type of pile being used
   * @return the card at the provided indices
   * @throws IllegalArgumentException if the pileIndex or cardIndex is invalid
   * @throws IllegalStateException    if the game has not started
   */
  private Card getCard(int pileIndex, int cardIndex, List<List<Card>> pileList) {
    if (!gameStarted) {
      throw new IllegalStateException("Game hasnt started yet");
    }
    if (pileIndex < 0 || pileIndex > pileList.size() - 1) {
      throw new IllegalArgumentException("Invalid pile index");
    }
    if (cardIndex < 0 || cardIndex > pileList.get(pileIndex).size() - 1) {
      throw new IllegalArgumentException("Invalid card index");
    }
    return pileList.get(pileIndex).get(cardIndex);
  }


  @Override
  public Card getOpenCardAt(int pileIndex) {
    if (!gameStarted) {
      throw new IllegalStateException("Game hasnt started yet");
    }
    if (pileIndex < 0 || pileIndex > openPiles.size() - 1) {
      throw new IllegalArgumentException("Invalid pile index");
    }
    if (openPiles.get(pileIndex).size() == 0) {
      return null;
    } else {
      return openPiles.get(pileIndex).get(0);
    }
  }
}

