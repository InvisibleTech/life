package org.invisibletech.life.board;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class BoardTest {
  @Test
  void enforcesRegularMatrix() {
    assertThrows(IndexOutOfBoundsException.class, () -> new Board(new Boolean[][] {
        {
            true, true
        }, {
            false
        },
    }));
  }

  @Test
  void canCreateWithExpectedState() {
    final var underTest = new Board(new Boolean[][] {
        {
            true, true
        }, {
            false, false
        },
    });

    assertArrayEquals(new Boolean[][] {
        {
            true, true
        }, {
            false, false
        },
    }, underTest.matrixOf());

    assertEquals(2, underTest.getRows());
    assertEquals(2, underTest.getCols());

    assertEquals(true, underTest.cellAt(0, 0));
    assertEquals(false, underTest.cellAt(1, 1));

    assertFalse(underTest.toString().isBlank());
  }

  @Test
  void shouldSupportMutation() {
    final var underTest = new Board(new Boolean[][] {
        {
            true, true
        }, {
            false, false
        },
    });
    assertEquals(false, underTest.cellAt(1, 1));

    underTest.setCell(1, 1, true);
    assertEquals(true, underTest.cellAt(1, 1));
  }

  @Test
  void copyConstructionShouldProduce() {
    final var underTest = new Board(new Boolean[][] {
        {
            true, true
        }, {
            false, false
        },
    });

    final var copy = new Board(underTest);
    assertEquals(underTest, copy);
    assertEquals(underTest.hashCode(), copy.hashCode());

    copy.setCell(1, 1, true);
    assertNotEquals(underTest, copy);
  }
}
