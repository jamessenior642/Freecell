package cs3500.freecell.model;

import cs3500.freecell.model.multimove.MultiMoveFreecellModel;

/**
 * factory class that creates either a Simple or Multi-Move game of FreeCell.
 */
public class FreecellModelCreator {

  /**
   * enum representing the two different game types.
   * <br> SingleMove
   * <br> MultiMove
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }


  /**
   * creates either a multi move or simple game.
   *
   * @param type the model type being created
   * @return the Freecell Model of the given type
   */
  public static FreecellModel create(GameType type) {
    if (type == null) {
      throw new IllegalArgumentException("Type cannot be null.");
    }
    switch (type) {
      case SINGLEMOVE:
        return new SimpleFreecellModel();
      case MULTIMOVE:
        return new MultiMoveFreecellModel();
      default:
        return null;
    }
  }

}
