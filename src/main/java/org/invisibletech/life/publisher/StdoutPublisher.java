package org.invisibletech.life.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StdoutPublisher implements BoardPublisher {
  public static final Logger LOGGER = LoggerFactory.getLogger(StdoutPublisher.class);

  @Override
  public void render(final boolean[][] board) {
    System.out.print("\n<=========>\n");
    for (var row = 0; row < board.length; row++) {
      for (var col = 0; col < board[row].length; col++) {
        System.out.printf("%c", board[row][col] ? '@' : '*');
      }
      System.out.printf("\n");
    }
  }

}
