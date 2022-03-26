package cs3500.freecell.model;


/**
 * Type for the Four suits of cards.
 * <br> Diamonds
 * <br> Clubs
 * <br> Hearts
 * <br> Spades
 */
public enum Suit {
  DIAMONDS("♦", "Red"), CLUBS("♣", "Black"),
  HEARTS("♥", "Red"), SPADES("♠", "Black");

  private final String label;
  private final String color;

  /**
   * Constructor for Suit.
   *
   * @param label symbol of the suit
   * @param color color of the suit
   */
  Suit(String label, String color) {
    this.label = label;
    this.color = color;
  }

  /**
   * Override toString.
   *
   * @return the symbol of the suit
   */
  @Override
  public String toString() {
    return label;
  }


  /**
   * Get the color of the Suit.
   */
  public String getColor() {
    return color;
  }

}


