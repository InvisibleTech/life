package org.invisibletech.life.board;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Generate a board using Random booleans.
 */
public class RandomBoardFactory implements BoardFactory {
  @Override
  public Board createBoard(final int height, final int width) {
    final var rand = new Random();

    final var matrix = IntStream.range(0, height).mapToObj(i -> {
      final var row = new Boolean[width];
      Arrays.setAll(row, n -> rand.nextBoolean());
      return row;
    }).toArray(Boolean[][]::new);

    return new Board(matrix);
  }
}
