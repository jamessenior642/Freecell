package cs3500.freecell.controller;

import java.io.IOException;
import java.nio.CharBuffer;


/**
 * class representing a Readable that always throws
 * an exception.
 */
public class Unreadable implements Readable {

  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("read did not work.");
  }

}
