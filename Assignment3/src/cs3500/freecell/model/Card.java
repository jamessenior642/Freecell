package cs3500.freecell.model;


import java.util.Objects;

/**
 * Class representing a standard playing card.
 */
public class Card {
  private final int rank;
  private final Suit suit;

  /**
   * Constructor for Card.
   *
   * @param r the value of the card
   * @param s the suit of the card
   * @throws IllegalArgumentException if Card value is less than 1 or greater than 13
   */
  public Card(int r, Suit s) {
    if (r > 13 || r < 1) {
      throw new IllegalArgumentException("Card is invalid.");
    }
    this.rank = r;
    this.suit = s;
  }


  /**
   * returns the rank of the card.
   *
   * @return the rank of the card.
   */
  public int getRank() {
    return rank;
  }


  /**
   * returns the suit.
   *
   * @return suit of the card.
   */
  public Suit getSuit() {
    return suit;
  }

  /**
   * returns the color.
   *
   * @return color of the card.
   */
  public String getColor() {
    return getSuit().getColor();
  }


  /**
   * returns this card as a String.
   *
   * @return the card as a string
   */
  @Override
  public String toString() {
    if (rank == 1) {
      return "A" + this.suit.toString();
    } else if (rank == 11) {
      return "J" + this.suit.toString();
    } else if (rank == 12) {
      return "Q" + this.suit.toString();
    } else if (rank == 13) {
      return "K" + this.suit.toString();
    } else {
      return rank + this.suit.toString();
    }
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Card card = (Card) o;
    return rank == card.rank && suit == card.suit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(rank, suit);
  }


  /**
   * returns true if the card is valid.
   *
   * @return true if this card is valid.
   */
  public boolean validCard() {
    return true;
  }
}
