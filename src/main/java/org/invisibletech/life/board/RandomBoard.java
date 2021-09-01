package org.invisibletech.life.board;

import java.util.Random;

public class RandomBoard implements BoardFactory {
  @Override
  public boolean[][] createBoard(final int height, final int width) {
    final var rand = new Random();
    final var board = new boolean[height][width];

    for (var row = 0; row < height; row++)
      for (var col = 0; col < width; col++)
        board[row][col] = rand.nextBoolean();

    return board;
  }
}
