package org.invisibletech.life.engine;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.invisibletech.life.board.Board;
import org.invisibletech.life.game.FiniteBoardGame;
import org.invisibletech.life.game.Game;
import org.junit.jupiter.api.Test;

class FiniteBoardTest {
  private static final boolean ALIVE = true;

  @Test
  void computeNextBoardDoesNotChangeDimensions() {
    final Game underTest = new FiniteBoardGame(3, 3, (h, w) -> new Board(new Boolean[][] {
        {
            false, false, false
        }, {
            false, false, false
        }, {
            false, false, false
        },
    }));

    final var beforeNext = underTest.currentBoard();
    assertEquals(3, beforeNext.getRows());
    assertEquals(3, beforeNext.getCols());

    final var nextBoard = underTest.computeNextBoard();
    assertEquals(3, nextBoard.getRows());
    assertEquals(3, nextBoard.getCols());
  }

  @Test
  void computeNextBoardOnDeadBoard() {
    final Game underTest = new FiniteBoardGame(3, 3, (h, w) -> new Board(new Boolean[][] {
        {
            false, false, false
        }, {
            false, false, false
        }, {
            false, false, false
        },
    }));

    assertArrayEquals(new Boolean[][] {
        {
            false, false, false
        }, {
            false, false, false
        }, {
            false, false, false
        },
    }, underTest.computeNextBoard().matrixOf());
  }

  @Test
  void computeNextBoardWithOneLife() {
    final Game underTest = new FiniteBoardGame(3, 3, (h, w) -> new Board(new Boolean[][] {
        {
            ALIVE, false, false
        }, {
            false, false, false
        }, {
            false, false, false
        },
    }));

    assertArrayEquals(new Boolean[][] {
        {
            false, false, false
        }, {
            false, false, false
        }, {
            false, false, false
        },
    }, underTest.computeNextBoard().matrixOf());
  }

  @Test
  void computeNextBoardForStable2NeighborPattern() {
    final Game underTest = new FiniteBoardGame(3, 3, (h, w) -> new Board(new Boolean[][] {
        {
            false, ALIVE, false
        }, {
            ALIVE, false, ALIVE
        }, {
            false, ALIVE, false
        },
    }));

    final var expected = new Boolean[][] {
        {
            false, ALIVE, false
        }, {
            ALIVE, false, ALIVE
        }, {
            false, ALIVE, false
        }
    };

    assertArrayEquals(expected, underTest.computeNextBoard().matrixOf());
    assertArrayEquals(expected, underTest.computeNextBoard().matrixOf());
  }

  @Test
  void computeNextBoardForStable3NeighborPattern() {
    final Game underTest = new FiniteBoardGame(3, 3, (h, w) -> new Board(new Boolean[][] {
        {
            ALIVE, ALIVE, false
        }, {
            ALIVE, ALIVE, false
        }, {
            false, false, false
        },
    }));

    final var expected = new Boolean[][] {
        {
            ALIVE, ALIVE, false
        }, {
            ALIVE, ALIVE, false
        }, {
            false, false, false
        }
    };

    assertArrayEquals(expected, underTest.currentBoard().matrixOf());
    assertArrayEquals(expected, underTest.computeNextBoard().matrixOf());
  }

  @Test
  void computeNextBoardFor4Neighbors() {
    final Game underTest = new FiniteBoardGame(3, 3, (h, w) -> new Board(new Boolean[][] {
        {
            false, ALIVE, false
        }, {
            ALIVE, ALIVE, ALIVE
        }, {
            false, ALIVE, false
        }
    }));

    assertArrayEquals(new Boolean[][] {
        {
            ALIVE, ALIVE, ALIVE
        }, {
            ALIVE, false, ALIVE
        }, {
            ALIVE, ALIVE, ALIVE
        }
    }, underTest.computeNextBoard().matrixOf());
  }

  @Test
  void computeNextBoardForBurstingRing() {
    final Game underTest = new FiniteBoardGame(3, 3, (h, w) -> new Board(new Boolean[][] {
        {
            ALIVE, ALIVE, ALIVE
        }, {
            ALIVE, false, ALIVE
        }, {
            ALIVE, ALIVE, ALIVE
        },
    }));

    assertArrayEquals(new Boolean[][] {
        {
            ALIVE, false, ALIVE
        }, {
            false, false, false
        }, {
            ALIVE, false, ALIVE
        }
    }, underTest.computeNextBoard().matrixOf());
  }

  @Test
  void computeNextBoardWhereNewLifeJoins() {
    final Game underTest = new FiniteBoardGame(3, 3, (h, w) -> new Board(new Boolean[][] {
        {
            ALIVE, ALIVE, false
        }, {
            false, ALIVE, false
        }, {
            false, false, false
        }
    }));

    assertArrayEquals(new Boolean[][] {
        {
            ALIVE, ALIVE, false
        }, {
            ALIVE, ALIVE, false
        }, {
            false, false, false
        }
    }, underTest.computeNextBoard().matrixOf());
    ;
  }

  @Test
  void computeNext4By4BoardForStable2NeighborPattern() {
    final Game underTest = new FiniteBoardGame(4, 4, (h, w) -> new Board(new Boolean[][] {
        {
            false, ALIVE, false, false
        }, {
            ALIVE, false, ALIVE, false
        }, {
            false, ALIVE, false, false
        }, {
            false, false, false, false
        }
    }));

    final var expected = new Boolean[][] {
        {
            false, ALIVE, false, false
        }, {
            ALIVE, false, ALIVE, false
        }, {
            false, ALIVE, false, false
        }, {
            false, false, false, false
        }
    };
    assertArrayEquals(expected, underTest.computeNextBoard().matrixOf());
    assertArrayEquals(expected, underTest.computeNextBoard().matrixOf());
  }
}
