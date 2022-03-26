package cs3500.freecell.controller;

import java.io.IOException;

/**
 * class representing an unappendable appendable that
 * always throws an IO Exception.
 */
public class Unappendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Could not append.");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Could not append.");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Could not append.");
  }
}
