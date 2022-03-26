package cs3500.freecell.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.view.FreecellTextView;

/**
 * class representing an implementation of the FreeCellController interface.
 */
public class SimpleFreecellController implements FreecellController<Card> {
  private final FreecellTextView view;
  private final Scanner scanner;
  private FreecellModel<Card> model;
  //keeps track of parameters as fields
  private PileType sourceP;
  private int sourcePIndex;
  private int cardIndex;
  private PileType destP;
  private int destPI;

  /**
   * constructor for a Simple Freecell Controller.
   * @param model the model being taken in
   * @param rd the readable
   * @param ap the appendable
   * @throws IllegalArgumentException if the given arguments are null
   */
  public SimpleFreecellController(FreecellModel<Card> model, Readable rd, Appendable ap) {
    if (model == null || rd == null || ap == null) {
      throw new IllegalArgumentException("Argument(s) are null.");
    }
    this.model = model;
    this.scanner = new Scanner(rd);
    view = new FreecellTextView(model, ap);
  }

  @Override
  public void playGame(List<Card> deck, int numCascades, int numOpens, boolean shuffle) {
    if (!validDeck(deck)) {
      throw new IllegalArgumentException("Deck is invalid.");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model is null.");
    }
    try {
      // starts game if possible.
      try {
        model.startGame(deck, numCascades, numOpens, shuffle);
      } catch (IllegalArgumentException ia) {
        view.renderMessage("Could not start game.");
        return;
      }
      //renders initial board
      view.renderBoard();
      //sets input to be checked.
      int inpNumber = 0;
      while (scanner.hasNext()) {
        String in = scanner.next();
        if (in.equalsIgnoreCase("Q")) {
          view.renderMessage("Game quit prematurely.");
          return;
        }
        //checks the first input and sets it to source
        if (inpNumber % 3 == 0) {
          try {
            sourceP = givePile(in);
            sourcePIndex = givePIndex(in) - 1;
          } catch (IllegalArgumentException ia) {
            view.renderMessage("Invalid source pile index: " + in + ", enter again.\n");
            continue;
          }
        }
        // checks the second input and sets it to the card index
        if (inpNumber % 3 == 1) {
          try {
            cardIndex = Integer.parseInt(in) - 1;
          } catch (NumberFormatException nf) {
            view.renderMessage("Invalid card index: " + in + ", enter again.\n");
            continue;
          }
        }
        // checks the third input and sets it to the destination
        if (inpNumber % 3 == 2) {
          try {
            destP = givePile(in);
            destPI = givePIndex(in) - 1;
          } catch (IllegalArgumentException ia) {
            view.renderMessage("Invalid destination pile: " + in + ", enter again.\n");
            continue;
          }
          // executes move if possible
          try {
            model.move(sourceP, sourcePIndex, cardIndex, destP, destPI);
          } catch (NullPointerException np) {
            throw new IllegalArgumentException("Source or destination can't be null.");
          } catch (IllegalStateException is) {
            throw new IllegalStateException("Game has not been started.");
          } catch (IllegalArgumentException ia) {
            view.renderMessage("Move invalid, try again\n");
            inpNumber++;
            reInputs();
            continue;
          }
          // renders the moved board and resets the inputs
          view.renderBoard();
          reInputs();
        }
        inpNumber++;
        // message for when game is over
        if (model.isGameOver()) {
          view.renderMessage("Game over.");
          break;
        }
      }
    } catch (IOException io) {
      throw new IllegalStateException("Could not append.");
    }
    // gives error message if not enough inputs are given
    if (!model.isGameOver()) {
      throw new IllegalStateException("Ran out of inputs however game has not ended.");
    }
  }


  private boolean validDeck(List<Card> deck) {
    return deck != null && !anyDupes(deck) && deck.size() == 52  && allValid(deck);
  }

  private boolean anyDupes(List<Card> deck) {
    List<Card> soFar = new ArrayList<>();
    for (Card c : deck) {
      if (!soFar.contains(c)) {
        soFar.add(c);
      } else {
        return true;
      }
    }
    return false;
  }

  private boolean allValid(List<Card> deck) {
    List<Card> soFar = new ArrayList<>();
    for (Card c : deck) {
      if (!c.validCard()) {
        soFar.add(c);
      } else {
        return true;
      }
    }
    return false;
  }


  private PileType givePile(String p) {
    char f = p.charAt(0);
    switch (f) {
      case 'O':
        return PileType.OPEN;
      case 'C':
        return PileType.CASCADE;
      case 'F':
        return PileType.FOUNDATION;
      default:
        throw new IllegalArgumentException("Invalid PileType Given: " + p);
    }
  }

  private int givePIndex(String i) throws IllegalArgumentException {
    String index = i.substring(1);
    try {
      return Integer.parseInt(index);
    }
    catch (NumberFormatException nf) {
      throw new IllegalArgumentException("Invalid Pile Index Given.");
    }
  }

  private void reInputs() {
    sourcePIndex = 0;
    sourceP = null;
    cardIndex = 0;
    destP = null;
    destPI = 0;
  }

}



