package cs3500.freecell.model.multimove;

import java.util.ArrayList;
import java.util.List;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.SimpleFreecellModel;


/**
 * class representing a version of the Freecell game
 * where multiple cards can be moved at once.
 */
public class MultiMoveFreecellModel extends SimpleFreecellModel {

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) {
    List<Card> movePile;
    if (!gameStarted) {
      throw new IllegalStateException("Game was not started yet");
    }
    if (source == null || destination == null) {
      throw new IllegalArgumentException("Given arguments can't be null.");
    }
    if (getPiles(source).get(pileNumber) == null
            || getPiles(source).get(pileNumber).get(cardIndex) == null
            || getPiles(destination).get(destPileNumber) == null) {
      throw new IllegalArgumentException("Given piles cannot be null.");
    }
    switch (source) {
      case OPEN:
        super.move(source, pileNumber, cardIndex, destination, destPileNumber);
        break;
      case FOUNDATION:
        throw new IllegalArgumentException("Can't move from foundation pile.");
      case CASCADE:
        if (!haveSpace((cascPiles.get(pileNumber).size()) - cardIndex)) {
          throw new IllegalArgumentException("Not enough slots available.");
        }
        try {
          movePile = getMovePile(pileNumber, cardIndex);
        } catch (IllegalArgumentException iae) {
          throw new IllegalArgumentException("Invalid build.");
        }
        Card f = movePile.get(0);
        if (movePile.size() == 1) {
          super.move(PileType.CASCADE, pileNumber, cardIndex, destination, destPileNumber);
        } else if (destination.equals(PileType.CASCADE)) {
          if (cascPiles.get(destPileNumber).size() == 0) {
            moveAllCasc(pileNumber, destPileNumber, movePile);
            return;
          }
          Card topCardDest = cascPiles.get(destPileNumber)
                  .get(cascPiles.get(destPileNumber).size() - 1);
          if ((f.getSuit().getColor() != topCardDest.getSuit().getColor())
                  && (f.getRank() - topCardDest.getRank() == -1)) {
            moveAllCasc(pileNumber, destPileNumber, movePile);
          } else {
            throw new IllegalArgumentException("Move Invalid.");
          }
        } else {
          throw new IllegalArgumentException(
                  "Can't MultiMove to foundation or open piles.");
        }
        break;
      default:
        throw new IllegalArgumentException("Source Pile Invalid.");
    }
  }


  private List<List<Card>> getPiles(PileType p) {
    switch (p) {
      case OPEN:
        return openPiles;
      case CASCADE:
        return cascPiles;
      case FOUNDATION:
        return foundationPiles;
      default:
        throw new IllegalArgumentException("Invalid PileType Given.");
    }
  }


  private List<Card> getMovePile(int pileNumber, int cardIndex) {
    List<Card> movePile = new ArrayList<>();
    for (int i = cardIndex; i < cascPiles.get(pileNumber).size(); i++) {
      movePile.add(cascPiles.get(pileNumber).get(i));
    }
    if (buildValid(movePile)) {
      return movePile;
    } else {
      throw new IllegalArgumentException("Not a Valid Build.");
    }
  }


  private boolean haveSpace(int cards) {
    int openEmpties = 0;
    int cascEmpties = 0;
    double moves;
    for (List<Card> o : openPiles) {
      if (o.size() == 0) {
        openEmpties++;
      }
    }
    for (List<Card> c : cascPiles) {
      if (c.size() == 0) {
        cascEmpties++;
      }
    }
    moves = (openEmpties + 1) * Math.pow(2, cascEmpties);
    return (cards <= moves);
  }


  private void moveAllCasc(int pileNumber, int destPileNumber, List<Card> movePile) {
    cascPiles.get(destPileNumber).addAll(movePile);
    cascPiles.get(pileNumber).removeAll(movePile);
  }


  private boolean buildValid(List<Card> movePile) {
    boolean valid = false;
    if (movePile.size() == 1) {
      return true;
    }
    for (int i = 1; i < movePile.size(); i++) {
      valid = (movePile.get(i).getRank() - (movePile.get(i - 1).getRank()) == -1)
              && !(movePile.get(i).getSuit().getColor()
              .equals(movePile.get(i - 1).getSuit().getColor()));
    }
    return valid;
  }


}

