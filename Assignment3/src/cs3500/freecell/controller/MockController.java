package cs3500.freecell.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.freecell.model.Card;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;


/**
 * class representing a mock for the SimpleFreecellController.
 */
public class MockController implements FreecellController<Card> {

  private final Scanner scanner;
  private final Appendable app;
  private FreecellModel<Card> model;

  /**
   * Constructor for the mock controller.
   *
   * @param model the model being taken in.
   * @param rd    the readable for the controller.
   * @param ap    the appendable given to the controller.
   * @throws IllegalArgumentException if any arguments are null.
   */
  public MockController(FreecellModel<Card> model, Readable rd, Appendable ap)
          throws IllegalArgumentException {
    if (model == null || rd == null || ap == null) {
      throw new IllegalArgumentException("Arguments can't be null.");
    }
    this.scanner = new Scanner(rd);
    this.model = model;
    this.app = ap;
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
        app.append("Could not start game.");
        return;
      }
      //sets input to be checked.
      int i = 0;
      while (scanner.hasNext()) {
        String in = scanner.next();
        if (in.equalsIgnoreCase("Q")) {
          app.append("Game quit prematurely.");
          return;
        }
        //checks the first input and sets it to source
        if (i % 3 == 0) {
          try {
            givePile(in);
            givePIndex(in);
            app.append("Source pile index: " + in + "\n");
          } catch (IllegalArgumentException ia) {
            app.append("Invalid source pile index: " + in + ", enter again.\n");
            continue;
          }
        }
        // checks the second input and sets it to the card index
        if (i % 3 == 1) {
          try {
            Integer.parseInt(in);
            givePIndex(in);
            app.append("Card index: " + in + "\n");
          } catch (NumberFormatException nf) {
            app.append("Invalid card index: " + in + ", enter again.\n");
            continue;
          }
        }
        // checks the third input and sets it to the destination
        if (i % 3 == 2) {
          try {
            givePile(in);
            givePIndex(in);
            app.append("Destination pile index: " + in + "\n");
          } catch (IllegalArgumentException ia) {
            app.append("Invalid destination pile: " + in + ", enter again.\n");
            continue;
          }
        }
        i++;
        if (model.isGameOver()) {
          app.append("Game over.");
          break;
        }
      }
    } catch (IOException io) {
      throw new IllegalStateException("Could not append.");
    }
    // gives error message if not enough inputs are given
    if (!(model.isGameOver() || scanner.hasNext())) {
      throw new IllegalStateException("Ran out of inputs however game has not ended.");
    }
  }

  private boolean validDeck(List<Card> deck) {
    return deck != null && !anyDupes(deck) && deck.size() == 52;
  }

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

  private PileType givePile(String p) {
    char f = p.charAt(0);
    switch (f) {
      case 'F':
        return PileType.FOUNDATION;
      case 'C':
        return PileType.CASCADE;
      case 'O':
        return PileType.OPEN;
      default:
        throw new IllegalArgumentException("Invalid PileType Given: " + p);
    }
  }

  private int givePIndex(String ip) throws IllegalArgumentException {
    String index = "";
    for (int i1 = 1; i1 < ip.length(); i1++) {
      index = index + ip.charAt(i1);
    }
    try {
      return Integer.parseInt(index);
    } catch (NumberFormatException nf) {
      throw new IllegalArgumentException("Invalid Pile Index Given.");
    }
  }

}
