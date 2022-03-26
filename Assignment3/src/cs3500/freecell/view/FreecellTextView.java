package cs3500.freecell.view;

import java.io.IOException;

import cs3500.freecell.model.FreecellModelState;
import cs3500.freecell.model.PileType;

import static cs3500.freecell.model.PileType.CASCADE;
import static cs3500.freecell.model.PileType.FOUNDATION;
import static cs3500.freecell.model.PileType.OPEN;

/**
 * class representing the text view of the freeCell game.
 */
public class FreecellTextView implements FreecellView {
  private final FreecellModelState<?> state;
  private Appendable appendable;

  /**
   * constructor for the text view of FreeCell.
   *
   * @param model takes in the FreeCell model
   */
  public FreecellTextView(FreecellModelState<?> model) {
    if (model == null) {
      throw new IllegalArgumentException("");
    }
    this.state = model;
  }

  /**
   * constructor for the View that takes in an appendable.
   *
   * @param model takes in the FreeCell model
   * @param ap appendable object
   */
  public FreecellTextView(FreecellModelState<?> model, Appendable ap) {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("");
    }
    this.state = model;
    this.appendable = ap;
  }

  @Override
  public String toString() {
    if (state.getNumCascadePiles() == -1) {
      return "";
    }
    else {
      String fin = "";
      fin += toStringHelpOne(FOUNDATION) + "\n" + toStringHelpOne(OPEN) + "\n"
              + toStringHelpOne(CASCADE);
      return fin;
    }
  }


  /**
   * first helper method for toString.
   *
   * @param pile takes in the pile to be Stringified
   * @return the Pile as a string
   */
  private String toStringHelpOne(PileType pile) {
    String init;
    String fin = "";
    int pileCount;
    int totalP;
    switch (pile) {
      case OPEN:
        init = "O";
        pileCount = state.getNumOpenPiles();
        totalP = state.getNumOpenPiles();
        break;
      case CASCADE:
        init = "C";
        pileCount = state.getNumCascadePiles();
        totalP = state.getNumCascadePiles();
        break;
      case FOUNDATION:
        init = "F";
        pileCount = 4;
        totalP = 4;
        break;
      default:
        init = "";
        pileCount = 0;
        totalP = 0;
    }
    for (int i = 0; i < pileCount; i++) {
      int cardCount;
      switch (pile) {
        case OPEN:
          cardCount = state.getNumCardsInOpenPile(i);
          break;
        case CASCADE:
          cardCount = state.getNumCardsInCascadePile(i);
          break;
        case FOUNDATION:
          cardCount = state.getNumCardsInFoundationPile(i);
          break;
        default:
          cardCount = 0;
      }
      String fin2 = "";
      if (cardCount == 0 && i != totalP - 1) {
        fin2 = fin2 + "\n";
      }
      for (int j = 0; j < cardCount; j++) {
        String cur;
        switch (pile) {
          case OPEN:
            cur = state.getOpenCardAt(i).toString();
            break;
          case CASCADE:
            cur = state.getCascadeCardAt(i, j).toString();
            break;
          case FOUNDATION:
            cur = state.getFoundationCardAt(i, j).toString();
            break;
          default:
            cur = "";
        }
        if (j == cardCount - 1 && i == totalP - 1) {
          fin2 = fin2 + " " + cur;
        } else if (j == cardCount - 1) {
          fin2 = fin2 + " " + cur + "\n";
        } else {
          fin2 = fin2 + " " + cur + ",";
        }
      }
      fin = fin + init + (i + 1) + ":" + fin2;
    }
    return fin;
  }

  @Override
  public void renderBoard() throws IOException {
    if (appendable == null) {
      System.out.println(state);
    }
    appendable.append(this + "\n");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    if (appendable == null) {
      System.out.println(message);
    }
    if (message == null) {
      return;
    }
    appendable.append(message);
  }
}

