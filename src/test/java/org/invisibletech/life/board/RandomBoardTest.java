package org.invisibletech.life.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class RandomBoardTest {
  @Test
  void createBoardMakesRectangularBoard() {
    final var board = new RandomBoardFactory().createBoard(3, 4);
    assertEquals(3, board.getRows());
    assertEquals(4, board.getCols());

    final var matrix = board.matrixOf();
    assertEquals(4, matrix[0].length);
    assertEquals(4, matrix[1].length);
    assertEquals(4, matrix[2].length);

    final var nonNullCells = Arrays.stream(matrix).flatMap(r -> Arrays.stream(r).filter(c -> c != null)).count();
    assertEquals(board.getRows() * board.getCols(), nonNullCells);
  }
}
