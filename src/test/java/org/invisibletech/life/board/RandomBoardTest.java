package org.invisibletech.life.board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RandomBoardTest {

  @Test
  void createBoardMakesRectangularBoard() {
    final var board = new RandomBoard().createBoard(3, 4);
    assertEquals(3, board.length);
    assertEquals(4, board[0].length);
    assertEquals(4, board[1].length);
    assertEquals(4, board[2].length);
  }

}
