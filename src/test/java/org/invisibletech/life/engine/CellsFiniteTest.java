package org.invisibletech.life.engine;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class CellsFiniteTest {

  private static final boolean ALIVE = true;

  @Test
  void computeNextBoardOnDeadBoard() {
    final CellsEngine underTest = new CellsFinite(3, 3, (h, w) -> new boolean[h][w]);

    assertArrayEquals(new boolean[3][3], underTest.computeNextBoard());
  }

  @Test
  void computeNextBoardWithOneLife() {
    final CellsEngine underTest = new CellsFinite(3, 3, (h, w) -> {
      final var b = new boolean[h][w];
      b[0][0] = ALIVE;
      return b;
    });

    assertArrayEquals(new boolean[3][3], underTest.computeNextBoard());
  }

  @Test
  void computeNextBoardForStable2NeighborPattern() {
    final CellsEngine underTest = new CellsFinite(3, 3, (h, w) -> new boolean[][] {
        {
            false, ALIVE, false
        }, {
            ALIVE, false, ALIVE
        }, {
            false, ALIVE, false
        },
    });

    final var expected = new boolean[][] {
        {
            false, ALIVE, false
        }, {
            ALIVE, false, ALIVE
        }, {
            false, ALIVE, false
        }
    };

    assertArrayEquals(expected, underTest.computeNextBoard());
    assertArrayEquals(expected, underTest.computeNextBoard());
  }

  @Test
  void computeNextBoardForStable3NeighborPattern() {
    final CellsEngine underTest = new CellsFinite(3, 3, (h, w) -> new boolean[][] {
        {
            ALIVE, ALIVE, false
        }, {
            ALIVE, ALIVE, false
        }, {
            false, false, false
        },
    });

    final var expected = new boolean[][] {
        {
            ALIVE, ALIVE, false
        }, {
            ALIVE, ALIVE, false
        }, {
            false, false, false
        }
    };

    assertArrayEquals(expected, underTest.currentBoard());
    assertArrayEquals(expected, underTest.computeNextBoard());
  }

  @Test
  void computeNextBoardFor4Neighbors() {
    final CellsEngine underTest = new CellsFinite(3, 3, (h, w) -> new boolean[][] {
        {
            false, ALIVE, false
        }, {
            ALIVE, ALIVE, ALIVE
        }, {
            false, ALIVE, false
        }
    });

    assertArrayEquals(new boolean[][] {
        {
            ALIVE, ALIVE, ALIVE
        }, {
            ALIVE, false, ALIVE
        }, {
            ALIVE, ALIVE, ALIVE
        }
    }, underTest.computeNextBoard());
  }

  @Test
  void computeNextBoardForBurstingRing() {
    final CellsEngine underTest = new CellsFinite(3, 3, (h, w) -> new boolean[][] {
        {
            ALIVE, ALIVE, ALIVE
        }, {
            ALIVE, false, ALIVE
        }, {
            ALIVE, ALIVE, ALIVE
        },
    });

    assertArrayEquals(new boolean[][] {
        {
            ALIVE, false, ALIVE
        }, {
            false, false, false
        }, {
            ALIVE, false, ALIVE
        }
    }, underTest.computeNextBoard());
  }

  @Test
  void computeNextBoardWhereNewLifeJoins() {
    final CellsEngine underTest = new CellsFinite(3, 3, (h, w) -> new boolean[][] {
        {
            ALIVE, ALIVE, false
        }, {
            false, ALIVE, false
        }, {
            false, false, false
        }
    });

    assertArrayEquals(new boolean[][] {
        {
            ALIVE, ALIVE, false
        }, {
            ALIVE, ALIVE, false
        }, {
            false, false, false
        }
    }, underTest.computeNextBoard());
    ;
  }

  @Test
  void computeNext4By4BoardForStable2NeighborPattern() {
    final CellsEngine underTest = new CellsFinite(4, 4, (h, w) -> new boolean[][] {
        {
            false, ALIVE, false, false
        }, {
            ALIVE, false, ALIVE, false
        }, {
            false, ALIVE, false, false
        }, {
            false, false, false, false
        }

    });

    final var expected = new boolean[][] {
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
    assertArrayEquals(expected, underTest.computeNextBoard());
    assertArrayEquals(expected, underTest.computeNextBoard());
  }

}
